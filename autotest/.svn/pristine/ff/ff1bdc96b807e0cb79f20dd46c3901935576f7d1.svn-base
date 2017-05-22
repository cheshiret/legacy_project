package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddDiscountSchedule extends SupportCase {
	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
			.getInstance();
	private FinMgrDiscountSchedulePage discountSchPg = FinMgrDiscountSchedulePage
			.getInstance();

	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	private String contract = "";
	private boolean loggedIn = false;
	private String errorMsg=null; //added by pzhu 2012/2/20

	@Override
	public void execute() {
		// Login Finance Manager
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			fin.logoutFinanceManager();
			loggedIn = false;
		}
		if ((!loggedIn) || (loggedIn && !discountPg.exists())) {
			fin.loginFinanceManager(login);
			fin.gotoDiscountPage();
			// add discount schedules for the discount just adding.
			discountPg.gotoDiscountSchPg();
			loggedIn = true;
		}

		schedule.scheduleId = fin.addNewDiscountSchedule(schedule, false);
		// activate the schedule you just add and verify status is active
		discountSchPg.changeDiscountStatus(schedule.scheduleId, "Active");
		errorMsg = discountSchPg.getErrorMsg();
		// verify current discount schedule's status and put out log information
		if (discountSchPg.isDiscountSchActive(schedule.scheduleId,
				schedule.discountName)
				&& schedule.scheduleId.length() > 0) {
			logMsg[2] = schedule.scheduleId;
			logMsg[9] = "Active";
			logMsg[10] = "Success";
		} else {
			logMsg[2] = schedule.scheduleId;
			logMsg[9] = "Inactive";
			logMsg[10] = "Fail-->>"+errorMsg;
			
		}

		// in order to run next data in data pool
		contract = login.contract;
	}

	@Override
	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("location");

		// discount schedule data
		schedule.discountName = dpIter.dpString("discountName");
		schedule.location = dpIter.dpString("schLocation");
		schedule.locationCategory = dpIter.dpString("locationCategory");
		schedule.feeType = dpIter.dpString("feeType");
		schedule.productCategory = dpIter.dpString("productCategory");
		schedule.loop = dpIter.dpString("loop");
		schedule.product = dpIter.dpString("product");
		schedule.productGroup = dpIter.dpString("productGroup");
		schedule.effectiveDate = dpIter.dpString("effectiveDate");
		schedule.startDate = dpIter.dpString("startDate");
		schedule.endDate = dpIter.dpString("endDate");
		schedule.rate = dpIter.dpString("rate");
		schedule.monRate = dpIter.dpString("monRate");
		schedule.tueRate = dpIter.dpString("tueRate");
		schedule.wedRate = dpIter.dpString("wedRate");
		schedule.thuRate = dpIter.dpString("thuRate");
		schedule.friRate = dpIter.dpString("friRate");
		schedule.satRate = dpIter.dpString("satRate");
		schedule.sunRate = dpIter.dpString("sunRate");
		schedule.salesChannel = dpIter.dpString("salesChannel");
		schedule.customerType = dpIter.dpString("customerType");
		schedule.custPass = dpIter.dpString("custPass");
		schedule.member = dpIter.dpString("member");
		schedule.season = dpIter.dpString("season");
		schedule.state = dpIter.dpString("state");
		schedule.tranType = dpIter.dpString("tranType");
		schedule.tranOccur = dpIter.dpString("tranOccurrence");
		schedule.accountCode = dpIter.dpString("accountCode");

		logMsg[0] = cursor + " ";
		logMsg[1] = "Unknown";
		logMsg[2] = "Unknown";
		logMsg[3] = schedule.location;
		logMsg[4] = schedule.feeType;
		logMsg[5] = schedule.effectiveDate;
		logMsg[6] = schedule.startDate;
		logMsg[7] = schedule.endDate;
		logMsg[8] = schedule.rate;
		logMsg[9] = "Inactive";
		logMsg[10] = "Fail due to error";
	}

	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 98; // the start point in the data pool
		endpoint = 98; // the end point in the data pool

		dataSource = casePath + "/" + caseName;

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		login.envType = "QA";

		// log information
		logMsg = new String[11];
		logMsg[0] = "cursor";
		logMsg[1] = "disName";
		logMsg[2] = "disSchId";
		logMsg[3] = "location";
		logMsg[4] = "disSchFeeType";
		logMsg[5] = "disScheffectiveDate";
		logMsg[6] = "disSchstartDate";
		logMsg[7] = "discountSchendDate";
		logMsg[8] = "disSchRate";
		logMsg[9] = "DisSchActiveOrInactive";
		logMsg[10] = "result";
	}
}
