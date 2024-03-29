package com.lescoccinellesmali.postit.view.fragment;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import com.lescoccinellesmali.postit.view.fragment.DatePickerFragment;

public class NewDatePickerFragment extends DialogFragment
implements DatePickerDialog.OnDateSetListener {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
	}
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
	}
}