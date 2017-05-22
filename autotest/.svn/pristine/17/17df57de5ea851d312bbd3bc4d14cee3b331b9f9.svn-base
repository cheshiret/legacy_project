package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.OutfitterAllocation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddOutfitterAllocationsFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Allocation type and outfitter allocations
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class AddOutfitterAllocations extends SetupCase {
	private Object[] args = new Object[4];
	private Data<OutfitterAllocation> outfitterAlloc;
	private LoginInfo login = new LoginInfo();
	private AddOutfitterAllocationsFunction func = new AddOutfitterAllocationsFunction();
	private int numOfYears = 1;
	private int numOfYearsAfterCurrent = 0;
	
	@Override
	public void executeSetup() {
		args[0] = login;
		args[1] = outfitterAlloc;
		args[2] = numOfYearsAfterCurrent;
		args[3] = numOfYears;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		String schema = DataBaseFunctions.getSchemaName(login.contract.replace("Contract", "").trim(), env);
		
		// Outfitter Allocaiton Info
		outfitterAlloc = new Data<OutfitterAllocation>();
		OutfitterAllocation.AllocationType.setValue(outfitterAlloc, datasFromDB.get("allocationType"));
		OutfitterAllocation.AllocationCode.setValue(outfitterAlloc, datasFromDB.get("allocationCode"));
		OutfitterAllocation.Location.setValue(outfitterAlloc, datasFromDB.get("HUNTLOCATION"));
		
		String licYear = LicenseManager.getInstance().getFiscalYear(schema);
		OutfitterAllocation.LicenseYear.setValue(outfitterAlloc, licYear);
		
		String[] outfitters = datasFromDB.get("outfitters").split(";");
		String[] qtys = datasFromDB.get("qtys").split(";");
		if (outfitters.length != qtys.length) {
			throw new ErrorOnDataException("the number of outfitters and quantitys should be equal! outfitters=" + outfitters.length + "; quantitys=" + qtys.length);
		}
		OutfitterAllocation.Outfitters.setValue(outfitterAlloc, outfitters);
		OutfitterAllocation.Quantities.setValue(outfitterAlloc, qtys);
		
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
		dataTableName = "d_hf_add_outfitter_alloc";
		ids = "";
	}

}
