package com.example.secondscreenclient.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.secondscreenclient.R;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

public class Server {
	
	private static final String TAG = "Server";
	private Uri.Builder builder;
	private Resources r;
	
	public Server(Context context){
		r = context.getResources();
	}
	
	public String postRequest(int path, List<NameValuePair> params){
		HttpResponse response = null;
		builder = getBuilder();
		builder.appendPath(r.getString(path));
		
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(builder.build().toString());
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			
			// all of this is logging only...
			// its needed to print out the post body
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
		
		return processReponse(response);
	}
	
	/* convert the response to a string
	 * if there are any errors, they will be handled by a throws
	 * the calling method should then handle that, by updating the UI
	 */
	private String processReponse(HttpResponse response){
		String line = "";
		StringBuilder string = new StringBuilder();
		try{
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			 while ((line = rd.readLine()) != null) { 
			        string.append(line); 
			    }
		}catch(Exception e){}
		
		return string.toString();
	}
	
	private Uri.Builder getBuilder(){
		builder = new Uri.Builder();
		builder = Uri.parse(r.getString(R.string.server_url)).buildUpon();
		return builder;
	}

}
