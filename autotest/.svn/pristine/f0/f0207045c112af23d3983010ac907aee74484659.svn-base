package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify date of birth format, value and error message in update account page
 * @Preconditions: 
 * @SPEC: Update Account page - DOB (YYYY-MM-DD) [TC:059851] 
 * @Task#: AUTO-1622
 * 
 * @author SWang
 * @Date  Apr 9, 2013
 */
public class DateOfBirthTextInput extends HFSKWebTestCase {
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String dateMoreThan150YearsPast, dateInFuture, dateOfBirthFormat, invalidDOB1, invalidDOB2, invalidDOB3, invalidDOB4, errorMesWhenDOBEmpty, errorMesWhenDOBInvalid, errorMesWhenDOBMoreThan150YPast, errorMesWhenDOBInFuture;

	public void execute() {
		//Go to update account page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateAccountPgFromMyAccountPanel();

		//Check the hint appears again after delete all entry, remove the focus out of the field
		updateAccountPg.setDateOfBirth(StringUtil.EMPTY);
		updateAccountPg.verifyDateOfBirth(dateOfBirthFormat);

		//Check the hint will be gone after start to enter characters in DOB field, 
		updateAccountPg.setDateOfBirth(cus.dateOfBirth);
		updateAccountPg.verifyDateOfBirth(cus.dateOfBirth);

		//Check error message after enter an invalid format of date of birth (see sample data), submit
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB1, errorMesWhenDOBInvalid);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB2, errorMesWhenDOBInvalid);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB3, errorMesWhenDOBInvalid);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB4, errorMesWhenDOBInvalid);

		//Check error message after enter a date more than 150 years in the past, submit
		this.verifyErrorMesAfterUpdateDateOfBirth(dateMoreThan150YearsPast, errorMesWhenDOBMoreThan150YPast);

		//Check error message when enter a date in the future
		this.verifyErrorMesAfterUpdateDateOfBirth(dateInFuture, errorMesWhenDOBInFuture);

		//Check error message when delete all entry, submit
		this.verifyErrorMesAfterUpdateDateOfBirth(StringUtil.EMPTY, errorMesWhenDOBEmpty);

		//Check successfully update and DOB is matching entered after enter a correct format in DOB field, fill out other fields properly, submit
		this.successfullyUpdateDOB(cus.dateOfBirth);

		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updateAccountPg.verifyDateOfBirth(cus.dateOfBirth);
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "lookupaccount@test.com";
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-02";
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_HAL_ID, false, false);
		dateInFuture = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "yyyy-MM-dd");
		dateMoreThan150YearsPast = DateFunctions.formatDate(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), -1813), "yyyy-MM-dd");
		invalidDOB1 = "1975-13-13";
		invalidDOB2 = "1975-02-31";
		invalidDOB3 = "02/02/1975";
		invalidDOB4 = "1975-ja-15";

		dateOfBirthFormat = "YYYY-MM-DD";
		errorMesWhenDOBEmpty = "Date of Birth \\("+dateOfBirthFormat+"\\) is required.";
		errorMesWhenDOBInvalid = "Date of Birth \\("+dateOfBirthFormat+"\\) entered is invalid. Please enter a date in the format "+dateOfBirthFormat+".";
		errorMesWhenDOBInFuture = "Date of Birth cannot be in( the)? future";
		errorMesWhenDOBMoreThan150YPast = "Date of Birth cannot be more than 150 years in the past.";
	}

	/**
	 * Verify error message after update date of birth with invalid message
	 * @param dateOfBirth
	 * @param errorMes
	 */
	public void verifyErrorMesAfterUpdateDateOfBirth(String dateOfBirth, String errorMes){
		updateAccountPg.setDateOfBirth(dateOfBirth);
		updateAccountPg.clickSubmitButton();
		updateAccountPg.verifyMsgExist(errorMes, true);
	}

	/**
	 * Verify successfully update date of birth
	 * @param dateOfBirth
	 */
	public void successfullyUpdateDOB(String dateOfBirth){
		HFAccountOverviewPage accountOverViewPg = HFAccountOverviewPage.getInstance();

		updateAccountPg.setDateOfBirth(dateOfBirth);
		updateAccountPg.clickSubmitButton();
		accountOverViewPg.waitLoading();
	}
}

