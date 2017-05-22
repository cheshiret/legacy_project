package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify identifier type (Passport) related UI display, values, error message
 * @Preconditions: Passport #: id=3, state_eq_ind=0, country_req_ind=1
 * @SPEC: 
 * Identifier - Passport (COUNTRY_ REQ_IND=1) [TC:044133] 
 * Max. input length of 100 for free text input fields [TC:044146] 
 * @Task#: AUTO-1626
 * 
 * @author SWang
 * @Date  Apr 7, 2013
 */
public class IdenPassport extends HFSKWebTestCase {
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private String conmmonSchema, invaliedNum1, invaliedNum2, invaliedNum3, invaliedNum4, topPgErrorMes,
	errorMesWhenIdenIsInvalid, errorMesWhenDOBIsBlank, errorMesWhenNoProfileisFound, OneHundreddAndOneCharactersString, OneHundreddCharactersString;
	private List<String> actualErrorMeses = new ArrayList<String>();
    private int hideNum, showNum;
	private String mask, maskedNum;
	
	public void execute() {
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		
		//Add identifier
		hf.signIn(url, cus.email, cus.password);
		hf.addIdentifier(cus.identifier);
		hf.signOut();

		//Go to account lookup page
		hf.gotoLookupAccountPage();

		//1. Verify identifier type "Passport" has number and country (values without unknown, sort alphabetically), no state drop down list
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = StringUtil.EMPTY;
		cus.identifier.state = StringUtil.EMPTY;
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, true);
		lookupAccountPg.verifyCountries(cus.identifier.identifierType, cus.identifier.countries);

		//2. Selected country will be populated in the field of the country drop down
		cus.identifier.country = cus.identifier.countries.get(2);
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyCountry(cus.identifier.identifierType, cus.identifier.countries.get(2));
		cus.identifier.country = cus.identifier.countries.get(0);
		lookupAccountPg.lookupAccount(cus);

		//3. Verify the input field and country drop down for Passport will be hidden after select another identifier
		lookupAccountPg.lookupAccount(cusNew);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, true, true, false);

		//4. Verify check point 1 after select the radio button for Passport again
		cus.identifier.country = StringUtil.EMPTY;
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, true);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, false, false, false);
		lookupAccountPg.verifyCountries(cus.identifier.identifierType, cus.identifier.countries);

		//5. Check text field max input length is 100 characters
		lookupAccountPg.setIdenNum(OneHundreddAndOneCharactersString, cus.identifier.identifierType);
		lookupAccountPg.verifyIdenNum(cus.identifier.identifierType, OneHundreddCharactersString);

		//6. Verify error message when leave the input field blank and country drop down list default, click on 'Lookup Profile' button
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		actualErrorMeses = lookupAccountPg.getIdenRelatedErrorMeses(cus.identifier.identifierType);
		lookupAccountPg.verifyIdenRelatredErrorMeses(cus.identifier.identifierType, actualErrorMeses);

		//7. Verify error message when enter an invalid passport number in the input field, select a country from country drop down, click on 'Lookup Profile' button
		cus.identifier.country = cus.identifier.countries.get(2);
		cus.identifier.identifierNum = invaliedNum1; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//8. Same as 7
		cus.identifier.identifierNum = invaliedNum2; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//9. Same as 7
		cus.identifier.identifierNum = invaliedNum3; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound); //topPgErrorMes); Per DEFECT-63513
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//10. Same as 7
		cus.identifier.identifierNum = invaliedNum4; 
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMes(cus.identifier.identifierType, errorMesWhenIdenIsInvalid);

		//11. Verify error message when enter a valid customer # in the input field, leave blank in 'Date of Birth', click on 'Lookup Profile' button;
		cus.identifier.identifierNum = "1R9Y4x4115"; 
		cus.dateOfBirth = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBIsBlank);

		//12. Verify error message when enter a proper format number but not valid (not in the system) customer # in the input field, enter a valid date in 'Date of Birth'
		cus.identifier.identifierNum = "1R9Y4x4116"; 
		cus.dateOfBirth = "1986-01-02";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound);

		//13. Verify error message when Enter a valid customer# (in the system) in the input field, enter a proper format date but not the right one associated with the customer in the system in 'Date of Birth'
		cus.identifier.identifierNum = "1R9Y4x4115";
		cus.dateOfBirth = "1986-01-03";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(errorMesWhenNoProfileisFound);

		//14. Successfully lookup account
		cus.dateOfBirth = "1986-01-02";
		hf.lookupAccount(cus);
		yourAccountFoundPg.verifyIdenRecord(cus.identifier.identifierType, maskedNum+", "+cus.identifier.country); //cus.identifier.identifierNum.toUpperCase()

		//15. Go to create account page after click create a new profile link
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
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);

		cus.identifier.countries.add("Please select");
		cus.identifier.countries.add("Canada");
		cus.identifier.countries.add("United States");
		cus.identifier.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, OrmsConstants.IDEN_PASSPORT_NUM_ID));

		cus.identifier.identifierTypeID = OrmsConstants.IDEN_PASSPORT_NUM_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true, "999999999");
		cus.identifier.identifierNum = "1R9Y4x4115";
		cus.identifier.country = cus.identifier.countries.get(2);
		cus.identifier.state = StringUtil.EMPTY;
		cus.identifier.identifierSecureID = hf.getCustIdenSecureID(schema, cus.identifier.identifierType, cus.identifier.identifierNum);
		hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);
		
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		cusNew.identifier.identifierNum = StringUtil.EMPTY;
		cusNew.identifier.country = StringUtil.EMPTY;
		cusNew.identifier.state = StringUtil.EMPTY;
		
		//at least 5 number and letters combined , the entry consists of only the following characters: numbers, letters, embedded space, dash.
		//When it only contains numbers, it shall be less than or equal to the defined Maximum Value (max_value in c_identifier_type table)
		invaliedNum1 = "123";
		invaliedNum2 = "abc";
		invaliedNum3 = "1123abc%9";
		invaliedNum4 = "1234567890";

		OneHundreddAndOneCharactersString = "1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9  123456789T";
		OneHundreddCharactersString = OneHundreddAndOneCharactersString.split("T")[0];

		topPgErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		errorMesWhenIdenIsInvalid = "The Identification number is not valid for the selected identification type.";
		errorMesWhenDOBIsBlank = "Date of Birth is required";
		errorMesWhenNoProfileisFound = "No (profile is|account has been) found.*";
	}
}
