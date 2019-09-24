package com.lescoccinellesmali.postit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lescoccinellesmali.postit.model.CheckDomainName;

import org.json.JSONException;
import org.json.JSONObject;

public class ValidateDomainNameActivity extends Activity {

    private EditText SCHOOL_URL;
    private Button CANCEL, NEXT;
    private String hostName = "";
    private static String KEY_SUCCESS = "success";
    SessionManager session;
    public String getHostName(){
        return hostName;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_domain);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Session Manager
        session = new SessionManager(getApplicationContext());
        SCHOOL_URL = (EditText) findViewById(R.id.school_url);
        CANCEL = (Button) findViewById(R.id.cancel_user_domain_btn);
        NEXT = (Button)findViewById(R.id.user_validate_domain_btn);
        CANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboard = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(dashboard);
                finish();
            }
        });
        NEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String school_url = "";
                school_url = SCHOOL_URL.getText().toString();
                if (school_url.equalsIgnoreCase("")) {
                    Toast.makeText(getBaseContext(), "School URL Is Required!", Toast.LENGTH_LONG).show();
                    return;
                }
                CheckDomainName checkDomainName = new CheckDomainName();

                boolean validURL = checkDomainName.isValidDomainName(school_url);
                if(validURL){

                    hostName = checkDomainName.getDomainName(school_url);
                    if(!hostName.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Valid URL: " + hostName, Toast.LENGTH_LONG).show();
                        //Do something
                        //Send request to the server
                        UserFunctions userFunction = new UserFunctions();
                        JSONObject json = userFunction.validateDomain(hostName);
                        try {
                            if (json.getString(KEY_SUCCESS) != null) {
                                String res = json.getString(KEY_SUCCESS);
                                if (Integer.parseInt(res) == 1) {
                                    //DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                                    ////JSONObject json_user = json.getJSONObject("user");
                                    //userFunction.logoutUser(getApplicationContext());
                                    //db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));
                                    Toast.makeText(getBaseContext(), " Database created Successfully!", Toast.LENGTH_LONG).show();
                                    //USERTRACKBYEMAIL = json_user.getString(KEY_EMAIL);
                                    session.storeDomainName(hostName);

                                    Intent dashboard = new Intent(getApplicationContext(), RegisterActivity.class);
                                    dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(dashboard);
                                    finish();
                                } else {
                                    Toast.makeText(getBaseContext(), "Server error...", Toast.LENGTH_LONG).show();
                                    //Intent register = new Intent(getBaseContext(), RegisterActivity.class);
                                    //startActivity(register);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Does Not Exist!", Toast.LENGTH_LONG).show();
                        return;
                    }


                }else{
                    Toast.makeText(getBaseContext(), "Invalid URL!", Toast.LENGTH_LONG).show();
                    return;
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
}
