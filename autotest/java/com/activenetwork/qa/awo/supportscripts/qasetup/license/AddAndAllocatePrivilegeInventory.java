package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddAndAllocatePriInvFunction;
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
public class AddAndAllocatePrivilegeInventory extends SetupCase {
	private String schema = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private PrivilegeInventory privInventory = new PrivilegeInventory();
	private PrivilegeInventoryAllocation privInvAllocation = new PrivilegeInventoryAllocation();
	private Object[] args = new Object[4];
	private AddAndAllocatePriInvFunction func = new AddAndAllocatePriInvFunction();
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_allo_pri_inv";
	}

	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("Contract");
		args[1] = datasFromDB.get("Location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		
		//add privilege inventory info
		privInventory.inventoryType = datasFromDB.get("InventoryType");
		privInventory.inventoryTypeStatus = datasFromDB.get("InventoryStatus");
		if(privInventory.inventoryStatus.trim().length() < 1) {
			privInventory.inventoryStatus = OrmsConstants.ACTIVE_STATUS;
		}
		privInventory.licenseYear = datasFromDB.get("LicenseYear");
		if(privInventory.licenseYear.trim().length() < 1) {
			privInventory.licenseYear = "All";
		} else if(!privInventory.licenseYear.equals("All") && Integer.parseInt(privInventory.licenseYear) < DateFunctions.getCurrentYear()) {
			privInventory.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		}
		privInventory.inventoryNumFrom = datasFromDB.get("AddInvNumFrom");
		privInventory.inventoryNumTo = datasFromDB.get("AddInvNumTo");
		
		//privilege inventory allocation info
		privInvAllocation.inventoryType = datasFromDB.get("InventoryType");
		privInvAllocation.inventoryTypeStatus = privInventory.inventoryTypeStatus;
		privInvAllocation.licenseYear = datasFromDB.get("LicenseYear");
		if(privInvAllocation.licenseYear.trim().length() < 1) {
			privInvAllocation.licenseYear = privInventory.licenseYear;
		} else if(!privInvAllocation.licenseYear.equals("All") && Integer.parseInt(privInvAllocation.licenseYear) < DateFunctions.getCurrentYear()) {
			privInvAllocation.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		}
		privInvAllocation.vendorNum = datasFromDB.get("VendorNumber");
		privInvAllocation.vendorName = datasFromDB.get("VendorName");
		privInvAllocation.agentName = datasFromDB.get("AgentName");
		privInvAllocation.agentID = lm.getAgentID(schema, privInvAllocation.agentName);
		privInvAllocation.inventoryNumFrom = datasFromDB.get("AllocationInvNumFrom");
		if(privInvAllocation.inventoryNumFrom.trim().length() < 1) {
			privInvAllocation.inventoryNumFrom = privInventory.inventoryNumFrom;
		}
		privInvAllocation.inventoryNumTo = datasFromDB.get("AllocationInvNumTo");
		if(privInvAllocation.inventoryNumTo.trim().length() < 1) {
			privInvAllocation.inventoryNumTo = privInventory.inventoryNumTo;
		}
		
		privInventory.inventoryStatus = "Available";
		privInventory.agentID = privInvAllocation.agentID;
		privInventory.agentName = privInvAllocation.agentName;
		
		args[2] = privInventory;
		args[3] = privInvAllocation;
	}
}
