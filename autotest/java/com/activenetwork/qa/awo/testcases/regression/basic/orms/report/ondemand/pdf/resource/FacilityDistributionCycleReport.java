package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class FacilityDistributionCycleReport extends ResourceManagerTestCase {

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath); //PDF report online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

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
		rd.facilityID = "RYAN PARK";
		rd.reportType="Summary";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Online";
	}
}
