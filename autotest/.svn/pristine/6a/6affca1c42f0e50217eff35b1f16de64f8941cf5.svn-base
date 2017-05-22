package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCreateInventoryWidget extends DialogWidget{
	
	private static LicMgrCreateInventoryWidget _instance = null;
	
	protected LicMgrCreateInventoryWidget(){
		
	}
	
	public static LicMgrCreateInventoryWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrCreateInventoryWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", new RegularExpression("Create (Privilege|Licence) Inventory", false));
	}
	
	public void selectInventoryType(String inventoryType, String invTypeStatus){		
		if(null != inventoryType && inventoryType.trim().length()>0){
			String inventoryTypeInfo = inventoryType + " ("+ invTypeStatus + ")";
			browser.selectDropdownList(".id", 
					new RegularExpression("^HFInventoryCreationUIModel-\\d+\\.inventoryType",false), inventoryTypeInfo);
		}else {
			browser.selectDropdownList(".id", 
					new RegularExpression("^HFInventoryCreationUIModel-\\d+\\.inventoryType",false), 0);
		}
	}
	
	public void selectLicenseYear(String licenseYear, String issueFromDate, String issueToDate){
		String licenseYearInfo = "";
		
		if(null !=licenseYear && licenseYear.trim().length()>0){
			if(licenseYear.equalsIgnoreCase("ALL")){
				licenseYearInfo = "All";
				if(null !=issueFromDate && issueFromDate.trim().length()>0){
					licenseYearInfo = licenseYearInfo + " (from " + DateFunctions.formatDate(issueFromDate, "dd/MM/yyyy");
				}
				
				if(null !=issueToDate && issueToDate.trim().length()>0){
					licenseYearInfo = licenseYearInfo + " to " + DateFunctions.formatDate(issueToDate, "dd/MM/yyyy") + ")";
				}else{
					if(!licenseYearInfo.equals("All")){
						licenseYearInfo = licenseYearInfo + ")";
					}
				}
			}else{
				licenseYearInfo = licenseYear;
			}	
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryCreationUIModel-\\d+\\.inventoryTypeYear",false), licenseYearInfo);
		}else {
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryCreationUIModel-\\d+\\.inventoryTypeYear",false), 0);
		}	
	}
	
	public void setInventoryNumFrom(String invNumFrom){
		browser.setTextField(".id", 
				new RegularExpression("HFInventoryCreationUIModel-\\d+\\.lowestInventoryNumber",false), invNumFrom, true);
	}
	
	public void setInventoryNumTo(String invNumTo){
		browser.setTextField(".id", 
				new RegularExpression("HFInventoryCreationUIModel-\\d+\\.highestInventoryNumber",false), invNumTo, true);
	}
	
	public void setPrivilegeInventory(PrivilegeInventory priInventory){
		this.selectInventoryType(priInventory.inventoryType, priInventory.inventoryTypeStatus);
		ajax.waitLoading();
		this.selectLicenseYear(priInventory.licenseYear, priInventory.issueFromDate, priInventory.issueToDate);
		this.setInventoryNumFrom(priInventory.inventoryNumFrom);
		this.setInventoryNumTo(priInventory.inventoryNumTo);
	}

}
