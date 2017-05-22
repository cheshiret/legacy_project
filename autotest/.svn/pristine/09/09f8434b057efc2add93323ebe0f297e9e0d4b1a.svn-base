package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrEditInvTypeLicenseYearWidget extends DialogWidget{
	
	private static LicMgrEditInvTypeLicenseYearWidget _instance = null;
	
	protected LicMgrEditInvTypeLicenseYearWidget(){
		
	}
	
	public static LicMgrEditInvTypeLicenseYearWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrEditInvTypeLicenseYearWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", "Edit Inventory Type License Year");
	}
	
	public String getInventoryType(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.inventoryType",false));
	}
	
	public String getLicenseYear(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.year",false));
	}
	
	public boolean checkIssueFromDateExists(){
		return browser.checkHtmlObjectExists(".id",
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueFromDate_ForDisplay",false));
	}
	
	public boolean checkIssueToDateExists(){
		return browser.checkHtmlObjectExists(".id",
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueToDate_ForDisplay",false));
	}
	
	public String getIssueFromDate(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueFromDate_ForDisplay",false));
	}
	
	public String getIssueToDate(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.issueToDate_ForDisplay",false));
	}
	
	public String getCost(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^HFInventoryTypeYearView-\\d+\\.cost:ZERO_TO_NULL",false));
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
	
	public void setInvTypeLicenseYearInfo(InventoryTypeLicenseYear expInvTypeLicYearInfo){
		if(this.checkIssueFromDateExists()){
			this.setIssueFromDate(expInvTypeLicYearInfo.issueFromDate);
		}
		
		if(this.checkIssueToDateExists()){
			this.setIssueToDate(expInvTypeLicYearInfo.issueToDate);
		}
		
		this.setCost(expInvTypeLicYearInfo.cost);
	}
	
	public boolean verifyInventoryTypeLicenseYearInfo(InventoryTypeLicenseYear expInvTypeLicYearInfo){
		boolean result = true;
		String value = "";
		
		value = this.getInventoryType();
//		if(!value.equals(expInvTypeLicYearInfo.inventoryType)){
//			result &= false;
			result &= MiscFunctions.compareResult("Inventory type", expInvTypeLicYearInfo.inventoryType, value);
//		}else {
//			logger.info("Inventory type is correct.");
//		}
		
		value = this.getLicenseYear();
//		if(!value.equals(expInvTypeLicYearInfo.licenseYear)){
//			result &= false;
			result &= MiscFunctions.compareResult("License Year", expInvTypeLicYearInfo.licenseYear, value);
//		}else {
//			logger.info("License year is correct.");
//		}
		
		if(this.checkIssueFromDateExists()){
			value = this.getIssueFromDate();
//			if(!value.equals(expInvTypeLicYearInfo.issueFromDate)){
//				result &= false;
				result &= MiscFunctions.compareResult("Issue From Date", expInvTypeLicYearInfo.issueFromDate, value);
//			}else {
//				logger.info("Issue from date is correct.");
//			}
		}
		
		if(this.checkIssueToDateExists()){
			value = this.getIssueToDate();
//			if(!value.equals(expInvTypeLicYearInfo.issueToDate)){
//				result &= false;
				result &= MiscFunctions.compareResult("Issue To Date", expInvTypeLicYearInfo.issueToDate, value);
//			}else {
//				logger.info("Issue to date is correct.");
//			}
		}
		
		value = this.getCost();
		if(!value.contains(expInvTypeLicYearInfo.cost)){
			result &= false;
//			MiscFunctions.compareResult("Cost is not correct.", expInvTypeLicYearInfo.cost, value);
			logger.error("Cost is not correct, expected value is: " + expInvTypeLicYearInfo.cost + ", but actual is: " + value);
		}else {
			logger.info("Cost is correct.");
		}
		
		return result;
	}

}
