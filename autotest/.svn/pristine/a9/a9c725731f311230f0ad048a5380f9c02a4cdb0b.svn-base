package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddLotteryQuantityControlFunction;
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
 * @Date  Sep 4, 2013
 */
public class AddLotteryQuantityControl extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private QuantityControlInfo control = new QuantityControlInfo();
	
	@Override
	public void executeSetup() {
		new AddLotteryQuantityControlFunction().execute(new Object[] {login, control});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_lottery_quantity_cont";
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
		
		//quantity control info
		control.productCode = datasFromDB.get("ProductCode");
		control.status = datasFromDB.get("Status");
		control.locationClass = datasFromDB.get("LocationClass");
		control.multiSalesAllowance = datasFromDB.get("MultiSalesAllowance");
		control.maxQuantityPerTran = datasFromDB.get("MaxQuantityPerTransaction");
		control.maxAllowed = datasFromDB.get("MaxAllowed");
	}
}
