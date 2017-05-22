/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.db.ResetPWForVeriFoneFunction;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Nov 23, 2012
 */
public class ResetPWForVeriFone extends SetupCase {
	private String contract, regID, pw, locID;
	private ResetPWForVeriFoneFunction func = new ResetPWForVeriFoneFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = contract;
		args[1] = regID;
		args[2] = pw;
		args[3] = locID;
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		contract = datasFromDB.get("contract");
		regID = datasFromDB.get("reg_id");
		pw = datasFromDB.get("password");
		locID = datasFromDB.get("loc_id");

	}

	@Override
	public void wrapParameters(Object[] param) {
		queryDataSql = "";// override this to run what you want to execute
		dataTableName = "d_resetpw_verifone";

	}

}
