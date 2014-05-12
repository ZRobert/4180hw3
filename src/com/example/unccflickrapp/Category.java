//Robert Payne
//Homework 3
//Stores the title, id, and num of photos
//of an album. The constructor formats
//the JSONObject.
package com.example.unccflickrapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Category {
	private String title;
	private String id;
	private String numOfPhotos;
	
	@Override
	public String toString() {
		return title + " (" + numOfPhotos + ")";
	}
	public Category(String title, String id) {
		super();
		this.title = title;
		this.id = id;
	}
	public Category(JSONObject categoriesJSONObject) throws JSONException {
		// TODO Auto-generated constructor stub
		this.id = categoriesJSONObject.getString("id");
		this.title = categoriesJSONObject.getString("title").split("[:+}]")[1];
		this.title = this.title.replace("\\", "").replace("\"", "");
		this.numOfPhotos = categoriesJSONObject.getString("photos");
		 
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
	

}
