package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.admindo;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.AdminDoHomePage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppUserPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.web.AdminDoTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1. Sign in Admin.do with multiple role/location 
 * @Preconditions:
 * Make sure the account "orms.pt.mul.user" have multiple role/location authorized to PublicWebSuppAPP, 
 * and at least two with "Admin.do" feature.
 * 1. "Admin.do/RA Contract", role "Admin.do User" has "PublicWebSuppAPP" application and "Admin.do" feature
 * 2. "PublicWebSupport Admin/RA Contract", role "PublicWebSupport Admin" has "PublicWebSuppAPP" application and "Admin.do" feature

 * @SPEC: 
 * Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 role to Admin.do) [TC:016596]
 * Sign out when a valid account already signed in Admin.do [TC:016566]
 * @Task#: Auto-1402
 *  
 * @author Lesley Wang
 * @Date  Jan 7, 2013
 */
public class SignInWithMultiRoleLocation extends AdminDoTestCase {
	private WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();

	public void execute() {
		//Login in with multiple role/location
		ma.invokeURL(url);
		ma.signIn(login);

		//Verify user name and role/location value
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(true);
		
		//Verify Admin.do home page is shown after sign in 
		this.verifyAdminDoHomePageExist();
		
		//Change role/location
		login.role = "PublicWebSupport Admin";
		ma.changeRoleLocationFromUserPanelToRoleLocationSelectingPage(); // Defect-40459
		ma.selectRoleLocAndContinue(login.role , login.location);

		//Verify user name, role/location value and "Change role/location" link after changing role/location
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(true);
		
		//Logout
		ma.logOutFromAdminDo();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "Admin.do User";
		login.location = "RA Contract";
	}
	
	/**
	 * Verify Admin.do Page exist after sign in successfully
	 */
	public void verifyAdminDoHomePageExist() {
		if (!AdminDoHomePage.getInstance().exists()) {
			throw new ErrorOnPageException("The Admin.do Page should exist after sign in!");
		}
		logger.info("The Admin.do Home Page is shown correctly after sign in!");
	}
}
