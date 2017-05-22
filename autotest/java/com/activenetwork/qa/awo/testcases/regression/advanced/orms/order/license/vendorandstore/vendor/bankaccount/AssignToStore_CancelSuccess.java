package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.bankaccount;

import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author qchen
 * @updated by pzhu Feb 9,
 * 		Update "vendor.number"-->>"AutoTest001"
 * 		Update "vendor.stores"-->>"AssignBankAccount001"
 * 		Because so many bank account with long numeric account number, will crash the
 * 		display of 'LicMgrChangeVendorBankAccountStoreAssignmentsWidget', and 'OK' button
 * 		will be not displayed in the widget. Eventually, Selenium can not perform press
 * 		'OK' button.
 * 		So, we just created new vender, agents. And keep bank accounts of this vender
 * 		with short number.
 * @Date  Aug 26, 2011
 */
public class AssignToStore_CancelSuccess extends LicenseManagerTestCase {
	private VendorInfo vendor = new VendorInfo();
	private boolean runningResult = true;
	

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		lm.gotoVendorBankAccountPage();

		//1. Add a new bank account in Bank Accounts sub page
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);

		//2. User confirms the entries and desire to proceed in adding the Bank Account - Store Assignment. User cancels, System shall aborts this process
		lm.assignVendorBankAccountToStore(vendor.stores[0], vendor.bankAccount.accountRegx, vendor.bankAccount.effectiveDate, false);
		List<List<String>> tempAssignments = lm.getVendorBankAccountStoreAssignmentInfo(vendor.stores[0]);
		for(int i = 0; i < tempAssignments.size(); i ++) {
			if(tempAssignments.get(i).get(4).contains(vendor.bankAccount.accountID)) {
				runningResult &= false;
			}
		}

		//3. User confirms the entries and desire to proceed in adding the Bank Account - Store Assignment. User clicks OK button, System shall add a new assignment
		lm.assignVendorBankAccountToStore(vendor.stores[0], vendor.bankAccount.accountRegx, vendor.bankAccount.effectiveDate);
		List<String> assignment = lm.getVendorBankAccountStoreAssignmentInfo(vendor.stores[0], vendor.bankAccount.accountID);
		runningResult &= this.verifyAssignment(assignment);
         
		//4. clean up
		//If deactive need unassign first. To add unassignVendorBankAccountToStore keyword.
//		lm.deactivateVendorBankAccount(vendor.bankAccount.accountID);

		//5. final verification
		if(runningResult) {
			logger.info("All checkpoints are PASSED.");
		} else {
			throw new ActionFailedException("The checkpoints are NOT all passed. Please refer to log for details info.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		vendor.number = "AutoTest001";//updated by pzhu
		vendor.stores = new String[]{"AssignBankAccount001"};//updated by pzhu
		vendor.bankAccount.accountPrenoteStatus = "Pending";
		vendor.bankAccount.accountStatus = "Active";
		vendor.bankAccount.accountType = "Checking";
		vendor.bankAccount.routingNum = "026009593";
		vendor.bankAccount.accountNum = "c" + new Random().nextInt(1000) + DateFunctions.getLongTimeStamp();
		vendor.bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		vendor.bankAccount.accountRegx = vendor.bankAccount.accountType + " Routing # " + vendor.bankAccount.routingNum + " Acct # " + vendor.bankAccount.accountNum.substring(0, 4);
	}

	private boolean verifyAssignment(List<String> actualAssignment) {
		boolean toReturn = true;
		logger.info("Verify whether the  vendor bank account - store assignment is correct.");
		if(!actualAssignment.get(2).equalsIgnoreCase("Active")) {
			toReturn &= false;
			logger.error("Assignment Status doesn't match.");
		}
		if(!actualAssignment.get(3).contains(vendor.stores[0])) {
			toReturn &= false;
			logger.error("Assignment Store Name doesn't match.");
		}
		String secondAccountString = "ID:" + vendor.bankAccount.accountID
													+ " Type:" + vendor.bankAccount.accountType
													+ " Routing #:" + vendor.bankAccount.routingNum
													+ " Account #:" + vendor.bankAccount.accountNum.substring(0, 4);

		if(!actualAssignment.get(5).replace("\r\n", " ").contains(secondAccountString)) {
			toReturn &= false;
			logger.error("Assignment Bank Account info doesn't match.");
		}
		if(!actualAssignment.get(6).equalsIgnoreCase(DateFunctions.formatDate(vendor.bankAccount.effectiveDate, "E MMM d yyyy").toString())) {
			toReturn &= false;
			logger.error("Assignment Effective Date doesn't match.");
		}
		if(!actualAssignment.get(7).replaceAll("\r\n", " ").trim().equals((DateFunctions.formatDate(DateFunctions.getToday(), "E MMM d yyyy").toString() + " Test-Auto, QA-Auto"))) {
			toReturn &= false;
			logger.error("Assignment Last Modified Date doesn't match.");
		}
		if(toReturn) {
			logger.info("All details info of this assignment are correct.");
		}
		return toReturn;
	}
}
