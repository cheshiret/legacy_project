package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class RAFeeReconciliationReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptRAFeeReconciliationReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 *  	Shane[20131129] blocked by DEFECT-54903
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
		login.contract = "NY Contract";
		login.location = "Administrator/Contract";

		rd.group = "Financial Reports";
		rd.reportName = "RA Fee Reconciliation Report";
		rd.invoice="#118560059 - 08-06-2008 - 08-11-2008 - OPRHP";
		rd.startDate = "Tue Aug 5 2008";
		rd.endDate = "Fri Aug 15 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		
		//templatesPath = "C://ReportTemplates";

	}
}
