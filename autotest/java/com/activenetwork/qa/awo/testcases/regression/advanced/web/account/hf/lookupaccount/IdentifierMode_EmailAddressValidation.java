package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify email address text field on Customer Identity (Your Account Found) page
 * @Preconditions: 
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * Customer exist. d_web_hf_signupaccount, id=870
 * @SPEC: Confirm Identity page - Email address input box - validation including max. 100 limit [TC:067991]
 * @Task#: Auto-1831
 * 
 * @author Lesley Wang
 * @Date  Jul 23, 2013
 */
public class IdentifierMode_EmailAddressValidation extends HFMOWebTestCase {
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage.getInstance();
	private String topMsg, emptyEmailMsg, invalidEmailMsg, emailWith50, emailWith51;
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}		
		
		// Empty customer email in LM 
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		custDetailsPg.editCustEmailAddress(cus.email);
		lm.logOutLicenseManager();
		
		// Lookup account to Confirm Identity page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		acctFoundPg.verifyEmailAddress(cus.email);
		
		// Submit with blank in email input box and verify error message
		hf.lookupAccountFromPurchaseTab(cus, acctFoundPg, false);
		acctFoundPg.verifyErrorMsgs(topMsg, emptyEmailMsg);
		
		// Go back to lookup account again to Confirm Identity page, verify no error message
		hf.gotoAccountLookupPgFromYourAccountFoundPg();
		hf.lookupAccountFromAccountLookupPage(cus);
		acctFoundPg.verifyErrorMsgs(StringUtil.EMPTY, StringUtil.EMPTY);
		
		// Submit with wrong email address format, verify error message
		cus.email = "1234";
		hf.lookupAccountFromPurchaseTab(cus, acctFoundPg, false);
		acctFoundPg.verifyErrorMsgs(topMsg, invalidEmailMsg);
		
		cus.email = "abc@gh";
		hf.lookupAccountFromPurchaseTab(cus, acctFoundPg, false);
		acctFoundPg.verifyErrorMsgs(topMsg, invalidEmailMsg);
		
		cus.email = "$#@GH.COM";
		hf.lookupAccountFromPurchaseTab(cus, acctFoundPg, false);
		acctFoundPg.verifyErrorMsgs(topMsg, invalidEmailMsg);
		
		// Click My Account to Lookup Account page
		hf.lookupAccount(cus);
		acctFoundPg.verifyErrorMsgs(StringUtil.EMPTY, StringUtil.EMPTY);
		
		// Input a string more than 100 characters into Email Address text field and observe the input value
		acctFoundPg.setCustEmailAddrFieldValue(emailWith51);
		acctFoundPg.verifyEmailAddress(emailWith50);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "IdentMode03_FN"; // d_web_hf_signupaccount, id=870
		cus.lName = "IdentMode03_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false).replace("Number", "#");
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.email = StringUtil.EMPTY;
		
		// Login info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";
		
		// Error Messages
		topMsg = "We need you to correct or provide more information. Please see each marked section.";
		emptyEmailMsg = "Email Address is required.";
		invalidEmailMsg = "The email address you provided is not valid.";
		emailWith50 = "email1234512@4512345123451234512345123451234512.co";
		emailWith51 = emailWith50 + "m";
	}
}
