package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.venue;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * D_ASSIGN_FEATURE:id=6222,6232
 * @SPEC:
 * Report Header and Report Footer [TC:107481] 
 * Report Body - Time Specific/Non Time Specific/Individual/Organization [TC:107482] 
 * Report Body - ComboTours [TC:107483]
 * Report Body - Group By&Order By&Layout [TC:107484]
 * @Task#: Auto-2311
 * 
 * @author Jane Wang
 * @Date  July 31, 2014
 */
public class DailyArrivalReportTicketing extends VenueManagerTestCase {

	private ReportData rd = new ReportData();
	
	public void execute() {
		//login venue manager
		vm.loginVenueManager(login);
		
		vm.selectOneReport(rd.group, rd.reportName);
		fileName = vm.runSpecialReport(rd, comparedPath); // run and download the report
		boolean correct = vm.verifyPdfReport(templatesPath, fileName); // verify report by compare with given template file

		if(!correct){
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES";
		
		//initialize report data
		rd.group = "All";
		rd.reportName = "Daily Arrival Report-Ticketing";
		rd.tourID = "All";
		rd.ticketCategory = "All";
		rd.tourDate = "2008-10-10";
		rd.startTime = "6:00";
		rd.startTimeampm = "AM";
		rd.endTime = "6:00";
		rd.endTimeampm = "PM";
		
	}

}
