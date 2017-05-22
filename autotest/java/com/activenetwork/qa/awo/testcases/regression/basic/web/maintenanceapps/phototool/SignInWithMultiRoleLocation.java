package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppUserPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (p)
 * 1. Sign in Photo Tool with multiple role/location, verify user name, role/location value and has "Change role/location" link after signing
 * 2. On 'ORMS user panel', change role/location, then sign in again to check user name, role/location value and has "Change role/location" link after signing
 * @Preconditions:
 * Make sure the account "orms.pt.mul.user" have multiple role/location, two of them can sign in "Photo tool" manager
 * 1. "PhotoTool User/RA Contract", role "PhotoTool User" has "PublicWebSuppAPP" application and "PhotoTool" feature
 * 2. "PublicWebSupport Admin/RA Contract", role "PublicWebSupport Admin" has "PublicWebSuppAPP" application and "PhotoTool" feature

 * @SPEC: 
 * Sign in and sign out with a user assigned multi role/locations [TC:020500] 
 * Change role/location [TC:020501] 
 * @Task#: AUTO-1403
 * 
 * @author Sara Wang
 * @Date  Dec 13, 2012
 */
public class SignInWithMultiRoleLocation extends PhotoToolTestCase {
	private WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();

	public void execute() {
		//Login in with multiple role/location
		pt.invokeURL(url);
		pt.signIn(login);

		//Verify user name and role/location value
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);

		//Change role/location
		login.role = "PublicWebSupport Admin";
		pt.changeRoleLocationFromUserPanelToRoleLocationSelectingPage();
		pt.selectRoleLocAndContinue(login.role , login.location);

		//Verify after changing role/location, user name, role/location value and has "Change role/location" link
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(true);

		//Logout
		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";
	}
}
