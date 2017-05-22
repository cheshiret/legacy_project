/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.residstatusdeclaration;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.web.hf.HFHeaderBar;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensesNotAvaliablePage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidencyStatusDeclarationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFSignUpPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the UI and the display of the Residency Status Declaration (RSD) page after: 
 * 1. look up account and create a web account firstly
 * 2. look up account and go to sign in
 * 3. sign in with an account without SK HF Profile.
 * @Preconditions:
 * The record has been inserted into the table x_prop in SK schema by support sql: 
 * insert into x_prop (id, name, namespace, type, value) values (CONTRACT_SEQ.nextval, 'ResidencyModel', 'Contract', 'Number', '2');
 * @SPEC: 
 * (Web) Residency Status Declaration page - Display [TC:059973]
 * (Web) Residency Status Declaration page - UI [TC:062507]
 * @Task#: Auto-1623
 * 
 * @author Lesley Wang
 * @Date  Apr 16, 2013
 */
public class ViewAfterLoginWithDiffAccounts extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String dobForWeb, pageTitle, pageCaption, statusInstru, proceedLabel, signUpPgTitle, signUpCaption, noLicMsg;//pageTopInfo
	private List<String> optionsNms;
	
	@Override
	public void execute() {
		// Create an account in LM
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		cus.identifier.identifierNum = lm.createNewCustomerWithoutSearch(cus);
		lm.logOutLicenseManager();
		
		// Sing In Mode: Go to HF SK Web to create a web account
		this.initialWebAccountInfo();
		hf.invokeURL(url);
		boolean isSignInMode = hf.isSignInWorkFlows(url);
		if (isSignInMode) {
			hf.lookUpAndCreateWebAccount(cus);
		} else { // Identifier Mode: lookup account
			hf.lookupAccountToAcctOverviewPg(cus);
		}
		
		// Verify Residency Status Declaration UI
		hf.gotoResidStatusDeclaPg();
		this.verifyResidStatusDeclaPgUI();
		hf.signOut();
		
		// Sign In Mode: Look up account and sign in
		if (isSignInMode) {
			hf.lookupAccount(cus);
			hf.gotoSignInAfterLookUp();
			hf.signInToAccountOverviewPg(cus.email, cus.password);
			hf.gotoResidStatusDeclaPg();
			hf.signOut();
			
			// Delete the account's HF profile from DB
			hf.deleteCustAllIdentExceptCustNum(schema, cus.email);
			hf.deleteCusAllAttr(schema, cus.email);
			hf.deleteCusAllAddress(schema, cus.email);
			hf.deleteCusHFProfile(schema, cus.email);
			
			// Sign in with the account and verify the page
			hf.signIn(url, cus.email, cus.password);
			this.verifyInfoMsgOnSignUpPg();
			this.purchaseLicWhenNoProfile();
			this.verifyInfoMsgOnNoLicAvailPg();
			
			// Create profile and view Residency Status Declaration page
			hf.gotoCreateAccountPgFromLicNotAvailPg();
			this.initialAccountIdentfierInfo();
			hf.createAnAccount(cus);
			hf.gotoResidStatusDeclaPg();
			hf.signOut();
			hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for LM
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin/SASK";
		
		// Customer info
		cus.setDefaultValuesForHFWebSignUp();
		cus.setDefaultCanadaAddress(); // set physical address
		cus.email = "hf" + new Random().nextInt(99999) + "@test.com";
		
		dobForWeb = cus.dateOfBirth;
		cus.dateOfBirth = DateFunctions.formatDate(cus.dateOfBirth, "yyyyMMdd");
		
		// UI info
		pageTitle = "Residency Status Declaration";
		pageCaption = ".*confirm your residency status.*validate your selection.*";
//		pageTopInfo = "Important.*provide additional information to confirm your residency.*add identification.*";
		statusInstru = "Please select the option that accurately describes your current residency status:";
		proceedLabel = "By clicking Proceed you declare that the above information is accurate and true.";
		optionsNms = new ArrayList<String> ();
		optionsNms.add(RESID_STATUS_SK);
		optionsNms.add(RESID_STATUS_SK + " - " + IDENT_TYPE_RCMP);
		optionsNms.add(RESID_STATUS_SK + " - " + IDENT_TYPE_CAF);
		optionsNms.add(RESID_STATUS_CAN);
		optionsNms.add(RESID_STATUS_CAN + " - " + IDENT_TYPE_CANDL);
		optionsNms.add(RESID_STATUS_CAN + " - " + IDENT_TYPE_FL);
		optionsNms.add(RESID_STATUS_NON);
		optionsNms.add(RESID_STATUS_NON + " - " + IDENT_TYPE_PASSPORT);
		optionsNms.add(RESID_STATUS_NON + " - " + IDENT_TYPE_OTHER);
		
		signUpPgTitle = "A Hunting and Fishing Saskatchewan Profile needed";
		signUpCaption = "This account does not have a Hunting and Fishing Saskatchewan Profile needed to process your request.";
	
		noLicMsg = ".*no licences available for your purchase.*not seeing any items because you do not have a Hunt & Fish record.*";
	}
	
	private void initialWebAccountInfo() {
		// info for look up account
		cus.identifier.identifierType = IDENT_TYPE_HAL;
		cus.dateOfBirth = dobForWeb;
		
		// info for create web account
		cus.email = "hf" + new Random().nextInt(99999) + "@test.com";
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
	}
	
	private void initialAccountIdentfierInfo() {
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Saskatchewan";
	}
	
	private void verifyResidStatusDeclaPgUI() {
		HFResidencyStatusDeclarationPage residStaDecPg = HFResidencyStatusDeclarationPage.getInstance();
		boolean result = true;
		String actTitle = residStaDecPg.getPageTitleAndCaption();
		result &= MiscFunctions.containString("Page Title", actTitle, pageTitle);
		result &= MiscFunctions.matchString("Page Caption", actTitle, pageCaption);
//		result &= MiscFunctions.matchString("Important info", residStaDecPg.getPageTopInfo(), pageTopInfo);
		result &= MiscFunctions.matchString("Status Instruction", residStaDecPg.getStatusInstruction(), statusInstru);
		result &= MiscFunctions.matchString("Proceed Instruction", residStaDecPg.getProceedInstruction(), proceedLabel);

		// Compare status sort order
		List<String> actOptions = residStaDecPg.getOptionsName();
		if (!actOptions.equals(optionsNms)) {
			result &= false;
			logger.error("The options names are wrong! Expect: " + optionsNms.toString() + " Actual: " + actOptions.toString());
		} else {
			result &= true;
			logger.info("The options names are correct!");
		}
		
		result &= MiscFunctions.compareResult("No status selected", true, residStaDecPg.isAllRadioBtnNotSelected());
	
		if (!result) {
			throw new ErrorOnPageException("Residency Status Declaration page UI is wrong! Check logger error");
		}
		logger.info("---Verify Residency Status Declaration page UI correctly!");
	}
	
	private void verifyInfoMsgOnSignUpPg() {
		HFSignUpPage signUpPg = HFSignUpPage.getInstance();
		boolean result = true;
		String actTitle = signUpPg.getPageTitleAndCaption();
		result &= MiscFunctions.containString("Page Title", actTitle, signUpPgTitle);
		result &= MiscFunctions.containString("Page Caption", actTitle, signUpCaption);
		if (!result) {
			throw new ErrorOnPageException("The messages on the page are wrong after sign in with account no profile! Check logger error");
		}
		logger.info("---Verify the messages on the page after sign in with account no profile correctly!");
	}
	
	private void purchaseLicWhenNoProfile() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFLicensesNotAvaliablePage notAvailPg = HFLicensesNotAvaliablePage.getInstance();
		header.clickPurchaseLicenseTab();
		notAvailPg.waitLoading();
	}
	
	private void verifyInfoMsgOnNoLicAvailPg() {
		HFLicensesNotAvaliablePage notAvailPg = HFLicensesNotAvaliablePage.getInstance();
		boolean result = true;
		result &= MiscFunctions.matchString("Page Messages", notAvailPg.getMessages(), noLicMsg);
		result &= MiscFunctions.compareResult("home page link exist", true, notAvailPg.isHomePageLinkExist());
		if (!result) {
			throw new ErrorOnPageException("The messages on No Available Licenses page are wrong! Check logger error");
		}
		logger.info("---Verify the messages on No Available Licenses page correctly!");
	}
}
