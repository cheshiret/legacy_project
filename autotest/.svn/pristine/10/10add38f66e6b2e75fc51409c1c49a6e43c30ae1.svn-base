package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Sara Wang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrLoopAreaSitePage extends InventoryManagerPage {

	private static InvMgrLoopAreaSitePage _instance = null;

	public static InvMgrLoopAreaSitePage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrLoopAreaSitePage();
		}

		return _instance;
	}

	protected InvMgrLoopAreaSitePage() {

	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Add New Site");
	}

	/**click sites tab*/
	public void clickSites() {
		browser.clickGuiObject(".className", "tabanchor", ".text", "Sites");
	}
	
	/**click Non-Site-Specific Groups tab*/
	public void clickNonSpecSiteGroup(){
	  browser.clickGuiObject(".className", "tabanchor", ".text", "Non-Site-Specific Groups");
	}
	
	/**
	 * Check if site tab exist
	 * @return
	 */
	public boolean isSiteTabExist(){
	   return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Sites");
	}
	
	/**
	 * check if NSS tab exist
	 * @return
	 */
	public boolean isNSSTabExist(){
	  return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Non-Site-Specific Groups");
	}

	/** Click on Add New Site link. */
	public void clickAddNewLoopArea() {
		browser.clickGuiObject(".class", "Html.A", ".text","Add New Site");
	}
	
 	public void selectSearchType(String searchType) {
  	  	browser.selectDropdownList(".id", "search_type", searchType);
  	}
  	
  	public void setSearchType(String searchType) {
  	  	browser.setTextField(".id", "search_value", searchType);
  	}
  	
  	public void selectStatus(String searchStatus) {
  	  	browser.selectDropdownList(".id", "search_status", searchStatus);
  	}
  	
  	public boolean checkStatus(String searchStatus){
  		String statusUI = browser.getDropdownListValue(".id", "search_status", 0);
  		if(!statusUI.equalsIgnoreCase(searchStatus)){
  			return false;
  		}else return true;
  	}

	public void selectSiteType(String siteType) {
  	  	browser.selectDropdownList(".id", "siteType", siteType);
  	}
  	
  	public void selectSiteRelationType(String siteRelationType) {
  	  	browser.selectDropdownList(".id", "siteRelationType", siteRelationType);
  	}
  	
  	public void selectTypeOfUse(String typeOfUse) {
  	  	browser.selectDropdownList(".id", "useType", typeOfUse);
  	}
  	
  	public void clickSearch() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Search");
  	}
  	
 	public void selectAll() {
  	  	browser.selectCheckBox(".name", "all_slct");
  	}
  	
  	public void unselectAll() {
  	  	browser.unSelectCheckBox(".name", "all_slct");
  	}
  	
 	public void clickAssign() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Assign");
  	}
  	
  	public void clickRemove() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
  	}
  	
  	public void clickSiteID(String siteID){
		RegularExpression rex = new RegularExpression("\"InvMgrSiteDetails.do\".+\"" + siteID + "\"", false);
  	  	browser.clickGuiObject(".class", "Html.A", ".href", rex);
  	}
  	
  	public IHtmlObject[] getLoopsSitesTable(){
  		return browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^Site ID(| )Site Code(| )Site.*", false));
  	}
  	
  	/**
  	 * Search sites
  	 * @param searchType
  	 * @param searchTypeValue
  	 * @param status
  	 * @param siteType
  	 * @param siteRelationType
  	 * @param typeOfUse
  	 */
  	public void searchSites(String searchType, String searchTypeValue, String status, String siteType, String siteRelationType, String typeOfUse) {
  		boolean criteriaSet = false;
  		if(null!=searchType && searchType.length()>0){
  			this.selectSearchType(searchType);
  			if(null!=searchTypeValue && searchTypeValue.length()>0){
  				this.setSearchType(searchTypeValue);
  			}
  			criteriaSet = true;
  		}
  		if(null!=status && status.length()>0){
  			this.selectStatus(status);
  			criteriaSet = true;
  		}
  		if(null!=siteType && siteType.length()>0){
  			this.selectSiteType(siteType);
  			criteriaSet = true;
  		}
  		if(null!=siteRelationType && siteRelationType.length()>0){
  			this.selectSiteRelationType(siteRelationType);
  			criteriaSet = true;
  		}
  		if(null!=typeOfUse && typeOfUse.length()>0){
  			this.selectTypeOfUse(typeOfUse);
  			criteriaSet = true;
  		}

  		if(criteriaSet){
  			this.clickSearch();
  		}
  		ajax.waitLoading();
  		this.waitLoading();
  	}
  	
	/**
 	 * Search sites via site code
 	 * @param siteCode
 	 */
  	public void searchSites(String siteCode) {
  		this.selectSearchType("Site Code");
	  	if(null!=siteCode && siteCode.length()>0) {
	  	  	this.setSearchType(siteCode);
	  	}
	  	  	this.clickSearch();
	}
  	
	/**
	 * Select specific loop or area site by site ID
	 * @param siteID
	 */
	public void selectSiteCheckBoxBySiteID(String siteID){
	  browser.clickGuiObject(".value",siteID,".type","checkbox");
	}
	
	/**
	 * Select specific loop or area site by site IDS
	 * @param siteIDs
	 */
	public void selectSiteCheckBoxBySiteIDs(String[] siteIDs){
	  	if(siteIDs==null || siteIDs.length<1) //do nothing
	  	  	return;
	  
	  	for(int i=0;i<siteIDs.length;i++) {
	  		selectSiteCheckBoxBySiteID(siteIDs[i]);
	  	}
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
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
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
	
	PagingComponent turnPage = new PagingComponent();
	public void turnToSiteExistPage(String siteId){
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rowIndex = 0;
		
		int i = 0;
		do {
			if(i!=0){
				turnPage.clickNext();
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
		turnPage.clickFirst();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void turnToLastPage(){
		if(turnPage.lastExists(true)){
			turnPage.clickLast();
			ajax.waitLoading();
			this.waitLoading();
		}
	}
}
