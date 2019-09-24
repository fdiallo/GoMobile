/*
package com.lescoccinellesmali.postit.view.fragment;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lescoccinellesmali.postit.R;
import com.lescoccinellesmali.postit.helper.UserFunctions;
import com.lescoccinellesmali.postit.helper.SessionManager;
import com.lescoccinellesmali.postit.view.activity.LoginActivity;
import com.lescoccinellesmali.postit.view.activity.PostListActivity;
import com.lescoccinellesmali.postit.view.activity.RegisterActivity;

public class UpdateAccountFragment extends Fragment {
	EditText USER_PHONE, USER_EMAIL, USER_PASS, CON_PASS;
	//String user_phone, user_email, user_pass;
	Button CANCEL, UPDATE, DELETE;
	private static String KEY_SUCCESS = "success";
	SessionManager session;
	public UpdateAccountFragment() {}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceStare) {
		View v = inflater.inflate(R.layout.fragment_update_account, container,
				false);
		session = new SessionManager(getActivity());
		USER_PHONE = (EditText) v.findViewById(R.id.update_user_phone_number);
		//USER_EMAIL = (EditText) v.findViewById(R.id.update_email);
		USER_PASS = (EditText) v.findViewById(R.id.update_pass);
		CON_PASS = (EditText) v.findViewById(R.id.confirm_update_pass);
		CANCEL = (Button) v.findViewById(R.id.user_cancel);
		UPDATE = (Button) v.findViewById(R.id.user_update);
		DELETE = (Button) v.findViewById(R.id.user_delete);
		session.checkLogin();
		UPDATE.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HashMap<String, String> user = session.getUserDetails();
				String userTrackByEmail = user.get(SessionManager.KEY_EMAIL);
				String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
				String phone = USER_PHONE.getText().toString();
				String password = USER_PASS.getText().toString();
				String conPass = CON_PASS.getText().toString();
				if(phone == "" || password == "" ){
					Toast.makeText(getActivity(), "Empty Field!", Toast.LENGTH_LONG).show();
					return;
				}
				if(!password.equalsIgnoreCase(conPass)){
					USER_PASS.setText("");
					CON_PASS.setText("");
					Toast.makeText(getActivity(), "Passwords do not match!", Toast.LENGTH_LONG).show();
					return;
				}
				
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.updateUser(domainName, userTrackByEmail, phone, password);
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						String res = json.getString(KEY_SUCCESS);
						if(Integer.parseInt(res) == 1) {
							SessionManager sessionManager = new SessionManager(getActivity());
							session.logoutUser();
							Intent dashboard = new Intent(getActivity(),
									LoginActivity.class);
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							getActivity().finish();
						} else {
							Toast.makeText(
									getActivity(),
									"Failed to update - " + "Success: "
											+ json.getString(KEY_SUCCESS),
									Toast.LENGTH_LONG).show();
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		DELETE.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HashMap<String, String> user = session.getUserDetails();
				String userTrackByEmail = user.get(SessionManager.KEY_EMAIL);
				String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.deleteUser(domainName, userTrackByEmail);
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							SessionManager sessionManager = new SessionManager(getActivity());
							session.logoutUser();
							Intent dashboard = new Intent(getActivity(),
									RegisterActivity.class);
							startActivity(dashboard);
							getActivity().finish();
						} else {
							Toast.makeText(
									getActivity(),
									"Failed to delete account - " + "Success: "
											+ json.getString(KEY_SUCCESS),
									Toast.LENGTH_LONG).show();
							// Intent register = new Intent(getBaseContext(),
							// RegisterActivity.class);
							// startActivity(register);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		CANCEL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
							Intent dashboard = new Intent(getActivity(),
									PostListActivity.class);
							startActivity(dashboard);
							getActivity().finish();
			}
		});
		return v;
	}

}
*/
