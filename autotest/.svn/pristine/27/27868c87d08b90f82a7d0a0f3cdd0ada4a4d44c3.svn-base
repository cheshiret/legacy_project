package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.bankaccount;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddVendorBankAccountTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: This case is used to cover the cancellation and proceeding section defined in Add Vendor Bank Account spec
 * @Preconditions: An existing vendor named "AutoTest"
 * @SPEC: <<Add Vendor Bank Account.doc>>
 * Add Vendor Bank Account [TC:004159] 
 * @Task#: AUTO-501
 * 
 * @author qchen
 * @Date  Aug 25, 2011
 */
public class Add_CancelSuccess extends LicMgrAddVendorBankAccountTestCase {
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(vendor.number);
		lm.gotoVendorBankAccountPage();
		
		//User confirms the entries and desire to proceed in adding the Vendor Bank Account. User cancels, the System aborts the process of adding the Vendor Bank Account
		lm.addVendorBankAccount(vendor.bankAccount, false);
		if(vendorBankAccountPage.checkBankAccountRecordExists(vendor.bankAccount)) {
			logger.error("Cancel action failed. System should not add the account record successfully.");
			runningResult &= false;
		}
		
		//User confirms the entries and desire to proceed in adding the Vendor Bank Account. User clicks OK button, the System should add a new record successfully
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		vendor.bankAccount.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		VendorBankAccountInfo accountUI = vendorBankAccountPage.getBankAccount(vendor.bankAccount.accountID);
		runningResult &= this.verifyBankAccountDetailsInfo(accountUI);
		
		//clean up data
		lm.deactivateVendorBankAccount(vendor.bankAccount.accountID);
		
		//final verification
		this.finalVerification();
		
		lm.logOutLicenseManager();
	}
}
