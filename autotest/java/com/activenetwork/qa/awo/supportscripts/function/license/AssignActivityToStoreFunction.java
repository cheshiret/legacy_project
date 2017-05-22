package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrActivityStoreAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AssignActivityToStoreFunction extends FunctionCase {
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String activityCode;
	private String locationClasses[];
	private boolean loggedIn = false;
	private String contract, location;
	
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedIn && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedIn= false;
		}
		if(login.contract.equals(contract) && loggedIn && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedIn || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedIn= true;
		}
		
		contract = login.contract;
		location = login.location;
		
		lm.gotoActivityPgFromHomePg();
		
		lm.goToActivityDetailPg(activityCode);
		lm.gotoLotteryProductAgentAssignmentPage();
		lm.assignLotteryProductToStoresThruLocationClass(locationClasses);
		this.verifyResult();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		activityCode = (String)param[1];
		locationClasses = (String[])param[2];
	}
	
	private String verifyResult(){
		LicMgrActivityStoreAssignmentPage assignmentsPage = LicMgrActivityStoreAssignmentPage.getInstance();
		
		if(!assignmentsPage.exists()){
			throw new ErrorOnPageException("Failed to assign Lottery to store: product code = "+activityCode+", locationClass = " + locationClasses.toString());
		}
		String assignedLocNums = "Assigend Agent Numbers: ";
		boolean result = true;
		for (String loc : locationClasses) {
			String assignedLocNum = assignmentsPage.getNumberOfAgentsActivelyAssignedByLocationName(loc);
			String agentsNum = assignmentsPage.getNumberOfAgentsInLocationClassByLocationName(loc);
			if (!assignedLocNum.equals(agentsNum) || assignedLocNum.equals("0")) {
				logger.error("Wrong assigned location number for product code = "+activityCode+", locationClass = "+loc + 
						", Expect: " + agentsNum + ", Actual: " + assignedLocNum);
				result = false;
			}
			assignedLocNums += assignedLocNum + ", ";
		}
		
		if(!result) {
			throw new ErrorOnPageException("Assigned Location Number is wrong! Check logger error!");
		}
		logger.info("Assign privlege to store successfully: product code=" + activityCode + ", locationClass=" + locationClasses.toString());
		
		return assignedLocNums;
	}
}
