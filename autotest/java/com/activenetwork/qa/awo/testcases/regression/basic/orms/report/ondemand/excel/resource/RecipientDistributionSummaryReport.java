package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * Description   : Functional Test Script
 * @author QA
 */
public class RecipientDistributionSummaryReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptRecipientDistributionSummaryReport</b>
	 * Generated     : <b>Jul 22, 2009 10:24:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Financial Reports";
		rd.reportName = "Recipient Distribution Summary Report";

		rd.locationID = "South Carolina State Park Service";
		rd.paymentMethod = "ALL";
		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Tue Jul 17 2007";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
