package com.liebekinder.mobiledegreasor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liebekinder.mobiledegreasor.core.Category;
import com.liebekinder.mobiledegreasor.core.CategoryManager;
import com.liebekinder.mobiledegreasor.core.Task;


public class Principale extends Activity {

	/**
	 * The global category manager. Restore it.
	 */
	private CategoryManager categoryManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		// //////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////////Hard coded example////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		categoryManager = new CategoryManager(this);
		
		Category voiture = new Category("Voiture",categoryManager);
		voiture.addTask(new Task("Faire vidange",voiture));
		voiture.addTask(new Task("Ecraser mémé",voiture));
		voiture.addTask(new Task("Aller à l'enterrement de mémé",voiture));

		Category chasse = new Category("Chasse",categoryManager);
		chasse.addTask(new Task("Chasser gibier",chasse));
		chasse.addTask(new Task("Vider gibier",chasse));
		chasse.addTask(new Task("Manger enfant",chasse));

		categoryManager.addCategory(voiture);
		categoryManager.addCategory(chasse);

		// //////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		
		// TEST//
		/*
		Log.i("test", "test2");


		
		LinearLayout linear = (LinearLayout) findViewById(R.id.layoutPrincipal);
		
		for (int i = 1; i <= 20; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			Button btn = new Button(this);
			btn.setId(i);
			final int id_ = btn.getId();
			btn.setText("button " + id_);
			//btn.setBackgroundColor(Color.rgb(70, 80, 90));
			linear.addView(btn, params);
			Button btn1 = ((Button) findViewById(id_));
			btn1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					Toast.makeText(view.getContext(),
							"Button clicked index = " + id_, Toast.LENGTH_SHORT)
							.show();
				}
			});
		}*/
		// TEST//

		setContentView(affiche());
		
	}
	
	public LinearLayout affiche(){
		LinearLayout global = new LinearLayout(this);
		
		for(Category cat: categoryManager.getCategoriesList()){
			ListView lv = new ListView(this);
			TextView tv = new TextView(this);
			
			Log.i("categorie: ",cat.getName());
			
			tv.setText(cat.getName());
			lv.addHeaderView(tv);
			lv.setAdapter(cat);
			global.addView(lv);
		}
		return global;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principale, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		CharSequence lal = item.getTitle();
		Log.i("her","clic method action: " + lal);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage("loul")
		       .setTitle("12314")
		       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.v("here", "message à la con");
				}
			})
		       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.v("here", "message à la con");
					
				}
			});

		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		dialog.show();
		
		return false;
	}
	

}
