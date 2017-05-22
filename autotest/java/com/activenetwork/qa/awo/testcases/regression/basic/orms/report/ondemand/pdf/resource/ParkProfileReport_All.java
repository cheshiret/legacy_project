package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ParkProfileReport_All extends ResourceManagerTestCase{
	
	private String saveAsName;
	
	public void execute() {

		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,saveAsName, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "Park Profile Report";

		rd.agencyID = "SC parks";
		rd.facilityID = "CROFT";
		rd.reportSection = "All";
		
		rd.startDate = "Wed Jul 1 2009";
		rd.endDate = "Wed Jul 1 2009";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
		
		rd.fileName = rd.reportName+rd.reportSection;
		saveAsName = rd.fileName.replaceAll(" ", "")+"_Mail";
	}
}
