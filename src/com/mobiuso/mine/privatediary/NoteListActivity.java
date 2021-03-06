package com.mobiuso.mine.privatediary;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.mobiuso.mine.databasehandlers.DiaryDbHandler;
import com.mobiuso.mine.listadapters.NoteListAdapter;
import com.mobiuso.mine.ui.FragmentNote.noteType;
import com.mobiuso.mine.ui.Note;

public class NoteListActivity extends Activity {

	private ListView noteListView;
	private ArrayList<Note> noteList = new ArrayList<Note>();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_list);
		noteListView = (ListView) findViewById(R.id.noteListView);
		getAllNotes();
//		NoteListAdapter noteListAdapter = new NoteListAdapter(NoteListActivity.this, noteListView,noteList);
//		noteListView.setAdapter(noteListAdapter);
	}
	
	public void getAllNotes(){
		DiaryDbHandler db = new DiaryDbHandler(NoteListActivity.this);
		noteList = (ArrayList<Note>) db.getAllNotes(noteType.TEXT);
	}
}
