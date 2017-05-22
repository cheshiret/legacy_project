package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddRAFeeThresholdScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddRAFeeThresholdSchedule extends SetupCase{
	  private LoginInfo login=new LoginInfo();
	  private ScheduleData schedule = new ScheduleData();
	  private AddRAFeeThresholdScheduleFunction addRAFeeThresholdFunc = new AddRAFeeThresholdScheduleFunction();
	  
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = schedule;
		addRAFeeThresholdFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("role");
		schedule.locationCategory=datasFromDB.get("LocationCategory");
		schedule.location=datasFromDB.get("LocationName");
		schedule.productCategory=datasFromDB.get("ProductCategory");
		schedule.loop=datasFromDB.get("Loop");
		schedule.productGroup=datasFromDB.get("Product Group");
		schedule.product=datasFromDB.get("Product");
		schedule.effectiveDate=datasFromDB.get("Effective Date");

		if (!StringUtil.notEmpty(schedule.effectiveDate)) {
			schedule.effectiveDate = DateFunctions.getDateAfterToday(-1);
		}

		schedule.salesChannel=datasFromDB.get("Sales Channel");
		schedule.productFeeClass=datasFromDB.get("Product Fee Class");
		schedule.tranType=datasFromDB.get("Transaction Type");
		schedule.tranOccur=datasFromDB.get("Transaction Occurrence");
		schedule.startCounter=datasFromDB.get("Start Counter");
		String otherRange = datasFromDB.get("OtherRange");
		if(StringUtil.notEmpty(otherRange)){
			String[] ranges = datasFromDB.get("OtherRange").split(",");
			schedule.otherRanges = new ArrayList<String>();
			for(String range:ranges){
				schedule.otherRanges.add(range);
			}
		}
	}

	public void wrapParameters(Object[] param) {
		queryDataSql = "select * from d_fin_ra_thres_sched where id=350 or id=360";//override this if you want to run setup for specific row,it will run all the records by default
		dataTableName = "d_fin_ra_thres_sched";
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		
		ids = "";
	}
}
