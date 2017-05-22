package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.customer;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Lookup the merged customer by the customer id and date of birth, 
 * verify the kept customer's info and the merged orders and licenses are shown
 * @Preconditions:
 * 1. privilege "HFSK License001" exists
 * 2. the role "SK Admin - Auto" has the feature "MergeCustomerProfile" assigned
 * 3. the SQL "updateXPROP.sql" has been run. The record exists:
 * SELECT * FROM X_PROP WHERE NAME='FindActiveCustomerProfileUsingMergedCustomerNumberDuringSale' and VALUE='true';
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=1870
 * d_assign_feature:id=4482
 * @SPEC: Using merged customer search - Authenticated by Identifier mode [TC:102827]
 * @Task#: Auto-1864
 * 
 * @author Lesley Wang
 * @Date  Aug 26, 2013
 */
public class LookupMergedCust extends HFSKWebTestCase {
	private Customer mergedCust = new Customer();
	private CustomerIdentifier rcmpIdent, rcmpIdent2;
	private HFAccountOverviewPage acctOverviewPg = HFAccountOverviewPage.getInstance();
	
	@Override
	public void execute() {
		 // Prepare two customers with same name and DOB, and purchased 1 license for each
		cus.custNum = hf.signUpNewAccount(url, cus);
		hf.makePrivilegeOrderToCart(cus, privilege);
		String ordNum = hf.checkOutCart(pay);
		String licNum = hf.getPrivilegeNumByOrdNum(schema, ordNum);
		hf.signOut();

		mergedCust.custNum = hf.signUpNewAccount(url, mergedCust);
		hf.makePrivilegeOrderToCart(mergedCust, privilege);
		String mergedOrdNum = hf.checkOutCart(pay);
		String mergedLicNum = hf.getPrivilegeNumByOrdNum(schema, mergedOrdNum);
		hf.signOut();

		// Merge Customer B to Customer A in LM, keep all profile of customer A
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus);
		lm.mergeCustomFromCustomerDetailPg();
		lm.logOutLicenseManager();
		
		//In Web Lookup account by the merged customer's id and DOB, verify customer A's info, orders and license numbers from the merged customer on Account Overview page
		mergedCust.identifier = this.initializeCustIDIdent(mergedCust.custNum);
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(mergedCust);
		acctOverviewPg.verifyPersonalInformation(cus.custNum, cus.fName, cus.lName, cus.email, StringUtil.EMPTY, StringUtil.EMPTY);
		acctOverviewPg.verifyOrdNumsExist(ordNum, mergedOrdNum);
		acctOverviewPg.verifyLicensesExist(licNum, mergedLicNum);
		hf.signOut();
		
		// Clean up
		hf.deleteCustIdentByCustNum(schema, rcmpIdent.id, cus.custNum);
		hf.deleteCustIdentByCustNum(schema, rcmpIdent2.id, mergedCust.custNum);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// RCMP identifier info
		rcmpIdent = new CustomerIdentifier();
		rcmpIdent.id = OrmsConstants.IDEN_RCMP_ID; 
		rcmpIdent.identifierType = hf.getIdenTypeName(schema, rcmpIdent.id, true, false);
		rcmpIdent.identifierNum = "MergeA"+new DecimalFormat("000000").format(new Random().nextInt(999999));
		rcmpIdent.state = "Ontario";
		
		rcmpIdent2 = new CustomerIdentifier();
		rcmpIdent2.id = rcmpIdent.id; 
		rcmpIdent2.identifierType = rcmpIdent.identifierType;
		rcmpIdent2.identifierNum = "MergeB"+new DecimalFormat("000000").format(new Random().nextInt(999999));
		rcmpIdent2.state = "Ontario";
		
		// Customer A's info (kept customer)
		long temp = DateFunctions.getCurrentTime();
		cus.setDefaultValuesForHFWebSignUp();
		cus.fName = "MergedFN" + temp;
		cus.lName = "MergedLN" + temp;
		cus.email = "mergedcustemail" + temp + "@test.com";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-26) + "-02-14";;
		cus.identifier = rcmpIdent; 
		cus.residencyStatus = RESID_STATUS_SK;
		cus.setDefaultSKMailingAddress();
		
		// Customer B's info (merged customer) 
		mergedCust.setDefaultValuesForHFWebSignUp();
		mergedCust.fName = cus.fName;
		mergedCust.lName = cus.lName;
		mergedCust.email = "mergedcust2email" + temp + "@test.com";
		mergedCust.dateOfBirth = cus.dateOfBirth;
		mergedCust.identifier = rcmpIdent2;
		mergedCust.mailingAddr = cus.mailingAddr;
		mergedCust.residencyStatus = RESID_STATUS_SK;
		
		// Privilege Info
		privilege.name = "HFSK License001";
		privilege.licenseYear = hf.getFiscalYear(schema);		
		
		// Login info for LM
		loginLM.location = "SK Admin/SASK";
	}
	
	private CustomerIdentifier initializeCustIDIdent(String custNum) {
		CustomerIdentifier ident = new CustomerIdentifier();
		ident.identifierType = hf.getIdenTypeName(schema, IDEN_HAL_ID, false, false);;
		ident.identifierNum = custNum;
		ident.state = StringUtil.EMPTY;
		return ident;
	}
}
