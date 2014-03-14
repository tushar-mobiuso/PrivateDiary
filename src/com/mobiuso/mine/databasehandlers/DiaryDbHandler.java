package com.mobiuso.mine.databasehandlers;

import java.util.ArrayList;
import java.util.List;

import com.mobiuso.mine.ui.FragmentNote.noteType;
import com.mobiuso.mine.ui.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDbHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "privatediaryDB";
	private static final String table_audionote_List = "Audio_ListTable";
	private static final String table_textnote_List = "Text_ListTable";
	private static final String table_picturenote_List = "Picture_ListTable";
	private static final String index = "Id";
	private static final String DATE = "Date";
	private static final String TIME = "Time";
	private static final String file_name = "File_Name";
	private static final String note_title = "Note_Title";
	private static final String note_text = "Note_Text";
	private static final String picture_caption = "Picture_Caption";

	public DiaryDbHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_AUDIO_NOTELIST_TABLE = "CREATE TABLE "
				+ table_audionote_List + "(" + index
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT,"
				+ file_name + " TEXT" + ")";

		String CREATE_TEXT_NOTELIST_TABLE = "CREATE TABLE "
				+ table_textnote_List + "(" + index + " INTEGER PRIMARY KEY,"
				+ DATE + " TEXT," + note_text + " TEXT" + ")";

		String CREATE_PICTURE_NOTELIST_TABLE = "CREATE TABLE "
				+ table_picturenote_List + "(" + index
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT,"
				+ file_name + " TEXT" + picture_caption + " TEXT" + ")";

		try {
			db.execSQL(CREATE_AUDIO_NOTELIST_TABLE);
			db.execSQL(CREATE_TEXT_NOTELIST_TABLE);
			db.execSQL(CREATE_PICTURE_NOTELIST_TABLE);
			// db.execSQL(CREATE_CONTACTS_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void addAudioNoteRecord(String time, String date, String fileName) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(INDEX, serverUserID);
		values.put(TIME, time);
		values.put(DATE, date);
		values.put(file_name, fileName);

		try {
			db.insert(table_audionote_List, null, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
	}

	public void addTextNoteRecord(String date, String userNote) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		// values.put(INDEX, serverUserID);
		values.put(DATE, date);
		values.put(note_text, userNote);

		try {
			db.insert(table_textnote_List, null, values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
	}

	public List<String> getAllAudioNotes() {

		List<String> noteList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT  * FROM  " + table_audionote_List;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				do {
					String fileName = cursor.getString(3);
					noteList.add(fileName);
				} while (cursor.moveToNext());
			}
		}
		// return contact list
		return noteList;
	}

	public List<Note> getAllNotes(noteType type) {
		String noteTable = null;

		if (type == noteType.AUDIO) {
			noteTable = table_audionote_List;
		} else if (type == noteType.TEXT) {
			noteTable = table_textnote_List;
		} else if (type == noteType.PICTURE) {
			noteTable = table_picturenote_List;
		}

		List<Note> noteList = new ArrayList<Note>();
		SQLiteDatabase db = this.getWritableDatabase();

		// Select All Query
		String selectQuery = "SELECT  * FROM  " + noteTable;
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				do {
					int noteId = cursor.getInt(0);
					String noteDate = cursor.getString(1);
					String noteText = cursor.getString(2);
					Note note  = new Note(noteId, noteDate, noteText);
					noteList.add(note);
				} while (cursor.moveToNext());
			}
		}
		// return contact list
		db.close();
		return noteList;
	}

	public void deleteNote(int position, noteType type) {
		// TODO Auto-generated method stub
		String noteTable = null;

		if (type == noteType.AUDIO) {
			noteTable = table_audionote_List;
		} else if (type == noteType.TEXT) {
			noteTable = table_textnote_List;
		} else if (type == noteType.PICTURE) {
			noteTable = table_picturenote_List;
		}
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "SELECT note_text FROM " + noteTable + " WHERE " + index
				+ " = " + position;
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.getCount() > 0) {
			String deleteQuery = "DELETE FROM " + noteTable + " WHERE " + index
					+ " = " + position;
			db.execSQL(deleteQuery);
		}
		db.close();
	}
}