package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.add;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddCertificationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify whether the error messages displayed at the top of Add Certification Widget are correct or not when the entries are invalid
 * @Preconditions: Need an existing customer.
 * @SPEC: <<Add Customer Certification.UCS>>
 * @Task#: AUTO-709
 * 
 * @author qchen
 * @Date  Sep 7, 2011
 */
public class VerifyErrorMessage extends LicenseManagerTestCase {
	private Customer customer = new Customer();
	private Customer mergedStatusCustomer = new Customer();
	private Certification certification = new Certification();
	private Certification existingCertification = new Certification();
	private LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
	private LicMgrAddCertificationWidget addCertificationWidget = LicMgrAddCertificationWidget.getInstance();
	private String errorMsg_certificationNumHasNotBeenSpecified = "Certification Number is required. Please specify the Certification Number.";
	private String errorMsg_effectiveFromDateHasNotBeenSpecified = "Effective From Date is required. Please specify the Effective From Date.";
	private String errorMsg_effectiveToDateIsLessThanFromDate = "Effective To Date must be on or after Effective From Date. Please re-enter.";
	private String errorMsg_alreadyExistingCertificationRecord = "A Certification with the same Certification Type, Certification Number, Effective From Date and Effective To Date already exists for this Customer. Please verify.";
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCertificationFromCustomerDetailsPg();
		
		//1. Certification Number has not been specified
		certification.num = "";
		String actualMsg = lm.addCustomerCertification(certification, true);
		addCertificationWidget.clickCancel();
		ajax.waitLoading();
		certificationPage.waitLoading();
		runningResult &= this.verifyErrorMessage(errorMsg_certificationNumHasNotBeenSpecified, actualMsg);
		
		//2. Effective From Date has not been specified
		certification.num = "AUTO" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = "";
		actualMsg = lm.addCustomerCertification(certification, true);
		addCertificationWidget.clickCancel();
		ajax.waitLoading();
		certificationPage.waitLoading();
		runningResult &= this.verifyErrorMessage(errorMsg_effectiveFromDateHasNotBeenSpecified, actualMsg);
		
		//3. Effective From Date is not a valid date
		runningResult &= this.verifyDateComponentValid(true, new String[]{"2012-12-32", "2012-13-30", "certification"});
		
		//4. Effective To Date has been specified, and is not a valid date
		runningResult &= this.verifyDateComponentValid(false, new String[]{"2012-12-00", "!@#$%^"});
		
		//5. Effective To Date has been specified, and is less than Effective From Date
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = DateFunctions.getDateAfterGivenDay(certification.effectiveFrom, -1);
		actualMsg = lm.addCustomerCertification(certification, true);
		addCertificationWidget.clickCancel();
		ajax.waitLoading();
		certificationPage.waitLoading();
		runningResult &= this.verifyErrorMessage(errorMsg_effectiveToDateIsLessThanFromDate, actualMsg);
		
		//6. There is already an existing "Non-Deleted" Customer Certification with any Status other than "Removed" for
		//the same Customer Profile with the same values for the following: Certification Type, Certification Number, Effective From Date,
		//and Effective To Date(if specified)
		existingCertification.num = certification.num;
		existingCertification.id = lm.addCustomerCertification(existingCertification, true);
		
		certification.effectiveFrom = existingCertification.effectiveFrom;
		certification.effectiveTo = existingCertification.effectiveTo;
		actualMsg = lm.addCustomerCertification(certification, true);
		addCertificationWidget.clickCancel();
		ajax.waitLoading();
		certificationPage.waitLoading();
		runningResult &= this.verifyErrorMessage(errorMsg_alreadyExistingCertificationRecord, actualMsg);
		
		//clean up environment
		lm.removeCustomerCertification(existingCertification.id, true);
		
		//final verification
		if(!runningResult) {
			throw new ErrorOnPageException("Checkpoints are NOT all passed, please refer the log for detail info.");
		} else {
			logger.info("All checkpoints are PASSED.");
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		customer.customerClass = "Individual";
		customer.fName = "QA-Advanced1";
		customer.lName = "TEST-Advanced1";
		customer.identifier.identifierType = "Tax ID";
		customer.identifier.identifierNum = "111111";
		
		mergedStatusCustomer.customerClass = "Individual";
		mergedStatusCustomer.fName = "Merged";
		mergedStatusCustomer.lName = "Merged";
		mergedStatusCustomer.identifier.identifierType = "Tax ID";
		mergedStatusCustomer.identifier.identifierNum = "222222";
		
		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = certification.effectiveFrom;
		
		existingCertification.status = certification.status;
		existingCertification.type = certification.type;
		existingCertification.effectiveFrom = certification.effectiveFrom;
		existingCertification.effectiveTo = certification.effectiveTo;
	}
	
	private boolean verifyErrorMessage(String expectedMsg, String actualMsg) {
		logger.info("Verify whether the error message is displayed correctly or not.");
		if(!expectedMsg.equalsIgnoreCase(actualMsg)) {
			logger.error("Error message - '" + expectedMsg + "' is displayed wrongly. The actual error message is - '" + actualMsg + "', but the expeted is - '" + expectedMsg + "'.");
			return false;
		} else {
			logger.info("Error message - '" + expectedMsg + "' is displayed correctly.");
			return true;
		}
	}
	
	private boolean verifyDateComponentValid(boolean isEffectiveFromDate, String invalidDates[]) {
		boolean result = true;
		certificationPage.clickAddCertification();
		ajax.waitLoading();
		addCertificationWidget.waitLoading();
		
		if((isEffectiveFromDate ? (!addCertificationWidget.verifyEffectiveFromDateFieldValid(invalidDates)) : (!addCertificationWidget.verifyEffectiveToDateFieldValid(invalidDates)))) {
			logger.error("Effective " + (isEffectiveFromDate ? "From":"To")  + " Date field works wrongly.");
			result &= false;
		} else {
			logger.info("Effective " + (isEffectiveFromDate ? "From":"To") + " Date field works correctly.");
		}
		addCertificationWidget.clickCancel();
		ajax.waitLoading();
		return result;
	}
}
