package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify customer number related UI display, values, error message
 * @Preconditions: Customer #: id=1, state_eq_ind=0, country_req_ind=0
 * @SPEC:
 *  Identifier - Customer # [TC:044130] Identifier - Customer # [TC:044130]  
 *  Max. input length of 100 for free text input fields [TC:044146] 
 * @Task#: AUTO-1625
 * 
 * @author Swang
 * @Date  Apr 1, 2013
 */
public class IdenCustomerNumber extends HFSKWebTestCase {
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private String customerNum,  invaliedNum1, invaliedNum2, invaliedNum3, topPgErrorMes,errorMesWhenIdenIsBlank,
	errorMesWhenIdenIsInvalid, errorMesWhenDOBIsBlank, errorMesWhenNoProfileisFound, OneHundreddAndOneCharactersString, OneHundreddCharactersString;

	public void execute() {
		//Get customer#
		hf.signIn(url, cus.email, cus.password);
		customerNum = hf.getIdenNumFromUpdateAccountPg(cus.identifier.identifierType);
		hf.signOut();

		//Go to account lookup page
		hf.gotoLookupAccountPage();

		//1. Verify customer# radio is selected, has number, no country and province drop down list 
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, false);

		//2. Verify customer# radio is not selected, no number, country and province after select another identifier
		lookupAccountPg.lookupAccount(cusNew);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, true, true, true);

		//3. Verify customer# radio is selected, has number, no country and province drop down list after select identifier Customer# again 
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = StringUtil.EMPTY;
		cus.identifier.state = StringUtil.EMPTY;
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, false);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, false, false, false);

		//4. Check text field max input length is 100 characters
		lookupAccountPg.setIdenNum(OneHundreddAndOneCharactersString, cus.identifier.identifierType);
		lookupAccountPg.verifyIdenNum(cus.identifier.identifierType, OneHundreddCharactersString);

		//5. Verify error message when leave the input field blank, click on 'Lookup Profile' button
		cus.identifier.identifierNum = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsBlank);

		//6. Verify error message when enter an invalid customer# in the input field
		cus.identifier.identifierNum = invaliedNum1; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//7. Verify error message when enter an invalid customer# in the input field
		cus.identifier.identifierNum = invaliedNum2; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound); //(topPgErrorMes);DEFECT-63513
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//8. Verify error message when enter an invalid customer# in the input field
		cus.identifier.identifierNum = invaliedNum3; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//9. Verify error message when enter a valid customer # in the input field, leave blank in 'Date of Birth', click on 'Lookup Profile' button;
		cus.identifier.identifierNum = "1R9Y4x4114"; 
		cus.dateOfBirth = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBIsBlank);

		//10. Verify error message when enter a proper format customer# but not valid (not in the system) customer # in the input field, enter a valid date in 'Date of Birth'
		cus.dateOfBirth = "1986-01-02";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound);

		//11. Verify error message when Enter a valid customer# (in the system) in the input field, enter a proper format date but not the right one associated with the customer in the system in 'Date of Birth'
		cus.identifier.identifierNum = customerNum;
		cus.dateOfBirth = "1986-01-03";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound);

		//12. Successfully lookup account
		cus.dateOfBirth = "1986-01-02";
		hf.lookupAccount(cus);
		yourAccountFoundPg.verifyCustNum(cus.identifier.identifierType, customerNum);

		//13. Go to create account page after click create a new profile link
		cus.dateOfBirth = "1986-01-03";
		cus.identifier.identifierNum = "123456798";
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoCreateAccountPgFromAccountLookupPg();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "lookupaccount@test.com";
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-02";
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		cus.identifier.identifierNum = "1R9Y4x4114";
		
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
        cusNew.identifier.identifierNum = StringUtil.EMPTY;
        cusNew.identifier.country = StringUtil.EMPTY;
        cusNew.identifier.state = StringUtil.EMPTY;
        
		invaliedNum1 = "abcd";
		invaliedNum2 = "0012#abcd";
		invaliedNum3 = "1234";

		OneHundreddAndOneCharactersString = "1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9  123456789T";
		OneHundreddCharactersString = OneHundreddAndOneCharactersString.split("T")[0];

		topPgErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		errorMesWhenIdenIsBlank = cus.identifier.identifierType+" is required.";
		errorMesWhenIdenIsInvalid = "The Identification number is not valid for the selected identification type.";
		errorMesWhenDOBIsBlank = "Date of Birth is required";
		errorMesWhenNoProfileisFound = "No (profile is|account has been) found.*";
	}
}
