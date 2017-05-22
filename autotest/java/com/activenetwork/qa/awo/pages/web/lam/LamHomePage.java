package com.activenetwork.qa.awo.pages.web.lam;


public class LamHomePage extends LamTopMenuBar {
  	private static LamHomePage _instance=null;
  	
  	public static LamHomePage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamHomePage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamHomePage() {
  	  
  	}
  	
  	public boolean exists() {
//  	return browser.checkHtmlObjectExists(".id","lam.home");
  		return browser.checkHtmlObjectExists(".id","lamHome");
  	}
}
