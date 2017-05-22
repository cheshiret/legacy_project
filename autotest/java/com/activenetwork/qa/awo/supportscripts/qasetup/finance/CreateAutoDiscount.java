package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddDiscountFunction;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddDiscountScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 *
 * @author QA
 */
public class CreateAutoDiscount extends SetupCase {
	/**
	 * Script Name : <b>CreateAutoDiscount</b> Generated : <b>Mar 8, 2010
	 * 3:45:34 AM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 *
	 * @since 2010/03/08
	 * @author Sara Wang
	 */

	private LoginInfo login = new LoginInfo();
	private DiscountData discount;

	private ScheduleData schedule;

	private AddDiscountScheduleFunction addSchedule = new AddDiscountScheduleFunction();
	private AddDiscountFunction addDiscount = new AddDiscountFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_auto_discount";
		
		queryDataSql = "";//override this to run what you want to execute

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}

	public void executeSetup() {
		
		//add discount.
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = discount;
		
		addDiscount.execute(args);
		
		Object[] args1 = new Object[2];
		args1[0] = login;
		args1[1] = schedule;
		
		addSchedule.execute(args1);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");

		this.discount = new DiscountData();
		this.schedule = new ScheduleData();
		
		// discount data
		schedule.discountName = discount.name = datasFromDB.get("discountName");
		discount.description = datasFromDB.get("description");
		discount.rateType = datasFromDB.get("rateType");

		String[] feeTypes = datasFromDB.get("feeTypes").split(",");
		discount.feeTypes.clear();
		for (int i = 0; i < feeTypes.length; i++) {
			discount.feeTypes.add(feeTypes[i]);
		}

		String[] behaviors = datasFromDB.get("behaviors").split(",");
		for (int j = 0; j < behaviors.length; j++) {
			discount.behaviors.add(behaviors[j]);
		}

		discount.rateUnit = datasFromDB.get("rateUnit");
		// discount schedule data
		schedule.location = datasFromDB.get("schLocation");
		schedule.locationCategory = datasFromDB.get("locationCategory");
		schedule.feeType = datasFromDB.get("feeType");
		schedule.productCategory = datasFromDB.get("productCategory");
		schedule.loop = datasFromDB.get("loop");
		schedule.product = datasFromDB.get("product");
		schedule.productGroup = datasFromDB.get("productGroup");

		schedule.effectiveDate = datasFromDB.get("effectiveDate");

		if (!StringUtil.notEmpty(schedule.effectiveDate)) {
			schedule.effectiveDate = DateFunctions.getDateAfterToday(-1);
		}

		schedule.startDate = datasFromDB.get("startDate");
		if (!StringUtil.notEmpty(schedule.startDate)) {
			schedule.startDate = DateFunctions.getDateAfterToday(-1);
		}

		schedule.endDate = datasFromDB.get("endDate");
		if (!StringUtil.notEmpty(schedule.endDate)) {
			schedule.endDate = DateFunctions.getDateAfterToday(365);
		}

		schedule.rate = datasFromDB.get("rate");
		schedule.salesChannel = datasFromDB.get("salesChannel");
		schedule.customerType = datasFromDB.get("customerType");
		schedule.custPass = datasFromDB.get("custPass");
		schedule.member = datasFromDB.get("member");
		schedule.season = datasFromDB.get("season");
		schedule.state = datasFromDB.get("state");
		schedule.accountCode = datasFromDB.get("accountCode");
	}
}
