package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 7, 2014
 */
public class LicMgrNewFacilitySetupPage extends LicMgrActivityMGTCommonPage{
	static class SingletonHolder {
		protected static LicMgrNewFacilitySetupPage _instance = new LicMgrNewFacilitySetupPage();
	}

	protected LicMgrNewFacilitySetupPage() {
	}

	public static LicMgrNewFacilitySetupPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] facilitySetupPageContentDIV(){
		return Property.concatPropertyArray(div(), ".id", "content_LM_ActivityMgmtUIPlugin_tabpanel");
	}

	protected Property[] agencyName(){
		return Property.toPropertyArray(".id", "agencyName");
	}

	protected Property[] regionName(){
		return Property.toPropertyArray(".id", "regionname");
	}

	protected Property[] facilityIDSpan(){
		return Property.concatPropertyArray(span(), ".text", new RegularExpression("Facility ID.*", false));
	}

	protected Property[] facilityIDTextField(){
		return Property.concatPropertyArray(input("text"));
	}

	protected Property[] facilityName(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.name", false));
	}

	protected Property[] shortName(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.shortName", false));
	}

	protected Property[] status(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.status", false));
	}

	protected Property[] address(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.streetAddress1", false));
	}

	protected Property[] supplementalAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.streetAddress2", false));
	}

	protected Property[] cityTown(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.cityTown", false));
	}

	protected Property[] state(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.stateProv", false));
	}

	protected Property[] county(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.county", false));
	}

	protected Property[] zip(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.zipPostal", false));
	}

	protected Property[] country(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.country", false));
	}

	protected Property[] publicLine(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.publicLine", false));
	}

	protected Property[] fax(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.fax", false));
	}

	protected Property[] email(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.email", false));
	}

	protected Property[] website(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.website", false));
	}

	protected Property[] primaryContactLN(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactLastName", false));
	}

	protected Property[] primaryContactFN(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactFirstName", false));
	}

	protected Property[] primaryContactPhone(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactPhone", false));
	}

	protected Property[] primaryContactFax(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactFax", false));
	}

	protected Property[] primaryContactEmail(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactEmail", false));
	}

	protected Property[] primaryContactAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactStreetAddress1", false));
	}

	protected Property[] primaryContactCityTown(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactCityTown", false));
	}

	protected Property[] primaryContactState(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactStateProv", false));
	}

	protected Property[] primaryContactZip(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactZipPostal", false));
	}

	protected Property[] primaryContactCountry(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.primaryContactCountry", false));
	}

	protected Property[] otherContactLN(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactLastName", false));
	}

	protected Property[] otherContactFN(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactFirstName", false));
	}

	protected Property[] otherContactPhone(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactPhone", false));
	}

	protected Property[] otherContactFax(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactFax", false));
	}

	protected Property[] otherContactEmail(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactEmail", false));
	}

	protected Property[] otherContactAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactStreetAddress1", false));
	}

	protected Property[] otherContactCityTown(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactCityTown", false));
	}

	protected Property[] otherContactState(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactStateProv", false));
	}

	protected Property[] otherContactZip(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactZipPostal", false));
	}

	protected Property[] otherContactCountry(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.otherContactCountry", false));
	}

	protected Property[] timeZone(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.timeZone", false));
	}

	protected Property[] geographicRegion(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.geographicRegion", false));
	}

	protected Property[] brochureInfoDesc(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.brochureInfoDesc", false));
	}

	protected Property[] drivingDirections(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.drivingDirections", false));
	}

	protected Property[] adaAccessible(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.adaAccessible", false));
	}

	protected Property[] latitudeDirection(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeDirection", false));
	}

	protected Property[] latitudeDegree(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeDegree", false));
	}

	protected Property[] latitudeMinute(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeMinute", false));
	}

	protected Property[] latitudeSecond(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.latitudeSecond", false));
	}

	protected Property[] longitudeDirection(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeDirection", false));
	}

	protected Property[] longitudeDegree(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeDegree", false));
	}

	protected Property[] longitudeMinute(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeMinute", false));
	}

	protected Property[] longitudeSecond(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.longitudeSecond", false));
	}

	protected Property[] hoursOfOperation(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilityView-\\d+.hoursOfOperation", false));
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}

	protected Property[] apply(){
		return Property.concatPropertyArray(a(), ".text",  "Apply");
	}

	protected Property[] errorMsg(String msg){
		return Property.concatPropertyArray(div(),".className", "message msgerror", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] errorMsg(){
		return Property.concatPropertyArray(div(),".className", "message msgerror");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(facilitySetupPageContentDIV());
	}

	public String getAgency(){
		return browser.getTextFieldValue(agencyName());
	}

	public String getRegion(){
		return browser.getTextFieldValue(regionName());
	}

	public String getFacilityID(){
		IHtmlObject[] objs1 = browser.getHtmlObject(facilityIDSpan());
		IHtmlObject[] objs2 = browser.getHtmlObject(facilityIDTextField());
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(facilityIDSpan(), facilityIDTextField()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Facility ID objs can't be found.");
		}
		System.out.println(12);
		String value = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return value;
	}

	public void setFacilityName(String name){
		browser.setTextField(facilityName(),name);
	}

	public String getFacilityName(){
		return browser.getTextFieldValue(facilityName());
	}

	public void setFacilityShortName(String name){
		browser.setTextField(shortName(),name);
	}

	public String getFacilityShortName(){
		return browser.getTextFieldValue(shortName());
	}

	public String getStatus(){
		return browser.getDropdownListValue(status(), 0);
	}

	public boolean isStatusEditable(){
		IHtmlObject[] objs = browser.getHtmlObject(status());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find staus objects");
		}
		boolean isEditable = Boolean.valueOf(objs[0].getProperty(".isTextEdit"));
		Browser.unregister(objs);
		return isEditable;
	}
	
	public void setAddress(String address) {
		browser.setTextField(address(), address);
	}

	public String getAddress() {
		return browser.getTextFieldValue(address());
	}

	public void setSupplementalAddress(String suppAddress) {
		browser.setTextField(supplementalAddress(), suppAddress);
	}

	public String getSupplementalAddress() {
		return browser.getTextFieldValue(supplementalAddress());
	}

	public void setCityTown(String cityTown) {
		browser.setTextField(cityTown(), cityTown);
	}

	public String getCityTown() {
		return browser.getTextFieldValue(cityTown());
	}

	public void selectState(String state) {
		browser.selectDropdownList(state(), state);
	}

	public String getState() {
		return browser.getDropdownListValue(state(), 0);
	}

	public void selectCounty(String county) {
		browser.selectDropdownList(county(), county);
	}

	public String getCounty() {
		return browser.getDropdownListValue(county(), 0);
	}

	public void setZip(String zipcode) {
		browser.setTextField(zip(), zipcode);
	}

	public String getZip() {
		return browser.getTextFieldValue(zip());
	}

	public void selectCountry(String country){
		browser.selectDropdownList(country(), country);
	}

	public String getCountry(){
		return browser.getDropdownListValue(country(), 0);
	}

	public void setPublicLine(String publicLine) {
		browser.setTextField(publicLine(), publicLine);
	}

	public String getPublicLine() {
		return browser.getTextFieldValue(publicLine());
	}

	public void setFax(String fax) {
		browser.setTextField(fax(), fax);
	}

	public String getFax() {
		return browser.getTextFieldValue(fax());
	}

	public void setEmail(String email) {
		browser.setTextField(email(), email);
	}

	public String getEmail() {
		return browser.getTextFieldValue(email());
	}

	public void setWebsite(String webiste) {
		browser.setTextField(website(), webiste);
	}

	public String getWebsite() {
		return browser.getTextFieldValue(website());
	}

	public void setPrimaryContactLN(String lastName) {
		browser.setTextField(primaryContactLN(), lastName);
	}

	public String getPrimaryContactLN() {
		return browser.getTextFieldValue(primaryContactLN());
	}

	public void setPrimaryContactFN(String firstName) {
		browser.setTextField(primaryContactFN(), firstName);
	}

	public String getPrimaryContactFN() {
		return browser.getTextFieldValue(primaryContactFN());
	}

	public void setPrimaryContactPhone(String phone) {
		browser.setTextField(primaryContactPhone(), phone);
	}

	public String getPrimaryContactPhone() {
		return browser.getTextFieldValue(primaryContactPhone());
	}

	public void setPrimaryContactFax(String fax) {
		browser.setTextField(primaryContactFax(), fax);
	}

	public String getPrimaryContactFax() {
		return browser.getTextFieldValue(primaryContactFax());
	}

	public void setPrimaryContactEmail(String email) {
		browser.setTextField(primaryContactEmail(), email);
	}

	public String getPrimaryContactEmail() {
		return browser.getTextFieldValue(primaryContactEmail());
	}

	public void setPrimaryContactAddress(String address) {
		browser.setTextField(primaryContactAddress(), address);
	}

	public String setPrimaryContactAddress() {
		return browser.getTextFieldValue(primaryContactAddress());
	}

	public void setPrimaryContactCityTown(String city) {
		browser.setTextField(primaryContactCityTown(), city);
	}

	public String getPrimaryContactCityTown() {
		return browser.getTextFieldValue(primaryContactCityTown());
	}

	public void selectPrimaryContactState(String state) {
		browser.selectDropdownList(primaryContactState(), state);
	}

	public String getPrimaryContactState() {
		return browser.getDropdownListValue(primaryContactState(), 0);
	}

	public void setPrimaryContactZip(String zipCode) {
		browser.setTextField(primaryContactZip(), zipCode);
	}

	public String getPrimaryContactZip() {
		return browser.getTextFieldValue(primaryContactZip());
	}

	public void selectPrimaryContactCountry(String country) {
		browser.selectDropdownList(primaryContactCountry(), country);
	}

	public String getPrimaryContactCountry() {
		return browser.getDropdownListValue(primaryContactCountry(), 0);
	}

	public void setOtherContactLN(String lastName) {
		browser.setTextField(otherContactLN(), lastName);
	}

	public String getOtherContactLN() {
		return browser.getTextFieldValue(otherContactLN());
	}

	public void setOtherContactFN(String firstName) {
		browser.setTextField(otherContactFN(), firstName);
	}

	public String getOtherContactFN() {
		return browser.getTextFieldValue(otherContactFN());
	}

	public void setOtherContactPhone(String phone) {
		browser.setTextField(otherContactPhone(), phone);
	}

	public String setOtherContactPhone() {
		return browser.getTextFieldValue(otherContactPhone());
	}

	public void setOtherContactFax(String fax) {
		browser.setTextField(otherContactFax(), fax);
	}

	public String getOtherContactFax() {
		return browser.getTextFieldValue(otherContactFax());
	}

	public void setOtherContactEmail(String email) {
		browser.setTextField(otherContactEmail(), email);
	}

	public String setOtherContactEmail() {
		return browser.getTextFieldValue(otherContactEmail());
	}

	public void setOtherContactAddress(String address) {
		browser.setTextField(otherContactAddress(), address);
	}

	public String getOtherContactAddress() {
		return browser.getTextFieldValue(otherContactAddress());
	}

	public void setOtherContactCityTown(String city) {
		browser.setTextField(otherContactCityTown(), city);
	}

	public String getOtherContactCityTown() {
		return browser.getTextFieldValue(otherContactCityTown());
	}

	public void selectOtherContactState(String state) {
		browser.selectDropdownList(otherContactState(), state);
	}

	public String getOtherContactState() {
		return browser.getDropdownListValue(otherContactState(), 0);
	}

	public void setOtherContactZip(String zipCode) {
		browser.setTextField(otherContactZip(), zipCode);
	}

	public String setOtherContactZip() {
		return browser.getTextFieldValue(otherContactZip());
	}

	public void selectOtherContactCountry(String country) {
		browser.selectDropdownList(otherContactCountry(), country);
	}

	public String getOtherContactCountry() {
		return browser.getDropdownListValue(otherContactCountry(), 0);
	}

	public void selectTimeZone(String timezone) {
		browser.selectDropdownList(timeZone(), timezone);
	}

	public String getTimeZone() {
		return browser.getDropdownListValue(timeZone(), 0);
	}

	public void selectGeographicRegion(String geographicRegion) {
		browser.selectDropdownList(geographicRegion(), geographicRegion);
	}

	public String getGeographicRegion() {
		return browser.getDropdownListValue(geographicRegion(), 0);
	}

	public List<String> getGeographicRegionElements() {
		return browser.getDropdownElements(geographicRegion());
	}

	public void setBrochureInfoDesc(String dscr){
		browser.setTextArea(brochureInfoDesc(), dscr);
	}

	public String setBrochureInfoDesc(){
		return browser.getTextAreaValue(brochureInfoDesc());
	}

	public void setDriveDirection(String direction){
		browser.setTextArea(drivingDirections(), direction);
	}

	public String getDriveDirection(){
		return browser.getTextAreaValue(drivingDirections());
	}

	public void selectAdaAccessible(boolean adaAccessble){
		browser.selectDropdownList(adaAccessible(), adaAccessble?"Yes":"No");
	}

	public String getAdaAccessible(){
		return browser.getDropdownListValue(adaAccessible(), 0);
	}

	public void selectLatitudeDirection(String latitudeDirection) {
		browser.selectDropdownList(latitudeDirection(), latitudeDirection, true);		
	}

	public String getLatitudeDirection(String latitudeDirection) {
		return browser.getDropdownListValue(latitudeDirection(), 0);		
	}

	public void setLatitudeDegree(String latitudeDegree){
		browser.setTextField(latitudeDegree(), latitudeDegree);
	}

	public String getLatitudeDegree(){
		return browser.getTextFieldValue(latitudeDegree());
	}

	public void setLatitudeMinute(String latitudeMinute){
		browser.setTextField(latitudeMinute(), latitudeMinute);
	}

	public String getLatitudeMinute(){
		return browser.getTextFieldValue(latitudeMinute());
	}

	public void setLatitudeSecond(String latitudeSecond){
		browser.setTextField(latitudeSecond(), latitudeSecond);
	}

	public String getLatitudeSecond(){
		return browser.getTextFieldValue(latitudeSecond());
	}

	public void selectLongitudeDirection(String longitudeDirection) {
		browser.selectDropdownList(longitudeDirection(), longitudeDirection, true);		
	}

	public String getLongitudeDirection() {
		return browser.getDropdownListValue(longitudeDirection(), 0);		
	}

	public void setLongitudeDegree(String longitudeDegree){
		browser.setTextField(longitudeDegree(), longitudeDegree);
	}

	public String getLongitudeDegree(){
		return browser.getTextFieldValue(longitudeDegree());
	}

	public void setLongitudeMinute(String longitudeMinute){
		browser.setTextField(longitudeMinute(), longitudeMinute);
	}

	public String setLongitudeMinute(){
		return browser.getTextFieldValue(longitudeMinute());
	}

	public void setLongitudeSecond(String longitudeSecond){
		browser.setTextField(longitudeSecond(), longitudeSecond);
	}

	public String getLongitudeSecond(){
		return browser.getTextFieldValue(longitudeSecond());
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		browser.setTextArea(hoursOfOperation(), hoursOfOperation);
	}

	public String getHoursOfOperation() {
		return browser.getTextAreaValue(hoursOfOperation());
	}

	public void clickOK(){
		browser.clickGuiObject(ok());
	}

	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}

	public void clickApply(){
		browser.clickGuiObject(apply());
	}

	public void setupFacilityDetailsData(FacilityData fd) {
		logger.info("Setup facility information...");
		this.setFacilityName(fd.facilityName);
		this.setFacilityShortName(fd.shortName);
		this.setAddress(fd.mailingAddress);
		if(StringUtil.notEmpty(fd.mailingSupplementalAddress))
			this.setSupplementalAddress(fd.mailingSupplementalAddress);
		this.setCityTown(fd.mailingCityTown);
		this.selectCountry(fd.mailingCountry);
		ajax.waitLoading();
		this.selectState(fd.mailingState);
		ajax.waitLoading();
		if(StringUtil.notEmpty(fd.mailingCounty))
			this.selectCounty(fd.mailingCounty);
		this.setZip(fd.mailingZipCode);
		if(StringUtil.notEmpty(fd.publicLine )) 
			this.setPublicLine(fd.publicLine);
		if(StringUtil.notEmpty(fd.fax))
			this.setFax(fd.fax);
		if(StringUtil.notEmpty(fd.email)) 
			this.setEmail(fd.email);
		if(StringUtil.notEmpty(fd.website)) 
			this.setWebsite(fd.website);
		if(StringUtil.notEmpty(fd.primaryPOCLastName)) 
			this.setPrimaryContactLN(fd.primaryPOCLastName);
		if(StringUtil.notEmpty(fd.primaryPOCFirstName)) 
			this.setPrimaryContactFN(fd.primaryPOCFirstName);
		if(StringUtil.notEmpty(fd.primaryPOCPhone)) 
			this.setPrimaryContactPhone(fd.primaryPOCPhone);
		if(StringUtil.notEmpty(fd.primaryPOCFax)) 
			this.setPrimaryContactFax(fd.primaryPOCFax);
		if(StringUtil.notEmpty(fd.primaryPOCEmail))
			this.setPrimaryContactEmail(fd.primaryPOCEmail);
		if(StringUtil.notEmpty(fd.primaryPOCAddress)) 
			this.setPrimaryContactAddress(fd.primaryPOCAddress);
		if(StringUtil.notEmpty(fd.primaryPOCCityTown)) 
			this.setPrimaryContactCityTown(fd.primaryPOCCityTown);
		if(StringUtil.notEmpty(fd.primaryPOCZipCode))
			this.setPrimaryContactZip(fd.primaryPOCZipCode);
		if(StringUtil.notEmpty(fd.primaryPOCCountry)){
			this.selectPrimaryContactCountry(fd.primaryPOCCountry);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.primaryPOCState)){
			this.selectPrimaryContactState(fd.primaryPOCState);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.otherPOCLastName)) 
			this.setOtherContactLN(fd.otherPOCLastName);
		if(StringUtil.notEmpty(fd.otherPOCFirstName))
			this.setOtherContactFN(fd.otherPOCFirstName);
		if(StringUtil.notEmpty(fd.otherPOCPhone))
			this.setOtherContactPhone(fd.otherPOCPhone);
		if(StringUtil.notEmpty(fd.otherPOCFax))
			this.setOtherContactFax(fd.otherPOCFax);
		if(StringUtil.notEmpty(fd.otherPOCEmail))
			this.setOtherContactEmail(fd.otherPOCEmail);
		if(StringUtil.notEmpty(fd.otherPOCAddress)) 
			this.setOtherContactAddress(fd.otherPOCAddress);
		if(StringUtil.notEmpty(fd.otherPOCCityTown))
			this.setOtherContactCityTown(fd.otherPOCCityTown);
		if(StringUtil.notEmpty(fd.otherPOCZipCode))
			this.setOtherContactZip(fd.otherPOCZipCode);
		if(StringUtil.notEmpty(fd.otherPOCCountry)){
			this.selectOtherContactCountry(fd.otherPOCCountry);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.otherPOCState)) {
			this.selectOtherContactState(fd.otherPOCState);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.timeZone))
			this.selectTimeZone(fd.timeZone);
		if(StringUtil.notEmpty(fd.geographicRegion) && this.getGeographicRegionElements().size() > 0) 
			this.selectGeographicRegion(fd.geographicRegion);
		if(StringUtil.notEmpty(fd.brochureDescription))
			this.setBrochureInfoDesc(fd.brochureDescription);
		if(StringUtil.notEmpty(fd.drivingDirection))
			this.setDriveDirection(fd.drivingDirection);
		this.selectAdaAccessible(fd.adaAccessible);
		if(StringUtil.notEmpty(fd.latitudeDirection))
			this.selectLatitudeDirection(fd.latitudeDirection);
		if(StringUtil.notEmpty(fd.latitudeDegrees )) 
			this.setLatitudeDegree(fd.latitudeDegrees);
		if(StringUtil.notEmpty(fd.latitudeMinutes))
			this.setLatitudeMinute(fd.latitudeMinutes);
		if(StringUtil.notEmpty(fd.latitudeSeconds))
			this.setLatitudeSecond(fd.latitudeSeconds);
		if(StringUtil.notEmpty(fd.longitudeDirection)) 
			this.selectLongitudeDirection(fd.longitudeDirection);
		if(StringUtil.notEmpty(fd.longitudeDegrees)) 
			this.setLongitudeDegree(fd.longitudeDegrees);
		if(StringUtil.notEmpty(fd.longitudeMinutes)) 
			this.setLongitudeMinute(fd.longitudeMinutes);
		if(StringUtil.notEmpty(fd.longitudeSeconds)) 
			this.setLongitudeSecond(fd.longitudeSeconds);
		if(StringUtil.notEmpty(fd.hourOfOperation))
			this.setHoursOfOperation(fd.hourOfOperation);
	}

	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
	public boolean isErrorMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}
	
	public void verifyErrorMsgExisted(String msg, boolean isExist) {
		if (this.isErrorMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}

	public void setUpFacilityDetailAndClickApply(FacilityData fd) {
		setupFacilityDetailsData(fd);
		clickApply();
		waitLoading();
	}
}

