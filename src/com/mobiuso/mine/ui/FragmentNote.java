package com.mobiuso.mine.ui;

import java.util.ArrayList;

import com.mobiuso.mine.databasehandlers.DiaryDbHandler;
import com.mobiuso.mine.listadapters.NoteListAdapter;
import com.mobiuso.mine.privatediary.NoteListActivity;
import com.mobiuso.mine.privatediary.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.app.Fragment;
import android.content.Context;
 
public class FragmentNote extends Fragment {
	
	private ListView noteListView;
	private ArrayList<String> noteList = new ArrayList<String>();
	private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	context = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_textnotes, container, false);
        noteListView = (ListView) rootView.findViewById(R.id.noteListView);
        
		getAllNotes();
		NoteListAdapter noteListAdapter = new NoteListAdapter(context, noteListView,noteList);
		noteListView.setAdapter(noteListAdapter);
		
		return rootView;
    }
	public void getAllNotes(){
		DiaryDbHandler db = new DiaryDbHandler(context);
		noteList = (ArrayList<String>) db.getAllNotes();
	}
}