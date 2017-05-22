package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppUserPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
 * Sign in Photo Tool with single role/location, verify user name, role/location value and no "Change role/location" link after signing
 * @Preconditions:
 * Make sure the account "orms.pt.one.user" only have one role/location ("PublicWebSupport Admin/RA Contract"), role "PublicWebSupport Admin" has "PublicWebSuppAPP" application and "PhotoTool" feature
 * @SPEC: 
 * Sign in and sign out with a user assigned single role/location [TC:020499] 
 * @Task#: AUTO-1403
 * 
 * @author Sara Wang
 * @Date  Dec 13, 2012
 */
public class SignInWithSingleRoleLocation extends PhotoToolTestCase {
	private WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();

	public void execute() {
		//Login in with single role/location
		pt.invokeURL(url);
		pt.signIn(login.userName, login.password);

		//Verify user name, role/location value and no "Change role/location" link
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(false);

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.one.user");
		login.password = TestProperty.getProperty("orms.pt.one.pw");
		login.role = "PublicWebSupport Admin";
		login.location = "RA Contract";
	}
}

