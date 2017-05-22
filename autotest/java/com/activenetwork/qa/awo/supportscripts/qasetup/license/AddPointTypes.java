package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.lottery.PointTypesAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPointTypesFunction;
import com.activenetwork.qa.testapi.datacollection.Data;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 13, 2014
 */
public class AddPointTypes extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private Data<PointTypesAttr> pointType = new Data<PointTypesAttr>();
	private AddPointTypesFunction pointTypesFunc = new AddPointTypesFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = pointType;
		pointTypesFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		//Point types info
		pointType.put(PointTypesAttr.code, datasFromDB.get("code"));
		pointType.put(PointTypesAttr.displayCode, datasFromDB.get("displayCode"));
		pointType.put(PointTypesAttr.description, datasFromDB.get("description"));
		pointType.put(PointTypesAttr.maxAllowed, datasFromDB.get("maxAllowed"));
		pointType.put(PointTypesAttr.transitionSettings, datasFromDB.get("transitionSettings"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_point_types";
	}
}
