/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.feeData.D_GroupUseFee;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author win7_vm
 * @Date  Nov 21, 2012
 */
public class AddGroupUseFeeScheduleFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private D_GroupUseFee fd = new D_GroupUseFee();
	private FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	
	public void wrapParameters(Object[] param) {
		fd = (D_GroupUseFee)param[1];
		
		login = (LoginInfo)param[0];
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
		fd.feeSchId = fnm.addNewGroupUseFeeSchedules(fd);
		newAddValue = fd.feeSchId;
		// Activate fee schedule
		fnm.activeOrDeactiveFeeSchedule(fd.feeSchId, true);
		
		if (StringUtil.isEmpty(fd.feeSchId) || !finFeeMainPg.isFeeScheduleActive(fd.feeSchId)) {
			throw new ErrorOnPageException("Failed to add new froup use fee schedule.");
		}
	}
}
