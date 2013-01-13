package com.example.secondscreenclient.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
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
	
	/*
	 * Looks after all post requests to the server.
	 * @pathList : List of path strings to build
	 * @params: list of name-value pairs for query
	 * params can be set to null, if there isn't a query to add
	 * 
	 */
	public String postRequest(ArrayList<String> pathList, List<NameValuePair> params){
		HttpResponse response = null;
		builder = getBuilder();
		appendPath(pathList);
		
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(builder.build().toString());
		// setting the data type to JSON in the request header
		// it spares doing it in the URL
		httppost.setHeader("Accept", r.getString(R.string.data_type));
		
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
	
	/*
	 * Looks after all get requests to the server.
	 * @pathList : List of path strings to build
	 * @params: list of name-value pairs for query
	 * params can be set to null, if there isn't a query to add
	 * 
	 */
	public String getRequest(ArrayList<String> pathList, List<NameValuePair> params) throws HttpStatusException{
		HttpResponse response = null;
		builder = getBuilder();
		appendPath(pathList);
		appendQueryParameter(params);
		
		Log.d(TAG, "Get Request: " + builder.build().toString());
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(builder.build().toString());
		// setting the data type to JSON in the request header
		// it spares doing it in the URL
		request.setHeader("Accept", r.getString(R.string.data_type));
		
		try {
			response = client.execute(request);
			int status_code = response.getStatusLine().getStatusCode();
			if(status_code != HttpStatus.SC_OK){
				throw new HttpStatusException(Integer.toString(status_code));
			}
		} catch(HttpStatusException e){
			e.printStackTrace();
			throw e;
		} catch(ClientProtocolException e) {
			e.printStackTrace();
		} catch(IOException e) {
			// TODO Auto-generated catch block
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
		Log.d(TAG, "server response: " + string.toString());
		return string.toString();
	}
	
	private Uri.Builder getBuilder(){
		builder = new Uri.Builder();
		builder = Uri.parse(r.getString(R.string.server_url)).buildUpon();
		return builder;
	}
	
	private Uri.Builder appendPath(ArrayList<String> pathList){
		// first check that path is not null, then build it
		// paths must be built in the correct order
		if(pathList != null){
			for(String path: pathList){
				builder.appendPath(path);
			}
		}
		return builder;
	}
	
	private Uri.Builder appendQueryParameter(List<NameValuePair> params){
		// first check if not null, then add to the builder query.
		if(!(params == null)){
			for(NameValuePair nv: params){
				builder.appendQueryParameter(nv.getName(), nv.getValue());
			}
		}
		return builder;
	}

}
