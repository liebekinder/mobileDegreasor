package com.liebekinder.mobiledegreasor;

import com.liebekinder.mobiledegreasor.core.Category;
import com.liebekinder.mobiledegreasor.core.CategoryManager;
import com.liebekinder.mobiledegreasor.core.Task;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Principale extends Activity {

	/**
	 * The global category manager. Restore it.
	 */
	private CategoryManager categoryManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principale);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principale, menu);
		

		////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////Hard coded example////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		categoryManager = new CategoryManager();
		
		Category voiture = new Category("Voiture");
		voiture.addTask(new Task("Faire vidange"));
		voiture.addTask(new Task("Ecraser mémé"));
		voiture.addTask(new Task("Aller à l'enterrement de mémé"));
		
		Category chasse = new Category("Chasse");
		voiture.addTask(new Task("Chasser gibier"));
		voiture.addTask(new Task("Vider gibier"));
		voiture.addTask(new Task("Manger enfant"));
		
		categoryManager.addCategory(voiture);
		categoryManager.addCategory(chasse);
	
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		return true;
	}

}
