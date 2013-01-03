package com.example.secondscreenclient.model;

import java.util.ArrayList;

import com.example.secondscreenclient.requests.GetStatusList;

import android.content.Context;


public class StatusList {
	
	Context context;
	Status[] statusList;
	
	public StatusList(Context context){
		this.context = context;
	}
	
	public Status[] getStatusList(Broadcast broadcast){
		Status[] data;
		
		if(statusList == null){
			GetStatusList getStatusList = new GetStatusList(context, this);
			ArrayList<String> pathList = new ArrayList<String>();
			pathList.add("broadcasts");
			pathList.add(broadcast.getUuid());
			pathList.add("statuses");
			
			getStatusList.setPath(pathList);
			getStatusList.execute();
			data = statusList;
		}else{
			data = statusList;
		}
		return data;
	}
	
	public void setStatusList(Status[] statusList){
		this.statusList = statusList;
	}

}
