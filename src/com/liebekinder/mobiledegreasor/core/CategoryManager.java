package com.liebekinder.mobiledegreasor.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.liebekinder.mobiledegreasor.Principale;

/**
 * Globalcategory manager
 * 
 * @author Ornicare
 *
 */
public class CategoryManager {
	/**
	 * List of all category
	 */
	private List<Category> categoriesList;
	private Principale principal;
	
	public CategoryManager(Principale p) {
		categoriesList = new ArrayList<Category>();
		principal = p;
	}
	
	/**
	 * Get the list of all category.
	 * 
	 * @return
	 */
	public List<Category> getCategoriesList() {
		return categoriesList;
	}
	
	public Context getContext(){
		return principal;
	}
	/**
	 * Add a new category. If it's name is already present, do nothing and return false.
	 * 
	 * @param newCat
	 * @return
	 */
	public boolean addCategory(Category newCat) {
		for(Category cat : categoriesList) {
			if(cat.getName().equals(newCat.getName())) return false;
		}
		categoriesList.add(newCat);
		return true;
	}
	
	/**
	 * Try to delete the first occur of a category and return true in case of success. If nothing is found, return false.
	 * @param catName
	 * @return
	 */
	public boolean deleteCategory(String catName) {
		for(Category cat : categoriesList) {
			if(cat.getName().equals(catName)) {
				categoriesList.remove(cat);
				return true;
			}
		}
		return false;
	}
	
	public String serialize() {
		String output = "";
		for(Category cat : categoriesList) {
			output+="\n\n\n"+cat.serialize();
		}
		return output.trim();
	}
	
	public void deserialize(String s) {
		for(String catS : s.split("\n\n\n")) {
			Category cat = new Category("",this);
			cat.deserialize(catS);
			addCategory(cat);
		}
	}
}
