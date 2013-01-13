package com.example.secondscreenclient.view;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.model.Status;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StatusListAdapter extends BaseAdapter {
	
	private static final String TAG = StatusListAdapter.class.getSimpleName();
	private Context context;
	private LayoutInflater layoutInflater;
	private Status[] entries;
	
	public StatusListAdapter(Context context){
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
		RelativeLayout statusView;
		Log.v(TAG, "Finding the view");
		if(convertView == null){
			statusView = (RelativeLayout) layoutInflater.inflate(R.layout.status_item, parent, false);
		} else {
			statusView = (RelativeLayout) convertView;
		}
		
		TextView statusText = (TextView) statusView.findViewById(R.id.status_text);
		TextView userText = (TextView) statusView.findViewById(R.id.status_user);
		Log.d(TAG, "statusText: " + entries[position].getText());
		Log.d(TAG, "userText: " + entries[position].getUser());
		
		userText.setText(entries[position].getUser());
		statusText.setText(entries[position].getText());
		return statusView;
	}
	
	public void updateEntries(Status[] entries){
		this.entries = entries;
		notifyDataSetChanged();
	}
}

