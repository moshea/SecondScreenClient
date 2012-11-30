package com.example.secondscreenclient.model;

import android.content.Context;

public class Broadcast {
	
	Context context;
	BroadcastData broadcastData;
	
	public Broadcast(Context context, BroadcastData broadcastData){
		this.context = context;
		this.broadcastData = broadcastData;
	}

	public BroadcastData getInfo(){
		return broadcastData;
	}
}
