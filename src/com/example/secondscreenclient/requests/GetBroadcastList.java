package com.example.secondscreenclient.requests;

import java.util.ArrayList;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.BroadcastData;
import com.example.secondscreenclient.model.BroadcastList;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

public class GetBroadcastList extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = "GetBroadcastList";
	private Context context;
	private BroadcastList broadcastList;
	// this stores a string, but is set by using a R.string int in the setter method.
	private int method;
	
	public GetBroadcastList(Context context, BroadcastList broadcastList){
		this.context = context;
		this.broadcastList = broadcastList;
	}

	@Override
	protected String doInBackground(Void... params) {
		Server server = new Server(context);
		
		ArrayList<String> pathList = new ArrayList<String>();
		pathList.add(context.getString(R.string.api_broadcasts));
		pathList.add(context.getString(method));

		return server.getRequest(pathList, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 * Once the response comes back from the server, create a list o
	 */
	protected void onPostExecute(String response){
		BroadcastData[] broadcastData = new Gson().fromJson(response, BroadcastData[].class);
		broadcastList.setNow(broadcastData);
		Intent intent = new Intent("updateNowBroadcastList");
		LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
		lbm.sendBroadcast(intent);
	}
	
	public void setMethod(int method){
		this.method = method;
	}

}
