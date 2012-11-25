package com.example.secondscreenclient.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.BroadcastList;
import com.example.secondscreenclient.model.ChannelList;
import com.example.secondscreenclient.model.User;
import com.example.secondscreenclient.view.BroadcastListAdapter;
import com.example.secondscreenclient.view.ChannelListAdapter;

/*
 * OnNow activity should display broadcasts that are currently running
 * Useful for finding shows that are on right now
 */
public class OnNowActivity extends MenuActivity{
	
	private static final String TAG = "OnNowActivity";
	BroadcastList broadcastList;
	BroadcastListAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "Creating OnNowActivity");
        super.onCreate(savedInstanceState);
        
        // reusing the main activity layout here, as its similar to
        // the broadcast list layout.
        setContentView(R.layout.activity_main);
        adapter = new BroadcastListAdapter(this);
        setListAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(
        		messageReciever, new IntentFilter("updateNowBroadcastList"));
        
        // important to get the broadcast list from the server after the
        // adapter has being set and the listener set up, otherwise, broadcastList could be populated
        // but no adapter to update to view would exist
        broadcastList = new BroadcastList(this);
        broadcastList.getNow();
    }
	
	private BroadcastReceiver messageReciever = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action == "updateNowBroadcastList"){
				Log.d(TAG, "About to update broadcast list");
				adapter.updateEntries(broadcastList.getNow());
			}
		}
		
    };

}