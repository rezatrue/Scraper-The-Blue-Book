package com.rezatrue.Scraper_The_Blue_Book.Objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompanyPage {

	private WebDriver driver;
	
	public CompanyPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By nameBy = By.xpath("//h2[contains(.,'Locations')]/following::h2[1]");
	private By addressBy = By.xpath("//h2[contains(.,'Locations')]/following::h2[1]/following::div[1]/div[1]/div[1]");
	private By websiteBy = By.xpath("//h2[contains(.,'Locations')]/following::h2[1]/following::div[1]/div[1]/a[contains(.,'Website')]");
	private By contactPersonBy = By.xpath("//h2[contains(.,'Locations')]/following::h2[1]/following::div[1]/div[1]/i/following::text()[1]");
	//private By phoneBy = By.xpath("//h2[contains(.,'Locations')]/following::h2[1]/following::div[1]/div[1]/a[contains(.,'View Phone')]");
	private By phone1By = By.xpath("//h2[contains(.,'Locations')]/following::h2[1]/following::div[1]/div[1]/a[contains(@title,'Phone')]");
	private By phone2By = By.xpath("//a[contains(@class,'telLink')]");
	private By contactPerson1By = By.xpath("//h5/strong");
	private String categoryXpathString = "//ol[contains(@class,'breadcrumb')]/li";
	private By categoryBy = By.xpath(categoryXpathString);
	
	public String getCategory() {
		
		List<WebElement> lst = driver.findElements(categoryBy);
		return driver.findElement(By.xpath(categoryXpathString+"["+lst.size()+"]")).getText();
	}
	
	public String getName() {
		return driver.findElement(nameBy).getText().trim();
	}
	
	public String getAddress() {
		return driver.findElement(addressBy).getText().replaceAll("\\n\\r\\s", " ");
	}
	
	public String getWebsite() {
		return driver.findElement(websiteBy).getAttribute("href");
	}
	
	public String getContactPerson() {
		String name;
		try {
			name = driver.findElement(contactPersonBy).getText();
		} catch (Exception e) {
			name = driver.findElement(contactPerson1By).getText();	
		}
		return name;
	}
	
	public String getPhone() {
		String phone = "";
		try{phone = driver.findElement(phone1By).getAttribute("data-dialnumber").trim();}
		catch(Exception e) {
			try{phone = driver.findElement(phone2By).getAttribute("data-dialnumber").trim();}catch(Exception ex) {
				System.out.println("No Phone number available");}
		}
		
		return phone;
	}
	
	
}
