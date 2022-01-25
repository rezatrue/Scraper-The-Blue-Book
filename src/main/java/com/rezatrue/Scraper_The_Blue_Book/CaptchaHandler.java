package com.rezatrue.Scraper_The_Blue_Book;

import org.openqa.selenium.WebDriver;

import com.rezatrue.Scraper_The_Blue_Book.Objects.CaptchaPage;

public class CaptchaHandler {

	//private String urlStarter = "https://www.thebluebook.com/captcha.html?url=";
	private String captchaUrlStarter1 = "https://www.thebluebook.com/captcha.html?url=";
	private String captchaUrlStarter2 = "https://www.thebluebook.com/iProView/validate/?qp=";
	private WebDriver driver;
	
	public CaptchaHandler(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCaptchaPage() {
		String url = driver.getCurrentUrl();
		if(url.startsWith(captchaUrlStarter1))
			return true;
		if(url.startsWith(captchaUrlStarter2))
			return true;
		return false;
//		CaptchaPage captchaPage = new CaptchaPage(driver);
//		return captchaPage.isSiteKeypresent();
	}
	
	public boolean resolveCaptchaIssue() {
		String url = driver.getCurrentUrl();
		
		CaptchaPage captchaPage = new CaptchaPage(driver);
		String siteKey = captchaPage.getSiteKey();
		System.out.println("Site Key : " + siteKey);
		ReCAPTCHAV2 captchav2 = new ReCAPTCHAV2();
		String reCode = captchav2.getRecaptchaResponseCode(siteKey, url);
					
		try {
			Thread.sleep(1000*2);
			
			if(reCode != null) {
				captchaPage.addSubmitButton();
				captchaPage.makeCodeTextVisibel();
				captchaPage.setResolveCode(reCode);
				captchaPage.getSubmitButtonWe().click();
			}
			
		} catch (InterruptedException e) {
			//Exception Message: stale element reference: element is not attached to the page document
			System.out.println("Elements Input issue: " + e.getMessage());
			try {
			driver.navigate().refresh();
			if(reCode != null) {
				captchaPage.addSubmitButton();
				captchaPage.makeCodeTextVisibel();
				captchaPage.setResolveCode(reCode);
				captchaPage.getSubmitButtonWe().click();
			}
			}catch (Exception ee) {return false;}
		}
		
		return isCaptchaPage();
		
	}
	
}
