package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.venue;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:verify Daily Reconciliation Report content using history data on venue manager
 *              
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:Daily Reconciliation Report [TC:006968] 
 *       Daily Reconciliation Report - Add Ticket Type Print Counts [TC:030019]   
 * @Task#:Auto-2295,AUTO-2275 
 * 
 * @author szhou
 * @Date Jul 24, 2014
 */
public class DailyReconciliationReport extends VenueManagerTestCase{

	private ReportData rd = new ReportData();
	
	public void execute() {
		//login venue manager
		vm.loginVenueManager(login);
		
		vm.selectOneReport(rd.group, rd.reportName);
		fileName = vm.runSpecialReport(rd, comparedPath); // run and download the report
		boolean correct = vm.verifyPdfReport(templatesPath, fileName); // verfiy report by compare with given template file

		if(!correct){
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
		
		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		
		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Daily Reconciliation Report";
		rd.operator = "Donna Shand";
		rd.startDate = "Mon Sep 1 2008";
	}
}
