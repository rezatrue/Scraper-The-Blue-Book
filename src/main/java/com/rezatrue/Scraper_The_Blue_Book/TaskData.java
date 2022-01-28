package com.rezatrue.Scraper_The_Blue_Book;

import java.util.LinkedList;

public class TaskData {

	int categorySerialNumber;
	int stateSerialNumber;
	LinkedList<Subcategory> subcategoriesListUrl;
	
	public TaskData() {
		subcategoriesListUrl = new LinkedList<>();
	}

	public int getCategorySerialNumber() {
		return categorySerialNumber;
	}

	public void setCategorySerialNumber(int categorySerialNumber) {
		this.categorySerialNumber = categorySerialNumber;
	}

	public int getStateSerialNumber() {
		return stateSerialNumber;
	}

	public void setStateSerialNumber(int stateSerialNumber) {
		this.stateSerialNumber = stateSerialNumber;
	}

	public LinkedList<Subcategory> getSubcategoriesListUrl() {
		return subcategoriesListUrl;
	}

	public void setSubcategoriesListUrl(LinkedList<Subcategory> subcategoriesListUrl) {
		this.subcategoriesListUrl = subcategoriesListUrl;
	}
		
}
