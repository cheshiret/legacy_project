package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class FLExportReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptFLExportReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "FL Contract";
		login.location = "Administrator/DEP";

		//initialize report data
		rd.group = "All";
		rd.reportName = "FL Monthly Export Report";
		rd.flMonthYear="August 2009";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat="XLS";
		rd.deliveryMethod = "Email";
	}
}
