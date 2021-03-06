package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.identifier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case is used to verify valid customer identifier. so use a valid identifier info to add identifier,
 * this identifier status should be verifier and verify status should be verifier.
 * @Preconditions:Please refer to http://wiki.reserveamerica.com/display/qa/Mock+Verifier to prepare customer and identifier info
 * such as verifier.ms-mock-identifier.valid.VIG555555=1955-07-01
 * 1. prepare a customer firstly, and the date of birth is 1955-07-01
 * 2. in this case, the identifier type = "MS Drivers License"; identifier number = VIG555555
 * @SPEC:<Validate Customer Identifier.UCS>
 * @Task#:AUTO-1012
 * 
 * @author vzhang
 * @Date  Jun 19, 2012
 */
public class ValidIdentifier_Verified extends LicenseManagerTestCase{
	private CustomerIdentifier identifier = new CustomerIdentifier();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//go to customer identifier sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerIdentifierSubTab();
		
		//add valid customer identifier
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
		cust.fName = "QA-CustIdenVerified";
		cust.lName = "Test-CustIdenVerified";
		cust.dateOfBirth = "1956-04-19";//VERIFIED
		
		identifier.identifierType = "MS Drivers License";
		identifier.identifierNum = "993999032";//VERIFIED
		identifier.state = "Mississippi";
	
		identifier.status = OrmsConstants.VERIFIED_STATUS;
		identifier.verifyStatus = OrmsConstants.VERIFIED_STATUS;		
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
