package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class GiftCardSalesReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptGiftCardSalesReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);//Excel Online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
//		result = rm.verifyExcelReport(templatesPath, fileName);
		
		rd.reportBy="Detail";
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Online
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
//		result &= rm.verifyExcelReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "All";
		rd.reportName = "Gift Card Sales Report";//"Gift Card Sales Summary Report and Gift Card Sales Detail Report";//
		rd.giftCardLoc="SC parks";
		rd.paymentGrp="Cash";
		rd.reportBy="Summary";
		rd.startDate="Wed Aug 01 2012";
		rd.endDate = "Fri Aug 31 2012";
		
		rd.reportFormat="XLS";
		rd.deliveryMethod = "Online";
		
		rd.includeGcPayments = "Include";
		rd.rAFeeAccount = "Include"; //Include Refunds Issued to Gift Card
		
//		templatesPath = "C:\\ReportTemplates";
	}
}

