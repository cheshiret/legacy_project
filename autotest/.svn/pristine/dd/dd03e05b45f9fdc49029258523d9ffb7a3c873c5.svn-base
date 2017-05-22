package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class PermitReservationDetailDataReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptPermitReservationDetailDataReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 * Debug history: 2012/1/16
	 * Some order has empty Permit Category, these data will not be displayed in report...
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "Permit Reservation Details Data Report";
		rd.facilityLocID="Boundary Waters Canoe Area Wilderness (Reservations)";
		rd.startDate = "Mon Jun 1 2009";
		rd.endDate = "Mon Jun 1 2009";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
