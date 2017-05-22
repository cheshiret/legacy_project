package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * @LinkSetUp:None
 * @SPEC:[TC:015317] Report Header & Report Columns  
 * 	     [TC:015311] Report Body   
 * @Task#: Auto-2270
 * @author Phoebe Chen
 * @Date  July 25, 2014
 */
public class DailyReconciliationDetailReport extends ResourceManagerTestCase{

	@Override
	public void execute() {
       rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF EMAIL
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF ONLINE
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
//		result = rm.verifyPdfReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Daily Reconciliation Detail Report";

		rd.paymentGroup = "All";
		rd.startDate = "Sat Sep 01 2012";
		rd.agencyID = "NPS";
		rd.facilityID = rm.getFacilityName("72777", schema);   //VOYAGEURS NATIONAL PARK TOURS
		rd.reportFormat = "PDF";
		
//		templatesPath = "C:\\ReportTemplates";
	}

}
