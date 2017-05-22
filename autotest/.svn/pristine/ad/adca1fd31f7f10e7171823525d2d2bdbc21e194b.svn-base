package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ParkRefundReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptParkRefundReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NE Contract";
		login.location = "Administrator/NGPC";

		rd.group = "All";
		rd.reportName = "Park Refunds Report";
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Tue Jul 15 2008";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
