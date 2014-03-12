package com.mobiuso.mine.privatediary;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PrivateDiaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_private_diary);
		Button recordNote = (Button) findViewById(R.id.bRecordNote);
		Button photoNote = (Button) findViewById(R.id.bPhotoNote);

		File f = new File(Environment.getExternalStorageDirectory()
				+ "/PrivateDiary/MyData");
//		if (!f.exists()) 
		{
			f.mkdirs();
		}
		
		recordNote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PrivateDiaryActivity.this,
						AudioRecordActivity.class);
				startActivity(i);
			}
		});
		photoNote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PrivateDiaryActivity.this,
						CameraPhotoCaptureActivity.class);
				startActivity(i);
			}
		});

		Button bPressMe = (Button) findViewById(R.id.bPressMe);
		bPressMe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vibrator.vibrate(1000);
				Log.d("Vibrate", "success");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_private_diary, menu);
		return true;
	}

}
