package com.activenetwork.qa.awo.supportscripts.qasetup.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.AssignFeaturesFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


public class AssignFeaturesInAdminManager extends SetupCase {
	/**
	 * @since 2010/12/02
	 * @author Sara Wang Comment: Event: 0-5 Call: 6 Voucher: 7-8 POS: 9-10
	 * 
	 */
	private LoginInfo login = new LoginInfo();
	private RoleInfo role = new RoleInfo();
	private AssignFeaturesFunction func = new AssignFeaturesFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_assign_feature";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}

	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = role;
		
		func.execute(args);
	}

	public void readDataFromDatabase() {
		
		login.contract = datasFromDB.get("contract").trim();
		login.location = datasFromDB.get("location").trim();

		role.roleName = datasFromDB.get("roleName").trim();
		role.feature = datasFromDB.get("feature").trim();
		role.application = datasFromDB.get("application").trim();
		if(StringUtil.notEmpty(datasFromDB.get("unassign"))){
			role.unAssignOrNot = Boolean.parseBoolean(datasFromDB.get("unassign"));
		}else{
			role.unAssignOrNot = false;
		}
		
	}
}
