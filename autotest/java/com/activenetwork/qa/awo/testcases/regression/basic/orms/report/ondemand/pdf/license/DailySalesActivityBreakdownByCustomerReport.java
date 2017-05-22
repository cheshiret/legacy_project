/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.license;

import java.io.File;

import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: This is basic report template compare strategy to compare new generated report with given template
 * @Preconditions:
 * @SPEC:[042468,042566,042565,042561,042464,042531,042469,042537,042541,042539,042538,042559,042446,042463,042434,59877]
 * @Task#:Auto-1157
 * 
 * @author ssong
 * @Date  Aug 7, 2012
 */
public class DailySalesActivityBreakdownByCustomerReport extends LicenseManagerTestCase{

	private String agentId, enterDate;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoVendorSearchPg();
		lm.gotoAgentDetailPgFromVendorSearchPg(agentId);
		lm.gotoDailySalesActivitySubPgFromAgentDetailPg("Customer");
		String fileName = initializeFile(comparedPath);
		lm.printActivityByCustomerReport(enterDate, fileName,false);
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
		+ "DailySalesActivityBreakdownByCustomerWithReturnDoc.pdf";
		return fileName;
	}
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		agentId = "61";
		enterDate = "Wed Jul 11 2012";
	}

}
