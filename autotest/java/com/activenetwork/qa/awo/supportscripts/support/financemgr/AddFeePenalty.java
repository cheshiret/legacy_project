package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeesTabs;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class AddFeePenalty extends SupportCase {
	/**
	 * Script Name : <b>AddFeePenalty</b> Generated : <b>Feb 3, 2010 3:52:08
	 * AM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/03
	 * @author dsui
	 */
	private LoginInfo login = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrHomePage fnmHmPg = FinMgrHomePage.getInstance();
	private FeePenaltyData fp = new FeePenaltyData();
	private FinMgrFeePenaltyMainPage finPenaltyMainPg = FinMgrFeePenaltyMainPage.getInstance();
	private FinMgrFeesTabs fnmFeeTab = FinMgrFeesTabs.getInstance();
	private FinMgrTopMenuPage fnmTopMenuPg = FinMgrTopMenuPage.getInstance();

	private boolean loggedIn = false;
	private String currentContract = "";
	private String penaltyID = "";
	private String penaltyStatus = "";
	private int count = 0;

	public void wrapParameters(Object[] param) {
		startpoint = 47; // the start point in the datapool
		endpoint = 48; // the end point in the datapool

		// Initialize login informaiton
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");

		// this datapool file name
		dataSource = casePath + "/" + caseName;

		logMsg = new String[6];
		logMsg[0] = "cusor";
		logMsg[1] = "Location";
		logMsg[2] = "LocationCategory";
		logMsg[3] = "PenaltyID";
		logMsg[4] = "PenaltyStatus";
		logMsg[5] = "Result";
	}

	public void execute() {
		if(!currentContract.equalsIgnoreCase(login.contract) && loggedIn) {
			fnm.logoutFinanceManager();
			loggedIn = false;
		}
		// Login finance manager
		if ((!loggedIn) || (loggedIn && !fnmTopMenuPg.exists())) {
			fnm.loginFinanceManager(login);
			fnmHmPg.waitLoading();
			loggedIn = true;
		}

		// Go to fee penalty page
		if (!finPenaltyMainPg.exists()) {
			if (!fnmFeeTab.isFeePenaltiesTabExist()) {
				fnm.gotoFeeMainPage();
			}
			fnm.gotoFeePenaltyPage();
		}

		// Add Fee penalty
		penaltyID = fnm.addNewFeePenalty(fp);

		// Activate Fee penalty
		finPenaltyMainPg.changeScheduleStatus(penaltyID, "Active");

		// Get penalty status
		boolean active = finPenaltyMainPg.isFeePenaltyActive(penaltyID);
		if (active) {
			penaltyStatus = "Active";
		} else {
			penaltyStatus = "Inactive";
		}

		// set log information
		logMsg[3] = penaltyID;
		logMsg[4] = penaltyStatus;
		if (!penaltyID.equalsIgnoreCase("Null")
				&& penaltyStatus.equalsIgnoreCase("Active")) {
			logMsg[5] = "Success";
		} else if (penaltyID.equalsIgnoreCase("Null")) {
			throw new ItemNotFoundException("Failed to create penalty schedule");
		} else {
			throw new ItemNotFoundException(
					"Failed to activate penalty schedule#" + penaltyID);
		}
		count++;
		if (count != 0 && count % 70 == 0) {
			browser.closeAllBrowsers();
		}
		
		currentContract = login.contract;
	}

	public void getNextData() {
		login.contract = dpIter.dpString("Contract");
		login.location = dpIter.dpString("LogLocation");

		fp.locationCategory = dpIter.dpString("Location Category").trim();
		fp.location = dpIter.dpString("Location").trim();
		fp.productCategory = dpIter.dpString("Product Category").trim();
		fp.feeType = dpIter.dpString("feeType").trim();
		fp.loop = dpIter.dpString("Loop").trim();
		fp.productGroup = dpIter.dpString("Product Group").trim();
		fp.product = dpIter.dpString("Product").trim();
		fp.effectDate = dpIter.dpString("Effective Date").trim();
		fp.startInv = dpIter.dpString("Start Date").trim();
		fp.endInv = dpIter.dpString("End Date").trim();
		fp.tranType = dpIter.dpString("Tran Type").trim();
		fp.tranOccur = dpIter.dpString("TranOccur").trim();
		fp.accountCode = dpIter.dpString("AccountCode").trim();
		fp.includeTax = dpIter.dpString("IncludeTax").trim();
		fp.units = dpIter.dpString("Units").trim();
		fp.value = dpIter.dpString("Value").trim();

		logMsg[0] = cursor + "";
		logMsg[1] = fp.location;
		logMsg[2] = fp.locationCategory;
		logMsg[3] = "Null";
		logMsg[4] = "Unknown";
		logMsg[5] = "Fail due to error";
	}
}
