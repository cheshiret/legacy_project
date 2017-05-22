/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.web.maintenanceapps.marketingspotmgr;

import com.activenetwork.qa.awo.pages.web.maintenanceapps.MarketingSpotSummaryPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppUserPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.web.MarketingSpotManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1. Sign in Marketing Spot Manager with a valid account assigned only one role/location authorized to PublicWebSuppAPP and Marketing Spot Manager
 * @Preconditions:
 * Make sure the account "qa-auto-spot-one" has one role/location authorized to PublicWebSuppAPP and "Marketing Spot Manager".
 * 1. "MarketingSpotMgr User/RA Contract", role "MarketingSpotMgr User" has "PublicWebSuppAPP" application and "Marketing Spot Manager" feature

 * @SPEC: 
 * Sign in with a valid account assigned only one role authorized to PublicWebSuppAPP and Marketing Spot Manager [TC:016538]
 * Sign out when a valid account already signed in Marketing Spot Manager [TC:016548]
 * @Task#: Auto-1400
 * 
 * @author Lesley Wang
 * @Date  Dec 17, 2012
 */
public class SignInWithSingleRoleLocation extends MarketingSpotManagerTestCase {
	private WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();

	public void execute() {
		//Login in with single role/location
		spotMgr.invokeURL(url);
		spotMgr.signIn(login.userName, login.password);

		//Verify user name and role/location value
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(false);
		
		//Verify Marketing Spot Summary page is shown after sign in 
		this.verifyMarketingSpotSummaryPageExist();
		
		//Logout
		spotMgr.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.spot.one.user");
		login.password = TestProperty.getProperty("orms.spot.one.pw");
		login.role = "MarketingSpotMgr User";
		login.location = "RA Contract";
	}
	
	/**
	 * Verify Marketing Spot Summary Page exist after sign in successfully
	 */
	public void verifyMarketingSpotSummaryPageExist() {
		if (!MarketingSpotSummaryPage.getInstance().exists()) {
			throw new ErrorOnPageException("The Marketing Spot Summary Page should exist after sign in!");
		}
		logger.info("The Marketing Spot Summary Page is shown correctly after sign in!");
	}
}
