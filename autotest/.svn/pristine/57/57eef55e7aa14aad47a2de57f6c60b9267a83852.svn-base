package com.activenetwork.qa.awo.supportscripts.function.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;

public class AssignUserRolesFunction extends FunctionCase{
	private LoginInfo login;
	private AdminManager am = AdminManager.getInstance();
	private AdminUserInfo userInfo = new AdminUserInfo();
	private boolean isLoggedIn = false;
	private String loggedInContract = "";
	
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
		if(!AdmMgrHomePage.getInstance().exists()) {
			am.gotoHomePage();
		}
		am.gotoUserDetailesPage(userInfo.userName);
		am.assignLocation(userInfo.location,userInfo.locLevel, true);
		am.assignRole(userInfo);
		logger.info("Assign User role/location successful: Role Name = " + userInfo.roleName + ", Location = " + userInfo.location);
		newAddValue = "passed";
	}
}

