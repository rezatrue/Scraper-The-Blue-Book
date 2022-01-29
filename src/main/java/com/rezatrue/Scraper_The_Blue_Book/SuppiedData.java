package com.rezatrue.Scraper_The_Blue_Book;

import java.util.LinkedList;

public class SuppiedData {

	int catNumber;
	LinkedList<Subcategory> list;
	
	public SuppiedData() {
		list = new LinkedList<>();
	}

	public SuppiedData(int catNumber, LinkedList<Subcategory> list) {
		this.catNumber = catNumber;
		this.list = list;
	}

	public int getCatNumber() {
		return catNumber;
	}

	public void setCatNumber(int catNumber) {
		this.catNumber = catNumber;
	}

	public LinkedList<Subcategory> getList() {
		return list;
	}

	public void setList(LinkedList<Subcategory> list) {
		this.list = list;
	}
	
	public void addToList(String name) {
		Subcategory subcategory = new Subcategory();
		subcategory.setName(name);
		list.add(subcategory);
	}
	
}
