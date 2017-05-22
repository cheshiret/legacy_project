package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddSeasonWidget extends DialogWidget{
	private static LicMgrAddSeasonWidget _instance = null;
	
	private LicMgrAddSeasonWidget(){
		
	}
	
	public static LicMgrAddSeasonWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddSeasonWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Add Season");
	}
	
	public void setHarvestDesignation(String harvestDesignation){
		browser.setTextField(".id", new RegularExpression("^HuntingSeasonView-\\d+\\.harvestDesignation",false), harvestDesignation);
	}
	
	public void setPrintOrder(String printOrder){
		browser.setTextField(".id", new RegularExpression("^HuntingSeasonView-\\d+\\.printOrder",false), printOrder);
	}
	
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression("^HuntingSeasonView-\\d+\\.description",false), description);
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".class","Html.DIV",".id","NOTSET");
	}
	
	public String getHarvestDesignation(){
		return browser.getTextFieldValue(".id", new RegularExpression("^HuntingSeasonView-\\d+\\.harvestDesignation",false));
	}
	
	public void setSeasonInfo(String printOrder, String description){
		this.setPrintOrder(printOrder);
		this.setDescription(description);
	}
	
	public void setSeasonInfo(String harvestDesignation, String printOrder, String description ){
		this.setHarvestDesignation(harvestDesignation);
		this.setSeasonInfo(printOrder, description);
	}

}
