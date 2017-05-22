package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class TourFeeAnalysisReport extends ResourceManagerTestCase{
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);  //xls report email
		rm.runSpecialRpt(rd, comparedPath);
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "Tour Fee Analysis Report";
		rd.facilityID = "VOYAGEURS NATIONAL PARK TOURS";
		rd.agencyID = "NPS";
		rd.tourID = "Grand Tour";
		rd.startDate = "07/01/2008";
		rd.endDate = "07/01/2008";
		
		rd.reportFormat = "XLS";
	}
}
