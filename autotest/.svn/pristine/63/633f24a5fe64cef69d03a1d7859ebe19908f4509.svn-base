package com.activenetwork.qa.awo.pages.web.lam;

public class LamMyAccountPage extends LamTopMenuBar {
  	private static LamMyAccountPage _instance=null;
  	
  	protected LamMyAccountPage() {
  	  
  	}
  	
  	public static LamMyAccountPage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamMyAccountPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".id","accountoverviewpage");
  	}
}
