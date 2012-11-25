package com.example.secondscreenclient.view;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.R.id;
import com.example.secondscreenclient.R.layout;
import com.example.secondscreenclient.requests.BroadcastData;
import com.example.secondscreenclient.requests.ChannelData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BroadcastListAdapter extends BaseAdapter {
	
	private static final String TAG = "BroadcastListAdapter";
	private Context context;
	private LayoutInflater layoutInflater;
	private BroadcastData[] entries;
	
	public BroadcastListAdapter(Context context){
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
		RelativeLayout broadcastView;
		Log.v(TAG, "Finding the view");
		if(convertView == null){
			Log.v(TAG, "convertView is null");
			broadcastView = (RelativeLayout) layoutInflater.inflate(R.layout.broadcast_item, parent, false);
		} else {
			Log.v(TAG, "convertView is not null");
			broadcastView = (RelativeLayout) convertView;
		}
		
		TextView titleText = (TextView) broadcastView.findViewById(R.id.broadcast_title);
		TextView subText = (TextView) broadcastView.findViewById(R.id.broadcast_subtitle);
		TextView synopsisText = (TextView) broadcastView.findViewById(R.id.broadcast_synopsis);
		Log.d(TAG, "titleText: " + entries[position].getTitle());
		Log.d(TAG, "subText: " + entries[position].getSubtitle());
		Log.d(TAG, "synopsisText: " + entries[position].getSynopsis());
		
		titleText.setText(entries[position].getTitle());
		subText.setText(entries[position].getSubtitle());
		synopsisText.setText(entries[position].getSynopsis());
		return broadcastView;
	}
	
	public void updateEntries(BroadcastData[] entries){
		this.entries = entries;
		notifyDataSetChanged();
	}
}
