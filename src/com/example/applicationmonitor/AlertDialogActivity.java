package com.example.applicationmonitor;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;

public class AlertDialogActivity extends Activity {

	void showDialog()
	{
		DialogFragment newFragment = MyAlertDialogFragment.newInstance(android.R.string.dialog_alert_title);
		newFragment.show(getFragmentManager(), "Dialog");
	}
	
	public void doPositiveClick()
	{
		finish();
	}
	
	public void doNegativeClick()
	{
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		showDialog();
	}
}
