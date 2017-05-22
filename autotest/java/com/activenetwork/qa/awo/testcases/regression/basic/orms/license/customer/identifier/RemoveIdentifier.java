/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.identifier;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This test case is used to verify a basic flow for remove customer identifier
 * @Preconditions:Need to prepare a customer at first
 * @SPEC:<Remove Customer Identifier>
 * @Task#:AUTO-804
 * 
 * @author ssong
 * @Date  Dec 28, 2011
 */
public class RemoveIdentifier extends LicenseManagerTestCase{

	private CustomerIdentifier identifier = new CustomerIdentifier();
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Identifiers");
		
		identifier.id = lm.addCustomerIdentifier(identifier);		
		lm.changeIdentifierStatus(identifier.identifierType, identifier.identifierNum, "Remove");
		if(custIdenPg.checkIdentifierExist(identifier.id)){
			throw new ErrorOnPageException("Remove Button Not Work,Identifier "+identifier.id+" Still Exists.");
		}
		
		lm.logOutLicenseManager();
	}


	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Identifier1";
		cust.lName = "QA-Identifier1";
		
		identifier.identifierType = "MS Drivers License";
		identifier.identifierNum = "12345678";
		identifier.state = "Mississippi";
	}
}
