package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class NYInventorySummaryBySiteTypeReport extends ResourceManagerTestCase{

	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
//		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		
		rd.group = "Operational Reports";
		rd.reportName = "NY Inventory Summary by Site Type Report";
		rd.agencyID = "OPRHP";
	
		rd.reportFormat = "PDF";
	}
}
