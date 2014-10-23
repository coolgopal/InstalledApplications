package com.example.applicationmonitor;

import java.util.ArrayList;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat;

public class MyBroadCastReceiver extends BroadcastReceiver {

	Context context;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = context;
		
		if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
		{
			Intent newIntent = new Intent(context, MainActivity.class);
			newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(newIntent);
		}
		
		if(intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED))
		{
			// Add notification to notification bar
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("Application Monitor")
			.setContentText("Package Installed!!!");

			NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			mNotifyMgr.notify(1, mBuilder.build());
			
			// show the dialog
			if(isUsingHighRiskPermissions(intent.getPackage()))
			{
				Intent newIntent = new Intent(context, AlertDialogActivity.class);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(newIntent);
			}
		}
	}
	
	private boolean isUsingHighRiskPermissions(String pkgName)
	{
		PackageInfo pkgInfo;
		ArrayList<String> usingPermissions = new ArrayList<String>();
		
		try {
			pkgInfo = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_PERMISSIONS);
			
			for(int i = 0; i < pkgInfo.requestedPermissions.length; i++)
				usingPermissions.add(pkgInfo.requestedPermissions[i].toString());

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(usingPermissions.contains("android.permission.READ_PHONE_STATE") 
				|| usingPermissions.contains("android.permission.MODIFY_PHONE_STATE") 
				|| usingPermissions.contains("android.permission.RECORD_AUDIO") 
				|| usingPermissions.contains("android.permission.PROCESS_OUTGOING_CALLS") 
				|| usingPermissions.contains("android.permission.ACCOUNT_MANAGER") 
				|| usingPermissions.contains("android.permission.BRICK") 
				|| usingPermissions.contains("android.permission.CLEAR_APP_CACHE") 
				|| usingPermissions.contains("android.permission.CLEAR_APP_USER_DATA") 
				|| usingPermissions.contains("android.permission.CONTROL_LOCATION_UPDATES") 
				|| usingPermissions.contains("android.permission.DELETE_CACHE_FILES") 
				|| usingPermissions.contains("android.permission.DELETE_PACKAGES") 
				|| usingPermissions.contains("android.permission.HARDWARE_TEST") 
				|| usingPermissions.contains("android.permission.KILL_BACKGROUND_PROCESSES") 
				|| usingPermissions.contains("android.permission.SIGNAL_PERSISTENT_PROCESSES") 
				|| usingPermissions.contains("android.permission.UPDATE_DEVICE_STATS") 
				|| usingPermissions.contains("android.permission.WRITE_SECURE_SETTINGS") 
				|| usingPermissions.contains("android.permission.WRITE_SETTINGS") 
				|| usingPermissions.contains("android.permission.WRITE_SMS") 
				|| usingPermissions.contains("android.permission.READ_SMS") 
				|| usingPermissions.contains("android.permission.CALL_PHONE") 
				|| usingPermissions.contains("android.permission.SYSTEM_ALERT_WINDOW") 
				|| usingPermissions.contains("android.permission.READ_SOCIAL_STREAM") 
				|| usingPermissions.contains("android.permission.AUTHENTICATE_ACCOUNTS") 
				|| usingPermissions.contains("android.permission.READ_ATTACHMENT") 
				|| usingPermissions.contains("android.permission.RECEIVE_MMS") )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
