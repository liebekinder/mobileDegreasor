package com.liebekinder.mobiledegreasor.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

/**
 * A category. Must be unique by it's name.
 * Contain tasks
 * 
 * @author Ornicare
 *
 */
public class Category{

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
//		if(s != null){
//			Log.v("string s", s);
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
//	}
	
}
