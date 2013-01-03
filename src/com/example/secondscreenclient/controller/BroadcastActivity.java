package com.example.secondscreenclient.controller;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Broadcast;
import com.example.secondscreenclient.model.StatusList;
import com.example.secondscreenclient.view.BroadcastListAdapter;
import com.example.secondscreenclient.view.StatusListAdapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

public class BroadcastActivity extends MenuActivity {
	
	private static final String TAG = "BroadcastActivity";
	private Broadcast broadcast;
	StatusList statusList;
	StatusListAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
	    super.onCreate(savedInstanceState);
	    
	    broadcast = (Broadcast) getIntent().getExtras().get("broadcast");

	    setContentView(R.layout.broadcast_activity);
	    adapter = new StatusListAdapter(this);
        setListAdapter(adapter);
        
        LocalBroadcastManager.getInstance(this).registerReceiver(
        		messageReciever, new IntentFilter("updateStatusList"));
	    
	    TextView titleText = (TextView) findViewById(R.id.title);
	    TextView subtitleText = (TextView) findViewById(R.id.subtitle);
	    TextView synopsisText = (TextView) findViewById(R.id.synopsis);
	    
	    titleText.setText(broadcast.getTitle());
	    subtitleText.setText(broadcast.getSubtitle());
	    synopsisText.setText(broadcast.getSynopsis());
	    
	    
	    statusList = new StatusList(this);
	    statusList.getStatusList(broadcast);
	    
	}
	
	private BroadcastReceiver messageReciever = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action == "updateStatusList"){
				Log.d(TAG, "About to update status list");
				adapter.updateEntries(statusList.getStatusList(broadcast));
			}
		}
		
    };

}
