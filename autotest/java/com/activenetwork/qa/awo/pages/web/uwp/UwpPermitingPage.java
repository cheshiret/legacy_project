package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This page is comment page for sub tab: Permit Area Details, Permit Area Map, Desolation Zone List and Date Range Availability
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 25, 2013
 */
public class UwpPermitingPage  extends UwpPage {

	public static UwpPermitingPage _instance = null;
	
	public static UwpPermitingPage getInstance(){
		if (null == _instance){
			_instance = new UwpPermitingPage();
		}
		return _instance;
	}
	
	protected UwpPermitingPage(){
		
	}
	
	public static String DESTINATION_ZONE_LIST = "Destination Zone List";
	public static String ENTRANCE_LIST = "Entrance List";
	public static String PERMIT_ZONE_LIST = "Permit Zone List";
	public static String RIVER_LIST = "River List";
	public static String TRAIL_LIST = "Trail List";
	
	/**Elements Properties Define Begin */
	protected Property[] getSelectedSubNavTDProp() {
		return Property.toPropertyArray(".class", "Html.TD", ".className", new RegularExpression("(first|last)?slct", false));
	}
	
	protected Property[] getPermitAreaDetailsProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "wildernessAreaFacilityDetails");
	}
	
	protected Property[] getPermitAreaMapProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "permitMap");
	}
	
	protected Property[] getDateRangeAvailProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "permitCalendar");
	}

	protected Property[] getEntranceListProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "entranceSearch");
	}
	
	protected Property[] getEntranceListProp(String entranceSubTitle) {
		return Property.toPropertyArray(".class", "Html.A", ".id", "entranceSearch", ".text", new RegularExpression(entranceSubTitle, false));
	}
	
	protected Property[] getSTIEntranceListProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "entranceSTISearch");
	}
	/**Elements Properties Define End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".id", "cgroundName");
	}
	
	public void clickPermitAreaDetails() {
	  	browser.clickGuiObject(getPermitAreaDetailsProp());
	}	
	
	public boolean isPermitAreaDetailsExist() {
		return browser.checkHtmlObjectExists(getPermitAreaDetailsProp());
	}
	
	public void clickPermitAreaMap() {
	  	browser.clickGuiObject(getPermitAreaMapProp());
	}

	public boolean isPermitAreaMapExist() {
		return browser.checkHtmlObjectExists(getPermitAreaMapProp());
	}
	
	public void clickDateRangeAvailability() {
	  	browser.clickGuiObject(getDateRangeAvailProp());
	}

	public boolean isDateRangeAvailExist() {
		return browser.checkHtmlObjectExists(getDateRangeAvailProp());
	}
	
	public void clickEntranceList() {
	  	browser.clickGuiObject(getEntranceListProp());
	}

	public boolean isEntarnceListExist() {
		return browser.checkHtmlObjectExists(getEntranceListProp());
	}
	
	public boolean isEntranceListDisplaying(String entranceSubTitle){
		return browser.checkHtmlObjectExists(getEntranceListProp(entranceSubTitle));
	}
	
	public void waitForEntranceListDisplaying(String entranceSubTitle){
		 browser.searchObjectWaitExists(getEntranceListProp(entranceSubTitle), LONG_SLEEP);
	}
	
	public void verifyEntranceListTabDisplaying(String entranceSubtitle, boolean displayed){
		if(this.isEntranceListDisplaying(entranceSubtitle)!=displayed){
			throw new ErrorOnDataException("Failed to verify entrance sub title:"+entranceSubtitle+" is "+(displayed?"":"not")+" displayed.");
		}
		logger.info("Successfully verify entrance sub title:"+entranceSubtitle+" is "+(displayed?"":"not")+" displayed.");
	}
	
	public String getErrorMes(){
		String warnMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV", ".className", new RegularExpression("(msg error|msg topofpage)", false));
		if(objs.length >0){
			warnMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return warnMsg;
	}
	
	public void verifyWarningMsg(String expectError){
		String currentError = this.getErrorMes();
		if (expectError.equalsIgnoreCase(currentError)){
			logger.info("Verification for error messsage successfully");
		}else{
			throw new ErrorOnPageException("Verification for error message failed on entrance list page..", expectError, currentError);
		}
	}
	
	public boolean isSTIEntranceListTabExist() {
		return browser.checkHtmlObjectExists(getSTIEntranceListProp());
	}
	
	public String getSTIEntranceListTabNm() {
		return browser.getObjectText(getSTIEntranceListProp());
	}
	
	public void clickFindOtherFacilities(){
		Property[] p1 = Property.concatPropertyArray(a(), ".text", "Find Other Facilities");
		Property[] p2 = Property.concatPropertyArray(div(), ".id", "leftNavPanelLinks");
		browser.clickGuiObject(Property.atList(p2, p1));
	}
}
