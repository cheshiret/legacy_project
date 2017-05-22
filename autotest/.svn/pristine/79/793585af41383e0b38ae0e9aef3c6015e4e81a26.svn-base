package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * ScriptName: LicMgrIdentifyCustomerPage
 * Description:
 * @date: Jan 13, 2011
 * @author qchen
 *
 */
public class LicMgrIdentifyCustomerPage extends LicMgrCommonTopMenuPage{
	private static LicMgrIdentifyCustomerPage _instance = null;
	
	protected LicMgrIdentifyCustomerPage() {
		
	}
	
	public static LicMgrIdentifyCustomerPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrIdentifyCustomerPage();
		}
		
		return _instance;
	}
	

	private String residencyStatusId1 = "IdentifyCustomerPage-\\d+\\.selectedResidencyStatus";
	private String residencyStatusId2 = "CustomerResidencyByAddressUI-\\d+\\.option";
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", new RegularExpression("identifier_status(|WildLifePrivilegesUI)", false));
	}
	
	/**
	 * Private method to check the customer class radio button
	 * @param index
	 */
	private void checkCustomerClassRadioButton(int index) {
		if(index < 0) {
			index = 0;
		}
		browser.selectRadioButton(".id", new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false), index);
	}
	
	/**
	 * Check the individual customer class radio button
	 */
	public void checkIndividualCustomerClass() {
		this.checkCustomerClassRadioButton(0);
	}
	
	/**
	 * Check the commercial customer class radio button
	 */
	public void checkCommercialCustomerClass() {
		this.checkCustomerClassRadioButton(1);
	}
	
	/**
	 * Check the trapper customer class radio button
	 */
	public void checkTrapperCustomerClass() {
		this.checkCustomerClassRadioButton(2);
	}
	
	/**
	 * Set the date of birth text field
	 * @param birthDate
	 */
	public void setDateOfBirth(String birthDate) {
		RegularExpression idPattern=new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay", false);
		browser.setTextField(Property.toPropertyArray(".id", idPattern), birthDate, true, 0, (IHtmlObject)null, IText.Event.LOSEFOCUS);
	}
	
	/**
	 * Select the identifier type
	 * @param type
	 */
	public void selectIdentifierType(String type) {
		//browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierType", false), type, true);
		browser.selectDropdownList(".id", new RegularExpression("(IdentifyCustomerSearchCriteria-\\d+\\.identifierType|IdentifyCustomerPage-\\d+\\.selectedIdentifierType)", false), type, true);
	}
	
	public boolean isIdentifyCustomerSectionExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierNumber", false));
	}
	
	/**
	 * Set the identifier number
	 * @param identifierNum
	 */
	public void setIdentifierNumber(String identifierNum) {
		browser.setTextField(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierNumber", false), identifierNum, true);
	}
	
	/**
	 * Select State/Province
	 * @param state
	 */
	public void selectStatsProvince(String state) {
		browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.state", false), state, true);
	}
	
	/**
	 * Select the customer's country
	 * @param country
	 */
	public void selectCustomerIdentifierCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.country", false), country, true);
	}
	
	/**
	 * Select the customer identifier state
	 * @param state
	 */
	public void selectCustomerIdentifierState(String state) {
		browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.state", false), state, true);
	}
	
	/**
	 * Check whether the resident status radio button exists
	 * @return
	 */
	public boolean isResidentStatusRadioButtonExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(residencyStatusId1+"|"+residencyStatusId2, false));
	}
	
	public boolean isResidentStatusRadioButtonEnabled() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(residencyStatusId1+"|"+residencyStatusId2, false));
		boolean enabled = objs[0].isEnabled();
		
		Browser.unregister(objs);
		return enabled;
	}
	
	/**
	 * Check Resident residency status radio button
	 */
	public void checkResidentStatus() {
		if(isResidentStatusRadioButtonExists()) {
			browser.selectRadioButton(".id", new RegularExpression("IdentifyCustomerPage-\\d+\\.selectedResidencyStatus", false), 0);
		}
	}
	
	/**
	 * Check Non Resident residency status radio button
	 */
	public void checkNonResidentStatus() {
		if(isResidentStatusRadioButtonExists()) {
			browser.selectRadioButton(".id", new RegularExpression("IdentifyCustomerPage-\\d+\\.selectedResidencyStatus", false), 1);
		}
	}
	
	public void selectResidencyStatus(String status){
		if(isResidentStatusRadioButtonExists()){
			if(isResidentStatusRadioButtonExists() && isResidentStatusRadioButtonEnabled()) {

				IHtmlObject[] objs = browser.getHtmlObject(".class","Html.LABEL",".for", new RegularExpression(residencyStatusId1+"|"+residencyStatusId2, false));

				int index=-1;
				for(int i=0;i<objs.length; i++){
					if(objs[i].text().equalsIgnoreCase(status)){
						index = i;
						break;
					}
				}

				Browser.unregister(objs);
				if(index>-1){
					browser.selectRadioButton(".id", new RegularExpression(residencyStatusId1+"|"+residencyStatusId2, false), index);
				}else{
					throw new ItemNotFoundException("Did not found resident status = " + status);
				}
			}
		}
	}
	
	public boolean isIdenTypeExists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.identifierValue", false));
	}
	
	public void setIdenNum(String idenType){
		browser.setTextField(".id", new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.identifierValue", false), idenType);
	}
	
	public boolean isStateExists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.state", false));
	}
	public void selectState(String state){
		browser.selectDropdownList(".id", new RegularExpression("CustomerResidencyByAddressUI-\\d+\\.state", false), state);
	}
	
	public void setResidencyStatusRelatedIden(CustomerIdentifier iden){
		if(isIdenTypeExists()){
			setIdenNum(iden.identifierNum);
		}
		if(isStateExists()){
			selectState(iden.state);
		}
	}
	
//	public void selectResidencyStatus(String status){
//		if(status.equalsIgnoreCase("Resident")) {
//			checkResidentStatus();
//		} else {
//			checkNonResidentStatus();
//		}
//	}
	
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
	
	public boolean isAdditionalProofOfResidencyExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("IdentifyCustomerPage-\\d+\\.additionalProof", false));
	}
	
	/**
	 * check country whether exists
	 * @return
	 */
	public boolean isIdentifierCountryExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.country", false));
	}
	
	/**
	 * Check identifier state exists or not
	 * @return
	 */
	public boolean isIdentifierStateExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.state", false));
	}
	
	public void identifyCustomer(Customer cust){
		this.identifyCustomer(cust.customerClass, cust.dateOfBirth, cust.identifier.identifierType, cust.identifier.identifierNum, cust.residencyStatus, cust.identifier.country, cust.identifier.state);
		//selectAdditionalProofOfResidency(cust.additionalProofOfResidency);
	}
	
	/**
	 * Set some customer detail information to identify a customer
	 * @param customerClass
	 * @param dateOfBirth
	 * @param identifierType
	 * @param identifierNum
	 * @param residencyStatus
	 */
	public void identifyCustomer(String customerClass, String dateOfBirth, String identifierType, String identifierNum, String residencyStatus, String identifierCountry, String identifierState) {
		if(customerClass.equalsIgnoreCase("Individual")) {
			checkIndividualCustomerClass();
		} else if(customerClass.equalsIgnoreCase("Business")) {
			checkCommercialCustomerClass();
		} else if(customerClass.equalsIgnoreCase("Trapper")) {
			checkTrapperCustomerClass();
		} else {
			throw new ItemNotFoundException("Cann't find the Customer Class type - " + customerClass);
		}
		ajax.waitLoading();
		
		if(dateOfBirth.length() > 0) {
			setDateOfBirth(dateOfBirth);
		}
		
		if(identifierType.length() > 0 ) {
			selectIdentifierType(identifierType);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		setIdentifierNumber(identifierNum);
		
		if( this.isIdentifierCountryExists() && identifierCountry.length() > 0 ) {
			selectCustomerIdentifierCountry(identifierCountry);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		if(this.isIdentifierStateExists() && identifierState.length() > 0) {
			selectCustomerIdentifierState(identifierState);
		}
		
		if(isResidentStatusRadioButtonExists()) {
			if(residencyStatus.equalsIgnoreCase("Resident")) {
				checkResidentStatus();
			} else {
				checkNonResidentStatus();
			}
			ajax.waitLoading();
		}
	}
	
	public void selectAdditionalProofOfResidency(String proof){
		if(this.getIdentifyType().equalsIgnoreCase("Social Security Number")){
			browser.selectDropdownList(".id", new RegularExpression("IdentifyCustomerPage-\\d+\\.additionalProof",false), proof);
		}
	}
	
	public String getIdentifyType(){
		return browser.getDropdownListValue(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.identifierType", false), 0);
	}

	/**
	 * Get the error message on the top of this page
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find error message object on the top of page.");
		}
		String toReturn = objs[0].getProperty(".text").trim();
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Verify if the Date of Birth input value is valid
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyDateOfBirthTextFieldValid(String invalidDates[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("IdentifyCustomerSearchCriteria-\\d+\\.dateOfBirth_ForDisplay", false))[0], invalidDates);
	}
	
	/**
	 * Get the list of customer class
	 * @return
	 */
	public List<String> getCustClass(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "TD", ".text", new RegularExpression("^Customer Class.*", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find customer class DIV.");
		}
		String content = objs[0].text().replace("Customer Class", "").replace("*", "").trim();
		List<String> custClass = new ArrayList<String>(Arrays.asList(content.split("\\s+")));
		Browser.unregister(objs);
		return custClass;
	}
}
