/*
 * Created on Mar 12, 2010
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager;

//import testAPI.dataCollection.orms.FacilityData;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * This page is used for adding a new facility, after the select location page.
 * @author QA
 */
public class InvMgrAddFacilityDetailsPage  extends InventoryManagerPage{
	private static InvMgrAddFacilityDetailsPage _instance = null;

	public static InvMgrAddFacilityDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrAddFacilityDetailsPage();
		}

		return _instance;
	}

	protected InvMgrAddFacilityDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(ok()) && browser.checkHtmlObjectExists(facilityName());
	}

	protected Property[] ok(){
		return Property.toPropertyArray(".text","OK",".href", new RegularExpression("(\"StoreFacilityDetailsOK\")|(#link)", false));
	}
	
	protected Property[] facilityName(){
		return Property.toPropertyArray(".id","FacilityDetailView.name");
	}
	/**
	 * Set facility name
	 * @param name
	 */
	public void setFacilityName(String name){
	  	browser.setTextField(facilityName(),name);
	}
	
	/**
	 * Set facility short name
	 * @param name
	 */
	public void setFacilityShortName(String name){
	  	browser.setTextField(".id","FacilityDetailView.shortName",name);
	}
	
	public void setFacilityDescription(String description){
	  	browser.setTextArea(".id","FacilityDetailView.description",description);
	}
	
	protected Property[] stateCountry(){                                        
		return Property.addToPropertyArray(select(),".id",new RegularExpression("FacilityDetailView-\\d+\\.stateCountry",false));
	}
	
	protected Property[] facilityState(){
		return Property.addToPropertyArray(select(),".id","FacilityDetailView.state");
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(stateCountry(), country);
	}
	
	public void selectFacilityState(String state){
		browser.selectDropdownList(facilityState(), state);
	}
	
	/**
	 * Set facility description
	 * @param description
	 */
	public void setAlternateFacilityID(String id){
		browser.setTextField(".id","FacilityDetailView.alternateLocID",id);
	}
	
	public boolean checkAlternateFacilityIDExist(){
		return browser.checkHtmlObjectExists(".id","FacilityDetailView.alternateLocID");
	}
	
	public boolean checkShortNameEdit(){
		IHtmlObject[] objs=browser.getHtmlObject(".id","FacilityDetailView.shortName");
		if(objs.length<1){
			throw new ErrorOnPageException("can't find Alternate facility id object.");
		}
		return Boolean.parseBoolean(objs[0].getProperty(".isContentEditable"));
	}
	
	public boolean checkAlternateFacilityIDReadOnly(){
		IHtmlObject[] objs=browser.getHtmlObject(".id","FacilityDetailView.alternateLocID");
		if(objs.length<1){
			throw new ErrorOnPageException("can't find Alternate facility id object.");
		}
		return Boolean.parseBoolean(objs[0].getProperty(".isContentEditable"));
	}
	
	public String getAlternateFacilityID(){
		return browser.getTextFieldValue(".id","FacilityDetailView.alternateLocID");
	}
	
	/**
	 * Select facility status
	 * @param status
	 */
	public void selectFacilityStatus(String status){
	  	browser.selectDropdownList(".id","FacilityDetailView.locationStatus",status);
	}
	
	/**
	 * Select facility production category
	 * @param category
	 */
	public void selectProductCategory(String category){
	  	browser.selectDropdownList(".id","productCategory",category);
	}
	
	public void setMailingAddress(String address) {
		browser.setTextField(".id", "FacilityDetailView.streetName", address);
	}
	
	public void setMailingCityTown(String city) {
		browser.setTextField(".id", "FacilityDetailView.cityTown", city);
	}
	
	public void setMailingZipCode(String zipcode) {
		browser.setTextField(".id", "FacilityDetailView.zipPostal", zipcode);
	}
	
	/**
	 * Select mailing address state
	 * @param state
	 */
	public void selectMailingState(String state){
	  	browser.selectDropdownList(".id","FacilityDetailView.stateProvince",state);
	}
	
	/**
	 * Select mailing address country
	 * @param country
	 */
	public void selectMailingCountry(String country){
	  	browser.selectDropdownList(".id",new RegularExpression("FacilityDetailView-\\d+\\.country", false),country);
	}
	
	public void setDirectLine(String directLine) {
		browser.setTextField(".id", "FacilityDetailView.directLine", directLine);
	}
	
	public void setPublicLine(String publicLine) {
		browser.setTextField(".id", "FacilityDetailView.publicLine", publicLine);
	}
	
	public void setFax(String fax) {
		browser.setTextField(".id", "FacilityDetailView.fax", fax);
	}
	
	public void setEmail(String email) {
		browser.setTextField(".id", "FacilityDetailView.email", email);
	}
	
	public void setHTTP(String webSite) {
		browser.setTextField(".id", "FacilityDetailView.http", webSite);
	}
	
	public void setPrimaryPOCLastName(String lastName) {
		browser.setTextField(".id", "FacilityDetailView.pOCLName", lastName);
	}
	
	public void setPrimaryPOCFirstName(String firstName) {
		browser.setTextField(".id", "FacilityDetailView.pOCFName", firstName);
	}
	
	public void setPrimaryPOCPhone(String phone) {
		browser.setTextField(".id", "FacilityDetailView.pOCPhone", phone);
	}
	
	public void setPrimaryPOCFax(String fax) {
		browser.setTextField(".id", "FacilityDetailView.pOCFax", fax);
	}
	
	public void setPrimaryPOCEmail(String email) {
		browser.setTextField(".id", "FacilityDetailView.pOCEmail", email);
	}
	
	public void setPrimaryPOCAddress(String address) {
		browser.setTextField(".id", "FacilityDetailView.pOCStreetName", address);
	}
	
	public void setPrimaryPOCCityTown(String city) {
		browser.setTextField(".id", "FacilityDetailView.pOCCityTown", city);
	}
	
	public void selectPrimaryPOCState(String state) {
		browser.selectDropdownList(".id", "FacilityDetailView.pOCStateProvince", state);
	}
	
	public void setPrimaryPOCZipCode(String zipCode) {
		browser.setTextField(".id", "FacilityDetailView.pOCZipPostal", zipCode);
	}
	
	public void selectPrimaryPOCCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression("FacilityDetailView-\\d+\\.pOCCountry", false), country);
	}
	
	public void setOtherPOCLastName(String lastName) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCLName", lastName);
	}
	
	public void setOtherPOCFirstName(String firstName) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCFName", firstName);
	}
	
	public void setOtherPOCPhone(String phone) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCPhone", phone);
	}
	
	public void setOtherPOCFax(String fax) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCFax", fax);
	}
	
	public void setOtherPOCEmail(String email) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCEmail", email);
	}
	
	public void setOtherPOCAddress(String address) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCStreetName", address);
	}
	
	public void setOtherPOCCityTown(String city) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCCityTown", city);
	}
	
	public void selectOtherPOCState(String state) {
		browser.selectDropdownList(".id", "FacilityDetailView.otherPOCStateProvince", state);
	}
	
	public void setOtherPOCZipCode(String zipCode) {
		browser.setTextField(".id", "FacilityDetailView.otherPOCZipPostal", zipCode);
	}
	
	public void selectOtherPOCCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression("FacilityDetailView-\\d+\\.otherPOCCountry", false), country);
	}
	
	public void selectTimeZone(String timezone) {
		browser.selectDropdownList(".id", "FacilityDetailView.timeZone", timezone);
	}
	
	public void selectGeographicRegion(String geographicRegion) {
		browser.selectDropdownList(".id", "FacilityDetailView.geographicRegion", geographicRegion);
	}
	
	public List<String> getGeographicRegionElements() {
		return browser.getDropdownElements(".id", "FacilityDetailView.geographicRegion");
	}
	
	public void setAlias(String alias) {
		browser.setTextArea(".id", "attr_100000", alias);
	}
	
	/**
	 * Set brochure info
	 * @param info
	 */
	public void setBrochureDescription(String dscr){
	  	browser.setTextArea(".id", "attr_304", dscr);
	}
	
	public void setBrochureGeograhy(String value) {
		browser.setTextArea(".id", "attr_10100", value);
	}
	
	public void setBrochureRecreation(String value) {
		browser.setTextArea(".id", "attr_10101", value);
	}
	
	public void setBrochureFacilities(String value) {
		browser.setTextArea(".id", "attr_10102", value);
	}
	
	public void setBrochureNearbyAttractions(String value) {
		browser.setTextArea(".id", "attr_10103", value);
	}
	
	/**
	 * Select the concessionaire value
	 * @param concessionaire: true-Yes,false-No
	 */
	public void selectConcessionaire(boolean concessionaire) {
		browser.selectDropdownList(".id", "attr_10401", concessionaire ? "Yes" : "No");
	}
	
	public void setCheckinTime(String ckeckinTime) {
		browser.setTextField(".id", "attr_15", ckeckinTime);
	}
	
	public void selectCheckinAmPm(String value) {
		browser.selectDropdownList(".id", "attr_15_ispm", value);
	}
	
	public void setCheckoutTime(String checkoutTime) {
		browser.setTextField(".id", "attr_16", checkoutTime);
	}
	
	public void selectCheckoutAmPm(String value) {
		browser.selectDropdownList(".id", "attr_16_ispm", value);
	}
	
	/**
	 * Set driving direction
	 * @param direction
	 */
	public void setDriveDirection(String direction){
	  	browser.setTextArea(".id","attr_100001",direction);
	}
	
	public void setFeeAndCancellationDescription(String dscr) {
		browser.setTextArea(".id", "attr_10216", dscr);
	}
	
	public void selectCollectCreditCardInfoDuringCheckin(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_10210", yesOrNo ? "Yes" : "No");
	}
	
	public void selectUsesFieldApplication(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_10400", yesOrNo ? "Yes" : "No");
	}
	
	public void selectAllowPreCheckIn(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_2401", yesOrNo ? "Yes" : "No");
	}
	
	public void selectCheckinListSortOrder(String order) {
		browser.selectDropdownList(".id", "attr_10200", order);
	}
	
	public void selectCheckoutListSortOrder(String order) {
		browser.selectDropdownList(".id", "attr_10201", order);
	}
	
	public void selectPageDefaultRowCount(String rowCount) {
		browser.selectDropdownList(".id", "attr_10300", rowCount);
	}
	
	public void selectReservationSearchDefault(String type) {
		browser.selectDropdownList(".id", "attr_10202", type);
	}
	
	public void selectFinancialSessionType(String type) {
		browser.selectDropdownList(".id", "attr_90651", type);
	}
	
	public void selectOpeningFloatRequired(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_155", yesOrNo ? "Yes" : "No");
	}
	
	public void selectCloseBlindly(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_157", yesOrNo ? "Yes" : "No");
	}
	
	public void selectTransmittals(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_77", yesOrNo ? "Yes" : "No");
	}
	
	public void selectTransmittalTraceNumRequired(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_78", yesOrNo ? "Yes" : "No");
	}
	
	/**
	 * select latitude direction
	 * @param latitudeDirection
	 */
	public void selectLatitudeDirection(String latitudeDirection) {
		browser.selectDropdownList(".id","attr_100014",latitudeDirection,true);		
	}
	
	/**
	 * set latitude degree
	 * @param latDegree
	 */
	public void setLatitudeDegree(String latDegree){
		browser.setTextField(".id", "attr_100010", latDegree);
	}
	
	/**
	 * set latitude minutes
	 * @param latMinute
	 */
	public void setLatitudeMinute(String latMinute){
		browser.setTextField(".id", "attr_100011", latMinute);
	}
	
	/**
	 * set latitude seconds
	 * @param latSeconds
	 */
	public void setLatitudeSeconds(String latSeconds){
		browser.setTextField(".id", "attr_100012", latSeconds);
	}
	
	/**
	 * select longitude direction
	 * @param longitudeDirection
	 */
	public void selectLongitudeDirection(String longitudeDirection) {
		browser.selectDropdownList(".id","attr_100013",longitudeDirection,true);		
	}
	
	/**
	 * set longitude degree
	 * @param logDegree
	 */
	public void setLongitudeDegree(String logDegree){
		browser.setTextField(".id", "attr_100007", logDegree);
	}
	
	/**
	 * set longitude minutes
	 * @param logMinute
	 */
	public void setLongitudeMinute(String logMinute){
		browser.setTextField(".id", "attr_100008", logMinute);
	}
	
	/**
	 * set longitude seconds
	 * @param logSeconds
	 */
	public void setLongitudeSeconds(String logSeconds){
		browser.setTextField(".id", "attr_100009", logSeconds);
	}
	
	/**
	 * Set important info
	 * @param info - important info
	 */
	public void setImportantInfo(String info){
	  	browser.setTextArea(".id","attr_100002",info);
	}
	
	public void setOtherPhoneNumbers(String numbers) {
		browser.setTextArea(".id", "attr_525", numbers);
	}
	
	public void setRadioChannel(String channel){
		browser.setTextArea(".id","attr_8009", channel);
	}
	
	public String getRadioChannel(){
		return browser.getTextAreaValue(".id","attr_8009");
	}
	
	public void selectPopularFacility(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_10105", yesOrNo ? "Yes" : "No");
	}
	
	public void setReceiptCopiesNum(String num) {
		browser.setTextField(".id", "attr_150", num);
	}
	
	/**
	 * Set a facility name to add to referral facilities
	 * @param name
	 */
	public void setReferralFacilityName(String name) {
		browser.setTextField(".id", "referralparkinput", name);
	}
	
	/**
	 * Click 'Add' button to add a facility name to the Referral Facilities Text Area in left
	 */
	public void addReferralFacility() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("referralparkinput", false));
	}
	
	/**
	 * Click 'Remove' button to remove a selected facility from the Referral Facilities Text Area left
	 */
	public void removeReferralFacility() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("removeSelectedOptions", false));
	}
	
	public void setBookingWindowDesc(String desc) {
		browser.setTextArea(".id", "attr_10218", desc);
	}
	
	public void setSeasonDateDesc(String desc) {
		browser.setTextArea(".id", "attr_10217", desc);
	}
	
	public void selectHiddenOnWebSearch(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_381", yesOrNo ? "Yes" : "No");
	}
	
	public void selectPOSQuickSale(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_2766", yesOrNo ? "Yes" : "No");
	}
	
	public void selectHideFacilityLocationOnWeb(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_380", yesOrNo ? "Yes" : "No");
	}
	
	public void selectShowExitAlerts(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_4100", yesOrNo ? "Yes" : "No");
	}
	
	public void selectCommercialReservationsMadeFor(String makeFor) {
		browser.selectDropdownList(".id", "attr_100126", makeFor);
	}
	
	public void setConfirmationPeriod(String period) {
		browser.setTextField(".id", "attr_4103", period);
	}
	
	public void setConfirmationPeriodStartTime(String startTime) {
		browser.setTextField(".id", "attr_4104", startTime);
	}
	
	public void selectConfirmationPeriodStartTimeAmPm(String amOrPm) {
		browser.selectDropdownList(".id", "attr_4104_ispm", amOrPm);
	}
	
	public void selectDisplayAvailabilityOnButtonCaption(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_4102", yesOrNo ? "Yes" : "No");
	}
	
	public void setOnlinePermitPrintPeriod(String period) {
		browser.setTextField(".id", "attr_4105", period);
	}
	
	public void setOnlinePermitPrintPeriodStartTime(String startTime) {
		browser.setTextField(".id", "attr_4106", startTime);
	}
	
	public void selectOnlinePermitPrintPeriodStartTimeAmPm(String amOrPm) {
		browser.selectDropdownList(".id", "attr_4106_ispm", amOrPm);
	}
	
	public void selectPermitInventoryAllocationsApplicable(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_100125", yesOrNo ? "Yes" : "No");
	}
	
	public void selectRandomReleaseOfInventoryAppliesToRevokes(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_4109", yesOrNo ? "Yes" : "No");
	}
	
	public void selectWalkinAllocationsIncludeAdvancedAllocations(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_4101", yesOrNo ? "Yes" : "No");
	}
	
	public void setPrintPermitInformation(String info) {
		browser.setTextArea(".id", "attr_2370", info);
	}
	
	public void setWebFacilitySpecialMessage(String messsage) {
		browser.setTextArea(".id", "attr_100147", messsage);
	}
	
	public void selectADAAccessible(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_10020", yesOrNo ? "Yes" : "No");
	}
	
	public void setAvailabilityLagTime(String time) {
		browser.setTextField(".id", "attr_10021", time);
	}
	
	public void setMaximumGenericTickets(String num) {
		browser.setTextField(".id", "attr_10025", num);
	}
	
	public void setMaximumUnsoldTickets(String num) {
		browser.setTextField(".id", "attr_10026", num);
	}
	
	public void setFacilityFeesURL(String url) {
		browser.setTextField(".id", "attr_10022", url);
	}
	
	public void selectSignatureLineOnTicketReceipt(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_10015", yesOrNo ? "Yes" : "No");
	}
	
	public void selectAutoInvalidateTickets(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_200200", yesOrNo ? "Yes" : "No");
	}
	
	public void selectTicketMailOut(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_200100", yesOrNo ? "Yes" : "No");
	}
	
	public void setTourFacilitySaleType(String type) {
		browser.setTextField(".id", "attr_10030", type);
	}
	
	public void selectPersonalCheckPaymentForGeneralPublicPOSOrders(boolean yesOrNo) {
		browser.selectDropdownList(".id", "attr_10106", yesOrNo ? "Yes" : "No");
	}
	
	/** Click on Add button to add facility product category*/
	public void clickAdd(){
	  	browser.clickGuiObject(".class","Html.A",".text","Add");
	}
	
	/** Click on OK button*/
	public void clickOK(){
	  	browser.clickGuiObject(".class","Html.A",".text","OK");
	}

	/** Click on Cancel button*/
	public void clickCancel(){
	  	browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}

	/** Click on Apply button*/
	public void clickApply(){
	  	browser.clickGuiObject(".class","Html.A",".text","Apply");
	}
	
	/**
	 * Set up facility details info
	 * @param fd - facility data
	 */
	public void setupFacilityDetailsData(FacilityData fd) {
		logger.info("Setup facility information...");
	  	this.setFacilityName(fd.facilityName);
		if(null != fd.shortName && fd.shortName.length()>0 && this.checkShortNameEdit()){
		  	this.setFacilityShortName(fd.shortName);
	  	}
		this.selectFacilityStatus(fd.status);
	  	this.setFacilityDescription(fd.description);
	  	if(StringUtil.isEmpty(fd.country)){ //The 'Facility State/Province' section is new added in 3.06, it is come from the mail address, and can not be empty
	  		fd.country = fd.mailingCountry;
	  	}
		if(StringUtil.isEmpty(fd.facility_state)){ //Add this to make sure there is value for old cases which are not set fd.country and fd.facility_state
	  		fd.facility_state = fd.mailingState;
	  	}
	  	selectCountry(fd.country);
	  	ajax.waitLoading();
	  	selectFacilityState(fd.facility_state);
	  	if(this.checkAlternateFacilityIDExist()){
	  		this.setAlternateFacilityID(fd.alternateFacilityID);
	  	}
	  	if(null != fd.productCategory && fd.productCategory.length()>0){
		  	this.selectProductCategory(fd.productCategory);
		  	this.waitLoading();
	  	}
	  	if(null != fd.mailingAddress && fd.mailingAddress.length() > 0) {
	  		this.setMailingAddress(fd.mailingAddress);
	  	}
	  	if(null != fd.mailingCityTown && fd.mailingCityTown.length() > 0) {
	  		this.setMailingCityTown(fd.mailingCityTown);
	  	}
	  	
	  	if(null != fd.mailingZipCode && fd.mailingZipCode.length() > 0) {
	  		this.setMailingZipCode(fd.mailingZipCode);
	  	}
	  	this.selectMailingCountry(fd.mailingCountry);
	  	ajax.waitLoading();
	  	this.selectMailingState(fd.mailingState);
	  	if(null != fd.directLine && fd.directLine.length() > 0) {
	  		this.setDirectLine(fd.directLine);
	  	}
	  	if(null != fd.publicLine && fd.publicLine.length() > 0) {
	  		this.setPublicLine(fd.publicLine);
	  	}
	  	if(null != fd.fax && fd.fax.length() > 0) {
	  		this.setFax(fd.fax);
	  	}
	  	if(null != fd.email && fd.email.length() > 0) {
	  		this.setEmail(fd.email);
	  	}
	  	if(null != fd.http && fd.http.length() > 0) {
	  		this.setHTTP(fd.http);
	  	}
	  	if(null != fd.primaryPOCLastName && fd.primaryPOCLastName.length() > 0) {
	  		this.setPrimaryPOCLastName(fd.primaryPOCLastName);
	  	}
	  	if(null != fd.primaryPOCFirstName && fd.primaryPOCFirstName.length() > 0) {
	  		this.setPrimaryPOCFirstName(fd.primaryPOCFirstName);
	  	}
	  	if(null != fd.primaryPOCPhone && fd.primaryPOCPhone.length() > 0) {
	  		this.setPrimaryPOCPhone(fd.primaryPOCPhone);
	  	}
	  	if(null != fd.primaryPOCFax && fd.primaryPOCFax.length() > 0) {
	  		this.setPrimaryPOCFax(fd.primaryPOCFax);
	  	}
	  	if(null != fd.primaryPOCEmail && fd.primaryPOCEmail.length() > 0) {
	  		this.setPrimaryPOCEmail(fd.primaryPOCEmail);
	  	}
	  	if(null != fd.primaryPOCAddress && fd.primaryPOCAddress.length() > 0) {
	  		this.setPrimaryPOCAddress(fd.primaryPOCAddress);
	  	}
	  	if(null != fd.primaryPOCCityTown && fd.primaryPOCCityTown.length() > 0) {
	  		this.setPrimaryPOCCityTown(fd.primaryPOCCityTown);
	  	}
	  	if(null != fd.primaryPOCZipCode && fd.primaryPOCZipCode.length() > 0) {
	  		this.setPrimaryPOCZipCode(fd.primaryPOCZipCode);
	  	}
  		this.selectPrimaryPOCCountry(fd.primaryPOCCountry);
  		ajax.waitLoading();
  		if(null != fd.primaryPOCState && fd.primaryPOCState.length() > 0) {
	  		this.selectPrimaryPOCState(fd.primaryPOCState);
	  	}
  		if(null != fd.otherPOCLastName && fd.otherPOCLastName.length() > 0) {
	  		this.setOtherPOCLastName(fd.otherPOCLastName);
	  	}
	  	if(null != fd.otherPOCFirstName && fd.otherPOCFirstName.length() > 0) {
	  		this.setOtherPOCFirstName(fd.otherPOCFirstName);
	  	}
	  	if(null != fd.otherPOCPhone && fd.otherPOCPhone.length() > 0) {
	  		this.setOtherPOCPhone(fd.otherPOCPhone);
	  	}
	  	if(null != fd.otherPOCFax && fd.otherPOCFax.length() > 0) {
	  		this.setOtherPOCFax(fd.otherPOCFax);
	  	}
	  	if(null != fd.otherPOCEmail && fd.otherPOCEmail.length() > 0) {
	  		this.setOtherPOCEmail(fd.otherPOCEmail);
	  	}
	  	if(null != fd.otherPOCAddress && fd.otherPOCAddress.length() > 0) {
	  		this.setOtherPOCAddress(fd.otherPOCAddress);
	  	}
	  	if(null != fd.otherPOCCityTown && fd.otherPOCCityTown.length() > 0) {
	  		this.setOtherPOCCityTown(fd.otherPOCCityTown);
	  	}
	  	if(null != fd.otherPOCZipCode && fd.otherPOCZipCode.length() > 0) {
	  		this.setOtherPOCZipCode(fd.otherPOCZipCode);
	  	}
	  	this.selectOtherPOCCountry(fd.otherPOCCountry);
	  	ajax.waitLoading();
		if(null != fd.otherPOCState && fd.otherPOCState.length() > 0) {
	  		this.selectOtherPOCState(fd.otherPOCState);
	  	}
	  	if(null != fd.timeZone && fd.timeZone.length() > 0) {
	  		this.selectTimeZone(fd.timeZone);
	  	}
	  	if(null != fd.geographicRegion && fd.geographicRegion.length() > 0 && this.getGeographicRegionElements().size() > 0) {
	  		this.selectGeographicRegion(fd.geographicRegion);
	  	}
	  	
	  	/*
	  	 * facility attributes details info
	  	 */
	  	if(!fd.productCategory.equals("Service") && !fd.productCategory.equals("Lottery") && !fd.productCategory.equals("Supply")) {
	  		this.selectCloseBlindly(fd.closeBlindly);
	  		if(!fd.productCategory.equals("GiftCard") && !fd.productCategory.equals("Privilege") && !fd.productCategory.equals("VehicleRTI")) {
	  			//below attributes are common for: Asset, Site, Permit, Ticket, POS
	  			if(null != fd.alias && fd.alias.length() > 0) {
	  		  		this.setAlias(fd.alias);
	  		  	}
	  		  	this.setBrochureDescription(fd.brochureDescription);
  		  		this.selectConcessionaire(fd.concessionaire);
	  		  	this.setDriveDirection(fd.drivingDirection);
	  		  	this.selectUsesFieldApplication(fd.usesFieldApplication);
		  		if(null != fd.pageDefaultRowCount && fd.pageDefaultRowCount.length() > 0) {
		  			this.selectPageDefaultRowCount(fd.pageDefaultRowCount);
		  	  	}
		  		if(null != fd.financialSessionType && fd.financialSessionType.length() > 0) {
			  		this.selectFinancialSessionType(fd.financialSessionType);
			  	}
		  		if(null != fd.latitudeDirection && fd.latitudeDirection.length() > 0) {
					this.selectLatitudeDirection(fd.latitudeDirection);
					if(null != fd.latitudeDegrees && fd.latitudeDegrees.length() > 0) {
						this.setLatitudeDegree(fd.latitudeDegrees);
					}
					if(null != fd.latitudeMinutes && fd.latitudeMinutes.length() > 0) {
						this.setLatitudeMinute(fd.latitudeMinutes);
					}
					if(null != fd.latitudeSeconds && fd.latitudeSeconds.length() > 0) {
						this.setLatitudeSeconds(fd.latitudeSeconds);
					}
				}
				if(null != fd.longitudeDirection && fd.longitudeDirection.length() > 0) {
					this.selectLongitudeDirection(fd.longitudeDirection);
					if(null != fd.longitudeDegrees && fd.longitudeDegrees.length() > 0) {
						this.setLongitudeDegree(fd.longitudeDegrees);
					}
					if(null != fd.longitudeMinutes && fd.longitudeMinutes.length() > 0) {
						this.setLongitudeMinute(fd.longitudeMinutes);
					}
					if(null != fd.longitudeSeconds && fd.longitudeSeconds.length() > 0) {
						this.setLongitudeSeconds(fd.longitudeSeconds);
					}
				}
				this.setImportantInfo(fd.importantInfo);
				
				//below attributes are common for: Site, Permit, Ticket, POS
				if(!fd.productCategory.equals("Asset")) {
					this.selectOpeningFloatRequired(fd.openingFloatRequired);
				  	this.selectTransmittals(fd.transmittals);
				  	this.selectTransmittalTraceNumRequired(fd.transmittalTraceNumRequired);
					
				  //below attributes are common for: Site, Permit, Ticket
				  	if(!fd.productCategory.equals("POS")) {
				  		this.selectHiddenOnWebSearch(fd.hiddenOnWebSearch);
				  		
				  		if(fd.productCategory.equals("Site")) {
				  			//attribute for Site
					  		this.setupFacilityAttributesForSite(fd);
					  	} else if(fd.productCategory.equals("Permit")) {
					  		//attribute for Permit
					  		this.setupFacilityAttributesForPermit(fd);
					  	} else if(fd.productCategory.equals("Ticket")) {
					  	//attribute for Ticket
					  		this.setupFacilityAttributesForTicket(fd);
					  	} else if(fd.productCategory.equals("Activity")){
					  		this.setupFacilityAttributesForActivity(fd);
					  	} else throw new ErrorOnPageException("Unkonw product category. Please double check it.");
				  	} else {
				  		//attribute for POS
				  		this.selectPersonalCheckPaymentForGeneralPublicPOSOrders(fd.personalCheckPaymentForGeneralPublicPOSOrders);
				  	}
				}
	  		}
	  	}
	}
	
	private void setupFacilityAttributesForSite(FacilityData fd) {
		logger.info("Setup facility attributes details info for Site product category.");
	  	if(null != fd.brochureGeography && fd.brochureGeography.length() > 0) {
	  		this.setBrochureGeograhy(fd.brochureGeography);
	  	}
	  	if(null != fd.brochureRecreation && fd.brochureRecreation.length() > 0) {
	  		this.setBrochureRecreation(fd.brochureRecreation);
	  	}
	  	if(null != fd.brochureFacilities && fd.brochureFacilities.length() > 0) {
	  		this.setBrochureFacilities(fd.brochureFacilities);
	  	}
	  	if(null != fd.brochureNearbyAttractions && fd.brochureNearbyAttractions.length() > 0) {
	  		this.setBrochureNearbyAttractions(fd.brochureNearbyAttractions);
	  	}
	  	if(null != fd.checkinTime && fd.checkinTime.length() > 0) {
	  		this.setCheckinTime(fd.checkinTime);
	  		if(null != fd.checkinTimeAmPm && fd.checkinTimeAmPm.length() > 0) {
	  			this.selectCheckinAmPm(fd.checkinTimeAmPm);
	  		}
	  	}
	  	if(null != fd.checkoutTime && fd.checkoutTime.length() > 0) {
	  		this.setCheckoutTime(fd.checkoutTime);
	  		if(null != fd.checkoutTimeAmPm && fd.checkoutTimeAmPm.length() > 0) {
	  			this.selectCheckoutAmPm(fd.checkoutTimeAmPm);
	  		}
	  	}
	  	if(null != fd.feeAndCancellationDescription && fd.feeAndCancellationDescription.length() > 0) {
	  		this.setFeeAndCancellationDescription(fd.feeAndCancellationDescription);
	  	}
	  	this.selectCollectCreditCardInfoDuringCheckin(fd.collectCreditCardInfoDuringCheckin);
	  	this.selectAllowPreCheckIn(fd.allowPreCheckIn);
	  	if(null != fd.checkinListSortOrder && fd.checkinListSortOrder.length() > 0) {
	  		this.selectCheckinListSortOrder(fd.checkinListSortOrder);
	  	}
	  	if(null != fd.checkoutListSortOrder && fd.checkoutListSortOrder.length() > 0) {
	  		this.selectCheckoutListSortOrder(fd.checkoutListSortOrder);
	  	}
	  	if(null != fd.reservationSearchDefault && fd.reservationSearchDefault.length() > 0) {
	  		this.selectReservationSearchDefault(fd.reservationSearchDefault);
	  	}
	  	if(null != fd.otherPhoneNumbers && fd.otherPhoneNumbers.length() > 0) {
	  		this.setOtherPhoneNumbers(fd.otherPhoneNumbers);
	  	}
	  	this.selectPopularFacility(fd.popularFacility);
	  	if(null != fd.receiptCopiesNum && fd.receiptCopiesNum.length() > 0) {
	  		this.setReceiptCopiesNum(fd.receiptCopiesNum);
	  	}
	  	//add facility to Referral Facilities Text Area
	  	if(null != fd.referralFacilities && fd.referralFacilities.length > 0) {
	  		for(int i = 0; i < fd.referralFacilities.length; i ++) {
	  			this.setReferralFacilityName(fd.referralFacilities[i]);
	  			this.addReferralFacility();
	  		}
	  	}
	  	if(null != fd.bookingWindowDesc && fd.bookingWindowDesc.length() > 0) {
	  		this.setBookingWindowDesc(fd.bookingWindowDesc);
	  	}
	  	if(null != fd.seasonDateDesc && fd.seasonDateDesc.length() > 0) {
	  		this.setSeasonDateDesc(fd.seasonDateDesc);
	  	}
	  	this.selectHideFacilityLocationOnWeb(fd.hideFacilityLocationOnWeb);
	}
	
	private void setupFacilityAttributesForPermit(FacilityData fd) {
		logger.info("Setup facility attributes details info for Permit product category.");
		this.selectShowExitAlerts(fd.showExitAlerts);
		if(null != fd.commercialReservationsMadeFor && fd.commercialReservationsMadeFor.length() > 0) {
			this.selectCommercialReservationsMadeFor(fd.commercialReservationsMadeFor);
		}
		if(null != fd.confirmationPeriod && fd.confirmationPeriod.length() > 0) {
			this.setConfirmationPeriod(fd.confirmationPeriod);
		}
		if(null != fd.confirmationPeriodStartTime && fd.confirmationPeriodStartTime.length() > 0) {
			this.setConfirmationPeriodStartTime(fd.confirmationPeriodStartTime);
			if(null != fd.confirmationPeriodStartTimeAmPm && fd.confirmationPeriodStartTimeAmPm.length() > 0) {
				this.selectConfirmationPeriodStartTimeAmPm(fd.confirmationPeriodStartTimeAmPm);
			}
		}
		this.selectDisplayAvailabilityOnButtonCaption(fd.displayAvailabilityOnButtonCaption);
		if(null != fd.onlinePermitPrintPeriod && fd.onlinePermitPrintPeriod.length() > 0) {
			this.setOnlinePermitPrintPeriod(fd.onlinePermitPrintPeriod);
		}
		if(null != fd.onlinePermitPrintPeriodStartTime && fd.onlinePermitPrintPeriodStartTime.length() > 0) {
			this.setOnlinePermitPrintPeriodStartTime(fd.onlinePermitPrintPeriodStartTime);
			if(null != fd.onlinePermitPrintPeriodStartTimeAmPm && fd.onlinePermitPrintPeriodStartTimeAmPm.length() > 0) {
				this.selectOnlinePermitPrintPeriodStartTimeAmPm(fd.onlinePermitPrintPeriodStartTimeAmPm);
			}
		}
		this.selectPermitInventoryAllocationsApplicable(fd.permitInventoryAllocationsApplicable);
		this.selectRandomReleaseOfInventoryAppliesToRevokes(fd.randomReleaseOfInventoryAppliesToRevokes);
		this.selectWalkinAllocationsIncludeAdvancedAllocations(fd.walkinAllocationsIncludeAdvancedAllocations);
		if(null != fd.printPermitInformation && fd.printPermitInformation.length() > 0) {
			this.setPrintPermitInformation(fd.printPermitInformation);
		}
		if(null != fd.webFacilitySpecialMessage && fd.webFacilitySpecialMessage.length() > 0) {
			this.setWebFacilitySpecialMessage(fd.webFacilitySpecialMessage);
		}
	}
	
	private void setupFacilityAttributesForTicket(FacilityData fd) {
		logger.info("Setup facility attributes details info for Ticket product category.");
		this.selectADAAccessible(fd.adaAccessible);
		if(null != fd.availabilityLagTime && fd.availabilityLagTime.length() > 0) {
			this.setAvailabilityLagTime(fd.availabilityLagTime);
		}
		if(null != fd.maximumGenericTickets && fd.maximumGenericTickets.length() > 0) {
			this.setMaximumGenericTickets(fd.maximumGenericTickets);
		}
		if(null != fd.maximumUnsoldTickets && fd.maximumUnsoldTickets.length() > 0) {
			this.setMaximumUnsoldTickets(fd.maximumUnsoldTickets);
		}
		if(null != fd.facilityFeesURL && fd.facilityFeesURL.length() > 0) {
			this.setFacilityFeesURL(fd.facilityFeesURL);
		}
		this.selectSignatureLineOnTicketReceipt(fd.signatureLineOnTicketReceipt);
		this.selectAutoInvalidateTickets(fd.autoInvalidateTickets);
		this.selectTicketMailOut(fd.ticketMailOut);
		if(null != fd.tourFacilitySaleType && fd.tourFacilitySaleType.length() > 0) {
			this.setTourFacilitySaleType(fd.tourFacilitySaleType);
		}
	}
	
	private void setupFacilityAttributesForActivity(FacilityData fd) {
		logger.info("Setup facility attributes details info for Activity product category.");
	  	if(fd.productCategory.equals("Activity")){
	  		selectEnforceMinToConfirmRule(fd.enforceMinimumToConfirmRuleInField);
	  	}
	}
	
	/**
	 * Get the facility name
	 * @return
	 */
	public String getFacilityName() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.name");
	}
	
	/**
	 * Get the facility short name
	 * @return
	 */
	public String getShortName() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.shortName");
	}
	/**
	 * Retrieve the facility id
	 * @return - facility id
	 */
	public String getFacilityID(){
	  	return browser.getTextFieldValue(".id","FacilityDetailView.id");
	}
	
	/**
	 * Get the facility status
	 */
	public String getFacilityStatus() {
		return browser.getDropdownListValue(".id", "FacilityDetailView.locationStatus");
	}
	
	/**
	 * Get the facility description
	 * @return
	 */
	public String getDescription() {
		return browser.getObjectText(".id", "FacilityDetailView.description");
	}
	
	/**
	 * Get the facility supported product category
	 * @return
	 */
	public String getProductCategory() {
		return browser.getDropdownListValue(".id", "productCategory");
	}
	
	public String getMailingAddress() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.streetName");
	}
	
	public String getMailingCityTown(){
		return browser.getTextFieldValue(".id", "FacilityDetailView.cityTown");
	}
	
	public String getMailingState() {
		return browser.getDropdownListValue(".id", "FacilityDetailView.stateProvince");
	}
	
	public String getMailingZipCode() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.zipPostal");
	}
	
	public String getMailingCountry() {
		return browser.getDropdownListValue(".id",  new RegularExpression("FacilityDetailView-\\d+\\.country", false));
	}
	
	public String getDirectLine() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.directLine");
	}
	
	public String getPublicLine() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.publicLine");
	}
	
	public String getFax() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.fax");
	}
	
	public String getEmail() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.email");
	}
	
	public String getHTTP() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.http");
	}
	
	public String getPrimaryPOCLastName() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCLName");
	}
	
	public String getPrimaryPOCFirstName() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCFName");
	}
	
	public String getPrimaryPOCPhone() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCPhone");
	}
	
	public String getPrimaryPOCFax() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCFax");
	}
	
	public String getPrimaryPOCEmail() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCEmail");
	}
	
	public String getPrimaryPOCAddress() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCStreetName");
	}
	
	public String getPrimaryPOCCityTown() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCCityTown");
	}
	
	public String getPrimaryPOCState() {
		return browser.getDropdownListValue(".id", "FacilityDetailView.pOCStateProvince");
	}
	
	public String getPrimaryPOCZipCode() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.pOCZipPostal");
	}
	
	public String getPrimaryPOCCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression("FacilityDetailView-\\d+\\.pOCCountry", false));
	}
	
	public String getOtherPOCLastName() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCLName");
	}
	
	public String getOtherPOCFirstName() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCFName");
	}
	
	public String getOtherPOCPhone() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCPhone");
	}
	
	public String getOtherPOCFax() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCFax");
	}
	
	public String getOtherPOCEmail() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCEmail");
	}
	
	public String getOtherPOCAddress() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCStreetName");
	}
	
	public String getOtherPOCCityTown() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCCityTown");
	}
	
	public String getOtherPOCState() {
		return browser.getDropdownListValue(".id", "FacilityDetailView.otherPOCStateProvince");
	}
	
	public String getOtherPOCZipCode() {
		return browser.getTextFieldValue(".id", "FacilityDetailView.otherPOCZipPostal");
	}
	
	public String getOtherPOCCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression("FacilityDetailView-\\d+\\.otherPOCCountry", false));
	}
	
	public String getTimeZone() {
		return browser.getDropdownListValue(".id", "FacilityDetailView.timeZone");
	}
	
	public String getGeographicRegion() {
		return browser.getDropdownListValue(".id", "FacilityDetailView.geographicRegion");
	}
	
	public String getAlias() {
		return browser.getObjectText(".id", "attr_100000");
	}
	
	public String getBrochureDescription() {
		return browser.getObjectText(".id", "attr_304");
	}
	
	public String getBrochureGeography() {
		return browser.getObjectText(".id", "attr_10100");
	}
	
	public String getBrochureRecreation() {
		return browser.getObjectText(".id", "attr_10101");
	}
	
	public String getBrochureFacilities() {
		return browser.getObjectText(".id", "attr_10102");
	}
	
	public String getBrochureNearbyAttractions() {
		return browser.getObjectText(".id", "attr_10103");
	}
	
	public boolean getConcessionaire() {
		String value = browser.getDropdownListValue(".id", "attr_10401");
		return value.equals("Yes") ? true : false;
	}
	
	public String getCheckinTime() {
		return browser.getTextFieldValue(".id", "attr_15");
	}
	
	public String getCheckinTimeAmPm() {
		return browser.getDropdownListValue(".id", "attr_15_ispm");
	}
	
	public String getCheckoutTime() {
		return browser.getTextFieldValue(".id", "attr_16");
	}
	
	public String getCheckoutTimeAmPm() {
		return browser.getDropdownListValue(".id", "attr_16_ispm");
	}
	
	public String getDrivingDirections() {	
		return browser.getObjectText(".id", "attr_100001");
	}
	
	public String getFeeAndCancellationDescription() {
		return browser.getObjectText(".id", "attr_10216");
	}
	
	public boolean getCollectCreditCardInfoDuringCheckin() {
		String value = browser.getDropdownListValue(".id", "attr_10210");
		return value.equals("Yes") ? true :  false;
	}
	
	public boolean getUsesFieldApplication() {
		String value = browser.getDropdownListValue(".id", "attr_10400");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getAllowPreCheckin() {
		String value = browser.getDropdownListValue(".id", "attr_2401");
		return value.equals("Yes") ? true : false;
	}
	
	public String getCheckinListSortOrder() {
		return browser.getDropdownListValue(".id", "attr_10200");
	}
	
	public String getCheckoutListSortOrder() {
		return browser.getDropdownListValue(".id", "attr_10201");
	}
	
	public String getPageDefaultRowCount() {
		return browser.getDropdownListValue(".id", "attr_10300");
	}
	
	public String getReservationSearchDefault() {
		return browser.getDropdownListValue(".id", "attr_10202");
	}
	
	public String getFinancialSessionType() {
		return browser.getDropdownListValue(".id", "attr_90651");
	}
	
	public boolean getOpeningFloatRequired() {
		String value = browser.getDropdownListValue(".id", "attr_155");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getCloseBlindly() {
		String value = browser.getDropdownListValue(".id", "attr_157");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getTransmittals() {
		String value = browser.getDropdownListValue(".id", "attr_77");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getTransmittalTraceNumRequired() {
		String value = browser.getDropdownListValue(".id", "attr_78");
		return value.equals("Yes") ? true : false;
	}
	
	public String getLatitudeDirection() {
		return browser.getDropdownListValue(".id", "attr_100014");
	}
	
	public String getLatitudeDegrees() {
		return browser.getTextFieldValue(".id", "attr_100010");
	}
	
	public String getLatitudeMinutes() {
		return browser.getTextFieldValue(".id", "attr_100011");
	}
	
	public String getLatitudeSeconds() {
		return browser.getTextFieldValue(".id", "attr_100012");
	}
	
	public String getLongitudeDirection() {
		return browser.getDropdownListValue(".id", "attr_100013");
	}
	
	public String getLongitudeDegrees() {
		return browser.getTextFieldValue(".id", "attr_100007");
	}
	
	public String getLongitudeMinutes() {
		return browser.getTextFieldValue(".id", "attr_100008");
	}
	
	public String getLongitudeSeconds() {
		return browser.getTextFieldValue(".id", "attr_100009");
	}
	
	public String getImportantInfo() {
		return browser.getObjectText(".id", "attr_100002");
	}
	
	public String getOtherPhoneNumbers() {
		return browser.getObjectText(".id", "attr_525");
	}
	
	public boolean getPopularFacility() {
		String value = browser.getDropdownListValue(".id", "attr_10105");
		return value.equals("Yes") ? true : false;
	}
	
	public String getReceiptCopiesNum() {
		return browser.getTextFieldValue(".id", "attr_150");
	}
	
	public String[] getReferralFacilities() {
		return browser.getDropdownElements(".id", "FacilityDetailView.parkReferrals").toArray(new String[0]);
	}
	
	public String getBookingWindowDesc() {
		return browser.getObjectText(".id", "attr_10218");
	}
	
	public String getSeasonDateDesc() {
		return browser.getObjectText(".id", "attr_10217");
	}
	
	public boolean getHiddenOnWebSearch() {
		String value = browser.getDropdownListValue(".id", "attr_381");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getHideFacitilyLocationOnWeb() {
		String value = browser.getDropdownListValue(".id", "attr_380");
		return value.equals("Yes") ? true : false;
	}
	
	//permit special facility attributes
	public boolean getShowExitAlerts() {
		String value = browser.getDropdownListValue(".id", "attr_4100");
		return value.equals("Yes") ? true : false;
	}
	
	public String getCommercialReservationsMadeFor() {
		return browser.getDropdownListValue(".id", "attr_100126");
	}
	
	public String getConfirmationPeriod() {
		return browser.getTextFieldValue(".id", "attr_4103");
	}
	
	public String getConfirmationPeriodStartTime() {
		return browser.getTextFieldValue(".id", "attr_4104");
	}
	
	public String getConfirmationPeriodStartTimeAmPm() {
		return browser.getDropdownListValue(".id", "attr_4104_ispm");
	}
	
	public boolean getDisplayAvailabilityOnButtonCaption() {
		String value = browser.getDropdownListValue(".id", "attr_4102");
		return value.equals("Yes") ? true : false;
	}
	
	public String getOnlinePermitPrintPeriod() {
		return browser.getTextFieldValue(".id", "attr_4105");
	}
	
	public String getOnlinePermitPrintPeriodStartTime() {
		return browser.getTextFieldValue(".id", "attr_4106");
	}
	
	public String getOnlinePermitPrintPeriodStartTimeAmPm() {
		return browser.getDropdownListValue(".id", "attr_4106_ispm");
	}
	
	public boolean getPermitInventoryAllocationsApplicable() {
		String value = browser.getDropdownListValue(".id", "attr_100125");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getRandomReleaseOfInventoryAppliesToRevokes() {
		String value = browser.getDropdownListValue(".id", "attr_4109");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getWalkinAllocationsIncludeAdvancedAllocations() {
		String value = browser.getDropdownListValue(".id", "attr_4101");
		return value.equals("Yes") ? true : false;
	}
	
	public String getPrintPermitInformation() {
		return browser.getObjectText(".id", "attr_2370");
	}
	
	public String getWebFacilitySpecialMessage() {
		return browser.getObjectText(".id", "attr_100147");
	}
	
	//ticket special facility attribute
	public boolean getADAAccessible() {
		String value = browser.getDropdownListValue(".id", "attr_10020");
		return value.equals("Yes") ? true : false;
	}
	
	public String getAvailabilityLagTime() {
		return browser.getTextFieldValue(".id", "attr_10021");
	}
	
	public String getMaximumGenericTickets() {
		return browser.getTextFieldValue(".id", "attr_10025");
	}
	
	public String getMaximumUnsoldTickets() {
		return browser.getTextFieldValue(".id", "attr_10026");
	}
	
	public String getFacilityFeesURL() {
		return browser.getTextFieldValue(".id", "attr_10022");
	}
	
	public boolean getSignatureLineOnTicketReceipt() {
		String value = browser.getDropdownListValue(".id", "attr_10015");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean getAutoInvalidateTickets() {
		String value = browser.getDropdownListValue(".id", "attr_200200");
		return value.equals("Yes") ? true : false;
	}
	public boolean checkTicketMailOut(){
		return browser.checkHtmlObjectExists(".id", "attr_200100");
	}
	
	public boolean getTicketMailOut() {
		String value = browser.getDropdownListValue(".id", "attr_200100");
		return value.equals("Yes") ? true : false;
	}
	
	public String getTourFacilitySaleType() {
		return browser.getTextFieldValue(".id", "attr_10030");
	}
	
	//POS special facility attribute
	public boolean getPersonalCheckPaymentForGeneralPublicPOSOrders() {
		String value = browser.getDropdownListValue(".id", "attr_10106");
		return value.equals("Yes") ? true : false;
	}
	
	public boolean checkErrorExist(){
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".className", "message msgerror");
	}
	
	public String getErrorMess(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.SPAN",".className", "message msgerror");
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find error message.");
		}
		String message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
	
	/**
	 * Get facility details information
	 * @return
	 */
	public FacilityData getFacilityInfo() {
		FacilityData facility = new FacilityData();
		facility.facilityName = this.getFacilityName();
		facility.shortName = this.getShortName();
		facility.facilityID = this.getFacilityID();
		facility.status = this.getFacilityStatus();
		facility.description = this.getDescription();
		facility.productCategory = this.getProductCategory();
		facility.alternateFacilityID=this.getAlternateFacilityID();
		facility.mailingAddress = this.getMailingAddress();
		facility.mailingCityTown = this.getMailingCityTown();
		facility.mailingState = this.getMailingState();
		facility.mailingZipCode = this.getMailingZipCode();
		facility.mailingCountry = this.getMailingCountry();
		facility.directLine = this.getDirectLine();
		facility.publicLine = this.getPublicLine();
		facility.fax = this.getFax();
		facility.email = this.getEmail();
		facility.http = this.getHTTP();
		facility.primaryPOCLastName = this.getPrimaryPOCLastName();
		facility.primaryPOCFirstName = this.getPrimaryPOCFirstName();
		facility.primaryPOCPhone = this.getPrimaryPOCPhone();
		facility.primaryPOCFax = this.getPrimaryPOCFax();
		facility.primaryPOCEmail = this.getPrimaryPOCEmail();
		facility.primaryPOCAddress = this.getPrimaryPOCAddress();
		facility.primaryPOCCityTown = this.getPrimaryPOCCityTown();
		facility.primaryPOCState = this.getPrimaryPOCState();
		facility.primaryPOCZipCode = this.getPrimaryPOCZipCode();
		facility.primaryPOCCountry = this.getPrimaryPOCCountry();
		facility.otherPOCLastName = this.getOtherPOCLastName();
		facility.otherPOCFirstName = this.getOtherPOCFirstName();
		facility.otherPOCPhone = this.getOtherPOCPhone();
		facility.otherPOCFax = this.getOtherPOCFax();
		facility.otherPOCEmail = this.getOtherPOCEmail();
		facility.otherPOCAddress = this.getOtherPOCAddress();
		facility.otherPOCCityTown = this.getOtherPOCCityTown();
		facility.otherPOCState = this.getOtherPOCState();
		facility.otherPOCZipCode = this.getOtherPOCZipCode();
		facility.otherPOCCountry = this.getOtherPOCCountry();
		facility.timeZone = this.getTimeZone();
		if(this.getGeographicRegionElements().size() > 0) {
			facility.geographicRegion = this.getGeographicRegion();
		}
		
		//below are facility attribute
		if(!facility.productCategory.equals("Service") && !facility.productCategory.equals("Lottery") && !facility.productCategory.equals("Supply") && !facility.productCategory.equals("Activity")) {
			facility.closeBlindly = this.getCloseBlindly();
			if(!facility.productCategory.equals("GiftCard") && !facility.productCategory.equals("Privilege") && !facility.productCategory.equals("VehicleRTI")) {
				facility.alias = this.getAlias();
				facility.brochureDescription = this.getBrochureDescription();
				facility.concessionaire = this.getConcessionaire();
				facility.drivingDirection = this.getDrivingDirections();
				facility.usesFieldApplication = this.getUsesFieldApplication();
				facility.pageDefaultRowCount = this.getPageDefaultRowCount();
				facility.financialSessionType = this.getFinancialSessionType();
				facility.latitudeDirection = this.getLatitudeDirection();
				facility.latitudeDegrees = this.getLatitudeDegrees();
				facility.latitudeMinutes = this.getLatitudeMinutes();
				facility.latitudeSeconds = this.getLatitudeSeconds();
				facility.longitudeDirection = this.getLongitudeDirection();
				facility.longitudeDegrees = this.getLongitudeDegrees();
				facility.longitudeMinutes = this.getLongitudeMinutes();
				facility.longitudeSeconds = this.getLongitudeSeconds();
				facility.importantInfo = this.getImportantInfo();
				
				if(!facility.productCategory.equals("Asset")) {
					facility.openingFloatRequired = this.getOpeningFloatRequired();
					facility.transmittals = this.getTransmittals();
					facility.transmittalTraceNumRequired = this.getTransmittalTraceNumRequired();
					
					if(!facility.productCategory.equals("POS")) {
						facility.hiddenOnWebSearch = this.getHiddenOnWebSearch();
						
						if(facility.productCategory.equals("Site")) {
							facility.brochureGeography = this.getBrochureGeography();
							facility.brochureRecreation = this.getBrochureRecreation();
							facility.brochureFacilities = this.getBrochureFacilities();
							facility.brochureNearbyAttractions = this.getBrochureNearbyAttractions();
							facility.checkinTime = this.getCheckinTime();
							facility.checkinTimeAmPm = this.getCheckinTimeAmPm();
							facility.checkoutTime = this.getCheckoutTime();
							facility.checkoutTimeAmPm = this.getCheckoutTimeAmPm();
							facility.feeAndCancellationDescription = this.getFeeAndCancellationDescription();
							facility.collectCreditCardInfoDuringCheckin = this.getCollectCreditCardInfoDuringCheckin();
							facility.allowPreCheckIn = this.getAllowPreCheckin();
							facility.checkinListSortOrder = this.getCheckinListSortOrder();
							facility.checkoutListSortOrder = this.getCheckoutListSortOrder();
							facility.reservationSearchDefault = this.getReservationSearchDefault();
							facility.otherPhoneNumbers = this.getOtherPhoneNumbers();
							facility.popularFacility = this.getPopularFacility();
							facility.receiptCopiesNum = this.getReceiptCopiesNum();
							facility.referralFacilities = this.getReferralFacilities();
							facility.bookingWindowDesc = this.getBookingWindowDesc();
							facility.seasonDateDesc = this.getSeasonDateDesc();
							facility.hideFacilityLocationOnWeb = this.getHideFacitilyLocationOnWeb();
						}
						
						if(facility.productCategory.equals("Permit")) {
							facility.showExitAlerts = this.getShowExitAlerts();
							facility.commercialReservationsMadeFor = this.getCommercialReservationsMadeFor();
							facility.confirmationPeriod = this.getConfirmationPeriod();
							facility.confirmationPeriodStartTime = this.getConfirmationPeriodStartTime();
							facility.confirmationPeriodStartTimeAmPm = this.getConfirmationPeriodStartTimeAmPm();
							facility.displayAvailabilityOnButtonCaption = this.getDisplayAvailabilityOnButtonCaption();
							facility.onlinePermitPrintPeriod = this.getOnlinePermitPrintPeriod();
							facility.onlinePermitPrintPeriodStartTime = this.getOnlinePermitPrintPeriodStartTime();
							facility.onlinePermitPrintPeriodStartTimeAmPm = this.getOnlinePermitPrintPeriodStartTimeAmPm();
							facility.permitInventoryAllocationsApplicable = this.getPermitInventoryAllocationsApplicable();
							facility.randomReleaseOfInventoryAppliesToRevokes = this.getRandomReleaseOfInventoryAppliesToRevokes();
							facility.walkinAllocationsIncludeAdvancedAllocations = this.getWalkinAllocationsIncludeAdvancedAllocations();
							facility.printPermitInformation = this.getPrintPermitInformation();
							facility.webFacilitySpecialMessage = this.getWebFacilitySpecialMessage();
						}
						
						if(facility.productCategory.equals("Ticket")) {
							facility.adaAccessible = this.getADAAccessible();
							facility.availabilityLagTime = this.getAvailabilityLagTime();
							facility.maximumGenericTickets = this.getMaximumGenericTickets();
							facility.maximumUnsoldTickets = this.getMaximumUnsoldTickets();
							facility.facilityFeesURL = this.getFacilityFeesURL();
							facility.signatureLineOnTicketReceipt = this.getSignatureLineOnTicketReceipt();
							facility.autoInvalidateTickets = this.getAutoInvalidateTickets();
							if(this.checkTicketMailOut()){
								facility.ticketMailOut = this.getTicketMailOut();
							}
							facility.tourFacilitySaleType = this.getTourFacilitySaleType();
						}
					} else {
						facility.personalCheckPaymentForGeneralPublicPOSOrders = this.getPersonalCheckPaymentForGeneralPublicPOSOrders();
					}
				}
			}
		}
		if(facility.productCategory.equals("Activity")){
			facility.enforceMinimumToConfirmRuleInField = this.getSelectionOfEnforceMinToConfirmRule().equalsIgnoreCase("Yes")?true:false;
		}
		
		return facility;
	}
	
	/**
	 * Compare the actual facility detail info with expected
	 * @param expectedFacility
	 * @return
	 */
	public boolean compareFacilityInfo(FacilityData expectedFacility) {
		boolean result = true;
		FacilityData actualFacility = this.getFacilityInfo();
		if(!expectedFacility.facilityName.equals(actualFacility.facilityName)) {
			logger.error("Facility Name is wrong. Expected result is " + expectedFacility.facilityName + " but actual result is " + actualFacility.facilityName);
			result &= false;
		}
		if(!expectedFacility.shortName.equals(actualFacility.shortName)) {
			logger.error("Facility Short Name is wrong. Expected result is " + expectedFacility.shortName + " but actual result is " + actualFacility.shortName);
			result &= false;
		}
		if(!expectedFacility.facilityID.equals(actualFacility.facilityID)) {
			logger.error("Facility ID is wrong. Expected result is " + expectedFacility.facilityID + " but actual result is " + actualFacility.facilityID);
			result &= false;
		}
		if(!expectedFacility.alternateFacilityID.equals(actualFacility.alternateFacilityID)) {
			logger.error("Alternate Facility ID is wrong. Expected result is " + expectedFacility.alternateFacilityID + " but actual result is " + actualFacility.alternateFacilityID);
			result &= false;
		}
		if(!expectedFacility.status.equals(actualFacility.status)) {
			logger.error("Facility Status is wrong. Expected result is " + expectedFacility.status + " but actual result is " + actualFacility.status);
			result &= false;
		}
		if(!expectedFacility.description.equals(actualFacility.description)) {
			logger.error("Facility Description is wrong. Expected result is " + expectedFacility.description + " but actual result is " + actualFacility.description);
			result &= false;
		}
		if(!expectedFacility.productCategory.equals(actualFacility.productCategory)) {
			throw new ErrorOnPageException("Facility Product Category is wrong. Expected result is " + expectedFacility.productCategory + " but actual result is " + actualFacility.productCategory);
		}
		if(!expectedFacility.mailingAddress.equals(actualFacility.mailingAddress)) {
			logger.error("Facility Mailing Address is wrong. Expected result is " + expectedFacility.mailingAddress + " but actual result is " + actualFacility.mailingAddress);
			result &= false;
		}
		if(!expectedFacility.mailingCityTown.equals(actualFacility.mailingCityTown)) {
			logger.error("Facility Mailing City/Town is wrong. Expected result is " + expectedFacility.mailingCityTown + " but actual result is " + actualFacility.mailingCityTown);
			result &= false;
		}
		if(!expectedFacility.mailingState.equals(actualFacility.mailingState)) {
			logger.error("Facility Mailing State is wrong. Expected result is " + expectedFacility.mailingState + " but actual result is " + actualFacility.mailingState);
			result &= false;
		}
		if(!expectedFacility.mailingZipCode.equals(actualFacility.mailingZipCode)) {
			logger.error("Facility Mailing Zip Code is wrong. Expected result is " + expectedFacility.mailingZipCode + " but actual result is " + actualFacility.mailingZipCode);
			result &= false;
		}
		if(!expectedFacility.mailingCountry.equals(actualFacility.mailingCountry)) {
			logger.error("Facility Mailing Country is wrong. Expected result is " + expectedFacility.mailingCountry + " but actual result is " + actualFacility.mailingCountry);
			result &= false;
		}
		if(!expectedFacility.directLine.equals(actualFacility.directLine)) {
			logger.error("Facility Direct Line is wrong. Expected result is " + expectedFacility.directLine + " but actual result is " + actualFacility.directLine);
			result &= false;
		}
		if(!expectedFacility.publicLine.equals(actualFacility.publicLine)) {
			logger.error("Facility Public Line is wrong. Expected result is " + expectedFacility.publicLine + " but actual result is " + actualFacility.publicLine);
			result &= false;
		}
		if(!expectedFacility.fax.equals(actualFacility.fax)) {
			logger.error("Facility Fax is wrong. Expected result is " + expectedFacility.fax + " but actual result is " + actualFacility.fax);
			result &= false;
		}
		if(!expectedFacility.email.equals(actualFacility.email)) {
			logger.error("Facility Email is wrong. Expected result is " + expectedFacility.email + " but actual result is " + actualFacility.email);
			result &= false;
		}
		if(!expectedFacility.http.equals(actualFacility.http)) {
			logger.error("Facility Http is wrong. Expected result is " + expectedFacility.http + " but actual result is " + actualFacility.http);
			result &= false;
		}
		if(!expectedFacility.primaryPOCLastName.equals(actualFacility.primaryPOCLastName)) {
			logger.error("Facility Primary POC Last Name is wrong. Expected result is " + expectedFacility.primaryPOCLastName + " but actual result is " + actualFacility.primaryPOCLastName);
			result &= false;
		}
		if(!expectedFacility.primaryPOCFirstName.equals(actualFacility.primaryPOCFirstName)) {
			logger.error("Facility Primary POC First Name is wrong. Expected result is " + expectedFacility.primaryPOCFirstName + " but actual result is " + actualFacility.primaryPOCFirstName);
			result &= false;
		}
		if(!expectedFacility.primaryPOCPhone.equals(actualFacility.primaryPOCPhone)) {
			logger.error("Facility Primary POC Phone is wrong. Expected result is " + expectedFacility.primaryPOCPhone + " but actual result is " + actualFacility.primaryPOCPhone);
			result &= false;
		}
		if(!expectedFacility.primaryPOCFax.equals(actualFacility.primaryPOCFax)) {
			logger.error("Facility Primary POC Fax is wrong. Expected result is " + expectedFacility.primaryPOCFax + " but actual result is " + actualFacility.primaryPOCFax);
			result &= false;
		}
		if(!expectedFacility.primaryPOCEmail.equals(actualFacility.primaryPOCEmail)) {
			logger.error("Facility Primary POC Email is wrong. Expected result is " + expectedFacility.primaryPOCEmail + " but actual result is " + actualFacility.primaryPOCEmail);
			result &= false;
		}
		if(!expectedFacility.primaryPOCAddress.equals(actualFacility.primaryPOCAddress)) {
			logger.error("Facility Primary POC Address is wrong. Expected result is " + expectedFacility.primaryPOCAddress + " but actual result is " + actualFacility.primaryPOCAddress);
			result &= false;
		}
		if(!expectedFacility.primaryPOCCityTown.equals(actualFacility.primaryPOCCityTown)) {
			logger.error("Facility Primary POC City/Town is wrong. Expected result is " + expectedFacility.primaryPOCCityTown + " but actual result is " + actualFacility.primaryPOCCityTown);
			result &= false;
		}
		if(!"".equals(expectedFacility.primaryPOCState)&&!expectedFacility.primaryPOCState.equals(actualFacility.primaryPOCState)) {
			logger.error("Facility Primary POC State is wrong. Expected result is " + expectedFacility.primaryPOCState + " but actual result is " + actualFacility.primaryPOCState);
			result &= false;
		}
		if(!expectedFacility.primaryPOCZipCode.equals(actualFacility.primaryPOCZipCode)) {
			logger.error("Facility Primary POC Zip Code is wrong. Expected result is " + expectedFacility.primaryPOCZipCode + " but actual result is " + actualFacility.primaryPOCZipCode);
			result &= false;
		}
		if(!"".equals(expectedFacility.primaryPOCCountry)&&!expectedFacility.primaryPOCCountry.equals(actualFacility.primaryPOCCountry)) {
			logger.error("Facility Primary POC Country is wrong. Expected result is " + expectedFacility.primaryPOCCountry + " but actual result is " + actualFacility.primaryPOCCountry);
			result &= false;
		}
		if(!expectedFacility.otherPOCLastName.equals(actualFacility.otherPOCLastName)) {
			logger.error("Facility other POC Last Name is wrong. Expected result is " + expectedFacility.otherPOCLastName + " but actual result is " + actualFacility.otherPOCLastName);
			result &= false;
		}
		if(!expectedFacility.otherPOCFirstName.equals(actualFacility.otherPOCFirstName)) {
			logger.error("Facility other POC First Name is wrong. Expected result is " + expectedFacility.otherPOCFirstName + " but actual result is " + actualFacility.otherPOCFirstName);
			result &= false;
		}
		if(!expectedFacility.otherPOCPhone.equals(actualFacility.otherPOCPhone)) {
			logger.error("Facility other POC Phone is wrong. Expected result is " + expectedFacility.otherPOCPhone + " but actual result is " + actualFacility.otherPOCPhone);
			result &= false;
		}
		if(!expectedFacility.facilityName.equals(actualFacility.facilityName)) {
			logger.error("Facility Name is wrong. Expected result is " + expectedFacility.facilityName + " but actual result is " + actualFacility.facilityName);
			result &= false;
		}
		if(!expectedFacility.otherPOCFax.equals(actualFacility.otherPOCFax)) {
			logger.error("Facility other POC Fax is wrong. Expected result is " + expectedFacility.otherPOCFax + " but actual result is " + actualFacility.otherPOCFax);
			result &= false;
		}
		if(!expectedFacility.otherPOCEmail.equals(actualFacility.otherPOCEmail)) {
			logger.error("Facility other POC Email is wrong. Expected result is " + expectedFacility.otherPOCEmail + " but actual result is " + actualFacility.otherPOCEmail);
			result &= false;
		}
		if(!expectedFacility.otherPOCAddress.equals(actualFacility.otherPOCAddress)) {
			logger.error("Facility other POC Address is wrong. Expected result is " + expectedFacility.otherPOCAddress + " but actual result is " + actualFacility.otherPOCAddress);
			result &= false;
		}
		if(!expectedFacility.otherPOCCityTown.equals(actualFacility.otherPOCCityTown)) {
			logger.error("Facility other POC City/Town is wrong. Expected result is " + expectedFacility.otherPOCCityTown + " but actual result is " + actualFacility.otherPOCCityTown);
			result &= false;
		}
		if(!"".equals(expectedFacility.otherPOCState)&&!expectedFacility.otherPOCState.equals(actualFacility.otherPOCState)) {
			logger.error("Facility other POC State is wrong. Expected result is " + expectedFacility.otherPOCState + " but actual result is " + actualFacility.otherPOCState);
			result &= false;
		}
		if(!expectedFacility.otherPOCZipCode.equals(actualFacility.otherPOCZipCode)) {
			logger.error("Facility other POC Zip Code is wrong. Expected result is " + expectedFacility.otherPOCZipCode + " but actual result is " + actualFacility.otherPOCZipCode);
			result &= false;
		}
		if(!"".equals(expectedFacility.otherPOCCountry)&&!expectedFacility.otherPOCCountry.equals(actualFacility.otherPOCCountry)) {
			logger.error("Facility other POC Country is wrong. Expected result is " + expectedFacility.otherPOCCountry + " but actual result is " + actualFacility.otherPOCCountry);
			result &= false;
		}
		if(!expectedFacility.timeZone.equals(actualFacility.timeZone)) {
			logger.error("Facility Time Zone is wrong. Expected result is " + expectedFacility.timeZone + " but actual result is " + actualFacility.timeZone);
			result &= false;
		}
		if(!expectedFacility.geographicRegion.equals(actualFacility.geographicRegion)) {
			logger.error("Facility Geographic Region is wrong. Expected result is " + expectedFacility.geographicRegion + " but actual result is " + actualFacility.geographicRegion);
			result &= false;
		}
		
		/*
		 * Verify facility attributes info
		 */
		if(!actualFacility.productCategory.equals("Service") && !actualFacility.productCategory.equals("Lottery") && !actualFacility.productCategory.equals("Supply") && !actualFacility.productCategory.equals("Activity")) {
			if(expectedFacility.closeBlindly != actualFacility.closeBlindly) {
				logger.error("Facility Attribute - Close Blindly is wrong. Expected result is " + expectedFacility.closeBlindly + " but actual result is " + actualFacility.closeBlindly);
				result &= false;
			}
			
			if(!actualFacility.productCategory.equals("GiftCard") && !actualFacility.productCategory.equals("Privilege") && !actualFacility.productCategory.equals("VehicleRTI")) {
				if(!expectedFacility.alias.equals(actualFacility.alias)) {
					logger.error("Facility Attribute - Alias is wrong. Expected result is " + expectedFacility.alias + " but actual result is " + actualFacility.alias);
					result &= false;
				}
				if(!expectedFacility.brochureDescription.equals(actualFacility.brochureDescription)) {
					logger.error("Facility Attribute - Brochure Description is wrong. Expected result is " + expectedFacility.brochureDescription + " but actual result is " + actualFacility.brochureDescription);
					result &= false;
				}
				if(expectedFacility.concessionaire != actualFacility.concessionaire) {
					logger.error("Facility Attribute - Concessionaire is wrong. Expected result is " + expectedFacility.concessionaire + " but actual result is " + actualFacility.concessionaire);
					result &= false;
				}
				if(!expectedFacility.drivingDirection.equals(actualFacility.drivingDirection)) {
					logger.error("Facility Attribute - Driving Direction is wrong. Expected result is " + expectedFacility.drivingDirection + " but actual result is " + actualFacility.drivingDirection);
					result &= false;
				}
				if(expectedFacility.usesFieldApplication != actualFacility.usesFieldApplication) {
					logger.error("Facility Attribute - Uses Field Application is wrong. Expected result is " + expectedFacility.usesFieldApplication + " but actual result is " + actualFacility.usesFieldApplication);
					result &= false;
				}
				if(!expectedFacility.pageDefaultRowCount.equals(actualFacility.pageDefaultRowCount)) {
					logger.error("Facility Attribute - Page Default Row Count is wrong. Expected result is " + expectedFacility.pageDefaultRowCount + " but actual result is " + actualFacility.pageDefaultRowCount);
					result &= false;
				}
				if(!expectedFacility.financialSessionType.equals(actualFacility.financialSessionType)) {
					logger.error("Facility Attribute - Financial Session Type is wrong. Expected result is " + expectedFacility.financialSessionType + " but actual result is " + actualFacility.financialSessionType);
					result &= false;
				}
				
				if(!expectedFacility.latitudeDirection.equals(actualFacility.latitudeDirection)) {
					logger.error("Facility Attribute - Latitude Direction is wrong. Expected result is " + expectedFacility.latitudeDirection + " but actual result is " + actualFacility.latitudeDirection);
					result &= false;
				}
				if(!expectedFacility.latitudeDegrees.equals(actualFacility.latitudeDegrees)) {
					logger.error("Facility Attribute - Latitude Degrees is wrong. Expected result is " + expectedFacility.latitudeDegrees + " but actual result is " + actualFacility.latitudeDegrees);
					result &= false;
				}
				if(!expectedFacility.latitudeMinutes.equals(actualFacility.latitudeMinutes)) {
					logger.error("Facility Attribute - Latitude Minutes is wrong. Expected result is " + expectedFacility.latitudeMinutes + " but actual result is " + actualFacility.latitudeMinutes);
					result &= false;
				}
				if(!expectedFacility.latitudeSeconds.equals(actualFacility.latitudeSeconds)) {
					logger.error("Facility Attribute - Latitude Seconds is wrong. Expected result is " + expectedFacility.latitudeSeconds + " but actual result is " + actualFacility.latitudeSeconds);
					result &= false;
				}
				if(!expectedFacility.longitudeDirection.equals(actualFacility.longitudeDirection)) {
					logger.error("Facility Attribute - Longitude Direction is wrong. Expected result is " + expectedFacility.longitudeDirection + " but actual result is " + actualFacility.longitudeDirection);
					result &= false;
				}
				if(!expectedFacility.longitudeDegrees.equals(actualFacility.longitudeDegrees)) {
					logger.error("Facility Attribute - Longitude Degrees is wrong. Expected result is " + expectedFacility.longitudeDegrees + " but actual result is " + actualFacility.longitudeDegrees);
					result &= false;
				}
				if(!expectedFacility.longitudeMinutes.equals(actualFacility.longitudeMinutes)) {
					logger.error("Facility Attribute - Longitude Minutes is wrong. Expected result is " + expectedFacility.longitudeMinutes + " but actual result is " + actualFacility.longitudeMinutes);
					result &= false;
				}
				if(!expectedFacility.longitudeSeconds.equals(actualFacility.longitudeSeconds)) {
					logger.error("Facility Attribute - Longitude Seconds is wrong. Expected result is " + expectedFacility.longitudeSeconds + " but actual result is " + actualFacility.longitudeSeconds);
					result &= false;
				}
				if(!expectedFacility.importantInfo.equals(actualFacility.importantInfo)) {
					logger.error("Facility Attribute - Important Info is wrong. Expected result is " + expectedFacility.importantInfo + " but actual result is " + actualFacility.importantInfo);
					result &= false;
				}
				
				if(!actualFacility.productCategory.equals("Asset")) {
					if(expectedFacility.openingFloatRequired != actualFacility.openingFloatRequired) {
						logger.error("Facility Attribute - Opening Float Required is wrong. Expected result is " + expectedFacility.openingFloatRequired + " but actual result is " + actualFacility.openingFloatRequired);
						result &= false;
					}
					if(expectedFacility.transmittals != actualFacility.transmittals) {
						logger.error("Facility Attribute - Transmittals is wrong. Expected result is " + expectedFacility.transmittals + " but actual result is " + actualFacility.transmittals);
						result &= false;
					}
					if(expectedFacility.transmittalTraceNumRequired != actualFacility.transmittalTraceNumRequired) {
						logger.error("Facility Attribute - Transmittal Trace Num Required is wrong. Expected result is " + expectedFacility.transmittalTraceNumRequired + " but actual result is " + actualFacility.transmittalTraceNumRequired);
						result &= false;
					}
				}
				
				if(!actualFacility.productCategory.equals("POS")) {
					if(expectedFacility.hiddenOnWebSearch != actualFacility.hiddenOnWebSearch) {
						logger.error("Facility Attribute - Hidden On Web Search is wrong. Expected result is " + expectedFacility.hiddenOnWebSearch + " but actual result is " + actualFacility.hiddenOnWebSearch);
						result &= false;
					}

					if(actualFacility.productCategory.equals("Site")) {
						if(!expectedFacility.brochureGeography.equals(actualFacility.brochureGeography)) {
							logger.error("Facility Attribute - Brochure Geography is wrong. Expected result is " + expectedFacility.brochureGeography + " but actual result is " + actualFacility.brochureGeography);
							result &= false;
						}
						if(!expectedFacility.brochureRecreation.equals(actualFacility.brochureRecreation)) {
							logger.error("Facility Attribute - Brochure Recreation is wrong. Expected result is " + expectedFacility.brochureRecreation + " but actual result is " + actualFacility.brochureRecreation);
							result &= false;
						}
						if(!expectedFacility.brochureFacilities.equals(actualFacility.brochureFacilities)) {
							logger.error("Facility Attribute - Brochure Facilities is wrong. Expected result is " + expectedFacility.brochureFacilities + " but actual result is " + actualFacility.brochureFacilities);
							result &= false;
						}
						if(!expectedFacility.brochureNearbyAttractions.equals(actualFacility.brochureNearbyAttractions)) {
							logger.error("Facility Attribute - Brochure NearbyAttractions is wrong. Expected result is " + expectedFacility.brochureNearbyAttractions + " but actual result is " + actualFacility.brochureNearbyAttractions);
							result &= false;
						}
						if(!expectedFacility.checkinTime.equals(actualFacility.checkinTime)) {
							logger.error("Facility Attribute - Checkin Time is wrong. Expected result is " + expectedFacility.checkinTime + " but actual result is " + actualFacility.checkinTime);
							result &= false;
						}
						if(!"".equals(expectedFacility.checkinTimeAmPm)&&!expectedFacility.checkinTimeAmPm.equals(actualFacility.checkinTimeAmPm)) {
							logger.error("Facility Attribute - Checkin Time AmPm is wrong. Expected result is " + expectedFacility.checkinTimeAmPm + " but actual result is " + actualFacility.checkinTimeAmPm);
							result &= false;
						}
						if(!expectedFacility.checkoutTime.equals(actualFacility.checkoutTime)) {
							logger.error("Facility Attribute - Checkout Time is wrong. Expected result is " + expectedFacility.checkoutTime + " but actual result is " + actualFacility.checkoutTime);
							result &= false;
						}
						if(!"".equals(expectedFacility.checkoutTimeAmPm)&&!expectedFacility.checkoutTimeAmPm.equals(actualFacility.checkoutTimeAmPm)) {
							logger.error("Facility Attribute - Checkout Time AmPm is wrong. Expected result is " + expectedFacility.checkoutTimeAmPm + " but actual result is " + actualFacility.checkoutTimeAmPm);
							result &= false;
						}
						if(!expectedFacility.feeAndCancellationDescription.equals(actualFacility.feeAndCancellationDescription)) {
							logger.error("Facility Attribute - Fee And Cancellation Description is wrong. Expected result is " + expectedFacility.feeAndCancellationDescription + " but actual result is " + actualFacility.feeAndCancellationDescription);
							result &= false;
						}
						if(expectedFacility.collectCreditCardInfoDuringCheckin != actualFacility.collectCreditCardInfoDuringCheckin) {
							logger.error("Facility Attribute - Collect Credit Card Info During Checkin is wrong. Expected result is " + expectedFacility.collectCreditCardInfoDuringCheckin + " but actual result is " + actualFacility.collectCreditCardInfoDuringCheckin);
							result &= false;
						}
						if(expectedFacility.allowPreCheckIn != actualFacility.allowPreCheckIn) {
							logger.error("Facility Attribute - Allow Pre-CheckIn is wrong. Expected result is " + expectedFacility.allowPreCheckIn + " but actual result is " + actualFacility.allowPreCheckIn);
							result &= false;
						}
						if(!expectedFacility.checkinListSortOrder.equals(actualFacility.checkinListSortOrder)) {
							logger.error("Facility Attribute - Checkin List Sort Order is wrong. Expected result is " + expectedFacility.checkinListSortOrder + " but actual result is " + actualFacility.checkinListSortOrder);
							result &= false;
						}
						if(!expectedFacility.checkoutListSortOrder.equals(actualFacility.checkoutListSortOrder)) {
							logger.error("Facility Attribute - Checkout List Sort Order is wrong. Expected result is " + expectedFacility.checkoutListSortOrder + " but actual result is " + actualFacility.checkoutListSortOrder);
							result &= false;
						}
						if(!expectedFacility.reservationSearchDefault.equals(actualFacility.reservationSearchDefault)) {
							logger.error("Facility Attribute - Reservation Search Default is wrong. Expected result is " + expectedFacility.reservationSearchDefault + " but actual result is " + actualFacility.reservationSearchDefault);
							result &= false;
						}
						if(!expectedFacility.otherPhoneNumbers.equals(actualFacility.otherPhoneNumbers)) {
							logger.error("Facility Attribute - Other Phone Numbers is wrong. Expected result is " + expectedFacility.otherPhoneNumbers + " but actual result is " + actualFacility.otherPhoneNumbers);
							result &= false;
						}
						if(expectedFacility.popularFacility != actualFacility.popularFacility) {
							logger.error("Facility Attribute - Popular Facility is wrong. Expected result is " + expectedFacility.popularFacility + " but actual result is " + actualFacility.popularFacility);
							result &= false;
						}
						if(!expectedFacility.receiptCopiesNum.equals(actualFacility.receiptCopiesNum)) {
							logger.error("Facility Attribute - Receipt Copies Num is wrong. Expected result is " + expectedFacility.receiptCopiesNum + " but actual result is " + actualFacility.receiptCopiesNum);
							result &= false;
						}
//						if(expectedFacility.referralFacilities.length > 0) {//TODO DEFECT-32482
//							for(int i = 0; i < expectedFacility.referralFacilities.length; i++) {
//								System.out.println("expectedFacility.referralFacilities[i]: " + expectedFacility.referralFacilities[i]);
//								System.out.println("actualFacility.referralFacilities[i]: " + actualFacility.referralFacilities[i]);
//								if(!expectedFacility.referralFacilities[i].equals(actualFacility.referralFacilities[i])) {
//									logger.error("Facility Attribute - Referral Facilities is wrong. Expected result is " + expectedFacility.referralFacilities + " but actual result is " + actualFacility.referralFacilities);
//									result &= false;
//								}
//							}
//						}
						if(!expectedFacility.bookingWindowDesc.equals(actualFacility.bookingWindowDesc)) {
							logger.error("Facility Attribute - Booking Window Desc is wrong. Expected result is " + expectedFacility.bookingWindowDesc + " but actual result is " + actualFacility.bookingWindowDesc);
							result &= false;
						}
						if(!expectedFacility.seasonDateDesc.equals(actualFacility.seasonDateDesc)) {
							logger.error("Facility Attribute - Season Date Desc is wrong. Expected result is " + expectedFacility.seasonDateDesc + " but actual result is " + actualFacility.seasonDateDesc);
							result &= false;
						}
						if(expectedFacility.hideFacilityLocationOnWeb != actualFacility.hideFacilityLocationOnWeb) {
							logger.error("Facility Attribute - Hide Facility Location On Web is wrong. Expected result is " + expectedFacility.hideFacilityLocationOnWeb + " but actual result is " + actualFacility.hideFacilityLocationOnWeb);
							result &= false;
						}
					}
					if(actualFacility.productCategory.equals("Permit")) {
						if(expectedFacility.showExitAlerts != actualFacility.showExitAlerts) {
							logger.error("Facility Attribute - Show Exit Alerts is wrong. Expected result is " + expectedFacility.showExitAlerts + " but actual result is " + actualFacility.showExitAlerts);
							result &= false;
						}
						if(!expectedFacility.commercialReservationsMadeFor.equals(actualFacility.commercialReservationsMadeFor)) {
							logger.error("Facility Attribute - Commercial Reservations Made For is wrong. Expected result is " + expectedFacility.commercialReservationsMadeFor + " but actual result is " + actualFacility.commercialReservationsMadeFor);
							result &= false;
						}
						if(!expectedFacility.confirmationPeriod.equals(actualFacility.confirmationPeriod)) {
							logger.error("Facility Attribute - Confirmation Period is wrong. Expected result is " + expectedFacility.confirmationPeriod + " but actual result is " + actualFacility.confirmationPeriod);
							result &= false;
						}
						if(!expectedFacility.confirmationPeriodStartTime.equals(actualFacility.confirmationPeriodStartTime)) {
							logger.error("Facility Attribute - Confirmation Period Start Time is wrong. Expected result is " + expectedFacility.confirmationPeriodStartTime + " but actual result is " + actualFacility.confirmationPeriodStartTime);
							result &= false;
						}
						if(!"".equals(expectedFacility.confirmationPeriodStartTimeAmPm)&&!expectedFacility.confirmationPeriodStartTimeAmPm.equals(actualFacility.confirmationPeriodStartTimeAmPm)) {
							logger.error("Facility Attribute - Confirmation Period Start Time AmPm is wrong. Expected result is " + expectedFacility.confirmationPeriodStartTimeAmPm + " but actual result is " + actualFacility.confirmationPeriodStartTimeAmPm);
							result &= false;
						}
						if(expectedFacility.displayAvailabilityOnButtonCaption != actualFacility.displayAvailabilityOnButtonCaption) {
							logger.error("Facility Attribute - Display Availability On Button Caption is wrong. Expected result is " + expectedFacility.displayAvailabilityOnButtonCaption + " but actual result is " + actualFacility.displayAvailabilityOnButtonCaption);
							result &= false;
						}
						if(!expectedFacility.onlinePermitPrintPeriod.equals(actualFacility.onlinePermitPrintPeriod)) {
							logger.error("Facility Attribute - Online Permit Print Period is wrong. Expected result is " + expectedFacility.onlinePermitPrintPeriod + " but actual result is " + actualFacility.onlinePermitPrintPeriod);
							result &= false;
						}
						if(!expectedFacility.onlinePermitPrintPeriodStartTime.equals(actualFacility.onlinePermitPrintPeriodStartTime)) {
							logger.error("Facility Attribute - Online Permit Print Period Start Time is wrong. Expected result is " + expectedFacility.onlinePermitPrintPeriodStartTime + " but actual result is " + actualFacility.onlinePermitPrintPeriodStartTime);
							result &= false;
						}
						if(!"".equals(expectedFacility.onlinePermitPrintPeriodStartTimeAmPm)&&!expectedFacility.onlinePermitPrintPeriodStartTimeAmPm.equals(actualFacility.onlinePermitPrintPeriodStartTimeAmPm)) {
							logger.error("Facility Attribute - Online Permit Print Period Start Time AmPm is wrong. Expected result is " + expectedFacility.onlinePermitPrintPeriodStartTimeAmPm + " but actual result is " + actualFacility.onlinePermitPrintPeriodStartTimeAmPm);
							result &= false;
						}
						if(expectedFacility.permitInventoryAllocationsApplicable != actualFacility.permitInventoryAllocationsApplicable) {
							logger.error("Facility Attribute - Permit Inventory Allocations Applicable is wrong. Expected result is " + expectedFacility.permitInventoryAllocationsApplicable + " but actual result is " + actualFacility.permitInventoryAllocationsApplicable);
							result &= false;
						}
						if(expectedFacility.randomReleaseOfInventoryAppliesToRevokes != actualFacility.randomReleaseOfInventoryAppliesToRevokes) {
							logger.error("Facility Attribute - Random Release Of Inventory Applies To Revokes is wrong. Expected result is " + expectedFacility.randomReleaseOfInventoryAppliesToRevokes + " but actual result is " + actualFacility.randomReleaseOfInventoryAppliesToRevokes);
							result &= false;
						}
						if(expectedFacility.walkinAllocationsIncludeAdvancedAllocations != actualFacility.walkinAllocationsIncludeAdvancedAllocations) {
							logger.error("Facility Attribute - Walkin Allocations Include Advanced Allocations is wrong. Expected result is " + expectedFacility.walkinAllocationsIncludeAdvancedAllocations + " but actual result is " + actualFacility.walkinAllocationsIncludeAdvancedAllocations);
							result &= false;
						}
						if(!"".equals(expectedFacility.printPermitInformation)&&!expectedFacility.printPermitInformation.equals(actualFacility.printPermitInformation)) {
							logger.error("Facility Attribute - Print Permit Information is wrong. Expected result is " + expectedFacility.printPermitInformation + " but actual result is " + actualFacility.printPermitInformation);
							result &= false;
						}
						if(!expectedFacility.webFacilitySpecialMessage.equals(actualFacility.webFacilitySpecialMessage)) {
							logger.error("Facility Attribute - Web Facility Special Message is wrong. Expected result is " + expectedFacility.webFacilitySpecialMessage + " but actual result is " + actualFacility.webFacilitySpecialMessage);
							result &= false;
						}
					}
					
					if(actualFacility.productCategory.equals("Ticket")) {
						if(expectedFacility.adaAccessible != actualFacility.adaAccessible) {
							logger.error("Facility Attribute - ADA Accessible is wrong. Expected result is " + expectedFacility.adaAccessible + " but actual result is " + actualFacility.adaAccessible);
							result &= false;
						}
						if(!expectedFacility.availabilityLagTime.equals(actualFacility.availabilityLagTime)) {
							logger.error("Facility Attribute - Availability Lag Time is wrong. Expected result is " + expectedFacility.availabilityLagTime + " but actual result is " + actualFacility.availabilityLagTime);
							result &= false;
						}
						if(!expectedFacility.maximumGenericTickets.equals(actualFacility.maximumGenericTickets)) {
							logger.error("Facility Attribute - Maximum Generic Tickets is wrong. Expected result is " + expectedFacility.maximumGenericTickets + " but actual result is " + actualFacility.maximumGenericTickets);
							result &= false;
						}
						if(!expectedFacility.maximumUnsoldTickets.equals(actualFacility.maximumUnsoldTickets)) {
							logger.error("Facility Attribute - Maximum Unsold Tickets is wrong. Expected result is " + expectedFacility.maximumUnsoldTickets + " but actual result is " + actualFacility.maximumUnsoldTickets);
							result &= false;
						}
						if(!expectedFacility.facilityFeesURL.equals(actualFacility.facilityFeesURL)) {
							logger.error("Facility Attribute - Facility Fees URL is wrong. Expected result is " + expectedFacility.facilityFeesURL + " but actual result is " + actualFacility.facilityFeesURL);
							result &= false;
						}
						if(expectedFacility.signatureLineOnTicketReceipt != actualFacility.signatureLineOnTicketReceipt) {
							logger.error("Facility Attribute - Signature Line On Ticket Receipt is wrong. Expected result is " + expectedFacility.signatureLineOnTicketReceipt + " but actual result is " + actualFacility.signatureLineOnTicketReceipt);
							result &= false;
						}
						if(expectedFacility.autoInvalidateTickets != actualFacility.autoInvalidateTickets) {
							logger.error("Facility Attribute - Auto-Invalidate Tickets is wrong. Expected result is " + expectedFacility.autoInvalidateTickets + " but actual result is " + actualFacility.autoInvalidateTickets);
							result &= false;
						}
						if(expectedFacility.ticketMailOut != actualFacility.ticketMailOut) {
							logger.error("Facility Attribute - Ticket Mail Out is wrong. Expected result is " + expectedFacility.ticketMailOut + " but actual result is " + actualFacility.ticketMailOut);
							result &= false;
						}
						if(!expectedFacility.tourFacilitySaleType.equals(actualFacility.tourFacilitySaleType)) {
							logger.error("Facility Attribute - Tour Facility Sale Type is wrong. Expected result is " + expectedFacility.tourFacilitySaleType + " but actual result is " + actualFacility.tourFacilitySaleType);
							result &= false;
						}
					}
				} else {
					if(expectedFacility.personalCheckPaymentForGeneralPublicPOSOrders != actualFacility.personalCheckPaymentForGeneralPublicPOSOrders) {
						logger.error("Facility Attribute - Personal Check Payment For General Public POS Orders is wrong. Expected result is " + expectedFacility.personalCheckPaymentForGeneralPublicPOSOrders + " but actual result is " + actualFacility.personalCheckPaymentForGeneralPublicPOSOrders);
						result &= false;
					}
				}
			}
		}
		if(actualFacility.productCategory.equals("Activity")){
			result &= MiscFunctions.compareResult("Facility Attribute - Activity Facility enforceMinimumToConfirmRuleInField", expectedFacility.enforceMinimumToConfirmRuleInField, actualFacility.enforceMinimumToConfirmRuleInField);
		}
		
		return result;
	} 

	public String setUpFacilityDetailAndClickApply(FacilityData fd) {
		setupFacilityDetailsData(fd);
		clickApply();
		waitLoading();
		String facId = getFacilityID();
		return facId;
	}
	
	public void selectEnforceMinToConfirmRule(boolean enforceMinumumToConfirmRuleInField){
		browser.selectDropdownList(".id", "attr_4131", enforceMinumumToConfirmRuleInField ? "Yes" : "No");
	}
	
	public String getSelectionOfEnforceMinToConfirmRule(){
		return browser.getDropdownListValue(".id", "attr_4131");
	}
	
	public List<String> getElementsOfEnforceMinToConfirmRule(){
		return browser.getDropdownElements(".id", "attr_4131");
	}
	
	/**
	 * This is just part of facility info for slip, you can add other attribute in this method
	 * @param fd
	 */
	public void setupFacilityAttributesForSlip(FacilityData fd) {
		logger.info("Setup facility attributes details info for Slip product category.");
	  	//Brochure information
		if(StringUtil.notEmpty(fd.brochureDescription)){
	  		this.setBrochureDescription(fd.brochureDescription);
	  	}
		if(StringUtil.notEmpty(fd.brochureGeography)) {
	  		this.setBrochureGeograhy(fd.brochureGeography);
	  	}
	  	if(StringUtil.notEmpty(fd.brochureRecreation)) {
	  		this.setBrochureRecreation(fd.brochureRecreation);
	  	}
	  	if(StringUtil.notEmpty(fd.brochureFacilities)) {
	  		this.setBrochureFacilities(fd.brochureFacilities);
	  	}
	  	if(StringUtil.notEmpty(fd.brochureNearbyAttractions)) {
	  		this.setBrochureNearbyAttractions(fd.brochureNearbyAttractions);
	  	}
	  	//Driving Directions
	  	if(StringUtil.notEmpty(fd.drivingDirection)){
	  		this.setDriveDirection(fd.drivingDirection);
	  	}
	  	//Important information
	  	if(StringUtil.notEmpty(fd.importantInfo)){
	  		this.setImportantInfo(fd.importantInfo);
	  	}
	  	if(fd.seasonDateDesc != null){ //Do no replace it with 'StringUtil.isEmpty(fd.seasonDateDesc)', for it may need to be set as "";
	  		this.setSeasonDateDesc(fd.seasonDateDesc);
	  	}
	}
	
	public FacilityData getFacilityAttributeForSlip(){
		FacilityData fInfo = new FacilityData();
		fInfo.checkinTime = this.getCheckinTime();
		fInfo.checkoutTime = this.getCheckoutTime();
		fInfo.publicLine = this.getPublicLine();
		fInfo.radioChannel = this.getRadioChannel();
		
		fInfo.brochureDescription = this.getBrochureDescription();
		fInfo.brochureGeography = this.getBrochureGeography();
		fInfo.brochureRecreation = this.getBrochureRecreation();
		fInfo.brochureFacilities = this.getBrochureFacilities();
		fInfo.brochureNearbyAttractions = this.getBrochureNearbyAttractions();
		fInfo.importantInfo = this.getImportantInfo();
		fInfo.drivingDirection = this.getDrivingDirections();
		
		fInfo.seasonDateDesc = this.getSeasonDateDesc();
		return fInfo;
	}
	
	public String getMailAddress(){
		String address;
		String mailingAddress = this.getMailingAddress();
		String mailingCityTown = this.getMailingCityTown();
		String mailingState = this.getMailingState();
		String mailingZipCode = this.getMailingZipCode();
		address = mailingAddress +", "+ mailingCityTown +", "+ mailingState +", "+ mailingZipCode;
		return address;
	}
}
