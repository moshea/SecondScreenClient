package com.example.secondscreenclient.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.AndroidPhone;
import com.example.secondscreenclient.model.UserData;
import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

public class PostUser extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = "PostUser";
	private Context context;
	private SharedPreferences preferences;
	private Resources r;
	
	public PostUser(Context context){
		this.context = context;
	}

	@Override
	protected String doInBackground(Void...voids) {
		AndroidPhone phone = new AndroidPhone(context);
		Server server = new Server(context);
		ArrayList<String> pathList = new ArrayList<String>();
		pathList.add(context.getString(R.string.api_users));
		
		
		/* to get an application id, we need to pass the server a unique identifier
		 * the server doesn't demand it, as some phones may not return it
		 * but it will help identify users
		 */
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("device_id", phone.getId()));
		nameValuePairs.add(new BasicNameValuePair("os_version", phone.getOSVersion()));
		nameValuePairs.add(new BasicNameValuePair("api_level", phone.getAPILevel()));
		nameValuePairs.add(new BasicNameValuePair("make", phone.getMake()));
		nameValuePairs.add(new BasicNameValuePair("model", phone.getModel()));
		
		return server.postRequest(pathList, nameValuePairs);
	}
	
	/*
	 * the response of a post to /users returns a new
	 * application id. Store this locally, so we can use it elsewhere
	 */
	protected void onPostExecute(String response){
		UserData userData = new Gson().fromJson(response, UserData.class);
		
		r = context.getResources();
		preferences = context.getSharedPreferences(r.getString(R.string.preferences_file), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		
		Log.d(TAG, "Set preference: applicationId: " + userData.getUuid());
		editor.putString("applicationId", userData.getUuid());
		editor.commit();
	}

}
