//Robert Payne
//Homework 3
//Made this array of photos static that way the 
//GalleryActivity and the ImageViewerActivity
//have access to the URLs without having to 
//have multiple ArrayLists
package com.example.unccflickrapp;

import java.util.ArrayList;

import android.util.Log;

public class PhotosArray extends ArrayList<Photo>{

	private static final long serialVersionUID = 1L;
	private static ArrayList<Photo> photos;

	static void add(ArrayList<Photo> object) {
		// TODO Auto-generated method stub
		photos = object;
	}
	static ArrayList<Photo> get() {
		return photos;
	}
	
	
}
