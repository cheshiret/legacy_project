package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddConfigurationScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Add distribution configuration schedule
 * Original Host : WinNT Version 5.1  Build 2600 (S)
 * @Date  2010/03/10
 * @author Ssong
 */
public class AddConfigurationSchedule extends SetupCase {
	private ScheduleData schedule = new ScheduleData();
	private LoginInfo login = new LoginInfo();
	private AddConfigurationScheduleFunction addScheduleFunc = new AddConfigurationScheduleFunction();

	public void wrapParameters(Object[] param) {
		queryDataSql = "";//override this if you want to run setup for specific row,it will run all the records by default
		dataTableName = "d_fin_add_config_schedule";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = schedule;
		addScheduleFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");

		schedule.location = datasFromDB.get("location");
		schedule.paymentOrRefund = datasFromDB.get("paymentOrRefund");
		schedule.payGroup = datasFromDB.get("payGroup");
		schedule.payType = datasFromDB.get("payType");
		schedule.reconcilable = datasFromDB.get("reconcilable");
		schedule.distributable = datasFromDB.get("distributable");
		if(StringUtil.notEmpty(datasFromDB.get("EFFDATE")))
			schedule.effectiveDate = DateFunctions.formatDate(datasFromDB.get("EFFDATE"),"MM-d-yyyy");
		else
			schedule.effectiveDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1),"MM-d-yyyy");
		
		if(StringUtil.notEmpty(datasFromDB.get("DISTRIDSTARTDATE")))
			schedule.reconcilStartDate = DateFunctions.formatDate(datasFromDB.get("DISTRIDSTARTDATE"),"MM-d-yyyy");
		else
			schedule.reconcilStartDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1),"MM-d-yyyy");
		
		schedule.distributStartDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1),"MM-d-yyyy");
	}
}
