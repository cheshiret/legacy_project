/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSelectRoleLocPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Check the error messages when sign in photo tool with a valid account assigned:
 * 1. only one role authorized to PublicWebSuppAPP but not to Photo Tool
 * 2. multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 to Photo Tool) and select the invalid role/location
 * @Preconditions:
 * 1. make sure the account qa-auto-pt-nopt has one role with PublicWebSuppAPP but no Photo Tool in RA contract
 * 2. make sure the account qa-auto-pt-mul has three roles with PublicWebSuppAPP but only 1 to Photo Tool in RA contract
 * @SPEC:
 * Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 to Photo Tool) [TC:016593]
 * 	Sign in with a valid account assigned only one role authorized to PublicWebSuppAPP but not to Photo Tool [TC:016569]
 * @Task#: Auto-1401
 * 
 * @author Lesley Wang
 * @Date  Dec 13, 2012
 */
public class VerifyInvalidRoleLocErrMessages extends PhotoToolTestCase {

	private String userWithoutPT, errMsg_NoPTRole;
	private WebMaintenanceAppSelectRoleLocPage selectLocPg = WebMaintenanceAppSelectRoleLocPage.getInstance();

	public void execute() {
		pt.invokeURL(url);
		
		// Checkpoint 1: login with the account assigned one role authorized to PublicWebSuppAPP but no Photo Tool , check the error message
		pt.signIn(userWithoutPT, login.password);
		boolean result = selectLocPg.compareTopErrMessage(errMsg_NoPTRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		
		selectLocPg.clickContinueButton();
		result &= selectLocPg.compareTopErrMessage(errMsg_NoPTRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		pt.logOut();
		
		// Checkpoint 2: login with the account assigned multiple roles authorized to PublicWebSuppAPP and select the role without Photo Tool , check the error message
		pt.signIn(login);
		result &= selectLocPg.compareTopErrMessage(errMsg_NoPTRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		pt.logOut();
		
		// Checkpoint 3: login with the account assigned multiple roles authorized to PublicWebSuppAPP but don't select any role/location , check the error message
		pt.signIn(login.userName, login.password);
		selectLocPg.clickContinueButton();
		result &= selectLocPg.compareTopErrMessage(selectLocPg.errMsg_EmptyRole);
		result &= selectLocPg.compareFieldErrMessage(selectLocPg.roleFieldLabel, selectLocPg.roleFieldErrMsg);
		pt.logOut();
		
		if (!result) {
			throw new ErrorOnPageException("Error Messages are wrong! Check error log!");
		}
		logger.info("Verify error messages correctly!");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "Admin.do User";
		login.location = "RA Contract";
		userWithoutPT = TestProperty.getProperty("orms.pt.nopt.user");
		
		errMsg_NoPTRole = "The selected account-role/location combination is not authorized to use the Photo Tool. Please select another role/location or sign out and sign in with another account.";
	}
}
