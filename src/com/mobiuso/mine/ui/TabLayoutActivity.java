package com.mobiuso.mine.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mobiuso.mine.privatediary.AudioRecordActivity;
import com.mobiuso.mine.privatediary.R;

public class TabLayoutActivity extends Activity {

	ActionBar.Tab Tab1, Tab2, Tab3;
	Fragment fragmentTab1 = new FragmentNote();
	Fragment fragmentTab2 = new FragmentAudio();
	Fragment fragmentTab3 = new FragmentPhoto();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
//		Button btn = (Button) findViewById(R.id.button1);
//		
//		btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent in = new Intent(TabLayoutActivity.this, AudioRecordActivity.class);
//				startActivity(in);
//			}
//		});
		
		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setIcon(R.drawable.icon_textnote_tab);
		Tab2 = actionBar.newTab().setIcon(R.drawable.icon_audionote_tab);
		Tab3 = actionBar.newTab().setIcon(R.drawable.icon_photonote_tab);

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
	}
}