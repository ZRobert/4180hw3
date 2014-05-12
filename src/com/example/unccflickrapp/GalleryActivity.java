//Robert Payne
//Homework 3
//Uses a grid view to output thumbnails
//of the pictures contained within the
//photo album. When the user touches
//the thumbnail, it will load up
//the ImageViewerActivity.
package com.example.unccflickrapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryActivity extends Activity {
	String galleryId;
	GridView gridView;

	ImageAdapter adapter;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("ID")) {
				galleryId = getIntent().getExtras().getString("ID");
			}
		}
		new ASyncLoadPhotos(this).execute(galleryId);
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Log.d("demo1", (String) ((TextView) view).getText());
				Intent i = new Intent(getBaseContext(),
						ImageViewerActivity.class);
				i.putExtra("ID", position);
				startActivity(i);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallery, menu);
		return true;
	}

	public void showProgressDialog() {
		// TODO Auto-generated method stub
		pd = new ProgressDialog(GalleryActivity.this);
		pd.setMessage("Loading photos...");
		pd.setCancelable(false);
		pd.show();
	}

	public void dismissProgressDialog() {
		// TODO Auto-generated method stub

		pd.dismiss();
	}

	public void receiveMessages(ArrayList<Photo> result) {
		// TODO Auto-generated method stub
		PhotosArray.add(result);
		adapter = new ImageAdapter(this.getBaseContext());
		gridView.setAdapter(adapter);
	}

}
