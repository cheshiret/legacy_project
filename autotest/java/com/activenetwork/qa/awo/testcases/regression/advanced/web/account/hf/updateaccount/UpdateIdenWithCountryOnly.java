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
 * @Description: (P) Verify below check points for adding "Green Card" (Passport, NON-US DL Number, VISA) identifier type which has country_req_ind=1
 * No state, country drop down list default and all options
 * error messages when leave the input field blank and country DDL as default
 * error message when can't match at least 5 number and letters combined , the entry consists of only the following characters: numbers, letters, embedded space, dash
 * error message when can't match only contains numbers, it shall be less than or equal to the define Maximum Value (max_value in c_identifier_type table)
 * error message when enter an existing identifier
 * verify successfully message when enter a valid number (not exists in the system yet)
 * Verify identifier info after successfully update one
 * 
 * @Preconditions: 
 * d_web_hf_signupaccount 
 * id=510, hfmo_00003@webhftest.com, UpdateIdenCO1_FN, UpdateIdenCO1_LN, Green Card|1R9Y4x4135|Canada| |
 * id=520, hfmo_00004@webhftest.com, UpdateIdenCO2_FN, UpdateIdenCO2_LN, Green Card|1R9Y4x4136|Canada| |
 * 
 * @SPEC:
 * TC046094: Update Identifier - Passport (COUNTRY_ REQ_IND=1);
 * TC046099: Update Identifier - Green Card (COUNTRY_ REQ_IND=1)
 * TC046100: Update Identifier - Visa (COUNTRY_ REQ_IND=1)
 * TC046098: Update Identifier - NON-US DL Number (COUNTRY_ REQ_IND=1)

 * @Task#: AUTO-1637, AUTO-1638
 * 
 * @author Swang
 * @Date  Jun 4, 2013
 */
public class UpdateIdenWithCountryOnly extends HFMOWebTestCase {
	private HFUpdateIdentificationPage updateIdenPg = HFUpdateIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String invalidValue1, invalidValue2, invalidValue3, invalidValue4, validValue, topErrorMes, errorMes1, errorMes2, errorMes3, errorMes4, errorMes5, errorMes6, message5, mask, maskedNum;
	private int hideNum, showNum;
	private CustomerIdentifier iden;

	public void execute() {
		//Go to 1# account update identifier page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoUpdateIdenPg(iden.id);

		//no state, country drop down list default and all options
		updateIdenPg.verifyStateExists(false);
		updateIdenPg.verifyCountry(iden.countries.get(0));
		updateIdenPg.verifyCountries(iden.countries);

		//error messages when leave the input field blank and country DDL as default 
		iden.identifierNum = StringUtil.EMPTY;
		iden.country = iden.countries.get(0);
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyCountry(iden.country);
		updateIdenPg.verifyErrorMsgExist(topErrorMes, true);
		updateIdenPg.verifyErrorMsgExist(errorMes1, true);
		updateIdenPg.verifyErrorMsgExist(errorMes2, true);

		//error message when can't match at least 5 number and letters combined , the entry consists of only the following characters: numbers, letters, embedded space, dash
		iden.country = iden.countries.get(1);
		iden.identifierNum = invalidValue1;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		iden.identifierNum = invalidValue2;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		iden.identifierNum = invalidValue3;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes4, true);

		//error message when can't match only contains numbers, it shall be less than or equal to the define Maximum Value (max_value in c_identifier_type table)
		iden.identifierNum = invalidValue4;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes5, true);

		//error message when enter an existing identifier
		iden.identifierNum = cusNew.identifier.identifierNum;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes6, true);

		//verify successfully message when enter a valid number (not exists in the system yet)
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, false, true);
		iden.identifierNum = validValue;
		iden.country = iden.countries.get(1);
		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum.replace("-", "").replace(" ", ""), mask, hideNum, showNum);
		hf.updateIdenFromUpdateIdentificationPg(cus.identifier);
		updateAccountPg.verifyMsgExist(message5, true);

		//Verify identifier info after successfully update one
		updateAccountPg.verifyIdentifierInfo(iden, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		//First customer parameters
		cus.email = "hfmo_00003@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "UpdateIdenCO1_FN";
		cus.lName = "UpdateIdenCO1_LN";
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		iden = new CustomerIdentifier();
		iden.id = OrmsConstants.IDEN_GREENCARD_NUM_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, false, true);
		iden.identifierNum = "1R9Y4x4135";
		iden.country = "Canada"; 
		iden.countries.add("Please select");
		iden.countries.add("Canada");
		iden.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, OrmsConstants.IDEN_GREENCARD_NUM_ID));

		//Second customer parameters
		cusNew.email = "hfmo_00004@webhftest.com";
		cusNew.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cusNew.fName = "UpdateIdenCO2_FN";
		cusNew.lName = "UpdateIdenCO2_LN";
		cusNew.identifier.id = OrmsConstants.IDEN_GREENCARD_NUM_ID;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, cusNew.identifier.id, false, true);
		cusNew.identifier.identifierNum = "1R9Y4x4136";
		cusNew.identifier.country = "Canada";

		hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");

		invalidValue1 = "123";
		invalidValue2 = "a-bc";
		invalidValue3 = "1123abc%9";
		invalidValue4 = "003456789000";
		validValue = "A"+new DecimalFormat("0000000").format(new Random().nextInt(9999999))+"- "+new DecimalFormat("0").format(new Random().nextInt(9));

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = iden.identifierType+" is required\\.";
		errorMes2 = "Country is required\\.";
		errorMes3 = iden.identifierType+" must contains at least 5 characters, excluding spaces and dashes\\.";
		errorMes4 = "Green Card must only contain numbers, embedded spaces or a dash\\.";
		errorMes5 = iden.identifierType+" is invalid\\.";
		errorMes6 = iden.identifierType+" with the same number is used by another record\\. Please contact the call center for more information\\.";
		message5 = iden.identifierType+" information is updated successfully";
	}
}
