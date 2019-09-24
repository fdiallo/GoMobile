/*
package com.lescoccinellesmali.postit.view.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lescoccinellesmali.postit.R;

public class MainActivity extends Activity {
	Button Login, Register, Delete, Upadate;
	int status = 0;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button register = (Button) findViewById(R.id.registrationButton);
		//register.getBackground().setColorFilter(new LightingColorFilter(0xFFFF8877, 0xFFFF8877));
		register.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
//				Intent register = new Intent(getBaseContext(), RegisterActivity.class);
//				startActivity(register);
				Intent register = new Intent(getBaseContext(), ValidateDomainNameActivity.class);
				startActivity(register);
			}
		});
		Button login = (Button) findViewById(R.id.loginButton);
		login.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				status = 1;
				Bundle b = new Bundle();
				b.putInt("status", status);
				Intent i = new Intent(getBaseContext(), LoginActivity.class);
				i.putExtras(b);
				startActivity(i);
			}
		});
	}
}*/
