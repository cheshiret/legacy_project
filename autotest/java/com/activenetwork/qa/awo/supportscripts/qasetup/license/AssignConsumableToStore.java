/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignConsumableToStoreFunction;

/**
 * @Description: Add consumable product to store(a vender/agent)
 * @Preconditions: Consumable product code
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Apr 16, 2012
 * Used by test cases:
 * testCases.regression.basic.orms.finance.feeSchedule.rafee.edit.EditRAFeeScheduleForPOSOrder
 */
public class AssignConsumableToStore extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private String consumableProdCode = "";
	private String locationClasses;
	private AssignConsumableToStoreFunction assConToStore = new AssignConsumableToStoreFunction();
	
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = consumableProdCode;
		args[2] = locationClasses;
		assConToStore.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_consu_to_store";
		ids="40";
	}
	
	public void readDataFromDatabase() {
		login.contract= datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		consumableProdCode = datasFromDB.get("consumablePrdCode");
		locationClasses = datasFromDB.get("locationClass");
	}
	
}
