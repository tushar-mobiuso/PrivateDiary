package com.mobiuso.mine.ui;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TabHost;

import com.mobiuso.mine.databasehandlers.DiaryDbHandler;
import com.mobiuso.mine.privatediary.R;
import com.mobiuso.mine.ui.FragmentNote.noteType;

public class TabLayoutActivity extends Activity {

	ActionBar.Tab Tab1, Tab2, Tab3;
	Fragment fragmentTab1 = new FragmentNote();
	Fragment fragmentTab2 = new FragmentAudio();
	Fragment fragmentTab3 = new FragmentPhoto();

	private Button btnwriteNote;
	private Button btnSave;
	private EditText etUserNote;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		btnwriteNote = (Button) findViewById(R.id.btnWriteNote);

		btnwriteNote.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				ActionBar.Tab tab = getActionBar().getSelectedTab();
				noteType type = (noteType) tab.getTag();
				
				if (type == noteType.AUDIO) {
					
				} else if (type == noteType.TEXT) {
//					showPopUpWindow(TabLayoutActivity.this);
				} else if (type == noteType.PICTURE) {
					
				}
			}
		});

		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setIcon(R.drawable.icon_textnote_tab);
		Tab1.setTag(noteType.TEXT);
		Tab2 = actionBar.newTab().setIcon(R.drawable.icon_audionote_tab);
		Tab2.setTag(noteType.AUDIO);
		Tab3 = actionBar.newTab().setIcon(R.drawable.icon_photonote_tab);
		Tab3.setTag(noteType.PICTURE);

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
	}

//	private void showPopUpWindow(Context context) {
//		// TODO Auto-generated method stub
//
//		// 12-03-2013 Nitin Mahale
//		// Showing writ Note pop up screen
//
//		LayoutInflater feedback_Popup_inflater = (LayoutInflater) getBaseContext()
//				.getSystemService(LAYOUT_INFLATER_SERVICE);
//		View userNoteView = feedback_Popup_inflater.inflate(
//				R.layout.activity_writenote, null);
//		final PopupWindow writenote_popup = new PopupWindow(userNoteView,
//				LayoutParams.WRAP_CONTENT - 20, LayoutParams.WRAP_CONTENT - 20);
//
//		writenote_popup.setContentView(userNoteView);
//		writenote_popup.setFocusable(true);
//
//		btnSave = (Button) userNoteView.findViewById(R.id.btnSaveNote);
//		etUserNote = (EditText) userNoteView.findViewById(R.id.etUserNote);
//
//		writenote_popup.setBackgroundDrawable(new BitmapDrawable());
//		writenote_popup.setOutsideTouchable(true);
//
//		writenote_popup.showAsDropDown(btnwriteNote, 0, 0);
//
//		ImageView closePopup = (ImageView) userNoteView
//				.findViewById(R.id.ivClosePopUpView);
//
//		btnSave.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				String userNote = etUserNote.getText().toString();
//				// String currentDate = DateFormat.getDateInstance().format(
//				// new Date());
//
//				DiaryDbHandler db = new DiaryDbHandler(TabLayoutActivity.this);
//				db.addTextNoteRecord(Calendar.getInstance().getTime()
//						.toString(), userNote);
//				
//				writenote_popup.setOutsideTouchable(false);
//				writenote_popup.dismiss();
//			}
//		});
//
//		closePopup.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				writenote_popup.setOutsideTouchable(false);
//				writenote_popup.dismiss();
//			}
//		});
//	}
}