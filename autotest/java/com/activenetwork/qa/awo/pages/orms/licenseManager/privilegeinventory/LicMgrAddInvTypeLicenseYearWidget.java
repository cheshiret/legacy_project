package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddInvTypeLicenseYearWidget extends DialogWidget{
	private static LicMgrAddInvTypeLicenseYearWidget _instance = null;
	
	protected LicMgrAddInvTypeLicenseYearWidget(){
		
	}
	
	public static LicMgrAddInvTypeLicenseYearWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddInvTypeLicenseYearWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", new RegularExpression("Add Inventory Type (License|Licence) Year", false));
	}
	
	public void selectInventoryType(String invType){
		browser.selectDropdownList(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.inventoryType",false), invType, true);
	}
	
	public void selectLicenseYear(String licenseYear){
		browser.selectDropdownList(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.year",false), licenseYear, true);
	}
	
	public void setIssueFromDate(String issueFrom){
		browser.setTextField(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueFromDate_ForDisplay",false), issueFrom, true);
	}
	
	public void setIssueToDate(String issueTo){
		browser.setTextField(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueToDate_ForDisplay",false), issueTo, true);
	}
	
	public void setCost(String cost){
		browser.setTextField(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.cost:ZERO_TO_NULL",false), cost, true);
	}
	
	public boolean checkIssueFromDateExists(){
		return browser.checkHtmlObjectExists(".id",
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueFromDate_ForDisplay",false));
	}
	
	public boolean checkIssueToDateExists(){
		return browser.checkHtmlObjectExists(".id",
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueToDate_ForDisplay",false));
	}
	
	public void setInvTypeLicenseYearInfo(InventoryTypeLicenseYear invTypeLicenseYear){
		this.selectInventoryType(invTypeLicenseYear.inventoryType);
		this.selectLicenseYear(invTypeLicenseYear.licenseYear);
		ajax.waitLoading();
		if(this.checkIssueFromDateExists()){
			this.setIssueFromDate(invTypeLicenseYear.issueFromDate);
		}	
		if(this.checkIssueToDateExists()){
			this.setIssueToDate(invTypeLicenseYear.issueToDate);
		}
		this.setCost(invTypeLicenseYear.cost);		
	}

}
