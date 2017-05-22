package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify email address when input on Account Found Page after lookup account
 * @Preconditions:
 * Make sure HFMO site is authenticated by identifier, setup in ui-options in HF PL site.
 *  <option name="use-hf-authenticate-by-identifier" visible="true">
 *  <option name="display-update-profile-links-on-account-found-page" visible="true"/> 
 *  Customer exist. d_web_hf_signupaccount, id=850
 * @SPEC:Confirm Identity page - Found profile which has no email address entered yet [TC:067990]
 * @Task#: Auto-1831
 * 
 * @author Lesley Wang
 * @Date  Jul 23, 2013
 */
public class IdentifierMode_LookupAcctWithoutEmail extends HFMOWebTestCase {
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private HFAccountOverviewPage acctOverviewPg = HFAccountOverviewPage.getInstance();
	private HFUpdateAccountPage updateAcctPg = HFUpdateAccountPage.getInstance();
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
		
		// Verify there is no email from DB
		this.verifyEmailInDB(StringUtil.EMPTY, cus.identifier.identifierNum);
		
		// Input email after lookup account on Account Found page
		hf.invokeURL(url);
		hf.lookupAccountFromPurchaseTab(cus, HFLicenseCategoriesListPage.getInstance(), false);
		
		// Verify email on Account Overview page, Update Account page and DB
		hf.gotoMyAccountPgFromHeaderBar();
		acctOverviewPg.verifyAccountEmailAndName(cus.email, cus.fName, cus.lName);
		this.gotoUpdateAcctPgByEmailLink();
		updateAcctPg.verifyEmail(cus.email);
		this.verifyEmailInDB(cus.email, cus.identifier.identifierNum);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// customer info
		cus.fName = "IdentMode01_FN"; // d_web_hf_signupaccount, id=850
		cus.lName = "IdentMode01_LN";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; 
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false).replace("Number", "#");
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.email = "hfmo_00019@webhftest.com";
		
		// Login info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";
	}

	private void gotoUpdateAcctPgByEmailLink() {
		acctOverviewPg.clickEmailAddrLink();
		updateAcctPg.waitLoading();
	}
	
	private void verifyEmailInDB(String email, String custNum) {
		String emailFromDB = hf.getHFCustomerEmailInfo(schema, null, custNum);
		String loginNmFromDB = hf.getHFLoginNmByCustNum(schema, custNum);
		
		if (!emailFromDB.equals(email) || StringUtil.notEmpty(loginNmFromDB)) {
			throw new ErrorOnDataException("Email info and login name in DB are wrong!", email+", loginName is empty", 
					emailFromDB + ", loginName=" +loginNmFromDB);
		}
		logger.info("---Verify email and login name in DB correctly!");
	}
}
