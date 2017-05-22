package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.facilityutilizationsummaryrpt.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyLocationCategory extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rptCriteriaPg.selectAgencyID(rd.agencyID);
		rptCriteriaPg.selectFacilityID(rd.facilityID);

		//verify location match given location
		this.verifyParentLocationCorrect();
		
		rm.logoutResourceManager();
	}

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
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Facility Utilization Summary Report";
		rd.agencyID = "USFS";
		rd.facilityID = "RYAN PARK";
		rd.regionID = "F2";
		rd.districtID = "F206";
		rd.projectID = "F20602";
	}
}
