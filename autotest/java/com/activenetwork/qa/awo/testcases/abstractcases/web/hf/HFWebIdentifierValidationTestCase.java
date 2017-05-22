package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is abstract test case for identifier validation
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jul 17, 2013
 */
public abstract class HFWebIdentifierValidationTestCase extends HFWebTestCase {
	protected HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
	protected HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage.getInstance();
	protected String topPgErrorMes, errMsgWhenIdenIsBlank, errMsgWhenIdenIsInvalid, 
		errMsgWhenDOBIsBlank, errMsgWhenNoProfileFound, errMsgWhenNoAccountFound, 
		errMsgWhenStateIsBlank, errMsgWhenCountryIsBlank;
	protected List<String> msgs;
	
	protected HFWebIdentifierValidationTestCase() {
		super();
		topPgErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		errMsgWhenIdenIsBlank = " is required.";
		errMsgWhenStateIsBlank = "State/Province is required.";
		errMsgWhenCountryIsBlank = "Country is required.";
		errMsgWhenIdenIsInvalid = "The Identification number is not valid for the selected identification type.";
		errMsgWhenDOBIsBlank = "Date of Birth is required";
		errMsgWhenNoProfileFound = "No profile is found. Please check and correct the information or contact our call center if everything is accurate.";
		errMsgWhenNoAccountFound = "No account has been found.*proceed to the sign up page and enter the required information.";
	}
	
	protected void initialCommonHFSKTestInfo() {
		url =  TestProperty.getProperty(env + ".web.hfsk.url");
		schema = DataBaseFunctions.getSchemaName("SK", env);
	}
	
	protected void initialCommonHFMOTestInfo() {
		url =  TestProperty.getProperty(env + ".web.hfmo.url");
		schema = DataBaseFunctions.getSchemaName("MO", env);
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);
	}

	/** Verify multiple error messages when lookup account with invalid info */
	protected void verifyErrorMsgWhenLookUpAcctWithInvalidInfo(Customer cus, String topErrMsg, String dobErrMsg, List<String> identMsgs) {
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topErrMsg);
		if (identMsgs != null && identMsgs.size() > 0) {
			lookupAccountPg.verifyIdenRelatredErrorMeses(cus.identifier.identifierType, identMsgs);
		}
		if (StringUtil.notEmpty(dobErrMsg)) {
			lookupAccountPg.verifyDateOfBirthErrorMes(dobErrMsg);
		} else {
			lookupAccountPg.verifyDateOfBirthErrorMesExist(false);
		}
		logger.info("Verify Error Message correctly when input ident number=" + cus.identifier.identifierNum + ", date of birth=" + cus.dateOfBirth);
	}
	
	/** Verify single error message when lookup account with invalid info */
	protected void verifyErrorMsgWhenLookUpAcctWithInvalidInfo(Customer cus, String topErrMsg, String dobErrMsg, String identMsg) {
		List<String> msgs = new ArrayList<String>();
		if (StringUtil.notEmpty(identMsg))
			msgs.add(identMsg);
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topErrMsg, dobErrMsg, msgs);
	}
}

