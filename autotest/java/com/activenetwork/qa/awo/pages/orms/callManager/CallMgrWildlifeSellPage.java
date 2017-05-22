package com.activenetwork.qa.awo.pages.orms.callManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Feb 28, 2012
 */
public class CallMgrWildlifeSellPage extends CallMgrCommonTopMenuPage {

	private static String prefix = "WildLifeSearchCriteria-";
	
	static private CallMgrWildlifeSellPage _instance = null;
	
	static public CallMgrWildlifeSellPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrWildlifeSellPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Wildlife Sale");
	}
	
	public void setCustomerInfoForPrivilegeAndConsuamble(Customer cust){
		if(null != cust.customerClass && cust.customerClass.length()>0){
			selectCustomerClassForPrivilegeAndConsuamble(cust.customerClass);
			ajax.waitLoading();
		}
		
		if(null != cust.dateOfBirth && cust.dateOfBirth.length()>0){
			setDateOfBirthForPrivilegeAndConsuamble(cust.dateOfBirth);
		}
		
		if(null != cust.identifier){
			if(null != cust.identifier.identifierType && cust.identifier.identifierType.length()>0){
				selectIdentifierTypeForPrivilegeAndConsuamble(cust.identifier.identifierType);
				ajax.waitLoading();
				this.waitLoading();
			}
			
			if(null != cust.identifier.identifierNum && cust.identifier.identifierNum.length()>0){
				setIdentifierNumForPrivilegeAndConsuamble(cust.identifier.identifierNum);
			}
			
			if(null != cust.identifier.country && cust.identifier.country.length() > 0 && isIdentifierCountryForPrivilegeExists()) {
				selectIdentifierCountryForPrivilegeAndConsuamble(cust.identifier.country);
				ajax.waitLoading();
				this.waitLoading();
			}
			
			if(null != cust.identifier.state && cust.identifier.state.length() > 0 && isIdentifierStateForPrivilegeExists()) {
				selectIdentifierStateForPrivilegeAndConsuamble(cust.identifier.state);
			}
		}
		
		if(this.isResidencyStatusExists()) {
			if(null != cust.residencyStatus && cust.residencyStatus.length()>0){
				selectResidencyStatus(cust.residencyStatus);
				ajax.waitLoading();
			}
		}
		
	}
	
	public void selectCustomerClassForPrivilegeAndConsuamble(String type){
		int classType=-1;
		
		if(type.equalsIgnoreCase("Individual")) {
			classType=0;
		} else if(type.equalsIgnoreCase("Business")) {
			classType=1;
		} else {
			throw new ItemNotFoundException("Unknown class type: "+type);
		}
		
		RegularExpression idPattern=new RegularExpression(prefix+"\\d+\\.customerClass",false);
		browser.selectRadioButton(".id",idPattern,classType);
	}
	
	public void setDateOfBirthForPrivilegeAndConsuamble(String birthday){
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.dateOfBirth_ForDisplay",false), birthday);
	}
	
	public void selectIdentifierTypeForPrivilegeAndConsuamble(String type){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.identifierType",false), type);
	}
	
	public void setIdentifierNumForPrivilegeAndConsuamble(String num){
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.identifierNumber",false), num);
	}

	public void selectIdentifierCountryForPrivilegeAndConsuamble(String country){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.country",false), country);
	}
	
	/**
	 * Select the customer identifier state
	 * @param state
	 */
	public void selectIdentifierStateForPrivilegeAndConsuamble(String state) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "\\d+\\.state", false), state, true);
	}
	
	/**
	 * check country whether exists
	 * @return
	 */
	public boolean isIdentifierCountryForPrivilegeExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "\\d+\\.country", false));
	}
	
	/**
	 * Check identifier state exists or not
	 * @return
	 */
	public boolean isIdentifierStateForPrivilegeExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "\\d+\\.state", false));
	}
	
	public boolean isResidencyStatusExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix+"\\d+\\.selectedResidencyStatus",false));
	}
	
	public void selectResidencyStatus(String residency){
		int index=-1;
		
		if(residency.equalsIgnoreCase("Resident")) {
			index=0;
		} else if(residency.equalsIgnoreCase("Non Resident")) {
			index=1;
		} else {
			throw new ItemNotFoundException("Unknown resident type: "+residency);
		}
		
		RegularExpression idPattern=new RegularExpression(prefix+"\\d+\\.selectedResidencyStatus",false);
		browser.selectRadioButton(".id",idPattern,index);
	}
	
	public void clickPurchasePrivilege(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Purchase (Privilege|Licence)", false));
		
	}
	public void clickPurchaseConsumable(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Purchase Consumables");
		
	}
	
	public void clickRequestInspection(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Inspection");
		
	}
	
	public void selectVehicleType(String type){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.vehicleTypeRegistration",false), type);
	}
	
	public List<String> getVehicleTypesElements(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix+"\\d+\\.vehicleTypeRegistration",false));
	}
	
	public String getVehicleType(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"\\d+\\.vehicleTypeRegistration",false));
	}
	
	public void setVehicleNum(String num){
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.vehicleNum",false), num);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
		
	}
	
	public void setRegistrationVehicleInfo(String vehicleType, String minNum){
		if(null != vehicleType){
			selectVehicleType(vehicleType);
		}
		
		if(null != minNum){
			setVehicleNum(minNum);
		}
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
	
	public void selectCustomerClassForVehicle(String type){
		int classType=-1;
		
		if(type.equalsIgnoreCase("Individual")) {
			classType=0;
		} else if(type.equalsIgnoreCase("Business")) {
			classType=1;
		} else {
			throw new ItemNotFoundException("Unknown class type: "+type);
		}
		
		RegularExpression idPattern=new RegularExpression(prefix+"\\d+\\.customerClassVehicle",false);
		browser.selectRadioButton(".id",idPattern,classType);
	}
	
	public void setDateOfBirthForVehicle(String birthday){
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.dateOfBirthVehicle_ForDisplay",false), birthday);
	}
	
	public String getDateOfBirthForVehicle(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.dateOfBirthVehicle_ForDisplay",false));
	}
	
	public void selectIdentifierTypeForVehicle(String type){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.identifierTypeVehicle",false), type);
	}
	
	public String getIdentifierTypeForVehicle(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"\\d+\\.identifierTypeVehicle",false));
	}
	
	public void setIdentifierNumForVehicle(String num){
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.identifierNumberVehicle",false), num);
	}
	
	public String getIdentifierNumForVehicle(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.identifierNumberVehicle",false));
	}

	public void selectIdentifierCountryForVehicle(String country){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.countryVehicle",false), country);
	}
	
	public void selectIdentifierStateForVehicle(String state){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.stateVehicle",false), state);
	}
	
	public boolean isIdentifierCountryForVehicleExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "\\d+\\.countryVehicle", false));
	}
	
	public boolean isIdentifierStateForVehicleExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "\\d+\\.stateVehicle", false));
	}
	
	public void setCustomerInfoForVehicle(Customer cust){
		if(null != cust.customerClass && cust.customerClass.length()>0){
			selectCustomerClassForVehicle(cust.customerClass);
			ajax.waitLoading();
		}
		
		if(null != cust.dateOfBirth && cust.dateOfBirth.length()>0){
			setDateOfBirthForVehicle(cust.dateOfBirth);
		}
		
		if(null != cust.identifier){
			if(null != cust.identifier.identifierType && cust.identifier.identifierType.length()>0){
				selectIdentifierTypeForVehicle(cust.identifier.identifierType);
				ajax.waitLoading();
			}
			
			if(null != cust.identifier.identifierNum && cust.identifier.identifierNum.length()>0){
				setIdentifierNumForVehicle(cust.identifier.identifierNum);
			}
			
			if(null != cust.identifier.country && cust.identifier.country.length() > 0 && isIdentifierCountryForVehicleExists()) {
				selectIdentifierCountryForVehicle(cust.identifier.country);
			}
			
			if(null != cust.identifier.state && cust.identifier.state.length() > 0 && isIdentifierStateForVehicleExists()) {
				selectIdentifierStateForVehicle(cust.identifier.state);
			}
		}
	}
	
	public void clickClearForInspection(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Request Inspection.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Request Inspection Clear button object.");
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "Clear", true, 0, objs[0]);
		Browser.unregister(objs);
	}
}
