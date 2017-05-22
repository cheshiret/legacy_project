package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.idahoexportreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifySelectionCriteriaForWeb extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rptCriteriaPg.selectFacilityID(rd.facilityID);

		//verify location match given location
		this.verifyLocationCategoryCorrect();
		
		rm.logoutResourceManager();
	}

	private void verifyLocationCategoryCorrect(){
		logger.info("Verify Location Category.");
		
		if(!rptCriteriaPg.getAgencyDefaultID().equals(rd.agencyID)){
			throw new ErrorOnPageException("Agency Not Match "+rd.agencyID);
		}
		if(!rptCriteriaPg.getRegionDefaultID().equals(rd.regionID)){
			throw new ErrorOnPageException("Region Not Match "+rd.regionID);
		}
		if(!rptCriteriaPg.getFacilityID().equals(rd.facilityID)){
			throw new ErrorOnPageException("Park Not Match "+rd.facilityID);
		}
	}
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Idaho Export Report";
		
		rd.agencyID = "Reserve America";
		rd.facilityID = "NY Work-at-home";
		rd.regionID = "Call Center";
	}
}
