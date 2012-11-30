package com.example.secondscreenclient.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Broadcast;
import com.example.secondscreenclient.model.BroadcastList;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

public class GetBroadcastList extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = GetBroadcastList.class.getSimpleName();
	private Context context;
	private BroadcastList broadcastList;
	private ArrayList<String> pathList;
	List<NameValuePair> params;
	
	public GetBroadcastList(Context context, BroadcastList broadcastList){
		this.context = context;
		this.broadcastList = broadcastList;
	}

	@Override
	protected String doInBackground(Void... p) {
		Server server = new Server(context);

		return server.getRequest(pathList, params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 * Once the response comes back from the server, create a list o
	 */
	protected void onPostExecute(String response){
		Broadcast[] broadcasts = new Gson().fromJson(response, Broadcast[].class);
		broadcastList.setBroadcastList(broadcasts);
		Intent intent = new Intent("updateBroadcastList");
		LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
		lbm.sendBroadcast(intent);
	}
	
	public void setPath(ArrayList<String> pathList){
		this.pathList = pathList;
	}
	public void setParams(List<NameValuePair> params){
		this.params = params;
	}

}
