/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.admindo;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.AdminDoTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Sign in Admin.do with invalid account and check the error messages
 * @Preconditions:
 * Make sure the account qa-auto-pt-nopwsa doesn't have the application PublicWebSuppAPP assigned in RA contract
 * Make sure the account qa-auto-pt-norole doesn't have any roles in RA contract
 * @SPEC:
 * One of the fields (User name and Password) is missing [TC:016562]
 * Sign in with invalid user name and password [TC:016563]
 * Sign in with a valid account without any roles assigned [TC:016564]
 * Sign in with a valid account without any roles authorized to PublicWebSuppAPP assigned [TC:016595]
 * @Task#: Auto-1402
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2013
 */
public class VerifyInvalidAccountErrMessages extends AdminDoTestCase {

	private String invalidValue, userWithoutPWSA;
	private WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();

	public void execute() {
		ma.invokeURL(url);

		// Checkpoint 1: input valid user name but leave password blank, check the error messages
		ma.signIn(login.userName, "");
		boolean result = signInPg.compareTopErrMessage(signInPg.topMsg_emptyValue);
		result &= signInPg.compareFieldErrMessage(signInPg.pwLabel, signInPg.fieldMsg_pw);

		// Checkpoint 2: input password but leave user name blank, check the error messages
		ma.signIn("", login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_emptyValue);
		result &= signInPg.compareFieldErrMessage(signInPg.userNameLabel, signInPg.fieldMsg_username);

		// Checkpoint 3: input invalid user name and invalid password, check the error message
		ma.signIn(invalidValue, invalidValue);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidValue);
		result &= signInPg.compareFieldErrMsgExist(false);

		// Checkpoint 4: input invalid user name but valid password, check the error message
		ma.signIn(invalidValue, login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidValue);
		result &= signInPg.compareFieldErrMsgExist(false);

		// Checkpoint 5: sign in with a valid account without any roles assigned
		ma.signIn(login.userName, login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidRole);
		result &= signInPg.compareFieldErrMsgExist(false);

		// Checkpoint 6: sign in with a valid account without any roles authorized to PublicWebSuppAPP
		ma.signIn(userWithoutPWSA, login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidRole);
		result &= signInPg.compareFieldErrMsgExist(false);

		if (!result) {
			throw new ErrorOnPageException("Error Messages are wrong! Check error log!");
		}
		logger.info("Verify invalid account error messages correctly!");
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.norole.user");
		login.password = TestProperty.getProperty("orms.pt.norole.pw");
		userWithoutPWSA = TestProperty.getProperty("orms.pt.nopwsa.user");

		invalidValue = "invalid";
	}
}
