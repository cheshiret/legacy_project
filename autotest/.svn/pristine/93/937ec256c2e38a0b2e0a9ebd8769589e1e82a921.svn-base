package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddInventoryTypeWidget extends DialogWidget{
	private static LicMgrAddInventoryTypeWidget _instance = null;
	
	protected LicMgrAddInventoryTypeWidget(){
		
	}
	
	public static LicMgrAddInventoryTypeWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrAddInventoryTypeWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", "Add Inventory Type");
	}
	
	public void setInventoryType(String inventoryType){
		browser.setTextField(".id", new RegularExpression("HFInventoryTypeView-\\d+\\.code",false), inventoryType);
	}

}
