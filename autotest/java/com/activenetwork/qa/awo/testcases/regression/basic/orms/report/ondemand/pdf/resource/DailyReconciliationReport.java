/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:verify Daily Reconciliation Report content using history data on report manager
 *              
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:Selection Criteria [TC:015312] 
 *       Report Header [TC:015337]  
 *       Report Body [TC:015338] 
 *       Daily Reconciliation Report - Add Ticket Type Print Counts [TC:030019]   
 * @Task#:Auto-2295,AUTO-2275 
 * 
 * @author szhou
 * @Date Jul 24, 2014
 */
public class DailyReconciliationReport extends ResourceManagerTestCase{

	@Override
	public void execute() {
        rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF EMAIL
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF ONLINE
//		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.group = "Operational Reports";
		rd.reportName = "Daily Reconciliation Report";
		rd.agencyID="NPS";
		rd.park = "MAMMOTH CAVE NATIONAL PARK TOURS";
		rd.startDate = "Mon Sep 1 2008";
		rd.includeSummary = "Yes";
		rd.reportFormat = "PDF";
	}

}
