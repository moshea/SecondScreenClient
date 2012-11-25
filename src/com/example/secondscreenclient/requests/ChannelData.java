package com.example.secondscreenclient.requests;
import com.google.gson.annotations.Expose;

public class ChannelData {
	@Expose
	private String id;
	@Expose
	private String broadcasterId;
	@Expose
	private String logoUrl;
	@Expose
	private String name;
	
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
}