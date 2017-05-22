package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class RevenueDistributionDetailReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptRevenueDistributionDetailReport</b>
	 * Generated     : <b>Jul 23, 2009 2:14:17 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 */
	public void execute() {

		rm.loginResourceManager(login);
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.reportName = "Revenue Distribution Detail Report";

		rd.locationID = "South Carolina State Park Service";
		rd.paymentMethod = "ALL";

		rd.startDate = "07/17/2007";
		rd.endDate = "07/17/2007";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
