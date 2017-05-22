package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ReservationDetailsReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>ReservationDetailsReport</b>
	 * Generated     : <b>Feb 25, 2010 12:12:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/25
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//Excel Online
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
		rd.reportName = "Reservation Details Report";
		rd.agencyID = "USFS";
		rd.facilityID = "RYAN PARK";//RYAN PARK (WY)
		rd.startDate = "Thu May 1 2008";
		rd.endDate = "Sat May 31 2008";
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
