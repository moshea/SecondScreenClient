package com.example.secondscreenclient.requests;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.example.secondscreenclient.model.StatusList;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

public class GetStatusList extends AsyncTask<Void, Void, String> {
	
	private static final String TAG = GetBroadcastList.class.getSimpleName();
	private Context context;
	private StatusList statusList;
	private ArrayList<String> pathList;
	List<NameValuePair> params;
	
	public GetStatusList(Context context, StatusList statusList){
		this.context = context;
		this.statusList = statusList;
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
		com.example.secondscreenclient.model.Status[] statuses = new Gson().fromJson(response, com.example.secondscreenclient.model.Status[].class);
		statusList.setStatusList(statuses);
		Intent intent = new Intent("updateStatusList");
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

