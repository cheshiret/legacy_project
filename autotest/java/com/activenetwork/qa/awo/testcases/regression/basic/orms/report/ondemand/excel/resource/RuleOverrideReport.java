package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class RuleOverrideReport extends ResourceManagerTestCase{
	public void execute() {
		rm.loginResourceManager(login);

		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);   //Excel Online
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NY Contract";
		login.location = "Administrator/Contract";

		rd.group = "All";
		rd.reportName = "Rule Override Report";
		rd.agencyID="OPRHP";
		rd.facilityID="ALLEGANY STATE PARK";
		
		rd.startDate = "Sun Jun 1 2008";
		rd.endDate = "Sun Jun 15 2008";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
