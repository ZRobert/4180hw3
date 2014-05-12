//Robert Payne
//Homework 3
//Parses the result from the getPhoto flickr api
//and prepares them to be turned into objects.
package com.example.unccflickrapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhotoJSONParser {

	static public class PhotoJSONParserUtil {
		
		static ArrayList<Photo> parsePhotos(String jsonString)
				throws JSONException {

			ArrayList<Photo> photos = new ArrayList<Photo>();
			JSONObject photosJSONObject = new JSONObject(jsonString);
			photosJSONObject = photosJSONObject.getJSONObject("photoset");
			JSONArray photosJSONArray = photosJSONObject.getJSONArray("photo");
			for (int i = 0; i < photosJSONArray.length(); i++) {
				JSONObject photoJSONObject = photosJSONArray.getJSONObject(i);
				Photo photo = new Photo(photoJSONObject);
				photos.add(photo);
			}
			return photos;
		}
	}
}
