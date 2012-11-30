package com.example.secondscreenclient.model;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;

public class BroadcastData implements Parcelable {
	
	private final static String TAG = "BroadcastData";
	
	@Expose
	private String uuid;
	@Expose
	private String channelId;
	@Expose
	private String start;
	@Expose
	private String end;
	@Expose 
	private String title;
	@Expose
	private String subtitle;
	@Expose
	private String synopsis;
	
	public BroadcastData(Parcel source){
		Log.d(TAG, "Reconstructing parcel data");
		setUuid(source.readString());
		setChannelId(source.readString());
		setStart(source.readString());
		setEnd(source.readString());
		setTitle(source.readString());
		setSubtitle(source.readString());
		setSynopsis(source.readString());
	}
	
	@Override
	public int describeContents() {
		return this.hashCode();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.d(TAG, "writing to parcel. flags: " + flags);
		dest.writeString(getUuid());
		dest.writeString(getChannelId());
		dest.writeString(getStart());
		dest.writeString(getEnd());
		dest.writeString(getTitle());
		dest.writeString(getSubtitle());
		dest.writeString(getSynopsis());
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public static final Parcelable.Creator<BroadcastData> CREATOR 
			= new Parcelable.Creator<BroadcastData>() {
		public BroadcastData createFromParcel(Parcel in) {
			return new BroadcastData(in);
		}

		public BroadcastData[] newArray(int size) {
			return new BroadcastData[size];
		}
	};
	
}
