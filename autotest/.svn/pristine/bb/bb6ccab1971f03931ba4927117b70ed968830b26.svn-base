package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transmittaldetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifySevenLevelHirachyLocation extends ResourceManagerTestCase{
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
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Transmittal Detail Report";
		rd.agencyID = "BLM";
		rd.regionID = "OR";
		rd.districtID = "080";
		rd.projectID = "084";
		rd.facilityID = "FISHERMENS BEND";
	}

	private void selectFacility(String facilityID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.selectFacilityID(facilityID);
	}
	
	private void verifyLocationDefaultCorrect() {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		String options = rptCriteriaPg.getAllSelectionCriteria("AgencyID");
		String[] optionsArray = options.split("#");
		
		if (!optionsArray[0].equals(rptCriteriaPg.getAgencyDefaultID())) {
			throw new ErrorOnPageException("Agency default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getRegionDefaultID())) {
			throw new ErrorOnPageException("State default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getDistrictDefaultID())) {
			throw new ErrorOnPageException("District default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getProjectDefaultID())) {
			throw new ErrorOnPageException("Field office default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getFacilityID())) {
			throw new ErrorOnPageException("Park default value is not 'All'");
		}
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
