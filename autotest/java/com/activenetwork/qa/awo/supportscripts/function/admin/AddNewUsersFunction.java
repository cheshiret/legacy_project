package com.activenetwork.qa.awo.supportscripts.function.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: Add new user in Admin Manager. If the username exists, only check the user's status.
 * 
 * @author Lesley Wang
 * @Date  Jan 5, 2013
 */
public class AddNewUsersFunction extends FunctionCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo login;
	private boolean isLoggedIn = false;
	private String loggedInContract = "";
	private AdminUserInfo userInfo = new AdminUserInfo();
	private AdmMgrHomePage admHmPg = AdmMgrHomePage.getInstance();
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		userInfo = (AdminUserInfo)param[1];
	}

	@Override
	public void execute() {
		if(!loggedInContract.equalsIgnoreCase(login.contract) && isLoggedIn && isBrowserOpened) {
			am.switchToContract(login.contract, login.location);
		}
		if(!isLoggedIn || !isBrowserOpened) {
			am.loginAdminMgr(login);
			isLoggedIn = true;
		}
		loggedInContract = login.contract;
		am.searchUser("", userInfo.userName, "");
		if (!admHmPg.checkUserExist(userInfo.userName)) {
			am.addNewUser(userInfo);
			am.searchUser("", userInfo.userName, "");
		} 
		// Check the added user status
		String userStatus = admHmPg.getUserStatus();
		if (Boolean.valueOf(userStatus) != userInfo.activeStatus) {
			throw new ErrorOnPageException("The new user status is wrong!", String.valueOf(userInfo.activeStatus),
					userStatus);
		}
		logger.info("Successfully add new user: username=" + userInfo.userName + ", status=" + userStatus);
		newAddValue = userStatus;
	}
}
