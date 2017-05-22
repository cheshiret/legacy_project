/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPassInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddPosPassNumberFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author ssong
 * @Date  Jan 24, 2014
 */
public class AddPosPassNumber extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private POSPassInfo pass;
	private AddPosPassNumberFunction addPosPrd = new AddPosPassNumberFunction();
	
	public void executeSetup() {
		addPosPrd.execute(login, pass);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		
		pass = new POSPassInfo();
		pass.setWarehousName(datasFromDB.get("WAREHOUSENAME"));
		pass.setPassName(datasFromDB.get("PASSNAME"));
		pass.setPassNum(datasFromDB.get("PASSNUMBER"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_pos_passnum";
		
		ids = "10,20,30";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
