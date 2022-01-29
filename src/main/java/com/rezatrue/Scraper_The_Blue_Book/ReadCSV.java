package com.rezatrue.Scraper_The_Blue_Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.nio.file.Files;


public class ReadCSV {

	public LinkedList<SuppiedData> read(String fileName) {
		LinkedList<SuppiedData> sList = new LinkedList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			int lastInt = 0;
			SuppiedData data = null; 
			while (line != null) {
				try {
					String[] attributes = line.split(";");
					System.out.println(attributes[0] + " " + attributes[1]);
					int newInt = Integer.parseInt(attributes[0]);
					if(newInt != lastInt) {
						if(data != null) sList.add(data);
						data = new SuppiedData();
						data.setCatNumber(newInt);
						data.addToList(attributes[1].trim());
						lastInt = newInt;
					}else {
						data.addToList(attributes[1].trim());
						
					}
				}catch(Exception e) {;}
			line = br.readLine();	
			}
			
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }
		return sList;
		
	}
	
	
}
