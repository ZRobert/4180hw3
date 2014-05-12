//Robert Payne
//Homework 3
//AsyncTask that is dedicated to retrieving
//an image from a url and communicates
//with the active activity. 
package com.example.unccflickrapp;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ASyncLoadImage extends AsyncTask<String, Void, Drawable> {
		ImageViewerActivity activity = null;
		HttpClient client = new DefaultHttpClient();
		ImageView imageView = null;
		int position;
		

		public ASyncLoadImage(ImageViewerActivity mainActivity) {
			// TODO Auto-generated constructor stub
			this.activity = mainActivity;
		
		}
		public ASyncLoadImage(ImageView imageView){
			this.imageView = imageView;
		}

		@Override
		protected Drawable doInBackground(String... params) {
			String urlString =  params[0];
			Drawable result = null;
			try {

					URL url = new URL(urlString);
					InputStream content = (InputStream)url.getContent();
					result = Drawable.createFromStream(content , "src"); 

			} catch (UnsupportedEncodingException e) {
				// bundle.putString("ERROR", "Problem with Params");
			} catch (Exception e) {
				// bundle.putString("ERROR", "Problem with URL");
			}

			
			return result;
		}

		@Override
		protected void onPreExecute() {
			if(activity != null){
				this.activity.showProgressDialog();
			}

		}

		@Override
		protected void onPostExecute(Drawable result) {
			
			if(activity != null){
				this.activity.dismissProgressDialog();
				this.activity.receiveMessages(result);

			} 
			else if(imageView != null){
				 this.imageView.setImageDrawable(result);
			}

		}
}
