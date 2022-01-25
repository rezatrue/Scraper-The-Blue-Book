package com.rezatrue.Scraper_The_Blue_Book.Objects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListPageJSoup {
	
	private String baseUrl = "https://www.thebluebook.com";
	private Document doc;
	
	public ListPageJSoup(String html) {
		
		//doc = Jsoup.parse(input, "UTF-8", "https://www.thebluebook.com/");
		doc = Jsoup.parse(html);
		//this.doc = doc;
	}
	
	private String listSizeSelectorRaw = "h1#resulttitle";
	private String listSizeSelector = "h1#resulttitle > span > strong > em";
	private String locationsSelector = "div.media-body > div.addy_wrapper > a.hide-mobile";
	private String pagesAnchorsSelector = "div.pager-outer-wrapper > div > a";
	//private String dispalyAllResultsSelector = "h2.addition-results-link";
	
	public int getListSize() {
		int size = 0;
		
		try {
			String title = doc.select(listSizeSelector).text().trim();
			String subText;
			if(title.length() == 0)
				subText = doc.select(listSizeSelectorRaw).text().trim();
			else
				subText = title.substring(title.lastIndexOf("-")+1, title.length()).trim();
			size = Integer.parseInt(subText.substring(0, subText.indexOf("Companies")).trim());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return size;
	}
	
	
	public LinkedList<String> getLocationListUrl() {
		System.out.println("getLocationListUrl Extracting");
		LinkedList<String> list = new LinkedList<>();		
		Elements elements = doc.select(locationsSelector);
		Iterator<Element> it = elements.iterator();
		
		while(it.hasNext()) {
			System.out.println("One Location");
			list.add(baseUrl + it.next().attr("href"));
		}
		return list;
	}
	
	public String nextPageUrl() {
		System.out.println("nextPageUrl Extracting");
		String nextUrl = "";
		Elements elements = doc.select(pagesAnchorsSelector);
		Iterator<Element> it = elements.iterator();
		
		while(it.hasNext()) {
			Element element = it.next();
			if(element.text().contains("Next")) {
				nextUrl = baseUrl + "/"+ element.attr("href");
				System.out.println(nextUrl);
			}
		}
		return nextUrl;		
	}
	

}
