package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAddInvTypeLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAddInventoryTypeWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInventoryTypeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryTypePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 23, 2012
 */
public class AddPrivInvTypeAndLicenseYear extends SupportCase {
	private boolean loggedIn = false;
	private String contract = "";
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeInventoryTypePage inventoryTypePage = LicMgrPrivilegeInventoryTypePage.getInstance();
	private LicMgrInventoryTypeLicenseYearPage inventoryTypeLicYearPage = LicMgrInventoryTypeLicenseYearPage.getInstance();
	private InventoryTypeLicenseYear invTypeLicYear = new InventoryTypeLicenseYear();
	private String schema = "";
	private boolean isAddInventoryLicenseYear = true;

	@Override
	public void execute() {
		if(!contract.equals(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn) || (loggedIn && (!inventoryTypePage.exists() || !inventoryTypeLicYearPage.exists()))) {
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.addPrivilegeInventoryTypeInfo(invTypeLicYear.inventoryType);
		
		//if need to add inventory type license year
		if(isAddInventoryLicenseYear) {
			inventoryTypePage.switchToInventoryTypeLicenseYearsTab();
			lm.addInvTypeLicenseYear(invTypeLicYear);
		}
		
		this.verifyResult();
		
		contract = login.contract;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 2;
		endpoint = 2;
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg = new String[6];
		logMsg[0] = "Index";
		logMsg[1] = "InventoryType";
		logMsg[2] = "LicenseYeaer";
		logMsg[3] = "IssueFromDate";
		logMsg[4] = "Cost";
		logMsg[5] = "Result";
	}

	@Override
	public void getNextData() {
		login.contract = dpIter.dpString("Contract");
		login.location = dpIter.dpString("Location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
		
		invTypeLicYear.inventoryType = dpIter.dpString("InventoryType");
		isAddInventoryLicenseYear = dpIter.dpBoolean("isAddInventoryLicenseYear");
		
		if(isAddInventoryLicenseYear) {
			invTypeLicYear.licenseYear = dpIter.dpString("LicenseYear");
			if(invTypeLicYear.licenseYear.trim().length() < 1) {
				invTypeLicYear.licenseYear = "ALL";
			} else if(!invTypeLicYear.licenseYear.equals("ALL") && Integer.parseInt(invTypeLicYear.licenseYear) < DateFunctions.getCurrentYear()) {
				invTypeLicYear.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
			}
			
			invTypeLicYear.issueFromDate = dpIter.dpString("IssueFromDate");
			if(invTypeLicYear.issueFromDate.trim().length() < 1) {
				invTypeLicYear.issueFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
			}
			invTypeLicYear.issueToDate = dpIter.dpString("IssueToDate");
			invTypeLicYear.cost = dpIter.dpString("Cost");
		}
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = invTypeLicYear.inventoryType;
		logMsg[2] = invTypeLicYear.licenseYear;
		logMsg[3] = invTypeLicYear.issueFromDate;
		logMsg[4] = invTypeLicYear.cost;
	}
	
	private void verifyResult() {
		LicMgrAddInventoryTypeWidget invTypeWidget = LicMgrAddInventoryTypeWidget.getInstance();
		LicMgrAddInvTypeLicenseYearWidget invTypeLicYearWidget = LicMgrAddInvTypeLicenseYearWidget.getInstance();
		
		if(invTypeWidget.exists()) {
			invTypeWidget.clickCancel();
			ajax.waitLoading();
			inventoryTypePage.waitLoading();
		}
		if(invTypeLicYearWidget.exists()) {
			invTypeLicYearWidget.clickCancel();
			ajax.waitLoading();
			inventoryTypeLicYearPage.waitLoading();
		}
		
		if(!inventoryTypePage.exists() || (isAddInventoryLicenseYear && !inventoryTypeLicYearPage.exists())) {
			logger.error("Create inventory type - " + invTypeLicYear.inventoryType + " failed." 
					+ (isAddInventoryLicenseYear ? (" And also failed to create inventory type license year - " + invTypeLicYear.licenseYear):""));
			
			logMsg[5] = "Failed";
			
		} else {
			logMsg[5] = "Passed";
		}
	}
}
