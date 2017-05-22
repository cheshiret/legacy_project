/*
 * $Id: InvMgrServiceActivitiesPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author CGuo
 */
public class InvMgrServiceActivitiesPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrServiceHomePage
	 * Generated     : Jan 3, 2006 3:21:18 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2006/01/03
	 */
	private static InvMgrServiceActivitiesPage _instance = null;

	public static InvMgrServiceActivitiesPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrServiceActivitiesPage();
		}

		return _instance;
	}

	protected InvMgrServiceActivitiesPage() {
	}

	public boolean exists() {
	    Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Search");
		p[2] = new Property(".href", new RegularExpression("\"ServiceAmenityWorker\"", false));
	  	return browser.checkHtmlObjectExists(p);
	}
	
	public void clickEvent() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Events");
	}
	
	public void searchBy(String type) {
	  browser.selectDropdownList(".id", "srchBy", type);
	}
	
	public void setSearchValue(String searchValue) {
	  browser.setTextField(".id", "srchByText", searchValue);
	  
	}
	
	public void clickSearch() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void selectAll() {
	  browser.selectCheckBox(".name", "all_slct");
	}
	
	public void clickAssign() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
	}
	
	public void clickRemove() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	public void selectSearchType(String type){
		browser.selectDropdownList(".id","srchByType", type);
	}
	
	public void selectSearchCategory(String category){
		browser.selectDropdownList(".id", "srchByCatg", category);
	}
	
	public void selectShowStatus(String status){
		browser.selectDropdownList(".id", "showAssigned", status);
	}
	
	public void setSearchFilter(String searchBy, String searchValue, String category, String type, String showStatus){
		if(StringUtil.notEmpty(searchBy)&&StringUtil.notEmpty(searchValue)){
			this.searchBy(searchBy);
			this.setSearchValue(searchValue);
		}
		if(StringUtil.notEmpty(category)){
			this.selectSearchCategory(category);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(type)){
			this.selectSearchType(type);
		}
		if(StringUtil.notEmpty(showStatus)){
			this.selectShowStatus(showStatus);
		}
	}
	
	public void searchServiceOrActivities(String searchBy, String searchValue, String category, String type, String showStatus){
		this.setSearchFilter(searchBy, searchValue, category, type, showStatus);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public String getServiceInfo() {
	  IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("^ID Assigned Category Type Name Icon", false));
	  String service=objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  
	  return service;
	}
	
	/**
	 * Parse the Services, Activities table and return the table details
	 * @return
	 */
	public List<String[]> parseServiceTable() {
		List<String[]> services = new ArrayList<String[]>();
		IHtmlObject serviceTab[] = browser.getTableTestObject(".text", new RegularExpression("^ID Assigned Category Type Name Icon", false));
		
		int rowNum = ((IHtmlTable)serviceTab[0]).rowCount();
		int colNum = ((IHtmlTable)serviceTab[0]).columnCount();
		
		for(int row = 0; row < rowNum; row++) {
			String servicesDetails[] = new String[colNum];
			for(int col = 0; col < colNum; col++) {
				String titleName[] = new String[colNum];
				titleName[col] = ((IHtmlTable)serviceTab[0]).getCellValue(0, col).trim();
				
				if(row >= 1){
					if(titleName[col].length() > 0){
						if(titleName[col].equals("ID")) {
							servicesDetails[col] = ((IHtmlTable)serviceTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Assigned")) {
							servicesDetails[col] = ((IHtmlTable)serviceTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Category")) {
							servicesDetails[col] = ((IHtmlTable)serviceTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Type")) {
							servicesDetails[col] = ((IHtmlTable)serviceTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Name")) {
							servicesDetails[col] = ((IHtmlTable)serviceTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Icon")) {
							servicesDetails[col] = ((IHtmlTable)serviceTab[0]).getCellValue(row, col).toString().trim();
						}
					}
				}
			}
			
			if(!"ID".equalsIgnoreCase(servicesDetails[1]) && null != servicesDetails[1]) {
				services.add(servicesDetails);
			}
		}
		
		return services;
	}
	
	/**
	 * Select specific Services Activities by rowNum
	 * @param rowNum
	 */
	public void selectServicActivitCheckBoxByID(String rowNum){
		browser.clickGuiObject(".id","row_"+(Integer.parseInt(rowNum)-1)+"_checkbox",".type","checkbox");
	}
	
	/**
	 * Select specific Services Activities by rowNums
	 * @param rowNums
	 */
	public void selectServicActivitCheckBoxByIDs(String[] rowNums){
		if(rowNums==null || rowNums.length<1) //do nothing
			return;

		for(int i=0;i<rowNums.length;i++) {
			selectServicActivitCheckBoxByID(rowNums[i]);
		}
	}
	
	/**
	 * If "Next" button is available, click next button in reservation search page.
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	public IHtmlObject getServiceActivetiesTable(){
		RegularExpression res = new RegularExpression("^ID Assigned Category Type Name Icon.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", res);
		IHtmlObject table = objs[0];
		return table;
	}
	
	/**
	 * This case return false when there is no services/activities in the table list, and true if there is any
	 * @return
	 */
	public boolean hasSearchValue(){
		IHtmlTable table = (IHtmlTable)this.getServiceActivetiesTable();
		if(table.rowCount() ==1){
			return false;
		}
		return true;
	}
	
	/**
	 * Get specific Services Activities row Numbers via event names
	 * @param names
	 * @return
	 */
	public String getSpecificServicActivitRowNum(String[] names){
		List<String> eventUINames = new ArrayList<String>();
		int eventRowNum = 0;
		String eventRowNums = "";

		RegularExpression res = new RegularExpression("^ID Assigned Category Type Name Icon.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", res);
		if(objs.length>0){
			do{
				for(int i=0; i<names.length; i++){
					IHtmlTable table = (IHtmlTable)objs[0];
						eventUINames = table.getColumnValues(5);
						for(int j=0; j<eventUINames.size(); j++){
		                    if(eventUINames.get(j).equals(names[i])){
		                    	++eventRowNum;
		                    	eventRowNums = eventRowNums + String.valueOf(j)+"#";
		                    }
						}
				}
			}while(names.length != eventRowNum && gotoNext());
		}else throw new ObjectNotFoundException("Object doesn't find.");
		
		return eventRowNums;
	}
	
    /**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Check all the value of specific String Array aren't null
	 * @param stringArray
	 * @return
	 */
	public boolean CheckStringArrayExistNullValue(String[] stringArray){
		boolean existNull = false;
		
		for(int i=0; i<stringArray.length; i++){
			if(!(null!=stringArray[i] && stringArray[i].length()>0)){
				existNull = true;
			}
		}
		return existNull;
	}
}
