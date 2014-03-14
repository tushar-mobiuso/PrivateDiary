package com.mobiuso.mine.ui;

import java.util.Date;

public class Note {

	private String noteText;
	private int noteId;
	private String noteDate;
	
	public Note(int noteId, String noteDate, String noteText) {
		// TODO Auto-generated constructor stub
		this.noteId = noteId;
		this.noteDate = noteDate;
		this.noteText = noteText;
	}
	public String getCreatedDate() {
		return noteDate;
	}
	public void setCreatedDate(String createdDate) {
		this.noteDate = createdDate;
	}
	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
}
