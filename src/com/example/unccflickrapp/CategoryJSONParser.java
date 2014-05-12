//Robert Payne
//Homework 3
//parser to get the album information from flickr ready
//and converts to Category Objects
package com.example.unccflickrapp;

import java.util.ArrayList;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CategoryJSONParser {

	static public class CategoryJSONParserUtil {

		static ArrayList<Category> parseCategories(String jsonString)
				throws JSONException {
		
			ArrayList<Category> categories = new ArrayList<Category>();
			JSONObject categoriesJSONObject = new JSONObject(jsonString); 
			categoriesJSONObject = categoriesJSONObject.getJSONObject("photosets");
			JSONArray categoriesJSONArray = categoriesJSONObject.getJSONArray("photoset");
			for (int i = 0; i < categoriesJSONArray.length(); i++) {
				JSONObject categoryJSONObject = categoriesJSONArray.getJSONObject(i);
				Category category = new Category(categoryJSONObject);
				categories.add(category);
			}
			return categories;
		}
	}
}
