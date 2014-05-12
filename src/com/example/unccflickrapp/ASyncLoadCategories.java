//Robert Payne
//Homework 3
//This class reads the UNCC flickr photo album
//information and sends it to be parsed. 
//Communicates with the main activity.
package com.example.unccflickrapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ASyncLoadCategories extends
		AsyncTask<Void, Void, ArrayList<Category>> {
	private MainActivity main;
	private HttpClient client = new DefaultHttpClient();

	public ASyncLoadCategories(MainActivity mainActivity) {
		this.main = mainActivity;
	}

	@Override
	protected ArrayList<Category> doInBackground(Void...v) {
		String urlString = "http://api.flickr.com/services/rest/?method=flickr.photosets.getlist&api_key=6d7cfa13af13b9062395dae3af0ef1c9&user_id=40729938%40N03&format=json&nojsoncallback=1"; // params[0];

		ArrayList<Category> result = null;
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
				result = CategoryJSONParser.CategoryJSONParserUtil
						.parseCategories(sb.toString());
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
		this.main.showProgressDialog();

	}

	@Override
	protected void onPostExecute(ArrayList<Category> result) {
		this.main.dismissProgressDialog();
		this.main.receiveMessages(result);

	}

}
