package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify allocate privilege inventory
 * @Preconditions:
 * @SPEC:Allocate Privilege Inventory.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 20, 2012
 */
public class AllocatePrivilegeInventory extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private PrivilegeInventory privilegeInventory = new PrivilegeInventory();
	private PrivilegeInventory searchPriInventory = new PrivilegeInventory();
	private PrivilegeInventoryAllocation priInventoryAllocation = new PrivilegeInventoryAllocation();
	private PriInventoryItemInfo priInventoryItem1 = new PriInventoryItemInfo();
	private PriInventoryItemInfo priInventoryItem2 = new PriInventoryItemInfo();

	@Override
	public void execute() {
		//clear up from DB for inventory type license year
		lm.deletePriInventoryFromDB(schema, invTypeLicenseYear);
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		//check and clear up for inventory, just add once when changed schema
		List<String[]> inventoryTypeInfo = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType);
		if(inventoryTypeInfo.size()<1){
			//add privilege inventory type
			lm.addPrivilegeInventoryTypeInfo(priInventoryType);
		}
		//add privilege inventory type license year
		lm.gotoInventoryTypeLicenseYearPageFromInventoryTypePage();
		lm.addInvTypeLicenseYear(invTypeLicenseYear);
		
		//add privilege inventory type
		lm.gotoPrivilegeInventoryPageFromInventoryTypePage();
		lm.addPrivilegeInventory(privilegeInventory);
		//allocate privilege inventory type
		lm.allocatePrivilegeInventory(priInventoryAllocation);
		
		//search and verify
		searchPriInventory.allocationStatus = "Allocated";
		lm.searchPrivilegeInventory(searchPriInventory);
		this.verifyInventoryInfoFromUI(priInventoryItem1);
		
		//search and verify
		searchPriInventory.allocationStatus = "Unallocated";
		lm.searchPrivilegeInventory(searchPriInventory);
		this.verifyInventoryInfoFromUI(priInventoryItem2);
		
		//search and verify
		searchPriInventory.allocationStatus = "";
		searchPriInventory.agentID = priInventoryAllocation.agentID;
		searchPriInventory.agentName = priInventoryAllocation.agentName;
		lm.searchPrivilegeInventory(searchPriInventory);
		this.verifyInventoryInfoFromUI(priInventoryItem1);
		
		//clear up from DB for inventory type license year
		lm.deletePriInventoryFromDB(schema, invTypeLicenseYear);
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		priInventoryType = "For Pri Inv Allocate";
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		
		privilegeInventory.inventoryType = invTypeLicenseYear.inventoryType;
		privilegeInventory.inventoryTypeStatus = "Active";
		privilegeInventory.licenseYear = invTypeLicenseYear.licenseYear;
		privilegeInventory.inventoryNumFrom = "001";
		privilegeInventory.inventoryNumTo = "002";
		
		priInventoryAllocation.inventoryType = privilegeInventory.inventoryType;
		priInventoryAllocation.inventoryTypeStatus = privilegeInventory.inventoryTypeStatus;
		priInventoryAllocation.licenseYear = privilegeInventory.licenseYear;
		priInventoryAllocation.vendorNum = "Auto555";
		priInventoryAllocation.vendorName = "Auto Vendor";
		priInventoryAllocation.agentName = "WAL-MART";
		priInventoryAllocation.agentID = lm.getAgentID(schema, priInventoryAllocation.agentName);
		priInventoryAllocation.inventoryNumFrom = "001";
		priInventoryAllocation.inventoryNumTo = "001";	
		
		priInventoryItem1.inventoryNum = "001";
		priInventoryItem1.status = "Available";
		priInventoryItem1.agentInfo = priInventoryAllocation.agentID + " - " + priInventoryAllocation.agentName;
		
		priInventoryItem2.inventoryNum = "002";
		priInventoryItem2.status = "Available";
		
		searchPriInventory.inventoryType = privilegeInventory.inventoryType;
		searchPriInventory.inventoryTypeStatus = privilegeInventory.inventoryTypeStatus;
		searchPriInventory.licenseYear = privilegeInventory.licenseYear;		
	}
	
	private void verifyInventoryInfoFromUI(PriInventoryItemInfo expInventoryInfo){
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage.getInstance();
		boolean result = true;
		logger.info("Verify inventory info.");
		
		int actualItemCount = privilegeInventoryPg.getPriInventoryItemRecordsCount();
		if(actualItemCount !=1){
			result &= false;
			MiscFunctions.compareResult("Privilege inventory item count is not correct.", 1, actualItemCount);
		}
		
		result &= privilegeInventoryPg.verifyPrivilegeInventoryInfo(expInventoryInfo);
		
		if(!result){
			throw new ErrorOnPageException("Inventory list info is not correct, please check error log.");
		}else {
			logger.info("Inventory list info is corret from UI.");
		}		
	}
}