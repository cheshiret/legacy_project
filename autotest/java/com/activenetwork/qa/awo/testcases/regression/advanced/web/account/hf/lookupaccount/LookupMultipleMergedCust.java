package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Create 3 customers (Customer A, B, C) with the same first name, last name and date of birth, purchase privilege by each customer,
 * and merge the customer C to customer B, then merge customer B to customer A. Then 
 * 1. enter customer A's id and DOB, submit -> account found and no merged message
 * 2. enter customer B's id and DOB, submit -> account found and has merged message, customer A's info is shown, orders of customer A,B,C are shown
 * 3. enter customer C's id and DOB, submit -> account found and has merged message, customer A's info is shown, orders of customer A,B,C are shown
 * @Preconditions:
 * 1. privilege "HFSK License001" exists
 * 2. the role "SK Admin - Auto" has the feature "MergeCustomerProfile" assigned
 * 3. the sql "updateXPROP.sql" has been run. The record exists:
 * SELECT * FROM X_PROP WHERE NAME='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale' and VALUE='true';
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=1870
 * d_assign_feature:id=4482
 * @SPEC: Using merged customer number search - Multi-Merged case - Authenticated by Identifier mode [TC:102864]
 * 
 * @Task#: Auto-1864
 * 
 * @author Lesley Wang
 * @Date  Aug 27, 2013
 */
public class LookupMultipleMergedCust extends HFSKWebTestCase {
	private Customer mergedCustB = new Customer();
	private Customer mergedCustC = new Customer();
	private CustomerIdentifier rcmpIdentA, rcmpIdentB, rcmpIdentC;
	private String mergedCustMsg, defaultIdentType, ordNum, mergedOrdNumB, mergedOrdNumC, licNum, mergedLicNumB, mergedLicNumC;
	private HFAccountOverviewPage acctOverviewPg = HFAccountOverviewPage.getInstance();
	private HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage.getInstance();
	
	@Override
	public void execute() {
		// Prepare 3 customers with same name and DOB, and purchased 1 license for each
		String[] tempStrs = this.prepareCustAndPurchasePriv(cus);
		ordNum = tempStrs[0];
		licNum = tempStrs[1];

		tempStrs = this.prepareCustAndPurchasePriv(mergedCustB);
		mergedOrdNumB = tempStrs[0];
		mergedLicNumB = tempStrs[1];

		tempStrs = this.prepareCustAndPurchasePriv(mergedCustC);
		mergedOrdNumC = tempStrs[0];
		mergedLicNumC = tempStrs[1];
		
		// Merge Customer C to Customer B, then Customer B to Customer A in LM, keep all profile of customer A
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(mergedCustB);
		lm.mergeCustomerFromCustomerDetailPg();
		
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus);
		lm.mergeCustomerFromCustomerDetailPg();
		lm.logOutLicenseManager();
		
		//Checkpoint 1: In Web Lookup account by customer A's id and DOB, verify no merged message
		hf.invokeURL(url);
		this.lookupCustAndVerify(cus, false);
		
		//Checkpoint 2: In Web Lookup account by customer B's id and DOB, verify customer A's info, orders and license numbers from the merged customer on Account Overview page
		this.lookupCustAndVerify(mergedCustB, true);
		hf.signOut();
		
		//Checkpoint 3: In Web Lookup account by customer C's id and DOB, verify customer A's info, orders and license numbers from the merged customer on Account Overview page
		this.lookupCustAndVerify(mergedCustC, true);
		hf.signOut();		
		
		// Clean up
		hf.deleteCustIdentByCustNum(schema, rcmpIdentA.id, cus.custNum);
		hf.deleteCustIdentByCustNum(schema, rcmpIdentB.id, mergedCustB.custNum);		
		hf.deleteCustIdentByCustNum(schema, rcmpIdentC.id, mergedCustC.custNum);		
	}

	@Override
	public void wrapParameters(Object[] param) {
		// RCMP identifier info
		rcmpIdentA = new CustomerIdentifier();
		rcmpIdentA.id = OrmsConstants.IDEN_RCMP_ID; 
		rcmpIdentA.identifierType = hf.getIdenTypeName(schema, rcmpIdentA.id, true, false);
		rcmpIdentA.identifierNum = "MultiA"+new DecimalFormat("000000").format(new Random().nextInt(999999));
		rcmpIdentA.state = "Ontario";
		
		rcmpIdentB = new CustomerIdentifier();
		rcmpIdentB.id = rcmpIdentA.id; 
		rcmpIdentB.identifierType = rcmpIdentA.identifierType;
		rcmpIdentB.identifierNum = "MultiB"+new DecimalFormat("000000").format(new Random().nextInt(999999));
		rcmpIdentB.state = "Ontario";
		
		rcmpIdentC = new CustomerIdentifier();
		rcmpIdentC.id = rcmpIdentA.id; 
		rcmpIdentC.identifierType = rcmpIdentA.identifierType;
		rcmpIdentC.identifierNum = "MultiC"+new DecimalFormat("000000").format(new Random().nextInt(999999));
		rcmpIdentC.state = "Ontario";
		
		defaultIdentType = hf.getIdenTypeName(schema, IDEN_HAL_ID, false, false);
		
		// Customer A's info (kept customer)
		long temp = DateFunctions.getCurrentTime();
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultSKMailingAddress();
		cus.fName = "MultiMFN" + temp;
		cus.lName = "MultiMFN" + temp;
		cus.email = "multimergedemail" + temp + "@test.com";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-27) + "-02-14";;
		cus.identifier = rcmpIdentA; 
		cus.residencyStatus = RESID_STATUS_SK;
		cus.mailingAddr.city = "Saskatoon";
		cus.mailingAddr.state = "Saskatchewan";
		
		// Customer B's info (merged customer) 
		mergedCustB.setDefaultValuesForHFWebSignUp();
		mergedCustB.setDefaultSKMailingAddress();
		mergedCustB.fName = cus.fName;
		mergedCustB.lName = cus.lName;
		mergedCustB.email = "multimergedemail2" + temp + "@test.com";
		mergedCustB.dateOfBirth = cus.dateOfBirth;
		mergedCustB.identifier = rcmpIdentB;
		mergedCustB.mailingAddr = cus.mailingAddr;
		mergedCustB.residencyStatus = RESID_STATUS_SK;
		
		// Customer C's info
		mergedCustC.setDefaultValuesForHFWebSignUp();
		mergedCustC.setDefaultSKMailingAddress();
		mergedCustC.fName = cus.fName;
		mergedCustC.lName = cus.lName;
		mergedCustC.email = "multimergedemail3" + temp + "@test.com";
		mergedCustC.dateOfBirth = cus.dateOfBirth;
		mergedCustC.identifier = rcmpIdentC;
		mergedCustC.mailingAddr = cus.mailingAddr;
		mergedCustC.residencyStatus = RESID_STATUS_SK;
		
		// Privilege Info
		privilege.name = "HFSK License001";
		
		// Login info for LM
		loginLM.location = "SK Admin/SASK";		
	}

	private String[] prepareCustAndPurchasePriv(Customer cust) {
		cust.custNum = hf.signUpNewAccount(url, cust);
		hf.makePrivilegeOrderToCart(cust, privilege);
		String ordNum = hf.checkOutCart(pay);
		String licNum = hf.getPrivilegeNumByOrdNum(schema, ordNum);
		hf.signOut();
		return new String[] {ordNum, licNum};
	}
	
	private CustomerIdentifier initializeCustIDIdent(String custNum) {
		CustomerIdentifier ident = new CustomerIdentifier();
		ident.identifierType = defaultIdentType;
		ident.identifierNum = custNum;
		ident.state = StringUtil.EMPTY;
		return ident;
	}
	
	/** Generate merged customer message on Account Found page*/
	private String generateMergedCustMsg(String custNum) {
		String msg = "Your account with HAL ID " + custNum + " is identified as duplicated and deactivated.\\s*" +
				"All profile and purchase information has been moved to this account.*";
		return msg;
	}
	
	private void lookupCustAndVerify(Customer custForLookup, boolean isMergedMsgExist) {
		custForLookup.identifier = this.initializeCustIDIdent(custForLookup.custNum);
		hf.lookupAccount(custForLookup);
		acctFoundPg.verifyMergedCustMsgExist(isMergedMsgExist);
		if (isMergedMsgExist) {
			mergedCustMsg = this.generateMergedCustMsg(custForLookup.custNum);
			acctFoundPg.verifyMergedCustMsg(mergedCustMsg);
			acctFoundPg.verifyCustNum(defaultIdentType, cus.custNum);
			
			hf.gotoAccountOverviewPgFromYourAccountFoundPg();
			acctOverviewPg.verifyPersonalInformation(cus.custNum, cus.fName, cus.lName, cus.email, StringUtil.EMPTY, StringUtil.EMPTY);
			acctOverviewPg.verifyOrdNumsExist(ordNum, mergedOrdNumB, mergedOrdNumC);
			acctOverviewPg.verifyLicensesExist(licNum, mergedLicNumB, mergedLicNumC);
		}	
		logger.info("Successfully lookup customer and verify the info for customer id=" + custForLookup.custNum);
	}
}
