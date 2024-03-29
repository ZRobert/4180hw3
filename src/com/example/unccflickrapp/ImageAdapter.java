//Robert Payne
//Homework 3
//Handles the images that are used by
//the grid view in gallery activity.
//Will call ASyncLoadImage in order
//to get the correct image to load.

package com.example.unccflickrapp;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Photo> photos;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return PhotosArray.get().size();
	}

	public Object getItem(int position) {
		return PhotosArray.get().get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);

		} else {
			
			imageView = (ImageView) convertView;

		}
		imageView.setImageResource(R.drawable.ic_launcher);
		new ASyncLoadImage(imageView).executeOnExecutor(
				AsyncTask.SERIAL_EXECUTOR, PhotosArray.get().get(position)
						.getThumbnailURL());
		return imageView;
	}

	public ImageView updateView(Drawable image) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageDrawable(image);
		return imageView;
	}
}
