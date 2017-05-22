package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.idahoexportreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifySelectionCriteriaForAllAgency extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rptCriteriaPg.selectAgencyID(rd.agencyID);

		//verify location match given location
		this.verifyLocationCategoryCorrect();
		
		rm.logoutResourceManager();
	}

	private void verifyLocationCategoryCorrect(){
		logger.info("Verify Location Category.");
		
		String allRegions = rptCriteriaPg.getAllRegionOptions();
		if(!allRegions.contains("All")||!allRegions.contains("Call Center")||!allRegions.contains("STATE PARKS")||!allRegions.contains("Web")){
			throw new ErrorOnPageException("Region/Division Dropdown list Not Contain Correct Option.");
		}
		String allParks = rptCriteriaPg.getAllParks();
		if(!allParks.contains("Bear Lake State Park")||!allParks.contains("CA Call Center")||!allParks.contains("NY Work-at-home")){
			throw new ErrorOnPageException("Park Dropdown list Not Contain Correct Option.");
		}
	}
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";
		
		rd.group = "Financial Reports";
		rd.reportName = "Idaho Export Report";
		
		rd.agencyID = "All";
	}
}
