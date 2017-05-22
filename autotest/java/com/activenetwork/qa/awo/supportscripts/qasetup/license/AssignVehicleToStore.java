/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignVehicleToStoreFunction;

/**
 * @Description: Assign agents to existed vehicle product in Vehicle detail page in License Manager. 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Apr 18, 2012
 * Used by: 
 * testCases.regression.basic.orms.finance.feeSchedule.rafee.edit.EditRAFeeScheduleForVehicleOrder
 * testCases.regression.advanced.orms.order.license.products.vehicles.tandisplay.DuplicateRegistration_Boat
 * testCases.regression.advanced.orms.order.license.products.vehicles.tandisplay.DuplicateRegistration_Dealer
 *  
 */
public class AssignVehicleToStore extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private String vehicleCode = "";
	private String locationClasses = "";
	private AssignVehicleToStoreFunction assVehToStore = new AssignVehicleToStoreFunction();
	
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = vehicleCode;
		args[2] = locationClasses;
		assVehToStore.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_vehicle_to_store";
		ids="1773,1783";
	}
	
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		vehicleCode = datasFromDB.get("vehicleCode");
		locationClasses = datasFromDB.get("locationClass");
	}
}
