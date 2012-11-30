package com.example.secondscreenclient.controller;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Broadcast;
import com.example.secondscreenclient.model.Broadcast;
import com.example.secondscreenclient.view.ChannelListAdapter;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.TextView;

public class BroadcastActivity extends Activity {
	
	private static final String TAG = "BroadcastActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    Broadcast broadcast = (Broadcast) getIntent().getExtras().get("broadcast");

	    setContentView(R.layout.broadcast_activity);
	    
	    TextView titleText = (TextView) findViewById(R.id.title);
	    TextView subtitleText = (TextView) findViewById(R.id.subtitle);
	    TextView synopsisText = (TextView) findViewById(R.id.synopsis);
	    
	    titleText.setText(broadcast.getTitle());
	    subtitleText.setText(broadcast.getSubtitle());
	    synopsisText.setText(broadcast.getSynopsis());
	}

}
