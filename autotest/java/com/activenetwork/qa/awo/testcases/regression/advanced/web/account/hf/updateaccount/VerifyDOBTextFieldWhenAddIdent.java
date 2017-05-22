/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Date of Birth text field on Add Identification page.
 * The field will be shown if the customer's date of birth is missing.
 * @Preconditions:
 * @SPEC:Free text input - Date of Birth [TC:044606]
 * @Task#: Auto-1631
 * 
 * @author Lesley Wang
 * @Date  Apr 28, 2013
 */
public class VerifyDOBTextFieldWhenAddIdent extends HFSKWebTestCase {
	HFAddIdentificationPage addIdentPg = HFAddIdentificationPage.getInstance();
	HFUpdateAccountPage updAcctPg  = HFUpdateAccountPage.getInstance();
	CustomerIdentifier iden = new CustomerIdentifier();
	String maskedNum, defaultDOB, invalidDOB, dateBefore150, futureDate, 
	emptyDOBErrMsg, invalidDOBErrMsg, dateBefore150ErrMsg, dateInFutureErrMsg;

	@Override
	public void execute() {
		// Clear DOB info and existing identifier of the account
		hf.updateCustDOBInProfile(schema, cus.email, StringUtil.EMPTY);
		hf.udpateCustDOB(schema, cus.email, StringUtil.EMPTY);
		hf.deleteCustIden(schema, iden.identifierTypeID, cus.email);

		hf.signIn(url, cus.email, cus.password);

		hf.gotoAddIdentificationPg();
		addIdentPg.verifyDateOfBirth(defaultDOB);

		// Input empty DOB and verify error message
		this.inputInvalidDOBAndVerifyErrorMsg(null, emptyDOBErrMsg);

		// Input invalid DOB and verify error message
		this.inputInvalidDOBAndVerifyErrorMsg(invalidDOB, invalidDOBErrMsg);

		// Input a date more than 150 years in the past and verify error message
		this.inputInvalidDOBAndVerifyErrorMsg(dateBefore150, dateBefore150ErrMsg);

		// Input a date in the future and verify error message
		this.inputInvalidDOBAndVerifyErrorMsg(futureDate, dateInFutureErrMsg);

		// Input a valid date and identifier, and verify identifier and DOB info added correctly
		addIdentPg.setDateOfBirth(cus.dateOfBirth);
		hf.addIdentifierFromAddIdentificationPg(iden);
		hf.gotoUpdateProfilePg();
		updAcctPg.verifyDateOfBirth(cus.dateOfBirth);
		updAcctPg.verifyIdentifierInfo(iden, maskedNum);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "custwithoutdob@test.com"; // d_web_hf_signupaccount, id=250
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-05";

		iden.identifierTypeID = OrmsConstants.IDEN_RCMP_ID;
		iden.identifierType = OrmsConstants.IDENT_TYPE_NAME_RCMP;
		iden.identifierNum = "X" + new DecimalFormat("00000").format(new Random().nextInt(99999));
		iden.state = "Alberta";
		iden.stateCode = "AB";

		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(iden.identifierNum, mask, hideNum, showNum);

		defaultDOB = "YYYY-MM-DD";
		invalidDOB = DateFunctions.getToday();
		dateBefore150 = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday("yyyy-MM-dd"), -151*12);
		futureDate = DateFunctions.getDateAfterToday(2, "yyyy-MM-dd");

		emptyDOBErrMsg = "Date of Birth is required";
		invalidDOBErrMsg = "Date of Birth.*entered is invalid. Please enter a date in the format " + defaultDOB + ".*";
		dateBefore150ErrMsg = "Date of Birth cannot be more than 150 years in the past.*";
		dateInFutureErrMsg = "Date of Birth cannot be in future";
	}

	private void inputInvalidDOBAndVerifyErrorMsg(String birthday, String errMsg) {
		if (birthday != null) {
			addIdentPg.setDateOfBirth(birthday);
		}
		addIdentPg.clickAddIdentificationButton();
		addIdentPg.waitLoading();
		addIdentPg.verifyErrorMsgExist(errMsg, true);
	}
}
