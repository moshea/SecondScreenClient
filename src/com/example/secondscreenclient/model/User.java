package com.example.secondscreenclient.model;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.requests.PostUser;
import com.google.gson.annotations.Expose;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

public class User {
	
	private static final String TAG = "User";
	private Context context;
	private SharedPreferences preferences;
	private Resources r;
	@Expose
	private String uuid;
	
	public User(Context context){
		this.context = context;
		getPreferences();
	}
	
	/* 
	 * Need to overload the constructor class with no params to
	 * allow Gson library to create the class
	 */
	public User(){}
	
	/* application id's are given out by the server
	 * if we don't have one stored in the preferences file,
	 * then get one from the server, store it and return the app id
	 */
	public String getApplicationId(){
		Log.d(TAG, "getApplicationId");
		String application_id;
		
		if(!preferences.contains("application_id")){
			Log.d(TAG, "No application id stored - getting new one");
			new PostUser(context).execute();
			application_id = null;
		}else{
			application_id = preferences.getString("application_id", "");
			Log.d(TAG, "app id from perfs: " + application_id);
		}
		return application_id;
	}
	
	private void getPreferences(){
		r = context.getResources();
		preferences = context.getSharedPreferences(r.getString(R.string.preferences_file), Context.MODE_PRIVATE);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setContext(Context context){
		this.context = context;
		getPreferences();
	}

}
