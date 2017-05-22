package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LotteryExecutionConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LotteryExecutionConfigInfo.GroupConfiguration;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddLotteryExecutionConfigFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 4, 2013
 */
public class AddLotteryExecutionConfig extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private LotteryExecutionConfigInfo config = new LotteryExecutionConfigInfo();
	
	@Override
	public void executeSetup() {
		new AddLotteryExecutionConfigFunction().execute(new Object[] {login, config});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_lottery_execution_con";
		ids = "";
	}
	
	@Override
	public void readDataFromDatabase() {
		//login info
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("Location");
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		//lottery execution config info
		config.setAlgorithm(datasFromDB.get("Algorithm"));
		config.setStatus(datasFromDB.get("Status"));
		config.setDescription(datasFromDB.get("Description"));
		config.setLotteryType(datasFromDB.get("LotteryType"));
		config.setDrawType(datasFromDB.get("DrawType"));
		config.setRandomNumberRangeFrom(datasFromDB.get("RandomNumberRangeFrom"));
		config.setRandomNumberRangeTo(datasFromDB.get("RandomNumberRangeTo"));
		config.setUseSystemSeed(datasFromDB.get("UseSystemSeed").equalsIgnoreCase("Yes") ? true : false);
		config.setInitialNumber(datasFromDB.get("InitialNumber").equalsIgnoreCase("Yes") ? true : false);
		config.setAwardMethod(datasFromDB.get("AwardMethod"));
		String emailNotifications = datasFromDB.get("EmailNotification");
		if(!StringUtil.isEmpty(emailNotifications)) {
			config.setEmailNotifications(emailNotifications.split(","));
		}
		config.setSuccessfulRangeFrom(datasFromDB.get("SuccessfulRangeFrom"));
		config.setSuccessfulRangeTo(datasFromDB.get("SuccessfulRangeTo"));
		config.setSupportGroup(datasFromDB.get("SupportGroup").equalsIgnoreCase("Yes") ? true : false);
		GroupConfiguration groupConfiguration = config.new GroupConfiguration();
		groupConfiguration.setGroupPointsUsage(datasFromDB.get("GroupPointsUsage"));
		groupConfiguration.setGroupQuotaUsage(datasFromDB.get("GroupQuotaUsage"));
		groupConfiguration.setGroupQuotaIntegrity(datasFromDB.get("GroupQuotaIntegrity"));
		config.setGroupConfiguration(groupConfiguration);
	}
}
