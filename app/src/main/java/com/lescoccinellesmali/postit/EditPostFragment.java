package com.lescoccinellesmali.postit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.ProgressDialog;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.lescoccinellesmali.postit.model.Post;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EditPostFragment extends ListFragment {
	// public EditPostFragment(){}
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle saveInstanceStare){
	// View v = inflater.inflate(R.layout.fragment_edit_post, container, false);
	// return v;
	// }
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	ArrayList<Post> mPosts;
	//private static final String TAG = "PostListFragment";
	private boolean mSubtitleVisible;
	JSONParser jParser = new JSONParser();
	ArrayList<HashMap<String, String>> productsList;
	//private static String url_user_posts = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/get_user_posts.php";
	private static String url_user_posts = "http://www.fallaye.com/get_user_posts.php";
	// private static String url_user_products =
	// "http://lescoccinellesmali.com/get_user_posts.php";
	
	//private static String url_delete_post = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/delete_post.php";
	private static String url_delete_post = "http://www.fallaye.com/delete_post.php";
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
	private static final String TAG_DATE = "date";
	private static final String TAG_TYPE = "type";
	JSONArray products = null;
	// EditText USER_NAME, USER_EMAIL, USER_PASS;
	// String user_name, user_email, user_pass;
	Button EDIT, DELETE;
	//private static String KEY_SUCCESS = "success";
	SessionManager session;
	//public static final String EXTRA_POST_ID = "com.lescoccinellesmali.postit.post_id";
	int postId;
	String user_email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//postId = (Integer) getArguments().getSerializable(EXTRA_POST_ID);
		session = new SessionManager(getActivity());
		session.checkLogin();
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.posts_title);
		mPosts = new ArrayList<Post>();
		new LoadAllProducts().execute();
		setRetainInstance(true);
		mSubtitleVisible = false;
	}

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (mSubtitleVisible) {
				getActivity().getActionBar().setSubtitle(R.string.subtitle);
			}
		}
		// session = new SessionManager(getActivity());
		// session.checkLogin();
		// EDIT = (Button) v.findViewById(R.id.button_edit_post);
		// DELETE = (Button) v.findViewById(R.id.button_delete_post);
		//
		// EDIT.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// }
		// })
		return v;
	}

	// @Override
	// public void onListItemClick(ListView l, View v, int position, long id) {
	// Post p = ((PostAdapter) getListAdapter()).getItem(position);
	// Intent i = new Intent(getActivity(), PostPagerActivity.class);
	// i.putExtra(PostFragment.EXTRA_POST_ID, p.getId());
	// startActivity(i);
	// }
	protected class PostAdapter extends ArrayAdapter<Post> {
		public PostAdapter(ArrayList<Post> posts) {
			super(getActivity(), 0, posts);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.fragment_edit_post, null);
			}
			Post p = getItem(position);
			TextView titleTextView = (TextView) convertView
					.findViewById(R.id.post_list_item_titleTextView);
			titleTextView.setText("Title: " + p.getTitle());

			session = new SessionManager(getActivity());
			session.checkLogin();
			EDIT = (Button) convertView.findViewById(R.id.button_edit_post);
			DELETE = (Button) convertView.findViewById(R.id.button_delete_post);
			//HashMap<String, String> user = session.getUserDetails();
			//String userTrackByEmail = user.get(SessionManager.KEY_EMAIL);
			String title = p.getTitle();
			//UserFunctions userFunction = new UserFunctions();
			EDIT.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Post p = ((PostAdapter) getListAdapter()).getItem(position);
					Intent i = new Intent(getActivity(),
							PostUpdatePagerActivity.class);
					i.putExtra(PostUpdateFragment.EXTRA_POST_ID, p.getId());
					startActivity(i);
				}
			});
			
			DELETE.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Post p = ((PostAdapter) getListAdapter()).getItem(position);
					postId = p.getId();
					new DeletePost().execute();
				}
			});
			return convertView;
		}
	}
	public void upatePostFragmentView(ArrayList<Post> arrayPosts) {
		if (arrayPosts == null) {
			Toast.makeText(getActivity(), "No record found!", Toast.LENGTH_LONG)
					.show();
			return;
		}
		PostAdapter adapter = new PostAdapter(arrayPosts);
		setListAdapter(adapter);
		setRetainInstance(true);
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

	class LoadAllProducts extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		protected String doInBackground(String... args) {
			HashMap<String, String> user = session.getUserDetails();
			String user_email = user.get(SessionManager.KEY_EMAIL);
			String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("domain_name", domainName));
			params.add(new BasicNameValuePair("user_email", user_email));
			JSONObject json = jParser.makeHttpRequest(url_user_posts, "POST",
					params);
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					products = json.getJSONArray(TAG_POSTS);
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
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
						String date = c.getString(TAG_DATE);
						String typeNum = c.getString(TAG_TYPE);
						//String postType = txtType.getText().toString();
						String type;
						if(typeNum.equals("1")){
							type = "Club";
						}
						else if(typeNum.equals("2")){
							type = "Event";
						}
						else if(typeNum.equals("3")){
							type = "Job";
						}
						else if(typeNum.equals("4")){
							type = "Housing";
						}else{
							type = "Event";
						}
						
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
						p.setDate(date);
						//p.setType(type.equals("1"));
						p.setType(type);
						mPosts.add(p);
					}
				} else {
					Intent i = new Intent(getActivity(), NewPostActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(String file_url) {
			PostAdapter adapter = new PostAdapter(mPosts);
			setListAdapter(adapter);
		}
//		Context mAppContext;
//		public LoadAllProducts(Context context) {
//			mAppContext = context;
//		}
//		private LoadAllProducts sPostList;
//		public LoadAllProducts get(Context c) {
//			if (sPostList == null) {
//				sPostList = new LoadAllProducts(c.getApplicationContext());
//			}
//			return sPostList;
//		}
		public ArrayList<Post> getPosts() {
			return mPosts;
		}
	}
	class DeletePost extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			 pDialog = new ProgressDialog(getActivity());
			 pDialog.setMessage("Deleting Post..");
			 Toast.makeText(getActivity(), " Deleting Post. PID: " + postId, Toast.LENGTH_LONG).show();
			 pDialog.setIndeterminate(false);
			 pDialog.setCancelable(true);
			 pDialog.show();
		}
		protected String doInBackground(String... args) {
			//Toast.makeText(getActivity(), " Deleting Post. PID: " + postId, Toast.LENGTH_LONG).show();
			String pid = "" + postId;
			HashMap<String, String> user = session.getUserDetails();
			String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("domain_name", domainName));
			params.add(new BasicNameValuePair("pid", pid));
			params.add(new BasicNameValuePair("user_email", user_email));
			JSONObject json = jsonParser.makeHttpRequest(url_delete_post,
					"POST", params);
			Log.d("Params", params.toString());
			Log.d("PID", pid);		
			Log.d("Delete Response", json.toString());
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Intent dashboard = new Intent(getActivity(), PostListActivity.class);
                    dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(dashboard);
                    //finish();
					getActivity().finish();
				} else {
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}

//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		View view = getActivity().getCurrentFocus();
//		if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
//			int scrcoords[] = new int[2];
//			view.getLocationOnScreen(scrcoords);
//			float x = ev.getRawX() + view.getLeft() - scrcoords[0];
//			float y = ev.getRawY() + view.getTop() - scrcoords[1];
//			if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
//				((InputMethodManager)this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getActivity().getWindow().getDecorView().getApplicationWindowToken()), 0);
//		}
//		return super.getActivity().dispatchTouchEvent(ev);
//	}
}