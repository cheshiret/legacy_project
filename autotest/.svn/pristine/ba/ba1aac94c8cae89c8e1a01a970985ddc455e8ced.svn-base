package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Verify Confirm Identity page (Your Account Found page) UI with email address in Identifier Mode.
 * 1. Customer name: <first name> <last name>
 * 2. Customer Number
 * 3. Customer Identifier: no identifier; identifier with country and state
 * 4. Customer address: with county
 * 5. Customer Home phone: 10 digits
 * 
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * @SPEC: Confirm Identity page - Found profile which has email address entered already [TC:067989]
 * @Task#: Auto-1831
 * 
 * @author Lesley Wang
 * @Date  Jul 23, 2013
 */
public class IdentifierMode_ConfirmIdentityPgUI extends HFMOWebTestCase {
	private HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage.getInstance();
	private String pgTitle, acctInfoLabel, contactInfoLabel, custNameLabel,
		custHomeAddrLabel, custHomeCityLabel, custHomePhoneLabel, custEmailAddrLabel;
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}
		
		hf.invokeURL(url);
		
		// lookup account by customer number and verify confirm identity page UI and account info
		hf.lookupAccount(cus);
		this.verifyConfirmIdentityPgUI();
		acctFoundPg.verifyAccountInformation(cus, false);
		acctFoundPg.verifyContactInformation(cus);
		
		// lookup account by identifier and verify account information with identifier info
		this.resetCustIdentifier();
		hf.gotoAccountLookupPgFromYourAccountFoundPg();
		hf.lookupAccountFromAccountLookupPage(cus);
		acctFoundPg.verifyAccountInformation(cus, true);
		acctFoundPg.verifyContactInformation(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// customer info
		cus.fName = "IdentMode02_FN"; // d_web_hf_signupaccount, id=860
		cus.lName = "IdentMode02_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.custNum = cus.identifier.identifierNum;
		cus.physicalAddr.address = "2480 Meadowvale";
		cus.physicalAddr.city = "Saint Louis";
		cus.physicalAddr.state = "Missouri";
		cus.physicalAddr.county = "St. Louis city";
		cus.physicalAddr.country = "United States";
		cus.hPhone = "7896540123";
		cus.email = "hfmo_00020@webhftest.com";
		
		//Your account found page testing parameters
		pgTitle = "Customer Record Found\\s*A record (is|was) found with the identification provided.*".replaceAll("\\s", "");
		acctInfoLabel = "Account Information";
		contactInfoLabel = "Contact Information";
		custNameLabel = "Name";
		custHomeAddrLabel = "Home Address";
		custHomeCityLabel = "Home City";
		custHomePhoneLabel = "Home Phone#";
		custEmailAddrLabel = "Email Address";
	}

	/** reset customer identifier info to lookup by identifier */
	private void resetCustIdentifier() {
		cus.identifier.id = OrmsConstants.IDEN_OTHER_NUM_ID; // state and country required
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, true, true);
		cus.identifier.identifierNum = "1R9Y4X4153"; // d_web_hf_signupaccount, id=860
		cus.identifier.country = "Canada";
		cus.identifier.state = "Manitoba";
		cus.identifier.stateCode = "MB";
	}
	
	/** Verify Confirm Identify Page UI in Identifier Mode */
	private void verifyConfirmIdentityPgUI() {
		boolean result = true;
		result &= MiscFunctions.matchString("Page Title", acctFoundPg.getPageTitle(), pgTitle);
		
		// Account Information Labels
		result &= MiscFunctions.compareString("Account Information Section Label", acctInfoLabel, acctFoundPg.getAcctInfoSecLabel());
		result &= MiscFunctions.compareResult("Update Information link exist", true, acctFoundPg.isUpdateInfoLinkExist());
		result &= MiscFunctions.compareString("Customer Name Label", custNameLabel, acctFoundPg.getCustNameLabel());
		result &= MiscFunctions.compareString("Customer Number Label", cus.identifier.identifierType.replace("Number", "#"), acctFoundPg.getCustNumLabel());
		
		// Contact Information Labels
		result &= MiscFunctions.compareString("Contact Information Section Label", contactInfoLabel, acctFoundPg.getContactInfoSecLabel());
		result &= MiscFunctions.compareResult("Update Address link exist", true, acctFoundPg.isUpdateAddrLinkExist());
		result &= MiscFunctions.compareString("Customer Home Address Label", custHomeAddrLabel, acctFoundPg.getCustHomeAddrLabel());
		result &= MiscFunctions.compareString("Customer Home City Label", custHomeCityLabel, acctFoundPg.getCustHomeCityLabel());
		result &= MiscFunctions.compareString("Customer Home Phone Label", custHomePhoneLabel, acctFoundPg.getCustHomePhoneLabel());
		result &= MiscFunctions.compareString("Customer Email Address Label", custEmailAddrLabel, acctFoundPg.getCustEmailAddrLabel());
		result &= MiscFunctions.compareResult("Email Address text box exist", false, acctFoundPg.isEmailAddrTextFieldExist());
		
		// Submit and Cancel Buttons
		result &= MiscFunctions.compareResult("Confirm and Proceed button exist", true, acctFoundPg.isConfirmAndProceedExist());
		result &= MiscFunctions.compareResult("This is not my record link exist", true, acctFoundPg.isNotMyRecordLinkExist());
		
		// The following objects should not exist in Identifier Mode
		result &= MiscFunctions.compareResult("Email Address link exist", false, acctFoundPg.isEmailLinkExist());
		result &= MiscFunctions.compareResult("Forgot Password link exist", false, acctFoundPg.isForgotPwLinkExist());
		result &= MiscFunctions.compareResult("Create Web Account button exist", false, acctFoundPg.isCreateWebAccountExist());
		result &= MiscFunctions.compareResult("Go to Sign In button exist", false, acctFoundPg.isGoToSignInExist());
		
		if (!result) {
			throw new ErrorOnPageException("Confirm Identity Page UI common text are wrong! Check above logger errors");
		}
		logger.info("---Successfully verify Confirm Identity Page UI common text!");
	}
}
