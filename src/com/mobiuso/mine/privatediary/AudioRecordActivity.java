package com.mobiuso.mine.privatediary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mobiuso.mine.databasehandlers.DiaryDbHandler;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AudioRecordActivity extends Activity {

	Integer[] freqset = { 11025, 16000, 22050, 44100 };
	private ArrayAdapter<Integer> adapter;
	private String path;

	Spinner spFrequency;
	Button startRec, stopRec, playBack, saveNote, showNoteList;

	Boolean recording;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio_recording_activity);
		startRec = (Button) findViewById(R.id.startrec);
		stopRec = (Button) findViewById(R.id.stoprec);
		playBack = (Button) findViewById(R.id.playback);
		saveNote = (Button) findViewById(R.id.bSaveAudioNote);
		showNoteList = (Button) findViewById(R.id.bShowNoteList);

		// saveNote.setVisibility(View.INVISIBLE);

		startRec.setOnClickListener(startRecOnClickListener);
		stopRec.setOnClickListener(stopRecOnClickListener);
		playBack.setOnClickListener(playBackOnClickListener);
		playBack.setOnClickListener(playBackOnClickListener);
		saveNote.setOnClickListener(saveNoteOnClickListener);
		showNoteList.setOnClickListener(showNoteListOnClickListener);

		spFrequency = (Spinner) findViewById(R.id.frequency);
		adapter = new ArrayAdapter<Integer>(this,
				android.R.layout.simple_spinner_item, freqset);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spFrequency.setAdapter(adapter);

	}

	OnClickListener startRecOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			Toast.makeText(AudioRecordActivity.this, "Recording Started",
					Toast.LENGTH_SHORT).show();
			Thread recordThread = new Thread(new Runnable() {
				@Override
				public void run() {
					recording = true;
					startRecord();
				}
			});
			recordThread.start();
		}
	};

	OnClickListener stopRecOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			recording = false;
			Toast.makeText(AudioRecordActivity.this, "Recording Stopped",
					Toast.LENGTH_SHORT).show();
			playBack.setVisibility(View.VISIBLE);
			saveNote.setVisibility(View.VISIBLE);
		}
	};

	OnClickListener playBackOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			playRecord();
		}
	};

	OnClickListener saveNoteOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String currentDate = DateFormat.getDateInstance()
					.format(new Date());
			DiaryDbHandler db = new DiaryDbHandler(AudioRecordActivity.this);
			db.addNoteRecord(Calendar.getInstance().getTime().toString(),
					currentDate, path);
			Toast.makeText(AudioRecordActivity.this, "Note added",
					Toast.LENGTH_SHORT).show();
		}
	};

	OnClickListener showNoteListOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent noteListIntent = new Intent(AudioRecordActivity.this,
					NoteListActivity.class);
			startActivity(noteListIntent);
		}
	};

	private void startRecord() {
		path = Environment.getExternalStorageDirectory() + "/Note-"
				+ Calendar.getInstance().getTime().toString();
		File file = new File(path);

		int sampleFreq = (Integer) spFrequency.getSelectedItem();

		try {
			file.createNewFile();

			OutputStream outputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					outputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(
					bufferedOutputStream);

			int minBufferSize = AudioRecord.getMinBufferSize(sampleFreq,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT);

			short[] audioData = new short[minBufferSize];

			AudioRecord audioRecord = new AudioRecord(
					MediaRecorder.AudioSource.MIC, sampleFreq,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, minBufferSize);

			audioRecord.startRecording();

			while (recording) {
				int numberOfShort = audioRecord.read(audioData, 0,
						minBufferSize);
				for (int i = 0; i < numberOfShort; i++) {
					dataOutputStream.writeShort(audioData[i]);
				}
			}
			audioRecord.stop();
			dataOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void playRecord() {
		File file = new File(path);
		int shortSizeInBytes = Short.SIZE / Byte.SIZE;

		int bufferSizeInBytes = (int) (file.length() / shortSizeInBytes);
		short[] audioData = new short[bufferSizeInBytes];

		try {
			InputStream inputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					inputStream);
			DataInputStream dataInputStream = new DataInputStream(
					bufferedInputStream);

			int i = 0;
			while (dataInputStream.available() > 0) {
				audioData[i] = dataInputStream.readShort();
				i++;
			}

			dataInputStream.close();

			int sampleFreq = (Integer) spFrequency.getSelectedItem();

			AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
					sampleFreq, AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes,
					AudioTrack.MODE_STREAM);

			audioTrack.play();
			audioTrack.write(audioData, 0, bufferSizeInBytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}