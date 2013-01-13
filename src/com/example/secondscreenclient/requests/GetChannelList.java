package com.example.secondscreenclient.requests;

import java.util.ArrayList;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Channel;
import com.example.secondscreenclient.model.ChannelList;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

public class GetChannelList extends AsyncTask<Void, Void, String> {
	
	private Context context;
	private ChannelList channelList;
	
	public GetChannelList(Context context, ChannelList channelList){
		this.context = context;
		this.channelList = channelList;
	}

	@Override
	protected String doInBackground(Void... params) {
		Server server = new Server(context);
		ArrayList<String> pathList = new ArrayList<String>();
		pathList.add(context.getString(R.string.api_channels));

		String response = null;
		try{
			response = server.getRequest(pathList, null);
		}catch(HttpStatusException e){
			response = null;
		}
		return response;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 * @param response - the json response string from the server
	 * if the response string is null, we need to do a UI update to handle that
	 */
	protected void onPostExecute(String response){
		if(response != null){
			Channel[] channelData = new Gson().fromJson(response, Channel[].class);
			channelList.setData(channelData);
			Intent intent = new Intent("updateChannelList");
			LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
			lbm.sendBroadcast(intent);
		}else{
			/*
			 * TODO: do something here if it all goes wrong!
			 */
		}
	}

}
