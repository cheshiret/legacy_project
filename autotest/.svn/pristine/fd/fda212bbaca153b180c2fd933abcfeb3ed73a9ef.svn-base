package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * Description : Functional Test Script
 *
 * @author QA
 */
public class AddRAFeeSchedule extends SupportCase {
	/**
	 * Script Name : <b>AddRAFeeSchedule</b> Generated : <b>Feb 8, 2010 12:50:32
	 * AM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 *
	 * @since 2010/02/08
	 * @author dsui
	 */
	FinMgrTopMenuPage fnmTopMenuPg = FinMgrTopMenuPage.getInstance();
	FinanceManager fnm = FinanceManager.getInstance();
	FinMgrFeeMainPage fnmFeeMainPg = FinMgrFeeMainPage.getInstance();
	FinMgrRaFeeSchMainPage fnmRaFeeSchMainPg = FinMgrRaFeeSchMainPage.getInstance();
	FinMgrRaFeeSchMainPage fnmRaFeeMainPg = FinMgrRaFeeSchMainPage
			.getInstance();
	LoginInfo login = new LoginInfo();
	RaFeeScheduleData ra = new RaFeeScheduleData();
	String scheduleID = "";
	private String currentContract = "";
	boolean loggedIn = false;

	public void wrapParameters(Object[] param) {
		startpoint = 28; // the start point in the datapool
		endpoint = 28; // the end point in the datapool

		// Initialize login informaiton
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");

		dataSource = casePath + "/" + caseName;

		logMsg = new String[6];
		logMsg[0] = "Index";
		logMsg[1] = "Status";
		logMsg[2] = "ScheduleID";
		logMsg[3] = "LocationCategory";
		logMsg[4] = "Location";
		logMsg[5] = "Result";
	}

	public void execute() {
		// Login finance manager
		if(!currentContract.equalsIgnoreCase(login.contract) && loggedIn) {
			fnm.logoutFinanceManager();
			loggedIn = false;
		}
		if ((!loggedIn) || (loggedIn && !fnmRaFeeSchMainPg.exists())) {
			fnm.loginFinanceManager(login);
			loggedIn = true;

			fnm.gotoFeeMainPage();
			fnmFeeMainPg.clickRaFeeSchedule();
			fnmRaFeeSchMainPg.waitLoading();
		}

		// Add new RA fee schedule
		scheduleID = fnm.addNewRaFeeSchedule(ra);

		// Activate RA Fee Schedule
		fnmRaFeeMainPg.changeScheduleStatus(scheduleID, "Active");

		// get current fee schedule's status
		if (fnmRaFeeMainPg.isFeeScheduleActive(scheduleID) && scheduleID.length() > 0) {
			logMsg[1] = scheduleID;
			logMsg[2] = "Active";
			logMsg[5] = "Success";
		} else {
			logMsg[1] = scheduleID;
			logMsg[2] = "Inactive";
			logMsg[5] = "Fail";
		}

		currentContract = login.contract;
	}

	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("role");
		ra.locationCategory = dpIter.dpString("LocationCategory");
		ra.location = dpIter.dpString("LocationName");

		ra.productCategory = dpIter.dpString("ProductCategory");

		ra.loop = dpIter.dpString("Loop");
		ra.productGroup = dpIter.dpString("ProductGroup");
		ra.product = dpIter.dpString("Product");
		ra.effectDate = dpIter.dpString("Effective Date");

		if (!StringUtil.notEmpty(ra.effectDate)) {
			ra.effectDate = DateFunctions.getDateAfterToday(-1);
		}

		ra.salesChannel = dpIter.dpString("Sales Channel");
		ra.raFeeOption = dpIter.dpString("RA Earned");
		if (StringUtil.notEmpty(ra.productCategory) && ra.productCategory.matches("Site")) //Sara[11/28/2013], some cases need this kinds of setup
			ra.reverseAndRepriceIndicator = dpIter.dpBoolean("REVERSEANDREPRICEINDICATOR");
		ra.tranType = dpIter.dpString("Transaction Type");
		ra.tranOccur = dpIter.dpString("Transaction Occurrence");
		ra.acctCode = dpIter.dpString("Account Code");
		ra.applyRate = dpIter.dpString("applyRate");
		ra.permitCategory = dpIter.dpString("PermitCategory");
		ra.permitType = dpIter.dpString("PermitType");
		ra.ticketCategory = dpIter.dpString("TicketCategory");
		ra.productFeeClass = dpIter.dpString("ProductFeeClass");
		ra.unitOption = dpIter.dpString("UnitOption");
		ra.baseRate = dpIter.dpString("BaseRate");
		ra.otherRate = dpIter.dpString("OtherRate");

		logMsg[0] = cursor + " ";
		logMsg[1] = "Inactive";
		logMsg[2] = "Unknown";
		logMsg[3] = ra.locationCategory;
		logMsg[4] = ra.location;
		logMsg[5] = "Fail due to error";

	}

}
