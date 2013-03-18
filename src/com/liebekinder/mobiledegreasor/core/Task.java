package com.liebekinder.mobiledegreasor.core;

import java.util.UUID;

/**
 * A task.
 * Just a string with an UUID.
 * 
 * @author Ornicare
 *
 */
public class Task {
	
	/**
	 * Task name
	 */
	private String name;
	
	/**
	 * It's own UUID
	 */
	private UUID uuid;
	
	/**
	 * State of the task
	 */
	private boolean checked = false;

	public Task(String name) {
		this.name = name;
		this.uuid = UUID.randomUUID();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
}
