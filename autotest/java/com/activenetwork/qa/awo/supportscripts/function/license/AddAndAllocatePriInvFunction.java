package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrAllocatePrivilegeInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrCreateInventoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrInventoryTypeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddAndAllocatePriInvFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeInventoryPage privInvPage = LicMgrPrivilegeInventoryPage.getInstance();
	private LicMgrInventoryTypeLicenseYearPage inventoryTypeLicYearPage = LicMgrInventoryTypeLicenseYearPage.getInstance();
	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private PrivilegeInventoryAllocation privInvAllocation = new PrivilegeInventoryAllocation();
	private boolean loggedin=false;
	private String contract = "";
	private LicMgrCreateInventoryWidget createInvWidget = LicMgrCreateInventoryWidget.getInstance();
	private LicMgrAllocatePrivilegeInventoryWidget allocateInvWidget = LicMgrAllocatePrivilegeInventoryWidget.getInstance();
	private String location;
	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		privInventory = (PrivilegeInventory)param[2];
		privInvAllocation = (PrivilegeInventoryAllocation)param[3];	
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
		contract = login.contract;
		location = login.location;
		
		if(createInvWidget.exists()) {
			this.cancelFromCreateInventoryWidget();
		}
		if(allocateInvWidget.exists()) {
			this.cancelFromAllocateInventoryWidget();
		}
		
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		
		//switch to Inventory Type License Years
		if(privInventory.licenseYear.equalsIgnoreCase("All")) {
			this.getPrivilegeInventoryIssueDatesWhenLicenseYearIsAll();
		}
		
		
		privInvPage.switchToPrivilegeInventoryTab();
		
		//firstly, add privilege inventory
		lm.addPrivilegeInventory(privInventory);
		this.verifyAddPriInventorySuccessfully();
		
		//then, allocate privilege inventory to an agent
		lm.allocatePrivilegeInventory(privInvAllocation);
		this.verifyAllocatePriInventorySuccessfully();
		newAddValue = "Successfully add and allocation privilege type for " + privInvAllocation.inventoryType;		
	}
	
	private void getPrivilegeInventoryIssueDatesWhenLicenseYearIsAll() {
		privInvPage.switchToInventoryTypeLicenseYearsTab();
		inventoryTypeLicYearPage.waitLoading();
		String id = inventoryTypeLicYearPage.getInvTypeLicenseYearID(privInventory.inventoryType);
		InventoryTypeLicenseYear inv = inventoryTypeLicYearPage.getInvTypeLicenseYearInfoByID(id);
		privInventory.issueFromDate = inv.issueFromDate;
		privInventory.issueToDate = inv.issueToDate;
		privInvAllocation.issueFromDate = inv.issueFromDate;
		privInvAllocation.issueToDate = inv.issueToDate;
	}

	private void verifyAddPriInventorySuccessfully() {
		if(createInvWidget.exists()) {
			String errMsg = createInvWidget.getErrorMsg();
			createInvWidget.clickCancel();
			ajax.waitLoading();
			privInvPage.waitLoading();
			throw new ErrorOnPageException("Failed to create inventory due to: " + errMsg);
		}
		logger.info("Succesffully add privilege inventory!");
	}
	
	private void verifyAllocatePriInventorySuccessfully() {
		if(allocateInvWidget.exists()) {
			String errMsg = allocateInvWidget.getErrorMsg();
			allocateInvWidget.clickCancel();
			ajax.waitLoading();
			privInvPage.waitLoading();
			throw new ErrorOnPageException("Failed to allocate inventory due to:" + errMsg);
		}
		lm.searchPrivilegeInventory(privInventory);
		// Check the first and the last allocation info
		this.verifyPrivilegeInventoryInfo(privInvAllocation.inventoryNumFrom, privInventory.inventoryStatus, privInventory.agentID, privInventory.agentName);
		this.gotoLastPage();
		this.verifyPrivilegeInventoryInfo(privInvAllocation.inventoryNumTo, privInventory.inventoryStatus, privInventory.agentID, privInventory.agentName); 
		logger.info("Successfully allocation Inventory type!");
	}
	
	private void verifyPrivilegeInventoryInfo(String num, String status, String agentID, String agentName) {
		PriInventoryItemInfo actInventory = new PriInventoryItemInfo();
		String agentInfo = agentID + " - " + agentName;
		actInventory = privInvPage.getPrivilegeInventoryInfo(num);
		if (!actInventory.status.equalsIgnoreCase(status) || !actInventory.agentInfo.equals(agentInfo)) {
			throw new ErrorOnPageException("Privilege Inventory Allocation Info(" + num + ") is wrong!", 
					"status: " + status + ", agentInfo: " + agentInfo, 
					"status: " + actInventory.status + ", agentInfo: " + actInventory.agentInfo);
		}
	}
	
	private void gotoLastPage() {
		PagingComponent turnPageComponent = new PagingComponent();
		if (turnPageComponent.lastExists()) {
			turnPageComponent.clickLast();
			ajax.waitLoading();
			privInvPage.waitLoading();
		}
	}
	
	private void cancelFromCreateInventoryWidget() {
		logger.info("Cancel from Create Privilege Inventory widget to Privilege Inventory page..");
		createInvWidget.clickCancel();
		ajax.waitLoading();
		privInvPage.waitLoading();
	}
	
	private void cancelFromAllocateInventoryWidget() {
		logger.info("Cancel from Allocate Privilege Inventory widget to Privilege Inventory page.");
		allocateInvWidget.clickCancel();
		ajax.waitLoading();
		privInvPage.waitLoading();
	}
}
