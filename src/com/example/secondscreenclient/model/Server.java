package com.example.secondscreenclient.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.secondscreenclient.R;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

public class Server {
	
	private static final String TAG = "Server";
	private Context context;
	private Uri.Builder builder;
	private Resources r;
	
	public Server(Context context){
		this.context = context;
		r = context.getResources();
	}
	
	/* to get an application id, we need to pass the server a unique identifier
	 * the server doesn't demand it, as some phones may not return it
	 * but it will help identify users
	 */
	public String getApplicationId(){
		AndroidPhone phone = new AndroidPhone(context);
		
		builder = getBuilder();
		builder.appendPath(r.getString(R.string.api_users));
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("device_id", phone.getId()));
		nameValuePairs.add(new BasicNameValuePair("os_version", phone.getOSVersion()));
		nameValuePairs.add(new BasicNameValuePair("api_level", phone.getAPILevel()));
		nameValuePairs.add(new BasicNameValuePair("make", phone.getMake()));
		nameValuePairs.add(new BasicNameValuePair("model", phone.getModel()));
		
		
		postRequest(builder.build().toString(), nameValuePairs);
		return "";
	}
	
	private Uri.Builder getBuilder(){
		builder = new Uri.Builder();
		builder.scheme("http");
		builder.authority(r.getString(R.string.server_url));
		return builder;
	}
	
	private HttpResponse postRequest(String url, List<NameValuePair> params){
		HttpResponse response = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			
			// all of this is logging only...
			Log.d(TAG, "post url: " + httppost.getRequestLine());
			InputStream is = httppost.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while((str = br.readLine()) != null){
				Log.d(TAG, "post params: " + str);
			}
			// finished logging!
			
			response = client.execute(httppost);
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return response;
	}

}
