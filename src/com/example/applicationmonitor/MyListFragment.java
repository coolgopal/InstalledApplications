package com.example.applicationmonitor;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MyListFragment extends ListFragment {
    
	int listType;
    ArrayList<PackageInfo> packageListCategory0 = null;
    ArrayList<PackageInfo> packageListCategory1 = null;
    ArrayList<PackageInfo> packageListCategory2 = null;
	
    public MyListFragment()
    {
    	super();
    }
    
    public MyListFragment(int listType)
    {
    	super();
    	this.listType = listType;
    }

    @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    ArrayList<PackageInfo> filteredPackageList = getInstalledApps();
	    switch(listType)
	    {
	    	case 0:
	    		packageListCategory0 = getPackageListCategory0(filteredPackageList);
	    	    ApkAdapter apkAdapter0 = new ApkAdapter(getActivity(), packageListCategory0, getActivity().getPackageManager());
	    	    setListAdapter(apkAdapter0);
	    		break;
	    	case 1:
	    		packageListCategory1 = getPackageListCategory1(filteredPackageList);
	    	    ApkAdapter apkAdapter1 = new ApkAdapter(getActivity(), packageListCategory1, getActivity().getPackageManager());
	    	    setListAdapter(apkAdapter1);
	    		break;
	    	case 2:
	    		packageListCategory2 = getPackageListCategory2(filteredPackageList);
	    	    ApkAdapter apkAdapter2 = new ApkAdapter(getActivity(), packageListCategory2, getActivity().getPackageManager());
	    	    setListAdapter(apkAdapter2);
	    		break;
	    	default:
	    		break;
	    }
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		  PackageInfo packageInfo = (PackageInfo) l.getItemAtPosition(position);
			AppData appData = (AppData) getActivity().getApplicationContext();
	        appData.setPackageInfo(packageInfo);
	 
	        Intent appInfo = new Intent(getActivity(), ApkInfo.class);
	        startActivity(appInfo);
	  }
	  
	  /**
	   * Returns whether the given packageInfo is a system package or not.
	   * User-installed packages (Market or otherwise) should not be denoted 
	   * as system packages.
	   * @param pkgInfo
	   * @return
	   */
	  private boolean isSystemPackage(PackageInfo pkgInfo)
	  {
		  return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)? true : false;
	  }
	  
	  private ArrayList<PackageInfo> getInstalledApps()
	  {
		  ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
		  List<PackageInfo> packageList = getActivity().getPackageManager().getInstalledPackages(PackageManager.GET_PERMISSIONS);

		  for(int i = 0; i < packageList.size(); i++)
		  {
			  PackageInfo pInfo = packageList.get(i); 
			  
			  if(isSystemPackage(pInfo))
				  continue;
			  res.add(pInfo);
		  }
		  
		  return res;
	  }
	  
	  private boolean isPackageCategory0(PackageInfo pInfo)
	  {
			if(pInfo.requestedPermissions == null)
			{
				Log.d("Application Monitor", pInfo.requestedPermissions + " permission is null");
				return false;
			}

		  ArrayList<String> usingPermissions = new ArrayList<String>();
		  
		  for(int i = 0; i < pInfo.requestedPermissions.length; i++)
			  usingPermissions.add(pInfo.requestedPermissions[i].toString());
		  
		  if(usingPermissions.contains("android.permission.BROADCAST_SMS")
				  || usingPermissions.contains("android.permission.CALL_PHONE")
				  || usingPermissions.contains("android.permission.CALL_PRIVILEGED")
				  || usingPermissions.contains("android.permission.INTERNET")
				  || usingPermissions.contains("android.permission.SEND_SMS"))
		  {			  
			  return true;
		  }
		  else
		  {
			  return false;
		  }
	  }
	  
	  private ArrayList<PackageInfo> getPackageListCategory0(ArrayList<PackageInfo> pkgList)
	  {
		  ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
		  //PackageManager pm = getActivity().getPackageManager();
		  
		  for(int i = 0; i < pkgList.size(); i++)
		  {
			  PackageInfo pInfo;
/*			try {
*/				pInfo = pkgList.get(i);
				//pInfo = pm.getPackageInfo(pkgList.get(i).packageName, PackageManager.GET_PERMISSIONS);
				
				  if(isPackageCategory0(pInfo))
					  res.add(pInfo);

/*			} 
			catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
*/		  }
		  
		  return res;
	  }

	  private boolean isPackageCategory1(PackageInfo pInfo)
	  {
			if(pInfo.requestedPermissions == null)
			{
				Log.d("Application Monitor", pInfo.requestedPermissions + " permission is null");
				return false;
			}

		  ArrayList<String> usingPermissions = new ArrayList<String>();
		  
		  for(int i = 0; i < pInfo.requestedPermissions.length; i++)
			  usingPermissions.add(pInfo.requestedPermissions[i].toString());
		  
		  if(usingPermissions.contains("android.permission.ACCESS_COARSE_LOCATION")
				  || usingPermissions.contains("android.permission.ACCESS_FINE_LOCATION")
				  || usingPermissions.contains("android.permission.ACCESS_MOCK_LOCATION")
				  || usingPermissions.contains("android.permission.ACCOUNT_MANAGER")
				  || usingPermissions.contains("android.permission.GET_ACCOUNTS")
				  || usingPermissions.contains("android.permission.MANAGE_ACCOUNTS")
				  || usingPermissions.contains("android.permission.READ_CONTACTS")
				  || usingPermissions.contains("android.permission.READ_EXTERNAL_STORAGE")
				  || usingPermissions.contains("android.permission.READ_HISTORY_BOOKMARKS")
				  || usingPermissions.contains("android.permission.READ_SMS")
				  || usingPermissions.contains("android.permission.RECEIVE_MMS")
				  || usingPermissions.contains("android.permission.RECEIVE_SMS")
				  || usingPermissions.contains("android.permission.READ_CALL_LOG")
				  || usingPermissions.contains("android.permission.READ_PROFILE"))
		  {			  
			  return true;
		  }
		  else
		  {
			  return false;
		  }
	  }
	  
	  private ArrayList<PackageInfo> getPackageListCategory1(ArrayList<PackageInfo> pkgList)
	  {
		  ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
		  PackageManager pm = getActivity().getPackageManager();
		  
		  for(int i = 0; i < pkgList.size(); i++)
		  {
			  PackageInfo pInfo;
			try {
				pInfo = pm.getPackageInfo(pkgList.get(i).packageName, PackageManager.GET_PERMISSIONS);
				
				  if(isPackageCategory1(pInfo))
					  res.add(pInfo);

			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  return res;
	  }

	  private boolean isPackageCategory2(PackageInfo pInfo)
	  {
		  return true;
	  }
	  
	  private ArrayList<PackageInfo> getPackageListCategory2(ArrayList<PackageInfo> pkgList)
	  {
		  ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
		  PackageManager pm = getActivity().getPackageManager();
		  
		  for(int i = 0; i < pkgList.size(); i++)
		  {
			  PackageInfo pInfo;
			try {
				pInfo = pm.getPackageInfo(pkgList.get(i).packageName, PackageManager.GET_PERMISSIONS);
				  if(isPackageCategory2(pInfo))
					  res.add(pInfo);

			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  return res;
	  }

} 

