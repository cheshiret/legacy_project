package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AssignPOSToWarehouseFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


public class AssignPOSToWarehouse extends SetupCase{
	private LoginInfo login=new LoginInfo();
	private String[] posProducts;
	private String warehouseName;
	private AssignPOSToWarehouseFunction function = new AssignPOSToWarehouseFunction();
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;

		args[1] = warehouseName;
		args[2] = posProducts;

		function.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		warehouseName = datasFromDB.get("warehouseName");
		posProducts = datasFromDB.get("posProducts").split("\\|");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_assi_pos_to_warehs";
ids = "20";		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
	}
}
