package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class SCParkVenueDetailReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptYieldManagementReport</b>
	 * Generated     : <b>Jul 27, 2010 17:09:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/07/27
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//xls online
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "Financial Reports";
		rd.reportName = "SC Park Revenue Detail Report";	
		rd.agencyID = "SC parks";
		rd.facilityID = "ANDREW JACKSON";
		rd.startDate = "Mon Jul 16 2007";
		rd.endDate = "Wed Jul 18 2007";
		rd.includeAdjustments = "No";
		rd.includeRAFee = "No";
		rd.yesNoFlag = "Yes";  //include Non-Depositable items
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "ONLINE";
	}
}
