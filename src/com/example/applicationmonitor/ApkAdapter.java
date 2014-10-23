package com.example.applicationmonitor;

import java.util.List;
//import com.ibc.android.demo.appslist.activity.R;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
public class ApkAdapter extends BaseAdapter {
 
    List<PackageInfo> packageList;
    Activity context;
    PackageManager packageManager;
 
    public ApkAdapter()
    {
    	super();
    }
    public ApkAdapter(Activity pcontext, List<PackageInfo> ppackageList,
            PackageManager ppackageManager) {
        super();
        context = pcontext;
        packageList = ppackageList;
        packageManager = ppackageManager;
    }
 
    public ApkAdapter newInstance(Activity pcontext, List<PackageInfo> ppackageList,
            PackageManager ppackageManager)
    {
    	context = pcontext;
    	packageList = ppackageList;
    	packageManager = ppackageManager;
    	return new ApkAdapter();
    }
    private class ViewHolder {
        TextView apkName;
    }
 
    public int getCount() {
        return packageList.size();
    }
 
    public Object getItem(int position) {
        return packageList.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();
 
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.apklist_item, parent, false);
            holder = new ViewHolder();
 
            holder.apkName = (TextView) convertView.findViewById(R.id.appname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        PackageInfo packageInfo = (PackageInfo) getItem(position);
        Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
        String appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        appIcon.setBounds(0, 0, 40, 40);
        holder.apkName.setCompoundDrawables(appIcon, null, null, null);
        holder.apkName.setCompoundDrawablePadding(15);
        holder.apkName.setText(appName);
 
        return convertView;
    }
}