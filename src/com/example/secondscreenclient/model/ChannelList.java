package com.example.secondscreenclient.model;

import com.example.secondscreenclient.requests.GetChannelList;

import android.content.Context;
import android.util.Log;

public class ChannelList {
	
	private static final String TAG = "ChannelList";
	private Context context;
	private ChannelData[] data;
	
	public ChannelList(Context context){
		this.context = context;
	}
	
	/*
	 * getList will retrieve the channel list from the server
	 * TODO: implement local cache clearing after a certain time limit
	 */
	public ChannelData[] getList(){
		ChannelData[] channelData;
		if(data == null){
			Log.d(TAG, "Getting a new channel list from server");
			GetChannelList getChannelList = new GetChannelList(context, this);
			getChannelList.execute();
			channelData = null;
		}else{
			channelData = data;
		}
		return channelData;
	}
	
	/*
	 * we only need a setter for data, as the data should
	 * be read from getList, instead of getData.
	 */
	public void setData(ChannelData[] data){
		this.data = data;
	}

}
