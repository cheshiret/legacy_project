package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntParameterFunction;

/**
 * @Description: Add hunt parameter
 * @author Lesley Wang
 * @Date  Aug 19, 2013
 */
public class AddHuntParameter extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private String huntCode;
	private HuntParameterInfo[] huntParams;
	private AddHuntParameterFunction addHuntParamFunc = new AddHuntParameterFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = huntCode;
		args[2] = huntParams;
		addHuntParamFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		huntCode = datasFromDB.get("huntCode");
		String[] paramDescriptions = datasFromDB.get("paramDescription").split(";");
		huntParams = new HuntParameterInfo[paramDescriptions.length];
		for (int i = 0; i < paramDescriptions.length; i++) {
			HuntParameterInfo huntParam = new HuntParameterInfo();
			huntParam.setHuntParamDes(paramDescriptions[i]);
			huntParam.setHuntParamValue(datasFromDB.get("paramValue").split(";")[i]);
			huntParam.setPrintHuntParam(Boolean.valueOf(datasFromDB.get("printParam").split(";")[i]));
			huntParams[i] = huntParam;
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_hunt_parameter";
	}

}
