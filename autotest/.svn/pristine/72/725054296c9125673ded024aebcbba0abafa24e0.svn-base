package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.awo.pages.UwpPage;


public abstract class LamTopMenuBar extends UwpPage {

	public boolean menuBarExists() {
  	  	return browser.checkHtmlObjectExists(".id","topnav");
  	}
  
  	/**
  	 * Click to go to my listing
  	 */
	public void clickMyListing() {
  	  	browser.clickGuiObject(".id","myListings");
  	}
  	
	/**
	 * Click to go to my account
	 */
  	public void clickMyAccount() {
  	  	browser.clickGuiObject(".id","MyAccount");
  	}
  	
  	/**
  	 * Click to signout
  	 */
  	public void clickSignOut() {
  	  	browser.clickGuiObject(".text","Sign Out");
  	}
  	
  	/**
  	 * Click to signin
  	 */
  	public void clickSignIn() {
	  	browser.clickGuiObject(".text","Sign In");
	}
}
