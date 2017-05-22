package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBankAccountWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 25, 2011
 */
public abstract class LicMgrAddVendorBankAccountTestCase extends LicenseManagerTestCase {
	protected VendorInfo vendor = new VendorInfo();
	protected LicMgrVendorBankAccountsSubPage vendorBankAccountPage = LicMgrVendorBankAccountsSubPage.getInstance();
	protected LicMgrAddVendorBankAccountWidget addBankAccountWidget = LicMgrAddVendorBankAccountWidget.getInstance();
	protected boolean runningResult = true;
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		this.resetBankAccountData();
	}
	
	protected void resetBankAccountData() {
		vendor.number = "AutoTest";
		vendor.bankAccount.accountPrenoteStatus = "Pending";
		vendor.bankAccount.accountStatus = "Active";
		vendor.bankAccount.accountType = "Checking";
		vendor.bankAccount.routingNum = "026009593";
		vendor.bankAccount.accountNum = this.getBankAccountNum();
		vendor.bankAccount.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
	}
	
	/**
	 * Get a random account number
	 * @return
	 */
	private String getBankAccountNum() {
		Random random = new Random();
		char first = (char)(random.nextInt(25) + 'a');
		char second = (char)(random.nextInt(25) + 'a');
		char third = (char)(random.nextInt(25) + 'a');
		char forth = (char)(random.nextInt(25) + 'a');
		
		String accountNum = String.valueOf(first) + String.valueOf(second) + String.valueOf(third) + String.valueOf(forth);
		
		return accountNum;
	}
	
	/**
	 * Verify whether the new added bank account details info is correct or not
	 * @param accountUI
	 * @return
	 */
	protected boolean verifyBankAccountDetailsInfo(VendorBankAccountInfo accountUI) {
		boolean result = true;
		logger.info("Verify the actual bank account details info is correct with expected or not.");
		if(!accountUI.accountPrenoteStatus.equals(vendor.bankAccount.accountPrenoteStatus)) {
			logger.info("Expect account prenote status should be " + vendor.bankAccount.accountPrenoteStatus
					+ ", but actually is " + accountUI.accountPrenoteStatus);
			result &= false;
		}
		if(!accountUI.accountStatus.equals(vendor.bankAccount.accountStatus)) {
			logger.info("Expect account status should be " + vendor.bankAccount.accountStatus 
					+ ", but acutally is " + accountUI.accountStatus);
			result &= false;
		}
		if(!accountUI.accountType.equals(vendor.bankAccount.accountType)) {
			logger.info("Expect account type should be " + vendor.bankAccount.accountType 
					+ ", but actually is " + accountUI.accountType);
			result &= false;
		}
		if(!accountUI.routingNum.equals(vendor.bankAccount.routingNum)) {
			logger.info("Expect routing number should be " + vendor.bankAccount.routingNum 
					+ ", but actually is " + accountUI.routingNum);
			result &= false;
		}
		if(!accountUI.accountNum.equals(vendor.bankAccount.accountNum)) {
			logger.info("Expect routing number should be " + vendor.bankAccount.accountNum 
					+ ", but actually is " + accountUI.routingNum);
			result &= false;
		}
		
		if (!accountUI.creationDateTime.startsWith(DateFunctions.formatDate(vendor.bankAccount.creationDateTime, "MM/dd/yyyy"))) {
			logger.info("Expect creation date should be " + vendor.bankAccount.creationDateTime
					+ ", but actually is " + accountUI.creationDateTime);
			result &= false;
		}
	
		if(!accountUI.creationUser.replace(", ", ",").equals(vendor.bankAccount.creationUser.replace(", ", ","))) {
			logger.info("Expect creation user should be " + vendor.bankAccount.creationUser
					+ ", but actually is " + accountUI.creationUser);
			result &= false;
		}
		
		return result;
	}
	
	protected void finalVerification() {
		if(runningResult) {
			logger.info("All checkpoints are PASSED.");
		} else {
			throw new ErrorOnPageException("The checkpoints are NOT all passed. Please refer to log for details info.");
		}
	}
}
