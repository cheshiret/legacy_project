package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.identifier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case is used to verify not applicable customer identifier. so use a not applicable identifier info to add identifier,
 * this identifier status should be active and verify status should be not applicable.
 * @Preconditions:Please refer to http://wiki.reserveamerica.com/display/qa/Mock+Verifier
 * 1. prepare a customer firstly
 * 2. in this case, the identifier type = "VISA"; identifier number = CustIdenNotApp123
 *    because this identifier type in C_IDENTIFIER_TYPE. VERIFIABLE_IND=0 (select VERIFIABLE_IND from C_IDENTIFIER_TYPE where name = 'VISA')
 * @SPEC:<Validate Customer Identifier.UCS>
 * @Task#:AUTO-1012
 * 
 * @author vzhang
 * @Date  Jun 19, 2012
 */
public class ValidIdentifier_NotApp extends LicenseManagerTestCase{
	private CustomerIdentifier identifier = new CustomerIdentifier();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//go to customer identifier sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerIdentifierSubTab();
		
		//add not applicable customer identifier
		identifier.id = lm.safeAddCustomerIdentifier(identifier);
		//verify customer identifier status and verify status
		this.verifyIdentifierStatusAndIdentifierStatus();
		
		//clear up
		lm.changeIdentifierStatus(identifier.identifierType, identifier.identifierNum, "Remove");
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-CustIdenNotApp";
		cust.lName = "Test-CustIdenNotApp";
		
		identifier.identifierType = "VISA";
		identifier.identifierNum = "CustIdenNotApp123";
		identifier.country = "Canada";
	
		identifier.status = OrmsConstants.ACTIVE_STATUS;
		identifier.verifyStatus = OrmsConstants.NOTAPPLICABLE_STATUS;		
	}
	
	private void verifyIdentifierStatusAndIdentifierStatus(){
		LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
		logger.info("Verify customer identifier status and verify status info.");
		
		String value = custIdenPg.getIdentifierStatus(identifier.identifierType, identifier.identifierNum );
		if(!value.equals(identifier.status)){
			throw new ErrorOnDataException("Identifier Status is not correct", identifier.status, value);
		}else {
			logger.info("Identifier Status is correct.");
		}
		
		lm.verifyIdentifierVerifyStatusFromDB(identifier.id, identifier.verifyStatus , schema);		
	}

}
