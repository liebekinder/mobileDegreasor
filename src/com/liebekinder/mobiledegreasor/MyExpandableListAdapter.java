package com.liebekinder.mobiledegreasor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liebekinder.mobiledegreasor.core.Category;
import com.liebekinder.mobiledegreasor.core.Child;
import com.liebekinder.mobiledegreasor.core.MyOnCheckedChangeListener;
import com.liebekinder.mobiledegreasor.core.Task;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	
	private ArrayList<Category> list;

	public MyExpandableListAdapter(Context context, ArrayList<Category> list) {
		super();
		this.list = list;
		mContext = context;
	}

	@Override
	public Task getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition).getTasksList().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	/*@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView row = (TextView) convertView;
		if (row == null) {
			row = new TextView(mContext);
		}
		row.setText(mContents[groupPosition][childPosition]);
		return row;
	}*/
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		
		return new Child(mContext, list.get(groupPosition).getTasksList().get(childPosition)).create();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).getTasksList().size();
	}

	@Override
	public Category getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	/*@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView row = (TextView) convertView;
		if (row == null) {
			row = new TextView(mContext);
		}
		row.setTypeface(Typeface.DEFAULT_BOLD);
		row.setText(mTitles[groupPosition]);
		return row;
	}*/
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
		View convertView, ViewGroup parent) {
	
		TextView tv = new TextView(mContext);
		tv.setText(((Category)getGroup(groupPosition)).getName());
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
		tv.setPadding(60, 0, 0, 0);
			
		/*tv.setLongClickable(true);
		tv.setClickable(true);
		
		tv.setOnCreateContextMenuListener((Activity)mContext);*/
		
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}