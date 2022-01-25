package com.rezatrue.Scraper_The_Blue_Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

// woring with
//https://proxymesh.com/account/dashboard/

public class MeshProxyHandler {

	private Properties prop;
	private LinkedList<String> meshProxyPorts = null;
	private String meshPac = null;
	private String meshUser = null;
	private String meshPassword = null;
	
	
	public MeshProxyHandler() {
		meshProxyPorts = new LinkedList<>();
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("mesh.properties");
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
			if(key.startsWith("meshHostPort")) this.meshProxyPorts.add(value);
			if(key.contentEquals("meshPac")) this.meshPac=value;
			if(key.contentEquals("meshUser")) this.meshUser=value;
			if(key.contentEquals("meshPassword")) this.meshPassword=value;
		}
			
		meshProxyPorts.forEach(e -> System.out.println(e));
		System.out.println("meshPac="+meshPac);
		System.out.println("meshUser="+meshUser);
		System.out.println("meshPassword="+meshPassword);
		
	}

	public String getMeshProxyPort() {
		//if(meshProxyPorts.size() == 1) return meshProxyPorts.get(0);
		if(meshProxyPorts.size() < 1) return null;
		Random random = new Random();
		return meshProxyPorts.get(random.nextInt(meshProxyPorts.size()));
		
	}


	public String getMeshPac() {
		return meshPac;
	}


	public String getMeshUser() {
		return meshUser;
	}


	public String getMeshPassword() {
		return meshPassword;
	}
	
}
