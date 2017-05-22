package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (P) Verify date of birth format, value and error message in
 *               create an account page
 * @Preconditions:
 * @SPEC: 
 * Create an Account page - DOB (YYYY-MM-DD) [TC:059850]
 * Customer Profile - Date of Birth [TC:044143]
 * DOB check [TC:048827] 
 * @Task#: AUTO-1622, AUTO-1771, Auto-1483
 * 
 * @author SWang
 * @Date Apr 9, 2013, July 10, 2013
 */
public class DateOfBirthTextInput extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private String dateMoreThan150YearsPast, dateInFuture, dateOfBirthFormat,
			invalidDOB1, invalidDOB2, invalidDOB3, invalidDOB4,
			errorMesWhenDOBEmpty, errorMesWhenDOBInvalid,
			errorMesWhenDOBMoreThan150YPast, errorMesWhenDOBInFuture;

	public void execute() {
		// Go to create account page
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);
		
		// Check the hint in Date of Birth field:YYYY-MM-DD in user id mode, or the date by look up account in identifier mode
		if (hf.isSignInWorkFlows(url)) {
			createAccPg.verifyDateOfBirth(dateOfBirthFormat);
		} else {
			createAccPg.verifyDateOfBirth(cus.dateOfBirth); 
		}	
		
		// Check the hint will be gone after start to enter characters in DOB
		// field,
		createAccPg.setDateOfBirth(cus.dateOfBirth);
		createAccPg.verifyDateOfBirth(cus.dateOfBirth);

		// Check the hint appears again after delete all entry, remove the focus
		// out of the field
		createAccPg.setDateOfBirth(StringUtil.EMPTY);
		createAccPg.verifyDateOfBirth(dateOfBirthFormat);

		// Check error message after enter an invalid format of date of birth
		// (see sample data), submit
		createAccPg.setupNewAccountInfo(cus);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB1,
				errorMesWhenDOBInvalid);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB2,
				errorMesWhenDOBInvalid);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB3,
				errorMesWhenDOBInvalid);
		this.verifyErrorMesAfterUpdateDateOfBirth(invalidDOB4,
				errorMesWhenDOBInvalid);

		// Check error message after enter a date more than 150 years in the
		// past, submit
		this.verifyErrorMesAfterUpdateDateOfBirth(dateMoreThan150YearsPast,
				errorMesWhenDOBMoreThan150YPast);

		// Check error message when enter a date in the future
		this.verifyErrorMesAfterUpdateDateOfBirth(dateInFuture,
				errorMesWhenDOBInFuture);

		// Check error message when delete all entry, submit
		this.verifyErrorMesAfterUpdateDateOfBirth(StringUtil.EMPTY,
				errorMesWhenDOBEmpty);

		// Check successfully update and DOB is matching entered after enter a
		// correct format in DOB field, fill out other fields properly, submit
		this.successfullyUpdateDOB(cus.dateOfBirth);

		hf.gotoUpdateAccountPgFromMyAccountPanel();
		createAccPg.verifyDateOfBirth(cus.dateOfBirth);
		hf.signOut();
		hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
	}

	public void wrapParameters(Object[] param) {
		cus.email = "updateDOB" + hf.getNextEmail();
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultSKMailingAddress();
		cus.dateOfBirth = "1986-01-03";
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		schema = DataBaseFunctions.getSchemaName("SK", env);

		dateInFuture = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(2), "yyyy-MM-dd");
		dateMoreThan150YearsPast = DateFunctions.formatDate(DateFunctions
				.getDateAfterGivenMonth(DateFunctions.getToday(), -1813),
				"yyyy-MM-dd");
		invalidDOB1 = "1975-13-13";
		invalidDOB2 = "1975-02-31";
		invalidDOB3 = "02/02/1975";
		invalidDOB4 = "1975-ja-15";

		dateOfBirthFormat = "YYYY-MM-DD";
		errorMesWhenDOBEmpty = "Date of Birth \\(" + dateOfBirthFormat
				+ "\\) is required.";
		errorMesWhenDOBInvalid = "Date of Birth \\(" + dateOfBirthFormat
				+ "\\) entered is invalid. Please enter a date in the format "
				+ dateOfBirthFormat + ".";
		errorMesWhenDOBInFuture = "Date of Birth cannot be in( the)? future";
		errorMesWhenDOBMoreThan150YPast = "Date of Birth cannot be more than 150 years in the past.";
	}

	/**
	 * Verify error message after update date of birth with invalid message
	 * 
	 * @param dateOfBirth
	 * @param errorMes
	 */
	public void verifyErrorMesAfterUpdateDateOfBirth(String dateOfBirth,
			String errorMes) {
		createAccPg.setDateOfBirth(dateOfBirth);
		createAccPg.clickCreateAccount();
		createAccPg.verifyErrorMsgExist(errorMes, true);
	}

	/**
	 * Verify successfully update date of birth
	 * 
	 * @param dateOfBirth
	 */
	public void successfullyUpdateDOB(String dateOfBirth) {
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();

		createAccPg.setDateOfBirth(dateOfBirth);
		createAccPg.clickCreateAccount();
		accountOverviewPg.waitLoading();
	}
}
