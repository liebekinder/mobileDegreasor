package com.liebekinder.mobiledegreasor.core;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Child extends View implements OnCreateContextMenuListener{

	private Task task;
	private Context mContext;

	public Child(Context mContext, Task task) {
		super(mContext);
		this.mContext = mContext;
		this.task = task;
	}

	public View create() {
		LinearLayout linear = new LinearLayout(mContext);
		linear.setOrientation(LinearLayout.HORIZONTAL);
		
		TextView tv = new TextView(mContext);
		tv.setText(task.getName());
		tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
		
		final CheckBox cb = new CheckBox(mContext);
		cb.setChecked(task.isChecked());
		

		linear.addView(cb);
		linear.addView(tv);
		
		linear.setLongClickable(true);
		//linear.setClickable(true);
		
		//linear.setOnCreateContextMenuListener(this);
		
		linear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				cb.setChecked(!cb.isChecked());
				Toast.makeText(mContext, "Child !", Toast.LENGTH_LONG).show();
			}
		});
		
		//((Activity)mContext).registerForContextMenu(linear);
		
		cb.setChecked(task.isChecked());
		cb.setOnCheckedChangeListener(new MyOnCheckedChangeListener(mContext, task));
		
		return linear;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	    menu.setHeaderTitle("osef");
	    //menu.add(Menu.NONE, 0, 0, "Enfant");
	    
	    AdapterView.AdapterContextMenuInfo info ;
	    try {
	        info = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    } catch (ClassCastException e) {
	        Log.e("error", "bad menuInfo", e);
	        return;
	    }
	    
	    String selectedTask = ((Child) info.targetView).getTask().getName();
	    menu.add(Menu.NONE, 0, 0, "qsfdghg");

	 }

	public Task getTask() {
		return task;
	}

}
