package com.example.secondscreenclient.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Broadcast;
import com.example.secondscreenclient.model.BroadcastList;
import com.example.secondscreenclient.model.Channel;
import com.example.secondscreenclient.view.BroadcastListAdapter;

/*
 * OnNow activity should display broadcasts that are currently running
 * Useful for finding shows that are on right now
 */
public class OnNowActivity extends MenuActivity{
	
	private static final String TAG = OnNowActivity.class.getSimpleName();
	BroadcastList broadcastList;
	BroadcastListAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // reusing the main activity layout here, as its similar to
        // the broadcast list layout.
        setContentView(R.layout.activity_main);
        
        this.getListView().setOnItemClickListener(onItemClickListener);
        
        adapter = new BroadcastListAdapter(this);
        setListAdapter(adapter);
        
        LocalBroadcastManager.getInstance(this).registerReceiver(
        		messageReciever, new IntentFilter("updateBroadcastList"));
        
        // important to get the broadcast list from the server after the
        // adapter has being set and the listener set up, otherwise, broadcastList could be populated
        // but no adapter to update to view would exist
        broadcastList = new BroadcastList(this);
        Bundle extras = getIntent().getExtras();
        
        if((extras != null) && extras.containsKey("channel")){
        	Channel channel = (Channel) getIntent().getExtras().get("channel");
        	channel.setContext(this);
        	channel.getBroadcasts(broadcastList);
        }else{
            broadcastList.getNow();
        }
    }
	
	private BroadcastReceiver messageReciever = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action == "updateBroadcastList"){
				Log.d(TAG, "About to update broadcast list");
				adapter.updateEntries(broadcastList.getNow());
			}
		}
		
    };
    
    private OnItemClickListener onItemClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
			Log.d(TAG, "List item clicked: " + position);
			
			Broadcast broadcast = (Broadcast) adapter.getAdapter().getItem(position);
			Log.d(TAG, "BroadcastData uuid: " + broadcast.getUuid());
			Intent intent = new Intent(view.getContext(), BroadcastActivity.class);
			intent.putExtra("broadcast", broadcast);
			startActivity(intent);
		}
    	
    };

}
