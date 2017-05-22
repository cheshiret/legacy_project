package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the widget that used to add a new hunt quota in license manager, Admin(drop down list)-->Lotteries --- >
 *  Hunts --<click>-->Add Hunt  --<select>-->specie --->CLICK Add New Hunt Location ---> CLICK Add New
 * @author pchen
 * @date Sep 18, 2012
 */
public class LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget extends DialogWidget{
	private static LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget _instance = null;
	
	protected LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget (){}
	
	public static LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddNewHuntLocationInfoSubLocatinCategoryWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "Category");
	}
	
	public void setCategoryName(String categoryName){
		browser.setTextField(".id", new RegularExpression("HuntSubLocationCategoryView-\\d+\\.name", false), categoryName);
	}
}
