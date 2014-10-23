package com.example.applicationmonitor;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ApkInfo extends Activity{
	
	TextView appLabel, permissions;
	PackageInfo packageInfo;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apkinfo);
		
		findViewsById();
		AppData appData = (AppData) getApplicationContext();
		packageInfo = appData.getPackageInfo();
		
		setValues();
	}
	
	private void findViewsById()
	{
		appLabel = (TextView) findViewById(R.id.applabel);
		permissions = (TextView) findViewById(R.id.req_permission);
	}
	
	private void setValues()
	{
		appLabel.setText(getPackageManager().getApplicationLabel(packageInfo.applicationInfo));
		if(packageInfo.requestedPermissions != null)
		{
			permissions.setText(getPermissions(packageInfo.requestedPermissions));
		}
		else
		{
			permissions.setText("-");
		}
	}
	
	private String getPermissions(String[] requestedPermissions)
	{
		String permission = "";
		for(int i = 0; i < requestedPermissions.length; i++)
		{
			permission = permission + requestedPermissions[i] + ",\n";
		}
		return permission;
	}
	
	public void uninstallApk(View view)
	{
		Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
		intent.setData(Uri.parse("package:" + packageInfo.packageName));
		startActivity(intent);
	}
}
