/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Mar 30, 2012
 */
public class LicMgrVehicleEditCoOwnerWidget extends DialogWidget {
	private String prefix = "VehicleCoownerView-";
	
	private static LicMgrVehicleEditCoOwnerWidget instance = null;

	private LicMgrVehicleEditCoOwnerWidget() {
	}

	public static LicMgrVehicleEditCoOwnerWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrVehicleEditCoOwnerWidget();
		}
		return instance;
	}

	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", "Edit Co-Ownerclose");
		return flag1 && flag2;
	}
	
	public String getFirstName() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.firstName", false));
	}
	
	public String getMiddleName() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.middleName", false));
	}
	
	public String getLastName() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.lastName", false));
	}
	
	public String getSuffix(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"\\d+\\.suffix", false));
	}
	
	public String getDateOfBirth() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.dateOfBirth", false));
	}
	
	public String getBusinessName() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.businessName", false));
	}
	
	public String getIdentificationType(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"\\d+\\.identificationType", false));
	}
	
	public String getIdentificationNum() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"\\d+\\.identification", false));
	}
	
	public String getCoOwnerID(){
		String id = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Co-Owner #\\s?\\d+$", false));
		
		if(objs.length<1){
			throw new ErrorOnPageException("No co-owner num display on edit co-owner widget.");
		}
		id = objs[0].getProperty(".text").replaceAll("Co-Owner #", "").replaceAll("\\s?", "");
		Browser.unregister(objs);
		return id;
	}
	
	public String getCreateUser() {
		String user = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Creation User.*", false));
		
		if(objs.length<2){
			throw new ErrorOnPageException("No creation user display on edit co-owner widget.");
		}
		user = objs[1].text().replaceAll("Creation User", "").replaceAll("\\s?", "");//there is a 'Create User' in vehicle detail page
		Browser.unregister(objs);
		return user;
	}
	
	public String getCreateDateTime() {
		String date = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Creation Date/Time\\s?[a-z]{3} [a-z]{3} [0-9]{1,2} [0-9]{4}.*[AM|PM]$", false));
		
		if(objs.length<1){
			throw new ErrorOnPageException("No creation date/time display on edit co-owner widget.");
		}
		date = objs[0].getProperty(".text").replaceAll("Creation Date/Time", "").trim();
		Browser.unregister(objs);
		return date;
	}
		
	public OwnerInfo getCoOwnerDetailInfo(){
		OwnerInfo coOwner = new OwnerInfo();
		coOwner.id = getCoOwnerID();
		coOwner.firstName = getFirstName();
		coOwner.lastName = getLastName();
		coOwner.midName = getMiddleName();
		coOwner.suffix = getSuffix();
		coOwner.dateOfBirth = getDateOfBirth();
		coOwner.businessName = getBusinessName();
		coOwner.identifierType = getIdentificationType();
		coOwner.identifierNum = getIdentificationNum();
		coOwner.creationUser = getCreateUser();
		coOwner.creationDateTime = getCreateDateTime();
		return coOwner;
	}
	
	public boolean compareOwnerDetailInfo(OwnerInfo ownerInfo) {
		boolean flag = true;
		OwnerInfo ownerInfoUI = getCoOwnerDetailInfo();
		
		flag &= MiscFunctions.compareResult("coOwner ID is wrong.",ownerInfo.id,ownerInfoUI.id);
		flag &= MiscFunctions.compareResult("coOwner firstName is wrong.",ownerInfo.firstName,ownerInfoUI.firstName);
		flag &= MiscFunctions.compareResult("coOwner midName is wrong.",ownerInfo.midName,ownerInfoUI.midName);
		flag &= MiscFunctions.compareResult("coOwner lastName is wrong.",ownerInfo.lastName,ownerInfoUI.lastName);
		flag &= MiscFunctions.compareResult("coOwner suffix is wrong.",ownerInfo.suffix,ownerInfoUI.suffix);
		flag &= MiscFunctions.compareResult("coOwner dateOfBirth is wrong.",ownerInfo.dateOfBirth,ownerInfoUI.dateOfBirth);
		flag &= MiscFunctions.compareResult("coOwner businessName is wrong.",ownerInfo.businessName,ownerInfoUI.businessName);
		flag &= MiscFunctions.compareResult("coOwner identifierType is wrong.",ownerInfo.identifierType,ownerInfoUI.identifierType);
		flag &= MiscFunctions.compareResult("coOwner identifierNum is wrong.",ownerInfo.identifierNum,ownerInfoUI.identifierNum);
		flag &= MiscFunctions.compareResult("coOwner creationUser is wrong.",ownerInfo.creationUser,ownerInfoUI.creationUser);
		flag &= MiscFunctions.compareResult("coOwner creationDateTime is wrong.",ownerInfo.creationDateTime,ownerInfoUI.creationDateTime);
		
		return flag;
	}
	
	public void verifyCoOwnerNumDisplay(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Co-Owner #\\s?\\d+", false));
		if(objs.length<1){
			throw new ErrorOnPageException("No Co-Owner # display on edit co-owner widget.");
		}
		logger.info("Co-Owner # has displayed in add Co-Owner widget");
		Browser.unregister(objs);		
	}

	public String getIdentificationState() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"\\d+\\.stateProvince", false));
	}
	
	public void editCoOwnerInfo(OwnerInfo ownerUpdate) {
		if(null == ownerUpdate){
			throw new ErrorOnDataException("Please check input parameter for owner info");
		}
		this.setFirstName(ownerUpdate.firstName);
		this.setMiddleName(ownerUpdate.midName);
		this.setLastName(ownerUpdate.lastName);
		this.selectSuffix(ownerUpdate.suffix);
		this.setDateOfBirth(ownerUpdate.dateOfBirth);
		this.setBusinessName(ownerUpdate.businessName);
		this.selectIdentificationType(ownerUpdate.identifierType);
		ajax.waitLoading();
		this.setIdentificationNum(ownerUpdate.identifierNum);
		if(stateExisted() && ""!=ownerUpdate.identifierState){
			selectState(ownerUpdate.identifierState);
		}
		this.clickOK();
	}
	
	public void setFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.firstName", false), fName);	
	}
	
	public void setMiddleName(String mName) {
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.middleName", false), mName);
	}
	
	public void setLastName(String lName) {
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.lastName", false), lName);
	}
	
	public void selectSuffix(String suffix){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.suffix", false), suffix);
	}
	
	public void setDateOfBirth(String date) {
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.dateOfBirth", false), date);
	}
	
	public void setBusinessName(String bName) {
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.businessName", false), bName);
	}
	
	public void selectIdentificationType(String type){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.identificationType", false), type);
	}
	
	public void setIdentificationNum(String num) {
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.identification", false), num);
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"\\d+\\.stateProvince", false), state);
	}
	
	public boolean stateExisted(){
		boolean flag = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefix+"\\d+\\.stateProvince", false));
		if(objs.length>0){
			flag = true;
		}
		return flag;
	}
	
}
