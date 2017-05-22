package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 * 
 * Notice: PCR-2793 per Spec: "Park Profile Report" in ORMS 3.02: "\ufffd	Remove current report permissions from Resource Manager and System Manager", so inactive test cases in DB for QA4, mark ParkProfileReport need to be updated
 * test this report by run Park Profile Report-Section A - Closure Summary
 */
public class ClosureSummaryReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptClosureSummaryReport</b>
	 * Generated     : <b>Jul 22, 2009 8:31:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */
	private String saveAsName;
	
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "Email";
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF EMAIL
		
//		rd.deliveryMethod = "Online";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF Online
//		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,saveAsName, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.reportName = "Park Profile Report";

		rd.agencyID = "SC parks";
		rd.facilityID = "CROFT";
		rd.reportSection = "Section A - Closure Summary";

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
		
		rd.startDate = "7/1/2007";
		rd.endDate = "7/30/2007";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		rd.fileName = rd.reportName+rd.reportSection;
		saveAsName = rd.fileName.replaceAll(" ", "")+"_Mail";
	}
}
