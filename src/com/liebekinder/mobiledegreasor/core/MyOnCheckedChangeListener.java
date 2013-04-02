package com.liebekinder.mobiledegreasor.core;

import com.liebekinder.mobiledegreasor.Principale;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MyOnCheckedChangeListener implements OnCheckedChangeListener{

	private Task task;
	private Context mContext;

	public MyOnCheckedChangeListener(Context mContext, Task task) {
		this.task = task;
		this.mContext = mContext;
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		task.setChecked(isChecked);
		((Principale) mContext).saveState();
	}
}
