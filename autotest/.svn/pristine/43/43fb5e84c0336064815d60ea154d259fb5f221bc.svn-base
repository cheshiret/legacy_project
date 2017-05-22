package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification.add;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description: Verify the user can operates any certification record if the customer profile has status other than 'Deceased' or 'Merged'
 * @Preconditions: Need 2 existing customers, one's status is 'Active' and
 * the other is 'Merged'(Need "MergeCustomerProfile" and "AllowMergeWithCustomerNumber")
 * @SPEC: <<Add Customer Certification.UCS>>
 * @Task#: AUTO-709
 *
 * @author qchen
 * @Date  Sep 7, 2011
 */
public class VerifyCustomerStatus extends LicenseManagerTestCase {
	private Customer customer = new Customer();
	private Customer mergedStatusCustomer = new Customer();
	private LicMgrCustomerCertificationPage certificationPage = LicMgrCustomerCertificationPage.getInstance();
	private Certification certification = new Certification();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//System verifies that the known Customer Profile has Status other than "Deceased" or "Merged"
		//a. if the customer profile has status - Merged, the certification(s) shall NOT be added/updated
		mergedStatusCustomer.status = "Merged";
		lm.gotoCustomerDetailFromTopMenu(mergedStatusCustomer);
		lm.gotoCertificationFromCustomerDetailsPg();
		this.verifyCertificationCannotBeOperated();

		//b. if the customer profile has status - Deceased, the certification(s) shall NOT be added/updated
		//precondition1, update the customer status as Active in case the last time execution failed in halfway
		customer.status = "Active";
		lm.editCustomerStatus(customer);
		//preconfition2, add a certification record for the below verification
		lm.gotoCertificationFromCustomerDetailsPg();
		certification.id = lm.addCustomerCertification(certification);
		//update the customer status as Deceased
		customer.status = "Deceased";
		lm.editCustomerStatus(customer);
		lm.gotoCertificationFromCustomerDetailsPg();
		this.verifyCertificationCannotBeOperated();

		//c. clean up environment
		customer.status = "Active";
		lm.editCustomerStatus(customer);
		lm.gotoCertificationFromCustomerDetailsPg();
		lm.removeCustomerCertification(certification.id, true);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		customer.customerClass = "Individual";
		customer.fName = "Automation";
		customer.lName = "Test";
		customer.identifier.identifierType = "Tax ID";
		customer.identifier.identifierNum = "333333";

		mergedStatusCustomer.customerClass = "Individual";
		mergedStatusCustomer.fName = "QA-Merge";
		mergedStatusCustomer.lName = "TEST-Merge";
		//The customer(Status: Merge) will don't have identifier other than the customer#
//		mergedStatusCustomer.identifier.identifierType = "Tax ID";
//		mergedStatusCustomer.identifier.identifierNum = "222222";

		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "AUTO" + DateFunctions.getCurrentTime();
		certification.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		certification.effectiveTo = certification.effectiveFrom;
	}

	/**
	 * Verify whether the customer certification records can be operated or not when the customer status is 'Merged' or 'Deceased'
	 */
	private void verifyCertificationCannotBeOperated() {
		if(certificationPage.isAddCertificationButtonEnabled() || certificationPage.isActivateButtonEnabled() || certificationPage.isDeactivateButtonEnabled() || certificationPage.isRemoveButtonEnabled() || certificationPage.isAllCertificationCheckBoxesEnabled()) {
			throw new ActionFailedException("Customer certification(s) cannot be added/updated since customer profile has status - 'Merged' and 'Deceased'.");
		}
		logger.info("Customer certification(s) really cannot be added/updated when the customer status is 'Merged' or 'Deceased'.");
	}
}
