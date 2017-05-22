/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.admindo;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSelectRoleLocPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.AdminDoTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking the UI of the sign in page and select role/location page
 * @Preconditions:
 * @SPEC:
 * Admin.do Sign in Screen [TC:016594]
 * Sign in page secured by Https [TC:019724]
 * User abandons the sign in process [TC:016561] step 2
 * Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 role to Admin.do) [TC:016596]
 * @Task#: Auto-1402
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2013
 */
public class VerifySignInAndRoleLocPages extends AdminDoTestCase {

	private WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();
	private WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();
	
	public void execute() {
		ma.invokeURL(url);
		
		// Checkpoint 1: check login in page UI
		signInPg.setUserName(login.userName);
		signInPg.setPassword(login.password);
		signInPg.verifySignInPgUI(login.userName, signInPg.headerText1, signInPg.headerText2);
		this.verifySignInPgSecuredByHttps();
		
		// Checkpoint 2: check Role/Location Selection Page UI
		ma.signInToSelectRoleLocPg();
		selectLocPg.verifyRoleLocSelectionPgUI(login.userName, true, selectLocPg.roleLocText, selectLocPg.defaultRoleLoc);
		this.verifySignInPgSecuredByHttps();
		
		// Checkpoint 3: after sign in, the page should be secured by Https too
		ma.selectRoleLocAndContinue(login.role, login.location);
		this.verifySignInPgSecuredByHttps(); //Blocked by Defect-39783
		
		ma.logOutFromAdminDo();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "Admin.do User";
		login.location = "RA Contract";
	}

	/**
	 * Verify the page url starts with https
	 */
	private void verifySignInPgSecuredByHttps() {
		String url = ma.getPageUrl();
		if (!url.startsWith("https")) {
			throw new ErrorOnPageException("The page should be secured by https. page url=" + url);
		}
		logger.info("The page is secured by https!");
	}

}
