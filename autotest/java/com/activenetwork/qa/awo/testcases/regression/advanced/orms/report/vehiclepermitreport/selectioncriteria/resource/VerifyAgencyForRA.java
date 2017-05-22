package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.vehiclepermitreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyAgencyForRA extends ResourceManagerTestCase{
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		selectAgency(rd.agencyID);
		verifyLocationDefaultCorrect();

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
		rd.group = "All";
		rd.reportName = "Vehicle Permits Report";
		rd.agencyID = "Reserve America";
	}

	private void selectAgency(String agencyID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.selectAgencyID(agencyID);
	}
	
	private void verifyLocationDefaultCorrect() {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		if (!"All".equals(rptCriteriaPg.getRegionDefaultID())) {
			throw new ErrorOnPageException("Region default value is not 'All'");
		}
		if (!"CA Call Center".equals(rptCriteriaPg.getFacilityID())) {
			throw new ErrorOnPageException("Park default value is not 'CA Call Center'");
		}
	}
}
