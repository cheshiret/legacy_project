/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
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
 * @Date  Nov 22, 2012
 */
public class AddTaxScheduleFunction extends FunctionCase {
	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrTaxSchedulePage finTaxSchPg = FinMgrTaxSchedulePage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	
	public void wrapParameters(Object[] param) {
		schedule = (ScheduleData)param[1];
		
		login = (LoginInfo)param[0];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
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
		
		//goto tax main page
		fin.gotoTaxMainPage();
		
		//goto tax schedule page
        fin.gotoTaxSchedulePage();
        
		//add new tax schedule
        schedule.scheduleId = fin.addNewTaxSchedule(schedule);
        newAddValue = schedule.scheduleId;
		//Activate tax schedule
		finTaxSchPg.changeTaxScheduleStatus(schedule.scheduleId, OrmsConstants.ACTIVE_STATUS);
	    
	    //get current taxSchedule's status
	    if(StringUtil.isEmpty(schedule.scheduleId) || !finTaxSchPg.isTaxScheduleActive(schedule.scheduleId)){
	    	throw new ErrorOnPageException("Failed to add new tax schedule.");
	    }
	}
}
