package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AssignPOSToSupplierFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This support script was used for assign POS to supplier in Inventory manager.
 * @Preconditions: Should run after AddWarehouse, AddPosProduct, AddPosSupplier.
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Sep 19, 2012
 */
public class AssignPOSToSupplier extends SetupCase {
	private LoginInfo login=new LoginInfo();
	private POSInfo posInfo;
	private String warehouseName,supplierName;
	private AssignPOSToSupplierFunction assignPosToSupplier = new AssignPOSToSupplierFunction();

	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;

		args[1] = warehouseName;
		args[2] = supplierName;
		args[3] = posInfo;
		
		assignPosToSupplier.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		// login info
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		warehouseName = datasFromDB.get("warehouseName");
		
		supplierName = datasFromDB.get("suppliername");

		posInfo = new POSInfo();
		posInfo.product = datasFromDB.get("posname");
		posInfo.unitCost = datasFromDB.get("unitcost");
		posInfo.supplierProductCode = datasFromDB.get("productcode");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_assign_postosupplier";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
