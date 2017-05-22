/*
 * $Id: InvMgrFacilityDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description  2-168690
 * 
 * @author CGuo
 */
public class InvMgrFacilityDetailPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrFacilityPage
	 * Generated     : Feb 9, 2005 4:41:51 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2005/02/09
	 */

	private static InvMgrFacilityDetailPage _instance = null;

	public static InvMgrFacilityDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrFacilityDetailPage();
		}

		return _instance;
	}

	protected InvMgrFacilityDetailPage() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Facility Information", false));
	}

	/**View change request item*/
	public void clickViewChangeRequestItems() {
		browser.clickGuiObject(".class", "Html.A", ".text","View Change Request Items");
	}

	/**Click Notes&ALerts*/
	public void clickNotesAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	/**click Add Notes/Alerts*/
	public void clickAddNotesAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Notes/Alerts");
	}

	/**Click Delete Rates cache*/
	public void clickDeleteRatesCache() {
		browser.clickGuiObject(".class", "Html.A", ".text","Delete Rates Cache");
	}

	/**Click Availability Grid*/
	public void clickAvailabilityGrid() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Availability Grid");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**cancel Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**click Apply*/
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Get Facility ID
	 * @return
	 */
	public String getFacilityID() {
		IHtmlObject[] objs = browser.getHtmlObject(".id","FacilityDetailView.id");
		String id = objs[0].getProperty(".value").toString();
		Browser.unregister(objs);

		return id;
	}
	
	public void selectFacilityCategory(String category){
	   browser.selectDropdownList(".id","page_name",category);
	}
	
	public String getDescription(){
	   IHtmlObject[] objs=browser.getHtmlObject(".id","FacilityDetailView.description");
	   String description=objs[0].getProperty(".text").toString();
	   
	   Browser.unregister(objs);
	   
	   return description;
	}
	
	public void setDescription(String description){
	  	browser.setTextArea(".id","FacilityDetailView.description",description);
	}
	
	public boolean checkNoticeMessage(String mess){
	   return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",mess);
	}
	
	public void clickAdd(){
	   browser.clickGuiObject(".class","Html.A",".text","Add");
	}
	
	public void selectCategoryDropDown(String category){
	  browser.selectDropdownList("id","productCategory",category);
	}
	
	public void selectCategory(String category){
	   this.clickAdd();
	   this.waitLoading();
	   selectCategoryDropDown(category);
	   this.waitLoading();
	   this.clickAdd();
	   this.waitLoading();
	   this.clickApply();
	   
	   this.waitLoading();
	}
	/**
	 * Select Auto-Invalidate Tickets as 'Yes' or 'No'
	 * @param isAutoIncaliTic
	 */
	public void selectAutoInvalidateTickets(String isAutoIncaliTic){
		   browser.selectDropdownList(".id","attr_200200",isAutoIncaliTic);
	}
	
	
	public String getCheckinTime() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "attr_15");
		String checkinTime = objs[0].getProperty(".value").toString();
		if("".equals(checkinTime)){
			checkinTime=" "+checkinTime;
		}
		checkinTime = checkinTime +this.getChenckinIspm();

		Browser.unregister(objs);
		return checkinTime;
	}

	public String getChenckinIspm(){
		int ispm = 0;
		String flag= browser.getDropdownListValue(".id", "attr_15_ispm",0);
		if(flag.equalsIgnoreCase("PM")){
			ispm = 1;
		}
		String checkinIspm="";
		switch (ispm) {
		case 0:
			checkinIspm ="AM";
			break;
		case 1:
			checkinIspm ="PM";
			break;
		default:
			break;
		}
		return checkinIspm;
	}
	
	public String getCheckoutTime() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "attr_16");
		String checkoutTime = objs[0].getProperty(".value").toString();
		checkoutTime = checkoutTime +this.getChenckoutIspm();

		Browser.unregister(objs);
		return checkoutTime;
	}

	public String getChenckoutIspm(){
		int ispm = 0;
		String flag= browser.getDropdownListValue(".id", "attr_15_ispm",0);
		if(flag.equalsIgnoreCase("PM")){
			ispm = 1;
		}
		String checkinIspm="";
		switch (ispm) {
		case 0:
			checkinIspm ="AM";
			break;
		case 1:
			checkinIspm ="PM";
			break;
		default:
			break;
		}
		return checkinIspm;
	}
	
	public void setCheckInTime(String time) {
		if (time.contains("AM")||time.contains("am")) {
			browser.selectDropdownList(".id", "attr_15_ispm", "AM");
		} else if (time.contains("PM")||time.contains("pm")) {
			browser.selectDropdownList(".id", "attr_15_ispm", "PM");
		}
		browser.setTextField(".id", "attr_15", time.split("(AM|am|PM|pm)")[0]);
	}

	public void setCheckOutTime(String time) {
		if (time.contains("AM")||time.contains("am")) {
			browser.selectDropdownList(".id", "attr_16_ispm", "AM");
		} else if (time.contains("PM")||time.contains("pm")) {
			browser.selectDropdownList(".id", "attr_16_ispm", "PM");
		}
		browser.setTextField(".id", "attr_16", time.split("(AM|am|PM|pm)")[0]);
	}
	
	public String getPublicLine(){
		IHtmlObject[]objs =browser.getHtmlObject(".id", "FacilityDetailView.publicLine");
		String publicLine=objs[0].getProperty(".value");
		Browser.unregister(objs);
		return publicLine;
	}
	
	public String getBrochureInfoDes(){
		IHtmlObject[]objs =browser.getHtmlObject(".id", "attr_304");
		String description=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return description;
	}
	
	public String getImportantInfo(){
		IHtmlObject[]objs =browser.getHtmlObject(".id", "attr_100002");
		String importantInfo=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return importantInfo;
	}
	
	public void setImportantInfo(String importantInfo){
		browser.setTextArea(".id", "attr_100002", importantInfo);
	}
	
	public String getDirections(){
		IHtmlObject[]objs =browser.getHtmlObject(".id", "attr_100001");
		String direction=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return direction;
	}
	
	/**
	 * Modify facility name
	 * @param name
	 */
	public void modifyFacilityName(String name){
	  	browser.setTextField(".id","FacilityDetailView.name",name);
	}
	
	/**
	 * Modify facility short name
	 * @param name
	 */
	public void modifyFacilityShortName(String name){
	  	browser.setTextField(".id","FacilityDetailView.shortName",name);
	}
	
	/**
	 * Modify facility description
	 * @param description
	 */
	public void modifyFacilityDescription(String description){
	  	browser.setTextArea(".id","FacilityDetailView.description",description);
	}
	
	/**
	 * Select facility production category
	 * @param category
	 */
	public void selectProductCategory(String category){
	  	browser.selectDropdownList(".id","productCategory",category);
	}
	
	/**
	 * Select mailing address state
	 * @param state
	 */
	public void selectMailToState(String state){
	  	browser.selectDropdownList(".id","FacilityDetailView.stateProvince",state);
	}
	
	/**
	 * Modify Alias info
	 * @param info
	 */
	public void modifyAliasInfo(String info){
	  	browser.setTextArea(".id","attr_100000",info);
	}
	
	/**
	 * Modify prochure info
	 * @param info
	 */
	public void modifyBrochureInfo(String info){
	  	browser.setTextArea(".id","attr_304",info);
	}
	
	public void setGeographyInfo(String info) {
		browser.setTextArea(".id","attr_10100",info);
	}
	
	public void setRecreationInfo(String info) {
		browser.setTextArea(".id","attr_10101",info);
	}
	
	public void setFacilitiesInfo(String info) {
		browser.setTextArea(".id","attr_10102",info);
	}
	
	public void setNearbyAttractionsInfo(String info) {
		browser.setTextArea(".id","attr_10103",info);
	}
	/**
	 * Modify driving direction
	 * @param direction
	 */
	public void modifyDriveDirection(String direction){
	  	browser.setTextArea(".id","attr_100001",direction);
	}
	
	/**
	 * Modify important info
	 * @param info - important info
	 */
	public void modifyImportantInfo(String info){
	  	browser.setTextArea(".id","attr_100002",info);
	}
	
	/**
	 * Select Allow Pre-Check In drop down list value
	 * @param expectValue: Yes
	 *                     No
	 */
	public void selectAllowPreCheckIn(String expectValue){
		browser.selectDropdownList(".id","attr_2401",expectValue);
	}
	
	/**
	 * Verify Change Request Mode link exist or not 
	 * @return
	 */
	public boolean checkChangeRequestModeLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Request Mode");
	}
	
	/**
	 * Verify Change Immediate Mode link exist or not 
	 * @return
	 */
	public boolean checkChangeImmediateModeLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Immediate Mode");
	}
	
	/**
	 * Verify Mode bar exist or not
	 * @return
	 */
	public boolean checkModeBar(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", "Change Request Mode");
	}
	
	/**
	 * Verify 'Add New' button is available or not
	 * @return
	 */
	public boolean checkAddNew(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add New");
	}
	
	/** Click Supplementary Info tab */
	public void clickSupplementaryInfoTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Supplementary Info");
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
	 * Get message after successfully modifying facility detail information  
	 * @return
	 */
	public String getSuccessfulMessage(){
		String successMes = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".className", "message msgsuccess");
		if(objs.length>0){
			successMes = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return successMes;
	}
	
	public String getWebFacilitySpecificMess(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "attr_100147");
		String specificMess = objs[0].text()
		.replace("<b>", "").replace("</b>", "")
		.replace("<br>", "").replace("<br />", "")
		.replace("<p>", "").replace("</p>", "")
		.replace(" <hr />", "")
		.replace("<ul>", "").replace("</ul>", "")
		.replace("<li>", "").replace("</li>", "")
		.replace("<a href=\"http://get.adobe.com/reader/\">", "").replace("</a>", "")
		.replace("<a href=\"http://www.recreation.gov/marketing/html/Desolation_Print_Permit_Online.pdf\">", "")
		.replace("<p align=\"justify\">", "")
		.replaceAll("\\s+", " ");
		
		Browser.unregister(objs);
		return specificMess;
	}

	/**
	 * @param string
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", "FacilityDetailView.locationStatus", status);
	}

	/**
	 * @param string
	 */
	public void selectHiddenOnWebSearch(String item) {
		browser.selectDropdownList("id", "attr_381", item);
	}

	public List<String> getProductCateGories() {
		IHtmlObject[] objs=browser.getDropdownList(".id", new RegularExpression("productCategory(ReadOnly)?",false));
		List<String> list=new ArrayList<String>();
		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find product category drop-down list.");
		}
		for(int i=0;i<objs.length;i++){
			ISelect ddList=(ISelect)objs[i];
			list.add(ddList.getSelectedText());
		}
		Browser.unregister(objs);
		return list;
	}
	
	public void setConfirmationPeriod(String confirmationPeriod) {
		browser.setTextField(".id", "attr_4103", confirmationPeriod);
	}
	
	public void setOnlinePermitPrintPeriod(String onlinePermitPrintPeriod){
		browser.setTextField(".id", "attr_4105", onlinePermitPrintPeriod);
	}

	public void setConfirmationPeriodStartTime(String confirmationPeriodStartTime){
		browser.setTextField(".id", "attr_4104", confirmationPeriodStartTime);
	}
	
	public void setOnlinePermitPrintPeriodStartTime(String onlinePermitPrintPeriodStartTime){
		browser.setTextField(".id", "attr_4106", onlinePermitPrintPeriodStartTime);
	}

	public void selectConfirmationPeriodStartTimeAmOrPm(String value){
		browser.selectDropdownList(".id", "attr_4104_ispm", value);
	}
	
	public void selectOnlinePermitPrintPeriodStartTimeAmOrPm(String value){
		browser.selectDropdownList(".id", "attr_4106_ispm", value);
	}

	/**
	 * Setup customer confirmation info
	 * @param confirmationPeriod
	 * @param confirmationPeriodStartTime
	 * @param isAM   --true: Select 'AM'
	 *               --false: Select 'PM'
	 */
	public void setupCustomerConfirmation(String confirmationPeriod, String confirmationPeriodStartTime, boolean isAM){
		this.setConfirmationPeriod(confirmationPeriod);
		this.setConfirmationPeriodStartTime(confirmationPeriodStartTime);
		if(isAM){
			this.selectConfirmationPeriodStartTimeAmOrPm("AM");
		}else{
			this.selectConfirmationPeriodStartTimeAmOrPm("PM");
		}
	}
	
	/**
	 * Setup Online permit print info
	 * @param onlinePermitPrintPeriod
	 * @param onlinePermitPrintPeriodStartTime
	 * @param isAM   --true: Select 'AM'
	 *               --false: Select 'PM'
	 */
	public void setupOnlinePermitPrint(String onlinePermitPrintPeriod, String onlinePermitPrintPeriodStartTime, boolean isAM){
		this.setOnlinePermitPrintPeriod(onlinePermitPrintPeriod);
		this.setOnlinePermitPrintPeriodStartTime(onlinePermitPrintPeriodStartTime);
		if(isAM){
			this.selectOnlinePermitPrintPeriodStartTimeAmOrPm("AM");
		}else{
			this.selectOnlinePermitPrintPeriodStartTimeAmOrPm("PM");
		}
	}

	/**
	 * Modify customer confirmation information
	 * @param confirmationPeriod
	 * @param confirmationPeriodStartTime
	 * @param isAM   --true: Select 'AM'
	 *               --false: Select 'PM'
	 */
	public void modifyCustomerConfirmation(String confirmationPeriod, String confirmationPeriodStartTime, boolean isAM){
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
		.getInstance();
		invDetails.setupCustomerConfirmation(confirmationPeriod, confirmationPeriodStartTime, isAM);
		invDetails.clickApply();
		invDetails.waitLoading();
	}
	
	public void selectPOSQuickSale(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_2766", yesOrNo ? "Yes" : "No");
	}
	
	public void setMaxPOSPrdOnDashboard(String maxPOSPrd){
		browser.setTextField(".id", "attr_2767", maxPOSPrd);
	}

	public void setWebSettings(String webSettingValue){
		browser.setTextField(".id", "attr_10030", webSettingValue);
	}
	
	public String getWebSettings(){
		return browser.getTextFieldValue(".id", "attr_10030");
	}
	
	public void selectFacilityLatDirection(String dir) {
		if (StringUtil.isEmpty(dir)) {
			browser.selectDropdownList(".id", "attr_100014", 0);
		} else {
			browser.selectDropdownList(".id", "attr_100014", dir);
		}
	}
	
	public void setFacilityLatDegrees(String degrees) {
		browser.setTextField(".id", "attr_100010", degrees);
	}
	
	public void setFacilityLatMin(String min) {
		browser.setTextField(".id", "attr_100011", min);
	}
	
	public void setFacilityLatSec(String sec) {
		browser.setTextField(".id", "attr_100012", sec);
	}
	
	public void selectFacilityLongiDirection(String dir) {
		if (StringUtil.isEmpty(dir)) {
			browser.selectDropdownList(".id", "attr_100013", 0);
		} else {
			browser.selectDropdownList(".id", "attr_100013", dir);
		}
	}
	
	public void setFacilityLongiDegrees(String degrees) {
		browser.setTextField(".id", "attr_100007", degrees);
	}
	
	public void setFacilityLongiMin(String min) {
		browser.setTextField(".id", "attr_100008", min);
	}
	
	public void setFacilityLongiSec(String sec) {
		browser.setTextField(".id", "attr_100009", sec);
	}
	
	public void setFacilityLatitude(String dir, String degrees, String minutes, String seconds) {
		this.selectFacilityLatDirection(dir);
		this.setFacilityLatDegrees(degrees);
		this.setFacilityLatMin(minutes);
		this.setFacilityLatSec(seconds);
	}
	
	public void setFacilityLongitude(String dir, String degrees, String minutes, String seconds) {
		this.selectFacilityLongiDirection(dir);
		this.setFacilityLongiDegrees(degrees);
		this.setFacilityLongiMin(minutes);
		this.setFacilityLongiSec(seconds);
	}
	
	public void selectFinancialSessionType(String type) {
		browser.selectDropdownList("id", "attr_90651", type);
	}
	
	public void selectOpeningFloatRequired(boolean isRequired) {
		browser.selectDropdownList(".id", "attr_155", isRequired ? "Yes" : "No");
	}
	
	public void selectCloseBlindly(boolean yes) {
		browser.selectDropdownList(".id", "attr_157", yes ? "Yes" : "No");
	}
	
	public void selectTransmittals(boolean yes) {
		browser.selectDropdownList(".id", "attr_77", yes ? "Yes" : "No");
	}
	
	public void selectTransmittalTraceNumRequired(boolean isRequired) {
		browser.selectDropdownList(".id", "attr_78", isRequired ? "Yes" : "No");
	}
	
	public void selectEnforceMinimumtoConfirmRuleInField(boolean isEnforced){
		browser.selectDropdownList(".id", "attr_4131", isEnforced ? "Yes" : "No");
	}
	
	public void selectContractSetting(String settingValue){
		browser.selectDropdownList(".id", "attr_8061", settingValue);
	}
	
	public void selectContractSetting(){
		browser.selectDropdownList(".id", "attr_8061", StringUtil.EMPTY);
	}
	
	public void setContractLength(String lengthValue){
		browser.setTextField(".id", "attr_8062", lengthValue);
	}
	
	public void setContractLength(){
		browser.setTextField(".id", "attr_8062", StringUtil.EMPTY);
	}
	
	public boolean isContractLengthExisting(){
		return browser.checkHtmlObjectDisplayed(".id", "attr_8062");
	}
	
	public void setSeasonalContractSettingInfo(String settingValue,String lengthValue){
		if(StringUtil.isEmpty(settingValue))
			this.selectContractSetting();
		else
			this.selectContractSetting(settingValue);
		if(isContractLengthExisting()){
			if((StringUtil.isEmpty(lengthValue) || Integer.valueOf(lengthValue)==0))
				this.setContractLength();
			else
				this.setContractLength(lengthValue);
		}
		
	}
	
	protected Property[] WaitingListDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression("attr_8054",false));
	}
	
	public boolean isWaitingListDropDownListExist(){
		return browser.checkHtmlObjectExists(this.WaitingListDropDownList());
	}
	
	public boolean isTransferListExist(){
		return browser.checkHtmlObjectExists(this.TransferListDropDownList());
	}
	
	public boolean ListEntrywithSlipRes(){
		return browser.checkHtmlObjectExists(this.ListEntrywithSlipResDropDownList());
	}
	
	public void selectWaitingList(String waitingList){
		if(StringUtil.notEmpty(waitingList)){
			browser.selectDropdownList(WaitingListDropDownList(), waitingList, true);
		}
	}
	
	public String getWaitingList(){
		return browser.getDropdownListValue(WaitingListDropDownList(), 0);
	}

	protected Property[] TransferListDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression("attr_8055",false));
	}
	
	public void selectTransferList(String transferList){
		if(StringUtil.notEmpty(transferList)){
			browser.selectDropdownList(TransferListDropDownList(), transferList, true);
		}
	}
	
	public String getTransferList(){
		return browser.getDropdownListValue(TransferListDropDownList(), 0);
	}

	protected Property[] ListEntrywithSlipResDropDownList(){
		return Property.toPropertyArray(".id", new RegularExpression("attr_8056",false));
	}
	
	public void selectListEntrywithSlipRes(String listEntrywithSlipRes){;
		if(StringUtil.notEmpty(listEntrywithSlipRes)){
			browser.selectDropdownList(ListEntrywithSlipResDropDownList(), listEntrywithSlipRes, true);
		}
	}
	
	protected Property[] reservationSearchDefault(){
		return Property.addToPropertyArray(select(), ".id", "attr_10202");
	}
	
	public boolean isReservationSearchDefaultDropDownExist(){
		return browser.checkHtmlObjectExists(reservationSearchDefault());
	}
	
	public void selectReservationSearchDefault(String defaultVal){
		browser.selectDropdownList(reservationSearchDefault(), defaultVal);
	}
	
	public String getReservationSearchDefaultValue(){
		return browser.getDropdownListValue(reservationSearchDefault(),0);
	}
	
	public List<String> getAllReservationSearchDefaultValues(){
		return browser.getDropdownElements(reservationSearchDefault());
	}
	
	public String getListEntrywithSlipRes(){
		return browser.getDropdownListValue(ListEntrywithSlipResDropDownList(), 0);
	}
	
	public void setReferalPark(String park){
		browser.setTextField(".id", "referralparkinput", park, true);
	}
	
	public void clickAddReferralPark(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Facility Name.*",false));
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public void clickRemoveReferralPark(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	public List<String> getReferralFacilities(){
		List<String> facilities = browser.getDropdownElements(".id", "FacilityDetailView.parkReferrals");
		return facilities;
	}
	
	public void selectReferralFacility(String facilityName){
		browser.selectDropdownList(".id", "FacilityDetailView.parkReferrals", facilityName);
	}
	
	public void removeReferralFacility(String facilityName){
		this.selectReferralFacility(facilityName);
		this.clickRemoveReferralPark();
	}
	
	public void clearAllReferralFacilities(){
		List<String> refacilities = this.getReferralFacilities();
		for(String facility:refacilities){
			this.removeReferralFacility(facility);
		}
	}
	
	public void addReferralFacility(String facility){
		this.setReferalPark(facility);
		this.clickAddReferralPark();
	}
	
	public void setReferralFacilities(boolean clearOld, List<String> facilityNames){
		if(clearOld){
			this.clearAllReferralFacilities();
		}
		for(String facility:facilityNames){
			this.addReferralFacility(facility);
		}
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void updateReservationSearchDefault(String val){
		selectReservationSearchDefault(val);
		clickApply();
		
		waitLoading();
	}
}
