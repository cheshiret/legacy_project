package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @author Sara Wang
 * This page is also can be used for closure slips page.
 */
public class InvMgrClosuresSitesPage extends InvMgrClosuresPage{
  	private static InvMgrClosuresSitesPage _instance;
  	
  	protected InvMgrClosuresSitesPage() {
  	  
  	}
  	
  	public static InvMgrClosuresSitesPage getInstance() {
  	  	if(null==_instance) {
  	  	  _instance=new InvMgrClosuresSitesPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	public boolean exists() {
  		//Quentin[20131115]
 		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Closure (Slips|Sites)", false), ".href", new RegularExpression("\"ClosuresSites\\.do\"", false)));
  	}
  	
  	public void clickSeasonsTab() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Seasons");
  	}
  	
  	public void clickClosuresTab() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Closures");
  	}
  	
  	public void setSiteCode(String siteCode) {
  	  	browser.setTextField(".id", "siteCode", siteCode);
  	}
  	
  	public void selectShow(String text) {
  	  	browser.selectDropdownList(".id", "assigned", text);
  	}
  	
  	public void selectLoopArea(String option) {
  	  	browser.selectDropdownList(".id", "loop", option);
  	}
  	
  	public void clickGo() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Search", false));
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
  	  	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Remove", false));
  	}
  	
  	/**
  	 * Search sites
  	 * @param siteCodes
  	 * @param show
  	 * @param loop
  	 */
 	public void searchSites(String[] siteCodes, String show, String loop) {
  	  	boolean criteriaSet = false;
  	  	if(siteCodes != null && siteCodes.length == 1) {
  	  	  	this.setSiteCode(siteCodes[0]);
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
  	  	this.waitLoading();
  	}
  	
 	/**
 	 * Search sites via site code
 	 * @param siteCode
 	 */
  	public void searchSites(String siteCode) {
	  	if(null!=siteCode && siteCode.length()>0) {
	  	  	this.setSiteCode(siteCode);
	  	}
	  	this.selectShow("Assigned or Not");
	  	this.clickGo();
	  	this.waitLoading();
	}
  	
	/**
	 * Select specific season site by site ID
	 * @param siteID
	 */
	public void selectSiteCheckBoxBySiteID(String siteID){
	  browser.selectCheckBox(".value",siteID);
	}
	
  	public void searchSiteByCode(String code){
	  	if(code!=null) {
		  	this.setSiteCode(code);
		}
	  	this.selectShow("Assigned or Not");
		this.clickGo();
		this.waitLoading();
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
	
	public void assignSites(String[] siteIDs){
		String schema = DataBaseFunctions.getSchemaName(getContract(), TestProperty.getProperty("target_env"));
		
		for(int i=0;i<siteIDs.length;i++) {
			String siteCd = DataBaseFunctions.getSiteNum(siteIDs[i], schema);
			searchSiteByCode(siteCd);
	  		selectSiteCheckBoxBySiteID(siteIDs[i]);
	  		clickAssign();
	  		this.waitLoading();
	  	}
	}
	
	public void assignSlips(String... slipCDs){
		for(String slipCD:slipCDs){
			searchSiteByCode(slipCD);
	  		this.selectAll();
	  		clickAssign();
	  		this.waitLoading();
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
	
	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
  	public String getAssignStatus(){
		IHtmlObject[] obj=browser.getTableTestObject(".text", new RegularExpression("^Site ID.*", false));
  	  	String status = ((IHtmlTable)obj[0]).getCellValue(1, 5).trim();
  	  	Browser.unregister(obj);
  	  	return status;
  	}
  	
  	public void clickClosuresDetailsTab() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Closure Details");
  	}
  	
  	public String getProductCode() {
  		return browser.getTextFieldValue(".id", "siteCode");
  	}
  	
  	public List<String> getShowStatusValues() {
  		return browser.getDropdownElements(".id", "assigned");
  	}
  	
  	public List<String> getLoopOrDockArea() {
  		return browser.getDropdownElements(".id", "loop");
  	}
  	
  	public void setProductCode(String prdCode) {
  	  	browser.setTextField(".id", "siteCode", prdCode);
  	}

  	public void searchProductByLoopOrDock(String loop){
		this.setProductCode(StringUtil.EMPTY);
	  	this.selectShow("Assigned or Not");
		this.selectLoopArea(loop);
	  	this.clickGo();
		this.waitLoading();
	}
  	
  	public List<String> getAllProductIDs() {
		List<String> ids = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^(Site|Slip) ID.*", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not get product list table on page.");
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		if(table.rowCount()<2)
			return null;
		int col = table.findColumn(0, new RegularExpression("^(Site|Slip) ID", false));
		for(int i=1;i<table.rowCount();i++)
			ids.add(table.getCellValue(i, col));
		Browser.unregister(objs);
		return ids;
	}
  	
  	public void searchSiteOrSlipByAllEntry(String prdCode, String show, String loop) {
  	  	this.setProductCode(prdCode);
  	  	this.selectShow(show);
  	  	this.selectLoopArea(loop);
  	  	this.clickGo();
  	  	this.waitLoading();
  	}
  	
  	public boolean isViewChangeRequestItemsBtnExisted() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "View Change Request Items");
	}
  	
  	public String getErrorMessage(){
  		return browser.getObjectText(".id", "NOTSET");
  	}
  	
  	/**
  	 * 
  	 * @return true -> Enable
  	 *        false -> Disable
  	 */
  	public boolean isAssignEnable(){
  		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", "Assign");
  	}
  	
  	public void checkAssignButtonStatus(boolean expect){
  		if(isAssignEnable() != expect){
  			throw new ErrorOnPageException("Assign button should"+(expect? " ":" NOT ")+"be enable!");
  		}
  	}
  	
  	public void searchByShow(String show){
  		this.selectShow(show);
  		this.clickGo();
  		this.waitLoading();
  	}
}
