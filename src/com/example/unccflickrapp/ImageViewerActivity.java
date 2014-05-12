//Robert Payne
//Homework 3
//This activity calls the ASyncLoadImage
//task and displays the image on the view.
//This activity also supports the left/right
//swiping gestures for loading adjacent
//photos with respect to the order
//they are displayed on the grid view.
package com.example.unccflickrapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.gesture.GestureOverlayView;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ImageViewerActivity extends Activity implements OnGestureListener {
	FrameLayout frameLayout;
	ImageView imageView;
	int currentPhoto;
	ProgressDialog pd;
	// GestureOverlayView gesture;
	GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);
		frameLayout = (FrameLayout) findViewById(R.id.FrameLayout1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		// gesture = (GestureOverlayView)
		// findViewById(R.id.gestureOverlayView1);
		// gesture.setOnTouchListener(this);
		gestureDetector = new GestureDetector(this);
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("ID")) {
				currentPhoto = getIntent().getExtras().getInt("ID");
				new ASyncLoadImage(this).execute(PhotosArray.get()
						.get(currentPhoto).getFullSizeURL());

			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_viewer, menu);
		return true;
	}

	public void showProgressDialog() {
		// TODO Auto-generated method stub
		pd = new ProgressDialog(ImageViewerActivity.this);
		pd.setMessage("Loading photo...");
		pd.setCancelable(false);
		pd.show();
	}

	public void dismissProgressDialog() {
		// TODO Auto-generated method stub

		pd.dismiss();
	}

	public void receiveMessages(Drawable result) {
		// TODO Auto-generated method stub
		imageView.setImageDrawable(result);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (velocityX > 0) {
			Log.d("Fling", "right" + "Current: " + currentPhoto + "");
			if (currentPhoto < PhotosArray.get().size() - 1) {
				currentPhoto++;
				new ASyncLoadImage(this).execute(PhotosArray.get()
						.get(currentPhoto).getFullSizeURL());

			}

		} else if (velocityX < 0) {
			Log.d("Fling", "left" + "Current: " + currentPhoto + "");
			if (currentPhoto > 0) {
				currentPhoto--;
				new ASyncLoadImage(this).execute(PhotosArray.get()
						.get(currentPhoto).getFullSizeURL());
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		return gestureDetector.onTouchEvent(me);
	}

}
