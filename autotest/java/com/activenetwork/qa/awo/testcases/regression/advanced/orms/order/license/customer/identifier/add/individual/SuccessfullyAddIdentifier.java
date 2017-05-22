package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.identifier.add.individual;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerIdentifiersPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify successfully add identifiers and verify verified-status
 * @Preconditions: Have Individual customer with fName: QA-Customer1, mName:QaTest-CusotmerProfile-1,lName:TEST-Profile1 and date of birth is '30-Dec-1966'
 * @Note: Split case: AddIndentifierForNoneIndividualCustomer
 * @SPEC: Add Customer Identifier
 * @Task#:
 * 
 * @author QA
 * @Date  Feb 2, 2012
 */
public class SuccessfullyAddIdentifier extends LicenseManagerTestCase{
	private CustomerIdentifier identifier = new CustomerIdentifier();
	private LicMgrCustomerIdentifiersPage custIdenPg = LicMgrCustomerIdentifiersPage.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Identifiers");

		identifier.id = lm.addCustomerIdentifier(identifier);
		custIdenPg.verifyIdentifierList(identifier);

		lm.gotoIdentifierDetailPage(identifier.id);
		lm.verifyIdentifierFromDetailPg(identifier);

		lm.changeIdentifierStatus(identifier.identifierType, identifier.identifierNum, "Remove");
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
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.lName = "TEST-Profile1";
		cust.dateOfBirth = "Apr 04 1976";

		//Identifier info
		identifier.status = OrmsConstants.ACTIVE_STATUS;
		identifier.identifierType = "Green Card";
		identifier.identifierNum = "1N5T2I4V6M0";
		identifier.state = "";
		identifier.country = "Canada";
		identifier.createDate = DateFunctions.getToday();
		identifier.creationApp = "LicenseManager";
		identifier.createUser = login.userName;
	}
}
