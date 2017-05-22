package com.activenetwork.qa.awo.keywords.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.pos.InteragencyPassAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.pos.SerializePassAttr;
import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.MaPassInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.OrderDetails;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.GeneralSignOutPage;
import com.activenetwork.qa.awo.pages.web.AdiminDoDialogPage;
import com.activenetwork.qa.awo.pages.web.bw.BwChangeCompletePage;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.ra.RAParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.ra.RAParkDetailsPageForCampAndMarina;
import com.activenetwork.qa.awo.pages.web.recgov.ActivitiesDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.AgencySignInPage;
import com.activenetwork.qa.awo.pages.web.recgov.OrmsApplicationLaunchPadPage;
import com.activenetwork.qa.awo.pages.web.recgov.RaCrossOverToRecPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovCapeHattterasORVPermitSaleOverviewPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovCapeHattterasORVPermitSalePage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovExploreAndMorePage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovHomePage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovInteragencyPassDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovInteragencyPassSpotPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPrintThisPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSurveyPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpActiveAdvantageTrialOfferPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpActivePassesPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAdminPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCalendarPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundDirectotyPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsByStatePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsMapBrowsePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundsMapSearchPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampingLotteryApplicationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampingLotteryApplicationListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampingLotteryDetailsPage1;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampingLotteryDetailsPage2;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeReservationConfirmPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeReservationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeTourReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCheckAvailabilityPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCheckoutPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpClaimFreeGiftPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCreateAccountPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCreateAvailabilityNotificationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCreateCampingClubProfilePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCrossOverToSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCurrentResListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDiscountPassesPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpExpiredPassesPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpExternalWebsitePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpFeeAndCancellationPoliciesPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpGoogleNationMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpGoogleStateMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpKOACampsiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpKOAOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMaMarketingSpotPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMaPassesPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpParkDetailsCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpParkListCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPastResListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPayBalanceConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPayBalancePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitLotteryApplicationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPreRegistrationListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpProductDetailsCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpProductListCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationSearchHomePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRedeemableVouchersListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSelectDiscountPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSerializePassDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSerializePassSpotPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSiteSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSocialSecurityNumberRequiredPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTicketLotteryApplicationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTicketLotteryApplicationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTicketLotteryConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourCheckoutPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourParkListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateEmailPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateOrganizationProfile;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdatePasswordPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateProfilePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.pages.web.xmloutput.CampSearchResultXMLOutputPage;
import com.activenetwork.qa.awo.pages.web.xmloutput.CampgroundDetailXMLOutputPage;
import com.activenetwork.qa.awo.pages.web.xmloutput.CampsiteDetailXMLOutputPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.page.PrintDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * This class contains the most popular work flows in WEB testing.
 * @author QA
 */
public class UWP extends Web {
	private static UWP _instance = null;

	public static UWP getInstance() {
		if (null == _instance)
			_instance = new UWP();

		return _instance;
	}

	protected UWP() {
	}

	
	/**
	 * Go to sign up page of web
	 */
	public void signUp() {
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		UwpCreateAccountPage createAccountPg = UwpCreateAccountPage
		.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		
		logger.info("Sign up.");

		memberStatusBar.signUp();
//		createAccountPg.waitLoading();
		Object page = browser.waitExists(createAccountPg, signInOrUpPg); // Sara[12/2/2013] update from 3.04.01 due to RA Sign Up flow changed
		if (page == signInOrUpPg) {
			signInOrUpPg.clickContinueToSignUp();
			signInOrUpPg.waitLoading();
		}
	}
	
	@Override
	public boolean tryToSignOut() {
		GeneralSignOutPage signoutPg=GeneralSignOutPage.getInstance();
		ConfirmDialogPage dialog=ConfirmDialogPage.getInstance();
		logger.info("Trying to sign out from current session");
		return signoutPg.tryToSignOut(dialog);
	}

	/**
	 * Sign in ORMS application launch pad with the given user and password.
	 * starts at header bar, ends at launch pad page.
	 * @param email - user account
	 * @param pw - password
	 * @param envType - environment type
	 */
	public void agencySignIn(String email, String pw, String envType) {
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		AgencySignInPage agencySignIn = AgencySignInPage.getInstance();
		OrmsApplicationLaunchPadPage launchPad = OrmsApplicationLaunchPadPage.getInstance();
		AlertDialogPage secAlertDialog = AlertDialogPage.getInstance();

		logger.info("Sign in ORMS Application with user=" + email + "/password=" + pw);
		if(!signInPg.exists()) {
			memberStatusBar.gotoSignIn();
			signInPg.waitLoading();
		}

		signInPg.clickAgencySignIn();
		browser.waitExists(secAlertDialog,agencySignIn);
		agencySignIn.agencySignIn(email, pw, envType);
		browser.waitExists(secAlertDialog,launchPad);
	}

	/**
	 * Verify the error message display on page when using an existing email to sign up.
	 * @param cust - customer info
	 */
	public void verifyAccountExists(Customer cust) {
		UwpCreateAccountPage createAccountPg = UwpCreateAccountPage.getInstance();
		UwpSignInSignUpPage signInSignUpPg = UwpSignInSignUpPage.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		
		logger.info("verify the account is already exist.");
		this.signUp();
//		createAccountPg.waitLoading();
//		createAccountPg.createNewMemberAccount(cust);
//
//		Object page = browser.waitExists(accountOVPg,createAccountPg);
//		if (page == accountOVPg) {
//			throw new ErrorOnPageException("Account does not exist in our system.");
//		}
		Object page = browser.waitExists(createAccountPg, signInOrUpPg); // update from 3.04.01 due to RA Sign Up flow changed
		if (page == signInOrUpPg) {
			signInOrUpPg.clickContinueToSignUp();
			signInOrUpPg.waitLoading();
			signInOrUpPg.createNewMemberAccount(cust);	
		} else {
			createAccountPg.createNewMemberAccount(cust);
		}
		
		page = browser.waitExists(accountOVPg,createAccountPg, signInOrUpPg);
		if (page == accountOVPg) {
			throw new ErrorOnPageException("Account does not exist in our system.");
		}else if(page == signInOrUpPg){
			signInOrUpPg.verifyEmailExistError();
		}else createAccountPg.verifyEmailExistError();
	}

	/**
	 * This method goes to verify the sign is failed as expected.
	 * It starts at member status bar, ends at sign in page if pass, or account
	 * overview page if failed.
	 * @param email - email account
	 * @param pw - password
	 */
	public void verifySignInFailed(String email, String pw) {
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();

		memberStatusBar.gotoSignIn();

		Object page = browser.waitExists(signInUpPg, signInPg);
		if (page == signInPg) {
			signInPg.signIn(email, pw);
		} else {
			signInUpPg.signIn(email, pw);
		}
		
		page = browser.waitExists(signInPg,signInUpPg,accountOVPg);

		if(page == accountOVPg){
			throw new ErrorOnPageException("Should not log into with " + email);
		}else {
			if(signInPg.verifySignInFailed())
				logger.info("Log in failed as expected.");
		}
	}

	/**
	 * Abandon the current shopping cart.
	 */
	public void abandonCart() {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		ConfirmDialogPage confirmDg=ConfirmDialogPage.getInstance();

		logger.info("Abandon cart.");
		confirmDg.setBeforePageLoading(false);
		confirmDg.setDismissible(false);
		shoppingCart.abandonThisCart();
		confirmDg.waitLoading();
		confirmDg.clickOK();
		shoppingCart.waitLoading();
	
		if (!shoppingCart.isShoppingCartEmpty()) {
			throw new PageNotFoundException("The shopping cart is not empty!");
		}
	}

	public void verifyCancelLinkNotAvailable() {
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		boolean exists=resDetailsPg.cancelLinkExists();
		if(exists) {
			throw new ErrorOnPageException("Cancel link should not be available.");
		}
	}

	public void verifyChangeLinkNotAvailable() {
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		boolean exists=resDetailsPg.changeLinkExists();
		if(exists) {
			throw new ErrorOnPageException("Change Reservation link should not be available.");
		}
	}
	
	public void verifyChangeDateOrSiteTransferLinkNotAvailable() {
		UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
		boolean exists=updateResDetailPg.changeDateOrTransferToAnotherSiteExists();
		if(exists) {
			throw new ErrorOnPageException("Change Date(s)?/Transfer to Another Site link should not be available.");
		}
	}
	
	/**
	 * Cancel reservation to 'Cancel Reservation' page from 'Reservation Details' page.
	 * @param pay
	 */
	public void cancelResToCancelResPg() {
		UwpCancellationPage cancelPg = UwpCancellationPage.getInstance();
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();

		logger.info("Cancel reservation to 'Cancel Reservation' page from 'Reservation Details' page.");
		resDetailsPg.cancelReservation();
		cancelPg.waitLoading();
	}

	/**
	 * Cancel reservation to 'Cancellation Completed!' page from 'Cancel Reservation' page
	 * @param pay
	 */
	public void cancelResToCancellationCompletedPgFromCancelResPg(Payment pay) {
		UwpCancellationPage cancelPg = UwpCancellationPage.getInstance();
		UwpPayBalancePage payBalance = UwpPayBalancePage.getInstance();
		UwpCancellationConfirmationPage cancelConfirmPg = UwpCancellationConfirmationPage
		.getInstance();

		logger.info("Cancel reservation to 'Cancellation Completed!' page from 'Cancel Reservation' page");
		if(cancelPg.isRequiredPayBalance()) {
			payBalance.setupPayment(pay);
		}
		
		cancelPg.clickProceedWithCancellation();

		cancelConfirmPg.waitLoading();
		if (!cancelConfirmPg.cancelCompleted())
			throw new ItemNotFoundException("Cancellation can't be completed!");
	}
	
	public void failedToCancelResFromCancelResPg(Payment pay){
		UwpCancellationPage cancelPg = UwpCancellationPage.getInstance();
		UwpPayBalancePage payBalance = UwpPayBalancePage.getInstance();

		logger.info("Failed to Cancel reservation from 'Cancel Reservation' page");
		if(cancelPg.isRequiredPayBalance()) {
			payBalance.setupPayment(pay);
		}
		
		cancelPg.clickProceedWithCancellation();
		cancelPg.waitLoading();
	}
	
	/**
	 * this method will need to fill in payment info when reservation is free 
	 * and has cancellation transaction fee.
	 * Cancel a reservation from reservation details page. work flow starts at
	 * reservation details page and ends at cancellation confirmation page.
	 * @param pay - payment info
	 */
	public void cancelReservation(Payment pay) {
		logger.info("Cancel resevation to 'Cancellation Completed!' from 'Reservation Details'.");
		this.cancelResToCancelResPg(); 
		this.cancelResToCancellationCompletedPgFromCancelResPg(pay);
	}
	
	/**
	 * Cancel single or multiple reservations from account Panel page and ends at cancellation confirmation page
	 * @param resNums
	 * @param conteactCode
	 * @param status: Confirmed
	 * @param pay
	 */
	public void cancelReservation(String[] resNums, String conteactCode, String status, Payment pay){
		for(int i=0; i<resNums.length; i++){
			this.gotoResDetailFromAccount(resNums[i], conteactCode, status);
			this.cancelReservation(pay);
		}
	}
	
	public void cancelReservation(String[] resNums, String conteactCode, Payment pay){
		cancelReservation(resNums, conteactCode, "Confirmed", pay);
	}

	/**
	 * Go to My Reservation & Account page from header bar.
	 */
	public void gotoMyReservationsAccount() {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();

		logger.info("Goto my reservations account.");

		headerBar.gotoMyReservationsAccount();
		accountOVPg.waitLoading();
	}

	/**
	 * Go to Past Reservations page from account panel.
	 */
	public void gotoPastResPgFromAccountOverviewPg() {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpPastResListPage pastResPg = UwpPastResListPage.getInstance();
		logger.info("Go to past reservations page from account panel.");
		
		accountPanel.gotoPastReservations();
		pastResPg.waitLoading();
	}
	
	public void gotoPastResPgFromHomePg(){
		gotoCurrentReservationsPage();
		gotoPastResPgFromAccountOverviewPg();
	}
	/**
	 * Go to Past Reservations page from account panel.
	 */
	public void gotoRedeemableVouchersPg() {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpRedeemableVouchersListPage redeemableVoucherPg = UwpRedeemableVouchersListPage.getInstance();
		logger.info("Go to redeemable voucher page from account panel.");
		
		accountPanel.gotoRedeemableVouchers();
		redeemableVoucherPg.waitLoading();
	}
	
	/**
	 * Go to Current Reservations page from header bar, or order confirmation page, or pay 
	 * balance confirmation page.
	 */
	public void gotoCurrentReservationsPage() {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpCurrentResListPage currentResPg = UwpCurrentResListPage.getInstance();
		UwpConfirmationPage orderConfirmPg = UwpConfirmationPage.getInstance();
		UwpPayBalanceConfirmationPage payBalanceConfimPg = UwpPayBalanceConfirmationPage
		.getInstance();
		UwpCancellationConfirmationPage cancelConfirmPg = UwpCancellationConfirmationPage
		.getInstance();

		logger.info("Goto my reservations account.");

		if(orderConfirmPg.exists()) {
			//order confirmation and pay balance confirmation page may have same page mark;
			//use this to make sure go to current reservations page from pay balance confirm page.
			if(payBalanceConfimPg.isPayBalanceConfirmPg()) { 
				payBalanceConfimPg.clickContinueToCurrentRes();
			} else if(cancelConfirmPg.isCancelConfirmPg()) {
				cancelConfirmPg.gotoCurrentReservationsPg();
			} else {
				orderConfirmPg.gotoCurrentReservations();
			}
		} else {
			headerBar.gotoMyReservationsAccount();
			accountOVPg.waitLoading();
			accountPanel.gotoCurrentReservations();
		}
		currentResPg.waitLoading();
	}
	
	public void gotoDiscountPassesPage(){
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpDiscountPassesPage discountPassesPg = UwpDiscountPassesPage.getInstance();
		logger.info("Go to discount passes page from My Account panel");
		accountPanel.clickDiscountPasses();
		discountPassesPg.waitLoading();
	}
	
	public void gotoExpiredPassesPgFromActivePassesPg(){
		UwpActivePassesPage activePassesPg = UwpActivePassesPage.getInstance();
		UwpExpiredPassesPage expiredPassesPg = UwpExpiredPassesPage.getInstance();
		logger.info("Go to expired passes page from active passes page");
		activePassesPg.clickExpiredPasses();
		expiredPassesPg.waitLoading();
	}
	
	public void gotoActivePassesPgFromExpiredPasses(){
		UwpActivePassesPage activePassesPg = UwpActivePassesPage.getInstance();
		UwpExpiredPassesPage expiredPassesPg = UwpExpiredPassesPage.getInstance();
		logger.info("Go to active passes page from expired passes page");
		expiredPassesPg.clickActivePasses();
		activePassesPg.waitLoading();
	}
	
	/**
	 * Go to reservation details page from current reservation page.
	 * Start page: current reservation page
	 * End page: reservation details page
	 * @param orderNum
	 * @param contractCode
	 */
	public void gotoPastReservationDetailPage(String orderNum, String contractCode){
		UwpPastResListPage pastResPg = UwpPastResListPage.getInstance();
		UwpReservationDetailsPage resDetailsPage = UwpReservationDetailsPage.getInstance();
		
		logger.info("Go to past reservation details page from past reservation page.");
		pastResPg.gotoResDetails(orderNum, contractCode);
		resDetailsPage.waitLoading();
	}
	
	/**
	 * Go to reservation details page from current reservation page.
	 * Start page: current reservation page
	 * End page: reservation details page
	 * @param orderNum
	 * @param contractCode
	 */
	public void gotoReservationDetailPage(String orderNum, String contractCode){
		UwpCurrentResListPage currentResPg = UwpCurrentResListPage.getInstance();
		UwpReservationDetailsPage resDetailsPage = UwpReservationDetailsPage.getInstance();
		
		logger.info("Go to reservation details page from current reservation page.");
		currentResPg.gotoResDetails(orderNum, contractCode);
		resDetailsPage.waitLoading();
	}
	
	public void gotoReservationDetailsPgFromConfirmationPg(String orderNum){
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		logger.info("Go to reservation:"+orderNum+" details page from confirmation page.");
		UwpReservationDetailsPage resDetailsPage = UwpReservationDetailsPage.getInstance();
		confirmPg.clickReservationLink(orderNum);
		resDetailsPage.waitLoading();
	}
	
	/**
	 * Go to reservation details page from home page
	 * @param orderNum
	 * @param contractCode
	 * @param status: "Confirmed"
	 */
	public void gotoReservationDetailPageFromHomePage(String orderNum, String contractCode, String status){
		this.gotoMyReservationsAccount();
		this.gotoResDetailFromAccount(orderNum, contractCode, status);
	}
	/**
	 * Go to lottery applications list page
	 */
	public void gotoLotteryApplicationsListPageFromHeaderBar() {
		UwpHeaderBar header = UwpHeaderBar.getInstance();
		UwpAccountOverviewPage overviewPage = UwpAccountOverviewPage.getInstance();
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpCampingLotteryApplicationListPage lotteryAppPage = UwpCampingLotteryApplicationListPage.getInstance();
		
		logger.info("Go to 'Lottery Application List' page from header bar.");
		header.gotoMyReservationsAccount();
		overviewPage.waitLoading();
		accountPanel.gotoLotteryApplications();
		lotteryAppPage.waitLoading();
	}

	public void gotoLotteryApplicationDetailPage(String appNum, String contractCode, boolean isRecgov) {
		UwpCampingLotteryApplicationListPage listPage = UwpCampingLotteryApplicationListPage.getInstance();
		UwpCampingLotteryApplicationDetailsPage detailPage = UwpCampingLotteryApplicationDetailsPage.getInstance();
		UwpTicketLotteryApplicationDetailsPage ticketPg = UwpTicketLotteryApplicationDetailsPage.getInstance();
		
		logger.info("Go to 'Lottery Application Details' page from 'Lottery Application List' page.");
		if(!isRecgov){
			listPage.searchResNum(appNum);
		}
		listPage.clickSeeDetails(appNum, contractCode);
		browser.waitExists(detailPage, ticketPg);
	}
	
	public void gotoLotteryApplicationDetailPageFromHeaderBar(String appNum, String contractCode, boolean isRecgov){
		this.gotoLotteryApplicationsListPageFromHeaderBar();
		this.gotoLotteryApplicationDetailPage(appNum, contractCode, isRecgov);
	}
	
	public void gotoLotteryApplicationDetailPageFromHeaderBar(String appNum, String contractCode){
		this.gotoLotteryApplicationsListPageFromHeaderBar();
		this.gotoLotteryApplicationDetailPage(appNum, contractCode, true);
	}
	
	/**
	 * This method is used to accept permit lottery application.
	 * It starts with Lottery Application Detail page and ends with Change Completed page
	 * @param permit - Permit detail info
	 * @return - permit order number
	 */
	public String acceptPermitLottery(PermitInfo permit,Payment pay) {
		UwpPermitLotteryApplicationDetailsPage lotteryAppDetailPage = UwpPermitLotteryApplicationDetailsPage.getInstance();
		BwPermitOrderDetailsPage permitOrderDetailPage = BwPermitOrderDetailsPage.getInstance();
		BwChangeCompletePage changeCompletePage = BwChangeCompletePage.getInstance();
		UwpPayBalancePage balancePage=UwpPayBalancePage.getInstance();
		
		logger.info("Accept lottery application.");
		lotteryAppDetailPage.clickAcceptReservation();
		permitOrderDetailPage.waitLoading();
		permitOrderDetailPage.setupOrderDetails(permit);
		permitOrderDetailPage.clickContinueToShoppingCart();
		Object page=browser.waitExists(balancePage,changeCompletePage);
		
		if(page==balancePage){
			balancePage.setupPayment(pay);
			balancePage.clickPayBalance();
		}
		changeCompletePage.waitLoading();
		String toReturn = changeCompletePage.getOrderNum();
		
		return toReturn;
	}
	
	/**
	 * Change reservation date in shopping cart.
	 * @param newArrival
	 * @param maxLoop
	 * @return - the new date
	 */
	public String changeDateFromCartToCart(String newArrival, int maxLoop) {
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpOrderDetailsPage orderDetailsPg = UwpOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Change date from cart.");
		shoppingCart.gotoOrderDetailFirstItem();
		orderDetailsPg.waitLoading();
		orderDetailsPg.clickChangeDates();
		prdDetailPg.waitLoading();

		String date = prdDetailPg.bookdaysAfterGivenDate(newArrival,maxLoop);
		orderDetailsPg.waitLoading();
		orderDetailsPg.gotoShoppingCart();
		shoppingCart.waitLoading();

		return date;
	}

	/**
	 * This method goes to fill in the payment info when pay balance
	 * it starts at reservation details page, ends at pay balance confirm page
	 * @param pay - payment info
	 */
	public void payBalance(Payment pay) {
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		UwpPayBalancePage payBalance = UwpPayBalancePage.getInstance();
		UwpPayBalanceConfirmationPage payBalanceConfimPg = UwpPayBalanceConfirmationPage
		.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();
		logger.info("Pay balance and verify success.");
		resDetailsPg.clickOnPayBalance();
		payBalance.waitLoading();

		payBalance.setupPayment(pay);
		payBalance.clickPayBalance();

		accountPanelPg.waitLoading();
		payBalanceConfimPg.verifyPayBalanceSuccess();
	}

	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and ends at the confirmation page
	 * @param pay -  payment information
	 * @return - the reservation number
	 */
	public String checkOutShoppingCart(Payment pay) {
		return checkOutShoppingCart(pay, false);
	}

	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and ends at the confirmation page
	 * @param pay -  payment information
	 * @param isMinimum -  IS Fully or Minimum payment ?
	 * @return - the reservation number
	 */
	public String checkOutShoppingCart(Payment pay, boolean isMinimum) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpPayBalanceConfirmationPage payBalanceConfimPg = UwpPayBalanceConfirmationPage
		.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		UwpClaimFreeGiftPage claimFreegfPg = UwpClaimFreeGiftPage.getInstance();

		logger.info("Check out shopping cart.");
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();

		if (isMinimum) {
			checkoutPg.selectMinimumPayment();
		}
		checkoutPg.setupPayment(pay);
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();
		Object obj = browser.waitExists(claimFreegfPg,confirmPg);
		//Add claim free gift page.
		if(obj == claimFreegfPg){
			claimFreegfPg.clickNoThanks();
		}
		confirmPg.waitLoading();
		confirmPg.verifySuccess();

		if (isMinimum) {
			logger.info("--- verify the pay balance message displays.");
			payBalanceConfimPg.verifyBalanceMsg();
		}

		String resID = confirmPg.getAllResNo();
		return resID;
	}

	/**
	 * Check out a free reservation from shopping cart to confirmation page.
	 * @return - reservation id
	 */
	public String checkOutFreeResCart() {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		logger.info("Check out a free reservation cart.");
		shoppingCart.clickCheckOutShoppingCart();

		checkoutPg.waitLoading();
		int hiddenPayment=checkoutPg.getHiddenPaymentFields();

		if(hiddenPayment<1) {
			throw new ErrorOnPageException("No free res payment field. ");
		}else {
			logger.info(" --- " + hiddenPayment + " free reservation(s).");
		}

		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();

		confirmPg.waitLoading();
		confirmPg.verifySuccess();

		String resID = confirmPg.getAllResNo();
		return resID;
	}
	
	
	/**
	 * Check out shopping cart to confirmation page. The workflow starts from Shopping cart page and ends at the home page
	 * @param pay -  payment information
	 * @return - the reservation number
	 */
	public String checkOutCartToConfirmationPage(Payment pay) {
		return checkOutCartToConfirmationPage(pay, false);
	}
	
	public void gotoCheckoutPgFromShoppingCartPg(){
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		
		logger.info("Go to check out page from shopping cart page.");
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();
	}
	
	/**
	 * Check out shopping cart to confirmation page. The workflow starts from Shopping cart page and ends at the home page
	 * @param pay -  payment information
	 * @param isCopy - whether or not to click on Copy card info from previous link
	 * @return - the reservation number
	 */
	public String checkOutCartToConfirmationPage(Payment pay, boolean isCopy) {
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		UwpActiveAdvantageTrialOfferPage advantageOfferPg = UwpActiveAdvantageTrialOfferPage.getInstance();

		
		logger.info("Check out cart to confirmation page.");
		gotoCheckoutPgFromShoppingCartPg();
		checkoutPg.setupPayment(pay);
		if(isCopy) {
			checkoutPg.clickCopyPreviousCardInfo();//copy the previous card info for second payment section
		}
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();

		Object page=browser.waitExists(confirmPg,advantageOfferPg);
		if(page==advantageOfferPg) {
			advantageOfferPg.clickNoThanks();
			confirmPg.waitLoading();
		}

		confirmPg.verifySuccess();

		String resID = confirmPg.getAllResNo();
		return resID;
	}
	
	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and ends at the home page
	 * @param pay -  payment information
	 * @param isCopy - whether or not to click on Copy card info from previous link
	 * @return - the reservation number
	 */
	public String checkOutCart(Payment pay, boolean isCopy) {
		logger.info("Check out cart.");
		String resID=checkOutCartToConfirmationPage(pay,isCopy);
		gotoHomePgFromProcessCartCompletedPg();
		return resID;//exportReservNo(fees2);
	}
	
	public void gotoHomePgFromProcessCartCompletedPg(){
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		UwpHomePage hmPg = UwpHomePage.getInstance();
	    logger.info("Go to home page from process cart completed page");
		confirmPg.clickContinueToRAHome();
		hmPg.waitLoading();
	}
	
	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and ends at the home page
	 * @param pay -  payment information
	 * @return - the reservation number
	 */
	public String checkOutCart(Payment pay) {
		return checkOutCart(pay, false);
	}

	/**
	 * Check out shopping cart. The workflow starts from Shopping cart page and ends at the home page
	 * @param pay -  array of payment information
	 * @return - the reservation number
	 */
	public String checkOutCart(Payment[] pays) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		UwpHomePage hmPg = UwpHomePage.getInstance();

		logger.info("Check out cart.");

		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();
		checkoutPg.setupPayments(pays);
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();

		confirmPg.waitLoading();
		confirmPg.verifySuccess();

		String resID = confirmPg.getAllResNo();

		confirmPg.clickContinueToRAHome();
		hmPg.waitLoading();

		return resID;//exportReservNo(fees2);
	}

	/**
	 * Check out tour shopping cart with single reservation number
	 * @param pay - the payment information
	 * @return - the tour reservation number
	 */
	public String checkOutTourCart(Payment pay) {
		return this.checkOutTourCartWithMultipleResNum(pay)[0];
	}
	
	/**
	 * Check out tour shopping cart with multiple reservation number 
	 * from page: UwpTourShoppingCartPage
	 * to page: tourConfirmationPg
	 * @param pay - the payment information
	 * @return - the tour reservation number
	 */
	public String[] checkOutTourCartWithMultipleResNum(Payment pay) {
		UwpTourShoppingCartPage tourShoppingCartPg = UwpTourShoppingCartPage
		.getInstance();
		UwpTourCheckoutPage tourCheckoutPg = UwpTourCheckoutPage.getInstance();
		UwpTourConfirmationPage tourConfirmationPg = UwpTourConfirmationPage
		.getInstance();
		UwpActiveAdvantageTrialOfferPage advantageOfferPg = UwpActiveAdvantageTrialOfferPage.getInstance();
		
		logger.info("Check out tour cart.");
		pay.amount = tourShoppingCartPg.getTotalAmount();
		tourShoppingCartPg.clickCheckOutCart();
		tourCheckoutPg.waitLoading();

		tourCheckoutPg.completePurchase(pay);
		Object page=browser.waitExists(tourConfirmationPg,advantageOfferPg);
		if(page==advantageOfferPg) {
			advantageOfferPg.clickNoThanks();
			tourConfirmationPg.waitLoading();
		}

		String totalConfirmation = tourConfirmationPg.getAmountPaid();
		if (!totalConfirmation.trim().equalsIgnoreCase(pay.amount))
			throw new ItemNotFoundException("The amount in payment page "
					+ pay.amount
					+ " does not match the amount in confirmation page "
					+ totalConfirmation);
		
		String[] resNums = tourConfirmationPg.getResNums();
		
		tourConfirmationPg.verifySuccess();

		return resNums;
	}


	/**
	 * Check out a free tour reservation from shopping cart to confirmation page.
	 * @return - reservation id
	 */
	public String checkOutFreeTourCart() {
		UwpTourShoppingCartPage tourShoppingCartPg = UwpTourShoppingCartPage
		.getInstance();
		UwpTourCheckoutPage tourCheckoutPg = UwpTourCheckoutPage.getInstance();
		UwpTourConfirmationPage tourConfirmationPg = UwpTourConfirmationPage
		.getInstance();

		logger.info("Check out a free tour reservation cart.");
		tourShoppingCartPg.clickCheckOutCart();

		tourCheckoutPg.waitLoading();
		int hiddenPayment=tourCheckoutPg.getHiddenPaymentFields();

		if(hiddenPayment<1) {
			throw new ErrorOnPageException("No free tour res payment field. ");
		}else {
			logger.info(" --- " + hiddenPayment + " free reservation(s).");
		}

		tourCheckoutPg.selectAcknowlegeAcceptedCheckBox();
		tourCheckoutPg.clickCheckOut();

		tourConfirmationPg.waitLoading();
		tourConfirmationPg.verifySuccess();

		String resID = tourConfirmationPg.getResNum();
		return resID;
	}

	/**
	 * Create a new customer account
	 * @param cust - the customer information
	 */
	public void createAccount(Customer cust) {
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		UwpCreateAccountPage createAccountPg = UwpCreateAccountPage.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpCreateCampingClubProfilePage campingProfilePg = UwpCreateCampingClubProfilePage
		.getInstance();

		logger.info("Create a new account.");
		memberStatusBar.signUp();
		Object page = browser.waitExists(createAccountPg, signInOrUpPg); // update from 3.04.01 due to RA Sign Up flow changed
		if (page == signInOrUpPg) {
			signInOrUpPg.clickContinueToSignUp();
			signInOrUpPg.waitLoading();
			signInOrUpPg.createNewMemberAccount(cust);	
		} else {
			createAccountPg.createNewMemberAccount(cust);
		}

		page = browser.waitExists(campingProfilePg, accountOVPg);
		if (page == campingProfilePg) {
			campingProfilePg.clickSkip();
			accountOVPg.waitLoading();
		}

		accountOVPg.verifyAccountOverview(cust.email);
	}

	/**
	 * Go to reservation details page from confirmation page.
	 * @param index - the index of the reservations
	 */
	public void gotoResDetailsFromConfirm(int index) {
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		logger.info("Goto reservation details from confirmation.");
		confirmPg.waitLoading();
		confirmPg.gotoResDetail(index);

		resDetailsPg.waitLoading();
	}

	public void gotoCurrentResListPg(){
		UwpCurrentResListPage resListPg = UwpCurrentResListPage.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		logger.info("Go to current reservation list page from account overview page");
		accountOVPg.gotoCurrentReservationPg();
		resListPg.waitLoading();
	}

	public void gotoResDetailFromCurrentResListPg(String resNo, String contract,String status){
		UwpCurrentResListPage resListPg = UwpCurrentResListPage.getInstance();
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();

		logger.info("Goto Reservation details from current reservation list page.");
		resListPg.verifyStatus(resNo, contract, status);
		resListPg.gotoResDetails(resNo, contract);
		resDetailsPg.waitLoading();
	}
	
	/**
	 * Go to reservation details page from My Reservation & Account page.
	 * @param resNo - the reservation number
	 * @param contract - the brief contract name
	 * @param status - reservation status to be verified
	 */
	public void gotoResDetailFromAccount(String resNo, String contract,String status) {
		gotoCurrentResListPg();
		gotoResDetailFromCurrentResListPg(resNo, contract, status);
	}

	/**
	 * Go to tour reservation details page from confirmation page.
	 */
	public void gotoTourReservationDetailFromConfirm() {
		UwpTourConfirmationPage tourConfirmationPg = UwpTourConfirmationPage.getInstance();
		UwpTourReservationDetailsPage tourResvDetailsPg = UwpTourReservationDetailsPage
		.getInstance();

		logger.info("Goto tour reservation details from confirmation.");

		tourConfirmationPg.clickReservationNum();
		tourResvDetailsPg.waitLoading();
	}
	
	public void gotoTourReservationDetailFromConfirmPage(String resNum) {
		UwpTourConfirmationPage tourConfirmationPg = UwpTourConfirmationPage.getInstance();
		UwpTourReservationDetailsPage tourResvDetailsPg = UwpTourReservationDetailsPage
		.getInstance();

		logger.info("Goto tour reservation details from confirmation clicking resNum - "+resNum);

		tourConfirmationPg.clickReservationNum(resNum);
		tourResvDetailsPg.waitLoading();
	}

	/**
	 * Go to tour reservation details page from My Reservation & Account page.
	 * @param tour - the existing tour information to be verified
	 */
	public void gotoTourDetailsFromAccount(TicketInfo tour) {
		UwpTourReservationDetailsPage tourResvDetailsPg = UwpTourReservationDetailsPage
		.getInstance();
		UwpCurrentResListPage resListPg = UwpCurrentResListPage.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();

		logger.info("Goto tour details from account.");
		accountPanelPg.gotoCurrentReservations();
		resListPg.waitLoading();

		resListPg.gotoTourList();
		resListPg.waitLoading();
		resListPg.verifyStatus(tour.resId, tour.contractCode, tour.status);

		resListPg.gotoTourOrderDetails(tour.resId, tour.contractCode);

		tourResvDetailsPg.waitLoading();
	}

	/**
	 * Remove items from shopping cart, item list link 1:3, stands for remove
	 * first and third items in cart.
	 * @param itemList
	 */
	public void removeItemFromCart(String itemList) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Remove item " + itemList + " from cart.");

		int numOfItems = shoppingCart.numberOfItems();
		shoppingCart.removeItems(itemList);

		shoppingCart.waitLoading();
		if (shoppingCart.numberOfItems() != (numOfItems - (itemList.split(":")).length))
			throw new ItemNotFoundException(
			"Number of remaining items in the cart is not correct!");
	}

	public String bookSiteIntoCart(BookingData bd, OrderDetails od, boolean isDiscount) {
		UwpProductListCommonPage siteListPage = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage dateRangeAvailPage = UwpDateRangeAvailabilityPage.getInstance();
		UwpOrderDetailsPage orderDetailsPage = UwpOrderDetailsPage.getInstance();
		UwpSocialSecurityNumberRequiredPage ssnPg=UwpSocialSecurityNumberRequiredPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		
		//Quentin[20130922] totally new method
		logger.info("Book site into shopping cart.");
		Object page = this.bookParkToSiteListPg(bd);
		if(page == dateRangeAvailPage) {
			this.gotoSiteDetailsPageFromDateRangeAvailabilityPage(bd.siteNo, bd.maxLoop);
		} else if(page == siteListPage) {
			this.gotoSiteDetailsPageFromSiteList(bd.siteNo);
		}
		
		String arrivalDate = prdDetailPg.bookAnyDays(bd.maxLoop);
		page=browser.waitExists(orderDetailsPage, ssnPg);
		if(page== ssnPg) {
			ssnPg.setSocialSecurityNumber("078051120");
			ssnPg.clickContinue();
			orderDetailsPage.waitLoading();
		}
		this.fillInOrderDetails(od, bd.contractCode, isDiscount);
		
		return arrivalDate;
	}
	
//	/**
//	 * This method goes throw the work flows of booking a site into shopping cart. 
//	 * The work flow starts from head bar and ends at shopping cart page.
//	 * @param bd - booking site criteria information
//	 * @param od - order details information
//	 * @param isDiscount - whether the order with discount or not
//	 */
//	public String bookSiteIntoCart(BookingData bd, OrderDetails od,boolean isDiscount) {
//		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
//		UwpCampsiteDetailsPage campsiteDetailPg = UwpCampsiteDetailsPage.getInstance();
//		UwpOrderDetailsPage uwpOrderDetails = UwpOrderDetailsPage.getInstance();
//		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
//		UwpSocialSecurityNumberRequiredPage ssnPg=UwpSocialSecurityNumberRequiredPage.getInstance();
//
//		logger.info("Book site into cart.");
//		headerBar.gotoFindCampSpot();
//
//		searchPanel.waitExists();
//		Browser.sleep(5);
//		searchPanel.searchSite(bd);
//
//		campsiteDetailPg.waitExists();
//		String arriveDate = campsiteDetailPg.bookAnyDays(bd.maxLoop);
//
//		Object page=browser.waitExists(uwpOrderDetails,ssnPg);
//		if(page== ssnPg) {
//			ssnPg.setSocialSecurityNumber("078051120");
//			ssnPg.clickContinue();
//			uwpOrderDetails.waitExists();
//
//		}
//		uwpOrderDetails.setOrderDetails(od, bd.contractCode,isDiscount);
//		uwpOrderDetails.clickContinueToShoppingCart();
//
//		shoppingCart.waitExists();
//
//		return arriveDate;
//	}

	/**
	 * Update email address in account page.
	 * @param newEmail
	 * @param password
	 */
	public void updateEmail(String newEmail, String password) {
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpUpdateEmailPage updateEmlPg = UwpUpdateEmailPage.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();
		UwpHomePage hmPg=UwpHomePage.getInstance();

		logger.info("Update email to " + newEmail);
		Object page=browser.waitExists(accountPanelPg,hmPg);
		if(page==hmPg) {
			hmPg.gotoAccountPage();
			accountPanelPg.waitLoading();
		}
		accountPanelPg.gotoUpdateEmail();

		updateEmlPg.waitLoading();
		updateEmlPg.updateEmail(newEmail, password);

		accountOVPg.waitLoading();
		accountOVPg.verifyAccountOverview(newEmail);
	}

	/**
	 * Update order details from shopping cart page.
	 * @param ord - the new information of order
	 */
	public void updateOrderDetail(OrderDetails ord, String schema) {
		UwpOrderDetailsPage orderDetailsPg = UwpOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Update order details.");
		shoppingCart.gotoOrderDetailFirstItem();

		orderDetailsPg.waitLoading();
		orderDetailsPg.setOrderDetails(ord, schema);

		shoppingCart.waitLoading();
		shoppingCart.gotoOrderDetailFirstItem();

		orderDetailsPg.waitLoading();
		orderDetailsPg.gotoShoppingCart();

		shoppingCart.waitLoading();
	}

	/**
	 * Update password in account page.
	 * @param password
	 * @param newPwd
	 */
	public void updatePassword(String password, String newPwd) {
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();
		UwpUpdatePasswordPage updatePwdPg = UwpUpdatePasswordPage.getInstance();

		logger.info("Update password.");
		accountPanelPg.gotoUpdatePassword();

		updatePwdPg.waitLoading();
		updatePwdPg.updatePassword(password, newPwd);

		accountOVPg.waitLoading();
	}

	/**
	 * Update customer profile in account page.
	 * @param mp - new profile information of the customer
	 */
	public void updateProfile(Customer mp) {
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();
		UwpUpdateProfilePage updateProfilePg = UwpUpdateProfilePage.getInstance();

		logger.info("Update profile.");
		accountOVPg.verifyAccountOverview(mp.email);

		accountPanelPg.waitLoading();
		accountPanelPg.gotoUpdateProfile();

		updateProfilePg.waitLoading();
		updateProfilePg.updateProfile(mp);

		accountOVPg.waitLoading();
		accountOVPg.verifyAccountOverview(mp.email);

		accountPanelPg.waitLoading();
		accountPanelPg.gotoUpdateProfile();

		updateProfilePg.waitLoading();
		updateProfilePg.verifyProfile(mp);
	}

	/**
	 * Update reservation details with default values for equip type and equip length.
	 */
	public void updateReservationDetails() {
		updateResDetailsFromResDetailsPg(null, null);
	}

	/**
	 * Update reservation details with given values for equip type and equip length.
	 * Start page: Reservation Details
	 * End page: Change Reservation Details
	 * @param equipType
	 * @param equipLength
	 */
	public void updateResDetailsFromResDetailsPg(String equipType, String equipLength) {
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
		
		logger.info("Go to 'Update reservation details'page to update reservation details.");
		resDetailsPg.clickChangeReservationLink();

		updateResDetailPg.waitLoading();
		this.updateReservationDetails(equipType, equipLength);
		//		this.updateReservationDetails();
		//		updateResDetailPg.setOrderDetails(equipType, equipLength);
	}
	
	/**
	 * Update reservation details with given values for equip type and equip length.
	 * Start page: Change Reservation Details
	 * End page: Change Reservation Details
	 * @param equipType
	 * @param equipLength
	 */
	public void updateReservationDetails(String equipType, String equipLength) {
		this.updateReservationDetails(equipType, equipLength, null);
	}
	
	/**
	 * Go to 'Change Reservation Details' page from 'Update Reservation Details' page
	 * @param equipType
	 * @param equipLength
	 */
	public void gotoChangeResDetailsPgFromUpdateResDetailsPg(String equipType, String equipLength) {
		UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
		UwpChangeReservationConfirmPage confirmPg = UwpChangeReservationConfirmPage.getInstance();
		
		logger.info("Go to 'Change Reservation Details' page from 'Update Reservation Details' page.");
		updateResDetailPg.setEquipment(equipType, equipLength, StringUtil.EMPTY, StringUtil.EMPTY);
		updateResDetailPg.selectCheckBoxAgreementAccepted();
		updateResDetailPg.clickConfirmPermitRes();
		if(updateResDetailPg.exists())
			updateResDetailPg.clickConfirmPermitRes();
		confirmPg.waitLoading();
	}
	
	public BigDecimal finishUpdateResFromChangeResDetailsPg(Payment pay){
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		UwpChangeReservationConfirmPage confirmPg = UwpChangeReservationConfirmPage.getInstance();
        BigDecimal minimumPaymentAmount = BigDecimal.ZERO;
        
		logger.info("Finish update reservation from 'Change Reservation Details' page.");
		
		if(confirmPg.isMinimumPaymentExist()){
			minimumPaymentAmount = confirmPg.getMinimumPaymentAmount();
		}
		if(confirmPg.checkPaymentInfomationExist()){
			confirmPg.setupPayment(pay);
		}
		
		confirmPg.clickConfirm();
		resDetailsPg.waitLoading();
		
		logger.info("Minimum payment amount:"+minimumPaymentAmount);
		return minimumPaymentAmount;
	}
	
	public BigDecimal updateReservationDetails(String equipType, String equipLength, Payment pay) {
		this.gotoChangeResDetailsPgFromUpdateResDetailsPg(equipType, equipLength);
		return this.finishUpdateResFromChangeResDetailsPg(pay);
	}

	/**
	 * Execute telnet command to do import for CRS contracts.
	 * @param contract
	 */
	public void execTelnetCmdForCRS(String contract) {
		String script = TestProperty.getProperty("crs.import."+contract.toUpperCase());
		String ip = TestProperty.getProperty("crs.import.IP");
		String user = TestProperty.getProperty("crs.user");
		String pw = TestProperty.getProperty("crs.pwd");

		logger.info("Execute telnet command for CRS " + contract);
		com.activenetwork.qa.testapi.util.Telnet telnet = new com.activenetwork.qa.testapi.util.Telnet();

		telnet.connect(ip);
		telnet.login(user, pw);
		telnet.sendCommand(script);
	}

	/**
	 * Book a site into shopping cart for KOA contract with default order information both in QA and Production
	 * @param bd - booking information
	 * @param isProduction - whether the KOA site is under production environment
	 * @return - arrival date
	 */
	public String bookSiteIntoCartForKOA(BookingData bd) {
		return bookSiteIntoCartForKOA(bd, new OrderDetails());
	}

	/**
	 * Book a site into shopping cart for KOA contract in QA or Production environment
	 * @param bd - booking information
	 * @param od - order information
	 * @param isProduction - whether the KOA site is under production environment
	 * @return - arrival date
	 */
	public String bookSiteIntoCartForKOA(BookingData bd, OrderDetails od) {
		// updated by Nicole, Sep 22,2013
		// Unified search
//		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpKOACampsiteDetailsPage campsiteDetailPg = UwpKOACampsiteDetailsPage.getInstance();
		UwpKOAOrderDetailsPage koaOrderDetails = UwpKOAOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Book site into cart for KOA.");
//		headerBar.gotoFindCampSpot();
//
//		searchPanel.waitExists();
//		searchPanel.searchSite(bd);
//
//		campsiteDetailPg.waitExists();
//		campsiteDetailPg.fillBookingData(bd);
//		String arriveDate = campsiteDetailPg.bookAnyAvailableDates(90);
//		DataBaseFunctions.setValueFromQAAutomationTable("last_koa_date", arriveDate);
		
		this.bookParkToSiteListPg(bd);
//		this.bookSiteToOrderDetailPg(bd);
		this.bookSiteToSiteDetails(bd);
		campsiteDetailPg.waitLoading();
		campsiteDetailPg.fillBookingData(bd);
		String arriveDate = campsiteDetailPg.bookAnyAvailableDates(90);
		DataBaseFunctions.setValueFromQAAutomationTable("last_koa_date", arriveDate);
		koaOrderDetails.waitLoading();
		koaOrderDetails.fillOrderDetails(od);
		koaOrderDetails.gotoShoppingCart();

		shoppingCart.waitLoading();

		return arriveDate;
	}

	/**
	 * Book tour into "Tour Order Details" or "Shopping cart"page from "UwpHeaderBar" or "UnifiedSearchPanel"
	 * @param bd
	 * @param isCombo ---true: the tout is combo tour
	 * @param isGroupTicket  ---true: click "Swich to Group Sales" link in the top of "Find Tours" search panel.
	 *                       ---false: click "Switch to Public Sales" in the top of "Find Tours" search panel
	 * @return
	 */
	
	public String bookTourIntoTourSearchPg(TicketInfo bd) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpTourParkListPage tourParkList = UwpTourParkListPage.getInstance();
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		UwpParkListCommonPage parkListPage=UwpParkListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();
		logger.info("Book tour into 'Tour search' page.");

//		if((MiscFunctions.isRECEnv() && MiscFunctions.isRECUnifiedSearchOpen()) || 
//				(MiscFunctions.isRAEnv() && MiscFunctions.isRAUnifiedSearchOpen()) ||
//				(!MiscFunctions.isRECEnv() && !MiscFunctions.isRAEnv() && MiscFunctions.isPLWUnifiedSearchOpen())){
		if(AwoUtil.isUnifiedSearch()){
			this.gotoUnifiedSearchPanel(false);
		} else headerBar.gotoTourParkList();
		
//		if(!bd.isUnifiedSearch){
//			headerBar.gotoTourParkList();			
//		}else{
////			if(!unifiedSearchPanel.exists()){
////			this.gotoHomePage();
////		}
//			this.gotoUnifiedSearchPanel(false);// Sara[08/26/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
//		}

		Object page=browser.waitExists(tourSearch, unifiedSearchPanel, tourParkList);
		String tourPark="";
		if(tourParkList==page){
			tourPark = tourParkList.gotoTourParkDetails(bd.park);
		}else if(unifiedSearchPanel==page){
			unifiedSearchPanel.setupTourSearchCriteria(bd);
			unifiedSearchPanel.clickSearch();
			page=browser.waitExists(suggestionPage,parkListPage);
			if(page==suggestionPage){
				if(StringUtil.notEmpty(bd.parkId) && StringUtil.notEmpty(bd.contractCode)){
					suggestionPage.clickFacilitySuggestion(bd.parkId, bd.contractCode);
				}else if (StringUtil.notEmpty(bd.park)){
					suggestionPage.clickFacilitySuggestion(bd.park);
				}else suggestionPage.clickFacilitySuggestion(bd.whereTextValue);
				
				parkListPage.waitLoading();
			}
			parkListPage.waitLoading();
			parkListPage.clickFacility(bd.park);
			tourPark=bd.park;
		}

		tourSearch.waitLoading();
		return tourPark;
	}
	
	public TicketInfo bookTourIntoTourOrderDetailsPg(TicketInfo bd, boolean isCombo, boolean isGroupTicket) {
		String tourPark = this.bookTourIntoTourSearchPg(bd);
		
		logger.info("Book "+(isCombo?"Combo":"")+" tour into 'Tour Order Details' page from 'Tour Search' page.");
       return this.bookTourIntoTourOrderDetailsPgFromTourSearchPanel(tourPark, bd, isCombo, isGroupTicket);
	}
	
	public void boolTourIntoTourDetailsPg(TicketInfo bd, boolean isGroupTicket) {
		this.bookTourIntoTourSearchPg(bd);
		this.bookTourIntoTourDetailsPgFromTourSearchPanel(bd, isGroupTicket);
	}
	
	public void setUpDeliveryMethod(String parkID,String method_ID,String ticketCat_ID,String salsChannel_ID,String occurence_ID,String schema){
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Set up delivery method...");
		
		String insertSql="insert into D_TICKET_DELIVERY_METHOD_CFG (ID, LOC_ID, DELIVERY_METHOD_ID, TICKET_CATEGORY_ID, SALE_CHANNEL_ID, TRANSACTION_OCCURRENCE_ID) " +
				"values " +
				"(get_sequence('D_TICKET_DELIVERY_METHOD_CFG'),"+parkID+","+method_ID+","+ticketCat_ID+","+salsChannel_ID+","+occurence_ID+")";
		if(db.executeUpdate(insertSql)<1){
			throw new ActionFailedException("Failed set up Delivery Method");
		}
		
	}
	public void deleteAllDeliveryMethodsForFacility(String parkID, String schema){
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String deleteSql="DELETE  FROM D_TICKET_DELIVERY_METHOD_CFG  Where LOC_ID="+parkID;
		String querySql="SELECT count(*) FROM D_TICKET_DELIVERY_METHOD_CFG  Where LOC_ID="+parkID;
		logger.info("Delete all delivery method for park which of id:"+parkID);
		if(Integer.parseInt(db.executeQuery(querySql, "count(*)", 0))>0){
			db.executeUpdate(deleteSql);
		}
	}
	
	/**
	 * Book (Combo)? tour into 'Tour Details' page from 'Tour Search Panel'
	 * @param isGroupTicket
	 */
	public String bookTourIntoTourDetailsPgFromTourSearchPanel(TicketInfo bd, boolean isGroupTicket){
		int period = 1;//James: change to 1 day. in regression test, the inventory should be always available.
		return bookTourIntoTourDetailsPgFromTourSearchPanel(bd,isGroupTicket, period);
	}
	
	public String bookTourIntoTourDetailsPgFromTourSearchPanel(TicketInfo bd, boolean isGroupTicket, int searchPeriod){
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpUpdateOrganizationProfile orgProfile = UwpUpdateOrganizationProfile.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();

		logger.info("Book tour into 'Tour Details' page from 'Tour Search Panel'.");

		if(isGroupTicket && !tourSearch.isInGroupSales()) {
			tourSearch.clickSwitchToGroupSales();
			Object page = browser.waitExists(orgProfile, tourSearch);
			if(page == orgProfile){
				orgProfile.updateOrganizationProfile("School", "ReserveAmerica");
				orgProfile.clickSaveChanges();
			}
		} else if(!isGroupTicket && tourSearch.isInGroupSales()) {
			tourSearch.clickSwitchToPublicSales();
		}

		tourSearch.waitLoading();
		tourSearch.setSearchCriteria(bd);

		String tourDate = tourSearch.searchTour(searchPeriod);

		tourDetails.waitLoading();
		return tourDate;
	}
	
    /**
     * "Make More Reservation" to "Tour List" page from "Tour Order" or "Shopping Cart" page, Then search tour to "Tour Details" page
     * @param startPage: "UwpTourOrderDetailsPage", "UwpShoppingCartPage" or 'UwpShoppingCartPage'
     * @param bd
     * @param isGroupTicket
     */
	public void makeMoreReservationsToTourDetailsPg(Page startPage, TicketInfo bd, boolean isGroupTicket){
		 UwpTourListPage tourListPg = UwpTourListPage.getInstance();
		 UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();
		 UwpShoppingCartPage tourShoppingCartPg = UwpShoppingCartPage.getInstance();
		 UwpTourShoppingCartPage tourShoppingCartPage=UwpTourShoppingCartPage.getInstance();
		 
		logger.info("'Make More Reservation' to 'Tour Details' page from '"+startPage+"' page.");
		if(startPage==tourOrderDetailsPg){
			tourOrderDetailsPg.clickMakeMoreReservationsLink();	
		}else if(startPage==tourShoppingCartPg){
			if(!tourShoppingCartPg.checkContinueShoppingExist()){
				tourShoppingCartPg.clickMakeMoreReservationsLink();
			} else {
				tourShoppingCartPg.clickContinueShoppinLink();
			}
		}else if(startPage==UwpTourShoppingCartPage.getInstance()){
			if(!tourShoppingCartPage.checkContinueShoppingExist()){
				tourShoppingCartPage.clickMakeMoreReservationsLink();
			} else {
				tourShoppingCartPage.clickContinueShoppinLink();
			}
		}else{
			throw new ErrorOnDataException("Unknown page:"+startPage.getName());
		}
		tourListPg.waitLoading();
		
		this.bookTourIntoTourDetailsPgFromTourSearchPanel(bd, isGroupTicket);
	}
	
    /**
     * "Make More Reservation" to "Tour List" page from "Tour Order" or "Shopping Cart" page, Then search tour to "Tour Order Details" page
     * @param startPage: "Tour Order" or "Shopping Cart" page
     * @param bd
     * @param isCombo
     * @param isGroupTicket
     */
	public void makeMoreReservationsToTourOrderDetailsPg(Page startPage, TicketInfo bd, boolean isCombo, boolean isGroupTicket){
		 UwpTourListPage tourListPg = UwpTourListPage.getInstance();
		 UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();
		 UwpShoppingCartPage tourShoppingCartPg = UwpShoppingCartPage.getInstance();
		
		logger.info("'Make More Reservation' to 'Tour Order Details' page from '"+startPage+"' page.");
		if(startPage==tourOrderDetailsPg){
			tourOrderDetailsPg.clickMakeMoreReservationsLink();	
		}else if(startPage==tourShoppingCartPg){
			if(!tourShoppingCartPg.checkContinueShoppingExist()){
				tourShoppingCartPg.clickMakeMoreReservationsLink();
			} else {
				tourShoppingCartPg.clickContinueShoppinLink();
			}
		}
		tourListPg.waitLoading();
		
		this.bookTourIntoTourOrderDetailsPgFromTourSearchPanel(bd, isCombo, isGroupTicket);
	}

	/**
	 * 'Continue shopping' to 'home' page from 'Shopping Cart' page.
	 */
	public void makeMoreReservations(){
		UwpShoppingCartPage tourShoppingCartPg = UwpShoppingCartPage.getInstance();
		UwpHomePage hmPg = UwpHomePage.getInstance();
		logger.info("'Continue shopping' to 'home' page from 'Shopping Cart' page.");
		tourShoppingCartPg.clickContinueShoppinLink();
		hmPg.waitLoading();
	}
	
	/**
	 * Book tour into "Tour Order Details" from "Tour Search Panel" 
	 * @param tourPark: use for return tour park name
	 * @param bd
	 * @param isCombo
	 * @param isGroupTicket
	 * @return
	 */
	public TicketInfo bookTourIntoTourOrderDetailsPgFromTourSearchPanel(String tourPark, TicketInfo bd, boolean isCombo, boolean isGroupTicket) {
		String tourDate = this.bookTourIntoTourDetailsPgFromTourSearchPanel(bd, isGroupTicket);
		return this.bookTourIntoTourOrderDetailsPgFromTourDetailsPg(tourDate, tourPark, bd, isCombo);
	}
	
	public TicketInfo bookTourIntoCartFromTourDetailsPg(TicketInfo bd){
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage.getInstance();
		UwpTourOrderDetailsPage tpDetailPg=UwpTourOrderDetailsPage.getInstance();
		
		logger.info("Book "+(bd.isComboTour?"Combo":"")+" tour into 'Tour Details' page from 'Tour Details Page'.");

		if (bd.isComboTour) {
			tourDetails.bookComboTour(bd);
		} else {
			if(null!= bd.ticketTypes && bd.ticketTypes.size()>0 && null!=bd.ticketTypeNums &&  bd.ticketTypeNums.size()>0){
				tourDetails.bookTourByGivenTicketType(bd.ticketTypeNums, bd.ticketTimeSeq, bd.ticketTypes, bd.timeSlot, bd.deliveryMethod);
			}else{
				bd = tourDetails.bookTourByGivenTicketType(bd);
				
				/*bd.park = bd.park.replaceAll(" ", "");
				bd.tourDate = bd.tourDate.replaceAll(" ", "");*/ // should not change status in keyword
			}
		}

		Object page=browser.waitExists(tpDetailPg,tourShoppingCart,tourDetails);
		
		if(page==tourDetails && tourDetails.isIgnoreWarningCheckBoxExisting()){
				tourDetails.selectIgnoreWarning();
				tourDetails.clickBookTour();
				browser.waitExists(tpDetailPg,tourShoppingCart);
		}else if(page==tourDetails){
			throw new ActionFailedException("Work flow stops in Tour Details page");
		}
		
		if(page ==  tpDetailPg) {
			//Set TPA infos
			if((bd.perTicketTPAsList!= null && bd.perTicketTPAsList.size()>0) || bd.perInventoryTPAs!=null && bd.perInventoryTPAs.size()>0){
				this.bookTourIntoCartFromTourOrderDetailsPg(bd);
			}else{
				tpDetailPg.selectAgreementCheckBox();
				tpDetailPg.clickContinueToShoppingCart();
				tourShoppingCart.waitLoading();
			}
		}
		
		return bd;
	}
	
	public TicketInfo bookTourIntoTourOrderDetailsPgFromTourDetailsPg(String tourDate, String tourPark, TicketInfo bd, boolean isCombo) {
		UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpTourShoppingCartPage tourShoppingCartPage = UwpTourShoppingCartPage.getInstance();
		
		logger.info("Book "+(isCombo?"Combo":"")+" tour into 'Tour Details' page from 'Tour Details Page'.");

		TicketInfo tour = new TicketInfo();
		if (isCombo) {
			tourDetails.bookComboTour(bd);
		} else {
			if(null!= bd.ticketTypes && bd.ticketTypes.size()>0 && null!=bd.ticketTypeNums &&  bd.ticketTypeNums.size()>0){
				tourDetails.bookTourByGivenTicketType(bd.ticketTypeNums, bd.ticketTimeSeq, bd.ticketTypes, bd.timeSlot, bd.deliveryMethod);
			}else{
				tour = tourDetails.bookTourByGivenTicketType(bd);
				if(StringUtil.isEmpty(tourPark)){
					tour.park = bd.park;
				}
			}

		}
		browser.waitExists(tourShoppingCartPage, tourOrderDetailsPg);
//		tourOrderDetailsPg.waitExists();
		//		browser.waitExists(tourShoppingCart, tourOrderDetailsPg);//shopping cart could be existed here for bd.perTicketTPAsList= null
		return tour;
	}
	
	public TicketInfo bookTourIntoTourOrderDetailsPgFromTourDetailsPg(TicketInfo bd, boolean isCombo) {
		return this.bookTourIntoTourOrderDetailsPgFromTourDetailsPg("", "",  bd, isCombo);
	}
	
	public TicketInfo bookTourIntoTourOrderDetailsPgFromTourSearchPanel(TicketInfo bd, boolean isCombo, boolean isGroupTicket) {
		return bookTourIntoTourOrderDetailsPgFromTourSearchPanel("", bd, isCombo, isGroupTicket);
	}
	

	/**
	 * Book tour into "Tour Order Details" page from "UwpHeaderBar" or "UnifiedSearchPanel"
	 * under "Public Sales"
	 * @param bd
	 * @param isCombo ---true: the tout is combo tour
	 * @return
	 */
	public TicketInfo bookTourIntoTourOrderDetailsPg(TicketInfo bd, boolean isCombo) {
		return this.bookTourIntoTourOrderDetailsPg(bd, isCombo, false);
	}

	/**
	 * Book tour to "Shopping cart" from "Tour Order Details" page
	 * @param bd
	 */
	public void bookTourIntoCartFromTourOrderDetailsPg(TicketInfo bd) {
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage.getInstance();
		UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();

		logger.info("Book tour to \"Shopping cart\" from \"Tour Order Details\" page");
		tourOrderDetailsPg.setOrderDetails(bd);
		tourOrderDetailsPg.clickContinueToShoppingCart();
		tourShoppingCart.waitLoading();
	}
	
	/**
	 * Book tour to "Tour Order Details" page from the same page
	 * @param bd
	 */
	public void bookTourIntoTourOrderDetailsPgFromTheSamePg(TicketInfo bd) {
		UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();

		logger.info("Book tour to\"Tour Order Details\" page from the same page");
		tourOrderDetailsPg.setOrderDetails(bd);
		tourOrderDetailsPg.clickContinueToShoppingCart();
		tourOrderDetailsPg.waitLoading();
	}

	/**
	 * Book tour into shopping cart.
	 * @param bd - the book information
	 * @param isCombo - true, Combo tour; false, not combo tour
	 * @return tour info
	 */
	public TicketInfo bookTourIntoCart(TicketInfo bd, boolean isCombo) {
		return this.bookTourIntoCart(bd, isCombo, false);
	}
	
	/**
	 *  Book tour into shopping cart from Home Page or Search Panel.
	 * @param bd - the book information
	 * @param isCombo - true, Combo tour; false, not combo tour
	 * @param isGroupTicket
	 * @return
	 */
	public TicketInfo bookTourIntoCart(TicketInfo bd, boolean isCombo, boolean isGroupTicket) {
		bd.park = this.bookTourIntoTourSearchPg(bd);
		bd.tourDate = this.bookTourIntoTourDetailsPgFromTourSearchPanel(bd, isGroupTicket);
	
		if((bd.perTicketTPAsList!= null && bd.perTicketTPAsList.size()>0) || bd.perInventoryTPAs!=null && bd.perInventoryTPAs.size()>0){
			this.bookTourIntoTourOrderDetailsPgFromTourDetailsPg(bd.tourDate, bd.park, bd, isCombo);
			this.bookTourIntoCartFromTourOrderDetailsPg(bd);
		}else{
			this.bookTourIntoCartFromTourDetailsPg(bd);//bd.isComboTour will identifier if it is combo tour
		}
		return bd;
	}

	/**
	 * Override method of gotoTourDetailsPage
	 * @param ticket
	 */
	public void gotoTourDetailsPage(TicketInfo ticket) {
		this.gotoTourDetailsPage(ticket, false);
	}

	/**
	 * gotoTourDetailsPage
	 * @param ticket
	 */
	public void gotoTourDetailsPage(TicketInfo ticket, boolean isGroupTicket) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpTourParkListPage tourParkList = UwpTourParkListPage.getInstance();
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpUpdateOrganizationProfile orgProfile = UwpUpdateOrganizationProfile.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpParkListCommonPage parkListPage = UwpParkListCommonPage.getInstance();

		logger.info("Book an " + (isGroupTicket?"Organization":"Individual") + " tour into cart.");
		
		if(!unifiedSearchPanel.exists()) {
			this.gotoHomePage();
		}
		if(!ticket.isUnifiedSearch) {
			headerBar.gotoTourParkList();
		}
		Object page = browser.waitExists(tourParkList, unifiedSearchPanel);
		if(page == tourParkList) {
			tourParkList.gotoTourParkDetails(ticket.park);
		}
		if(page == unifiedSearchPanel) {
			unifiedSearchPanel.setupTourSearchCriteria(ticket);
			unifiedSearchPanel.clickSearch();
			page = browser.waitExists(suggestionPage, parkListPage);
			if(page == suggestionPage) {
				suggestionPage.clickFacilitySuggestion(ticket.park);
				parkListPage.waitLoading();
			}
			parkListPage.clickFacility(ticket.park);
		}

		tourSearch.waitLoading();
		if(isGroupTicket) {
			tourSearch.clickSwitchToGroupSales();
			orgProfile.waitLoading();
			orgProfile.updateOrganizationProfile("School", "ReserveAmerica");
			orgProfile.clickSaveChanges();
			tourSearch.waitLoading();
		} else if(!isGroupTicket && tourSearch.isInGroupSales()) {
			tourSearch.clickSwitchToPublicSales();
		}
		tourSearch.waitLoading();
		tourSearch.setSearchCriteria(ticket);
		int period = 60;//days
		tourSearch.searchTour(period);
		tourDetails.waitLoading();
	}

	/**
	 * Book a new tour into shopping cart.
	 * @param bd - the book information
	 * @return - the tour information booked in cart
	 */
	public TicketInfo bookTourIntoCart(TicketInfo bd) {
		return bookTourIntoCart(bd, false);
	}

	/**
	 * Search to tour list page.
	 * @param ti - ticket info data
	 */
	public void gotoTourListPg(TicketInfo ti){
		UwpHomePage homePg = UwpHomePage.getInstance();
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpTourParkListPage tourParkList = UwpTourParkListPage.getInstance();
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpUnifiedSearchPanel unifiedSearch = UwpUnifiedSearchPanel.getInstance();
		UwpParkListCommonPage parkListPage=UwpParkListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();


		logger.info("Go to tour list page.");
		headerBar.clickHomeLink();
		browser.waitExists(homePg, unifiedSearch);
		// if it is unified search, set up search criteria 
		if(ti.isUnifiedSearch){
			unifiedSearch.setupTourSearchCriteria(ti);
			unifiedSearch.clickSearch();
			Object page = browser.waitExists(suggestPg, parkListPage);
			if(page == suggestPg){
				suggestPg.clickFacilitySuggestion(ti.parkId, ti.contractCode);
				parkListPage.waitLoading();
			}
			parkListPage.clickFacility(ti.park);
		}else{
			headerBar.gotoTourParkList();
			tourParkList.waitLoading();
			tourParkList.gotoTourParkDetails(ti.park);
		}

		tourSearch.waitLoading();
		tourSearch.setSearchCriteria(ti);
		tourSearch.clickSearchTours();
		tourSearch.waitLoading();
	}

	/**
	 * Verify the tour status in tour list page.
	 * @param status - expected tour availability status
	 * @param desText - expected descriptive text
	 */
	public void verifyTourStatusInTourList(String status, String desText){
		UwpTourListPage tourList = UwpTourListPage.getInstance();

		logger.info("Verify tour status in tour list page.");

		String actualStatus = tourList.getTourAvailability();
		String actualText = tourList.getAvailDescriptiveText().trim();

		if(status.equalsIgnoreCase(actualStatus) && 
				desText.equalsIgnoreCase(actualText)){
			logger.info("---Tour status is right.");
		}else {
			throw new ErrorOnPageException("Tour status is wrong!");
		}
	}

	/**
	 * This method goes to verify whether the Google map display as expected
	 * @return True when the result match, and false when doesn't match
	 */
	public boolean verifyGoogleMapDisplay(boolean isRec) {
		UwpGoogleStateMapPage stateMap = UwpGoogleStateMapPage.getInstance();

		boolean toReturn = false;

		logger.info("Verify google map displays.");

		if (stateMap.checkNoCampMessageExists()) {
			toReturn = true;
			logger.info("The map displays correctly and have no campgrounds found in the viewing area!");
		} else {
			// if not exist, then should throw there is no camp message.
			if(isRec) {
				stateMap.siteFlagWaitExistsForRec();
				if (stateMap.getSiteFlagObjectForRec().length > 0) {
					toReturn = true;
				}
			} else {
				stateMap.siteFlagWaitExists();
				if (stateMap.getSiteFlagObject().length > 0) {
					toReturn = true;
				}
			}
		}
		return toReturn;
	}

	/**
	 * This method goes to a external website by click on the link. 
	 * The work flow starts from head bar and ends at a external website page.
	 * @param bd - booking data to be provided
	 */
	public void searchSiteIntoExternalWeb(BookingData bd) {
//		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
//		UwpCampsiteDetailsPage campsiteDetailPg = UwpCampsiteDetailsPage
//		.getInstance();
		UwpParkDetailsCommonPage campgroundDetailsPage = UwpParkDetailsCommonPage.getInstance();

		logger.info("Find a site and click on to access to external website...");

//		headerBar.gotoFindCampSpot();
//		searchPanel.waitExists();
//		searchPanel.setupSearchCriteria(bd);
//		searchPanel.clickSearch();
//
//		searchPanel.searchParkWaitExists();
//		searchPanel.clickOnParkName();
//		searchPanel.parkDetailWaitExists();
//
//		campsiteDetailPg.gotoSiteInExternalWebsite();
		this.bookParkToSiteListPg(bd);
		campgroundDetailsPage.waitLoading();
		campgroundDetailsPage.gotoSiteInExternalWebsite();
	}

	/**
	 * This method goes to check whether the park name displays in external site equals to
	 * the one has been displayed in UWP site. 
	 * @param park - park name
	 * @return boolean value
	 */
	public boolean checkParkNameDisplayed(String park) {
		UwpExternalWebsitePage externalPg = UwpExternalWebsitePage
		.getInstance();

		boolean toReturn = false;
		externalPg.waitLoading();
		if (externalPg.checkParkDisplayed(park))
			toReturn = true;

		return toReturn;
	}

	/**
	 * This method goes to a site's date range availability page. 
	 * The work flow starts from head bar and ends at site date range availabilty page.
	 * @param bd - booking data to be provided
	 * @return parkName
	 */
	public String findSiteInDateRange(BookingData bd) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangePg = UwpDateRangeAvailabilityPage.getInstance();

		logger.info("Go to site list date range availability page.");

		headerBar.gotoFindCampSpot();
		searchPanel.waitLoading();
		searchPanel.setupSearchCriteria(bd);
		searchPanel.doSearch(bd.conType);

		searchPanel.waitLoading();
		String parkName=searchPanel.clickOnFirstPark();

		Object page = browser.waitExists(siteListPg, rangePg);
		if(page == siteListPg) {
			siteListPg.clickDateRangeAvailability();
			rangePg.waitLoading();
		}
		return parkName;
	}
	
	public void findTour(TicketInfo bd){
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		tourSearch.setSearchCriteria(bd);
		tourSearch.clickSearchTours();
		tourSearch.searchWaitExists();
	}

	/**
	 * Book a new tour into shopping cart.
	 * @param bd - the book information
	 * @return - the tour information booked in cart
	 */
	public TicketInfo findTourIntoCart(TicketInfo bd) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Book tour into cart.");

		TicketInfo tour;
		headerBar.gotoTourParkList();

		tourSearch.waitLoading();
		tourSearch.setSearchCriteria(bd);
		int period = 60;//days
		String tourDate = tourSearch.searchTour(period);

		tourDetails.waitLoading();
//		tour = tourDetails.bookTour(bd.ticketNums,bd.ticketTimeSeq);
		tour = tourDetails.bookTourByGivenTicketType(bd);
		tour.tourDate = tourDate.replaceAll(" ", "");

		tourShoppingCart.waitLoading();

		return tour;
	}

	/**
	 * Find any recreation area and goes to its detail page and verify display.
	 * @param bd - the book information
	 * @return whether the name display
	 */
	public boolean gotoRecreationAreaDetailsPg(BookingData bd) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpRecreationSearchHomePage recreaSearch = UwpRecreationSearchHomePage
		.getInstance();

		logger.info("Go to recreation area details page.");
		boolean isMatch = false;
		headerBar.gotoRecreationSearch();

		recreaSearch.waitLoading();
		recreaSearch.setupAreaSearchCriteria(bd);
		recreaSearch.clickRecreationSearchSubmit();

		recreaSearch.waitLoading();
		String nameInList = recreaSearch.gotoFirstRecreationArea();
		recreaSearch.waitLoading();

		if (nameInList.equalsIgnoreCase(recreaSearch.getAreaName())
				&& nameInList.length() > 0) {
			isMatch = true;
			logger.info("The area name display correctly.");
		} else if (nameInList.length() == 0) {
			isMatch = true;
			logger.info("There is no recreation area match your search criteria.");
		} else {
			logger.error("The area name didn't appear.");
		}

		return isMatch;
	}
	
	public void gotoRecreationAreaDetailsPgFromViewAsListPg(String parkName){
		UwpViewAsListCommonPage parkViewAsListPg = UwpViewAsListCommonPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		RAParkDetailsPageForCampAndMarina parkDetailsPg = RAParkDetailsPageForCampAndMarina.getInstance();
		logger.info("Go to recreation area details page from view as list page after clicking park name:"+parkName);
		parkViewAsListPg.clickParkName(parkName);
		if(parkDetailsPg.exists())
			parkDetailsPg.clickCheckAvailability();
		recreationAreaDetailsPg.waitLoading();
	}
	
	/**
	 * This method goes to book another order into shopping cart.
	 * it start from shopping cart page, and also end at shopping cart page.
	 * @param bd - booking data
	 * @param od - order details
	 * @param isSameContract - whether the site from same contract or not
	 */
	public void bookAnotherSiteIntoCart(BookingData bd, OrderDetails od,boolean isSameContract) {
		UwpProductListCommonPage siteResultPg = UwpProductListCommonPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();

		logger.info("Book another site into cart.");
		shoppingCart.gotoBookMoreReservation();
		siteResultPg.waitLoading();

		if (!isSameContract) {
			siteResultPg.gotoParkListPage();
			searchPanel.waitLoading();
			searchPanel.searchSite(bd);
		} else {
			siteResultPg.selectFirstAvailSite();
		}

		prdDetailPg.waitLoading();
		prdDetailPg.bookAnyDays(bd.maxLoop);

		orderDetails.waitLoading();
		orderDetails.setOrderDetails(od,bd.contractCode);
		orderDetails.clickContinueToShoppingCart();
		shoppingCart.waitLoading();
	}
	
	/**
	 * Go to campground details page by given park name. if the park name is not specified, will click the first park name on search result page.
	 * This method starts from home page, ends at campgroundDetails page.
	 * Integrated with Unified search
	 * @param bd
	 * @param startFromHomePage start from home page, or unified search result pages
	 */
	public void gotoCampgroundDetailsPg(BookingData bd, boolean startFromHomePage) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpHomePage homePage = UwpHomePage.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkListCommonPage parkResult = UwpParkListCommonPage.getInstance();
		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		RAParkDetailsPage raParkDetailsPg = RAParkDetailsPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		RAParkDetailsPageForCampAndMarina parkDetailsPg = RAParkDetailsPageForCampAndMarina.getInstance();
		UwpPermitingPage permitingCommenPg = UwpPermitingPage.getInstance();
		
		logger.info("Go to campground details page.");
		if(AwoUtil.isUnifiedSearch()){
			if(startFromHomePage){				
				headerBar.clickHomeLink();
				homePage.waitLoading();
			}else{
				unifiedSearchPanel.waitLoading();
			}
			
			if (bd.clickClearSearch){
				unifiedSearchPanel.clickClearSearch();
			}
			
			//new method for setting up unified search parameter 
			unifiedSearchPanel.setupUnifiedSearch(bd);
			if(bd.clickSearch){
				unifiedSearchPanel.clickSearch();
				
				Object obj = browser.waitExists(suggestPg,unifiedParkListPg);
				if(obj == suggestPg){
					if(StringUtil.notEmpty(bd.parkId) && StringUtil.notEmpty(bd.contractCode)){
						suggestPg.clickFacilitySuggestion(bd.parkId, bd.contractCode);
					}else if (StringUtil.notEmpty(bd.park)){
						suggestPg.clickFacilitySuggestion(bd.park);
					}else suggestPg.clickFacilitySuggestion(bd.whereTextValue);
				}
			}
			unifiedParkListPg.waitLoading();
			
			if(StringUtil.isEmpty(bd.whereTextValue)) {
				unifiedParkListPg.clickBookSitesForFirstPark();
			} else {
				unifiedParkListPg.clickParkName(bd.whereTextValue.toUpperCase());
			}
			browser.waitExists(campDetails, parkDetailsPg, permitingCommenPg);
			if(parkDetailsPg.exists())
			{
				parkDetailsPg.clickCheckAvailability();
				raParkDetailsPg.waitLoading();
			}
		}else{
			headerBar.gotoFindCampSpot();
			searchPanel.waitLoading();
			searchPanel.setupSearchCriteria(bd);
			searchPanel.doSearch(bd.conType);
			parkResult.gotoParkDetailsByParkName(bd.park);
			campDetails.waitLoading();
		}
	}

	/**
	 * Go to campground details page by given park name. if the park name is not specified, will click the first park name on search result page.
	 * This method starts from home page, ends at campgroundDetails page.
	 * Integrated with Unified search
	 * @param bd
	 */
	public void gotoCampgroundDetailsPg(BookingData bd) {
		this.gotoCampgroundDetailsPg(bd, true);
	}
	
	/**
	 * Go to camp date range availability page, start from camping common page, ends at Date Range Availability page.
	 *  
	 */
	public void gotoDateRangeAvailPg(){
		UwpCampingPage cmpPg = UwpCampingPage.getInstance();
		UwpDateRangeAvailabilityPage dateRangePg = UwpDateRangeAvailabilityPage.getInstance();
		cmpPg.clickDateRangeAvailability();
		dateRangePg.waitLoading();		
	}
	
	/**
	 * Go to camp site List Page, start from camping common page, ends at Camp site List Page.
	 *  
	 */
	public void gotoCampSiteListPg(){
		UwpCampingPage cmpPg = UwpCampingPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		cmpPg.clickCampsiteList();
		siteListPg.waitLoading();		
	}
	
	/**
	 * Go to camp site map page, start from camping common page, ends at camp site map page.
	 *  
	 */
	public void gotoCampMapPg(){
		UwpCampingPage cmpPg = UwpCampingPage.getInstance();
		UwpCampgroundMapPage mapPg = UwpCampgroundMapPage.getInstance();
		cmpPg.clickCampgroundMap();
		mapPg.waitLoading();		
	}
	
	/**
	 * Go to Basic Map Mode
	 * 
	 * @author Lesley Wang
	 * Feb 28, 2013
	 */
	public void gotoCampBasicMapMode() {
		UwpCampgroundMapPage mapPg = UwpCampgroundMapPage.getInstance();
		logger.info("Go to Camp Map page and switch to Basic Map Mode...");
		this.gotoCampMapPg();
		mapPg.clickSwitchToBasicMap();
		mapPg.waitLoading();
	}
	
	/**
	 * Go to Enhanced Map Mode
	 * 
	 * @author Lesley Wang
	 * Feb 28, 2013
	 */
	public void gotoCampEnhancedMapMode() {
		UwpCampgroundMapPage mapPg = UwpCampgroundMapPage.getInstance();
		logger.info("Go to camp map page and switch to Enhanced Map Mode...");
		this.gotoCampMapPg();
		mapPg.clickSwitchToEnhancedMap();
		mapPg.waitLoading();
	}
	
	/**
	 * Go to camp site details page, start from camping common page, ends at camp site details page.
	 *  
	 */
	public void gotoCampGroundDetailsPg(){
		UwpCampingPage cmpPg = UwpCampingPage.getInstance();
		UwpParkDetailsCommonPage detailPg = UwpParkDetailsCommonPage.getInstance();
		cmpPg.clickCampgroundDetails();
		detailPg.waitLoading();
	}
	
	
	
	/**
	 * Go to Campsite list page by given park name.
	 * This method starts from home page, ends at Campsite list page.
	 * @param bd
	 */
	public void gotoCampsiteListPage(BookingData bd) {//aaa
//		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkListCommonPage parkResult =UwpParkListCommonPage.getInstance();
//		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		UwpCampingPage campDetails = UwpCampingPage.getInstance();
		
		UwpProductListCommonPage campListPg = UwpProductListCommonPage.getInstance();
		UwpCampingPage campPg = UwpCampingPage.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg=UwpViewAsListCommonPage.getInstance();
//		UwpProductListCommonPage prdListPg = UwpProductListCommonPage.getInstance();
		RAParkDetailsPageForCampAndMarina parkDetailsPg = RAParkDetailsPageForCampAndMarina.getInstance();
		logger.info("Go to campground details page.");
		
//		headerBar.gotoFindCampSpot();
//		searchPanel.waitExists();
//		searchPanel.setupSearchCriteria(bd);		
//		searchPanel.doSearch(bd.conType);
		
		Page page = this.gotoParkListPage(bd);
		
		if(page==unifiedParkListPg){
			if(StringUtil.isEmpty(bd.park)) {
				unifiedParkListPg.clickBookSitesForFirstPark();
			} else {
				if(bd.clickEnterDate){ //Sara[8/28/2013]: in unified search view as list page, click "Check abailability" button for each park
					if(StringUtil.notEmpty(bd.parkId)||StringUtil.notEmpty(bd.contractCode)){
						unifiedParkListPg.clickCheckAvailability(bd.parkId, bd.contractCode);
					}else unifiedParkListPg.clickFirstCheckAvailability();
					campListPg.waitLoading();
					campListPg.clickCampgroundDetails();
					
				}else{
					if(bd.clickParkName) {
						unifiedParkListPg.clickParkName(bd.park);	
					} else {
						if(StringUtil.isEmpty(bd.parkId)||StringUtil.isEmpty(bd.contractCode)){
							unifiedParkListPg.clickParkName(bd.park);
						}else{
							unifiedParkListPg.clickParkName(bd.contractCode, bd.parkId);
						}
					}
					
				}
			}
			
			browser.waitExists(parkDetailsPg,campDetails);
			if(parkDetailsPg.exists()){
				parkDetailsPg.clickCheckAvailability();
				campDetails.waitLoading();
			}
			
			campDetails.clickCampsiteList(); 
			campListPg.waitLoading();

		} else {
			if(!bd.clickEnterDate){
				
				parkResult.gotoParkDetailsByParkName(bd.park);
				campDetails.waitLoading();
				campDetails.clickCampsiteList();  
				campListPg.waitLoading();
			}else{
				parkResult.gotoParkListByEnterDateBtn(bd.park);
				campPg.waitLoading();
			}
		}
	}
	
	public Page gotoParkListPage(BookingData bd){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkListCommonPage parkResult = UwpParkListCommonPage.getInstance();
		
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg=UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionpage=UwpUnifiedSearchSuggestionPage.getInstance();
		
		logger.info("Go to Park List page.");
		
		if(AwoUtil.isUnifiedSearch()){
			this.gotoUnifiedSearchPanel(false);// Lesley[08/16/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
			logger.info("Go Unified Search.");
			unifiedSearchPanel.setupCampingSearchCriteria(bd);
			unifiedSearchPanel.clickSearch();
			Object page=browser.waitExists(unifiedParkListPg,suggestionpage);
			if(page==suggestionpage){
				Browser.sleep(1);
				if(StringUtil.notEmpty(bd.parkId) && StringUtil.notEmpty(bd.contractCode)){
					suggestionpage.clickFacilitySuggestion(bd.parkId, bd.contractCode);
				}else if (StringUtil.notEmpty(bd.park)){
					suggestionpage.clickFacilitySuggestion(bd.park);
				}else suggestionpage.clickFacilitySuggestion(bd.whereTextValue);

				unifiedParkListPg.waitLoading();
			}
			return unifiedParkListPg;
		}else {
			headerBar.gotoFindCampSpot();
			searchPanel.waitLoading();
			searchPanel.setupSearchCriteria(bd);		
			searchPanel.doSearch(bd.conType);
			parkResult.waitLoading();
			return parkResult;
		}
	}
	
	public void gotoCampgroundapMapSearchPg(BookingData bd){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpCampgroundsMapSearchPage campgroundSearchPg = UwpCampgroundsMapSearchPage.getInstance();
		
		logger.info("Go to campground search page.");
		
		headerBar.gotoCampgroudByMap();
		if(!campgroundSearchPg.exists()){
			this.gotoCampgroundMapSearchPgFromMapBrowsePg();
		}
		searchPanel.waitLoading();
		searchPanel.findCampgroundsViaMapSearch(bd);
		campgroundSearchPg.waitLoading();
	}
	
	public void gotoCampgroundMapBrowsePgFromMapSearchPg(){
		UwpCampgroundsMapSearchPage campgroundSearchPg = UwpCampgroundsMapSearchPage.getInstance();
		UwpCampgroundsMapBrowsePage campgroundMapBrowserPg = UwpCampgroundsMapBrowsePage.getInstance();
		
		logger.info("Go to campground map browse page from map search page.");
		campgroundSearchPg.clickMapBrowseTab();
		campgroundMapBrowserPg.waitLoading();
	}
	
	public void gotoCampgroundMapSearchPgFromMapBrowsePg(){
		UwpCampgroundsMapSearchPage campgroundSearchPg = UwpCampgroundsMapSearchPage.getInstance();
		UwpCampgroundsMapBrowsePage campgroundMapBrowserPg = UwpCampgroundsMapBrowsePage.getInstance();
		
		logger.info("Go to campground map search page from map browse page.");
		campgroundMapBrowserPg.clickMapSearchTab();
		campgroundSearchPg.waitLoading();
	}
	
	public void gotoCampgroundsByStateDirectory(String directoryLink){
		UwpHomePage uwpHomePg = UwpHomePage.getInstance();
		UwpCampgroundDirectotyPage cdPg = UwpCampgroundDirectotyPage.getInstance();
		UwpCampgroundsByStatePage statePg = UwpCampgroundsByStatePage.getInstance();

		logger.info("Go to campgrounds page via state directory");
		uwpHomePg.gotoCampgroundDirectory();
		cdPg.clickOnAgencyLink(directoryLink);
		statePg.waitLoading();
		statePg.waitForPageTitleDisplay(directoryLink);
	}	
	
	/**
	 * Go to Campsite list page by given park name 
	 * This method starts from home page, ends at Campsite list page.
	 * @param bd
	 * @param isParkSuggestPg: 
	 * true  - goto campsite list page via click the park name hyperlink on park search result "Are you looking for...?" page.
	 *					   
	 * false - goto campsite list page via click the park name/ enter date button on exactely search result page.
	 */
	public void gotoCampsiteListPage(BookingData bd, boolean isParkSuggestPg) {//aaa
		
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance(); 
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkListCommonPage parkResult = UwpParkListCommonPage.getInstance();
		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		UwpCampingPage campPg = UwpCampingPage.getInstance();
		
		if (!isParkSuggestPg){
			this.gotoCampsiteListPage(bd);
		}else{
			logger.info("Go to campground List page.");
			
			headerBar.gotoFindCampSpot();
			searchPanel.waitLoading();
			searchPanel.setupSearchCriteria(bd);		
			searchPanel.clickSearch();
			parkResult.waitLoading();			
			parkResult.gotoParkDetailsByParkName(bd.park);
			campDetails.waitLoading();
			campDetails.clickCampsiteList();  
			campPg.waitLoading();
		}
	}



	/**
	 * This method goes to fill in the order details infos, 
	 * it starts at order details page and ends at shopping cart page.
	 * @param od - order details info
	 * @param contract - contract is needed for dynamically selecting customer pass
	 * @param isDiscount - whether this order with or without discounts
	 * @param isPromoDiscount - is promotion discount
	 * @param isGroupRate -  is rate type group or family
	 * @return -  Discount name if exists
	 */
	public void fillInOrderDetails(OrderDetails od, String contract,boolean isDiscount, 
			boolean isPromoDiscount, boolean isGroupRate, String discountName) {
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpSelectDiscountPage discountSelect = UwpSelectDiscountPage.getInstance();

		logger.info("Book site into cart from order details page.");

		orderDetails.setOrderDetails(od, contract, isDiscount, isPromoDiscount, isGroupRate);
		orderDetails.clickContinueToShoppingCart();
		Object page = browser.waitExists(shoppingCart,discountSelect);
		if(page == discountSelect) {
			if(isDiscount){//Sara[12/06/2013]
				if(null != discountName && discountName.length()>0) {
					discountSelect.selectDiscount(discountName);
				} else {
					discountSelect.selectDiscount("None");
				}
			}
			discountSelect.clickContinueToShoppingCart();
			shoppingCart.waitLoading();
		}
	}

	public void fillInOrderDetails(OrderDetails od, String contract,boolean isDiscount, 
			boolean isPromoDiscount, boolean isGroupRate) {
		fillInOrderDetails(od, contract, isDiscount, isPromoDiscount, isGroupRate, "");
	}

	/**
	 * This method goes to fill in the order details infos, 
	 * it starts at order details page and ends at shopping cart page.
	 * @param od - order details info
	 * @param contract - contract is needed for dynamically selecting customer pass
	 * @param isDiscount - whether this order with or without discounts
	 * @param isGroupRate -  is rate type group or family
	 * @return -  Discount name if exists
	 * @return
	 */
	public void fillInOrderDetails(OrderDetails od, String contract,
			boolean isDiscount, boolean isGroupRate) {
		fillInOrderDetails(od, contract, isDiscount, false, isGroupRate);
	}

	/**
	 * This method goes to fill in the order details infos, 
	 * it starts at order details page and ends at shopping cart page.
	 * @param od - order details info
	 * @param contract - contract is needed for dynamically selecting customer pass
	 * @param isDiscount - whether this order with or without discounts
	 * @return discount -  Discount name if exists
	 */
	public void fillInOrderDetails(OrderDetails od, String contract,boolean isDiscount) {
		fillInOrderDetails(od, contract, isDiscount, false, false);
	}

	/**
	 * This method goes to fill in the order details infos, 
	 * it starts at order details page and ends at shopping cart page.
	 * @param od - order details info
	 * @param contract - contract is needed for dynamically selecting customer pass
	 */
	public void fillInOrderDetails(OrderDetails od, String contract) {
		fillInOrderDetails(od, contract, false);
	}

	/**
	 * This method goes to fill in the order details infos, 
	 * it starts at order details page and ends at shopping cart page.
	 * @param contract - contract is needed for dynamically selecting customer pass
	 */
	public void fillInOrderDetails(String contract) {
		fillInOrderDetails(new OrderDetails(), contract, false);
	}

	/**
	 * Search to park's search result list or site list page; and then retreve all park name and its status
	 * or all available site name to a vector.
	 * it starts from header bar, ends at a park's search result list page or site list page.
	 * @param bd - BookingData
	 * @param isSiteSearch true - site list; false - park list
	 * @return - park/site list vector
	 */
	public List<String> searchAndGetParkorSiteList(BookingData bd, boolean isSiteSearch) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkListCommonPage parkListPg = UwpParkListCommonPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangePg = UwpDateRangeAvailabilityPage.getInstance();

		if(isSiteSearch) {
			logger.info("Search and retrieve the site list from home.");
		}else {
			logger.info("Search and retrieve the park list from home.");
		}

		List<String> list = null;
		headerBar.gotoFindCampSpot();

		searchPanel.waitLoading();
		searchPanel.setupSearchCriteria(bd);
		searchPanel.doSearch(bd.conType);

		parkListPg.waitLoading();
		if(isSiteSearch) {
			if(parkListPg.getAvailableParks()<1) {
				throw new ItemNotFoundException("No available parks on date you searched.");
			}

			bd.park = searchPanel.getFirstParkName(); //get the selected park name
			searchPanel.gotoSiteList(bd.park, bd.contractCode); //this method handle the KOA

			Object page=browser.waitExists(rangePg,siteListPg);
			if(page==rangePg) {
				rangePg.gotoSiteList();
			}
			siteListPg.waitLoading();// if park available, then should have available sites

			list = siteListPg.getAllAvailableSiteName();
		}else {
			list = parkListPg.getAllParkName();
		}

		return list;
	}

	/**
	 * Compare parks name and status from two vector, orms list should contain all web list element.
	 * @param webPark - web park list vector
	 * @param ormsPark - orms park list vector
	 */
	public void verifyParkListBwtORMSandWeb(List<String> webPark, List<String> ormsPark) {
		logger.info("Compare park name and status from park list vectors.");

		String parkInWEB = "";
		String parkInORMS = "";
		String webStatus = "";
		String ormsStatus = "";
		for(int i=0; i < webPark.size(); i++) {
			parkInWEB = webPark.get(i).toString();

			// get the park name from vector first, then verify status
			if (!parkInWEB.equalsIgnoreCase("true")&&!parkInWEB.equalsIgnoreCase("false")) {
				int counter = 0;

				for(int j=0; j < ormsPark.size(); j++) {
					parkInORMS = ormsPark.get(j).toString();
					if(parkInORMS.equalsIgnoreCase(parkInWEB)) {
						counter++; // increase the counter when found the park in the other vector
						webStatus = webPark.get(i-1).toString(); // park status is the previous element
						ormsStatus = ormsPark.get(j-1).toString();// park status is the previous element
					}
				}
				if(counter == 0) {
					throw new ItemNotFoundException("Did not find " + parkInWEB + 
					" in second vector which already in first vector.");
				}else if(counter > 1) {
					throw new ItemNotFoundException("More than one same site found in second vector.");
				}else {
					if(!webStatus.equalsIgnoreCase(ormsStatus)) {
						throw new ItemNotFoundException("the status is not the same for park "
								+ parkInWEB);
					}
				}
			}
		}
	}

	/**
	 * Compare Sites name from two vector, the second vector should have all elements of first vector.
	 * @param webList - web site list vector
	 * @param ormsList - orms site list vector
	 */
	public void verifySiteListBwtORMSandWeb(List<String> webList, List<String> ormsList) {
		logger.info("Compare site name from site list vectors.");

		String siteInORMS = "";
		String siteInWEB = "";
		for(int i=0; i < webList.size(); i++) {
			siteInWEB = webList.get(i).toString();

			int counter = 0;
			for(int j=0; j < ormsList.size(); j++) {
				siteInORMS = ormsList.get(j).toString();
				if(siteInWEB.equalsIgnoreCase(siteInORMS)) {
					counter++; // increase the counter when found the park in the other vector
				}
			}
			if(counter == 0) {
				throw new ItemNotFoundException("Did not find " + siteInWEB + 
				" in second vector which already in first vector.");
			}
		}
	}

	/**
	 * This method goes to retrieve the first site's availability info by 
	 * searching a specified site. It starts from site search panel, and ends at 
	 * site availability page.
	 * @param bd - booking data
	 * @return list -  site availability info vector
	 */
	public List<String> searchAndGetSiteAvailability(BookingData bd) {
		UwpSiteSearchPanel siteSearchPanel = UwpSiteSearchPanel.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangeListPg = UwpDateRangeAvailabilityPage.getInstance();

		logger.info("Search and get site date range availabilities.");

		siteSearchPanel.searchSite(bd);
		Object page=browser.waitExists(siteListPg,rangeListPg);

		if(page==siteListPg) {
			siteListPg.clickDateRangeAvailability();
			rangeListPg.waitLoading();
		}

		List<String> list = rangeListPg.getSiteDateRangeAvailability();
		return list;
	}

	/**
	 * Update the arrival date and length of stay in site details page.
	 * this method just wait for site details, order details page or sign in page, 
	 * not handle the diff situation, need to handle this during your test cases.
	 * @param arrivDate - arrival date
	 * @param duration - length of stay
	 */
	public void updateDateInSiteDetailPg(String arrivDate, String duration) {
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		
		logger.info("Update arrival date and length in site details.");
		
		if(DateFunctions.compareDates(prdDetailPg.getArriveDate(),arrivDate)!=0){
//			siteDetailPg.setArriveDate(arrivDate);	
//			siteDetailPg.clickGroundName();
			prdDetailPg.setArriveDateAndMoveFocus(arrivDate);	// fix the issue that the page refreshes after lose focus from date field
			prdDetailPg.waitLoading();
		}
		prdDetailPg.setLengthOfStay(duration);
		prdDetailPg.waitLoading();
		prdDetailPg.clickBookTheseDays();
		browser.waitExists(prdDetailPg,orderDetails,signInPg, signInUpPg);
	}

	/**
	 * Remove the reservation item from order details page to release the inventory.
	 */
	public void removeResFromOrderDetailsPg() {
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Remove reservation from order details page.");
		orderDetails.clickRemoveThis();
		shoppingCart.waitLoading();
		if (!shoppingCart.isShoppingCartEmpty())
			throw new PageNotFoundException("The shopping cart is not empty!");
	}

	/**
	 * Update the arrival date and length of stay in site details page.
	 * this method starts at order details page, ends at site details page.
	 * this method just wait for site details, order details page, 
	 * not handle the diff situation, need to handle this during your test cases.
	 * @param arrivDate - arrival date
	 * @param duration - length of stay
	 */
	public void changeDateFromOrderDetails(String arrivDate, String duration) {
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		logger.info("Change arrival date and length from order details page.");
		this.changeDateFromOrderDetailsPageToSiteDetails();
		
		prdDetailPg.setLengthOfStay(duration);
//		siteDetailPg.setArriveDate(arrivDate);
		prdDetailPg.setArriveDateAndMoveFocus(arrivDate);// only remove focus, the page can refresh
		prdDetailPg.waitLoading();
		prdDetailPg.clickBookTheseDays();
		browser.waitExists(prdDetailPg,orderDetails);
	}
	
	/**
	 * 'Change Dates' to site details page from order details page
	 */
	public void changeDateFromOrderDetailsPageToSiteDetails(){
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();

		logger.info("'Change Dates' to site details page from order details page.");
		orderDetails.clickChangeDates();
		prdDetailPg.waitLoading();
	}
	
	/**
	 * Go to 'Change Reservation details' page from 'Reservation Details'page
	 * Start page: 'Reservation Details'page
	 * End page: 'Change Reservation details' page
	 */
	public void gotoChangeResDetailsFromResDetailsPg(){
		UwpReservationDetailsPage resDetailsPg = UwpReservationDetailsPage.getInstance();
		UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
		
		logger.info("Click 'Change reservation' link to 'Change Reservation Details' page from 'Reservation Details'page");
		resDetailsPg.clickChangeReservationLink();
		updateResDetailPg.waitLoading();
	}
	
	/**
	 * Go to 'Change Reservation' page from 'Reservation Details'page
	 * Start page: 'Reservation Details'page
	 * End page: 'Change Reservation' page
	 */
	public void gotoChangeResPgFromResDetailsPg(){
		UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
		UwpChangeReservationPage changeResPg = UwpChangeReservationPage.getInstance();

		this.gotoChangeResDetailsFromResDetailsPg();

		logger.info("Click 'Change Date(s)?/Transfer to Another Site to 'Change Reservation' page from 'Change Reservation Details' page");
		updateResDetailPg.clickChangeDateOrTransferToAnotherSite();
		changeResPg.waitLoading();
	}
	
	/**
	 * Go to 'Change Reservation Details' page from 'Change Reservation' page.
	 * Start page: 'Change Reservation' page
	 * End page: 'Change Reservation Details' page
	 * @param bd
	 */
	public void changeDateOrTransferSiteToChangeResDetailsPg(BookingData bd){
		UwpChangeReservationPage changeResPg = UwpChangeReservationPage.getInstance();
		UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
		
		logger.info("Change date or transfer site to 'Change Reservation Details' page from 'Change Reservation' page.");
		changeResPg.changeDateOrThansferToAnotherSite(bd, bd.isSearchingBySiteNum, bd.isChangingDateOnly);
		changeResPg.clickBookTheseDays();
		updateResDetailPg.waitLoading();
	}
	
	/**
	 * Make change date and transfer site from 'Reservation Details'page 
	 * and the end page is "Reservation details" page
	 * @param bd
	 */
	public void changeDateOrTransferSite(BookingData bd){
		this.changeDateOrTransferSite(bd, null);
	}
	
	public void changeDateOrTransferSiteToChangeResConfirmPage(BookingData bd){
		gotoChangeResPgFromResDetailsPg();
		changeDateOrTransferSiteToChangeResDetailsPg(bd);
		this.gotoChangeResDetailsPgFromUpdateResDetailsPg(bd.equip, bd.equipLength);
	}
	
	/**
	 * Make change date and transfer site from 'Reservation Details'page 
	 * and the end page is "Reservation details" page
	 * @param bd
	 */
	public BigDecimal changeDateOrTransferSite(BookingData bd, Payment pay){
		gotoChangeResPgFromResDetailsPg();
		changeDateOrTransferSiteToChangeResDetailsPg(bd);
		return updateReservationDetails(bd.equip, bd.equipLength, pay);
	}
	
	/**
	 * Click book more reservation from shopping cart and go to park search page if from
	 * different contract.
	 * @param isSameContract - is the park from same contract
	 */
	public void bookMoreReservations(boolean isSameContract) {
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
//		UwpTourParkListPage tourParkList = UwpTourParkListPage.getInstance();
		UwpTourListPage tourParkList = UwpTourListPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();

		logger.info("Book more res from cart.");
		shoppingCart.gotoBookMoreReservation();
		Object page = browser.waitExists(siteListPg, tourParkList);
		
		if(page == siteListPg){
			if(!isSameContract) {// go to search park again for diff contract
				siteListPg.gotoParkListPage();
				searchPanel.waitLoading();
			}
		}else if(page == tourParkList){
			if(!isSameContract) {// go to search park again for diff contract on REC
				this.gotoHomePage();
			}
		}
	}

	public boolean isUnifiedSearch() {
		return AwoUtil.isUnifiedSearch();
//		return ( MiscFunctions.isRECEnv() && MiscFunctions.isRECUnifiedSearchOpen()) || 
//		(MiscFunctions.isRAEnv() && MiscFunctions.isRAUnifiedSearchOpen()) ||
//		(!MiscFunctions.isRECEnv() && !MiscFunctions.isRAEnv() && MiscFunctions.isPLWUnifiedSearchOpen());
	}
	
	/**
	 * This method goes to park search results page. It starts form header bar
	 * Ends at park search results list page.
	 * @param bd - booking data
	 */
	public void gotoParkList(BookingData bd) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpParkListCommonPage parkList = UwpParkListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionpage=UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage viewAsListPg = UwpViewAsListCommonPage.getInstance();
		
		logger.info("Search to park result list page.");
		
		//Sara[08/27/2013]: comment the two line codes due to new RA UI design in QA1/3 in 3.04.03
//		headerBar.clickHomeLink();
//		browser.waitExists(homePg,unifiedSearchPanel); 
		
		// if it is unified search, set up search criteria 
//		if(bd.isUnifiedSearch){
		if(isUnifiedSearch()){
			
			this.gotoUnifiedSearchPanel(false);// Sara[08/27/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
			unifiedSearchPanel.setupCampingSearchCriteria(bd);
			unifiedSearchPanel.clickSearch();			
		}else{	
			headerBar.gotoFindCampSpot();
			searchPanel.waitLoading();
			searchPanel.setupSearchCriteria(bd);
			searchPanel.clickSearch();
		}
		//handle with suggestion page
		Object page=browser.waitExists(viewAsListPg, parkList,suggestionpage);
		if(page==suggestionpage){
			if(StringUtil.notEmpty(bd.parkId) && StringUtil.notEmpty(bd.contractCode)){
				suggestionpage.clickFacilitySuggestion(bd.parkId, bd.contractCode);
			}else if (StringUtil.notEmpty(bd.park)){
				suggestionpage.clickFacilitySuggestion(bd.park);
			}else suggestionpage.clickFacilitySuggestion(bd.whereTextValue);
			
//			parkList.waitExists();
			viewAsListPg.waitLoading();
		}
	}

	/**
	 * Verify whether or not the payment fields have been separated
	 */
	public void verifyResPayment(int numOfPayments) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		logger.info("Verify the payment items info.");

		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();

		checkoutPg.checkPaymentRules(numOfPayments);
		checkoutPg.gotoShoppingCart();
		shoppingCart.waitLoading();
	}

	/**
	 * Verify the available mark in the site detail page
	 * @param arrivalDate
	 * @param daynum
	 * @param mark
	 * @param siteNum
	 */
	public void verifyAvilableMarkInStDetailPg(String arrivalDate, int daynum,String mark){
		IHtmlObject[] objs=browser.getTableTestObject(".id","calendar");
		IHtmlTable table = (IHtmlTable) objs[0];

		int columnSize = table.columnCount();

		int col=0;
		String day = DateFunctions.formatDate(arrivalDate, "yyyy/MM/d").split("/")[2];
		for(int i=0;i<columnSize;i++){
			if(table.getCellValue(1, i).matches("0?"+day+" [M|T|W|F|S][h|a|u]?")){
				col=i;
				break;
			}
		}

		for(int k=0;k<daynum;k++){
			if(!table.getCellValue(2, col+k).split(" ")[0].trim().equalsIgnoreCase(mark)) {
				throw new ItemNotFoundException("The site status is wrong");
			}
		}			
	}

	/**
	 * Overload method to verify the business rule takes effective at site detail page via verifying the status of the site
	 * @param ruleName
	 * @param arrivalDate
	 * @param numOfStay
	 * @param expectedStatus - the expected status of site of at the specific day
	 */
	public void verifyBusinessRuleEffectiveAtSiteDetailPage(String ruleName, String arrivalDate, int numOfStay, String expectedStatus) {
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();

		logger.info("Verify whether the " + ruleName + " Rule take effect in Web sales channel.");

		if (ruleName.length() == 0) {
			throw new ActionFailedException("Please set value to the parameter - ruleName");
		}

		try{
			if(expectedStatus.toLowerCase().startsWith("avail")) {
				this.verifyAvilableMarkInStDetailPg(arrivalDate, numOfStay, "A");
			} else if(expectedStatus.toLowerCase().startsWith("unavail")) {
				this.verifyAvilableMarkInStDetailPg(arrivalDate, numOfStay, "X");
			}
		} catch(ItemNotFoundException e) {
			throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site# = " + prdDetailPg.getSiteNumber() + ") in Web sales channel.");
		}

		logger.info("----The " + ruleName + " Rule take effect in Web successfully.");
	}

	/**
	 * Override method
	 * @param ruleName
	 * @param errorMsg
	 * @param product
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtSiteDetailPage(String ruleName, String errorMsg, Object product) {
		return this.verifyBusinessRuleEffectiveAtOrderDetailPage(ruleName, errorMsg, product, false);
	}

	/**
	 * Verify whether the rule take effect to the Web sales channel - verify at Site Detail Page or Order Detail Page
	 * @param ruleName
	 * @param errorMsg
	 * @param product
	 * @param verifyCustomerPass - if verifyCustomerPass is true means that case will go to 
	 * 													Order Detail Page to verify the Customer Pass Type
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtOrderDetailPage(String ruleName, String errorMsg, Object product, boolean verifyCustomerPass) {
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		UwpTourDetailsPage tourDetailPg = UwpTourDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpSelectDiscountPage discountSelect = UwpSelectDiscountPage.getInstance();
		Object page = null;
		String msg = "";

		logger.info("Verify whether the " + ruleName + " Rule take effect in Web sales channel.");

		if(product instanceof String) {
			prdDetailPg.waitLoading();
			prdDetailPg.clickBookTheseDays();
			page = browser.waitExists(prdDetailPg, orderDetails, signInPg, signInUpPg);

			if(page == prdDetailPg) {
				msg = prdDetailPg.getErrorMsg();
				if(errorMsg.length() == 0){
					throw new ActionFailedException("Please set value to the parameter - errorMsg.");
				}
				if(msg == null || "".equalsIgnoreCase(msg)) {
					throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site#=" 
							+ product + ") in sales channel - Web.");
				}
				if(!msg.contains(errorMsg)) {
					throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site#=" 
							+ product + ") in sales channel - Web.");
				}
				logger.info("----The " + ruleName + " Rule take effect to the Site (Site# = " + product + ") in sales channel - Web successfully.");
			} else {
				if(!verifyCustomerPass) {
					if(errorMsg.length() != 0) {
						throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site# = " 
								+ product + ") in sales channel - Web.");
					}
				}
				//logger.info("----As negatively, the " + ruleName + " Rule applied successfully to sales channel - Web.");		  		
			}

			//verify the rule effective at Order Detail Page
			if(verifyCustomerPass) {
				if(page == orderDetails) {
					OrderDetails dtl = new OrderDetails();
					orderDetails.setEquipment(dtl.equipType, dtl.equipLength, dtl.numOccupant,dtl.numVehicles);
					orderDetails.selectCheckBoxAgreementAccepted();
					orderDetails.clickContinueToShoppingCart();
					page = browser.waitExists(orderDetails, shoppingCart, discountSelect);
				}

				if(page == orderDetails) {
					if(errorMsg.length() == 0) {
						throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site#=" 
								+ product + ") in sales channel - Web.");
					}

					msg = orderDetails.getCustPassValidationMsg();
					if(msg == null || "".equalsIgnoreCase(msg)) {
						throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site#=" 
								+ product + ") in sales channel - Web.");
					}
					if(!msg.contains(errorMsg)) {
						throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site#=" 
								+ product + ") in sales channel - Web.");
					}
					logger.info("----The " + ruleName + " Rule take effect to the Site (Site# = " + product + ") in sales channel - Web successfully.");
				}
			}

			/*
			 * 
			 */
			if(verifyCustomerPass && (page != orderDetails) || (!(verifyCustomerPass)) && (page != prdDetailPg)){
				if(null != errorMsg && !"".equalsIgnoreCase(errorMsg)) {
					throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Site (Site#=" 
							+product + ") in sales channel - Web.");
				}
				logger.info("----As negatively, the " + ruleName + " Rule take effect correctly to sales channel - Web.");
			}
		} else if(product instanceof TicketInfo) {
			TicketInfo tour = (TicketInfo)product;
			tourDetailPg.waitLoading();
			tourDetailPg.bookTour(tour.ticketNums, tour.ticketTimeSeq);
			tourDetailPg.selectDeliveryMethod(tour.deliveryMethod);
			tourDetailPg.clickOnBookTour();

			page = browser.waitExists(tourDetailPg, orderDetails, signInPg);

			if(page == tourDetailPg) {
				msg = tourDetailPg.getErrorMsg();
				if(msg == null || "".equalsIgnoreCase(msg)) {
					throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Tour (Tour Name = " 
							+ tour.tourName + ") in sales channel - Web.");
				}
				if(!msg.contains(errorMsg)) {
					throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Tour (Tour Name = " 
							+ tour.tourName + ") in sales channel - Web.");
				}
				logger.info("----The " +ruleName + " Rule take effect to the Tour (Tour Name = " + tour.tourName + ") in sales channel - Web successfully.");
			} else {
				if(null != errorMsg && "".equalsIgnoreCase(errorMsg)) {
					throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Tour (Tour Name = " 
							+ tour.tourName + ") in sales channel - Web.");
				}
				logger.info("----As negatively, the " + ruleName + " Rule doesn't take effect to sales channel - Web.");		  		
			}
		} else if(product instanceof PermitInfo){
//			PermitInfo permit = (PermitInfo)product;

		} else {
			throw new ActionFailedException("The 3rd parameter can not be identified. Please check it again.");
		}
		return msg;
	}

	/**
	 * Verify whether the business rules take effect at the Confirmation page
	 * @param ruleName
	 * @param errorMsg
	 * @param siteInfo
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtConfirmationPage(String ruleName, String errorMsg, String siteNo) {
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpHomePage hmPg = UwpHomePage.getInstance();
		UwpActiveAdvantageTrialOfferPage advantageOfferPg = UwpActiveAdvantageTrialOfferPage.getInstance();
		String msgOrResIDs = "";

		logger.info("Verify whether the " + ruleName + " Rule take effect in Web sales channel.");
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();
		checkoutPg.setupPayment(new Payment("Visa"));
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();

		Object page=browser.waitExists(advantageOfferPg, confirmPg);
		if(page == advantageOfferPg) {
			advantageOfferPg.clickNoThanks();
			confirmPg.waitLoading();
		}
		msgOrResIDs = confirmPg.getErrorMsg();
		if(!msgOrResIDs.contains("This page may not be used as proof of payment upon arrival.")) {
			if(errorMsg.length() == 0){
				throw new ActionFailedException("Please set value to the parameter - errorMsg.");
			}
			if(msgOrResIDs.matches(".*" + errorMsg.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)") + ".*")){
				logger.info("----The " + ruleName + " Rule take effect to the Site (Site# = " + siteNo + ") in sales channel - Web successfully.");
			}
		} else {
			if(errorMsg.length() == 0){
				logger.info("----As negatively, the " + ruleName + " Rule applies successfully to sales channel - Web.");
			}
			msgOrResIDs = confirmPg.getAllResNo();
			confirmPg.clickContinueToRAHome();
			hmPg.waitLoading();
		} 
		return msgOrResIDs;
	}

	/**
	 * Verify the available status of ticket at the arrival date
	 * @param ruleName
	 * @param arrivalDate
	 * @param expectedStatus
	 */
	public void verifyBusinessRuleEffectiveAtTourDetailPage(String ruleName, String arrivalDate, String expectedStatus) {
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();

		logger.info("Verify whether the " + ruleName + " Rule take effect in Web sales channel.");

		String tourName = tourDetails.getTourName();
		if (ruleName.length() == 0) {
			throw new ActionFailedException("Please set value to the parameter - ruleName");
		}

		boolean ticketStatus = tourDetails.getTicketStatus(arrivalDate);
		boolean compareStatus = false;
		if(expectedStatus.toLowerCase().startsWith("avail")) {
			compareStatus = true;
		} else if(expectedStatus.toLowerCase().startsWith("unavail")) {
			compareStatus = false;
		}

		if(ticketStatus != compareStatus) {
			throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Ticket (Tour Name = " + tourName + ") in Web sales channel.");
		}

		logger.info("----The " + ruleName + " Rule take effect in Web successfully.");
	}

	//	/**
	//	 * Select one discount from discount selection page when there are more than one 
	//	 * eligible discounts.
	//	 * @param discount
	//	 */
	//	public void selectDiscountFromSelectionPg(String discount){
	//		UwpSelectDiscountPage selectDiscount = UwpSelectDiscountPage.getInstance();
	//		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
	//		
	//		logger.info("Select discount from discount selection page.");
	//		selectDiscount.selectDiscount(discount);
	//		selectDiscount.clickContinueToShoppingCart();
	//		shoppingCart.waitExists();
	//	}

	/**
	 * Verify the whether the discount has been applied successful in shopping cart page
	 * will only work for tier 1 discount
	 * @param discountRate -  discount rate
	 */
	public void verifyDiscountInCart(String discountRate) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Verify the discount  in shopping cart.");
		if(shoppingCart.isContainDiscount()){
			double discFee = Double.parseDouble(shoppingCart.getDiscount());
			double campFee = Double.parseDouble(shoppingCart.getCampingFee());
			double result = Double.parseDouble(discountRate) / 100;

			if (!(Math.abs((discFee/campFee-result))<.0000001)) {
				throw new ErrorOnPageException(" ---Discount fee applies error.");
			}else {
				logger.info(" ---Discount has been applied successfully.");
			}
		} else {
			logger.info(" ---No discount found in shopping cart page.");
		}
	}

	/**
	 * Fill in user account info and verify the order details page displays.
	 * This method starts from cross over sign in page, end at shopping cart page
	 * @param email - user email
	 * @param pw - password
	 * @param isStay - is stay on RA to process the order in cart first
	 */
	public void crossOverVerification(String email, String pw, boolean isStay){
		ConfirmDialogPage confirmDg=ConfirmDialogPage.getInstance();
		RaCrossOverToRecPage raToRec = RaCrossOverToRecPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpShoppingCartPage shoppingCartPg = UwpShoppingCartPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		
		logger.info("Verify reservation transfer.");
		confirmDg.setDismissible(false);
		Object page=browser.waitExists(confirmDg, raToRec);

		if(page == confirmDg){
			if(isStay){
				confirmDg.clickCancel();
//				campsiteDetailPg.gotoShoppingCart();
				prdDetailPg.clickItemsInCartLink();//Quentin[20130910] UWP new ui change
			} else {
				confirmDg.clickOK();
				raToRec.waitLoading();
				raToRec.waitForPageLoad();
				browser.maximize();
				raToRec.signIn(email, pw);//repeat this part of code to avoid more parameter for this method
				raToRec.waitForOrderDetailsPg();
				raToRec.gotoCart();
				raToRec.waitForShoppingCartPg();
				raToRec.abandonThisCart();
				confirmDg.setDismissible(false);
				confirmDg.waitLoading();
				confirmDg.clickOK();
				raToRec.waitForSignOutPg();
				raToRec.gotoSignOut();
				raToRec.waitForHomePg();
				raToRec.closeBrowser();// close rec browser after sign out
//				campsiteDetailPg.gotoShoppingCart();
				prdDetailPg.clickItemsInCartLink();//Quentin[20130910] UWP new ui change
				Object secondPage = browser.waitExists(shoppingCartPg, signInPg, signInUpPg);
				if(signInPg == secondPage) {
					signInPg.signIn(email, pw);
				} else if (signInUpPg == secondPage) {
					signInUpPg.signIn(email, pw);
				}
			}
			shoppingCartPg.waitLoading();
		} else {
			raToRec.waitLoading();
			raToRec.waitForPageLoad();
			browser.maximize();
			raToRec.signIn(email, pw);//repeat this part of code to avoid more parameter for this method		
			raToRec.waitForOrderDetailsPg();
			raToRec.gotoCart();
			raToRec.waitForShoppingCartPg();
			raToRec.abandonThisCart();
			confirmDg.setDismissible(false);
			confirmDg.waitLoading();
			confirmDg.clickOK();
			raToRec.waitForSignOutPg();
			raToRec.gotoSignOut();
			raToRec.waitForHomePg();
			raToRec.closeBrowser();// close rec browser after sign out
		}
	}

	/**
	 * Fill in camping lottery application details page 1 and 2.
	 * This methods start from application details page 1 and ends at page 2.
	 * parameter isCollectCCPayment to verify the setting of collect credit card info in lottery details page.
	 * @param bd - booking data
	 * @param od - order details data
	 * @param pay - payment info
	 * @param isCollectCCPayment - whether or not collect the credit card info
	 */
	public void fillInLotteryDetails(BookingData bd, OrderDetails od, Payment pay,
			boolean isCollectCCPayment) {
		UwpCampingLotteryDetailsPage1 pageOne=UwpCampingLotteryDetailsPage1.getInstance();
		UwpCampingLotteryDetailsPage2 pageTwo=UwpCampingLotteryDetailsPage2.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

		logger.info("Fill in lottery application details page.");
		pageOne.setPageOneDetails(bd);//do nothing on page one
		pageOne.clickContinue();

		pageTwo.waitLoading();
		pageTwo.setPageTwoDetails(od, pay, isCollectCCPayment);
		pageTwo.selectCheckBoxAgreementAccepted();
		pageTwo.clickContinueToShoppingCart();
		shoppingCart.waitLoading();
	}

	/**
	 * Fill in camping lottery application details page 1 and 2.
	 * This methods start from application details page 1 and ends at page 2.
	 * fill in with Visa if collecting cc payment info in page 2
	 * @param bd - booking data
	 * @param od - order details data
	 */
	public void fillInLotteryDetails(BookingData bd, OrderDetails od) {
		UwpCampingLotteryDetailsPage2 pageTwo=UwpCampingLotteryDetailsPage2.getInstance();

		logger.info("Fill in lottery application details page.");

		Payment pay = new Payment("Visa");//fill in with VISA payment type by default
		fillInLotteryDetails(bd, od, pay, pageTwo.needCollectCCPaymentInfo());
	}

	/**
	 * Verify lottery order status from My Reservation & Account page.
	 * @param resNo - the reservation number
	 * @param status - lottery order status to be verified
	 */
	public void verifyLotteryOrderStatusFromAccount(String resNo, String status) {
		UwpCampingLotteryApplicationListPage lotteryList = UwpCampingLotteryApplicationListPage.getInstance();
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();

		logger.info("Verify lottery order status from account panel.");
		accountPanelPg.gotoLotteryApplications();
		lotteryList.waitLoading();

		if(status.equalsIgnoreCase(lotteryList.getLotteryOrderStatus(resNo))) {
			logger.info("Lottery order status is right.");
		} else {
			throw new ErrorOnPageException("Lottery order status is wrong!");
		}
	}

	/**
	 * Verify business rules in site details page.
	 * @param ruleName -  rule name
	 * @param errorMsg - prompt error message when violated the rules
	 * @param isClick - whether or not click Book these Dates button
	 * @param isShoppingCart - whether or not go to shopping cart page
	 * @return Minimum window
	 */
	public String verifyBusinessRuleInSiteDetailsPg(String ruleName, String errorMsg, 
			boolean isClick, boolean isShoppingCart) {
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		UwpSocialSecurityNumberRequiredPage ssnPg=UwpSocialSecurityNumberRequiredPage.getInstance();

		logger.info("Verify "+ruleName+" rule");
		if(isClick) {
			prdDetailPg.clickBookTheseDays();
			Object page=browser.waitExists(prdDetailPg,orderDetails,ssnPg,signInPg, signInUpPg);
			if(page == ssnPg) {
				ssnPg.setSocialSecurityNumber("078051120");
				ssnPg.clickContinue();
				page=browser.waitExists(prdDetailPg,orderDetails,signInPg, signInUpPg);
			}
			if(page==orderDetails) {
				throw new ItemNotFoundException(ruleName+" rule has been broken.");
			}
			if(page==signInPg || page==signInUpPg){
				throw new ItemNotFoundException(ruleName+" rule has been broken.");
			}
		}

		String msg=prdDetailPg.getErrorMsg();//get error messages
		String lengthOfStay=prdDetailPg.getMinimumLengthOfStay();// get the minimum stay
		if(!msg.matches(errorMsg))
			throw new ItemNotFoundException(ruleName+" rule has been broken.");
		else 
			logger.info("---"+ruleName+" rule applied successful.");

		if(isShoppingCart) {
//			siteDetailPg.gotoShoppingCart();
			prdDetailPg.clickItemsInCartLink();//Lesley[20130906]:Update due to Items In Cart link change to Items text and Check Out Now link.
			shoppingCart.waitLoading();
		}

		return lengthOfStay;
	}

	/**
	 * verify the rule violation in site details page.
	 * @param ruleName - specified rule name
	 * @param errorMsg - error messages when rule has been broken.
	 */
	public String verifyBusinessRuleInSiteDetailsPg(String ruleName, String errorMsg) {
		return verifyBusinessRuleInSiteDetailsPg(ruleName, errorMsg, true, false);
	}

	/**
	 * Verify 'Account Overview', 'Current Reservations', 'Past Reservations', 'Lottery Applications'
	 * pages.
	 * @param data - text for account overview page, other page will be order number
	 */
	public void accountPanelVerification(String data) {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpAccountOverviewPage accountOverview = UwpAccountOverviewPage.getInstance();
		UwpCurrentResListPage currentRes = UwpCurrentResListPage.getInstance();
		UwpPastResListPage pastResPg = UwpPastResListPage.getInstance();	
		UwpCampingLotteryApplicationListPage lotteryList = UwpCampingLotteryApplicationListPage.getInstance();

		logger.info("Verify account panel pages.");
		String pageName = accountPanel.getActivatePageName();// get the link name for current active page.
		if(pageName.equalsIgnoreCase("Account Overview")) {
			accountOverview.verifyAccountOverview(data);
		} else if(pageName.equalsIgnoreCase("Current Reservations")) {
			if(currentRes.verifyOrderexists(data)) {
				logger.info("Current Reservations page displays correct.");
			} else {
				throw new ErrorOnPageException("Current Reservations page displays with error!");
			}
		} else if(pageName.equalsIgnoreCase("Past Reservations")) {
			if(pastResPg.isPastReservationsPg()) {
				logger.info("Past Reservations page displays correct.");
			} else {
				throw new ErrorOnPageException("Past Reservations page displays with error!");
			}
		} else if(pageName.equalsIgnoreCase("Lottery Applications")) {
			if(lotteryList.verifyOrderexists(data)) {
				logger.info("Lottery Applications page displays correct.");
			} else {
				throw new ErrorOnPageException("Lottery Applications page displays with error!");
			}
		}
	}

	/**
	 * Go to special page from Reservation section in Account Overview page.
	 * @param pageName - link name for page.
	 */
	public void gotoOtherPagesByReservationsSection(String pageName) {
		UwpCampingLotteryApplicationListPage lotteryAppPg = UwpCampingLotteryApplicationListPage
		.getInstance();
		UwpAccountOverviewPage accountOVPg = UwpAccountOverviewPage.getInstance();
		UwpCurrentResListPage currentResPg = UwpCurrentResListPage.getInstance();
		UwpPastResListPage pastResPg = UwpPastResListPage.getInstance();	
		UwpPreRegistrationListPage preCheckInPg = UwpPreRegistrationListPage.getInstance();

		logger.info("Go to "+ pageName +" page from Reservations section from Account Overview page.");

		if(pageName.equalsIgnoreCase("Current Reservations")) {
			accountOVPg.gotoCurrentReservationPg();
			currentResPg.waitLoading();
		} else if(pageName.equalsIgnoreCase("Past Reservations")) {
			accountOVPg.gotoPastReservationPg();
			pastResPg.waitLoading();
		} else if(pageName.equalsIgnoreCase("Lottery Applications")) {
			accountOVPg.gotoLotteryApplicationsPg();
			lotteryAppPg.waitLoading();
		} else if(pageName.equalsIgnoreCase("Pre-Registration")){
			accountOVPg.gotoPreRegistrationPg();
			preCheckInPg.waitLoading();
		}else {
			throw new ItemNotFoundException("No link for provided page name.");
		}
	}

	/**
	 * This method starts from park search result page, ends at site search results page.
	 * Go to site list page by clicking park by given name, and do site search in site list page.
	 * @param parkName - park name
	 * @param siteInfo - site info
	 */
	public void gotoSiteListFromParkListPg(String parkName, BookingData bd){
		UwpParkListCommonPage parkList = UwpParkListCommonPage.getInstance();
		UwpUnifiedFindProductCommonPanel searchPanel = UwpUnifiedFindProductCommonPanel.getInstance();
		UwpSearchPanel uwpSearchPanel = UwpSearchPanel.getInstance();
		UwpParkDetailsCommonPage campDetailPg = UwpParkDetailsCommonPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangeListPg = UwpDateRangeAvailabilityPage.getInstance();

		parkList.waitLoading();
		//check unifSearchForm existed or not
//		if(bd.isUnifiedSearch){
		if((MiscFunctions.isRECEnv() && MiscFunctions.isRECUnifiedSearchOpen()) || 
				(MiscFunctions.isRAEnv() && MiscFunctions.isRAUnifiedSearchOpen()) ||
				(!MiscFunctions.isRECEnv() && !MiscFunctions.isRAEnv() && MiscFunctions.isPLWUnifiedSearchOpen())){
			logger.info("Click Facility Name and go to campsite list");
			parkList.clickFacility(bd.park);
			campDetailPg.waitLoading();
			campDetailPg.clickCampsiteList();
		}else{		
			logger.info("Go to site list by Park Name");
			parkList.gotoSiteListByParkName(parkName);
			siteListPg.waitLoading();
			uwpSearchPanel.setupSearchCriteria(bd);
			uwpSearchPanel.clickSearch();
			browser.waitExists(siteListPg, rangeListPg);
			return;
		}
		
		searchPanel.setupSearchCriteria(bd);
		searchPanel.clickSearch();
		browser.waitExists(siteListPg, rangeListPg);
	}

	/**
	 * Method goto shopping cart page by clicking the "Items In Cart:" link or "Back To Shopping Cart" link
	 * this method can goto shopping cart page from any page if the "Items In Cart:" link or "Back To Shopping Cart" link exists, 
	 * if not, it will throw an ObjectNotFoundException
	 */
	public void gotoShoppingCartPg() {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpConfirmationPage uwpConfirmationPg = UwpConfirmationPage.getInstance();
		UwpOrderDetailsPage orderDetailsPg = UwpOrderDetailsPage.getInstance();

		if(orderDetailsPg.isItemsInCartLinkExists()){
			orderDetailsPg.clickItemsInCartLink();
			shoppingCart.waitLoading();
		} else if(uwpConfirmationPg.exists()) {
			//if the confirmation page exists, the "Items In Cart" doesn't exist, but there
			//is a "Back To Shopping Cart" link exists
			uwpConfirmationPg.clickBackToShoppingCart();
			shoppingCart.waitLoading();
		} else {
			throw new ObjectNotFoundException("Can not go to shoppingcart page from this page directly.");
		}
	}

	/**
	 * this method is complete process of canceling reservation by resID, and it includes gotoMyReservationsAccount, 
	 * gotoResDetailFromAccount, cancelReservation etc keywords
	 * @param resID
	 * @param contractCode
	 * @param resStatus
	 */
	public void cleanUpReservation(String resID, String contractCode, String resStatus){
		this.gotoMyReservationsAccount();
		this.gotoResDetailFromAccount(resID, contractCode, resStatus);
		this.cancelReservation(new Payment("Visa"));
	}

	/**
	 * Fill in donation or special pass info in order details page.
	 * @param od - order details data
	 * @return - donation amount has been selected
	 */
	public String checkPOSInOrderDetailsPg(OrderDetails od) {
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();

		logger.info("Check special Pass/donation POS in order details page.");
		orderDetails.waitLoading();
		if(od.isPassPOS) {//check Pass POS
			orderDetails.checkSpecialPassPOS();
		}
		if(od.passOfferName!=null)
		{
			if(od.passOfferSubName!=null)//Annual Camping Pass (In State/Out of State)
			{
				orderDetails.selectPassOffer(od.passOfferName);
				orderDetails.selectPassSubOffer(od.passOfferSubName);
			}else{ //Annual Day Use Pass 
				orderDetails.selectPassOffer(od.passOfferName);
			}
			
		}

		String donationAmnt = "";
		if(od.isDonationPOS) {//select donation
			if(od.isMinFixedAmount) {
				donationAmnt=orderDetails.checkMinFixedDonationAmnt();
			} else if(od.isMaxFixedAmount) {
				donationAmnt=orderDetails.checkMaxFixedDonationAmnt();
			} else if(od.otherAmount.length() > 0) {
				orderDetails.checkOtherDonationAmnt(od.otherAmount);
			} else {
				orderDetails.checkNoDonationAmnt();
			}
		}

		return donationAmnt;
	}

	/**
	 * Verify the number of contracts, number of order items in shopping cart page or
	 * confirmation page.
	 * @param numContracts - number of contracts order from
	 * @param numItems - number of order items
	 * @param verifyStr - facility names
	 */
	public void verifyItemsInShopCartOrConfirmPg(int numContracts, int numItems, String[] verifyStr) {
		UwpShoppingCartPage shopCart = UwpShoppingCartPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		String tableText = "";
		if(confirmPg.exists()) {
			logger.info("Verify order item(s) info in confirmation page.");
			if (confirmPg.numberOfContracts() != numContracts) {
				throw new ErrorOnPageException("Number of contract(s) in confirmation page wrong!");
			} else {
				logger.info("The order divide into " + numContracts + " sections for each contract.");
			}

			if (confirmPg.numberOfOrders() != numItems) {
				throw new ErrorOnPageException("Number of order item(s) in confirmation page wrong!");
			} else {
				logger.info(numItems + " order item(s) in confirmation page.");
			}
			tableText = confirmPg.getTextInConfirmPage();
			for(int i = 0; i < verifyStr.length; i++) {
				if(tableText.indexOf(verifyStr[i]) == -1) {
					throw new ErrorOnPageException(verifyStr[i] + " did not display in confirmation page!");
				} else {
					logger.info(verifyStr[i] + " displays in confirmation page.");
				}
			}
		} else {
			logger.info("Verify order item(s) info in shopping cart page.");
			if (shopCart.numberOfContracts() != numContracts) {
				throw new ErrorOnPageException("Number of contract(s) in cart wrong!");
			} else {
				logger.info("The order divide into " + numContracts + " sectons for each contract(s).");
			}

			if (shopCart.numberOfItems() != numItems) {
				throw new ErrorOnPageException("Number of item(s) in cart wrong!");
			} else {
				logger.info(numItems + " item(s) in cart.");
			}
			tableText = shopCart.getTextInShoppingCart();
			for(int i = 0; i < verifyStr.length; i++) {
				if(tableText.indexOf(verifyStr[i]) == -1) {
					throw new ErrorOnPageException(verifyStr[i] + " did not display in cart!");
				} else {
					logger.info(verifyStr[i] + " displays in shopping cart.");
				}
			}
		}
	}

	/**
	 * Verify the number of order items in shopping cart or confirmation page for 1 contract.
	 * @param numItems - number of order items
	 * @param verifyStr - facility names
	 */
	public void verifyItemsInShopCartOrConfirmPg(int numItems, String[] verifyStr) {
		verifyItemsInShopCartOrConfirmPg(1,numItems,verifyStr);
	}

	/**
	 * Retrieve and verify whether the POS amount is correct as expected for special POS name.
	 * This methods can be used in shopping cart or order confirmation page.
	 * @param posName - POS name
	 * @param amount - Pos amount as expected.
	 */
	public void getAndVerifyPOSAmount(String posName, String amount) {
		UwpShoppingCartPage shopCart = UwpShoppingCartPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		if(confirmPg.exists()) {
			logger.info("Verify the POS amount in confirmation page");
			String amountInConfirm = confirmPg.getPOSAmountByName(posName);
			if(StringUtil.compareNumStrings(amountInConfirm, amount)==0) {
				logger.info("The POS amount is correct in confirm page.");
			} else {
				throw new ErrorOnPageException("POS amount is wrong in confirm page!", amount, amountInConfirm);
			}
		} else {
			logger.info("Verify the POS amount in shopping cart page");
			String amountInCart = shopCart.getPOSAmountByName(posName);
			if(StringUtil.compareNumStrings(amountInCart,amount)==0) {
				logger.info("The POS amount is correct in shopping cart.");
			} else {
				throw new ErrorOnPageException("POS amount is wrong in shopping cart!", amount, amountInCart);
			}
		}
	}
	
	/**
	 * Retrieve and verify whether the POS quantity is correct as expected for special POS name.
	 * This methods can be used in shopping cart or order confirmation page.
	 * @param posName - POS name
	 * @param quantity - POS quantity as expected.
	 */
	public void getAndVerifyPOSQuantity(String posName, String quantity) {
		quantity = "1";
		UwpShoppingCartPage shopCart = UwpShoppingCartPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();

		if(confirmPg.exists()) {
			logger.info("Verify the POS Quantity in confirmation page");
			String quantityInConfirm = confirmPg.getPOSQuantityByName(posName);
			if(StringUtil.compareNumStrings(quantityInConfirm, quantity)==0) {
				logger.info("The POS Quantity is correct in confirm page.");
			} else {
				throw new ErrorOnPageException("POS Quantity is wrong in confirm page!", quantity, quantityInConfirm);
			}
		} else {
			logger.info("Verify the POS Quantity in shopping cart page");
			String quantityInCart = shopCart.getPOSQuantityByName(posName);
			if(StringUtil.compareNumStrings(quantityInCart,quantity)==0) {
				logger.info("The POS Quantity is correct in shopping cart.");
			} else {
				throw new ErrorOnPageException("POS Quantity is wrong in shopping cart!", quantity, quantityInCart);
			}
		}
	}
	
	public void getAndVerifyPOSFee(String posName, String fee) {
		UwpShoppingCartPage shopCart = UwpShoppingCartPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		
		if(confirmPg.exists()) {
			logger.info("Verify the POS Reservation Fee in confirmation page");
			String feeInConfirm = confirmPg.getPOSReservationFeeByName(posName);
			if(StringUtil.compareNumStrings(feeInConfirm,fee)==0) {
				logger.info("The POS Reservation Fee is correct in confirm page.");
			} else {
				throw new ErrorOnPageException("POS Reservation Fee is wrong in confirm page!", fee,feeInConfirm );
			}
		} else{
			logger.info("Verify the POS Reservation Fee in shopping cart page");
			String feeInCart = shopCart.getPOSReservationFeeByName(posName);
			if(StringUtil.compareNumStrings(feeInCart,fee)==0) {
				logger.info("The POS Reservation Fee is correct in shopping cart.");
			} else {
				throw new ErrorOnPageException("POS Reservation Fee is wrong in shopping cart!", fee, feeInCart);
			}
		}
	}

	public void checkPassOfferInfo(String passOfferTitle, String[] content)
	{
		//
		
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		String info = orderDetails.getPassOfferInfo("New Mexico State Parks Pass Offer");
		logger.info("Got Pass Offer info-->"+info);
		
		for(String s: content)
		{
			logger.info("Checking ("+s+")"+" in Pass Offer info on Page...");
			if(!info.toUpperCase().contains(s.toUpperCase()))
			{
				throw new ErrorOnPageException(passOfferTitle+" does not contain "+s);
			}
		}
		logger.info("Checking Pass Offer info Passed!!!");
		
		
	}
	

	/**
	 * Verify the park or site photos in park search result, park details or site details page.
	 * @param conCode - contract code
	 * @param parkID - park facility id
	 * @param expectPicNum - expected number of pictures
	 */
	public void verifyParkPictures(String conCode, String parkID, int expectPicNum) {
		UwpParkListCommonPage parkList = UwpParkListCommonPage.getInstance();
		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();

		logger.info("Click and verify on each pictures.");

		int numOfPics = campDetails.numOfParkPics(conCode, parkID);//this method can be used for several pages
		int pageMark = 0;//indicate the page, as the popup we can not get the page at that time
		if(expectPicNum != numOfPics) {//verify number of pictures
			throw new ErrorOnPageException("The number of pictures is wrong!");
		}

		for(int i=0; i<expectPicNum; i++) {//click and verify each pictures
			String[] sPicDimen = campDetails.clickPictureByIndex(conCode, parkID, i);

			if(parkList.exists()) {
				logger.info(" ---Verify photo "+ (i+1) +" in park search page.");
				pageMark = 1;//1 - park search results page
				if(!MiscFunctions.isRAEnv() && !MiscFunctions.isRECEnv() && !(sPicDimen[0].equals("80") && sPicDimen[1].equals("53")) ||
					(MiscFunctions.isRAEnv() && !(sPicDimen[0].equals("180") && sPicDimen[1].equals("120")))) {
					throw new ErrorOnPageException("Small picture dimensions wrong!");
				}
			} else if(campDetails.exists()){
				logger.info(" ---Verify photo "+ (i+1) +" in park details page.");
				pageMark = 2;//2 - park details page
				if(!(sPicDimen[0].equals("180") && sPicDimen[1].equals("120"))) {
					throw new ErrorOnPageException("Big picture dimensions wrong!");
				}
			} else if(prdDetailPg.exists()){
				logger.info(" ---Verify photo "+ (i+1) +" in site details page.");
				pageMark = 3;//3 - site details page
				if(!(sPicDimen[0].equals("180") && sPicDimen[1].equals("120"))) {
					throw new ErrorOnPageException("Big picture dimensions wrong!");
				}
			}

			int counter = 0;
			do {
				counter++;
				Browser.sleep(1);
				if(counter>10) {//sleep 10 sec to wait for picture pop up
					throw new ErrorOnPageException("The big pop up picture did not show up" +
					" in 10 seconds.");
				}
			} while(!campDetails.isPopPicExists());

			//click on Popped up big picture
			String[] bPicDimen = campDetails.clickOnPoppedPicture();
			if(pageMark ==1 || pageMark == 2) {//park search results and details apge
				if(!(bPicDimen[0].equals("540") && bPicDimen[1].equals("360"))) {
					throw new ErrorOnPageException("Large picture dimensions wrong! Expected width is: 540, height is: 360, but actual width is: " + bPicDimen[0] + ", height is: " + bPicDimen[1]);
				}
			} else if(pageMark == 3){//site details page
				if(!(bPicDimen[0].equals("540") && (expectPicNum==1?bPicDimen[1].equals("405"):(i==0?bPicDimen[1].equals("360"): bPicDimen[1].equals("405"))))){ //bPicDimen[1].equals("405"))) {//Sara[12/2/2013] two pictures width are different in site detail page. The first is 360, the second is 405.
					throw new ErrorOnPageException("Large picture dimensions wrong! Expected width is: 540, height is: 405, but actual width is: " + bPicDimen[0] + ", height is: " + bPicDimen[1]);
				}
			}
			pageMark = 0;// reset the park mark
			Browser.sleep(2);
		}
		logger.info(numOfPics + " pictures in total and big picture can be popped up.");
	}
	
	public void bookParkToParkListPage(BookingData bd) {
		
	}

	public void findSites(BookingData bd){
		UwpSiteSearchPanel findSitePanel = UwpSiteSearchPanel.getInstance();
		findSitePanel.waitLoading();
		findSitePanel.setupSearchCriteria(bd);
		findSitePanel.clickSearchCampsitesSubmit();
		findSitePanel.waitLoading();
	}
	
	/**
	 * Search park from home page to site list page.
	 * This method start from search panel and end at site list page or range list or campground common page(for isRange)
	 * @param bd - bookingData data
	 */
	public Object bookParkToSiteListPg(BookingData bd) {
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkListCommonPage parkList = UwpParkListCommonPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpParkDetailsCommonPage parkDetailPg = UwpParkDetailsCommonPage
				.getInstance();
		RAParkDetailsPageForCampAndMarina raParkDetails = RAParkDetailsPageForCampAndMarina.getInstance();
		UwpDateRangeAvailabilityPage rangeListPg = UwpDateRangeAvailabilityPage
				.getInstance();
		UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
//		UnifiedSearchPanel unifiedSearchPanel = UnifiedSearchPanel.getInstance();
		UwpSiteSearchPanel findSitePanel = UwpSiteSearchPanel.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg=UwpViewAsListCommonPage.getInstance();
//		UnifiedSearchSuggestionPage suggestionpage=UnifiedSearchSuggestionPage.getInstance();

		logger.info("Search park from home page to site list page.");
		
		Page page=gotoParkListPage(bd);//james[20130909] merge unified search into gotoParkListPage

		//		headerBar.clickHomeLink(); // Lesley[08/14/2013]: comment the two line codes due to new RA UI design in QA1/3 in 3.04.03
		//		homePg.waitExists();

		// if it is unified search, set up search criteria 
		if(page==unifiedParkListPg){
			if(StringUtil.isEmpty(bd.park)) {
				unifiedParkListPg.clickBookSitesForFirstPark();
			} else {
				if(bd.clickEnterDate){ //Sara[8/28/2013]: in unified search view as list page, click "Check abailability" button for each park
					if(StringUtil.notEmpty(bd.parkId)||StringUtil.notEmpty(bd.contractCode)){
						unifiedParkListPg.clickCheckAvailability(bd.parkId, bd.contractCode);
					}else unifiedParkListPg.clickFirstCheckAvailability();

				}else{
					//Lesley[20140402] update due to PCR 4463 - Facility Page Re-design in RA
					// Click park name to park details page
					if(StringUtil.isEmpty(bd.parkId)||StringUtil.isEmpty(bd.contractCode)){
						unifiedParkListPg.clickParkName(bd.park);
					}else{
						unifiedParkListPg.clickParkName(bd.contractCode, bd.parkId);
					}
					
					Object detailsPage = browser.waitExists(raParkDetails, parkDetailPg); 
					if (detailsPage == raParkDetails)
							raParkDetails.clickCheckAvailability();
					
					if (bd.clickParkName && !bd.searchInParkDetail) {
						if (detailsPage == parkDetailPg) {
							parkDetailPg.clickBookNow();//end at SiteList Page
						}
					} else {
						if(!bd.contractCode.equalsIgnoreCase("KOA") && !bd.contractCode.equalsIgnoreCase("ELS")) {
							findSites(bd);//end at SiteList Page
						}
					}
				
//					if(bd.clickParkName) {
//						unifiedParkListPg.clickParkName(bd.park);
//						parkDetailPg.waitLoading();
//						if (detailsPage == parkDetailPg) {
//							if(bd.searchInParkDetail) {
//								findSites(bd);
//							} else {
//								parkDetailPg.clickBookNow();//end at SiteList Page
//							}
//						} else {
//							raParkDetails.clickCheckAvailability(); //end at Site List page
//						}
//					} else {
//						if(StringUtil.isEmpty(bd.parkId)||StringUtil.isEmpty(bd.contractCode)){
//							unifiedParkListPg.clickParkName(bd.park);
//						}else{
//							unifiedParkListPg.clickParkName(bd.contractCode, bd.parkId);
//						}
//						parkDetailPg.waitLoading();
//						if(!bd.contractCode.equalsIgnoreCase("KOA") && !bd.contractCode.equalsIgnoreCase("ELS")) {
//							findSitePanel.waitLoading();
//							findSitePanel.setupSearchCriteria(bd);
//							findSitePanel.clickSearchCampsitesSubmit();//end at SiteList Page
//						}
//					}
				}
			}
		} else {
			if(bd.ignoreStatus) {//park availability is unknown
				//go to first park' site list page whatever the park status is
				parkList.bookFirstPark();
			} else {//park is available
				if(bd.clickParkName) {//force to click on park name link to go to park details page
					parkList.clickParkName(bd.park,bd.contractCode);
					parkDetailPg.waitLoading();
					if(bd.searchInParkDetail) {
						searchPanel.setupSearchCriteria(bd);
						searchPanel.clickSearch();
					} else {
						parkDetailPg.clickBookNow();
					}
				} else {
					searchPanel.gotoSiteList(bd.park, bd.contractCode);
				}
			}
		}

		Object endPage = browser.waitExists(siteListPg, rangeListPg, campCommonPg);
		
		return endPage;
	}

	
	public void findCampgroundToSiteListPg(BookingData bd){
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();

		logger.info("Find campround to site list page");
		searchPanel.setupSearchCriteria(bd);
		searchPanel.clickSearch();
		siteListPg.waitLoading();
	}
	
	public void gotoSiteDetailsPageFromSiteList(String siteNum){
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		
		logger.info("Go to site details page from site lsit via site number.");
		if(!StringUtil.isEmpty(siteNum)) {
			siteListPg.clickSiteNum(siteNum);
		} else {
			siteListPg.selectFirstAvailSite();
		}
		prdDetailPg.waitLoading();
	}
	
	public void gotoSiteDetailsPageFromDateRangeAvailabilityPage(String siteNum, int maxLoop) {
		UwpDateRangeAvailabilityPage dateRangeAvailPage = UwpDateRangeAvailabilityPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		
		logger.info("Goto Site Details page from Date Range Availability page.");
		if(!StringUtil.isEmpty(siteNum)) {
			dateRangeAvailPage.clickSiteNum(siteNum);
		} else {
			IHtmlObject[] invs = dateRangeAvailPage.getAvailableInventorys();
			int count = 0;
			while (invs.length < 1) {
				Browser.unregister(invs);
				count++;
				if (count > maxLoop) throw new ItemNotFoundException("There is no inventory in " + maxLoop * 14 + " days.");

				dateRangeAvailPage.goNext2Weeks();
				dateRangeAvailPage.waitLoading();
				invs = dateRangeAvailPage.getAvailableInventorys();
			}
			invs[0].click();
		}
		prdDetailPg.waitLoading();
	}
	
	/**
	 * This method goes to site details page from site list page.
	 * It starts from park's site list page, and ends at site details page 
	 * @param bd - BookingData data
	 */
	public void searchSiteFromSiteListToSiteDetails(BookingData bd) {
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangeListPg = UwpDateRangeAvailabilityPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();

		logger.info("Search site from site list page to site details page.");
		searchPanel.setupSearchCriteria(bd);
		searchPanel.clickSearch();

		if(bd.contractCode.equals("KOA")) {
			bd.isRange = false;//KOA park can not go to date range availability page
		}

		if(bd.isRange) {
			rangeListPg.waitLoading();
			if(StringUtil.isEmpty(bd.siteNo)) {
				//if site number is unknown, only when select first available site will make sense
				rangeListPg.selectFirstAvailSite();
			} else {
				if(bd.ignoreStatus) {//site availability is unknown
					//will click on site number like whatever the site status is
					rangeListPg.clickSiteNum(bd.siteNo);
				} else {//site is available
					if(bd.clickSiteNum) {//forced to go to site details page by click site# link
						rangeListPg.clickSiteNum(bd.siteNo);
					} else {//go to site details page by click first A link
						rangeListPg.selectAvailSiteBySiteNum(bd.siteNo);
					}
				}
			}
		} else {
			siteListPg.waitLoading();
			//			siteListPg.waitPageRefreshDone();//to wait the site list page refresh done
			if(StringUtil.isEmpty(bd.siteNo)) {
				//if site number is unknown, only when select first available site will make sense
				siteListPg.selectFirstAvailSite();
			} else {
				if(bd.ignoreStatus) {
					//click on site number link whatever (ignore) the site status is
					siteListPg.clickSiteNum(bd.siteNo);
				} else {
					//search with loop to make sure only one specified site returned
					if(bd.clickSiteNum) {//forced to go to site details page by click site# link
						siteListPg.clickSiteNum(bd.siteNo);
					} else {//go to site details page by click see details link
//						siteListPg.clickSeeDetails();
						String siteID = siteListPg.getSiteID(bd.siteNo); // update by lesley, because in some cases, bd.siteNo is empty.
						siteListPg.clickSeeDetails(Integer.parseInt(siteID));
					}
				}
			}
		}

		prdDetailPg.waitLoading();
	}

	/**
	 * Book site to site details page from "Find Sites" panel
	 * @param bd
	 */
	public void bookSiteToSiteDetails(BookingData bd){
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangeListPg = UwpDateRangeAvailabilityPage.getInstance();
		UwpProductDetailsCommonPage campsiteDetailPg = UwpProductDetailsCommonPage.getInstance();

		logger.info("Search site from site list page to site details page.");
		searchPanel.setupSearchCriteria(bd);
		searchPanel.clickSearch();


		boolean isKOA=bd.contractCode.equals("KOA");
		boolean siteNoProvided=!StringUtil.isEmpty(bd.siteNo);
		boolean siteIDProvided=!StringUtil.isEmpty(bd.siteID);

		if(isKOA) {
			bd.isRange = false;//KOA park will only go to site list page
		}

		// for range date should be modified for unified search 
		if(bd.isRange) {
			//should force to data range availability section
			Object page = browser.waitExists(siteListPg, rangeListPg);
			if(page == siteListPg) {
				siteListPg.clickDateRangeAvailability();
			}
			rangeListPg.waitLoading();

			if(!siteNoProvided && !siteIDProvided) {
				//if site number is unknown, only when select first available site will make sense
				bd.siteID=rangeListPg.selectFirstAvailSite();
			} else if(bd.ignoreStatus) {//site availability is unknown
				//will click on site number like whatever the site status is
				if(siteNoProvided) {
					rangeListPg.clickSiteNum(bd.siteNo);
				} else {
					rangeListPg.clickSiteNum(Integer.parseInt(bd.siteID));
				}
			} else {//site is available
				boolean siteAvailable=false;

				if(siteNoProvided) 
					siteAvailable=rangeListPg.checkSiteAvailable(bd.siteNo);
				else 
					siteAvailable=rangeListPg.checkSiteAvailable(Integer.parseInt(bd.siteID));	

				if(siteAvailable) {
					if(bd.clickSiteNum) {//forced to go to site details page by click site# link
						if(siteNoProvided)
							rangeListPg.clickSiteNum(bd.siteNo);
						else
							rangeListPg.clickSiteNum(Integer.parseInt(bd.siteID));
					} else {//go to site details page by click first A link
						if(siteNoProvided)
							rangeListPg.selectAvailSiteBySiteNum(bd.siteNo);
						else
							rangeListPg.selectAvailSiteBySiteID(bd.siteID);
					}
				} else {
					throw new ItemNotFoundException("site is not available");
				}
			}
		} else {
			Object page = browser.waitExists(siteListPg, rangeListPg);
			if(page == rangeListPg){
				rangeListPg.clickCampsiteList();
				siteListPg.waitLoading();
			}

			if(!siteNoProvided && !siteIDProvided) {
				siteListPg.selectFirstAvailSite();
			} else {
				if(siteNoProvided && !siteIDProvided) {
					bd.siteID=siteListPg.getSiteID(bd.siteNo);
				}
				if(bd.ignoreStatus) {
					siteListPg.clickSiteNum(Integer.parseInt(bd.siteID));
				} else {
					boolean enterDateButtonDisplays = siteListPg.checkEnterDateForSite(bd.siteID);
					if(!enterDateButtonDisplays == bd.enterDateButtonDisplays){
						boolean seeDetailsButtonDisplays = siteListPg.checkSiteAvailable(Integer.parseInt(bd.siteID));
						if(!seeDetailsButtonDisplays == bd.seeDetailsButtonDisplays){
							throw new ItemNotFoundException("site is not available.site id is:"+bd.siteID+", site name is:"+bd.siteName);
						}
					}
					
					if(bd.clickSiteNum) {//forced to go to site details page by click site# link
						siteListPg.clickSiteNum(Integer.parseInt(bd.siteID));
					}else if(enterDateButtonDisplays) {//go to site details page by click see details link
						siteListPg.clickEnterDate(Integer.parseInt(bd.siteID));
					}else{
						siteListPg.clickSeeDetails(Integer.parseInt(bd.siteID));
					}
					
//					boolean siteAvailable=siteListPg.checkSiteAvailable(Integer.parseInt(bd.siteID));
//					if(siteAvailable) {
//						if(bd.clickSiteNum) {//forced to go to site details page by click site# link
//							siteListPg.clickSiteNum(Integer.parseInt(bd.siteID));
//						} else {//go to site details page by click see details link
//							siteListPg.clickSeeDetails(Integer.parseInt(bd.siteID));
//						}
//					} else {
//						throw new ItemNotFoundException("site is not available.site id is:"+bd.siteID+", site name is:"+bd.siteName);
//					}
					
				}
			}
		}

//		bd.siteID = "";  //1/30/2013 by Sara Wang. Please let me know if you have any question.

		if(isKOA) {
			UwpKOACampsiteDetailsPage koaCampsitedetailsPg=UwpKOACampsiteDetailsPage.getInstance();
			koaCampsitedetailsPg.waitLoading();
		} else {
			UwpCheckAvailabilityPage checkAvailabilityPg=UwpCheckAvailabilityPage.getInstance();
			Object page=browser.waitExists(checkAvailabilityPg,campsiteDetailPg);
			if(page==checkAvailabilityPg) {
				checkAvailabilityPg.setLengthOfStay(bd.lengthOfStay);
				checkAvailabilityPg.setArriveDate(bd.arrivalDate);
				if(bd.contractCode.equals("NRSO")) {
					if (campsiteDetailPg.isBookingOnRecGov()) {
						logger.info("Cross over from RA to RecGov.");
						campsiteDetailPg.clickBookTheseDays();
						return;//NRRS park need to cross over to RecGov
					}
				}
				checkAvailabilityPg.clickCheckAvailability();
			}
			campsiteDetailPg.waitLoading();

			if(bd.contractCode.equals("NRSO")) {
				if (campsiteDetailPg.isBookingOnRecGov()) {
					logger.info("Cross over from RA to RecGov.");
					campsiteDetailPg.clickBookTheseDays();
					return;//NRRS park need to cross over to RecGov
				}
			}
		}
	}
	
	/**
	 * Book site to order details from site details page
	 * @param isKOA
	 * @param equip
	 * @param equipLength
	 */
	public void bookSiteToOrderDetailsFromSiteDetails(boolean isKOA, String arrivalDate, String lengthOfStay, String equip , String equipLength){
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpSocialSecurityNumberRequiredPage ssnPg=UwpSocialSecurityNumberRequiredPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		UwpKOACampsiteDetailsPage koaCampsitedetailsPg=UwpKOACampsiteDetailsPage.getInstance();
		
		logger.info("Search site from site details page to order details page.");
	
		if(isKOA) {
			koaCampsitedetailsPg.setEquipment(equip, equipLength);
			koaCampsitedetailsPg.clickCheckAvailablility();
//			browser.waitDisappear(10, koaCampsitedetailsPg); Lesley[20131024]: will be still in camp site details page if there is an error. For TC OnlyOneResInCartRuleCheck_KOA 
		} else {
			prdDetailPg.checkAvailability(arrivalDate, lengthOfStay);
			prdDetailPg.clickBookTheseDays();
		}
			
		//wait for signInPg for middle sign in scenario
		Object page=browser.waitExists(orderDetails,ssnPg,signInPg, signInUpPg, koaCampsitedetailsPg);
		if(page == ssnPg) {
			ssnPg.setSocialSecurityNumber("078051120");
			ssnPg.clickContinue();
			browser.waitExists(orderDetails,signInPg, signInUpPg);
		}
	}
	
	public void bookSiteToOrderDetailsFromSiteDetails(boolean isKOA, String equip , String equipLength){
		this.bookSiteToOrderDetailsFromSiteDetails(isKOA, "", "", equip, equipLength);
	}
	
	public void bookSiteToOrderDetailsFromSiteDetails(String arrivalDate, String lengthOfStay){
		this.bookSiteToOrderDetailsFromSiteDetails(false, arrivalDate, lengthOfStay, "", "");
	}
	
	/**
	 * Search site from site list page to order details page.
	 * This method start at site list page and end at order detail page
	 * @param bd - bookingData
	 */
	public void bookSiteToOrderDetailPg(BookingData bd) {
		boolean isKOA=bd.contractCode.equals("KOA");
		
		logger.info("Search site from site list page to order details page.");
	    this.bookSiteToSiteDetails(bd);
	    this.bookSiteToOrderDetailsFromSiteDetails(isKOA, bd.arrivalDate, bd.lengthOfStay, bd.equip, bd.equipLength);
	}

	/**
	 * book a site from home page to shopping cart page.
	 * @param bd - bookingData data
	 * @param od - orderDetails data
	 * @param isDiscount - whether or not has discount
	 */
	public void makeReservationToCart(BookingData bd, OrderDetails od, boolean isDiscount) {
		this.bookParkToSiteListPg(bd);
		if(bd.contractCode.equalsIgnoreCase("KOA") && TestProperty.getProperty("fullCaseName").contains(".sanity.")) {
			this.bookSiteIntoCartForKOA(bd);
		} else {
			this.bookSiteToOrderDetailPg(bd);
			this.fillInOrderDetails(od, bd.contractCode, isDiscount);
		}
	}

	/**
	 * book a site from home page to shopping cart page.
	 * @param bd - bookingData data
	 * @param od - orderDetails data
	 */
	public void makeReservationToCart(BookingData bd, OrderDetails od) {
		makeReservationToCart(bd, od, false);
	}
	
	/**
	 * book a site from home page to shopping cart page.
	 * @param bd - bookingData data
	 */
	public void makeReservationToCart(BookingData bd) {
		OrderDetails od = new OrderDetails();
		makeReservationToCart(bd, od);
	}

	/** Book a site with POS from home page to shopping cart page */
	public void makeReservationWithPOSToCart(BookingData bd, OrderDetails od) {
		this.bookParkToSiteListPg(bd);// search park
		this.bookSiteToOrderDetailPg(bd); // search site
		this.checkPOSInOrderDetailsPg(od);
		this.fillInOrderDetails(od,bd.contractCode);
	}
	
	/**
	 * Book a site into order details page, this method start from state map page, ends at order details page.
	 * @param bd - booking data
	 * @param clickOnMap - is click on map flag
	 * @param isMapSearch - is search the park in Map Search section
	 */
	public void bookSiteFromMapToOrderDetails(BookingData bd, boolean clickOnMap, boolean clickNameLink, boolean isMapSearch) {
		UwpGoogleStateMapPage stateMap = UwpGoogleStateMapPage.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpParkDetailsCommonPage parkDetailPg = UwpParkDetailsCommonPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
		UwpCheckAvailabilityPage checkAvailabilityPg=UwpCheckAvailabilityPage.getInstance();
		UwpOrderDetailsPage orderDetails = UwpOrderDetailsPage.getInstance();
		UwpSocialSecurityNumberRequiredPage ssnPg=UwpSocialSecurityNumberRequiredPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpUnifiedSearchViewAsMapPage unifiedMapPg=UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		UwpDateRangeAvailabilityPage dateRangePg=UwpDateRangeAvailabilityPage.getInstance();
		
		logger.info("Book site from state map page to order details page.");
		Object page = browser.waitExists(unifiedMapPg,stateMap, siteListPg);

		if(page == stateMap) {
			if(!StringUtil.isEmpty(bd.switchToState)) {
				stateMap.selectState(bd.switchToState);//search parks by switch state
				stateMap.waitLoading();
			} else if(!StringUtil.isEmpty(bd.near)) {
				stateMap.setNear(bd.near);//search parks near by
				stateMap.clickGo();
				stateMap.waitLoading();
			}

			if(isMapSearch) {
				stateMap.clickMapSearch();
				searchPanel.waitLoading();
				searchPanel.setupSearchCriteria(bd);
				searchPanel.clickSearch();
				stateMap.waitLoading();
			}

			if(clickOnMap) {
				if(stateMap.isMapSearch()) {
					stateMap.clickAFromMapSeachView();
				} else {
					if(bd.contractCode.equalsIgnoreCase("NRSO")) {
						stateMap.clickMapFlagByParkTitle(bd.park+", "+bd.park.split("\\(")[1].replaceAll("\\)", ""));
					} else {
						stateMap.clickMapFlagByParkTitle(bd.park+", "+bd.contractCode);
					}
				}
				stateMap.enterDateWidgetWaitExists();
				stateMap.clickBookNowNext();
			} else {
				if(!stateMap.isMapSearch()) {
					clickNameLink = true;//only park name link available in Map Browse section
				}
				if(clickNameLink) {
					if(bd.contractCode.equalsIgnoreCase("NRSO")) {
						stateMap.clickParkFromMapBrowse(bd.park+", "+bd.park.split("\\(")[1].replaceAll("\\)", ""));
					} else {
						logger.info("click park name in the map browser list.. ");
						stateMap.clickParkFromMapBrowse(bd.park+", "+bd.contractCode);
					}
					parkDetailPg.waitLoading();
					if(bd.clickBookNow) {//click Book Now to go to site list page
						parkDetailPg.clickBookNow();
						siteListPg.waitLoading();
					}
				} else {
					stateMap.clickBookNowNext();
					siteListPg.waitLoading();
				}
			}
		} else if (page==unifiedMapPg) {
			//james[20130909] add step to click map pin
			unifiedMapPg.clickMapPin(bd.contractCode, bd.parkId);
//			unifiedMapPg.mapBubbleWidgetWaitExists();
			unifiedMapPg.waitForMapBubbleWidget();
			unifiedMapPg.clickBookSitesInWidget();
			page=browser.waitExists(dateRangePg,siteListPg);
			if(page==dateRangePg) {
				dateRangePg.gotoSiteList();
				siteListPg.waitLoading();
			}
		}
		
		//search site
		if(bd.clickEnterDate) {//will click on Enter Date in site list page, ends at campsite details page
//			siteListPg.clickPageresults();//Sara[3/24/2014], Can't click "Entry Date" button in site list page because of the panel when mouse is over site number link
			siteListPg.clickEnterDate(bd.siteNo);
			page = browser.waitExists(checkAvailabilityPg, prdDetailPg);//check availability page
			if(page == checkAvailabilityPg) {
				checkAvailabilityPg.setLengthOfStay(bd.lengthOfStay);
				checkAvailabilityPg.setArriveDate(bd.arrivalDate);
				checkAvailabilityPg.clickCheckAvailability();
			}
		} else {
			//			stateMap.clickParkFromMapBrowse(bd.park+", "+bd.contractCode);
			this.searchSiteFromDiffPanel(bd);
		}
		page=browser.waitExists(orderDetails, prdDetailPg);
		if(page==prdDetailPg){
			prdDetailPg.clickBookTheseDays();
		}

		//wait for signInPg for middle sign in scenario
		page=browser.waitExists(orderDetails, ssnPg, signInPg, signInUpPg);
		if(page == ssnPg) {
			ssnPg.setSocialSecurityNumber("078051120");
			ssnPg.clickContinue();
			browser.waitExists(orderDetails,signInPg, signInUpPg);
		}
	}
	
	/**
	 * Search site from search panel to site details page.
	 * this method can use for Camground Details, Campground Map, Campsite List and Date Range Availability
	 * @param bd - booking data
	 */
	public void searchSiteFromDiffPanel(BookingData bd) {
		UwpSiteSearchPanel siteSearch = UwpSiteSearchPanel.getInstance();
		UwpCampgroundMapPage campMap = UwpCampgroundMapPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpDateRangeAvailabilityPage rangeListPg = UwpDateRangeAvailabilityPage.getInstance();
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();

		logger.info("Search site to site details page.");
		siteSearch.waitLoading();
		siteSearch.searchSite(bd);
		
		if(campMap.exists()) {//James[20140402] this steps gone with the new UI
			//verify the expected site in Matching Campsites section when site is specific
			if(!StringUtil.isEmpty(bd.siteNo)) {
				if(!bd.siteNo.equalsIgnoreCase(campMap.getMatchingSiteNums().get(0))){
					throw new ItemNotFoundException("Given site "+bd.siteNo+" not found in campground map page.");
				}
			}
			//currently can not handle the object in Campground Map page
			//will go to Campsite List page or Date Range Availability page to book the site.
			if(bd.isRange) {
				campMap.clickDateRangeAvailability();
			} else {
				campMap.clickCampsiteList();
			}
		}

		if(bd.isRange && !bd.contractCode.equals("KOA")) {//KOA park will only go to site list page
			rangeListPg.waitLoading();
			if(StringUtil.isEmpty(bd.siteNo)) {
				//if site number is unknown, only when select first available site will make sense
				rangeListPg.selectFirstAvailSite();
			} else {
				if(bd.ignoreStatus) {//site availability is unknown
					//will click on site number like whatever the site status is
					rangeListPg.clickSiteNum(bd.siteNo);
				} else {//site is available
					if(bd.clickSiteNum) {//forced to go to site details page by click site# link
						rangeListPg.clickSiteNum(bd.siteNo);
					} else {//go to site details page by click first A link
						rangeListPg.selectAvailSiteBySiteNum(bd.siteNo);
					}
				}
			}
		} else {
			siteListPg.waitLoading();
			if(StringUtil.isEmpty(bd.siteNo)) {
				//when site number is unknown, only when select first available site will make sense
				siteListPg.selectFirstAvailSite();
			} else {
				if(bd.ignoreStatus) {
					//click on site number link whatever (ignore) the site status is
					siteListPg.clickSiteNum(bd.siteNo);
				} else {
					//search with loop to make sure only one specified site returned
					if(bd.clickSiteNum) {//forced to go to site details page by click site# link
						siteListPg.clickSiteNum(bd.siteNo);
					} else {//go to site details page by click see details link
						List<String> sitenums=siteListPg.getAllSiteNums();
						if(sitenums.contains(bd.siteNo)){
							siteListPg.clickSeeDetails(bd.siteNo);
						} else {
							throw new ItemNotFoundException("Given site "+bd.siteNo+" not found!!");
						}
					}
				}
			}
		}

		if(bd.contractCode.equals("KOA")) {
			UwpKOACampsiteDetailsPage koaCampsitedetailsPg=UwpKOACampsiteDetailsPage.getInstance();
			koaCampsitedetailsPg.waitLoading();
		} else {
			prdDetailPg.waitLoading();
		}
	}

	/**
	 * Go to state map page by click on map area in Map Home page.
	 * @param state - state name
	 */
	public void gotoStateMapPg(String state) {
		this.gotoStateMapPg(null, state, false, true);
	}

	
	/**
	 * query the total campsite number.
	 * @param parkName
	 * @param schema
	 * @return
	 */
	public int queryCampsiteTotalSitesNumber(String parkName, String schema) {
		int totalNumber = 0;

		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + "; get total site number");
		
		String query = "select count(*) as TOTAL " + "from P_PRD " +"where product_cat_id = 3 "
		+ "and active_ind = 1 " + "and deleted_ind = 0 " +"and IMPORT_WEB_RESERVABLE = 'Y' "
		+ "and park_id = " 
		+ "("
		+ "select id " + "from d_loc " + "where UPPER(name) = UPPER('" + parkName   + "')"
		+ ")";
		logger.debug("Execute query: " + query);
		List<String> result = db.executeQuery(query, "TOTAL");
		
		if (result.size() > 0) {
			totalNumber = Integer.parseInt(result.get(0));
		} else {
			throw new ErrorOnDataException("Did not find the customer type id.");
		}

		return totalNumber;
	}
	
	public List<SiteInfoData> queryCampsiteEachSiteTypeAndNumber(String parkName, String schema) {
		List<SiteInfoData> cpSiteInfos = new ArrayList<SiteInfoData>();

		db.resetSchema(schema);
		logger.debug("Reset schema as " + schema + "; get total site number");
		// UPPER(name) = UPPER('"+parkName+"')"
		String query = "SELECT P.PRD_GRP_ID, G.PRD_GRP_NAME AS SITETYPE, COUNT(*) AS NUMBEROFSITES " + "FROM P_PRD P, P_PRD_GRP G " +
		"WHERE P.PRD_GRP_ID=G.PRD_GRP_ID " + "AND P.PRODUCT_CAT_ID=3 " + "AND P.ACTIVE_IND=1 " + 
		"AND P.DELETED_IND=0 " + "AND P.IMPORT_WEB_RESERVABLE='Y' " + 
		"AND P.PARK_ID= " + "(" + "SELECT ID " + "FROM D_LOC WHERE UPPER(name) = UPPER('"+parkName+"')" + ") " +
		"AND G.ACTIVE_IND=1 AND G.DELETED_IND=0 " +
		"GROUP BY p.prd_grp_id, g.prd_grp_name order by g.prd_grp_name";
		logger.debug("Execute query: " + query);
		
		List<String> type = db.executeQuery(query, "SITETYPE");
		List<String> num = db.executeQuery(query, "NUMBEROFSITES");
		
		if (type.size()>0 && type.size() == num.size()) {
			for (int i =0; i < type.size(); i++){
				SiteInfoData temp = new SiteInfoData();
				temp.siteType = type.get(i);
				temp.siteTypeTotalNum = Integer.parseInt(num.get(i));
				cpSiteInfos.add(temp);
			}
		} else {
			throw new ErrorOnDataException("can't find the campsite type and number info based on given Park:" + parkName);
		}

		return cpSiteInfos;
	}
	
	/**
	 * Go to Unified Search panle by clicking campground map link or find camping spot link in RA, or home link on REC
	 * @param byMap
	 * @author Lesley
	 * @date Aug 16, 2013
	 */
	public void gotoUnifiedSearchPanel(boolean byMap) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		logger.info("Go to unified search panle page....");
		if (MiscFunctions.isRAEnv()) {// Lesley[08/16/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
			if (byMap) {
				headerBar.gotoCampgroudByMap();
			}
			else {
				headerBar.gotoFindCampSpot();
			}
			unifiedSearchPanel.waitLoading();
		} else {
			gotoHomePage();
		}
	}
	
	public void gotoUnifiedSearchPanel(){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		
		headerBar.clickHomeLink();
		searchPanel.waitLoading();
	}
	/**
	 * This method goes to state map page from home page.
	 * The work flow starts from head bar and ends at state map page.
	 * @param bd - booking data to be provided
	 * @param clickMapArea - is click on map directly
	 * @param clickStateName - is click on state name link
	 */
	public void gotoStateMapPg(BookingData bd, String state, boolean clickMapArea, boolean clickStateName) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpGoogleNationMapPage nationMap = UwpGoogleNationMapPage.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
		UwpGoogleStateMapPage stateMap = UwpGoogleStateMapPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage unifiedSuggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg=UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchViewAsMapPage unifiedMapPg=UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();

		logger.info("Go to State '"+state+"' Map page from home page via "+(clickMapArea?"clickMapArea":(clickStateName?"clickStateName":"do a normal search.")));
//		headerBar.clickHomeLink();
//		browser.waitExists(homePg,unifiedSearchPanel);
//		if(bd.isUnifiedSearch){
//		if((MiscFunctions.isRECEnv() && MiscFunctions.isRECUnifiedSearchOpen()) || 
//				(MiscFunctions.isRAEnv() && MiscFunctions.isRAUnifiedSearchOpen()) ||
		//				(!MiscFunctions.isRECEnv() && !MiscFunctions.isRAEnv() && MiscFunctions.isPLWUnifiedSearchOpen())){
		if(AwoUtil.isUnifiedSearch()){
			this.gotoUnifiedSearchPanel(true);// Lesley[08/16/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
			logger.info("Go through Unified search UI");
			unifiedSearchPanel.setupCampingSearchCriteria(bd);
			unifiedSearchPanel.clickSearch();
			Object page = browser.waitExists(unifiedParkListPg, unifiedSuggestionPg, unifiedMapPg);

			if (page == unifiedSuggestionPg){
				if(StringUtil.notEmpty(bd.parkId) && StringUtil.notEmpty(bd.contractCode)){
					unifiedSuggestionPg.clickFacilitySuggestion(bd.parkId, bd.contractCode);
				}else if (StringUtil.notEmpty(bd.park)){
					unifiedSuggestionPg.clickFacilitySuggestion(bd.park);
				}else unifiedSuggestionPg.clickFacilitySuggestion(bd.whereTextValue);

				//				unifiedParkListPg.waitExists();
				page = browser.waitExists(unifiedParkListPg, unifiedMapPg); //Lesley[08/16/2013]: update due to RA Unified Search in QA1/3 in 3.04.03
			}
			
//			unifiedParkListPg.clickMapPin(bd.park);		
//			unifiedMapPg.mapBubbleWidgetWaitExists();
			if (page == unifiedParkListPg) {
				unifiedParkListPg.clickMapPin(bd.park);
			} else {
				unifiedMapPg.clickMapPin(bd.contractCode, bd.parkId);
			}
			unifiedMapPg.mapBubbleWidgetWaitExists();
		}else{
			logger.info("Go through legacy search UI");
			headerBar.legacyGotoCampgroundByMap();
				
			Object page = browser.waitExists(nationMap, stateMap,siteListPg);
			if(page == nationMap) {
				if(clickMapArea) {
					nationMap.gotoStateMapByClickOnMap(state);
				} else if(clickStateName) {
					nationMap.gotoStateMapByClickStateLink(state);
				} else {
					searchPanel.setupSearchCriteria(bd);
					searchPanel.clickSearch();
				}
				stateMap.waitLoading();
	
				if(null!=state && state.length()>0) {
					if (!state.equalsIgnoreCase(stateMap.getSelectedState())) {
						throw new ItemNotFoundException("The map does NOT show the state selected!");
					}
				}
				//verify the specific park displays in Map Search section when search park in Map Home page
	
				if(!clickMapArea && !clickStateName) {
					if (!stateMap.isParkSearched(bd.park+", "+((bd.stateCode.trim().length()>0)?bd.stateCode:bd.contractCode))) {
						throw new ItemNotFoundException("The park did not show in map search section!");
					}
				}
			}
		}
	}

	/**
	 * Go to Unified Search Suggestion or Result page from Panel page
	 * The work flow starts from head bar by clicking "SEARCH FOR PLACES" menu, and ends at unified Search Home Page.
	 * @param whereValue
	 * @param selectAutoCompleteOption --true: select auto-complete option 
	 *                                 --false: un-select auto-complete option
	 */
	
	public void setWhereInUnifiedSearchPanel(String whereValue){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		RecgovSurveyPage recgSurverPg = RecgovSurveyPage.getInstance();
		logger.info("Set Where field value in Unified Search Panel.");
		
		//Handle surver page
		if(recgSurverPg.exists()){
			recgSurverPg.clickNoThanksButton();
			unifiedSearchPanel.waitLoading();
		}
		
		//Handle if not in Unified seach panel
		if(!unifiedSearchPanel.checkInterestInDropDownListExist()){
			headerBar.clickHomeLink();
			unifiedSearchPanel.waitLoading();
		}
		
		//Set Where value
		unifiedSearchPanel.clickClearSearch();
		unifiedSearchPanel.setWhere(whereValue);
	}
	
	public Object gotoUnifiedSearchSuggestionOrResultPage(String whereValue, boolean selectAutoCompleteOption){
		UwpUnifiedSearchHelpInfoPage searchHelpInfoPg = UwpUnifiedSearchHelpInfoPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage unifiedSearchSuggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage unifiedSearchResultPg = UwpViewAsListCommonPage.getInstance();
		logger.info("Go to Unified Search Suggestion or Result page from Panel page");
		
		setWhereInUnifiedSearchPanel(whereValue);
		
		if(selectAutoCompleteOption){
			unifiedSearchPanel.waitLoadingIconDismiss();		
			if(!unifiedSearchPanel.checkAutoCompleteBoxExist())	{
				unifiedSearchPanel.triggerAutoCompleteBoxDisplay();
				unifiedSearchPanel.waitAutoCompleteBox();
			}
			String value = unifiedSearchPanel.getFacilityOptions().get(0);
			unifiedSearchPanel.selectAutoCompleteBoxValue(value);
		}else{
			unifiedSearchPanel.removeFocus();
		}
			
		unifiedSearchPanel.clickSearch();
		return browser.waitExists(unifiedSearchSuggestionPg, unifiedSearchResultPg, searchHelpInfoPg);
	}
	
	public void gotoUnifiedSearchSuggestion(String whereValue){
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage unifiedSearchSuggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		logger.info("Go to Unified Search Suggestion page");
		
		setWhereInUnifiedSearchPanel(whereValue);
		unifiedSearchPanel.removeFocus();
		unifiedSearchPanel.clickSearch();
		unifiedSearchSuggestionPg.waitLoading();
	}
	
	/**
	 * Go to Unified Search Suggestion or Result page from Panel page
	 * @param whereValue
	 * @return unifiedSearchSuggestionPg or unifiedSearchResultPg
	 */
	public Object gotoUnifiedSearchSuggestionOrResultPage(String whereValue){
		return this.gotoUnifiedSearchSuggestionOrResultPage(whereValue, false);
	}

	/**
	 * Verify error message in Unified Search help information or Suggestion page from Panel page
	 * @param UwpUnifiedSearch unifiedSearch
	 * @param expectedErrorMes: The expect error message displays in Unified Search help information or Suggestion page
	 */
	public void verifyUnifiedSearchErrorMes(UwpUnifiedSearch unifiedSearch, String expectedErrorMes){
		UwpUnifiedSearchHelpInfoPage searchHelpInfoPg = UwpUnifiedSearchHelpInfoPage.getInstance();
		UwpUnifiedSearchSuggestionPage searchSuggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();

		String actualErrorMsg = "";
		Object page = this.makeUnifiedSearch(unifiedSearch);
		if(page == searchHelpInfoPg){
			actualErrorMsg = searchHelpInfoPg.getErrorMes();
		}else if(page == searchSuggestionPg){
			actualErrorMsg = searchSuggestionPg.getErrorMes();
		}else if(page == searchResult){
			actualErrorMsg = searchResult.getWaringMes();
		}else throw new ErrorOnPageException("No matched page be found.");

		logger.info("Verify error message in the page:"+page);
		if(!actualErrorMsg.equals(expectedErrorMes)){
			throw new ErrorOnPageException("The Actual error message ---" +actualErrorMsg+ "--- " +
					"doesn't equal to the Expect one ---" +expectedErrorMes+ "---");
		}
	}

	/**
	 * Make Unified Search from Unified Search Panel(Click Search For Places sub tab from top header)
	 * @param dayUse
	 */
	public Object makeUnifiedSearch(UwpUnifiedSearch unifiedSearch){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		RecgovHomePage homePg  = RecgovHomePage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchHelpInfoPage helpPage=UwpUnifiedSearchHelpInfoPage.getInstance();
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();

		logger.info("Make Unified Search...");
		Object page = null;
		headerBar.waitLoading();

		if(!searchPanel.exists()){
			headerBar.clickHomeLink();
			searchPanel.waitLoading();
		}

		searchPanel.setupUnifiedSearch(unifiedSearch);

		if(unifiedSearch.clickSearch){
			if(!searchPanel.isInterestInMenuBar())
				searchPanel.removeFocus();
			searchPanel.clickSearch();
		}else if(unifiedSearch.clickClearSearch){
			searchPanel.clickClearSearch();
			if(!searchPanel.isInterestInMenuBar())
				searchPanel.removeFocus();//Sara[]
		}
		Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
		browser.waitExists();
		Browser.sleep(DYNAMIC_SLEEP_BEFORE_CHECK);
		page = browser.waitExists(helpPage,searchResult,suggestionPg, homePg, mapPg);

		return page;
	}
	
	public void verifyArrivalDateWidgetExist(boolean flag){
		UwpCalendarPage page=UwpCalendarPage.getInstance();
		if(page.checkArrivalDateWidgetExist()!=flag){
			throw new ObjectNotFoundException("Arrival Date widget object should "+(flag?"":" not ")+" existing.");
	    }
	}
	
	/**
	 * Verify expected page exist
	 * @param expectedPg: UnifiedSearchHelpInfoPage, UnifiedSearchSuggestionPage, UnifiedParkViewAsListPage
	 */
	public void verifyExpectedPgExist(Object expectedPg){
		UwpUnifiedSearchHelpInfoPage searchHelpPg = UwpUnifiedSearchHelpInfoPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage parkViewAsList = UwpViewAsListCommonPage.getInstance();
		RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
		
		logger.info("Start to verify expected page:"+expectedPg+" exist...");
		Object page = browser.waitExists(searchHelpPg, suggestionPg, viewAsList, parkViewAsList);
		
		String expectedPageName = ((Page)expectedPg).getName();
		if(page != expectedPg) {
			throw new ErrorOnDataException("Expected page can't be found. Expected page: " + expectedPageName + ", Actual page: " + ((Page)page).getName());
		} else {
			logger.info("Successfully verify page: "  + expectedPageName + " exists.");
		}
	}
	
	/**
	 * Go to "Explore and more" page
	 */
	public void gotoExploreAndMore(){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		RecgovExploreAndMorePage exploreAndMore = RecgovExploreAndMorePage.getInstance();
		
		logger.info("Go to 'Explore and more' page from header bar.");
		headerBar.waitLoading();
		browser.waitExists();
		headerBar.gotoExploreAndMore();
		exploreAndMore.waitLoading();
	}
	
	/**
	 * Go to 'Explore activity' page from 'Explore and more' page.
	 * @param selectedActivity
	 */
	public void gotoExploreActivityPageFromExploreAndMorePage(String selectedActivity){
		ActivitiesDetailsPage activitiesDetailsPg = ActivitiesDetailsPage.getInstance();
		RecgovExploreAndMorePage exploreAndMorePg = RecgovExploreAndMorePage.getInstance();
		
		logger.info("Go to 'Explore activity' page from 'Explore and more' page.");
		exploreAndMorePg.clickExploreWays(selectedActivity);
		activitiesDetailsPg.waitLoading();
		activitiesDetailsPg.waitExploreActivitiesLoadingComplete();
	}
	
	/**
	 * Go to 'Explore activity' page from header bar
	 * @param selectedActivity
	 */
	public void gotoExploreActivityFromHeaderBare(String selectedActivity){
		this.gotoExploreAndMore();
		this.gotoExploreActivityPageFromExploreAndMorePage(selectedActivity);
	}
	
	public void findNearByLocationOrZipCode(String locationOrZipCode, boolean pickUpAutoCompletedBoxValue, Page expectedPage){
		ActivitiesDetailsPage activitiesDetailsPg = ActivitiesDetailsPage.getInstance();
		
		logger.info("Find near location or zip code from 'Explore Activite details page'.");
		activitiesDetailsPg.focusLocationOrZipCode();
		activitiesDetailsPg.setLocationOrZipCode(locationOrZipCode);
		if(pickUpAutoCompletedBoxValue){
			activitiesDetailsPg.triggerAutoCompleteBoxDisplay(locationOrZipCode);
			activitiesDetailsPg.selectAutoCompleteBoxValue(locationOrZipCode);
		}
		activitiesDetailsPg.clickFindNearby();
		Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
		expectedPage.waitLoading();
	}
	
	/**
	 * Retrieve web build number and put it in property.
	 *
	 * @param url
	 *            - url
	 */
	public void getWebBuildNumber(String url, boolean isNewBrowser) {
		UwpAdminPage uwpAdminPg = UwpAdminPage.getInstance();
		AdiminDoDialogPage dialogPg = AdiminDoDialogPage.getInstance();

		logger.info("Retrieving web build number from " + url);

		if (isNewBrowser) {
			browser.open("about:blank");
			browser.load(url);
		} else {
			browser.load(url);
		}
		dialogPg.waitLoading();
		dialogPg.login(TestProperty.getProperty("web.admin.user"),
				TestProperty.getProperty("web.admin.pw"));
		uwpAdminPg.waitLoading();
		uwpAdminPg.clickSystem();
		uwpAdminPg.waitForRefresh();

		String bn = uwpAdminPg.getWebBuildNumber();
		TestProperty.putProperty("web.build", bn);

		logger.info("Web Build# is " + bn);
	}
	
	/**
	 * Verify whether the rule take effect to the Web sales channel - verify at Order Confirmation Page
	 * @param ruleName
	 * @param errorMsg
	 * @param isNegatively
	 * @return error message/ order number
	 */
	public String verifyBusinessRuleEffectiveAtOrderConfirmationPage(String ruleName, String errorMsg, boolean isNegatively ) {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		UwpCheckoutPage checkoutPg = UwpCheckoutPage.getInstance();
		UwpConfirmationPage confirmPg = UwpConfirmationPage.getInstance();
		Payment pay = new Payment("Visa");
		
		logger.info("Check out cart to confirmation page.");
		shoppingCart.clickCheckOutShoppingCart();
		checkoutPg.waitLoading();
		checkoutPg.setupPayment(pay);
		checkoutPg.selectAcknowlegeAcceptedon();
		checkoutPg.clickCompleteThisPurchase();
		confirmPg.waitLoading();
		
		logger.info("Verify whether the " + ruleName + " Rule take effect in Web sales channel.");
		String msg = "";
		msg = confirmPg.getErrorMsg();
		if(isNegatively){
			confirmPg.verifySuccess();
			msg = confirmPg.getAllResNo();
			logger.info("----As negatively, the " + ruleName + " Rule doesn't take effect to sales channel - Web.");
		}else{
			if(msg == null || "".equalsIgnoreCase(msg)) {
				throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect.");
			}
			if(!msg.contains(errorMsg)) {
				throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect.");
			}
			logger.info("----The " +ruleName + " Rule take effect in sales channel - Web successfully.");
		}
		
		return msg;
	}
	
	/**
	 * Go to 'Change Reservation details' page from 'Reservation Details'page
	 * Start page: 'Reservation Details'page
	 * End page: 'Change Reservation details' page
	 */
	public void gotoChangeTourResDetailsFromResDetailsPg(){
		UwpTourReservationDetailsPage resDetailsPg = UwpTourReservationDetailsPage.getInstance();
		UwpChangeTourReservationDetailsPage updateResDetailPg = UwpChangeTourReservationDetailsPage.getInstance();
		
		logger.info("Click 'Change reservation' link to 'Change Reservation Details' page from 'Reservation Details'page");
		resDetailsPg.clickChangeDetailsLink();
		updateResDetailPg.waitLoading();
	}
	
	public void gotoChangeTourResDetailsFromResDetailsPg(int changeLinkIndex) {
		UwpTourReservationDetailsPage resDetailsPg = UwpTourReservationDetailsPage.getInstance();
		UwpChangeTourReservationDetailsPage updateResDetailPg = UwpChangeTourReservationDetailsPage.getInstance();
		
		logger.info("Click 'Change reservation' link #" + changeLinkIndex + " to 'Change Reservation Details' page from 'Reservation Details'page");
		resDetailsPg.clickChangeDetailsLink(changeLinkIndex);
		updateResDetailPg.waitLoading();
	}
	
	/**
	 * Go to 'Find Permits' page from 'View As List' page.
	 * @param contractCode
	 * @param parkId
	 */
	public void gotoFindPermitsPanelFromViewAsListPage(String contractCode, String parkId){
		UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		logger.info("Go to 'Find Permits' page from 'View As List' page." );

		searchResult.clickCheckAvailability(parkId, contractCode);
		bwSearchPanel.waitLoading();
	}
	
	/**
	 * Go to facility details page from view as list page
	 * @param contractCode
	 * @param parkId
	 */
	public void gotoFacilityDetailsPageFromViewAsListPage(String contractCode, String parkId){
		UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();
		UwpParkDetailsCommonPage campGroundDetailsPg = UwpParkDetailsCommonPage.getInstance();
		RAParkDetailsPageForCampAndMarina parkDetailsPg = RAParkDetailsPageForCampAndMarina.getInstance();
		logger.info("Go to 'Facility Details' page from 'View As List' page click the park with contractCode:"+contractCode+", parkId:"+parkId );

		searchResult.clickParkName(contractCode, parkId);
		if(parkDetailsPg.exists())
			parkDetailsPg.clickCheckAvailability();
		
		campGroundDetailsPg.waitLoading();
	}
	
	/**
	 * Go to page(facility details page) from view as list page clicking first park name
	 */
	public void gotoProducteDetailsPageFromViewAsListPage(Page finalPage){
		UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();
		logger.info("Go to "+finalPage+" from 'View As List' page clicking first page name." );

		searchResult.clickFirstParkName();
		finalPage.waitLoading();
	}
	
	/**
	 * Go to page(facility details page) from view as list page clicking first park name
	 * @param contractCode
	 * @param parkId
	 */
	public void gotoProducteDetailsPageFromViewAsMapPage(Page finalPage, String contractCode, String parkId){
		UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		logger.info("Go to "+finalPage+" from 'View As Map' page clicking first page name." );
		
		viewAsMapPg.waitFirstMapPinDisplays(contractCode, parkId);
		viewAsMapPg.clicFirstkMapPin(contractCode);
		if(StringUtil.notEmpty(contractCode) && StringUtil.notEmpty(parkId)){
			viewAsMapPg.clickMapPin(contractCode, parkId);
		}else viewAsMapPg.clicFirstkMapPin(contractCode);
		
		Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
//		viewAsMapPg.mapBubbleWidgetWaitExists();
		viewAsMapPg.waitForMapBubbleWidget();
		viewAsMapPg.clickParkNameInWidget();
		
		finalPage.waitLoading();
	}
	
	public void gotoProducteDetailsPageFromViewAsMapPage(Page finalPage){
		gotoProducteDetailsPageFromViewAsMapPage(finalPage, StringUtil.EMPTY, StringUtil.EMPTY);
	}
	
	/**
	 * Go back to view as list page from site list page
	 * @param contractCode
	 * @param parkId
	 */
	public void goBackToViewAsListPageFromSiteListPage(String contractCode, String parkId){
		UwpProductListCommonPage siteListPg = UwpProductListCommonPage.getInstance();
		UwpViewAsListCommonPage searchResult = UwpViewAsListCommonPage.getInstance();
		logger.info("Go back to view as list page from site list page.");
		
		siteListPg.clickParksNearbyLink(contractCode, parkId);
		searchResult.waitLoading();
	}
	
    /**
     * Go to permit area details page from view as map page
     * @param contractCode
     * @param parkId
     */
	public void gotoPermitAreaDetailstPageFromViewAsMapPage(String contractCode, String parkId){
		 UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		 RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		 logger.info("Go to 'Permit Area Details' page from 'View As Map' page.");
		 
		viewAsMapPg.clickMapPin(contractCode, parkId);
		viewAsMapPg.mapBubbleWidgetWaitExists();
		viewAsMapPg.waitForParkNameInWidget();
		viewAsMapPg.clickParkNameInWidget();
		permitAreaDetailsPg.waitLoading();
	}
	
	/**
	 * In camp ground details page, click 'Find Other Facilities' link
	 * @param finalPage
	 */
	public void findOtherFacilities(Page finalPage){
		UwpParkDetailsCommonPage campGroundDetailPg = UwpParkDetailsCommonPage.getInstance();
		logger.info("Go to "+finalPage+" from UwpCampgroundDetailPage after clicking 'Find Other Facilities' link." );

		campGroundDetailPg.clickFindOtherFacilities();
		finalPage.waitLoading();
	}
	
	public void backToSearch(Page finalPage){
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		logger.info("Go to "+finalPage+" from UwpCampgroundDetailPage after clicking 'Find Other Facilities' link." );

		recreationAreaDetailsPg.clickBackToSearch();
		finalPage.waitLoading();
	}
	
	/**
	 * Go to "Find Tours" page from "Tour Details" page
	 */
	public void findOtherTours(){
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
//		UwpTourParkListPage tourList = UwpTourParkListPage.getInstance();
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpTourListPage tourList = UwpTourListPage.getInstance();
		logger.info("Go to 'Find Tours' from UwpTourDetailsPage after clicking 'Find Other Tour' link." );

		tourDetails.clickFindOtherTour();
		tourList.waitLoading();
		tourSearch.waitLoading();
	}
	
	/**
	 * Goto login page clicking "Log in" link from member status bar
	 */
	public void gotoLogInPage(){
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInOrUpPg = UwpSignInSignUpPage.getInstance();
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		
		logger.info("Goto login page clicking 'Log in' link from member status bar");
		memberStatusBar.gotoSignIn();
		browser.waitExists(signInPg, signInOrUpPg);
	}
	
	/**
	 * Go to view as list page from search panel according to click search button
	 */
	public void gotoViewAsListPageFromSearchPanel(){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpViewAsListCommonPage viewAsListPg = UwpViewAsListCommonPage.getInstance();

		logger.info("Go to view as list page from search panel according to only click search button in search panel.");
		searchPanel.clickSearch();
		viewAsListPg.waitLoading();
	}
	
	/**
	 * Click the "Find Other Facilities" link on campground details page, The work flow start from Camp ground details page, ends at unifSearchResults.do Or unifSearch.do page.
	 */
	public void gotoViewAsListPage(){
		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchHelpInfoPage unifiedHelpPg = UwpUnifiedSearchHelpInfoPage.getInstance();
		campDetails.clickFindOtherFacilities();
		browser.waitExists(unifiedParkListPg, unifiedHelpPg);
	}
	
	/**
	 * Goto view as map from view as list page
	 */
	public void gotoViewAsMapFromViewAsList(){
		UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		logger.info("Go to view as map page from view as list page.");
		unifiedParkListPg.clickViewAsMap();
		Browser.sleep(3);
		unifiedMapPg.waitLoading();
		unifiedMapPg.waitMapLoadingComplete();
	}
	
	/**
	 * Goto view as list page/help page via home page unified search panel.
	 * @param bd
	 */
	public void gotoViewAsListPage(UwpUnifiedSearch bd){
		UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		
		Object obj = this.makeUnifiedSearch(bd);
		if(obj == unifiedParkListPg){
			logger.info("Successfully go to view as list page.");
		}else if(obj == suggestPg){
			if(!StringUtil.isEmpty(bd.parkId)&& !StringUtil.isEmpty(bd.contractCode)){
				suggestPg.clickFacilitySuggestion(bd.parkId , bd.contractCode);
			}else{
				if(bd.selectExactOption){
					suggestPg.clickExtraSuggestion(bd.whereTextValue);
				}else{
					suggestPg.clickFacilitySuggestion(bd.whereTextValue);
				}
			}
			Object objPage =browser.waitExists(unifiedParkListPg, unifiedMapPg);
			Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
			if (objPage ==unifiedMapPg){
				unifiedMapPg.clickViewAsList();
				unifiedParkListPg.waitLoading();
			}
		}else throw new PageNotFoundException("Go to incorrect page.");
		
	}
	
	public void gotoInteragencyPassDetailsPgFromViewAsListPg(String contractCode, String parkId){
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
		RecgovParkDetailsPage parkDetailsPg = RecgovParkDetailsPage.getInstance();
		RecgovInteragencyPassSpotPage interagencyPassSpotPg = RecgovInteragencyPassSpotPage.getInstance();
		RecgovInteragencyPassDetailsPage interagencyPassDetailsPg = RecgovInteragencyPassDetailsPage.getInstance();
		
		logger.info("Go to interagency pass details page from view as list page when parkID:"+parkId+" and contractCode:"+contractCode);
		viewAsListPg.clickParkName(contractCode, parkId);
		parkDetailsPg.waitLoading();
		interagencyPassSpotPg.waitLoading();
		
		interagencyPassSpotPg.clickInteragencyPassSpotImg();
		interagencyPassDetailsPg.waitLoading();
	}
	
	public void purchaseInteragencyPassToShoppingCartFromDetailsPg(Data<InteragencyPassAttr> interagencyPass, String email, String password){
		RecgovInteragencyPassDetailsPage interagencyPassDetailsPg = RecgovInteragencyPassDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCartPg = UwpShoppingCartPage.getInstance();
		UwpCrossOverToSignInSignUpPage signInOrUpPg = UwpCrossOverToSignInSignUpPage.getInstance();
		
		logger.info("Go to shopping cart page from interagency pass details page.");
		interagencyPassDetailsPg.setInteragencyPassInfo(interagencyPass);
		interagencyPassDetailsPg.clickAddToShoppingCartButton();
		
		//Sara[20140711] in interagency pass details page, after click add to sopping cart button, sign in or sign up page occurs
		if(signInOrUpPg.exists()){
			signInOrUpPg.signIn(email, password);
		}
	
		shoppingCartPg.waitLoading();
	}
	
	public void gotoCapeHatterasOffRoadVehiclePermitDetailsPg(String parkName){
		UwpViewAsListCommonPage unifiedParkListPg=UwpViewAsListCommonPage.getInstance();
		RecgovCapeHattterasORVPermitSaleOverviewPage capeHattterasOffRoadVehiclePermitPg = RecgovCapeHattterasORVPermitSaleOverviewPage.getInstance();
		RecgovCapeHattterasORVPermitSalePage capeHattterasOffRoadVehiclePermitDetailsPg = RecgovCapeHattterasORVPermitSalePage.getInstance();
		
		logger.info("Go to cape hatteras off road wehicle permit details page from view as lsit page.");
		unifiedParkListPg.clickParkName(parkName);
		capeHattterasOffRoadVehiclePermitPg.waitLoading();

		capeHattterasOffRoadVehiclePermitPg.clickPurchaseOrvPermits();
		capeHattterasOffRoadVehiclePermitDetailsPg.waitLoading();
	}
	
	/**
	 * Goto view as map page via unified search panel.
	 * @param bd
	 */
	public void gotoViewAsMapPage(UwpUnifiedSearch bd){
		this.gotoViewAsListPage(bd);
		this.gotoViewAsMapFromViewAsList();
	}
	
	public void gotoViewAsListPageFromViewAsMap() {
		UwpUnifiedSearchViewAsMapPage mapPage = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
		logger.info("Go to View as List page from view as map page...");
		mapPage.clickViewAsList();
		unifiedParkListPg.waitLoading();
	}
	
	/**
	 * Go to page in XML Format
	 * @param url
	 * @author Lesley Wang
	 * @date Nov 5, 2012
	 */
	public void openPageInXMLFormat(String url) {
		logger.info("Go to page in XML Format: " + url);
		this.invokeURL(url);
		browser.waitExists(CampgroundDetailXMLOutputPage.getInstance(url), 
				CampsiteDetailXMLOutputPage.getInstance(url), 
				CampSearchResultXMLOutputPage.getInstance(url));
		System.out.println(21);
	} 
	
	/**
	 * Go to MA passes page from MA marketing spot page
	 */
	public void gotoMaPassedPageFromMaMarketingSpotPage(){
		UwpMaMarketingSpotPage maMarketingSpotPg = UwpMaMarketingSpotPage.getInstance();
		UwpMaPassesPage maPassedPg = UwpMaPassesPage.getInstance();
		
		logger.info("Go to 'MA Passes' page from 'MA Marketing Spot' page");
		maMarketingSpotPg.clickMaMarketingSpot();
		maPassedPg.waitLoading();
	}
	
	public void gotoSerializePasseDetailsPgFromHomePage(boolean isAnnualDayUsePass){
		UwpSerializePassSpotPage serializePassSpotPg = UwpSerializePassSpotPage.getInstance();
		UwpSerializePassDetailsPage serializePassDetailsPg = UwpSerializePassDetailsPage.getInstance();
		
		logger.info("Go to serialize pass from home page");
		gotoHomePage();
		if(isAnnualDayUsePass){
			serializePassSpotPg.clickAnnualDayUseImg();
		}else serializePassSpotPg.clickAnnualCampingPassImg();
		serializePassDetailsPg.waitLoading();
	}
	
	/**
	 * Back to previous page from MA Passes page
	 * @param finalPage: UwpCampgroundDetailPage, UwpCampsiteListPage, UwpDateRangeAvailabilityPage and UwpCampgroundMapPage
	 * @param isClickingUpplerLeftReturingLink
	 */
	public void backToPreviousPageFromMaPassesPage(Page finalPage, boolean isClickingUpplerLeftReturingLink){
		UwpMaPassesPage maPassedPg = UwpMaPassesPage.getInstance();
		
		if(isClickingUpplerLeftReturingLink){
			maPassedPg.clickUpperLeftBackToPreviousPageLink();
		}else{
			maPassedPg.clickBottomBackToPreviousPageLink();
		}
		
		finalPage.waitLoading();
	}
	
	/**
	 * Book MA Pass to shopping cart page from MA pass page
	 * @param isResidentPass: true: Select pass "Massachusetts Resident"
	 *                        false: Select pass "Non Massachusetts Resident"
	 * @param isBuyingASecondCarSticker: true: Select "Buy a second car sticker" check box
	 *                                   false: Doesn't select "Buy a second car sticker" check box
	 */
	public void bookMaPassToCartFromMaPassPage(MaPassInfo mapass) {
		UwpMaPassesPage maPassedPg = UwpMaPassesPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		
		logger.info("Book Ma pass to shopping cart page from ma pass page.");
		maPassedPg.setMaPassesInfo(mapass);
		maPassedPg.clickAddToShoppingCartButton();
		
		shoppingCart.waitLoading();
	}
	
	public void bookSerializePassToCartFromDetailsPg(Data<SerializePassAttr> serializePass) {
		UwpSerializePassDetailsPage serializePassDetailsPg = UwpSerializePassDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		
		logger.info("Book Serialize pass to shopping cart page from details page.");
		serializePassDetailsPg.setupPassInfo(serializePass);
		serializePassDetailsPg.clickAddToShoppingCart();
		shoppingCart.waitLoading();
	}
	
	/**
	 * Book MA Pass to shopping cart page from any page having "MA pass spot"
	 */
	public void makeMaPassesToCart(MaPassInfo mapass) {
		this.gotoMaPassedPageFromMaMarketingSpotPage();
		this.bookMaPassToCartFromMaPassPage(mapass);
	}
	
	public void makeSerializePassesToCart(Data<SerializePassAttr> serializePass) {
		this.gotoSerializePasseDetailsPgFromHomePage(serializePass.booleanValue(SerializePassAttr.isAnnualDayUsePass));
		this.bookSerializePassToCartFromDetailsPg(serializePass);
	}
	
	/**
	 * Book MA Pass to shopping cart page from after signing 
	 * @param bd
	 * @param isResidentPass: true: Select pass "Massachusetts Resident"
	 *                        false: Select pass "Non Massachusetts Resident"
	 * @param isBuyingASecondCarSticker: true: Select "Buy a second car sticker" check box
	 *                                   false: Doesn't select "Buy a second car sticker" check box
	 */
	public void makeMaPassesToCart(BookingData bd, MaPassInfo maPass) {
		this.bookParkToSiteListPg(bd);
		this.gotoMaPassedPageFromMaMarketingSpotPage();
		this.bookMaPassToCartFromMaPassPage(maPass);
	}
	
	/**
	 * Update TPAs information from Change Tour Reservation details page, and end at tour reservation details page
	 * @param tour
	 * @author Lesley Wang
	 * Jan 24, 2013
	 */
	public void updateTpaInfomationFromChangeResDetailsPageToResDetailsPage(TicketInfo tour) {
		UwpChangeTourReservationDetailsPage changeTourResDetailsPg = UwpChangeTourReservationDetailsPage.getInstance();
		UwpTourReservationDetailsPage resDetailsPg = UwpTourReservationDetailsPage
				.getInstance();
		logger.info("Update TPAs information from Change Tour Reservation detials page...");
		changeTourResDetailsPg.setPerTicketTourParticipantAttributes(tour.ticketTypes, tour.perTicketTPAsList, tour.contractCode);
		changeTourResDetailsPg.setPerInventoryTourParticipantAttributes(tour.perInventoryTPAs);
		changeTourResDetailsPg.clickChangeTourReservationDetailsBtn();
		resDetailsPg.waitLoading();
	}
	
	/**
	 * Update TPA information on Change Tour Reservation Details page. 
	 * The workflow starts from current reservations page, ends at reservation details page
	 * @param ordNum
	 * @param ticket
	 * @author Lesley Wang
	 * Jan 24, 2013
	 */
	public void updateTicketTPAsInWeb(String ordNum, TicketInfo ticket) {
		logger.info("Go to ticket reservation details page to change ticket TPA info for ordNum=" + ordNum);
		this.gotoCurrentReservationsPage();
		this.gotoReservationDetailPage(ordNum, ticket.contractCode);
		this.gotoChangeTourResDetailsFromResDetailsPg();
		this.updateTpaInfomationFromChangeResDetailsPageToResDetailsPage(ticket);
	}
	
	/**
	 * Go to tour order details page from shopping cart page by clicking change details link
	 * 
	 * @author Lesley Wang
	 * Feb 17, 2013
	 */
	public void gotoTourOrderDetailsPgFromShoppingCartPg() {
		UwpOrderDetailsPage orderDetailsPg = UwpOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		logger.info("Go to tour order details page from shoppoing cart page by clicking the first Change Details link...");
		shoppingCart.gotoOrderDetailFirstItem();
		orderDetailsPg.waitLoading();
	}
	
	/**
	 * Go to 'Create Availability Notification' page from camping site details page
	 */
	public void gotoCreateAvailabilityNotificationPageFromSiteDetailsPage(){
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
	  	UwpCreateAvailabilityNotificationPage createNotifyPg=UwpCreateAvailabilityNotificationPage.getInstance();
	  	
	  	logger.info("Go to 'Create Availability Notification' page from camping site details page.");
	  	prdDetailPg.clickCreateAvailabilityNotificationLink();
	  	createNotifyPg.waitLoading();
	}
	
	/**
	 * Go to 'Create Availability Notification' page from campground map page
	 */
	public void gotoCreateAvailNotifPgFromCampMapPg(){
		UwpCampgroundMapPage mapPg = UwpCampgroundMapPage.getInstance();
	  	UwpCreateAvailabilityNotificationPage createNotifyPg=UwpCreateAvailabilityNotificationPage.getInstance();
	  	
	  	logger.info("Go to 'Create Availability Notification' page from campground map page.");
	  	mapPg.clickCreateAvailNotifLink();
	  	createNotifyPg.waitLoading();
	}
	
	/**
	 * Go to camping site details page from 'Create Availability Notification' page
	 */
	public void goBackToSiteDetailsPageFromCreateAvailabilityNotificationPage(){
		UwpProductDetailsCommonPage prdDetailPg = UwpProductDetailsCommonPage.getInstance();
	  	UwpCreateAvailabilityNotificationPage createNotifyPg=UwpCreateAvailabilityNotificationPage.getInstance();
	  	
	  	logger.info("Go to camping site details page from 'Create Availability Notification' page.");
	  	createNotifyPg.clickBackToPrevious();
	  	prdDetailPg.waitLoading();
	}
	
	/**
	 * Go to camping site list page from 'Create Availability Notification' page
	 */
	public void goBackToCampsiteListPgFromCreateAvailNotifPg(){
		UwpProductListCommonPage campListPg = UwpProductListCommonPage.getInstance();
	  	UwpCreateAvailabilityNotificationPage createNotifyPg=UwpCreateAvailabilityNotificationPage.getInstance();
	  	
	  	logger.info("Go to camping site list page from 'Create Availability Notification' page...");
	  	createNotifyPg.clickBackToPrevious();
	  	campListPg.waitLoading();
	}
	
	/**
	 * Go to print this page from tour reservation details page
	 * @param resId
	 */
	public void printToPrintThisPgFromTourResDetailsPg() {
		UwpTourReservationDetailsPage tourResPg = UwpTourReservationDetailsPage.getInstance();
		RecgovPrintThisPage printThisPg = RecgovPrintThisPage.getInstance();
		tourResPg.clickPrintTicketsBtnExist();
		printThisPg.waitLoading();
	}
	public void reprintToPrintThisPgFromTourResDetailsPg() {
		UwpTourReservationDetailsPage tourResPg = UwpTourReservationDetailsPage.getInstance();
		RecgovPrintThisPage printThisPg = RecgovPrintThisPage.getInstance();
		tourResPg.clickReprintTicketsBtnExist();
		printThisPg.waitLoading();
	}
	
	public void finishPrintFunctionFromPrintThisPg(boolean clickPrintButton){
		UwpTourReservationDetailsPage tourResPg = UwpTourReservationDetailsPage.getInstance();
		RecgovPrintThisPage  printThisPage=RecgovPrintThisPage.getInstance();
		PrintDialogPage  printDialogPage=PrintDialogPage.getInstance();

		logger.info("Finish print function from 'Print This Page' to 'Reservation Details'");
		printThisPage.clickPrintThispage();
		printDialogPage.setDismissible(false);
		printDialogPage.waitLoading();
		if(clickPrintButton){
			printDialogPage.clickPrint();
		}else{
			printDialogPage.clickCancel();
		}
		printThisPage.waitLoading();
		printThisPage.clickDone();
		tourResPg.waitLoading();
	}
	
	public void gotoRegionalMapPgFromCampgroundDetailsPg(){
		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		UwpGoogleStateMapPage mapPage = UwpGoogleStateMapPage.getInstance();
		
		logger.info("Go to regional map page from campground details page.");
		campDetails.clickFacilityGoogleMap();
		mapPage.waitLoading();
		mapPage.waitMapFinishLoading();
	}
	
	public void gotoViewAsMapFromCampgroundDetailsPg(){
		UwpParkDetailsCommonPage campDetails = UwpParkDetailsCommonPage.getInstance();
		UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		logger.info("Go to view as map page from campground details page.");
		campDetails.clickFacilityGoogleMap();
		viewAsMapPg.waitLoading();
		viewAsMapPg.waitMapLoadingComplete();
	}
	
	public void gotoFeeAndCancellationPgFromFacilityDetailsPg(){
		UwpParkDetailsCommonPage parkDetailsPg = UwpParkDetailsCommonPage.getInstance();
		UwpFeeAndCancellationPoliciesPage feeAndCancellationPolicyPg = UwpFeeAndCancellationPoliciesPage.getInstance();
		logger.info("Go to fee and cancellation policy page from park details page.");
		parkDetailsPg.clickFeesAndCancellation();
		feeAndCancellationPolicyPg.waitLoading();
	}
	
	/**The following methods are about ticket lottery */
	/** Book park to ticket lottery application page */
	public void bookTicketToLotteryApplicationPg(TicketInfo bd) {
		UwpTicketLotteryApplicationPage appPg = UwpTicketLotteryApplicationPage.getInstance();
		UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
		RAParkDetailsPageForCampAndMarina parkDetailsPg = RAParkDetailsPageForCampAndMarina.getInstance();
		
		logger.info("Go to ticket lottery application page from home page...");
		this.gotoViewAsListPage(bd);
		unifiedParkListPg.clickParkName(bd.contractCode, bd.parkId);
		if(parkDetailsPg.exists())
			parkDetailsPg.clickCheckAvailability();
		
		appPg.waitLoading();
	}
	
	public String fillInTicketLotteryDetails(TicketInfo ticket) {
		UwpTicketLotteryApplicationPage appPg = UwpTicketLotteryApplicationPage.getInstance();
		UwpTicketLotteryConfirmationPage confirmPg = UwpTicketLotteryConfirmationPage.getInstance();
		
		logger.info("Fill in ticket lottery details to submission confirmation page...");
		if (ticket.lotteryChoices == null || ticket.lotteryChoices.size() < 1) {
			appPg.setPreferredChoice(ticket);
		} else {
			appPg.setPreferencesChoices(ticket.lotteryChoices);
		}
		if (StringUtil.notEmpty(ticket.deliveryMethod)) {
			appPg.selectDeliveryMethod(ticket.deliveryMethod);
		}
		
		appPg.clickSubmit();
		Object page = browser.waitExists(confirmPg, appPg);
		String toReturn = "";
		if (page == confirmPg) {
			toReturn = confirmPg.getOrdNum();
		} 
		return toReturn;
	}
	
	public void printTicketLotteryInLotteryDetailsPg() {
		UwpTicketLotteryApplicationDetailsPage detailsPg = UwpTicketLotteryApplicationDetailsPage.getInstance();
		RecgovPrintThisPage printPg = RecgovPrintThisPage.getInstance();
		
		logger.info("Print ticket lottery order in lottery details page...");
		detailsPg.clickPrintTicketsBtn();
		printPg.waitLoading();
		this.finishPrintFunctionFromPrintThisPg(false);
	}
}