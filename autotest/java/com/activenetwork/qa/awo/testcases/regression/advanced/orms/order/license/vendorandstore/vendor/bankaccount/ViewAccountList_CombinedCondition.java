package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.bankaccount;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**TODO DEFECT-48228 
 * @Description:
 * View bank account list by combined conditions.
 * @Preconditions:
 * There should be at least 2 bank account records:
 * 1. Active, Pending, Checking
 * 2. Inactive, Bypassed, Saving
 * @SPEC: View Vendor Bank Accounts [TC:004222]
 * @Task#: Auto-1595
 * 
 * @author nding
 * @Date  Aug 13, 2013
 */
public class ViewAccountList_CombinedCondition extends LicenseManagerTestCase{
	private String vendorNum;
	private VendorBankAccountInfo bankAccount1 = new VendorBankAccountInfo();
	private VendorBankAccountInfo bankAccount2 = new VendorBankAccountInfo();
	private LicMgrVendorBankAccountsSubPage bankAccountPage = LicMgrVendorBankAccountsSubPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorBankAccountPage();
		
		// prepare bank account.
		this.prepartBankAccount();
		
		// 1. Bank Account Status = Active, Prenote Status = Pending, Account Type = Checking
		this.unCheckShowCurrentRecordsOnly();
		bankAccountPage.checkBankAccountStatusActive();
		bankAccountPage.checkPrenoteStatusPending();
		bankAccountPage.checkAccountTypeChecking();
		this.search();

		// verify search result
		bankAccountPage.verifyStatusColumnValue(ACTIVE_STATUS);
		bankAccountPage.verifyPrenoteStatusColumnValue(PENDING_STATUS);
		bankAccountPage.verifyAccountTypeColumnValue("Checking");
		
		// 2. Bank Account Status = Active|Inactive, Prenote Status = Bypassed, Account Type = Saving
		bankAccountPage.checkBankAccountStatusActive();
		bankAccountPage.checkBankAccountStatusInactive();
		bankAccountPage.unCheckPrenoteStatusPending();
		bankAccountPage.checkPrenoteStatusBypassed();
		bankAccountPage.unCheckAccountTypeChecking();
		bankAccountPage.checkAccountTypeSaving();
		this.search();
		
		// verify search result
		bankAccountPage.verifyStatusColumnValue(ACTIVE_STATUS, INACTIVE_STATUS);
		bankAccountPage.verifyPrenoteStatusColumnValue("Bypassed");
		bankAccountPage.verifyAccountTypeColumnValue("Savings");
		
		// 3. Prenote Status = Bypassed|Pending|Successful, Account Type = Saving|Checking
		bankAccountPage.unCheckBankAccountStatusInactive();
		bankAccountPage.checkPrenoteStatusPending();
		bankAccountPage.checkPrenoteStatusBypassed();
		bankAccountPage.checkPrenoteStatusSuccessful();
		bankAccountPage.checkAccountTypeChecking();
		bankAccountPage.checkAccountTypeSaving();
		this.search();
		
		// verify search result
		bankAccountPage.verifyPrenoteStatusColumnValue(PENDING_STATUS, "Bypassed", PENDING_STATUS, "Successful");
		bankAccountPage.verifyAccountTypeColumnValue("Checking", "Savings", "Checking");
		lm.logOutLicenseManager();
	}
	
	private void prepartBankAccount(){
		bankAccount1.accountID = lm.addVendorBankAccount(bankAccount1, true);
		bankAccount2.accountID = lm.addVendorBankAccount(bankAccount2, true);
		
		// bypass
		bankAccountPage.bypassPreNote(bankAccount2.accountID);
		
		// deactivate
		bankAccountPage.deactivateBankAccount(bankAccount2.accountID);
		bankAccountPage.waitLoading();
	}
	
	private void unCheckShowCurrentRecordsOnly(){
		bankAccountPage.unCheckShowCurrentRecordsOnlyCheckBox();
		ajax.waitLoading();
		bankAccountPage.waitLoading();
	}

	private void search(){
		bankAccountPage.clickGo();
		ajax.waitLoading();
		bankAccountPage.waitLoading();
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		vendorNum = "247"; //this vendor number change change randomly as long as we can get to vendor details page
		
		bankAccount1.accountPrenoteStatus = "Pending";
		bankAccount1.accountStatus = "Active";
		bankAccount1.accountType = "Checking";
		bankAccount1.routingNum = "065301566";// Don't change!
		bankAccount1.accountNum = String.valueOf(DateFunctions.getCurrentTime());
		
		bankAccount2.accountPrenoteStatus = "Pending";
		bankAccount2.accountStatus = "Active";
		bankAccount2.accountType = "Savings";
		bankAccount2.routingNum = "065301566";// Don't change!
		bankAccount2.accountNum = String.valueOf(DateFunctions.getCurrentTime());
	}
	
}
