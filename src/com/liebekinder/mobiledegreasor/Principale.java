package com.liebekinder.mobiledegreasor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liebekinder.mobiledegreasor.core.CategoryManager;

public class Principale extends Activity {

	/**
	 * The global category manager. Restore it.
	 */
	private CategoryManager categoryManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principale);

		// //////////////////////////////////////////////////////////////////////////////////
		// ////////////////////////////Hard coded example////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		/*CategoryManager categoryManager = new CategoryManager();

		Category voiture = new Category("Voiture");
		voiture.addTask(new Task("Faire vidange"));
		voiture.addTask(new Task("Ecraser mémé"));
		voiture.addTask(new Task("Aller à l'enterrement de mémé"));

		Category chasse = new Category("Chasse");
		voiture.addTask(new Task("Chasser gibier"));
		voiture.addTask(new Task("Vider gibier"));
		voiture.addTask(new Task("Manger enfant"));

		categoryManager.addCategory(voiture);
		categoryManager.addCategory(chasse);*/

		// //////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////
		
		// TEST//
		
		Log.i("test", "test2");


		
		LinearLayout linear = (LinearLayout) findViewById(R.id.layout_principal);
		
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
		}
		// TEST//

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principale, menu);

		return true;
	}

}
