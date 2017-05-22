package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignAllocTypeToPrivFunction;

/**
 * @Description: Assign Allocation Type to privilege and assign the allocation privilege to other privileges
 * 
 * @author Lesley Wang
 * @Date  Mar 26, 2014
 */
public class AssignAllocTypeToPriv extends SetupCase {
	private Object[] args = new Object[4];
	private LoginInfo login = new LoginInfo();
	private String allocationType, allocPrivCode;
	private String[] privCodesWithAllocPriv;
	private AssignAllocTypeToPrivFunction func = new AssignAllocTypeToPrivFunction();
	
	@Override
	public void executeSetup() {
		args[0] = login;
		args[1] = allocPrivCode;
		args[2] = allocationType;
		args[3] = privCodesWithAllocPriv;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		allocPrivCode = datasFromDB.get("allocPrivCode");
		allocationType = datasFromDB.get("allocType");
		privCodesWithAllocPriv = datasFromDB.get("privCodesWithAllocPriv").split(";");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_allo_type";
		ids = "";
	}

}
