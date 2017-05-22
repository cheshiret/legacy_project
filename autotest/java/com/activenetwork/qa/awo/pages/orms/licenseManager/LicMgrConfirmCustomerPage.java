package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * ScriptName: LicMgrConfirmCustomerPage
 * Description:
 * @date: Jan 14, 2011
 * @author qchen
 *
 */
public class LicMgrConfirmCustomerPage extends LicMgrCustomerDetailsPage {
	private static LicMgrConfirmCustomerPage _instance = null;
	
	protected LicMgrConfirmCustomerPage() {
		
	}
	
	public static LicMgrConfirmCustomerPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrConfirmCustomerPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false));
	}
	
	/**
	 * Method used to get all attributes' value
	 * @param element - the type of element needed to get value
	 * @param idRegEx - the regular expression of element's id value
	 * @return
	 */
	private String getAttributeValueById(String elementType, RegularExpression idRegEx, IHtmlObject top) {
		IHtmlObject objs[] = null;
		String toReturn = "";
		Property property[] = new Property[1];
		property[0] = new Property(".id", idRegEx);
		
		if(elementType.equalsIgnoreCase("TextField")){
			objs = browser.getTextField(property, top);
			if(null != objs && objs.length>0){
				toReturn = ((IText)objs[0]).getText();
			}
		} else if(elementType.equalsIgnoreCase("TextArea")) {
			objs = browser.getTextArea(property, top);
			if(null != objs && objs.length>0){
				toReturn = ((IText)objs[0]).getText();
			}
		} else if(elementType.equalsIgnoreCase("Select")) {
			objs = browser.getDropdownList(property, top);
			if(null != objs && objs.length>0){
				toReturn =((ISelect)objs[0]).getSelectedText();
			}
		} else {
			throw new ItemNotFoundException("Unknown element type: " + elementType);
		}
		
		//if(objs.length > 0) {
			//if(elementType.equalsIgnoreCase("Select")) {
				//toReturn = ((ISelect)objs[0]).getSelectedText().trim();
			//} else {
				//toReturn = objs[0].getProperty(".text").trim();
			//}
		//}
		
		Browser.unregister(objs);
		if(top != null){
			Browser.unregister(top);
		}
		return toReturn;
	}
	
	/**
	 * Overload method used to get all attributes' value
	 * @param elementType
	 * @param idRegEx
	 * @return
	 */
	public String getAttributeValueById(String elementType, RegularExpression idRegEx) {
		return getAttributeValueById(elementType, idRegEx, null);
	}
	
	private String prefix = "CustomerProfileView-\\d+\\.";
	
	/**
	 * Get customer number/MDWFP#
	 * @return
	 */
	public String getCustomerNumber() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"customerNumber", false));
	}
	
	/**
	 * Get customer status
	 * @return
	 */
	public String getStatus() {
		return getAttributeValueById("Select", new RegularExpression(prefix+"status", false));
	}
	
	/**
	 * Get customer class
	 * @return
	 */
	public String getCustomerClass() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"customerClass\\.name", false));
	}
	
	/**
	 * Get customer creation application name
	 * @return
	 */
	public String getCreationApplication() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"creationInfo\\.ApplicationName", false));
	}
	
	/**
	 * Get customer creation date
	 * @return
	 */
	public String getCreationDate() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"creationInfo\\.date", false));
	}
	
	/**
	 * Get customer creation user name
	 * @return
	 */
	public String getCreationUser() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"creationInfo\\.userName", false));
	}
	
	/**
	 * Get customer business name
	 * @return
	 */
	public String getBusinessName() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"businessNumber", false));
	}
	
	/**
	 * Get customer first name
	 * @return
	 */
	public String getFirstName() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"primaryContact\\.firstName", false));
		                                                               
	}
	
	/**
	 * Get customer middle name
	 * @return
	 */
	public String getMiddleName() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"primaryContact\\.middleName", false));
	}
	
	/**
	 * Get customer last name
	 * @return
	 */
	public String getLastName() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"primaryContact\\.lastName", false));
	}
	
	/**
	 * Get the date of customer's birth
	 * @return
	 */
	public String getDateOfBirth() {
		return getAttributeValueById("TextField", new RegularExpression(prefix+"dateOfBirth_ForDisplay", false));
	}
	
	/**
	 * Set customer date of birth
	 * @param dob
	 */
	public void setDateOfBirth(String dob) {
		browser.setTextField(".id", new RegularExpression(prefix+"dateOfBirth_ForDisplay", false), dob);
	}
	
	/**
	 * Get the top HtmlObject by id's value
	 * @param idValue
	 * @return
	 */
	public IHtmlTable getTableObject(String idValue) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", idValue);
		IHtmlTable physicalAddrTable = null;
		
		if(objs.length > 0) {
			physicalAddrTable = (IHtmlTable)objs[0];
		}
		
		return physicalAddrTable;
	}
	
	/**
	 * Get the Physical Address table object
	 * @return
	 */
	public IHtmlTable getPhysicalAddressTable() {
		return getTableObject("physicalAddress");
	}
	
	/**
	 * Get the Mailing Address table object
	 * @return
	 */
	public IHtmlTable getMailingAddressTable() {
		return getTableObject("maillingAddress");
	}
	
	/**
	 * Get physical address
	 * @return
	 */
	public String getPhysicalAddress() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.address", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical supplemental address
	 * @return
	 */
	public String getPhysicalSupplementalAddress() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.supplemental", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical city
	 * @return
	 */
	public String getPhysicalCityTown() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.city", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical state
	 * @return
	 */
	public String getPhysicalState() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.state", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical county
	 * @return
	 */
	public String getPhysicalCounty() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.county", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical zip code/postal
	 * @return
	 */
	public String getPhysicalZipCode() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.zipCode", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical country
	 * @return
	 */
	public String getPhysicalCountry() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.country", false), getPhysicalAddressTable());
	}
	
	/**
	 * Get physical status
	 * @return
	 */
	public String getPhysicalStatus() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.verificationStatus\\.name", false), getPhysicalAddressTable());
	}
	
	public void clickValidate() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*HFCustomerProfileConfirmationPage", false));
	}
	
	/**
	 * Check the 'Mailing Address same as Physical Address' check box 
	 */
	public void checkMailingAddrSameAsPhysicalAddr() {
		browser.selectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
		ajax.waitLoading();
	}
	
	/**
	 * Un-check the 'Mailing Address same as Physical Address' check box
	 */
	public void uncheckMailingAddrSameAsPhysicalAddr() {
		browser.unSelectCheckBox(".id", new RegularExpression("AddressView-\\d+\\.sameAddress", false));
		ajax.waitLoading();
	}
	
	/**
	 * Get mailing address
	 * @return
	 */
	public String getMailingAddress() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.address", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing supplemental address
	 * @return
	 */
	public String getMailingSupplementalAddress() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.supplemental", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing city
	 * @return
	 */
	public String getMailingCityTown() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.city", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing state
	 * @return
	 */
	public String getMailingState() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.state", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing county
	 * @return
	 */
	public String getMailingCounty() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.county", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing zip code/postal
	 * @return
	 */
	public String getMailingZipCode() {
		return getAttributeValueById("TextField", new RegularExpression("AddressView-\\d+\\.zipCode", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing country
	 * @return
	 */
	public String getMailingCountry() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.country", false), getMailingAddressTable());
	}
	
	/**
	 * Get mailing status
	 * @return
	 */
	public String getMailingStatus() {
		return getAttributeValueById("Select", new RegularExpression("AddressView-\\d+\\.verificationStatus\\.name", false), getMailingAddressTable());
	}
	
	/**
	 * Click OK button
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * Click Cancel button
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public String getMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	/**
	 * set customerInfo.
	 * @param cust
	 */
	public void setCustomerInfo(Customer cust){
		this.clickEdit();
		ajax.waitLoading();
		this.waitLoading();
		this.setCustomerProfile(cust);
		if(null != cust.physicalAddr){
			this.setPhysicalAddress(cust.physicalAddr);
		}
		if(null != cust.mailingAddr){
			if(!cust.mailAddrAsPhy){
				this.uncheckMailingAddrSameAsPhysicalAddr();
				this.setMaillingAddress(cust.mailingAddr);
			}
			else{
				this.uncheckMailingAddrSameAsPhysicalAddr();
				this.checkMailingAddrSameAsPhysicalAddr();
			}
		}
	}
	/**
	 * compare CustomerInfo page.
	 * @param cusomerInfo
	 * @return
	 */
	public boolean compareCustomerInfo(Customer cusomerInfo){
		boolean isEqual = this.compareCustInfoSuccess(cusomerInfo);
		String temp = this.getPhysicalAddress();
		isEqual &= MiscFunctions.compareResult("customer physical address", cusomerInfo.physicalAddr.address, temp);
		temp = this.getPhysicalZipCode();
		isEqual &= MiscFunctions.compareResult("customer physical zip", cusomerInfo.physicalAddr.zip, temp);
		temp = this.getPhysicalCountry();
		isEqual &= MiscFunctions.compareResult("customer physical country", cusomerInfo.physicalAddr.country, temp);
		temp= this.getPhysicalSupplementalAddress();
		isEqual &= MiscFunctions.compareResult("customer physical supplementalAddr", cusomerInfo.physicalAddr.supplementalAddr, temp);
		temp = this.getPhysicalCityTown();
		isEqual &= MiscFunctions.compareResult("customer physical city", cusomerInfo.physicalAddr.city, temp);
		temp = this.getPhysicalState();
		isEqual &= MiscFunctions.compareResult("customer physical state", cusomerInfo.physicalAddr.state, temp);
		temp = this.getPhysicalCounty();
		isEqual &= MiscFunctions.compareResult("customer physical county", cusomerInfo.physicalAddr.county, temp);
		temp = this.getMailingCountry();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr country", cusomerInfo.mailingAddr.country, temp);
		temp = this.getMailingAddress();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr address", cusomerInfo.mailingAddr.address, temp);
		temp = this.getMailingZipCode();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr zip", cusomerInfo.mailingAddr.zip, temp);
		temp = this.getMailingCityTown();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr city", cusomerInfo.mailingAddr.city, temp);
		temp= this.getMailingSupplementalAddress();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr supplementalAddr", cusomerInfo.mailingAddr.supplementalAddr, temp);
		temp = this.getMailingState();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr state", cusomerInfo.mailingAddr.state, temp);
		temp = this.getMailingCounty();
		isEqual &= MiscFunctions.compareResult("customer mailingAddr county", cusomerInfo.mailingAddr.county, temp);
		return  isEqual;
	}

	/**
	 * Set customer details info in Confirm Customer page during purchase process
	 * @param cust
	 *//*
	public void setCustomerInfo(Customer cust) {
		//TODO add other set methods
		if(StringUtil.notEmpty(cust.fName)null != cust.fName && cust.fName.length()>0){
			this.setFirstName(cust.fName);
		}
		if(StringUtil.notEmpty(cust.lName)){
			this.setLastName(cust.lName );
		}
		if(StringUtil.notEmpty(cust.mName)){
			this.setMiddleName(cust.mName);
		}
		if(StringUtil.notEmpty(cust.hPhone)){
			this.setHomePhone(cust.hPhone);
		}
		if(StringUtil.notEmpty(cust.bPhone)){
			this.setBusniessPhone(cust.bPhone);
		}
		if(StringUtil.notEmpty(cust.mPhone)){
			this.setMobilePhone(cust.mPhone);
		}
		if(StringUtil.notEmpty(cust.fax)){
			this.setFax(cust.fax);
		}
		if(StringUtil.notEmpty(cust.email)){
			this.setEmail(cust.email);
		}
		if(StringUtil.notEmpty(cust.phoneContact)){
			this.selectPhoneContactPreferred(cust.phoneContact);
		}
		if(StringUtil.notEmpty(cust.contactTime)){
			this.selectPreferredContactTime(cust.contactTime);
		}
		if(StringUtil.notEmpty(cust.custGender)){
			this.selectGender(cust.custGender);
		}
		if(StringUtil.notEmpty(cust.ethnicity)){
			this.selectEthnicity(cust.ethnicity);
		}
		if(StringUtil.notEmpty(cust.solicitationIndcator)){
			this.selectSolicitation(cust.solicitationIndcator);
		}
		if(null != cust.physicalAddr){
			this.setPhysicalAddress(cust.physicalAddr);
		}
		if(!cust.mailAddrAsPhy){
			if(cust.mailAddrAsPhy){
				this.setMaillingAddress(cust.mailingAddr);
			}
		}
	}
	*//**
	 * set first name.
	 * @param firstName
	 *//*
	public void setFirstName(String firstName){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.firstName", false),firstName);
	}
	*//**
	 * set last name.
	 * @param middleName
	 *//*
	public void setMiddleName(String middleName){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.middleName", false),middleName);
	}
	*//**
	 * 
	 * @param LastName
	 *//*
	public void setLastName(String LastName){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.lastName", false),LastName);
	}
	*//**
	 * set home phone.
	 * @param homePhone
	 *//*
	public void setHomePhone(String homePhone){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.homePhone", false),homePhone);
	}
	*//**
	 * set business phone.
	 * @param businessPhone
	 *//*
	public void setBusniessPhone(String businessPhone){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.businessPhone", false),businessPhone);
	}
	*//**
	 * set mobile phone.
	 * @param mobilePhone
	 *//*
	public void setMobilePhone(String mobilePhone){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.mobilePhone", false),mobilePhone);
	}
	*//**
	 * set fax.
	 * @param fax
	 *//*
	public void setFax(String fax){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.fax", false),fax);
	}
	*//**
	 * set email
	 * @param email
	 *//*
	public void setEmail(String email){
		browser.setTextField(".id",new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.email", false),email);
	}
	*//**
	 * select phone contact preferred.
	 * @param pContactPref
	 *//*
	public void selectPhoneContactPreferred(String pContactPref){
		browser.selectDropdownList(".id", new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.phoneContactPreference", false),pContactPref);
	}
	
	*//**
	 * select preferred contact time.
	 * @param preContactTime
	 *//*
	public void selectPreferredContactTime(String preContactTime){
		browser.selectDropdownList(".id", new RegularExpression("HFCustomerProfileView-\\d+\\.primaryContact\\.preferredContactTime", false),preContactTime);
	}
	*//**
	 * select gender.
	 * @param gender
	 *//*
	public void selectGender(String gender){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false),gender);
	}
	*//**
	 * select ethnicity
	 * @param ethnicity
	 *//*
	public void selectEthnicity(String ethnicity){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false),ethnicity);
	}
	*//**
	 * s
	 * @param solicitation
	 *//*
	public void selectSolicitation(String solicitation){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5033\\]\\:BOOLEAN_YESNO", false),solicitation);
	}
	*//**
	 * set address.
	 * @param address
	 * @param index
	 *//**/
	public void setAddress(String address,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address", false), address,index);
	}
	/**
	 * set zip
	 * @param zip
	 * @param index
	 */
	public void setZip(String zip,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false), zip,index);
	}
	/**
	 * set country.
	 * @param country
	 * @param index
	 */
	public void selectCountry(String country,int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country", false), country,index);
	}
	/**
	 * set supplemental Address.
	 * @param suppleAddress
	 * @param index
	 */
	public void setSupplementalAddress(String suppleAddress,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false), suppleAddress,index);
	}
	/**
	 * set city.
	 * @param city
	 * @param index
	 */
	public void setCity(String city,int index){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.city", false), city,index);
	}
	/**
	 * select state.
	 * @param state
	 */
	public void selectState(String state,int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.state", false), state,index);
	}
	/**
	 * select county.
	 * @param county
	 * @param index
	 */
	public void selectCounty(String county,int index){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county", false), county,index);
	}
	/**
	 * click validate.
	 * @param index
	 */
	public void clickValidate(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate",index);
	}
	/**
	 * set address info.
	 * @param custAddr
	 * @param index
	 */
	 
	 public void setAddressInfo(Address custAddr,int index){
		 if(StringUtil.notEmpty(custAddr.country)){
			 this.selectCountry(custAddr.country, index);
		    	ajax.waitLoading();
		 }
	    if(StringUtil.notEmpty(custAddr.state)){
	    	this.selectState(custAddr.state, index);
	    	ajax.waitLoading();
	    }
	    if(StringUtil.notEmpty(custAddr.county))	{
	    	this.selectCounty(custAddr.county, index);
	    	ajax.waitLoading();
	    }
	    if(StringUtil.notEmpty(custAddr.city)){
	    	this.setCity(custAddr.city, index);
	    }
	    if(StringUtil.notEmpty(custAddr.address)){
	    	this.setAddress(custAddr.address, index);
	    }
	    if(StringUtil.notEmpty(custAddr.zip)){
	    	this.setZip(custAddr.zip, index);
	    }
	    if(StringUtil.notEmpty(custAddr.supplementalAddr)){
	    	this.setSupplementalAddress(custAddr.supplementalAddr, index);
	    }
	    if(custAddr.isValidate){
	    	this.clickValidate(index);
	    }
	    ajax.waitLoading();
	 }
	 /**
	  * set physical address.
	  * @param custAddr
	  */
	public void setPhysicalAddress(Address custAddr){
		this.setAddressInfo(custAddr, 0);
	}
	/*
	 * set mailling address.
	 * @param mailingAddr
	 */
	public void setMaillingAddress(Address mailingAddr){
	    this.setAddressInfo(mailingAddr, 1);
	}
	
	public boolean getDisableAttribute(String text){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", text);
		if(objs.length<1){
			throw new ErrorOnDataException("No OK link element exist");
		}
		String isDisable = objs[0].getProperty(".isDisabled");
		Browser.unregister(objs);
		return Boolean.parseBoolean(isDisable);
	}
	/**
	 * Get the status of isdisable attibute.
	 * @return
	 */
	public void verifyOkButtonEnable(){
		boolean isDisenable =this.getDisableAttribute("OK");
		if(isDisenable){
			throw new ErrorOnPageException("The ok button should enable always");
		}else{
			logger.info("Ok button is enable always");
		}
	}
	/**
	 * get the cancel button disable attribute.
	 * @return
	 */
	public void verifyCancelButtonEnable(){
		boolean isDisenable =this.getDisableAttribute("Cancel");;
		if(isDisenable){
			throw new ErrorOnPageException("The cancel button should enable always");
		}else{
			logger.info("Cancel button is enable always");
		}
	}
	/**
	 * edit customer profile.
	 */
	public void editCustProfile(Customer cust){
		this.clickEdit();
		ajax.waitLoading();
		this.waitLoading();
		this.setCustomerInfo(cust);
		this.clickOK();
	}
	
} 
