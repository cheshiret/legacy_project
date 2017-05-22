package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify reinstate privilege inventory
 * @Preconditions:
 * @SPEC:Reinstate Privilege Inventory.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 20, 2012
 */
public class ReinstatePrivilegeInventory extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private PrivilegeInventory privilegeInventory = new PrivilegeInventory();
	private PrivilegeInventory searchPriInventory = new PrivilegeInventory();
	private PriInventoryItemInfo priInventoryItem = new PriInventoryItemInfo();
	private PrivilegeInventoryAllocation priInventoryAllocation = new PrivilegeInventoryAllocation();

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
		lm.searchPrivilegeInventory(searchPriInventory);
		
		//withdraw privilege inventory
		lm.withdrawPrivilegeInventoryItem(priInventoryItem.inventoryNum, true);
		//reinstate inventory
		lm.reinstatePrivilegeInventoryItem(priInventoryItem.inventoryNum, priInventoryAllocation);
		
		//verify inventory info
		priInventoryItem.status = "Available";
		priInventoryItem.agentInfo = priInventoryAllocation.agentID + " - " + priInventoryAllocation.agentName;
		this.verifyInventoryInfoFromUI(priInventoryItem);
		
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
		
		priInventoryType = "For Pri Inv Reinstate";
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		
		privilegeInventory.inventoryType = invTypeLicenseYear.inventoryType;
		privilegeInventory.inventoryTypeStatus = "Active";
		privilegeInventory.licenseYear = invTypeLicenseYear.licenseYear;
		privilegeInventory.inventoryNumFrom = "001";
		privilegeInventory.inventoryNumTo = "001";
		
		searchPriInventory.inventoryType = privilegeInventory.inventoryType;
		searchPriInventory.inventoryTypeStatus = privilegeInventory.inventoryTypeStatus;
		searchPriInventory.licenseYear = privilegeInventory.licenseYear;		
		
		priInventoryAllocation.vendorNum = "Auto555";
		priInventoryAllocation.vendorName = "Auto Vendor";
		priInventoryAllocation.agentName = "WAL-MART";
		priInventoryAllocation.agentID = lm.getAgentID(schema, priInventoryAllocation.agentName);
		
		priInventoryItem.inventoryNum = "001";
	}
	
	private void verifyInventoryInfoFromUI(PriInventoryItemInfo expInventoryInfo){
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage.getInstance();
		boolean result = true;
		logger.info("Verify inventory info.");
		
		result &= privilegeInventoryPg.verifyPrivilegeInventoryInfo(expInventoryInfo);
		
		if(!result){
			throw new ErrorOnPageException("Inventory list info is not correct, please check error log.");
		}else {
			logger.info("Inventory list info is corret from UI.");
		}		
	}
}
