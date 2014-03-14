package com.mobiuso.mine.ui;

import java.util.ArrayList;
import java.util.Calendar;

import com.mobiuso.mine.databasehandlers.DiaryDbHandler;
import com.mobiuso.mine.listadapters.NoteListAdapter;
import com.mobiuso.mine.privatediary.NoteListActivity;
import com.mobiuso.mine.privatediary.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

public class FragmentNote extends Fragment {

	private ListView noteListView;
	private ArrayList<Note> noteList = new ArrayList<Note>();
	private Context context;
	NoteListAdapter noteListAdapter;

	private Button btnwriteNote;
	private Button btnSave;
	private EditText etUserNote;

	public enum noteType {
		TEXT, AUDIO, PICTURE
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = getActivity();
		View rootView = inflater.inflate(R.layout.fragment_textnotes,
				container, false);
		noteListView = (ListView) rootView.findViewById(R.id.noteListView);
		btnwriteNote = (Button) rootView. findViewById(R.id.btnWriteNote);
		getAllTextNotes();
		if (noteList.size() > 0) {
			noteListAdapter = new NoteListAdapter(context, noteListView,
					noteList, FragmentNote.this);
			noteListView.setAdapter(noteListAdapter);
		}

//		btnwriteNote.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				showPopUpWindow();
//			}
//		});
		return rootView;
	}

	public void getAllTextNotes() {
		DiaryDbHandler db = new DiaryDbHandler(context);
		noteList = (ArrayList<Note>) db.getAllNotes(noteType.TEXT);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getAllTextNotes();
		if (noteList.size() > 0) {
			noteListAdapter = new NoteListAdapter(context, noteListView,
					noteList, FragmentNote.this);
			noteListView.setAdapter(noteListAdapter);
		}
		if (noteList.size() > 0) {
			noteListView.invalidateViews();
			noteListAdapter.notifyDataSetChanged();
			noteListAdapter.notifyDataSetInvalidated();
		}
	}

	public void deleteListItem(int position) {
		
		int id = noteList.get(position).getNoteId();
		DiaryDbHandler db = new DiaryDbHandler(context);
		db.deleteNote(id, noteType.TEXT);
		noteList.remove(position);
		Toast.makeText(context, "Item deleted " + position, 2000).show();
		onResume();
	}

	public void showPopUpWindow() {
		// TODO Auto-generated method stub

		// 12-03-2013 Nitin Mahale
		// Showing writ Note pop up screen

		LayoutInflater feedback_Popup_inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		View userNoteView = feedback_Popup_inflater.inflate(
				R.layout.activity_writenote, null);

		final PopupWindow writenote_popup = new PopupWindow(userNoteView,
				LayoutParams.WRAP_CONTENT - 20, LayoutParams.WRAP_CONTENT - 20);

		writenote_popup.setContentView(userNoteView);
		writenote_popup.setFocusable(true);

		btnSave = (Button) userNoteView.findViewById(R.id.btnSaveNote);
		etUserNote = (EditText) userNoteView.findViewById(R.id.etUserNote);

		writenote_popup.setBackgroundDrawable(new BitmapDrawable());
		writenote_popup.setOutsideTouchable(true);

		writenote_popup.showAsDropDown(btnwriteNote, 0, 0);

		ImageView closePopup = (ImageView) userNoteView
				.findViewById(R.id.ivClosePopUpView);

		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String userNote = etUserNote.getText().toString();
				// String currentDate = DateFormat.getDateInstance().format(
				// new Date());

				DiaryDbHandler db = new DiaryDbHandler(context);
				db.addTextNoteRecord(Calendar.getInstance().getTime()
						.toString(), userNote);

				writenote_popup.setOutsideTouchable(false);
				writenote_popup.dismiss();
				onResume();
			}
		});

		closePopup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				writenote_popup.setOutsideTouchable(false);
				writenote_popup.dismiss();
				onResume();
			}
		});
	}
}