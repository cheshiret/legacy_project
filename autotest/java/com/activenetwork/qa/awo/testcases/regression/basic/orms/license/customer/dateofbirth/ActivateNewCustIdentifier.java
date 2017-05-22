package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify a new customer identifier status is set to "Verified",
 * 						when input the valid "Date Of Birth" & customer identifier info
 * @Preconditions: 1. http://wiki.reserveamerica.com/display/qa/Mock+Verifier
 * 							2. table ALL_C_IDENTIFIER_TYPE in COMMON schema
 * 							3. table C_CUST_CLASS_ID_TYPE in MS schema
 * 							4. update C_CUST_CLASS set OPTIONAL_DOB_IND=1 where name='Business';(non-individual)
 * @SPEC: AUTO-940
 * @Task#: PCR 2926, <<Activate Customer Identifier.doc>>, TC003840 step14&step15.
 * 
 * @author qchen
 * @Date  Mar 9, 2012
 */
public class ActivateNewCustIdentifier extends LicenseManagerTestCase {
	private LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage.getInstance();
	private String verifiedDOB, verifiedIdetifierType, verifiedIdentifierNum, verifiedIdentifierState;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//precondition
		// update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		this.checkAndRemoveExistingCustIdentifierInfo(cust);
		
		//1. add a new customer with valid "Date Of Birth" value
		cust.custNum = lm.createNewCustomer(cust);
		
		//2. add a valid identifier record: MS Drivers License, System sets the status of the identifier as "Verified"
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass, cust.status);
		lm.gotoCustomerSubTabPage("Identifiers");
		cust.identifier.id = lm.safeAddCustomerIdentifier(cust.identifier);
		String statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		//3. update the customer date of birth as an invalid value, System sets the status of identifier as "Active"
		//a. de-activate the VERIFIED identifier
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Deactivate");
		//b. update customer date of birth
		cust.dateOfBirth = DateFunctions.getDateAfterToday(-10);
		this.updateCustomerDateOfBirth(cust.dateOfBirth);
		//c. activate the INACTIVE identifier
		lm.changeIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum, "Activate");
		statusOnPage = identifierPage.getIdentifierStatus(cust.identifier.identifierType, cust.identifier.identifierNum);
		this.verifyIdentifierStatus(OrmsConstants.ACTIVE_STATUS, statusOnPage);
		
		//3. update customer date of birth back to verified date
		cust.dateOfBirth = verifiedDOB;
		this.updateCustomerDateOfBirth(cust.dateOfBirth);
		
		//4.remove original customer identifier then add a new one as invalid (with invalid identifier type),
		//System sets the status of identifier as "Active"
		cust.identifier.identifierType = "US Drivers License";
		cust.identifier.state = "New York";
		cust.identifier.id = lm.addCustomerIdentifier(cust.identifier);
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
		 * refer to:  http://wiki.reserveamerica.com/display/qa/Mock+Verifier
		 */
		//verifier.ms-mock-identifier.valid.800443985=1965-06-20 ---- update at Feb 25 2013
		verifiedDOB = "1965-06-20";
		verifiedIdetifierType = "MS Drivers License";
		verifiedIdentifierNum = "800443985";
		verifiedIdentifierState = "Mississippi";
		
		//customer info
		int sequence = DataBaseFunctions.getEmailSequence();
		cust.fName = "Activate" + sequence;
		cust.mName = "NewCust" + sequence;
		cust.lName = "Identifier" + sequence;
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.businessName = "ActivateNewCustIdentifier" + sequence;
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.email = "fishing@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		
		cust.dateOfBirth = verifiedDOB;
		cust.identifier.identifierType = verifiedIdetifierType;
		cust.identifier.identifierNum = verifiedIdentifierNum;
		cust.identifier.state = verifiedIdentifierState;
	}
	
	private void checkAndRemoveExistingCustIdentifierInfo(Customer c) {
		LicMgrCustomersSearchPage custSearchPage = LicMgrCustomersSearchPage.getInstance();
		LicMgrCustomerDetailsPage custDetailPage = LicMgrCustomerDetailsPage.getInstance();
		LicMgrCustomerIdentifiersPage identifierPage = LicMgrCustomerIdentifiersPage.getInstance();
		
		logger.info("Check and remove existing customer identifier(Type='" + c.identifier.identifierType + "', Num='" + c.identifier.identifierNum + "').");
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		custSearchPage.selectCustClass(c.customerClass);
		ajax.waitLoading();
		custSearchPage.selectLicenseType(c.identifier.identifierType);
		ajax.waitLoading();
		custSearchPage.setLicenseNum(c.identifier.identifierNum);
		custSearchPage.selectStatus(0);
		custSearchPage.clickSearch();
		ajax.waitLoading();
		List<List<String>> infos = custSearchPage.getAllCustomersinfoInCustList();
		if(infos.size() > 0) {
			for(int i = 0; i < infos.size(); i ++) {
				logger.info("----Go to customer(#=" + infos.get(i).get(0) + ") detail page.");
				custSearchPage.clickCustomerNumber(infos.get(i).get(0));
				ajax.waitLoading();
				custDetailPage.waitLoading();
				custDetailPage.clickIdentifiersTab();
				ajax.waitLoading();
				identifierPage.waitLoading();
				if(identifierPage.checkIdentifierExistByTypeAndNumber(c.identifier.identifierType, c.identifier.identifierNum)) {
					logger.info("----Remove customer identifier(Type='" + c.identifier.identifierType + "', Num='" + c.identifier.identifierNum + "').");
					lm.removeCustIdentifier(c.identifier.identifierType, c.identifier.identifierNum);
				}
				custDetailPage.clickOK();
				ajax.waitLoading();
				custSearchPage.waitLoading();
			}
		}
	}
	
	private void updateCustomerDateOfBirth(String dob) {
		logger.info("Update customer Date Of Birth to " + dob);
		identifierPage.clickEdit();
		ajax.waitLoading();
		identifierPage.setDateOfBirth(dob);
		identifierPage.clickApply();
		ajax.waitLoading();
		identifierPage.waitLoading();
	}
	
	private void verifyIdentifierStatus(String expected, String actual) {
		boolean result = MiscFunctions.compareResult("Identifier Status", expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The identifier status should be " + expected);
		} else logger.info("Identifier status is correct.");
	}
}
