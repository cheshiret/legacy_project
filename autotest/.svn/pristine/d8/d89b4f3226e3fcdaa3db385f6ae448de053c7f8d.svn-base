package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.vehiclepermitreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifySevenLevelHirachyLocation extends ResourceManagerTestCase {

	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		verifyLocationDefaultCorrect();
		selectFacility(rd.facilityID);
		verifyParentLocationCorrect();

		// logout resource manager
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";//Administrator/ACORN CAMP EAST (CA)

		// initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Vehicle Permits Report";
		rd.agencyID = "USACE";
		rd.regionID = "CB";
		rd.districtID = "CB5";
		rd.projectID = "CB5016510";
		rd.facilityID = "ACORN VALLEY";
	}

	private void selectFacility(String facilityID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.selectFacilityID(facilityID);
	}
	
	private void verifyLocationDefaultCorrect() {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		if (!"All".equals(rptCriteriaPg.getAgencyDefaultID())) {
			throw new ErrorOnPageException("Agency default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getRegionDefaultID())) {
			throw new ErrorOnPageException("Region default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getDistrictDefaultID())) {
			throw new ErrorOnPageException("District default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getProjectDefaultID())) {
			throw new ErrorOnPageException("Project default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getFacilityHQDefaultID())) {
			throw new ErrorOnPageException("Facility HQ default value is not 'All'");
		}
		//correct date: ACKER ROCK LOOKOUT
		
		//DEFECT-31246 
		//For 'PYRAMID LAKE - EMIGRANT LANDING DAY USE AREA', we can find it from DB via querying SQL: 
		//select * from d_loc where id = '72312';
		//The name is ' PYRAMID LAKE - EMIGRANT LANDING DAY USE AREA' with blank space in front.
		//Above defect has been fixed
		//2012/12/13, there is new default value '2011 National Christmas Tree Lighting Ceremony'
//		if (!"ACKER ROCK LOOKOUT".equals(rptCriteriaPg.getFacilityID())) {
//			throw new ErrorOnPageException("Park default value is not 'ACKER ROCK LOOKOUT'");
//		}
	}

	/**
	 * This method used to verify the parent location changed with the park
	 * change
	 */
	private void verifyParentLocationCorrect() {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		if (!rd.agencyID.equals(rptCriteriaPg.getAgencyDefaultID())) {
			throw new ErrorOnPageException("Agency Not Match " + rd.agencyID);
		}
		if (!rd.regionID.equals(rptCriteriaPg.getRegionDefaultID())) {
			throw new ErrorOnPageException("Region Not Match " + rd.regionID);
		}
		if (!rd.districtID.equals(rptCriteriaPg.getDistrictDefaultID())) {
			throw new ErrorOnPageException("District Not Match "
					+ rd.districtID);
		}
		if (!rd.projectID.equals(rptCriteriaPg.getProjectDefaultID())) {
			throw new ErrorOnPageException("Project Not Match " + rd.projectID);
		}
		if (!rd.facilityID.equals(rptCriteriaPg.getFacilityID())) {
			throw new ErrorOnPageException("Park Not Match " + rd.facilityID);
		}
	}
}
