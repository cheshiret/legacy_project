package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (p) 
 * 1. Verify UI changes (number text field, country DDL and state DDL) after selecting different identifier
 * 2. Verify top error message and identifier related error message
 * 3. Verify identifier info after successfully add edentifier
 * 
 * @Preconditions:
 * d_web_hf_signupaccount, id=210, hfsk_00002@webhftest.com, 2000-01-03
 *  
 * @SPEC: Add Identifier - Other (STATE_ REQ_IND=1, COUNTRY_REQ_IND=1) [TC:044611] 
 * @Task#: Auto-1632
 * 
 * @author Swang
 * @Date  May 2, 2013
 */
public class AddOtherNumIden extends HFSKWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String identifierType, conmmonSchema, topErrorMes, idenNumErrorMes, idenCountryErrorMes, idenStateErrorMes,
	invaliedNum1, invaliedNum2, invaliedNum3, invaliedNum4, errorMesWithInvalidIdenNum1, errorMesWithInvalidIdenNum2, errorMesWithInvalidIdenNum3, maskedNum;
	private List<String> idenState1, idenState2;

	public void execute() {
		//Delete used identifier
		hf.deleteCustIden(schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.email);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, cus.email);

		//Go to add identification page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoAddIdentificationPg();

		//Verify country DDL values and no state DDL
		addIdenPg.selectIdentifierType(cus.identifier.identifierType);
		addIdenPg.verifyCountries(cus.identifier.identifierType, cus.identifier.countries);
		addIdenPg.verifyStateDisplayed(cus.identifier.identifierType, false);

		//Verify state DDL values after select country "Canada" and the selected country
		addIdenPg.syncProvinceSelectingCountry(cus.identifier.countries.get(1), cus.identifier.identifierType, false);
		addIdenPg.verifyCountry(cus.identifier.identifierType, cus.identifier.countries.get(1));
		addIdenPg.verifyStates(cus.identifier.identifierType, idenState1);

		//Verify no state DDL values after select country without states
		addIdenPg.syncProvinceSelectingCountry(cus.identifier.countries.get(3), cus.identifier.identifierType, true);
		addIdenPg.verifyStateDisplayed(cus.identifier.identifierType, false);

		//Verify state DDL values after select country "United States"
		addIdenPg.syncProvinceSelectingCountry(cus.identifier.countries.get(2), cus.identifier.identifierType, false);
		addIdenPg.verifyStates(cus.identifier.identifierType, idenState2);

		//Verify UI changed between identifier Passport # and Other #
		addIdenPg.selectIdentifierType(identifierType);
		addIdenPg.verifyIdenTypeUI(identifierType, true, true, true);
		addIdenPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false);
		addIdenPg.selectIdentifierType(cus.identifier.identifierType);
		addIdenPg.verifyIdenTypeUI(identifierType, false, false, false);
		addIdenPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, true, true);

		//Verify error message when identifier number is blank and country is initial
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = cus.identifier.countries.get(0);
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(topErrorMes, true);
		addIdenPg.verifyErrorMsgExist(idenNumErrorMes, true);
		addIdenPg.verifyErrorMsgExist(idenCountryErrorMes, true);

		//Verify error message when province is initial
		cus.identifier.identifierNum = "1R9Y4x4122";
		cus.identifier.country = cus.identifier.countries.get(1);
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(idenStateErrorMes, true);

		//Verify error message when identifier number is invalid
		cus.identifier.country = cus.identifier.countries.get(1);
		cus.identifier.state = "Alberta";
		cus.identifier.identifierNum = invaliedNum1;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum1, true);

		//Verify error message when identifier number is invalid
		cus.identifier.identifierNum = invaliedNum2;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum1, true);

		//Verify error message when identifier number is invalid
		cus.identifier.identifierNum = invaliedNum3;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum2, true);

		//Verify error message when identifier number is invalid
		cus.identifier.identifierNum = invaliedNum4;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum3, true);

		//Successfully add identifier
		cus.identifier.identifierNum = "1R9Y4x4122";
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);

		//Verify details info in update account page
		hf.gotoUpdateProfilePg();
		updateAccountPg.verifyIdentifierInfo(cus.identifier, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);

		cus.email = "hfsk_00002@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.dateOfBirth = "2000-01-03";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_OTHER_NUM_ID, true, true);
		cus.identifier.identifierNum = "1R9Y4x4122";
		cus.identifier.stateCode = "AB";
		identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);

		cus.identifier.countries.add("Please select");
		cus.identifier.countries.add("Canada");
		cus.identifier.countries.add("United States");
		cus.identifier.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID));

		idenState1 = new ArrayList<String>();
		idenState1.add("Please select");
		idenState1.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.identifier.countries.get(1)));

		idenState2 = new ArrayList<String>();
		idenState2.add("Please select");
		idenState2.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.identifier.countries.get(2)));

		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);

		//OTHER must contain at least 5 numbers and letters combined, and must only contain numbers, letters, embedded spaces or a dash
		invaliedNum1 = "123";
		invaliedNum2 = "a-bc";
		invaliedNum3 = "1123abc%9";
		invaliedNum4 = "1234567890000";

		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		idenNumErrorMes = cus.identifier.identifierType+" is required.";
		idenCountryErrorMes = "Country is required.";
		idenStateErrorMes = "Province is required.";
		errorMesWithInvalidIdenNum1 = cus.identifier.identifierType+" must contains at least 5 characters, excluding spaces and dashes.";
		errorMesWithInvalidIdenNum2 = cus.identifier.identifierType+" must only contain numbers, embedded spaces or a dash.";
		errorMesWithInvalidIdenNum3 = cus.identifier.identifierType+" is invalid.";
	}
}
