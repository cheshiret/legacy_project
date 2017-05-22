package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddTaxScheduleFunction;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description   : Functional Test Script
 * @since  2010/02/25
 * @author Sara Wang
 */
public class AddTaxSchedule extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	private AddTaxScheduleFunction addTaxFunc = new AddTaxScheduleFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_tax_schedule";
	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = schedule;
		addTaxFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("logLocation");

		schedule.location = datasFromDB.get("location");
		schedule.locationCategory = datasFromDB.get("locationCategory");
		
		schedule.taxName = datasFromDB.get("taxName");
		schedule.productCategory = datasFromDB.get("productCategory");
		schedule.feeType = datasFromDB.get("feeType");
		schedule.productGroup = datasFromDB.get("productGroup");
		schedule.product = datasFromDB.get("product");
		schedule.startDate = datasFromDB.get("startDate");
		schedule.endDate = datasFromDB.get("endDate");
		schedule.customerType = datasFromDB.get("customerType");
		schedule.accountCode = datasFromDB.get("accountCode");
		schedule.rate = datasFromDB.get("accountRate");
		schedule.tranType=datasFromDB.get("tranType");
		
		if(!StringUtil.isEmpty(schedule.productCategory) && schedule.productCategory.equalsIgnoreCase("Slip")) {
			String marinaType = datasFromDB.get("MARINARATETYPE");
			if(StringUtil.isEmpty(marinaType)){
				schedule.marinaRateType=ALL_STATUS;
			}else {
				schedule.marinaRateType=marinaType;
			}
		}
	}
}
