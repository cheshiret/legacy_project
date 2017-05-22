/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:Selection Criteria UI [TC:053096]
 * @Task#:Auto-2306
 * 
 * @author Vivian
 * @Date  Jul 4, 2014
 */
public class POSBarcodeExportReport extends ResourceManagerTestCase{

	@Override
	public void execute() {
		templatesPath = "C:\\newhome\\ReportTemplates\\";
		rm.loginResourceManager(login);
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//Excel Online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);

		login.contract = "ID Contract";
		login.location = "Administrator - Auto/Idaho Contract";
		rd.group = "Operational Reports";
		rd.reportName = "POS Barcode Export Report";
        
		rd.agencyID="IDPR";
		rd.regionLocID = "All";
		rd.facilityID = "Bear Lake State Park";//must be a park name
		rd.facilityLocID = "All";
		rd.productClass = "All";
		rd.productSubClass = "All";

		rd.startDate = "Tue Jan 01 2013";
		rd.endDate = "Tue Dec 31 2013";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}

}
