package com.rezatrue.Scraper_The_Blue_Book;

public class Subcategory {
	String url;
	String name;
	
	public Subcategory(){
		super();
	}

	public Subcategory(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
