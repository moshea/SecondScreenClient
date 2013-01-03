package com.example.secondscreenclient.model;

import java.util.ArrayList;

import com.example.secondscreenclient.requests.GetBroadcastList;

import android.content.Context;

public class BroadcastList {
	
	Context context;
	Broadcast[] broadcastList;
	
	public BroadcastList(Context context){
		this.context = context;
	}
	
	public Broadcast[] getNow(){
		Broadcast[] data;
		
		if(broadcastList == null){
			GetBroadcastList getBroadcastList = new GetBroadcastList(context, this);
			ArrayList<String> pathList = new ArrayList<String>();
			pathList.add("broadcasts");
			pathList.add("now");
			
			getBroadcastList.setPath(pathList);
			getBroadcastList.execute();
			data = null;
		} else {
			data = broadcastList;
		}
		return data;
	}
	
	public Broadcast[] fromChannel(Channel channel){
		Broadcast[] data;
		
		if(broadcastList == null){
			GetBroadcastList getBroadcastList = new GetBroadcastList(context, this);
			ArrayList<String> pathList = new ArrayList<String>();
			pathList.add("channels");
			pathList.add(channel.getId());
			pathList.add("broadcasts");

			getBroadcastList.setPath(pathList);
			getBroadcastList.execute();
			data = null;
		} else {
			data = broadcastList;
		}
		return data;
	}
	
	public void setBroadcastList(Broadcast[] broadcastList){
		this.broadcastList = broadcastList;
	}

}
