package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

public class SysMgrFinUtilPage extends SysMgrCommonTopMenuPage{
	
private static SysMgrFinUtilPage _instance=null;
	
	protected SysMgrFinUtilPage(){}
	
	public static SysMgrFinUtilPage getInstance(){
		if(_instance==null){
			_instance=new SysMgrFinUtilPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		RegularExpression regx=new RegularExpression("^( )*ThreadName( )*Server( )*Status.*",false); 
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", regx);
	}
	
	public void clickRunDailyEFT(){
		browser.clickGuiObject(".class",  "Html.A",".text","Process Daily EFT");
	}
	
	public void clickRunEFTInvoice(){
		browser.clickGuiObject(".class",  "Html.A",".text","Run EFT Invocing");
	}
	
	public void clickRunEFTTransmission(){
		browser.clickGuiObject(".class",  "Html.A",".text","Run EFT Transmission");
	}
	
	public void clickRunEFTReturn(){
		browser.clickGuiObject(".class",  "Html.A",".text","Process EFT Returns");
	}
}
