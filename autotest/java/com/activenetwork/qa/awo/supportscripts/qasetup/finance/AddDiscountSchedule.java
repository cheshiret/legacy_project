package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddDiscountScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddDiscountSchedule extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	private AddDiscountScheduleFunction addDisScheFunc = new AddDiscountScheduleFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = schedule;
		addDisScheFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");

		// discount schedule data
		schedule.discountName = datasFromDB.get("discountName");
		schedule.location = datasFromDB.get("schLocation");
		schedule.locationCategory = datasFromDB.get("locationCategory");
		schedule.feeType = datasFromDB.get("feeType");
		schedule.productCategory = datasFromDB.get("productCategory");
		schedule.loop = datasFromDB.get("loops");
		schedule.product = datasFromDB.get("product");
		schedule.productGroup = datasFromDB.get("productGroup");
		// if date is null, set the date as default value,ignore past date,as Sophia's data need setup for specific date
		schedule.effectiveDate = datasFromDB.get("effectiveDate");
		if(StringUtil.isEmpty(schedule.effectiveDate)){
			schedule.effectiveDate = DateFunctions.getDateAfterToday(-5);
		}

		if (!"Transaction Fee".equals(schedule.feeType)) {
			schedule.startDate = datasFromDB.get("startDate");
			if(StringUtil.isEmpty(schedule.startDate)){
				schedule.startDate = DateFunctions.getDateAfterToday(-365);
			}
			schedule.endDate = datasFromDB.get("endDate");
			if(StringUtil.isEmpty(schedule.endDate)){
				schedule.endDate = DateFunctions.getDateAfterToday(365);
			}
		}
		schedule.marinaRateType = datasFromDB.get("MARINA_RATE_TYPE");
		schedule.rate = datasFromDB.get("rate");
		schedule.monRate = datasFromDB.get("monRate");
		schedule.tueRate = datasFromDB.get("tueRate");
		schedule.wedRate = datasFromDB.get("wedRate");
		schedule.thuRate = datasFromDB.get("thuRate");
		schedule.friRate = datasFromDB.get("friRate");
		schedule.satRate = datasFromDB.get("satRate");
		schedule.sunRate = datasFromDB.get("sunRate");
		schedule.salesChannel = datasFromDB.get("salesChannel");
		schedule.customerType = datasFromDB.get("customerType");
		schedule.custPass = datasFromDB.get("custPass");
		schedule.member = datasFromDB.get("member");
		schedule.season = datasFromDB.get("season");
		schedule.state = datasFromDB.get("state");
		schedule.tranType = datasFromDB.get("tranType");
		schedule.tranOccur = datasFromDB.get("tranOccurrence");
		schedule.accountCode = datasFromDB.get("accountCode");
		schedule.boatCategory = datasFromDB.get("BoatCategory");
		
		//Below flow were added for 'Buy X Get Y Discount'
		schedule.disctAppliedTo = datasFromDB.get("appliedTo");
		if(StringUtil.notEmpty(datasFromDB.get("AppliedMon")))//default value:true
			schedule.disctAppliedMon = Boolean.parseBoolean(datasFromDB.get("AppliedMon"));
		if(StringUtil.notEmpty(datasFromDB.get("AppliedTue")))
			schedule.disctAppliedTue = Boolean.parseBoolean(datasFromDB.get("AppliedTue"));
		if(StringUtil.notEmpty(datasFromDB.get("AppliedWed")))
			schedule.disctAppliedWed = Boolean.parseBoolean(datasFromDB.get("AppliedWed"));
		if(StringUtil.notEmpty(datasFromDB.get("AppliedThu")))
			schedule.disctAppliedThu = Boolean.parseBoolean(datasFromDB.get("AppliedThu"));
		if(StringUtil.notEmpty(datasFromDB.get("AppliedFri")))
			schedule.disctAppliedFri = Boolean.parseBoolean(datasFromDB.get("AppliedFri"));
		if(StringUtil.notEmpty(datasFromDB.get("AppliedSat")))
			schedule.disctAppliedSat = Boolean.parseBoolean(datasFromDB.get("AppliedSat"));
		if(StringUtil.notEmpty(datasFromDB.get("AppliedSun")))
			schedule.disctAppliedSun = Boolean.parseBoolean(datasFromDB.get("AppliedSun"));
		if(StringUtil.notEmpty(datasFromDB.get("buyX")))
			schedule.disctBuyX = datasFromDB.get("buyX");
		if(StringUtil.notEmpty(datasFromDB.get("getYDisct")))
			schedule.disctGetY = datasFromDB.get("getYDisct");
		if(StringUtil.notEmpty(datasFromDB.get("maxDisctUnits")))
			schedule.maxDisctUnits = datasFromDB.get("maxDisctUnits");
		if(StringUtil.notEmpty(datasFromDB.get("calMethod")))
			schedule.disctCalMethod = datasFromDB.get("calMethod");
		if(StringUtil.notEmpty(datasFromDB.get("MIN_UNIT_OF_STAY")))
				schedule.minimumUnitOfStay = datasFromDB.get("MIN_UNIT_OF_STAY");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_add_disct_schedule";
		ids = "select ID from " + dataTableName + " where ID in (5870,5880)";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}
}
