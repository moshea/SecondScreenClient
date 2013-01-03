package com.example.secondscreenclient.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;

public class Status {
	
	private final static String TAG = Status.class.getSimpleName();
	
	@Expose
	String text;
	@Expose 
	String user;
	@Expose
	String id;
	
	public Status(Parcel source){
		Log.d(TAG, "Reconstructing parcel data");
		setText(source.readString());
		setUser(source.readString());
		setId(source.readString());
	}
	
	public int describeContents() {
		return this.hashCode();
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		Log.d(TAG, "writing to parcel. flags: " + flags);
		dest.writeString(getText());
		dest.writeString(getUser());
		dest.writeString(getId());
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public static final Parcelable.Creator<Broadcast> CREATOR 
			= new Parcelable.Creator<Broadcast>() {
		public Broadcast createFromParcel(Parcel in) {
			return new Broadcast(in);
		}
	
		public Broadcast[] newArray(int size) {
			return new Broadcast[size];
		}
	};

}
