/*
package com.lescoccinellesmali.postit.view.activity;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;

import com.lescoccinellesmali.postit.R;
import com.lescoccinellesmali.postit.helper.SessionManager;
import com.lescoccinellesmali.postit.helper.UserFunctions;
import com.lescoccinellesmali.postit.helper.CheckDomainName;

public class LoginActivity extends Activity {
	//EditText USERNAME, USERPASS;
	EditText USEREMAIL, USERPASS, DOMAIN_NAME;
	//String useremail, userpass;
	Button LOGIN, CANCEL;
	Context CTX = this;
	SessionManager session;
	private static String KEY_SUCCESS = "success";
	//    private static String KEY_UID = "uid";
//    private static String KEY_NAME = "name";
//    private static String KEY_PASS = "password";
//    private static String KEY_EMAIL = "email";
//    private static String KEY_CREATED_AT = "created_at";
	//public static String USERTRACKBYEMAIL = "";
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//Session Manager
		session = new SessionManager(getApplicationContext());
		DOMAIN_NAME = (EditText)findViewById(R.id.loginSchoolURL);
		LOGIN = (Button) findViewById(R.id.user_login);
		USEREMAIL = (EditText) findViewById(R.id.user_email);
		DOMAIN_NAME.requestFocus();
		USERPASS = (EditText) findViewById(R.id.user_password);

		CANCEL = (Button) findViewById(R.id.user_login_cancel);
		CANCEL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent dashboard = new Intent(getApplicationContext(), PostListActivity.class);
//				dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(dashboard);
//				finish();
				Intent dashboard = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(dashboard);
				finish();
			}
		});
		LOGIN.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				String email = "", password = "", school_url = "", domain_name = "";
				school_url = DOMAIN_NAME.getText().toString();
				email = USEREMAIL.getText().toString();
				password = USERPASS.getText().toString();

				if(school_url.equalsIgnoreCase("") || email.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
					Toast.makeText(getBaseContext(), "All Field Are Required!", Toast.LENGTH_LONG).show();
					return;
				}
				CheckDomainName checkDomainName = new CheckDomainName();
				boolean validURL = checkDomainName.isValidDomainName(school_url);
				if(validURL) {
					domain_name = checkDomainName.getDomainName(school_url);
					if (!domain_name.isEmpty()) {
						Toast.makeText(getBaseContext(), "Valid URL: " + domain_name, Toast.LENGTH_LONG).show();
						UserFunctions userFunction = new UserFunctions();
						JSONObject json = userFunction.loginUser(domain_name, email, password);
						try {
							if (json.getString(KEY_SUCCESS) != null) {
								String res = json.getString(KEY_SUCCESS);
								if (Integer.parseInt(res) == 1) {
									//DatabaseHandler db = new DatabaseHandler(getApplicationContext());
									JSONObject json_user = json.getJSONObject("user");
									//userFunction.logoutUser(getApplicationContext());
									//db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));
									//USERTRACKBYEMAIL = json_user.getString(KEY_EMAIL);
									//Creating user login session
									session.storeDomainName(domain_name);
									session.createLoginSession(email);
									Intent dashboard = new Intent(getApplicationContext(), PostListActivity.class);
									dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(dashboard);
									finish();
								} else {
									Toast.makeText(getBaseContext(), "Login Failed! Invalid credentials ", Toast.LENGTH_LONG).show();
									USEREMAIL.setText("");
									USERPASS.setText("");
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}else
					Toast.makeText(getBaseContext(), "Invalid Domain Name: " + domain_name, Toast.LENGTH_LONG).show();
				}else
				Toast.makeText(getBaseContext(), "Wrong Domain Name: " + domain_name, Toast.LENGTH_LONG).show();
			}
		});
	}

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
