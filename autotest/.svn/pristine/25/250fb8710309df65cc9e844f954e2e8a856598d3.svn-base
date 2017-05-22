package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignLotteryToStoreFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 10, 2013
 */
public class AssignLotteryToStore extends SetupCase {
	
	private LoginInfo login = new LoginInfo();
	private String productCode;
	private String locationClasses[];
	
	@Override
	public void executeSetup() {
		new AssignLotteryToStoreFunction().execute(new Object[] {login, productCode, locationClasses});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assign_lottery_to_store";
		ids = "10";
	}
	
	@Override
	public void readDataFromDatabase() {
		//login info
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("Location");
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
				
		//assignment info
		productCode = datasFromDB.get("ProductCode");
		String locationClass = datasFromDB.get("LocationClass");
		locationClasses = locationClass.split(",");
	}
}
