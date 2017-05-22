package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddSpeciesWidget extends DialogWidget{
	private static LicMgrAddSpeciesWidget _instance = null;
	
	private LicMgrAddSpeciesWidget(){
		
	}
	
	public static LicMgrAddSpeciesWidget getInstance(){
		if(null==_instance){
			_instance = new LicMgrAddSpeciesWidget();
		}
		
		return _instance;
	}
	
	private String prefix = "^SpeciesView-\\d+\\.";
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Add Species");
	}
	
	public void setHarvestDesignation(String harvestDesignation){
		browser.setTextField(".id", new RegularExpression(prefix + "harvestDesignation",false), harvestDesignation);
	}
	
	public void setCode(String code){
		browser.setTextField(".id", new RegularExpression(prefix + "code", false),code);
	}
	
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression(prefix + "description", false),description);
	}
	
	public void setLocationAlias(String locAlias){
		browser.setTextField(".id", new RegularExpression(prefix + "locAlias", false),locAlias);
	}
	
	public void clickAddSpeciesSubType(){
		browser.clickGuiObject(".class","Html.A",".text","Add Species Sub-Type");
	}
	
	public void setSubTypeCode(String code, int index){
		browser.setTextField(".id", new RegularExpression("SpeciesSubTypeView-\\d+\\.code", false), code,
				true, index);
	}
	
	public void setSubTypeDesc(String desc, int index){
		browser.setTextField(".id", new RegularExpression("SpeciesSubTypeView-\\d+\\.description", false), desc,
				true, index);
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public String getHarvestDesignation(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix + "harvestDesignation",false));		
	}
	
	public void setSpeciesInfo(String description){
		this.setDescription(description);
	}
	
	public void setSpeciesInfo(String harvestDesignation, String description){
		this.setHarvestDesignation(harvestDesignation);
		this.setSpeciesInfo(description);
	}

	public void setSpeciesInfo(Species species, boolean isUseDefaultHarvestDesig) {
		if(!isUseDefaultHarvestDesig){
			this.setHarvestDesignation(species.speciesId);
		}
		this.setCode(species.code);
		this.setDescription(species.description);
		this.setLocationAlias(species.locationAlias);
		for(int i=0; i<species.subTypeList.size(); i++){
			this.clickAddSpeciesSubType();
			ajax.waitLoading();
			this.setSubTypeCode(species.subTypeList.get(i).code, i);
			this.setSubTypeDesc(species.subTypeList.get(i).desc, i);
		}
	}
}
