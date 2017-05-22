package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAddInvTypeLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAddInventoryTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInventoryTypeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryTypePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPriInvTypeAndLYFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeInventoryTypePage inventoryTypePage = LicMgrPrivilegeInventoryTypePage.getInstance();
	private LicMgrInventoryTypeLicenseYearPage inventoryTypeLicYearPage = LicMgrInventoryTypeLicenseYearPage.getInstance();	
	private InventoryTypeLicenseYear invTypeLicYear = new InventoryTypeLicenseYear();
	private boolean isAddInventoryLicenseYear;
	private boolean loggedin=false;
	private String contract = "";
	private String location = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		login.contract = (String)param[0];
		login.location = (String)param[1];
		invTypeLicYear = (InventoryTypeLicenseYear)param[2];
		isAddInventoryLicenseYear = (Boolean)param[3];
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		location = login.location;
		contract = login.contract;
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.addPrivilegeInventoryTypeInfo(invTypeLicYear.inventoryType);
		this.verifyAddPriInvTypeSuccessfully();
		newAddValue = inventoryTypePage.getInventoryTypeIDByTypeName(invTypeLicYear.inventoryType);
		//if need to add inventory type license year
		if(isAddInventoryLicenseYear) {
			inventoryTypePage.switchToInventoryTypeLicenseYearsTab();
			invTypeLicYear.id = lm.addInvTypeLicenseYear(invTypeLicYear);
			this.verifyAddPriInvTypeLicYearSuccessfully();
			newAddValue += ", " + invTypeLicYear.id;
		}		
	}
	
	private void verifyAddPriInvTypeSuccessfully() {
		LicMgrAddInventoryTypeWidget invTypeWidget = LicMgrAddInventoryTypeWidget.getInstance();
		if(invTypeWidget.exists()) {
			String errMsg = invTypeWidget.getErrorMsg();
			invTypeWidget.clickCancel();
			ajax.waitLoading();
			inventoryTypePage.waitLoading();
			throw new ErrorOnPageException("Failed to add inventory type: " + invTypeLicYear.inventoryType + ", due to:" + errMsg);
		}
		if (!inventoryTypePage.campareInventoryTypeNameAndStatus(invTypeLicYear.inventoryType, true)) {
			throw new ErrorOnPageException("The added inventory type info is wrong!");
		}
		logger.info("Successfully Add Privelege Inventory Type!");
	}
	
	private void verifyAddPriInvTypeLicYearSuccessfully() {
		LicMgrAddInvTypeLicenseYearWidget invTypeLicYearWidget = LicMgrAddInvTypeLicenseYearWidget.getInstance();
		if(invTypeLicYearWidget.exists()) {
			String errMsg = invTypeLicYearWidget.getErrorMsg();
			invTypeLicYearWidget.clickCancel();
			ajax.waitLoading();
			inventoryTypeLicYearPage.waitLoading();
			throw new ErrorOnPageException("Failed to add inventory type (" + invTypeLicYear.inventoryType + ") license year: " + invTypeLicYear.licenseYear + ", due to:" + errMsg);
		}
		
		if (!inventoryTypeLicYearPage.verifyInvTypeLicenseYearInfo(invTypeLicYear)) {
			throw new ErrorOnPageException("The added inventory type license year info is wrong!");
		}	
		logger.info("Successfully Add Privelege Inventory License Year Type!");
	}
}
