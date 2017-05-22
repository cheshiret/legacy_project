package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (P) Verify below check points for identifier which has COUNTRY_
 *               REQ_IND=1 Verify country DDL values and no state DDL Verify UI
 *               changed between identifier "NON-US DL Number" and
 *               "Conservation Number" Verify error message when identifier
 *               number is blank and country is initial Verify error message
 *               when identifier number is invalid error message when identifier
 *               number is bigger than the maximum length error message when
 *               identifier type has been added to another customer successfully
 *               add identifier
 * 
 * @Preconditions: d_web_hf_signupaccount id=570, hfmo_00009@webhftest.com,
 *                 AddIdenCO1_FN, AddIdenCO1_LN id=580,
 *                 hfmo_00010@webhftest.com, AddIdenCO2_FN, AddIdenCO2_LN,
 *                 NON-US DL Number|1R9Y4x4142|Canada
 * 
 * @SPEC: Add Identifier - NON-US DL Number (COUNTRY_ REQ_IND=1) [TC:044613] Add
 *        Identifier - Green Card (COUNTRY_ REQ_IND=1) [TC:044614] Add
 *        Identifier - Visa (COUNTRY_ REQ_IND=1) [TC:044615]
 * @Task#: Auto-1714
 * 
 * @author Swang
 * @Date Jun 8, 2013
 */
public class AddIdenWithCountryOnly extends HFMOWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage
			.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage
			.getInstance();
	private String topErrorMes, invalidValue1, invalidValue2, invalidValue3,
			invalidValue4, validValue, errorMes1, errorMes2, errorMes3,
			errorMes4, errorMes5, mask, maskedNum;
	private CustomerIdentifier iden1, iden2;

	public void execute() {
		hf.deleteCustIden(schema, OrmsConstants.IDEN_NONUSDL_NUM_ID,
				cus.fName, cus.lName);

		// Go to 1# account, add identifier page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoAddIdentificationPg();

		// Verify country DDL values and no state DDL
		addIdenPg.selectIdentifierType(iden1.identifierType);
		addIdenPg.verifyStateDisplayed(iden1.identifierType, false);
		addIdenPg.verifyCountry(iden1.identifierType, iden1.countries.get(0));
		addIdenPg.verifyCountries(iden1.identifierType, iden1.countries);

		// Verify UI changed between identifier "NON-US DL Number" and
		// "Conservation Number"
		addIdenPg.selectIdentifierType(iden2.identifierType);
		addIdenPg.verifyIdenTypeUI(iden2.identifierType, true, true);
		addIdenPg.verifyIdenTypeUI(iden1.identifierType, false, false, false);
		addIdenPg.selectIdentifierType(iden1.identifierType);
		addIdenPg.verifyIdenTypeUI(iden2.identifierType, false, false);
		addIdenPg.verifyIdenTypeUI(iden1.identifierType, true, true, true);

		// Verify error message when identifier number is blank and country is
		// initial
		iden1.identifierNum = StringUtil.EMPTY;
		iden1.country = iden1.countries.get(0);
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(topErrorMes, true);
		addIdenPg.verifyErrorMsgExist(errorMes1, true);
		addIdenPg.verifyErrorMsgExist(errorMes2, true);

		// Verify error message when identifier number is invalid
		iden1.identifierNum = invalidValue1;
		iden1.country = iden1.countries.get(1);
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes3, true);

		iden1.identifierNum = invalidValue2;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes3, true);

		iden1.identifierNum = invalidValue3;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes3, true);

		// error message when identifier number is bigger than the maximum
		// length
		iden1.identifierNum = invalidValue4;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes4, true);

		// error message when identifier type has been added to another customer
		iden1.identifierNum = cusNew.identifier.identifierNum;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes5, true);

		// successfully add identifier
		iden1.identifierNum = validValue;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		hf.gotoUpdateProfilePg();
		updateAccountPg.verifyIdentifierInfo(iden1, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		// First customer parameters
		cus.email = "hfmo_00009@webhftest.com";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "AddIdenCO1_FN";
		cus.lName = "AddIdenCO1_LN";
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema,
				cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName,
				cus.fName, schema);

		iden1 = new CustomerIdentifier();
		iden1.id = OrmsConstants.IDEN_NONUSDL_NUM_ID;
		iden1.identifierType = hf
				.getIdenTypeName(schema, iden1.id, false, true);
		iden1.identifierNum = cus.identifier.identifierNum;
		iden1.countries.add("Please select");
		iden1.countries.add("Canada");
		iden1.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema,
				schema, iden1.id));
		iden1.country = iden1.countries.get(1);

		iden2 = new CustomerIdentifier();
		iden2.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID;
		iden2.identifierType = hf.getIdenTypeName(schema, iden2.id, false,
				false);
		iden2.identifierNum = cus.identifier.identifierNum;

		// Second customer parameters
		cusNew.email = "hfmo_00010@webhftest.com";
		cusNew.dateOfBirth = "01/01/"
				+ DateFunctions.getYearAfterCurrentYear(-16);
		cusNew.fName = "AddIdenCO2_FN";
		cusNew.lName = "AddIdenCO2_LN";
		cusNew.identifier.id = OrmsConstants.IDEN_NONUSDL_NUM_ID;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema,
				cusNew.identifier.id, false, true);
		cusNew.identifier.identifierNum = "1R9Y4x4142";
		cusNew.identifier.country = iden1.countries.get(1);

		invalidValue1 = "123";
		invalidValue2 = "a-bc";
		invalidValue3 = "1123abc%9";
		invalidValue4 = "9999999999990";
		validValue = new DecimalFormat("000000000").format(new Random()
				.nextInt(999999999));

		int hideNum = Integer.valueOf(TestProperty
				.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty
				.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil
				.encryptString(validValue, mask, hideNum, showNum);

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = iden1.identifierType + " is required\\.";
		errorMes2 = "Country is required\\.";
		errorMes3 = iden1.identifierType
				+ " must only contain numbers and letters\\.";
		errorMes4 = iden1.identifierType + " is invalid\\.";
		errorMes5 = iden1.identifierType
				+ " with the same number is used by another record\\. Please contact the call center for more information\\.";
	}
}