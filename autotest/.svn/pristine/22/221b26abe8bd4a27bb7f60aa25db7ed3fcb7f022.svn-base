package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier.add.individual;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddIdentifiersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify identifier information after canceling adding customer identifier
 * @Preconditions: Have Individual customer with fName: QA-Customer1, mName:QaTest-CusotmerProfile-1,lName:TEST-Profile1 and date of birth is '30-Dec-1966'
 * @Note: Split case: AddIndentifierForNoneIndividualCustomer
 * @SPEC: Add Customer Identifier
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 2, 2012
 */
public class CancellyAddIdentifier extends LicenseManagerTestCase{
	private CustomerIdentifier identifier = new CustomerIdentifier();

	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Identifiers");

		this.cancelToAddIndentifier();

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";
		cust.fName = "QA-Customer1";
		cust.mName = "QaTest-CusotmerProfil-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";

		//Identifier info
		identifier.identifierType = "Green Card";
		identifier.identifierNum = "N15T2I4V6M0";
		identifier.country = "Canada";
	}

	private void cancelToAddIndentifier(){
		LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();
		LicMgrAddIdentifiersPage addIdenPg = LicMgrAddIdentifiersPage.getInstance();
		lm.switchIndentifiersAndAddIdentifiersPg(custIdenPg);
		addIdenPg.setIdentifier(identifier);
		addIdenPg.clickCancel();
		custIdenPg.waitLoading();   
		if(custIdenPg.getIdentifierRow(identifier.id, identifier.identifierType, identifier.identifierNum)!=-1){
			throw new ErrorOnPageException("It should be successfully add the customer with " +
					"customer type("+identifier.identifierType+") and customer number("+identifier.identifierNum+")");
		}
	}
}
