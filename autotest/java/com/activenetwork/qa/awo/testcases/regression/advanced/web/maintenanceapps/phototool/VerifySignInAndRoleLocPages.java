/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSelectRoleLocPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking the UI of the sign in page and select role/location page
 * @Preconditions:
 * @SPEC:
 * Photo Tool Sign in Screen [TC:016571]
 * Sign in page secured by Https [TC:019723]
 * User abandons the sign in process [TC:016549] step 2
 * Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 to Photo Tool) [TC:016593]
 * @Task#: Auto-1401
 * 
 * @author Lesley Wang
 * @Date  Dec 13, 2012
 */
public class VerifySignInAndRoleLocPages extends PhotoToolTestCase {
	private WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();
	private WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();
	
	public void execute() {
		pt.invokeURL(url);
		
		// Checkpoint 1: check login in page UI
		signInPg.setUserName(login.userName);
		signInPg.setPassword(login.password);
		signInPg.verifySignInPgUI(login.userName, signInPg.headerText1, signInPg.headerText2);
		this.verifySignInPgSecuredByHttps(true);
		
		// Checkpoint 2: check Role/Location Selection Page UI
		pt.signInToSelectRoleLocPg();
		selectLocPg.verifyRoleLocSelectionPgUI(login.userName, true, selectLocPg.roleLocText, selectLocPg.defaultRoleLoc);
		this.verifySignInPgSecuredByHttps(true);
		
		// Checkpoint 3: after sign in, the page should be secured by Https too
		pt.selectRoleLocAndContinue(login.role, login.location);
		this.verifySignInPgSecuredByHttps(false); // Update due to the blocked Defect-39783 still in Required Inputs status
		
		pt.logOut();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";
	}
	
	/**
	 * Verify the page url starts with https
	 */
	private void verifySignInPgSecuredByHttps(boolean result) {
		String url = pt.getPageUrl();
		if (result != url.startsWith("https")) {
			throw new ErrorOnPageException("Blocked by Defect-39783. The page should be secured by https. page url=" + url);
		}
		logger.info("The page is secured by https!");
	}
}
