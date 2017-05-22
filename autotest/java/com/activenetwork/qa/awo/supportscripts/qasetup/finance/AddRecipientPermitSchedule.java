package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddRecipientPermitScheduleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddRecipientPermitSchedule extends SetupCase{
	private ScheduleData permitschedule = new ScheduleData();
	private LoginInfo login = new LoginInfo();
	private AddRecipientPermitScheduleFunction addRecipientPermitFunc = new AddRecipientPermitScheduleFunction();

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = permitschedule;
		addRecipientPermitFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");		
		if(!login.contract.contains("Contract")){
			login.contract += " Contract";
		}
		login.location=datasFromDB.get("roleLocation");
		permitschedule.revenueLocation = datasFromDB.get("revenueLocation");
		permitschedule.revenueLocationCategory = datasFromDB.get("revenueLocationCategory");
		permitschedule.recipientType = datasFromDB.get("recipientType");
		if(permitschedule.recipientType.equals("Location")){
			permitschedule.recipient = datasFromDB.get("recipient");
			permitschedule.recipientLocationCategory = datasFromDB.get("recipientLocationCategory");
		}
		if(permitschedule.recipientType.equals("Concessionaire")){
			permitschedule.concessionaireCode = datasFromDB.get("concessionaireCode");
		}
		
		if(StringUtil.notEmpty(datasFromDB.get("EFFDATE")))
			permitschedule.effectiveDate = datasFromDB.get("EFFDATE");
		else
			permitschedule.effectiveDate = DateFunctions.getDateAfterToday(0);
		
		if(StringUtil.notEmpty(datasFromDB.get("STARTDATE")))
			permitschedule.startDate = datasFromDB.get("STARTDATE");
		else
			permitschedule.startDate = DateFunctions.getDateAfterToday(0);
		
		if(StringUtil.notEmpty(datasFromDB.get("ENDDATE")))
			permitschedule.endDate = datasFromDB.get("ENDDATE");
		else
			permitschedule.endDate = DateFunctions.getDateAfterToday(365);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_recipt_permit_sched";
	    queryDataSql = "";
	}
}
