package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.undistributedfundsreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class Verify7LevelHirachyLocation extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rptCriteriaPg.selectFacilityID(rd.facilityID);
		this.verifyParentLocationCorrect();
		
		//logout resource manager
		rm.logoutResourceManager();		
	}

	/**
	 * This method used to verify the parent location changed with the park change
	 */
	private void verifyParentLocationCorrect(){
		if(!rptCriteriaPg.getAgencyDefaultID().equals(rd.agencyID)){
			throw new ErrorOnPageException("Agency Not Match "+rd.agencyID);
		}
		if(!rptCriteriaPg.getRegionDefaultID().equals(rd.regionID)){
			throw new ErrorOnPageException("Region Not Match "+rd.regionID);
		}
		if(!rptCriteriaPg.getDistrictDefaultID().equals(rd.districtID)){
			throw new ErrorOnPageException("District Not Match "+rd.districtID);
		}
		if(!rptCriteriaPg.getProjectDefaultID().equals(rd.projectID)){
			throw new ErrorOnPageException("Project Not Match "+rd.projectID);
		}
		if(!rptCriteriaPg.getFacilityID().equals(rd.facilityID)){
			throw new ErrorOnPageException("Park Not Match "+rd.facilityID);
		}
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
		rd.agencyID = "USACE";
		rd.regionID = "CK";
		rd.districtID = "CK5";
		rd.projectID = "CK5001680";
		rd.facilityID = "BURCHFIELD BRANCH PARK";
	}
}
