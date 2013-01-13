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

	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 * Only calling the server connection with the correct
	 * path list and params. the params and pathlist should be 
	 * set up before calling doInBackground
	 */
	@Override
	protected String doInBackground(Void... p) {
		Server server = new Server(context);
		String response = null;
		try{
			response = server.getRequest(pathList, params);
		}catch( HttpStatusException e){
			
		}
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 * Once the response comes back from the server, create a list of statuses
	 * If our response is null, it means something went wrong, so we also have
	 * to handle that case.
	 */
	protected void onPostExecute(String response){
		if(response != null){
			com.example.secondscreenclient.model.Status[] statuses = new Gson().fromJson(response, com.example.secondscreenclient.model.Status[].class);
			statusList.setStatusList(statuses);
			Intent intent = new Intent("updateStatusList");
			LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
			lbm.sendBroadcast(intent);	
		}else{
			/*
			 * TODO: do something if response is null. for now do nothing!
			 */
		}
	}
	
	/*
	 * @param pathList
	 * The broken down path list as an ArrayList, this will
	 * be put together in the server
	 */
	
	public void setPath(ArrayList<String> pathList){
		this.pathList = pathList;
	}
	/*
	 * @param params
	 * a list of parameters to send to the server, again should be 
	 * set before calling doInBackground
	 */
	public void setParams(List<NameValuePair> params){
		this.params = params;
	}

}

