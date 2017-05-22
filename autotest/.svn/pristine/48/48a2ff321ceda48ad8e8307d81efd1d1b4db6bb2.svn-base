package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ParkProfileReport_SectionA extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptParkProfileReport_SectionA</b>
	 * Generated     : <b>Jul 21, 2009 5:55:31 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/21
	 * @author QA
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
//		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

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
		rd.reportSection="Section A - All";
		
		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Fri Aug 17 2007";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
		
		rd.fileName = rd.reportName+rd.reportSection;
		saveAsName = rd.fileName.replaceAll(" ", "")+"_Mail";
	}
}
