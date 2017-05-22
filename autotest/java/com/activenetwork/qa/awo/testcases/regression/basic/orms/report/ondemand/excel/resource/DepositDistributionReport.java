package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class DepositDistributionReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>DepositReconciliationReport</b>
	 * Generated     : <b>Feb 4, 2010 11:48:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/04
	 * @author QA
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
		
		rd.group = "Financial Reports";
		rd.reportName = "Deposit Distribution Report";
		rd.agencyID = "USACE";
		rd.facilityID = "HIDDEN VIEW";//HIDDEN VIEW (CA)
		rd.startDate = "Sun Jul 6 2008";
		rd.endDate = "Sun Jul 6 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
