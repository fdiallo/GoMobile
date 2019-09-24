//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//
//import com.lescoccinellesmali.postit.model.Post;
//
//class LoadAllPosts extends AsyncTask<String, String, String> {
//	private String url, message;
//	public 	LoadAllPosts(String url, String message){
//		this.url = url;
//		this.message = message;
//	}
//	
//	@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			pDialog = new ProgressDialog(getActivity());
//			pDialog.setMessage("Creating Post..");
//			// Toast.makeText(getActivity(), " Deleting Post. PID: " + postId,
//			// Toast.LENGTH_LONG).show();
//			pDialog.setIndeterminate(false);
//			pDialog.setCancelable(true);
//			pDialog.show();
//		}
//
//		protected String doInBackground(String... args) {
//			List<NameValuePair> params = new ArrayList<NameValuePair>();
//			url_all_products = "http://10.0.2.2/~fallaye/PostIt/get_all_posts.php?page="
//					+ current_page;
//			json = jParser.makeHttpRequest(url_all_products, "GET", params);
//			try {
//				success = json.getInt(TAG_SUCCESS);
//				if (success == 1) {
//					products = json.getJSONArray(TAG_POSTS);
//					for (int i = 0; i < products.length(); i++) {
//						JSONObject c = products.getJSONObject(i);
//						int id = Integer.parseInt(c.getString(TAG_PID));
//						String title = c.getString(TAG_TITLE);
//						String author = c.getString(TAG_AUTHOR);
//						String location = c.getString(TAG_LOCATION);
//						String description = c.getString(TAG_DESCRIPTION);
//						String year = c.getString(TAG_YEAR);
//						String month = c.getString(TAG_MONTH);
//						String day = c.getString(TAG_DAY);
//						String hour = c.getString(TAG_HOUR);
//						String min = c.getString(TAG_MIN);
//						String date = c.getString(TAG_DATE);
//						String study_group = c.getString(TAG_STUDYGROUP);
//						Post p = new Post();
//						p.setId(id);
//						p.setTitle(title);
//						p.setAuthor(author);
//						p.setLocation(location);
//						p.setDescription(description);
//						p.setYear(year);
//						p.setMonth(month);
//						p.setDay(day);
//						p.setHour(hour);
//						p.setMin(min);
//						p.setDate(date);
//						p.setStudyGroup(study_group.equals("1"));
//						mPosts.add(p);
//					}
//				} else {
//					// Intent i = new Intent(getActivity(),
//					// NewPostActivity.class);
//					// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					// startActivity(i);
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		protected void onPostExecute(String file_url) {
//			// PostAdapter adapter = new PostAdapter(mPosts);
//			// setAdapter(adapter);
//			// ListView lv = getListView();
//			// lv.setAdapter(new PostAdapter(mPosts));
//			int currentPosition = lv.getFirstVisiblePosition();
//			lv.setSelectionFromTop(currentPosition + 1, 1);
//			pDialog.dismiss();
//		}
//		// Context mAppContext;
//		// public LoadAllProducts(Context context) {
//		// mAppContext = context;
//		// }
//		// private LoadAllProducts sPostList;
//		//
//		// public LoadAllProducts get(Context c){
//		// if(sPostList == null){
//		// sPostList = new LoadAllProducts(c.getApplicationContext());
//		// }
//		// return sPostList;
//		// }
//		// public ArrayList<Post> getPosts(){
//		// return mPosts;
//		// }
//	}
