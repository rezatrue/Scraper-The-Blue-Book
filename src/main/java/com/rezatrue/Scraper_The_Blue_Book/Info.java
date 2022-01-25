package com.rezatrue.Scraper_The_Blue_Book;

public class Info {
	String serialNumber;
	String profileUrl;
	String companyName;
	String address;
	String website;
	String contactPerson;
	String phone;
	String category;
	
	public Info(){
		super();
	}

	public Info(String serialNumber, String profileUrl, String companyName, String address, String website,
			String contactPerson, String phone, String category) {
		super();
		this.serialNumber = serialNumber;
		this.profileUrl = profileUrl;
		this.companyName = companyName;
		this.address = address;
		this.website = website;
		this.contactPerson = contactPerson;
		this.phone = phone;
		this.category = category;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
