package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class FieldPermitNumberDailyReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptFieldPermitNumberDailyReport</b>
	 * Generated     : <b>Jul 27, 2009 5:07:54 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/27
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);

		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);// PDF EMAIL
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF ONLINE
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
		rd.reportName = "Field Permit Number Daily Report";
		rd.agencyLocID = "OPRHP";
		rd.regionLocID = "*All";
		rd.facilityLocID = "ALLEGANY STATE PARK";
		rd.endDate = "03/26/2007";
		rd.reportFormat = "PDF";
	}
}
