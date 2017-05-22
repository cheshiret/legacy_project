package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class FieldDepositReport extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptFieldDepositReport </b> Generated : <b>Jul 27,
	 * 2009 3:34:45 AM </b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/07/27
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);

		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);// PDF EMAIL
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);// PDF Online
//		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "Financial Reports";
		rd.reportName = "Field Deposit Report";
		rd.agencyID = "SC parks";
		rd.facilityLocID = "AIKEN";
		rd.startDate = "07/01/2007";
		rd.startTime = "12:00";
		rd.startTimeampm = "AM";
		rd.endDate = "07/31/2007";
		rd.endTime = "12:00";
		rd.endTimeampm = "PM";
		rd.reportFormat = "PDF";
	}
}

