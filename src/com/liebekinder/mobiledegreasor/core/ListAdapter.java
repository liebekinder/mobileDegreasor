package com.liebekinder.mobiledegreasor.core;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAdapter extends BaseExpandableListAdapter {

	private List<Category> categoriesList;
	private Context context;
	
	public ListAdapter(List<Category> l, Context c){
		categoriesList = l;
		context = c;
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return categoriesList.get(groupPosition).getTasksList().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		LinearLayout linear = new LinearLayout(context);
		linear.setOrientation(LinearLayout.HORIZONTAL);
		
			TextView tv = new TextView(context);
			tv.setText(((Task)getChild(groupPosition, childPosition)).getName());
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			
			CheckBox cb = new CheckBox(context);
			cb.setChecked(((Task)getChild(groupPosition, childPosition)).isChecked());
			

			linear.addView(cb);
			linear.addView(tv);
		
		return linear;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return categoriesList.get(groupPosition).getTasksList().size();
	}

	@Override
	public long getCombinedChildId(long groupId, long childId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCombinedGroupId(long groupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return categoriesList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return categoriesList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
			TextView tv = new TextView(context);
			tv.setText(((Category)getGroup(groupPosition)).getName());
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
		
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return categoriesList.isEmpty();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		// TODO Auto-generated method stub		
		categoriesList.get(groupPosition).setUnwrapped(false);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub
		categoriesList.get(groupPosition).setUnwrapped(true);
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
	}

}
