package com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Sara Wang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrCampingUnitSitesPage extends InventoryManagerPage{
  	private static InvMgrCampingUnitSitesPage _instance;
  	
  	protected InvMgrCampingUnitSitesPage() {
  	  
  	}
  	
  	public static InvMgrCampingUnitSitesPage getInstance() {
  	  	if(null==_instance) {
  	  	  _instance=new InvMgrCampingUnitSitesPage();
  	  	}
  	  	
  	  	return _instance;
  	}

  	public boolean exists() {
  		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"CampingUnitSites\\.do\",.*\"showSites\",.*\"InventoryWorker\",.*\\)", false);
  		return browser.checkHtmlObjectExists(".text", "Search", ".href", rex);
  	}
  	
  	public void clickCamplingUnitsTab() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Camping Units");
  	}
  	
  	public void selectSearchType(String searchType) {
  	  	browser.selectDropdownList(".id", "search", searchType);
  	}
  	
  	public void setSearchValue(String searchValue) {
  	  	browser.setTextField(".id", "criteria", searchValue);
  	}
  	
  	public void selectShow(String show) {
  	  	browser.selectDropdownList(".id", "show", show);
  	}
  	
  	public void clickGo() {
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
  	
	/** Click loops */
	public void clickLoops(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitLoops.do.*", false));
	}
  	
  	/**
  	 * Search sites
  	 * @param searchType
  	 * @param searchTypeValue
  	 * @param show
  	 */
 	public void searchSites(String searchType, String searchTypeValue, String show) {
  	  	boolean criteriaSet = false;
 		if(null!=searchType && searchType.length()>0){
 			this.selectSearchType(searchType);
 			if(null!=searchTypeValue && searchTypeValue.length()>0){
 				this.setSearchValue(searchTypeValue);
 			}
  	  	  	criteriaSet = true;
 		}
 		if(null!=show && show.length()>0){
 			this.selectShow(show);
  	  	  	criteriaSet = true;
 		}
  	  	
  	  	if(criteriaSet)
  	  	  	this.clickGo();
  	}
 	
	/**
 	 * Search sites via site name
 	 * @param siteName
 	 */
  	public void searchSites(String siteName) {
  		this.selectSearchType("Site Name");
	  	if(null!=siteName && siteName.length()>0) {
	  	  	this.setSearchValue(siteName);
	  	}
	  	  	this.clickGo();
	}
  	
	/**
	 * Select specific camping units site by site ID
	 * @param siteID
	 */
	public void selectSiteCheckBoxBySiteID(String siteID){
	  browser.clickGuiObject(".value",siteID,".type","checkbox");
	}
	
	public void selectSite(String siteName){
		this.searchSites(siteName);
		this.waitLoading();
		browser.selectCheckBox(".id","row_0_checkbox");
	}
	public void assignOrRemoveSite(String siteName,boolean isAssign){
		selectSite(siteName);
		if(isAssign){
			this.clickAssign();
		}else{
			clickRemove();
		}
		this.waitLoading();		
	}
	
	/**
	 * Select specific camping units site by site IDS
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
	 * Click camp unit combo details 
	 */
	public void clickCampUnitComboDetails(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitDetails.do.*", false),0);
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
}
