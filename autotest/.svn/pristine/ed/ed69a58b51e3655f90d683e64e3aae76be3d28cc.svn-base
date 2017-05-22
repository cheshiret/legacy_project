package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuotaTypeInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddQuotaTypeFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 *
 * @author fliu
 * @Date  March 13, 2012
 */
public class AddQuotaType extends SetupCase {
	private String facilityName;
	private QuotaTypeInfo quotaTypeInfo;
	LoginInfo loginInfo = new LoginInfo();
	private AddQuotaTypeFunction addQuotaType = new AddQuotaTypeFunction();

	@Override
	public void readDataFromDatabase() {
		loginInfo.contract = datasFromDB.get("contract");
		loginInfo.location = datasFromDB.get("location");
		facilityName = datasFromDB.get("facilityName");
		quotaTypeInfo = new QuotaTypeInfo();
		quotaTypeInfo.quotaTypeCode = datasFromDB.get("quotatypecode");
		quotaTypeInfo.quotaTypeName = datasFromDB.get("quotatypename");
		quotaTypeInfo.quotaInterval = datasFromDB.get("quotainterval");
		quotaTypeInfo.commercialAllocation = datasFromDB.get("commercialallocation");
		quotaTypeInfo.nonCommercialAllocation = datasFromDB.get("noncommercialallocation");
		
	}

	@Override
	public void executeSetup() {
		addQuotaType.execute(loginInfo, facilityName, quotaTypeInfo);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_quota_type";
		queryDataSql = "";

		String env = TestProperty.getProperty("target_env");
		loginInfo.url = AwoUtil.getOrmsURL(env);
		
		loginInfo.userName = TestProperty.getProperty("orms.im.user");
		loginInfo.password = TestProperty.getProperty("orms.im.pw");
	}
}
