package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor.bankaccount;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1: verify the account list column name match with spec.
 * 2: verify only active record display on Vendor Bank Accounts sub-tab page for initial page load.
 * 3: verify all vendor bank accounts display on Vendor Bank Account sub-tab page.
 * @Preconditions:
 * 1: choose whatever vendor Num exist in the system. as long as there is more than one vendor account number setup for the select vendor.
 * @SPEC: View vendor bank account
 * View Vendor Bank Accounts [TC:004222]
 * @Task#: AUTO-765
 * 
 * @author bzhang
 * @Date  Sep 29, 2011
 */
public class ViewAccountList extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private String[] colNames = {"ID", "Prenote Status", "Status", "Account Type", "Routing #", "Account #", "# Agent Assignments", "Bank Name", "Branch Name/Place", "Creation Date/Time", "Creation User"};
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		lm.gotoVendorBankAccountPage();
		this.verifyBankAccountColumnNames(colNames);
			
		//add one activate bank account, verify only active account display on the page for initial page load
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		
		lm.gotoVendorBankAccountPage();
		this.verifyBankAccountInitialListStatus("Active");
		
		//de-activate bank account, verify only active account display on the page for initial page load
		lm.deactivateVendorBankAccount(vendor.bankAccount.accountID);
		lm.gotoVendorBankAccountPage();	
		this.verifyBankAccountInitialListStatus("Active");
		
		//verify System retrieves all the Bank Account records associated with the selected Vendor
		this.verifyBankAccountMatchWithDB();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		vendor.number = "778"; //this vendor number change change randomly as long as we can get to vendor details page
		vendor.bankAccount.accountPrenoteStatus = "Pending";
		vendor.bankAccount.accountStatus = "Active";
		vendor.bankAccount.accountType = "Checking";
		vendor.bankAccount.routingNum = "026009593";
		vendor.bankAccount.accountNum = String.valueOf(DateFunctions.getCurrentTime());
	}
	
	/**
	 * verify bank account initially displayed account record are all in the status of "Active"
	 * @param status
	 */
	private void verifyBankAccountInitialListStatus(String status){
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();		
		boolean flag = true;
		List<VendorBankAccountInfo> accounts = bankAccountSubPg.getAllBankAccounts();
		
		if (accounts.size() ==0){
			throw new ErrorOnPageException("There is no bank account listed on the page!");
		}
		
		for (VendorBankAccountInfo account: accounts){
			if (!account.accountStatus.equalsIgnoreCase(status)){
				flag = false;
				break;
			}
		}
		
		if (!flag){
			throw new ErrorOnPageException("only those Bank Account records with Status of " + status +"should be list on the page..");
		}
	}
	
	/**
	 * verify bank account column named match with spec.
	 */
	private void verifyBankAccountColumnNames(String[] expectColumnHeaders){
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();		
		String[] actualColumnHeaders = bankAccountSubPg.getBankAccountListColumnNames();
		logger.info("test");

		logger.info("Start verifing Bank Account List column names.");
		
		for (int i = 0; i < actualColumnHeaders.length; i ++){
			if (!actualColumnHeaders[i].equalsIgnoreCase(expectColumnHeaders[i])){
				logger.info("Actual column header is: " + actualColumnHeaders[i] + " the expect header is: " + expectColumnHeaders[i]);
				throw new ErrorOnPageException("Bank account list column names didn't match with Spec");
			}
		}
	}
	
	/**
	 * verify bank account initially displayed account record are all in the status of "Active"
	 * @param status
	 */
	private void verifyBankAccountMatchWithDB(){
		LicMgrVendorBankAccountsSubPage bankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();
		
		bankAccountSubPg.isShowCurrentReadOnly(false);  //uncheck "Show current records only" check box in order to show all records.
		List<VendorBankAccountInfo> bankInfos = bankAccountSubPg.getAllBankAccounts();
		String[] accountIDsString = lm.queryVendorBankAccountID(schema, vendor.number);
		
		logger.info("Start verify the vendor bank account total number info with DB.");
		if (bankInfos.size() == accountIDsString.length){
			for (int i = 0; i < accountIDsString.length; i ++){
				boolean flag = false;
				for(int j = 0 ; j < bankInfos.size(); j ++){
					if (accountIDsString[i].equals(bankInfos.get(j).accountID)){
						flag = true;
						break;
					}
				}
				
				if (!flag){
					throw new ErrorOnDataException("the account info displayed on the page didn't match with the account info in the DB.");	
				}
			}
			
		}else {
			throw new ErrorOnDataException("the account info displayed on the page didn't match with the account info in the DB.");
		}
		logger.info("Vendor bank account total number info match with DB.");
	}

}
