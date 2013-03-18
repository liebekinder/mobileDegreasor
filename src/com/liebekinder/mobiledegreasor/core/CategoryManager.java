package com.liebekinder.mobiledegreasor.core;

import java.util.ArrayList;
import java.util.List;

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
	private List<Category> categoriesList = new ArrayList<Category>();
	
	public CategoryManager() {
		
	}
	
	/**
	 * Get the list of all category.
	 * 
	 * @return
	 */
	public List<Category> getCategoriesList() {
		return categoriesList;
	}
	
	/**
	 * Add a new category. If it's name is already present, do nothing and return false.
	 * 
	 * @param newCat
	 * @return
	 */
	public boolean addCategory(Category newCat) {
		for(Category cat : categoriesList) {
			if(cat.getName().equals(cat.getName())) return false;
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
}
