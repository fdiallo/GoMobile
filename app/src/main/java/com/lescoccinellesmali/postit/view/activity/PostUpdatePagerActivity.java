/*
package com.lescoccinellesmali.postit.view.activity;
import java.util.ArrayList;

import com.lescoccinellesmali.postit.R;
import com.lescoccinellesmali.postit.helper.PostList;
import com.lescoccinellesmali.postit.model.Post;
import com.lescoccinellesmali.postit.view.fragment.PostUpdateFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class PostUpdatePagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Post> mPosts;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		mPosts = PostList.get(this).getPosts();
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			@Override
			public int getCount() {
				return mPosts.size();
			}
			@Override
			public Fragment getItem(int pos) {
				Post post = mPosts.get(pos);
				return PostUpdateFragment.newInstance(post.getId());
			}
		});
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			//mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int pos) {
				Post post = mPosts.get(pos);
				if(post.getTitle() != null){
					setTitle(post.getTitle());
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		int postId =  (Integer) getIntent()
				.getSerializableExtra(PostUpdateFragment.EXTRA_POST_ID);
		for(int i = 0; i < mPosts.size(); i++){
			if(mPosts.get(i).getId() == postId){
				mViewPager.setCurrentItem(i);
				break;
			}
		}
	}
	//Do not delete this code
//	Post mPost;
//	public void onCheckboxClicked(View view) {
//		boolean checked = ((CheckBox) view).isChecked();
//		switch(view.getId()) {
//		case R.id.studyGroups:
//			if (checked){
//				this.mPost.setType(true);
//			}
//			else{
//				this.mPost.setType(false);
//			}
//			break;
//		}
//	}	
}*/
