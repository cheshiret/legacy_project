package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bankaccount;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrDeactiveVendorBankAccountWarningWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * 1: deactive bank account which don't have any agent assignments, verify deactive successfully.
 * 2: deactive bank acount which have at least one agent assignment, verify the warning message, and deactive should not work;
 * @Preconditions:
 * 1: there is one vendor setup.
 * 2: there is at least one account assignments for the selected vendor.
 * @SPEC: Deactivate vendor bank account
 * @Task#: AUTO-764
 * 
 * @author bzhang
 * @Date  Sep 29, 2011
 */
public class DeactivateAccount  extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private VendorInfo vendor2 = new VendorInfo();
	private String ErrorMsg;
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		lm.gotoVendorBankAccountPage();
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		
		//deactive bank account without agent assignments
		lm.deactivateVendorBankAccount(vendor.bankAccount.accountID);
		this.verifyBankAccountStatus(vendor.bankAccount.accountID, "Inactive");
		
		//deactive bank account with agent assignments.
		vendor2.bankAccount.accountID = this.getBankAccountIDWithAssignments();
		lm.deactivateVendorBankAccount(vendor2.bankAccount.accountID);
		this.verifyWarningMsg(ErrorMsg);
		this.verifyBankAccountStatus(vendor2.bankAccount.accountID, "Active");
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendor.number = "778"; //this vendor number change randomly as long as we can get to vendor details page
		vendor.bankAccount.accountPrenoteStatus = "Pending";
		vendor.bankAccount.accountStatus = "Active";
		vendor.bankAccount.accountType = "Checking";
		vendor.bankAccount.routingNum = "026009593";
		vendor.bankAccount.accountNum = String.valueOf(DateFunctions.getCurrentTime());
		
		vendor2.stores = new String[]{"Commercial Agents Address Fort Jones"};
		vendor2.bankAccount.accountPrenoteStatus = "Pending";
		vendor2.bankAccount.accountStatus = "Active";
		vendor2.bankAccount.accountType = "Checking";
		vendor2.bankAccount.routingNum = vendor.bankAccount.routingNum;
		vendor2.bankAccount.accountNum = String.valueOf(DateFunctions.getCurrentTime());
		vendor2.bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		vendor2.bankAccount.accountRegx = vendor.bankAccount.accountType + " Routing # " + vendor.bankAccount.routingNum + " Acct # " + vendor.bankAccount.accountNum.substring(0, 4);
		
		ErrorMsg = "The following .* are actively assigned to this .* Bank Account record. You have to change the Bank Account assignments first before deactivating this Bank Account.";
	}
	
	private void verifyBankAccountStatus(String accountID, String expectStatus){
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();

		logger.info("verify vendor bank account (ID=" + accountID + ") status..");
		bankAccountSubPg.isShowCurrentReadOnly(false);
		if (!bankAccountSubPg.getBankAccount(accountID).accountStatus.equalsIgnoreCase(expectStatus)){
			throw new ErrorOnPageException("Deactive vendor bank acount ID:" + accountID + " failed!");	
		}
	}
	
	/**
	 * @return
	 */
	private String getBankAccountIDWithAssignments(){
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();
		String accountID = "";
		
		accountID = bankAccountSubPg.getFirstBankAccountWithAssignedStore().accountID;
		
		//if there is no bank account with agent assignments, create one 
		if (accountID.length() ==0){
			accountID = lm.addVendorBankAccount(vendor2.bankAccount, true);
			lm.assignVendorBankAccountToStore(vendor2.stores[0], vendor2.bankAccount.accountRegx, vendor2.bankAccount.effectiveDate);
		}
		
		return accountID;
	}
	
	/**
	 * verify the warning message displayed on the Deactive bank account warning page.
	 * start from deactivate bank Accounts warning widget page, ends at Bank Accounts sub-Tab page.
	 *
	 * @param errorMsg
	 */
	private void verifyWarningMsg(String errorMsg){
		LicMgrDeactiveVendorBankAccountWarningWidget deactiveWarnPage = LicMgrDeactiveVendorBankAccountWarningWidget.getInstance();
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();
		
		String actualMsg = deactiveWarnPage.getErrorMessage();
		
		if (!actualMsg.matches(errorMsg)){
			throw new ErrorOnPageException("We can't deactive bank acount which have active assignments");
		}
		
		deactiveWarnPage.clickOK();
		bankAccountSubPg.waitLoading();		
	}
}
