package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrCustomerDetailsPage extends LicMgrCommonTopMenuPage {
	private static LicMgrCustomerDetailsPage _instance = null;
	
	public static LicMgrCustomerDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrCustomerDetailsPage();
		}

		return _instance;
	}

	protected LicMgrCustomerDetailsPage() {

	}
	
	public RegularExpression custStatusIdPattern = new RegularExpression(
			"(HF)?CustomerProfileView-\\d+\\.status", false);
	public RegularExpression custNumIdPattern = new RegularExpression(
			"(HF)?CustomerProfileView-\\d+\\.customerNumber", false);
	public RegularExpression custClassIdPattern = new RegularExpression(
			"(HF)?CustomerProfileView-\\d+\\.customerClass\\.name", false);
	public RegularExpression creationApplicationIdPattern = new RegularExpression(
			"(HF)?CustomerProfileView-\\d+\\.creationInfo\\.ApplicationName", false);
	public RegularExpression creationDateIdPattern = new RegularExpression(
			"(HF)?CustomerProfileView-\\d+\\.creationInfo\\.date", false);
	public RegularExpression creationUserIdPattern = new RegularExpression(
			"(HF)?CustomerProfileView-\\d+\\.creationInfo\\.userName", false);

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"customerTabs");
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void clickNotesAndAlertsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Notes & Alerts", false));
		
	}

	public void clickIdentifiersTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Identifiers");
		
	}

	public void clickEducationTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Education(\\([0-9]+\\))?", false));
		
	}
	
	public void clickHistoricalOrdersTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Historical Orders(\\([0-9]+\\))?", false));
		
	}

	public void clickCertificationsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Certifications(\\([0-9]+\\))?", false));
		
	}

	public void clickSuspensionsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Suspensions(\\([0-9]+\\))?", false));
		
	}

	public void clickVehiclesTab() {
		browser.clickGuiObject(".id", new RegularExpression("customerTabs_\\d*", false), ".text",
				new RegularExpression("Vehicles", false));
		
	}

	public void clickPrivilegesTab() {
		browser.clickGuiObject(".id",new RegularExpression("customerTabs_\\d+", false),".text",
				new RegularExpression("(Privileges|Licences)(\\([0-9]+\\))?", false));
	}

	public void clickHarvestTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Harvest", false));
	}

	public void clickOrdersTab() {
		browser.clickGuiObject(".id", new RegularExpression("customerTabs_\\d*", false), ".text",
				new RegularExpression("Orders", false));
	}
	public void clickMergeHistoryTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Merge History", false));
	}
	public void clickPointsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Points|(Pool Status)", false));
	}
	
	public void clickUnlockedPrivilegesTab() {
		browser.clickGuiObject(".id", new RegularExpression("customerTabs_\\d*", false), ".text",
				new RegularExpression("((Unlocked Privileges)|(Awarded Licences))\\s*(\\([0-9]+\\))?", false));
	}
	
	public void clickAddressTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Addresses", false));
	}
	
	public void clickPurchaseAuthorizationTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Purchase Authorization", false));
	}
	
	public void clickMerge(){
		browser.clickGuiObject(".class", "Html.A", ".text","Merge");		
	}

	public void setFirstName(String fName) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.firstName",
				false);
		browser.setTextField(".id", regx, fName);
	}

	public void setLastName(String lName) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false);
		browser.setTextField(".id", regx, lName);
	}

	public void setDateOfBirth(String date) {
//		browser.setTextField(".id", new RegularExpression(
//				".*\\.dateOfBirth_ForDisplay$", false), date);
		setDateAndGetMessage((IText)browser.getTextField(".id", new RegularExpression(".*\\.dateOfBirth_ForDisplay$", false))[0], date);
	}

	public void setHomePhone(String hPhone) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone",
				false);
		browser.setTextField(".id", regx, hPhone);
	}

	public void setFax(String fax) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.fax", false);
		browser.setTextField(".id", regx, fax);
	}

	public void setEmail(String email) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.email", false);
		browser.setTextField(".id", regx, email);
	}

	public void selectPhoneContactPre(String prefer) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.phoneContactPreference",
				false);
		browser.selectDropdownList(".id", regx, prefer);
	}

	public void selectPreferredContractTime(String contractTime) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.preferredContactTime",
				false);
		browser.selectDropdownList(".id", regx, contractTime);
	}

	public void selectEyeColor(String color) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000001\\]", false);
		browser.selectDropdownList(".id", idPattern, color);
	}

	public void selectEthnicity(String ethnicity) {

		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false);
		Property[] p = new Property[] {
				new Property(".id", idPattern) };
		browser.selectDropdownList(p, ethnicity, false);
	}

	public void selectSolicitationIndicator(String indicator) {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5033\\]:BOOLEAN_YESNO",
				false);
		browser.selectDropdownList(".id", regx, indicator);
	}

	public void selectMSResident(boolean isMSResident) {
		String item = isMSResident == true ? "Yes" : "No";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000104\\]:BOOLEAN_YESNO",
				false);
		browser.selectDropdownList(".id", regx, item);
	}

	public void selectCustomerGender(String gender) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		Property[] p = new Property[] { new Property(".id", idPattern), };
		browser.selectDropdownList(p, gender, false);
	}
	
	//Set hunter Eligibility questions
	
	public void selectNoSuspension(boolean isNoSuspension) {
		String item = isNoSuspension == true ? "Yes" : "No";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8541\\]:BOOLEAN_YESNO",
				false);
		//Property[] p = new Property[] { new Property(".id", idPattern), };
		browser.selectDropdownList(".id", regx, item);
	}
	
	public void selectHuntingLicence(boolean isHuntingLicence) {
		String item = isHuntingLicence == true ? "Yes" : "No";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8542\\]:BOOLEAN_YESNO",
				false);
		//Property[] p = new Property[] { new Property(".id", idPattern), };
		browser.selectDropdownList(".id", regx, item);
	}

	public void selectHuntingEducation(boolean isHuntingEducation) {
		String item = isHuntingEducation == true ? "Yes" : "No";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8543\\]:BOOLEAN_YESNO",
				false);
		//Property[] p = new Property[] { new Property(".id", idPattern), };
		browser.selectDropdownList(".id", regx, item);
	}
	
	public void selectNoResident(boolean isNoResident) {
		String item = isNoResident == true ? "Yes" : "No";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8545\\]:BOOLEAN_YESNO",
				false);
		//Property[] p = new Property[] { new Property(".id", idPattern), };
		browser.selectDropdownList(".id", regx, item);
	}
	
	public void selectPriorGoatRegistration(boolean isPriorGoatRegistration) {
		String item = isPriorGoatRegistration == true ? "Yes" : "No";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8545\\]:BOOLEAN_YESNO",
				false);
		//Property[] p = new Property[] { new Property(".id", idPattern), };
		browser.selectDropdownList(".id", regx, item);
	}
	
	
	public void selectAttributeGender(String gender) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000101\\]", false);
		Property[] p = new Property[] { new Property(".id", idPattern),
				new Property(".text", "MF"), };
		browser.selectDropdownList(p, gender, false);
	}

	public void setBusinessPhone(String bPhone) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.businessPhone",
				false);
		browser.setTextField(".id", regx, bPhone);
	}

	public void setMobilePhone(String mPhone) {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.mobilePhone",
				false);
		browser.setTextField(".id", regx, mPhone);
	}

	public void setHeight(String height) {
		RegularExpression idPattern = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000102\\]", false);
		browser.setTextField(".id", idPattern, height);
	}

	public void setMarkings(String markings) {
		browser.setTextArea(".id",
				"AttributeValuesWrapper-\\d+\\.attr\\[210000103\\]", markings);
	}

	public void selectSuffix(String suffix) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix", false);
		browser.selectDropdownList(".id", idPattern, suffix);
	}
	
	public void selectAmericanCitizen(boolean isAmericanCitizen) {
		String item = isAmericanCitizen == true ? "Yes" : "NO";
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[210000303\\]:BOOLEAN_YESNO",
				false);
		browser.selectDropdownList(".id", regx, item);
	}

	public String getCustomerNumber() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.customerNumber", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getCustomerStatus() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.status", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerClass() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.customerClass\\.name", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public String getCreateApplication(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.creationInfo\\.ApplicationName", false);
		return browser.getTextFieldValue(".id",regx);
	}
	
	public String getCreateDate(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.creationInfo\\.date", false);
		return browser.getTextFieldValue(".id",regx);
	}
	
	public String getCreateUser(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.creationInfo\\.userName", false);
		return browser.getTextFieldValue(".id",regx);
	}

	public String getFirstName() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact.firstName", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public String getMiddleName() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.middleName", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public void setMiddleName(String nName){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.middleName", false);
		browser.setTextField(".id", regx, nName);
	}

	public String getLastName() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public String getSuffix(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getDateOfBirth() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false);
		return browser.getTextFieldValue(".id", regx);
	}
	
	public boolean getOverrideReqIdentifier(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		return browser.isCheckBoxSelected(".id", regx);
	}
	
	public String getOverrideReason(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideReason", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getHomePhone() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getBusinessPhome() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.businessPhone",
				false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getMobilePhone() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.mobilePhone",
				false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getFax() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.fax", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getEmail() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.email", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getPhoneContactPre() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.phoneContactPreference",
				false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getPreferredContactTime() {
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.primaryContact\\.preferredContactTime",
				false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerGender() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	//Update for those hunter eligibility questions
	public String getNoSuspension() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8541\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public String getHuntingLicence() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8542\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public String getHuntingEducation() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8543\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public String getNoResident() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8545\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public String getPriorGoatRegistration() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[8549\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerEthnicity() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5032\\]", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getCustomerSolicitationInd() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5033\\]:BOOLEAN_YESNO",
				false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public String getCustomerLifetimeSlogan() {
		RegularExpression regx = new RegularExpression(
				"AttributeValuesWrapper-\\d+\\.attr\\[5034\\]",
				false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getAttributeMSResident() {
		RegularExpression regx = new RegularExpression(
				".*\\.attr\\[210000104\\].*", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getAttributeEyeColor() {
		RegularExpression regx = new RegularExpression(
				"*.attr\\[210000001\\]$", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getAttributeGender() {
		RegularExpression regx = new RegularExpression(
				".*\\.aattr\\[210000101\\]$", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getAttributeHight() {
		RegularExpression regx = new RegularExpression(
				".*\\.attr\\[210000102\\]$", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}

	public String getAttributeMarkings() {
		RegularExpression regx = new RegularExpression(
				".*\\.attr\\[210000103\\]$", false);
		return browser.getTextFieldValue(".id", regx);
	}

	public String getNewAttributeAmericaCitizen() {
		RegularExpression regx = new RegularExpression(
				".*\\.attr\\[210000303\\].*", false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	public void clickValidate(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate",index);
	}
	
	public void setCustomerProfile(Customer customer){
		this.selectCustomerStatus(customer.status);
		this.setFirstName(customer.fName);
		this.setMiddleName(customer.mName);
		this.setLastName(customer.lName);
		this.selectSuffix(customer.suffix);
		this.setDateOfBirth(customer.dateOfBirth);
		this.selectNoSuspension(customer.isNoSuspension);
		//this.selectMSResident(customer.isNoResident);
		if(customer.overrideReqId){
			this.selectOverrideRequiredIdentifiers();
			this.setOverrideReason(customer.overideReason);
		}else{
			this.unselectOverrideRequiredIdentifiers();
		}
		ajax.waitLoading();
		if(StringUtil.isEmpty(customer.hPhone) && StringUtil.isEmpty(customer.bPhone) && StringUtil.isEmpty(customer.mPhone)) {
			customer.hPhone="9052866600";
		}
		this.setHomePhone(customer.hPhone);
		this.setBusinessPhone(customer.bPhone);
		this.setMobilePhone(customer.mPhone);
		if(!customer.phoneContact.equalsIgnoreCase("No preference")){
			this.selectPhoneContactPre(customer.phoneContact);
		}
		this.setFax(customer.fax);
		this.setEmail(customer.email);
		this.selectPhoneContactPre(customer.phoneContact);
		this.selectPreferredContractTime(customer.contactTime);
		this.selectCustomerGender(customer.custGender);
		this.selectEthnicity(customer.ethnicity);
		this.selectSolicitationIndicator(customer.solicitationIndcator);
	}

	public Customer getCustomerProfile(Customer cust) {
		cust.custProfileStatus = this.getCustomerStatus();
		cust.customerClass = this.getCustomerClass();
		cust.fName = this.getFirstName();
		cust.lName = this.getLastName();
		cust.dateOfBirth = this.getDateOfBirth();
		cust.hPhone = this.getHomePhone();
		cust.bPhone = this.getBusinessPhome();
		cust.mPhone = this.getMobilePhone();
		cust.fax = this.getFax();
		cust.email = this.getEmail();
		cust.phoneContact = this.getPhoneContactPre();
		cust.contactTime = this.getPreferredContactTime();
		cust.custGender = this.getCustomerGender();
		cust.ethnicity = this.getCustomerEthnicity();
		cust.solicitationIndcator = this.getCustomerSolicitationInd();
		cust.isMSResident = this.getAttributeMSResident().equals("No") ? false
				: true;
		cust.eyeColor = this.getAttributeEyeColor();
		cust.attributeGender = this.getAttributeGender();
		cust.height = this.getAttributeHight();
		cust.markings = this.getAttributeMarkings();
		cust.isAmerican = this.getNewAttributeAmericaCitizen().equals("No") ? false
				: true;
		return cust;
	}

	public void selectOverrideIdentifier() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		browser.selectCheckBox(".id", idPattern);
	}

	public void setOverrideReason(String reason) {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideReason", false);
		browser.setTextField(".id", idPattern, reason);
	}
	
	public void clickPrintCustomerRecord() {
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Print Customer Record");
	}
	
	public void clickChangeHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text",
		"Change History");
	}
	
	public void clickPurchasePrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Purchase (Privilege|Licence)", false));
	}
	
	public void clickActivityRegistration(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Actions.*",false));
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Activity Registration", false), true, 0,  objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectCustomerStatus(String status){
		browser.selectDropdownList(".id", this.custStatusIdPattern, status);
	}
	
	public void editCustomerStatus(String status){
		this.clickEdit();
		ajax.waitLoading();
		this.selectCustomerStatus(status);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Get specific property value
	 * @param idPattern
	 * @param propertyName
	 * @return
	 */
	public String getPropertyValue(RegularExpression idPattern, String propertyName){
		String propertyValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", idPattern);
		if(objs.length>0){
			propertyValue = objs[0].getProperty(propertyName);
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return propertyValue;
	}
	
	/**
	 * Get warning message
	 * @return warning message
	 */
	public String getWarnMsg() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warnMes = objs[0].getProperty(".text").trim();
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warnMes;
	}
	
	public boolean checkWarnMes(){
		return browser.checkHtmlObjectExists(".className", new RegularExpression("(message|message msgerror)",false),
				".id", "NOTSET");
	}
	
	public void verifyWarnMesText(String expectedText){
		String actualWarnMes = this.getWarnMsg();
        if(!actualWarnMes.equals(expectedText)){
        	throw new ErrorOnPageException("The expected warning message is:"+expectedText+
        			", but the actual one is:"+actualWarnMes);
        }		
	}
	
	public void verifyWarnMesExsit(boolean expectedExist){
		boolean actualExist = this.checkWarnMes();
		if(actualExist!=expectedExist){
			throw new ErrorOnPageException("The warning message should "+(expectedExist?"":"not ")+"be existed.");
		}
	}
	
	/**
	 * Check if the status is enabled or not
	 * @return
	 */
	public boolean isStatusEnabled() {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.status", false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find Status object.");
		}
		boolean isEnabled = !Boolean.parseBoolean(objs[0].getProperty("isDisabled").trim());
		
		Browser.unregister(objs);
		return isEnabled;
	}
	
	/**
	 * Change date of birth
	 * @param dateOfBirth: Format is 'MMM dd yyyy' Dec 30 1966
	 * 	 
	 */
	public void changeDateOfBirth(String dateOfBirth){
		if(!this.getDateOfBirth().equals(dateOfBirth)){
			this.clickEdit();
			ajax.waitLoading();
			this.setDateOfBirth(dateOfBirth);
			this.clickApply();
			ajax.waitLoading();
		}
	}
	
	public String getStatus(){
		RegularExpression regx = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.status", false);
		
		return browser.getDropdownListValue(".id", regx, 0);		
	}
	
	public void selectOverrideRequiredIdentifiers() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		browser.selectCheckBox(".id", idPattern);
		ajax.waitLoading();
	}

	public void unselectOverrideRequiredIdentifiers() {
		RegularExpression idPattern = new RegularExpression(
				"(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false);
		browser.unSelectCheckBox(".id", idPattern);
	}
	
	public void clickEdit() {
		browser.clickGuiObject(".class","Html.A",".text","Edit");
	}
	
	public boolean isEditExists(){
		return browser.checkHtmlObjectDisplayed(".class","Html.A",".text","Edit");
	}
	
	/**
	 * detect if the apply button is enabled
	 * @return
	 */
	public boolean isApplyEnabled() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text","Apply");
		boolean enabled=false;
		if(objs.length>0) {
			enabled= objs[0].isEnabled();
		} 
		
		Browser.unregister(objs);
		
		return enabled;
	}
	
	public boolean isMergeEnabled() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text","Merge");
		boolean enabled=false;
		if(objs.length>0) {
			enabled= objs[0].isEnabled();
		} 
		
		Browser.unregister(objs);
		
		return enabled;
	}
	
	/**
	 * Verify the customer profile.
	 * @param expectedCust - the customer detail info.
	 * 	 
	 */
	public boolean compareCustInfoSuccess(Customer expectedCust){
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Customer First name", expectedCust.fName, getFirstName());
		pass &= MiscFunctions.compareResult("Customer Last Name", expectedCust.lName, getLastName());
		pass &= MiscFunctions.compareResult("Customer Date of Birth", expectedCust.dateOfBirth, getDateOfBirth());
		pass &= MiscFunctions.compareResult("Customer Home Phone", expectedCust.hPhone, getHomePhone());
		pass &= MiscFunctions.compareResult("Customer Business Phone", expectedCust.bPhone, getBusinessPhome());
		pass &= MiscFunctions.compareResult("Customer Mobile Phone", expectedCust.mPhone, getMobilePhone());
		pass &= MiscFunctions.compareResult("Customer Gendar", expectedCust.custGender, getCustomerGender());
		pass &= MiscFunctions.compareResult("Customer Solicitation Indicator", expectedCust.solicitationIndcator, getCustomerSolicitationInd());
		pass &= MiscFunctions.compareResult("Customer Ethnicith", expectedCust.ethnicity, getCustomerEthnicity());
		pass &= MiscFunctions.compareResult("Customer Suffix", expectedCust.suffix, getSuffix());
		pass &= MiscFunctions.compareResult("Customer Override Required Identifiers", expectedCust.overrideReqId, getOverrideReqIdentifier());
		pass &= MiscFunctions.compareResult("Customer Override Reason", expectedCust.overideReason, getOverrideReason());
		pass &= MiscFunctions.compareResult("Customer Fax", expectedCust.fax, getFax());
		pass &= MiscFunctions.compareResult("Customer Email", expectedCust.email, getEmail());
		pass &= MiscFunctions.compareResult("Customer Status", expectedCust.status, getStatus());

		return pass;	
	}
	
	public boolean isOverrideRequiredIdentifiersSelected(){
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.overrideRequiredIdentifer", false));
		boolean selected = ((ICheckBox)objs[0]).isSelected();
		Browser.unregister(objs);
		return selected;
	}
	
	/**
	 * This method was used to edit customer profile to setup OverrideRequiredIdentifier as selected/un-selected
	 * @param selected
	 */
	public void setupOverrideRequiredIdentifier(boolean selected){
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage.getInstance();
		boolean curSelected = custDetailPg.isOverrideRequiredIdentifiersSelected();
		if(selected && !curSelected){
			logger.info("Edit customer profile to Select OverrideRequiredIdentifier.");
			custDetailPg.clickEdit();//if edit button not existed, feature ModifyWildlifeCustomerProfile was not assigned to role 'HF HQ Role'
			ajax.waitLoading();
			custDetailPg.waitLoading();
			
			custDetailPg.selectOverrideIdentifier();
			ajax.waitLoading();
			custDetailPg.waitLoading();
			custDetailPg.setOverrideReason("Automation test");
			
			custDetailPg.clickApply();
			ajax.waitLoading();
			custDetailPg.waitLoading();
			
		}else if(!selected && curSelected){
			logger.info("Edit customer profile to Un-Select OverrideRequiredIdentifier.");
			custDetailPg.clickEdit();//if edit button not existed, feature ModifyWildlifeCustomerProfile was not assigned to role 'HF HQ Role'
			ajax.waitLoading();
			custDetailPg.waitLoading();
			
			custDetailPg.unselectOverrideRequiredIdentifiers();
			ajax.waitLoading();
			custDetailPg.waitLoading();
			
			custDetailPg.clickApply();
			ajax.waitLoading();
			custDetailPg.waitLoading();
		}
		
		logger.info("OverrideRequiredIdentifier was setup for customer profile.");
	}
	
	public boolean getObjectEnabledProp(Object objID) {
		IHtmlObject[] objs=browser.getHtmlObject(".id", objID);
		if(objs.length<1)
			throw new ItemNotFoundException("Could not get any object by id "+objID);
		
		boolean enabled=objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	public void verifyCustProfileNotEditable() {
		logger.info("Verify customer profile were read-only for lastname, firstname, suffix, middilename, phone, dob, status, address, gender");
		boolean enabled=false;
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.firstName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.middleName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("AddressView-\\d+\\.address", false));
		
		if(enabled)
			throw new ErrorOnPageException("Failed to verify customer profile were read-only.");
		logger.info("---Verify customer profile were read-only successfully.");
	}
	
	public void verifyCustProfileNotEditableExpectAddress() {
		logger.info("Verify customer profile were read-only for lastname, firstname, suffix, middilename, phone, dob, status, gender");
		boolean enabled=false;
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.firstName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.middleName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.suffix", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.lastName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.primaryContact\\.homePhone", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5031\\]", false));
		
		boolean addrEnable = this.getObjectEnabledProp(new RegularExpression("AddressView-\\d+\\.address", false));
		if(!enabled && addrEnable)
			logger.info("---Verify customer profile were read-only expect address successfully.");
		else
			throw new ErrorOnPageException("Failed to verify customer profile were read-only expect address.");
	}
	
	public void verifyCustAddressEditable() {
		boolean enabled= this.getObjectEnabledProp(new RegularExpression("AddressView-\\d+\\.address", false));
		if(!enabled)
			throw new ErrorOnPageException("Customer address should be editable.");
		logger.info("---Verify customer address editable successfully.");
	}
	
	/**
	 * Verify Edit button existed or not
	 * @param existed
	 */
	public void verifyEditButtonExisted(boolean existed) {
		Boolean existedUI=browser.checkHtmlObjectDisplayed(".class", "Html.DIV", ".text", new RegularExpression("Edit", false));
		if(existed){
			if(!existedUI)
				throw new ItemNotFoundException("Could not find Edit button by DIV text.");
			else
				logger.info("---Verify edit button existed successfully.");
		}else{
			if(!existedUI)
				logger.info("---Verify edit button not existed successfully.");
			else
				throw new ItemNotFoundException("Edit button should not existed on page.");
		}
	}
	
	public void verifyEditButtonEnabled(boolean enabled) {
		boolean htmlAExisted=browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Edit", false));
		boolean htmlSpanExisted=browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("Edit", false));
//		if((htmlAExisted && htmlSpanExisted) || (!htmlAExisted && !htmlSpanExisted))
//			throw new ErrorOnPageException("You need to modify this method to verify Edit button is enabled or not.");
		
		boolean enabledUI=false;
		if(htmlAExisted)
			enabledUI=true;
		else if(htmlSpanExisted)
			enabledUI=false;
		
		if(enabled){
			if(!enabledUI)
				throw new ErrorOnPageException("Edit button should be enabled.");
			else
				logger.info("---Verify edit button enabled successfully.");
		}else{
			if(!enabledUI)
				logger.info("---Verify edit button not enabled successfully.");
			else
				throw new ErrorOnPageException("Edit button should not enabled on page.");
		}
	}
	
	public void editCustInfoByClickEditBtn() {
		logger.info("Click Edit button on customer details page.");
		this.clickEdit();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public boolean isMemberShipSectionExisted() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.TR", ".text", new RegularExpression("^Membership.*", false));
	}
	
	public void verifyMemberShipSectionExisted(boolean shouldExisted) {
		boolean existedUI=isMemberShipSectionExisted();
		if(shouldExisted && !existedUI)
			throw new ErrorOnPageException("Membership section should be existed on customer details page.");
		else if(!shouldExisted && existedUI)
			throw new ErrorOnPageException("Membership section should not existed on customer details page.");
		logger.info("Verify member ship section as "+shouldExisted+" successfully.");
	}
	
	public void verifyCustMembershipNotEditable() {
		logger.info("Verify customer membership were read-only.");
		boolean enabled=false;
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.webMember", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.loginName", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.membershipStatus", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.signUpDate", false));
		enabled &= this.getObjectEnabledProp(new RegularExpression("(HF)?CustomerProfileView-\\d+\\.signUpChannel", false));
		
		if(enabled)
			throw new ErrorOnPageException("Failed to verify customer membership were read-only.");
		logger.info("---Verify customer membership were read-only successfully.");
	}
	
	public boolean verifyDateOfBirthTextFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(Property.toPropertyArray(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.dateOfBirth_ForDisplay", false)))[0], invalidDates);
	}
	
	public void selectResidencyOverride(String value) {
		browser.selectDropdownList(".id", new RegularExpression("(HF)?CustomerProfileView-\\d+\\.overrideResidencyType", false), value);
	}
	
	public void editCustResidOverride(String value) {
		this.clickEdit();
		ajax.waitLoading();
		this.selectResidencyOverride(value);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/** Edit customer email address */
	public void editCustEmailAddress(String value) {
		if(!value.equals(this.getEmail())){
			this.editCustInfoByClickEditBtn();
			this.setEmail(value);
			this.clickApply();
			ajax.waitLoading();
		}
	}
	public void clickPropertyTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Property( \\(\\d+\\))?", false));
	}
	
}
