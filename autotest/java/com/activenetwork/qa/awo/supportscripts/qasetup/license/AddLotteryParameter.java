package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LotteryParameterInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddLotteryParameterFunction;
import com.activenetwork.qa.testapi.datacollection.Data;

/**
 * @Description: Add Lottery Parameter 
 * @author Lesley Wang
 * @Date  Mar 25, 2014
 */
public class AddLotteryParameter extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private String lotteryCode;
	private List<Data<LotteryParameterInfo>> lotteryParams;
	private AddLotteryParameterFunction addLotteryParamFunc = new AddLotteryParameterFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = lotteryCode;
		args[2] = lotteryParams;
		addLotteryParamFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		lotteryCode = datasFromDB.get("lotteryCode");
		String[] paramDescriptions = datasFromDB.get("paramDescription").split(";");
		String[] paramValues = datasFromDB.get("paramValue").split(";");
		String[] isPrint = datasFromDB.get("printParam").split(";");
		lotteryParams = new ArrayList<Data<LotteryParameterInfo>> ();
		for (int i = 0; i < paramDescriptions.length; i++) {
			Data<LotteryParameterInfo> param = new Data<LotteryParameterInfo>();
			LotteryParameterInfo.Description.setValue(param, paramDescriptions[i]);
			LotteryParameterInfo.Value.setValue(param, paramValues[i]);
			LotteryParameterInfo.IsPrintParam.setValue(param, Boolean.valueOf(isPrint[i]));
			lotteryParams.add(param);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_lottery_parameter";
		ids = "";
	}
}
