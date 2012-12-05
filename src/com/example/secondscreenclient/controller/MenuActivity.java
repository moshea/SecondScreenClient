package com.example.secondscreenclient.controller;

import com.example.secondscreenclient.R;

import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends ListActivity{

	private static final String TAG = "MenuActivity";
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    		case R.id.menu_on_now:
    			Log.d(TAG, "On Now menu pressed");
    			Intent intent = new Intent(this, BroadcastsActivity.class);
    			startActivity(intent);
    			return true;
    			
    		case R.id.menu_channels:
    			Log.d(TAG, "Channels menu pressed");
    			Intent intent1 = new Intent(this, MainActivity.class);
    			startActivity(intent1);
    			return true;
    			
    		default:
    			Log.d(TAG, "Uncatered for menu button pressed");
    			return super.onOptionsItemSelected(item);
    	}
    }
}
