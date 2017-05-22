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
 * @Description: (P) Verify below check points for adding "US Drivers License" identifier type which has state_req_ind=1
 * no country, state drop down list default and all options
 * error messages when leave the input field blank and state DDL as default 
 * error message when can't match at least 5 number and letters combined , the entry consists of only numbers and letters
 * error message when can't match only contains numbers, it shall be less than or equal to the define Maxmum Value (max_value in c_identifier_type table)
 * verify successfully message when enter a valid number (not exists in the system yet)
 * Verify identifier info after successfully update one 
 * 
 * @Preconditions:
 * d_web_hf_signupaccount
 * id=530, hfmo_00005@webhftest.com, UpdateIdenUDL1_FN, UpdateIdenUDL1_LN, US Drivers License|1R9Y4x4137| |Alabama| 
 * id=540, hfmo_00006@webhftest.com, UpdateIdenUDL2_FN, UpdateIdenUDL2_LN, US Drivers License|1R9Y4x4138| |Alabama|
 * 
 * @SPEC: Update Identifier - US Drivers License (STATE_ REQ_IND=1) [TC:046097] 
 * @Task#: AUTO-1637
 * 
 * @author Swang
 * @Date  Jun 6, 2013
 */
public class UpdateIdenWithStateOnly extends HFMOWebTestCase {
	private HFUpdateIdentificationPage updateIdenPg = HFUpdateIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String invalidValue1, invalidValue2, invalidValue3, invalidValue4, validValue, topErrorMes, errorMes1, errorMes2, errorMes3, errorMes4, errorMes5, successfulMes, mask, maskedNum;
	private int hideNum, showNum;
	private CustomerIdentifier iden;

	public void execute() {
		//Go to 1# account update identifier page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoUpdateIdenPg(iden.id);

		//no country, state drop down list default and all options
		updateIdenPg.verifyCountryExists(false);
		updateIdenPg.verifyState(iden.states.get(0));
		updateIdenPg.verifyStates(iden.states);

		//error messages when leave the input field blank and state DDL as default 
		iden.identifierNum = StringUtil.EMPTY;
		iden.state = iden.states.get(0);
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyState(iden.states.get(0));
		updateIdenPg.verifyErrorMsgExist(topErrorMes, true);
		updateIdenPg.verifyErrorMsgExist(errorMes1, true);
		updateIdenPg.verifyErrorMsgExist(errorMes2, true);

		//error message when can't match at least 5 number and letters combined , the entry consists of only numbers and letters
		iden.state = iden.states.get(1);
		iden.identifierNum = invalidValue1;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		iden.identifierNum = invalidValue2;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		iden.identifierNum = invalidValue3;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when can't match only contains numbers, it shall be less than or equal to the define Maxmum Value (max_value in c_identifier_type table)
		iden.identifierNum = invalidValue4;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes4, true);

		//error message when enter an existing identifier
		iden.identifierNum = cusNew.identifier.identifierNum;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes5, true);

		//verify successfully message when enter a valid number (not exists in the system yet)
		iden.identifierNum = validValue;
		iden.state = iden.states.get(1);
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateAccountPg.verifyMsgExist(successfulMes, true);

		//Verify identifier info after successfully update one
		updateAccountPg.verifyIdentifierInfo(iden, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		//First customer parameters
		cus.email = "hfmo_00005@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "UpdateIdenUDL1_FN";
		cus.lName = "UpdateIdenUDL1_LN";
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		iden = new CustomerIdentifier();
		iden.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, true, false);
		iden.identifierNum = "1R9Y4x4137";
		iden.state = "Alabama";
		iden.states.add("Please select");
		iden.states.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID, StringUtil.EMPTY));

		//Second customer parameters
		cusNew.email = "hfmo_00006@webhftest.com";
		cusNew.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cusNew.fName = "UpdateIdenUDL2_FN";
		cusNew.lName = "UpdateIdenUDL2_LN";
		cusNew.custNum = hf.getCustomerNumByCustName(cusNew.lName, cusNew.fName, schema);
		cusNew.identifier.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, cusNew.identifier.id, true, false);
		cusNew.identifier.identifierNum = "1R9Y4x4138";
		cusNew.identifier.state = "Alabama";

		invalidValue1 = "abc";
		invalidValue2 = "1235";
		invalidValue3 = "ab124#*1";
		invalidValue4 = "01234567890123";
		validValue = "A"+new DecimalFormat("0000000").format(new Random().nextInt(9999999))+"BC";

		hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(validValue, mask, hideNum, showNum);

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = iden.identifierType+" is required\\.";
		errorMes2 = "State/Province is required\\.";
		errorMes3 = iden.identifierType+" must only contain numbers and letters\\.";
		errorMes4 = iden.identifierType+" is invalid\\.";
		errorMes5 = iden.identifierType+" with the same number is used by another record\\. Please contact the call center for more information\\.";
		successfulMes = iden.identifierType+" information is updated successfully";
	}
}
