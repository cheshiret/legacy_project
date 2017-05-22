package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: view the available privilege inventory displayed correctly
 * @Preconditions: Vendor - 'Auto Vendor', store - 'WAL-MART'
 * @SPEC: View Available Privilege Inventory Allocated to Store [TC:019655]
 * @Task#: AUTO-1216
 * 
 * @author qchen
 * @Date  Aug 23, 2012
 */
public class ViewAvailable extends LicenseManagerTestCase {
	
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private PrivilegeInventoryAllocation privInvAllocation = new PrivilegeInventoryAllocation();
	private String vendorName, storeName, dateFormat;
	private LicMgrStoreInventoryPage storeInvPage = LicMgrStoreInventoryPage.getInstance();
	private List<PrivilegeInventory> expectedInventories = new ArrayList<PrivilegeInventory>();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//check and clean up data
		checkAndCleanUp();
		
		//goto Privilege Inventory page to create a Inventory Type, Inventory Type License Year and Privilege Inventory(3)
		//1. add inventory type
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.addPrivilegeInventoryTypeInfo(privInventory.inventoryType);
		//2. add inventory type license year
		lm.gotoInventoryTypeLicenseYearPageFromInventoryTypePage();
		lm.addInvTypeLicenseYear(invTypeLicenseYear);
		//3. create inventory
		lm.gotoPrivilegeInventoryPageFromInventoryTypeLicenseYearPage();
		lm.addPrivilegeInventory(privInventory);
		//4. allocate privilege inventory
		lm.allocatePrivilegeInventory(privInvAllocation);
		
		//goto Store-Inventory tab to view these available inventories
		lm.gotoStoreDetailPage(storeName, vendorName);
		lm.gotoStoreInventoryTab();
		storeInvPage.searchPrivilegeInventory(privInventory.inventoryType, null);
		this.verifySearchingResult();
		List<PrivilegeInventory> inventoriesOnPage = storeInvPage.getAllPrivilegeInventories();
		storeInvPage.comparePrivilegeInventoryInfo(expectedInventories, inventoriesOnPage);
		
		//goto privilege inventory to return, withdraw 2 privilege inventories
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		lm.gotoPrivilegeInventoryPageFromInventoryTypePage();
		lm.searchPrivilegeInventory(privInventory);
		lm.returnPrivilegeInventoryItem("1", true);
		lm.withdrawPrivilegeInventoryItem("2", true);
		
		//goto Store-Inventory tab to view the reset available inventories
		expectedInventories.remove(0);
		expectedInventories.remove(1);
		lm.gotoStoreDetailPage(storeName, vendorName);
		lm.gotoStoreInventoryTab();
		storeInvPage.searchPrivilegeInventory(privInventory.inventoryType, null);
		inventoriesOnPage = storeInvPage.getAllPrivilegeInventories();
		storeInvPage.comparePrivilegeInventoryInfo(expectedInventories, inventoriesOnPage);
		
		//clean up
		checkAndCleanUp();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		vendorName = "Auto Vendor";
		storeName = "WAL-MART";
		String vendorNum = "Auto555";
		String storeId = lm.getAgentID(schema, storeName);
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		dateFormat = "EEE MMM d yyyy";
		
		//inventory type license year info
		invTypeLicenseYear.inventoryType = "ViewStoreAvailableInv";
		invTypeLicenseYear.licenseYear = "ALL";
		invTypeLicenseYear.issueFromDate = DateFunctions.getToday(timeZone);
		invTypeLicenseYear.issueToDate = DateFunctions.getDateAfterToday(2, timeZone);
		invTypeLicenseYear.cost = "14";
		
		//privilege inventory info
		privInventory.inventoryType = invTypeLicenseYear.inventoryType;
		privInventory.inventoryTypeStatus = OrmsConstants.ACTIVE_STATUS;
		privInventory.licenseYear = invTypeLicenseYear.licenseYear;
		privInventory.inventoryStatus = "Available";
		privInventory.allocationStatus = "Allocated";
		privInventory.agentID = storeId;
		privInventory.agentName = storeName;
		privInventory.inventoryNumFrom = "1";
		privInventory.inventoryNumTo = "3";
		privInventory.issueFromDate = invTypeLicenseYear.issueFromDate;
		privInventory.issueToDate = invTypeLicenseYear.issueToDate;
		
		//privilege inventory allocation info
		privInvAllocation.inventoryType = privInventory.inventoryType;
		privInvAllocation.inventoryTypeStatus = privInventory.inventoryTypeStatus;
		privInvAllocation.licenseYear = privInventory.licenseYear;
		privInvAllocation.vendorNum = vendorNum;
		privInvAllocation.vendorName = vendorName;
		privInvAllocation.agentID = storeId;
		privInvAllocation.agentName = storeName;
		privInvAllocation.inventoryNumFrom = privInventory.inventoryNumFrom;
		privInvAllocation.inventoryNumTo = privInventory.inventoryNumTo;
		privInvAllocation.issueFromDate = privInventory.issueFromDate;
		privInvAllocation.issueToDate = privInventory.issueToDate;
		
		//initial expected privilege inventories info
		PrivilegeInventory inventory = new PrivilegeInventory();
		inventory.inventoryType = privInventory.inventoryType;
		inventory.licenseYear = privInventory.licenseYear;
		inventory.issueFromDate = DateFunctions.formatDate(privInventory.issueFromDate, dateFormat);
		inventory.issueToDate = DateFunctions.formatDate(privInventory.issueToDate, dateFormat);
		inventory.inventoryNumber = "1";
		inventory.inventoryStatus = privInventory.inventoryStatus;
		for(int i = Integer.parseInt(privInventory.inventoryNumFrom); i <= Integer.parseInt(privInventory.inventoryNumTo); i ++) {
			inventory.inventoryNumber = String.valueOf(i);
			expectedInventories.add(inventory);
		}
	}
	
	private void checkAndCleanUp() {
		if(lm.isPrivilegeInventoryTypeExists(schema, privInventory.inventoryType)) {
			if(lm.isPrivilegeInventoryTypeLicenseYearExists(schema, privInventory.inventoryType, privInventory.licenseYear)) {
				if(lm.isPrivilegeInventoryExists(schema, privInventory.inventoryType, privInventory.licenseYear)) {
					lm.deletePrivilegeInventory(schema, privInventory.inventoryType, privInventory.licenseYear);
				}
				lm.deletePrivilegeInventoryTypeLicenseYear(schema, privInventory.inventoryType, privInventory.licenseYear);
			}
			lm.deletePrivilegeInventoryType(schema, privInventory.inventoryType);
		}
	}
	
	public void verifySearchingResult() {
		storeInvPage.verifySearchResult("Inventory Type", privInventory.inventoryType);
		storeInvPage.verifySearchResult("License Year", privInventory.licenseYear);
		storeInvPage.verifySearchResult("Issue From", DateFunctions.formatDate(privInventory.issueFromDate, dateFormat));
		storeInvPage.verifySearchResult("Issue To", DateFunctions.formatDate(privInventory.issueToDate, dateFormat));
		storeInvPage.verifySearchResult("Inventory Status", privInventory.inventoryStatus);
	}
}
