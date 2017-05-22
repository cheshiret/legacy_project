/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AllocateFieldPOSInventoryFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author ssong
 * @Date  Jan 24, 2014
 */
public class AllocateFieldPOSInventory extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private POSInfo pos;
	private AllocateFieldPOSInventoryFunction allocateFunc = new AllocateFieldPOSInventoryFunction();
	private String wareHouseName;
	
	public void executeSetup() {
		allocateFunc.execute(login, wareHouseName, pos);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		pos = new POSInfo();
		wareHouseName = datasFromDB.get("WAREHOUSNAME");
		pos.product = datasFromDB.get("PRODUCTNAME");
		pos.serilizeNumberType = datasFromDB.get("SERIALIZATIONNUMTYPE");
		pos.revLocation.agency = datasFromDB.get("AGENCY");
		pos.revLocation.region = datasFromDB.get("REGION");
		pos.revLocation.facility = datasFromDB.get("FACILITY");
		String passNums = datasFromDB.get("PASSNUMES");
		pos.passNums = (ArrayList<String>) StringUtil.arrayToList(passNums.split(","));
		pos.serializedRangeFrom = datasFromDB.get("SERIALIZEDRANGEFROM");
		pos.serializedRangeTo = datasFromDB.get("SERIALIZEDRANGETO");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_allocate_filed_pos_inven";
		
		ids = "120,130";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
}
