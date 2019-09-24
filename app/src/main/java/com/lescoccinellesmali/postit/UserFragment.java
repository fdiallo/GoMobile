//package com.lescoccinellesmali.postit;
//
//
//import com.lescoccinellesmali.postit.model.User;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//public class UserFragment extends Fragment {
//
//	private User mUser;
//	private EditText mUsernameField;
//	private EditText mPasswordField;
//	private EditText mPhoneField;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState){
//		super.onCreate(savedInstanceState);
//		mUser = new User();
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
//			Bundle savedInstanceState){
//		View v = inflater.inflate(R.layout.fragment_user, parent, false);
//
//		mUsernameField = (EditText) v.findViewById(R.id.user_name);
//		mUsernameField.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				mUser.setUsername(s.toString());				
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//			}
//		});
//		
//		mPasswordField = (EditText) v.findViewById(R.id.user_password);
//		mPasswordField.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				mUser.setPassword(s.toString());
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//			}
//		});
//		
//		return v;
//	}
//}
