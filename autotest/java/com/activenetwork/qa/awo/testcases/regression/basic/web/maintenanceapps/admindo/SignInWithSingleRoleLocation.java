/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.admindo;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.AdminDoHomePage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppUserPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.web.AdminDoTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Sign in Admin.do with a valid account assigned only one role/location authorized to PublicWebSuppAPP and Admin.do
 * @Preconditions:
 * Make sure the account "qa-auto-pt-one" has one role/location authorized to PublicWebSuppAPP and "Admin.do".
 * 1. "PublicWebSupport Admin/RA Contract", role "PublicWebSupport Admin" has "PublicWebSuppAPP" application and "Admin.do" feature

 * @SPEC: 
 * Sign in with a valid account assigned only one role authorized to PublicWebSuppAPP and Admin.do [TC:016570]
 * Sign out when a valid account already signed in Admin.do [TC:016566]
 * @Task#: Auto-1402
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2013
 */
public class SignInWithSingleRoleLocation extends AdminDoTestCase {

	private WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();

	public void execute() {
		//Login in with single role/location
		ma.invokeURL(url);
		ma.signIn(login.userName, login.password);

		//Verify user name and role/location value
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(false);
		
		//Verify Admin.do page is shown after sign in 
		this.verifyAdminDoHomePageExist();
		
		//Logout
		ma.logOutFromAdminDo();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.one.user");
		login.password = TestProperty.getProperty("orms.pt.one.pw");
		login.role = "PublicWebSupport Admin";
		login.location = "RA Contract";
	}
	
	/**
	 * Verify Admin.do Page exist after sign in successfully
	 */
	public void verifyAdminDoHomePageExist() {
		if (!AdminDoHomePage.getInstance().exists()) {
			throw new ErrorOnPageException("The Admin.do home Page should exist after sign in!");
		}
		logger.info("The Admin.do home Page is shown correctly after sign in!");
	}

}
