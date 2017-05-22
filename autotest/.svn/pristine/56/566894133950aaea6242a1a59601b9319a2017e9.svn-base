package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify email address text field in Identifier Mode
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 * @SPEC: Update Profile UI change - Authenticated by identifier model [TC:100015]
 * @Task#: Auto-1832
 * 
 * @author Lesley Wang
 * @Date  Jul 26, 2013
 */
public class IdentifierMode_EmailAddrTextFieldValidation extends HFMOWebTestCase {
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
	private HFAccountOverviewPage acctOverviewPg = HFAccountOverviewPage.getInstance();
	private String topMsg, emptyEmailMsg, invalidEmailMsg, validEmail;
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}		

		// Empty customer email in LM 
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		custDetailsPg.editCustEmailAddress(StringUtil.EMPTY);
		lm.logOutLicenseManager();

		// Lookup account and update information to Update Profile page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoUpdProfilePgFromAcctFoundPg(true);
		updateAccPg.verifyEmailAddrInCustDetailsSec(StringUtil.EMPTY);
		this.updateEmail(cus.email, acctOverviewPg);
		hf.signOut();
		
		// Lookup account and verify email on Update Account page
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updateAccPg.verifyEmailAddrInCustDetailsSec(cus.email);
		
		// Input invalid email address and verify error message
		this.updateEmail(StringUtil.EMPTY, updateAccPg);
		updateAccPg.verifyMsgExist(topMsg, true);
		updateAccPg.verifyMsgExist(emptyEmailMsg, true);
		
		this.updateEmail("12@#", updateAccPg);
		updateAccPg.verifyMsgExist(topMsg, true);
		updateAccPg.verifyMsgExist(invalidEmailMsg, true);
		
		this.updateEmail("test@test", updateAccPg);
		updateAccPg.verifyMsgExist(topMsg, true);
		updateAccPg.verifyMsgExist(invalidEmailMsg, true);
		
		// Input valid email and verify on update profile page
		this.updateEmail(validEmail, acctOverviewPg);
		hf.gotoUpdateAccountPgFromMyAccountPanel();
		updateAccPg.verifyEmailAddrInCustDetailsSec(validEmail);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "IdentMode06_FN"; // d_web_hf_signupaccount, id=900
		cus.lName = "IdentMode06_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false).replace("Number", "#");
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.email = "hfmo_00024@webhftest.com";
		validEmail = "hfmo_00024_2@webhftest.com";
		
		// Login info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";

		// Error Messages
		topMsg = "We need you to correct or provide more information. Please see each marked section.";
		emptyEmailMsg = "Email Address is required.";
		invalidEmailMsg = "The email address you provided is not valid.";		
	}
	
	private void updateEmail(String newEmail, Page finalPg) {
		updateAccPg.setEmailAddrInCustDetailsSec(newEmail);
		updateAccPg.clickSubmitButton();
		finalPg.waitLoading();
	}
}
