/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;


import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;



public class AddFeeScheduleFunction extends FunctionCase {


	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();

	private LoginInfo login;
	private FeeScheduleData feeSchData;
	private String feeScheduleId = "";
	private String result;
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;

	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.feeSchData = (FeeScheduleData)param[1];
	}

	public void execute() {
		if (!login.contract.equals(previousContract) && loggedIn && isBrowserOpened) {
			fin.switchToContract(login.contract, login.location);
		} 
		
		if (!loggedIn || !isBrowserOpened) { // Logged in
			fin.loginFinanceManager(login);
			loggedIn = true;
		}
		previousContract = login.contract;
		
		if(!homePage.exists()){
			fin.gotoHomePage();
		}
		
		fin.gotoFeeMainPage();

		// add use fee or attribute Fee schedule
		feeScheduleId = fin.addNewFeeSchedule(feeSchData);
		if(StringUtil.isEmpty(feeScheduleId)||!feeScheduleId.matches("\\d+")){
			result = "Fee schedule ID is blank, fee schedule is not added successfully.";
			throw new ErrorOnPageException(result);
		}
		
		// Activate RA Fee Schedule
//		if(feeSchData.activeStatus.equals(OrmsConstants.ACTIVE_STATUS)){
			finFeeMainPg.changeScheduleStatus(feeScheduleId, OrmsConstants.ACTIVE_STATUS);

			// get current fee schedule's status
			if (finFeeMainPg.isFeeScheduleActive(feeScheduleId)) {
				result = feeScheduleId+" is Active, adding successfully....";
			} else {
				result = feeScheduleId+" is InActive, adding Failed....";
				throw new ErrorOnPageException(result);
			}
//		}
			newAddValue = feeScheduleId;
	}
}
