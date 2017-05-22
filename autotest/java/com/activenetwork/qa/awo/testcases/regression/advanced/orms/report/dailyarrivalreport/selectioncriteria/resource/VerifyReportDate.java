package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.dailyarrivalreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyReportDate extends ResourceManagerTestCase{

	ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	String errorMessage = "Missing Required Field: {1,2}StartDate";
	
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		// Not enter Start Date
		rm.verifyReportDate("", "", errorMessage);
		
		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		// Initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Daily Arrival Report";		
	}	
}
