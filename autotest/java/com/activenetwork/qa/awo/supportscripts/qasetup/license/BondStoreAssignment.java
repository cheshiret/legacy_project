package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.BondStoreAssignmentFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class BondStoreAssignment extends SetupCase{
	private LoginInfo login = new LoginInfo();
	
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private String vendorNum = "";
	private String agentName = "";
	private BondStoreAssignmentFunction bondStoreAssFunc = new BondStoreAssignmentFunction();
	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = vendorNum;
		args[2] = agentName;
		args[3] = bondInfo;
		bondStoreAssFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		vendorNum = datasFromDB.get("vendorNum");
		agentName = datasFromDB.get("agentName");
		
		bondInfo.bondNum = datasFromDB.get("bondNum");
		if(StringUtil.isEmpty(bondInfo.bondNum)){
			bondInfo.bondNum = DateFunctions.getCurrentTime()+"";
		}
		bondInfo.issuer = datasFromDB.get("bondIssuer");
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_bond_store_assi";
	}
}
