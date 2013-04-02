package com.liebekinder.mobiledegreasor.core;

import java.util.UUID;

import android.widget.TextView;

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
	
	public Category parent;
	/**
	 * It's own UUID
	 */
	private UUID uuid;
	
	/**
	 * State of the task
	 */
	private boolean checked = false;
	public TextView vue; 
	

	public Task(String name, Category p) {
		this.name = name;
		this.uuid = UUID.randomUUID();
		parent = p;
		vue = new TextView(p.getContext());
		vue.setText(name);
	}
	
	public void setName(String name) {
		this.name = name;
		vue.setText(name);
	}
	
	public TextView getVue() {
		return vue;
	}

	public void setVue(TextView vue) {
		this.vue = vue;
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
