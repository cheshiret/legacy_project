package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.AddNewUsersFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Add new user in Admin Manager. If the username exists, only check the user's status.
 * 
 * @author Lesley Wang
 * @Date  Jan 5, 2013
 */
public class AddNewUsers extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private AdminUserInfo userInfo = new AdminUserInfo();
	private AddNewUsersFunction func = new AddNewUsersFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = userInfo;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		userInfo.userName = datasFromDB.get("userName");
		userInfo.password = datasFromDB.get("password");
		if (userInfo.password.isEmpty()) {
			userInfo.password = "@ut0l2e4";
		}
		userInfo.confirmPassword = userInfo.password;
		userInfo.activeStatus = Boolean.valueOf(datasFromDB.get("isActive"));
		userInfo.firstName = datasFromDB.get("firstName");
		userInfo.lastName = datasFromDB.get("lastName");
		userInfo.middleName = datasFromDB.get("middleName");
		userInfo.email = datasFromDB.get("email");
		userInfo.phone = datasFromDB.get("phone");
		userInfo.fax = datasFromDB.get("fax");
		userInfo.address = datasFromDB.get("address");
		userInfo.province = datasFromDB.get("province");
		userInfo.zip = datasFromDB.get("zip");
		userInfo.locale = datasFromDB.get("locale");
		userInfo.comment = datasFromDB.get("comment");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_add_new_users";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
