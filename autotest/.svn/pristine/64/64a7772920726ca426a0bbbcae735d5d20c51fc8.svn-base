/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  2011-9-27
 */
public class LicMgrBondIssuerDetailsPage extends LicMgrSystemConfigurationPage {

	private static LicMgrBondIssuerDetailsPage _instance = null;

	protected LicMgrBondIssuerDetailsPage() {

	}

	public static LicMgrBondIssuerDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrBondIssuerDetailsPage();
		}

		return _instance;
	}

	public boolean exists() {

		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Business Name", false));
	}
	
	// View the change history
	public void clickChangeHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change History");
	}
	
	// Click OK
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	// Click Cancel
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public String getBusinessNm() {
		RegularExpression regx = new RegularExpression(".*businessName$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setBusinessNm(String businessNm){
		RegularExpression regx = new RegularExpression(".*businessName$", false);
		browser.setTextField(".id", regx, businessNm);
	}

	public String getContactAdr() {
		RegularExpression regx = new RegularExpression(".*address$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setContactAdr(String contactAddress){
		RegularExpression regx = new RegularExpression(".*address$", false);
		browser.setTextField(".id", regx, contactAddress);
	}

	public String getState() {
		RegularExpression regx = new RegularExpression(".*state$", false);
		return browser.getDropdownListValue(".id", regx);
	}
	
	public void setState(String state){
		RegularExpression regx = new RegularExpression(".*state$", false);
		browser.selectDropdownList(".id", regx, state);
	}

	public String getCityOrTown() {
		RegularExpression regx = new RegularExpression(".*city$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setCityOrTown(String city){
		RegularExpression regx = new RegularExpression(".*city$", false);
		browser.setTextField(".id", regx, city);
	}

	public String getZipCode() {
		RegularExpression regx = new RegularExpression(".*zipCode$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setZipCode(String zipCd){
		RegularExpression regx = new RegularExpression(".*zipCode$", false);
		browser.setTextField(".id", regx, zipCd);
	}

	public String getCountry() {
		RegularExpression regx = new RegularExpression(".*country$", false);
		return browser.getDropdownListValue(".id", regx);
	}
	
	public void setCountry(String country){
		RegularExpression regx = new RegularExpression(".*country$", false);
		browser.selectDropdownList(".id", regx, country);
	}

	public String getContactFirstNm() {
		RegularExpression regx = new RegularExpression(".*contactFirstName$", false);
//		return browser.getTextField(".id", regx)[0].text();
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setContactFirstNm(String firstName){
		RegularExpression regx = new RegularExpression(".*contactFirstName$", false);
		browser.setTextField(".id", regx, firstName);
	}

	public String getContactLastNm() {
		RegularExpression regx = new RegularExpression(".*contactLastName$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setContactLastNm(String lastName){
		RegularExpression regx = new RegularExpression(".*contactLastName$", false);
		browser.setTextField(".id", regx, lastName);
	}

	public String getPhone() {
		RegularExpression regx = new RegularExpression(".*contactPhone$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setPhone(String phone){
		RegularExpression regx = new RegularExpression(".*contactPhone$", false);
		browser.setTextField(".id", regx, phone);
	}

	public String getEmail() {
		RegularExpression regx = new RegularExpression(".*contactEmail$", false);
		return browser.getTextFieldValue(Property.toPropertyArray(".id", regx));
	}
	
	public void setEmail(String email){
		RegularExpression regx = new RegularExpression(".*contactEmail$", false);
		browser.setTextField(".id", regx, email);
	}

	public String getCreationUser() {
		RegularExpression regx = new RegularExpression(".*Creation User", false);
		IHtmlObject objs[] = browser.getHtmlObject(".className", "inputwithrubylabel", ".text", regx);
		String oo = objs[0].getProperty(".text").toString();
		String creationUser  = oo.replace("Creation User", "");
		Browser.unregister(objs);
		return creationUser;
	}
	
	public String getCreationLocation() {
		RegularExpression regx = new RegularExpression(".*Creation Location", false);
		IHtmlObject objs[] = browser.getHtmlObject(".className", "inputwithrubylabel", ".text", regx);
		String oo = objs[0].getProperty(".text").toString();
		String creationLocation  = oo.replace("Creation Location", "");
		Browser.unregister(objs);
		return creationLocation;
	}
	
	public String getCreationDateTime() {
		RegularExpression regx = new RegularExpression(".*Creation Date/Time", false);
		IHtmlObject objs[] = browser.getHtmlObject(".className", "inputwithrubylabel", ".text", regx);
		String oo = objs[0].getProperty(".text").toString();
		oo = oo.replace("Creation Date/Time", "").trim();
		String creationDateTime = oo.substring(0, 15);
		Browser.unregister(objs);
		return creationDateTime;
	}

	public String getLastModifiedUser() {
		RegularExpression regx = new RegularExpression(".*Last Modified User", false);
		IHtmlObject objs[] = browser.getHtmlObject(".className", "inputwithrubylabel", ".text", regx);
		String oo = objs[0].getProperty(".text").toString();
		String lastModifiedUser  = oo.replace("Last Modified User", "");
		Browser.unregister(objs);
		return lastModifiedUser;
	}

	public String getLastModifiedLocation() {
		RegularExpression regx = new RegularExpression(".*Last Modified Location", false);
		IHtmlObject objs[] = browser.getHtmlObject(".className", "inputwithrubylabel", ".text", regx);
		String oo = objs[0].getProperty(".text").toString();
		String lastModifiedLocation  = oo.replace("Last Modified Location", "");
		Browser.unregister(objs);
		return lastModifiedLocation;
	}
	
	public String getLastModifiedDateTime() {
		RegularExpression regx = new RegularExpression(".*Last Modified Date/Time", false);
		IHtmlObject objs[] = browser.getHtmlObject(".className", "inputwithrubylabel", ".text", regx);
		String oo = objs[0].getProperty(".text").toString();
		oo = oo.replace("Last Modified Date/Time", "").trim();
		String lastModifiedDateTime = oo.substring(0, 15);
		Browser.unregister(objs);
		return lastModifiedDateTime.trim();
	}

	private boolean compareInfo(String msg, String exp, String actual) {
		if (!exp.equals(actual)) {
			logger.error(msg + " is wrong! Expect is:" + exp + "; actual is:" + actual);
			return false;
		}
		logger.info(msg + " is correct as " + exp);
		return true;
	}
	
	public boolean compareBondIssuerInformation(LicMgrBondIssuerInfo bondIssuerInfo) {
		boolean result = true;
		
		String temp = this.getBusinessNm();
		result &= this.compareInfo("Business Name", bondIssuerInfo.businessNm, temp);
		
		temp = this.getCityOrTown();
		result &= this.compareInfo("City/Town", bondIssuerInfo.cityOrTown, temp);
		
		temp = this.getContactAdr();
		result &= this.compareInfo("Contact Address", bondIssuerInfo.contactAddress, temp);
		
		temp = this.getState();
		result &= this.compareInfo("State", bondIssuerInfo.state, temp);
		
		temp = this.getZipCode();
		result &= this.compareInfo("ZIP/Postal Code", bondIssuerInfo.zipCd, temp);
		
		temp = this.getContactFirstNm();
		result &= this.compareInfo("Contact First Name", bondIssuerInfo.firstName, temp);
		
		temp = this.getCountry();
		result &= this.compareInfo("Country", bondIssuerInfo.country, temp);
		
		temp = this.getContactLastNm();
		result &= this.compareInfo("Contact Last Name", bondIssuerInfo.lastName, temp);
		
		temp = this.getPhone();
		result &= this.compareInfo("Contact Phone #", bondIssuerInfo.phone, temp);
		
		temp = this.getEmail();
		result &= this.compareInfo("Contact Email", bondIssuerInfo.email, temp);
		
		return result;
	}
	
	public void verifyDetailOfBondIssuer(LicMgrBondIssuerInfo bondIssuerInfo) {
		// verify Bond Issuer Information
		if (!this.compareBondIssuerInformation(bondIssuerInfo)) {
			throw new ErrorOnPageException("Bond Issuer Information is wrong! Check logger error!");
		}

		// verify Add/Update information 
		String temp = this.getCreationUser().replaceAll("\\s+", "");
		if(!bondIssuerInfo.creationUser.equals(temp)) {
			String msg="Creation User is wrong! The expect is: \"" + bondIssuerInfo.creationUser+"\" actual: \"" + temp + "\"";
			logger.error(msg);
			throw new ErrorOnPageException(msg);
		}
		temp = this.getCreationLocation().trim();
		if(!bondIssuerInfo.creationLocation.equals(temp)) {
			logger.error("Creation Location is wrong!The expect is:" + bondIssuerInfo.creationLocation);
			throw new ErrorOnPageException("Creation Location is wrong!The expect is: " + bondIssuerInfo.creationLocation + ", but actual is: " + temp);
		}
		
//		if(!"".equals(bondIssuerInfo.creationDateTime)) {
//			if(DateFunctions.daysBetween(this.getCreationDateTime(), bondIssuerInfo.creationDateTime) != 0) {
//				logger.error("Creation Date/Time is wrong!The expect is:" + bondIssuerInfo.creationDateTime);
//				throw new ErrorOnPageException("Creation Time is wrong!The expect is:" + bondIssuerInfo.creationDateTime);
//			}
//		}
		temp = this.getLastModifiedUser().trim().replaceAll("\\s+", "");
		if(!bondIssuerInfo.lastModifiedUser.equals(temp)) {
			logger.error("Last Modified User is wrong!The expect is:" + bondIssuerInfo.lastModifiedUser);
			throw new ErrorOnPageException("Last Modified User is wrong!The expect is: " + bondIssuerInfo.lastModifiedUser + ", but actual is: " + temp);
		}
		temp = this.getLastModifiedLocation().trim();
		if(!bondIssuerInfo.lastModifiedLocation.equals(temp)) {
			logger.error("Last Modified Location is wrong!The expect is:" + bondIssuerInfo.lastModifiedLocation);
			throw new ErrorOnPageException("Last Modified Location is wrong!The expect is: " + bondIssuerInfo.lastModifiedLocation + ", but actual is: " + temp);
		}
		
		if(!"".equals(bondIssuerInfo.lastModifiedDateTime)) {
			if(DateFunctions.daysBetween(this.getLastModifiedDateTime(), bondIssuerInfo.lastModifiedDateTime) != 0) {
				logger.error("Last Modified Date/Time is wrong!The expect is:" + bondIssuerInfo.lastModifiedDateTime);
				throw new ErrorOnPageException("Last Modified Date/Time is wrong!The expect is: " + bondIssuerInfo.lastModifiedDateTime);
			}
		}
	}
}
