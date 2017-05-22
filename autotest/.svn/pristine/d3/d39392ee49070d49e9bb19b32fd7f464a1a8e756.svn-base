/*
 * Created on Jan 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrNonSiteSpecGroupPage extends InventoryManagerPage {

	private static InvMgrNonSiteSpecGroupPage _instance = null;

	public static InvMgrNonSiteSpecGroupPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrNonSiteSpecGroupPage();
		}

		return _instance;
	}

	protected InvMgrNonSiteSpecGroupPage() {

	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text","Add New Group");
	}
	
	/**
	 * Select search for.
	 * @param criteria - search for
	 */
	public void selectSearchFor(String criteria) {
		browser.selectDropdownList(".id", "search_type", criteria);
	}

	/**
	 * Fill in search value.
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", "search_value", value);
	}

	/**
	 * Search site by given search type and value
	 * @param criteria - search type
	 * @param value - search value
	 */
	public void searchSite(String criteria, String value) {
		this.selectSearchFor(criteria);
		this.setSearchValue(value);
		this.clickSearch();
		waitLoading();
	}
	
	/** Click on Search link. */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**click sites tab*/
	public void clickSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sites");
	}
	
	/**click Loop/Areas tab*/
	public void clickLoopsAreas() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Loops/Areas");
	}
	
	/**
	 * Select site by site ID
	 * @param siteID
	 * @throws PageNotFoundException
	 */
	public void selectSiteBySiteID(String siteID) throws PageNotFoundException{
	  browser.clickGuiObject(".class","Html.A",".text",siteID);
	}
	
	/**Click Add new group*/
	public void clickAddNewGroup(){
	  browser.clickGuiObject(".class","Html.A",".text","Add New Group");
	}
	
	/**Click Activate button*/
	public void clickActivate(){
	  browser.clickGuiObject(".class","Html.A",".text","Activate");
	}
	
	/**Click Deactivate button*/
	public void clickDeactivate(){
	  browser.clickGuiObject(".class","Html.A",".text","Deactivate");
	}
	
	/**
	 * Select Non Site Specific groups by site ID
	 * @param siteID
	 */
	public void selectNonSiteSpecificGroupsCheckBoxBySiteID(String siteID){
		if(!StringUtil.isEmpty(siteID)){
			 browser.selectCheckBox(".value",siteID);
		}
	}
	
	public void selectAllNssGroup(){
		  browser.clickGuiObject(".name","all_slct",".type","checkbox");
		}
	
	/**
	 * Select Non Site Specific groups by site IDs
	 * @param siteIDs
	 */
	public void selectNonSiteSpecificGroupsCheckBoxBySiteIDs(String[] siteIDs){
	  	if(siteIDs==null || siteIDs.length<1) //do nothing
	  	  	return;
	  
	  	for(int i=0;i<siteIDs.length;i++) {
	  		selectNonSiteSpecificGroupsCheckBoxBySiteID(siteIDs[i]);
	  	}
	}
	
	/**
	 * Active one or more sites by site IDs
	 * @param siteIDs
	 */
	public void activeSite(String... siteIDs){
		this.selectNonSiteSpecificGroupsCheckBoxBySiteIDs(siteIDs);
		this.clickActivate();
	}
	
	/**
	 * Deactive one or more sites by site IDs
	 * @param siteIDs
	 */
	public void deactiveNotesOrAlerts(String... siteIDs){
		this.selectNonSiteSpecificGroupsCheckBoxBySiteIDs(siteIDs);
		this.clickDeactivate();
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
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
     * Click site ID 
     * @param siteID
     */
	public void clickSiteID(String siteID){
		RegularExpression rex = new RegularExpression("\"InvMgrSiteDetails.do\".+\"" + siteID + "\"", false);
  	  	browser.clickGuiObject(".class", "Html.A", ".href", rex);
  	}
	
	/**
	 * Get site id
	 * @param siteCode
	 * @return
	 */
	public String getSiteID(String siteCode){
		return getColumnValueInSiteListPage(siteCode, "Site ID");
	}
	
	public String getSiteStatus(String siteCode){
		return getColumnValueInSiteListPage(siteCode, "Active");
	}
	
	private String getColumnValueInSiteListPage(String siteCode, String attributeName){
		String value = "";
		RegularExpression res = new RegularExpression("^Site ID Site Code Site Name Active Site Type Parent Loop/Area.*",false);
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", res);
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[0];
			//Get attribute name column number
			int columnForCode = table.findColumn(0, "Site Code");
			int column = table.findColumn(0, attributeName);
			if(table.rowCount()>0){
				for(int i=1; i<table.rowCount(); i++){
					if(table.getColumnValues(columnForCode).get(i).contains(siteCode)){
						value = table.getCellValue(i, column);
						break;
					}
				}
			}else throw new ErrorOnDataException("No site info be found...");
		}else throw new ObjectNotFoundException("Site list object can't find...");
		
		return value;
	}
	public void activeAllNssGroup(){
		this.selectAllNssGroup();
		this.clickActivate();
		this.waitLoading();
	}
	
  	public IHtmlObject[] getNSSGroupTable(){
  		return browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Site ID(| )Site Code(| )Site.*", false));
  	}
	
	public String getSiteInfo(String siteCode, String location){
		String returnString = "";
		int row = 0;

		IHtmlObject[] objs = this.getNSSGroupTable();
		IHtmlTable table = (IHtmlTable)objs[0];

		//Get First site info
		if(location.equalsIgnoreCase("First")){
			row = 1;
		}else if(location.equalsIgnoreCase("Last")){
			row = table.rowCount()-1;
		}else {
			for(int i=1; i<table.rowCount(); i++){
				if(table.getCellValue(i, 2).equals(siteCode)){
					if(location.equalsIgnoreCase("Previous")){
						row = i-1;
					}else if(location.equalsIgnoreCase("Next")){
						row = i+1;
					}else throw new ErrorOnDataException("Please enter the site's location.");
				}
			}
		}

		returnString = "Site Site ID "+table.getCellValue(row,1)+
		" Site Code "+table.getCellValue(row,2)+
		" Site Name " +table.getCellValue(row,3)+
		" Parent Loop "+table.getCellValue(row,6)+
		" Site Type "+table.getCellValue(row,5);

		Browser.unregister(objs);
		return returnString;
	}
}
