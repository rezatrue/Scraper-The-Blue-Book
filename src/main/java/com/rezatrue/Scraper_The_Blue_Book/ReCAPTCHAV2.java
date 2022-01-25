package com.rezatrue.Scraper_The_Blue_Book;

import com.twocaptcha.TwoCaptcha;
import com.twocaptcha.captcha.ReCaptcha;

public class ReCAPTCHAV2 {

	private final String YOUR_API_KEY = "8ba8d534a82046e7d47e520d1c02e3d8";
	//private String siteKey = "6LeIxboZAAAAAFQy7d8GPzgRZu2bV0GwKS8ue_cH";
	//private String siteUrl = "https://2captcha.com/demo/recaptcha-v2-callback";
	
	String login;
	String password;
	String IP_address;
	String port;
	
	public ReCAPTCHAV2() {
	}
	
	public ReCAPTCHAV2(String login, String password, String IP_address, String port) {
		this.login = login;
		this.password = password;
		this.IP_address = IP_address;
		this.port = port;
	}

	public String getRecaptchaResponseCode(String siteKey, String siteUrl) {
        TwoCaptcha solver = new TwoCaptcha(YOUR_API_KEY);
        ReCaptcha captcha = new ReCaptcha();
        solver.setSoftId(156570);
        solver.setDefaultTimeout(120);
        solver.setRecaptchaTimeout(600);
        solver.setPollingInterval(10);
        captcha.setSiteKey(siteKey);
        captcha.setUrl(siteUrl);
        if(IP_address != null)
        	captcha.setProxy("HTTPS", login+":"+password+"@"+IP_address+":"+port);
        try {
            solver.solve(captcha);
            System.out.println("Captcha solved: " + captcha.getCode());
            return captcha.getCode();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        return null;
	}
	
	
}
