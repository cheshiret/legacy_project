package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ConcessionaireUnearnedRevenueReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptMonthlyTicketingRecipientDistributionReport</b>
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
		login.location = "Administrator/BEAVER CREEK (IDAHO)";

		rd.group = "All";
		rd.reportName = "Concessionaire Unearned Revenue Report";
		rd.startDate=DateFunctions.getDateAfterToday(-15);
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}

