package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class UndistributedVendorFeesReport extends ResourceManagerTestCase{
	public void execute() {
		//login resource manager and run Undistributed Vendor Fees Report
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "FL Contract";
		login.location = "Administrator/DEP";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Undistributed Vendor Fees Report";
		rd.agencyID = "DRP";
		rd.regionID = "D3";
		rd.facilityID = "ANASTASIA SP";
		rd.startDate = "Sun Jun 1 2008";
		rd.endDate="Sat Jun 7 2008";
		rd.includeDistributed = "Include";
		rd.reportFormat = "XLS";
		
	}
}
