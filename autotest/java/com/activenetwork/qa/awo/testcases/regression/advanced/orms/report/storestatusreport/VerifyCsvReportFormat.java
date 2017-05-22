/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.storestatusreport;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.FileUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:TC:019679
 * @Task#:Auto-1394
 * 
 * @author ssong
 * @Date  Mar 18, 2013
 */
public class VerifyCsvReportFormat extends LicenseManagerTestCase{

	private ReportData rd = new ReportData();

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//run report and check CSV report format
		lm.selectOneRpt(rd.group, rd.reportName);
		String fileName = lm.runSpecialReport(rd, comparedPath);
		checkReportFormat(fileName);
		
		lm.logOutLicenseManager();
	}

	private void checkReportFormat(String fileName){
		String[] lines = FileUtil.readLines(fileName);
		if(!lines[0].matches("\".*\",\".*\",\".*\",\".*\",\".*\",\".*\",\".*\",\".*\"")){//no report header and report column, should display report content directly
			throw new ErrorOnPageException("CSV format report has no report Header,should display store info directly.");
		}
	}
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// report information
		rd.group = "All";
		rd.reportName = "Store Status Report";
		rd.storeStatus = new String[]{"Inactive"};
		rd.fileName = "StoreStatusReport"+"_CheckCSVFormat";
		
		rd.reportFormat = "CSV";
		
	}

}
