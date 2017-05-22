/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Sign in Photo Tool with invalid account and check the error messages
 * @Preconditions:
 * Make sure the account qa-auto-pt-nopwsa doesn't have the application PublicWebSuppAPP assigned in RA contract
 * Make sure the account qa-auto-pt-norole doesn't have any roles in RA contract
 * @SPEC: 
 * One of the fields (User name and Password) is missing [TC:016550]
 * Sign in with invalid user name and password [TC:016551]
 * Sign in with a valid account without any roles assigned [TC:016539]
 * Sign in with a valid account without any roles authorized to PublicWebSuppAPP assigned [TC:016540]
 * @Task#: Auto-1401
 * 
 * @author Lesley Wang
 * @Date  Dec 11, 2012
 */
public class VerifyInvalidAccountErrMessages extends PhotoToolTestCase {
	private String invalidValue, userWithoutPWSA;
	private WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();

	public void execute() {
		pt.invokeURL(url);
		
		// Checkpoint 1: input valid user name but leave password blank, check the error message
		pt.signIn(login.userName, StringUtil.EMPTY);
		boolean result = signInPg.compareTopErrMessage(signInPg.topMsg_emptyValue);
		result &= signInPg.compareFieldErrMessage(signInPg.pwLabel, signInPg.fieldMsg_pw);

		// Checkpoint 2: input password but leave user name blank, check the error message
		pt.signIn(StringUtil.EMPTY, login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_emptyValue);
		result &= signInPg.compareFieldErrMessage(signInPg.userNameLabel, signInPg.fieldMsg_username);

		// Checkpoint 3: input invalid user name and invalid password, check the error message
		pt.signIn(invalidValue, invalidValue);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidValue);
		result &= signInPg.compareFieldErrMsgExist(false);

		// Checkpoint 4: input invalid user name but valid password, check the error message
		pt.signIn(invalidValue, login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidValue);
		result &= signInPg.compareFieldErrMsgExist(false);

		// Checkpoint 5: sign in with a valid account without any roles assigned
		pt.signIn(login.userName, login.password);
		result &= signInPg.compareTopErrMessage(signInPg.topMsg_invalidRole);
		result &= signInPg.compareFieldErrMsgExist(false);

		// Checkpoint 6: sign in with a valid account without any roles authorized to PublicWebSuppAPP
		pt.signIn(userWithoutPWSA, login.password);
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
