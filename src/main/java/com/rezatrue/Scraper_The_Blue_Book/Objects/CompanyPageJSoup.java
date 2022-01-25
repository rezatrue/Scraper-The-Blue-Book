package com.rezatrue.Scraper_The_Blue_Book.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CompanyPageJSoup {

	private Document doc;
	
	public CompanyPageJSoup(String html) {
		//doc = Jsoup.parse(input, "UTF-8", "https://www.thebluebook.com/");
		doc = Jsoup.parse(html);
	}
	
	private String nameSelector = "div.card-body.p-0 > h2";
	private String addressSelector = "div.card-body.p-0 > div > div.col-md > div";
	private String website1Selector = "div.card-body.p-0 > div > div.col-md > a.badge.badge-light.text-primary.extTrk";
	private String website2Selector = "a.badge.badge-light.text-primary.extTrk";
	private String phone1Selector = "div.card-body.p-0 > div > div.col-md > a";
	private String phone2Selector = "a.telLink";
	private String contactPerson1Selector = "div.media-body > h5.media-heading.m-0.border-bottom";
	private String contactPerson2Selector = "h5 > strong";
	private String contactPersonRawSelector = "div.card-body.p-0 > div > div.col-md";
	private String categorysSelector = "ol.breadcrumb > li";
	
	public String getCategory() {
		Elements cats = doc.select("ol.breadcrumb > li");
		return doc.select(categorysSelector+":nth-child("+cats.size()+") > a:nth-child(1)").text();
	}
	
	public String getName() {
		return doc.select(nameSelector).text().trim();
	}
	
	public String getAddress() {
		return doc.select(addressSelector).text().replaceAll("\\n\\r\\s", " ").trim();
	}
	
	public String getWebsite() {
		String website = doc.select(website1Selector).attr("href");
		if(website.length() == 0)
			return doc.select(website2Selector).attr("href");
		else return website;
	}
	
	public String getContactPerson() {
		String name = "";
		name = doc.select(contactPerson1Selector).text();
		if(name.length() == 0) name = doc.select(contactPerson2Selector).text();
		if(name.length() == 0) {
			
			int fristIndex = doc.select(addressSelector).text().replaceAll("\\n\\r\\s", " ").length() + 1;
			String contectRaw = doc.select(contactPersonRawSelector).text();
			try {
			name = (contectRaw.substring(fristIndex, contectRaw.indexOf("View Phone"))).trim();
			}catch(Exception e) {
				try {
					name = (contectRaw.substring(fristIndex, contectRaw.length())).trim();
					}catch(Exception ee) {
						System.out.println("No Contect Persion");
					}
			}
			
		}
		if(name.length() > 0 && name.contains("Unknown User")) name = "";
		return name;
	}
	
	public String getPhone() {
		String phone = doc.select(phone1Selector).attr("data-dialnumber");
		if(phone.length() == 0)
		    return doc.select(phone2Selector).attr("data-dialnumber");
		else return phone;
	}
	
	
}
