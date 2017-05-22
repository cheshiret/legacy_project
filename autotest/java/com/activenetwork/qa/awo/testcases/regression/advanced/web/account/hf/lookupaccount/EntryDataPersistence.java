package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P)
 * @Preconditions: 
 * @SPEC: Entry Data persistence when switching between identifiers [TC:044141] 
 * @Task#: AUTO-1627
 * 
 * @author Swang
 * @Date  Apr 8, 2013
 */
public class EntryDataPersistence extends HFSKWebTestCase {
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private String identifierNum, dateOfBirthFormat, topPgErrorMes, errorMesWhenNoProfileisFound;

	public void execute() {
		//Go to account lookup page
		hf.invokeURL(url);
		hf.gotoLookupAccountPage();
		
		//Enter data for customer# and passport# identifier and Date of Birth 
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.lookupAccount(cusNew);

		//All the entry data and selections shall be retained when switch between the identifiers (customer# and passport#)
		lookupAccountPg.selectIdentifierType(cus.identifier.identifierType);
		lookupAccountPg.verifyIdenTypeValues(cus.identifier);
		lookupAccountPg.selectIdenTypeCountrySync(cusNew.identifier.identifierType);
		lookupAccountPg.verifyIdenTypeValues(cusNew.identifier);

		//Check tops error message when can't provide correct info after enter invalid customer number
		cus.identifier.identifierNum = "123%%45";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound); //topPgErrorMes); Per DEFECT-63513, Requirements changed, the non-alphanumeric character is removed

		//Check only retain the current selected identifier information and DOB after submit
		lookupAccountPg.verifyIdenTypeValues(cus.identifier);

		lookupAccountPg.selectIdenTypeCountrySync(cusNew.identifier.identifierType);
		cusNew.identifier.identifierNum = StringUtil.EMPTY;
		cusNew.identifier.country = lookupAccountPg.getCountries(cusNew.identifier.identifierType).get(0);
		lookupAccountPg.verifyIdenTypeValues(cusNew.identifier);
		lookupAccountPg.verifyDateOfBirth(cus.dateOfBirth);

		//Check top error message when no matched profile found
		lookupAccountPg.lookupAccount(cusNew);
		cus.identifier.identifierNum = "123456789";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound);

		//Check only retain the current selected identifier information and DOB after submit
		lookupAccountPg.verifyIdenTypeValues(cus.identifier);
		lookupAccountPg.selectIdenTypeCountrySync(cusNew.identifier.identifierType);
		lookupAccountPg.verifyIdenTypeValues(cusNew.identifier);
		lookupAccountPg.verifyDateOfBirth(cus.dateOfBirth);

		//Check to go to your account account page after enter invalid data for passport# identifier and Date of Birth 
		cus.identifier.identifierNum = identifierNum;
		lookupAccountPg.lookupAccount(cusNew);
		hf.lookupAccountFromAccountLookupPage(cus);
		yourAccountFoundPg.verifyCustNum(cus.identifier.identifierType, cus.identifier.identifierNum);

		//Check identifier info in account account page from your account found page
		hf.gotoAccountLookupPgFromYourAccountFoundPg();
		cus.identifier.identifierNum = StringUtil.EMPTY;
		lookupAccountPg.verifyIdenTypeValues(cus.identifier);
		lookupAccountPg.selectIdenTypeCountrySync(cusNew.identifier.identifierType);
		lookupAccountPg.verifyIdenTypeValues(cusNew.identifier);
		lookupAccountPg.verifyDateOfBirth(dateOfBirthFormat);
	}

	public void wrapParameters(Object[] param) {
		cus.email = "lookupaccount@test.com";
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-02";
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		cus.identifier.country = StringUtil.EMPTY;
		cus.identifier.state = StringUtil.EMPTY;
		
		identifierNum = hf.getCustomerNumByEmail(cus.email, schema);
		cus.identifier.identifierNum = identifierNum;
		
		cusNew.dateOfBirth = cus.dateOfBirth;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true, "999999999");
		cusNew.identifier.identifierNum = "1R9Y4x4117";
		cusNew.identifier.country = "Canada";
		cusNew.identifier.state = StringUtil.EMPTY;

		dateOfBirthFormat = "YYYY-MM-DD";
		topPgErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		errorMesWhenNoProfileisFound = "No account has been found with the supplied information.*proceed to the sign up page.*";
	}
}
