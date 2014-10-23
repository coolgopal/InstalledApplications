package com.example.applicationmonitor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyAlertDialogFragment extends DialogFragment {

	public static MyAlertDialogFragment newInstance(int title)
	{
		MyAlertDialogFragment frag = new MyAlertDialogFragment();
		Bundle args = new Bundle();
		args.putInt("title", title);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		int title = getArguments().getInt("title");
		return new AlertDialog.Builder(getActivity())
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(title)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							((AlertDialogActivity)getActivity()).doPositiveClick();
						}
					})
					.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							((AlertDialogActivity)getActivity()).doNegativeClick();
						}
					})
					.create();
					
		
	}
	
	
	
}
