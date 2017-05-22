package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryStoreAssignmentsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 10, 2013
 */
public class AssignLotteryToStoreFunction extends FunctionCase {
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String productCode;
	private String locationClasses[];
	private boolean isLoggedIn = false;
	private String loggedInContract, loggedInLocation;
	
	@Override
	public void execute() {
		if(isLoggedIn) {
			if(!loggedInContract.equalsIgnoreCase(login.contract)) {
				lm.logOutLicenseManager();
				isLoggedIn = false;
			} else if(loggedInLocation.equalsIgnoreCase(login.location)) {
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		
		if(!isLoggedIn) {
			lm.loginLicenseManager(login);
			isLoggedIn = true;
		}
		
		loggedInContract = login.contract;
		loggedInLocation = login.location;
		
		if(!LicenseMgrHomePage.getInstance().exists()) {
			lm.gotoHomePage();
		}
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(productCode);
		lm.gotoLotteryProductAgentAssignmentPage();
		lm.assignLotteryProductToStoresThruLocationClass(locationClasses);
		this.verifyResult();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		productCode = (String)param[1];
		locationClasses = (String[])param[2];
	}
	
	private String verifyResult(){
		LicMgrLotteryStoreAssignmentsPage assignmentsPage = LicMgrLotteryStoreAssignmentsPage.getInstance();
		
		if(!assignmentsPage.exists()){
			throw new ErrorOnPageException("Failed to assign Lottery to store: product code = "+productCode+", locationClass = " + locationClasses.toString());
		}
		String assignedLocNums = "Assigend Agent Numbers: ";
		boolean result = true;
		for (String loc : locationClasses) {
			String assignedLocNum = assignmentsPage.getNumberOfAgentsActivelyAssignedByLocationName(loc);
			String agentsNum = assignmentsPage.getNumberOfAgentsInLocationClassByLocationName(loc);
			if (!assignedLocNum.equals(agentsNum) || assignedLocNum.equals("0")) {
				logger.error("Wrong assigned location number for product code = "+productCode+", locationClass = "+loc + 
						", Expect: " + agentsNum + ", Actual: " + assignedLocNum);
				result = false;
			}
			assignedLocNums += assignedLocNum + ", ";
		}
		
		if(!result) {
			throw new ErrorOnPageException("Assigned Location Number is wrong! Check logger error!");
		}
		logger.info("Assign privlege to store successfully: product code=" + productCode + ", locationClass=" + locationClasses.toString());
		
		return assignedLocNums;
	}
}
