package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationType;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddAllocationTypeAndLicYearFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Allocation type and allocation type license year
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Jan 29, 2014
 */
public class AddAllocationTypeAndLicYear extends SetupCase {
	private Object[] args = new Object[5];
	private AddAllocationTypeAndLicYearFunction func = new AddAllocationTypeAndLicYearFunction();
	private Data<AllocationTypeLicenseYear> allocTypeLY;
	private Data<AllocationType> allocType;
	private LoginInfo login = new LoginInfo();
	private int numOfYears = 1;
	private int numOfYearsAfterCurrent = 0;
	
	@Override
	public void executeSetup() {
		args[0] = login;
		args[1] = allocType;
		args[2] = allocTypeLY;
		args[3] = numOfYearsAfterCurrent;
		args[4] = numOfYears;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		String schema = DataBaseFunctions.getSchemaName(login.contract.replace("Contract", "").trim(), env);
		
		// Allocation Type info
		String allocationType = datasFromDB.get("allocationType");
		allocType = new Data<AllocationType>();
		AllocationType.AllocationType.setValue(allocType, allocationType);
		AllocationType.Code.setValue(allocType, datasFromDB.get("code"));
		AllocationType.Species.setValue(allocType, datasFromDB.get("species"));
		
		// Allocation Type License Year info
		allocTypeLY = new Data<AllocationTypeLicenseYear>();
		AllocationTypeLicenseYear.AllocationType.setValue(allocTypeLY, allocationType);
		AllocationTypeLicenseYear.AllocationCode.setValue(allocTypeLY, datasFromDB.get("code"));
		
		String licYear = LicenseManager.getInstance().getFiscalYear(schema);
		AllocationTypeLicenseYear.LicenseYear.setValue(allocTypeLY, licYear);
		
		AllocationTypeLicenseYear.TagQty.setValue(allocTypeLY, datasFromDB.get("tagQty"));
		AllocationTypeLicenseYear.TotalQuota.setValue(allocTypeLY, datasFromDB.get("totalQuota"));
		AllocationTypeLicenseYear.location.setValue(allocTypeLY, datasFromDB.get("huntlocation"));
		
		String num = datasFromDB.get("numOfYearsAfterCurrent");
		if (StringUtil.notEmpty(num)) {
			numOfYearsAfterCurrent = Integer.parseInt(num); //The start license year=license year + numOfYearsAfterCurrent 
		}
		
		num = datasFromDB.get("numOfYears");
		if (StringUtil.notEmpty(num)) {
			numOfYears = Integer.parseInt(num); // the number of added license years
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_allo_type_licyear";
		ids = "";
	}
}
