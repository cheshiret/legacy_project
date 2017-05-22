package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrPrivilegeInventoryCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeInventoryPage extends LicMgrPrivilegeInventoryCommonPage {
	
	private static LicMgrPrivilegeInventoryPage _instance = null;
	
	protected LicMgrPrivilegeInventoryPage(){
		
	}
	
	public static LicMgrPrivilegeInventoryPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrPrivilegeInventoryPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", new RegularExpression("(Privilege|Licence) Inventory", false),".className","selected");
	}
	
	public void clickCreateInventory(){
		browser.clickGuiObject(".class", "Html.A",".text","Create Inventory");
	}
	
	public void clickAllocatePrivilegeInventory(){
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("Allocate (Privilege|Licence) Inventory", false));
	}
	
	public void clickWithdrawInventory(){
		browser.clickGuiObject(".class", "Html.A",".text","Withdraw Inventory");
	}
	
	public void clickReinstateInventory(){
		browser.clickGuiObject(".class", "Html.A",".text","Reinstate Inventory");
	}
	
	public void clickMarkInventoryAsReturned(){
		browser.clickGuiObject(".class", "Html.A",".text","Mark Inventory as Returned");
	}
	
	public void clickInventoryNumber(String inventoryNum){
		browser.clickGuiObject(".class", "Html.A",".text",inventoryNum);
	}
	
	public void selectInventoryType(String inventoryType, String invTypeStatus){
		if(null != inventoryType && inventoryType.trim().length()>0){
			String inventoryTypeInfo = inventoryType + " ("+ invTypeStatus + ")";
			browser.selectDropdownList(".id", 
					new RegularExpression("^HFInventoryUISearchCriteria-\\d+\\.inventoryType",false), inventoryTypeInfo);
		}else {
			browser.selectDropdownList(".id", 
					new RegularExpression("^HFInventoryUISearchCriteria-\\d+\\.inventoryType",false), 0);
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
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.inventoryTypeYear",false), licenseYearInfo);
		}else {
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.inventoryTypeYear",false), 0);
		}		
	}
	
	public void selectInventoryStatus(String inventoryStatus){
		if(null != inventoryStatus && inventoryStatus.trim().length()>0){
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.status",false), inventoryStatus);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.status",false), 0);
		}		
	}
	
	public void selectAllocationStatus(String allocationStatus){
		if(null != allocationStatus && allocationStatus.trim().length()>0){
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.allocationStatus",false), allocationStatus);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.allocationStatus",false), 0);
		}	
	}
	
	public void selectAgentInfo(String agentID, String agentName){
		if(null != agentID && agentID.trim().length()>0){
			String agentInfo = agentID + " - " + agentName;
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.store",false), agentInfo);
		}else{
			browser.selectDropdownList(".id", 
					new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.store",false), 0);
		}	
	}
	
	public void setInventoryNumFrom(String inventoryNumFrom){
		browser.setTextField(".id", 
				new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.lowestInventoryNumber",false), inventoryNumFrom, true);
	}
	
	public void setInventoryNumTo(String inventoryNumTo){
		browser.setTextField(".id", 
				new RegularExpression("HFInventoryUISearchCriteria-\\d+\\.highestInventoryNumber",false), inventoryNumTo, true);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Go");
	}
	
	public void setSearchCriteria(PrivilegeInventory priInventory){
		this.selectInventoryType(priInventory.inventoryType, priInventory.inventoryTypeStatus);
		ajax.waitLoading();
		this.selectLicenseYear(priInventory.licenseYear, priInventory.issueFromDate, priInventory.issueToDate);	
		ajax.waitLoading();
		this.selectInventoryStatus(priInventory.inventoryStatus);
		this.selectAllocationStatus(priInventory.allocationStatus);
		this.selectAgentInfo(priInventory.agentID, priInventory.agentName);
		ajax.waitLoading();
		this.setInventoryNumFrom(priInventory.inventoryNumFrom);
		this.setInventoryNumTo(priInventory.inventoryNumTo);
	}
	
	public PriInventoryItemInfo getPrivilegeInventoryInfo(String inventoryNum){
		PriInventoryItemInfo inventory = new PriInventoryItemInfo();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "inventoryList_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found inventory list table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, inventoryNum);
		inventory.inventoryNum = table.getCellValue(row, 1);
		inventory.status = table.getCellValue(row, 2);
		inventory.agentInfo = table.getCellValue(row, 3);
		inventory.order = table.getCellValue(row, 4);
		inventory.privielgeNumber = table.getCellValue(row, 5);
		
		Browser.unregister(objs);
		return inventory;
	}
	
	public int getInventoryItemRow(String inventoryNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "inventoryList_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found inventory list table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, inventoryNum);
		Browser.unregister(objs);
		return row;
	}
	
	public void selectPriInventoryItemCheckBox(String inventoryNum){
		int index = this.getInventoryItemRow(inventoryNum);
		browser.selectCheckBox(".id", new RegularExpression("HFInventoryUIView-\\d+\\.selected",false), index-1, true);
	}
	
	public void selectFirstPriInventoryItemCheckBox() {
		browser.selectCheckBox(".id", new RegularExpression("HFInventoryUIView-\\d+\\.selected",false), 0, true);
	}
	
	public void clickReallocateInventory(){
		browser.clickGuiObject(".class", "Html.A",".text","Reallocate Inventory");
	}
	
	public int getPriInventoryItemRecordsCount(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "inventoryList_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found inventory list table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int count = table.rowCount()-1; 
		Browser.unregister(objs);
		return count;
	}
	
	public boolean verifyPrivilegeInventoryInfo(PriInventoryItemInfo expInventory){		
		PriInventoryItemInfo actInventory = new PriInventoryItemInfo();
		boolean result = true;
		
		actInventory = this.getPrivilegeInventoryInfo(expInventory.inventoryNum);
		if(!expInventory.status.equals(actInventory.status)){
			result &= false;
			MiscFunctions.compareResult("Inventory Status is not correct.", expInventory.status, actInventory.status);
		}else {
			logger.info("Inventory status is correct.");
		}
		
		if(!expInventory.agentInfo.equals(actInventory.agentInfo)){
			result &= false;
			MiscFunctions.compareResult("Agent is not correct.",expInventory.agentInfo,actInventory.agentInfo);
		}else {
			logger.info("Agent is correct.");
		}
		
		if(!expInventory.order.equals(actInventory.order)){
			result &= false;
			MiscFunctions.compareResult("Order info is not correct.",expInventory.order,actInventory.order);
		}else {
			logger.info("Order info is correct.");
		}
		
		if(!expInventory.privielgeNumber.equals(actInventory.privielgeNumber)){
			result &= false;
			MiscFunctions.compareResult("Privilege number is not correct.",expInventory.privielgeNumber,actInventory.privielgeNumber);
		}else {
			logger.info("Privilege number is correct.");
		}
		
		return result;
	}
	
	

}
