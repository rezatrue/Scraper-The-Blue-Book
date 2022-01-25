package com.rezatrue.Scraper_The_Blue_Book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;

import com.rezatrue.Scraper_The_Blue_Book.Objects.CompanyPage;
import com.rezatrue.Scraper_The_Blue_Book.Objects.CompanyPageJSoup;
import com.rezatrue.Scraper_The_Blue_Book.Objects.ListPage;
import com.rezatrue.Scraper_The_Blue_Book.Objects.ListPageJSoup;
import com.rezatrue.Scraper_The_Blue_Book.Objects.SearchPage;

public class TaskProcessor {

	private int categorySerialNumber; // range 1 - 18
	private int stateSerialNumber; // range 1 - 84
	private int subcategorySerialNumber; // range 
	private String folderName;
	
	public TaskProcessor(int categorySerialNumber, int stateSerialNumber, int subcategorySerialNumber) {
		this.categorySerialNumber = categorySerialNumber;
		this.stateSerialNumber = stateSerialNumber;
		this.subcategorySerialNumber = subcategorySerialNumber;
		this.folderName = categorySerialNumber+"-"+stateSerialNumber;
		
		File directory = new File(folderName);
		if (! directory.exists()) directory.mkdir();
				
	}
	
	private FireFoxHandaler fireFoxHandaler;
	
	public void start() {
		// remove "//" from line 60 of the class FireFoxHandaler to make this headless
        fireFoxHandaler = new FireFoxHandaler();
        //fireFoxHandaler.openBrowser();
  		//LoadUserAgent userAgent = new LoadUserAgent();
  		//fireFoxHandaler.openChromeBrowser(userAgent.getRandomUserAgent());
  		//fireFoxHandaler.openFirefoxBrowser(userAgent.getRandomUserAgent());
        fireFoxHandaler.openFirefoxBrowser();
        System.out.println("XY"+1);
  		openCategorySubList();
  		System.out.println("XY"+2);
  		traverseSubCatList();
  		System.out.println("XY"+3);
  		fireFoxHandaler.closeBrowser(); 
	}
	
    private LinkedList<Subcategory> subcategoriesListUrl;
    private void openCategorySubList(){
    	subcategoriesListUrl = new LinkedList<>();
    	SearchPage sp = new SearchPage(fireFoxHandaler.driver);
  		System.out.println("XY"+4);
    	fireFoxHandaler.loadUrl(sp.getBaseUrl());
    	if(fireFoxHandaler.isCaptchaPresent()) {
    		fireFoxHandaler.captchaBypassWith2capt();
    		if(fireFoxHandaler.isCaptchaPresent()) {
        		fireFoxHandaler.captchaBypassWith2capt();
    		}
		}
    	fireFoxHandaler.waitUntillCkickable(sp.categoryBy);
    	
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
    	
    	subcategoriesListUrl = sp.getSubCategorylist();
    	
    	// display urls
    	Iterator<Subcategory> it = subcategoriesListUrl.iterator();
    	while(it.hasNext()) {
    		System.out.println(" --- ");
    		System.out.println(it.next().getName());
    	}
    }
	
    private void traverseSubCatList() {
    	if(subcategoriesListUrl.size() == 0) {
    		System.out.println("Sub Category List size Zero");
    		return;
    	}
    	
    	if(subcategoriesListUrl.size() < subcategorySerialNumber) {
    		System.out.println("Sub Category Serial Number not exist");
    		return;
    	}
    	
    	if(subcategorySerialNumber > 1) {
    		for(int i = 1; i < subcategorySerialNumber; i++) {
        		subcategoriesListUrl.removeFirst();
    		}
    	}
    	
    	while(subcategoriesListUrl.size() > 0) {
    		Subcategory subcategory = subcategoriesListUrl.removeFirst();
    		System.out.println("Subcategory URL: " + subcategory.getUrl());
        	//getLocationsList(subcategory.getUrl());
        	getLocationsListScraperApi(subcategory.getUrl());
        	String fileName = subcategory.getName();
        	
        	//  spliting task temp
        	/*
        	for(int x = 0; x < subcategoryLocationsListUrl.size(); x++) {
        		data.add(new Info((x+1)+"", subcategoryLocationsListUrl.get(x), fileName, null, null, null, null, null));
        	}
        	String catCode = categorySerialNumber + "_"+ stateSerialNumber + "-("+ subcategoryLocationsListUrl.size() + ")-"+ fileName; 
    		CsvUtm8Generator csvGenerator = new CsvUtm8Generator(folderName, catCode);
    		csvGenerator.listtoCsv(data);
        	*/
    		
        	try {
				//getData();
				getDataJSoup();
				printData(fileName);
			} catch (Exception e) {
				System.out.println("Try getData(): " +e.getMessage());
			}
			        	
        	System.out.println(" Moving to Next sub Cat");
    	}
    }

// ............ getLocationsList(String url) use selenium, 2captcha & proxyMesh.........//
// ............ getLocationsListScraperApi(String url) use scraperapi.com.........//    
    private LinkedList<String> subcategoryLocationsListUrl;
    private void getLocationsList(String url) {
		subcategoryLocationsListUrl = new LinkedList<>();
		ListPage listPage = new ListPage(fireFoxHandaler.driver);
		
		String urlStartingWith;
		try {
			urlStartingWith = url.substring(url.indexOf("//")+2, url.indexOf("&search"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			return;
		}
		
		boolean loaded = fireFoxHandaler.loadUrl(url);
		if(loaded) {
			if(!fireFoxHandaler.driver.getCurrentUrl().contains(urlStartingWith)) {
				if(fireFoxHandaler.isCaptchaPresent()) {
					fireFoxHandaler.captchaBypassWith2capt();
					if(fireFoxHandaler.driver.getCurrentUrl().contains(urlStartingWith)) loaded = true;
					else loaded = true;
				}
			}else loaded = true;
		}
		if(!loaded) {
			loaded = fireFoxHandaler.loadUrl(url);
			if(!fireFoxHandaler.driver.getCurrentUrl().contains(urlStartingWith)) {
				if(fireFoxHandaler.isCaptchaPresent()) {
					fireFoxHandaler.captchaBypassWith2capt();
					if(fireFoxHandaler.driver.getCurrentUrl().contains(urlStartingWith)) loaded = true;
					else loaded = true;
				}
			}else loaded = true;
		}
		
		if(!loaded) {System.out.println("Locations List URL not loaded"); return; }
		if(listPage.getListSize() == 0) {System.out.println("0 Item in the list"); return; }
		
		try{
			listPage.getDispalyAllResultsWE().click();
		}catch(Exception e) {
			System.out.println("List is fully visible");
		}
		fireFoxHandaler.fullPageScroll();
		subcategoryLocationsListUrl.addAll(listPage.getLocationListUrl());
		
		try {
			while(listPage.isNextPagePresent()) {
				listPage.clickNextButton();
				if(fireFoxHandaler.isCaptchaPresent()) {
					fireFoxHandaler.captchaBypassWith2capt();
					if(fireFoxHandaler.isCaptchaPresent()) {
						fireFoxHandaler.captchaBypassWith2capt();
					}
				}
				fireFoxHandaler.fullPageScroll();
				subcategoryLocationsListUrl.addAll(listPage.getLocationListUrl());
			}
		}catch(Exception e) {
			System.out.println("Next Page Clicking Issue: "+ e.getMessage());
		}

    }

    private void getLocationsListScraperApi(String listPageurl) {
		subcategoryLocationsListUrl = new LinkedList<>();
		
		// check category link is proper
		String urlStartingWith;
		try {
			urlStartingWith = listPageurl.substring(listPageurl.indexOf("//")+2, listPageurl.indexOf("&search"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			return;
		}
		ScraperapiHandler scraperapiHandler = new ScraperapiHandler();
		String pageHtml = scraperapiHandler.loadPage(listPageurl, false);
		if(pageHtml.length() == 0) {
			pageHtml = scraperapiHandler.loadPage(listPageurl, false);
			if(pageHtml.length() == 0) {System.out.println("Locations List URL not loaded"); return; }
		}	
		
		System.out.println(pageHtml);
		ListPageJSoup listPageJSoup = new ListPageJSoup(pageHtml);
		waitOneMinute();
		
		
		if(listPageJSoup.getListSize() == 0) {System.out.println("0 Item in the list"); return; }
		
		subcategoryLocationsListUrl.addAll(listPageJSoup.getLocationListUrl());
		
		//fireFoxHandaler.fullPageScroll();
		String nextPage = listPageJSoup.nextPageUrl();
		try {
			while(nextPage.length() > 0){
				pageHtml = "";
				pageHtml = scraperapiHandler.loadPage(nextPage, false);
				if(pageHtml.length() == 0) {
					pageHtml = scraperapiHandler.loadPage(nextPage, false);
					if(pageHtml.length() == 0) break;
				}
				System.out.println(pageHtml);
				listPageJSoup = new ListPageJSoup(pageHtml);
				waitOneMinute();
				//fireFoxHandaler.fullPageScroll();
				subcategoryLocationsListUrl.addAll(listPageJSoup.getLocationListUrl());
				nextPage = listPageJSoup.nextPageUrl();
			} 
		}catch(Exception e) {
			System.out.println("Next Page Clicking Issue: "+ e.getMessage());
		}

    }    
    private void waitOneMinute() {
    	try { System.out.println("Wait for 1 second"); Thread.sleep(1000);
		} catch (InterruptedException e1) { e1.printStackTrace();}
    }
    
    //............  getData() use Selenium & getDataJSoup() use JSoup .......//
    private LinkedList<Info> data;
    private void getData() {
    	
    	if(subcategoryLocationsListUrl.size() == 0) {System.out.println("Locations List size 0"); return; }
    	
    	data = new LinkedList<>();
    	CompanyPage companyPage = new CompanyPage(fireFoxHandaler.driver);
    	int count = 1;
    	while(subcategoryLocationsListUrl.size() > 0) {
    		String profileUrl = subcategoryLocationsListUrl.removeFirst();
    		boolean isPageLoaded = fireFoxHandaler.loadUrl(profileUrl);
    		if(fireFoxHandaler.isCaptchaPresent()) {
    			fireFoxHandaler.captchaBypassWith2capt();
    			isPageLoaded = true;
    			//isPageLoaded = fireFoxHandaler.loadUrl(profileUrl);
			}
    		System.out.println("Data Page Loaded");
    		Info info = new Info();
    		info.setSerialNumber(""+count++);
    		info.setProfileUrl(profileUrl);
    		if(isPageLoaded) {
    		try{info.setCompanyName(companyPage.getName());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setAddress(companyPage.getAddress());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setWebsite(companyPage.getWebsite());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setPhone(companyPage.getPhone());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setContactPerson(companyPage.getContactPerson());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setCategory(companyPage.getCategory());}catch(Exception e) {System.out.println(e.getMessage());}
    		}
    		data.add(info);
    	}    	
    }

    private void getDataJSoup() {
    	
    	if(subcategoryLocationsListUrl.size() == 0) {System.out.println("Locations List size 0"); return; }
    	
    	data = new LinkedList<>();
    	CompanyPageJSoup companyPageJSoup;
    	ScraperapiHandler scraperapiHandler = new ScraperapiHandler();
    	int count = 1;
    	while(subcategoryLocationsListUrl.size() > 0) {
    		String profileUrl = subcategoryLocationsListUrl.removeFirst();
    		String pageHtml = scraperapiHandler.loadPage(profileUrl, false);
    		
    		System.out.println("Data Page Loaded");
    		Info info = new Info();
    		info.setSerialNumber(""+count++);
    		info.setProfileUrl(profileUrl);
    		    		
    		if(!pageHtml.equalsIgnoreCase("error")) {
    			companyPageJSoup = new CompanyPageJSoup(pageHtml);	
    			
    		try{info.setCompanyName(companyPageJSoup.getName());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setAddress(companyPageJSoup.getAddress());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setWebsite(companyPageJSoup.getWebsite());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setPhone(companyPageJSoup.getPhone());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setContactPerson(companyPageJSoup.getContactPerson());}catch(Exception e) {System.out.println(e.getMessage());}
    		try{info.setCategory(companyPageJSoup.getCategory());}catch(Exception e) {System.out.println(e.getMessage());}
    		}
    		data.add(info);
    	}    	
    }

    private void printData(String fileName) {
    	String catCode = categorySerialNumber + "_"+ stateSerialNumber + "-("+ data.size() + ")-"+ fileName; 
		CsvUtm8Generator csvGenerator = new CsvUtm8Generator(folderName, catCode);
		csvGenerator.listtoCsv(data);
		data.clear();
		
    }
	
}
