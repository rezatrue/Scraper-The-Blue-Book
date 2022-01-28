package com.rezatrue.Scraper_The_Blue_Book;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;

import com.rezatrue.Scraper_The_Blue_Book.Objects.SearchPage;

public class CategoryItemList {

	private int stateSerialNumber;
	private FireFoxHandaler fireFoxHandaler;
	private LinkedList<TaskData> list; 
	
	public CategoryItemList(int stateSerialNumber) {
		fireFoxHandaler = new FireFoxHandaler();
		this.stateSerialNumber = stateSerialNumber;
		list = new LinkedList<>();
	}

	//private LinkedList<Subcategory> subcategoriesListUrl;
	private void openCategorySubList(int categorySerialNumber){
    	//subcategoriesListUrl = new LinkedList<>();
		TaskData taskData = new TaskData();
		taskData.setCategorySerialNumber(categorySerialNumber);
		taskData.setStateSerialNumber(stateSerialNumber);
		
    	SearchPage sp = new SearchPage(fireFoxHandaler.driver);
  		System.out.println("XY"+4);
  		String currentUrl = fireFoxHandaler.driver.getCurrentUrl();
  		if(!currentUrl.startsWith(sp.getBaseUrl())) {
  	    	fireFoxHandaler.loadUrl(sp.getBaseUrl());
  	  		if(fireFoxHandaler.isCaptchaPresent()) {
	    		fireFoxHandaler.captchaBypassWith2capt();
	    		if(fireFoxHandaler.isCaptchaPresent()) {
	        		fireFoxHandaler.captchaBypassWith2capt();
	    		}
			}
  	  		fireFoxHandaler.waitUntillCkickable(sp.categoryBy);
  		}
  	
  		System.out.println("XY"+5);

    	//select state range (1 - 48)
    	Select select = new Select(sp.getStateOptionsWE());
    	select.selectByIndex(stateSerialNumber - 1);
    	
    	// open category page
    	//WebElement catWebE = sp.getCategoyWEs().get(categorySerialNumber -1);
    	sp.openCategoyPageUrl(categorySerialNumber -1);
    	if(fireFoxHandaler.isCaptchaPresent()) {
    		fireFoxHandaler.captchaBypassWith2capt();
    		//openCategorySubList();
    	}
    	fireFoxHandaler.waitUntillCkickable(sp.subCategoryBy);
    	
    	//subcategoriesListUrl = sp.getSubCategorylist();
    	taskData.setSubcategoriesListUrl(sp.getSubCategorylist());
    	// display url counts
    	System.out.println("............ " + sp.getSubCategorylist().size() + " .................");
    	list.add(taskData);
    	
    }

	
	public void collectList() {
		
		fireFoxHandaler.openFirefoxBrowser();
		
		for(int i = 1; i <= 18; i++) {
			openCategorySubList(i);
		}
		
		fireFoxHandaler.closeBrowser();
	}
	
	public void start() {
		
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i =0; i < list.size(); i++) {
			executor.submit(new TaskProcessor(list.remove()));
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(5, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
