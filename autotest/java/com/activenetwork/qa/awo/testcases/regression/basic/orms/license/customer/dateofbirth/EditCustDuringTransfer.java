package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify if the system required User to input DOB during transfer process, even the Option Date Of Birth is TRUE
 * @Preconditions: Need an existing privilege product with BUSINESS customer class.
 * @SPEC: PCR 2926, <<Edit Customer Profile.doc>>, TC003916 step31.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 14, 2012
 */
public class EditCustDuringTransfer extends LicenseManagerTestCase {
	private Customer originalCust = new Customer();
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
			cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, cust.businessName, schema);
		}
		
		//1. make a original privilege order as precondition
		lm.makePrivilegeToCartFromCustomerTopMenu(originalCust, privilege);
		String orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//Step31. User is editing customer profile in the process of performing a privilege transaction - Transfer Privilege, search for
		//an existing privilege and click on "Transfer" button, edit customer profile in the privilege transfer process, Date Of Birth has not been specified
		//positive scenario: DOB is null, system will pop up an error message on the top of page
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		String msgOnPage = lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE, false, true);
		this.verifyErrorMsg(expected, msgOnPage);
		
		//negative scenario: DOB is NOT null, system will be edited successfully
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE, false, false);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//transferred customer info
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "68685958";
		cust.identifier.state = "Mississippi";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Feb 23 2013";
		cust.fName = "Edit";
		cust.mName = "Customer";
		cust.lName = "DuringTransfer";
		cust.businessName = "EditCustomerDuringTransfer";
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.email = "editing.transfer@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
		
		//original customer info
		originalCust.customerClass = "Individual";
		originalCust.dateOfBirth = "19880608";
		originalCust.identifier.identifierType = "Green Card";
		originalCust.identifier.identifierNum = "111111";
		originalCust.identifier.country = "Canada";
		originalCust.residencyStatus = "Non Resident";
		
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
