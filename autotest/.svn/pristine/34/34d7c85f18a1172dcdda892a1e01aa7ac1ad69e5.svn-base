/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
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
 * @Date  Apr 16, 2012
 */
public class LicMgrVehicleAddCoOwnerWidget extends DialogWidget {
	private String prefix = "VehicleCoownerView-";
	
	private static LicMgrVehicleAddCoOwnerWidget instance = null;

	private LicMgrVehicleAddCoOwnerWidget() {
	}

	public static LicMgrVehicleAddCoOwnerWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrVehicleAddCoOwnerWidget();
		}
		return instance;
	}

	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", "Add Co-Ownerclose");
		return flag1 && flag2;
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
	
	public void setupCoOwnerInfo(OwnerInfo ownerInfo){
		if(null == ownerInfo){
			throw new ErrorOnDataException("Please check input parameter for owner info");
		}
		
		setFirstName(ownerInfo.firstName);
		setMiddleName(ownerInfo.midName);
		setLastName(ownerInfo.lastName);
		selectSuffix(ownerInfo.suffix);
		setDateOfBirth(ownerInfo.dateOfBirth);
		setBusinessName(ownerInfo.businessName);
		selectIdentificationType(ownerInfo.identifierType);
		ajax.waitLoading();
		setIdentificationNum(ownerInfo.identifierNum);
		if(stateExisted() && ""!=ownerInfo.identifierState){
			selectState(ownerInfo.identifierState);
		}
	}
	
	public void verifyCoOwnerNumDisplay(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Co-Owner #\\s?New", false));
		if(objs.length<1){
			throw new ErrorOnPageException("No Co-Owner # display on add co-owner widget.");
		}
		logger.info("Co-Owner # has displayed in add Co-Owner widget");
		Browser.unregister(objs);		
	}
}
