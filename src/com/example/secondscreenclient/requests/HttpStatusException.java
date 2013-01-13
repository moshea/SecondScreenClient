package com.example.secondscreenclient.requests;

import org.apache.http.client.ClientProtocolException;

public class HttpStatusException extends ClientProtocolException {
	
	public HttpStatusException(String message){
		super(message);
	}

}
