package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.createanaccount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify create profile UI change when the site is in identifier mode
 * 1. Account Information section including password and retype password fields are not shown
 * 2. Email address text field is shown under Customer Details section
 * 3. Identification section shown and default value is based on the info used by lookup
 * 4. Identification section Clear link
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * @SPEC: Create Profile UI change - Authenticated by identifier model [TC:068022]
 * @Task#: Auto-1832, Auto-1870
 * 
 * @author Lesley Wang, Sara Wang
 * @Date  Jul 25, 2013, Sep 12, 2013
 */
public class IdentifierMode_CreateAccountUI  extends HFMOWebTestCase {
	private HFCreateAccountPage createAcctPg = HFCreateAccountPage.getInstance();
	private String identSecTitle, identTypeDefValue;
	private List<String> identTypesFromDB;
	private CustomerIdentifier custNumIdent, socialSecNumIdent, usDirversLicIdent, passportIdent, otherIdent;
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}				
		
		// Lookup by customer number to sign in page and verify page UI
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoCreateAccountPgFromAccountLookupPg();
		this.verifyAcctInfoSection();
		this.verifyIdentifierSection(cus.identifier, cus.dateOfBirth);
		
		// Lookup by customer identifier to sign up page and verify identifier section
		this.lookupByIdentAndVerifyCreateAcctUI(socialSecNumIdent);
		this.lookupByIdentAndVerifyCreateAcctUI(usDirversLicIdent);
		this.lookupByIdentAndVerifyCreateAcctUI(passportIdent);
		this.lookupByIdentAndVerifyCreateAcctUI(otherIdent);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Identifiers info
		String tempStr = new DecimalFormat("000000000").format(new Random().nextInt(999999999));
		custNumIdent = new CustomerIdentifier();
		custNumIdent.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		custNumIdent.identifierType = hf.getIdenTypeName(schema, custNumIdent.id, false, false);
		custNumIdent.identifierNum = tempStr;
		
		socialSecNumIdent = new CustomerIdentifier();
		socialSecNumIdent.id = OrmsConstants.IDEN_SOCIALSECURITY_NUM_ID; 
		socialSecNumIdent.identifierType = hf.getIdenTypeName(schema, socialSecNumIdent.id, false, false);
		socialSecNumIdent.identifierNum = tempStr;
		
		usDirversLicIdent = new CustomerIdentifier();
		usDirversLicIdent.id = OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID; 
		usDirversLicIdent.identifierType = hf.getIdenTypeName(schema, usDirversLicIdent.id, true, false);
		usDirversLicIdent.identifierNum = tempStr;
		usDirversLicIdent.state = "Alabama";
		
		passportIdent = new CustomerIdentifier();
		passportIdent.id = OrmsConstants.IDEN_PASSPORT_NUM_ID; 
		passportIdent.identifierType = hf.getIdenTypeName(schema, passportIdent.id, false, true);
		passportIdent.identifierNum = tempStr;
		passportIdent.country = "Canada";
		
		otherIdent = new CustomerIdentifier();
		otherIdent.id = OrmsConstants.IDEN_OTHER_NUM_ID; 
		otherIdent.identifierType = hf.getIdenTypeName(schema, otherIdent.id, true, true);
		otherIdent.identifierNum = tempStr;
		otherIdent.country = "Canada";
		otherIdent.state = "Alberta";
		
		// Customer Info
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-20);
		cus.identifier = custNumIdent; 
		
		// Other Test Data
		identSecTitle = "Identification";
		identTypeDefValue = "Please select";
		identTypesFromDB = hf.getAllIdenTypesFromDB(schema);
		identTypesFromDB.set(0, identTypeDefValue);
	}
	
	private void verifyAcctInfoSection() {
		boolean result = true;
		if (createAcctPg.isPasswordExisting()) {
			result = false;
			logger.error("Password text field should not exist on Create Account page!");
		}
		if (createAcctPg.isRetryPasswordExisting()) {
			result = false;
			logger.error("Retype Password text field should not exist on Create Account page!");
		}
		if (!createAcctPg.isEmailAddressExistInCustDetailsSec()) {
			result = false;
			logger.error("Email Address text field should exist in Customer Details section on Create Account page!");
		}
		
		if (!result) {
			throw new ErrorOnPageException("Password/Retype Password/Email address text fields are wrong!");
		}
		logger.info("---Successfully verify Password/Retype Password/Email address text fields");
	}
	
	private void verifyIdentifierSection(CustomerIdentifier ident, String dob) {
		boolean result = true;
		result &= MiscFunctions.compareString("Identification section title", identSecTitle, createAcctPg.getIdentSectionTitle());
		result &= MiscFunctions.compareResult("Clear Link exist", true, createAcctPg.isIdentClearLinkExist());
		result &= MiscFunctions.compareString("Identification Type List Values", identTypesFromDB.toString(), createAcctPg.getIdentTypes().toString());
		if (ident.identifierType.equals(custNumIdent.identifierType)) {
			result &= MiscFunctions.compareString("Identification Type default value", identTypeDefValue, createAcctPg.getIdentType());
			result &= MiscFunctions.compareResult("Customer number exist", false, createAcctPg.isIdentNumFieldExist());
		} else {
			result &= MiscFunctions.compareString("Identification Type default value", ident.identifierType, createAcctPg.getIdentType());
			result &= MiscFunctions.compareString("Customer number exist", ident.identifierNum, createAcctPg.getIdentNum());
		}
		boolean isExist = StringUtil.notEmpty(ident.state);
		result &= MiscFunctions.compareResult("Customer state list", isExist, createAcctPg.isIdenStateListExist());
		if (isExist)
			result &= MiscFunctions.compareString("Customer state", ident.state, createAcctPg.getIdentState());
		isExist = StringUtil.notEmpty(ident.country);
		result &= MiscFunctions.compareResult("Customer country list", isExist, createAcctPg.isIdentCountryListExist());
		if (isExist)
			result &= MiscFunctions.compareString("Customer country", ident.country, createAcctPg.getIdentCountry());
		
		result &= MiscFunctions.compareString("Date of Birth", dob, createAcctPg.getDateOfBirth());
		
		if (!result) {
			throw new ErrorOnPageException("Identification section is wrong!");
		}
		logger.info("---Successfully verify Identification section when lookup by identifier type " + ident.identifierType);
	}
	
	private void lookupByIdentAndVerifyCreateAcctUI(CustomerIdentifier ident) {
		cus.identifier = ident;
		hf.lookupAccount(cus);
		hf.gotoCreateAccountPgFromAccountLookupPg();
		this.verifyIdentifierSection(cus.identifier, cus.dateOfBirth);
		createAcctPg.clickIdentClearLinkAndWait();
		cus.identifier = custNumIdent;
		this.verifyIdentifierSection(cus.identifier, cus.dateOfBirth);
	}
}
