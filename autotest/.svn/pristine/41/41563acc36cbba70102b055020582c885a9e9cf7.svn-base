/**
 * 
 */
package com.activenetwork.qa.awo.testcases.production.orms;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:Local PC mustn't install adobe pdf reader.
 * @SPEC:
 * @Task#:AUTO-882
 * 
 * @author asun
 * @Date  2012-2-27
 */
public class RM_RunReport extends ResourceManagerTestCase {
	
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		rm.logoutResourceManager();
	}


	public void wrapParameters(Object[] param) {
		
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
			login.url = TestProperty.getProperty(env + ".training.orms.url");
		  	login.userName = TestProperty.getProperty(env+".training.orms.adm.user");
			login.password = TestProperty.getProperty(env+".training.orms.adm.pw");
			login.location = "imp-admin-role/";			
		} else {
			login.location = "Administrator/";
			login.userName = TestProperty.getProperty("orms.adm.user");
			login.password = TestProperty.getProperty("orms.adm.pw");
		}
		TestProperty.putProperty("role.auto", "false");
		login.contract = "CA Contract";
		login.location = login.location+"California Parks and Recreation";

		rd.reportName="7-Day Campers Report";
		rd.startDate = DateFunctions.getDateAfterToday(-7);
		rd.reportFormat = "PDF";
		rd.deliveryMethod= "Online";
	}
}
