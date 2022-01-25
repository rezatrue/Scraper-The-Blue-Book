package com.rezatrue.Scraper_The_Blue_Book;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class FireFoxHandaler {
	WebDriver driver;

	String meshProxyPort = null;
	String meshPac = null;
	String meshUser = null;
	String meshPassword = null;
	
	//private ProxyHandler proxyHandler; 
	private MeshProxyHandler meshProxyHandler;
	
	FireFoxHandaler(){
		try {
		meshProxyHandler = new MeshProxyHandler();
		this.meshProxyPort = meshProxyHandler.getMeshProxyPort();
		this.meshPac = meshProxyHandler.getMeshPac();
		this.meshUser = meshProxyHandler.getMeshUser();
		this.meshPassword = meshProxyHandler.getMeshPassword();
		
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public boolean openFirefoxBrowser() {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
	    Proxy proxy = new Proxy();
	    proxy.setHttpProxy(meshProxyPort); 
	    proxy.setSslProxy(meshProxyPort);

	    //DesiredCapabilities capabilities = DesiredCapabilities.chrome(); 
	    //capabilities.setCapability("proxy", proxy);

	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    options.setHeadless(false);
	    options.setProxy(proxy);
	    //capabilities.setCapability(ChromeOptions.CAPABILITY, options);

	    driver = new ChromeDriver(options);
		
		   	driver.get("http://mensmugs.com/");
			//driver.get("https://www.javatpoint.com/");
			try {
				Thread.sleep(1000*5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		   return true;
		}
	
	
	
	public void closeBrowser() {
		driver.quit();
//		System.clearProperty("webdriver.gecko.driver");
//		try {
//			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
//			//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
//	        //Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe /T");
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	/*public boolean openBrowser() {
		System.setProperty("webdriver.gecko.driver",
				"Geckodriver\\v0.26.0-win64\\geckodriver.exe");
		
		driver = new FirefoxDriver();	
		//driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		//runfirefoxDefaultProfile();
		
		return true;
	}*/
	
	private int limits = 2;
	public boolean loadUrl(String url) {
		
        System.out.println("Loading limits: "+ limits);

		try {
			limits--;
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(1000*2, TimeUnit.MILLISECONDS);
			limits = 2; // reset
			return true;
		} catch (Exception e) {
			System.out.println("Loading URL Exception: " + e.getMessage());
			if(limits <= 0) loadUrl(url);
			limits = 2; // reset
			return false;
		}
		
	}

	
	public boolean captchaBypassWith2capt() {
		
		CaptchaHandler capt = new CaptchaHandler(driver);
		return capt.resolveCaptchaIssue();
	}
	
	public boolean isCaptchaPresent() {
		CaptchaHandler capt = new CaptchaHandler(driver);
		return capt.isCaptchaPage();
	}
	
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	//...........................................

	
	public String takeScreenShot(){
		
		TakesScreenshot tss = (TakesScreenshot)driver;
		
		String destinationPath = System.getProperty("user.dir")+"screenshot+.png";
		File destinationFile = new File(destinationPath);
		File source = tss.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, destinationFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationPath;
	}

	public void waitForMill(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime,TimeUnit.MILLISECONDS);	
	}
	
	private void scrollToViewElement(By by) {
		
		try {
			WebElement element = driver.findElement(by);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean waitUntillCkickable(By by) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public boolean waitUntillVisible(By by) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void fullPageScroll() {
		// https://stackoverflow.com/questions/42982950/how-to-scroll-down-the-page-till-bottomend-page-in-the-selenium-webdriver
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("scroll(0, 250);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 550);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 750);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1050);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1250);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1550);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 1750);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 2050);");
			Thread.sleep(500);
			jse.executeScript("scroll(0, 2450);");
			Thread.sleep(700);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if I direct go to bottom of the page page full content don't load
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}


	//// ...............................................Proxy.............................
	/*
	String httpProxyWithPort = "null";
	String httpsProxyWithPort = null;
	String socksProxyWithPort = null;
	String username = null;
	String password = null;
	
	
		// have some issue with proxy authentication
		public boolean openFirefoxBrowser1(String userAgent) {
		   	
		   FirefoxBinary firefoxBinary = new FirefoxBinary();
		   //firefoxBinary.addCommandLineOptions("--headless");
		   System.setProperty("webdriver.gecko.driver", "Geckodriver\\v0.26.0-win64\\geckodriver.exe");
		   FirefoxOptions firefoxOptions = new FirefoxOptions();
		   
		   if(!httpProxyWithPort.isEmpty()) {
			   System.out.println("proxy on Fire");
		   Proxy proxy = new Proxy();
		   //proxy.setProxyType(Proxy.ProxyType.MANUAL);
		   proxy.setHttpProxy(httpProxyWithPort);
		   proxy.setSslProxy(httpsProxyWithPort);
//		   proxy.setSocksProxy(socksProxyWithPort);
//		   proxy.setSocksVersion(5);
		   //proxy.setSocksUsername(username);
		   //proxy.setSocksPassword(password);
		   firefoxOptions.setProxy(proxy);
		   }
		   firefoxOptions.addPreference("general.useragent.override", userAgent);
		   firefoxOptions.setBinary(firefoxBinary);
		   driver = new FirefoxDriver(firefoxOptions);
	   
	        
	        WebDriverWait wait = new WebDriverWait(driver, 10);      
	 		wait.until(ExpectedConditions.alertIsPresent());
			try {
				Runtime.getRuntime().exec("Geckodriver/auth_popup_firefox.exe");
				Thread.sleep(1000*5);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//loadUrl("https://www.thebluebook.com");
			driver.get("https://www.javatpoint.com/");
	        
		   //driver.get("https://"+username+":"+password+"@"+"www.thebluebook.com");
		   
		   return true;
		}
		
		FireFoxHandaler1(){
			try {
			proxyHandler = new ProxyHandler();
			String proxy = proxyHandler.getProxy();
			System.out.println("proxy: "+proxy);
			if(proxy != null) {
				this.username = proxyHandler.getUser();
				this.password = proxyHandler.getPassword();	
				this.httpProxyWithPort = proxy + ":" + proxyHandler.getHttpPort();
				this.httpsProxyWithPort = proxy + ":" + proxyHandler.getHttpsPort();
				this.socksProxyWithPort = proxy + ":" + proxyHandler.getSocks5Port();
			}
			}catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}

		// https://itnext.io/how-to-run-a-headless-chrome-browser-in-selenium-webdriver-c5521bc12bf0
		public boolean openChromeBrowser1(String userAgent) {
	       
			System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");          
	        ChromeOptions options = new ChromeOptions();
	        //options.addArguments("--headless");
	       
		   if(!httpProxyWithPort.isEmpty()) {
			   System.out.println("pp");
		   Proxy proxy = new Proxy();
		   proxy.setProxyType(Proxy.ProxyType.MANUAL);
		   proxy.setHttpProxy(httpProxyWithPort);
		   proxy.setSslProxy(httpsProxyWithPort);
		   proxy.setProxyAutoconfigUrl(proxyAutoconfigUrl);
		   
//		   proxy.setSocksProxy(socksProxyWithPort);
//		   proxy.setSocksVersion(5);
		   //proxy.setSocksUsername(username);
		   //proxy.setSocksPassword(password);
		   options.setProxy(proxy);
		   }
		           
	        driver = new ChromeDriver(options); 
	        driver.navigate().to("http://www.javatpoint.com/");
	        try {
				Thread.sleep(1000*20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        

		   WebDriverWait wait = new WebDriverWait(driver, 10);      
		   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		   
		   alert.sendKeys(username + Keys.TAB + password);
		   alert.accept();
		   	       
	 	   //driver.navigate().to("http://"+username+":"+password+"@"+"www.javatpoint.com");
		   //driver.navigate().to("https://"+username+":"+password+"@"+"www.thebluebook.com");
		   
		   //driver.get("https://www.thebluebook.com");
		   driver.manage().window().maximize();
		   return true;
		}

		*/
		
}
