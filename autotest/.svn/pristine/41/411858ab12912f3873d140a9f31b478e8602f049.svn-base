package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrPrivilegeInventoryCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrInventoryTypeLicenseYearPage extends LicMgrPrivilegeInventoryCommonPage {
	
	private static LicMgrInventoryTypeLicenseYearPage _instance = null;
	
	protected LicMgrInventoryTypeLicenseYearPage (){
		
	}
	
	public static LicMgrInventoryTypeLicenseYearPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrInventoryTypeLicenseYearPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", new RegularExpression("Inventory Type (License|Licence) Years", false),".className","selected");
	}
	
	public void clickAddInventoryTypeLicenseYear(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Inventory Type (License|Licence) Year", false));
	}
	
	public IHtmlObject[] getInvTypeLicenseYearTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "hfinventorytypeyeargrid");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Inventory Type License Year Table Object.");
		}
		
		return objs;
	}
	
	/**
	 * This method can only get 'inventory type license year' id identifier by inventory type and its license year must be 'ALL'
	 * @param invType
	 * @return
	 */
	public String getInvTypeLicenseYearID(String invType) {
		IHtmlObject[] objs = this.getInvTypeLicenseYearTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
//		int columnIdex = table.findColumn(0, "License Year");
//		int rowIndex = table.findRow(columnIdex, "ALL");
		int columnIndex = table.findColumn(0, "Inventory Type");
		int rowIndex = table.findRow(columnIndex, invType);
		String id = table.getCellValue(rowIndex, 0);
		
		Browser.unregister(objs);
		return id;
	}
	
	public String getInvTypeLicenseYearID(InventoryTypeLicenseYear invTypeLicYear){
		String id = "";
		IHtmlObject[] objs = this.getInvTypeLicenseYearTableObject();
		IHtmlTable invTypeLicYearTab = (IHtmlTable)objs[0];
		
		int rowCount = invTypeLicYearTab.rowCount();
		
		if(invTypeLicYear.issueFromDate.trim().length()>0){
			invTypeLicYear.issueFromDate = DateFunctions.formatDate(invTypeLicYear.issueFromDate, "EEE MMM d yyyy");
		}
		if(invTypeLicYear.issueToDate.trim().length()>0){
			invTypeLicYear.issueToDate = DateFunctions.formatDate(invTypeLicYear.issueToDate, "EEE MMM d yyyy");
		}
		String invType, licYear, issueFrom, issueTo = "";
		for(int i=1; i<rowCount; i++){
			invType = invTypeLicYearTab.getCellValue(i, 1);
			licYear = invTypeLicYearTab.getCellValue(i, 2);
			issueFrom = invTypeLicYearTab.getCellValue(i, 3);
			issueTo = invTypeLicYearTab.getCellValue(i, 4);
			
			if(invType.equals(invTypeLicYear.inventoryType) && licYear.equals(invTypeLicYear.licenseYear)
					&& issueFrom.equals(invTypeLicYear.issueFromDate.trim())
					&& issueTo.equals(invTypeLicYear.issueToDate.trim())) {
				id = invTypeLicYearTab.getCellValue(i, 0);
				break;
			}
		}
		
		Browser.unregister(objs);
		
		return id;
	}
	
	public InventoryTypeLicenseYear getInvTypeLicenseYearInfoByID(String id){
		InventoryTypeLicenseYear invTypeLicYearInfo = new InventoryTypeLicenseYear();
		IHtmlObject[] objs = this.getInvTypeLicenseYearTableObject();
		IHtmlTable invTypeLicYearTab = (IHtmlTable)objs[0];
		int row = invTypeLicYearTab.findRow(0, id);
		
		invTypeLicYearInfo.id = invTypeLicYearTab.getCellValue(row, 0);
		invTypeLicYearInfo.inventoryType = invTypeLicYearTab.getCellValue(row,1);
		invTypeLicYearInfo.licenseYear =  invTypeLicYearTab.getCellValue(row,2);
		invTypeLicYearInfo.issueFromDate = invTypeLicYearTab.getCellValue(row,3);
		invTypeLicYearInfo.issueToDate =  invTypeLicYearTab.getCellValue(row,4);
		invTypeLicYearInfo.cost = invTypeLicYearTab.getCellValue(row,5);
		invTypeLicYearInfo.totalInventory =  invTypeLicYearTab.getCellValue(row,6);
		invTypeLicYearInfo.allocatedInventory =   invTypeLicYearTab.getCellValue(row,7);
		invTypeLicYearInfo.soldInventory =   invTypeLicYearTab.getCellValue(row,8);
		
		Browser.unregister(objs);
		return invTypeLicYearInfo;	
	}
	
	public void clickInvTypeLicenseYearID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text",id);
	}
	
	public IHtmlObject[] getInvTypeLicenseYearRowObject(String invTypeLicenseYearID){
		List<Property[]> prpList = new ArrayList<Property[]>();
		Property[] tablePrp = new Property[2];
		tablePrp[0] = new Property(".class", "Html.TABLE");
		tablePrp[1] = new Property(".id", new RegularExpression("hfinventorytypeyeargrid",false));
		
		Property[] rowPrp = new Property[2];
		rowPrp[0] = new Property(".class", "Html.TR");
		rowPrp[1] = new Property(".text", new RegularExpression("^" +invTypeLicenseYearID + ".*",false));
		
		prpList.add(tablePrp);
		prpList.add(rowPrp);
			
		IHtmlObject[] rowObjs = browser.getHtmlObject(prpList);
		
		return rowObjs;
	}
	
	public void clickChangeHistory(String invTypeLicenseYearID){		
		IHtmlObject[] invTypeLicYearRowObjs = this.getInvTypeLicenseYearRowObject(invTypeLicenseYearID);	
		if(invTypeLicYearRowObjs.length<1){
			throw new ObjectNotFoundException("Did not found inventory type license year ID row object." +
					" inventory type license year = " + invTypeLicenseYearID);
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "Change History", true, 0, invTypeLicYearRowObjs[0]);		
		Browser.unregister(invTypeLicYearRowObjs);		
	}
	
	public void clickViewInventory(String invTypeLicenseYearID){
		IHtmlObject[] invTypeLicYearRowObjs = this.getInvTypeLicenseYearRowObject(invTypeLicenseYearID);
		if(invTypeLicYearRowObjs.length<1){
			throw new ObjectNotFoundException("Did not found inventory type license year ID row object." +
					" inventory type license year = " + invTypeLicenseYearID);
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "View Inventory", true, 0, invTypeLicYearRowObjs[0]);		
		Browser.unregister(invTypeLicYearRowObjs);	
	}
	
	public boolean verifyInvTypeLicenseYearInfo(InventoryTypeLicenseYear expectInvTypeLicYearInfo){
		boolean result = true;
		expectInvTypeLicYearInfo.id = this.getInvTypeLicenseYearID(expectInvTypeLicYearInfo);
		InventoryTypeLicenseYear actulInvTypeLicYearInfo = this.getInvTypeLicenseYearInfoByID(expectInvTypeLicYearInfo.id);
		
		if(!actulInvTypeLicYearInfo.id.equals(expectInvTypeLicYearInfo.id )){
			result &= false;
			MiscFunctions.compareResult("ID", expectInvTypeLicYearInfo.id, actulInvTypeLicYearInfo.id);
		}else {
			logger.info("ID is correct");
		}
		
		if(!actulInvTypeLicYearInfo.inventoryType.equals(expectInvTypeLicYearInfo.inventoryType)){
			result &= false;
			MiscFunctions.compareResult("Inventory type", expectInvTypeLicYearInfo.inventoryType, actulInvTypeLicYearInfo.inventoryType);
		}else {
			logger.info("Inventory type is correct");
		}
		
		if(!actulInvTypeLicYearInfo.licenseYear.equals(expectInvTypeLicYearInfo.licenseYear)){
			result &= false;
			MiscFunctions.compareResult("License year",expectInvTypeLicYearInfo.licenseYear, actulInvTypeLicYearInfo.licenseYear);
		}else {
			logger.info("License year is correct");
		}
		
		if(null != expectInvTypeLicYearInfo.issueFromDate && expectInvTypeLicYearInfo.issueFromDate.length()>0){
			expectInvTypeLicYearInfo.issueFromDate = DateFunctions.formatDate(expectInvTypeLicYearInfo.issueFromDate, "EEE MMM d yyyy");
		}
		result &= MiscFunctions.compareResult("Issue From Date",expectInvTypeLicYearInfo.issueFromDate, actulInvTypeLicYearInfo.issueFromDate);
//		if(DateFunctions.compareDates(actulInvTypeLicYearInfo.issueFromDate, expectInvTypeLicYearInfo.issueFromDate) != 0) {
//			result &= false;
//			MiscFunctions.compareResult("Issue From Date",expectInvTypeLicYearInfo.issueFromDate, actulInvTypeLicYearInfo.issueFromDate);
//		}else {
//			logger.info("Issue From Date is correct.");
//		}
		
		if(null != expectInvTypeLicYearInfo.issueToDate && expectInvTypeLicYearInfo.issueToDate.length()>0){
			expectInvTypeLicYearInfo.issueToDate = DateFunctions.formatDate(expectInvTypeLicYearInfo.issueToDate, "EEE MMM d yyyy");
		}
		result &= MiscFunctions.compareResult("Issue To Date",expectInvTypeLicYearInfo.issueToDate, actulInvTypeLicYearInfo.issueToDate);
		
//		if(DateFunctions.compareDates(actulInvTypeLicYearInfo.issueToDate, expectInvTypeLicYearInfo.issueToDate) != 0) {
//			result &= false;
//			MiscFunctions.compareResult("Issue To Date",expectInvTypeLicYearInfo.issueToDate, actulInvTypeLicYearInfo.issueToDate);
//		}else {
//			logger.info("Issue To Date is correct.");
//		}
		
		if(!actulInvTypeLicYearInfo.cost.contains(expectInvTypeLicYearInfo.cost)){
			result &= false;
			MiscFunctions.compareResult("Cost",expectInvTypeLicYearInfo.cost, actulInvTypeLicYearInfo.cost);
		}else {
			logger.info("Cost is correct.");
		}
		
		if(null ==expectInvTypeLicYearInfo.totalInventory || expectInvTypeLicYearInfo.totalInventory.trim().length()<1){
			expectInvTypeLicYearInfo.totalInventory = "0";
		}
		if(!actulInvTypeLicYearInfo.totalInventory.equals(expectInvTypeLicYearInfo.totalInventory)){
			result &= false;
			MiscFunctions.compareResult("Total Inventory",expectInvTypeLicYearInfo.totalInventory, actulInvTypeLicYearInfo.totalInventory);
		}else {
			logger.info("Total Inventory is correct.");
		}
		
		if(null ==expectInvTypeLicYearInfo.allocatedInventory || expectInvTypeLicYearInfo.allocatedInventory.trim().length()<1){
			expectInvTypeLicYearInfo.allocatedInventory = "0";
		}
		if(!actulInvTypeLicYearInfo.allocatedInventory.equals(expectInvTypeLicYearInfo.allocatedInventory)){
			result &= false;
			MiscFunctions.compareResult("Allocated Inventory",expectInvTypeLicYearInfo.allocatedInventory, actulInvTypeLicYearInfo.allocatedInventory);
		}else {
			logger.info("Allocated Inventory is correct.");
		}
		
		if(null ==expectInvTypeLicYearInfo.soldInventory || expectInvTypeLicYearInfo.soldInventory.trim().length()<1){
			expectInvTypeLicYearInfo.soldInventory = "0";
		}
		if(!actulInvTypeLicYearInfo.soldInventory.equals(expectInvTypeLicYearInfo.soldInventory)){
			result &= false;
			MiscFunctions.compareResult("Sold Inventory",expectInvTypeLicYearInfo.soldInventory, actulInvTypeLicYearInfo.soldInventory);
		}else {
			logger.info("Sold Inventory is correct.");
		}
		
		return result;		
	}
}
