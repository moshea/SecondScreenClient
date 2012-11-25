package com.example.secondscreenclient.view;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.R.id;
import com.example.secondscreenclient.R.layout;
import com.example.secondscreenclient.requests.ChannelData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChannelListAdapter extends BaseAdapter {
	
	private static final String TAG = "ChannelListAdapter";
	private Context context;
	private LayoutInflater layoutInflater;
	private ChannelData[] entries;
	
	public ChannelListAdapter(Context context){
		this.context = context;
		layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		int length;
		if(entries == null){
			length = 0;
		} else {
			length = entries.length;
		}
		return length;
	}

	@Override
	public Object getItem(int position) {
		return entries[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout channelView;
		Log.v(TAG, "Finding the view");
		if(convertView == null){
			Log.v(TAG, "convertView is null");
			channelView = (RelativeLayout) layoutInflater.inflate(R.layout.channel_item, parent, false);
		} else {
			Log.v(TAG, "convertView is not null");
			channelView = (RelativeLayout) convertView;
		}
		
		TextView nameText = (TextView) channelView.findViewById(R.id.channel_name);
		Log.d(TAG, "nameText: " + entries[position].getName());
		nameText.setText(entries[position].getName());
		return channelView;
	}
	
	public void updateEntries(ChannelData[] entries){
		this.entries = entries;
		notifyDataSetChanged();
	}
}
