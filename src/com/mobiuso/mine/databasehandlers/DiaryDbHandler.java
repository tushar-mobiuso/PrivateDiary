package com.mobiuso.mine.databasehandlers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDbHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "privatediaryDB";
	private static final String TABLE_AUDIO_LIST = "Audio_ListTable";
	private static final String TABLE_CONTACTS = "Contact_ListTable";
	private static final String INDEX = "id";
	private static final String DATE = "date";
	private static final String TIME = "time";
	private static final String FILE_NAME = "file_name";
	private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

	public DiaryDbHandler(Context context) {

		super(context,DATABASE_NAME,null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_AUDIOLIST_TABLE = "CREATE TABLE " + TABLE_AUDIO_LIST + "("
                + INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT,"
                + TIME + " TEXT,"  + FILE_NAME + " TEXT" + ")";
		
//		 String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
//	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
//	                + KEY_PH_NO + " TEXT" + ")";
		try{
			db.execSQL(CREATE_AUDIOLIST_TABLE);
//			db.execSQL(CREATE_CONTACTS_TABLE);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void addNoteRecord(String time, String date, String fileName) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(INDEX, serverUserID);
		values.put(TIME, time);
		values.put(DATE, date);
		values.put(FILE_NAME, fileName);

		try{
			db.insert(TABLE_AUDIO_LIST, null, values);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		db.close();
	}
	
	 public List<String> getAllNotes() {
		 
		    List<String> noteList = new ArrayList<String>();
		   // Select All Query
		    String selectQuery = "SELECT  * FROM  " + TABLE_AUDIO_LIST ;
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	String fileName = cursor.getString(3);
		            noteList.add(fileName);
		        } while (cursor.moveToNext());
		    }
		 
		    // return contact list
		    return noteList;
		}
}