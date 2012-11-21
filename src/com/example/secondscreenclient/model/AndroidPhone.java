package com.example.secondscreenclient.model;

import android.content.Context;
import android.telephony.TelephonyManager;

public class AndroidPhone {
	
	Context context;
	
	public AndroidPhone(Context context){
		this.context = context;
	}
	
	public String getId(){
		TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}
	
	public String getOSVersion(){
		return System.getProperty("os.version");
	}
	
	public String getAPILevel(){
		return android.os.Build.VERSION.SDK;
	}
	
	public String getMake(){
		return android.os.Build.DEVICE;
	}
	
	public String getModel(){
		String model = android.os.Build.MODEL;
		String product = android.os.Build.PRODUCT;
		return model + " (" + product + ")";
	}

}
