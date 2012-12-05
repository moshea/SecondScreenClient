package com.example.secondscreenclient.view;

import com.example.secondscreenclient.R;
import com.example.secondscreenclient.R.id;
import com.example.secondscreenclient.R.layout;
import com.example.secondscreenclient.model.Channel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChannelListAdapter extends BaseAdapter {
	
	private static final String TAG = "ChannelListAdapter";
	private Context context;
	private LayoutInflater layoutInflater;
	private Channel[] entries;
	private ImageLoader imageLoader;
	
	public ChannelListAdapter(Context context){
		this.context = context;
		layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this.context));
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
		ImageView channelLogo = (ImageView) channelView.findViewById(R.id.channel_logo);
		Log.d(TAG, "nameText: " + entries[position].getName());
		Log.d(TAG, "imageUrl: " + entries[position].getLogoUrl());
		
		nameText.setText(entries[position].getName());
		imageLoader.displayImage(entries[position].getLogoUrl(), channelLogo);
		return channelView;
	}
	
	public void updateEntries(Channel[] entries){
		this.entries = entries;
		notifyDataSetChanged();
	}
}
