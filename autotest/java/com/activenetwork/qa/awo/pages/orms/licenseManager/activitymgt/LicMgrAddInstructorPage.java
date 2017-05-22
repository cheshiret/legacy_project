package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrAddInstructorPage extends LicMgrActivityMGTCommonPage{
	static class SingletonHolder {
		protected static LicMgrAddInstructorPage _instance = new LicMgrAddInstructorPage();
	}

	protected LicMgrAddInstructorPage() {
	}

	public static LicMgrAddInstructorPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] firstName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.firstName", false));
	}

	protected Property[] middleName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+.profile\\.primaryContact\\.middleName", false));
	}

	protected Property[] lastName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.lastName", false));
	}

	protected Property[] suffix(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.suffix", false));
	}

	protected Property[] dateOfBirth(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.dateOfBirth_ForDisplay", false));
	}

	protected Property[] instructorType(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.instructorType", false));
	}

	protected Property[] addNew(){
		return Property.concatPropertyArray(a(), ".text", "Add New");
	}

	protected Property[] homePhone(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.homePhone", false));
	}

	protected Property[] businessPhone(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.businessPhone", false));
	}

	protected Property[] mobilePhone(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.mobilePhone", false));
	}

	protected Property[] fax(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.fax", false));
	}

	protected Property[] email(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.email", false));
	}

	protected Property[] phoneContactPreference(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+.profile\\.primaryContact\\.phoneContactPreference", false));
	}

	protected Property[] preferredContactTime(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorView-\\d+\\.profile\\.primaryContact\\.preferredContactTime", false));
	}

	protected Property[] physicalAddressTR(){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^Physical Address.*", false));
	}

	protected Property[] mailingAddressTR(){
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^Mailing Address.*", false));
	}

	protected Property[] address(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.address", false));
	}

	protected Property[] zipCode(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false));
	}

	protected Property[] country(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.country", false));
	}

	protected Property[] verificationStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.verificationStatus.name", false));
	}

	protected Property[] validateButton(){
		return Property.toPropertyArray(".id", "Anchor");
	}

	protected Property[] supplementalAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false));
	}

	protected Property[] city(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.city", false));
	}

	protected Property[] state(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.state", false));
	}

	protected Property[] county(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.county", false));
	}

	protected Property[] sameAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
	}

	protected Property[] identifierType(){
		return Property.toPropertyArray(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierType", false));
	}

	protected Property[] identifierNumber(){
		return Property.toPropertyArray(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierNumber", false));
	}

	protected Property[] identifierState(){
		return Property.toPropertyArray(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierState", false));
	}

	protected Property[] identifierCountry(){
		return Property.toPropertyArray(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierCountry", false));
	}

	protected Property[] remove(){
		return Property.toPropertyArray(".id", "Anchor", ".text", "Remove");
	}

	protected Property[] add(){
		return Property.toPropertyArray(".id", "Anchor", ".text", "Add");
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}

	protected Property[] errorMsg(String msg){
		return Property.concatPropertyArray(div(),".className", "message msgerror", ".text", new RegularExpression(msg, false));
	}

	protected Property[] errorMsg(){
		return Property.concatPropertyArray(div(),".className", "message msgerror");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(phoneContactPreference());
	}

	public void setFirstName(String firstName){
		browser.setTextField(firstName(), firstName);
	}

	public void setMiddleName(String middleName){
		browser.setTextField(middleName(), middleName);
	}

	public void setLastName(String lastName){
		browser.setTextField(lastName(), lastName);
	}

	public void selectSuffix(String suffix){
		browser.selectDropdownList(suffix(), suffix);
	}

	public void setDateOfBirth(String dateOfBirth){
		browser.setTextField(dateOfBirth(), dateOfBirth);
	}

	public List<String> getInstructorTypes(){
		return browser.getDropdownElements(instructorType());
	}

	public void selectInstructorType(String instructorType){
		browser.selectDropdownList(instructorType(), instructorType);
	}

	public void clickAddNew(){
		browser.clickGuiObject(addNew());
	}

	public void setHomePhone(String homePhone){
		browser.setTextField(homePhone(), homePhone);
	}

	public void setBusinessPhone(String businessPhone){
		browser.setTextField(businessPhone(), businessPhone);
	}

	public void setMobilePhone(String mobilePhone){
		browser.setTextField(mobilePhone(), mobilePhone);
	}

	public void setFax (String fax){
		browser.setTextField(fax(), fax);
	}

	public void setEmail(String email){
		browser.setTextField(email(), email);
	}

	public void selectPhoneContactPreference(String phoneContactPreference){
		browser.selectDropdownList(phoneContactPreference(), phoneContactPreference);
	}

	public void selectpreferredContactTime(String preferredContactTime){
		browser.selectDropdownList(preferredContactTime(), preferredContactTime);
	}

	public void setPhysicalAddress(String address){
		browser.setTextField(address(), address);
	}

	public void setPhysicalZip(String zipCode){
		browser.setTextField(zipCode(), zipCode);
	}

	public void selectPhysicalCountry(String country){
		browser.selectDropdownList(country(), country, 0);
	}

	public void clickPhysicalValidate(){
		browser.clickGuiObject(validateButton(), 0);
	}

	public void setPhysicalSupplementalAddress(String supplementalAddress){
		browser.setTextField(supplementalAddress(), supplementalAddress);
	}

	public void setPhysicalCity(String city){
		browser.setTextField(city(), city);
	}

	public void selectPhysicalState(String state){
		browser.selectDropdownList(state(), state, 0);
	}

	public void selectPhysicalCounty(String county){
		browser.selectDropdownList(county(), county, 0);
	}

	public void selectSameAddress(){
		browser.selectCheckBox(sameAddress());
	}

	public void unSelectSameAddress(){
		browser.unSelectCheckBox(sameAddress());
	}

	public void setMailingAddress(String address){
		browser.setTextField(address(), address, 1);
	}

	public void setMailingZip(String zipCode){
		browser.setTextField(zipCode(), zipCode, 1);
	}

	public void selectMailingCountry(String country){
		browser.selectDropdownList(country(), country, 1);
	}

	public void clickMailingValidate(){
		browser.clickGuiObject(validateButton(), 1);
	}

	public void setMailingSupplementalAddress(String supplementalAddress){
		browser.setTextField(supplementalAddress(), supplementalAddress, 1);
	}

	public void setMailingCity(String city){
		browser.setTextField(city(), city, 1);
	}

	public void selectMailingState(String state){
		browser.selectDropdownList(state(), state, 1);
	}

	public void selectMailingCounty(String county){
		browser.selectDropdownList(county(), county, 1);
	}

	public void selectIdenType(String idenType, int index){
		browser.selectDropdownList(identifierType(), idenType, index);
	}

	public List<String> getIdenTypes(){
		return browser.getDropdownElements(identifierType());
	}

	public void setIdenNum(String idenNum, int index){
		browser.setTextField(identifierNumber(), idenNum, index);
	}

	public boolean isIdenStateExist(){
		return browser.checkHtmlObjectExists(identifierState());
	}

	public void selectIdenState(String state, int index){
		browser.selectDropdownList(identifierState(), state, index);
	}

	public boolean isIdenCountryExist(){
		return browser.checkHtmlObjectExists(identifierCountry());
	}

	public void selectIdenCountry(String country, int index){
		browser.selectDropdownList(identifierCountry(), country, index);
	}

	public void clickRemove(){
		browser.clickGuiObject(remove());
	}

	public void clickAdd(){
		browser.clickGuiObject(add());
	}

	public void clickOK(){
		browser.clickGuiObject(ok());
	}

	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}

	/** set Identifier Info */
	public void setIdentifiers(Customer customer) {
		List<CustomerIdentifier> tempList = new ArrayList<CustomerIdentifier>();
		tempList.addAll(customer.identifiers);
		if (StringUtil.notEmpty(customer.identifier.identifierType)) {
			if(!tempList.contains(customer.identifier))
				tempList.add(customer.identifier);

		}
		if(tempList.size()>0) {
			int available=getIdenTypes().size();
			if (available<tempList.size()) {
				int size=tempList.size()-available;
				for(int i=1;i<size;i++) {
					clickAdd();
					ajax.waitLoading();
				}
			}
			for (int i = 0; i < tempList.size(); i++) {				
				selectIdenType(tempList.get(i).identifierType, i);
				ajax.waitLoading();

				setIdenNum(tempList.get(i).identifierNum, i);

				if(isIdenStateExist()) {
					selectIdenState(tempList.get(i).state, i);
					ajax.waitLoading();
				}

				if (isIdenCountryExist()) {
					selectIdenCountry(tempList.get(i).country, i);
					ajax.waitLoading();
				}
			}
		}
	}

	public void setupInstructor(Customer customer){
		//Name/DOB
		setFirstName(customer.fName);
		setMiddleName(customer.mName);
		setLastName(customer.lName);
		if(StringUtil.notEmpty(customer.suffix))
			selectSuffix(customer.suffix);
		setDateOfBirth(customer.dateOfBirth);
		if(StringUtil.notEmpty(customer.instructorType))
			selectInstructorType(customer.instructorType);

		//Phone/Email
		if(StringUtil.isEmpty(customer.hPhone) && StringUtil.isEmpty(customer.bPhone) && StringUtil.isEmpty(customer.mPhone)) {
			customer.hPhone="9052866600";
		}
		setHomePhone(customer.hPhone);
		setBusinessPhone(customer.bPhone);
		setMobilePhone(customer.mPhone);
		setFax(customer.fax);
		setEmail(customer.email);
		if(StringUtil.notEmpty(customer.phoneContact))
			selectPhoneContactPreference(customer.phoneContact);
		if(StringUtil.notEmpty(customer.contactTime))
			selectpreferredContactTime(customer.contactTime);

		//Physical Address
		if(StringUtil.notEmpty(customer.physicalAddr.address)) 
			setPhysicalAddress(customer.physicalAddr.address);
		if(StringUtil.notEmpty(customer.physicalAddr.supplementalAddr))
			setPhysicalSupplementalAddress(customer.physicalAddr.supplementalAddr);
		if(StringUtil.notEmpty(customer.physicalAddr.city)) 
			setPhysicalCity(customer.physicalAddr.city);
		if(StringUtil.notEmpty(customer.physicalAddr.country)) {
			selectPhysicalCountry(customer.physicalAddr.country);
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(customer.physicalAddr.state)) {
			selectPhysicalState(customer.physicalAddr.state);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(customer.physicalAddr.county)) {
			selectPhysicalCounty(customer.physicalAddr.county);
			ajax.waitLoading();
		}	
		if(StringUtil.notEmpty(customer.physicalAddr.zip)) 
			setPhysicalZip(customer.physicalAddr.zip);

		if(customer.physicalAddr.isValidate){
			clickPhysicalValidate();
			ajax.waitLoading();
		}

		if (customer.mailAddrAsPhy) {
			selectSameAddress();
			ajax.waitLoading();
		} else {
			unSelectSameAddress();
			ajax.waitLoading();

			//Mailing Address
			if(StringUtil.notEmpty(customer.mailingAddr.address)) 
				setMailingAddress(customer.mailingAddr.address);
			if(StringUtil.notEmpty(customer.mailingAddr.supplementalAddr))
				setMailingSupplementalAddress(customer.mailingAddr.supplementalAddr);
			if(StringUtil.notEmpty(customer.mailingAddr.city)) 
				setMailingCity(customer.mailingAddr.city);
			if(StringUtil.notEmpty(customer.mailingAddr.country)) {
				selectMailingCountry(customer.mailingAddr.country);
				ajax.waitLoading();
			}
			if(StringUtil.notEmpty(customer.mailingAddr.state)) {
				selectMailingState(customer.mailingAddr.state);
				ajax.waitLoading();
			}	
			if(StringUtil.notEmpty(customer.mailingAddr.county)) {
				selectMailingCounty(customer.mailingAddr.county);
				ajax.waitLoading();
			}	
			if(StringUtil.notEmpty(customer.mailingAddr.zip)) 
				setMailingZip(customer.mailingAddr.zip);

			if(customer.mailingAddr.isValidate){
				clickMailingValidate();
				ajax.waitLoading();
			}
		}

		//set customer identifier info
		setIdentifiers(customer);
	}

	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}

	public boolean isErrorMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}

	public void verifyErrorMsgExisted(String msg, boolean isExist) {
		if (isErrorMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}

	//	public void setUpFacilityDetailAndClickApply(FacilityData fd) {
	//		setupFacilityDetailsData(fd);
	//		clickApply();
	//		waitLoading();
	//	}
}


