package com.liebekinder.mobiledegreasor;

import java.util.ArrayList;

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

		LinearLayout linear = new LinearLayout(mContext);
		linear.setOrientation(LinearLayout.HORIZONTAL);
		
		TextView tv = new TextView(mContext);
		tv.setText(((Task)getChild(groupPosition, childPosition)).getName());
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
		
		CheckBox cb = new CheckBox(mContext);
		cb.setChecked(((Task)getChild(groupPosition, childPosition)).isChecked());
		

		linear.addView(cb);
		linear.addView(tv);
		
		linear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(mContext, "Child !", Toast.LENGTH_LONG).show();
			}
		});
		
		
		cb.setChecked(list.get(groupPosition).getTasksList().get(childPosition).isChecked());
		cb.setOnCheckedChangeListener(new MyOnCheckedChangeListener(mContext, list.get(groupPosition).getTasksList().get(childPosition)));
		
		return linear;
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
		tv.setText(((Category)getGroup(groupPosition)).getName()+(((Category)getGroup(groupPosition)).isUnwrapped()?"-":"+"));
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
			
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