package com.rezatrue.Scraper_The_Blue_Book.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptchaPage {

	private String captchaUrlStarter1 = "https://www.thebluebook.com/captcha.html?url=";
	private String captchaUrlStarter2 = "https://www.thebluebook.com/iProView/validate/?qp=";
	
	private WebDriver driver;
	
	public CaptchaPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By sitKeyBy = By.xpath("//div[@class='g-recaptcha']");
	//private By submitBy = By.xpath("//input[@id='submit']");
	private By submitBy = By.xpath("//button[@id='submit']");
	
	public boolean isSiteKeypresent() {		
		try {
			driver.findElement(sitKeyBy);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public String getSiteKey() {
		return driver.findElement(sitKeyBy).getAttribute("data-sitekey");
	}
	
	public void addSubmitButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;      
        //js.executeScript("document.getElementById('searchFrm').innerHTML = '<input id=\"submit\" type=\"submit\">';");
        js.executeScript("document.getElementById('searchFrm').innerHTML = '<button id=\"submit\" type=\"submit\">';");
	}
	
	public void makeCodeTextVisibel() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("g-recaptcha-response"));
        js.executeScript("arguments[0].setAttribute('style', 'display: block')", element);
    }
	
	
	public WebElement getSubmitButtonWe() {
		return driver.findElement(submitBy);
	}
	
	public void setResolveCode(String reCode) {
		JavascriptExecutor js = (JavascriptExecutor) driver;     
        js.executeScript("document.getElementById('g-recaptcha-response').innerHTML = '"+ reCode +"';");
	}

}
