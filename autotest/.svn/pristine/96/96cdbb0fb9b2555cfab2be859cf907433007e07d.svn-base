/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.license;

import java.io.File;

import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: This is basic report template compare strategy to compare new generated report with given template
 * @Preconditions: this test case used to test replacement privilege
 *  the report section for breakdown by payment type,whether all credit cards pay changed to Charge Cards depends on DB configuration
 *  query by: select * from  x_prop where name = 'GroupCreditCard';
 *  [Shane:20140527],update script to include product revenue summary section in the report and covered privilege order
 * @SPEC:[042566,042469,042537,042539,059887]
 * @Task#:Auto-1157
 * 
 * @author ssong
 * @Date  Aug 8, 2012
 */
public class DailySalesActivityBreakdownByCustomerReportWithReplaceData extends LicenseManagerTestCase{

	private String agentId, enterDate;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoVendorSearchPg();
		lm.gotoAgentDetailPgFromVendorSearchPg(agentId);
		lm.gotoDailySalesActivitySubPgFromAgentDetailPg("Customer");
		String fileName = initializeFile(comparedPath);
		lm.printActivityByCustomerReport(enterDate, fileName,true);
		boolean correct = lm.verifyPdfReport(templatesPath, fileName);
		if(!correct){
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
		lm.logOutLicenseManager();
	}

	private String initializeFile(String path){
		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new RuntimeException("Failed to create directory " + path);
			}
		}
		String fileName = file.getAbsolutePath() + "\\"
		+ "DailySalesActivityBreakdownByCustomerWithReplaceData.pdf";
		return fileName;
	}
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		agentId = "654";
		enterDate = "Wed Jul 11 2012";
	}
}
