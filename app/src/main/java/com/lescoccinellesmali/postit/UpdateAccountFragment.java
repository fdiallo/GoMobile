package com.lescoccinellesmali.postit;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


    @TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceStare) {
		View v = inflater.inflate(R.layout.fragment_update_account, container,
				false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
		session = new SessionManager(getActivity());
        final HashMap<String, String> user = session.getUserDetails();
		USER_PHONE = (EditText) v.findViewById(R.id.update_user_phone_number);

        USER_PHONE.setText(user.get(SessionManager.KEY_PHONE));
        USER_PHONE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
		//USER_EMAIL = (EditText) v.findViewById(R.id.update_email);
		USER_PASS = (EditText) v.findViewById(R.id.update_pass);

        USER_PASS.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

		CON_PASS = (EditText) v.findViewById(R.id.confirm_update_pass);
        CON_PASS.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

		CANCEL = (Button) v.findViewById(R.id.user_cancel);
		UPDATE = (Button) v.findViewById(R.id.user_update);
		DELETE = (Button) v.findViewById(R.id.user_delete);
		session.checkLogin();
		UPDATE.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//HashMap<String, String> user = session.getUserDetails();
				String userTrackByEmail = user.get(SessionManager.KEY_EMAIL);
				String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
                String phone, password, conPass;
				if(!TextUtils.isEmpty(USER_PHONE.getText().toString())) {
                    phone = USER_PHONE.getText().toString();
                }else {
                    Toast.makeText(getActivity(), "Empty Phone!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!TextUtils.isEmpty(USER_PASS.getText().toString())) {
                    password = USER_PASS.getText().toString();
                }else {
                    Toast.makeText(getActivity(), "Empty Password!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!TextUtils.isEmpty(CON_PASS.getText().toString())) {
                    conPass = CON_PASS.getText().toString();
                }else {
                    Toast.makeText(getActivity(), "Empty Password Confirmation !", Toast.LENGTH_LONG).show();
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
                deleteAccount();
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


    private void deleteAccount(){
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


    private void dialogAlert(String title, String message ){

        AlertDialog.Builder builder = new AlertDialog.Builder(getTargetFragment().getActivity());
        builder.setTitle(R.string.app_name);
        builder.setMessage("Do you want to stop ?");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
