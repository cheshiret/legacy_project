package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * This is add category for hunt page in license manager, Admin(drop down list)-->Lotteries --- > 
 * Hunt Locations --<BUTTON>-->Add Hunt Location --(Sub Location)--> Add New
 * @author pchen
 * @Date Nov 12, 2012
 */
public class LicMgrAddCategoryWidget extends DialogWidget{
	private static LicMgrAddCategoryWidget _instance = null;
	
	protected LicMgrAddCategoryWidget (){}
	
	public static LicMgrAddCategoryWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddCategoryWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return (super.exists() && browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "category",getWidget()[0]));
	}
	
	public void setCategoryName(String categoryName){
		browser.setTextField(".id", new RegularExpression("HuntSubLocationCategoryView-\\d+\\.name", false), categoryName);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
