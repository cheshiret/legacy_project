package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.suspension;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerSuspensionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: This case is used to verify the removing suspension record function works correct or not
 * @Preconditions: Need an existing customer
 * @SPEC: <<Remove Suspension.UCS>> & <<View Customer Suspension.UCS>>
 * @Task#: AUTO-756
 * 
 * @author qchen
 * @Date  Dec 5, 2011
 */
public class RemoveSuspension extends LicenseManagerTestCase {
	private Suspension suspension = new Suspension();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoSuspensionsFromCustomerDetailsPg();
		
		//add customer suspension
		suspension.id = lm.addCustomerSuspension(suspension);
		//Before removing, verify the new suspension record exists
		this.verifySuspensionExists(suspension.id, true);
		//remove suspension
		lm.removeCustomerSuspension(suspension);
		//After removing, verify the suspension record doesn't exist
		this.verifySuspensionExists(suspension.id, false);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-ExpireSuspension";
		cust.lName = "Test-ExpireSuspension";
		cust.identifier.identifierType = "Tax Id";
		cust.identifier.identifierNum = "569865304";
		
		suspension.status = "Active";
		suspension.type = "Bad Check Suspension";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1);
		suspension.endDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.comment = "Remove customer suspension - " + DateFunctions.getCurrentTime();
	}
	
	public void verifySuspensionExists(String suspensionID, boolean expectedExist) {
		LicMgrCustomerSuspensionPage suspensionPage = LicMgrCustomerSuspensionPage.getInstance();
		
		logger.info("Verify the customer suspension(ID#=" + suspensionID + ") exists or not.");
		
		boolean result = suspensionPage.checkSuspensionExists(suspensionID);
		logger.info("result is: " + result);
		if(expectedExist != result) {
			throw new ErrorOnPageException("The suspension should(ID#=" + suspensionID + ") " + (expectedExist ? "" : "NOT") + " exist in suspension list.");
		}
		logger.info("The suspension should(ID#=" + suspensionID + ") really " + (expectedExist ? "exists" : "don't exist"));
	}
}
