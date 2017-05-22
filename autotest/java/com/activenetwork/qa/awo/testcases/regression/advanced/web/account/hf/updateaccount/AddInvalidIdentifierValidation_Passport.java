/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify error message when add an invalid passport identification
 * @Preconditions:
 * @SPEC:
 *  Add Identifier - Passport (COUNTRY_ REQ_IND=1) [TC:044609]
 * @Task#: Auto-1631
 * 
 * @author Lesley Wang
 * @Date  Apr 28, 2013
 */
public class AddInvalidIdentifierValidation_Passport extends HFSKWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage.getInstance();
	private String conmmonSchema, topErrorMes, identNumReqMes, idenCountryReqMes,
	invaliedNum1, invaliedNum2, invaliedNum3, errorMes_InvalidNum1, errorMes_InvalidNum2, errorMes_InvalidNum3, errorMes_InvalidNum4;
	
	@Override
	public void execute() {
		// Add a identifier for one account
		hf.deleteCustIden(schema, cusNew.identifier.identifierTypeID, cusNew.email);
		hf.signIn(url, cusNew.email, cusNew.password);
		hf.addIdentifier(cusNew.identifier);
		hf.signOut();
		
		// Login in with another account to test
		hf.signIn(url, cus.email, cus.password);
		hf.gotoAddIdentificationPg();

		// Verify default values
		addIdenPg.selectIdentifierType(cus.identifier.identifierType);
		addIdenPg.verifyCountries(cus.identifier.identifierType, cus.identifier.countries);
		addIdenPg.verifyStateDisplayed(cus.identifier.identifierType, false);

		// Leave identifier number as empty and country as default
		this.inputInvalidIdentAndVerifyErrorMsg(StringUtil.EMPTY, StringUtil.EMPTY, topErrorMes, identNumReqMes, idenCountryReqMes);
		
		// Input identifier number with length less than 5.
		this.inputInvalidIdentAndVerifyErrorMsg(invaliedNum1, cus.identifier.countries.get(1), topErrorMes, errorMes_InvalidNum1);
		
		// Input identifier number containing invalid characters
		this.inputInvalidIdentAndVerifyErrorMsg(invaliedNum2, cus.identifier.countries.get(1), topErrorMes, errorMes_InvalidNum2);
		
		// Input identifier number containing invalid characters
		this.inputInvalidIdentAndVerifyErrorMsg(invaliedNum3, cus.identifier.countries.get(1), topErrorMes, errorMes_InvalidNum3);
			
		// Input existing identifier number same as other account
		this.inputInvalidIdentAndVerifyErrorMsg(cusNew.identifier.identifierNum, cus.identifier.countries.get(1), topErrorMes, errorMes_InvalidNum4);
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);
		
		cus.email = "updatecustprofile@test.com"; // d_web_hf_signupaccount, id=110
		cus.password = "asdfasdf";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		cus.identifier.countries.add("Please select");
		cus.identifier.countries.add("Canada");
		cus.identifier.countries.add("United States");
		cus.identifier.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, OrmsConstants.IDEN_PASSPORT_NUM_ID));
		cus.identifier.state = StringUtil.EMPTY;
		
		cusNew.email = "inputinvaliddentinfo02@test.com";
		cusNew.password = cus.password;
		cusNew.identifier.identifierType = cus.identifier.identifierType;
		cusNew.identifier.identifierTypeID = IDEN_PASSPORT_NUM_ID;
		cusNew.identifier.country = cus.identifier.countries.get(1);
		cusNew.identifier.identifierNum = "PASSPORT";
				
		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		identNumReqMes = cus.identifier.identifierType+" is required.";
		idenCountryReqMes = "Country is required.";
		errorMes_InvalidNum1 = cus.identifier.identifierType+" must contains at least 5 characters, excluding spaces and dashes.";
		errorMes_InvalidNum2 = cus.identifier.identifierType+" must only contain numbers, embedded spaces or a dash.";
		errorMes_InvalidNum3 = cus.identifier.identifierType+" is invalid.";
		errorMes_InvalidNum4 = cus.identifier.identifierType + " with the same number is used by another account.*";
		invaliedNum1 = "123- ";
		invaliedNum2 = "1123abc%9";
		invaliedNum3 = "1000000000";
	}

	private void inputInvalidIdentAndVerifyErrorMsg(String num, String country, String... errMsg) {
		cus.identifier.identifierNum = num;
		cus.identifier.country = country;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		for (String err : errMsg) {
			addIdenPg.verifyErrorMsgExist(err, true);
		}
	}
}
