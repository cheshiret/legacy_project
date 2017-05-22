package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ReservationClosureReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptReservationClosureReport</b>
	 * Generated     : <b>Jul 23, 2009 12:52:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
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

		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		rd.group = "Operational Reports";
		rd.reportName = "Reservation Closure Report";
        
		rd.agencyID="OPRHP";
		rd.regionLocID = "All";
		rd.facilityLocID = "All";

		rd.startDate = "Sun Jun 1 2008";
		rd.endDate = "Mon Jun 30 2008";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";

	}
}
