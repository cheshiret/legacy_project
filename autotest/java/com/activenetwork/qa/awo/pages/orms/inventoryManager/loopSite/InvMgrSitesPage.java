/*
 * $Id: InvMgrSitesPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
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
 * @author CGuo
 */
public class InvMgrSitesPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrSitesPage
	 * Generated     : Feb 9, 2005 6:54:50 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2005/02/09
	 */

	private static InvMgrSitesPage _instance = null;

	public static InvMgrSitesPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrSitesPage();
		}

		return _instance;
	}

	protected InvMgrSitesPage() {

	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",
				"Add New Site");
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
	
	public void selectParentLoc(String parLoc){
		browser.selectDropdownList(".id", "parentLocation", parLoc);
	}
	
	public void searchByParentLoc(String parLoc){
		this.selectParentLoc(parLoc);
		this.setSearchValue(StringUtil.EMPTY);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
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
		ajax.waitLoading();
		this.waitLoading();
	}

	/**
	 * Select to site's detail page by given site code.
	 * @param siteCode
	 * @throws PageNotFoundException
	 */
	public void selectSite(String siteCode) throws PageNotFoundException {
		browser.clickGuiObject(".class", "Html.A", ",text", siteCode);
	}
	
	/**
	 * Click site ID 
	 * @param siteID
	 * @throws PageNotFoundException
	 */
	public void clickSiteID(String siteID) throws PageNotFoundException{
	  browser.clickGuiObject(".class","Html.A",".text",siteID);
	}
	
	/**
	 * Select specific site by site ID
	 * @param siteID
	 */
	public void selectSiteCheckBoxBySiteID(String siteID){
	  browser.clickGuiObject(".value",siteID,".type","checkbox");
	}
	
	public void selectSiteCheckBox(){
		browser.selectCheckBox(".id", "row_0_checkbox");
	}
	
	public void selectAllSitesCheckBox(){
		browser.selectCheckBox(".id", "all_slct");
	}
	
	/**
	 * Select specific site by site IDs
	 * @param siteIDs
	 */
	public void selectSiteCheckBoxBySiteIDs(String[] siteIDs){
	  	if(siteIDs==null || siteIDs.length<1) //do nothing
	  	  	return;
	  
	  	for(int i=0;i<siteIDs.length;i++) {
	  		selectSiteCheckBoxBySiteID(siteIDs[i]);
	  	}
	}

	/** Click on Add New Site link. */
	public void clickAddNewSite() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Site");
	}

	/** Click on Search link. */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**Click Site Tab*/
	public void clickSiteTab(){
	  browser.clickGuiObject(".className","tabanchor",".text","Sites");
	}
	
	/**click NonSpecificSite Tab*/
	public void clickNonSpecificSiteTab(){
//	  browser.clickGuiObject(".className","tabanchor",".text","Non-Site-Specific Groups");
	  browser.clickGuiObject(".class","Html.A",".text","Non-Site-Specific Groups");
	}
	
	/**Click Activate button*/
	public void clickActivate(){
	  browser.clickGuiObject(".class","Html.A",".text","Activate");
	}
	
	/**Click Deactivate button*/
	public void clickDeactivate(){
	  browser.clickGuiObject(".class","Html.A",".text","Deactivate");
	}
	
	/**Get warning message*/
	public String getWarningMessage() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV", ".id", "com.reserveamerica.common.ValidationEx");
		String warningMes = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);
		return warningMes;
	}
	
	/**
	 * Active one or more sites by site IDs
	 * @param siteIDs
	 */
	public void activeSite(String... siteIDs){
		if(siteIDs.length < 2&&siteIDs.length >0){
			this.searchSite("Site ID", siteIDs[0]);
		}
		this.selectSiteCheckBoxBySiteIDs(siteIDs);
		this.clickActivate();
	}
	
	public void activeAllSites(){
		this.selectAllSitesCheckBox();
		this.clickActivate();
	}
	
	/**
	 * Deactive one or more sites by site IDs
	 * @param siteIDs
	 */
	public void deactiveNotesOrAlerts(String... siteIDs){
		if(siteIDs.length < 2&&siteIDs.length >0){
			this.searchSite("Site ID", siteIDs[0]);
		}
		this.selectSiteCheckBoxBySiteIDs(siteIDs);
		this.clickDeactivate();
		ajax.waitLoading();
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**click Loop/Areas tab*/
	public void clickLoopsAreas() {
		browser.clickGuiObject(".className", "tabanchor", ".text", "Loops/Areas");
	}
	
	private String getColumnValueInSiteListPage(String siteCode, String attributeName){
		String siteAttributeValue = "";

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
						siteAttributeValue = table.getCellValue(i, column);
						break;
					}
				}
			}else throw new ErrorOnDataException("No site info be found...");
		}else throw new ObjectNotFoundException("Site list object can't find...");
		
		return siteAttributeValue;
	}
	
	private boolean isColumnValueExistingInSiteListPage(String siteCode, String attributeName){
		boolean isExisting = false;

		RegularExpression res = new RegularExpression("^Site ID Site Code Site Name Active Site Type Parent Loop/Area.*",false);
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", res);
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[0];
			if(table.rowCount()>1){
				isExisting = true;
			}
		}else throw new ObjectNotFoundException("Site list object can't find...");
		
		return isExisting;
	}
	
	/**
	 * Get site Code
	 * @param siteCode
	 * @return
	 */
	public String getSiteCode(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Site Code");
	}
	
	public boolean isSiteCodeExisting(String siteCode){
		this.searchSite("Site Code", siteCode);
		return this.isColumnValueExistingInSiteListPage(siteCode, "Site Code");
	}
	
	/**
	 * Get all site codes 
	 * @return
	 */
	public String[] getSiteCodes(){
		String[] siteCodes = null;
		RegularExpression res = new RegularExpression("^Site ID Site Code Site Name Active Site Type Parent Loop/Area.*",false);
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", res);
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[0];
			siteCodes = new String[table.rowCount()-1];
			int columnForCode = table.findColumn(0, "Site Code");
			if(table.rowCount()>0){
				for(int i=1; i<table.rowCount(); i++){
					siteCodes[i-1] = table.getCellValue(i, columnForCode);
				}
			}else throw new ErrorOnDataException("No site info be found...");
		}else throw new ObjectNotFoundException("Site list object can't find...");
		
		return siteCodes;
	}
	
	public String getSiteID(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Site ID");
	}
	
	public String getSiteName(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Site Name");
	}
	
	public String getSiteStatus(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Active");
	}
	
	public String getSiteType(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Site Type");
	}
	
	public String getSiteLoop(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Parent Loop/Area");
	}
	
	public String getSiteRelationType(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Site Relation Type");
	}
	
	public String getSiteTypeOfUse(String siteCode){
		return this.getColumnValueInSiteListPage(siteCode, "Type of Use");
	}
	
  	public IHtmlObject[] getLoopsSitesTable(){
  		return browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Site ID(| )Site Code(| )Site.*", false));
  	}
  	
	public String getSiteInfo(String siteID, String location){
		String returnString = "";
		int row = 0;
		if(location.equalsIgnoreCase("Previous")||location.equalsIgnoreCase("Next")){
			this.turnToSiteExistPage(siteID);
		}else if(location.equalsIgnoreCase("First")){
			this.turnToFirstPage();
		}else if(location.equalsIgnoreCase("Last")){
			this.turnToLastPage();
		}
		IHtmlObject[] objs = this.getLoopsSitesTable();
		IHtmlTable table = (IHtmlTable)objs[0];

		//Get First site info
		if(location.equalsIgnoreCase("First")){
			row = 1;
		}else if(location.equalsIgnoreCase("Last")){
			row = table.rowCount()-1;
		}else {
			for(int i=1; i<table.rowCount(); i++){
				if(table.getCellValue(i, 1).equals(siteID)){
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
	
//	PagingComponent turnPage = new PagingComponent();
	public void turnToSiteExistPage(String siteId){
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rowIndex = 0;
		
		int i = 0;
		do {
			if(i!=0){
				this.clickNext();
				ajax.waitLoading();
				this.waitLoading();
			}
			objs = this.getLoopsSitesTable();
			table = (IHtmlTable)objs[0];
			
			rowIndex = table.findRow(1, siteId);
			i +=1;
		} while(rowIndex < 1);
		
		Browser.unregister(objs);
	}
	public void turnToFirstPage(){
		this.clickFirst();
		ajax.waitLoading();
		this.waitLoading();
	}
	public void turnToLastPage(){
		this.clickLast();
		ajax.waitLoading();
		this.waitLoading();
	}
	String firstButtonPattern = "(\\s?)+First$";
	String lastButtonPattern = "^Last.*";
	String previoustButtonPattern = "^Previous.*";
	String nextButtonPattern = "^Next.*";
	public boolean isButtonExist(String buttonPattern){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression(buttonPattern, false));
	}
	
	public void clickButton(String buttonPattern){
		if(this.isButtonExist(buttonPattern)){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(buttonPattern, false));
		}
	}
	
	public void clickFirst(){
		this.clickButton(firstButtonPattern);
	}
	
	public void clickLast(){
		this.clickButton(lastButtonPattern);
	}
	
	public void clickPrevious(){
		this.clickButton(previoustButtonPattern);
	}
	
	public void clickNext(){
		this.clickButton(nextButtonPattern);
	}
}
