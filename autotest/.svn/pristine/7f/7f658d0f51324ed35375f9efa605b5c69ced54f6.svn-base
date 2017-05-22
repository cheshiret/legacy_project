package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
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
public class DailyArrivalReportTicketing extends ResourceManagerTestCase {

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF EMAIL
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF ONLINE
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.group = "All";
		rd.reportName = "Daily Arrival Report-Ticketing";
		rd.facilityLocID = "ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES";
		rd.tourID = "All";
		rd.ticketCategory = "All";
		rd.tourDate = "2008-10-10";
		rd.startTime = "6:00";
		rd.startTimeampm = "AM";
		rd.endTime = "6:00";
		rd.endTimeampm = "PM";
		rd.reportFormat = "PDF";
	}
}
