package com.rezatrue.Scraper_The_Blue_Book.Objects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListPage {
	
	private WebDriver driver;
	
	public ListPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By listSizeBy = By.xpath("//h1[@id='resulttitle']");
	private By locationsBy = By.xpath("//a[contains(.,'Locations')]");
	private By nextPageBy = By.xpath("//div[@class='pager-outer-wrapper']/div/a[contains(.,'Next')]");
	private By dispalyAllResultsBy = By.xpath("//h2[@class='addition-results-link']");
	
	public int getListSize() {
		int size = 0;
		
		try {
			String title = driver.findElement(listSizeBy).getText();
			String subText = title.substring(title.lastIndexOf("-")+1, title.length()).trim();
			size = Integer.parseInt(subText.substring(0, subText.indexOf("Companies")).trim());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return size;
	}
	
	
//	public List<WebElement> getLocationWEs() {
//		return driver.findElements(locationsBy);
//	}
	
	public LinkedList<String> getLocationListUrl() {
		LinkedList<String> list = new LinkedList<>();
		List<WebElement> lst = driver.findElements(locationsBy);
		Iterator<WebElement> it = lst.iterator();
		
		while(it.hasNext()) {
			list.add(it.next().getAttribute("href"));
		}
		return list;
	}
	
	public boolean isNextPagePresent() {
		
		try {
			driver.findElement(nextPageBy);
			System.out.println("Next Page preasent");
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
				
	}
	
	public boolean clickNextButton() {
		try {
			driver.findElement(nextPageBy).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private WebElement getNextPageWE() {
		return driver.findElement(nextPageBy);
	}
	
	public String getNextPageUrl() {
		return getNextPageWE().getAttribute("href");
	}
	
	public WebElement getDispalyAllResultsWE() {
		return driver.findElement(dispalyAllResultsBy);
	}

}
