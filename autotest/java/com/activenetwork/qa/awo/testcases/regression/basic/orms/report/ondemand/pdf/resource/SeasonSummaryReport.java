package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class SeasonSummaryReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptSeasonSummaryReport</b>
	 * Generated     : <b>Jul 23, 2009 2:30:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 * 
	 * Notice: PCR-2793 per Spec: "Park Profile Report" in ORMS 3.02: "\ufffd	Remove current report permissions from Resource Manager and System Manager"
	 * test this report by run Park Profile Report-Section A - Season Summary
	 */
	private String saveAsName;
	
	public void execute() {

		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);
//		result=rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,saveAsName, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "Park Profile Report";
		
		rd.agencyID = "SC parks";
		rd.facilityID = "CROFT";
		rd.reportSection="Section A - Season Summary";
		
		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Fri Aug 17 2007";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
		
		rd.fileName = rd.reportName+rd.reportSection;
		saveAsName = rd.fileName.replaceAll(" ", "")+"_Mail";
	}
}
