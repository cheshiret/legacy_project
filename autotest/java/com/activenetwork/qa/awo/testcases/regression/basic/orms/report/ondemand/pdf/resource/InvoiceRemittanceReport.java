package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: If there isn't any record, it will only one report(Invoice Remittance Report)
 *               If there are records, it will generate two reports.(Invoice Remittance Report & IndividualInvoiceDetailReport)
 */
public class InvoiceRemittanceReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptInvoiceRemittanceReport</b>
	 * Generated     : <b>Jul 27, 2009 5:40:10 AM</b>
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
		rm.runSpecialRpt(rd, comparedPath);// PDF EMAIL
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "Financial Reports";
		rd.reportName = "Invoice Remittance Report";
		rd.invoice="#269732869 - 06-01-2012 - 06-30-2012 - South Carolina State Park Service";
		rd.locationIDNoValidate = "South Carolina State Park Service";
//		rd.startDate = "7/1/2007";
//		rd.endDate = "7/7/2007";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
	}
}
