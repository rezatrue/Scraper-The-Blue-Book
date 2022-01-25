package com.rezatrue.Scraper_The_Blue_Book.Objects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.rezatrue.Scraper_The_Blue_Book.Subcategory;

public class SearchPage {
	
	private String baseUrl = "https://www.thebluebook.com/products/bluesearchtechnology/search-companies.html";
	private WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public By categoryBy = By.xpath("//div[@class='categories']/ul/li/a");
	private By stateBy = By.xpath("//select[@name='regionalselector']");
	//public By subCategoryBy = By.xpath("//div[@class='subcategories']/div[1]//ul/li/a");
	public By subCategoryBy = By.xpath("//div[@class='subcategories']/div[not(contains(@style,'display:none'))]//ul/li/a");
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
//	public List<WebElement> getCategoyWEs() {
//		return driver.findElements(categoryBy);
//	}
	
//	public String getCategoyPageUrl(int slno) {
//		String url = driver.findElements(categoryBy).get(slno).getAttribute("href");
//		System.out.println("Cat page URL: "+ url);
//		return url;
//	}
	
	public String openCategoyPageUrl(int slno) {
		String pageUrl = driver.findElements(categoryBy).get(slno).getAttribute("href");
		driver.findElements(categoryBy).get(slno).sendKeys(Keys.ENTER);
		return pageUrl;
		
	}
	
	public WebElement getStateOptionsWE() {
		return driver.findElement(stateBy);
	}

	public LinkedList<Subcategory> getSubCategorylist() {
		LinkedList<Subcategory> list = new LinkedList<>();
		List<WebElement> lst = driver.findElements(subCategoryBy);
		
		Iterator<WebElement> it = lst.iterator();
		
		while(it.hasNext()) {
			WebElement e = it.next();
			Subcategory subcategory = new Subcategory();
			subcategory.setName(e.getText());
			subcategory.setUrl(e.getAttribute("href"));
			System.out.println(e.getText());
			System.out.println(e.getAttribute("href"));
			list.add(subcategory);
		}
		
//		driver.findElements(subCategoryBy)
//		.forEach(i -> {
//			list.add(new Subcategory(i.getText(), i.getAttribute("href")));
//		});
		
		
		return list; 
	}
}
