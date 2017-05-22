package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntFunction;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Hunt
 * @author Lesley Wang
 * @Date  Aug 8, 2013
 */
public class AddHunt extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private HuntInfo hunt = new HuntInfo();
	private AddHuntFunction addHuntFunc = new AddHuntFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = hunt;
		addHuntFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		hunt.setSpecie(datasFromDB.get("species"));
		hunt.setHuntCode(datasFromDB.get("code"));
		hunt.setDescription(datasFromDB.get("description"));
		hunt.setAllowedApplicants(datasFromDB.get("allowedapp").split(";"));
		hunt.setMinAllowedOfGroup(datasFromDB.get("minAllowed"));
		hunt.setMaxAllowedOfGroup(datasFromDB.get("maxAllowed"));
		String temp = datasFromDB.get("quotaLimited");
		if (StringUtil.isEmpty(temp) || temp.equalsIgnoreCase("true")) {
			hunt.setQuotaLimited(true);
		} else {
			hunt.setQuotaLimited(false);
		}
		hunt.setHuntQuotaDescription(datasFromDB.get("quotaDes"));
		hunt.setSpecieSubType(datasFromDB.get("speciesSubType"));
		hunt.setHuntLocationInfo(datasFromDB.get("huntLocation"));
		hunt.setWeapon(datasFromDB.get("weapon"));
		hunt.setHuntDatePeriodInfo(datasFromDB.get("datePeriod"));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_hunt";
	}
}
