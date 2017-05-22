package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify if the system required User to input DOB during original purchase process, even the Option Date Of Birth is TRUE
 * @Preconditions: Need an existing privilege product which Code is COP, name is CalculateOrderPrice.
 * @SPEC: PCR 2926, <<Edit Customer Profile.doc>>, TC003916 step32.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 14, 2012
 */
public class EditCustDuringOriginalPurchase extends LicenseManagerTestCase {
	private String expected;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		// update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		//check if customer exists, if not, add new one
		if(!lm.checkCustomerExists(cust.fName, cust.lName, schema)) {
			cust.custNum = lm.createNewCustomer(cust);
		} else {
			cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		}
		
		//Step32. User is adding customer profile in the process of performing a privilege transaction: Purchase Privilege,
		//click on "Purchase Privilege" button from LM home page, and then add new customer profile in the privilege sale process,
		//Date Of Birth has not been specified.
		//1. negative scenario: editing customer without specific DOB, system popup an error message
		lm.gotoHomePage();
		String msgOnPage = lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE, false, true);
		this.verifyErrorMsg(expected, msgOnPage);
		
		//2. positive scenario: editing customer with specific DOB successfully
		lm.gotoHomePage();
		lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE, false, false);
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
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "29154415";
		cust.identifier.state = "Mississippi";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Feb 23 2013";
		cust.fName = "Edit";
		cust.mName = "Customer";
		cust.lName = "DuringPurchase";
		cust.businessName = "EditCustomerDuringPurchase";
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.email = "editing.purchase@reserveamerica.com";
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
