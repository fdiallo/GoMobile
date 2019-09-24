package com.lescoccinellesmali.postit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.lescoccinellesmali.postit.model.Post;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;


public class PostUpdateFragment extends Fragment {
	Context ctx;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private Post mPost;
	private EditText mTitleField;
	private EditText mAuthorField;
	private EditText mDateField;
	private EditText mTimeField;
	private Button mDateButton;
	private EditText mLocationField;
	private EditText mDescriptionField, txtType;
	// private Button mPostButton;
	String post_title, post_author, post_date, post_location, post_description,
			post_year, post_month, post_day, post_hour, post_min, type;
	Date mDate;
	Button btnDatePicker, btnTimePicker;
	//EditText txtDate, txtTime;
	private int mYear, mMonth, mDay, mHour, mMinute;
	//private int type_flag, event_flag;
	boolean post_type, post_event;
	public static final String EXTRA_POST_ID = "com.lescoccinellesmali.postit.post_id";
	//private static final String DIALOG_DATE = "date";
	private static final int REQUUEST_DATE = 0;
	Button CANCEL, UPDATE;

	//private static String url_update_post = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/update_post.php";
	private static String url_update_post = "http://www.fallaye.com/update_post.php";
	private static final String TAG_SUCCESS = "success";
	private String user_email;
	private String domainName;
	int postId;

	private static final String TAG = "DialogDemo";
	private Button showDialogButton;
	private Context mContext;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		postId = (Integer) getArguments().getSerializable(EXTRA_POST_ID);
		mPost = PostList.get(getActivity()).getPost(postId);
		setHasOptionsMenu(true);

	}

	public static PostUpdateFragment newInstance(int postId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_POST_ID, postId);
		PostUpdateFragment fragment = new PostUpdateFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		if (resultCode == REQUUEST_DATE) {
			String date = (String) data
					.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mPost.setDate(date);
			//updateDate();
		}
	}

	private void updateDate() {
		mDateButton.setText(mPost.getDate().toString());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if (NavUtils.getParentActivityName(getActivity()) != null) {
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
		View v = inflater.inflate(R.layout.fragment_update_post, parent, false);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}
		mTitleField = (EditText) v.findViewById(R.id.post_title);
		mTitleField.setText(mPost.getTitle());
		mTitleField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		mAuthorField = (EditText) v.findViewById(R.id.post_author);
		mAuthorField.setText(mPost.getAuthor());
		mAuthorField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		mLocationField = (EditText) v.findViewById(R.id.post_location);
		mLocationField.setText(mPost.getLocation());
		mLocationField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		mDescriptionField = (EditText) v.findViewById(R.id.post_description);
		mDescriptionField.setText(mPost.getDescription());
		mDescriptionField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		mDateField = (EditText) v.findViewById(R.id.txtDate);
		mDateField.setText(mPost.getMonth() + "-" + mPost.getDay() + "-"
				+ mPost.getYear());
		mDateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		mTimeField = (EditText) v.findViewById(R.id.txtTime);
		mTimeField.setText(mPost.getHour() + ":" + mPost.getMin());
		mTimeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		//type_flag = (mPost.getType()) ? 1 : 0;
		String typeNum = mPost.getType();
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
		txtType = (EditText) v.findViewById(R.id.txtType);
		txtType.setText(type);
		txtType.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					hideKeyboard(v);
				}
			}
		});
		CANCEL = (Button) v.findViewById(R.id.post_update_cancel);
		CANCEL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), PostListActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		SessionManager session = new SessionManager(getActivity());
		session.checkLogin();
		HashMap<String, String> user = session.getUserDetails();
		user_email = user.get(SessionManager.KEY_EMAIL);
		domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
		// mTitleField = (EditText) v.findViewById(R.id.post_title);
		// inputAuthor = (EditText) v.findViewById(R.id.post_author);
		// inputLocation = (EditText) v.findViewById(R.id.post_location);
		// inputDescription = (EditText) v.findViewById(R.id.post_description);
		btnDatePicker = (Button) v.findViewById(R.id.btnDatePicker);
		btnTimePicker = (Button) v.findViewById(R.id.btnTimePicker);
		btnDatePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				// Launch Date Picker Dialog
				DatePickerDialog dpd = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
												  int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								mDateField.setText(dayOfMonth + "-"
										+ (monthOfYear + 1) + "-" + year);
							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});
		btnTimePicker.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Process to get Current Time
				final Calendar c = Calendar.getInstance();
				mHour = c.get(Calendar.HOUR_OF_DAY);
				mMinute = c.get(Calendar.MINUTE);
				// Launch Time Picker Dialog
				TimePickerDialog tpd = new TimePickerDialog(getActivity(),
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay,
												  int minute) {
								// Display Selected time in textbox
								mTimeField.setText(hourOfDay + ":" + minute);
							}
						}, mHour, mMinute, false);
				tpd.show();
			}
		});
		UPDATE = (Button) v.findViewById(R.id.post_update);
		UPDATE.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new UpdatePost().execute();
			}
		});
		mContext = getActivity();
		showDialogButton =
				(Button) v.findViewById(R.id.show_dialog_btn);
		showDialogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialogButtonClick();
			}
		});
		return v;
	}

	private int selected = 0;
	private int buffKey = 0; // add buffer value 
	private void showDialogButtonClick() {
		Log.i(TAG, "show Dialog ButtonClick");
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("Show dialog");
		final CharSequence[] choiceList = {"Club", "Event" , "Job" , "Housing" };
		builder.setSingleChoiceItems(choiceList, selected, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//set to buffKey instead of selected 
				//(when cancel not save to selected)
				buffKey = which;
			}
		}).setCancelable(false).setPositiveButton("Done", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d(TAG,"Which value="+which);
				Log.d(TAG,"Selected value="+buffKey);
				Toast.makeText(mContext, "Select "+choiceList[buffKey], Toast.LENGTH_SHORT).show();
				//set buff to selected
				selected = buffKey;
				//txtType = 
				txtType.setText(choiceList[buffKey]);
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(mContext, "Cancel click", Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	class UpdatePost extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Updating Post..");
			Toast.makeText(getActivity(), " Updating Post. PID: " + postId, Toast.LENGTH_LONG).show();
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... args) {

			//Toast.makeText(getActivity(), " Updating Post. PID: " + postId, Toast.LENGTH_LONG).show();
			String pid = "" + postId;
			String title = mTitleField.getText().toString();
			String author = mAuthorField.getText().toString();
			String location = mLocationField.getText().toString();
			String description = mDescriptionField.getText().toString();
			String[] dayString = mDateField.getText().toString().split("-");
			String day = dayString[0];
			String month = dayString[1];
			String year = dayString[2];
			if (Integer.parseInt(month) < 10) {
				month = "0" + month;
			}
			Log.d("Month: ", month);
			if (Integer.parseInt(day) < 10) {
				day = "0" + mDay;
			}
			Log.d("Day: ", day + "");
			String[] timeString = mTimeField.getText().toString().split(":");
			String hour = timeString[0];
			String min = timeString[1];
			String dateStr = year + "-" + month + "-" + day + " " + hour + ":"
					+ min + ":" + "00";
			// mDate = new Date();
			String date = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				mDate = sdf.parse(dateStr);
				date = sdf.format(mDate) + ":00";
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			Log.d("DateString: ", date);


			String postType = txtType.getText().toString();
			String type;
			if(postType.equals("Club")){
				type = "1";
			}
			else if(postType.equals("Event")){
				type = "2";
			}
			else if(postType.equals("Job")){
				type = "3";
			}
			else if(postType.equals("Housing")){
				type = "4";
			}else{
				type = "1";
			}

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pid", pid));
			params.add(new BasicNameValuePair("title", title));
			params.add(new BasicNameValuePair("author", author));
			params.add(new BasicNameValuePair("location", location));
			params.add(new BasicNameValuePair("description", description));
			params.add(new BasicNameValuePair("day", day));
			params.add(new BasicNameValuePair("month", month));
			params.add(new BasicNameValuePair("year", year));
			params.add(new BasicNameValuePair("hour", hour));
			params.add(new BasicNameValuePair("min", min));
			params.add(new BasicNameValuePair("date", date));
			params.add(new BasicNameValuePair("type", type));
			//HashMap<String, String> user = session.getUserDetails();
			//String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
			params.add(new BasicNameValuePair("domain_name", domainName));
			params.add(new BasicNameValuePair("user_email", user_email));
			JSONObject json = jsonParser.makeHttpRequest(url_update_post,
					"POST", params);
			Log.d("Params", params.toString());
			Log.d("PID", pid);
			Log.d("Title", title);
			Log.d("Author", author);
			Log.d("Location", location);
			Log.d("Description", description);
			Log.d("day", day);
			Log.d("month", month);
			Log.d("year", year);
			Log.d("hour", hour);
			Log.d("min", min);
			Log.d("date", date);
			Log.d("type", type);
			Log.d("user_email", user_email);
			//Log.d("StudyGroup", study_group);
			Log.d("Create Response", json.toString());
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					getActivity().finish();
				} else {
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}

	public void hideKeyboard(View view) {
		InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}