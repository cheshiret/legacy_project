package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify whether System pop-up error message when adding a customer without inputing DOB during transfer process
 * @Preconditions: Need an existing privilege product, Code=COP, name=CalculateOrderPrice
 * @SPEC: PCR 2926, <<Add Customer Profile.doc>>, TC003915 step38&41.
 * 				And for step 41, it needs to update Optional DOB indicator to FALSE, to avoid impact other cases, ignore this scenario.
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 13, 2012
 */
public class AddCustDuringTransfer extends LicenseManagerTestCase {
	private Customer originalCust = new Customer();
	private String DOB, expected;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		//2. make a existing privilege as precondition
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(originalCust, privilege);
		String orderNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		
		//Step38: User is adding customer profile in the process of performing a privilege transaction:
		//Transfer Privilege - search for an existing privilege and click on "Transfer" button, add new 
		//customer profile in the privilege transfer process, Date Of Birth has not been specified
		//3. negative scenario: adding customer without specific DOB, System pop up an error message
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		String msgOnPage = lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE, true, true);
		this.verifyErrorMsg(expected, msgOnPage);

		//4. positive scenario: adding customer with specific DOB successfully
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.addOrEditCustomerDuringPurchase(cust, OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE, true, false);
		orderNum = lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//customer info
		int sequence = DataBaseFunctions.getEmailSequence();
		cust.identifier.identifierType = "MS Drivers License";
		cust.identifier.identifierNum = String.valueOf(sequence);
		cust.identifier.state = "Mississippi";
		cust.residencyStatus = "Non Resident";
		DOB = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		cust.dateOfBirth = DOB;
		cust.fName = "Add" + sequence;
		cust.mName = "Customer" + sequence;
		cust.lName = "DuringTransfer" + sequence;
		cust.businessName = "AddCustomerDuringTransfer" + sequence;
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
	
//	private String addCustomerDuringTransfer(Customer c, boolean isNullDOB) {
//		LicMgrPrivilegeItemDetailPage privilegeDetailPage = LicMgrPrivilegeItemDetailPage.getInstance();
//		LicMgrIdentifyCustomerPage identifyCustPage = LicMgrIdentifyCustomerPage.getInstance();
//		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance();
//		LicMgrNewCustomerPage newCustPage = LicMgrNewCustomerPage.getInstance();
//		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
//		
//		logger.info("Add customer which has" + (isNullDOB ? " not":"") + " specific DOB during transfer privilege process");
//		privilegeDetailPage.clickTransfer();
//		ajax.waitLoading();
//		identifyCustPage.waitExists();
//		if(!isNullDOB) {
//			c.dateOfBirth = DOB;
//		}
//		identifyCustPage.identifyCustomer(c);
//		identifyCustPage.clickOK();
//		ajax.waitLoading();
//		confirmWidget.waitExists();
//		confirmWidget.clickOK();
//		ajax.waitLoading();
//		newCustPage.waitExists();
//		
//		if(isNullDOB) {
//			c.dateOfBirth = "";
//		}
//		newCustPage.setCustomerDetails(c);
//		newCustPage.clickOK();
//		ajax.waitLoading();
//		Object page = browser.waitExists(newCustPage, orderCartPage);
//		if(page == newCustPage) {
//			return newCustPage.getErrorMsg();
//		}
//		return "";
//	}
	
	private void verifyErrorMsg(String expected, String actual) {
		boolean result = MiscFunctions.compareResult("Date Of Birth", expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The actual error message is wrong with expected.");
		}
	}
}
