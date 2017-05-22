package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.posproductsoldsummaryreport.selectioncriteria.resource;

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

		// logout resource manager
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		// initialize report data
		rd.group = "All";
		rd.reportName = "POS Product Sold Summary Report";
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
			throw new ErrorOnPageException("Region default value is not 'All'");
		}
		if (!"All".equals(rptCriteriaPg.getFacilityID())) {
			throw new ErrorOnPageException("Park default value is not 'All'");
		}
	}
}
