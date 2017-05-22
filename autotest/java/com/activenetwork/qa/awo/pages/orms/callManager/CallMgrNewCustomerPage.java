package com.activenetwork.qa.awo.pages.orms.callManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 14, 2012
 */
public class CallMgrNewCustomerPage extends CallMgrTopMenuPage {
	
	private static CallMgrNewCustomerPage _instance = null;
	
	private  CallMgrNewCustomerPage() {}
	
	public static CallMgrNewCustomerPage getInstance() {
		if(null == _instance) {
			_instance = new CallMgrNewCustomerPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {                                                                      
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier.identifierType", false));
	}

	public void setBusinessName(String bName) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.businessName", false);
		browser.setTextField(".id", idPattern, bName);
	}

	public boolean checkBusinessName() {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.businessName", false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setFirstName(String fName) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.firstName",
				false);
		browser.setTextField(".id", idPattern, fName);
	}

	public void setMiddleName(String mName) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.middleName",
				false);
		browser.setTextField(".id", idPattern, mName);
	}

	public void setLastName(String lName) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.lastName", false);
		browser.setTextField(".id", idPattern, lName);
	}

	public void selectSuffix(String suffix) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.suffix", false);
		browser.selectDropdownList(".id", idPattern, suffix);
	}

	public void setDateOfBirth(String date) {
		browser.setTextField(dateOfBirth(), date);
	}

	public void selectOverrideRequiredIdentifiers() {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.overrideRequiredIdentifer", false);
		browser.selectCheckBox(".id", idPattern);
		ajax.waitLoading();
	}

	public void unselectOverrideRequiredIdentifiers() {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.overrideRequiredIdentifer", false);
		browser.unSelectCheckBox(".id", idPattern);
	}

	public void setOverrideReason(String reason) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.overrideReason", false);
		
		browser.focusOn(".id", idPattern);
		browser.setTextField(".id", idPattern, reason);
	}

	public void setHomePhone(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.homePhone",
				false);
		browser.setTextField(".id", idPattern, phone);
	}

	public boolean checkHomePhone() {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.homePhone",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setBusinessPhone(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.businessPhone",
				false);
		browser.setTextField(".id", idPattern, phone);
	}

	public boolean checkBusinessPhone() {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.businessPhone",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setMobilePhone(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.mobilePhone",
				false);
		browser.setTextField(".id", idPattern, phone);
	}

	public boolean checkMobilePhone() {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.mobilePhone",
				false);
		return browser.checkHtmlObjectExists(".id", idPattern);
	}

	public void setFax(String phone) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.fax", false);
		browser.setTextField(".id", idPattern, phone);
	}

	public void setEmail(String email) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.email", false);
		browser.setTextField(".id", idPattern, email);
	}

	public void selectPhoneContactPreference(String option) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.phoneContactPreference",
				false);
		browser.selectDropdownList(".id", idPattern, option);
	}

	public void selectPreferedContactTime(String option) {
		RegularExpression idPattern = new RegularExpression(
				"((HFCustomerProfileView)|(CustomerProfileView))-\\d+\\.primaryContact\\.preferredContactTime",
				false);
		browser.selectDropdownList(".id", idPattern, option);
	}

	public void selectResident(boolean isResident) {
		String option = isResident ? "Yes" : "No";
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:BOOLEAN_YESNO",
				false);
		browser.selectDropdownList(".id", idPattern, option);
	}

	public void setDate(String date) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\]:DATE_ForDisplay",
				false);
		browser.setTextField(".id", idPattern, date);
	}

	public void selectEyeColor(String color) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000001\\]", false);
		browser.selectDropdownList(".id", idPattern, color);
	}

	public void clickGender(){
		RegularExpression idPattern = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		browser.clickGuiObject(".id", idPattern);
	}
	
	public void selectGender(String gender) {
		RegularExpression idPattern = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		// Property[] p=new Property[]{
		// new Property(".id",idPattern),
		// new Property(".text","MaleFemaleUnknown"),
		// };
		browser.selectDropdownList(".id", idPattern, gender, false);
	}

	public void setHeight(String height) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000102\\]", false);
		browser.setTextField(".id", idPattern, height);
	}

	public void setPhysicalAddress(String address) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.address", false);
		browser.setTextField(".id", idPattern, address, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void setPhysicalSupplemental(String supplemental) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.supplemental", false);
		browser.setTextField(".id", idPattern, supplemental, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void setPhysicalCityTown(String city) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.city", false);
		browser.setTextField(".id", idPattern, city, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectPhysicalState(String state) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, state, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectPhysicalState(int index) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public IHtmlObject[] getPhysicalAddressTable() {
		return browser.getHtmlObject(".id", "physicalAddress");
	}

	public IHtmlObject[] getMaillingAddressTable() {
		return browser.getHtmlObject(".id", "maillingAddress");
	}

	public void selectPhysicalCounty(String county) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.county", false);
		browser.selectDropdownList(".id", idPattern, county, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void setPhysicalZipCode(String zip) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.zipCode", false);
		browser.setTextField(".id", idPattern, zip, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectPhysicalCountry(String country) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, country, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectPhysicalCountry(int index) {
		IHtmlObject[] table = getPhysicalAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void clickPhysicalValidate() {
		IHtmlObject[] table = getPhysicalAddressTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", true,
				0, table[0]);
		Browser.unregister(table);
	}

	public void setMaillingAddress(String address) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.address", false);
		browser.setTextField(".id", idPattern, address, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void setMaillingCityTown(String city) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.city", false);
		browser.setTextField(".id", idPattern, city, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectMaillingState(String state) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, state, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectMaillingState(int index) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.state", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectMaillingCounty(String county) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.county", false);
		browser.selectDropdownList(".id", idPattern, county, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void setMaillingZipCode(String zip) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.zipCode", false);
		browser.setTextField(".id", idPattern, zip, true, 0, table[0]);
		Browser.unregister(table);
	}

	public void selectMaillingCountry(String country) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, country, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void selectMaillingCountry(int index) {
		IHtmlObject[] table = getMaillingAddressTable();
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.country", false);
		browser.selectDropdownList(".id", idPattern, index, true, table[0]);
		Browser.unregister(table);
		ajax.waitLoading();
	}

	public void clickMaillingValidate() {
		IHtmlObject[] table = getMaillingAddressTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", true,
				0, table[0]);
		Browser.unregister(table);
	}

	public void selectMailingAddressSameAsPhysicalAddress() {
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.sameAddress", false);
		browser.selectCheckBox(".id", idPattern);
	}

	public void unselectMailingAddressSameAsPhysicalAddress() {
		RegularExpression idPattern = new RegularExpression(
				"AddressView-\\d+\\.sameAddress", false);
		browser.unSelectCheckBox(".id", idPattern);
		ajax.waitLoading();
	}

	public void selectIdentifierType(String type, int index) {
		RegularExpression idPattern = new RegularExpression(
				"CustomerIdentifierView-\\d+\\.identifier\\.identifierType",
				false);
		browser.selectDropdownList(".id", idPattern, type, index);
		ajax.waitLoading();
	}

	public void selectIdentifierType(String type) {
		this.selectIdentifierType(type, 0);
	}

	public void setIdentifierNumber(String number, int index) {
		RegularExpression idPattern = new RegularExpression(
				"CustomerIdentifierView-\\d+\\.identifier\\.identifierNumber",
				false);
		browser.setTextField(".id", idPattern, number, index);
	}

	public void setIdentifierNumber(String number) {
		this.setIdentifierNumber(number, 0);
	}

	public void removeIdentifier(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true,
				index);
	}

	public void addIdentifier() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}

	public void selectEthnicity(String ethnicity) {

		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false);
		// Property[] p=new Property[]{
		// new Property(".id",idPattern),
		// new Property(".text",new
		// RegularExpression("^WhiteBlack.*UnKnown$",false)),
		// };
		browser.selectDropdownList(".id", idPattern, ethnicity);
	}

	public void selectSolicitation(String solicitation) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5033\\]:BOOLEAN_YESNO",
				false);
		// Property[] p=new Property[]{
		// new Property(".id",idPattern),
		// new Property(".text","NoYes"),
		// };
		browser.selectDropdownList(".id", idPattern, solicitation);
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
		ajax.waitLoading();
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
		ajax.waitLoading();
	}

	public void clickPhyValidate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate");
	}

	public boolean checkPhyValidateExist() {
		boolean flag = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Validate");
		if (objs.length < 1) {
			flag = false;
		}
		Browser.unregister(objs);
		return flag;
	}

	public boolean checkMailValidateExist() {
		boolean flag = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Validate");
		if (objs.length < 2) {
			flag = false;
		}
		Browser.unregister(objs);
		return flag;
	}

	public void clickMailValidate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate", 1);
	}

	public String getPhyStatus() {
		RegularExpression regx = new RegularExpression(
				"AddressView-\\d+\\.verificationStatus\\.name", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getMailStatus() {
		String status = "";
		RegularExpression regx = new RegularExpression(
				"AddressView-\\d+\\.verificationStatus\\.name", false);
		IHtmlObject[] objs = browser.getTextField(".id", regx);

		if (objs.length < 2) {
			throw new ItemNotFoundException(
					"The Status for mailing address is not found~!");
		}

		status = objs[1].getProperty(".text");
		return status;
	}

	public void selectIdentifierCountry(String country, int index) {
		RegularExpression regx = new RegularExpression(
				"CustomerIdentifierView-\\d+\\.identifier\\.country", false);
		browser.selectDropdownList(".id", regx, country);
	}

	public void selectIdentifierCountry(String country) {
		this.selectIdentifierCountry(country, 0);
	}

	public boolean checkIdentifierCountryExist() {
		RegularExpression regx = new RegularExpression(
				"CustomerIdentifierView-\\d+\\.identifier\\.country", false);
		return browser.checkHtmlObjectExists(".id", regx);
	}

	/** click 'Add' to add Identifier or Additional Contacts*/
	public void clickAdd(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", index);
		ajax.waitLoading();
	}
	
    /**
     * click 'Add' to add Identifier 
     * @param index --0: add customer identifier when customer class is Individual
     *              --1: add customer identifier when customer class is other than Individual
     *                   Because it exists addition address which has Add button
     */
	public void clickAddIdentifier(int index) {
		this.clickAdd(index);
	}
	
	/** click 'Add' to add Additional Contacts */
	public void clickAddAdditionalContacts () {
		this.clickAdd(0);
	}

	/** Get customer number */
	public String getCustomerNum() {
		RegularExpression regx = new RegularExpression(
				"HFCustomerProfileView-\\d+\\.customerNumber", false);
		return browser.getTextFieldValue(".id", regx);
	}

	/** Get customer class */
	public String getCustomerClass() {
		RegularExpression regx = new RegularExpression(
				"HFCustomerProfileView-\\d+\\.customerClass.name", false);
		return browser.getTextFieldValue(".id", regx);
	}

	/** Get creation application */
	public String getCreationApplication() {
		RegularExpression regx = new RegularExpression(
				"HFCustomerProfileView-\\d+\\.creationInfo.ApplicationName",
				false);
		return browser.getTextFieldValue(".id", regx);
	}

	/** Get creation date */
	public String getCreationDate() {
		RegularExpression regx = new RegularExpression(
				"HFCustomerProfileView-\\d+\\.creationInfo.date", false);
		return browser.getTextFieldValue(".id", regx);
	}

	/** Get creation user */
	public String getCreationUser() {
		RegularExpression regx = new RegularExpression(
				"HFCustomerProfileView-\\d+\\.creationInfo.userName", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	/** Click the validate */
	public void clickValidate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate");	
	}
	
	/** set Identifier Info */
	public void setIdentifiers(Customer cust) {
		List<CustomerIdentifier> tempList = new ArrayList<CustomerIdentifier>();
		tempList.addAll(cust.identifiers);
		if (cust.identifier.identifierType != null
				&& cust.identifier.identifierType.trim().length() > 0) {
			if(!tempList.contains(cust.identifier)) {
				tempList.add(cust.identifier);
			}
		}
		if(tempList.size()>0) {
			int available=getNumberOfIdentifierDropdownList();
			if (available<tempList.size()) {
				int size=tempList.size()-available;
				for(int i=0;i<size;i++) {
					if(cust.customerClass.equalsIgnoreCase("Individual")){
						this.clickAddIdentifier(0);
					}else{
						this.clickAddIdentifier(1);
					}
					ajax.waitLoading();
				}
			}
			for (int i = 0; i < tempList.size(); i++) {				
				this.selectIdentifierType(tempList.get(i).identifierType, i);
				ajax.waitLoading();
				this.setIdentifierNumber(tempList.get(i).identifierNum, i);

				if (this.checkIdentifierCountryExist()) {
					this.selectIdentifierCountry(tempList.get(i).country, i);
				}
			}
		}
	}
	
	public int getNumberOfIdentifierDropdownList() {
		RegularExpression idpattern=new RegularExpression("CustomerIdentifierView-\\d+.identifier.identifierType",false);
		IHtmlObject[] objs=browser.getDropdownList(".id",idpattern);
		
		int size=objs.length;
		
		Browser.unregister(objs);
		return size;
	}

	/**
	 * Get warning message
	 * 
	 * @return warning message
	 */
	public String getErrorMsg() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if (objs.length > 0) {
			warnMes = objs[0].getProperty(".text");
		} else {
			throw new com.activenetwork.qa.testapi.ObjectNotFoundException("Object can't find.");
		}

		Browser.unregister(objs);
		return warnMes;
	}
	
	
	public String getMessage() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if (objs.length > 0) {
			warnMes = objs[0].getProperty(".text");
		} 
		Browser.unregister(objs);
		return warnMes;
	}

	public void setCustomerDetails(Customer cust) {
		if (this.checkBusinessName() && null != cust.businessName
				&& cust.businessName.length() > 0) {
			this.setBusinessName(cust.businessName);
		}
		this.setFirstName(cust.fName);
		if(null!=cust.mName && cust.mName.length()>0){
			this.setMiddleName(cust.mName);
		}
		this.setLastName(cust.lName);
		this.setDateOfBirth(cust.dateOfBirth);
		if (this.checkHomePhone() && null != cust.hPhone
				&& cust.hPhone.length() > 0) {
			this.setHomePhone(cust.hPhone);
		}
		if (this.checkBusinessPhone() && null != cust.bPhone
				&& cust.bPhone.length() > 0) {
			this.setBusinessPhone(cust.bPhone);
		}
		if (this.checkMobilePhone() && null != cust.mPhone
				&& cust.mPhone.length() > 0) {
			this.setMobilePhone(cust.mPhone);
		}
		
		this.clickGender();
		this.selectGender(cust.custGender);  

		this.selectEthnicity(cust.ethnicity);
		this.selectSolicitation(cust.solicitationIndcator);
		this.selectSuffix(cust.suffix);
		if(cust.overrideReqId){
			this.selectOverrideRequiredIdentifiers();
			this.setOverrideReason(cust.overideReason);
		}else{
			this.unselectOverrideRequiredIdentifiers();
		}
		this.setFax(cust.fax);
		this.setEmail(cust.email);
		this.setHeight(cust.height);
		this.setPhysicalAddress(cust.physicalAddr.address);
		this.setPhysicalSupplemental(cust.physicalAddr.supplementalAddr);
		this.setPhysicalCityTown(cust.physicalAddr.city);
		this.selectPhysicalCountry(cust.physicalAddr.country);
		ajax.waitLoading();
		this.selectPhysicalState(cust.physicalAddr.state);
		ajax.waitLoading();
		if (null != cust.physicalAddr.county
				&& cust.physicalAddr.county.length() > 0) {
			this.selectPhysicalCounty(cust.physicalAddr.county);
			ajax.waitLoading();
		}
		this.setPhysicalZipCode(cust.physicalAddr.zip);
		ajax.waitLoading();
		if (cust.mailAddrAsPhy) {
			this.selectMailingAddressSameAsPhysicalAddress();
		} else {
			this.unselectMailingAddressSameAsPhysicalAddress();
		}
		ajax.waitLoading();
		//set customer identifier info
		this.setIdentifiers(cust);
		if(cust.physicalAddr.isValidate){
			this.clickValidate();
		}
		ajax.waitLoading();
	}
	
	private Property[] dateOfBirth(){
		return Property.toPropertyArray(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false));
	}

	/**
	 * Get date of birth value
	 * @return
	 */
	public String getDateOfBirth() {
		return browser.getTextFieldValue(dateOfBirth());
	}
	
	/**
	 * Verify if the date of birth input value is valid or not
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyDateOfBirthTextFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(dateOfBirth())[0], invalidDates);
	}
	
	/**
	 * This method was used to get the first identifier type value
	 * @return
	 */
	public String getIdentifierType(){
		return browser.getDropdownListValue(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierType", false));
	}
	
	/**
	 * This method was used to get the first identifier number value
	 * @return
	 */
	public String getIdentifierNumber(){
		return browser.getTextFieldValue(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierNumber", false));
	}
	
	/**
	 * This method was used to get the first identifier country value
	 * @return
	 */
	public String getIdentifierCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression("(CustomerIdentifierView-\\d+\\.identifier\\.country)|(IdentifierView-\\d+\\.country)", false));
	}
}
