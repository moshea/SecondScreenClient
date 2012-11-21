package com.example.secondscreenclient;

import com.example.secondscreenclient.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(TAG, "Starting app...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User(this);
        user.getApplicationId();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
