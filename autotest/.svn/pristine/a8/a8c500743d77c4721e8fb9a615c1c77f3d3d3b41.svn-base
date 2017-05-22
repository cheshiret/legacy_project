package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrReinstateInventoryWidget extends DialogWidget{
	
	private static LicMgrReinstateInventoryWidget _instance = null;
	
	protected LicMgrReinstateInventoryWidget(){
		
	}
	
	public static LicMgrReinstateInventoryWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrReinstateInventoryWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", "Reinstate Inventory");
	}
	
	public void selectVendor(String vendorNum, String vendorName){
		if(null !=vendorNum && vendorNum.trim().length()>0){
			String vendorInfo = vendorNum + " - " + vendorName;
			browser.selectDropdownList(".id", 
					new RegularExpression("InventoryContainer-\\d+\\.selectedVendor",false), vendorInfo);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("InventoryContainer-\\d+\\.selectedVendor",false), 0);
		}
	}
	
	public void selectAgent(String agentID, String agentName){
		if(null !=agentID && agentID.trim().length()>0){
			String agentInfo = agentID + " - " + agentName;
			browser.selectDropdownList(".id", 
					new RegularExpression("InventoryContainer-\\d+\\.selectedStore",false), agentInfo);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("InventoryContainer-\\d+\\.selectedStore",false), 0);
		}	
	}
	
	public void setReinstateInventoryInfo(String vendorNum, String vendorName, String agentID, String agentName){
		this.selectVendor(vendorNum,vendorName);
		ajax.waitLoading();
		this.selectAgent(agentID, agentName);
	}
}
