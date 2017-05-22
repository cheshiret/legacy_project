package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class DistributedVendorFeeDetailReport extends ResourceManagerTestCase{

	@Override
	public void execute() {
		//login resource manager and run Distributed Vendor Fee Detail Report
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "FL Contract";
		login.location = "Administrator/DEP";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Distributed Vendor Fee Detail Report";
		rd.locationID = "DEP";
		rd.agencyID = "DRP";
		rd.regionID = "D3";
		rd.facilityID = "ANASTASIA SP";
		rd.startDate = "Sun Jun 1 2008";
		rd.endDate="Tue Jun 3 2008";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
	}

}
