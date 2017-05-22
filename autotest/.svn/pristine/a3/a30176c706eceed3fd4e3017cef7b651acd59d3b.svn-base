package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignPrivToHuntFunction;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Assign Privilege to Hunt in LM
 * @author Lesley Wang
 * @Date  Aug 18, 2013
 */
public class AssignPrivToHunt extends SetupCase {
	
	private LoginInfo login = new LoginInfo();
	private List<HuntPermitInfo> huntPermits;
	private String huntCode;
	private AssignPrivToHuntFunction assignToHuntFunc = new AssignPrivToHuntFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = huntCode;
		args[2] = huntPermits;
		assignToHuntFunc.execute(args);
	}

		@Override
	public void readDataFromDatabase() {
			login.contract=datasFromDB.get("contract");
			login.location=datasFromDB.get("location");
			
			huntCode = datasFromDB.get("huntCode");
			String[] appTypes = datasFromDB.get("applicantType").split(";");
			huntPermits = new ArrayList<HuntPermitInfo>();
			for (int i = 0; i < appTypes.length; i++) {
				HuntPermitInfo huntPermit = new HuntPermitInfo();
				
				String appType = appTypes[i];
				if (StringUtil.isEmpty(appType))
					appType = "All";
				huntPermit.setApplicantType(appType);
				
				huntPermit.setPermit(datasFromDB.get("privCodeNm").split(";")[i]);
				huntPermit.setMinAge(datasFromDB.get("minAge").split(";")[i]);
				huntPermit.setMaxAge(datasFromDB.get("maxAge").split(";")[i]);
				huntPermit.setResidencyStatus(datasFromDB.get("residStatus").split(";")[i]);
				huntPermit.setHuntPermitStatus("Active");
				
				huntPermits.add(huntPermit);
			}
			
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assign_priv_to_hunt";
	}

}
