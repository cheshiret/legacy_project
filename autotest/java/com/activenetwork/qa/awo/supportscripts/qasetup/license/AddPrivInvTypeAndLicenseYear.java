package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPriInvTypeAndLYFunction;
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
public class AddPrivInvTypeAndLicenseYear extends SetupCase {
	private InventoryTypeLicenseYear invTypeLicYear = new InventoryTypeLicenseYear();
	private boolean isAddInventoryLicenseYear = true;
	private Object[] args = new Object[4];
	private AddPriInvTypeAndLYFunction func = new AddPriInvTypeAndLYFunction();
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_priv_invtype_licyear";
	}

	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("Contract");
		args[1] = datasFromDB.get("Location");
		
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		
		invTypeLicYear.inventoryType = datasFromDB.get("InventoryType");
		isAddInventoryLicenseYear = Boolean.parseBoolean(datasFromDB.get("isAddInventoryLicenseYear"));
				
		if(isAddInventoryLicenseYear) {
			invTypeLicYear.licenseYear = datasFromDB.get("LicenseYear");
			if(invTypeLicYear.licenseYear.trim().length() < 1) {
				invTypeLicYear.licenseYear = "ALL";
			} else if(invTypeLicYear.licenseYear.trim().length() < 4){
				invTypeLicYear.licenseYear = String.valueOf(DateFunctions.getCurrentYear() + Integer.valueOf(invTypeLicYear.licenseYear.trim()));
			} else if(!invTypeLicYear.licenseYear.equals("ALL") && Integer.parseInt(invTypeLicYear.licenseYear) < DateFunctions.getCurrentYear()) {
				invTypeLicYear.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
			}
			
			invTypeLicYear.issueFromDate = datasFromDB.get("IssueFromDate");
			if(invTypeLicYear.issueFromDate.trim().length() < 1 && invTypeLicYear.licenseYear.equals("ALL")) {
				invTypeLicYear.issueFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
			}
			invTypeLicYear.issueToDate = datasFromDB.get("IssueToDate");
			invTypeLicYear.cost = datasFromDB.get("Cost");
			if (invTypeLicYear.cost.trim().isEmpty()) {
				invTypeLicYear.cost = "0";
			}
		}
		
		args[2] = invTypeLicYear;
		args[3] = isAddInventoryLicenseYear;
	}
	
}
