package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class NRRSInvoiceDetailReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>NRRSInvoiceDetailReport</b>
	 * Generated     : <b>Feb 24, 2010 2:31:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/24
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Financial Reports";
		rd.reportName = "NRRS Invoice Detail Report";
		rd.invoice = "#13822463 - 03-01-2007 - 03-31-2007 - NRRS";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}

