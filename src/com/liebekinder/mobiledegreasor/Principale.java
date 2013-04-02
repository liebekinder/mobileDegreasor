package com.liebekinder.mobiledegreasor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liebekinder.mobiledegreasor.core.Category;
import com.liebekinder.mobiledegreasor.core.CategoryManager;
import com.liebekinder.mobiledegreasor.core.ListAdapter;
import com.liebekinder.mobiledegreasor.core.Task;

public class Principale extends Activity {

	/**
	 * The global category manager. Restore it.
	 */
	private CategoryManager categoryManager;
	private ListAdapter listadapter;
	private SharedPreferences shared = null;
	private Principale context;

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
		Category chasse2 = new Category("Chasse2",categoryManager2);
		chasse2.addTask(new Task("Chassers gibier",chasse2));
		chasse2.addTask(new Task("Viders gibier",chasse2));
		chasse2.addTask(new Task("Mangers enfant",chasse2));

		categoryManager2.addCategory(voiture);
		categoryManager2.addCategory(chasse);
		categoryManager2.addCategory(chasse2);


		restoreState();

		// TEST : si pas demodèle, en mettre un //
		if (categoryManager.getCategoriesList().isEmpty())
			categoryManager = categoryManager2;
		Log.i("test2", categoryManager.getCategoriesList().get(0).getName());
		// FIN TEST//

		listadapter = new ListAdapter(categoryManager.getCategoriesList(), this);
		setContentView(affiche());
		

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
		/*
		 * AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 * 
		 * // 2. Chain together various setter methods to set the dialog
		 * characteristics builder.setMessage("loul") .setTitle("12314")
		 * .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub Log.v("here", "message à la con"); }
		 * }) .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		 * {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub Log.v("here", "message à la con");
		 * 
		 * } });
		 * 
		 * // 3. Get the AlertDialog from create() AlertDialog dialog =
		 * builder.create(); dialog.show();
		 */

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
				if(name.equals("")) {
					message = "Category name cannot be null !";
				}
				else {
					message = "Category \""+name+"\" successfully created !";
					if(!categoryManager.addCategory(new Category(name, categoryManager))) message = "Category \""+name+"\" already exists !";
					else{						
						saveState();
						((BaseExpandableListAdapter) listadapter).notifyDataSetChanged();
						affiche();						
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
