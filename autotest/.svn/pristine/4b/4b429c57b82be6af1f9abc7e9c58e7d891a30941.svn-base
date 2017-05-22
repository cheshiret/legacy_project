package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transmittaldetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyTransmittalId extends ResourceManagerTestCase{
	private static final String errorMess="Please provide a Transmittal ID.";
	private static final String invalidMess="Invalid Transmittal ID entered. Please refine the search criteria.";
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		setReportCriteria(rd);
		setTransmittalID(rd.transmittalID);
		verifyValidTransmittalIdMess(errorMess);
		rd.transmittalID= "12345";
		setTransmittalID(rd.transmittalID);
		verifyValidTransmittalIdMess(invalidMess);

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
		rd.agencyID = "NPS";
		rd.facilityID = "SANTA CRUZ SCORPION";
		rd.startDate="Fri Oct 1 2010";
		rd.reportFormat = "XLS";
	}

	private void setReportCriteria(ReportData rd) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.setReportCriteria(rd);
	}

	private void setTransmittalID(String transmittalID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.setTransmittalID(transmittalID);
		rptCriteriaPg.clickOk();
		rptCriteriaPg.waitLoading();
	}
	
	private void verifyValidTransmittalIdMess(String error){
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		String errorMess=rptCriteriaPg.getErrorMsg();
		if(!error.equals(errorMess)){
			throw new ErrorOnPageException("Error Message display not correct on the top");
		}
	}
}
