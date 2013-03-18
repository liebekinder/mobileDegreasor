package com.liebekinder.mobiledegreasor.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A category. Must be unique by it's name.
 * Contain tasks
 * 
 * @author Ornicare
 *
 */
public class Category {

	/**
	 * Category's name
	 */
	private String name;
	
	/**
	 * Category state
	 */
	private boolean unwrapped = false;

	public String getName() {
		return name;
	}
	
	/**
	 * List of all tasks
	 */
	private List<Task> tasksList = new ArrayList<Task>();
	
	public Category(String name) {
		this.name = name;
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
	
	
}
