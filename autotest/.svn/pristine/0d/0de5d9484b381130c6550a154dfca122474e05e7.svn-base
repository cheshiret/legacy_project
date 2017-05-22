/**
 * 
 */
package com.activenetwork.qa.awo.keywords.web;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAcceptAwardPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAvailableHuntsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFConfirmationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFContactUsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateWebSignInPage;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFDeclineAwardPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFHarvestReportFiledPage;
import com.activenetwork.qa.awo.pages.web.hf.HFHeaderBar;
import com.activenetwork.qa.awo.pages.web.hf.HFHomePage;
import com.activenetwork.qa.awo.pages.web.hf.HFIdentificationAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoryPrdListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensesNotAvaliablePage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryAddGroupMembersPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryCategoriesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryHuntsSelectionPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryTermsConditionsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFMyAccountPanel;
import com.activenetwork.qa.awo.pages.web.hf.HFNewProductsAddedPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFPointDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFPointsHistoryPage;
import com.activenetwork.qa.awo.pages.web.hf.HFPrivInventoryFulfillmentPage;
import com.activenetwork.qa.awo.pages.web.hf.HFProductQuestionnairePage;
import com.activenetwork.qa.awo.pages.web.hf.HFReportHarvestDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidStatusValidationFailPage;
import com.activenetwork.qa.awo.pages.web.hf.HFResidencyStatusDeclarationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFSealNumOptionsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFSendMyPasswordPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.hf.HFSignInNavigationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFSignInPage;
import com.activenetwork.qa.awo.pages.web.hf.HFSignUpPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateEmailAddressPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdatePasswordPage;
import com.activenetwork.qa.awo.pages.web.hf.HFYourAccountFoundPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCheckoutPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for HF Web testing
 * 
 * @author Lesley Wang
 * @Date Feb 25, 2013
 */
public class HFKeyword extends Web {
	private static HFKeyword _instance = null;

	public static HFKeyword getInstance() {
		if (null == _instance)
			_instance = new HFKeyword();

		return _instance;
	}

	protected HFKeyword() {
	}

	/**
	 * Invoke a new browser with the given URL and don't need to get build
	 * number.
	 * 
	 * @param url
	 */
	public void invokeURL(String url) {
		invokeURL(url, false, true);
	}

	/**
	 * Invoke a new browser with the given URL.
	 * 
	 * @param url
	 * @param getBuildNum
	 */
	public void invokeURL(String url, boolean getBuildNum) {
		invokeURL(url, getBuildNum, true);
	}

	/**
	 * Invoke a browser with the given URL.
	 * 
	 * @param url
	 *            - the url to open
	 * @param getBuildNum
	 *            - flag to control if need to get a build number
	 * @param isNewBrowser
	 *            - if need to get a build number
	 */
	public void invokeURL(String url, boolean getBuildNum, boolean isNewBrowser) {
		invokeURL(url, HF, getBuildNum, isNewBrowser);
		browser.waitExists(HFHomePage.getInstance(),
				HFSignInPage.getInstance(), HFAccountLookupPage.getInstance());
	}

	/**
	 * Go to Create Account Page from Header Bar
	 */
	public void gotoCreateAccountPage() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFSignUpPage signUpPg = HFSignUpPage.getInstance();
		HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();

		logger.info("Go to create account page...");
		header.clickSignUpLink();
		signUpPg.waitLoading();
		signUpPg.clickCreateNewRecord();
		createAccPg.waitLoading();
	}

	public void gotoCreateAccountPage(String url, Customer cus) {
		if (isSignInWorkFlows(url)) {
			gotoCreateAccountPage();
		} else {
			lookupAccount(cus);
			gotoCreateAccountPgFromAccountLookupPg();
		}
	}

	/**
	 * Create an account from 'Create an Account' page
	 * 
	 * @param cus
	 * @return
	 */
	public String createAnAccount(Customer cus) {
		HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage
				.getInstance();
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();

		logger.info("Create an account from 'Create an Account' page.");
		createAccPg.setupNewAccountInfo(cus);
		createAccPg.clickCreateAccount();
		Object page = browser.waitExists(accOverviewPg, createAccPg,
				resStatusPg);

		String halID = "";
		if (page == accOverviewPg) {
			halID = accOverviewPg.getHALIDNum();
			logger.info("Create a new Account correctly! HAL ID#=" + halID);
		} else if (page == resStatusPg) {
			logger.info("Residency status declaration page displays for more infomation.");
		} else {
			halID = createAccPg.getTopErrorMsg();
		}
		return halID;
	}

	public void setupAccountContactNumbers(Customer cus) {
		HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage
				.getInstance();

		logger.info("Setup Contact numbers info during create an account from 'Create an Account' page.");
		createAccPg.setupContactNumbers(cus);
		createAccPg.clickCreateAccount();
		browser.waitExists(accOverviewPg, createAccPg);
	}

	/** Sign Up new account based on different authentication mode configuration */
	public String signUpNewAccount(String url, Customer cus) {
		this.invokeURL(url);
		// dynamically adapt to the Authentication mode configuration
		String brand = MiscFunctions.getPLNameFromURL(url);
		// String
		// value=WebConfiguration.getInstance().getHFWebAuthByIdentifierValue(brand);
		String value = WebConfiguration.getInstance().getUIOption(
				Conf.plbrand_UIOptions, UIOptions.HFWebAuthByIdentifier, brand);
		if (StringUtil.isEmpty(value) || !value.equalsIgnoreCase("true")) {
			// Sign in Mode
			return this.signUpNewAccount(cus);
		} else {
			// Identifier Mode
			return this.createNewProfile(cus);
		}
	}

	/**
	 * Sign up a new account by clicking Sign Up link on header bar, and ends at
	 * Account Overview Page or Create Account Page
	 * 
	 * @param cus
	 *            - Customer info for sign up
	 */
	public String signUpNewAccount(Customer cus) {
		this.gotoCreateAccountPage();
		return this.createAnAccount(cus);
	}

	/**
	 * Go to LookUp Account Page from Header Bar
	 */
	public void gotoLookupAccountPage() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFSignUpPage signUpPg = HFSignUpPage.getInstance();
		HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();

		logger.info("Go to Account Lookup page...");
		if (header.isSignUpLinkExisting()) { // For HFSK, there is sign up link,
												// so we can click it
			header.clickSignUpLink();
			signUpPg.waitLoading();
			signUpPg.clickLookUpExistingRecord();
		} else {
			header.clickMyAccountTab(); // For HFMO, there is no sign up link.
										// After clicking my account tab to
										// account lookup page
		}

		lookupAccountPg.waitLoading();
	}

	public Object lookupAccountFromAccountLookupPage(Customer cus) {
		HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();

		logger.info("Look up account from Account Lookup page.");
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.clickLookupProfileButton();
		return browser.waitExists(lookupAccountPg, yourAccountFoundPg);
	}

	/**
	 * Go back to account lookup page from your account found page click 'This
	 * is not my record' link.
	 */
	public void gotoAccountLookupPgFromYourAccountFoundPg() {
		HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();

		logger.info("Go back to account lookup page from your account found page click 'This is not my record' link.");
		yourAccountFoundPg.clickThisIsNotMyRecordLink();
		lookupAccountPg.waitLoading();
	}

	/**
	 * Lookup a new account by clicking Sign Up link on header bar, and ends at
	 * Account Overview Page or Create Account Page
	 * 
	 * @param cus
	 *            - Customer info for sign up
	 */
	public void lookupAccount(Customer cus) {
		logger.info("Look up account from header bar, and ends at Account Found Page or Account Lookup page.");
		this.gotoLookupAccountPage();
		this.lookupAccountFromAccountLookupPage(cus);
	}

	/** Successfully Lookup Account from header bar to account overview page */
	public void lookupAccountToAcctOverviewPg(Customer cus) {
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();

		this.lookupAccount(cus);
		if (yourAccountFoundPg.isEmailAddrTextFieldExist()) {
			yourAccountFoundPg.setCustEmailAddrFieldValue(cus.email);
		}
		this.gotoAccountOverviewPgFromYourAccountFoundPg();
	}

	public void lookupAccountFromPurchaseTab(Customer cus, Page finalPg,
			boolean withEmail) {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFAccountLookupPage lookupPg = HFAccountLookupPage.getInstance();
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();

		header.clickPurchaseLicenseTab();
		lookupPg.waitLoading();
		this.lookupAccountFromAccountLookupPage(cus);
		if (!withEmail) {
			yourAccountFoundPg.setCustEmailAddrFieldValue(cus.email);
		}
		yourAccountFoundPg.clickConfirmAndProceed();
		finalPg.waitLoading();
	}

	/**
	 * Create new profile from account lookup page
	 * 
	 * @param cus
	 * @return
	 */
	public String createNewProfile(Customer cus) {
		this.lookupAccount(cus);
		this.gotoCreateAccountPgFromAccountLookupPg();
		return this.createAnAccount(cus);
	}

	/**
	 * Go to create account page from account lookup page
	 */
	public void gotoCreateAccountPgFromAccountLookupPg() {
		HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();
		HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
		logger.info("Go to create account page from account lookup page.");

		lookupAccountPg.clickCreateANewProfileLink();
		createAccPg.waitLoading();
	}

	/**
	 * Update profile by clicking Update Profile link, and ends at my account
	 * page
	 * 
	 * @param cus
	 *            - Customer info
	 * @Note: Please enhance this method, if you want to update other customer
	 *        profile info than only name info
	 */
	public void updateCustNameInfo(Customer cus) {
		HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();

		logger.info(" Update profile by clicking Update Profile link, and ends at Update Account page...");
		this.gotoUpdateAccountPgFromMyAccountPanel();
		updateAccPg.updateCustomerName(cus.fName, cus.mName, cus.lName,
				cus.suffix);
		updateAccPg.clickSubmitButton();
		accountOverviewPg.waitLoading();
	}

	public void gotoUpdProfilePgFromAcctFoundPg(boolean byUpdInfoLink) {
		logger.info("Go to udpate profile page from account found page by the link Update "
				+ (byUpdInfoLink ? "Information " : "Address "));
		HFYourAccountFoundPage acctFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
		if (byUpdInfoLink) {
			acctFoundPg.clickUpdateInfo();
		} else {
			acctFoundPg.clickUpdateAddress();
		}
		updateAccPg.waitLoading();
	}

	/** Update account info from account found page */
	public void updateInfoFromAcctFoundPg(Customer cus) {
		logger.info("Update Account Info by clicking Update Information link on Account Found page...");
		this.gotoUpdProfilePgFromAcctFoundPg(true);
		this.updateProfileFromUpdateAcctPg(cus);
	}

	/**
	 * Update account info from account found page by clicking Update Address
	 * link
	 */
	public void updateAddrFromAcctFoundPg(Customer cus) {
		logger.info("Update Account Info by clicking Update Address link on Account Found page...");
		this.gotoUpdProfilePgFromAcctFoundPg(false);
		this.updateProfileFromUpdateAcctPg(cus);
	}

	/** Update account info from my account panel */
	public void updateProfile(Customer cus) {
		logger.info(" Update profile by clicking Update Profile link...");
		this.gotoUpdateAccountPgFromMyAccountPanel();
		this.updateProfileFromUpdateAcctPg(cus);
	}

	/** Update account info from update account page */
	public void updateProfileFromUpdateAcctPg(Customer cus) {
		HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();
		logger.info("Update Customer Profile from update account page...");
		updateAccPg.setCustProfile(cus);
		updateAccPg.clickSubmitButton();
		browser.waitExists(accountOverviewPg, updateAccPg);
	}

	/** Update customer date of birth on Update Account Page */
	public void updateCustDOB(String dob) {
		HFUpdateAccountPage updateAccPg = HFUpdateAccountPage.getInstance();
		HFAccountOverviewPage acctOverviewPg = HFAccountOverviewPage
				.getInstance();

		logger.info("Update customer date of birth to " + dob);
		this.gotoMyAccountPgFromHeaderBar();
		this.gotoUpdateAccountPgFromMyAccountPanel();
		updateAccPg.setDateOfBirth(dob);
		updateAccPg.clickSubmitButton();
		acctOverviewPg.waitLoading();
	}

	/**
	 * Update email address
	 * 
	 * @param pw
	 * @param newEmail
	 */
	public void updateEmailAddress(String pw, String newEmail) {
		HFUpdateEmailAddressPage updateEmailPg = HFUpdateEmailAddressPage
				.getInstance();

		this.gotoUpdateEmailAddressPgFromMyAccountPanel();

		updateEmailPg.setPassword(pw);
		updateEmailPg.setNewEmail(newEmail);
		updateEmailPg.clickSaveChangesButton();

		if (updateEmailPg.topPageErrorMesDisplays()
				&& !updateEmailPg.successfulMesDisplays()) {
			throw new ErrorOnPageException("Failed to update email address.");
		}
	}

	/**
	 * Update password
	 * 
	 * @param oldPW
	 * @param newPW
	 */
	public void updatePassword(String oldPW, String newPW) {
		HFUpdatePasswordPage updatePasswordPg = HFUpdatePasswordPage
				.getInstance();

		this.gotoUpdatePasswordPgFromMyAccountPanel();

		updatePasswordPg.setOldPassword(oldPW);
		updatePasswordPg.setNewPassword(newPW);
		updatePasswordPg.repeatNewPassword(newPW);
		updatePasswordPg.clickSaveChangesButton();

		if (updatePasswordPg.topPageErrorMesDisplays()
				&& !updatePasswordPg.successfulMesDisplays()) {
			throw new ErrorOnPageException("Failed to update password.");
		}
	}

	/**
	 * Go to Forget password page from Sign in page.
	 */
	public void gotoForgetYourPasswordPgFromSignInPg() {
		HFSignInPage signInPg = HFSignInPage.getInstance();
		HFSendMyPasswordPage sendMyPasswordPg = HFSendMyPasswordPage
				.getInstance();

		logger.info("Go to Forget password page from Sign in page.");
		signInPg.clickForgetPasswordLink();

		sendMyPasswordPg.exists();
		sendMyPasswordPg.waitLoading();
	}

	/**
	 * Forget password from Sign in page, then end page also is Sign in page.
	 * 
	 * @param email
	 * @param lastName
	 */
	public void forgetYourPassword(String email, String lastName) {
		HFSendMyPasswordPage sendMyPasswordPg = HFSendMyPasswordPage
				.getInstance();
		HFSignInPage signInPg = HFSignInPage.getInstance();

		this.gotoForgetYourPasswordPgFromSignInPg();

		sendMyPasswordPg.setUserName(email);
		sendMyPasswordPg.setLastName(lastName);
		sendMyPasswordPg.clickSendMyPasswordButton();

		Object page = browser.waitExists(sendMyPasswordPg, signInPg);

		if (page == signInPg) {
			logger.info("Successfully forget password, email:" + email
					+ " and last name:" + lastName);
		} else {
			throw new ErrorOnPageException("Failed to forget password, email:"
					+ email + " and last name:" + lastName);
		}
	}

	/**
	 * Add identification, the start page is AddIdentificationPage, the end page
	 * is AddIdentificationPage or identificationAddedPg
	 * 
	 * @param identifierType
	 * @param identifierNum
	 * @param country
	 * @param state
	 */
	public void addIdentifierFromAddIdentificationPg(CustomerIdentifier iden) {
		HFAddIdentificationPage addIdentificationPg = HFAddIdentificationPage
				.getInstance();
		HFIdentificationAddedPage identificationAddedPg = HFIdentificationAddedPage
				.getInstance();

		logger.info("Add identification from Add Identification page.");
		addIdentificationPg.addIdentification(iden);
		addIdentificationPg.clickAddIdentificationButton();
		browser.waitExists(identificationAddedPg, addIdentificationPg);
	}

	/**
	 * Add identification, the start page is HFAccountOverviewPage, the end page
	 * is identificationAddedPg
	 * 
	 * @param iden
	 */
	public void addIdentifier(CustomerIdentifier iden) {
		this.gotoAddIdentificationPg();
		this.addIdentifierFromAddIdentificationPg(iden);
	}

	public void gotoUpdateIdenPgFromUpdateAccountPg(String idenTypeID) {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		HFUpdateIdentificationPage updateIdentPg = HFUpdateIdentificationPage
				.getInstance();
		logger.info("Go to Update Identification page for typeID=" + idenTypeID);
		updateAccountPg.clickUpdateLink(idenTypeID);
		updateIdentPg.waitLoading();
	}

	public void gotoUpdateIdenPg(String idenTypeID) {
		gotoUpdateProfilePg();
		gotoUpdateIdenPgFromUpdateAccountPg(idenTypeID);
	}

	/**
	 * Update identification, the start page is AddIdentificationPage, the end
	 * page is updateAccountPg or updateIdentPg
	 * 
	 * @param iden
	 */
	public void updateIdenFromUpdateIdentificationPg(CustomerIdentifier iden) {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		HFUpdateIdentificationPage updateIdentPg = HFUpdateIdentificationPage
				.getInstance();

		logger.info("Add identification from Add Identification page.");
		updateIdentPg.setNumber(iden.identifierNum);
		if (StringUtil.notEmpty(iden.country)) {
			updateIdentPg.selectCountry(iden.country);
		}
		if (StringUtil.notEmpty(iden.state)) {
			updateIdentPg.waitForStateSync();
			updateIdentPg.selectState(iden.state);
		}
		updateIdentPg.waitLoading();
		updateIdentPg.clickSaveChangesButton();
		browser.waitExists(updateAccountPg, updateIdentPg);
	}

	/**
	 * Update identification, the start page is HFAccountOverviewPage, the end
	 * page is updateAccountPg
	 * 
	 * @param identifierType
	 * @param identifierNum
	 * @param country
	 */
	public void updateIdentifier(String idenTypeID, String idenNum,
			String country, String state) {
		this.gotoUpdateIdenPg(idenTypeID);
		CustomerIdentifier iden = new CustomerIdentifier();
		iden.id = idenTypeID;
		iden.identifierNum = idenNum;
		iden.country = country;
		iden.state = state;

		this.updateIdenFromUpdateIdentificationPg(iden);
	}

	public void updateIdentifier(String idenTypeID, String idenNum,
			String country) {
		this.updateIdentifier(idenTypeID, idenNum, country, StringUtil.EMPTY);
	}

	public void updateIdentifierState(String idenTypeID, String idenNum,
			String state) {
		this.updateIdentifier(idenTypeID, idenNum, StringUtil.EMPTY, state);
	}

	/**
	 * Sign out to Home page
	 */
	public void signOut() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFHomePage homePg = HFHomePage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();

		logger.info("Sign out by clicking Sign Out link on header bar to home page...");
		header.clickSignOutLink();
		browser.waitExists(confirmDialogPg, homePg);
		if (confirmDialogPg.exists()) {
			confirmDialogPg.setDismissible(false);
			confirmDialogPg.clickOK();
			homePg.waitLoading();
		}
	}

	/**
	 * Go to Sign in page
	 */
	public void gotoSignInPage() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFSignInPage signInPg = HFSignInPage.getInstance();
		HFSignInNavigationPage signInNavigationPg = HFSignInNavigationPage
				.getInstance();

		logger.info("Go to Member Sign in page by clicking Sign in link on header bar...");
		header.clickSignInLink();
		if (signInNavigationPg.exists()) {
			signInNavigationPg.clickAlreadyRegisteredWithAWin();
		}
		signInPg.waitLoading();
	}

	/**
	 * Verify webiste work flow model
	 * 
	 * @param url
	 * @return
	 */
	public boolean isSignInWorkFlows(String url) {
		String brand = MiscFunctions.getPLNameFromURL(url);
		String value = WebConfiguration.getInstance().getUIOption(
				Conf.plbrand_UIOptions, UIOptions.HFWebAuthByIdentifier, brand);
		// String
		// value=UIOptionConfigurationUtil.getHFWebAuthByIdentifierValue(brand);
		if (StringUtil.isEmpty(value) || !value.equalsIgnoreCase("true")) {
			return true;
		} else
			return false;
	}

	/**
	 * Sign in with the email and password
	 * 
	 * @param email
	 * @param pw
	 */
	public void signIn(String url, String email, String pw) {
		HFSignInPage signInPg = HFSignInPage.getInstance();
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage
				.getInstance();
		HFSignUpPage signUpPg = HFSignUpPage.getInstance();
		logger.info("Sign in with email=" + email + ", pw=" + pw);

		this.invokeURL(url);

		String brand = MiscFunctions.getPLNameFromURL(url);
		// String
		// value=WebConfiguration.getInstance().getHFWebAuthByIdentifierValue(brand);
		String value = WebConfiguration.getInstance().getUIOption(
				Conf.plbrand_UIOptions, UIOptions.HFWebAuthByIdentifier, brand);
		if (StringUtil.isEmpty(value) || !value.equalsIgnoreCase("true")) {
			// go normal signin work flows
			this.gotoSignInPage();
			signInPg.setEmail(email);
			signInPg.setPassword(pw);
			signInPg.clickSignInButton();
			browser.waitExists(accOverviewPg, signInPg, signUpPg);
		} else {
			// go through authentication by identifier work flows
			String contract = brand.substring(2);
			String env = TestProperty.getProperty("target_env");
			String schema = TestProperty.getProperty(env + ".db.schema.prefix")
					+ contract;
			String[] data = getCustomerInfo(email, schema);
			Customer cus = new Customer();
			cus.dateOfBirth = data[1]; // d_web_hf_signupaccount, id=740
			cus.identifier.identifierType = getIdenTypeName(schema,
					IDEN_HAL_ID, false, false);
			;
			cus.identifier.identifierNum = data[0];

			lookupAccount(cus);
			gotoAccountOverviewPgFromYourAccountFoundPg();

		}
	}

	public String[] getCustomerInfo(String email, String schema) {
		db.resetSchema(schema);
		// String
		// query="select cch.cust_number as custnum,to_char(cc.birthdate, 'YYYY-MM-DD') as dob from c_cust_hfprofile cch, c_cust cc where cc.cust_id=cch.cust_id and cc.login_name='"+email+"'";
		// Sara[8/30/2013]: date format is different between MO and SK contract
		String query = "select cch.cust_number as custnum,to_char(cch.birthday, "
				+ (schema.contains("mo") ? "'MM/DD/YYYY'" : "'YYYY-MM-DD'")
				+ ") as dob "
				+ "from c_cust_hfprofile cch, c_cust cc, c_cust_phone ph "
				+ "where cc.cust_id=cch.cust_id and cc.cust_id=ph.cust_id and "
				+ "(cc.login_name='"
				+ email
				+ "'"
				+ " or ph.typ='EMAIL' and ph.val='" + email + "')"; // Lesley
																	// [20130715]:
																	// update
																	// due to
																	// login
																	// name will
																	// be null
																	// if Sign
																	// up
																	// account
																	// in
																	// identifier
																	// mode

		List<String> results = db.executeQuery(query, "CUSTNUM");
		String[] data = new String[results.size()];
		results.toArray(data);

		if (results.size() > 0) {
			data = db.executeQuery(query, new String[] { "custnum", "dob" }, 0);
		}

		return data;
	}

	public void signIn(String email, String pw) {
		this.signIn(StringUtil.EMPTY, email, pw);
	}

	/**
	 * Sign in successfully. starts from Sign in page, ends as account ovewview
	 * page
	 * 
	 * @param email
	 * @param pw
	 * @author Lesley Wang Apr 16, 2013
	 */
	public void signInToAccountOverviewPg(String email, String pw) {
		HFSignInPage signInPg = HFSignInPage.getInstance();
		HFAccountOverviewPage accOverviewPg = HFAccountOverviewPage
				.getInstance();
		logger.info("Sign in with email=" + email + ", pw=" + pw);
		signInPg.setEmail(email);
		signInPg.setPassword(pw);
		signInPg.clickSignInButton();
		accOverviewPg.waitLoading();
	}

	/**
	 * Go to HF Home page by clicking Home tab on header bar
	 */
	public void gotoHFHomePg() {
		logger.info("Go to HF home page by clicking Home tab on the header...");
		HFHeaderBar.getInstance().clickHomeTab();
		HFHomePage.getInstance().waitLoading();
	}

	public String gotoHFHomePg(boolean leaveThisPage, Page fromPage) {
		HFHeaderBar headerBar = HFHeaderBar.getInstance();
		HFHomePage homePg = HFHomePage.getInstance();
		ConfirmDialogPage windowsIEConfirmationDialogPage = ConfirmDialogPage
				.getInstance();

		logger.info("Go to HF home page by clicking Home tab on the header, the from page is:"
				+ fromPage);

		headerBar.clickHomeTab();
		windowsIEConfirmationDialogPage.setDismissible(false);
		windowsIEConfirmationDialogPage.waitLoading();
		String popupMes = windowsIEConfirmationDialogPage.text();

		if (leaveThisPage) {
			windowsIEConfirmationDialogPage.clickOK();
			homePg.waitLoading();
		} else {
			windowsIEConfirmationDialogPage.clickCancel();
			fromPage.waitLoading();
		}

		logger.info("Popup message is: " + popupMes);
		return popupMes.replaceAll("\\n", "");
	}

	/**
	 * Go to Residency Status Declaration page by clicking Purchase a Licence
	 * link in header bar. The page can be shown when Determine Residency Based
	 * on Addressâ€™ Indicator set to True in DB
	 */
	public void gotoResidStatusDeclaPg() {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		logger.info("Go to Residency Status Declaration page...");
		header.clickPurchaseLicenseTab();
		resStatusPg.waitLoading();
	}

	/**
	 * Select Resident Status and proceed on Resident status page, ends as
	 * validation fail page or product category list page
	 * 
	 * @param residencyStatus
	 * @param identifier
	 * @author Lesley Wang May 9, 2013
	 */
	public void selectResidStatusAndProceed(String residencyStatus,
			CustomerIdentifier identifier) {
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();// Sara[20140113]After
																				// click
																				// OK
																				// to
																				// accept
																				// award
																				// to
																				// HFResidencyStatusDeclarationPage
																				// page.
																				// After
																				// set
																				// data
																				// and
																				// click
																				// Process
																				// button
																				// will
																				// go
																				// to
																				// shopping
																				// cart
																				// page
		logger.info("Select resident status=" + residencyStatus
				+ " and proceed...");
		if (identifier == null || StringUtil.isEmpty(identifier.identifierType)) {
			resStatusPg.selectResidentStatus(residencyStatus);
		} else {
			resStatusPg.setResidentStatusInfo(residencyStatus, identifier);
		}
		resStatusPg.clickProceed();
		browser.waitExists(catListPg, failPg, resStatusPg, shoppingCartPg);
	}

	/**
	 * Select resident status and proceed, ends at product category list page or
	 * faile page or resident status page
	 * 
	 * @param residencyStatus
	 * @param identifier
	 */
	public void selectResidStatusToPrdCategoryPg(String residencyStatus,
			CustomerIdentifier identifier) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage
				.getInstance();
		HFLotteryCategoriesListPage lotteryListPg = HFLotteryCategoriesListPage
				.getInstance();

		logger.info("Select resident status=" + residencyStatus
				+ " to product category list page...");
		this.selectResidStatusAndProceed(residencyStatus, identifier);
		if (failPg.exists()) {
			failPg.clickProceed();
			browser.waitExists(catListPg, lotteryListPg, failPg);
		}
	}

	public void selectResidStatusToPrdCategoryPg(String residencyStatus) {
		this.selectResidStatusToPrdCategoryPg(residencyStatus, null);
	}

	/**
	 * Go to License Categories List page by clicking Purchase tab, login
	 * in/lookup account if not firstly
	 */
	public void gotoLicenseCategoriesListPg(Customer cus, boolean isIdentified) {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFSignInPage signInPg = HFSignInPage.getInstance();
		HFAccountLookupPage lookupPg = HFAccountLookupPage.getInstance();
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFSignInNavigationPage signInNavigationPg = HFSignInNavigationPage
				.getInstance();

		logger.info("Go to License Categories List page by clicking Purchase a License tab...");
		header.clickPurchaseLicenseTab();
		if (signInNavigationPg.exists()) {
			signInNavigationPg.clickAlreadyRegisteredWithAWin();
		}

		Object page = null;
		if (!isIdentified) {
			page = browser.waitExists(signInPg, lookupPg);
			if (page == signInPg) {
				signInPg.setEmail(cus.email);
				signInPg.setPassword(cus.password);
				signInPg.clickSignInButton();

			} else if (page == lookupPg) {
				page = this.lookupAccountFromAccountLookupPage(cus);
				if (page == yourAccountFoundPg) {
					if (yourAccountFoundPg.isEmailAddrTextFieldExist()) {// find
																			// account
						yourAccountFoundPg
								.setCustEmailAddrFieldValue(cus.email);
					}
					yourAccountFoundPg.clickConfirmAndProceed();
				} else {
					gotoCreateAccountPgFromAccountLookupPg();// can't find, need
																// create one
					createAnAccount(cus);
				}
			}
		}
		page = browser.waitExists(resStatusPg, catListPg);
		if (page == resStatusPg) {
			if (cus.residencyIden != null
					&& StringUtil.notEmpty(cus.residencyIden.identifierType)) {
				selectResidStatusToPrdCategoryPg(cus.residencyStatus,
						cus.residencyIden);
			} else
				selectResidStatusToPrdCategoryPg(cus.residencyStatus,
						cus.identifier);
		}
	}

	/**
	 * Go to License Categories List page by clicking Purchase a License tab on
	 * header bar after login in
	 */
	public void gotoLicenseCategoriesListPg(String residencyStatus,
			CustomerIdentifier identifier) {
		HFHeaderBar header = HFHeaderBar.getInstance();
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();

		logger.info("Go to License Categories List page by clicking Purchase a License tab...");
		header.clickPurchaseLicenseTab();
		Object page = browser.waitExists(resStatusPg, catListPg);
		if (page == resStatusPg) {
			selectResidStatusToPrdCategoryPg(residencyStatus, identifier);
		}
	}

	public void gotoLicenseCategoriesListPg(String residencyStatus) {
		this.gotoLicenseCategoriesListPg(residencyStatus, null);
	}

	public void gotoLicenseCategoriesListPg() {
		this.gotoLicenseCategoriesListPg(StringUtil.EMPTY);
	}

	/**
	 * Go to License product list page from license categories list page by
	 * clicking the first See Licenses button
	 */
	public void gotoLicensesListPgFromLicenseCatListPg() {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage
				.getInstance();
		logger.info("Go to License product list page from license categories list page by clicking the first See Licenses button...");
		catListPg.clickSeeLicensesBtn();
		prdListPg.waitLoading();
	}

	/**
	 * Search license in category list page and go to product list page by
	 * clicking category name
	 * 
	 * @param category
	 * @param type
	 * @param licYear
	 */
	public void searchLicensetoLicenseListPg(String category, String type,
			String licYear) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage
				.getInstance();
		logger.info("Search license in category list page: category="
				+ category + "; type=" + type + ";licYear=" + licYear
				+ " and click Category name to product list page....");
		catListPg.filterPrivilege(category, type, licYear);
		catListPg.clickCategoryNm(category);
		prdListPg.waitLoading();
	}

	public void gotoLicenseDetailsPgFromLicenseCatListPg(PrivilegeInfo priv) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();

		logger.info("Search privilege and then go to privilege details page...");
		catListPg.filterPrivilege(priv.displayCategory,
				priv.displaySubCategory, priv.licenseYear);
		this.gotoLicenseDetailsPgFromLicenseCatListPg(priv.name);
	}

	public void gotoLicenseDetailsPgFromLicenseCatListPg(String licenseNm) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();
		logger.info("Go to License details page from license categories list page for product="
				+ licenseNm);
		if (!catListPg.isPrivilegeNameExist(licenseNm)) {
			throw new ErrorOnPageException(licenseNm
					+ " is not displayed on category list page...");
		}
		catListPg.clickLicenseName(licenseNm);
		prdDetailsPg.waitLoading();
	}

	public void gotoLicenseDetailsPgFromLicenseCatListPg(String licenseNm,
			String licCode, String huntDes, String huntQty) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();
		logger.info("Go to License details page from license categories list page for product="
				+ licenseNm
				+ ", licCode="
				+ licCode
				+ ", hunt="
				+ huntDes
				+ ", huntQty=" + huntQty);
		catListPg.clickUnlockedLic(licenseNm, licCode, huntDes, huntQty);
		prdDetailsPg.waitLoading();
	}

	/**
	 * Add privilege from product details page, after click "Add to Cart" button
	 * in produce details page, have 2 results 1. if give final page, will wait
	 * final page 2. else wait the one define in method
	 * 
	 * @param privilege
	 * @param finalPage
	 */
	public void addPrivilegeFromPrdDetailsPg(PrivilegeInfo privilege,
			Page finalPage) {
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFEducationDeclarePage eduDeclarePage = HFEducationDeclarePage
				.getInstance();
		HFPrivInventoryFulfillmentPage privInvFulfillPg = HFPrivInventoryFulfillmentPage
				.getInstance();

		logger.info("Add the privilege to cart with quantity=" + privilege.qty
				+ " and license year =" + privilege.licenseYear);
		if (StringUtil.notEmpty(privilege.licenseYear)
				&& !prdDetailsPg.isLicYearSelected(privilege.licenseYear)) {
			prdDetailsPg.clickLicenseYear(privilege.licenseYear);
			prdDetailsPg.waitLoading();
		}
		if (!privilege.qty.equalsIgnoreCase("1")) {
			prdDetailsPg.selectLicenseQty(privilege.qty);
		}
		prdDetailsPg.clickAddToCart();

		if (finalPage == null) {
			browser.waitExists(prdQuePg, shoppingCartPg, eduDeclarePage,
					privInvFulfillPg);
		} else {
			browser.waitExists(finalPage);
		}
	}

	public void addHuntFromAvailableHuntPg(String huntID) {
		HFAvailableHuntsPage availableHuntPg = HFAvailableHuntsPage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();

		logger.info("Add the hunt (ID:"
				+ huntID
				+ ") from available hunt page. The final page maybe available hunt page or shopping cart pgae.");
		availableHuntPg.clickSelect(huntID);
		browser.waitExists(shoppingCartPg, availableHuntPg);
	}

	public void checkoutNow() {
		HFHeaderBar headerBar = HFHeaderBar.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();

		logger.info("Check out now from header bar to shopping cart page.");
		headerBar.clickItemsInCartLink();
		shoppingCartPg.waitLoading();
	}

	public void continueShoppingToLicensePurchaseDetailsPg() {
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFLicenseCategoriesListPage categoriesPg = HFLicenseCategoriesListPage
				.getInstance();

		logger.info("Continue shopping to license categories details page from shopping cart page.");
		shoppingCartPg.clickContinueShoppinLink();
		categoriesPg.waitLoading();
	}

	public void continueShppingToPrdDetailsPg(PrivilegeInfo privilege) {
		continueShoppingToLicensePurchaseDetailsPg();
		gotoPrdDetailsPgFromLicenseCategoryListPg(privilege, null);
	}

	public void continueShoppingToLotteryCategoryListPg() {
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFLotteryCategoriesListPage categoriesPg = HFLotteryCategoriesListPage
				.getInstance();

		logger.info("Continue shopping to lottery categories details page from shopping cart page.");
		shoppingCartPg.clickContinueShoppinLink();
		categoriesPg.waitLoading();
	}

	public void continueShopping(PrivilegeInfo privilege, Page finalPage) {
		continueShppingToPrdDetailsPg(privilege);
		addPrivilegeFromPrdDetailsPg(privilege, finalPage);
	}

	public void addPrivilegeToCartFromPrdDetailsPg(PrivilegeInfo privilege) {
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage
				.getInstance();
		HFPrivInventoryFulfillmentPage privInvFulfillPg = HFPrivInventoryFulfillmentPage
				.getInstance();
		HFNewProductsAddedPage newPrdAddedPg = HFNewProductsAddedPage
				.getInstance();
		HFSealNumOptionsPage sealOptionsPg = HFSealNumOptionsPage.getInstance();
		addPrivilegeFromPrdDetailsPg(privilege, null);

		if (eduDeclarePg.exists()) {
			eduDeclarePg.checkAttest();
			eduDeclarePg.clickAttestAndProceed();
		}
		
		if (prdQuePg.exists()) {
			prdQuePg.setValidFromDate(privilege.validFromDate);
			if (privilege.privilegeQuestions != null)
				prdQuePg.answerPrivilegeQuestions(privilege.privilegeQuestions);
			prdQuePg.clickContinue();
			if (newPrdAddedPg.exists()) {
				newPrdAddedPg.clickContinue();
				if (sealOptionsPg.exists()) {
					if (privilege.purchseChoiceIsEmail) {
						sealOptionsPg.clickPurchaseChoiceByEmail();
					} else {
						sealOptionsPg.clickPurchaseChoiceBySealNum();
						sealOptionsPg.setSealNum(privilege.sealNum);
					}
					sealOptionsPg.clickContinue();
				}
			}
		}

		if (privInvFulfillPg.exists()) {
			logger.info("Fulfill privielge inventory, default method is fullfilled by mail...");
			if (StringUtil.notEmpty(privilege.inventoryNum)) {
				logger.info("Select Inventory on hand method and input the inventory number "
						+ privilege.inventoryNum);
				if (privInvFulfillPg.isInvOnHandMethodRadioBtnExist()) {
					privInvFulfillPg.selectInvOnHandMethodRadioBtn();
				}
				privInvFulfillPg.setInvTypeNumTextFieldValue(
						privilege.inventoryNum, 0);
			} else
				privInvFulfillPg.selectFulfilledByMailMethodRadioBtn();

			privInvFulfillPg.clickContinueBtn();
		}
		
		shoppingCartPg.waitLoading();
	}

	public void gotoPrdQuestionPgFromEduInfomationPg() {
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage
				.getInstance();
		eduInfoPg.clickYesAttest();
		eduInfoPg.clickSubmit();
		prdQuePg.waitLoading();
	}

	public void gotoNewProdAddedPgFromProdQuestionPg(PrivilegeInfo privilege) {
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();
		HFNewProductsAddedPage newPrdAddedPg = HFNewProductsAddedPage
				.getInstance();

		prdQuePg.answerPrivilegeQuestions(privilege.privilegeQuestions);
		prdQuePg.clickContinue();
		newPrdAddedPg.waitLoading();
	}

	public void gotoShoppingCartPgFromNewProdAddedPg() {
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFNewProductsAddedPage newProdAddedPg = HFNewProductsAddedPage
				.getInstance();
		newProdAddedPg.clickContinue();
		shoppingCartPg.waitLoading();
	}

	public void addNewMembersToCart(List<Customer> custs) {
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage
				.getInstance();
		HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();

		if (null != custs && custs.size() > 0) {
			for (int i = 0; i < custs.size(); i++) {
				addGroupMembersPg.clickAddMoreMembers();
				addGroupMembersPg.waitForRemove(i + 1);
				addGroupMembersPg.setWinNum(custs.get(i).custNum, i + 1);
				addGroupMembersPg.setDOB(custs.get(i).dateOfBirth, i + 1);
			}
		}
		addGroupMembersPg.clickContinueBtn();
		addGroupMembersPg.waitForSuccessMsg();
		addGroupMembersPg.clickContinueBtn();
		cartPg.waitLoading();
	}

	public void returnToProductDetailsPage(){
		HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();
		HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage.getInstance();

		logger.info("Return product details page from education infomation required page.");
		eduInfoRequiredPg.clickReturnToProductDetails();
		productDetailsPg.waitLoading();
	}
	
	public void cancelToProductDetailsPage() {
		HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage
				.getInstance();
		HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();

		logger.info("Cancel without pop-up dialog from education infomation required page to product details page.");
		eduInfoRequiredPg.clickCancel();
		productDetailsPg.waitLoading();
	}

	public String cancelToUpdateEdu(boolean leaveThisPage) {
		HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage
				.getInstance();
		HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();
		ConfirmDialogPage windowsIEConfirmationDialogPage = ConfirmDialogPage
				.getInstance();
		logger.info("Cancel with pop-up dialog from education infomation required page to product details page.");

		eduInfoRequiredPg.clickCancel();
		Browser.sleep(DYNAMIC_SLEEP_BEFORE_CHECK);
		windowsIEConfirmationDialogPage.setDismissible(false);
		windowsIEConfirmationDialogPage.waitLoading();
		String popupMes = windowsIEConfirmationDialogPage.text();

		if (leaveThisPage) {
			windowsIEConfirmationDialogPage.clickStayOnThisPage();// clickCancel()
			productDetailsPg.waitLoading();
		} else {
			windowsIEConfirmationDialogPage.clickLeaveThisPage();// clickOK()
			eduInfoRequiredPg.waitLoading();
		}

		logger.info("Popup message is: " + popupMes);
		return popupMes.replaceAll("\\n", "");
	}

	/**
	 * Make privilege order from Purchase License tab to license details page.
	 * 
	 * @param cus
	 * @param privilege
	 * @param hunt
	 * @param isIdentified
	 *            : true - has logged in or looked up account; false - not
	 *            identified. will login in or lookup account firstly
	 */
	public void makePrivilegeOrderToLicenseDetailPg(Customer cus,
			PrivilegeInfo privilege, HuntInfo hunt, boolean isIdentified) {
		this.gotoLicenseCategoriesListPg(cus, isIdentified);
		gotoPrdDetailsPgFromLicenseCategoryListPg(privilege, hunt);
	}

	public void gotoPrdDetailsPgFromLicenseCategoryListPg(
			PrivilegeInfo privilege, HuntInfo hunt) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();

		catListPg.filterPrivilege(privilege.displayCategory,
				privilege.displaySubCategory, privilege.licenseYear);
		if (StringUtil.isEmpty(privilege.name))
			privilege.name = privilege.purchasingName;
		if (hunt == null) {
			this.gotoLicenseDetailsPgFromLicenseCatListPg(privilege.name);
		} else {
			this.gotoLicenseDetailsPgFromLicenseCatListPg(privilege.name,
					privilege.code, hunt.getDescription(),
					hunt.getNumOfTagQty());
		}
	}

	/**
	 * Make privilege order from Purchase License tab to license details page.
	 * 
	 * @param cus
	 * @param privilege
	 * @param isIdentified
	 *            : true - has logged in or looked up account; false - not
	 *            identified. will login in or lookup account firstly
	 */
	public void makePrivilegeOrderToLicenseDetailPg(Customer cus,
			PrivilegeInfo privilege, boolean isIdentified) {
		// this.gotoLicenseCategoriesListPg(cus, isIdentified);
		// if (StringUtil.isEmpty(privilege.name))
		// privilege.name = privilege.purchasingName;
		// this.gotoLicenseDetailsPgFromLicenseCatListPg(privilege.name);
		this.makePrivilegeOrderToLicenseDetailPg(cus, privilege, null,
				isIdentified);
	}

	/**
	 * Make privilege order from Purchase License tab to license details page
	 * when the user has login in or identified
	 */
	public void makePrivilegeOrderToLicenseDetailPg(Customer cus,
			PrivilegeInfo privilege) {
		// this.gotoLicenseCategoriesListPg(cus.residencyStatus,
		// cus.identifier);
		// if (StringUtil.isEmpty(privilege.name))
		// privilege.name = privilege.purchasingName;
		// this.gotoLicenseDetailsPgFromLicenseCatListPg(privilege.name);
		this.makePrivilegeOrderToLicenseDetailPg(cus, privilege, true);
	}

	/**
	 * Make privilege order from Purchase header bar to order cart page
	 * 
	 * @param cus
	 * @param privilege
	 * @param isIdentified
	 *            : true - has logged in or looked up account; false - not
	 *            identified. will login in or lookup account firstly
	 */
	public void makePrivilegeOrderToCart(Customer cus, PrivilegeInfo privilege,
			HuntInfo hunt, boolean isIdentified) {
		makePrivilegeOrderToLicenseDetailPg(cus, privilege, hunt, isIdentified);
		this.addPrivilegeToCartFromPrdDetailsPg(privilege);
	}

	/**
	 * Make more privilege order from Purchase header bar to order cart page
	 * 
	 * @param cus
	 * @param privilege
	 * @param isIdentified
	 *            : true - has logged in or looked up account; false - not
	 *            identified. will login in or lookup account firstly
	 */
	public void makePrivilegeOrderToCart(Customer cus,
			PrivilegeInfo[] privileges, HuntInfo[] hunts, boolean isIdentified) {
		if (privileges.length != hunts.length) {
			throw new ErrorOnPageException(
					"the number of privilege and hunt are different! priv #: "
							+ privileges.length + "; hunt #:" + hunts.length);
		}

		for (int i = 0; i < privileges.length; i++) {
			this.makePrivilegeOrderToCart(cus, privileges[i], hunts[i],
					isIdentified);
			if (!isIdentified)
				isIdentified = true;
			if (i + 1 < privileges.length) {
				this.gotoPrdCategoryListPgFromCart();
			}
		}
	}

	public void gotoPrdCategoryListPgFromCart() {
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();

		logger.info("Go to product category list page from shopping cart page by Continue link...");
		shoppingCartPg.gotoBookMoreReservation();
		catListPg.waitLoading();
	}

	/**
	 * Make privilege order from Purchase header bar to order cart page
	 * 
	 * @param cus
	 * @param privilege
	 * @param isIdentified
	 *            : true - has logged in or looked up account; false - not
	 *            identified. will login in or lookup account firstly
	 */
	public void makePrivilegeOrderToCart(Customer cus, PrivilegeInfo privilege,
			boolean isIdentified) {
		makePrivilegeOrderToLicenseDetailPg(cus, privilege, isIdentified);
		this.addPrivilegeToCartFromPrdDetailsPg(privilege);
	}

	/**
	 * Make privilege order from Purchase header bar to order cart page when the
	 * user has login in or identified
	 */
	public void makePrivilegeOrderToCart(Customer cus,
			PrivilegeInfo... privileges) {
		for (PrivilegeInfo privilege : privileges) {
			this.makePrivilegeOrderToCart(cus, privilege, true);
		}
	}

	/** Make privilege order to cart without resident status declaration */
	public void makePrivilegeOrderToCart(PrivilegeInfo privilege) {
		this.makePrivilegeOrderToCart(new Customer(), privilege);
	}

	public void makePrivilegeOrder(Customer cus, PrivilegeInfo privilege,
			Page finalPage) {
		makePrivilegeOrderToLicenseDetailPg(cus, privilege);
		this.addPrivilegeFromPrdDetailsPg(privilege, finalPage);
	}

	/** Attest and proceed education declaration to shopping cart page */
	public void attestAndProceedEduDeclare(Page finalPg, Education... edus) {
		HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage
				.getInstance();
		logger.info("Attest and proceed from education declare page to "
				+ finalPg.getName());
		if (edus == null || edus.length < 1) {
			eduDeclarePg.checkAttest();
		} else {
			// check the attest checkbox based on the education type description
			for (Education edu : edus) {
				eduDeclarePg.checkAttest(edu.educationDes);
			}
		}
		eduDeclarePg.clickAttestAndProceed();
		finalPg.waitLoading();
	}

	public void attestAndProceedEduDeclare() {
		this.attestAndProceedEduDeclare(HFShoppingCartPage.getInstance());
	}

	public void attestAndProceedEduDeclare(String... validFromDates) {
		HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();

		logger.info("Attest and proceed to shoopint cart page from education declare page.");
		eduDeclarePg.checkAttest();
		eduDeclarePg.clickAttestAndProceed();
		if (validFromDates != null && validFromDates.length > 0) {
			logger.info("set valid from date...");
			prdQuePg.waitLoading();
			this.setupValidDatesToShoppingCartPg(validFromDates);
		} else {
			shoppingCartPg.waitLoading();
		}
	}

	/** Go to shopping cart page from valid from dates page */
	public void setupValidDatesToShoppingCartPg(String... validFromDates) {
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();

		logger.info("set valid from dates to shopping cart page...");
		for (int i = 0; i < validFromDates.length; i++) {
			prdQuePg.setValidFromDate(validFromDates[i], i);
		}
		prdQuePg.clickContinue();
		shoppingCartPg.waitLoading();
	}

	/** make a replace privilege order to shopping cart page. */
	public void replacePrivilegeToCart(String priNum) {
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();

		logger.info("Replace privilege to order cart page...");
		this.gotoLicDetailsPg(priNum, true);
		licDetailsPg.clickReplaceLostLicBtn();
		shoppingCartPg.waitLoading();
	}

	/** Go to Update Account page by clicking Update Profile on My Account Panel */
	public void gotoUpdateAccountPgFromMyAccountPanel() {
		HFMyAccountPanel panel = HFMyAccountPanel.getInstance();
		HFUpdateAccountPage updatePg = HFUpdateAccountPage.getInstance();
		logger.info("Go to Update Account page by clicking Update Profile on My Account Panel...");
		panel.clickUpdateProfile();
		updatePg.waitLoading();
	}

	/**
	 * Get identifier number, include customer# in update account page
	 * 
	 * @param idenType
	 * @return
	 */
	public String getIdenNumFromUpdateAccountPg(String idenType) {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		this.gotoUpdateAccountPgFromMyAccountPanel();
		return updateAccountPg.getIdenNum(idenType);
	}

	/**
	 * Go to 'My Account' page from 'Header bar'
	 */
	public void gotoMyAccountPgFromHeaderBar() {
		HFHeaderBar headerBar = HFHeaderBar.getInstance();
		HFMyAccountPanel panel = HFMyAccountPanel.getInstance();

		logger.info("Go to 'My Account' page from 'Header bar'.");
		headerBar.clickMyAccountTab();
		panel.waitLoading();
	}

	public void gotoMyAccountPgFromHeaderBar(boolean isOK) {
		HFHeaderBar headerBar = HFHeaderBar.getInstance();
		HFMyAccountPanel panel = HFMyAccountPanel.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();

		logger.info("Go to 'My Account' page from 'Header bar'.");
		headerBar.clickMyAccountTab();
		confirmDialogPg.setDismissible(false);
		browser.waitExists(confirmDialogPg, panel, headerBar);
		if (confirmDialogPg.exists()) {
			if (isOK) {
				confirmDialogPg.clickOK();
				panel.waitLoading();
			} else {
				confirmDialogPg.clickCancel();
				headerBar.waitLoading();
			}
		} else {
			panel.waitLoading();
		}
	}

	/**
	 * Go to Update Email Address page by clicking Update Email on My Account
	 * Panel
	 */
	public void gotoUpdateEmailAddressPgFromMyAccountPanel() {
		HFMyAccountPanel myAccountPanel = HFMyAccountPanel.getInstance();
		HFUpdateEmailAddressPage updateEmailPg = HFUpdateEmailAddressPage
				.getInstance();

		logger.info("Go to Update Email Address page by clicking Update Email on My Account Panel...");
		myAccountPanel.clickUpdateEmail();
		updateEmailPg.waitLoading();
	}

	/**
	 * Go to Update Password page by clicking Update Email on My Account Panel
	 */
	public void gotoUpdatePasswordPgFromMyAccountPanel() {
		HFMyAccountPanel myAccountPanel = HFMyAccountPanel.getInstance();
		HFUpdatePasswordPage updatePasswordPg = HFUpdatePasswordPage
				.getInstance();

		logger.info("Go to Update Password page by clicking Update Password on My Account Panel...");
		myAccountPanel.clickUpdatePassword();
		updatePasswordPg.waitLoading();
	}

	public void gotoAddIdentificationPg() {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		HFAddIdentificationPage addIdentificationPg = HFAddIdentificationPage
				.getInstance();
		HFMyAccountPanel myAccountPanel = HFMyAccountPanel.getInstance();

		if (!myAccountPanel.exists()) {
			this.gotoMyAccountPgFromHeaderBar();
		}
		if (!updateAccountPg.exists()) {
			myAccountPanel.clickUpdateProfile();
			updateAccountPg.waitLoading();
		}
		updateAccountPg.clickAddIdentificationLink();
		addIdentificationPg.waitLoading();
	}

	public void gotoUpdateProfilePg() {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		HFMyAccountPanel myAccountPanel = HFMyAccountPanel.getInstance();

		if (!myAccountPanel.exists()) {
			this.gotoMyAccountPgFromHeaderBar();
		}
		myAccountPanel.clickUpdateProfile();
		updateAccountPg.waitLoading();
	}

	public void gotoShoppingCartPgFromHeaderBar() {
		HFHeaderBar headerBar = HFHeaderBar.getInstance();
		HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
		logger.info("Go to shopping cart page by clicking the link Items In Cart in hearder bar...");
		headerBar.clickItemsInCartLink();
		cartPg.waitLoading();
	}

	public void abandonCart() {
		HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();
		ConfirmDialogPage confirmDg = ConfirmDialogPage.getInstance();

		logger.info("Abandon cart...");
		confirmDg.setBeforePageLoading(false);
		confirmDg.setDismissible(false);
		cartPg.abandonThisCart();
		confirmDg.waitLoading();
		confirmDg.clickOK();
		cartPg.waitLoading();
	}

	public void gotoCreateWebSignInPgFromAcctFoundPg() {
		HFCreateWebSignInPage createWebPg = HFCreateWebSignInPage.getInstance();
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		logger.info("Go to create web account page from account found page...");
		yourAccountFoundPg.clickCreateWebAccount();
		createWebPg.waitLoading();
	}

	public void gotoCurrentLicencesListPgFromMyAcctPanel() {
		HFMyAccountPanel acctPanel = HFMyAccountPanel.getInstance();
		HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage
				.getInstance();
		logger.info("Go to current license page from account overview page.");
		acctPanel.clickLicences();
		currentLicensePg.waitLoading();
	}

	public void gotoExpiredLicensesListPgFromCurrentLicencesListPg() {
		HFExpiredLicensesListPage expiredLicensesPg = HFExpiredLicensesListPage
				.getInstance();
		HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage
				.getInstance();
		logger.info("Go to current license page from expired licence page.");
		currentLicensePg.clickExpiredTab();
		expiredLicensesPg.waitLoading();
	}

	public void gotoCurrentLicensesListPgFromExpiredLicencesListPg() {
		HFExpiredLicensesListPage expiredLicensesPg = HFExpiredLicensesListPage
				.getInstance();
		HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage
				.getInstance();
		logger.info("Go to expired licence page expired licence page.");
		expiredLicensesPg.clickCurrentTab();
		currentLicensePg.waitLoading();
	}

	public void createWebAccountAfterLookUp(String email, String pw,
			String rePw, String hPhone) {
		HFCreateWebSignInPage createWebPg = HFCreateWebSignInPage.getInstance();
		HFAccountOverviewPage accOvePg = HFAccountOverviewPage.getInstance();
		logger.info("Create Web Account after look up account...");
		logger.info("pw=" + pw);
		if (StringUtil.isEmpty(hPhone)) {
			createWebPg.setWebAccountInfo(email, pw, rePw);
		} else {
			createWebPg.setWebAccountInfo(email, pw, rePw, hPhone);
		}
		createWebPg.clickCreateButton();
		browser.waitExists(accOvePg, createWebPg);
	}

	public void lookUpAndCreateWebAccount(Customer cus) {
		this.lookupAccount(cus);
		this.gotoCreateWebSignInPgFromAcctFoundPg();
		this.createWebAccountAfterLookUp(cus.email, cus.password,
				cus.retypePassword, cus.hPhone);
	}

	public void gotoSignInAfterLookUp() {
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFSignInPage signInPg = HFSignInPage.getInstance();
		logger.info("Go to sign in after look up account found by clicking the button....");
		yourAccountFoundPg.clickGoToSignIn();
		signInPg.waitLoading();
	}

	public void gotoSignInAfterLookUpViaEmail() {
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFSignInPage signInPg = HFSignInPage.getInstance();
		logger.info("Go to sign in after look up account found by clicking the link....");
		yourAccountFoundPg.clickEmailLink();
		signInPg.waitLoading();
	}

	public void gotoAccountLookupPgAfterLookUp() {
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFAccountLookupPage accountLookupPg = HFAccountLookupPage.getInstance();
		logger.info("Go to sign in after look up account found by clicking the link....");
		yourAccountFoundPg.clickThisIsNotMyRecordLink();
		accountLookupPg.waitLoading();
	}

	public void gotoAccountOverviewPgFromYourAccountFoundPg() {
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();
		logger.info("Go to account overview found page from your account found page.");
		yourAccountFoundPg.clickConfirmAndProceed();
		accountOverviewPg.waitLoading();
	}

	public void LotteryAppPgFromAccountOverviewPg() {
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		accountOverviewPg.clickMoreApplicationsLink();
		lotteryAppPg.waitLoading();
	}

	public void gotoLotteryAppPgFromAccountOverviewPg() {
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		logger.info("Go to lottery application page from account overview page.");
		accountOverviewPg.clickLotteryApp();
		lotteryAppPg.waitLoading();
	}

	public void gotoLotteryAppPgFromHeaderBar() {
		gotoMyAccountPgFromHeaderBar();
		gotoLotteryAppPgFromAccountOverviewPg();
	}

	public void gotoLotteryAppPgFromYourAccountFoundPg() {
		gotoAccountOverviewPgFromYourAccountFoundPg();
		gotoLotteryAppPgFromAccountOverviewPg();
	}

	public void gotoSendPasswordPgAfterLookup() {
		HFYourAccountFoundPage yourAccountFoundPg = HFYourAccountFoundPage
				.getInstance();
		HFSendMyPasswordPage sendPwPg = HFSendMyPasswordPage.getInstance();
		logger.info("Go to send password page after look up account found by clicking the link....");
		yourAccountFoundPg.clickForgotPasswordLink();
		sendPwPg.waitLoading();
	}

	public void gotoCreateAccountPgFromLicNotAvailPg() {
		HFLicensesNotAvaliablePage notAvailPg = HFLicensesNotAvaliablePage
				.getInstance();
		HFCreateAccountPage createPg = HFCreateAccountPage.getInstance();
		logger.info("Go to create account page from license not available page...");
		notAvailPg.clickCreateHFRecord();
		createPg.waitLoading();
	}

	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and
	 * ends at the home page
	 * 
	 * @param pay
	 *            - payment information
	 * @return - the reservation number
	 */
	public String checkOutCart(Payment pay) {
		return checkOutCart(pay, false);
	}

	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and
	 * ends at the home page
	 * 
	 * @param pay
	 *            - payment information
	 * @param isCopy
	 *            - whether or not to click on Copy card info from previous link
	 * @return - the reservation number
	 */
	public String checkOutCart(Payment pay, boolean isCopy) {
		HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
		HFHomePage hmPg = HFHomePage.getInstance();

		logger.info("Check out cart.");
		String resID = checkOutCartToConfirmationPage(pay, isCopy);
		confirmPg.gotoHomePage();
		hmPg.waitLoading();

		logger.info("resID:" + resID);
		return resID;
	}

	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and
	 * ends at the home page
	 * 
	 * @param pay
	 *            - array of payment information
	 * @return - the reservation number
	 */
	public String checkOutCart(Payment[] pays) {
		HFShoppingCartPage shoppingCart = HFShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
		HFHomePage hmPg = HFHomePage.getInstance();

		logger.info("Check out cart.");
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();
		checkoutPg.setupPayments(pays);
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();
		confirmPg.waitLoading();
		String resID = confirmPg.getAllResNums();

		confirmPg.gotoHomePage();
		hmPg.waitLoading();

		return resID;// exportReservNo(fees2);
	}

	/**
	 * Check out shopping cart to confirmation page. The workflow starts from
	 * Shopping cart page and ends at the home page
	 * 
	 * @param pay
	 *            - payment information
	 * @return - the reservation number
	 */
	public String checkOutCartToConfirmationPage(Payment pay) {
		return checkOutCartToConfirmationPage(pay, false);
	}

	/**
	 * Check out shopping cart to confirmation page. The workflow starts from
	 * Shopping cart page and ends at the home page
	 * 
	 * @param pay
	 *            - payment information
	 * @param isCopy
	 *            - whether or not to click on Copy card info from previous link
	 * @return - the reservation number
	 */
	public String checkOutCartToConfirmationPage(Payment pay, boolean isCopy) {
		HFShoppingCartPage shoppingCart = HFShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();

		logger.info("Check out cart to confirmation page...");
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();
		checkoutPg.setupPayment(pay);
		if (isCopy) {
			checkoutPg.clickCopyPreviousCardInfo();// copy the previous card
													// info for second payment
													// section
		}
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();

		confirmPg.waitLoading();

		String resID = confirmPg.getAllResNums();
		return resID;
	}

	public void gotoUpdateAcctPgFromResidFailPg() {
		HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
		HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage
				.getInstance();
		logger.info("Go to Update Account page from Resident Status Validation Fail page...");
		failPg.clickUpdateAccountLink();
		updAcctPg.waitLoading();
	}

	public void gotoResidDeclarPgFromResidFailPg() {
		HFResidencyStatusDeclarationPage statusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage
				.getInstance();
		logger.info("Go to Resident Status Declaration page from Resident Status Validation Fail page...");
		failPg.clickGoBack();
		statusPg.waitLoading();
	}

	public void gotoContactPgFromResidFailPg() {
		HFContactUsPage contactPg = HFContactUsPage.getInstance();
		HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage
				.getInstance();
		logger.info("Go to Contact page from Resident Status Validation Fail page...");
		failPg.clickContactCallCenterLink();
		contactPg.waitLoading();
	}

	public void gotoProdCateListPgFromResidFailPg() {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFResidStatusValidationFailPage failPg = HFResidStatusValidationFailPage
				.getInstance();
		logger.info("Go to Product Category List page from Resident Status Validation Fail page...");
		failPg.clickProceed();
		catListPg.waitLoading();
	}

	public void gotoAddIdentificationPgFromUptAcctPg() {
		HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
		HFAddIdentificationPage addIdentificationPg = HFAddIdentificationPage
				.getInstance();
		logger.info("Go to Add Identification page from update account page...");

		updateAccountPg.clickAddIdentificationLink();
		addIdentificationPg.waitLoading();
	}

	public String[] getAllLicenceOrders() {
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();
		HFOrderHistoryPage orderHistory = HFOrderHistoryPage.getInstance();
		String[] orderNums = new String[] {};

		if (accountOverviewPg.isMoreOrdersLinkExists()) {
			accountOverviewPg.clickMoreOrdersLink();
			orderHistory.waitLoading();

			orderNums = orderHistory.getAllOrderNums();
		} else if (accountOverviewPg.isRecentOrderItmeExists()) {
			orderNums = accountOverviewPg.getAllRecentOrderNums();
		} else {
			logger.info("No any order item found.");
		}

		return orderNums;
	}

	public String[] getAllLicencesNum() {
		HFAccountOverviewPage accountOverviewPg = HFAccountOverviewPage
				.getInstance();
		HFOrderHistoryPage orderHistory = HFOrderHistoryPage.getInstance();
		String[] orderNums = new String[] {};

		if (accountOverviewPg.isMoreOrdersLinkExists()) {
			accountOverviewPg.clickMoreOrdersLink();
			orderHistory.waitLoading();

			orderNums = orderHistory.getAllLicencesNum();
		} else if (accountOverviewPg.isRecentOrderItmeExists()) {
			orderNums = accountOverviewPg.getAllLicencesNum();
		} else {
			logger.info("No any license num found.");
		}

		// Remove the duplicate value
		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(orderNums));
		return set.toArray(new String[0]);
	}

	public void gotoOrerHistoryPgFromAcctOvePg() {
		HFAccountOverviewPage acctOvePg = HFAccountOverviewPage.getInstance();
		HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();

		logger.info("Go to order history page from account overview page.");
		acctOvePg.clickMoreOrdersLink();
		orderHistoryPg.waitLoading();
	}

	public void gotoOrderHistoryPgFromMyAcctPanel() {
		HFMyAccountPanel acctPanel = HFMyAccountPanel.getInstance();
		HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();

		logger.info("Go to Order History page by clicking Order History link on My Account panel....");
		acctPanel.clickOrderHistory();
		orderHistoryPg.waitLoading();
	}

	public void gotoOrdHistPgFromOrdConfirmPg() {
		HFOrderHistoryPage orderHistoryPg = HFOrderHistoryPage.getInstance();
		HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();

		logger.info("Go to order history page from order confirmation page....");
		confirmPg.gotoCurrentReservations();
		orderHistoryPg.waitLoading();
	}

	public void gotoLicDetailsPgFromOrdHistPg(String licNum) {
		HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();

		logger.info("Go to License Details page from order history page for licence number="
				+ licNum);
		if (StringUtil.isEmpty(licNum)) {
			ordHistPg.clickFirstLicenseNum();
		} else {
			ordHistPg.clickLicenseNum(licNum);
		}
		licDetailsPg.waitLoading();
	}

	public void gotoPreviousPgFromLicDetailsPg(boolean byTopLink) {
		HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
		HFCurrentLicensesListPage curLicListPg = HFCurrentLicensesListPage
				.getInstance();
		HFExpiredLicensesListPage expiredLicListPg = HFExpiredLicensesListPage
				.getInstance();
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();

		logger.info("Go back to previous page from License Details page...");
		if (byTopLink) {
			licDetailsPg.clickPrevious();
		} else {
			licDetailsPg.clickBottomPrevious();
		}
		browser.waitExists(ordHistPg, curLicListPg, expiredLicListPg);
	}

	public void gotoOrderDetailsPgFromLicDetailsPg(String ordNum) {
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
		HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();

		logger.info("Go to order details page from license details page... order number="
				+ ordNum);
		licDetailsPg.clickOrderNumber(ordNum);
		ordDetailsPg.waitLoading();
	}

	public void gotoOrderDetailsPgFromOrdHistPg(String licNum, String ordNum) {
		this.gotoLicDetailsPgFromOrdHistPg(licNum);
		this.gotoOrderDetailsPgFromLicDetailsPg(ordNum);
	}

	/** Go to Order Details Page from header bar or my account panel */
	public void gotoOrderDetailsPg(String licNum, String ordNum) {
		HFMyAccountPanel myAcctPanel = HFMyAccountPanel.getInstance();
		HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
		if (!myAcctPanel.exists()) {
			this.gotoMyAccountPgFromHeaderBar();
		}
		if (!ordHistPg.exists()) {
			this.gotoOrderHistoryPgFromMyAcctPanel();
		}
		this.gotoOrderDetailsPgFromOrdHistPg(licNum, ordNum);
	}

	public void gotoLicDetailsPgFromOrdDetailsPg(boolean topLink) {
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
		HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
		logger.info("Go to License Details Page from order details page by click "
				+ (topLink ? "Top" : "Bottom") + " previous link...");
		if (topLink) {
			ordDetailsPg.clickPreviousTopLink();
		} else {
			ordDetailsPg.clickPreviousBottomLink();
		}
		licDetailsPg.waitLoading();
	}

	public void gotoLicDetailsPgFromOrdDetailsPg(String priNum) {
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
		HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
		logger.info("Go to License Details Page from order details page by click privilege num="
				+ priNum);
		ordDetailsPg.clickLicenseNum(priNum);
		licDetailsPg.waitLoading();
	}

	/**
	 * Go to License Details page no matter what is the start page, only if log
	 * in
	 */
	public void gotoLicDetailsPg(String priNum, boolean isCurrent) {
		HFMyAccountPanel acctPanel = HFMyAccountPanel.getInstance();
		HFCurrentLicensesListPage curLicListPg = HFCurrentLicensesListPage
				.getInstance();
		HFExpiredLicensesListPage expiredLicPg = HFExpiredLicensesListPage
				.getInstance();
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();

		logger.info("Go to License Details page: number=" + priNum);
		if (!acctPanel.exists()) {
			this.gotoMyAccountPgFromHeaderBar();
		}
		if (!curLicListPg.exists()) {
			this.gotoCurrentLicencesListPgFromMyAcctPanel();
		}
		if (!isCurrent) {
			this.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
			expiredLicPg.findAndClickLicenseNum(priNum);
		} else {
			curLicListPg.findAndClickLicenseNum(priNum);
		}
		licDetailsPg.waitLoading();
	}

	/** Go to License Details page from current license list page */
	public void gotoLicDetailsPgFromCurtLicListPg(String priNum) {
		HFCurrentLicensesListPage curLicListPg = HFCurrentLicensesListPage
				.getInstance();
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();

		logger.info("Go to License Details page from current license list page: number="
				+ priNum);
		curLicListPg.findAndClickLicenseNum(priNum);
		licDetailsPg.waitLoading();
	}

	/** Go to License Details page from expired license list page */
	public void gotoLicDetailsPgFromExpiredLicListPg(String priNum) {
		HFExpiredLicensesListPage expiredLicPg = HFExpiredLicensesListPage
				.getInstance();
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();

		logger.info("Go to License Details page from expired license list page: number="
				+ priNum);
		expiredLicPg.findAndClickLicenseNum(priNum);
		licDetailsPg.waitLoading();
	}

	/** Fill harvest report from License Details page */
	public String fillHarvestReportFromLicDetailsPg(Harvest harvest) {
		HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
		HFReportHarvestDetailsPage reportHarvPg = HFReportHarvestDetailsPage
				.getInstance();
		HFHarvestReportFiledPage reportFiledPg = HFHarvestReportFiledPage
				.getInstance();

		logger.info("Complete harvest report from License Details page for harvest number="
				+ harvest.harvestNum);
		licDetailsPg.clickHarvestNumber(harvest.harvestNum);
		reportHarvPg.waitLoading();
		reportHarvPg.formatAndSetDateOfKill(harvest.dateOfKill);
		reportHarvPg.clickReportHarvestBtn();
		reportFiledPg.waitLoading();
		String confirmNum = reportFiledPg.getConfirmationNum();
		reportFiledPg.clickDone();
		licDetailsPg.waitLoading();
		return confirmNum;
	}

	public void updateEdu(Education edu) {
		HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage
				.getInstance();
		HFEducationInfomationAddedPage eduInfoAddedPg = HFEducationInfomationAddedPage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		eduInfoRequiredPg.setupEducationInfo(edu);
		eduInfoRequiredPg.clickSubmit();
		browser.waitExists(eduInfoAddedPg, eduInfoRequiredPg, shoppingCartPg);
	}

	public void updateEduAndContinue(Education edu, Page finalPg) {
		logger.info("Update education info and continue to the specfic page - "
				+ finalPg.getName());
		if (edu != null) {
			this.updateEdu(edu);
			HFEducationInfomationAddedPage.getInstance().clickContinue();
		} else {
			// click skip this step link
			HFEducationInfomationRequiredPage.getInstance().clickSkipStepLink();
		}
		finalPg.waitLoading();
	}

	public void updateEduToShoppingCartPg(Education edu) {
		this.updateEduAndContinue(edu, HFShoppingCartPage.getInstance());
	}

	/** Go to product details page from category products list page */
	public void gotoPrdDetailsPgFromCategoryPrdListPg(String privNm) {
		this.gotoPrdDetailsPgFromCategoryPrdListPg(privNm, StringUtil.EMPTY);
	}

	/** Go to product details page from category products list page */
	public void gotoPrdDetailsPgFromCategoryPrdListPg(String privNm,
			String authPrivNum) {
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();
		HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage
				.getInstance();

		logger.info("Go to produce details page from category products list page for the privilege - "
				+ privNm);
		if (StringUtil.isEmpty(authPrivNum)) {
			prdListPg.clickPrivName(privNm);
		} else {
			prdListPg.clickPrivName(privNm, authPrivNum);
		}
		prdDetailsPg.waitLoading();
	}

	/** Go to product details page from privilege inventory fulfillment page */
	public void gotoPrdDetailsPgFromPrivInvFulfillPg() {
		HFPrivInventoryFulfillmentPage privInvFulfillmentPg = HFPrivInventoryFulfillmentPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();

		logger.info("Go back to product details page from privilege inventory fulfillment page...");
		privInvFulfillmentPg.clickReturnToDetailsLink();
		browser.waitExists(confirmDialogPg, prdDetailsPg);
		if (confirmDialogPg.exists()) {
			confirmDialogPg.setDismissible(false);
			confirmDialogPg.clickOK();
			prdDetailsPg.waitLoading();
		}
	}

	public String printPrivilegesOnConfirmationPg(String path) {
		HFConfirmationPage confirmPg = HFConfirmationPage.getInstance();
		FileDownloadDialogPage fileDownloadPg = FileDownloadDialogPage
				.getInstance();

		logger.info("Print privielges to download the pdf file to " + path);
		String ordNum = confirmPg.getAllResNums();

		confirmPg.clickPrintPrivileges();
		fileDownloadPg.setDismissible(false);
		fileDownloadPg.setBeforePageLoading(false);
		fileDownloadPg.waitLoading();

		File file = new File(path);
		if (!file.exists()) {
			boolean exists = file.mkdir();
			if (!exists) {
				throw new RuntimeException("Failed to create directory - "
						+ path);
			}
		}

		// download file
		String fullFileName = file.getAbsolutePath() + "\\"
				+ "PrintPrivileges_" + ordNum + ".pdf";
		this.downloadFile(fullFileName);
		confirmPg.waitLoading();
		return fullFileName;
	}

	/**
	 * Go back from product purchase details page by clicking Find Other Items
	 * link
	 */
	public void goBackFromPrdPurchaseDetailsPg() {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage
				.getInstance();
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();

		logger.info("Click the link Find Other Items to go back from produce purchase details page...");
		prdDetailsPg.clickFindOtherItems();
		browser.waitExists(prdListPg, catListPg);
	}

	/**
	 * Go back from category products list page by clicking Find Other Licences
	 * link
	 */
	public void goBackFromCategoryPrdListPg() {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		HFLicenseCategoryPrdListPage prdListPg = HFLicenseCategoryPrdListPage
				.getInstance();

		logger.info("Click the link Find Other Items to go back from produce purchase details page...");
		prdListPg.clickFindOtherPrivs();
		catListPg.waitLoading();
	}

	public void goBackFromQuestionPg() {
		HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage
				.getInstance();
		HFLicensePurchaseDetailsPage prdDetailsPg = HFLicensePurchaseDetailsPage
				.getInstance();
		ConfirmDialogPage dialogPage = ConfirmDialogPage.getInstance();

		logger.info("Go back from question page to license details page...");
		prdQuePg.clickReturnToLicDetails();
		dialogPage.setDismissible(false);
		dialogPage.waitLoading();
		dialogPage.clickOK();
		prdDetailsPg.waitLoading();
	}

	/** Go to Lottery Categories list page */
	public void gotoLotteryCategoriesListPg(Customer cus) {
		HFLotteryTermsConditionsPage termsPg = HFLotteryTermsConditionsPage
				.getInstance();
		HFResidencyStatusDeclarationPage residDeclarPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFLotteryCategoriesListPage lotteryListPg = HFLotteryCategoriesListPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();

		logger.info("Go to Lottery Categories list page...");
		String brand = MiscFunctions.getPLNameFromURL();
		// if(UIOptionConfigurationUtil.isHFWebLotteryTopLinkShown(brand)) {
		// if(brand.equals("hfab")?WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_Navigation,
		// UIOptions.HFABWebLotteryTopLinkShown, brand)
		// :WebConfiguration.getInstance().getUIOptionBoolean(Conf.plbrand_Navigation,
		// UIOptions.HFWebLotteryTopLinkShown, brand)) {
		if (brand.equals("hfab") ? true : WebConfiguration.getInstance()
				.getUIOptionBoolean(Conf.plbrand_Navigation,
						UIOptions.HFWebLotteryTopLinkShown, brand)) {
			// Click the Lottery link to lottery categories list page
			HFHeaderBar.getInstance().clickLotteryTab();
		} else {
			// Input the lottery categories list page URL
			String url = browser.url();
			url = url.substring(0, url.lastIndexOf("/"));
			url += "/productCategoriesList.do?prodType=lottery&topTabIndex=Lottery";
			invokeURL(url, HF, false, false);
		}
		if (confirmDialogPg.exists()) {
			confirmDialogPg.setDismissible(false);
			confirmDialogPg.clickOK();
		}
		Object page = browser.waitExists(termsPg, residDeclarPg, lotteryListPg);
		if (page == termsPg) {
			termsPg.clickAcceptAndProceed();
			page = browser.waitExists(residDeclarPg, lotteryListPg);
		}

		if (page == residDeclarPg) {
			if (cus.residencyIden != null
					&& StringUtil.notEmpty(cus.residencyIden.identifierType)) {
				selectResidStatusToPrdCategoryPg(cus.residencyStatus,
						cus.residencyIden);
			} else {
				selectResidStatusToPrdCategoryPg(cus.residencyStatus,
						cus.identifier);
			}
		}
	}

	/** Go to lottery product details page from lottery categories list page */
	public void gotoLotteryDetailsPgFromCatListPg(String lotteryName) {
		HFLotteryCategoriesListPage lotteryListPg = HFLotteryCategoriesListPage
				.getInstance();
		HFLotteryDetailsPage lotteryDetailsPg = HFLotteryDetailsPage
				.getInstance();

		lotteryListPg.clickLotteryName(lotteryName);
		lotteryDetailsPg.waitLoading();
	}

	/** Apply lottery to add hunts page from lottery application details page */
	public void applyLotteryFromLotteryDetailsPgToAddHuntsPg(String option) {
		HFLotteryDetailsPage lotteryDetailsPg = HFLotteryDetailsPage
				.getInstance();
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage
				.getInstance();

		logger.info("Apply lottery as " + option
				+ " from lottery details page to hunts selection page...");
		lotteryDetailsPg.selectLotteryApplyForADraw();
		if (option.equalsIgnoreCase("Individual")) {
			lotteryDetailsPg.selectIndividual();
		} else if (option.equalsIgnoreCase("GroupLeader")) {
			lotteryDetailsPg.selectGroupLeader();
		}
		lotteryDetailsPg.clickContinue();
		huntsPg.waitLoading();
	}

	public void applyLotteryAsIndividualToAddHuntsPg() {
		this.applyLotteryFromLotteryDetailsPgToAddHuntsPg("Individual");
	}

	public void applyLotteryAsGroupLeaderToAddHuntsPg() {
		this.applyLotteryFromLotteryDetailsPgToAddHuntsPg("GroupLeader");
	}

	/**
	 * Select submit lottery application with point from lottery details page to
	 * add hunts page
	 */
	private void submitLotteryWithPointsToAddHuntsPg(String option) {
		HFLotteryDetailsPage lotteryDetailsPg = HFLotteryDetailsPage
				.getInstance();
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage
				.getInstance();

		logger.info("Submit lottery with points as " + option
				+ " from lottery details page to add hunts page...");
		lotteryDetailsPg.selectSubmitWithPoints();
		if (option.equalsIgnoreCase("Individual")) {
			lotteryDetailsPg.selectPointIndividual();
		} else if (option.equalsIgnoreCase("GroupLeader")) {
			lotteryDetailsPg.selectPointGroupLeader();
		}
		lotteryDetailsPg.clickContinue();
		huntsPg.waitLoading();
	}

	public void submitLotteryWithPointsAsIndividualToAddHuntsPg() {
		this.submitLotteryWithPointsToAddHuntsPg("Individual");
	}

	public void submitLotteryWithPointsAsGroupLeaderToAddHuntsPg() {
		this.submitLotteryWithPointsToAddHuntsPg("GroupLeader");
	}

	public void gotoLotteryDetailsPgFromAddHuntsPg() {
		HFLotteryDetailsPage lotteryDetailsPg = HFLotteryDetailsPage
				.getInstance();
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage
				.getInstance();

		logger.info("Go to lottery details page from add hunts page...");
		huntsPg.clickNavigationLink();
		lotteryDetailsPg.waitLoading();
	}

	/** Submit lottery hunts from hunts selection page to shopping cart page */
	public void submitLotteryHuntsToCart() {
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage
				.getInstance();
		HFShoppingCartPage cartPg = HFShoppingCartPage.getInstance();

		logger.info("Click Submit on Hunts Selection page to shopping cart page...");
		huntsPg.clickHuntWidgetSubmitBtn();
		cartPg.waitLoading();
	}

	public void submitLotteryHuntsToAddMembersPg() {
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage
				.getInstance();
		HFLotteryAddGroupMembersPage addMembersPg = HFLotteryAddGroupMembersPage
				.getInstance();

		logger.info("Click Submit on Hunts Selection page to shopping cart page...");
		huntsPg.clickHuntWidgetSubmitBtn();
		addMembersPg.waitLoading();
	}

	public void gotoAddHuntsPgFromHeaderBar(Customer cus, String lotteryDes,
			boolean isPurchasePoint, String appType) {
		logger.info("Go to add hunts page from header bar...");
		this.gotoLotteryCategoriesListPg(cus);
		this.gotoLotteryDetailsPgFromCatListPg(lotteryDes);
		if (isPurchasePoint) {
			this.submitLotteryWithPointsToAddHuntsPg(appType);
		} else {
			this.applyLotteryFromLotteryDetailsPgToAddHuntsPg(appType);
		}
	}

	public void gotoAddHuntsPgAsIndividualFromHeaderBar(Customer cus,
			String lotteryDes, boolean isPurchasePoint) {
		this.gotoAddHuntsPgFromHeaderBar(cus, lotteryDes, isPurchasePoint,
				"Individual");
	}

	public void gotoAddHuntsPgAsIndividualFromHeaderBar(Customer cus,
			String lotteryDes) {
		this.gotoAddHuntsPgAsIndividualFromHeaderBar(cus, lotteryDes, false);
	}

	public void gotoAddHuntsPgAsGroupLeaderFromHeaderBar(Customer cus,
			String lotteryDes, boolean isPurchasePoint) {
		this.gotoAddHuntsPgFromHeaderBar(cus, lotteryDes, isPurchasePoint,
				"GroupLeader");
	}

	public void gotoAddHuntsPgAsGroupLeaderFromHeaderBar(Customer cus,
			String lotteryDes) {
		this.gotoAddHuntsPgAsGroupLeaderFromHeaderBar(cus, lotteryDes, false);
	}

	public void submitLotteryHuntsToCart(Customer cus, String lotteryDes,
			boolean isPurchasePoint, String appType, String... huntDescs) {
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage
				.getInstance();

		this.gotoAddHuntsPgFromHeaderBar(cus, lotteryDes, isPurchasePoint,
				appType);
		huntsPg.addHuntChoices(huntDescs);
		this.submitLotteryHuntsToCart();
	}

	public void submitLotteryWithPointsAsIndividualToCart(Customer cus,
			String lotteryDes, String... huntDescs) {
		this.submitLotteryHuntsToCart(cus, lotteryDes, true, "Individual",
				huntDescs);
	}

	public void submitLotteryWithPointsAsGroupLeaderToCart(Customer cus,
			String lotteryDes, String... huntDescs) {
		this.submitLotteryHuntsToCart(cus, lotteryDes, true, "GroupLeader",
				huntDescs);
	}

	public void submitLotteryWithoutPointsAsIndividualToCart(Customer cus,
			String lotteryDes, String... huntDescs) {
		this.submitLotteryHuntsToCart(cus, lotteryDes, false, "Individual",
				huntDescs);
	}

	public void submitLotteryWithoutPointsAsGroupLeaderToCart(Customer cus,
			String lotteryDes, String... huntDescs) {
		this.submitLotteryHuntsToCart(cus, lotteryDes, false, "GroupLeader",
				huntDescs);
	}

	/** Go to points history page */
	public void gotoPointsHistoryPage() {
		HFPointsHistoryPage pointsPg = HFPointsHistoryPage.getInstance();
		HFMyAccountPanel myAcctPanel = HFMyAccountPanel.getInstance();

		logger.info("Go to points history page...");
		if (!myAcctPanel.exists()) {
			this.gotoMyAccountPgFromHeaderBar();
		}
		myAcctPanel.clickPoints();
		pointsPg.waitLoading();
	}

	/** Go to point details page */
	public void gotoPointDetailsPageFromPointsPg(String pointDes) {
		HFPointsHistoryPage pointsPg = HFPointsHistoryPage.getInstance();
		HFPointDetailsPage detailsPg = HFPointDetailsPage.getInstance();

		logger.info("Go to point details page for point=" + pointDes);
		pointsPg.clickPointType(pointDes);
		detailsPg.waitLoading();
	}

	public void gotoPointDetailsPage(String pointDes) {
		this.gotoPointsHistoryPage();
		this.gotoPointDetailsPageFromPointsPg(pointDes);
	}

	public String gotoAcceptAwardPgFromLotteryAppPg(String orderNum) {
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		HFAcceptAwardPage acceptAwardPg = HFAcceptAwardPage.getInstance();
		logger.info("Go to Accept award page from lottery application page.");
		lotteryAppPg.clickPurchaseLicenseLink(orderNum);
		acceptAwardPg.waitLoading();
		return acceptAwardPg.getPgTitle();
	}

	public void gotoLotteryAppPgFromAcceptAwardPg() {
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		HFAcceptAwardPage acceptAwardPg = HFAcceptAwardPage.getInstance();
		logger.info("Go to lottery application page from accept award page.");
		acceptAwardPg.clickCancel();
		lotteryAppPg.waitLoading();
	}

	public String gotoDeclindAwardPgFromLotteryAppPg(String orderNum) {
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		HFDeclineAwardPage declineAwardPg = HFDeclineAwardPage.getInstance();
		logger.info("Go to Decline award page from lottery application page.");
		lotteryAppPg.clickDeclineAwardLink(orderNum);
		declineAwardPg.waitLoading();
		return declineAwardPg.getPgTitle();
	}

	public void gotoLotteryAppPgFromDeclindAwardPg() {
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		HFDeclineAwardPage declineAwardPg = HFDeclineAwardPage.getInstance();
		logger.info("Go to lottery application page from Decline award page");
		declineAwardPg.clickCancel();
		lotteryAppPg.waitLoading();
	}

	public void acceptAwardToCart(String orderNum, String residencyStatus) {
		HFAcceptAwardPage acceptAwardPg = HFAcceptAwardPage.getInstance();
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		gotoAcceptAwardPgFromLotteryAppPg(orderNum);
		acceptAwardPg.clickOK();
		browser.waitExists(resStatusPg, shoppingCartPg);
		if (resStatusPg.exists())
			selectResidStatusAndProceed(residencyStatus, null);
	}

	public void declineAward(String orderNum, String residencyStatus) {
		HFDeclineAwardPage declineAwardPg = HFDeclineAwardPage.getInstance();
		HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage
				.getInstance();
		gotoDeclindAwardPgFromLotteryAppPg(orderNum);
		declineAwardPg.clickDecline();
		lotteryAppPg.waitLoading();
	}

	public void acceptAwardFromAcctOverviewPgToCart(String ordNum,
			String residencyStatus, CustomerIdentifier ident) {
		HFAccountOverviewPage overviewPg = HFAccountOverviewPage.getInstance();
		HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage
				.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();

		logger.info("Accept Awarded lottery application from account overview page to shopping cart page...");

		overviewPg.clickAcceptInAwardedAppSection(ordNum);
		overviewPg.waitAcceptAwardDialogShown();
		overviewPg.clickOKOnAcceptAwardDialog();
		browser.waitExists(resStatusPg, shoppingCartPg);
		if (resStatusPg.exists()) {
			selectResidStatusAndProceed(residencyStatus, ident);
		}
	}

	public void filterPrivilegeInLicenseCategoriesListPg(String category,
			String type, String licYear) {
		HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage
				.getInstance();
		catListPg.filterPrivilege(category, type, licYear);
		catListPg.waitLoading();
	}

	public void filterPrivilegeInLicenseCategoriesListPg(String licYear) {
		filterPrivilegeInLicenseCategoriesListPg(StringUtil.EMPTY,
				StringUtil.EMPTY, licYear);
	}
	
	public void chooseLotteryHuntsToEduInfoPg(HuntInfo...hunts){
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
		huntsPg.setupHuntChoicesWithContinueBtn(hunts);
		huntsPg.clickContinueBtn();
		eduInfoPg.waitLoading();
	}

	public void goToAddMemberPgFromEduInfoPg(){
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
		eduInfoPg.clickYesAttest();
		eduInfoPg.clickSubmit();
		addGroupMembersPg.waitLoading();
	}

	public void addNewMembersToEduInfoPg(List<Customer> custs){
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
		
			if(null!=custs && custs.size()>0){
				for(int i=0; i<custs.size(); i++){
					addGroupMembersPg.clickAddMoreMembers();
					addGroupMembersPg.waitForRemove(i+1);
					addGroupMembersPg.setWinNum(custs.get(i).custNum, i+1);
					addGroupMembersPg.setDOB(custs.get(i).dateOfBirth, i+1);
				}
			}
			addGroupMembersPg.clickContinueBtn();
			addGroupMembersPg.waitForSuccessMsg();
			addGroupMembersPg.clickContinueBtn();
			eduInfoPg.waitLoading();
	}
	
	public void gotoShoppingCartPgEduInfoPg(){
		HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		eduInfoPg.clickYesAttest();
		eduInfoPg.clickSubmit();
		shoppingCartPg.waitLoading();
	}
	
	public void chooseLotteryHuntsToAddGroupMembersPg(boolean isIndividual, List<Customer> custs, HuntInfo...hunts){
		HFLotteryHuntsSelectionPage huntsPg = HFLotteryHuntsSelectionPage.getInstance();
		HFLotteryAddGroupMembersPage addGroupMembersPg = HFLotteryAddGroupMembersPage.getInstance();

		huntsPg.setupHuntChoicesWithContinueBtn(hunts);
		huntsPg.clickContinueBtn();
		addGroupMembersPg.waitLoading();
		if(null!=custs && custs.size()>0){
			for(int i=0; i<custs.size(); i++){
				addGroupMembersPg.clickAddMoreMembers();
				addGroupMembersPg.waitForRemove(i+1);
				addGroupMembersPg.setWinNum(custs.get(i).custNum, i+1);
				addGroupMembersPg.setDateOfBirth(custs.get(i).dateOfBirth, i+1);
			}
		}
		addGroupMembersPg.clickContinueBtn();
		addGroupMembersPg.waitForSuccessMsg();
	}
}
