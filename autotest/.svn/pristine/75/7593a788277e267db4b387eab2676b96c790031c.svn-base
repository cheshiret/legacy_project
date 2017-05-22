package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.AssignUserRolesFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AssignUserRoles extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private AdminUserInfo userInfo = new AdminUserInfo();
	private AssignUserRolesFunction assignUserRolFunc = new AssignUserRolesFunction();
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = userInfo;
		assignUserRolFunc.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_assign_user_roles";
		ids = "650";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
	
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		userInfo.userName = datasFromDB.get("userName");
		userInfo.roleName = datasFromDB.get("roleName");
		userInfo.location = datasFromDB.get("assignLocation");
		userInfo.locLevel = datasFromDB.get("locLevel");
	}
}
