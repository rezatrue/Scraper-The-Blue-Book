package com.rezatrue.Scraper_The_Blue_Book;

public class App 
{
	
	//private static int categorySerialNumber = 18; // range 1 - 18
	private static int stateSerialNumber = 84; // range 1 - 84
	//private static int subcategorySerialNumber = 1; // range 
	
    public static void main( String[] args )
    {
        System.out.println( "Thebluebook.com Scraper!" );
        
        try {
        if(args.length != 0) {
    		//categorySerialNumber = Integer.parseInt(args[0]);
    		stateSerialNumber = Integer.parseInt(args[0]);
    		//subcategorySerialNumber = Integer.parseInt(args[2]);
        }
        //System.out.println("Category Serial Number: " + categorySerialNumber);
        System.out.println("State Serial Number: " + stateSerialNumber);
        //System.out.println("Subcategory Serial Number: " + subcategorySerialNumber);
           
        
    	System.out.println(">>>>>STATE:>>>>>>>"+stateSerialNumber+"<<<<<<<<<<<<<<<");
    	CategoryItemList categoryItemList = new CategoryItemList(stateSerialNumber);
    	categoryItemList.collectList();
    	categoryItemList.start();
    	
        }catch(Exception e) {
        	System.out.println("Exception Message: "+ e.getMessage());        	
			return;
        }
        
    }
     
}
