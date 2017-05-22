package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
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
public class LicMgrAddressesContactsPage extends LicMgrFacilityDetailsPage{
	static class SingletonHolder {
		protected static LicMgrAddressesContactsPage _instance = new LicMgrAddressesContactsPage();
	}

	protected LicMgrAddressesContactsPage() {
	}

	public static LicMgrAddressesContactsPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] facilityAddressesPageMark(){
		return Property.concatPropertyArray(div(), ".id", "tab_ActvityFacilitySubTabs_tabpanel", ".text", new RegularExpression("^Address.*", false));
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

	protected Property[] save(){
		return Property.concatPropertyArray(a(), "Save");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(facilityAddressesPageMark());
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

	public String getPrimaryContactAddress() {
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

	public String getOtherContactPhone() {
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

	public String getOtherContactEmail() {
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

	public String getOtherContactZip() {
		return browser.getTextFieldValue(otherContactZip());
	}

	public void selectOtherContactCountry(String country) {
		browser.selectDropdownList(otherContactCountry(), country);
	}

	public String getOtherContactCountry() {
		return browser.getDropdownListValue(otherContactCountry(), 0);
	}

	public void clickSave(){
		browser.clickGuiObject(save());
	}

	public void updateAddressesDetails(FacilityData fd) {
		logger.info("Update facility information...");
		//Address
		if(StringUtil.notEmpty(fd.mailingAddress))
			this.setAddress(fd.mailingAddress);
		if(StringUtil.notEmpty(fd.mailingSupplementalAddress))
			this.setSupplementalAddress(fd.mailingSupplementalAddress);
		if(StringUtil.notEmpty(fd.mailingCityTown))
			this.setCityTown(fd.mailingCityTown);
		if(StringUtil.notEmpty(fd.mailingCountry)){
			this.selectCountry(fd.mailingCountry);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.mailingState )){
			this.selectState(fd.mailingState);
			ajax.waitLoading();
		} 
		if(StringUtil.notEmpty(fd.mailingCounty)){
			this.selectCounty(fd.mailingCounty);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.mailingZipCode)) 
			this.setZip(fd.mailingZipCode);

		//Primary Contact
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
		if(StringUtil.notEmpty(fd.primaryPOCCountry)) {
			this.selectPrimaryContactCountry(fd.primaryPOCCountry);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.primaryPOCState)) {
			this.selectPrimaryContactState(fd.primaryPOCState);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(fd.primaryPOCZipCode)) 
			this.setPrimaryContactZip(fd.primaryPOCZipCode);
		//Other Contact
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
		if(StringUtil.notEmpty(fd.otherPOCCountry)) {
			this.selectOtherContactCountry(fd.otherPOCCountry);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(fd.otherPOCState)) {
			this.selectOtherContactState(fd.otherPOCState);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(fd.otherPOCZipCode)) 
			this.setOtherContactZip(fd.otherPOCZipCode);
	}

	public FacilityData getAddressesInfo() {
		FacilityData facility = new FacilityData();
		facility.mailingAddress = this.getAddress();
		facility.mailingSupplementalAddress = this.getSupplementalAddress();
		facility.mailingCityTown = this.getCityTown();
		facility.mailingState = this.getState();
		facility.mailingCounty = this.getCounty();
		facility.mailingZipCode = this.getZip();
		facility.mailingCountry = this.getCountry();
		facility.primaryPOCLastName = this.getPrimaryContactLN();
		facility.primaryPOCFirstName = this.getPrimaryContactFN();
		facility.primaryPOCPhone= this.getPrimaryContactPhone();
		facility.primaryPOCFax= this.getPrimaryContactFax();
		facility.primaryPOCEmail= this.getPrimaryContactEmail();
		facility.primaryPOCAddress= this.getPrimaryContactAddress();
		facility.primaryPOCCityTown = this.getPrimaryContactCityTown();
		facility.primaryPOCState = this.getPrimaryContactState();
		facility.primaryPOCZipCode = this.getPrimaryContactZip();
		facility.primaryPOCCountry = this.getPrimaryContactCountry();
		facility.otherPOCLastName = this.getOtherContactLN();
		facility.otherPOCFirstName = this.getOtherContactFN();
		facility.otherPOCPhone= this.getOtherContactPhone();
		facility.otherPOCFax= this.getOtherContactFax();
		facility.otherPOCEmail = this.getOtherContactEmail();
		facility.otherPOCAddress = this.getOtherContactAddress();
		facility.otherPOCCityTown = this.getOtherContactCityTown();
		facility.otherPOCState = this.getOtherContactState();
		facility.otherPOCZipCode = this.getOtherContactZip();
		facility.otherPOCCountry= this.getOtherContactCountry();
		return facility;
	}

	public void verifyAddressesInfo(FacilityData expectedFacility) {
		boolean result = true;
		FacilityData actualFacility = this.getAddressesInfo();
		result &= MiscFunctions.compareResult("Address", expectedFacility.mailingAddress, actualFacility.mailingAddress);
		result &= MiscFunctions.compareResult("Supplemental Address", expectedFacility.mailingSupplementalAddress, actualFacility.mailingSupplementalAddress);
		result &= MiscFunctions.compareResult("City Town", expectedFacility.mailingCityTown, actualFacility.mailingCityTown);
		result &= MiscFunctions.compareResult("State", expectedFacility.mailingState, actualFacility.mailingState);
		result &= MiscFunctions.compareResult("County", expectedFacility.mailingCounty, actualFacility.mailingCounty);
		result &= MiscFunctions.compareResult("Zip", expectedFacility.mailingZipCode, actualFacility.mailingZipCode);
		result &= MiscFunctions.compareResult("Country", expectedFacility.mailingCountry, actualFacility.mailingCountry);
		result &= MiscFunctions.compareResult("Primary Contact Last Name", expectedFacility.primaryPOCLastName, actualFacility.primaryPOCLastName);
		result &= MiscFunctions.compareResult("Primary Contact First Name", expectedFacility.primaryPOCFirstName, actualFacility.primaryPOCFirstName);
		result &= MiscFunctions.compareResult("Primary Contact Phone", expectedFacility.primaryPOCPhone, actualFacility.primaryPOCPhone);
		result &= MiscFunctions.compareResult("Primary Contact Fax", expectedFacility.primaryPOCFax, actualFacility.primaryPOCFax);
		result &= MiscFunctions.compareResult("Primary Contact Email", expectedFacility.primaryPOCEmail, actualFacility.primaryPOCEmail);
		result &= MiscFunctions.compareResult("Primary Contact Address", expectedFacility.primaryPOCAddress, actualFacility.primaryPOCAddress);
		result &= MiscFunctions.compareResult("Primary Contact City", expectedFacility.primaryPOCCityTown, actualFacility.primaryPOCCityTown);
		result &= MiscFunctions.compareResult("Primary Contact State", expectedFacility.primaryPOCState, actualFacility.primaryPOCState);
		result &= MiscFunctions.compareResult("Primary Contact Zip", expectedFacility.primaryPOCZipCode, actualFacility.primaryPOCZipCode);
		result &= MiscFunctions.compareResult("Primary Contact Country", expectedFacility.primaryPOCCountry, actualFacility.primaryPOCCountry);
		result &= MiscFunctions.compareResult("Other Contact Last Name", expectedFacility.otherPOCLastName, actualFacility.otherPOCLastName);
		result &= MiscFunctions.compareResult("Other Contact First Name", expectedFacility.otherPOCFirstName, actualFacility.otherPOCFirstName);
		result &= MiscFunctions.compareResult("Other Contact Phone", expectedFacility.otherPOCPhone, actualFacility.otherPOCPhone);
		result &= MiscFunctions.compareResult("Other Contact Fax", expectedFacility.otherPOCFax, actualFacility.otherPOCFax);
		result &= MiscFunctions.compareResult("Other Contact Email", expectedFacility.otherPOCEmail, actualFacility.otherPOCEmail);
		result &= MiscFunctions.compareResult("Other Contact Address", expectedFacility.otherPOCAddress, actualFacility.otherPOCAddress);
		result &= MiscFunctions.compareResult("Other Contact City", expectedFacility.otherPOCCityTown, actualFacility.otherPOCCityTown);
		result &= MiscFunctions.compareResult("Other Contact State", expectedFacility.otherPOCState, actualFacility.otherPOCState);
		result &= MiscFunctions.compareResult("Other Contact Zip", expectedFacility.otherPOCZipCode, actualFacility.otherPOCZipCode);
		result &= MiscFunctions.compareResult("Other Contact Country", expectedFacility.otherPOCCountry, actualFacility.otherPOCCountry);
		
		if(!result){
			throw new ErrorOnPageException("Falied to verify all check points in Addresses page. Please check the details from previous logs.");
		}
		logger.info("Successfully verify all check points in Addresses page.");
	}

	public void updateAddressesAndClickApply(FacilityData fd) {
		updateAddressesDetails(fd);
		clickSave();
		waitLoading();
	}
}
