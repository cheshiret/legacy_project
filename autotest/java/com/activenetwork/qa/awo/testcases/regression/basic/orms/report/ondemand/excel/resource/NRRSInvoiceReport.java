package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class NRRSInvoiceReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>NRRSInvoiceReport</b>
	 * Generated     : <b>Feb 24, 2010 2:05:48 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/24
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect( result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "NRRS Invoice Report";
		rd.invoice = "#13822463 - 03-01-2007 - 03-31-2007 - NRRS";
		rd.locationIDNoValidate = "NRRS";
		rd.startDate = "Tue Jan 1 2008";
		rd.endDate = "Thu Jan 31 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
