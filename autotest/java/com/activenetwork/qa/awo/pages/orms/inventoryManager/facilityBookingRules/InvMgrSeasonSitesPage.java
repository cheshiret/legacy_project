/*
 * Created on Nov 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Jdu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrSeasonSitesPage extends InvMgrSeasonDetailPage {
  	private static InvMgrSeasonSitesPage _instance;
  	
  	protected InvMgrSeasonSitesPage() {
  	  
  	}
  	
  	public static InvMgrSeasonSitesPage getInstance() {
  	  	if(null==_instance) {
  	  	  _instance=new InvMgrSeasonSitesPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	public boolean exists() {
//  		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"SeasonSites\\.do\",.*\"viewSeasonSites\",.*\"InventoryWorker\",.*\\)", false);
  		RegularExpression rex = new RegularExpression("^Site Number Show:.*", false);
//  		RegularExpression rex = new RegularExpression("javascript:invokeAction\\(.*\"ClosuresSites\\.do\",.*\"viewClosuresSites\",.*\"InventoryWorker\",.*\\)", false);
//  		return browser.checkHtmlObjectExists(".text", "Go", ".href", rex);
  		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", rex);
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
  	
  	public void selectShow(String text) {
  	  	browser.selectDropdownList(".id", "assigned", text);
  	}
  	
  	public void selectLoopArea(String option) {
  	  	browser.selectDropdownList(".id", "loop", option);
  	}
  	
  	public void setSiteNumber(String num) {
  	  	browser.setTextField(".id", "stieNumber", num);
  	}
  	
  	public void clickGo() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Search");
  	}
//  	
//  	public void clickSeasonsTab() {
//  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Seasons");
//  	}
//  	
//  	public void clickClosuresTab() {
//  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Closures");
//  	}
//  	
  	public void searchSites(String[] siteNumber, String show, String loop) {
  	  	boolean criteriaSet = false;
  	  	if(siteNumber != null && siteNumber.length == 1) {
  	  	  	this.setSiteNumber(siteNumber[0]);
  	  	  	criteriaSet = true;
  	  	}
  	  	
  	  	if(show != null && show.length() > 0) {
  	  	  	this.selectShow(show);
  	  	  	criteriaSet = true;
  	  	}
  	  	
  	  	if(loop != null && loop.length() > 0) {
  	  	  	this.selectLoopArea(loop);
  	  	  	criteriaSet = true;
  	  	}
  	  	
  	  	if(criteriaSet)
  	  	  	this.clickGo();
  	}
  	
  	public void searchSites(String siteNumber) {
	  	if(siteNumber != null) {
	  	  	this.setSiteNumber(siteNumber);
	  	}
	  	  	this.clickGo();
	}
  	
  	public String getAssignStatus(){
		IHtmlObject[] obj=browser.getTableTestObject(".text", new RegularExpression("^Site ID.*", false));
  	  	String status = ((IHtmlTable)obj[0]).getCellValue(1, 5).trim();
  	  	Browser.unregister(obj);
  	  	return status;
  	}
  	
  	public void setSiteCode(String code){
  	  browser.setTextField(".id", "siteCode", code);
  	}
  	
  	public void searchSiteByCode(String code){
	  	if(code!=null) {
		  	this.setSiteCode(code);
		}
	  	this.selectShow("Assigned or Not");
		  	this.clickGo();
	  	}
  	
	/**
	 * Select specific season site by site ID
	 * @param siteID
	 */
	public void selectSiteCheckBoxBySiteID(String siteID){
	  browser.clickGuiObject(".value",siteID,".type","checkbox");
	}
  	
	/**
	 * Select specific season site by site IDs
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
		StringBuffer warningMessageBuff = new StringBuffer();
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			for(int i=0; i<objs.length; i++){
				warningMessageBuff.append(objs[i].getProperty(".text").toString()+ "#");
			}
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
}

