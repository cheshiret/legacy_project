package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerInputDateOfBirthWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify the System required user input Date Of Birth value when an INACTIVE identifier was changed to VERIFIED 
 * @Preconditions:1. http://wiki.reserveamerica.com/display/qa/Mock+Verifier
 * 							2. table ALL_C_IDENTIFIER_TYPE in COMMON schema
 * 							3. table C_CUST_CLASS_ID_TYPE in MS schema
 * 							4. update C_CUST_CLASS set OPTIONAL_DOB_IND=1 where name='Business';(non-individual)
 * @SPEC: PCR 2926, <<Activate Customer Identifier.doc>>, TC003840 step6 & 7 & 14 & 15.
 * 								<<Customer Profile Management UI.doc>>, TC003933 step 103-105, 118-120, 128-130.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 12, 2012
 */
public class RequiredDOBForVerifiedIdentifier extends LicenseManagerTestCase {
	private LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage.getInstance();
	private String originalVerifiedIdentifierNum;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		//check if customer exists in system
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		if(cust.custNum.length() < 1) {
			cust.custNum = lm.createNewCustomer(cust);
		}
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustomerSubTabPage("Identifiers");
		
		//1. this step also cover TC3933 Step 118-120, add a VERIFIED identifier without DOB
		//before update DOB, check and remove existing identifier record to avoid conflict
		boolean exists = identifierPage.checkIdentifierExistByTypeAndNumber(cust.identifier.identifierType, cust.identifier.identifierNum);
		if(exists) {
			lm.removeCustIdentifier(cust.identifier.identifierType, cust.identifier.identifierNum);
		}
		this.updateCustomerDateOfBirth("");
		this.addIdentifier(cust.identifier, true);
		this.inputDateOfBirth(cust.dateOfBirth);
		String statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		lm.removeCustIdentifier(cust.identifier.identifierType, cust.identifier.identifierNum);
		
		//2. add a valid identifier record: MS Drivers License, System sets the status of the customer identifier to "Verified"
		cust.identifier.id = lm.safeAddCustomerIdentifier(cust.identifier);
		statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		//3. deactivate the above identifier record
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Deactivate");
		
		//4. update customer Date Of Birth as null
		this.updateCustomerDateOfBirth(StringUtil.EMPTY);
		
		//5. this step also cover TC3933 Step103-105. activate back the identifier record, System pop-up with Date Of Birth input
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Activate");
		this.inputDateOfBirth(cust.dateOfBirth);
		statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		//6. this step cover TC3933 Step128-130, edit customer identifier
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Deactivate");
		cust.identifier.identifierNum = "CHE" + String.valueOf(DateFunctions.getCurrentTime());
		lm.updateCustomerIdentifier(cust.identifier);
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Activate");
		this.updateCustomerDateOfBirth(StringUtil.EMPTY);
		cust.identifier.identifierNum = originalVerifiedIdentifierNum;
		lm.updateCustomerIdentifier(cust.identifier);
		this.inputDateOfBirth(cust.dateOfBirth);
		statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		/**
		 * IMPORTANT: the date of birth must be verified as the corresponding identifier info
		 * refer to:  http://wiki.reserveamerica.com/display/qa/Mock+Verifier
		 */
		//verifier.ms-mock-identifier.valid.811915158=1995-06-04
		cust.dateOfBirth = "1995-06-04";
		cust.identifier.identifierType = "MS Drivers License";
		cust.identifier.identifierNum = "811915158";
		cust.identifier.state = "Mississippi";
		
		originalVerifiedIdentifierNum = cust.identifier.identifierNum;
		
		//customer info
		cust.fName = "Required";
		cust.mName = "DateOfBirth";
		cust.lName = "ForIdentifier";
		cust.customerClass = OrmsConstants.BUSINESS_CUSTOMER_CLASS;//PCR 2926 is limited for Non-Individual customer classes
		cust.businessName = "RequiredDOBForIdentifier";
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.email = "hfishing@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
	}
	
	/**
	 * Update customer Date Of Birth in customer detail page
	 * @param dob
	 */
	private void updateCustomerDateOfBirth(String dob) {
		logger.info("Update customer Date Of Birth to " + dob);
		
		identifierPage.setDateOfBirth(dob);
		identifierPage.clickApply();
		ajax.waitLoading();
		identifierPage.waitLoading();
	}
	
	/**
	 * Verify identifier status
	 * @param expected
	 * @param actual
	 */
	private void verifyIdentifierStatus(String expected, String actual) {
		boolean result = MiscFunctions.compareResult("Identifier Status", expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The identifier status should be " + expected);
		} else logger.info("Identifier status is correct.");
	}
	
	/**
	 * Add customer identifier
	 * @param i
	 * @param safeAdd
	 */
	private void addIdentifier(CustomerIdentifier i, boolean safeAdd) {
		LicMgrAddIdentifiersPage addIdentifier = LicMgrAddIdentifiersPage.getInstance();
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance();
		LicMgrCustomerInputDateOfBirthWidget inputDOBPage = LicMgrCustomerInputDateOfBirthWidget.getInstance();
		
		logger.info("Add Customer Identifier " + i.identifierType + " = " + i.identifierNum);
		
		if (safeAdd && identifierPage.checkIdentifierExistByTypeAndNumber(i.identifierType, i.identifierNum)) {
			lm.removeCustIdentifier(i.identifierType, i.identifierNum);
		}
		
		identifierPage.clickAddIdentifier();
		addIdentifier.waitLoading();
		addIdentifier.setIdentifier(i);
		addIdentifier.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(inputDOBPage, confirmDialogWidget, identifierPage);
		if(page == confirmDialogWidget) {
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			browser.waitExists(inputDOBPage, identifierPage);
		}
	}
	
	/**
	 * Input date of birth during adding customer identifier process
	 * @param dob
	 */
	private void inputDateOfBirth(String dob) {
		LicMgrCustomerInputDateOfBirthWidget inputDOBPage = LicMgrCustomerInputDateOfBirthWidget.getInstance();
		
		logger.info("Verify and input Date Of Birth.");
		String type = inputDOBPage.getMessage().split("\\\"")[1].split("\\\"")[0].trim();
		if(!type.equals(cust.identifier.identifierType)) {
			throw new ErrorOnPageException("The expected System required input date of birth value for Identifier Type - " + cust.identifier.identifierType + ", not " + type);
		} else logger.info("Actual identifier type is correct - " + type);
		
		inputDOBPage.setDateOfBirth(dob);
		inputDOBPage.clickOK();
		ajax.waitLoading();
		identifierPage.waitLoading();
	}
}
