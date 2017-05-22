package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class DepositReconciliationReport extends ResourceManagerTestCase
{
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
		rm.runSpecialRpt(rd, comparedPath);//xls email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//xls online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.group = "Financial Reports";
		rd.reportName = "Deposit Reconciliation Report";
		rd.agencyID = "USACE";
		rd.facilityID = "HIDDEN VIEW";//HIDDEN VIEW (CA)
		rd.startDate = "Mon Dec 29 2008";
		rd.endDate = "Tue Dec 30 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
