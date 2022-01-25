package com.rezatrue.Scraper_The_Blue_Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class ProxyHandler {

	private Properties prop;
	private LinkedList<String> proxyList;
	private String httpPort;
	private String httpsPort;
	private String socks5Port;
	private String user;
	private String password;
	
	
	public ProxyHandler(){
		proxyList = new LinkedList<>();
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("proxy.properties");
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
			if(key.startsWith("proxy")) this.proxyList.add(value);
			if(key.contentEquals("httpPort")) this.httpPort=value;
			if(key.contentEquals("httpsPort")) this.httpsPort=value;
			if(key.contentEquals("socks5Port")) this.socks5Port=value;
			if(key.contentEquals("user")) this.user=value;
			if(key.contentEquals("password")) this.password=value;	
		}
//		proxyList.forEach(e -> System.out.println(e));
//		System.out.println("httpPort="+httpPort);
//		System.out.println("httpsPort="+httpsPort);
//		System.out.println("socks5Port="+socks5Port);
//		System.out.println("user="+user);
//		System.out.println("password="+password);
	}

	public String getProxy() {
		if(proxyList.size() < 1) return null;
		Random random = new Random();
		return proxyList.get(random.nextInt(proxyList.size()));
	}

	public String getHttpPort() {
		return httpPort;
	}

	public String getHttpsPort() {
		return httpsPort;
	}

	public String getSocks5Port() {
		return socks5Port;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
}
