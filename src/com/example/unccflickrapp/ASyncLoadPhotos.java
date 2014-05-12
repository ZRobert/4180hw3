//Robert Payne
//Homework 3
//Used by both ImageAdapter and ImageViewerActivity
//to retrieve the images off of the flickr site.
//Communicates with the active activity.
package com.example.unccflickrapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class ASyncLoadPhotos extends AsyncTask<String, Void, ArrayList<Photo>> {
	
	private GalleryActivity galleryActivity;
	private HttpClient client = new DefaultHttpClient();
	private String galleryId;
	
	public ASyncLoadPhotos(GalleryActivity galleryActivity) {
		// TODO Auto-generated constructor stub
		this.galleryActivity = galleryActivity;

	}

	@Override
	protected ArrayList<Photo> doInBackground(String... params) {
		galleryId = params[0];
		String urlString = 
		"http://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=6d7cfa13af13b9062395dae3af0ef1c9&photoset_id="
		+ galleryId 
		+ "&extras=url_sq%2Curl_m&format=json&nojsoncallback=1";
		ArrayList<Photo> result = null;
		try {

			HttpGet request = new HttpGet(urlString);
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line + "\n");
				}
				in.close();
				result = PhotoJSONParser.PhotoJSONParserUtil.parsePhotos(sb
						.toString());
				
				// bundle.putString("RESULT", sb.toString());
			} else {
				// bundle.putString("ERROR", "Problem with Response");
			}
		} catch (UnsupportedEncodingException e) {
			// bundle.putString("ERROR", "Problem with Params");
		} catch (Exception e) {
			// bundle.putString("ERROR", "Problem with URL");
		}

		return result;
	}

	@Override
	protected void onPreExecute() {
		this.galleryActivity.showProgressDialog();

	}

	@Override
	protected void onPostExecute(ArrayList<Photo> result) {
		this.galleryActivity.dismissProgressDialog();
		this.galleryActivity.receiveMessages(result);
	}

}
