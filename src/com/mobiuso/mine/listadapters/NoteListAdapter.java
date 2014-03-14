package com.mobiuso.mine.listadapters;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiuso.mine.privatediary.R;
import com.mobiuso.mine.ui.FragmentNote;
import com.mobiuso.mine.ui.Note;

public class NoteListAdapter extends BaseAdapter implements OnClickListener {

	private Context context;
	private ListView listview;
	private ViewHolder viewHolder;
	private ArrayList<Note> noteList;
	Fragment noteCurrentFragment;

	public NoteListAdapter(Context context, ListView noteListView,
			ArrayList<Note> noteList, Fragment fragmentNote) {
		super();
		this.context = context;
		this.listview = noteListView;
		this.noteList = noteList;
		noteCurrentFragment = fragmentNote;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = noteList.size();
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return noteList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (convertView == null) {
			v = LayoutInflater.from(context).inflate(
					R.layout.notes_adapter_list, null);
			viewHolder = new ViewHolder();
			viewHolder.fileNameText = (TextView) v
					.findViewById(R.id.tvFileName);
			viewHolder.createdOnText = (TextView) v.findViewById(R.id.tvCreatedDate);
			viewHolder.deleteItemImg = (ImageButton) v
					.findViewById(R.id.imgDelete);

			viewHolder.fileNameText.setText((noteList.get(position)
					.getNoteText()).replace("/mnt/sdcard/Note-", ""));
			viewHolder.createdOnText.setText(noteList.get(position).getCreatedDate());
			viewHolder.deleteItemImg.setImageResource(R.drawable.close);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}
		viewHolder.deleteItemImg.setOnClickListener(this);

		return v;
	}

	class ViewHolder {
		TextView fileNameText;
		TextView createdOnText;
		ImageButton deleteItemImg;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int position = listview.getPositionForView(v);
		if (noteCurrentFragment instanceof FragmentNote) {
			((FragmentNote) noteCurrentFragment).deleteListItem(position);
		}
	}

}
