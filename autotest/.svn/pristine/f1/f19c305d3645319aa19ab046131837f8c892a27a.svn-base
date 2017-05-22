/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
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
public class AddFeePenaltyFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private FeePenaltyData fp = new FeePenaltyData();
	private FinMgrFeePenaltyMainPage finPenaltyMainPg = FinMgrFeePenaltyMainPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	
	public void wrapParameters(Object[] param) {
		fp = (FeePenaltyData)param[1];
		
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
		
		// Go to fee penalty page
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();

		// Add Fee penalty
		fp.id = fnm.addNewFeePenalty(fp);
		newAddValue = fp.id;
		// Activate Fee penalty
		finPenaltyMainPg.changeScheduleStatus(fp.id, OrmsConstants.ACTIVE_STATUS);
		if (StringUtil.isEmpty(fp.id) || !finPenaltyMainPg.isFeePenaltyActive(fp.id)) {
			throw new ErrorOnPageException("Record was not added successfully");
		}
	}
}
