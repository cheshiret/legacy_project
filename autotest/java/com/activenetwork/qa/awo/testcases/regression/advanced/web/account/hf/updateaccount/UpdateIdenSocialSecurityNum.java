package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify below check points for adding "Social Security Number" identifier type
 * No state and country drop down list
 * error message when leave the input field blank
 * error message when can't match exactly 9 numbers, and that the entry consists of only numbers
 * error message when the first three numbers all be zeros
 * error message when the fourth and fifth numbers all be zeros 
 * error message when the sixth to ninth numbers all be zeros
 * error message when enter an existing Social Security Number information
 * Verify successfully message when enter a valid Social Security Number information (not exists in the system yet)
 * Verify identifier info after successfully update one
 * 
 * @Preconditions: 
 * d_web_hf_signupaccount
 * id=490, hfmo_00001@webhftest.com, UpdateIdenSSN1_FN, UpdateIdenSSN1_LN, Social Security Number|125690370| | | 
 * id=500, hfmo_00002@webhftest.com, UpdateIdenSSN2_FN, UpdateIdenSSN2_LN, Social Security Number|125690371| | | 
 * 
 * @SPEC: Update Identifier - Social Security Number [TC:046095] 
 * @Task#: AUTO-1637
 * 
 * @author Swang
 * @Date  Jun 5, 2013
 */
public class UpdateIdenSocialSecurityNum extends HFMOWebTestCase {
	private HFUpdateIdentificationPage updateIdenPg = HFUpdateIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String invalidValue1, invalidValue2, invalidValue3, invalidValue4, invalidValue5, invalidValue6, validValue, 
	topErrorMes, errorMes1, errorMes2, errorMes3, errorMes4, message5, mask, maskedNum;
	private int hideNum, showNum;
	private CustomerIdentifier iden;

	public void execute() {
		//Go to 1# account update identifier page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoUpdateIdenPg(iden.id);

		//No state and country drop down list
		updateIdenPg.verifyCountryExists(false);
		updateIdenPg.verifyStateExists(false);

		//error message when leave the input field blank
		iden.identifierNum = StringUtil.EMPTY;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(topErrorMes, true);
		updateIdenPg.verifyErrorMsgExist(errorMes1, true);

		//error message when can't match exactly 9 numbers, and that the entry consists of only numbers
		iden.identifierNum = invalidValue1;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes2, true);

		iden.identifierNum = invalidValue2;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes2, true);

		iden.identifierNum = invalidValue3;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes2, true);

		//error message when the first three numbers all be zeros
		iden.identifierNum = invalidValue4;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when the fourth and fifth numbers all be zeros 
		iden.identifierNum = invalidValue5;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when the sixth to ninth numbers all be zeros
		iden.identifierNum = invalidValue6;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when enter an existing Social Security Number information
		iden.identifierNum = cusNew.identifier.identifierNum;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes4, true);

		//Verify successfully message when enter a valid Social Security Number information (not exists in the system yet)
		iden.identifierNum = validValue;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateAccountPg.verifyMsgExist(message5, true);

		//Verify identifier info after successfully update one
		updateAccountPg.verifyIdentifierInfo(iden, maskedNum);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//First customer parameters
		cus.email = "hfmo_00001@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "UpdateIdenSSN1_FN";
		cus.lName = "UpdateIdenSSN1_LN";
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		iden = new CustomerIdentifier();
		iden.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, false, false);
		iden.identifierNum = "125690370";

		//Second customer parameters
		cusNew.email = "hfmo_00002@webhftest.com";
		cusNew.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cusNew.fName = "UpdateIdenSSN2_FN";
		cusNew.lName = "UpdateIdenSSN2_LN";
		cusNew.identifier.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, cusNew.identifier.id, false, false);
		cusNew.identifier.identifierNum = "125690371";

		invalidValue1 = "12345678";
		invalidValue2 = "1234567890";
		invalidValue3 = "123+45678";
		invalidValue4 = "000123456";
		invalidValue5 = "123004567";
		invalidValue6 = "123450000";
		validValue = new DecimalFormat("000000000").format(new Random().nextInt(999999999));

		hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(validValue, mask, hideNum, showNum);

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = iden.identifierType+" is required\\.";
		errorMes2 = iden.identifierType+" must contain exactly 9 digits\\.";
		errorMes3 = iden.identifierType+" is invalid\\.";
		errorMes4 = iden.identifierType+" with the same number is used by another record\\. Please contact the call center for more information\\.";
		message5 = iden.identifierType+" information is updated successfully";
	}
}
