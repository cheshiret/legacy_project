/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.license;

import java.io.File;

import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This script cover vehicle and consumable order,check report correct from product revenue summary section
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:057524,062504
 * @Task#:Auto-2199
 * 
 * @author ssong
 * @Date  May 27, 2014
 */
public class DailySalesActivityBreakdownByCustomerWithProductRevenueSummary extends LicenseManagerTestCase{

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
		+ "DailySalesActivityBreakdownByCustomerWithRevenueSummary.pdf";
		return fileName;
	}
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		agentId = "646";
		enterDate = "WED APR 23 2014";
	}

}
