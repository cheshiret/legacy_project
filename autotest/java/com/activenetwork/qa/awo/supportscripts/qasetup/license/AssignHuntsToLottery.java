package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignHuntsToLotteryFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Support script for assign hunts to lottery under the hunts sub tab on lottery product details page.
 * 
 * @author Lesley Wang
 * @Date  Jan 26, 2014
 */
public class AssignHuntsToLottery extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private String lotteryCode;
	private String[] huntCodes;
	private AssignHuntsToLotteryFunction function = new AssignHuntsToLotteryFunction();
	
	@Override
	public void executeSetup() {
		function.execute(new Object[] {login, lotteryCode, huntCodes});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_hunts_to_lottery";
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
		
		//lottery and hunts codes
		lotteryCode = datasFromDB.get("LotteryCode");
		huntCodes = datasFromDB.get("HuntsCode").split(",");
	}
}
