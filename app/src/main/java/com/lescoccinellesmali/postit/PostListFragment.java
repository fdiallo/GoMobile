package com.lescoccinellesmali.postit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.lescoccinellesmali.postit.model.Post;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PostListFragment extends Fragment {
	private static String url_all_posts;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_POSTS = "posts";
	private static final String TAG_PID = "pid";
	private static final String TAG_TITLE = "title";
	private static final String TAG_AUTHOR = "author";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_YEAR = "year";
	private static final String TAG_MONTH = "month";
	private static final String TAG_DAY = "day";
	private static final String TAG_HOUR = "hour";
	private static final String TAG_MIN = "min";
    private static final String TAG_AMPM = "ampm";
	private static final String TAG_DATE = "date";
	private static final String TAG_TYPE = "type";
	private boolean mSubtitleVisible;
	private ProgressDialog pDialog;
	ArrayList<Post> mPosts;
	JSONParser jParser = new JSONParser();
	JSONArray posts = null;
	JSONObject json; 
	PostAdapter adapter;
	ListView lv;
	Button btnLoadMore;
	private static int current_page = 1;
	int success;
	SessionManager session;
    PostList sPostList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		session = new SessionManager(getActivity());
		session.checkLogin();
		getActivity().setTitle(R.string.posts_title);
		sPostList = PostList.get(getActivity());
		mPosts = sPostList.getPosts();
		new LoadAllProducts().execute();
		setRetainInstance(true);
		mSubtitleVisible = false;
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {

		// View v = super.onCreateView(inflater, parent, savedInstanceState);
		View view = inflater.inflate(R.layout.post_list_view, parent, false);
		lv = (ListView) view.findViewById(R.id.myPostList);
		// lv.setAdapter(new PostAdapter(mPosts));
		btnLoadMore = new Button(getActivity());
		btnLoadMore.setText("Load More Posts");
		btnLoadMore.setMaxWidth(20);
		lv.addFooterView(btnLoadMore);
//		lv.setAdapter(new PostAdapter(mPosts));
		btnLoadMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (success != 1) {
					lv.removeFooterView(btnLoadMore);
					btnLoadMore.setVisibility(View.GONE);
				} else {
					++current_page;
					Log.i("PostListFragment", current_page + " ");
					Log.i("url_all_posts", url_all_posts);
					new LoadAllProducts().execute();
				}
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				//lv.setAdapter(adapter);
				Post p = (Post) parent.getItemAtPosition(position);
				//Post p = ((PostAdapter) getAdapter()).getItem(position);
				// Post p = ((PostAdapter) getListAdapter()).getItem(position);
				Intent i = new Intent(getActivity(), PostPagerActivity.class);
				i.putExtra(PostFragment.EXTRA_POST_ID, p.getId());
				startActivity(i);
			}
		});
		lv.setAdapter(new PostAdapter(mPosts));
		return view;
	}

	// @Override
	// public void onListItemClick(ListView l, View v, int position, long id) {
	// Post p = ((PostAdapter) getAdapter()).getItem(position);
	// Intent i = new Intent(getActivity(), PostPagerActivity.class);
	// i.putExtra(PostFragment.EXTRA_POST_ID, p.getId());
	// startActivity(i);
	// }
	class LoadAllProducts extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Creating Post..");
			// Toast.makeText(getActivity(), " Deleting Post. PID: " + postId,
			// Toast.LENGTH_LONG).show();
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			//url_all_posts = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/get_all_posts.php?page=" + current_page;
			url_all_posts = "http://www.fallaye.com/get_all_posts.php?page="
					+ current_page;
			HashMap<String, String> user = session.getUserDetails();
			String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("domain_name", domainName));
            params.add(new BasicNameValuePair("page", current_page + ""));
			json = jParser.makeHttpRequest(url_all_posts, "POST", params);
			try {
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					posts = json.getJSONArray(TAG_POSTS);
					for (int i = 0; i < posts.length(); i++) {
						JSONObject c = posts.getJSONObject(i);
						int id = Integer.parseInt(c.getString(TAG_PID));
						String title = c.getString(TAG_TITLE);
						String author = c.getString(TAG_AUTHOR);
						String location = c.getString(TAG_LOCATION);
						String description = c.getString(TAG_DESCRIPTION);
						String year = c.getString(TAG_YEAR);
						String month = c.getString(TAG_MONTH);
						String day = c.getString(TAG_DAY);
						String hour = c.getString(TAG_HOUR);
						String min = c.getString(TAG_MIN);
                        String ampm = c.getString(TAG_AMPM);
						String date = c.getString(TAG_DATE);
						String type = c.getString(TAG_TYPE);
						Post p = new Post();
						p.setId(id);
						p.setTitle(title);
						p.setAuthor(author);
						p.setLocation(location);
						p.setDescription(description);
						p.setYear(year);
						p.setMonth(month);
						p.setDay(day);
						p.setHour(hour);
						p.setMin(min);
                        p.setAmPm(ampm);
						p.setDate(date);
						p.setType(type);
						if(!mPosts.contains(p)) {
							mPosts.add(p);
						}
						//else{}
						
					}
				} else {
					// Intent i = new Intent(getActivity(),
					// NewPostActivity.class);
					// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
			// PostAdapter adapter = new PostAdapter(mPosts);
			// setAdapter(adapter);
			// ListView lv = getListView();
			// lv.setAdapter(new PostAdapter(mPosts));
//			int currentPosition = lv.getFirstVisiblePosition();
//			lv.setSelectionFromTop(currentPosition + 1, 1);
			//lv.setAdapter(new PostAdapter(mPosts));
			int currentPosition = lv.getFirstVisiblePosition();
			adapter = new PostAdapter(mPosts);
			lv.setAdapter(adapter);
			lv.setSelectionFromTop(currentPosition + 4, 1);
			pDialog.dismiss();
		}
		// Context mAppContext;
		// public LoadAllProducts(Context context) {
		// mAppContext = context;
		// }
		// private LoadAllProducts sPostList;
		//
		// public LoadAllProducts get(Context c){
		// if(sPostList == null){
		// sPostList = new LoadAllProducts(c.getApplicationContext());
		// }
		// return sPostList;
		// }
		// public ArrayList<Post> getPosts(){
		// return mPosts;
		// }
	}

	public void upatePostFragmentView(ArrayList<Post> arrayPosts) {
		if (arrayPosts == null) {
			Toast.makeText(getActivity(), "No record found!", Toast.LENGTH_LONG)
					.show();
			return;
		}
		// PostAdapter adapter = new PostAdapter(arrayPosts);
		// setAdapter(adapter);
		// setRetainInstance(true);
		lv.setAdapter(new PostAdapter(arrayPosts));
	}

	protected class PostAdapter extends ArrayAdapter<Post> {
		private List<Post> posts;

		public PostAdapter(ArrayList<Post> posts) {
			super(getActivity(), 0, posts);
		}

		public PostAdapter(Context context, ArrayList<Post> posts) {
			super(context, R.layout.list_item_post, posts);
			this.posts = posts;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_post, null);
			}
			Post p = getItem(position);
			TextView titleTextView = (TextView) convertView
					.findViewById(R.id.post_list_item_titleTextView);
			titleTextView.setText("Title: " + p.getTitle());
			TextView authorTextView = (TextView) convertView
					.findViewById(R.id.post_list_item_authorTextView);
			authorTextView.setText("Posted by: " + p.getAuthor());
			TextView dateTextView = (TextView) convertView
					.findViewById(R.id.post_list_item_dateTextView);
			dateTextView
					.setText("Date: " + p.getDay() + "-" + p.getMonth() + "-"
							+ p.getYear() + "  " + p.getHour() + ":"
							+ p.getMin() + ":"
                            + p.getAmPm());
			TextView locationTextView = (TextView) convertView
					.findViewById(R.id.post_list_item_locationTextView);
			locationTextView.setText("Location: " + p.getLocation());
			return convertView;
		}
	}
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_post_list, menu);
/******** Do not delete this code******/
//		MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
//		if (mSubtitleVisible && showSubtitle != null) {
//			showSubtitle.setTitle(R.string.hide_subtitle);
//		}
	}

	@TargetApi(11)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_new_post:
			//Post post = new Post();
			//PostList.get(getActivity()).addPost(post);
			Intent i = new Intent(getActivity(), NewPostActivity.class);
			//i.putExtra(PostFragment.EXTRA_POST_ID, post.getId());
			startActivityForResult(i, 0);
			return true;
			/*** Do not delete this code  ****/
//		case R.id.menu_item_show_subtitle:
//			if (getActivity().getActionBar().getSubtitle() == null) {
//				getActivity().getActionBar().setSubtitle(R.string.subtitle);
//				mSubtitleVisible = true;
//				item.setTitle(R.string.hide_subtitle);
//			} else {
//				getActivity().getActionBar().setSubtitle(null);
//				mSubtitleVisible = false;
//				item.setTitle(R.string.show_subtitle);
//			}
//			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 100) {
			Intent intent = getActivity().getIntent();
			getActivity().finish();
			startActivity(intent);
		}
	}
}