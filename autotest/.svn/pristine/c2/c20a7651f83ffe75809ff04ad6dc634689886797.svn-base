package com.activenetwork.qa.awo.supportscripts.qasetup.db;

import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.SetupSplitStaySiteFunction;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: setup split stay for site
 * @Preconditions: No
 * @SPEC: No
 * @Task#:
 * 
 * @author swang5
 * @Date  May 13, 2011
 */
public class SetupSplitStaySite extends SetupCase {

	private String schema = "";
	private String locId = "";
	private String prdId = "";
	private String splitGroupNum = "";
	private SetupSplitStaySiteFunction func = new SetupSplitStaySiteFunction();

	public void wrapParameters(Object[] param) {
		queryDataSql = "";//override this to run what you want to execute

		dataTableName = "d_setup_splitstay";
		env = TestProperty.getProperty("target_env");
		ids="";
	}

	@Override
	public void executeSetup() {
		func.execute(schema,locId,prdId,splitGroupNum);
		
	}

	@Override
	public void readDataFromDatabase() {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+datasFromDB.get("contract");
		locId = datasFromDB.get("locId");
		prdId = datasFromDB.get("prdId");
		splitGroupNum = datasFromDB.get("SPLITGROUPNUMBER");
		
	}
}
