package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class RIAccruedRevenueReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptRIAccruedRevenueReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "RI Contract";
		login.location = "Administrator/State of Rhode Island Contract";

		rd.group = "Financial Reports";
		rd.reportName = "RI Accrued Earned Revenue Report";	
		rd.agencyID = "DEM";
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Sat Jul 5 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";

	}
}
