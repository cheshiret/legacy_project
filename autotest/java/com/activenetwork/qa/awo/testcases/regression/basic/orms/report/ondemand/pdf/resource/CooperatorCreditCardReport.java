package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class CooperatorCreditCardReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>CooperatorCreditCardReport</b>
	 * Generated     : <b>Feb 3, 2010 9:13:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/03
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.reportName = "Cooperator Credit Card Report";
		rd.facilityID = "Boundary Waters Canoe Area Wilderness (Reservations)";
		rd.cooperatorID  = "Sawtooth Outfitters";//"Sawtooth Outfitters";//"WOLFSBERGER";
		rd.startDate = "Tue Jan 1 2008";
		rd.endDate = "Thu Jan 3 2008";
		rd.reportFormat = "PDF";
	}
}

