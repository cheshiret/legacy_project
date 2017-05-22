package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class RAFinanceSalesReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptRAFinanceSalesReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);	

		//Excel: Delivered via E-mail through Resource Manager. 
		//please check doc,RA Finance Sales Report Ver. 1.6
	//	rd.deliveryMethod = "Online";
	//	rm.selectOneRpt(rd.group, rd.reportName);
	//	fileName = rm.runSpecialRpt(rd, comparedPath);
	//	result = rm.verifyExcelReport(templatesPath, fileName);
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NY Contract";
		login.location = "Administrator/Contract";

		rd.group = "Financial Reports";
		rd.reportName = "RA Finance Sales Report";	
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Tue Jul 1 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";

	}
}
