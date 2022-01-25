package com.rezatrue.Scraper_The_Blue_Book;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

public class ScraperapiHandler {
	private String apiKey;
	public ScraperapiHandler() {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("scraperapi.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> keys = prop.stringPropertyNames();
		Iterator<String> it = keys.iterator();	
		while(it.hasNext()) {
			String key = it.next();
			String value = prop.getProperty(key);
			if(key.contentEquals("APIKey")) this.apiKey=value;			
		}
		
	}

	public String loadPage(String targetUrl, boolean render) {
		String res = "";
		try {
			 String url = "http://api.scraperapi.com?api_key=" + apiKey + "&url="+targetUrl;
			 if(render) url = url + "&render=true";
			 
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
			 res = response.toString();
			 in.close();
			 System.out.println("success");
			 } else {
			 throw new Exception("Error in API Call");
			 }
			} catch (Exception ex) {
			 ex.printStackTrace();
			}
		return res;
	}
}
