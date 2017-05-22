/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientScheduleMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
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
public class AddRecipientScheduleFunction extends FunctionCase {
	private ScheduleData schedule = new ScheduleData();
	private LoginInfo login = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private FinMgrRecipientScheduleMainPage recipientSchdMainPg = FinMgrRecipientScheduleMainPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	
	public void wrapParameters(Object[] param) {
		schedule = (ScheduleData)param[1];
		
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
		
		 //Goto recipient schedule Page
	    fnm.gotoDistributionTabPage();
	    fnm.gotoRecipientSchedulePage();
	    
	    //Add recipient schedule and active schedule
	    schedule.scheduleId = fnm.addNewRecipientSchedule(schedule);
	    newAddValue = schedule.scheduleId;
	    if(schedule.scheduleId.matches("\\d+")){
		    recipientSchdMainPg.changeScheduleStatus(schedule.scheduleId, "Active");
	    }else{
	    	throw new ErrorOnPageException("Create Recipient Schedule Failed.");
	    }
	    
	    if(!this.checkSchedule(schedule.scheduleId)) {
	    	throw new ErrorOnPageException("Activate Schedule Failed.");
    	}
		fnm.gotoHomePage();
	}
	
	/**
	 * @param scheduleId
	 */
	private boolean checkSchedule(String id) {
		recipientSchdMainPg.searchByScheduleId(id);
		ScheduleData record = recipientSchdMainPg.getAllRecordOnPage().get(0);
		
		boolean result = record.activeStatus.equalsIgnoreCase("Yes");
		return result;		
	}
}
