package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * Description   : Functional Test Script
 * @author QA
 */
public class VoucherUtilizationReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptVoucherUtilizationReport</b>
	 * Generated     : <b>Jul 23, 2009 5:55:03 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);
//		result=rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "VA Contract";
		login.location = "Administrator/Virginia Department of Conservation and Recreation";

		rd.group = "Financial Reports";
		rd.reportName = "Voucher Utilization Report";

		rd.voucherProgram = "All";
		rd.orderBy = "Transaction Date";

		rd.startDate = "Fri Aug 1 2008";
		rd.endDate = "Fri Aug 1 2008";

		rd.reportFormat = "PDF";
	}
}
