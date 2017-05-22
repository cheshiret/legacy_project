package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class InvoiceDetailReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptInvoiceDetailReport</b>
	 * Generated     : <b>Jul 27, 2009 5:23:31 AM</b>
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
		rm.runSpecialRpt(rd, comparedPath);// XLS Email

		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);// XLS ONLINE
		fileName = rm.unzipReport(fileName, comparedPath);
		
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
		rd.reportName = "Invoice Detail Report";
		rd.invoice = "#49723306 - 07-01-2007 - 07-31-2007 - South Carolina State Park Service";
		rd.locationIDNoValidate = "South Carolina State Park Service";
		rd.startDate = "Fri Jun 1 2007";
		rd.endDate = "Thu Jun 7 2007";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";

	}
}
