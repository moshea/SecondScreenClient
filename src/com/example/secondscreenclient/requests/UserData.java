package com.example.secondscreenclient.requests;
import com.google.gson.annotations.Expose;

public class UserData {
	@Expose
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
