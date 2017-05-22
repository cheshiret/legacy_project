package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class PermitConfirmationLetter extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptPermitConfirmationLetter</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "Permit Confirmation Letter";
		rd.facilityID="Boundary Waters Canoe Area Wilderness (Reservations)";
		rd.resNumber="6-343224";
		rd.startDate = "Mon Jun 1 2009";
		rd.endDate = "Mon Jun 15 2009";
		rd.maxLetters="1";

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}
}
