package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAllocatePrivilegeInventoryWidget extends DialogWidget{
	private static LicMgrAllocatePrivilegeInventoryWidget _instance = null;
	
	protected LicMgrAllocatePrivilegeInventoryWidget(){
		
	}
	
	public static LicMgrAllocatePrivilegeInventoryWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAllocatePrivilegeInventoryWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", new RegularExpression("Allocate (Privilege|Licence) Inventory", false));
	}
	
	public void selectInventoryType(String inventoryType, String invTypeStatus){		
		if(null != inventoryType && inventoryType.trim().length()>0){
			String inventoryTypeInfo = inventoryType + " ("+ invTypeStatus + ")";
			browser.selectDropdownList(".id", 
					new RegularExpression("^HFInventoryCreationUIModel-\\d+.inventoryType",false), inventoryTypeInfo);
		}else {
			browser.selectDropdownList(".id", 
					new RegularExpression("^HFInventoryCreationUIModel-\\d+.inventoryType",false), 0);
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
					licenseYearInfo = licenseYearInfo + ")";
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
	
	public void selectVendor(String vendorNum, String vendorName){
		if(null !=vendorNum && vendorNum.trim().length()>0){
			String vendorInfo = vendorNum + " - " + vendorName;
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryAllocationUIModel-\\d+\\.selectedVendor",false), vendorInfo);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryAllocationUIModel-\\d+\\.selectedVendor",false), 0);
		}		
	}
	
	public void selectAgent(String agentID, String agentName){
		if(null !=agentID && agentID.trim().length()>0){
			String agentInfo = agentID + " - " + agentName;
			System.out.println("7 - WAL-MART");
			System.out.println(agentInfo);
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryAllocationUIModel-\\d+\\.selectedStore",false), agentInfo);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryAllocationUIModel-\\d+\\.selectedStore",false), 0);
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
	
	public void setPrivilegeInventoryAllocationInfo(PrivilegeInventoryAllocation priInvAllocation){
		this.selectInventoryType(priInvAllocation.inventoryType, priInvAllocation.inventoryTypeStatus);
		ajax.waitLoading();
		this.selectLicenseYear(priInvAllocation.licenseYear, priInvAllocation.issueFromDate, priInvAllocation.issueToDate);
		ajax.waitLoading();
		this.selectVendor(priInvAllocation.vendorNum, priInvAllocation.vendorName);
		ajax.waitLoading();
		this.selectAgent(priInvAllocation.agentID, priInvAllocation.agentName);
		this.setInventoryNumFrom(priInvAllocation.inventoryNumFrom);
		this.setInventoryNumTo(priInvAllocation.inventoryNumTo);
	}
	
}
