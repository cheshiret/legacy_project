package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ConfirmationLetterReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptConfirmationLetterReport</b>
	 * Generated     : <b>Jul 22, 2009 10:44:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
//		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject),rd.reportFormat,errorResultPath);
//		rm.verifyReportCorrect(dhtmlResult, result, excelResult);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "All";
		rd.reportName = "Confirmation Letter Report";
		rd.resNumber="2-3983825";
		rd.startDate = "Sat Jul 5 2008";
		rd.endDate="Thu Jul 10 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";

	}
}
