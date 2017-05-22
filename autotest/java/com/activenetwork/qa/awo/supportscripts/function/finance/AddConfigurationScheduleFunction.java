/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrConfigSchedMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:Add distribution configuration schedule
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 21, 2012
 */
public class AddConfigurationScheduleFunction extends FunctionCase {
	private ScheduleData schedule = new ScheduleData();
	private FinMgrConfigSchedMainPage schdMainPg = FinMgrConfigSchedMainPage.getInstance();
	private LoginInfo login;
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();

	private boolean loggedin = false;
	private String preContract = "";

	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		schedule = (ScheduleData)param[1];

	}

	public void execute() {
		//Finance Manage can switch
		if(!preContract.equalsIgnoreCase(login.contract) && loggedin && isBrowserOpened){
			fnm.switchToContract(login.contract, login.location);
		}
		if(!loggedin|| !isBrowserOpened){
			fnm.loginFinanceManager(login);
			loggedin = true;
		}
		preContract = login.contract;
		if(!homePage.exists()){
			fnm.gotoHomePage();
		}

		//Goto configuration schedule Page
		fnm.gotoDistributionTabPage();
		fnm.gotoConfigurSchedulePage();

		//add a new configuration schedule
		schedule.scheduleId = fnm.addNewConfigSchedule(schedule);
		if(StringUtil.isEmpty(schedule.scheduleId)){
			throw new ErrorOnPageException("Failed to add new distribute configuration schedule.");
		}
		newAddValue = schedule.scheduleId;
		//change new added schedule status to active
		schdMainPg.changeScheduleStatus(schedule.scheduleId,OrmsConstants.ACTIVE_STATUS);

		// verify start
		schdMainPg.searchByScheduleId(schedule.scheduleId);
		boolean result = schdMainPg.verifyScheduleInfo(schedule);
		if(!result){
			throw new ErrorOnPageException("Record was not added successfully, please check logs above.");
		}
		// verify end
	}
}
