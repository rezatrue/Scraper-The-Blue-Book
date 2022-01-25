package com.rezatrue.Scraper_The_Blue_Book;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class CsvUtm8Generator {
	private String subcat;
	private String folder;
	
	public CsvUtm8Generator(String folder, String subcat) {
		this.folder = folder;
		this.subcat= subcat;
	}

	public int listtoCsv(LinkedList<Info> list) {
		
		int count = 0;
		
		System.out.println("list size : " + list.size());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Calendar cal = Calendar.getInstance();
		String fileName = dateFormat.format(cal.getTime());

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(folder+"//TheBlueBook_"+subcat+ "_" + fileName + ".csv", "UTF-8");

			writer.append("SL.No.");
			writer.append(",");
			writer.append("Profile_Url");
			writer.append(",");
			writer.append("Company_Name");
			writer.append(",");
			writer.append("Address");
			writer.append(",");
			writer.append("Website");
			writer.append(",");
			writer.append("Contact_Person");
			writer.append(",");
			writer.append("Phone");
			writer.append(",");
			writer.append("Category");
			writer.append(",");
			writer.append("\n");

			System.out.println(" -- out size-- "+ list.size());
			if(list.size() > 0) {
				Iterator<Info> it = list.iterator();
				
				while (it.hasNext()) {
					Info info = (Info) it.next();
					try {
					System.out.println(info.getCompanyName());
					writer.append(info.getSerialNumber());
					writer.append(",");
					writer.append(info.getProfileUrl());
					writer.append(",");
					writer.append(csvformatDevider(info.getCompanyName()));
					writer.append(",");
					writer.append(csvformatDevider(info.getAddress()));
					writer.append(",");
					writer.append(csvformatDevider(info.getWebsite()));
					writer.append(",");
					writer.append(csvformatDevider(info.getContactPerson()));
					writer.append(",");
					writer.append(csvformatDevider(info.getPhone()));
					writer.append(",");
					writer.append(csvformatDevider(info.getCategory()));
					writer.append("\n");
					
					}catch (Exception e) {
						//..
					}
				}
			}		

		} catch (IOException e) {
			System.out.println(" csv g Error : " + e.getMessage());
		}
		finally {	
				writer.flush();
				writer.close();	
        }
		return count;
	} 

	private String csvformatDevider(String text) {
		String newText; 
		try {
		newText = text.replaceAll("[\\t\\n\\r]", " ");
		
		if (newText.contains(","))
			if (!newText.startsWith("\"") && !newText.endsWith("\""))
				newText = "\"" + newText + "\"";
		}catch (Exception e) {
			return "";
		}
		return newText;
	}
	
	

}
