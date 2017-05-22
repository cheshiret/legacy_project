/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:This test cases used to cover PCR 2665,a void payment should be included when run report on void payment date 
 * @Preconditions: Void a payment at first and store the void date and payment id in qa_automation table
 * @SPEC:TC:037390
 * @Task#:AUTO-1114
 * 
 * @author ssong
 * @Date  Jun 21, 2012
 */
public class SCParkVenueDetailReport_WithVoidPmt extends ResourceManagerTestCase{
	
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//xls online
		result =rm.skipVerifyOnlineReport(templatesPath, fileName);
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
		rd.startDate = "Sun Jul 22 2007";
		rd.endDate = "Sun Jul 22 2007";
		rd.includeAdjustments = "No";
		rd.includeRAFee = "No";
		rd.yesNoFlag = "Yes";  //include Non-Depositable items
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "ONLINE";
		rd.fileName = rd.reportName+"_VoidPayment";
	}
}
