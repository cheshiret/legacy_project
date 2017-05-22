package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import com.activenetwork.qa.awo.sql.PosDataForDB;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.db.SetupPosFunction;

public class SetupPos extends SetupCase {

	private PosDataForDB data = new PosDataForDB();
	private String contract = "";
	private SetupPosFunction func = new SetupPosFunction();

	public void wrapParameters(Object[] param) {
		queryDataSql = "";//override this to run what you want to execute
		dataTableName = "d_setup_pos";
	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = contract;
		args[1] = data;
		func.execute(args);
	}
	
	public void readDataFromDatabase(){
		contract = datasFromDB.get("contract");
		data.groupName = datasFromDB.get("groupName");
		data.productName = datasFromDB.get("productName");
		data.barCode = datasFromDB.get("barCode");
		data.loc_id = datasFromDB.get("loc_id");
		data.revenue_loc_id = datasFromDB.get("revenue_loc_id");
		data.sale_loc_id = datasFromDB.get("sale_loc_id");
	}

}
