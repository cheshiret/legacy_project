/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.license;

import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:102389,102390,102391,105762,102392,102393,102394,102395,105827
 * @Task#:Auto-2196
 * 
 * @author ssong
 * @Date  Jun 17, 2014
 */
public class CustomerInformationReport extends LicenseManagerTestCase{

	private String idType,idNum,licenseYearCount;
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(INDIVIDUAL_CUSTOMER_CLASS, idType, idNum);
		String fileName = lm.printCustomerRecords(licenseYearCount, comparedPath, caseName+"_LM","pdf");
		boolean correct = lm.verifyPdfReport(templatesPath, fileName);
		if(!correct){
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
		
		lm.logOutLicenseManager();
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		idType = MS_LICENSE_TYPE_MDWFP;
		String customerId = "22000192";
		idNum =  lm.getCustomerNumByCustId(customerId, schema);
		licenseYearCount = "2";
		
	}

}
