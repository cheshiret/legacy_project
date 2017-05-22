package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ConcessionaireAnnualReservationReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptConcessionaireAnnualReservationReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/BEAVER CREEK (IDAHO)";//BEAVER CREEK (IDAHO) (ID)

		rd.group = "All";
		rd.reportName = "Concessionaire Annual Reservation Report";
		rd.yesNoFlag = "No";
		rd.supressEmptyLine = "Yes";
		rd.startDate="Wed Jul 1 2009";
		rd.endDate = "Wed Jul 1 2009";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
