package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify page UI, error message and result after added for identifier without state and country.
 * @Preconditions:
 * @LinkSetUp:
 *  d_web_hf_signupaccount:id=1510
 * @SPEC: Add Identifier - Tax ID [TC:044616] 
 * @Task#:AUTO-1768
 * 
 * @author SWang
 * @Date  Mar 18, 2014
 */
public class AddIdenWithoutCountryAndState extends HFSKWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String identifierType, topErrorMes, idenNumErrorMes, invaliedNum1, invaliedNum2, invaliedNum3, invaliedNum4, errorMesWithInvalidIdenNum1, errorMesWithInvalidIdenNum2, errorMesWithInvalidIdenNum3, maskedNum;

	public void execute() {
		//Delete used identifier
		hf.deleteCustIden(schema, OrmsConstants.IDEN_SKDL_ID, cus.email);
		hf.deleteCustIden(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, cus.email);

		//Go to add identification page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoAddIdentificationPg();

		//Verify country DDL values and no state DDL
		addIdenPg.selectIdentifierType(cus.identifier.identifierType);
		addIdenPg.verifyCountryDisplayed(cus.identifier.identifierType, false);
		addIdenPg.verifyStateDisplayed(cus.identifier.identifierType, false);

		//Verify UI changed between identifier Passport # and Other #
		addIdenPg.selectIdentifierType(identifierType);
		addIdenPg.verifyIdenTypeUI(identifierType, true, true, true);
		addIdenPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false);
		addIdenPg.selectIdentifierType(cus.identifier.identifierType);
		addIdenPg.verifyIdenTypeUI(identifierType, false, false, false);
		addIdenPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, false, false);

		//Verify error message when identifier number is blank and country is initial
		cus.identifier.identifierNum = StringUtil.EMPTY;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyErrorMsgExist(topErrorMes, true);
		addIdenPg.verifyErrorMsgExist(idenNumErrorMes, true);

		//Verify error message when identifier number is invalid
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
		cus.identifier.identifierNum = "1R9Y4x4206";
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);

		//Verify details info in update account page
		hf.gotoUpdateProfilePg();
		updateAccountPg.verifyIdentifierInfo(cus.identifier, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);

		cus.email = "hfsk_00071@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_SKDL_ID, false, false);
		cus.identifier.identifierNum = "1R9Y4x4206";
		identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);

		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		String mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(cus.identifier.identifierNum, mask, hideNum, showNum);

		invaliedNum1 = "123";
		invaliedNum2 = "a-bc";
		invaliedNum3 = "1123abc%9";
		invaliedNum4 = "1234567890000";

		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		idenNumErrorMes = IDENT_TYPE_SKDL+" is required.";
		errorMesWithInvalidIdenNum1 = IDENT_TYPE_SKDL+" must contains at least 5 characters, excluding spaces and dashes.";
		errorMesWithInvalidIdenNum2 = IDENT_TYPE_SKDL+" must only contain numbers, embedded spaces or a dash.";
		errorMesWithInvalidIdenNum3 = IDENT_TYPE_SKDL+" is invalid.";
	}
}
