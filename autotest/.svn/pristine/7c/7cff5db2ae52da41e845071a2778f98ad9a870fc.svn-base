/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 21, 2012
 */
public class AddRAFeeThresholdScheduleFunction extends FunctionCase {
	private FinanceManager finMgr=FinanceManager.getInstance();
	private FinMgrFeeMainPage fmFeeMainPg = FinMgrFeeMainPage.getInstance();
	private FinMgrRaFeeThresholdsSearchPage raFeeThresholdSeachPg = FinMgrRaFeeThresholdsSearchPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	private LoginInfo login=new LoginInfo();
	private ScheduleData schedule = new ScheduleData();

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		schedule = (ScheduleData)param[1];
	}

	public void execute() {
		if (!login.contract.equals(previousContract) && loggedIn && isBrowserOpened) {
			finMgr.switchToContract(login.contract, login.location);
		} 
		
		if (!loggedIn || !isBrowserOpened) { // Logged in
			finMgr.loginFinanceManager(login);
			loggedIn = true;
		}
		previousContract = login.contract;
		
		if(!homePage.exists()){
			finMgr.gotoHomePage();
		}
		
		finMgr.gotoFeeMainPage();
	    fmFeeMainPg.clickRaFeeThresholdTab();
	    raFeeThresholdSeachPg.waitLoading();

	    //Add new RA fee threshold schedule
	    schedule.scheduleId = finMgr.addNewRaFeeThresholdSchedule(schedule);
	    if(!schedule.scheduleId.matches("\\d+")){
	    	throw new ErrorOnPageException("Action failed, there is error message:"+schedule.scheduleId);
	    }
	    
	    newAddValue = schedule.scheduleId;
	    //Activate RA Fee threshold Schedule
	    raFeeThresholdSeachPg.changeThresholdSchduleStatus(schedule.scheduleId, "Active");

	    //get current schedule's status
	    if(StringUtil.isEmpty(schedule.scheduleId) || !raFeeThresholdSeachPg.isFeeScheduleActive(schedule.scheduleId)){
	    	throw new ErrorOnPageException("Failed to add new RA fee Threshold schedule.");
	    }
	}
}
