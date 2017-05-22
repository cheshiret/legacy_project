package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AssignPriToStoreFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeProductStoreAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage.getInstance();
	private String priCode = "";
	private String locationClasses = "";
	private String[] locationClass = new String[]{};
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		priCode = (String)param[2];
		locationClass = (String[])param[3];
		for (int i = 0; i < locationClass.length; i++) {
			locationClasses += locationClass[i] + " ";
		}
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(priCode);	
		lm.assignPrivilegeToStoresThruLocationClass(locationClass);
		
		newAddValue = this.verifyResult();
	}
	
	private String verifyResult(){
		if(!privilegeStoreAssignmentsPg.exists()){
			throw new ErrorOnPageException("Failed to assign privilege to store:privilege code="+priCode+",locationClass="+locationClasses);
		}
		String assignedLocNums = "Assigend Agent Numbers: ";
		boolean result = true;
		for (String loc : locationClass) {
			String assignedLocNum = privilegeStoreAssignmentsPg.getNumberOfAgentsActivelyAssignedByLocationName(loc);
			String agentsNum = privilegeStoreAssignmentsPg.getNumberOfAgentsInLocationClassByLocationName(loc);
			if (!assignedLocNum.equals(agentsNum) || assignedLocNum.equals("0")) {
				logger.error("Wrong assigned location number for privilege code="+priCode+",locationClass="+loc + 
						", Expect: " + agentsNum + ", Actual: " + assignedLocNum);
				result = false;
			}
			assignedLocNums += assignedLocNum + ", ";
		}
		if (!result) {
			throw new ErrorOnPageException("Assigned Location Number is wrong! Check logger error!");
		}
		logger.info("Successfully assign privlege to store: privilege code=" + priCode + ", locationClass=" + locationClasses);
		return assignedLocNums;
	}
}
