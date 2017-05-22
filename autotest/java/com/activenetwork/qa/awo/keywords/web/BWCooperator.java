package com.activenetwork.qa.awo.keywords.web;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.PermitEmergencyContact;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.web.WebPrintPopupPage;
import com.activenetwork.qa.awo.pages.web.bw.BwAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.bw.BwAccountPanel;
import com.activenetwork.qa.awo.pages.web.bw.BwBookPermitPage;
import com.activenetwork.qa.awo.pages.web.bw.BwChangeCompletePage;
import com.activenetwork.qa.awo.pages.web.bw.BwChangePermitDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwCheckOutPage;
import com.activenetwork.qa.awo.pages.web.bw.BwConfirmationPage;
import com.activenetwork.qa.awo.pages.web.bw.BwCreateGroupLeaderPage;
import com.activenetwork.qa.awo.pages.web.bw.BwFindGroupLeaderPage;
import com.activenetwork.qa.awo.pages.web.bw.BwHeaderBar;
import com.activenetwork.qa.awo.pages.web.bw.BwHomePage;
import com.activenetwork.qa.awo.pages.web.bw.BwPayBalancePage;
import com.activenetwork.qa.awo.pages.web.bw.BwPermitOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwPolicyChangePage;
import com.activenetwork.qa.awo.pages.web.bw.BwReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.bw.BwReservationListPage;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.bw.BwShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.bw.BwWelcomePage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovHomePage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaMapPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPrintThisPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSTIEntranceListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCancellationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpParkListCommonPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPayBalancePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitDateRangeAvailablityPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitFacilityListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPrintPermitPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPrintTicketsAndPermitsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.PrintDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This class contains the most popular work flows in WEB testing.
 *
 * @author QA
 */
public class BWCooperator extends Web {
	private static BWCooperator _instance = null;

	public static BWCooperator getInstance() {
		if (null == _instance)
			_instance = new BWCooperator();

		return _instance;
	}

	protected BWCooperator() {
	}

	/**
	 * Sign in Boundary Water cooperator, the work flow starts at welcome page,
	 * ends at Home page.
	 *
	 * @param email
	 *            - email account
	 * @param pw
	 *            - password
	 */
	public void signInBW(String email, String pw) {
		BwHomePage bwHmPg = BwHomePage.getInstance();
		BwAccountOverviewPage bwAccountOVPg = BwAccountOverviewPage
				.getInstance();
		BwWelcomePage bwWelcomePg = BwWelcomePage.getInstance();
		UwpSignInPage bwSignInPg = UwpSignInPage.getInstance();
		BwPolicyChangePage policyPg = BwPolicyChangePage.getInstance();

		logger.info("Sign in with email=" + email + " pw=" + pw);
		bwWelcomePg.waitLoading();
		bwWelcomePg.clickSignIn();
		bwSignInPg.waitLoading();

		bwSignInPg.signIn(email, pw);
		Object page = browser.waitExists(bwAccountOVPg, policyPg, bwHmPg);

		if (page == policyPg) {
			policyPg.clickKeepCurrentUser();
			browser.waitExists(bwAccountOVPg, bwHmPg);
		}
	}

	/**
	 * Sign in Recreation.gov.
	 *
	 * @param email
	 *            - email account
	 * @param pw
	 *            - password
	 */
	public void signInRecGov(String email, String pw) {
		signIn(email, pw);
	}

	/** Log out Boundary Water cooperator. */
	public void signOutBW() {
		BwWelcomePage bwWelcomePg = BwWelcomePage.getInstance();
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();
		ConfirmDialogPage confirmDg=ConfirmDialogPage.getInstance();

		logger.info("Signing out BW...");
		bwHeader.clickSignOut();
		if(confirmDg.exists()){
			confirmDg.clickOK();
		}
		bwWelcomePg.waitLoading();
	}

	/** Log out Rec.Gov. */
	public void signOutRecGov() {
		signOut();
	}

	/** Abandon cart and verify whether the shopping cart is empty. */
	public void abandonCart() {
		BwShoppingCartPage bwShoppingCart = BwShoppingCartPage.getInstance();
		ConfirmDialogPage confirmDg=ConfirmDialogPage.getInstance();

		logger.info("Abandon cart.");
		confirmDg.setBeforePageLoading(false);
		confirmDg.setDismissible(false);
		bwShoppingCart.clickAbandonCart();
		confirmDg.waitLoading();
		confirmDg.clickOK();
		bwShoppingCart.waitLoading();
	
		if (!bwShoppingCart.verifyItemsInCart(0)) {
			throw new PageNotFoundException("The shopping cart is not empty!");
		}
	}

	/**
	 * Go to 'Cancel Permit Reservation' page from 'Permit Reservation Details' page
	 */
	public void gotoCancelPermitResPageFromPermitResDetailsPage() {
		UwpCancellationPage bwCancelPg = UwpCancellationPage.getInstance();
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage
				.getInstance();

		logger.info("Go to 'Cancel Permit Reservation' page from 'Permit Reservation Details' page.");
		bwResDetailPg.clickCancelReservation();
		bwCancelPg.waitLoading();
	}
	
	public void completeCancellation(){
		UwpCancellationPage bwCancelPg = UwpCancellationPage.getInstance();
		BwChangeCompletePage bwCancelCompletePg = BwChangeCompletePage.getInstance();
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();

		UwpHomePage uwpHomePg = UwpHomePage.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();

		bwCancelPg.clickProceedWithCancellation();
		bwCancelCompletePg.waitLoading();

		bwHeader.waitLoading();
		bwHeader.clickPermits();

		//Wait for BW or REC.GOV home page
		browser.waitExists(uwpHomePg, bwHmPg);
	}
	
	/**
	 * Cancel permit order from permit order details page. The work flow starts
	 * at reservation details page, ends at Clicking wildness permits link.
	 *
	 */
	public void cancelPermitOrderFromDetails() {
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage.getInstance();
		logger.info("Cancelling permit order...");
		bwResDetailPg.waitLoading();
		gotoCancelPermitResPageFromPermitResDetailsPage();
		completeCancellation();
	}

	/**
	 * Change group size, work flow starts at reservation details page, ends at
	 * clicking Permits link from header bar.
	 *
	 * @param newSize
	 *            - new group size
	 */
	public void changeGroupSize(int newSize, Payment pay) {
		BwChangePermitDetailsPage bwChangePg = BwChangePermitDetailsPage
				.getInstance();
		BwChangeCompletePage bwCompletePg = BwChangeCompletePage.getInstance();
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage
				.getInstance();
		BwPayBalancePage bwPayBalance = BwPayBalancePage.getInstance();
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();

		logger.info("Changing group size to " + newSize);

		bwResDetailPg.waitLoading();
		bwResDetailPg.clickChangePermitDetails();

		bwChangePg.waitLoading();
		bwChangePg.changeGroupSize(newSize);
		bwChangePg.clickConfirmOrIssue();

		Object page = browser.waitExists(bwPayBalance, bwCompletePg);
		if (page == bwPayBalance) {
			bwPayBalance.getPaymentAmount();
			if (bwPayBalance.needPayment()) {
				bwPayBalance.setupPayment(pay);
			}
			bwPayBalance.clickPayBalance();
			bwCompletePg.waitLoading();
		}

		bwHeader.waitLoading();
		bwHeader.clickPermits();
		browser.waitExists(UwpHomePage.getInstance(), BwHomePage.getInstance());
	}
	
	/**
	 * Change emergency contract info, work flow starts at permit reservation details page, ends at
	 * clicking Permits link from header bar.
	 *
	 * @param PermitEmergencyContracts
	 *            - PermitEmergencyContracts
	 */
	public void changeEmergencyContracts(PermitEmergencyContact emergencyContrats, Payment pay){
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage.getInstance();
		BwChangePermitDetailsPage bwUpdatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
		BwChangeCompletePage bwChangeCompletePg = BwChangeCompletePage.getInstance();
		BwPayBalancePage bwPayBalance = BwPayBalancePage.getInstance();
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();
		
		logger.info("Changing Emergency contracts info.");
		bwResDetailPg.clickUpdateReservation();
		bwUpdatePermitInfoPg.waitLoading();
		
		bwUpdatePermitInfoPg.setEmergencyContractsInfo(emergencyContrats);
		bwUpdatePermitInfoPg.clickConfirmOrIssue();

		Object page = browser.waitExists(bwPayBalance, bwChangeCompletePg);
		if (page == bwPayBalance) {
			bwPayBalance.getPaymentAmount();
			if (bwPayBalance.needPayment()) {
				bwPayBalance.setupPayment(pay);
			}
			bwPayBalance.clickPayBalance();
			bwChangeCompletePg.waitLoading();
		}
		
		bwHeader.waitLoading();
		bwHeader.clickPermits();
		browser.waitExists(UwpHomePage.getInstance(), BwHomePage.getInstance());
	}

	/**
	 * Check out the shopping cart, work flow starts at shopping cart page, ends
	 * at Clicking Permits link from header bar.
	 *
	 * @param pay- payment info
	 * @param printPermitsAndTickets  --true: click "Print Permits and Tickets" button in confirmation page
	 *                                --false: click "Continue To Home" or "Go Back To Res List" button 
	 * @return - order number
	 */
	public String checkOutCart(Payment pay, boolean printPermitsAndTickets) {
		BwShoppingCartPage bwShoppingCart = BwShoppingCartPage.getInstance();
		BwCheckOutPage bwCheckout = BwCheckOutPage.getInstance();
		BwConfirmationPage bwConfirm = BwConfirmationPage.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();
		UwpHomePage uwpHmPg = UwpHomePage.getInstance();
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
		BwReservationListPage resListPage=BwReservationListPage.getInstance();
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();

		logger.info("Checking out cart...");
		bwShoppingCart.waitLoading();

		double grandTotal = bwShoppingCart.verifyFees();
		bwShoppingCart.clickProcessingCart();

		bwCheckout.waitLoading();
		if (bwCheckout.needPayment()) {
			bwCheckout.setupPayment(pay);
		}
		double paidAmount = bwCheckout.verifyFees(grandTotal);
		bwCheckout.checkOut();
		confirm.setDismissible(true);
		confirm.setDismissMethod(false);

		browser.waitExists(confirm, bwConfirm);
		bwConfirm.verifyFees(paidAmount);
		String orderNum = bwConfirm.getOrderNumbers();
		logger.info("Processed order#" + orderNum);

		if(printPermitsAndTickets){
			bwConfirm.clickPrintTicketsAndPermits();
			printPermitsAndTicketsPg.waitLoading();
		}else{
			if(bwConfirm.isContinueToHomeBtnExit()){
				bwConfirm.clickContinueToHome();
			}else if(bwConfirm.isContinueToRecreationGoveHomeLinkExisting()){
				bwConfirm.clickContinueToRecreationGoveHomeLink();
			}else if(bwConfirm.isGoBackToResListBtnExisting()){
				bwConfirm.clickGoBackToResList();
				resListPage.waitLoading();
				resListPage.clickHome();
			}else{
				throw new ErrorOnPageException("Can't find 'Continue to Home' or 'Go back to reservation list page' button.'");
			}

			browser.waitExists(bwHmPg, uwpHmPg);// for recGov home page
			if(bwHmPg.exists()){
				bwHeader.clickWildnessPermits();
				bwHeader.waitLoading();
			}
		}

		return orderNum;
	}
	
	/**
	 * Check out the shopping cart, work flow starts at shopping cart page, ends
	 * at Clicking Permits link from header bar.
	 *
	 * @param pay
	 *            - payment info
	 * @return - order number
	 */
	public String checkOutCart(Payment pay) {
		return this.checkOutCart(pay, false);
	}
	
	/**
	 * Check out the shopping cart, work flow starts at shopping cart page, ends
	 * at confirmation page
	 *
	 * @param pay  - payment info
	 */
	public String checkOutCartToConfirmationPg(Payment pay) {
		BwShoppingCartPage bwShoppingCart = BwShoppingCartPage.getInstance();
		BwCheckOutPage bwCheckout = BwCheckOutPage.getInstance();
		BwConfirmationPage bwConfirm = BwConfirmationPage.getInstance();
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();

		logger.info("Checking out cart to confirmation page...");
		bwShoppingCart.waitLoading();

		double grandTotal = bwShoppingCart.verifyFees();
		bwShoppingCart.clickProcessingCart();

		bwCheckout.waitLoading();
		if (bwCheckout.needPayment()) {
			bwCheckout.setupPayment(pay);
		}
		double paidAmount = bwCheckout.verifyFees(grandTotal);
		bwCheckout.checkOut();
		confirm.setDismissible(true);
		confirm.setDismissMethod(false);

		browser.waitExists(confirm, bwConfirm);
		bwConfirm.verifyFees(paidAmount);
		String orderNum = bwConfirm.getOrderNumbers();
		logger.info("Processed order#" + orderNum);

		return orderNum;
	}
	
	/**
	 * Go to "Print Tickets and Permits" page from confirmation page
	 */
	public void gotoPrintTicketsAndPermitsPgFromConfirmationPg(){
		BwConfirmationPage bwConfirm = BwConfirmationPage.getInstance();
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();
		
		logger.info("Go to 'Print permits and tickets' page from confirmation page.");
		bwConfirm.clickPrintTicketsAndPermits();
		printPermitsAndTicketsPg.waitLoading();
	}
	
	/**
	 * Go to "Print permits and tickets" page from 'Permit Reservation Details' page
	 */
	public void gotoPrintPermitsAndTicketsPgFrompermitResDetails(){
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
		.getInstance();
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();
		
		logger.info("Go to 'Print permits and tickets' page from 'Permit Reservation Details' page.");
		bwDetails.clickPrintTicketsAndPermits();
		printPermitsAndTicketsPg.waitLoading();
	}

	/**
	 * Go to lottery details page by given reservation number, work flow starts
	 * at header bar, ends at lottery details page. Not recreation.gov site.
	 *
	 * @param resId
	 *            - reservation number
	 */
	public void gotoLotteryDetailsFromHome(String resId) {
		gotoLotteryDetailsFromHome(resId, false);
	}

	/**
	 * Go to lottery details page by given reservation number, work flow starts
	 * at header bar, ends at lottery details page.
	 *
	 * @param resId
	 *            - reservation number
	 * @param isRecgov
	 *            - is recreation.gov site
	 */
	public void gotoLotteryDetailsFromHome(String resId, boolean isRecgov) {
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
				.getInstance();
		BwReservationListPage bwList = BwReservationListPage.getInstance();
		BwAccountPanel bwAccount = BwAccountPanel.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();

		logger.info("Goto Lottery Details page...");

		if (isRecgov) {
			uwpHead.waitLoading();
			uwpHead.gotoMyReservationsAccount();
			bwAccount.waitLoading();
			bwAccount.clickLotteryApplications();

		} else {
			bwHmPg.waitLoading();
			bwHmPg.clickViewLotteryApplications();
		}
		bwList.waitLoading();
		bwList.searchReservation(resId);
		while (!bwList.selectReservation(resId)) {
			if (bwList.nextPageAvailable())
				bwList.clickNext();
			else
				throw new ItemNotFoundException(
						"The given reservation was not found.");
		}
		bwDetails.waitLoading();
	}

	/**
	 * Go to permit details page from home page. Start at home page, ends at
	 * reservation details page, not rec.gov site.
	 *
	 * @param resId
	 *            - reservation number
	 */
	public void gotoPermitDetailsFromHome(String resId) {
		gotoPermitDetailsFromHome(resId, false);
	}
	
	/**
	 * Go to permit details page from home page. Start at home page, ends at
	 * reservation details page.
	 *
	 * @param resId
	 *            - reservation number
	 * @param isRecgov
	 *            - whether or not rev.gov site
	 */
	public void gotoPermitDetailsFromHome(String resId, boolean isRecgov) {
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
				.getInstance();
		BwReservationListPage bwList = BwReservationListPage.getInstance();
		BwAccountPanel bwAccount = BwAccountPanel.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		BwHeaderBar bwHead = BwHeaderBar.getInstance();

		logger.info("Goto Permit details page...");
		if (isRecgov) {
			uwpHead.waitLoading();
			uwpHead.gotoMyReservationsAccount();
			bwAccount.waitLoading();
			bwAccount.clickCurrentReservations();

		} else {
			Object page = browser.waitExists(bwHmPg,bwHead);
			if (page == bwHead){
				bwHead.clickPermits();
				bwHmPg.waitLoading();
			}
			
			bwHmPg.clickViewPermitOrders();
		}
		bwList.waitLoading();
		//There is no this Link Existing in Rec.gov 5/5/2012
//		if (isRecgov)
//			bwList.gotoPermitResList();
		boolean clickPast = false;
		while (!bwList.selectReservation(resId)) {
			if (bwList.nextPageAvailable())
				bwList.clickNext();
			else if (!clickPast) {
				bwAccount.waitLoading();
				bwAccount.clickPastReservations();
				if (isRecgov)
					bwList.gotoPermitResList();
				bwList.waitLoading();
				clickPast = true;
			} else
				throw new ItemNotFoundException(
						"The given reservation was not found.");
		}
		bwDetails.waitLoading();
	}

	/**
	 * Go to "permit Reservation details" page from "Change Completed" page
	 * @param resId
	 */
	public void gotoPermitResDetailsPageFromChangeCompletedPage(String resId) {
		BwChangeCompletePage changeCompletePg = BwChangeCompletePage.getInstance();
		
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
				.getInstance();
		BwReservationListPage bwList = BwReservationListPage.getInstance();

		logger.info("Go to \"permit Reservation details\" page from \"Change Completed\" page");
		changeCompletePg.clickContinueToCurrentRes();
		bwList.waitLoading();
		
		bwList.selectReservation(resId);
		bwDetails.waitLoading();
	}
	
	public void gotoPermitReservationDetailsFromConfirm(String resNum) {
		UwpConfirmationPage confirmationPg = UwpConfirmationPage.getInstance();
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
		.getInstance();

		logger.info("Goto permit reservation details from confirmation clicking resNum - "+resNum);

		confirmationPg.gotoResDetailByResNo(resNum);
		bwDetails.waitLoading();
	}

	
	/**
	 * Go to print permit details page from home page. Start at home page, ends at
	 * print permit page.
	 *
	 * @param resId
	 *            - reservation number
	 * @param isRecgov
	 *            - whether or not rev.gov site
	 */
	public void gotoPrintPermitFromHome(String resId, boolean payBalance) {
		BwAccountPanel bwAccount = BwAccountPanel.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();

		logger.info("Goto Permit details page from home page...");
		uwpHead.gotoMyReservationsAccount();
		bwAccount.waitLoading();
		bwAccount.clickPrintTicketsAndPermits();
		printPermitsAndTicketsPg.waitLoading();

		this.gotoPrintPermitPgFromPrintTicketsAndPermitsPg(resId, payBalance);
	}
	
	/**
	 * Go to "Pay Balance" page when the permit order is outstanding balance order 
	 * from home page. Start at home page, ends at
	 * "Pay Balance" page
	 * 
	 *
	 * @param resId
	 *            - reservation number
	 */
	public void gotoPayBalanceFromHome(String resId) {
		BwAccountPanel bwAccount = BwAccountPanel.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();
		UwpPayBalancePage payBalancePg = UwpPayBalancePage.getInstance();

		logger.info("Goto 'Pay Balance' page from home page...");
		uwpHead.gotoMyReservationsAccount();
		bwAccount.waitLoading();
		bwAccount.clickPrintTicketsAndPermits();
		printPermitsAndTicketsPg.waitLoading();

		printPermitsAndTicketsPg.clickPrintPermitButton(resId);
		payBalancePg.waitLoading();
	}
	
	/**
	 * Go to "Print Permit" page form "Pay Balance" page when the permit order is outstanding balance order 
	 * from home page. Start at "Print Permit" page
	 * "Pay Balance" page
	 * 
	 *
	 * @param resId
	 *            - reservation number
	 */
	public void gotoPrintPermitPgFromPayBalancePg() {
		UwpPrintPermitPage printPage = UwpPrintPermitPage.getInstance();
		UwpPayBalancePage payBalancePg = UwpPayBalancePage.getInstance();
		Payment pay = new Payment("visa");

		logger.info("Goto 'Print Permit' page from 'Pay Balance' page.");
		payBalancePg.setupPayment(pay);
		payBalancePg.clickPayBalance();

		printPage.waitLoading();
	}
	
	/**
	 * Goto 'Print Permit' page from 'Print Tickets & Permits' page
	 * If this is balance order, it will pay balance after clicking "Print Permit" button
	 * @param resId: reservation number
	 */
	public void gotoPrintPermitPgFromPrintTicketsAndPermitsPg(String resId, boolean payBalance) {
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();
		UwpPrintPermitPage printPage = UwpPrintPermitPage.getInstance();
		UwpPayBalancePage payBalancePg = UwpPayBalancePage.getInstance();
       
		logger.info("Goto 'Print Permit' page from 'Print Tickets & Permits' page.");
		Payment pay = new Payment("visa");
		printPermitsAndTicketsPg.clickPrintPermitButton(resId);

		browser.waitExists(printPage, payBalancePg);

		if(payBalance){
			payBalancePg.setupPayment(pay);
			payBalancePg.clickPayBalance();	
		}else{
			logger.info("No need to pay balance.");
		}

		printPage.waitLoading();
	}
	
	/**
	 * Goto 'Print Permit' page from 'Print Tickets & Permits' page
	 * If this is balance order, it will pay balance after clicking "Print Permit" button
	 * @param resId: reservation number
	 */
	public void gotoPrintPermitPgFromPrintTicketsAndPermitsPg(String resId) {
		this.gotoPrintPermitPgFromPrintTicketsAndPermitsPg(resId, false);
	}
	
	/**
	 * Goto 'Print this' page from 'Print Tickets & Permits' page
	 * @param resId: reservation number
	 * @param payBalance --true: pay balance
	 *                   --false: no need to pay balance
	 */
	public void gotoPrintThisPgFromPrintTicketsAnPermitsPg(String resId, boolean payBalance) {
		this.gotoPrintPermitPgFromPrintTicketsAndPermitsPg(resId, payBalance);
		this.gotoPrintThisPgFromPrintPermitPg();
	}
	
	public void gotoPrintPermitPgFromPermitResDetailsPg(boolean isPayBlance){
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage.getInstance();
		UwpPayBalancePage payBalancePg = UwpPayBalancePage.getInstance();
		UwpPrintPermitPage printPage=UwpPrintPermitPage.getInstance();   
		
		logger.info("Goto 'Print Permit' page from Permit Reservation Details page by Click 'Print Permit' or 'Reprint Permit' Button");
		
		if(bwDetails.isPrintBtnExisting()){
			bwDetails.clickPrintPermit();
		}else{
			bwDetails.clickReprintPermit();
		}
		
		if(isPayBlance){
			Payment pay=new Payment("Visa");
			payBalancePg.waitLoading();
			payBalancePg.setupPayment(pay);
			payBalancePg.clickPayBalance();
		}
		printPage.waitLoading();
	}
	
	public void gotoPrintThisPgFromReservationDetailsPage(boolean isPayBlance){
		this.gotoPrintPermitPgFromPermitResDetailsPg(isPayBlance);
		this.gotoPrintThisPgFromPrintPermitPg();
	}
	
	/**
	 * Goto 'Print this' page from 'Print Tickets & Permits' page
	 * @param resId: reservation number
	 */
	public void gotoPrintThisPgFromPrintTicketsAndPermitsPg(String resId) {
		this.gotoPrintPermitPgFromPrintTicketsAndPermitsPg(resId, false);
		this.gotoPrintThisPgFromPrintPermitPg();
	}
	
	/**
	 * Print Permit from 'Print permits and tickets' to 'Print This page'
	 */
	public void gotoPrintThisPgFromPrintPermitPg(){
		UwpPrintPermitPage printPage = UwpPrintPermitPage.getInstance();
		RecgovPrintThisPage  printThisPage=RecgovPrintThisPage.getInstance();
		logger.info("Print Permit from 'Print permits and tickets' to 'Print This page'");
		printPage.selectAgreeCheckBox();
		printPage.clickProceedWithPrintingBtn();
		printThisPage.waitLoading();
	}
	/**
	 * issue permit. From BwReservationDetailsPage and end page is BwChangePermitDetailsPage or ConfirmDialogPage or BwPayBalancePage or BwChangeCompletePage or WebPrintPopupPage page.
	 * @return - 
	 */
	public void issuePermit(){
		BwChangePermitDetailsPage bwChangePg = BwChangePermitDetailsPage
				.getInstance();
		BwChangeCompletePage bwCompletePg = BwChangeCompletePage.getInstance();
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage
				.getInstance();
		BwPayBalancePage bwPayBalance = BwPayBalancePage.getInstance();
		WebPrintPopupPage printPopup = WebPrintPopupPage.getInstance();
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();

		logger.info("Issue permit...");
		bwResDetailPg.waitLoading();
//		bwResDetailPg.clickChangePermitDetails();
		bwResDetailPg.clickIssuePermit();

		bwChangePg.waitLoading();
		if (!bwChangePg.okForIssue())
			throw new ItemNotFoundException(
					"The permit is not ready for issuance.");
		bwChangePg.setupIssueInfo();
		bwChangePg.clickConfirmOrIssue();

		confirm.setDismissible(true);
		confirm.setDismissMethod(false);
		browser.waitExists(bwChangePg,confirm, bwPayBalance, bwCompletePg, printPopup);
	}

	/**
	 * Issue permit from reservation details page. start at reservation details
	 * page, ends at home page.
	 */
	public void issuePermitFromDetails() {
		
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();
		BwPayBalancePage bwPayBalance = BwPayBalancePage.getInstance();
		WebPrintPopupPage printPopup = WebPrintPopupPage.getInstance();
		BwChangeCompletePage bwCompletePg = BwChangeCompletePage.getInstance();
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();

		logger.info("Issue permit...");
		this.issuePermit();
		if (bwPayBalance.exists()) {
			bwPayBalance.getPaymentAmount();
			bwPayBalance.clickPayBalance();
			// need to click NO button for download dialog
			browser.waitExists(confirm, bwCompletePg, printPopup);
		}

		if (printPopup.exists()) {
			printPopup.close();
			bwCompletePg.waitLoading();
		}

		bwCompletePg.verifyFees();

		bwHeader.waitLoading();
		bwHeader.clickPermits();

		bwHmPg.waitLoading();
	}
	
	/**
	 * Undo issue permit from reservation details page. Starts at reservation
	 * details page, ends at home page.
	 */
	public void undoIssuePermitFromDetails() {
		BwChangeCompletePage  bwCompletePg = BwChangeCompletePage.getInstance();
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage
				.getInstance();
		BwHeaderBar bwHeader = BwHeaderBar.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();

		logger.info("Undo issue permit...");
		bwResDetailPg.waitLoading();
		bwResDetailPg.clickUndoIssuance();
		bwCompletePg.waitLoading();

		bwHeader.waitLoading();
		bwHeader.clickPermits();

		bwHmPg.waitLoading();
	}

	/**
	 * Create a new permit order into shopping cart in Boundary Water
	 * cooperation. The work flow starts from Boundary Water cooperator search
	 * panel and ends at shopping cart page.
	 *
	 * @param bd
	 *            - Permit information datacollection
	 * @return actual entry date
	 */
	public String makePermitOrderToCart(PermitInfo bd) {
		return makePermitOrderToCart(bd, false);
	}

	/**
	 * Unified park search method
	 * @param productType - camping, permit,tour
	 * @param parkName
	 */
	public void unifiedSearchPark(String productType, String parkName) {
		BwSearchPanel permitSearchPanel = BwSearchPanel.getInstance();
		UwpTourSearchPanel tourSearchPanel = UwpTourSearchPanel.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage parkViewAsListPage=UwpViewAsListCommonPage.getInstance();

		logger.info("Unified search park - " + parkName);
		int type = productType.equalsIgnoreCase("Camping") ? 1 : (productType.equalsIgnoreCase("Permit") ? 2 : 3);
		uwpHead.clickHomeLink();
//		browser.waitExists(homePg,unifiedSearchPanel);
		// if it is unified search, set up search criteria
//		if(!uwpHead.isUnifiedSearch()){
//			if(type == 1) {
//				uwpHead.gotoFindCampSpot();
//			}else if(type == 2) {
//				uwpHead.gotoWildnessPermit();
//			} else if(type == 3) {
//				uwpHead.gotoTourParkList();
//			}
//		}
//		Object page = browser.waitExists(unifiedSearchPanel, permitFacilityList, tourFacilityList);

//		if (page == permitFacilityList) {
//			permitFacilityList.clickCheckAvailability(parkName);
//		} else if(page == tourFacilityList) {
//			tourFacilityList.gotoTourParkDetails(parkName);
//		} else if(page == unifiedSearchPanel) {
			unifiedSearchPanel.setWhere(parkName);
			unifiedSearchPanel.triggerAutoCompleteBoxDisplay();
			unifiedSearchPanel.waitAutoCompleteBox();
			if(unifiedSearchPanel.getFacilityOptions().size()>1){
				unifiedSearchPanel.selectAutoCompleteBoxValue(unifiedSearchPanel.getFacilityOptions().get(0));
			}
			if(type == 1) {
				unifiedSearchPanel.selectInterestIn("Camping & Lodging");
			} else if(type == 2) {
				unifiedSearchPanel.selectInterestIn("Permits & Wilderness");
			} else if(type == 3) {
				unifiedSearchPanel.selectInterestIn("Tours & Tickets");
			}

			unifiedSearchPanel.clickSearch();
			Object page = browser.waitExists(suggestionPage, parkViewAsListPage);
			if(page == suggestionPage) {
				suggestionPage.clickFacilitySuggestion(parkName);
				parkViewAsListPage.waitLoading();
			}
			parkViewAsListPage.clickParkName(parkName);
//		}

		browser.waitExists(permitSearchPanel, tourSearchPanel);
	}

	/**
	 * Create a new permit order into shopping cart in Rec.gov or Boundary water
	 * cooperation. The work flow starts from Rec.gove head bar or BW search
	 * panel
	 * @param permit
	 *            - permit info
	 * @param isRecgov
	 *            - whether or not rec.gov site
	 * @return actual entry date
	 */
	public String makePermitOrderToCart(PermitInfo permit, boolean isRecgov) {

		logger.info("Make permit order to shopping cart.");
		this.makePermitToOrderDetailsPage(permit, isRecgov);
        return this.gotoPermitShoppingCartFromOrderDetailPage(permit);
	}
	
	/**
	 * Create a new permit order into shopping cart in Rec.gov or Boundary water
	 * cooperation. The work flow starts from Rec.gove head bar or BW search
	 * panel
	 * Start page: Permit Order Details page
	 * @param permit
	 *            - permit info
	 * @return actual entry date
	 */
	public String gotoPermitShoppingCartFromOrderDetailPage(PermitInfo permit) {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
				.getInstance();
		BwShoppingCartPage bwCart = BwShoppingCartPage.getInstance();

		logger.info("Make permit order from 'Permit Order Details'to shopping cart.");
		bwOrdDetails.setupOrderDetails(permit);
		bwOrdDetails.clickContinueToShoppingCart();

		browser.waitExists(bwCart, bwOrdDetails);

		return permit.actualEntryDate;
	}
	
	public void gotoPermitShoppingCartFromOrderDetailPage() {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
				.getInstance();
		BwShoppingCartPage bwCart = BwShoppingCartPage.getInstance();

		logger.info("Make permit order from 'Permit Order Details'to shopping cart.");
		bwOrdDetails.clickContinueToShoppingCart();

		browser.waitExists(bwCart, bwOrdDetails);
	}
		
	/**
	 * Go to 'Permit Order Details' page from 'Shopping Cart' page.
	 */
	public void gotoPermitOrderDetailsPgFromShoppingCartPg(){
		logger.info("Go to 'Permit Order Details' page from 'Shopping Cart' page.");
		 BwPermitOrderDetailsPage bwPermitOrdDetailsPg = BwPermitOrderDetailsPage.getInstance();
		 BwShoppingCartPage bwShoppingCartPg = BwShoppingCartPage.getInstance();
		
		if (bwShoppingCartPg.isChangeDetailsLinkExist())
			bwShoppingCartPg.clickChangeDetailsLink();
		else 
			bwShoppingCartPg.clickDetailsRequiredLink();
		bwPermitOrdDetailsPg.waitLoading();
	}

	/**
	 * Change Itinerary from permit order details page according to click "Change Itinerary" link
	 */
	public void changeItineraryFromPermitOrderDetailsPg(){
		BwPermitOrderDetailsPage permitOrderDetailsPg = BwPermitOrderDetailsPage.getInstance();
		RecgovSTIEntranceListPage entranceListPg = RecgovSTIEntranceListPage.getInstance();
		
		logger.info("Change Itinerary from permit order details page according to click 'Change Itinerary' link. ");
		permitOrderDetailsPg.clickChangeItinerary();
		entranceListPg.waitLoading();
	}
	
	/**
	 * Method verifyBusinessRuleEffectiveAtEntranceDetailPage - verify whether the business rules take effect to permit
	 * @param ruleName
	 * @param errorMsg
	 * @param permit
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtBookPermitPage(String ruleName, String errorMsg, PermitInfo permit) {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		String statusMsg = "";

		logger.info("Verify whether the " + ruleName + " Rule takes effect at Book Permit page in Permit Manager.");

		bwBookPage.waitLoading();
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();

		Object page = browser.waitExists(bwBookPage, bwOrdDetails);
		if (page == bwBookPage) {
			statusMsg = bwBookPage.getErrorMsg();
			if(errorMsg.length() <= 0){
				throw new ActionFailedException("Please set value to the variable: errorMsg");
			}
			if(statusMsg == null || "".equalsIgnoreCase(statusMsg)) {
				throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + permit.entrance + ").");
			}
			if(!statusMsg.contains(errorMsg)) {
				throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + permit.entrance + ").");
			}

			logger.info("----The " + ruleName + " Rule take effect to permit (Permit Entrance = " + permit.entrance + ") successfully.");
		} else {
			if(errorMsg.length() > 0){
				throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + permit.entrance + ").");
			}
			logger.info("----As negatively, the" + ruleName + " Rule applies succeessfully.");
		}

		return statusMsg;
	}

	/**
	 * Verify the available status of permit at the arrival date
	 * @param ruleName
	 * @param arrivalDate
	 * @param expectedStatus
	 */
	public void verifyBusinessRuleEffectiveAtBookPermitPage(String ruleName, String arrivalDate, String expectedStatus) {
		UWP web = UWP.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

		logger.info("Verify whether the " + ruleName + " Rule take effect in Web sales channel.");

		if (ruleName.length() == 0) {
			throw new ActionFailedException("Please set value to the parameter - ruleName");
		}

		String entranceName = bwBookPage.getEntranceName();
		try{
			if(expectedStatus.toLowerCase().startsWith("avail")) {
				web.verifyAvilableMarkInStDetailPg(arrivalDate, 1, "A");
			} else if(expectedStatus.toLowerCase().startsWith("unavail")) {
				web.verifyAvilableMarkInStDetailPg(arrivalDate, 1, "X");
			}
		} catch(ItemNotFoundException e) {
			throw new ActionFailedException("The " + ruleName + " Rule doesn't take effect to Permit (Entrance Name = " + entranceName + " in Web.");
		}
	}

	/**
	 * verify whether the business rules take effect to permit
	 * @param ruleName
	 * @param errorMsg
	 * @param permit
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtOrderDetailsPage(String ruleName, String errorMsg, String entrance){
		BwPermitOrderDetailsPage bwOrdDetailsPage = BwPermitOrderDetailsPage.getInstance();
		BwPayBalancePage payBalancePage = BwPayBalancePage.getInstance();
		String statusMsg = "";

		logger.info("Verify whether the " + ruleName + " Rule takes effect at Permit Order Details page in Rec.gov.");

		boolean result = true;
		if(bwOrdDetailsPage.exists() || payBalancePage.exists()) {
			if(bwOrdDetailsPage.exists()) {
				statusMsg = bwOrdDetailsPage.getTopErrorMsg();
			} else if(payBalancePage.exists()) {
				statusMsg = payBalancePage.getErrorMsg();
			}
			
			if(errorMsg == null || errorMsg.length() == 0){
				throw new ActionFailedException("Please set value to the variable: errorMsg");
			}
			if(statusMsg == null || statusMsg.length() == 0) {
				result &= false;
			}
			if(!statusMsg.contains(errorMsg)) {
				result &= false;
			}
			if(!result) {
				throw new ErrorOnPageException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + entrance + ").");
			} else logger.info("----The " + ruleName + " Rule take effect to permit (Permit Entrance = " + entrance + ") successfully.");
		} else {
			if(errorMsg != null && errorMsg.length() > 0) {
				throw new ErrorOnPageException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + entrance + ").");
			}
			logger.info("----As negatively, the" + ruleName + " Rule applies succeessfully.");
		}
		
		return statusMsg;
	}

	/**
	 * verify whether the business rules take effect to permit
	 * @param ruleName
	 * @param errorMsg
	 * @param permit
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtChangeOrderDetailsPage(String ruleName, String errorMsg, String entrance){
		BwChangePermitDetailsPage changeDetailsPage = BwChangePermitDetailsPage.getInstance();
		String statusMsg = "";

		logger.info("Verify whether the " + ruleName + " Rule takes effect at Permit Order Details page in Rec.gov.");

		boolean result = true;
		if(changeDetailsPage.exists()) {
			statusMsg = changeDetailsPage.getErrorMsg();
			if(errorMsg == null || errorMsg.length() == 0){
				throw new ActionFailedException("Please set value to the variable: errorMsg");
			}
			if(statusMsg == null || statusMsg.length() == 0) {
				result &= false;
			}
			if(!statusMsg.contains(errorMsg)) {
				result &= false;
			}
			if(!result) {
				throw new ErrorOnPageException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + entrance + ").");
			} else logger.info("----The " + ruleName + " Rule take effect to permit (Permit Entrance = " + entrance + ") successfully.");
		} else {
			if(errorMsg != null && errorMsg.length() > 0) {
				throw new ErrorOnPageException("The " + ruleName + " Rule doesn't take effect to permit (Permit Entrance = " + entrance + ").");
			}
			logger.info("----As negatively, the" + ruleName + " Rule applies succeessfully.");
		}
		
		return statusMsg;
	}
	
	/**
	 * Creating a new group leader from BwFindGroupLeaderPage or
	 * BwPermitOrderDetailsPage The work flow will end at
	 * BwPermitOrderDetailsPage
	 *
	 * @param groupLeader
	 */
	public void createGroupLeader(Customer groupLeader) {
		BwFindGroupLeaderPage bwFindGrpLeader = BwFindGroupLeaderPage
				.getInstance();
		BwCreateGroupLeaderPage bwCreatGrpLeader = BwCreateGroupLeaderPage
				.getInstance();
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
				.getInstance();

		logger.info("Creating a new group leader...");

		Object page = browser.waitExists(bwOrdDetails, bwFindGrpLeader);
		if (page == bwOrdDetails) {
			bwOrdDetails.clickCreateGroupLeader();
		} else {
			bwFindGrpLeader.clickCreateGroupLeaderButton();
		}

		bwCreatGrpLeader.waitLoading();
		bwCreatGrpLeader.setupGroupLeaderInfo(groupLeader);
		bwCreatGrpLeader.clickCreatGroupLeader();

		bwOrdDetails.waitLoading();
	}

	/**
	 * Create a new permit order into shopping cart in Rec.gov or Boundary water
	 * cooperation. Using Find a existing Group Leader in order details page The
	 * work flow starts from Rec.gove head bar or BW search panel
	 *
	 * @param bd
	 *            - permit info data
	 * @param isRecgov
	 *            - whether or not rec.gov site
	 */
	public void bookPermitByFindGroupLeader(PermitInfo bd, boolean isRecgov) {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
				.getInstance();
		BwFindGroupLeaderPage bwFindGrpLeader = BwFindGroupLeaderPage
				.getInstance();
		// BwCreateGroupLeaderPage
		// bwCreatGrpLeader=BwCreateGroupLeaderPage.getInstance();
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		BwShoppingCartPage bwCart = BwShoppingCartPage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();

		logger.info("Make permit order to shopping cart...");

		if (isRecgov) {
			uwpHead.waitLoading();
			uwpHead.gotoWildnessPermit();
		}

		bwSearchPanel.waitLoading();
		bwSearchPanel.searchPermit(bd);
        
		bwBookPage.waitLoading();
		if (!bd.isRange)
			bwBookPage.setEntryDate(bd.entryDate);
		else
			bwBookPage.selectInventory(1);
		bwBookPage.waitLoading();
		bd.actualEntryDate = bwBookPage.getEntryDate();
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();

		bwOrdDetails.waitLoading();
		if (!isRecgov) {
			bwOrdDetails.clickFindGroupLeader();
			bwFindGrpLeader.waitLoading();
			bwFindGrpLeader.setupSearchCriteria(bd.groupLeader);
			bwFindGrpLeader.clickSearchButton();
			bwFindGrpLeader.searchWaitExists();
			boolean found = bwFindGrpLeader.foundCustomer();
			if (!found) {
				createGroupLeader(bd.groupLeader);
			} else {
				bwFindGrpLeader.selectCustomer();
			}

			bwOrdDetails.waitLoading();
		}
		bwOrdDetails.setupOrderDetails(bd);
		bwOrdDetails.clickContinueToShoppingCart();

		bwCart.waitLoading();
	}

	/**
	 * Make a permit order to Book Permit page
	 * @param permit
	 * @param isRecgov
	 */
	public void gotoBookPermitPage(PermitInfo permit, boolean isRecgov){
		UwpHomePage homePg = UwpHomePage.getInstance();
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpPermitFacilityListPage facilitList = UwpPermitFacilityListPage.getInstance();

		UwpParkListCommonPage parkListPage=UwpParkListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		logger.info("Make permit order to book permit page.");

		if (isRecgov) {
			uwpHead.clickHomeLink();
			browser.waitExists(homePg, unifiedSearchPanel);
			// if it is unified search, set up search criteria
			if(!permit.isUnifiedSearch){
				uwpHead.gotoWildnessPermit();
				facilitList.clickCheckAvailability(permit.facility);
			}else{
				unifiedSearchPanel.setupPermitSearchCriteria(permit);
				unifiedSearchPanel.clickSearch();
				Object page=browser.waitExists(suggestionPage,parkListPage);
				if(page==suggestionPage){
					suggestionPage.waitLoading();
					suggestionPage.clickFacilitySuggestion(permit.parkId,permit.contractCode);
					parkListPage.waitLoading();
				}

				parkListPage.clickFacility(permit.facility);
			}
		}

		bwSearchPanel.waitLoading();
		bwSearchPanel.searchPermit(permit);

		bwBookPage.waitLoading();
		if (!permit.isRange) {
			bwBookPage.setEntryDate(permit.entryDate);
		} else {
			bwBookPage.selectInventory(1);
		}
		permit.actualEntryDate = bwBookPage.getEntryDate();
	}

	/**
	 * Make permit to order details page and fill in the order details info
	 * @param permit
	 * @param isRecgov
	 */
	public void makePermitToOrderDetailsPage(PermitInfo permit, boolean isRecgov){
		this.makePermitToSearchPanel(permit, isRecgov);
		this.makePermitToOrderDetailsPageFromSearchPanel(permit, isRecgov);
	}
	
	/**
	 * Make permit to BW search panel
	 * @param permit
	 * @param isRecgov
	 */
	public void makePermitToSearchPanel(PermitInfo permit, boolean isRecgov){
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpPermitFacilityListPage facilitList = UwpPermitFacilityListPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		UwpViewAsListCommonPage parkViewAsListPage=UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();

		logger.info("Make permit order to BW search panel.");

		if (isRecgov) {
			if(permit.isUnifiedSearch){
				uwpHead.clickHomeLink();
			} else {
				uwpHead.gotoWildnessPermit();
			}
			Object page = browser.waitExists(facilitList, unifiedSearchPanel);

			if (page == facilitList) {
				facilitList.clickCheckAvailability(permit.facility);
			}else if(page == unifiedSearchPanel){
				unifiedSearchPanel.setupPermitSearchCriteria(permit);
				unifiedSearchPanel.clickSearch();
				page=browser.waitExists(suggestionPage,parkViewAsListPage);
				if(page==suggestionPage){
					suggestionPage.clickFacilitySuggestion(permit.parkId,permit.contractCode);
					parkViewAsListPage.waitLoading();
				}
				parkViewAsListPage.clickParkName(permit.facility);
				bwSearchPanel.waitLoading();
			}
		}
		if(!bwSearchPanel.exists()) {
			uwpHead.gotoWildnessPermit();
			bwSearchPanel.waitLoading();
		}
	}
	
	public void findPermits(PermitInfo permit){
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		RecgovSTIEntranceListPage iteneraryPermitEntranceListPg = RecgovSTIEntranceListPage.getInstance();
		bwSearchPanel.searchPermit(permit);
		
        //bwBookPage.waitExists();
		browser.waitExists(bwBookPage, iteneraryPermitEntranceListPg);
	}
	
	/**
	 * Make permit to order details page after filling in the order details info from search panel
	 * @param permit
	 * @param isRecgov
	 */
	
	public void makePermitToOrderDetailsPgFromSearchPanelWithoutSearch(PermitInfo permit, boolean isRecgov){
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
		BwFindGroupLeaderPage bwFindGrpLeader = BwFindGroupLeaderPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		RecgovSTIEntranceListPage entranceListPg = RecgovSTIEntranceListPage.getInstance();
		
		logger.info("Make permit order to order detail page from search panel without search.");
		
		//Sara[20130723] Work flow for Itinerary permit 
		//James[20130729] comment out in order to pass BW sanity cases. Please review page mark for RecgovSTIEntranceListPage
		//Sara[20130730] update page "RecgovSTIEntranceListPage" mark using permit grid DIV and add itinerary button
		if(entranceListPg.exists()){ 
			this.bookSTIPermitOnSTIEntListPg(permit.entrancesForItineraryPermit);
		}else{
			if (!permit.isRange) {
				bwBookPage.setEntryDate(permit.entryDate);
				if(bwBookPage.isLotterySelected()) {
					if(permit.dateFlexible) {
						logger.warn("The specified date " + permit.entryDate + " only have lottery. Switch to the first available inventroy");
						bwBookPage.selectInventory(1);
					} else {
						throw new ItemNotFoundException("The specified date " + permit.entryDate + " only have lottery");
					}
				}
			} else {
				bwBookPage.selectInventory(1);
			}

			permit.actualEntryDate = bwBookPage.getEntryDate();
			if(permit.exitDate.length()<=0){
				permit.exitDate=DateFunctions.getDateAfterGivenDay(permit.actualEntryDate, 1);
			}
			bwBookPage.waitForBookNowButtonExisting();
			bwBookPage.clickBookNow();
			
			browser.waitExists(bwOrdDetails, bwBookPage); 
			
			if (!isRecgov) {
				Customer groupLeader = new Customer();
				groupLeader.setAsDefaultGroupLeader();
				if(bwOrdDetails.isCreateGroupLeaderLinkExist()){
					createGroupLeader(groupLeader);
					bwOrdDetails.waitLoading();
				}
				if(bwOrdDetails.isFindGroupLeaderLinkExist()){
					bwOrdDetails.clickFindGroupLeader();
					bwFindGrpLeader.waitLoading();
					bwFindGrpLeader.searchByPhone(groupLeader.hPhone);
					bwFindGrpLeader.searchWaitExists();
					bwFindGrpLeader.selectCustomer(1);
					bwOrdDetails.waitLoading();
				}
			}
		}
	}
	
	public void makePermitToOrderDetailsPageFromSearchPanel(PermitInfo permit, boolean isRecgov){
		this.findPermits(permit);
		makePermitToOrderDetailsPgFromSearchPanelWithoutSearch(permit, isRecgov);
	}
	
	public void makePermitToOrderDetailsPageFromDateRangeAvailablityPage(PermitInfo permit, boolean isRecgov){
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
		BwFindGroupLeaderPage bwFindGrpLeader = BwFindGroupLeaderPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		UwpPermitDateRangeAvailablityPage dateRangeAvailablityPg = UwpPermitDateRangeAvailablityPage.getInstance();
		
		logger.info("Make permit order to order detail page from date range availablity page.");
		dateRangeAvailablityPg.selectAvailablePermitNum(1);
		bwBookPage.waitLoading();
		
		//Search available entry && exit date
		if(bwBookPage.isEntryAndExitSelectionModel()){
			bwBookPage.findEntryAvailability(permit.entrance, permit.entryDate);
			bwBookPage.findExitAvailability(permit.entrance_Exit,permit.exitDate);
		}else{
			bwBookPage.selectInventory(1);
		}
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();
		
		bwBookPage.waitForProgressBarDisapear();
		bwOrdDetails.waitLoading();
		if (!isRecgov) {
			Customer groupLeader = new Customer();
			groupLeader.setAsDefaultGroupLeader();
			if(bwOrdDetails.isCreateGroupLeaderLinkExist()){
				createGroupLeader(groupLeader);
				bwOrdDetails.waitLoading();
			}
			if(bwOrdDetails.isFindGroupLeaderLinkExist()){
				bwOrdDetails.clickFindGroupLeader();
				bwFindGrpLeader.waitLoading();
				bwFindGrpLeader.searchByPhone(groupLeader.hPhone);
				bwFindGrpLeader.searchWaitExists();
				bwFindGrpLeader.selectCustomer(1);
				bwOrdDetails.waitLoading();
			}
		}
	}
	
	/**
	 * Go to permit date range availability page from search panel
	 * @param permit
	 */
	public void gotoPermitDateRangeAvailablityFromSearchPanel(PermitInfo permit){
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		UwpPermitDateRangeAvailablityPage dateRangeAvailablityPg = UwpPermitDateRangeAvailablityPage.getInstance();
		
		logger.info("Make permit date range availablity from search panel.");
		bwSearchPanel.setupSearchData(permit);
		bwSearchPanel.clickSearch();

		if (!permit.isRange || permit.issue) { //!(permit.isRange && !permit.issue)
			bwSearchPanel.specificSearchWaitExists();
			bwSearchPanel.clickDateRangeAvailablity();
		}
		browser.waitExists(bwSearchPanel, dateRangeAvailablityPg);
	}

	public void makePermitLotteryToOrderDetailPage(PermitInfo permit) {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpPermitFacilityListPage facilitList = UwpPermitFacilityListPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel=UwpUnifiedSearchPanel.getInstance();
		UwpViewAsListCommonPage parkViewAsListPage=UwpViewAsListCommonPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();
		BwFindGroupLeaderPage bwFindGrpLeader = BwFindGroupLeaderPage.getInstance();
		
		logger.info("Apply a permit lottery to order detail page.");
		if(permit.isUnifiedSearch) {
			uwpHead.clickHomeLink();
		} else {
			uwpHead.gotoWildnessPermit();
		}
		Object page = browser.waitExists(facilitList, unifiedSearchPanel, bwSearchPanel);

		if (page == facilitList) {
			facilitList.clickCheckAvailability(permit.facility);
			bwSearchPanel.waitLoading();
		} else if (page == unifiedSearchPanel) {
			unifiedSearchPanel.setupPermitSearchCriteria(permit);
			unifiedSearchPanel.clickSearch();
			page=browser.waitExists(suggestionPage, parkViewAsListPage);
			if(page==suggestionPage) {
				suggestionPage.clickFacilitySuggestion(permit.parkId,permit.contractCode);
				parkViewAsListPage.waitLoading();
			}
			parkViewAsListPage.clickParkName(permit.facility);
			bwSearchPanel.waitLoading();
		}
				
		bwSearchPanel.searchPermit(permit);
		bwBookPage.waitLoading();
		if (!permit.isRange) {
			bwBookPage.setEntryDate(permit.entryDate);
			if(bwBookPage.isPermitSelected()) {
				int count = 0;
				do{
					bwBookPage.clickEntryUpdateAvailability();
					bwBookPage.waitLoading();
					Browser.sleep(1);
					count ++;
				} while(bwBookPage.isPermitSelected() && count < 5);
				if(bwBookPage.isPermitSelected()) {
					throw new ItemNotFoundException("The selected date " + permit.entryDate + " hasn't lottery.");
				}
			}
		} else {
			bwBookPage.selectInventory(1);
		}
		permit.actualEntryDate = bwBookPage.getEntryDate();
		permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.actualEntryDate, 1);
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();
		bwOrdDetails.waitLoading();
		
		Customer groupLeader = new Customer();
		groupLeader.setAsDefaultGroupLeader();
		if(bwOrdDetails.isCreateGroupLeaderLinkExist()){
			createGroupLeader(groupLeader);
			bwOrdDetails.waitLoading();
		}
		if(bwOrdDetails.isFindGroupLeaderLinkExist()){
			bwOrdDetails.clickFindGroupLeader();
			bwFindGrpLeader.waitLoading();
			bwFindGrpLeader.searchByPhone(groupLeader.hPhone);
			bwFindGrpLeader.searchWaitExists();
			bwFindGrpLeader.selectCustomer(1);
			bwOrdDetails.waitLoading();
		}
	}

	public void fillInLotteryDetails(PermitInfo permit) {
		BwPermitOrderDetailsPage orderDetailPage = BwPermitOrderDetailsPage.getInstance();
		UwpShoppingCartPage shoppingCartPage = UwpShoppingCartPage.getInstance();

		logger.info("Fill in Lottery Application order detail page.");
		orderDetailPage.selectLotteryPermitType();
		orderDetailPage.selectLotteryEntrance();
		orderDetailPage.setEntryDate(permit.entryDate);
		orderDetailPage.selectAgreementCheckbox();
		orderDetailPage.clickContinueToShoppingCart();
		shoppingCartPage.waitLoading();
	}

	/**
	 * Submit a lottery application to shopping cart in Boundary water
	 * cooperatior. This work flow starts from Boundary Water cooperator home
	 * page and ends at the shopping cart page.
	 *
	 * @param bd
	 *            - permit info data
	 * @param pay
	 *            - payment info data
	 */
	public void submitLotteryToCart(PermitInfo bd, Payment pay) {
		submitLotteryToCart(bd, pay, false);
	}

	/**
	 * Submit a lottery application to lottery application page in Rec.gov or Boundary
	 * Water cooperator. The work flow starts from Rec.Gov head bar or Boundary
	 * Water home page and ends at the lottery application page.
	 *
	 * @param bd
	 *            - permit info
	 * @param isRecgov
	 *            - whether or not rec.gov site
	 */
	public void submitLotteryToLotteryApplication(PermitInfo bd, boolean isRecgov){
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
		.getInstance();
		BwFindGroupLeaderPage bwFindGrpLeader = BwFindGroupLeaderPage
		.getInstance();
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpParkListCommonPage parkListPage=UwpParkListCommonPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		BwHomePage bwHmPg = BwHomePage.getInstance();
		UwpHeaderBar recHead = UwpHeaderBar.getInstance();
		BwCreateGroupLeaderPage bwCreatGrpLeader = BwCreateGroupLeaderPage
		.getInstance();
		UwpPermitFacilityListPage facilitList = UwpPermitFacilityListPage
		.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();

		logger.info("Submit lottery to shopping cart...");
		if (isRecgov) {
			if(!bd.isUnifiedSearch){
				recHead.gotoWildnessPermit();
			}else{
				if(!unifiedSearchPanel.exists()){
					this.gotoHomePage();
				}
			}

			Object page = browser.waitExists(facilitList, unifiedSearchPanel);
			if (page == facilitList) {
				facilitList.clickCheckAvailability(bd.facility);
			} else if(page == unifiedSearchPanel) {
				unifiedSearchPanel.setupPermitSearchCriteria(bd);
				unifiedSearchPanel.clickSearch();
				page=browser.waitExists(suggestionPage,parkListPage);
				if(page==suggestionPage){
					suggestionPage.waitLoading();
					suggestionPage.clickFacilitySuggestion(bd.parkId,bd.contractCode);
					parkListPage.waitLoading();
				}
				parkListPage.clickFacility(bd.facility);
			}
		} else {
			bwHmPg.clickSubmitALottery();
		}
		bwSearchPanel.waitLoading();
		bwSearchPanel.setupSearchData(bd);
		bwSearchPanel.searchLottery(bd.maxLoop, bd.isRange);

		bwBookPage.waitLoading();
		bwBookPage.selectLotteryInventory(1);
		bd.actualEntryDate = bwBookPage.getEntryDate();
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();

		bwOrdDetails.waitLoading();
		if (!isRecgov) {
			bwOrdDetails.waitLoading();
			bwOrdDetails.clickFindGroupLeader();

			bwFindGrpLeader.waitLoading();
			bwFindGrpLeader.searchByPhone(bd.leaderPhone);
			bwFindGrpLeader.searchWaitExists();
			if (bwFindGrpLeader.foundCustomer())
				bwFindGrpLeader.selectCustomer(1);
			else {
				bwFindGrpLeader.clickCreateGroupLeaderButton();
				bwCreatGrpLeader.waitLoading();
				Customer groupLeader = new Customer();
				groupLeader.setAsDefaultGroupLeader();
				bwCreatGrpLeader.setupGroupLeaderInfo(groupLeader);
				bwCreatGrpLeader.clickCreatGroupLeader();
			}

			bwOrdDetails.waitLoading();
		}
	}

	/**
	 * Submit a lottery application to shopping cart in Rec.gov or Boundary
	 * Water cooperator. The work flow starts from Rec.Gov head bar or Boundary
	 * Water home page and ends at the shopping cart page.
	 *
	 * @param bd
	 *            - permit info
	 * @param pay
	 *            - payment info
	 * @param isRecgov
	 *            - whether or not rec.gov site
	 */
	public void submitLotteryToCart(PermitInfo bd, Payment pay, boolean isRecgov) {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
		.getInstance();
		BwShoppingCartPage bwCart = BwShoppingCartPage.getInstance();

		this.submitLotteryToLotteryApplication(bd, isRecgov);
		bwOrdDetails.setupLotteryDetails(bd);// .setupOrderDetails(bd);
		if (bwOrdDetails.needSetupPayment()) {
			bwOrdDetails.setupPaymentInfo(pay);
		}
		bwOrdDetails.clickContinueToShoppingCart();

		bwCart.waitLoading();
	}

	/**
	 * Verify order status with given value.
	 *
	 * @param status
	 *            - status supposed to
	 */
	public void verifyOrderStatus(String status) {
		BwReservationDetailsPage bwResDetailPg = BwReservationDetailsPage
				.getInstance();

		logger.info("Verify order status is " + status);
		bwResDetailPg.waitLoading();
		bwResDetailPg.verifyStatus(status);
	}

	/**
	 * Click 'Remove This Reservation' link to remove the current reservation at
	 * the order detail page
	 */
	public void removeResFromOrderDetailsPg() {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
				.getInstance();
		BwShoppingCartPage shoppingCart = BwShoppingCartPage.getInstance();

		logger.info("Remove reservation from order details page.");
		bwOrdDetails.clickRemoveThisRes();
		shoppingCart.waitLoading();
	}


	/**
	 * Method executed the flow of canceling one or more permit orders from 'My Reservations Account' page
	 * and end with permit search page by permit orders' number.
	 * @param isRecgov
	 * @param permitOrderNums - 1 or more permit order number(s)
	 */
	public void cancelPermitOrders(boolean isRecgov, String... permitOrderNums) {
		if(permitOrderNums.length == 0) {
			throw new ActionFailedException("Please give the permit order numbers.");
		}

		for(int i = 0; i < permitOrderNums.length; i++){
			logger.info("Cancel the permit order#" + permitOrderNums[i]);
			gotoPermitDetailsFromHome(permitOrderNums[i], isRecgov);
			cancelPermitOrderFromDetails();
		}
	}
	
	/**
	 * Finish print function from "Print This Page" to "Reservation Details" 
	 * @param clickPrintButton ---true: click 'Print' button after clicking "Print this page" button
	 *                         ---false: click 'Cancel' button after clicking "Print this page" button 
	 */
	public void finishPrintFunctionFromPrintThisPg(boolean clickPrintButton){
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
		.getInstance();
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
		bwDetails.waitLoading();
	}

	/**
	 * Goto print ticket & permit page from Home page
	 */
	public void gotoPrintTicketPermitPageFromHomePage() {
		UwpPrintTicketsAndPermitsPage printPermitsAndTicketsPg = UwpPrintTicketsAndPermitsPage.getInstance();
		UwpHomePage homePage=UwpHomePage.getInstance();
		UwpAccountPanel accountPanel=UwpAccountPanel.getInstance();
		logger.info("Goto print ticket & permit page from Home page");
		homePage.gotoAccountPage();
		accountPanel.waitLoading();
		accountPanel.clickTicketPermitLink();
		printPermitsAndTicketsPg.waitLoading();
	}
	
	/**
	 * Reprint permit order from 'permit Reservation Details' page to 'Print This Page' page
	 */
	public void reprintFromPermitResDetailsPg(){
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage
		.getInstance();
		UwpPrintPermitPage printPage = UwpPrintPermitPage.getInstance();

		logger.info("Reprint permit order from 'permit Reservation Details' page to 'Print This Page' page");
		bwDetails.clickReprintPermit();
		printPage.waitLoading();
		
		this.gotoPrintThisPgFromPrintPermitPg();
	}
	
	/**
	 * Get permit print file top regulation warning texts from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public List<String> getPermitPrintFileTopRegulationWarningTextsFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get regulation warning texts with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitSectionB%'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (null == result || result.size() < 1) {
			throw new ErrorOnDataException("Can't find regulation warning texts with locID:"+locID+" under schema:"+schema);
		}
		return result;
	}

	/**
	 * Get permit print file left regulation warning texts from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public String getPermitPrintFileLeftRegulationWarningTextsFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get regulation warning texts with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitSectionCText1'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Find more than one left regulation warning text title record with locID:"+locID+" under schema:"+schema);
		}
		return result.get(0);
	}
	
	/**
	 * Print/Reprint Permit from reservation details page, End in Reservation Details page;
	 * 
	 * @param isReprint
	 */
	public void printPermitFromResDetailsPage(boolean isReprint) {
		BwReservationDetailsPage bwDetails = BwReservationDetailsPage.getInstance();
		UwpPrintPermitPage printPage = UwpPrintPermitPage.getInstance();
		WebPrintPopupPage printPopup = WebPrintPopupPage.getInstance();
		PrintDialogPage  printDialogPage=PrintDialogPage.getInstance();
		RecgovPrintThisPage  printThisPage=RecgovPrintThisPage.getInstance();
		
		logger.info("Print Permit from reservation details page..");
		if (isReprint)
			bwDetails.clickReprintPermit();
		else
			bwDetails.clickPrintPermit();
		printPage.waitLoading();
		printPage.selectAgreeCheckBox();
		printPage.clickProceedWithPrintingBtn();
		Object page=browser.waitExists(printThisPage,printPopup,bwDetails);
		if(page==printThisPage){
			printThisPage.clickPrintThispage();
			printDialogPage.setDismissible(false);
			printDialogPage.waitLoading();
			printDialogPage.clickPrint();
			printThisPage.clickDone();
			browser.waitExists(bwDetails);
		}
	
	}
	
	/**
	 * Get permit print file right regulation warning texts from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public String getPermitPrintFileRightRegulationWarningTextsFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get regulation warning texts with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitSectionCText2'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Find more than one right regulation warning text title record with locID:"+locID+" under schema:"+schema);
		}
		return result.get(0);
	}
	
	/**
	 * Get permit print file regulation texts title from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public String getPermitPrintFileRegulationTextTitleFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get regulation warning text title with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionAText'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Find more than one regulation warning text title record with locID:"+locID+" under schema:"+schema);
		}
		return result.get(0);
	}
	
	/**
	 * Get permit print file top regulation texts from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public List<String> getPermitPrintFileTopRegulationTextsFromDB(String schema, String locID){
		List<String> result = new ArrayList<String>();
		
		db.resetSchema(schema);

		logger.info("Get regulation top texts with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionB%'";
		result = db.executeQuery(sql, "MESSAGE");
		if (null == result || result.size() < 1) {
			throw new ErrorOnDataException("Can't find regulation top texts with locID:"+locID+" under schema:"+schema);
		}
		return result;
	}
	
	/**
	 * Get permit print file left regulation texts from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public List<String> getPermitPrintFileLeftRegulationTextsFromDB(String schema, String locID){
		List<String> result = new ArrayList<String>();
		
		db.resetSchema(schema);

		logger.info("Get regulation left texts with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionD%1'";
		result = db.executeQuery(sql, "MESSAGE");
		if (null == result || result.size() < 1) {
			throw new ErrorOnDataException("Can't find regulation top texts with locID:"+locID+" under schema:"+schema);
		}
		return result;
	}
	
	/**
	 * Get permit print file right regulation texts from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public List<String> getPermitPrintFileRightRegulationTextsFromDB(String schema, String locID){
		List<String> result = new ArrayList<String>();
		
		db.resetSchema(schema);

		logger.info("Get regulation top texts with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionD%2'";
		result = db.executeQuery(sql, "MESSAGE");
		if (null == result || result.size() < 1) {
			throw new ErrorOnDataException("Can't find regulation top texts with locID:"+locID+" under schema:"+schema);
		}
		
		return result;
	}
	
	public void activateOrInactivateLeftRegulationWarningTextFromBy(String schema, String parkId, boolean activate){
		db.resetSchema(schema);
		logger.info((activate?"Activate":"Inactivate")+" left regulation warning text with park ID = " + parkId);
        
		String active_ind = "";
		if(activate){
			active_ind = "1";
		}else{
			active_ind = "0";
		}
		
		String  sql = "update x_loc_msg set active_ind = "+active_ind+" where loc_id="+parkId+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionD%1'";
		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);
	}
	
	public void activateOrInactivateRightRegulationWarningTextFromBy(String schema, String parkId, boolean activate){
		db.resetSchema(schema);
		logger.info((activate?"Activate":"Inactivate")+" right regulation warning text with park ID = " + parkId);
        
		String active_ind = "";
		if(activate){
			active_ind = "1";
		}else{
			active_ind = "0";
		}
		
		String  sql = "update x_loc_msg set active_ind = "+active_ind+" where loc_id="+parkId+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionD%2'";
		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);
	}
	
	public void activateOrInactivateTopRegulationWarningTextFromBy(String schema, String parkId, boolean activate){
		db.resetSchema(schema);
		logger.info((activate?"Activate":"Inactivate")+" regulation warning text with park ID = " + parkId);
        
		String active_ind = "";
		if(activate){
			active_ind = "1";
		}else{
			active_ind = "0";
		}
		
		String  sql = "update x_loc_msg set active_ind = "+active_ind+" where loc_id="+parkId+" and MSG_DSCR like 'Back Country Permit Report: PermitRegSectionB%'";
		logger.debug("Execute sql: " + sql);
		db.executeUpdate(sql);
	}
	
	/**
	 * Get permit print file agreement rules from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public String getPermitPrintFileAgreementRulesFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get agreement rule with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitSectionDText1%'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Find more than one agreement rule record with locID:"+locID+" under schema:"+schema);
		}
		return result.get(0).trim();
	}
	
	/**
	 * Get permit print file footer from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public List<String> getPermitPrintFileFooterTextFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get agreement rule with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitRegFooter%'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (null==result || result.size() < 1) {
			throw new ErrorOnDataException("Can't find footer with locID:"+locID+" under schema:"+schema);
		}
		return result;
	}
	
	/**
	 * Get permit print file header main title from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public String getPermitPrintFileHeaderMainTitleFromDB(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get agreement rule with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitSectionAHeader1'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Find more than one header main title record with locID:"+locID+" under schema:"+schema);
		}
		return result.get(0);
	}
	
	/**
	 * Get permit print file Planned trip itinerary from DB
	 * 
	 * @param schema
	 * @param locID
	 * @return
	 */
	public String getPermitPrintFilePlannedTripItineraryNote(String schema, String locID) {
		db.resetSchema(schema);

		logger.info("Get agreement rule with locID:"+locID+" under schema:"+schema);
		String sql = "select MESSAGE from X_LOC_MSG WHERE LOC_ID = "+locID+" and MSG_DSCR like 'Back Country Permit Report: PermitSectionEText1'";
		List<String> result = db.executeQuery(sql, "MESSAGE");
		if (result.size() != 1) {
			throw new ErrorOnDataException("Find more than one Planned trip itinerary note record with locID:"+locID+" under schema:"+schema);
		}
		return result.get(0);
	}
	/**
	 * Go to Print Permit&Tickets page from rec.gov home page;
	 */
	public void gotoPrintPermitsAndTicketsPgFromHomePg() {
		RecgovHomePage homePage=RecgovHomePage.getInstance();
		UwpAccountPanel accountPage=UwpAccountPanel.getInstance();
		UwpPrintTicketsAndPermitsPage printListpage=UwpPrintTicketsAndPermitsPage.getInstance();
		
		homePage.gotoAccountPage();
		accountPage.waitLoading();
		accountPage.clickTicketPermitLink();
		printListpage.waitLoading();
	}
	
    /**
     * confirm permit reservation from res details page to home page;
     */
	public void confirmPermitReservationFromResDetailsPg() {
		BwReservationDetailsPage detailsPg=BwReservationDetailsPage.getInstance();
		BwChangePermitDetailsPage updatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
		BwChangeCompletePage changeCompletePg = BwChangeCompletePage.getInstance();
		UwpHeaderBar headerBar=UwpHeaderBar.getInstance();
		UwpHomePage homePage=UwpHomePage.getInstance();
		
		logger.info("confirm permit reservation from res details page to home page.....");
		detailsPg.clickConfirmPermitResButton();
		updatePermitInfoPg.exists();
		updatePermitInfoPg.clickConfirmOrIssue();
		changeCompletePg.waitLoading();
		changeCompletePg.clickContinueToCurrentRes();
		headerBar.clickHomeLink();
		homePage.waitLoading();
	}

	/**
	 * Go to 'Update Permit Information' page to update details info from 'Permit Reservation Details' page by clicking 'Update Reservation' link
	 */
	public void gotoUpdatePermitOrderDetailsPage() {
		BwReservationDetailsPage resDetailPage = BwReservationDetailsPage.getInstance();
		BwChangePermitDetailsPage updatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
		
		logger.info("Go to 'Update Permit Information' page from 'permit reservation detail' page.");
		resDetailPage.clickUpdateReservation();
		updatePermitInfoPg.waitLoading();
	}
	
	public void updatePermitOrderDetails(PermitInfo permit) {
		updatePermitResFromPermitResPg(permit, new Payment("Visa"));
	}
	
	/**
	 * update permit reservation, starts from update permit reservation details ends with change complete page
	 * @param permit
	 * @param pay
	 */
	public void updatePermitReservation(PermitInfo permit, Payment pay) {
		BwChangeCompletePage changeCompletePg = BwChangeCompletePage.getInstance();
		BwPayBalancePage payBalancePg = BwPayBalancePage.getInstance();
		BwChangePermitDetailsPage updatePermitInfoPg = BwChangePermitDetailsPage.getInstance();
		
		logger.info("Update permit order details.");
		if(permit!=null){
			updatePermitInfoPg.updatePermitRes(permit);
		}
		updatePermitInfoPg.clickConfirmChanges();
		Object page = browser.waitExists(changeCompletePg, payBalancePg, updatePermitInfoPg);
		if(page == payBalancePg) {
			payBalancePg.setupPayment(pay);
			payBalancePg.clickPayBalance();
			browser.waitExists(changeCompletePg, payBalancePg);
		}
	}
	
	/**
	 * update permit order details, starts from permit reservation details ends with change complete page
	 * @param permit
	 * @param pay
	 */
	public void updatePermitResFromPermitResPg(PermitInfo permit, Payment pay) {
		logger.info("Update permit order details.");
		this.gotoUpdatePermitOrderDetailsPage();
		this.updatePermitReservation(permit, pay);
	}
	
	/**
	 * Go to permit availability page from Home Page.
	 * 
	 * @param permit
	 */
	public void gotoPermitAvailibalityPageFromHomePg(PermitInfo permit) {
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		UwpHeaderBar uwpHead = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel
				.getInstance();
		UwpViewAsListCommonPage parkViewAsListPage = UwpViewAsListCommonPage
				.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPage = UwpUnifiedSearchSuggestionPage
				.getInstance();

		logger.info("Go to permit availability page from Home Page.");

		uwpHead.clickHomeLink();

		browser.waitExists(unifiedSearchPanel);

		unifiedSearchPanel.setupPermitSearchCriteria(permit);
		unifiedSearchPanel.clickSearch();
		Object page = browser.waitExists(suggestionPage, parkViewAsListPage);
		if (page == suggestionPage) {
			suggestionPage.clickFacilitySuggestion(permit.parkId,
					permit.contractCode);
			parkViewAsListPage.waitLoading();
		}
		parkViewAsListPage.clickParkName(permit.facility);

		bwSearchPanel.waitLoading();
		bwSearchPanel.searchPermit(permit);
		bwBookPage.waitLoading();

		bwBookPage.setEntryDate(permit.entryDate);
		if (bwBookPage.checkExitDate()) {
			bwBookPage.setExitDate(permit.exitDate);			
		}
		if (bwBookPage.checkExitPointListExist() && 
				!permit.exitPoint.equals(bwBookPage.getExitPoint())) {
			bwBookPage.selectExitPoint(permit.exitPoint);
			bwBookPage.clickUpdateExitPointAvailability();
			bwBookPage.waitUpdateAvailability();
		}

		if (bwBookPage.isLotterySelected()) {
			throw new ItemNotFoundException("The specified date "
					+ permit.entryDate + " only have lottery");
		}
	}

	/**
	 * Click Book Now button on Book Permit Page to order detail page 
	 * 
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public void gotoOrderDetailsPgFromBookPermitPg() {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage
				.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

		logger.info("Click Book Now button on Book Page to order detail page.");
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();
		bwOrdDetails.waitLoading();
	}
	
	/**
	 * Book permit on Entrance Details Page, starts from Entrance Details page, ends at Entrance Details page or Order Details page
	 * @param permit
	 * @return the error message when ends at Entrance Details page, or empty string
	 * @author Lesley Wang
	 * Mar 15, 2013
	 */
	public String bookPermitOnEntranceDetailsPage(PermitInfo permit) {
		BwPermitOrderDetailsPage bwOrdDetails = BwPermitOrderDetailsPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		logger.info("Update permit availability and then book permit on Entrance Details Page...");
		bwBookPage.setPermitAvailabilityInfo(permit);
		bwBookPage.waitForBookNowButtonExisting();
		bwBookPage.clickBookNow();
		Object page = browser.waitExists(bwOrdDetails, bwBookPage);
		if (page == bwBookPage) {
			String errorMsg = bwBookPage.getErrorMsg();
			logger.info("Entrance Details page is still shown due to: " + errorMsg);
			return errorMsg;
		} else {
			logger.info("Book permit from Entrance Details page to order details page correctly!");
			return StringUtil.EMPTY;
		}
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
		ConfirmDialogPage dialogPg = ConfirmDialogPage.getInstance();
		
		if(orderDetailsPg.isItemsInCartLinkExists()){
			orderDetailsPg.clickItemsInCartLink();
			shoppingCart.waitLoading();
		} else if(uwpConfirmationPg.exists()) {
			//if the confirmation page exists, the "Items In Cart" doesn't exist, but there
			//is a "Back To Shopping Cart" link exists
			dialogPg.setDismissible(false);
			Object page = browser.waitExists(dialogPg, uwpConfirmationPg);
			if (page == dialogPg) {
				dialogPg.dismiss();
				browser.waitExists(uwpConfirmationPg);
			}
			uwpConfirmationPg.clickBackToShoppingCart();
			shoppingCart.waitLoading();
		} else if(shoppingCart.exists()) {
			logger.info("Already in shopping cart.");
		} else {
			throw new ObjectNotFoundException("Can not go to shoppingcart page from this page directly.");
		}
	}
	

    /**
     * Verify the number of Emergency Contract rows in DB
     * @param schema
     * @param locID
     * @param permitTypeName
     * @param rows_Expected
     */
	public void verifyEmergencyContractRowsInDB(String schema, String locID, String permitTypeName, int rows_Expected){
		int rows_Actual = this.getPermitEmergencyContractRowsFromDB(schema, locID, permitTypeName);
		if(rows_Expected!=rows_Actual){
			throw new ErrorOnPageException("The number of Emergency Contracts rows is wrong in DB!", String.valueOf(rows_Expected), String.valueOf(rows_Actual));
		}
		logger.info("Successfully verify the number of Emergency Contracts rows in DB:"+rows_Expected);
	}
	
    /**
     * Verify the number of Alternate Leaders rows in DB
     * @param schema
     * @param locID
     * @param permitTypeName
     * @param rows_Expected
     */
	public void verifyAlternateLeadersMaxRowsInDB(String schema, String locID, String permitTypeName, int rows_Expected){
		int rows_Actual = this.getPermitAlternateLeadersMaxRowsFromDB(schema, locID, permitTypeName);
		if(rows_Expected!=rows_Actual){
			throw new ErrorOnPageException("The number of Alternate Leaders rows is wrong in DB!", String.valueOf(rows_Expected), String.valueOf(rows_Actual));
		}
		logger.info("Successfully verify the number of Alternate Leaders rows in DB:"+rows_Expected);
	}
	
	/**
	 * Verify the Emergency Contract display label in DB
	 * @param schema
	 * @param locID
	 * @param permitTypeName
	 * @param displayLabel_Expected
	 */
	public void verifyEmergencyContractDisplayLabelInDB(String schema, String locID, String permitTypeName, String displayLabel_Expected){
		String displayLable_Actual = this.getPermitEmergencyContractDisplayLabelFromDB(schema, locID, permitTypeName);
		if(!displayLabel_Expected.equals(displayLable_Actual)){
			throw new ErrorOnPageException("Emergency Contracts display label is wrong in DB!", displayLabel_Expected, displayLable_Actual);
		}
		logger.info("Successfully verify Emergency Contracts display label in DB:"+displayLabel_Expected);
	}
	
	/**
	 * Verify the Emergency Contract applicable status (Mandatory, Optional, Not Applicable) in DB
	 * @param schema
	 * @param locID
	 * @param permitTypeName
	 * @param applicableStatus_Expected
	 */
	public void verifyEmergencyContractApplicableStatusInDB(String schema, String locID, String permitTypeName, String applicableStatus_Expected){
		String applicableStatus_Actual = this.getPermitEmergencyContractApplicableStatus(schema, locID, permitTypeName);
		if(!applicableStatus_Expected.equals(applicableStatus_Actual)){
			throw new ErrorOnPageException("Emergency Contracts applicable status is wrong in DB!", applicableStatus_Expected, applicableStatus_Actual);
		}
		logger.info("Successfully verify Emergency Contracts applicable status in DB:"+applicableStatus_Expected);
	}
	
	public void verifyTrailheadsApplicableStatusInDB(String schema, String locID, String permitTypeName, String applicableStatus_Expected){
		String applicableStatus_Actual = this.getPermitTrailheadsApplicableStatus(schema, locID, permitTypeName);
		if(!applicableStatus_Expected.equals(applicableStatus_Actual)){
			throw new ErrorOnPageException("Trailheads applicable status is wrong in DB!", applicableStatus_Expected, applicableStatus_Actual);
		}
		logger.info("Successfully verify Trailheads applicable status in DB:"+applicableStatus_Expected);
	}
	
	public void verifyTripitineraryApplicableStatusInDB(String schema, String locID, String permitTypeName, String applicableStatus_Expected){
		String applicableStatus_Actual = this.getPermitTripItineraryApplicableStatus(schema, locID, permitTypeName);
		if(!applicableStatus_Expected.equals(applicableStatus_Actual)){
			throw new ErrorOnPageException("Tripitinerary applicable status is wrong in DB!", applicableStatus_Expected, applicableStatus_Actual);
		}
		logger.info("Successfully verify Tripitinerary applicable status in DB:"+applicableStatus_Expected);
	}
	
	public void verifyAlternateLeadersApplicableStatusInDB(String schema, String locID, String permitTypeName, String applicableStatus_Expected){
		String applicableStatus_Actual = this.getPermitAlternateLeadersApplicableStatus(schema, locID, permitTypeName);
		if(!applicableStatus_Expected.equals(applicableStatus_Actual)){
			throw new ErrorOnPageException("Alternate Leaders applicable status is wrong in DB!", applicableStatus_Expected, applicableStatus_Actual);
		}
		logger.info("Successfully verify Alternate Leaders  applicable status in DB:"+applicableStatus_Expected);
	}
	
	/**
	 * Check 'Permit Delivery Method' setup
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @param hasDeliveryMethod true:permit should has delivery method, false:permit should not have delivery method
	 */
	public void preparePermitWithDeliveryMethod(String schema, String parkID, String permitTypeName, boolean hasDeliveryMethod){
		boolean result = this.isPermitWithDeliveryMethodAttr(schema, parkID, permitTypeName);
		
		if(hasDeliveryMethod&&!result){
			int i = this.setupPermitWithDeliveryMethodAttr(schema, parkID, permitTypeName);
			if(i==-1){
				throw new ErrorOnDataException("Setup Permit type attr failed for permit(Name:"+permitTypeName+") should "+(hasDeliveryMethod?"":"doesn't ")+"have Delivery Method setup for parkID:"+parkID);
			}
		}else if(!hasDeliveryMethod&&result){
			this.updatePermitDeliveryApplicableStatus(schema, parkID, permitTypeName, "Not Applicable");
		}
		logger.info("Successfully verify the permit(Name:"+permitTypeName+") "+(hasDeliveryMethod?"":"doesn't ")+"have Delivery Method setup for parkID:"+parkID);
	}

	/**
	 * Check 'Permit Issue Station' setup
	 * @param schema
	 * @param parkID
	 * @param permitTypeName
	 * @param hasIssueStation true:permit should has issue station, false:permit should not have issue station
	 */
	public void prepareIsPermitWithIssueStation(String schema, String parkID, String permitTypeName, boolean hasIssueStation){
		boolean result = this.isPermitWithIssueStationAttr(schema, parkID, permitTypeName);
		
		if(hasIssueStation&&!result){
			int i = this.setupPermitWithIssueStationAttr(schema, parkID, permitTypeName);
			if(i==-1){
				throw new ErrorOnDataException("Setup Permit type attr failed for permit(Name:"+permitTypeName+") should "+(hasIssueStation?"":"doesn't ")+"have Issue Station setup for parkID:"+parkID);
			}
		}else if(!hasIssueStation&&result){
			this.updatePermitIssueStationApplicableStatus(schema, parkID, permitTypeName, "Not Applicable");
		}
		logger.info("Successfully verify the permit(Name:"+permitTypeName+") "+(hasIssueStation?"":"doesn't ")+"have Issue Station setup for parkID:"+parkID);
	}
	
	public void verifyIsTripItineraryRestrictFirstNightsStay(String schema, String parkID, String permitTypeName, boolean isTripItineraryRestrictFirstNightsStay){
		boolean result = this.isTripItineraryRestrictFirstNightsStay(schema, parkID, permitTypeName);
		
		if(result!=isTripItineraryRestrictFirstNightsStay){
			throw new ErrorOnDataException("The permit(Name:"+permitTypeName+") should "+(isTripItineraryRestrictFirstNightsStay?"":"doesn't ")+"have Trip Itinerary Restrict First Nights Stay setup for parkID:"+parkID);
		}
		logger.info("Successfully verify the permit(Name:"+permitTypeName+") "+(isTripItineraryRestrictFirstNightsStay?"":"doesn't ")+"have Trip Itinerary Restrict First Nights Stay setup for parkID:"+parkID);
	}
	
	/**
	 * verify issue permit restriction rule not available.
	 */
	public void verifyIssuePermitRestrictionRuleNotAvailable(){
		this.verifyIssuePermitRestrictionRule(null);
	}
	
	/**
	 * Verify issue Permit Restriction rule Error Msg.
	 */
	public void verifyIssuePermitRestrictionRule(String errorMsg){
		BwChangePermitDetailsPage bwChangePg = BwChangePermitDetailsPage
				.getInstance();
		BwChangeCompletePage bwCompletePg = BwChangeCompletePage.getInstance();
		if(bwChangePg.exists()){
			logger.info("Issue permit blocked");
			if(null !=errorMsg){
				if(!MiscFunctions.compareResult("Issue permit error Message",errorMsg,bwChangePg.getErrorMsg())){
					throw new ErrorOnPageException("Issue Permit restriction rule should be available.");
				}
			} else {
				throw new ErrorOnPageException("Ticket order should be issued successfully");
			}
		}else if(bwCompletePg.exists()) {
			if(!StringUtil.isEmpty(errorMsg)) {
				throw new ErrorOnPageException("Ticket order should NOT be issued successfully");
			}
			logger.info("Issue Permit restriction rule is not available.");
		}
	}
	
	/**
	 * Search permit in Rec Find Permit Panel;
	 * @param permit
	 */
	public void searchPermitInSearchPanel(PermitInfo permit) {
		BwSearchPanel panel=BwSearchPanel.getInstance();
		
		logger.info("Search permit in find permit panel....");
		
		panel.setupSearchData(permit);
		panel.clickSearch();
		panel.waitLoading();
	}
	
	/**
	 * Go to entrance details page from entrance list page by clicking entrance name link
	 * @param entrance
	 * @author Lesley Wang
	 * Mar 19, 2013
	 */
	public void gotoEntranceDetailsPgFromListPg(String entrance) {
		RecgovEntranceListPage entranceListPg = RecgovEntranceListPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		
		logger.info("Go to entranct details page from entrance list page for entrance=" + entrance);
		entranceListPg.clickEntranctNameLink(entrance);
		bwBookPage.waitLoading();
	}
	
	public void gotoEntranceDetailsPgByEntranceId(String parkID, String entranceID) {
		RecgovEntranceListPage entranceListPg = RecgovEntranceListPage.getInstance();
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();
		
		logger.info("Go to entranct details page from entrance list page for entranceID=" + entranceID);
		entranceListPg.clickEntranceName(parkID, entranceID);
		bwBookPage.waitLoading();
	}
	
	/** Go to permit area map page from facility details page */
	public void gotoPermitAreaMapPgFromFacilityDetailsPg() {
		UwpPermitingPage permitPg = UwpPermitingPage.getInstance();
		RecgovPermitAreaMapPage mapPg = RecgovPermitAreaMapPage.getInstance();
		
		logger.info("Go to permit area map page from facility details page...");
		permitPg.clickPermitAreaMap();
		mapPg.waitLoading();
	}
	
	/** Login in from STI entrance list page by clicking the Log-In link in details message */
	public void loginFromSTIEntranceListPg(String email, String pw) {
		RecgovSTIEntranceListPage entListPg = RecgovSTIEntranceListPage.getInstance();
		UwpSignInPage signInPg = UwpSignInPage.getInstance();
		UwpSignInSignUpPage signInUpPg = UwpSignInSignUpPage.getInstance();
		
		logger.info("Login in from STI entrance list page by email - "  + email + ", pw - " + pw);
		entListPg.clickLogIn();
		Object page = browser.waitExists(signInUpPg, signInPg);
		if (page == signInPg) {
			signInPg.signIn(email, pw);
		} else {
			signInUpPg.signIn(email, pw);
		}
		entListPg.waitLoading();
	}
	
	/**
	 * Book STI permit on STI entrance list page. The work flow may ends at STI Entrance List page due to some errors or permit order details page.
	 * @param entrances
	 * @return
	 */
	public String bookSTIPermitOnSTIEntListPg(EntranceInfo... entrances) {
		RecgovSTIEntranceListPage entListPg = RecgovSTIEntranceListPage.getInstance();
		BwPermitOrderDetailsPage ordDetailsPg = BwPermitOrderDetailsPage.getInstance();
		
		logger.info("Book STI permit on STI entrance list page...");
		if(entrances.length>0){
			entListPg.addEntrancesToItinerary(entrances);
			entListPg.waitForItineraryWidgetBookBtn();
		}
		entListPg.clickItineraryWidgetBookBtn();
		Object page = browser.waitExists(ordDetailsPg, entListPg);
		String msg = null;
		if (page == entListPg) {
			msg = entListPg.getTopErrorMsg();
			logger.info("Error occurs when book STI permit to order details page. Page still in Entrance List page. Error: " + msg);
		}
		
		return msg;
	}
	
	/**
	 * Book STI permit on STI entrance list page. The work flow may ends at STI Entrance List page due to some errors or permit order details page
	 * @param permits
	 * @return
	 */
	public String bookSTIPermitOnSTIEntListPg(PermitInfo... permits) {
		RecgovSTIEntranceListPage entListPg = RecgovSTIEntranceListPage.getInstance();
		BwPermitOrderDetailsPage ordDetailsPg = BwPermitOrderDetailsPage.getInstance();
		
		logger.info("Book STI permit on STI entrance list page...");
		for(PermitInfo permit: permits){
			searchPermitInSearchPanel(permit);
			entListPg.waitLoading();
			entListPg.addEntrancesToItinerary(permit.entrancesForItineraryPermit);
		}
		
		entListPg.waitForItineraryWidgetBookBtn();
		entListPg.clickItineraryWidgetBookBtn();
		Object page = browser.waitExists(ordDetailsPg, entListPg);
		String msg = null;
		if (page == entListPg) {
			msg = entListPg.getTopErrorMsg();
			logger.info("Error occurs when book STI permit to order details page. Page still in Entrance List page. Error: " + msg);
		}
		
		return msg;
	}
	
	/**Go to shopping cart page by click Items In Cart link*/
	public void gotoCartByItemsInCartLink() {
		UwpMemberStatusBar bar = UwpMemberStatusBar.getInstance();
		UwpShoppingCartPage cart = UwpShoppingCartPage.getInstance();
		logger.info("Go to shopping cart page by click Items In Cart link....");
		bar.clickItemsInCartLink();
		cart.waitLoading();
	}
	
	/**Go to STI entrance list page from order details page */
	public void gotoSTIEntrListPgFromOrdDetailsPg() {
		BwPermitOrderDetailsPage detailsPg = BwPermitOrderDetailsPage.getInstance();
		RecgovSTIEntranceListPage entListPg = RecgovSTIEntranceListPage.getInstance();
		logger.info("Go to STI entrance list page from order details page...");
		detailsPg.clickChangeItinerary();
		entListPg.waitLoading();
	}
}
