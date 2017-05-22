package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.dateofbirth;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify the error message on identify customer page, when inputing invalid value for 'Date of Birth' field
 * @Preconditions:
 * @SPEC: PCR 2926, <<Identify Customer.doc>>, TC004452 step4-10
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 8, 2012
 */
public class IdentifyCust extends LicenseManagerTestCase {
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String description, expected1, expected2;
	private boolean result = true;
	private TimeZone timeZone;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// update customer class - Business optional indicator as TRUE
//		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
//		lm.gotoIdentifyCustomerPage();
		//1. user doesn't input the DOB and click 'OK' button
		cust.dateOfBirth = "";
		String actualMsg = this.identifyCust(cust);
		result &= MiscFunctions.compareResult(description, expected1, actualMsg);
		
		//2. user inputs the DOB while it's not a valid date, click 'OK' button
		result &= homePg.verifyDateOfBirthTextFieldValid(new String[]{"123", "qwerty", "!@#$%^&"});
		
		//3. user inputs the DOB while it's more than 150 years in the past, click on 'OK' button
		cust.dateOfBirth = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(timeZone), -(12 * 151));
		actualMsg = this.identifyCust(cust);
		result &= MiscFunctions.compareResult(description, expected2, actualMsg);
		
		//4. user inputs the DOB while it's greater than the current date in contract time zone, click on 'OK' button
		//System persists the DOB on the Customer Profile
		cust.dateOfBirth = DateFunctions.getDateAfterToday(1, timeZone);
		String dateOfBirthOnPage = this.identifyCust(cust);
		result &= MiscFunctions.compareResult(description, cust.dateOfBirth, dateOfBirthOnPage);
		
		//final check
		if(!result) {
			throw new ErrorOnPageException("The checkpoints are NOT all passed. Please refer log for details.");
		} else logger.info("All checkpoints are PASSED.");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.identifier.identifierType = "US Drivers License";
		cust.identifier.identifierNum = "RA12345";
		cust.identifier.state = "Oregon";
		cust.residencyStatus = "Non Resident";
		
		description = "Date of Birth";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//expected1 = "The Date of Birth is required. Please enter the Date of Birth.";
		expected1 = "Date of Birth is required. Please specify the Date of Birth.";
		expected2 = "The Date of Birth cannot be more than 150 years in the past. Please re-enter.";
	}
	
	private String identifyCust(Customer customer) {
		LicMgrConfirmDialogWidget confirmDialog = LicMgrConfirmDialogWidget.getInstance();
		LicMgrNewCustomerPage customerProfilePage = LicMgrNewCustomerPage.getInstance();
		
//		identifyPage.identifyCustomer(customer);
//		identifyPage.clickOK();
//		ajax.waitLoading();
//		identifyPage.waitExists();
//		Object page = browser.waitExists(confirmDialog, identifyPage);
//		if(page == identifyPage) {
//			return identifyPage.getErrorMsg();
//		} else {
//			confirmDialog.clickOK();
//			ajax.waitLoading();
//			customerProfilePage.waitExists();
//			return customerProfilePage.getDateOfBirth();
//		}
		
		homePg.identifyCustomer(customer.customerClass, customer.dateOfBirth, customer.identifier.identifierType, customer.identifier.identifierNum, customer.identifier.country, customer.identifier.state);
		homePg.clickPurchasePrivilege();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialog, homePg);
		if(page == homePg) {
			return homePg.getErrorMessage();
		} else {
			confirmDialog.clickOK();
			ajax.waitLoading();
			customerProfilePage.waitLoading();
			return customerProfilePage.getDateOfBirth();
		}
	}
}
