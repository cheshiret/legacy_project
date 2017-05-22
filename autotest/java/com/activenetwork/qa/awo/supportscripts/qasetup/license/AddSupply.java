package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.SupplyInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrCreateNewSupplyPage;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddSupplyFunction;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 *
 * @author Reed Wang
 *
 */
// TODO: Create another super class to move try-catch block there?
// TODO: We close browser when exception occurs, so the screenshot couldn't be correctly captured, solve this.
public class AddSupply extends SetupCase {
	private SupplyInfo supply;
	LoginInfo login = new LoginInfo();
	private AddSupplyFunction addSupFunc = new AddSupplyFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = supply;
		addSupFunc.execute(args);
	}

	/* (non-Javadoc)
	 * @see testAPI.SupportCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_supply";
	}
	
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		supply = DatapoolUtil.fill(SupplyInfo.class, datasFromDB);
		String availableToApps = datasFromDB.get("availableToApp");
		if (StringUtil.notEmpty(availableToApps)) {
			List<String> apps = Arrays.asList(availableToApps.split(","));
			if (!apps.contains(LicMgrCreateNewSupplyPage.APPS_ALL)) {
				supply.availableToApp.put(LicMgrCreateNewSupplyPage.APPS_ALL,
						false);
				for (String app : apps) {
					supply.availableToApp.put(app, true);
				}
			}
		}

	}
}
