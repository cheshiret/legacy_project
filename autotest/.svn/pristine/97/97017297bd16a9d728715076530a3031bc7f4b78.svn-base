package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrLienCompanyDetailPage extends LicMgrSystemConfigurationPage{
	private RegularExpression lienCompanyIDRegExp = new RegularExpression("LienCompanyView-\\d+\\.id",false);
	private RegularExpression lienCompanyNameRegExp = new RegularExpression("LienCompanyView-\\d+\\.lienCompanyName\\.name",false);
	private RegularExpression addressRegExp = new RegularExpression("LienCompanyView-\\d+\\.address",false);
	private RegularExpression cityRegExp = new RegularExpression("LienCompanyView-\\d+\\.city",false);
	private RegularExpression stateRegExp = new RegularExpression("LienCompanyView-\\d+\\.state",false);
	private RegularExpression zipRegExp = new RegularExpression("LienCompanyView-\\d+\\.zip",false);
	private RegularExpression countryRegExp = new RegularExpression("LienCompanyView-\\d+\\.country",false);
	private RegularExpression contactFirstNameRegExp = new RegularExpression("LienCompanyView-\\d+\\.contactFirstName",false);
	private RegularExpression contactLastNameRegExp = new RegularExpression("LienCompanyView-\\d+\\.contactLastName",false);
	private RegularExpression contactPhoneRegExp = new RegularExpression("LienCompanyView-\\d+\\.contactPhone",false);
	private RegularExpression contactEmailRegExp = new RegularExpression("LienCompanyView-\\d+\\.contactEmail",false);
	
	private static LicMgrLienCompanyDetailPage _instance = null;
	
	protected LicMgrLienCompanyDetailPage(){}
	
	public static LicMgrLienCompanyDetailPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrLienCompanyDetailPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".id", lienCompanyIDRegExp);
	}
	
	public String getLienCompanyID(){
		return browser.getTextFieldValue(".id", lienCompanyIDRegExp);
	}
	
	public String getLienCompanyName(){
		return browser.getTextFieldValue(".id", lienCompanyNameRegExp);
	}
	
	public String getAddress(){
		return browser.getTextFieldValue(".id", addressRegExp);
	}
	
	public String getCity(){
		return browser.getTextFieldValue(".id", cityRegExp);
	}
	
	public String getState(){
		return browser.getDropdownListValue(".id", stateRegExp);
	}
	
	public String getZip(){
		return browser.getTextFieldValue(".id", zipRegExp);
	}
	
	public String getCountry(){
		return browser.getDropdownListValue(".id", countryRegExp);
	}
	
	public String getContactFirstName(){
		return browser.getTextFieldValue(".id", contactFirstNameRegExp);
	}
	
	public String getContactLastName(){
		return browser.getTextFieldValue(".id", contactLastNameRegExp);
	}
	
	public String getContactPhone(){
		return browser.getTextFieldValue(".id", contactPhoneRegExp);
	}
	
	public String getContactEmail(){
		return browser.getTextFieldValue(".id", contactEmailRegExp);
	}
	
	private String getSpanNameFromSpanObj(Object valueOfDivID){
		IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.SPAN", ".id",valueOfDivID);
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not fund SPAN Object.");
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN", divObjs[0]);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not found SPAN Object.");
		}
		
		String text = spanObjs[0].text();
		Browser.unregister(spanObjs);
		Browser.unregister(divObjs);
		
		return text;		
	}
	
	public String getCreationUser(){
		return this.getSpanNameFromSpanObj(new RegularExpression("LienCompanyView-\\d+\\.createUserName",false)).replaceAll("Creation User", StringUtil.EMPTY);
	}
	
	public String getCreationLocation(){
		return this.getSpanNameFromSpanObj(new RegularExpression("LienCompanyView-\\d+\\.createLocationName",false)).replaceAll("Creation Location", StringUtil.EMPTY);
	}
	
	public String getCreationDate(){
		return this.getSpanNameFromSpanObj(new RegularExpression("LienCompanyView-\\d+\\.createDate",false)).replaceAll("Creation Date/Time", StringUtil.EMPTY);
	}
	
	public String getLastUpdateUser(){
		return this.getSpanNameFromSpanObj(new RegularExpression("LienCompanyView-\\d+\\.updateUserName",false)).replaceAll("Last Updated User", StringUtil.EMPTY);
	}
	
	public String getLastUpdateLocation(){
		return this.getSpanNameFromSpanObj(new RegularExpression("LienCompanyView-\\d+\\.updateLocationName",false)).replaceAll("Last Updated Location", StringUtil.EMPTY);
	}
	
	public String getLastUpdateDate(){
		return this.getSpanNameFromSpanObj(new RegularExpression("LienCompanyView-\\d+\\.updateDate",false)).replaceAll("Last Updated Date/Time", StringUtil.EMPTY);
	}
	
	public LienCompanyDetailsInfo getLienCompanyInfo(){
		LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
		
		logger.info("Get lien company info from detail page.");
		lienCompany.lienCompanyAddrID = this.getLienCompanyID();
		lienCompany.lienCompanyName = this.getLienCompanyName();
		lienCompany.address = this.getAddress();
		lienCompany.city = this.getCity();
		lienCompany.state = this.getState();
		lienCompany.zip = this.getZip();
		lienCompany.country = this.getCountry();
		lienCompany.contactfName = this.getContactFirstName();
		lienCompany.contactlName = this.getContactLastName();
		lienCompany.contactPhone = this.getContactPhone();
		lienCompany.contactEmail = this.getContactEmail();
		lienCompany.creationUser = this.getCreationUser();
		lienCompany.creationLocation = this.getCreationLocation();
		lienCompany.creationDate = this.getCreationDate();
		lienCompany.lastUpdateUser = this.getLastUpdateUser();
		lienCompany.lastUpdateLocation = this.getLastUpdateLocation();
		lienCompany.lastUpdateDate = this.getLastUpdateDate();
		
		return lienCompany;		
	}
	
	public void compareLienCompanyDetailInfo(LienCompanyDetailsInfo expLienCompany){
		boolean result = true;
		LienCompanyDetailsInfo actLienCompany = this.getLienCompanyInfo();		
		
		logger.info("Compare lien comany detail info from detail page.");
		result &= MiscFunctions.compareResult("Lien Company ID", expLienCompany.lienCompanyAddrID, actLienCompany.lienCompanyAddrID);
		result &= MiscFunctions.compareResult("Lien Company Name", expLienCompany.lienCompanyName, actLienCompany.lienCompanyName);
		result &= MiscFunctions.compareResult("Address", expLienCompany.address, actLienCompany.address);
		result &= MiscFunctions.compareResult("City", expLienCompany.city, actLienCompany.city);
		result &= MiscFunctions.compareResult("State", expLienCompany.state, actLienCompany.state);
		result &= MiscFunctions.compareResult("Zip", expLienCompany.zip, actLienCompany.zip);
		result &= MiscFunctions.compareResult("Country", expLienCompany.country, actLienCompany.country);
		result &= MiscFunctions.compareResult("Contact First Name", expLienCompany.contactfName, actLienCompany.contactfName);
		result &= MiscFunctions.compareResult("Contact Last Name", expLienCompany.contactlName, actLienCompany.contactlName);
		result &= MiscFunctions.compareResult("Contact Phone", expLienCompany.contactPhone, actLienCompany.contactPhone);
		result &= MiscFunctions.compareResult("Contact Email", expLienCompany.contactEmail, actLienCompany.contactEmail);
		result &= MiscFunctions.compareResult("Creation User", expLienCompany.creationUser.replaceAll(" ", StringUtil.EMPTY), actLienCompany.creationUser.replaceAll(" ", StringUtil.EMPTY));
		result &= MiscFunctions.compareResult("Creation Location", expLienCompany.creationLocation, actLienCompany.creationLocation);
		result &= MiscFunctions.compareResult("Creation Date", expLienCompany.creationDate, actLienCompany.creationDate);
		result &= MiscFunctions.compareResult("Last Update User", expLienCompany.lastUpdateUser.replaceAll(" ", StringUtil.EMPTY), actLienCompany.lastUpdateUser.replaceAll(" ", StringUtil.EMPTY));
		result &= MiscFunctions.compareResult("Last Update Location", expLienCompany.lastUpdateLocation, actLienCompany.lastUpdateLocation);
		result &= MiscFunctions.compareResult("Last Update Date", expLienCompany.lastUpdateDate, actLienCompany.lastUpdateDate);
		
		if(!result){
			throw new ErrorOnDataException("Lien Company info at detial page is not correct, please check log.");
		}else{
			logger.info("Lien Company info at detial page is correct.");
		}
	}
	
	public void setLienCompanyName(String lienCompanyName){
		browser.setTextField(".id", lienCompanyNameRegExp, lienCompanyName, true);
	}
	
	public void setAddress(String address){
		browser.setTextField(".id", addressRegExp, address, true);
	}
	
	public void setCity(String city){
		browser.setTextField(".id", cityRegExp, city, true);
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", stateRegExp, state);
	}
	
	public void setZip(String zip){
		browser.setTextField(".id", zipRegExp, zip, true);
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", countryRegExp, country);
	}
	
	public void setContactFirstName(String contactFirstName){
		browser.setTextField(".id", contactFirstNameRegExp, contactFirstName, true);
	}
	
	public void setContactLastName(String contactLastName){
		browser.setTextField(".id", contactLastNameRegExp, contactLastName, true);
	}
	
	public void setContactPhone(String phone){
		browser.setTextField(".id", contactPhoneRegExp, phone, true);
	}
	
	public void setContactEmail(String email){
		browser.setTextField(".id", contactEmailRegExp, email, true);
	}
	
	public void setLienCompanyInfo(LienCompanyDetailsInfo lienCompany){
		if(lienCompany.lienCompanyName != null){
			this.setLienCompanyName(lienCompany.lienCompanyName);
		}
		
		if(lienCompany.address != null){
			this.setAddress(lienCompany.address);
		}
		
		if(lienCompany.city != null){
			this.setCity(lienCompany.city);
		}
		
		if(lienCompany.state != null){
			this.selectState(lienCompany.state);
		}
		
		if(lienCompany.zip != null){
			this.setZip(lienCompany.zip);
		}
		
		if(lienCompany.country != null){
			this.selectCountry(lienCompany.country);
		}
		
		if(lienCompany.contactfName != null){
			this.setContactFirstName(lienCompany.contactfName);
		}
		
		if(lienCompany.contactlName != null){
			this.setContactLastName(lienCompany.contactlName);
		}
		
		if(lienCompany.contactPhone != null){
			this.setContactPhone(lienCompany.contactPhone);
		}
		
		if(lienCompany.contactEmail != null){
			this.setContactEmail(lienCompany.contactEmail);
		}
		
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text","Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text","Apply");
	}
	
	public void clickChangeHistory(){
		browser.clickGuiObject(".class", "Html.A", ".text","Change History");
	}

}
