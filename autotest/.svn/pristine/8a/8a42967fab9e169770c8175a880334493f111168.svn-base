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
 * @Description:  (P) Verify below check points for identifier "Social Security Number"
 * no country DDL and no state DDL
 * UI changed between identifier "NON-US DL Number" and "US Drivers License"
 * error message when leave the input field blank
 * error message when can't match exactly 9 numbers, and that the entry consists of only numbers
 * error message when the first three numbers all be zeros
 * error message when the fourth and fifth numbers all be zeros 
 * error message when the sixth to ninth numbers all be zeros
 * error message when identifier type has been added to another customer
 * successfully add identifier
 * 
 * @Preconditions:
 * d_web_hf_signupaccount
 * id=630, hfmo_00013@webhftest.com, AddIdenSSN1_FN, AddIdenSSN1_LN
 * id=640, hfmo_00014@webhftest.com, AddIdenSSN2_FN, AddIdenSSN2_LN, Social Security Number|125690372
 * 
 * @SPEC: Add Identifier - Social Security Number [TC:044610] 
 * @Task#: Auto-1714
 * 
 * @author Swang
 * @Date  Jun 8, 2013
 */
public class AddIdenSocialSecurityNum extends HFMOWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String topErrorMes, invalidValue1, invalidValue2, invalidValue3, invalidValue4, invalidValue5, invalidValue6, validValue, errorMes1, errorMes2, errorMes3, errorMes4,  mask, maskedNum;
	private CustomerIdentifier iden1, iden2;

	public void execute() {
		hf.deleteCustIden(schema, OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID, cus.fName, cus.lName);

		//Go to 1# account, add identifier page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoAddIdentificationPg();

		//no country DDL and no state DDL
		addIdenPg.selectIdentifierType(iden1.identifierType);
		addIdenPg.verifyStateDisplayed(iden1.identifierType, false);
		addIdenPg.verifyCountryDisplayed(iden1.identifierType, false);

		//UI changed between identifier "NON-US DL Number" and "US Drivers License"
		addIdenPg.selectIdentifierType(iden2.identifierType);
		addIdenPg.verifyIdenTypeUI(iden2.identifierType, true, true, false, true);
		addIdenPg.verifyIdenTypeUI(iden1.identifierType, false, false);
		addIdenPg.selectIdentifierType(iden1.identifierType);
		addIdenPg.verifyIdenTypeUI(iden2.identifierType, false, false, false, false);
		addIdenPg.verifyIdenTypeUI(iden1.identifierType, true, true);

		//error message when leave the input field blank
		iden1.identifierNum = StringUtil.EMPTY;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(topErrorMes, true);
		addIdenPg.verifyErrorMsgExist(errorMes1, true);

		//error message when can't match exactly 9 numbers, and that the entry consists of only numbers
		iden1.identifierNum = invalidValue1;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes2, true);

		iden1.identifierNum = invalidValue2;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes2, true);

		iden1.identifierNum = invalidValue3;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes2, true);

		//error message when the first three numbers all be zeros
		iden1.identifierNum = invalidValue4;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when the fourth and fifth numbers all be zeros 
		iden1.identifierNum = invalidValue5;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when the sixth to ninth numbers all be zeros
		iden1.identifierNum = invalidValue6;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when identifier type has been added to another customer
		iden1.identifierNum = cusNew.identifier.identifierNum;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		addIdenPg.verifyErrorMsgExist(errorMes4, true);

		//successfully add identifier
		iden1.identifierNum = validValue;
		hf.addIdentifierFromAddIdentificationPg(iden1);
		hf.gotoUpdateProfilePg();
		updateAccountPg.verifyIdentifierInfo(iden1, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		//First customer parameters
		cus.email = "hfmo_00013@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "AddIdenSSN1_FN";
		cus.lName = "AddIdenSSN1_LN";
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		iden1 = new CustomerIdentifier();
		iden1.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID;
		iden1.identifierType = hf.getIdenTypeName(schema, iden1.id, false, false);
		iden1.identifierNum = cus.identifier.identifierNum;

		iden2 = new CustomerIdentifier();
		iden2.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID;
		iden2.identifierType = hf.getIdenTypeName(schema, iden2.id, true, false);
		iden2.identifierNum = cus.identifier.identifierNum;
		iden2.state = "Alabama";

		//Second customer parameters
		cusNew.email = "hfmo_00014@webhftest.com";
		cusNew.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cusNew.fName = "AddIdenSSN2_FN";
		cusNew.lName = "AddIdenSSN2_LN";
		cusNew.identifier.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, cusNew.identifier.id, false, false);
		cusNew.identifier.identifierNum = "125690372";

		invalidValue1 = "12345678";
		invalidValue2 = "1234567890";
		invalidValue3 = "123+45678";
		invalidValue4 = "000123456";
		invalidValue5 = "123004567";
		invalidValue6 = "123450000";
		validValue = new DecimalFormat("000000000").format(new Random().nextInt(999999999));

		int hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		int showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(validValue, mask, hideNum, showNum);

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = iden1.identifierType+" is required\\.";
		errorMes2 = iden1.identifierType+" must contain exactly 9 digits\\.";
		errorMes3 = iden1.identifierType+" is invalid\\.";
		errorMes4 = iden1.identifierType+" with the same number is used by another record\\. Please contact the call center for more information\\.";
	}
}
