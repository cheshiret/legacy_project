package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.accountoverview;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View Account Overview page in Identifier Mode
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 *  Customer exist. d_web_hf_signupaccount, id=860
 * @SPEC:Account Overview page when authenticate by identifier [TC:067992]
 * @Task#: Auto-1832
 * 
 * @author Lesley Wang
 * @Date  Jul 25, 2013
 */
public class IdentifierMode_AccountOverview extends HFMOWebTestCase {
	private HFAccountOverviewPage acctOverviewPg = HFAccountOverviewPage.getInstance();
	
	@Override
	public void execute() {
		// Make sure HFMO site is authenticated by identifier
		if (hf.isSignInWorkFlows(url)) {
			throw new ErrorOnPageException("HFMO site should be authenticated by identifier. Please check the site's ui-option!");
		}			
		
		// Lookup account to account overview page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		
		// Verify no Update Email or Update Password links on My Account Panel
		this.verifyUpdateEmailPwLinks();
		
		// Verify links on Account Overview page: customer number, name, email, phone number
		this.verifyAccountOverviewLinks();
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.fName = "IdentMode02_FN"; // d_web_hf_signupaccount, id=860
		cus.lName = "IdentMode02_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
	}

	private void verifyUpdateEmailPwLinks() {
		if (acctOverviewPg.isUpdateEmailExist() || acctOverviewPg.isUpdatePasswordExist()) {
			throw new ErrorOnPageException("Update Email and Update Password links should not exist on My Account Panel!");
		}
		logger.info("---Successfully verify Update Email and Update Password links not exist on My Account Panel!!");
	}
	
	private void verifyAccountOverviewLinks() {
		HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();

		acctOverviewPg.clickCustNumberLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
		
		acctOverviewPg.clickNameLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
		
		acctOverviewPg.clickEmailAddrLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
			
		acctOverviewPg.clickPhoneNumLink();
		updAcctPg.waitLoading();
		hf.gotoMyAccountPgFromHeaderBar();
		
		logger.info("---Verify Account Overview links correctly!");
	}
}
