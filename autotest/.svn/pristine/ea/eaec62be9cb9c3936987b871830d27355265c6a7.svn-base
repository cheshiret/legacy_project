package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssignActivityToStoreFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * This set up script used to assign the activity product to stores
 * @author Phoebe
 *
 */
public class AssignActivityToStore extends SetupCase {
	
	private LoginInfo login = new LoginInfo();
	private String activityCode;
	private String locationClasses[];
	
	@Override
	public void executeSetup() {
		new AssignActivityToStoreFunction().execute(new Object[] {login, activityCode, locationClasses});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assign_activity_to_store";
		ids = "20,30";
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
		activityCode = datasFromDB.get("ACTIVITY_CODE");
		String locationClass = datasFromDB.get("LOCATION_CLASS");
		locationClasses = locationClass.split(",");
	}
}

