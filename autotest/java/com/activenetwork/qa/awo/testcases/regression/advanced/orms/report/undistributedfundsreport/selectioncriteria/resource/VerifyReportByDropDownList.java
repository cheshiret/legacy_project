package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.undistributedfundsreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyReportByDropDownList extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		//verify report by drop down list contain correct options
		if(!rptCriteriaPg.getDefaultReportBy().equals("Revenue Location")){
			throw new ErrorOnPageException("Default Report By Value Not Correct.");
		}
		if(!rptCriteriaPg.getAllReportBy().contains("Collect Location")){
			throw new ErrorOnPageException("Report By List Not Contain Collect Location.");
		}
		
		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "UnDistributed Funds Report";
	}
}
