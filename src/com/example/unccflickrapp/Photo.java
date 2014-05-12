//Robert Payne
//Homework 3
//Stores both of the URL for the thumbnail
//and the full size bitmaps. The constructor
//formats the JSONObject.
package com.example.unccflickrapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Photo {
	
	private String thumbnailURL;
	private String fullSizeURL;
	
	@Override
	public String toString() {
		return "Photo [thumbnailURL=" + thumbnailURL + ", fullSizeURL="
				+ fullSizeURL + "]";
	}

	public String getThumbnailURL() {
		return thumbnailURL;
	}
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}
	public String getFullSizeURL() {
		return fullSizeURL;
	}
	public void setFullSizeURL(String fullSizeURL) {
		this.fullSizeURL = fullSizeURL;
	}
	public Photo(String thumbnailURL, String fullSizeURL) {
		super();
		this.thumbnailURL = thumbnailURL;
		this.fullSizeURL = fullSizeURL;
		
	}
	public Photo(JSONObject photoJSONObject) throws JSONException {
		this.thumbnailURL = photoJSONObject.getString("url_sq").replace("\\", "");
		this.fullSizeURL = photoJSONObject.getString("url_m").replace("\\", "");
	}
	
	
}
