package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class CampRegistratioPermiReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>CampRegistratioPermiReport</b>
	 * Generated     : <b>Feb 2, 2010 3:30:09 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/02
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//pdf Email
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "CA Contract";
		login.location = "Administrator/California Parks and Recreation";

		rd.group = "Operational Reports";
		rd.reportName = "Camp Registration Permit Report";
		rd.locationID = "ANGEL ISLAND SP";
		rd.areaID = "*All";
		rd.startDate = "01/01/2008";
		rd.endDate = "01/08/2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "PDF";
	}
}
