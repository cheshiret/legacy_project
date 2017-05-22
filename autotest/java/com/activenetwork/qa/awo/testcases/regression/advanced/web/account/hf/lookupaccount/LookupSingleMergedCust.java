package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Create two customers with two identifiers and merge the customers and one of their identifiers in LM, then 
 * 1. look up by the merged customer info and the kept customer, check the merged message on account found page; 
 * 2. go to Purchase flow by using the merged identifier, and verify the merged identifier works
 * 3. Update customer's date of birth and look up customer by:
 * a. merged customer's id and date of birth -> account found
 * b. merged customer's id and kept customer's date of birth -> account not found
 * c. kept customer's id and merged customer's date of birth -> account not found
 * d. kept customer's not merged identifier and date of birth -> account found
 * e. kept customer's merged identifier and date of birth -> account found
 * f. merged customer's not merged identifier and date of birth -> account not found
 * @Preconditions:
 * 1. privilege "HFSK License001" exists
 * 2. the role "SK Admin - Auto" has the feature "MergeCustomerProfile" assigned
 * 3. the sql "updateXPROP.sql" has been run. The record exists:
 * SELECT * FROM X_PROP WHERE NAME='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale' and VALUE='true';
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=1870
 * d_assign_feature:id=4482
 * @SPEC: Using merged customer search - Authenticated by Identifier mode [TC:102827]
 * @Task#: Auto-1864
 * 
 * @author Lesley Wang
 * @Date  Aug 21, 2013
 */
public class LookupSingleMergedCust extends HFSKWebTestCase {
	private Customer mergedCust = new Customer();
	private CustomerIdentifier cafIdent, rcmpIdent,  mergedCAFIdent, rcmpIdent2;
	private String mergedCustMsg, noAcctFoundMsg, custDOB, updatedCustDOB, defaultIdentType; //noProfileFoundMsg
	private HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage.getInstance();
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFAccountLookupPage lookupAcctPg = HFAccountLookupPage.getInstance();
	
	@Override
	public void execute() {
		// Prepare two customers with same name and DOB, different RCMP and CAF identifiers, and purchased 1 license for each
		cus.custNum = hf.signUpNewAccount(url, cus);
		hf.addIdentifier(cafIdent);
		hf.signOut();
		
		mergedCust.custNum = hf.signUpNewAccount(url, mergedCust);
		hf.addIdentifier(mergedCAFIdent);
		hf.signOut();

		// Merge Customer B to Customer A in LM, keep all profile of customer A, merge one of the identifier of Customer B to Customer A
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus);
		lm.mergeCustomerFromCustomerDetailPg(mergedCAFIdent);
		lm.logOutLicenseManager();
		
		// Checkpoint 1-1: In Web, lookup account by customer A's customer id and DOB, verify no merged message on Account Found page
		cus.identifier = this.initializeCustIDIdent(cus.custNum);
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		acctFoundPg.verifyMergedCustMsgExist(false);
		
		// Checkpoint 1-2:  Lookup account by the merged customer's id and DOB, verify merge message is shown and customer A's profile is shown
		mergedCust.identifier = this.initializeCustIDIdent(mergedCust.custNum);;
		hf.lookupAccount(mergedCust);
		mergedCustMsg = this.generateMergedCustMsg(mergedCust.custNum);
		acctFoundPg.verifyMergedCustMsg(mergedCustMsg);
		acctFoundPg.verifyCustNum(defaultIdentType, cus.custNum);
		
		// Checkpoint2: Go to Purchase flow by using the merged identifier, and verify the merged identifier works
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoLicenseCategoriesListPg(cus.residencyStatus, mergedCAFIdent);
		catListPg.verifyPrivilegeExist(privilege.name, true);
		
		// Update DOB for customer A
		hf.updateCustDOB(updatedCustDOB);
		hf.signOut();
		
		// Checkpoint 3-1: Lookup account by customer B's id and DOB, verify merged message on account found page
		hf.lookupAccount(mergedCust);
		acctFoundPg.verifyMergedCustMsg(mergedCustMsg);
		
		// Checkpoint 3-2: Lookup account by customer B's id and customer A's DOB, verify no account found
		mergedCust.dateOfBirth = updatedCustDOB;
		hf.lookupAccount(mergedCust);
		lookupAcctPg.verifyTopPgErrorMes(noAcctFoundMsg); //noProfileFoundMsg);
		
		// Checkpoint 3-3: Lookup account by customer A's id and customer B's DOB, verify no account found
		cus.dateOfBirth = custDOB;
		hf.lookupAccount(cus);
		lookupAcctPg.verifyTopPgErrorMes(noAcctFoundMsg); //noProfileFoundMsg);
		
		// Checkpoint 3-4: Lookup account by customer A's identifier, which is not from merged profile and customer A's DOB, verify account found
		cus.identifier = rcmpIdent;
		cus.dateOfBirth = updatedCustDOB;
		hf.lookupAccount(cus);
		acctFoundPg.verifyMergedCustMsgExist(false);
		
		// Checkpoint 3-5: Lookup account by customer A's merged identifier and DOB, verify account found.
		cus.identifier = mergedCAFIdent;
		hf.lookupAccount(cus);
		acctFoundPg.verifyMergedCustMsgExist(false);
		
		// Checkpoint 3-6: Lookup account by customer B's identifier, which is not merged and DOB, verify no account found
		mergedCust.identifier = rcmpIdent2;
		mergedCust.dateOfBirth = custDOB;
		hf.lookupAccount(mergedCust);
		lookupAcctPg.verifyTopPgErrorMes(noAcctFoundMsg);
		
		// Clean up customer identifiers
		hf.deleteCustIdentByCustNum(schema, rcmpIdent.id, cus.custNum);
		hf.deleteCustIdentByCustNum(schema, cafIdent.id, cus.custNum);
		hf.deleteCustIdentByCustNum(schema, rcmpIdent2.id, mergedCust.custNum);
		hf.deleteCustIdentByCustNum(schema, mergedCAFIdent.id, mergedCust.custNum);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// CAF identifier info
		cafIdent = new CustomerIdentifier();
		cafIdent.id = IDEN_CAF_ID;
		cafIdent.identifierType = hf.getIdenTypeName(schema, cafIdent.id, true, false);
		cafIdent.identifierNum = new DecimalFormat("000000").format(new Random().nextInt(999999));
		cafIdent.state = "Alberta";
		
		mergedCAFIdent = new CustomerIdentifier();
		mergedCAFIdent.id = cafIdent.id;
		mergedCAFIdent.identifierType = cafIdent.identifierType;
		mergedCAFIdent.identifierNum = new DecimalFormat("000000").format(new Random().nextInt(999999));
		mergedCAFIdent.state = "Alberta";
		mergedCAFIdent.identTypeShortNm = hf.getIdenTypeShortName(schema, mergedCAFIdent.id);
		
		// RCMP identifier info
		rcmpIdent = new CustomerIdentifier();
		rcmpIdent.id = OrmsConstants.IDEN_RCMP_ID; 
		rcmpIdent.identifierType = hf.getIdenTypeName(schema, rcmpIdent.id, true, false);
		rcmpIdent.identifierNum = "SingleA"+new DecimalFormat("00000").format(new Random().nextInt(99999));
		rcmpIdent.state = "Ontario";
		
		rcmpIdent2 = new CustomerIdentifier();
		rcmpIdent2.id = rcmpIdent.id; 
		rcmpIdent2.identifierType = rcmpIdent.identifierType;
		rcmpIdent2.identifierNum = "SingleB"+new DecimalFormat("00000").format(new Random().nextInt(99999));
		rcmpIdent2.state = "Ontario";
		
		// Customer Default Identifier (Customer ID) - HAL ID #
		defaultIdentType = hf.getIdenTypeName(schema, IDEN_HAL_ID, false, false);
		
		// Date of birth
		custDOB = DateFunctions.getYearAfterCurrentYear(-24) + "-02-14";
		updatedCustDOB = DateFunctions.getDateAfterGivenDay(custDOB, 1);
		
		// Customer A's info (kept customer)
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultSKMailingAddress();
		long temp = DateFunctions.getCurrentTime();
		cus.fName = "SingleMFN" + temp;
		cus.lName = "SingleMLN" + temp;
		cus.email = "singlemergedemail" + temp + "@test.com";
		cus.dateOfBirth = custDOB;
		cus.identifier = rcmpIdent; 
		cus.residencyStatus = RESID_STATUS_SK;
		cus.mailingAddr.city = "Saskatoon";
		cus.mailingAddr.state = "Saskatchewan";
		
		// Customer B's info (merged customer) 
		mergedCust.setDefaultValuesForHFWebSignUp();
		mergedCust.setDefaultSKMailingAddress();
		mergedCust.fName = cus.fName;
		mergedCust.lName = cus.lName;
		mergedCust.email = "singlemerged2email" + temp + "@test.com";
		mergedCust.dateOfBirth = cus.dateOfBirth;
		mergedCust.identifier = rcmpIdent2;
		mergedCust.mailingAddr = cus.mailingAddr;
		mergedCust.residencyStatus = RESID_STATUS_SK;
		
		// Privilege Info
		privilege.name = "HFSK License001";
		
		// Login info for LM
		loginLM.location = "SK Admin/SASK";
		
		//Sara[20140303] DEFECT-61188, both cases (either cannot find the account based on the identifier, or the account is found but the DOB does not match) should get the same error message
//		noProfileFoundMsg = "No profile is found.*check and correct the information or contact our call center.*";
		noAcctFoundMsg = "No account has been found.*confirm you have entered your HAL # or other identifier correctly and resubmit.*" +
				"Note.*Existing customers must not create a second HAL account.*contact Web Support.*" +
				"If you are a new customer, proceed to the sign up page.*";
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
}