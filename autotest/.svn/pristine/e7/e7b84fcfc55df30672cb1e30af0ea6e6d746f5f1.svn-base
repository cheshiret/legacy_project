package com.activenetwork.qa.awo.pages.orms.adminManager.audit;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdminManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class AdminMgrAuditLogsPage extends AdminManagerPage{
//
//	private static AdminMgrAuditLogsPage _instance = null;
//
//	public static AdminMgrAuditLogsPage getInstance(){
//		if (null == _instance) {
//			_instance = new AdminMgrAuditLogsPage();
//		}
//
//		return _instance;
//	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("application", false));
	}

	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	public void clickAuditLogTab(String tabName){
		browser.clickGuiObject(".class", "Html.A", ".text", tabName);
	}
	
	public void setSearchType(String searchType){
		browser.selectDropdownList(".id", "AuditLogSearchCriteria.searchBy", searchType);
	}

	public void setSearchValue(String searchValue){
		browser.setTextField(".id", "AuditLogSearchCriteria.searchByValue", searchValue);
	}

	public void setStartDate(String stratDate){
		browser.setTextField(".id", "AuditLogSearchCriteria.startDate_ForDisplay", stratDate);
	}

	public void setEndDate(String endDate){
		browser.setTextField(".id", "AuditLogSearchCriteria.endDate_ForDisplay", endDate);
	}
	
	public void clickInventoryAuditLogsTab() {
		clickAuditLogTab("Inventory Audit Logs");
	}
	
	public void clickCustomerAuditLogsTab() {
		clickAuditLogTab("Customer Audit Logs");
	}
	
	public void clickFeeAuditLogsTab() {
		clickAuditLogTab("Fee Audit Logs");
	}
}
