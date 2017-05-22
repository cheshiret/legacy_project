package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddLotteryScheduleFunction;
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
public class AddLotterySchedule extends SetupCase{

	private LoginInfo login = new LoginInfo();
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	
	@Override
	public void executeSetup() {
		new AddLotteryScheduleFunction().execute(new Object[] {login, schedule});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_lottery_schedule";
		ids = "20";
	}
	
	@Override
	public void readDataFromDatabase() {
		//login info
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("Location");
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.replace("Contract", StringUtil.EMPTY).trim();
		
		//privilege lottery schedule info
		schedule.setExecutionConfig(datasFromDB.get("LotteryExecutionConfig"));
		schedule.setLicenseYear(LicenseManager.getInstance().getFiscalYear(schema));
		schedule.setDescription(datasFromDB.get("Description"));
		schedule.setLottery(datasFromDB.get("Lottery"));
		schedule.setCalculateAgeMethod(datasFromDB.get("CalculateAgeAsOf"));
		schedule.setSpecificDate(datasFromDB.get("SpecificDate"));
		schedule.setFreezePeriodEndDate(datasFromDB.get("FreezePeriodEndDate"));
		schedule.setFreezePeriodEndTime(datasFromDB.get("FreezePeriodEndTime"));
		schedule.setFreezePeriodEndAmPm(datasFromDB.get("FreezePeriodEndAmPm"));
		schedule.setAwardAcceptanceByDate(datasFromDB.get("AwardAcceptanceByDate"));
		schedule.setAwardAcceptanceByTime(datasFromDB.get("AwardAcceptanceByTime"));
		schedule.setAwardAcceptanceByAmPm(datasFromDB.get("AwardAcceptanceByAmPm"));
	}
}
