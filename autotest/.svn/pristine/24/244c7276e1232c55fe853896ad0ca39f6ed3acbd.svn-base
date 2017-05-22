package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddRecipientScheduleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddRecipientSchedule extends SetupCase{
	private ScheduleData schedule = new ScheduleData();
	private LoginInfo login = new LoginInfo();
	private AddRecipientScheduleFunction addRecipientFunc = new AddRecipientScheduleFunction();

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = schedule;
		addRecipientFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		if(!login.contract.contains("Contract")){
			login.contract += " Contract";
		}
		login.location=datasFromDB.get("roleLocation");
		schedule.coverage = datasFromDB.get("coverage");
		schedule.revenueLocation = datasFromDB.get("revenueLocation");
		schedule.revenueLocationCategory = datasFromDB.get("revenueLocationCategory");

		schedule.recipientType = datasFromDB.get("recipientType");
		schedule.recipient = datasFromDB.get("recipient");
		schedule.recipientLocationCategory = datasFromDB.get("recipientLocationCategory");
		//if recipient permit is not all, please set 'Recipient' for permit column of data pool
		schedule.recipientPermit = datasFromDB.get("recipientPermit");
		schedule.productCategory = datasFromDB.get("productCategory");
		if(schedule.productCategory.equalsIgnoreCase("Lottery")){
			schedule.appPrdCategory = datasFromDB.get("appProductCategory");
		}else{
			schedule.productGroup = datasFromDB.get("productGroup");
		}
		schedule.product = datasFromDB.get("product");
		schedule.effectiveDate = datasFromDB.get("effectiveDate");

		if (!StringUtil.notEmpty(schedule.effectiveDate)) {
			schedule.effectiveDate = DateFunctions.getDateAfterToday(-1);
		}

		schedule.distributionType = datasFromDB.get("distributionType");
		schedule.tranType = datasFromDB.get("transactionType");
		schedule.tranOccur = datasFromDB.get("transactionOccurrence");
		schedule.salesChannel = datasFromDB.get("salesChannel");

		if(schedule.productCategory.equalsIgnoreCase("Ticket")){
			schedule.ticketCategory = datasFromDB.get("salesCategory");
			schedule.appRate = datasFromDB.get("appRate");
			schedule.unit = datasFromDB.get("unit");
			if(schedule.unit.equalsIgnoreCase("Flat")){
				schedule.ticketType = datasFromDB.get("ticketType");
			}
		}else {
			schedule.unit = datasFromDB.get("unit");
		}

		if(schedule.productCategory.equalsIgnoreCase("Slip")) {
			schedule.marinaRateType = datasFromDB.get("MARINARATETYPE");
		}
		schedule.rate = datasFromDB.get("rate");
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_recipient_schedule";
	    queryDataSql = "";
	}
}
