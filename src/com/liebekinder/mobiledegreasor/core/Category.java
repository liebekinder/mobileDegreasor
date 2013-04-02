package com.liebekinder.mobiledegreasor.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * A category. Must be unique by it's name.
 * Contain tasks
 * 
 * @author Ornicare
 *
 */
public class Category implements ListAdapter{

	/**
	 * Category's name
	 */
	private String name;
	
	/**
	 * Category state
	 */
	private boolean unwrapped = false;
	
	private CategoryManager parent;
	
	/**
	 * List of all tasks
	 */
	private List<Task> tasksList = new ArrayList<Task>();

	public String getName() {
		return name;
	}
	
	public Category(String name, CategoryManager p) {
		this.name = name;
		parent = p;
	}
	
	/**
	 * Get the list of all tasks.
	 * 
	 * @return
	 */
	public List<Task> getTasksList() {
		return tasksList;
	}
	
	/**
	 * Add a new task.
	 * 
	 * @param newCat
	 * @return
	 */
	public void addTask(Task task) {
		tasksList.add(task);
	}
	
	/**
	 * Delete the first occur of a task. (A priori unique).
	 * @param catName
	 * @return True in case of success
	 */
	public boolean deleteTask(UUID taskUUID) {
		for(Task task : tasksList) {
			if(task.getUuid().equals(taskUUID)) {
				tasksList.remove(task);
				return true;
			}
		}
		return false;
	}

	public boolean isUnwrapped() {
		return unwrapped;
	}

	public void setUnwrapped(boolean unwrapped) {
		this.unwrapped = unwrapped;
	}

	@Override
	public int getCount() {
		return tasksList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return tasksList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LinearLayout linear = new LinearLayout(getContext());
		linear.setOrientation(LinearLayout.HORIZONTAL);
		
			TextView tv = new TextView(getContext());
			tv.setText(((Task)getItem(arg0)).getName());
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
			
			CheckBox cb = new CheckBox(getContext());
			cb.setChecked(((Task)getItem(arg0)).isChecked());
			

			linear.addView(cb);
			linear.addView(tv);
		
		return linear;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return tasksList.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int arg0) {
		return true;
	}

	public Context getContext() {
		return parent.getContext();
	}
	
	public String serialize() {
		String output = name+"\n"+unwrapped+"\n\n";
		for(Task task : tasksList) {
			output+=task.serialize();
		}
		return output.trim();
	}
	
	public void deserialize(String s) {
		String[] catS = s.split("\n\n");
		String[] catSSplit = catS[0].split("\n");
		name = catSSplit[0];
		unwrapped = Boolean.valueOf(catSSplit[1]);
		for(int i =1; i< catS.length;i++){
			Task task = new Task("",this);
			task.deserialize(catS[i]);
			addTask(task);
		}
	}
	
}
