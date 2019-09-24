/*
package com.lescoccinellesmali.postit.view.activity;
import java.util.ArrayList;

import com.lescoccinellesmali.postit.R;
import com.lescoccinellesmali.postit.helper.PostList;
import com.lescoccinellesmali.postit.model.Post;
import com.lescoccinellesmali.postit.view.fragment.PostListFragment;
import com.lescoccinellesmali.postit.view.fragment.TopFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

public class PostListActivity extends SingleFragmentActivity implements TopFragment.TopFragmentListenerInterface {
	ArrayList<Post> mPosts;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_fragment);
	}

	@Override
	protected Fragment createFragment() {
		return new PostListFragment();
	}

	protected Fragment createTopFragment() {
		return new TopFragment();
	}

	@Override
	public void onSearchButtonClicked(String query) {
		PostList sPostList = PostList.get(getApplication());
		ArrayList<Post> searchPostList = sPostList.getPosts();
		mPosts = new ArrayList<Post>();
		for(Post p : searchPostList){
			if(p.getTitle().toLowerCase().contains(query.toLowerCase()) || p.getAuthor().toLowerCase().contains(query.toLowerCase()) || p.getLocation().toLowerCase().contains(query.toLowerCase()) || p.getDescription().toLowerCase().contains(query.toLowerCase())){
				mPosts.add(p);
			}
		}
		PostListFragment postListFragment = (PostListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
		if(postListFragment != null){
			postListFragment.upatePostFragmentView(mPosts);
		}
	}
	@Override
	public void onEventButtonClicked() {
		PostList sPostList = PostList.get(getApplication());
		ArrayList<Post> searchPostList = sPostList.getPosts();
		mPosts = new ArrayList<Post>();
		for(Post p : searchPostList){
			if(p.getType().equals("2")){
				mPosts.add(p);
			}
		}
		PostListFragment postListFragment = (PostListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
		if(postListFragment != null){
			postListFragment.upatePostFragmentView(mPosts);
		}
	}
	@Override
	public void onStudyGroupButtonClicked() {
		PostList sPostList = PostList.get(getApplication());
		ArrayList<Post> searchPostList = sPostList.getPosts();
		mPosts = new ArrayList<Post>();
		for(Post p : searchPostList){
			if(p.getType().equals("1")){
				mPosts.add(p);
			}
		}
		PostListFragment postListFragment = (PostListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
		if(postListFragment != null){
			postListFragment.upatePostFragmentView(mPosts);
		}
	}
	@Override
	public void onTodayButtonClicked() {
		PostList sPostList = PostList.get(getApplication());
		ArrayList<Post> searchPostList = sPostList.getPosts();
		mPosts = new ArrayList<Post>();
		for(Post p : searchPostList){
			if(p.getType().equals("3")){
				mPosts.add(p);
			}
		}
		PostListFragment postListFragment = (PostListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
		if(postListFragment != null){
			postListFragment.upatePostFragmentView(mPosts);
		}
	}
	@Override
	public void onThisWeekButtonClicked() {
		PostList sPostList = PostList.get(getApplication());
		ArrayList<Post> searchPostList = sPostList.getPosts();
		mPosts = new ArrayList<Post>();
		for(Post p : searchPostList){
			if(p.getType().equals("4")){
				mPosts.add(p);
			}
		}
		PostListFragment postListFragment = (PostListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
		if(postListFragment != null){
			postListFragment.upatePostFragmentView(mPosts);
		}
	}
	//For dimmissing keyboard on edittext boxes
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		View view = getCurrentFocus();
		if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
			int scrcoords[] = new int[2];
			view.getLocationOnScreen(scrcoords);
			float x = ev.getRawX() + view.getLeft() - scrcoords[0];
			float y = ev.getRawY() + view.getTop() - scrcoords[1];
			if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
				((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
		}
		return super.dispatchTouchEvent(ev);
	}
}*/
