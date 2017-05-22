package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the page for add product group of activity,
 * @How to get this page:Click "Add New" after Product Group dropdown list in activity detail page
 * @author Phoebe
 */
public class LicMgrAddActivityProductGroupWidget extends DialogWidget{
	private static LicMgrAddActivityProductGroupWidget _instance = null;
	
	protected LicMgrAddActivityProductGroupWidget (){}
	
	public static LicMgrAddActivityProductGroupWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddActivityProductGroupWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"addActivityProductGroupBar");
	}
	
	protected Property[] facilityPrdGroupName(){
		return Property.toPropertyArray(".id", new RegularExpression("ProductGroupView-\\d+\\.productGroupName", false));
	}
	
	
	public void setGroupName(String name){
		browser.setTextField(this.facilityPrdGroupName(), name);
	}
	
	public void setGoupNameAndClickOk(String name){
		this.setGroupName(name);
		this.clickOK();
	}
}
