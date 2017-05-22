package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class LotteryResultReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptLotteryResultReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
//		rd.deliveryMethod = "Online";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName =rm.runSpecialRpt(rd, comparedPath);//Excel Online
//		result = rm.verifyExcelReport(templatesPath, fileName);
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Lottery Results Report";
		rd.lotteryName = "BWCAW Lottery 2009";
		rd.lotterySched = "ID 284497205 (12/01/2008 - 01/15/2009 )";//"ID 284497205 12/01/2008 - 01/15/2009";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
