package com.activenetwork.qa.awo.testcases.regression.basic.web.hf;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
/**
 * 
 * @Description: P
 * 1. Failed to lookup account, invalid customer# or valid customer# with a proper format but not the right one DOB associated with the customer
 * 2. Successfully lookup account with valid customer# and property and right of DOB associated with the customer
 * @Preconditions:
 * @SPEC: Identify Customer (Individual class) [TC:044125]
 * @Task#: AUTO-1625, AUTO-1626 and AUTO-1627
 * 
 * @author Swang
 * @Date  Apr 9, 2013
 */
public class LookupAccount extends HFSKWebTestCase {
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private String customerNum, topPgErrorMes, errorMesWhenNoProfileisFound, invaliedNum;

	public void execute() {
		//Get customer#
//		hf.invokeURL(url);
		hf.signIn(url, cus.email, cus.password);
		customerNum = hf.getIdenNumFromUpdateAccountPg(cus.identifier.identifierType);
		hf.signOut();

		//Look up account with invalid customer#
		cus.identifier.identifierNum = invaliedNum; 
		hf.lookupAccount(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);

		//Look up with valid customer# and a proper format but not the right one DOB associated with the customer
		cus.identifier.identifierNum = customerNum;
		cus.dateOfBirth = "1986-01-03";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound);

		//Successfully look up account
		cus.dateOfBirth = "1986-01-02";
		hf.lookupAccount(cus);
		yourAccountFoundPg.verifyCustNum(cus.identifier.identifierType, cus.identifier.identifierNum);
	}

	public void wrapParameters(Object[] param) {
		cus.email = "lookupaccount@test.com";
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-02";
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		invaliedNum = "abcd";

		topPgErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		
		//Sara[20140303] DEFECT-61188
//		errorMesWhenNoProfileisFound = "No profile is found. Please check and correct the information or contact our call center if everything is accurate.";
		errorMesWhenNoProfileisFound = "No account has been found with the supplied information.*sign up page.*";
	}
}