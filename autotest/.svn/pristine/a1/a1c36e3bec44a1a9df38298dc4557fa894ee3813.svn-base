package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class LotteryApplicationDetailsReport extends ResourceManagerTestCase{

	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		//run lottery application details report
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
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
		rd.reportName = "Lottery Application Details Report";
		rd.lotteryName = "BWCAW Lottery 2009";
		rd.lotterySched = "ID 284497205 (12/01/2008 - 01/15/2009 )";//"ID 284497205 12/01/2008 - 01/15/2009";
		rd.orderStatus = "Voided";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
