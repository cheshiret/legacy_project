package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.inventory;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreInventoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: view the 'Privilege Inventory Allocation Counts' correctly
 * @Preconditions: 1. Vendor - 'Auto Vendor', store - 'WAL-MART'
 * 							2. an existing privilege inventory(ViewStoreInventoryCounts) which is allocated with Store - 'WAL-MART'
 * 							3. an existing privilege product which is associate with this privilege inventory in precondition2; 
 * @SPEC: View Store Privilege Inventory Counts [TC:019654]
 * @Task#: AUTO-1216
 * 
 * @author qchen
 * @Date  Aug 23, 2012
 */
public class ViewAllocationCounts extends LicenseManagerTestCase {

	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private String saleLocation, adminLocation, vendorName, storeName;
	private LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget countsWidget = LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto Store-Inventory page to get original counts - # Unusable Returned, # Unusable Withdrawn and # Sold
		lm.gotoStoreDetailPage(storeName, vendorName);
		lm.gotoStoreInventoryTab();
		lm.gotoViewPrivilegeInventoryAllocationCountsWidget();
		PrivilegeInventory originalInv = countsWidget.getPrivilegeInventoryInfo(privInventory.inventoryType, privInventory.inventoryTypeStatus, privInventory.licenseYear);
		this.gotoStoreInventoryTabFromCountsWidget();
		
		//precondition#1: go to Privilege Inventory page to Return/Withdraw inventories
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.gotoPrivilegeInventoryPageFromInventoryTypePage();
		lm.searchPrivilegeInventory(privInventory);
		lm.returnFirstPrivilegeInventoryItem();
		originalInv.numOfUnusableReturned += 1;
		originalInv.numOfAvailable -= 1;
		lm.withdrawFirstPrivilegeInventoryItem();
		originalInv.numOfUnusableWithdrawn += 1;
		originalInv.numOfAvailable -= 1;
		
		//precondition#2: make a privilege order to make it as 'Sold'
		lm.switchLocationInHomePage(saleLocation);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.processOrderCart(pay);
		originalInv.numOfSold += Integer.parseInt(privilege.qty);
		originalInv.numOfAvailable -= 1;
		
		//go to Store-Inventory page to verify the inventory counts
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoStoreDetailPage(storeName, vendorName);
		lm.gotoStoreInventoryTab();
		lm.gotoViewPrivilegeInventoryAllocationCountsWidget();
		PrivilegeInventory finalInv = countsWidget.getPrivilegeInventoryInfo(privInventory.inventoryType, privInventory.inventoryTypeStatus, privInventory.licenseYear);
		System.out.println("final Sold num: " + finalInv.numOfSold);
		this.verifyPrivilegeInventoryInfo(originalInv, finalInv);
		int totalCount = lm.getPrivilegeInventoryTotalCount(schema, privInventory.inventoryType, privInventory.licenseYear, privInventory.agentID);
		this.verifyCounts(finalInv, totalCount);
		this.gotoStoreInventoryTabFromCountsWidget();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		adminLocation = login.location.replace("/", "-");
		saleLocation = "HF HQ Role - Auto-WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		vendorName = "Auto Vendor";
		storeName = "WAL-MART";
		String storeId = lm.getAgentID(schema, storeName);
		
		//privilege inventory info
		privInventory.inventoryType = "ViewStoreInventoryCounts";
		privInventory.inventoryTypeStatus = OrmsConstants.ACTIVE_STATUS;
		privInventory.licenseYear = lm.getFiscalYear(schema);
		privInventory.inventoryStatus = "Available";
		privInventory.allocationStatus = "Allocated";
		privInventory.agentID = storeId;
		privInventory.agentName = storeName;
		
		//customer info
		cust.customerClass = "Individual";
		cust.dateOfBirth = "19880310";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111190";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		//privilege product info
		privilege.code = "VIC";
		privilege.name = "ViewStoreInventoryCounts";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
	}
	
	public void gotoStoreInventoryTabFromCountsWidget() {
		LicMgrStoreInventoryPage storeInventoryPage = LicMgrStoreInventoryPage.getInstance();
		LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget countsWidget = LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget.getInstance();
		
		logger.info("Go tostore inventory tab from 'View Privilege Inventory Allocation Counts' widget.");
		countsWidget.clickOK();
		ajax.waitLoading();
		storeInventoryPage.waitLoading();
	}
	
	private void verifyPrivilegeInventoryInfo(PrivilegeInventory expected, PrivilegeInventory actual) {
		boolean result = countsWidget.comparePrivilegeInventoryInfo(expected, actual);
		if(!result) {
			throw new ErrorOnPageException("Privilege Inventory info is incorrect.");
		}else logger.info("Privilege Inventory info is correct.");
	}
	
	private void verifyCounts(PrivilegeInventory pi, int totalCount) {
		int sumOfAvailable = pi.numOfAvailable != 0 ? pi.numOfAvailable : pi.numOfUnusableAvailable;
		int sumOfUnusable = pi.numOfUnusableReturned + pi.numOfUnusableWithdrawn + pi.numOfSold;
		if(sumOfAvailable + sumOfUnusable != totalCount) {
			throw new ErrorOnPageException("The privilege inventory total count(=" + totalCount  + ") doesn't match sum(=" + (sumOfAvailable + sumOfUnusable) + ") of each group(Unusable Return, Unusable Withdrawn and Sold.).");
		} else logger.info("The privilege inventory counts totally macth.");
	}
}
