package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (DEFECT-42940) Check below check points in update identification page for identifiers (Has country and number, has state and number, has number only)
 * 1. Page title, description and identifier title message
 * 2. Number, country, state and date of birth labels
 * 3. Save changes and cancel buttons
 * 4. Max input length for number and DOB

 * @Preconditions:
 * d_web_hf_signupdaaccount, id=370, hfsk_00016@webhftest.com, 2000-01-18
 * 
 * @SPEC:
 * Update identifier Page UI [TC:046087] 
 * Max. input length of 100 for free text input fields [TC:046091] 
 * @Task#: AUTO-1636
 * 
 * @author SWang
 * @Date  May 30, 2013
 */
public class UpdateIdenUIValidation extends HFSKWebTestCase {
	private HFUpdateIdentificationPage updateIdenPg = HFUpdateIdentificationPage.getInstance();
	private String pageTitle, idenTitle, numLabel, stateLabel, countryLabel, dateOfBirthLabel, OneHundredAndOneCharString, OneHundredharString;
	private CustomerIdentifier iden1, iden2, iden3;

	@Override
	public void execute() {
		//Precondition
		//* Add date of birth info to used customer
		hf.updateCustDOBInProfile(schema, cus.email, cus.dateOfBirth);
		hf.udpateCustDOB(schema, cus.email, cus.dateOfBirth);

		//* Delete all identifiers
		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);

		//* Add identifiers
		hf.signIn(url, cus.email, cus.password);
		hf.addIdentifier(iden1);
		hf.addIdentifier(iden2);
		hf.addIdentifier(iden3);
		hf.signOut();

		//Update date of birth to blank
		hf.updateCustDOBInProfile(schema, cus.email, "");
		hf.udpateCustDOB(schema, cus.email, "");

		//Go to update profile page
		hf.signIn(url, cus.email, cus.password);
		hf.gotoUpdateProfilePg();

		//Verify it should have date of birth text field because customer date of birth is blank now
		verifyUpdateIdentificationUI(iden1);
		verifyUpdateIdentificationUI(iden2);
		verifyUpdateIdentificationUI(iden3);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00016@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.dateOfBirth = "18-JAN-00";

		iden1 = new CustomerIdentifier();
		iden1.id = OrmsConstants.IDEN_SKDL_ID;
		iden1.identifierType = hf.getIdenTypeName(schema, iden1.id, false, false);
		iden1.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));

		iden2 = new CustomerIdentifier();
		iden2.id = OrmsConstants.IDEN_PASSPORT_NUM_ID;
		iden2.identifierType = hf.getIdenTypeName(schema, iden2.id, false, true);
		iden2.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		iden2.country = "Canada";

		iden3 = new CustomerIdentifier();
		iden3.id = OrmsConstants.IDEN_CANDL_ID;
		iden3.identifierType = hf.getIdenTypeName(schema, iden3.id, true, false);
		iden3.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		iden3.state = "Alberta";

		pageTitle = "Update Identification ?Keep this accurate and up-to-date in order to purchase the appropriate Licences\\.";
		idenTitle = " Information";
		numLabel = "Number";
		stateLabel = "Province";
		countryLabel = "Country";
		dateOfBirthLabel = "Date of Birth (YYYY-MM-DD)";

		OneHundredharString = "1 3 5 7 9 a b c d e A B C D E 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9 1 3 5 7 9  123456789";
		OneHundredAndOneCharString = OneHundredharString + "T";
	}

	private void verifyUpdateIdentificationUI(CustomerIdentifier iden) {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		boolean result = true;

		//Go to update identifier page
		hf.gotoUpdateIdenPgFromUpdateAccountPg(iden.id);

		//Check point 1: Page title and description
		result &= MiscFunctions.matchString("page title and description", updateIdenPg.getPageTitle(), pageTitle);

		//Check point 2: Iden title message
		result &= MiscFunctions.compareString("Iden title", iden.identifierType+idenTitle, updateIdenPg.getIdenTypeMes());

		//Check point 3, 4, 5 and 6: Number, country, state and date of birth labels
		result &= MiscFunctions.compareString("identification Number label", numLabel, updateIdenPg.getNumberLabel());
		if(StringUtil.notEmpty(iden.country)){
			result &= MiscFunctions.compareResult("identification Country label", countryLabel, updateIdenPg.getCountryLabel());
		}
		if(StringUtil.notEmpty(iden.state)){
			result &= MiscFunctions.compareResult("identification State label", stateLabel, updateIdenPg.getProvinceLabel());
		}
		result &= MiscFunctions.compareResult("date of birth label", dateOfBirthLabel, updateIdenPg.getDateOfBirthLabel());

		//Check point 7 and 8: Save changes and cancel buttons
		result &= MiscFunctions.compareResult("save changes button exists", true, updateIdenPg.isSaveChangesButtonExisting());
		result &= MiscFunctions.compareResult("cancel link exists", true, updateIdenPg.isCancelLinkExisting());

		//Check point 9: Max input length for number
		updateIdenPg.setNumber(OneHundredAndOneCharString);
		result &= MiscFunctions.compareResult("Max input length of 100 for number", OneHundredharString, updateIdenPg.getNumber());

		//Check point 10: Max input length for DOB
		updateIdenPg.setDOB(OneHundredAndOneCharString);
		result &= MiscFunctions.compareResult("Max input length of 100 for date of birth", OneHundredharString, updateIdenPg.getDOB());

		if (!result) {
			throw new ErrorOnPageException("Failed to verify update identification UI for iden type:"+iden.identifierType);
		}
		logger.info("Successfully verify update Identification UI for iden type:"+iden.identifierType);

		//Go back to update account page
		updateIdenPg.clickCancelLink();
		updateAccountPg.waitLoading();
	}
}
