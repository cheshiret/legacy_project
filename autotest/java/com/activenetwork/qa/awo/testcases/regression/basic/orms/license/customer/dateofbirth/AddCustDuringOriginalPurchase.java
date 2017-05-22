package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify whether System pop-up error message when adding a customer without inputing DOB during original purchase process
 * @Preconditions: Need an existing privilege product with BUSINESS customer class.
 * 
 * @SPEC: PCR 2926, <<Add Customer Profile.doc>> TC003915, Step 39&40.
 * 				And for step 40, it needs to update Optional DOB indicator to FALSE, to avoid impact other cases, ignore this scenario.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 13, 2012
 */
public class AddCustDuringOriginalPurchase extends LicenseManagerTestCase {
	private String expected = "";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		//Step39. User is adding customer profile in the process of performing a privilege transaction: Purchase Privilege,
		//click on "Purchase Privilege" button from LM home page, and then add new customer profile in the privilege sale process,
		//Date Of Birth has not been specified.
		//1. negative scenario: adding customer without specific DOB, system popup an error message
		String msgOnPage = lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE, true, true);
		this.verifyErrorMsg(expected, msgOnPage);
		
		//2. positive scenario: adding customer with specific DOB successfully
		lm.gotoHomePage();
		lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE, true, false);
		lm.addPrivilegeItem(privilege);
		lm.goToCart();
		String orderNum = lm.processOrderCart(pay);
		
		//clean up
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//customer info
		int sequence = DataBaseFunctions.getEmailSequence();
		
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "8965" + String.valueOf(sequence);
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		cust.fName = "Add" + sequence;
		cust.mName = "Customer" + sequence;
		cust.lName = "DuringPurchase" + sequence;
		cust.businessName = "AddCustomerDuringPurchase" + sequence;
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.email = "adding@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		
		//privilege info
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		expected = "Date Of Birth is required. Please specify the Date Of Birth.";
	}
	
	private void verifyErrorMsg(String expected, String actual) {
		boolean result = MiscFunctions.compareResult("Date Of Birth", expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The actual error message is wrong with expected.");
		}
	}
}
