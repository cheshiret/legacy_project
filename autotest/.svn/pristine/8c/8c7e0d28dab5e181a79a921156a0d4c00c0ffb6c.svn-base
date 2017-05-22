/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.marketingspotmgr;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSelectRoleLocPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.MarketingSpotManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Check the error messages when sign in marketing spot manager with a valid account assigned:
 * 1. only one role authorized to PublicWebSuppAPP but not to Marketing Spot Manager
 * 2. multiple roles authorized to PublicWebSuppAPP and at least 1 to Marketing Spot Manager) and select the invalid role/location
 * @Preconditions:
 * 1. make sure the account qa-auto-pt-nopt has one role with PublicWebSuppAPP but no Marketing Spot manager in RA contract
 * 2. make sure the account qa-auto-pt-mul has three roles with PublicWebSuppAPP but only 1 to Marketing Spot manager in RA contract
 * @SPEC:
 * Sign in with a valid account assigned only one role authorized to PublicWebSuppAPP but not to Marketing Spot Manager [TC:016591]
 * 	Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 to Marketing Spot Manager) [TC:016568]
 * @Task#: Auto-1400
 * 
 * @author Lesley Wang
 * @Date  Dec 17, 2012
 */
public class VerifyInvalidRoleLocErrMessages extends
		MarketingSpotManagerTestCase {

	private String userWithoutSpotMgr, errMsg_NoMarketSpotRole; 
	private WebMaintenanceAppSelectRoleLocPage selectLocPg = 	WebMaintenanceAppSelectRoleLocPage.getInstance();

	public void execute() {
		spotMgr.invokeURL(url);
		
		// Checkpoint 1: login with the account assigned one role authorized to PublicWebSuppAPP but no Marketing Spot Manager , check the error message
		spotMgr.signIn(userWithoutSpotMgr, login.password);
		boolean result = selectLocPg.compareTopErrMessage(errMsg_NoMarketSpotRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		
		selectLocPg.clickContinueButton();
		result &= selectLocPg.compareTopErrMessage(errMsg_NoMarketSpotRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		spotMgr.logOut();
		
		// Checkpoint 2: login with the account assigned multiple roles authorized to PublicWebSuppAPP and select the role without Spot Manager , check the error message
		spotMgr.signIn(login);
		result = selectLocPg.compareTopErrMessage(errMsg_NoMarketSpotRole);
		result &= selectLocPg.compareFieldErrMsgExist(false);
		spotMgr.logOut();
		
		// Checkpoint 3: login with the account assigned multiple roles authorized to PublicWebSuppAPP but don't select any role/location , check the error message
		spotMgr.signIn(login.userName, login.password);
		selectLocPg.clickContinueButton();
		result &= selectLocPg.compareTopErrMessage(selectLocPg.errMsg_EmptyRole);
		result &= selectLocPg.compareFieldErrMessage(selectLocPg.roleFieldLabel, selectLocPg.roleFieldErrMsg);
		spotMgr.logOut();

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
		userWithoutSpotMgr = TestProperty.getProperty("orms.pt.nopt.user");
		
		errMsg_NoMarketSpotRole = "The selected account-role/location combination is not authorized to use the Marketing Spot Manager. Please select another role/location or sign out and sign in with another account.";
	}
}
