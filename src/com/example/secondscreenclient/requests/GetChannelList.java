package com.example.secondscreenclient.requests;

import java.util.ArrayList;

import com.example.secondscreenclient.R;
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

		return server.getRequest(pathList, null);
	}
	
	protected void onPostExecute(String response){
		ChannelData[] channelData = new Gson().fromJson(response, ChannelData[].class);
		channelList.setData(channelData);
		Intent intent = new Intent("updateChannelList");
		LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
		lbm.sendBroadcast(intent);

	}

}
