package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.bankaccount;

import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddVendorBankAccountTestCase;

/**
 * 
 * @Description: This case is used to cover the business rule section defined in Add Vendor Bank Account spec
 * @Preconditions: An existing vendor named "AutoTest"
 * @SPEC: <<Add Vendor Bank Account.doc>>
 * Add Vendor Bank Account [TC:004159] 
 * @Task#: AUTO-501
 * 
 * @author qchen
 * @Date  Aug 25, 2011
 */
public class Add_BusinessRule extends LicMgrAddVendorBankAccountTestCase {
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(vendor.number);
		lm.gotoVendorBankAccountPage();
		
		//Rule1. The System shall require the User to specify the Account Type by selecting from a list of the following Account Types: "Checking" and "Savings"
		vendorBankAccountPage.clickAddBankAccount();
		addBankAccountWidget.waitLoading();
		List<String> accountTypes = addBankAccountWidget.getAccountTypeElements();
		addBankAccountWidget.clickCancel();
		ajax.waitLoading();
		vendorBankAccountPage.waitLoading();
		
		if(accountTypes.size() != 2 || !(accountTypes.get(0).equals("Checking") && accountTypes.get(1).equals("Savings"))) {//DEFECT-30683
			//the first option must be Checking because the System require the options sorted using alpha-numeric sorting in ascending order
			runningResult &= false;
			logger.error("The Options of Account Type should be 'Checking' and 'Savings'.");
		}
		
		//Rule2. The System shall require the User to specify the Routing Number. This shall allow for entry of a maximum of 9 numeric characters.
		vendorBankAccountPage.clickAddBankAccount();
		ajax.waitLoading();
		addBankAccountWidget.waitLoading();
		vendor.bankAccount.routingNum = "1234567890";
		addBankAccountWidget.setRoutingNum(vendor.bankAccount.routingNum);
		String routingNum = addBankAccountWidget.getRoutingNum();
		addBankAccountWidget.clickCancel();
		ajax.waitLoading();
		vendorBankAccountPage.waitLoading();
		if(!routingNum.equals(vendor.bankAccount.routingNum.substring(0, 9))) {
			runningResult &= false;
			logger.error("The Routing# field DOESN'T allow entry of a maximum of 9 numeric characters.");
		}
		
		//Rule3. The System shall require the User to specify the Account Number. This shall allow for entry of a maximum of 17 alphanumeric characters.
		vendorBankAccountPage.clickAddBankAccount();
		ajax.waitLoading();
		addBankAccountWidget.waitLoading();
		vendor.bankAccount.accountNum = "ABCDEFGH1234567890";
		addBankAccountWidget.setAccountNum(vendor.bankAccount.accountNum);
		String accountNum = addBankAccountWidget.getAccountNum();
		addBankAccountWidget.clickCancel();
		ajax.waitLoading();
		vendorBankAccountPage.waitLoading();
		if(!accountNum.equals(vendor.bankAccount.accountNum.substring(0, 17))) {
			runningResult &= false;
			logger.error("The Account# field DOESN'T allow entry of a maximum of 17 alphanumeric characters.");
		}
		
		//Rule4. The System shall set the Status of the Bank Account being added to "Active" and not editable.
		vendorBankAccountPage.clickAddBankAccount();
		ajax.waitLoading();
		addBankAccountWidget.waitLoading();
		String status = addBankAccountWidget.getStatus();
		if(!status.equals("Active")) {
			runningResult &= false;
			logger.error("The Status of the Bank Account should be 'Active'.");
		}
		if(addBankAccountWidget.checkStatusEditable()) {
			runningResult &= false;
			logger.error("The Status field should be NOT editable.");
		}
		
		//Rule5. The System shall set the Pre-Note Status of the Bank Account being added to "Pending" and not editable.
		String prenoteStatus = addBankAccountWidget.getPrenoteStatus();
		if(!prenoteStatus.equals("Pending")) {
			runningResult &= false;
			logger.error("The Pre-Note Status of the Bank Account should be 'Pending'.");
		}
		if(addBankAccountWidget.checkPrenoteStatusEditable()) {
			runningResult &= false;
			logger.error("The Pre-Note Status field should be NOT editable.");
		}
		addBankAccountWidget.clickCancel();
		
		//final verification
		this.finalVerification();
		lm.logOutLicenseManager();
	}
}
