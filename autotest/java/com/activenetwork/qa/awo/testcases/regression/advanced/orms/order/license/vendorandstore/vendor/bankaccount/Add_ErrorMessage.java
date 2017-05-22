package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.bankaccount;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddVendorBankAccountTestCase;

/**
 * 
 * @Description: This case is used to cover the invalid entries section of Add Vendor Bank Account spec
 * @Preconditions: An existing vendor named "AutoTest"
 * @SPEC: <<Add Vendor Bank Account.doc>>
 * Add Vendor Bank Account [TC:004159] 
 * @Task#: AUTO-501
 * 
 * @author qchen
 * @Date  Aug 25, 2011
 */
public class Add_ErrorMessage extends LicMgrAddVendorBankAccountTestCase {
	private String errorMsg_RoutingNumIsNull = "The Routing Number is required. Please specify the Routing Number.";
	private String errorMsg_AccountNumIsNull = "The Account Number is required. Please specify the Account Number.";
	private String errorMsg_RoutingNumDidNotPassChecksumTest = "The entered Routing Number failed the Checksum test. Please enter a valid Routing Number.";
	private String actualMsg, errorMsg_AlreadyExistingRecord;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(vendor.number);
		lm.gotoVendorBankAccountPage();
		
		//The Routing Number is not specified, i.e. left null/blank.
		vendor.bankAccount.routingNum = "";
		actualMsg = lm.addVendorBankAccount(vendor.bankAccount, true);
		addBankAccountWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(actualMsg, errorMsg_RoutingNumIsNull);
		
		//The Account Number is not specified, i.e. left null/blank.
		this.resetBankAccountData();
		vendor.bankAccount.accountNum = "";
		actualMsg = lm.addVendorBankAccount(vendor.bankAccount, true);
		addBankAccountWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(actualMsg, errorMsg_AccountNumIsNull);
		
		//Checksum test is required and the Routing Number did not pass the required Checksum test.
		this.resetBankAccountData();
		vendor.bankAccount.routingNum = "auto1234";
		actualMsg = lm.addVendorBankAccount(vendor.bankAccount, true);
		addBankAccountWidget.clickCancel();
		runningResult &= this.verifyErrorMsg(actualMsg, errorMsg_RoutingNumDidNotPassChecksumTest);
		
		//Add a new bank account which is all the same with existing one
		this.resetBankAccountData();
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		errorMsg_AlreadyExistingRecord = "Another active Vendor Bank Account record " + vendor.bankAccount.accountID + " already exists for the same Vendor " +
		"with the same Account Type, Routing Number, and Account Number. Duplicate active records are not allowed.";
		actualMsg = lm.addVendorBankAccount(vendor.bankAccount, true);
		addBankAccountWidget.clickCancel();
		vendorBankAccountPage.waitLoading();
		runningResult &= this.verifyErrorMsg(actualMsg, errorMsg_AlreadyExistingRecord);
		
		VendorBankAccountInfo accountUI = vendorBankAccountPage.getBankAccount(vendor.bankAccount.accountID);
		runningResult &= this.verifyBankAccountDetailsInfo(accountUI);
		
		//clean up data
		lm.deactivateVendorBankAccount(vendor.bankAccount.accountID);
		
		//verify the whether these 4 check points are all passed
		this.finalVerification();
		
		lm.logOutLicenseManager();
	}

	/**
	 * Verify error message is correct or not
	 * @param actualMsg
	 * @param expectedMsg
	 * @return
	 */
	private boolean verifyErrorMsg(String actualMsg, String expectedMsg) {
		boolean result = true;
		logger.info("Verify whether the error message displayed at add bank account widget correct or not.");
		if(!actualMsg.equalsIgnoreCase(expectedMsg)) {
			logger.error("The actual error message doesn't match the expected. The expected message is: '" 
					+ expectedMsg + "', but the actual message is: '" + actualMsg + "'.");
			result &= false;
		}
		
		logger.info("----The actual message shows correctly.");
		
		return result;
	}
}
