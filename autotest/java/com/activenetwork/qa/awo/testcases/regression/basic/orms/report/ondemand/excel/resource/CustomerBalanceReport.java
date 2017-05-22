package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class CustomerBalanceReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptCustomerBalanceReport</b>
	 * Generated     : <b>Jul 22, 2009 8:36:39 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//XLS Online CAMPING
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//XLS email CAMPING

		rd.deliveryMethod = "Online";
		rd.productType = "POS";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//XLS Online POS
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rd.productType = "TICKETING";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//XLS Online TICKETING
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		//verify CAMPING Email report
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "All";
		rd.reportName = "Customer Balance Report";
		rd.agencyID = "SC parks";
		rd.regionID = "All";
		rd.facilityID = "All";
		rd.balanceType = "Balance Owing";
		rd.reservationStatus = "All";
		rd.collectionStatus = "All";
		rd.paymentStatus = "All";
		rd.orderStatus = "All";
		rd.startDate = "08/01/2008";
		rd.endDate = "08/31/2008";
		rd.dateType = "Order Date";
		rd.productType = "CAMPING";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}
}

