package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify existing customer identifier status is set to "Verified",
 * 						when input the valid "Date Of Birth" & customer identifier info
 * @Preconditions: http://wiki.reserveamerica.com/display/qa/Mock+Verifier
 * @SPEC: PCR 2926, <<Activate Customer Identifier.doc>>, TC003840 step6&step7.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 9, 2012
 */
public class ActivateExistCustIdentifier extends LicenseManagerTestCase {
	private LicMgrCustomerDetailsPage custDetailsPage = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage.getInstance();
	private String verifiedDOB, verifiedIdentifierType, verifiedIdentifierNum, verifiedIdentifierState;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		if(StringUtil.isEmpty(cust.custNum)) {
			cust.custNum = lm.createNewCustomer(cust);
		}
		lm.gotoCustomerDetailFromTopMenu(cust);
		//1. add a valid identifier record: MS Drivers License, System sets the status of the customer identifier to "Verified"
		this.prepareIdentifierInfo();
		this.updateCustomerDateOfBirth(cust.dateOfBirth);
		lm.gotoCustomerSubTabPage("Identifiers");
		cust.identifier.id = lm.safeAddCustomerIdentifier(cust.identifier);
		String statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		//2. update customer's date of birth as other value which is not verified with the identifier info
		//a. de-activate the VERIFIED identifier
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Deactivate");
		//b. update customer date of birth
		cust.dateOfBirth = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		this.updateCustomerDateOfBirth(cust.dateOfBirth);
		//c. activate the INACTIVE identifier
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Activate");
		statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.ACTIVE_STATUS, statusOnPage);
		
		//3. update customer date of birth back to verified date
		cust.dateOfBirth = verifiedDOB;
		this.updateCustomerDateOfBirth(cust.dateOfBirth);
		
		//4. update customer identifier which is invalid, System sets the status of the customer identifier to "Active"
		cust.identifier.identifierNum = "INV800517729";
		cust.identifier.id = lm.updateCustomerIdentifier(cust.identifier);
		statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.ACTIVE_STATUS, statusOnPage);

		//5. clean up
		lm.removeCustIdentifier(cust.identifier.identifierType, cust.identifier.identifierNum);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		/**
		 * IMPORTANT: the date of birth must be verified as the corresponding identifier info
		 * refer to:  http://wiki.reserveamerica.com/d isplay/qa/Mock+Verifier
		 */
		//verifier.ms-mock-identifier.valid.800517729=1984-02-02 ---- Update in Feb 25 2013
		verifiedDOB = "1984-02-02";
		verifiedIdentifierType = "MS Drivers License";
		verifiedIdentifierNum = "800517729";
		verifiedIdentifierState = "Mississippi";
		
		//customer info
		cust.fName = "Activate";
		cust.mName = "ExistCust";
		cust.lName = "Identifier";
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.businessName = "ActivateExistCustIdentifier";
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.includeAreaCode = true;//must be include area code for phone number which exceeds 10 digits
		cust.email = "hunting@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
	}
	
	private void prepareIdentifierInfo() {
		cust.dateOfBirth = verifiedDOB;
		
		cust.identifier.identifierType = verifiedIdentifierType;
		cust.identifier.identifierNum = verifiedIdentifierNum;
		cust.identifier.state = verifiedIdentifierState;
	}
	
	private void updateCustomerDateOfBirth(String dob) {
		logger.info("Update customer Date Of Birth to " + dob);
		custDetailsPage.clickEdit();
		ajax.waitLoading();
		custDetailsPage.waitLoading();
		custDetailsPage.setDateOfBirth(dob);
		custDetailsPage.clickApply();
		ajax.waitLoading();
		custDetailsPage.waitLoading();
	}
	
	private void verifyIdentifierStatus(String expected, String actual) {
		boolean result = MiscFunctions.compareResult("Identifier Status", expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The identifier status should be " + expected);
		} else logger.info("Identifier status is correct.");
	}
}
