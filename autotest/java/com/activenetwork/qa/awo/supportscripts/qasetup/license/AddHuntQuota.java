package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntQuotaFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: add quota in data pool for hunt.
 * @Preconditions: Feature 'ViewPrivilegeLotteryProductList' and 'CreateModifyPrivilegeLotteryQuota' has been assigned
 * @author Phoebe
 * @Date  Nov 27, 2012
 */
public class AddHuntQuota extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private QuotaInfo quota = new QuotaInfo();
	private AddHuntQuotaFunction addQuotaFunc = new AddHuntQuotaFunction();
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = quota;
		addQuotaFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		//Quota info
		quota.setDescription(datasFromDB.get("description"));
		quota.setSpecie(datasFromDB.get("species"));
		String licYear = datasFromDB.get("licenseyear");
		if(StringUtil.isEmpty(licYear)){
			quota.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		}else{
			quota.setLicenseYear(String.valueOf(licYear));
		}
		List<QuotaType>	quotaType = new ArrayList<QuotaType>();
		int quotaTypeNum = Integer.parseInt(datasFromDB.get("quotatypenum"));
		String[] qtName = datasFromDB.get("quotaType").split(",");
		String quotaUse = datasFromDB.get("quotaUse");
		String[] qUse = new String[qtName.length];
		if(StringUtil.notEmpty(quotaUse))
			qUse = quotaUse.split(",");
		String[] ageMin = datasFromDB.get("agemin").split(",");
		String[] ageMax = datasFromDB.get("agemax").split(",");
		String[] residencyStatus = datasFromDB.get("residencystatus").split(",");
		String[] quotaNum = datasFromDB.get("quotanum").split(",");
		String[] isHybrid = datasFromDB.get("ishybrid").split(",");
		String weighted[] = datasFromDB.get("weighted").split(",");
		for(int i=0; i<quotaTypeNum; i++){
			//quotaType, quotaUse, ageMin, ageMax, residencyStatus, quota, isHybrid, Weighted, Random
			if(StringUtil.isEmpty(qUse[i]))
				qUse[i] = "Draw/OTC Sale";//Jane[2014-6-4]:Updated for PCR4267
			quotaType.add(new QuotaType(qtName[i], qUse[i], ageMin[i], ageMax[i], residencyStatus[i], quotaNum[i], new Boolean(isHybrid[i]),
					weighted[i]));
		}
		quota.setQuotaTypes(quotaType);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_hunt_quota";
	}

}
