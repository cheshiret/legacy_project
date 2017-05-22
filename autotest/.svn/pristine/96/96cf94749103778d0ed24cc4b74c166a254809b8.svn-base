/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.distribution.FinMgrRecipientPermitMainPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
public class AddRecipientPermitScheduleFunction extends FunctionCase {
	private ScheduleData permitschedule = new ScheduleData();
	private LoginInfo login = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
  	private FinMgrRecipientPermitMainPage recipientPermitPg = FinMgrRecipientPermitMainPage.getInstance();
	private FinMgrHomePage homePage = FinMgrHomePage.getInstance();
	private String previousContract = "";
	private boolean loggedIn = false;
	
	public void wrapParameters(Object[] param) {
		permitschedule = (ScheduleData)param[1];
		
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
		
		 //Goto recipient permit schedule Page
	    fnm.gotoDistributionTabPage();
	    fnm.gotoRecipientPermitPage();
	    //Add recipient permit schedule and active
	    permitschedule.scheduleId = fnm.addNewRecipientPermit(permitschedule);
	    newAddValue = permitschedule.scheduleId;
	    recipientPermitPg.changePermitStatus(permitschedule.scheduleId, "Active");
	    
	    if(!this.checkPermitSchedule(permitschedule.scheduleId)){
	    	throw new ErrorOnPageException("Failed to add new recipient permit schedule.");
	    }	    
	    fnm.gotoHomePage();
	}

	/**
	 * @param scheduleId
	 */
	private boolean checkPermitSchedule(String id) {
		recipientPermitPg.searchByPermitId(id);
		ScheduleData record = recipientPermitPg.getAllRecipientPermitRecord().get(0);
		
		boolean result = true;
		result &= record.activeStatus.equalsIgnoreCase("Yes");
		result &= StringUtil.isEmpty(permitschedule.revenueLocation)?true:record.revenueLocation.equalsIgnoreCase(permitschedule.revenueLocation);
		result &= StringUtil.isEmpty(permitschedule.recipientType)?true:record.recipientType.equalsIgnoreCase(permitschedule.recipientType);
		result &= StringUtil.isEmpty(permitschedule.recipient)?true:record.recipient.equalsIgnoreCase(permitschedule.recipient);
		result &= StringUtil.isEmpty(permitschedule.effectiveDate)?true:record.effectiveDate.equalsIgnoreCase(DateFunctions.formatDate(permitschedule.effectiveDate, "MM-dd-yyyy"));
		result &= StringUtil.isEmpty(permitschedule.startDate)?true:record.startDate.equalsIgnoreCase(DateFunctions.formatDate(permitschedule.startDate, "MM-dd-yyyy"));
		result &= StringUtil.isEmpty(permitschedule.endDate)?true:record.endDate.equalsIgnoreCase(DateFunctions.formatDate(permitschedule.endDate, "MM-dd-yyyy"));
		return result;		
	}
}
