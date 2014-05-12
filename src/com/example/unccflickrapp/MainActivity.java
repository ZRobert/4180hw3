//Robert Payne
//Homework 3
//Sets up the list view by calling
//ASyncLoadCategories and placing
//the Array List with the categories
//inside the listview.
package com.example.unccflickrapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	ArrayList<Category> categories;
	ListView listView;
	ArrayAdapter<Category> adapter;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.ListView1);

		new ASyncLoadCategories(this).execute();
  
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getBaseContext(), GalleryActivity.class);
				i.putExtra("ID", categories.get(position).getId());
				startActivity(i);

			}
		});
	}

	public void showProgressDialog() {
		pd = new ProgressDialog(MainActivity.this);
		pd.setMessage("Loading photo sets...");
		pd.setCancelable(false);
		pd.show();

	}

	public void dismissProgressDialog() {
		pd.dismiss();
	}

	public void receiveMessages(ArrayList<Category> list) {
		this.categories = new ArrayList<Category>(list);
		adapter = new ArrayAdapter<Category>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				categories);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
