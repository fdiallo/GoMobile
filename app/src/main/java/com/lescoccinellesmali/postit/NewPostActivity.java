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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
public class NewPostActivity extends Activity implements OnClickListener {
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	EditText inputTitle;
	EditText inputAuthor;
	EditText inputLocation;
	EditText inputDescription;
	Button btnDatePicker, btnTimePicker;
	EditText txtDate, txtTime, txtType;
	Button btnCreatePost, btnCancel;
	private int mYear, mMonth, mDay, mHour, mMinute;
	Date mDate;
	//private static String url_create_post = "http://10.0.2.2/~faldiall/go-mobile/POSTITPHP/create_post.php";
	//private static String url_create_post = "http://lescoccinellesmali.org/create_post.php";
	private static String url_create_post = "http://www.fallaye.com/create_post.php";
	//private static String url_create_post = "http://10.0.2.2/go-mobile/POSTITPHP/create_post.php";
	private static final String TAG_SUCCESS = "success"; 
	String user_email;
	private static final String TAG = "DialogDemo";
	private Button showDialogButton;
	private Context mContext;
	SessionManager session;
    String title = "", author = "", location = "", description = "", amPm = "";
    String[] dayString;
    String[] timeString;
    String postType;

    private int selected = 0;
    private int buffKey = 0; // add buffer value



    @Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "NewPostActivity start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_post);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		session = new SessionManager(getApplicationContext());
		session.checkLogin();
		HashMap<String, String> user = session.getUserDetails();
		user_email = user.get(SessionManager.KEY_EMAIL);
		inputTitle = (EditText) findViewById(R.id.post_title);
		inputAuthor = (EditText) findViewById(R.id.post_author);
		inputLocation = (EditText) findViewById(R.id.post_location);
		inputDescription = (EditText) findViewById(R.id.post_description);
		btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
		btnTimePicker = (Button) findViewById(R.id.btnTimePicker);
		txtDate = (EditText) findViewById(R.id.txtDate);
		txtTime = (EditText) findViewById(R.id.txtTime);
		txtType = (EditText)findViewById(R.id.txtType);
		btnDatePicker.setOnClickListener((android.view.View.OnClickListener) this);
		btnTimePicker.setOnClickListener((android.view.View.OnClickListener) this);

		mContext = this; // to use all around this class
		showDialogButton = 
				(Button) findViewById(R.id.show_dialog_btn);
		showDialogButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialogButtonClick();

			}
		});


		btnCreatePost = (Button) findViewById(R.id.createPostButton);
		btnCreatePost.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			    if( getUserInput()) {
                    new CreateNewPost().execute();
                }
			}
		});

		btnCancel = (Button) findViewById(R.id.post_cancel);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent dashboard = new Intent(getApplicationContext(),
						//PostListActivity.class);
               // dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//startActivity(dashboard);
				finish();
			}
		});
	}

	private void showDialogButtonClick() {
		Log.i(TAG, "show Dialog ButtonClick");
		AlertDialog.Builder builder = 
				new AlertDialog.Builder(mContext);
		builder.setTitle("Show dialog");

		final CharSequence[] choiceList = 
			{"Club", "Event" , "Job" , "Housing" };

		builder.setSingleChoiceItems(
				choiceList, 
				selected, 
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(
							DialogInterface dialog, 
							int which) {
						//set to buffKey instead of selected 
						//(when cancel not save to selected)
						buffKey = which;
					}
				})
				.setCancelable(false)
				.setPositiveButton("Done", 
						new DialogInterface.OnClickListener() 
				{
					@Override
					public void onClick(DialogInterface dialog, 
							int which) {
						Log.d(TAG,"Which value="+which);
						Log.d(TAG,"Selected value="+buffKey);
						Toast.makeText(
								mContext, 
								"Select "+choiceList[buffKey], 
								Toast.LENGTH_SHORT
								)
								.show();
						//set buff to selected
						selected = buffKey;
						txtType.setText(choiceList[buffKey]);
					}
				}
						)
						.setNegativeButton("Cancel", 
								new DialogInterface.OnClickListener() 
						{
							@Override
							public void onClick(DialogInterface dialog, 
									int which) {
								Toast.makeText(
										mContext, 
										"Cancel click", 
										Toast.LENGTH_SHORT
										)
										.show();
							}
						}
								);
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onClick(View v) {
		if (v == btnDatePicker) {
			// Process to get Current Date
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			// Launch Date Picker Dialog
			DatePickerDialog dpd = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// Display Selected date in textbox
					txtDate.setText(dayOfMonth + "-"
							+ (monthOfYear + 1) + "-" + year);
				}
			}, mYear, mMonth, mDay);
			dpd.show();
		}
		if (v == btnTimePicker) {
			// Process to get Current Time
			final Calendar c = Calendar.getInstance();
			mHour = c.get(Calendar.HOUR_OF_DAY);
			mMinute = c.get(Calendar.MINUTE);
			// Launch Time Picker Dialog
			TimePickerDialog tpd = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay,
						int minute) {
					// Display Selected time in textbox
					String hour = "";
					String ampm = "";
					if(hourOfDay > 12){
						hour = String.valueOf(hourOfDay - 12);
                        ampm = "PM";
					}else{
						hour = "" + hourOfDay;
                        ampm = "AM";
					}
					String min = "";
					if(minute < 10){
						min = "0" + minute;
					} 
					else{
						min = "" + minute;
					}

					txtTime.setText(hour + ":" + min + ":" + ampm);
				}
			}, mHour, mMinute, false);
			tpd.show();
		}
	}

	private boolean getUserInput(){

	    if(!TextUtils.isEmpty(inputTitle.getText().toString())) {
            title = inputTitle.getText().toString();
        }else {
            Toast.makeText(this, "Title Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(inputAuthor.getText().toString())){
            author = inputAuthor.getText().toString();
        }else {
            Toast.makeText(this, "Author Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(inputLocation.getText().toString())){
            location = inputLocation.getText().toString();
        }else {
            Toast.makeText(this, "Location Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(inputDescription.getText().toString())){
            description = inputDescription.getText().toString();
        }else {
            Toast.makeText(this, "Description Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(txtDate.getText().toString())){
            dayString = txtDate.getText().toString().split("-");
        }else {
            Toast.makeText(this, "Date Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(txtTime.getText().toString())){
            timeString = txtTime.getText().toString().split(":");
        }else {
            Toast.makeText(this, "Time Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!TextUtils.isEmpty(txtType.getText().toString())){
            postType = txtType.getText().toString();
        }else {
            Toast.makeText(this, "Type Can't Be Empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

	class CreateNewPost extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewPostActivity.this);
			pDialog.setMessage("Creating Post..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}


		protected String doInBackground(String... args) {



			String day = dayString[0];
			String month = dayString[1];
			String year = dayString[2];
			if(Integer.parseInt(month) < 10){
				month = "0" + month;
			}
			Log.d("Month: ", month);
			if(Integer.parseInt(day) < 10){
				day  = "0" + mDay;
			}
			Log.d("Day: ", day + "");

			String hour = timeString[0];

			String ampm = timeString[2];

			String min = timeString[1];
			String dateStr = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + "00";
			//mDate = new Date();
			String date = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				mDate = sdf.parse(dateStr);
				date = sdf.format(mDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			Log.d("DateString: ", date);

			String type = "";
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
				type = "";
			}
			//
			//			if(title == "")
			//				Toast.makeText(getBaseContext(), "Post Title is required!\n", Toast.LENGTH_LONG).show();
			//			if(author == "")
			//				Toast.makeText(getBaseContext(), "Post Author is requires!\n", Toast.LENGTH_LONG).show();
			//			if(location == "")
			//				Toast.makeText(getBaseContext(), "Post Locationis required!\n", Toast.LENGTH_LONG).show();
			//			if(description == "")
			//				Toast.makeText(getBaseContext(), "Post Description is requires!\n", Toast.LENGTH_LONG).show();
			//			if(day == "" || month == "" || year == "")
			//				Toast.makeText(getBaseContext(), "Please select Post date", Toast.LENGTH_LONG).show();
			//			if(hour == "" || min == "")
			//				Toast.makeText(getBaseContext(), "Please select Post time", Toast.LENGTH_LONG).show();

			//if(title != "" && author != "" && location != ""){
			if(title.equalsIgnoreCase("") || author.equalsIgnoreCase("") || location.equalsIgnoreCase("") ||
					description.equalsIgnoreCase("") || date.equalsIgnoreCase("") || type.equalsIgnoreCase("")){
				Toast.makeText(getBaseContext(), "All Field Are Required!", Toast.LENGTH_LONG).show();
				return "";

			}
			HashMap<String, String> user = session.getUserDetails();
			String domainName = user.get(SessionManager.KEY_DOMAIN_NAME);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("domain_name", domainName));
			params.add(new BasicNameValuePair("title", title));
			params.add(new BasicNameValuePair("author", author));
			params.add(new BasicNameValuePair("location", location));
			params.add(new BasicNameValuePair("description", description));
			params.add(new BasicNameValuePair("day", day));
			params.add(new BasicNameValuePair("month", month));
			params.add(new BasicNameValuePair("year", year));
			params.add(new BasicNameValuePair("hour", hour));
			params.add(new BasicNameValuePair("min", min));
            params.add(new BasicNameValuePair("ampm", ampm));
			params.add(new BasicNameValuePair("date", date));
			params.add(new BasicNameValuePair("type", type));
			params.add(new BasicNameValuePair("user_email", user_email));
			JSONObject json = jsonParser.makeHttpRequest(url_create_post, "POST", params);
			Log.d("Params", params.toString());
			Log.d("domain_name", domainName);
			Log.d("Title", title);
			Log.d("Author", author);
			Log.d("Location", location);
			Log.d("Description", description);
			Log.d("day", day);
			Log.d("month", month);
			Log.d("year", year);
			Log.d("hour", hour);
			Log.d("min", min);
            Log.d("amapm", ampm);
			Log.d("type", type);
			Log.d("Create Response", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {

				   // PostList.get(getApplicationContext()).getPosts();
					Intent intent = new Intent(getApplicationContext(), PostListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
				} else {
					//Toast.makeText(getBaseContext(), "Empty fields and/or invalid email", Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//}
			return null;

		}
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
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
}