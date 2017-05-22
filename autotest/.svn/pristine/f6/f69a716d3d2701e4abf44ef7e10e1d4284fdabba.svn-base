/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:This test cases used to cover PCR 2665,a void payment should be included when run report on void payment date 
 * @Preconditions: 
 * @SPEC:TC:037393
 * @Task#:AUTO-1114
 * 
 * @author ssong
 * @Date  Jun 21, 2012
 */
public class SCParkVenueConsolidatedReport_WithVoidPmt extends ResourceManagerTestCase{
	
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Financial Reports";
		rd.reportName = "SC Park Revenue Consolidated Report";	
		rd.agencyID = "SC parks";
		rd.facilityHQID = "ANDREW JACKSON";
		rd.startDate = "Sun Jul 22 2007";
		rd.endDate = "Sun Jul 22 2007";
		rd.includeAdjustments = "Include";
		rd.rAFeeAccount = "Include";
		rd.includeGcPayments = "Include";
		rd.fileName = rd.reportName+"_VoidPayment";
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}
}
