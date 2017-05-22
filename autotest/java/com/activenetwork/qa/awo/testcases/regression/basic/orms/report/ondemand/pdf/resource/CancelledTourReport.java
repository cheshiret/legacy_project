package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class CancelledTourReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>CancelledTourReport</b>
	 * Generated     : <b>Feb 3, 2010 12:35:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/03
	 * @author QA
	 */
  	public void execute() {

		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF EMAIL
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.group = "Operational Reports";
		rd.reportName = "Cancelled Tour Report";
		rd.facilityLocID = "VOYAGEURS NATIONAL PARK TOURS";
		rd.tourCategory = "Individual";
		rd.sortBy = "Time then Tour Name";
		rd.startDate = "Sun Jun 1 2008";
		rd.startTime = "12:00";
		rd.startTimeampm = "AM";
		rd.endTime = "12:00";
		rd.endTimeampm = "PM";
		rd.reportFormat = "PDF";
	}
}
