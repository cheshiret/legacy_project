package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class InvoiceSummaryReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptInvoiceSummaryReport</b>
	 * Generated     : <b>Jul 27, 2009 5:47:31 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/27
	 * @author QA
	 */
	public void execute() {

		rm.loginResourceManager(login);

		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//XLS EMAIL
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//XLS Online
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
		rd.reportName = "Invoice Summary Report";
		rd.invoice = "#49723306 - 07-01-2007 - 07-31-2007 - South Carolina State Park Service";
		rd.locationIDNoValidate = "South Carolina State Park Service";
		rd.startDate = "7/1/2007";
		rd.endDate = "7/7/2007";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
