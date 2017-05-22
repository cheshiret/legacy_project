package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddSlipToMarinaMapFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddSlipToMarinaMap extends SetupCase {

	private AddSlipToMarinaMapFunction addSlipToMarinaMap = new AddSlipToMarinaMapFunction();
	private LoginInfo login = new LoginInfo();
	private String facilityID;
	private String dock;
	private String[] slips;
	
	@Override
	public void executeSetup() {
		addSlipToMarinaMap.execute(login, facilityID, dock, slips);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		facilityID = datasFromDB.get("facilityid");
		dock = datasFromDB.get("dockarea");
		slips = datasFromDB.get("slips").split(",");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_slip_to_map";

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
	}

}
