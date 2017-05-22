package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class SevenDayCampersReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rpt7DayCampersReport</b>
	 * Generated     : <b>Jul 20, 2009 1:06:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * @since  2009/07/20
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		//PDF EMAIL
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName + DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);
//		result = rm.verifyPdfReport(templatesPath, fileName);
//		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "7-Day Campers Report";
		rd.locationID = "CROFT";
		rd.areaID = "*All";
		rd.orderBy = "Loop then Site";
		rd.showEmptySites = "Yes";
		rd.startDate = "07/01/2007";
		rd.reportFormat = "PDF";
	}
}
