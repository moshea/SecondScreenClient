package com.example.secondscreenclient.model;

import com.example.secondscreenclient.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.util.Log;

public class User {
	
	private static final String TAG = "User";
	private Context context;
	private SharedPreferences preferences;
	private Resources r;
	
	public User(Context context){
		this.context = context;
		r = context.getResources();
		preferences = context.getSharedPreferences(r.getString(R.string.preferences_file), Context.MODE_PRIVATE);
	}
	
	/* application id's are given out by the server
	 * if we don't have one stored in the preferences file,
	 * then get one from the server, store it and return the app id
	 */
	public String getApplicationId(){
		Log.d(TAG, "getApplicationId");
		String application_id;
		
		if(!preferences.contains("application_id")){
			Log.d(TAG, "No application id stored - getting new one");
			Server server = new Server(context);
			String new_app_id = server.getApplicationId();
			Log.d(TAG, "app id from server: " + new_app_id);
			
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("application_id", new_app_id);
			editor.commit();
			application_id = new_app_id;
		}else{
			application_id = preferences.getString("application_id", "");
			Log.d(TAG, "app id from perfs: " + application_id);
		}
		return application_id;
	}

}
