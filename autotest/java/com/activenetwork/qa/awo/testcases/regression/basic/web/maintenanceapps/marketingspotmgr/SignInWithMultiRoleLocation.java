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
 * 1. Sign in Marketing Spot Manager with multiple role/location 
 * @Preconditions:
 * Make sure the account "orms.pt.mul.user" have multiple role/location authorized to PublicWebSuppAPP, 
 * but at least one with "Marketing Spot Manager".
 * 1. "MarketingSpotMgr User/RA Contract", role "MarketingSpotMgr User" has "PublicWebSuppAPP" application and "Marketing Spot Manager" feature
 * 2. "PublicWebSupport Admin/RA Contract", role "PublicWebSupport Admin" has "PublicWebSuppAPP" application and "Marketing Spot Manager" feature

 * @SPEC: 
 * Sign in with a valid account assigned multiple roles (3 roles authorized to PublicWebSuppAPP but only 1 to Marketing Spot Manager) [TC:016568]
 * Sign out when a valid account already signed in Marketing Spot Manager [TC:016548]
 * @Task#: Auto-1400
 * 
 * @author Lesley Wang
 * @Date  Dec 17, 2012
 */
public class SignInWithMultiRoleLocation extends MarketingSpotManagerTestCase {

	private WebMaintenanceAppUserPanel userPanel = WebMaintenanceAppUserPanel.getInstance();

	public void execute() {
		//Login in with multiple role/location
		spotMgr.invokeURL(url);
		spotMgr.signIn(login);

		//Verify user name and role/location value
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(true);
		
		//Verify Marketing Spot Summary page is shown after sign in 
		this.verifyMarketingSpotSummaryPageExist();
		
		//Change role/location
		login.role = "PublicWebSupport Admin";
		spotMgr.changeRoleLocationFromUserPanelToRoleLocationSelectingPage(); // Defect-40459
		spotMgr.selectRoleLocAndContinue(login.role , login.location);

		//Verify after changing role/location, user name, role/location value and has "Change role/location" link
		userPanel.verifyUserName(login.userName);
		userPanel.verifyRoleLocation(login.role + "/" + login.location);
		userPanel.verifyChangeRoleLocationLinkExisting(true);
				
		//Logout
		spotMgr.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
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
