package com.cooki.tiger.childbook.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooki.tiger.childbook.R;

import java.io.File;

public class PageWidgetAdapter extends BaseAdapter {

	private Context mContext;
	private int count;
	private LayoutInflater inflater;

	private String[] imgs;
	public PageWidgetAdapter(Context context,String[] filesPath) {
		if(filesPath==null){
			filesPath = new String[]{};
		}
		imgs = filesPath;
		mContext = context;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		count = (int) Math.ceil(imgs.length / 2.0);

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.i("tag",count+"");
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imgs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewGroup layout;
		if(convertView == null) {
			layout = (ViewGroup) inflater.inflate(R.layout.item_layout, null);
		} else {
			layout = (ViewGroup) convertView;
		}
		setViewContent(layout, position);

		return layout;
	}

	private void setViewContent(ViewGroup group, int position) {
			ImageView image = (ImageView) group.findViewById(R.id.item_layout_leftImage);
			image.setImageDrawable(Drawable.createFromPath(imgs[position * 2]));
			image = (ImageView) group.findViewById(R.id.item_layout_rightImage);
		if(position*2+1<imgs.length)
			image.setImageDrawable(Drawable.createFromPath(imgs[position * 2+1]));
		else
			image.setImageResource(0);

	}

}
