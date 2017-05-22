package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class FacilityDistributionCycleReport extends ResourceManagerTestCase {

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath); //xls report online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rd.deliveryMethod = "Email";
		rd.reportType = "Detail";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);  //xls report email
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);  //xls report online
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "Facility Distribution Cycle Report";
		rd.locationID = "USFS";
		rd.frequency = "Monthly starting on the 1st";
		rd.period = "07-01-2011 - 07-31-2011 Trf 08-04-2011";
		rd.facilityID = "RYAN PARK";//RYAN PARK (WY)
		rd.reportType = "Summary";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}
}
