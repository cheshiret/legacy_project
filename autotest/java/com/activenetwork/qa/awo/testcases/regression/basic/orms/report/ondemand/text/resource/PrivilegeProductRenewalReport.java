package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case is use basic report test strategy compared new report with template
 * as this report will show all customer info which could renew their privilege, so the report data will be dynamic based on schema data
 * after change schema,need run setup script to change template file
 *               
 * @Preconditions:
 * @SPEC: TC019740,019741,019742,019743
 * @Task#:Auto-1393
 * 
 * @author ssong
 * @Date  Jan 24, 2013
 */
public class PrivilegeProductRenewalReport extends ResourceManagerTestCase{
	
	public void execute() {
		// Login
		rm.loginResourceManager(login);
		
		//Delivery through "email"		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
        rm.runSpecialRpt(rd, comparedPath);

        rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		String fileName = rm.runSpecialRpt(rd, comparedPath);

		result = rm.skipVerifyOnlineReport(templatesPath, fileName);	
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		// log out
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// report information
		rd.group = "All";
		rd.reportName = "Privilege Product Renewal Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.privilegeProduct = new String[] { "101 - RES ALL GAME HUNT/FRESH" };
		rd.includePrivilegeCode = "No";
		rd.startDate = "Mon Oct 01 2012";
		rd.endDate = "Mon Oct 01 2012";
		rd.reportFormat = "CSV";
	}
}
