/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 21, 2012
 */
public class AddRAFeeScheduleFunction extends FunctionCase {
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrFeeMainPage fnmFeeMainPg = FinMgrFeeMainPage.getInstance();
	private FinMgrRaFeeSchMainPage fnmRaFeeSchMainPg = FinMgrRaFeeSchMainPage.getInstance();
	private FinMgrRaFeeSchMainPage fnmRaFeeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	private LoginInfo login = new LoginInfo();
	private RaFeeScheduleData ra = new RaFeeScheduleData();
	
	public void wrapParameters(Object[] param) {
		ra = (RaFeeScheduleData)param[1];
		
		login = (LoginInfo)param[0];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

	public void execute() {
		if (!login.contract.equals(previousContract) && loggedIn && isBrowserOpened) {
			fnm.switchToContract(login.contract, login.location);
		} 
		
		if (!loggedIn || !isBrowserOpened) { // Logged in
			fnm.loginFinanceManager(login);
			loggedIn = true;
		}
		previousContract = login.contract;
		
		if(!homePage.exists()){
			fnm.gotoHomePage();
		}
		
		fnm.gotoFeeMainPage();
		fnmFeeMainPg.clickRaFeeSchedule();
		fnmRaFeeSchMainPg.waitLoading();

		// Add new RA fee schedule
		ra.feeId = fnm.addNewRaFeeSchedule(ra);
		newAddValue = ra.feeId;
		// Activate RA Fee Schedule
		fnmRaFeeMainPg.changeScheduleStatus(ra.feeId, "Active");

		// get current fee schedule's status
		if (StringUtil.isEmpty(ra.feeId) || !fnmRaFeeMainPg.isFeeScheduleActive(ra.feeId)) {
			throw new ErrorOnPageException("Failed to add new ra fee schedule.");
		}
	}
}
