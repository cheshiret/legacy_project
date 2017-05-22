package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.customerbalancereport.selectioncriteria.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyEndDateGreaterThanStartDate extends ResourceManagerTestCase{

	
	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.verifyReportDate(rd.startDate, rd.endDate, "End date should be greater than or equal to start date");
		
		rm.logoutResourceManager();
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Customer Balance Report";
		rd.startDate = "7/19/2010";
		rd.endDate = "7/18/2010";
	}
}
