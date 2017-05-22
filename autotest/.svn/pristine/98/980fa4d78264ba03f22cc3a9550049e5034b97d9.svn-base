package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.inventory;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreInventoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreViewUnusablePrivilegeInventoryWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: view unusable(Returned, Withdrawn) privilege inventory list info
 * @Preconditions: 1. Vendor - 'Auto Vendor', store - 'WAL-MART'
 * 							2. Privilege Inventory - Inventory Type - 'ViewStoreUnusableInv', License Year - current Year, 001-365
 * @SPEC: View Unusable Privilege Inventory Allocated to Store [TC:019665]
 * @Task#: AUTO-1216
 * 
 * @author qchen
 * @Date  Aug 23, 2012
 */
public class ViewUnusable extends LicenseManagerTestCase {
	
	private String vendorName, storeName;
	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private LicMgrStoreViewUnusablePrivilegeInventoryWidget unusableInvWidget = LicMgrStoreViewUnusablePrivilegeInventoryWidget.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. goto Privilege Inventory page to Withdraw and Return 2 inventories
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.gotoPrivilegeInventoryPageFromInventoryTypePage();
//		lm.searchPrivilegeInventory(privInventory);
		List<String> availableInvNums = lm.getAvailablePrivilegeInventoryNumbers(schema, privInventory.inventoryType, privInventory.licenseYear, storeName);
		String returnedInvNum = availableInvNums.get(0);
		String withdrawnInvNum = availableInvNums.get(1);
		privInventory.inventoryNumFrom = returnedInvNum;
		privInventory.inventoryNumTo = withdrawnInvNum;
		lm.searchPrivilegeInventory(privInventory);
		//a. return inventory
		lm.returnPrivilegeInventoryItem(returnedInvNum, true);
		//b. withdraw inventory
		lm.withdrawPrivilegeInventoryItem(withdrawnInvNum, true);
		
		//2. goto Store-Inventory page to view the unusable(Available, Returned and Withdrawn) inventory
		lm.gotoStoreDetailPage(storeName, vendorName);
		lm.gotoStoreInventoryTab();
		lm.gotoViewUnusablePrivilegeInventoryWidget();
		
		//a. search an Available inventory and verify its info. For showing unusable available inventory, need a inventory license year less than current year
//		privInventory.inventoryStatus = OrmsConstants.PRIV_INV_STATUS_AVAILABLE;
//		privInventory.inventoryNumber = availableInvNums.get(2);
//		unusableInvWidget.searchUnusablePrivilegeInventory(privInventory);
//		this.verifyUnusableInventoryInfo(privInventory);
		//b. search a Returned inventory and verify its info
		privInventory.inventoryStatus = OrmsConstants.PRIV_INV_STATUS_RETURNED;
		privInventory.inventoryNumber = returnedInvNum;
		unusableInvWidget.searchUnusablePrivilegeInventory(privInventory);
		this.verifyUnusableInventoryInfo(privInventory);
		//c. search a Withdrawn inventory and verify its info
		privInventory.inventoryStatus = OrmsConstants.PRIV_INV_STATUS_WITHDRAWN;
		privInventory.inventoryNumber = withdrawnInvNum;
		unusableInvWidget.searchUnusablePrivilegeInventory(privInventory);
		this.verifyUnusableInventoryInfo(privInventory);
		
		this.gotoInventoryTabFromViewUnusableInventoryWidget();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		vendorName = "Auto Vendor";
		storeName = "WAL-MART";
		
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//privilege inventory info
		privInventory.inventoryType = "ViewStoreUnusableInv";
		privInventory.inventoryTypeStatus = OrmsConstants.ACTIVE_STATUS;
		privInventory.licenseYear = lm.getFiscalYear(schema);
	}
	
	private void verifyUnusableInventoryInfo(PrivilegeInventory expected) {
		logger.info("Compare Unusable Privilege Inventory info.");
		
		PrivilegeInventory actual = unusableInvWidget.getUnusableInventory(expected);
		boolean result = unusableInvWidget.compareUnusableInventory(expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The unusable privilege inventory(InventoryNum=" + expected.inventoryNumber + ") is incorrect.");
		} else logger.info("The unusable privilege inventory(InventoryNum=" + expected.inventoryNumber + ") is correct.");
	}
	
	private void gotoInventoryTabFromViewUnusableInventoryWidget() {
		unusableInvWidget.clickOK();
		ajax.waitLoading();
		LicMgrStoreInventoryPage.getInstance().waitLoading();
	}
}
