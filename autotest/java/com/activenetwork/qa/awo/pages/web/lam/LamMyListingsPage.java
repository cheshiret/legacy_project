package com.activenetwork.qa.awo.pages.web.lam;


public class LamMyListingsPage extends LamTopMenuBar {
  	private static LamMyListingsPage _instance=null;
  	
  	public static LamMyListingsPage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamMyListingsPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamMyListingsPage() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".id","myListings");
  	}
  	
  	/**
  	 * Click to go to create new listing
  	 */
  	public void clickCreateNewListing() {
  	  	browser.clickGuiObject(".text","Create New Listing");
  	}
}
