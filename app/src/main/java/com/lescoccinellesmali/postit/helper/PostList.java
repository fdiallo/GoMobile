package com.lescoccinellesmali.postit.helper;
import java.util.ArrayList;
import com.lescoccinellesmali.postit.model.Post;
import android.content.Context;

public class PostList {
	private static PostList sPostList;
	private ArrayList<Post> mPosts;

	private PostList(Context appContext){
		mPosts = new ArrayList<Post>();
	}

	public static PostList get(Context c){
		if(sPostList == null){
			sPostList = new PostList(c.getApplicationContext());
		}
		return sPostList;
	}

	public void addPost(Post p){
		mPosts.add(p);
	}
	public ArrayList<Post> getPosts(){
		return mPosts;
	}


	public Post getPost(int id){
		for(Post p : mPosts){
			if(p.getId() == id){
				return p;
			}
		}
		return null;
	}
}

//
//package com.lescoccinellesmali.postit;
//		import java.util.ArrayList;
//		import java.util.HashMap;
//		import java.util.List;
//
//		import org.apache.http.NameValuePair;
//		import org.apache.http.message.BasicNameValuePair;
//		import org.json.JSONArray;
//		import org.json.JSONException;
//		import org.json.JSONObject;
//
//		import com.lescoccinellesmali.postit.model.Post;
//
//		import android.content.Context;
//
//public class PostList {
//	private static PostList sPostList;
//	private ArrayList<Post> mPosts;
//	private static final String TAG = "PostList";
//	//private DatabaseOperations mDOB;
//	JSONParser jParser = new JSONParser();
//	private static String url_all_products = "http://10.0.2.2/~fallaye/PostIt/get_all_posts.php";
//	//private static String url_all_products = "http://lescoccinellesmali.org/get_all_posts.php";
//	private static final String TAG_SUCCESS = "success";
//	private static final String TAG_POSTS = "posts";
//	private static final String TAG_PID = "pid";
//	private static final String TAG_TITLE = "title";
//	private static final String TAG_AUTHOR = "author";
//	private static final String TAG_LOCATION = "location";
//	private static final String TAG_DESCRIPTION = "description";
//
//	private static final String TAG_YEAR = "year";
//	private static final String TAG_MONTH = "month";
//	private static final String TAG_DAY = "day";
//	private static final String TAG_HOUR = "hour";
//	private static final String TAG_MIN = "min";
//	private static final String TAG_DATE = "date";
//
//	private static final String TAG_TYPE = "type";
//	JSONArray products = null;
//	SessionManager session;
//	public PostList(Context appContext){
//		mPosts = new ArrayList<Post>();
//		session = new SessionManager(appContext);
//		HashMap<String, String> user = session.getUserDetails();
//		String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("domain_name", domainName));
//		JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", params);
//		//Log.d("All Posts: ", json.toString());
//		try {
//			int success = json.getInt(TAG_SUCCESS);
//			if (success == 1) {
//				products = json.getJSONArray(TAG_POSTS);
//				for (int i = 0; i < products.length(); i++) {
//					JSONObject c = products.getJSONObject(i);
//					int id = Integer.parseInt(c.getString(TAG_PID));
//					String title = c.getString(TAG_TITLE);
//					String author = c.getString(TAG_AUTHOR);
//					String location = c.getString(TAG_LOCATION);
//					String description = c.getString(TAG_DESCRIPTION);
//					String year = c.getString(TAG_YEAR);
//					String month = c.getString(TAG_MONTH);
//					String day = c.getString(TAG_DAY);
//					String hour = c.getString(TAG_HOUR);
//					String min = c.getString(TAG_MIN);
//					String date = c.getString(TAG_DATE);
//					String type = c.getString(TAG_TYPE);
//					Post p = new Post();
//					p.setId(id);
//					p.setTitle(title);
//					p.setAuthor(author);
//					p.setLocation(location);
//					p.setDescription(description);
//					p.setYear(year);
//					p.setMonth(month);
//					p.setDay(day);
//					p.setHour(hour);
//					p.setMin(min);
//					p.setDate(date);
//					//p.setType(type.equals("1"));
//					p.setType(type);
//					mPosts.add(p);
//				}
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}
//	/*public boolean savePosts(){
//		try{
//			mDOB.putPostInformation(mDOB);
//			//Log.d(TAG, "Posts saved to database");
//			return true;
//		} catch(Exception e){
//			Log.e(TAG, "Error saving posts", e);
//			return false;
//		}
//	}*/
//	public static PostList get(Context c){
//		if(sPostList == null){
//			sPostList = new PostList(c.getApplicationContext());
//		}
//		return sPostList;
//	}
//	public void addPost(Post p){
//		mPosts.add(p);
//	}
//	public ArrayList<Post> getPosts(){
//		return mPosts;
//	}
//	public Post getPost(int id){
//		for(Post p : mPosts){
//			if(p.getId() == id){
//				return p;
//			}
//		}
//		return null;
//	}
//}