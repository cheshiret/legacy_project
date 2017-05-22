package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.remove;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This cases is used to verify remove customer certification
 * @Preconditions:Need run support script AddCustomerProfile info, when changed schedule
 * customer profile1: 		
 *      cust.fName = "QA-Merge";
 *		cust.lName = "TEST-Merge";
 * 
 * To get a Merged status customer: create A customer and B customer which the first name, last name and date of birth must be 
 * the same with A. Then merges these 2 customers, it wills get a Merged status customer. ---- Q
 * 
 * @SPEC:Remove customer certificaiton.USC
 * @Task#:Auto-718
 * 
 * @author VZhang
 * @Date  Aug 17, 2011
 */
public class VerifyByMergedCust extends LicenseManagerTestCase{

	private Certification certification = new Certification();
	private LicMgrCustomerCertificationPage certificationPg = LicMgrCustomerCertificationPage.getInstance();
	private boolean pass = true;
	
	public void execute() {
		//Login license manager		
		lm.loginLicenseManager(login);
		//go to customer details page which customer's status should be'Merged'.
		lm.gotoCustomerDetailFromTopMenu(cust);
		
		lm.gotoCertificationFromCustomerDetailsPg();
		this.verifyCertificationCannotBeOperated();
		
		if(!pass){
			throw new ErrorOnPageException("Some check point is not correct, please check error log.");
		}
		
		lm.logOutLicenseManager();		
	}

	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Merge";
		cust.lName = "TEST-Merge";
		cust.status = "Merged";
		
		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "AUTOTEST";
		certification.effectiveFrom = DateFunctions.getToday();
		certification.effectiveTo = DateFunctions.getDateAfterToday(30);	
	}
	
	private void verifyCertificationCannotBeOperated(){
		if(certificationPg.isAddCertificationButtonEnabled()){
			pass &= false;
			logger.error("Add Certification button should not enabled.");
		}
		
		if(certificationPg.isAllCertificationCheckBoxesEnabled()){
			pass &= false;
			logger.error("All check box should not enabled.");
		}
		
		if(certificationPg.isActivateButtonEnabled()){
			pass &= false;
			logger.error("Activate button should not enabled.");
		}
		
		if(certificationPg.isDeactivateButtonEnabled()){
			pass &= false;
			logger.error("Deactivate button should not enabled.");
		}
		
		if(certificationPg.isRemoveButtonEnabled()){
			pass &= false;
			logger.error("Remove button should not enabled.");
		}
	}
}
