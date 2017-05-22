package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:No testing data needed.
 * @SPEC:TC068025:Create Profile - Identification section - validation error messages
 * @Task#:Auto-1870
 * 
 * @author Swang
 * @Date  Sep 12, 2013
 */
public class ErrorMesForIdenSection extends HFMOWebTestCase {
	private HFCreateAccountPage createAccountPg = HFCreateAccountPage.getInstance();
	private String pleaseSelectValue, invalidValue, topErrorMes, idenTypeReqMes, idenNumReqMes, countryReqMes, stateReqMes, invalidNumMes1, invalidNumMes2, invalidNumMes3, tempStr;
	private CustomerIdentifier socialSecNumIdent, socialSecNumIdent2, usDirversLicIdent, usDirversLicIdent2, passportIdent, passportIdent2, otherIdent, otherIdent2;

	public void execute() {
		//Go to create account page
		hf.invokeURL(url);
		hf.gotoCreateAccountPage(url, cus);

		//Verify error message when iden type as default value 'Please select'
		cus.identifier.identifierType = pleaseSelectValue;
		hf.createAnAccount(cus);
		createAccountPg.verifyErrorMsgExist(topErrorMes, true);
		createAccountPg.verifyErrorMsgExist(idenTypeReqMes, true);

		//Iden without state and country
		setupIden(socialSecNumIdent);
		createAccountPg.verifyErrorMsgExist(topErrorMes, true);
		createAccountPg.verifyErrorMsgExist(idenNumReqMes, true);
		createAccountPg.verifyErrorMsgExist(countryReqMes, false);
		createAccountPg.verifyErrorMsgExist(stateReqMes, false);

		setupIden(socialSecNumIdent2);
		createAccountPg.verifyErrorMsgExist(socialSecNumIdent2.identifierType+invalidNumMes2, true);

		//Iden has state only
		setupIden(usDirversLicIdent);
		createAccountPg.verifyErrorMsgExist(topErrorMes, true);
		createAccountPg.verifyErrorMsgExist(idenNumReqMes, true);
		createAccountPg.verifyErrorMsgExist(countryReqMes, false);
		createAccountPg.verifyErrorMsgExist(stateReqMes, true);

		setupIden(usDirversLicIdent2);
		createAccountPg.verifyErrorMsgExist(usDirversLicIdent2.identifierType+invalidNumMes3, true);

		//Iden has country only
		setupIden(passportIdent);
		createAccountPg.verifyErrorMsgExist(topErrorMes, true);
		createAccountPg.verifyErrorMsgExist(idenNumReqMes, true);
		createAccountPg.verifyErrorMsgExist(countryReqMes, true);
		createAccountPg.verifyErrorMsgExist(stateReqMes, false);

		setupIden(passportIdent2);
		createAccountPg.verifyErrorMsgExist(passportIdent2.identifierType+invalidNumMes1, true);

		//Iden has state and country
		setupIden(otherIdent);
		createAccountPg.verifyErrorMsgExist(topErrorMes, true);
		createAccountPg.verifyErrorMsgExist(idenNumReqMes, true);
		createAccountPg.verifyErrorMsgExist(countryReqMes, false);//State DDL will display after select country
		createAccountPg.verifyErrorMsgExist(stateReqMes, true);

		setupIden(otherIdent2);
		createAccountPg.verifyErrorMsgExist(otherIdent2.identifierType+invalidNumMes1, true);
	}

	public void wrapParameters(Object[] param) {
		pleaseSelectValue = "Please select";
		invalidValue = "85&^45";
		tempStr = new DecimalFormat("000000000").format(new Random().nextInt(999999999));
		
		cus.setDefaultValuesForHFWebSignUp();
		cus.email = "hfmo_99999@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "IdenSection_FN";
		cus.lName = "IdenSection_LN";
		cus.custGender = "Male";
		cus.eyeColor = StringUtil.EMPTY;
		cus.hairColor = StringUtil.EMPTY;
		cus.heightFt = StringUtil.EMPTY;
		cus.heightIn = StringUtil.EMPTY;
		
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = tempStr;

		//No country and state
		socialSecNumIdent = new CustomerIdentifier();
		socialSecNumIdent.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID; 
		socialSecNumIdent.identifierType = hf.getIdenTypeName(schema, socialSecNumIdent.id, false, false);
		socialSecNumIdent.identifierNum = StringUtil.EMPTY;

		socialSecNumIdent2 = new CustomerIdentifier();
		socialSecNumIdent2.id = socialSecNumIdent.id;
		socialSecNumIdent2.identifierType = socialSecNumIdent2.identifierType;
		socialSecNumIdent2.identifierNum = invalidValue;

		//Have state only
		usDirversLicIdent = new CustomerIdentifier();
		usDirversLicIdent.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID; 
		usDirversLicIdent.identifierType = hf.getIdenTypeName(schema, usDirversLicIdent.id, true, false);
		usDirversLicIdent.identifierNum = StringUtil.EMPTY;
		usDirversLicIdent.state = pleaseSelectValue;

		usDirversLicIdent2 = new CustomerIdentifier();
		usDirversLicIdent2.id = usDirversLicIdent.id;
		usDirversLicIdent2.identifierType = usDirversLicIdent.identifierType;
		usDirversLicIdent2.identifierNum = invalidValue;
		usDirversLicIdent2.state = "Alabama";

		//Have country only
		passportIdent = new CustomerIdentifier();
		passportIdent.id = OrmsConstants.IDEN_PASSPORT_NUM_ID; 
		passportIdent.identifierType = hf.getIdenTypeName(schema, passportIdent.id, false, true);
		passportIdent.identifierNum = StringUtil.EMPTY;
		passportIdent.country = pleaseSelectValue;

		passportIdent2 = new CustomerIdentifier();
		passportIdent2.id = passportIdent.id;
		passportIdent2.identifierType = passportIdent.identifierType;
		passportIdent2.identifierNum = invalidValue;
		passportIdent2.country = "Canada";

		//Have country and state
		otherIdent = new CustomerIdentifier();
		otherIdent.id = OrmsConstants.IDEN_OTHER_NUM_ID; 
		otherIdent.identifierType = hf.getIdenTypeName(schema, otherIdent.id, true, true);
		otherIdent.identifierNum = StringUtil.EMPTY;
		otherIdent.country = "Canada";
		otherIdent.state = pleaseSelectValue;

		otherIdent2 = new CustomerIdentifier();
		otherIdent2.id = otherIdent.id;
		otherIdent2.identifierType = otherIdent.identifierType;
		otherIdent2.identifierNum = invalidValue;
		otherIdent2.country = otherIdent.country;
		otherIdent2.state = "Alberta";

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		idenTypeReqMes = "Identification Type is required\\.";
		idenNumReqMes = "Number is required\\.";
		countryReqMes = "Country is required\\.";
		stateReqMes = "State/Province is required\\.";
		invalidNumMes1 = " must only contain numbers, embedded spaces or a dash\\.";
		invalidNumMes2 = " must contain exactly 9 digits\\.";
		invalidNumMes3 = " must only contain numbers and letters.";
	}

	public void setupIden(CustomerIdentifier iden){
		createAccountPg.setupIden(iden);
		createAccountPg.clickCreateAccount();
		createAccountPg.waitLoading();
	}
}
