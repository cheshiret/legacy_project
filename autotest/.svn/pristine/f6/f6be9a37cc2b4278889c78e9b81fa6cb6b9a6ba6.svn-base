package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTConfigurationScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddEFTConfigurationScheduleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AddEFTConfigurationSchedule extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private EFTConfigurationScheduleInfo scheduleInfo = new EFTConfigurationScheduleInfo();
	private AddEFTConfigurationScheduleFunction addEFTScheFunc = new AddEFTConfigurationScheduleFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = scheduleInfo;
		addEFTScheFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		if(!login.contract.endsWith("Contract")){
			login.contract = login.contract + " Contract";
		}
		login.location = datasFromDB.get("locationOfRole");

		scheduleInfo.location = datasFromDB.get("locationOfSch");
		scheduleInfo.effectiveDate = datasFromDB.get("effectiveDate");
		if(scheduleInfo.effectiveDate.length()<1){
			scheduleInfo.effectiveDate = DateFunctions.getToday();
		}
		scheduleInfo.paymentGrp =  datasFromDB.get("paymentGroup");
		scheduleInfo.paymentType = datasFromDB.get("paymentType");
		scheduleInfo.invoiceTransEnabled = Boolean.parseBoolean(datasFromDB.get("invoiceTransEnabled"));

		scheduleInfo.invoiceTransDate = datasFromDB.get("invoiceTransDate");
		if(scheduleInfo.invoiceTransDate.length()<1){
			scheduleInfo.invoiceTransDate = DateFunctions.getToday();
		}
		scheduleInfo.remittanceTransEnabled = Boolean.parseBoolean(datasFromDB.get("remittanceTransEnabled"));
		scheduleInfo.remittanceTransDate = datasFromDB.get("remittanceTransDate");
		if(scheduleInfo.remittanceTransDate.length()<1){
			scheduleInfo.remittanceTransDate =  DateFunctions.getToday();
		}
		scheduleInfo.includeDepositAdj = Boolean.parseBoolean(datasFromDB.get("includeDepositAdj"));
		scheduleInfo.depositAdjStore = datasFromDB.get("depositAdjStore");
		scheduleInfo.deductVendorFee =  Boolean.parseBoolean(datasFromDB.get("deductVendorFee"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_add_eft_config_schd";
	}
}
