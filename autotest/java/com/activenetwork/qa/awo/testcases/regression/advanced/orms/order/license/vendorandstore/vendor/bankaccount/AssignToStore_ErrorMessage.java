package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrChangeVendorBankAccountStoreAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:
 * @Preconditions:Need existing Vendor named "AutoTest001"
 * @SPEC:
 * @Task#:
 * 
 * @author 
 * @Date  Apr 9, 2012
 */
public class AssignToStore_ErrorMessage extends LicenseManagerTestCase {
	private VendorInfo vendor = new VendorInfo();
	private VendorBankAccountInfo secondBankAccount = new VendorBankAccountInfo();
	private LicMgrChangeVendorBankAccountStoreAssignmentsWidget changeVendorBankAccountWidget = LicMgrChangeVendorBankAccountStoreAssignmentsWidget.getInstance();
	private String errorMsg_effectiveDateIsNull = "The Effective Date is required. Please enter the Effective Date in the field provided.";
	private String errorMsg_effectiveDateIsLessThanToday = "The Effective Date must be later than or equal to today's date.";
	private String actualErrorMsg, warningMsg_alreadyExistingAssignment;
	private boolean runningResult = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		lm.gotoVendorBankAccountPage();
		//pre-condition: create 2 new bank accounts
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		secondBankAccount.accountID = lm.addVendorBankAccount(secondBankAccount, true);
		
		//1. effective date is left blank
		actualErrorMsg = lm.assignVendorBankAccountToStore(vendor.stores[0], vendor.bankAccount.accountRegx, "");
		changeVendorBankAccountWidget.clickCancel();
		runningResult &= this.verifyErrorMessage(actualErrorMsg, errorMsg_effectiveDateIsNull);

		//2. effective date entry is not a valid date, the invalid value including "automation" and "2011/12/32"
		runningResult &= this.verifyEffectiveDate(vendor.stores[0], vendor.bankAccount.accountRegx, new String[]{"2011/12/32", "automation", "!@#$%^"});
		
		//3. effective date is less than the Current Date
		actualErrorMsg = lm.assignVendorBankAccountToStore(vendor.stores[0], vendor.bankAccount.accountRegx, DateFunctions.getDateAfterToday(-2));
		changeVendorBankAccountWidget.clickCancel();
		runningResult &= this.verifyErrorMessage(actualErrorMsg, errorMsg_effectiveDateIsLessThanToday);

		//4. there is already an existing Vendor Bank Account - Store Assignment record for the same Vendor that is also 'Active' and has the same values as
		//this record for the following:Store ID and Effective Date
		//Pre-conditions: add an already existing assignment
		lm.assignVendorBankAccountToStore(vendor.stores[0], vendor.bankAccount.accountRegx, vendor.bankAccount.effectiveDate);
		List<List<String>> tempAssignments = lm.getVendorBankAccountStoreAssignmentInfo(vendor.stores[0]);
		List<String> tempIDs = new ArrayList<String>();
		for(int i = 0; i < tempAssignments.size(); i ++) {
			tempIDs.add(tempAssignments.get(i).get(1).trim());
		}
		
		//user cancels the second assignment
		actualErrorMsg = lm.assignVendorBankAccountToStore(vendor.stores[0], secondBankAccount.accountRegx, secondBankAccount.effectiveDate, true, false);
		boolean tempResult = false;
		for(int i = 0; i < tempIDs.size(); i ++) {
			warningMsg_alreadyExistingAssignment = "Another active Vendor Bank Account - Agent Assignment record " + tempIDs.get(i) + " already exists with the same Agent ID and Effective Date but a different Bank Account ID. If you proceed with the new assignment, this existing assignment will be deactivated. Are you sure you want to continue?";
			tempResult |= this.verifyErrorMessage(actualErrorMsg, warningMsg_alreadyExistingAssignment);
		}
		runningResult &= tempResult;
		
		//5. clean up
		lm.deactivateVendorBankAccount(secondBankAccount.accountID);
		
		//final verification
		if(runningResult) {
			logger.info("All checkpoints are PASSED.");
		} else {
			throw new ErrorOnPageException("The checkpoints are NOT all passed. Please refer to log for details info.");
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendor.number = "AutoTest001";
		vendor.stores = new String[]{"AssignBankAccount001"};
		vendor.bankAccount.accountPrenoteStatus = "Pending";
		vendor.bankAccount.accountStatus = "Active";
		vendor.bankAccount.accountType = "Checking";
		vendor.bankAccount.routingNum = "026009593";
		vendor.bankAccount.accountNum = "e" + new Random().nextInt(1000) + DateFunctions.getLongTimeStamp();
		vendor.bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		vendor.bankAccount.accountRegx = vendor.bankAccount.accountType + " Routing # " + vendor.bankAccount.routingNum + " Acct # " + vendor.bankAccount.accountNum.substring(0, 4);
		
		secondBankAccount.accountPrenoteStatus = "Pending";
		secondBankAccount.accountStatus = "Active";
		secondBankAccount.accountType = "Savings";
		secondBankAccount.routingNum = vendor.bankAccount.routingNum;
		secondBankAccount.accountNum ="m" + new Random().nextInt(1000) + DateFunctions.getLongTimeStamp();
		secondBankAccount.effectiveDate = vendor.bankAccount.effectiveDate;
		secondBankAccount.accountRegx = secondBankAccount.accountType + " Routing # " + secondBankAccount.routingNum + " Acct # " + secondBankAccount.accountNum.substring(0, 4);
	}
	
	private boolean verifyEffectiveDate(String storeName, String account, String invalidDates[]) {
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage.getInstance();
		LicMgrChangeVendorBankAccountStoreAssignmentsWidget changeAssignmentWidget = LicMgrChangeVendorBankAccountStoreAssignmentsWidget.getInstance();
		
		logger.info("Verify effective date auto-displays correctly.");
		vendorBankAccountPg.clickChangeStoreBankAccountAssignments();
		ajax.waitLoading();
		changeAssignmentWidget.waitLoading();
		boolean toReturn = changeAssignmentWidget.verifyEffectiveDate(storeName, account, invalidDates);
		changeAssignmentWidget.clickCancel();
		vendorBankAccountPg.waitLoading();
		
		return toReturn;
	}
	
	private boolean verifyErrorMessage(String actualMsg, String expectedMsg) {
		boolean toReturn = true;
		logger.info("Verify whether the error message displayed at Vendor Bank Account Agent Assignments widget correct or not.");
		if(!actualMsg.equalsIgnoreCase(expectedMsg)) {
			logger.error("The expected error message doesn't match the expected. The expected message is: '" 
					+ expectedMsg + "', but the actual message is: '" + actualMsg + "'");
			toReturn &= false;
		} else {
			logger.info(actualMsg + " is displayed correctly.");
		}
		
		return toReturn;
	}
}
