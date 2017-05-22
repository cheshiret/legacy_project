package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAllocatePrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrCreateInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInventoryTypeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
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
public class AddAndAllocatePrivilegeInventory extends SupportCase {
	private boolean loggedIn = false;
	private String contract = "";
	private String schema = "";
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeInventoryPage privInvPage = LicMgrPrivilegeInventoryPage.getInstance();
	private LicMgrInventoryTypeLicenseYearPage inventoryTypeLicYearPage = LicMgrInventoryTypeLicenseYearPage.getInstance();
	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private PrivilegeInventoryAllocation privInvAllocation = new PrivilegeInventoryAllocation();
	
	@Override
	public void execute() {
		if(!contract.equals(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if(!loggedIn || ( !privInvPage.exists()&& loggedIn)) {
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		
		//switch to Inventory Type License Years
		if(privInventory.licenseYear.equalsIgnoreCase("All")) {
			this.getPrivilegeInventoryIssueDatesWhenLicenseYearIsAll();
		}
		
		privInvPage.switchToPrivilegeInventoryTab();
		//firstly, add privilege inventory
		lm.addPrivilegeInventory(privInventory);
		
		//then, allocate privilege inventory to an agent
		lm.allocatePrivilegeInventory(privInvAllocation);
		
		this.verifyResult();
		
		contract = login.contract;
	}

	private void getPrivilegeInventoryIssueDatesWhenLicenseYearIsAll() {
		privInvPage.switchToInventoryTypeLicenseYearsTab();
		inventoryTypeLicYearPage.waitLoading();
		String id = inventoryTypeLicYearPage.getInvTypeLicenseYearID(privInventory.licenseYear.toUpperCase());
		InventoryTypeLicenseYear inv = inventoryTypeLicYearPage.getInvTypeLicenseYearInfoByID(id);
		privInventory.issueFromDate = inv.issueFromDate;
		privInventory.issueToDate = inv.issueToDate;
		privInvAllocation.issueFromDate = inv.issueFromDate;
		privInvAllocation.issueToDate = inv.issueToDate;
	}

	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 1;
		endpoint = 1;
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg = new String[10];
		logMsg[0] = "Index";
		logMsg[1] = "InventoryType";
		logMsg[2] = "LicenseYear";
		logMsg[3] = "InventoryNumberFrom";
		logMsg[4] = "InventoryNumberTo";
		logMsg[5] = "AllocateToVendor";
		logMsg[6] = "AllocateToAgent";
		logMsg[7] = "AllocateInvNumberFrom";
		logMsg[8] = "AllocateInvNumberTo";
		logMsg[9] = "Result";
	}

	@Override
	public void getNextData() {
		login.contract = dpIter.dpString("Contract");
		login.location = dpIter.dpString("Location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
		
		//add privilege inventory info
		privInventory.inventoryType = dpIter.dpString("InventoryType");
		privInventory.inventoryTypeStatus = dpIter.dpString("InventoryStatus");
		if(privInventory.inventoryStatus.trim().length() < 1) {
			privInventory.inventoryStatus = OrmsConstants.ACTIVE_STATUS;
		}
		privInventory.licenseYear = dpIter.dpString("LicenseYear");
		if(privInventory.licenseYear.trim().length() < 1) {
			privInventory.licenseYear = "All";
		} else if(!privInventory.licenseYear.equals("All") && Integer.parseInt(privInventory.licenseYear) < DateFunctions.getCurrentYear()) {
			privInventory.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		}
		privInventory.inventoryNumFrom = dpIter.dpString("AddInvNumFrom");
		privInventory.inventoryNumTo = dpIter.dpString("AddInvNumTo");
		
		//privilege inventory allocation info
		privInvAllocation.inventoryType = dpIter.dpString("InventoryType");
		privInvAllocation.inventoryTypeStatus = privInventory.inventoryTypeStatus;
		privInvAllocation.licenseYear = dpIter.dpString("LicenseYear");
		if(privInvAllocation.licenseYear.trim().length() < 1) {
			privInvAllocation.licenseYear = privInventory.licenseYear;
		} else if(!privInvAllocation.licenseYear.equals("All") && Integer.parseInt(privInvAllocation.licenseYear) < DateFunctions.getCurrentYear()) {
			privInvAllocation.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		}
		privInvAllocation.vendorNum = dpIter.dpString("VendorNumber");
		privInvAllocation.vendorName = dpIter.dpString("VendorName");
		privInvAllocation.agentName = dpIter.dpString("AgentName");
		privInvAllocation.agentID = lm.getAgentID(schema, privInvAllocation.agentName);
		privInvAllocation.inventoryNumFrom = dpIter.dpString("AllocationInvNumFrom");
		if(privInvAllocation.inventoryNumFrom.trim().length() < 1) {
			privInvAllocation.inventoryNumFrom = privInventory.inventoryNumFrom;
		}
		privInvAllocation.inventoryNumTo = dpIter.dpString("AllocationInvNumTo");
		if(privInvAllocation.inventoryNumTo.trim().length() < 1) {
			privInvAllocation.inventoryNumTo = privInventory.inventoryNumTo;
		}
		
		//set value to log
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = privInventory.inventoryType;
		logMsg[2] = privInventory.licenseYear;
		logMsg[3] = privInventory.inventoryNumFrom;
		logMsg[4] = privInventory.inventoryNumTo;
		logMsg[5] = privInvAllocation.vendorNum + " - " + privInvAllocation.vendorName;
		logMsg[6] = privInvAllocation.agentID + " - " + privInvAllocation.agentName;
		logMsg[7] = privInvAllocation.inventoryNumFrom;
		logMsg[8] = privInvAllocation.inventoryNumTo;
	}
	
	private void verifyResult() {
		LicMgrCreateInventoryWidget createInvWidget = LicMgrCreateInventoryWidget.getInstance();
		LicMgrAllocatePrivilegeInventoryWidget allocateInvWidget = LicMgrAllocatePrivilegeInventoryWidget.getInstance();
		
		if(createInvWidget.exists()) {
			createInvWidget.clickCancel();
			ajax.waitLoading();
			privInvPage.waitLoading();
		}
		if(allocateInvWidget.exists()) {
			allocateInvWidget.clickCancel();
			ajax.waitLoading();
			privInvPage.waitLoading();
		}
		if(!privInvPage.exists()) {
			logger.error("Create and allocate Privilege Inventory failed. Inventory Type - " + privInventory.inventoryType + ", License Year - " + privInventory.licenseYear + ", Allocation Vendor Name - " + privInvAllocation.vendorName + ", Allocation Agent Name - " + privInvAllocation.agentName);
			logMsg[9] = "Failed";
		} else {
			logMsg[9] = "Passed";
		}
	}
}
