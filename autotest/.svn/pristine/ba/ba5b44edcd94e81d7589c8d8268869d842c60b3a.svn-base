package com.activenetwork.qa.awo.pages.web.lam;

public class LamOopsPage extends LamTopMenuBar {
  	private static LamOopsPage _instance=null;
  	
  	public static LamOopsPage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamOopsPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamOopsPage() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".id","sysError");
  	}
}
