package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class UnearnedRevenueReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptUnearnedRevenueReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "Unearned Revenue Report";
		rd.recipientType="Concessionaire";
		rd.startDate = DateFunctions.getDateAfterToday(-35);

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";

	}
}
