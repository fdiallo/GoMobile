/*
package com.lescoccinellesmali.postit.helper;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.lescoccinellesmali.postit.view.activity.ValidateDomainNameActivity;
import com.lescoccinellesmali.postit.model.Post;
import com.lescoccinellesmali.postit.view.activity.LoginActivity;

public class SessionManager {
	
	SharedPreferences pref;
	Editor editor;
	Context context;
	int PRIVATE_MODE = 0;
	//For shared preferences file name
	private static final String PREF_NAME = "PostItPref";
	//All shared preferences keys
	private static final String IS_LOGIN = "IsLoggedIn";
	private static final String IS_DOMAIN_NAME_SET = "IsDatabaseSet";
	public static final String KEY_DOMAIN_NAME = "domainName";
	//public static final String KEY_NAME = "name";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PASSWORD = "password";
	public SessionManager(Context context){
		this.context = context;
		pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	*/
/*
	 * Store Database on user device as school
	 *//*

	public void storeDomainName( String domainName){
		//Storing login value as true
		editor.putBoolean(IS_DOMAIN_NAME_SET, true);
		//editor.putString(KEY_NAME, name);
		editor.putString(KEY_DOMAIN_NAME, domainName);
		editor.commit();
	}

	public void checkDomainName(){
		if(!isDomainNameSet()){
			//User is not logged in, redirect to Login page
			Intent i = new Intent(context, ValidateDomainNameActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//Add new flag to start now Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//Starting login acitivity
			context.startActivity(i);
		}
	}
	*/
/*
	 * Create login session
	 *//*

	public void createLoginSession( String email){
		//Storing login value as true
		editor.putBoolean(IS_LOGIN, true);
		//editor.putString(KEY_NAME, name);
		editor.putString(KEY_EMAIL, email);
		editor.commit();
	}
	public void checkLogin(){
		if(!isLoggedIn()){
			//User is not logged in, redirect to Login page
			Intent i = new Intent(context, LoginActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//Add new flag to start now Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			//Starting login acitivity
			context.startActivity(i);
		}
	}
	*/
/*
	 * Get stored session data
	 *//*

	public HashMap<String, String>getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		//user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
		user.put(KEY_DOMAIN_NAME, pref.getString(KEY_DOMAIN_NAME, null));
		return user;
	}
	*/
/*
	 * Clear session details
	 *//*

	public void logoutUser(){
		editor.clear();
		editor.commit();
		PostList sPostList = PostList.get(context);
		ArrayList<Post> mPosts = sPostList.getPosts();
		mPosts.clear();
		mPosts = null;
		//After logout, redirect user to login activity
		Intent i = new Intent(context, LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//Add new flag to start now Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//Starting login acitivity
		context.startActivity(i);
	}
	*/
/*
	 * Quick check fo login to get loging state
	 *//*

	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}

	*/
/*
	 * Quick check for database to get database state
	 *//*

	public boolean isDomainNameSet(){
		return pref.getBoolean(IS_DOMAIN_NAME_SET, false);
	}
}*/
