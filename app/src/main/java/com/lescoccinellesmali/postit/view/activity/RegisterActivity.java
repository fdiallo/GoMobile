/*
package com.lescoccinellesmali.postit.view.activity;
import java.util.HashMap;
import java.util.regex.Pattern;


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
import com.lescoccinellesmali.postit.view.activity.LoginActivity;
import com.lescoccinellesmali.postit.view.activity.MainActivity;

public class RegisterActivity extends Activity {
	//EditText USER_NAME, USER_PHONE, USER_PASS, CON_PASS;
	EditText USER_NAME, USER_PHONE, USER_PASS, CON_PASS, USER_EMAIL;
	String user_name, user_pass, con_pass;
	Button REG, CANCEL;
	Context ctx = this;
	private static String KEY_SUCCESS = "success";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	public static String USERTRACKBYEMAIL = "";
	SessionManager session;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		session = new SessionManager(getApplicationContext());
		session.checkDomainName();
		USER_NAME = (EditText) findViewById(R.id.reg_name);
		USER_PHONE = (EditText) findViewById(R.id.reg_phone_num);
		USER_EMAIL = (EditText) findViewById(R.id.reg_email);
		USER_PASS = (EditText) findViewById(R.id.reg_pass);
		CON_PASS = (EditText) findViewById(R.id.confirm_reg_pass);
		REG = (Button) findViewById(R.id.user_reg_btn);
		CANCEL = (Button) findViewById(R.id.cancel_user_reg_btn);
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
		REG.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View view) {
				String name = "", phone = "", email = "", password = "", con_password = "", domainName = "";
				name = USER_NAME.getText().toString();
				phone = USER_PHONE.getText().toString();
				email = USER_EMAIL.getText().toString();
				password = USER_PASS.getText().toString();
				con_password = CON_PASS.getText().toString();
				if(name.equalsIgnoreCase("") || phone.equalsIgnoreCase("") || email.equalsIgnoreCase("") || password.equalsIgnoreCase("") || con_password.equalsIgnoreCase("")){
					Toast.makeText(getBaseContext(), "All Field Are Required!", Toast.LENGTH_LONG).show();
					return;
				}
				if(!password.equalsIgnoreCase(con_password)){
					Toast.makeText(getBaseContext(), "Passwords do not match!", Toast.LENGTH_LONG).show();
					return;
				}
				final Pattern rfc2822 = Pattern.compile(
						"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
						);
				if (!rfc2822.matcher(email).matches()) {
					Toast.makeText(getBaseContext(), "Invalid Email!", Toast.LENGTH_LONG).show();
					return;
				}
				HashMap<String, String> userDomainName = session.getUserDetails();
				domainName = userDomainName.get(SessionManager.KEY_DOMAIN_NAME);

				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.registerUser(name, phone, email, password, domainName);

				Toast.makeText(getBaseContext(), "JSON Length: " + json.toString(), Toast.LENGTH_LONG).show();
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						String res = json.getString(KEY_SUCCESS);
						if(Integer.parseInt(res) == 1){
							//DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							//userFunction.logoutUser(getApplicationContext());
							//db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));                       
							Toast.makeText(getBaseContext(), json_user.getString(KEY_NAME) + ", Registration Successfull!", Toast.LENGTH_LONG).show();
							USERTRACKBYEMAIL = json_user.getString(KEY_EMAIL);
							Intent dashboard = new Intent(getApplicationContext(), LoginActivity.class);
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							finish();
						}else{
							Toast.makeText(getBaseContext(), "Empty fields and/or invalid email", Toast.LENGTH_LONG).show();
							//Intent register = new Intent(getBaseContext(), RegisterActivity.class);
							//startActivity(register);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
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
