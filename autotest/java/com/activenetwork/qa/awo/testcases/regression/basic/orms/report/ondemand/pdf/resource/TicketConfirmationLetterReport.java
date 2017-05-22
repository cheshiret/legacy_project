package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * Description   : Functional Test Script
 * @author QA
 */
public class TicketConfirmationLetterReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptTicketConfirmationLetterReport</b>
	 * Generated     : <b>Jul 23, 2009 3:22:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 */
	public void execute() {

		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "Ticket Confirmation Letter";

		rd.facilityLocID = "CROFT";
		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Fri Aug 17 2007";
		rd.maxLetters = "10";
		rd.doNotEmail = "false";
		rd.doNotFax = "false";

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
	}
}
