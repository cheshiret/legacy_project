package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntQuotaLicenseYearFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Hunt Date Period License Year 
 * 1. the quota type info should be separated by ";" in the test data.
 * 2. if set the value of numOfYears and numOfYearsAfterCurrent, 
 * then the number of the added license year will be numOfYears, 
 * and the start license year will be licenseyear+numOfYearsAfterCurrent
 * @author Lesley Wang
 * @Date  Mar 24, 2014
 */
public class AddHuntQuotaLicenseYear extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private QuotaInfo quota;
	private String schema;
	private AddHuntQuotaLicenseYearFunction function = new AddHuntQuotaLicenseYearFunction();
	private int numOfYears = 1;
	private int numOfYearsAfterCurrent = 0;
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = quota;
		args[2] = numOfYearsAfterCurrent;
		args[3] = numOfYears;
		function.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		schema = DataBaseFunctions.getSchemaName(login.contract.replace("Contract", "").trim(), env);
		
		quota = new QuotaInfo();
		quota.setDescription(datasFromDB.get("QuotaDescription"));
		
		quota.setLicenseYear(datasFromDB.get("LicenseYear"));
		if(StringUtil.isEmpty(quota.getLicenseYear())) {
			quota.setLicenseYear(LicenseManager.getInstance().getFiscalYear(schema));
		}
		
		List<QuotaType>	quotaType = new ArrayList<QuotaType>();
		int quotaTypeNum = Integer.parseInt(datasFromDB.get("quotatypenum"));
		String[] quotaTypes = datasFromDB.get("quotaType").split(";");
		String[] quotaNum = datasFromDB.get("quotanum").split(";");
		String[] isHybrid = datasFromDB.get("ishybrid").split(";");
		String weighted[] = datasFromDB.get("weighted").split(";");
		for(int i=0; i<quotaTypeNum; i++){
			//quotaType, quota, isHybrid, Weighted, Random
			quotaType.add(new QuotaType(quotaTypes[i], StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, quotaNum[i], new Boolean(isHybrid[i]),weighted[i]));
		}
		quota.setQuotaTypes(quotaType);

		String num = datasFromDB.get("numOfYearsAfterCurrent");
		if (StringUtil.notEmpty(num)) {
			numOfYearsAfterCurrent = Integer.parseInt(num); //The start license year=license year + numOfYearsAfterCurrent 
		}
		
		num = datasFromDB.get("numOfYears");
		if (StringUtil.notEmpty(num)) {
			numOfYears = Integer.parseInt(num); // the number of added license years
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_quota_license_year";
		ids = "";
	}
}
