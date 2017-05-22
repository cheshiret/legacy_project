package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.OfficerInfo;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Officer MGT --- > Officers --<Button>-->Add Officer
 * @author pchen
 * @Date November 30, 2012
 */
public class LicMgrOfficersAddPage extends LicMgrOfficerMGTCommonPage{
	private static LicMgrOfficersAddPage _instance = null;
	
	protected LicMgrOfficersAddPage (){}
	
	public static LicMgrOfficersAddPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrOfficersAddPage();
		}
		
		return _instance;
	}
	
	private String prefix = "OfficerDetailView-\\d+\\.";
	
	private String addressPre = "AddressView-\\d+\\.";
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","add_officer_formbar");
	}
	
	public void setFirstName(String fName){
		browser.setTextField(".id", new RegularExpression(prefix+"firstName", false), fName);
	}
	
	public void setMiddleName(String mName){
		browser.setTextField(".id", new RegularExpression(prefix+"middleName", false), mName);
	}
	
	public void setLastName(String lName){
		browser.setTextField(".id", new RegularExpression(prefix+"lastName", false), lName);
	}
	
	public void setDateOfBirth(String birthDay){
		browser.setTextField(".id", new RegularExpression(prefix+"dob", false), birthDay);
	}
	
	public void setAddress(String address){
		browser.setTextField(".id", new RegularExpression(addressPre+"address", false), address);
	}
	
	public void setZipPostal(String zipCode){
		browser.setTextField(".id", new RegularExpression(addressPre+"zipCode", false), zipCode);
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression(addressPre+"country", false), country);
	}
	
	public void setSupplementalAddress(String suppleAddress){
		browser.setTextField(".id", new RegularExpression(addressPre + "supplemental", false), suppleAddress);
	}
	
	public void setCity(String city){
		browser.setTextField(".id", new RegularExpression(addressPre + "city", false), city);
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", new RegularExpression(addressPre+"state", false), state);
	}
	
	public void selectCounty(String county){
		browser.selectDropdownList(".id", new RegularExpression(addressPre+"county", false), county);
	}
	
	public void setPhone(String phone){
		browser.setTextField(".id", new RegularExpression(prefix + "phone", false), phone);
	}
	
	public void setEmail(String email){
		browser.setTextField(".id", new RegularExpression(prefix + "email", false), email);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	/**
	 * Set up officer information into the page
	 * @param officer
	 */
	public void setOfficerInfo(OfficerInfo officer){
		if(StringUtil.notEmpty(officer.firstName)){
			this.setFirstName(officer.firstName);
		}
		if(StringUtil.notEmpty(officer.middleName)){
			this.setMiddleName(officer.middleName);
		}
		if(StringUtil.notEmpty(officer.lastName)){
			this.setLastName(officer.lastName);
		}
		if(StringUtil.notEmpty(officer.birthday)){
			this.setDateOfBirth(officer.birthday);
		}
		if(StringUtil.notEmpty(officer.address)){
			this.setAddress(officer.address);
		}
		if(StringUtil.notEmpty(officer.zipCode)){
			this.setZipPostal(officer.zipCode);
		}
		if(StringUtil.notEmpty(officer.country)){
			this.selectCountry(officer.country);
		}
		if(StringUtil.notEmpty(officer.supplementalAddress)){
			this.setSupplementalAddress(officer.supplementalAddress);
		}
		if(StringUtil.notEmpty(officer.cityTown)){
			this.setCity(officer.cityTown);
		}
		if(StringUtil.notEmpty(officer.state)){
			this.selectState(officer.state);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(officer.county)){
			this.selectCounty(officer.county);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(officer.phone)){
			this.setPhone(officer.phone);
		}
		if(StringUtil.notEmpty(officer.email)){
			this.setEmail(officer.email);
		}
	}
	
	/**
	 * Get error messages on the page
	 */
	public String getErrorMsg(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");

		if(objs.length<1){
			return "";
		}
		if (objs.length > 1) {
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
			return message;
		}
		message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
}
