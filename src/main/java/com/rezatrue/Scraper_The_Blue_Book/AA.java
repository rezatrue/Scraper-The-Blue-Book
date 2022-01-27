package com.rezatrue.Scraper_The_Blue_Book;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AA {

	public static void main(String[] args) {
		Random random = new Random();
		//System.out.println(random.nextInt(0));
		System.out.println(random.nextInt(1));
		System.out.println(random.nextInt(2));
		/*
		String url = "http://www.thebluebook.com/search.html?class=500&region=43&searchsrc=index";
		String urlStartingWith = url.substring(url.indexOf("//")+2, url.indexOf("&search"));
		System.out.println(urlStartingWith);
		String url1 = "https://www.thebluebook.com/search.html?class=500&region=43&searchsrc=index&searchTerm=Car+Stops+%26+Curbing&regionLabel=Alabama";
		if(url1.contains(urlStartingWith)) {
			System.out.println("match");
		}
		*/
		
		/*
		System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
	    Proxy proxy = new Proxy();
	    proxy.setHttpProxy(meshProxyPort); 
	    proxy.setSslProxy(meshProxyPort);

	    //DesiredCapabilities capabilities = DesiredCapabilities.chrome(); 
	    //capabilities.setCapability("proxy", proxy);

	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    options.setHeadless(true);
	    options.setProxy(proxy);
		*/
		/*
		String apiKey = "78ffb16f95f7d62a690d452098337bc9";
		String proxy = "http://scraperapi:" + apiKey + "@proxy-server.scraperapi.com";
		//URL server = new URL("https://httpbin.org/ip");
		
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
	    systemProperties.setProperty("http.proxyHost", proxy);
	    systemProperties.setProperty("http.proxyPort", "8001");
	    
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("start-maximized");
	    options.setHeadless(false);
	    
	    WebDriver driver = new ChromeDriver(options);
	    driver.get("https://httpbin.org/ip");
	    driver.get("https://www.whatismyip.com/");
	    driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	    System.out.println(driver.findElement(By.xpath("//a[@id='ipv4']")).getText());
		*/
		
//		String targetUrl = "https://www.thebluebook.com/iProView/296168/0/0/locations-contacts/";
//		String targetUrl = "https://www.thebluebook.com/iProView/296168/0/0/locations-contacts/";
//		String targetUrl = "https://www.thebluebook.com/search.html?class=4110&region=7&searchsrc=index&searchTerm=Cast%20Stone&page=2";
//		String targetUrl = "https://www.thebluebook.com/search.html?class=175&region=43&searchsrc=index&searchTerm=Architects&regionLabel=Alabama";
		/*
		String reshtml = "";
		try {
			 String apiKey = "78ffb16f95f7d62a690d452098337bc9";
			 String url = "http://api.scraperapi.com?api_key=" + apiKey + "&url="+targetUrl+"&render=true";
			 URL urlForGetRequest = new URL(url);
			 String readLine = null;
			 HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			 conection.setRequestMethod("GET");
			 int responseCode = conection.getResponseCode();
			 if (responseCode == HttpURLConnection.HTTP_OK) {
			 BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			 StringBuffer response = new StringBuffer();
			 while ((readLine = in.readLine()) != null) {
			 response.append(readLine);
			 }
			 in.close();
			 reshtml = response.toString();
			 System.out.println(reshtml);
			 } else {
			 throw new Exception("Error in API Call");
			 }
			} catch (Exception ex) {
			 ex.printStackTrace();
			}
		
		*/
		
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/2AB, Inc. - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/A.M. Site Development LLC - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/A.G. Dre'Co, Inc. - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/AR Builders - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Bath Builders LLC - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Bates Diversified Construction, Inc. - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Bailey & Owens General Contractors - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Berner International Corp. - Locations and Key Contacts _ ProView.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Atlas Preferred Services, Inc. - Locations and Key Contacts _ ProView.html");
		
		
		
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Search page.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Search page1.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Search page2.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/Search page3.html");
//		File input = new File("F:/Scraping/2021/Dec/The-Blue-Book/pages/l4.html");
		/*
		try {
			Document doc = Jsoup.parse(input, "UTF-8", "https://www.thebluebook.com/");
			System.out.println(".........................");
			ListPageJSoup lpj = new ListPageJSoup(doc);
			
			System.out.println(lpj.getListSize());
			System.out.println(lpj.nextPageUrl());
			LinkedList<String> list = lpj.getLocationListUrl();
			for(int i =0 ; i <list.size(); i++) {
				System.out.println(list.get(i));
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
			
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i =0; i < 18; i++) {
			executor.submit(new Processor(i));
		}
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(2, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}


class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        System.out.println("Completed: " + id);
    }
}