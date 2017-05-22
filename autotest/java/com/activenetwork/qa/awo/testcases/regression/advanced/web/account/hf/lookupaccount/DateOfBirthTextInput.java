package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (DEFECT-42940) Verify date of birth format, value and error message in lookup account page
 * @Preconditions: 
 * @SPEC: 
 * Account Lookup page - DOB (YYYY-MM-DD) [TC:059849] 
 * Free text input - Date of Birth [TC:044127] 
 * Max. input length of 100 for free text input fields [TC:044146] 
 * 
 * @Task#: AUTO-1625, AUTO-1622
 * 
 * @author SWang
 * @Date  Apr 8, 2013
 */
public class DateOfBirthTextInput extends HFSKWebTestCase {
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	private HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	private String custNum, dateMoreThan150YearsPast, dateInFuture, dateOfBirthFormat, OneHundreddAndOneCharactersString, OneHundreddCharactersString,
	topPgErrorMes, errorMesWhenNoProfileisFound, errorMesWhenDOBEmpty, errorMesWhenDOBInvalid, errorMesWhenDOBMoreThan150YPast, errorMesWhenDOBInFuture;

	public void execute() {
		//Get customer #
		hf.signIn(url, cus.email, cus.password);
		custNum = hf.getIdenNumFromUpdateAccountPg(cus.identifier.identifierType);
		cus.identifier.identifierNum = custNum;
		hf.signOut();

		//Go to lookup account page
		hf.gotoLookupAccountPage();

		//Check the hint in Date of Birth field:YYYY-MM-DD
		lookupAccountPg.verifyDateOfBirth(dateOfBirthFormat, true);

		//Check the hint will be gone after start to enter characters in DOB field, 
		lookupAccountPg.setDateOfBirth(cus.dateOfBirth);
		lookupAccountPg.verifyDateOfBirth(cus.dateOfBirth);

		//Check the hint appears again after delete all entry, remove the focus out of the field
		lookupAccountPg.setDateOfBirth(StringUtil.EMPTY);
		lookupAccountPg.verifyDateOfBirth(dateOfBirthFormat, true);

		//Check DOB max input length is 100
		lookupAccountPg.setDateOfBirth(OneHundreddAndOneCharactersString);
		lookupAccountPg.verifyDateOfBirth(OneHundreddCharactersString);

		//Check error message after enter an invalid format of date of birth (see sample data), submit
		cus.dateOfBirth = "1975-13-13";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBInvalid);

		cus.dateOfBirth = "1975-02-31";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBInvalid);

		cus.dateOfBirth = "02/02/1975";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBInvalid);

		//Check the date entered by user shall be retained in 'Date of Birth' field, instruction 'MM/DD/YYYY' should not be showing
		lookupAccountPg.verifyDateOfBirth(cus.dateOfBirth);

		//Check error message after enter a date more than 150 years in the past, submit
		cus.dateOfBirth = dateMoreThan150YearsPast;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBMoreThan150YPast);

		//Check error message when enter a date in the future
		cus.dateOfBirth = dateInFuture;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBInFuture);

		//Check error message when delete all entry, submit
		cus.dateOfBirth = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topPgErrorMes);
		lookupAccountPg.verifyDateOfBirthErrorMes(errorMesWhenDOBEmpty);

		//Check no error message showing above the DOB field after enter a correct format in DOB field, "12345" for customer#, submit
		cus.dateOfBirth = "1986-01-02";
		cus.identifier.identifierNum = "12345";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyErrorMsgExist(errorMesWhenNoProfileisFound, true); //Sara[11/13/2013], message is changed. (errorMesWhenNoProfileisFound);
		lookupAccountPg.verifyDateOfBirthErrorMesExist(false);

		//Check it shows confirm identify page: Your Account Found after enter a valid DOB + HAL ID#, which belongs to an existing account, submit
		cus.identifier.identifierNum = custNum;
		hf.lookupAccount(cus);
		yourAccountFoundPg.waitLoading();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "lookupaccount@test.com";
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-02";
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		dateInFuture = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "yyyy-MM-dd");
		dateMoreThan150YearsPast = DateFunctions.formatDate(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), -1813), "yyyy-MM-dd");
		OneHundreddAndOneCharactersString = "1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9  123456789T";
		OneHundreddCharactersString = OneHundreddAndOneCharactersString.split("T")[0];

		dateOfBirthFormat = "YYYY-MM-DD";
		topPgErrorMes = "We need you to correct or provide more information. Please see each marked section.";
//		errorMesWhenNoProfileisFound = "No profile is found with the supplied information. Please reenter or create a new profile";
		errorMesWhenNoProfileisFound = "No account has been found with the supplied information.*If you are a new customer, proceed to the sign up page and enter the required information\\.";

		errorMesWhenDOBEmpty = "Date of Birth is required";
		errorMesWhenDOBInvalid = "Date of Birth ("+dateOfBirthFormat+") entered is invalid. Please enter a date in the format "+dateOfBirthFormat+".";
		errorMesWhenDOBInFuture = "Date of Birth cannot be in( the)? future";
		errorMesWhenDOBMoreThan150YPast = "Date of Birth cannot be more than 150 years in the past.";
	}
}
