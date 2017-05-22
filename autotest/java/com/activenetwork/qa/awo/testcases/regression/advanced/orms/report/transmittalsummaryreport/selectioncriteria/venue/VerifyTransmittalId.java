package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transmittalsummaryreport.selectioncriteria.venue;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyTransmittalId extends VenueManagerTestCase{
	private static final String permissionMess="You do not have permission to view the entered Transmittal ID. Please refine the search criteria.";
	private static final String invalidMess="Invalid Transmittal ID entered. Please refine the search criteria.";
	private ReportData rd = new ReportData();
	public void execute() {
		//login venue manager
		vm.loginVenueManager(login);
		
		vm.selectOneReport(rd.group, rd.reportName);
		setReportCriteria(rd);
		rd.transmittalID= "12345";
		setTransmittalID(rd.transmittalID);
		verifyValidTransmittalIdMess(invalidMess);
		//should add a new transmittal when schema has been changed
		//check transmittal property setup in Inventory manager
		rd.transmittalID= "1055896860";//udpate for QA4
		setTransmittalID(rd.transmittalID);
		verifyValidTransmittalIdMess(permissionMess);
		
		vm.logoutVenueManager();
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Transmittal Summary Report";	
		rd.agencyID = "NPS";
		rd.facilityID = "SANTA CRUZ SCORPION";
		rd.reportFormat = "XLS";
		rd.startDate = DateFunctions.getToday();
		rd.endDate = DateFunctions.getToday();
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
	/**
	 * This method used to get report date error message
	 * 
	 * @param rd
	 * @return error msg
	 */
	public String getReportErrorMesg(String error) {
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();

		logger.info("Get error message for report.");

		alertPg.setDismissible(false);
		String alertMsg = "";
		Object page0 = browser.waitExists(alertPg,rmCriteriaPg,rmOnlineReport);
		
		if(page0 == alertPg){
			alertMsg = alertPg.text();
			alertPg.dismiss();
		}else if(page0 == rmCriteriaPg){
			alertMsg = rmCriteriaPg.getErrorMsg();
		}else{
			throw new ErrorOnPageException("There should have an error message("+error+") displayed.");
		}
		
		return alertMsg;
	}
	private void verifyValidTransmittalIdMess(String error){
		String errorMess=this.getReportErrorMesg(error);
		MiscFunctions.compareResult(error, errorMess, "Error Message Not Correct");
	}

}
