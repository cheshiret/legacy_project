package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class OmbilExportReport extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptOmbilExportReport</b> Generated : <b>Jul 29, 2010
	 * 11:00:09 AM</b> Description : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		result = rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "OMBIL Export Report";
		rd.agencyID = "USACE";
		rd.monthYear = "July 2010";
		rd.emailSubject = rd.reportName 
				+ DateFunctions.getCurrentTime();
		rd.reportFormat = "CSV";
		rd.deliveryMethod = "Email";
	}
}
