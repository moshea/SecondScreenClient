package com.example.secondscreenclient.controller;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Channel;
import com.example.secondscreenclient.model.ChannelList;
import com.example.secondscreenclient.model.User;
import com.example.secondscreenclient.view.ChannelListAdapter;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/* 
 * MainActivity is extending MenuActivity, which in turn extends ListActivity.
 * This is done, as MainActivity shares the same menu with other list screens
 */
public class MainActivity extends MenuActivity{
	
	private static final String TAG = "MainActivity";
	ChannelList channelList;
	ChannelListAdapter adapter;
	User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "Starting app...");
        super.onCreate(savedInstanceState);
        user = new User(this);
        channelList = new ChannelList(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(
        		messageReciever, new IntentFilter("updateChannelList"));
        
        
        
        setContentView(R.layout.activity_main);
        adapter = new ChannelListAdapter(this);
        setListAdapter(adapter);
        // Load up the application id. if this is a first app start,
        // it will go to the server to get it.
        user.getApplicationId();
        channelList.getList();
        
        this.getListView().setOnItemClickListener(onItemClickListener);
    }
    
    private BroadcastReceiver messageReciever = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action == "updateChannelList"){
				Log.d(TAG, "About to update channel list");
				adapter.updateEntries(channelList.getList());
			}
		}
		
    };
    
    private OnItemClickListener onItemClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
			Log.d(TAG, "List item clicked: " + position);
			
			Channel channel = (Channel) adapter.getAdapter().getItem(position);
			Intent intent = new Intent(view.getContext(), OnNowActivity.class);
			intent.putExtra("channel", channel);
			startActivity(intent);
			
		}
    	
    };
}