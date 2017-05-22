package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.FeeDataForPersonOrTicketType;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class AddTranFeeSchedule extends SupportCase {
	/**
	 * Script Name : <b>AddTranFeeSchedule</b> Generated : <b>Feb 23, 2010
	 * 9:34:03 PM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/23
	 * @author Sara Wang
	 */

	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();

	private LoginInfo login = new LoginInfo();
	private FeeScheduleData feeSchData = new FeeScheduleData();
	private String feeScheduleId = "";
	private boolean loggedIn = false;
	private String currentContract = "";

	public void wrapParameters(Object[] param) {
		startpoint = 145; // the start point in the data pool
		endpoint = 145; // the end point in the data pool

		dataSource = casePath + "/" + caseName;

		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.envType = "QA";
		feeSchData.feeType = "Transaction Fee";

		// log informaiton
		logMsg = new String[8];
		logMsg[0] = cursor + " ";
		logMsg[1] = "location";
		logMsg[2] = "locationCategory";
		logMsg[3] = "productCategory";
		logMsg[4] = "feeType";

		logMsg[5] = "feeScheduleId";
		logMsg[6] = "ActiveOrInactive";
		logMsg[7] = "result";
	}

	public void execute() {
		// Login Finance Manager
		if(!currentContract.equalsIgnoreCase(login.contract) && loggedIn) {
			fnm.logoutFinanceManager();
			loggedIn = false;
		}
		if ((!loggedIn) || (loggedIn && !finFeeMainPg.exists())) {
			fnm.loginFinanceManager(login);
			loggedIn = true;
			fnm.gotoFeeMainPage();
		}
		currentContract = login.contract;
		
		// add transaction fee schedule on site,pos,permit,ticket,lottery
		feeScheduleId = fnm.addNewFeeSchedule(feeSchData);

		// Activate RA Fee Schedule
		finFeeMainPg.changeScheduleStatus(feeScheduleId, "Active");

		// get current fee schedule's status
		if (finFeeMainPg.isFeeScheduleActive(feeScheduleId) && feeScheduleId.length() > 0) {
			logMsg[5] = feeScheduleId;
			logMsg[6] = "Active";
			logMsg[7] = "Success";
		} else {
			logMsg[5] = feeScheduleId;
			logMsg[6] = "Inactive";
			logMsg[7] = "Fail";
		}
	}

	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("logLocation");

		feeSchData.location = dpIter.dpString("location");
		feeSchData.locationCategory = dpIter.dpString("locationCategory");

		feeSchData.productCategory = dpIter.dpString("productCategory");

		feeSchData.loop = dpIter.dpString("loop");
		feeSchData.productGroup = dpIter.dpString("productGroup");
		// use in lottery transaction fee
		feeSchData.appProductGroup = dpIter.dpString("appProductGroup");
		feeSchData.product = dpIter.dpString("product");
		feeSchData.effectDate = dpIter.dpString("effectDate");
		feeSchData.salesChannel = dpIter.dpString("salesChannel");
		feeSchData.state = dpIter.dpString("state");
		feeSchData.tranType = dpIter.dpString("tranType");
		feeSchData.tranOccur = dpIter.dpString("tranOccur");
		feeSchData.acctCode = dpIter.dpString("acctCode");

		// use in permit transaction fee
		feeSchData.permitCategory = dpIter.dpString("permitCategory");
		feeSchData.permitType = dpIter.dpString("permitType");

		// use in ticket transaction fee
		feeSchData.ticketCategory = dpIter.dpString("ticketCategory");

		feeSchData.isEmbedInTicketFee = dpIter.dpBoolean("isEmbedInTicketFee");

		// use in lottery transaction fee
		feeSchData.applyFee = dpIter.dpString("applyFee");

		feeSchData.tranFeeOption = dpIter.dpString("tranFeeOption");

		String personTypesStr = dpIter.dpString("personType");
		feeSchData.ticketType = dpIter.dpString("ticketType");
		if (personTypesStr != null && personTypesStr.length() > 0) {
			//			 && personTypesStr.contains("|")
			String[] types = personTypesStr.split("\\|");
			feeSchData.personOrTypeData = new FeeDataForPersonOrTicketType[types.length];
			for (int i = 0; i < types.length; i++) {
				feeSchData.personOrTypeData[i] = feeSchData.new FeeDataForPersonOrTicketType();
				feeSchData.personOrTypeData[i].personOrTicketType = types[i];
				if (null != dpIter.dpString("tranFee") && dpIter.dpString("tranFee").length() > 0) {
					feeSchData.personOrTypeData[i].ticketAnyDayRate = dpIter.dpString("tranFee").split("\\|")[i];
				}
			}
		} else {
			feeSchData.tranFee = dpIter.dpString("tranFee");
		}

		logMsg[0] = cursor + " ";
		logMsg[1] = feeSchData.location;
		logMsg[2] = feeSchData.locationCategory;
		logMsg[3] = feeSchData.productCategory;
		logMsg[4] = feeSchData.feeType;

		logMsg[5] = "Unknown";
		logMsg[6] = "Inactive";
		logMsg[7] = "Fail due to error";
	}
}
