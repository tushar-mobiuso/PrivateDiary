package com.mobiuso.mine.listadapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiuso.mine.privatediary.R;

public class NoteListAdapter extends BaseAdapter {

	private Context context;
	private ListView listview;
	private ViewHolder viewHolder;
	private ArrayList<String> noteList;

	public NoteListAdapter(Context context, ListView noteListView,
			ArrayList<String> noteList) {
		super();
		this.context = context;
		this.listview = noteListView;
		this.noteList = noteList;
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
		return position;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
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
			viewHolder.closeImg = (ImageButton) v.findViewById(R.id.imgDelete);

			viewHolder.fileNameText.setText(noteList.get(position).replace("/mnt/sdcard/Note-", ""));
			viewHolder.closeImg.setImageResource(R.drawable.close);
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) v.getTag();
		}
		return v;
	}

	class ViewHolder {

		TextView fileNameText;
		ImageButton closeImg;
	}

}
