package com.lescoccinellesmali.postit.view.fragment;


import com.lescoccinellesmali.postit.helper.PostList;
import com.lescoccinellesmali.postit.R;
import com.lescoccinellesmali.postit.model.Post;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.text.method.ScrollingMovementMethod;
public class PostFragment extends Fragment {
	Context ctx ;
	private Post mPost;
	private TextView mTitleField, mAuthorField, mDateField, mTimeField, mLocationField, mDescriptionField, mTypeField;
	private Button mDateButton;
	String post_title, post_author, post_date, post_location,
	post_description, post_year, post_month, post_day, post_hour, post_min, post_type;

	//private int type_flag, event_flag;
	//boolean post_type, post_event;
	//boolean post_event;
	public static final String EXTRA_POST_ID = 
			"com.lescoccinellesmali.postit.post_id";
	private static final String DIALOG_DATE = "date";
	private static final int REQUUEST_DATE = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int postId = (Integer) getArguments().getSerializable(EXTRA_POST_ID);		
		mPost = PostList.get(getActivity()).getPost(postId);
		setHasOptionsMenu(true);
	}
	public static PostFragment newInstance(int postId){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_POST_ID, postId);
		PostFragment fragment = new PostFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode,
			Intent data){
		if(resultCode != Activity.RESULT_OK) return;
		if(resultCode == REQUUEST_DATE){
			String date = (String) data
					.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mPost.setDate(date);
			updateDate();
		}
	}
	private void updateDate(){
		mDateButton.setText(mPost.getDate().toString());
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			if(NavUtils.getParentActivityName(getActivity()) != null){
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_post, parent, false);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			if(NavUtils.getParentActivityName(getActivity()) != null){
				getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}	
		mTitleField = (TextView) v.findViewById(R.id.post_title);
		mTitleField.setText(mPost.getTitle());
		mAuthorField = (TextView) v.findViewById(R.id.post_author);
		mAuthorField.setText(mPost.getAuthor());
		mLocationField = (TextView) v.findViewById(R.id.post_location);
		mLocationField.setText(mPost.getLocation());
		mDescriptionField = (TextView) v.findViewById(R.id.post_description);
		mDescriptionField.setText(mPost.getDescription());
		mDescriptionField.setMovementMethod(new ScrollingMovementMethod());
		mDateField = (TextView) v.findViewById(R.id.txtDate);
	  	mDateField.setText( mPost.getMonth() + "-" + mPost.getDay() + "-" + mPost.getYear());
	  	mTimeField = (TextView) v.findViewById(R.id.txtTime);
	  	mTimeField.setText(mPost.getHour() + ":" + mPost.getMin() + ":" + mPost.getAmPm());
	  	//type_flag = (mPost.getType()) ? 1 : 0;
		mTypeField = (TextView) v.findViewById(R.id.txtType);
		String typeNum = mPost.getType();
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
		
		mTypeField.setText(type);
		return v;
	}
}