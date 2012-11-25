package com.example.secondscreenclient.model;

import com.example.secondscreenclient.requests.BroadcastData;
import com.example.secondscreenclient.requests.GetBroadcastList;

import com.example.secondscreenclient.R;
import android.content.Context;

public class BroadcastList {
	
	Context context;
	BroadcastData[] broadcastDataNow;
	
	public BroadcastList(Context context){
		this.context = context;
	}
	
	public BroadcastData[] getNow(){
		BroadcastData[] data;
		
		if(broadcastDataNow == null){
			GetBroadcastList getBroadcastList = new GetBroadcastList(context, this);
			getBroadcastList.setMethod(R.string.api_method_now);
			getBroadcastList.execute();
			data = null;
		} else {
			data = broadcastDataNow;
		}
		return data;
	}
	
	public void setNow(BroadcastData[] broadcastDataNow){
		this.broadcastDataNow = broadcastDataNow;
	}

}
