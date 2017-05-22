package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.suspension;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify editing customer suspension function
 * @Preconditions: An existing customer
 * @SPEC: <<Edit Suspension.UCS>> & <<View Customer Suspension.UCS>>
 * @Task#: AUTO-756
 * 
 * @author qchen
 * @Date  Nov 14, 2011
 */
public class EditSuspension extends LicenseManagerTestCase {
	private Suspension suspension = new Suspension();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Suspensions");

		//add customer suspension
		suspension.id = lm.addCustomerSuspension(suspension);

		//edit customer suspension
		suspension.beginDate = DateFunctions.getToday();
		suspension.endDate = DateFunctions.getDateAfterToday(1);
		suspension.comment = "Edit customer suspension - " + DateFunctions.getCurrentTime();
		suspension.id = lm.editCustomerSuspension(suspension);
		//view customer suspension list
		lm.verifyCustomerSuspensions(suspension);
		//view customer suspension detail
		lm.verifyCustomerSuspensionDetailInfo(suspension);

		//remove customer suspension
		lm.removeCustomerSuspension(suspension);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		cust.customerClass = "Individual";
		cust.fName = "QA-Harvest";
		cust.lName = "TEST-Report";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "198527818";

		suspension.status = "Active";
		suspension.type = "Failure to Submit Harvest Report";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1);
		suspension.endDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.comment = "Original customer suspension - " + DateFunctions.getCurrentTime();
		TimeZone zone = DataBaseFunctions.getContractTimeZone("");;
		suspension.creationDateTime = DateFunctions.getToday("E MMM dd,yyyy hh:mm a", zone).replace(",", " ");
		suspension.creationUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
	}
}
