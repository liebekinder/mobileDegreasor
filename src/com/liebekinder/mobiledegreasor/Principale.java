package com.liebekinder.mobiledegreasor;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.liebekinder.mobiledegreasor.core.Category;
import com.liebekinder.mobiledegreasor.core.CategoryManager;
import com.liebekinder.mobiledegreasor.core.Child;
import com.liebekinder.mobiledegreasor.core.Task;

public class Principale extends Activity {

	/**
	 * The global category manager. Restore it.
	 */
	private CategoryManager categoryManager;
	private ListAdapter listadapter;
	private SharedPreferences shared = null;
	private Principale context;
	private MyExpandableListAdapter adapter;
	private ExpandableListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shared = PreferenceManager.getDefaultSharedPreferences(this);

		this.context = this;

		// //////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////////Hard coded
		// example////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		CategoryManager categoryManager2 = new CategoryManager(this);

		Category voiture = new Category("Voiture", categoryManager2);

		voiture.addTask(new Task("Faire vidange", voiture));
		voiture.addTask(new Task("Ecraser mémé", voiture));
		voiture.addTask(new Task("Aller à l'enterrement de mémé", voiture));

		Category chasse = new Category("Chasse", categoryManager2);
		chasse.addTask(new Task("Chasser gibier", chasse));
		chasse.addTask(new Task("Vider gibier", chasse));
		chasse.addTask(new Task("Manger enfant", chasse));
		Category chasse2 = new Category("Chasse2", categoryManager2);
		chasse2.addTask(new Task("Chassers gibier", chasse2));
		chasse2.addTask(new Task("Viders gibier", chasse2));
		chasse2.addTask(new Task("Mangers enfant", chasse2));

		categoryManager2.addCategory(voiture);
		categoryManager2.addCategory(chasse);
		categoryManager2.addCategory(chasse2);

		// TODO si bug : incompatibilité des données.
		// categoryManager=categoryManager2;
		// saveState();

		restoreState();

		// TEST : si pas demodèle, en mettre un //
		if (categoryManager.getCategoriesList().isEmpty())
			categoryManager = categoryManager2;
		Log.i("test2", categoryManager.getCategoriesList().get(0).getName());
		// FIN TEST//

		list = new ExpandableListView(this);
		// list.setGroupIndicator(null);
		list.setChildIndicator(null);
		adapter = new MyExpandableListAdapter(this,
				(ArrayList<Category>) categoryManager.getCategoriesList());
		list.setAdapter(adapter);

		list.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int groupPosition, long arg3) {
				Toast.makeText(
						context,
						"Clik on : "
								+ categoryManager.getCategoriesList()
										.get(groupPosition).getName(),
						Toast.LENGTH_LONG).show();
				return false;
			}

		});

		list.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				categoryManager.getCategoriesList().get(groupPosition)
						.setUnwrapped(false);
				saveState();
			}
		});

		list.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				categoryManager.getCategoriesList().get(groupPosition)
						.setUnwrapped(true);
				saveState();
			}
		});

		/*
		 * list.setOnItemLongClickListener(new OnItemLongClickListener() {
		 * 
		 * @Override public boolean onItemLongClick(AdapterView<?> adapterView,
		 * View view, int position, long id) { Toast.makeText(context,
		 * "Long clic", Toast.LENGTH_LONG).show(); return true; }
		 * 
		 * });
		 */

		for (int i = 0; i < categoryManager.getCategoriesList().size(); i++) {
			if (categoryManager.getCategoriesList().get(i).isUnwrapped())
				list.expandGroup(i);
		}

		registerForContextMenu(list);

		setContentView(list);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

		menu.setHeaderTitle("osef");
		int type = ExpandableListView
				.getPackedPositionType(info.packedPosition);

		int group = ExpandableListView
				.getPackedPositionGroup(info.packedPosition);

		int child = ExpandableListView
				.getPackedPositionChild(info.packedPosition);

		// Only create a context menu for child items
		if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
			// Array created earlier when we built the expandable list
			String page = categoryManager.getCategoriesList().get(group)
					.getTasksList().get(child).getName();

			menu.setHeaderTitle(page);
		}
		else if(type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
			// Array created earlier when we built the expandable list
			String page = categoryManager.getCategoriesList().get(group)
					.getName();

			menu.setHeaderTitle(page);
		}

		menu.add(Menu.NONE, 0, 0, "Test");

		/*
		 * try { info = (AdapterView.AdapterContextMenuInfo) menuInfo; } catch
		 * (ClassCastException e) { Log.e("error", "bad menuInfo", e); return; }
		 */

		// String selectedTask = ((Child) info.targetView).getTask().getName();
		// menu.add(Menu.NONE, 1, 1, selectedTask);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Log.i("sdds", item.toString());
		return true;
	}

	public void saveState() {
		SharedPreferences.Editor editor = null;
		editor = shared.edit();
		try {
			editor.putString("main", categoryManager.serialize());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		editor.commit();
	}

	public void restoreState() {
		String s = null;
		try {
			s = shared.getString("main", null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		categoryManager = new CategoryManager(this);
		if (s != null)
			categoryManager.deserialize(s);
	}

	public LinearLayout affiche() {
		LinearLayout global = new LinearLayout(this);

		ExpandableListView lv = new ExpandableListView(this);
		lv.setAdapter(listadapter);
		global.addView(lv);

		return global;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principale, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		CharSequence lal = item.getTitle();
		Log.i("her", "clic method action: " + lal);

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Create a new category");
		alert.setMessage("Enter your category name : ");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				String name = value.toString().replace("\n", "").trim();
				String message;
				if (name.equals("")) {
					message = "Category name cannot be null !";
				} else {
					message = "Category \"" + name
							+ "\" successfully created !";
					if (!categoryManager.addCategory(new Category(name,
							categoryManager)))
						message = "Category \"" + name + "\" already exists !";
					else {
						saveState();
						adapter.notifyDataSetChanged();
					}
				}
				Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();

		return false;
	}

}
