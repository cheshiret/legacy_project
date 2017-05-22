package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PriLandownerAttr;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPriLandownerFunction;
import com.activenetwork.qa.testapi.datacollection.Data;

public class AddPriLandowner extends SetupCase {
//	private String schema = "";
	private Object[] args = new Object[3];
	private AddPriLandownerFunction addPriLandownerFunction = new AddPriLandownerFunction();
	private Data<PriLandownerAttr> landowner = new Data<PriLandownerAttr>();;
	public void executeSetup() {
		addPriLandownerFunction.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_pri_landowners";
		queryDataSql = "select * from d_hf_add_pri_landowners";

	}
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
//		schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		landowner.put(PriLandownerAttr.privilegeCode, datasFromDB.get("PRIVILEGECODE"));
		landowner.put(PriLandownerAttr.minAcresToQuantity, datasFromDB.get("MINACRESTOQUALIFY"));
		landowner.put(PriLandownerAttr.maxCountyDeclaration, datasFromDB.get("MAXCOUNTYDECLARATION"));
		landowner.put(PriLandownerAttr.isAsBonusOnly, datasFromDB.get("ISSELLASBONUSONLY"));
		landowner.put(PriLandownerAttr.bonusPriName, datasFromDB.get("BONUSPRIVILEGE"));
		args[2] = landowner;
	}

}
