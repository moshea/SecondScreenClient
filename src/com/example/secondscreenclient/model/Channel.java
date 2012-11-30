package com.example.secondscreenclient.model;
import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.requests.GetBroadcastList;
import com.google.gson.annotations.Expose;

public class Channel implements Parcelable {
	@Expose
	private String id;
	@Expose
	private String broadcasterId;
	@Expose
	private String logoUrl;
	@Expose
	private String name;
	
	private Context context;
	
	public Channel(){}
	
	public Channel(Parcel source){
		setId(source.readString());
		setBroadcasterId(source.readString());
		setLogoUrl(source.readString());
		setName(source.readString());
	}
	
	public void getBroadcasts(BroadcastList broadcastList){
		GetBroadcastList getBroadcastList = new GetBroadcastList(context, broadcastList);
		ArrayList<String> pathList = new ArrayList<String>();
		pathList.add("channels");
		pathList.add(getId());
		pathList.add("broadcasts");

		getBroadcastList.setPath(pathList);
		getBroadcastList.execute();
	}
	
	@Override
	public int describeContents() {
		return this.hashCode();
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getId());
		dest.writeString(getBroadcasterId());
		dest.writeString(getLogoUrl());
		dest.writeString(getName());
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBroadcasterId() {
		return broadcasterId;
	}
	public void setBroadcasterId(String broadcasterId) {
		this.broadcasterId = broadcasterId;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static final Parcelable.Creator<Channel> CREATOR 
			= new Parcelable.Creator<Channel>() {
		public Channel createFromParcel(Parcel in) {
			return new Channel(in);
		}

		public Channel[] newArray(int size) {
			return new Channel[size];
		}
	};
}