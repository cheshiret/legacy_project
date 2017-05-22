/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.admindo;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSelectRoleLocPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.AdminDoTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Check the error messages when sign in admin.do with a valid account assigned:
 * 1. only one role authorized to PublicWebSuppAPP but not to Admin.do
 * 2. multiple roles authorized to PublicWebSuppAPP and at least 1 to Admin.do but select the invalid role/location
 * @Preconditions:
 * 1. make sure the account qa-auto-pt-nopt has one role with PublicWebSuppAPP but no Admin.do in RA contract
 * 2. make sure the account qa-auto-pt-mul has many roles with PublicWebSuppAPP but only 1 to Admin.do in RA contract
 * @SPEC:
 * Sign in with a valid account assigned only one role authorized to PublicWebSuppAPP but not to Admin.do [TC:016565]
 * Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 role to Admin.do) [TC:016596]
 * @Task#: Auto-1402
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2013
 */
public class VerifyInvalidRoleLocErrMessages extends AdminDoTestCase {

	private String userWithoutAdmin, errMsg_NoAdminRole; 
	private WebMaintenanceAppSelectRoleLocPage selectLocPg = 	WebMaintenanceAppSelectRoleLocPage.getInstance();

	public void execute() {
		ma.invokeURL(url);
		
		// Checkpoint 1: login with the account assigned one role authorized to PublicWebSuppAPP but no Admin.do , check the error message
		ma.signIn(userWithoutAdmin, login.password);
		boolean result = selectLocPg.compareTopErrMessage(errMsg_NoAdminRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		
		selectLocPg.clickContinueButton();
		result &= selectLocPg.compareTopErrMessage(errMsg_NoAdminRole);
		result = selectLocPg.compareFieldErrMsgExist(false);
		ma.logOutFromAdminDo();
		
		// Checkpoint 2: login with the account assigned multiple roles authorized to PublicWebSuppAPP and select the role without Admin.do , check the error message
		ma.invokeURL(url);
		ma.signIn(login);
		result &= selectLocPg.compareTopErrMessage(errMsg_NoAdminRole);
		result = selectLocPg.compareFieldErrMsgExist(false);
		ma.logOutFromAdminDo();
		
		// Checkpoint 3: login with the account assigned multiple roles authorized to PublicWebSuppAPP but don't select any role/location , check the error message
		ma.invokeURL(url);
		ma.signIn(login.userName, login.password);
		selectLocPg.clickContinueButton();
		result &= selectLocPg.compareTopErrMessage(selectLocPg.errMsg_EmptyRole);
		result &= selectLocPg.compareFieldErrMessage(selectLocPg.roleFieldLabel, selectLocPg.roleFieldErrMsg);
		ma.logOutFromAdminDo();

		if (!result) {
			throw new ErrorOnPageException("Error Messages are wrong! Check error log!");
		}
		logger.info("Verify error messages correctly!");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";
		userWithoutAdmin = TestProperty.getProperty("orms.pt.nopt.user");
		
		errMsg_NoAdminRole = "The selected account-role/location combination is not authorized to use the Admin.do. Please select another role/location or sign out and sign in with another account.";
		
	}
}
