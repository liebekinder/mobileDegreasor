package com.liebekinder.mobiledegreasor.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liebekinder.mobiledegreasor.Principale;

/**
 * Globalcategory manager
 * 
 * @author Ornicare
 *
 */
public class CategoryManager implements ExpandableListAdapter{
	/**
	 * List of all category
	 */
	private List<Category> categoriesList;
	private Principale principal;
	
	public CategoryManager(Principale p) {
		categoriesList = new ArrayList<Category>();
		principal = p;
	}
	
	/**
	 * Get the list of all category.
	 * 
	 * @return
	 */
	public List<Category> getCategoriesList() {
		return categoriesList;
	}
	
	public Context getContext(){
		return principal;
	}
	/**
	 * Add a new category. If it's name is already present, do nothing and return false.
	 * 
	 * @param newCat
	 * @return
	 */
	public boolean addCategory(Category newCat) {
		for(Category cat : categoriesList) {
			if(cat.getName().equals(newCat.getName())) return false;
		}
		categoriesList.add(newCat);
		return true;
	}
	
	/**
	 * Try to delete the first occur of a category and return true in case of success. If nothing is found, return false.
	 * @param catName
	 * @return
	 */
	public boolean deleteCategory(String catName) {
		for(Category cat : categoriesList) {
			if(cat.getName().equals(catName)) {
				categoriesList.remove(cat);
				return true;
			}
		}
		return false;
	}
	
	public String serialize() {
		String output = "";
		for(Category cat : categoriesList) {
			output+="\n\n\n"+cat.serialize();
		}
		return output.trim();
	}
	
	public void deserialize(String s) {
		for(String catS : s.split("\n\n\n")) {
			Category cat = new Category("",this);
			cat.deserialize(catS);
			addCategory(cat);
		}
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

		LinearLayout linear = new LinearLayout(getContext());
		linear.setOrientation(LinearLayout.HORIZONTAL);
		
			TextView tv = new TextView(getContext());
			tv.setText(((Task)getChild(groupPosition, childPosition)).getName());
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			
			CheckBox cb = new CheckBox(getContext());
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
		
			TextView tv = new TextView(getContext());
			tv.setText(((Category)getGroup(groupPosition)).getName());
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
		
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
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
		Log.i("","luol");
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub
		Log.i("","loul");
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
