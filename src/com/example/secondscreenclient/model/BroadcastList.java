package com.example.secondscreenclient.model;

import com.example.secondscreenclient.requests.GetBroadcastList;

import com.example.secondscreenclient.R;
import android.content.Context;

public class BroadcastList {
	
	Context context;
	Broadcast[] broadcastDataNow;
	
	public BroadcastList(Context context){
		this.context = context;
	}
	
	public Broadcast[] getNow(){
		Broadcast[] data;
		
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
	
	public void setNow(Broadcast[] broadcastDataNow){
		this.broadcastDataNow = broadcastDataNow;
	}

}
