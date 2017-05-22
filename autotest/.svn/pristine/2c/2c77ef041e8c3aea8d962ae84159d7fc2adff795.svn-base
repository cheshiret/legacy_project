package com.activenetwork.qa.awo.keywords.orms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InvoiceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.search.TicketSearch;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsAlertPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeeDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsInvoiceSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationFeesPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.lottery.OrmsLotteryApplicationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.lottery.OrmsLotteryApplicationSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.lottery.OrmsRevokeLotteryPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsEnterPinNumPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsPrintPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsProcessOrderCartPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSAddItemPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSChargeListInOrderDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSSaleSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSProductSetupPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsRequestReportPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsAddTicketPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsCancelTicketOrderPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsChangeTicketTypePage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketAvailabilityPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketSelectionPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketTransferSelectionPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTourAlertsPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTourDetaisPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTourParticipantsWidget;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSendReportPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrAddTourInventoryPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrEndCartPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrManageTicketsPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrReceiptsDetailsPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrReceiptsSearchPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrRequestConfirmationLetterPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrTourInventoryListPage;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author raonqa
 */
public class VenueManager extends Orms {
	private static VenueManager _instance = null;

	public static VenueManager getInstance() {
		if (null == _instance)
			_instance = new VenueManager();

		return _instance;
	}

	protected VenueManager() {
	}

	/**
	 * This method executes the work flows of logging into Venue Manager. The
	 * work flow opens a new browser to orms home page and ends at Venue Manager
	 * home page.
	 * 
	 * @param login
	 *            - the login information
	 */
	public void loginVenueManager(LoginInfo login) {
		loginVenueManager(login,true);
	}

	/**
	 * This method is a overload method,used for improve performance
	 * 
	 * @param login
	 * @param newBrowser
	 *            -determined whether we need to open a new browser
	 */
	public void loginVenueManager(LoginInfo login, boolean newBrowser) {
		VnuMgrHomePage fnmHmPg = VnuMgrHomePage.getInstance();
		login(login,"Venue Manager",newBrowser);
		fnmHmPg.waitLoading();
		ajax.waitLoading();
		fnmHmPg.waitLoading();
	}

	/**
	 * This method logs out Venue manger. The work flow starts from Venue
	 * manager home page and ends at orms home page.
	 */
	public void logoutVenueManager() {
		logger.info("Log out Venue Manager.");

		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsHomePage oHmPg = OrmsHomePage.getInstance();

		vmHmPg.clickSignOut();

		oHmPg.waitLoading();
	}

	/**
	 * This method processes Venue manager order cart. The work flow starts from
	 * order cart page and ends at Venue manager home page.
	 * 
	 * @param pay
	 *            - the payment information
	 * @return - the order numbers
	 */
	public String processOrderCart(Payment pay) {
		return processOrderCart(pay, false, true);
	}

	/**
	 * This method processes Venue manager order cart. 
	 * The work flow starts from order cart page 
	 * and ends at Venue manager home page 
	 * or still order cart page once blocked by rule.
	 * @param pay
	 * @param isPrint
	 * @param isOverrideRules
	 * @return order numbers or blocked rule message
	 */
	public String processOrderCart(Payment pay, boolean isPrint, boolean isOverrideRules) {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsOrderSummaryPage vmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsProcessOrderCartPopupPage popupPg = OrmsProcessOrderCartPopupPage
				.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
		OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsEnterPinNumPopupPage rulePopupPg = OrmsEnterPinNumPopupPage
		.getInstance();

		logger.info("Process order cart.");

		if (!pay.ticketInfo.ticketNum.equals("1")) {
			for (int i = 1; i < Integer.parseInt(pay.ticketInfo.ticketNum); i++) {
				vmOrdCartPg.clickAddTicketOrder();
				vmTicketAvailSchPg.waitLoading();
				makeTicketOrderToCart(pay.ticketInfo);
			}
		}
		vmOrdCartPg.setupPayment(pay);

		// Action for the "Auto-print Tickets" checkbox
		vmOrdCartPg.setupAutoPrintTickets(pay);

		vmOrdCartPg.clickProcessOrder();

		alert.setDismissible(true);
		alert.setBeforePageLoading(false);
		Object page = browser.waitExists(alert, printPg, popupPg, rulePopupPg, vmOrdSumPg);

		if (page == rulePopupPg) { // need to override rules
			if(isOverrideRules){
				logger.info("Set pin to override rule.");
				String pin = getPinNum(TestProperty.getProperty("orms.vm.user"));
				rulePopupPg.enterReason();
				rulePopupPg.enterPIN(pin);
				rulePopupPg.clickOK();
				page = browser.waitExists(printPg, popupPg, vmOrdSumPg,alert);
			}else{
				logger.info("Process has been blocked by rule.");
				rulePopupPg.clickCancel();
				vmOrdCartPg.waitLoading();
				return vmOrdCartPg.getErrorMessage();
			}
		}
		if (page == popupPg) { // pin popup or print ticket popup
			popupPg.enterPIN(pay.pin);
			popupPg.clickOK();
			page = browser.waitExists(alert, printPg, vmOrdSumPg);
		}

		if (page == printPg) { // print ticket popup
			 printPg.close();
//			printPg.waitForDisappear();
			vmOrdSumPg.waitLoading();
		}
		String reservNum = vmOrdSumPg.getAllOrdNums();

		if (isPrint == true) {
			vmOrdSumPg.clickPrintTickets();
			// vmOrdSumPg.waitExists();
			page = browser.waitExists(printPg, alert, vmOrdSumPg);

			if (page == printPg) {
				// printPg.waitForDisappear();
				printPg.close();
				vmOrdSumPg.waitLoading();
			}
		}

		vmOrdSumPg.clickFinishOrder();

		page = browser.waitExists(alert, printPg, vmHmPg); // the print page may
		// not be closed yet
		if (page == printPg) { // print ticket popup
			printPg.close();
			vmHmPg.waitLoading();
		}
		alert.resetDefault();
		
		ajax.waitLoading();
		return reservNum;
	}
	
	/**
	 * This method processes Venue manager order cart. The work flow starts from
	 * order cart page and ends at Order Summary page.
	 * 
	 * @param pay
	 *            - the payment information
	 * @return - the order numbers
	 */
	public String processOrderCartToSummaryPg(Payment pay, boolean isPrint) {
		OrmsOrderSummaryPage vmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsProcessOrderCartPopupPage popupPg = OrmsProcessOrderCartPopupPage
				.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();

		logger.info("Process order cart.");

		if (!pay.ticketInfo.ticketNum.equals("1")) {
			for (int i = 1; i < Integer.parseInt(pay.ticketInfo.ticketNum); i++) {
				vmOrdCartPg.clickAddTicketOrder();
				makeTicketOrderToCart(pay.ticketInfo);
			}
		}
		vmOrdCartPg.setupPayment(pay);
		
		// Action for the "Auto-print Tickets" checkbox
		vmOrdCartPg.setupAutoPrintTickets(pay);

		vmOrdCartPg.clickProcessOrder();

		alert.setDismissible(true);
		alert.setBeforePageLoading(false);
		Object page = browser.waitExists(alert, printPg, popupPg, vmOrdSumPg);

		if (page == popupPg) { // pin popup or print ticket popup
			popupPg.enterPIN(pay.pin);
			popupPg.clickOK();
			page = browser.waitExists(alert, printPg, vmOrdSumPg);
		}

		if (page == printPg) { // print ticket popup
			// /printPg.close();
			printPg.waitForDisappear();
			vmOrdSumPg.waitLoading();
		}
		String reservNum = vmOrdSumPg.getAllOrdNums();

		if (isPrint == true) {
			vmOrdSumPg.clickPrintTickets();
			// vmOrdSumPg.waitExists();
			page = browser.waitExists(printPg, alert, vmOrdSumPg);

			if (page == printPg) {
				// printPg.waitForDisappear();
				printPg.close();
				vmOrdSumPg.waitLoading();
			}

		}

		return reservNum;
	}

	public String processOrderCartToSummaryPg(Payment pay) {
		return processOrderCartToSummaryPg(pay, false);
	}

	/**
	 * This method executes the work flows of make multiple tickets to order
	 * cart
	 * 
	 * @param ticket
	 * @param done
	 *            -----whether all tickets has been made
	 */
	public void makeMulTicketOrderToCart(TicketInfo ticket, Boolean done) {
		makeMulTicketOrderToCart(ticket, false, done);
	}
	
	/**
	 * This method executes the work flows of make multiple tickets to order cart
	 * @param ticket
	 * @param isCombTour
	 * @param done
	 */
	public void makeMulTicketOrderToCart(TicketInfo ticket, Boolean isCombTour, Boolean done) {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage
				.getInstance();

		logger.info("Make multiple tickets to Order Cart.");

		this.makeTicketOrderToCart(ticket, isCombTour);
		if (!done) {
			vmOrderCartPg.clickAddTicketOrder();
			vmTicketAvailSchPg.waitLoading();
		}
	}

	/**
	 * This method makes a new ticket order to order cart. The work flow : From:
	 * Venue manager home page, Ends: order cart Page
	 * 
	 * @param ticket
	 *            - the ticket information
	 */
	public void makeTicketOrderToCart(TicketInfo ticket) {
		makeTicketOrderToCart(ticket, false);
	}
	
	public void makeTicketOrderToTicketSelectPg(TicketInfo ticket, boolean isComboTour){
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsTicketSelectionPage vmAddTicketToCartPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsAlertPage alertPg = OrmsAlertPage.getInstance();
		ConfirmDialogPage confirmDiaPg = ConfirmDialogPage.getInstance();
		logger.info("Make a" + (isComboTour ? " combo tour" : "") + " ticket order to cart.");

		if (!vmTicketAvailSchPg.exists()) {
			vmHmPg.clickSales();
			vmTicketAvailSchPg.waitLoading();
		}

		vmTicketAvailSchPg.searchAvailibility(ticket);
		vmTicketAvailSchPg.waitLoading();
		if(isComboTour) {
			vmTicketAvailSchPg.selectComboTourTicket(ticket.comboTourName, ticket.comboChildTours);
		} else {
			vmTicketAvailSchPg.selectTicket(ticket.timeSlot, ticket.quantity);
		}
		vmTicketAvailSchPg.clickPurchaseTickets();

		Object page = browser.waitExists(confirmDiaPg, alertPg, vmAddTicketToCartPg);
		if (page == confirmDiaPg) {
			confirmDiaPg.clickOK();
			vmAddTicketToCartPg.waitLoading();
		}
		if (alertPg == page) {
			alertPg.clickOK();
			vmAddTicketToCartPg.waitLoading();
		}
	}
	
	public void makeTicketOrderFromTicketSelectPgToCart(TicketInfo ticket){
		OrmsTicketSelectionPage vmAddTicketToCartPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketOrderDetailsPage orderDetailsPage = OrmsTicketOrderDetailsPage.getInstance();
		vmAddTicketToCartPg.setTicketTypeNumber(ticket.types, ticket.typeNums);
		if (vmAddTicketToCartPg.isIDPassRequired()) {
			this.setID_PassForTicket(ticket.passes, vmAddTicketToCartPg);
		}
		if (StringUtil.notEmpty(ticket.deliveryMethod)) {
			vmAddTicketToCartPg.selectDeliveryMethod(ticket.deliveryMethod);
		} else if(null == ticket.deliveryMethod && vmAddTicketToCartPg.checkDeliveryMethodSectionExists()){
			vmAddTicketToCartPg.selectDeliveryMethod("Will Call");//Set 'Will Call' as default delivery method
		}
		vmAddTicketToCartPg.waitLoading();
		vmAddTicketToCartPg.clickAddToCart();
		
		Object page = browser.waitExists(orderDetailsPage, vmOrderCartPg);
		if(page == orderDetailsPage) {
			if(ticket.tourParticipantAttributesForPerInventory.size() > 0) {
				orderDetailsPage.setTourParticipantAttributesForPerInventory(ticket.tourParticipantAttributesForPerInventory);
			}
			if(ticket.tourParticipantAttributesForPerTicket.size() > 0) {
				String tempTicketType = "";
				for(int i = 0 ; i < ticket.types.length; i ++) {
					tempTicketType = ticket.types[i];
					orderDetailsPage.setTourParticipantAttributesForPerTicket(tempTicketType, ticket.tourParticipantAttributesForPerTicket.get(tempTicketType));
				}
			}
			orderDetailsPage.clickOK();
		}
//		vmOrderCartPg.waitExists();
		vmOrderCartPg.waitLoading();
	}
	/**
	 * Make ticket tour(including ) order to order cart
	 * @param ticket
	 * @param isComboTour
	 */
	public void makeTicketOrderToCart(TicketInfo ticket, boolean isComboTour) {
		this.makeTicketOrderToTicketSelectPg(ticket, isComboTour);
		this.makeTicketOrderFromTicketSelectPgToCart(ticket);
	}
	
	/**
	 * Make lottery ticket tour(including ) order to order cart
	 * @param ticket
	 * 
	 */
	public void makeLotteryTicketOrderToCart(TicketInfo ticket, Customer cust) {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage.getInstance();
		OrmsLotteryApplicationDetailsPage lotteryDetailPg = OrmsLotteryApplicationDetailsPage.getInstance();
		
		
		logger.info("Make a lottery ticket order to cart.");

		if (!vmTicketAvailSchPg.exists()) {
			vmHmPg.clickSales();
			vmTicketAvailSchPg.waitLoading();
		}

		vmTicketAvailSchPg.searchAvailibility(ticket);
		vmTicketAvailSchPg.waitLoading();
		
		vmTicketAvailSchPg.selectLotteryTicket(ticket.timeSlot);

		vmTicketAvailSchPg.clickPurchaseTickets();
		omCustSchPg.waitLoading();
		omCustSchPg.searchCust(cust.fName, cust.lName, cust.email);
		omCustSchPg.searchWaitExists();
		omCustSchPg.selectCust(cust.lName);
		lotteryDetailPg.waitLoading();
		lotteryDetailPg.setTicketLotteryInfo(ticket.lotteryChoices);
		lotteryDetailPg.clickOK();
		ajax.waitLoading();
		
		Object page = browser.waitExists(lotteryDetailPg,vmOrderCartPg);
		
		if (page == lotteryDetailPg) {
			lotteryDetailPg.clickOK();
			vmOrderCartPg.waitLoading();
		}
		
	}

	public void gotoLotteryApplicationDetailPage(String lotteryNumber)
	{
		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
		OrmsLotteryApplicationDetailsPage lotteryDetailPg = OrmsLotteryApplicationDetailsPage
				.getInstance();
		OrmsLotteryApplicationSearchPage lotterySchPg = OrmsLotteryApplicationSearchPage
				.getInstance();

		logger.info("Going to lottery application" + lotteryNumber
				+ " details page in VM.");

		vmTopMenuPg.selectSearchDropDown("Lottery Applications");

		lotterySchPg.waitLoading();
		lotterySchPg.setApplicationNum(lotteryNumber);
		lotterySchPg.clickGO();
		ajax.waitLoading();
		lotterySchPg.clickApplicationNumber(lotteryNumber);

		lotteryDetailPg.waitLoading();
	}
	
	public void acceptLotteryToOrderCartPg() {
		OrmsLotteryApplicationDetailsPage lotteryDetailPg = OrmsLotteryApplicationDetailsPage
				.getInstance();
		OrmsTicketOrderDetailsPage OrdDetailsPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("accept lottery order");

		lotteryDetailPg.clickAccept();
		ajax.waitLoading();
		OrdDetailsPg.waitLoading();
		OrdDetailsPg.clickAccept();
		vmOrderCartPg.waitLoading();
			

	}

	public void revokeLotteryToCart(String reason, String note) {
		OrmsLotteryApplicationDetailsPage lotteryDetailPg = OrmsLotteryApplicationDetailsPage
				.getInstance();
		OrmsOrderCartPage pmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsRevokeLotteryPage revokePg = OrmsRevokeLotteryPage.getInstance();

		logger.info("revoke lottery order into order cart.");

		lotteryDetailPg.clickRevoke();
		ajax.waitLoading();
		revokePg.waitLoading();
		revokePg.selectReason(reason);
		revokePg.setNotes(note);
		revokePg.clickCompleteRevoke();

		pmOrdCartPg.waitLoading();
	}

	
	
	/**
	 * This method executes the work flow of entering Fee details page in order
	 * cart. The work flow starts from order cart and ends in fee details page
	 * 
	 * @param resID
	 * @param notCampOrder
	 *            --mark the order is camping reservation order or not
	 */
	public void gotoFeeDetailsPageInCart(String resID, boolean notCampOrder) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsFeeDetailsPage feeDetailsPg = OrmsFeeDetailsPage.getInstance();

		logger.info("Go to fee detail page from order cart page.");
		ordCartPg.goFeeDetail(resID, notCampOrder);
		feeDetailsPg.waitLoading();
	}

	/**
	 * This method executes the work flow of entering Fee details page in order
	 * cart. The work flow starts from order cart and ends in fee details page
	 * From: OrmsOrderCartPage To: OrmsFeeDetailsPage
	 * 
	 * @param resID
	 *            - the reservation number
	 */
	public void gotoFeeDetailsPageInCart(String resID) {
		OrmsOrderCartPage pmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsFeeDetailsPage feeDetailsPg = OrmsFeeDetailsPage.getInstance();

		pmOrdCartPg.goFeeDetail(resID, true);
		feeDetailsPg.waitLoading();
	}

	/**
	 * This method goes into Ticket order details page. The work flow starts
	 * from Venue manager home page and ends at ticket order details page.
	 * 
	 * @param ordID
	 */
	public void gotoTiketOrderDetails(String ordID) {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsTicketOrderSearchPage vmTicketSchPg = OrmsTicketOrderSearchPage
				.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();

		logger.info("Go to ticket#" + ordID + " order details page.");

		vmHmPg.searchTicketOrder();

		vmTicketSchPg.waitLoading();
		gotoTicketOrderDetailsFromSearchPg(ordID);
	}
	
	public void gotoTicketOrderDetailsFromSearchPg(String ordID){
		OrmsTicketOrderSearchPage vmTicketSchPg = OrmsTicketOrderSearchPage
				.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		vmTicketSchPg.searchTicketByOrderNumber(ordID);
		vmTicketSchPg.waitLoading();
		vmTicketSchPg.selectTicket(ordID);

		vmTicketDetailPg.waitLoading();
	}

	/**
	 * This method executes the work flows of going to ticket invoice
	 * search/list page The work flow starts from the Venue Manager top menu
	 * page and ends at the nvoice search/list page
	 * 
	 */
	public void gotoInvoiceSearchListPage() {
		OrmsInvoiceSearchPage oInvSchPg = OrmsInvoiceSearchPage.getInstance();
		VnuMgrTopMenuPage vmTopPg = VnuMgrTopMenuPage.getInstance();

		logger.info("Go to Invoice search/list page from top menu.");
		Object page = browser.waitExists(oInvSchPg, vmTopPg);
		if (page != oInvSchPg) {
			vmTopPg.selectSearchDropDown("Invoices");
		}
		oInvSchPg.waitLoading();
	}

	/**
	 * Search invoice records based on the given invoice data collection info.
	 * this work flow starts and ends at invoice search/list page.
	 * 
	 * @param inv
	 */
	public void searchInvoiceBasedOnInvoiceInfo(InvoiceInfo inv) {
		OrmsInvoiceSearchPage oInvSchPg = OrmsInvoiceSearchPage.getInstance();
		gotoInvoiceSearchListPage();
		oInvSchPg.searchInvoice(inv);
		oInvSchPg.waitLoading();
	}

	/**
	 * This work flow star from fee detail page, end to ticket order cart page.
	 */
	public void gotoTicketOrderCartPgFromFeeDetailPg() {
		OrmsFeeDetailsPage vmFeeDetailPg = OrmsFeeDetailsPage.getInstance();
		OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Got to ticket order cart page from fee detail page");
		vmFeeDetailPg.clickOK();
		vmOrdCartPg.waitLoading();
	}

	/**
	 * 
	 * go to tour details page from Vm home page/ticket selection page/ticket
	 * Availability page
	 * 
	 * @param tourname
	 * @param page
	 *            Vm home page/ticket selection page/ticket Availability page
	 * @Return void
	 * @Throws
	 */
	public void verifyTourDetailsPage(String tourname, OrmsPage page) {
		OrmsTourDetaisPage detailspage = OrmsTourDetaisPage.getInstance();
		OrmsTicketSelectionPage ticketselection = OrmsTicketSelectionPage
				.getInstance();
		OrmsTicketAvailabilityPage ticketAvailPage = OrmsTicketAvailabilityPage
				.getInstance();
		VnuMgrHomePage vmHomePage = VnuMgrHomePage.getInstance();
		logger.info("verify tour details page?");
		if (page == vmHomePage) {
			logger.info("goto tour details page from VM Home page....");
			if (vmHomePage.checkHtmlLinkExist(tourname)) {
				vmHomePage.clickTourNameUrl(tourname);
			} else {
				throw new ItemNotFoundException(
						"Tour Name Url is not Existent~!");
			}
		} else if (page == ticketselection) {
			logger
					.info("go to tour details page from ticket selection page...");

			ticketselection.clickTourNameUrl(tourname);
		} else if (page == ticketAvailPage) {
			logger
					.info("go to tour details page from ticket availability page....");
			ticketAvailPage.clickTourNameUrl(tourname);
		} else {
			throw new PageNotFoundException(page.getName()
					+ "is not expected as a starting page ~!");
		}

		Object endpage = browser.waitExists(detailspage);
		if (endpage != detailspage) {
			throw new ActionFailedException(
					"Tour details page is not existent~!");
		}
		logger.info("Tour details page is existent.....");
	}

	/**
	 * 
	 * go to Ticket availability page by clicking top "sales" url
	 * 
	 * @Return void
	 * @Throws
	 */
	public void gotoTicketAvailabilityPage() {
		VnuMgrTopMenuPage topmenu = VnuMgrTopMenuPage.getInstance();
		logger.info("Go to Ticket Availability Page...");
		topmenu.clickSales();
		browser.waitExists(OrmsTicketAvailabilityPage.getInstance());
	}

	/**
	 * Goto ticket search page and print ticket action. The ends page is ticket
	 * order details page
	 * 
	 * @param ordNum
	 */
	public void gotoTicketSearchPgToPrintTicket(String ordNum) {
		OrmsTicketOrderSearchPage vmTicketSchPg = OrmsTicketOrderSearchPage
				.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();

		logger.info("Go to Ticket Order Search/List page to print ticket.");

		vmHmPg.searchTicketOrder();
		vmTicketSchPg.waitLoading();
		vmTicketSchPg.searchTicketByOrderNumber(ordNum);
		vmTicketSchPg.waitLoading();

		IHtmlObject[] objs = vmTicketSchPg.getPrintObject();

		for (int i = objs.length - 1; i >= 0; i--) {
			if (i == objs.length - 1) {
				objs[i].doubleClick();
			} else {
				objs[i].click();
			}

			Object page = browser.waitExists(printPg, alert, vmTicketSchPg);
			if (page == printPg) {
				printPg.close();
				if (page == alert) {
					alert.clickOK();
				}
				vmTicketSchPg.waitLoading();
			}
		}
		Browser.unregister(objs);
		// Goto ticket detail page after printing all the tickets
		vmTicketSchPg.selectTicket(ordNum);
		vmTicketDetailPg.waitLoading();
	}

	/**
	 * This method cancels a ticket order. The work flow starts from order
	 * details page and ends at order cart.
	 */
	public void cancelTicketToCart(boolean allcancel, String cancelTicketNum) {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsCancelTicketOrderPage vmCancelTicketPg = OrmsCancelTicketOrderPage
				.getInstance();

		logger.info("Cancel ticket to order cart.");

		vmTicketDetailPg.clickCancelTickets();

		vmCancelTicketPg.waitLoading();
		if (allcancel) {
			vmCancelTicketPg.selectCancelAllCheckBox();
		} else {
			vmCancelTicketPg.setTicketNumberToCancel(cancelTicketNum);
		}
		vmCancelTicketPg.clickCancelTickets();

		vmOrderCartPg.waitLoading();
	}

	/**
	 * This method cancels tickets in order cart. The work flow starts from
	 * order details page and ends at home page.
	 */
	public void cancelAllTicketInCart() {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		VnuMgrEndCartPage vmEndCartPg = VnuMgrEndCartPage.getInstance();
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();

		logger.info("Cancel All tickets in current cart.");

		vmOrderCartPg.clickCancelCart();

		vmEndCartPg.waitLoading();
		vmEndCartPg.clickOkToCancelCart();

		vmHmPg.waitLoading();
	}

	/**
	 * This method executes the work flows of canceling call. The work flow
	 * starts from Top Menu to Venue manager home page.
	 */
	public void cancelCart() {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
		VnuMgrEndCartPage vmEndCallPg = VnuMgrEndCartPage.getInstance();
		OrmsEnterPinNumPopupPage ormsRulePopup = OrmsEnterPinNumPopupPage.getInstance();
		
		if (vmTopMenuPg.cartExists()) {
			logger.info("Cancel cart to Venue Manager home page.");
			vmTopMenuPg.clickCancelCart();
			Object page = browser.waitExists(ormsRulePopup, vmEndCallPg);
			if(page == ormsRulePopup){//This happens override rule enter pin pop up slowly at order cart page.
				ormsRulePopup.clickCancel();
			}
			vmEndCallPg.waitLoading();

			vmEndCallPg.clickOkToCancelCart();
			vmHmPg.waitLoading();
		} else {
			logger.info("There is no cart existing.");
		}
	}
	
	public boolean tryToCancelCart() {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
		VnuMgrEndCartPage vmEndCallPg = VnuMgrEndCartPage.getInstance();
		OrmsEnterPinNumPopupPage ormsRulePopup = OrmsEnterPinNumPopupPage.getInstance();
		try {
			if (vmTopMenuPg.cartExists()) {
				logger.info("Cancel cart to Venue Manager home page.");
				vmTopMenuPg.clickCancelCart();
				Object page = browser.waitExists(ormsRulePopup, vmEndCallPg);
				if(page == ormsRulePopup){//This happens override rule enter pin pop up slowly at order cart page.
					ormsRulePopup.clickCancel();
				}
				vmEndCallPg.waitLoading();
	
				vmEndCallPg.clickOkToCancelCart();
				vmHmPg.waitLoading();
			} else {
				logger.info("There is no cart existing.");
			}
			return true;
		} catch(Exception e) {
			logger.warn("Failed to cancel cart due to "+e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * cancel ticket purchase from ticket selection page. if you have other
	 * requriment,you can add you code.
	 * 
	 * @param page
	 * @Return void
	 * @Throws
	 */
	public void cancelTicketPurchase(OrmsPage page) {
		if (page instanceof OrmsTicketSelectionPage) {
			OrmsTicketSelectionPage apage = (OrmsTicketSelectionPage) page;
			logger.info("Cancel ticket purchase from Ticket Selection Page");
			apage.clickCancel();
			browser.waitExists(OrmsTicketAvailabilityPage.getInstance());
		}

	}

	/**
	 * This method adds more tickets to an existing ticket order. The work flow
	 * starts from order details page and ends at order cart.
	 * 
	 * @param types
	 *            - the array of ticket types to be added
	 * @param typeNums
	 *            - the array of numbers for each ticket type
	 */
	public void addMoreTicketToCart(String[] types, String[] typeNums) {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsAddTicketPage vmAddMoreTicketslPg = OrmsAddTicketPage.getInstance();

		logger.info("Add more tickets into order cart.");

		vmTicketDetailPg.clickAddTickets();

		vmAddMoreTicketslPg.waitLoading();
		vmAddMoreTicketslPg.addMoreTickets(types, typeNums);
		vmAddMoreTicketslPg.clickAddTickets();

		vmOrderCartPg.waitLoading();
	}

	/**
	 * This method changes the ticket time. The work flow starts from order
	 * details page and ends at order cart.
	 * 
	 * @param startDate
	 *            - the new start date
	 * @param timeSlot
	 *            - the timeslot for the ticket
	 * @param ticketNum
	 *            - the number of tickets
	 * @param isTimeNotSpecified
	 *            - is change to time not specified
	 * @param feeType
	 *            - the type of cancel tickets
	 * @param changeReason
	 *            - the change reason
	 */
	public void changeTimeToCart(String startDate, String timeSlot,
			String ticketNum, boolean isTimeNotSpecified, String[] feeType,
			String changeReason) {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketAvailabilityPage vmChangeTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsTicketSelectionPage vmTransferSelectionPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();

		logger.info("Change ticket time to start="
				+ (startDate.length() > 0 ? startDate : "unknown") + " time="
				+ (timeSlot.length() > 0 ? timeSlot : "unknown") + " number="
				+ ticketNum + " into order cart.");

		vmTicketDetailPg.clickChangeTicketTime();

		vmChangeTicketsAvailPg.waitLoading();
		vmChangeTicketsAvailPg.changeTicketTime(startDate, timeSlot, ticketNum,
				isTimeNotSpecified);
		vmChangeTicketsAvailPg.clickChangeTicketTime();

		vmTransferSelectionPg.waitLoading();

		if (null != changeReason) {

			vmTransferSelectionPg.selectChangeReason(changeReason);
		}

		if (null != feeType) {

			vmTransferSelectionPg.selectToTicketType(feeType);
			vmTransferSelectionPg.setTicketQuantity(ticketNum);
		}
		vmTransferSelectionPg.clickOK();

		Object page = browser.waitExists(ormsAlertPg, vmOrderCartPg);
		if (page == ormsAlertPg) {
			ormsAlertPg.clickOK();
			vmOrderCartPg.waitLoading();
		}
	}

	/**
	 * This method changes the ticket time. The work flow starts from order
	 * details page and ends at order cart.
	 * 
	 * @param startDate
	 *            - the new start date
	 * @param timeSlot
	 *            - the timeslot for the ticket
	 * @param ticketNum
	 *            - the number of tickets
	 * @param isTimeNotSpecified
	 *            - is change to time not specified
	 */
	public void changeTimeToCart(String startDate, String timeSlot,
			String ticketNum, boolean isTimeNotSpecified) {
		changeTimeToCart(startDate, timeSlot, ticketNum, isTimeNotSpecified, null, null, null, null, null, null, false);
	}

	/**
	 * This method changes the ticket time. The work flow starts from order
	 * details page and ends at order cart.
	 * 
	 * @param startDate
	 *            - the new start date
	 * @param timeSlot
	 *            - the timeslot for the ticket
	 * @param ticketNum
	 *            - the number of tickets
	 */
	public void changeTimeToCart(String startDate, String timeSlot,
			String ticketNum) {
		changeTimeToCart(startDate, timeSlot, ticketNum, false);
	}

	/**
	 * This method transfers a ticket. The work flow starts from Ticket details
	 * page and ends at order cart.
	 * 
	 * @param startDate
	 *            - the start date
	 * @param timeSlot
	 *            - the time slot
	 * @param ticketNum
	 *            - the number of tickets No transfer reason
	 */
	public void transferTicketToCart(TicketInfo ticket) {
		transferTicketToCart(ticket, null, false);
	}
	
	public void transferTicketToCart(TicketInfo ticket,String reason){
		transferTicketToCart(ticket, reason, false);
	}

	/**
	 * This method transfers a ticket. The work flow starts from Ticket details
	 * page and ends at order cart.
	 * 
	 * @param startDate
	 *            - the start date
	 * @param timeSlot
	 *            - the time slot
	 * @param ticketNum
	 *            - the number of tickets
	 * @param transferReason
	 *            - transfer reason
	 */
	public void transferTicketToCart(TicketInfo ticket, String transferReason, boolean isComboTour) {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketTransferSelectionPage vmTransferSelectionPg = OrmsTicketTransferSelectionPage
				.getInstance();
		OrmsTicketAvailabilityPage vmTransferTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		OrmsTourParticipantsWidget ormsTourParticipantPg = OrmsTourParticipantsWidget.getInstance();

		logger
				.info("transfer ticket to start="
						+ (ticket.startDate.length() > 0 ? ticket.startDate
								: "unknown")
						+ " time="
						+ (ticket.timeSlot.length() > 0 ? ticket.timeSlot
								: "unknown") + " number=" + ticket.quantity
						+ " into order cart.");

		vmTicketDetailPg.clickTransferTickets();

		vmTransferTicketsAvailPg.waitLoading();
		vmTransferTicketsAvailPg.transferOrChangeTicket(ticket,isComboTour);
		vmTransferTicketsAvailPg.clickTransferTicket();
		Object page = browser.waitExists(vmTransferSelectionPg, ormsAlertPg);
		if (page == ormsAlertPg) {
			ormsAlertPg.clickOK();
			vmTransferSelectionPg.waitLoading();
		}
		vmTransferSelectionPg.doTicketSelection(null, ticket.types,
				ticket.typeNums);
		if (transferReason != null) {
			vmTransferSelectionPg.selectTransferReason(transferReason);
		}
		vmTransferSelectionPg.clickOK();
		ajax.waitLoading();

		alert.setBeforePageLoading(true);
		Object pages = browser.waitExists(alert, ormsTourParticipantPg,vmOrderCartPg);
		
		if (page == alert) {
			alert.clickOK();
			vmOrderCartPg.waitLoading();
		}
		
		if(ormsTourParticipantPg == pages){
			if(ticket.removedTourParticipantAttributesForPerTicket.size()>0){
				ormsTourParticipantPg.removeTPAInfoOfPerTicket(ticket.removedTourParticipantAttributesForPerTicket);
			}
			
			if(ticket.updatedTourParticipantAttributesForPerTicket.size()>0){
				ormsTourParticipantPg.setTourParticipantAttributesForPerTicket(ticket.transferFromTourName, ticket.updatedTourParticipantAttributesForPerTicket);
			}
			
			if(ticket.updateTourParticipantAttributesForPerInventory.size()>0){
				ormsTourParticipantPg.setTourParticipantAttributesForPerInventory(ticket.transferFromTourName, ticket.updateTourParticipantAttributesForPerInventory);
			}
			
			if(ticket.tourParticipantAttributesForPerInventory.size() > 0){
				ormsTourParticipantPg.setTourParticipantAttributesForPerInventory(ticket.tour, ticket.tourParticipantAttributesForPerInventory);
			}
			
			if(ticket.tourParticipantAttributesForPerTicket.size() > 0){
				ormsTourParticipantPg.setTourParticipantAttributesForPerTicket(ticket.tour, ticket.tourParticipantAttributesForPerTicket);
			}
			
			ormsTourParticipantPg.clickOK();
			ajax.waitLoading();
			vmOrderCartPg.waitLoading();
		}			
	}

	/**
	 * This method invalids tickets. The work flow starts from and ends at
	 * ticket order details page
	 */
	public void invalidTickets() {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		VnuMgrManageTicketsPage vmManageTicketPg = VnuMgrManageTicketsPage
				.getInstance();

		logger.info("Invalid tickets in ticket order details page.");

		vmTicketDetailPg.clickManageTickets();

		vmManageTicketPg.waitLoading();
		vmManageTicketPg.invalidateAllTickets();
		vmManageTicketPg.waitLoading();
//		if (MiscFunctions.isQA24()) {
//			vmManageTicketPg.clickCancel();
//		} else {
//			vmManageTicketPg.clickReturnToOrderDetail();
//		}
		// Only Cancel button displayed in build3.02
		vmManageTicketPg.clickCancel();
		vmTicketDetailPg.waitLoading();
	}

	// /**
	// * This method voids tickets. The work flow starts from ticket order
	// details
	// * page and ends at order cart
	// *
	// * @param reason
	// * @param note
	// */
	// public void voidTicketToCart(String reason, String note) {
	// OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
	// OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
	// .getInstance();
	// OrmsVoidTicketOrderPage vmVoidTicketPg = OrmsVoidTicketOrderPage
	// .getInstance();
	//
	// logger.info("Void ticket order into order cart.");
	//
	// vmTicketDetailPg.clickVoidTickets();
	//
	// vmVoidTicketPg.waitExists();
	// vmVoidTicketPg.voidTicketOrder(reason, note);
	// vmOrderCartPg.waitExists();
	// }

	/**
	 * This method searched the receipt This work flow starts from ticket order
	 * details page and ends at receipt search page
	 * 
	 * @param ticket
	 *            order ID
	 * 
	 * */
	public void searchTicketReceipt(String orderID) {
		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
		VnuMgrReceiptsSearchPage vmReceSearPg = VnuMgrReceiptsSearchPage
				.getInstance();
		logger.info("Search the receipt by ticket order ID.");

		vmTopMenuPg.goToReceiptPage();
		vmReceSearPg.waitLoading();
		vmReceSearPg.setOrderNum(orderID);
		vmReceSearPg.clickGo();

		vmReceSearPg.waitLoading();
	}
	
	/**
	 * The method Change the ticket type The work flow start from ticket order
	 * details page and ends at order cart page
	 * 
	 * @param fType
	 *            is previous type, tType is changed type and addNum is added
	 *            number.
	 * */
	public void changeTicketType(String fType, String tType, String addNum) {
		changeTicketType(null, fType, tType, addNum, null, null);
	}
	
	public void gotoChangeTickTypePg(){
		OrmsChangeTicketTypePage vmChangeTicketpg = OrmsChangeTicketTypePage
				.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		
		logger.info("Go to change ticket type detail page.");
		vmTicketDetailPg.clickChangeTicketType();
		vmChangeTicketpg.waitLoading();
	}
	
	public void cancelFromChangeTicketType(){
		OrmsChangeTicketTypePage vmChangeTicketpg = OrmsChangeTicketTypePage
				.getInstance();
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		
		logger.info("Go to order detail page from change ticket type page.");
		vmChangeTicketpg.clickCancel();
		vmTicketDetailPg.waitLoading();
	}
	
	public void changeTicketType(String tourName, String fType, String tType, String addNum, Map<String, List<Map<String, String>>> wantRemoveTPA, Map<String, List<Map<String, String>>> newAddTPA) {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsChangeTicketTypePage vmChangeTicketpg = OrmsChangeTicketTypePage
				.getInstance();
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget.getInstance();
		
		logger.info("Change the ticket's type");
		vmTicketDetailPg.clickChangeTicketType();
		vmChangeTicketpg.waitLoading();
		vmChangeTicketpg.changeTicketType(fType, tType, addNum);
		vmChangeTicketpg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(tpaWidget, vmOrderCartPg);
		if(page == tpaWidget) {
			if(wantRemoveTPA != null && wantRemoveTPA.size() > 0) {
				tpaWidget.removeTPAInfoOfPerTicket(wantRemoveTPA);
			}
			tpaWidget.addTourParticipantAttributesForPerTicket(tourName, newAddTPA);
			tpaWidget.clickOK();
			ajax.waitLoading();
		}
		
		vmOrderCartPg.waitLoading();
	}

	/**
	 * Verify the ticket status in ticket details page
	 * 
	 * @param expectStatus
	 */
	public void verifyStatus(String expectStatus) {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		logger.info("Verify ticket status.");
		if (!vmTicketDetailPg.getOrderStatus().equalsIgnoreCase(expectStatus)) {
			throw new ItemNotFoundException("The ticket status should be "
					+ expectStatus);
		}
	}

	/**
	 * Select the customer from the order cart page
	 * 
	 * @param cust
	 *            -- the selected customer
	 */
	public void selectCustomerFromOrderCart(Customer cust) {
		OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		logger.info("Select the customer from the order cart page");
		vmOrdCartPg.clickChangeCustomer();
		omCustSchPg.waitLoading();

		omCustSchPg.searchCust(cust.fName, cust.lName, cust.email);
		omCustSchPg.searchWaitExists();
		omCustSchPg.selectCust(cust.lName);

		vmOrdCartPg.waitLoading();
	}

	public void addTourInventory(InventoryInfo tourInv,
			boolean isTimeSpecified, boolean isDateSpecified) {
		this.addTourInventory(tourInv, isTimeSpecified, isDateSpecified, false, true);
	}

	/**
	 * Go to tour inventory page from home page
	 */
	public void gotoTourInventoryPageFromHomePage() {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		VnuMgrTourInventoryListPage tourInvListPg = VnuMgrTourInventoryListPage
				.getInstance();

		logger.info("Go to tour inventory page from home page.");
		vmHmPg.clickTourInventory();
		tourInvListPg.waitLoading();
	}

	/**
	 * 
	 * Search the specified date's tour inventory and delete it first; and then
	 * add new tour inventory, this method starts from venue manager home page
	 * and ends at tour inventory list page.
	 * 
	 * @param tourInv
	 *            - tour inventory data
	 * @param isTimeSpecified
	 *            - is time specified?
	 * @param isDateSpecified
	 *            - is date specified?
	 * @param isDeleteExistingInv
	 * 			  - is delete existing tour inventory?
	 */
	public void addTourInventory(InventoryInfo tourInv,
			boolean isTimeSpecified, boolean isDateSpecified,
			boolean isNonTimeSpecific, boolean isDeleteExistingInv) {
		VnuMgrTourInventoryListPage tourInvListPg = VnuMgrTourInventoryListPage
				.getInstance();
		VnuMgrAddTourInventoryPage addTourInvPg = VnuMgrAddTourInventoryPage
				.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Add tour inventories.");
		this.gotoTourInventoryPageFromHomePage();

		tourInv.status = "Active"; // make sure the search status is for Active,
		// if not, it is hard to verify the delete
		// results

		// This code snap is a short solution used to handle the scenario of
		// "To time" can't be blank(have send mail to NA to confirm this
		// senerio),
		if (tourInv.firstTime.trim().length() > 0
				&& tourInv.lastTime.equals("")) {
			tourInv.lastTime = "11:59";
			tourInv.lastTimeStamp = "PM";
		}

		if(isDeleteExistingInv){
			tourInvListPg.searchTourInventory(tourInv);
			boolean controller = false;
			do {
				if (tourInvListPg.verifyNoResult()) {
					break; // do nothing when no result found
				} else {
					controller = tourInvListPg.isFindNext();// verify find next link
															// before deleting invs
					tourInvListPg.checkAllCheckbox();
					tourInvListPg.clickDelete(); // delete previous inventory if
													// exists
					browser.waitExists(confirmDialog, tourInvListPg);
	
					//Avoid only one page data
					if(!tourInvListPg.verifyNoResult()){
						tourInvListPg.checkAllCheckbox();
						tourInvListPg.clickCancelTourInv(); // Cancel inventory which
															// have been booked or on
															// hold
						browser.waitExists(confirmDialog, tourInvListPg);
					}
				}// if sperate delete and cancel, may cause dead loop when hold
					// inventories more than one page
			} while (controller);
	
			tourInvListPg.waitLoading();
	
			if (!tourInvListPg.verifyNoResult()) {
				throw new ItemNotFoundException("Cancel inventory failed!");
			} else {
				logger.info("---- Succeed to remove previous inventories.");
			}
		}

		tourInvListPg.clickAddTourInventory();// add tour inventory here
		addTourInvPg.waitLoading();
		addTourInvPg.setupTourInvCriteria(tourInv, isTimeSpecified,
				isDateSpecified, isNonTimeSpecific);

		addTourInvPg.clickCreateTourInventory();

		Object page = browser.waitExists(confirmDialog, tourInvListPg);
		if (page == confirmDialog)
			tourInvListPg.waitLoading();

		if (tourInvListPg.verifyAddSuccess()) {
			logger.info("---Tour inventory added successful.");
		} else {
			throw new ErrorOnPageException("Failed to add tour inventory!");
		}
	}

	/**
	 * This method goes to remove the specified date tour inventories. It starts
	 * from venue manager home page and ends at tour inventory list page.
	 * 
	 * @param tourInv
	 *            - tour inventory data
	 */
	public void deleteTourInventory(InventoryInfo tourInv) {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		VnuMgrTourInventoryListPage tourInvListPg = VnuMgrTourInventoryListPage
				.getInstance();
		ConfirmDialogPage conDialog = ConfirmDialogPage.getInstance();

		logger.info("Remove tour inventories for specified date.");
		vmHmPg.clickTourInventory();
		tourInvListPg.waitLoading();

		tourInvListPg.searchTourInventory(tourInv);
		if (!tourInvListPg.verifyNoResult()) {// delete previous inventory if
			// exists
			tourInvListPg.checkAllCheckbox();
			tourInvListPg.clickDelete();
			conDialog.waitLoading();
			tourInvListPg.waitLoading();
			// Cancel inventory which have been booked or on hold
			if (!tourInvListPg.verifyNoResult()) {
				tourInvListPg.checkAllCheckbox();
				tourInvListPg.clickCancelTourInv();
				conDialog.waitLoading();
				tourInvListPg.waitLoading();
			}
			if (!tourInvListPg.verifyNoResult()) {
				throw new ItemNotFoundException("Cancel inventory failed!");
			} else {
				logger.info("Succeed to remove inventories.");
			}
		}
	}

	/**
	 * Overload method of verifyBusinessRuleEffectiveAtOrderCartPage(String
	 * ruleName, String ruleCondID, TicketInfo ticket, String shortNameOfTour,
	 * Payment pay, boolean isPrint, boolean isNegative)
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param ticket
	 * @param shortNameOfTour
	 * @param pay
	 * @param isNegative
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtOrderCartPage(String ruleName,
			String ruleCondID, TicketInfo ticket, String shortNameOfTour,
			Payment pay, boolean isNegative) {
		return this.verifyBusinessRuleEffectiveAtOrderCartPage(ruleName,
				ruleCondID, ticket, shortNameOfTour, pay, false, isNegative);
	}

	/**
	 * Method verifyBusinessRuleEffectiveAtOrderCartPage - verify whether the
	 * business rule validation message displayed at the Order Cart page
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param ticket
	 * @param shortNameOfTour
	 * @param pay
	 * @param isPrint
	 * @param isNegative
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtOrderCartPage(String ruleName,
			String ruleCondID, TicketInfo ticket, String shortNameOfTour,
			Payment pay, boolean isPrint, boolean isNegative) {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsOrderSummaryPage vmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsProcessOrderCartPopupPage popupPg = OrmsProcessOrderCartPopupPage.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
		OrmsEnterPinNumPopupPage rulePopupPg = OrmsEnterPinNumPopupPage.getInstance();//This is a override rule enter pin pop-up
		String statusMsg = "";

		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in Venue Manager.");
		logger.info("----Process permit order cart.");

		vmOrdCartPg.setupPayment(pay);
		if(pay!=null)
			vmOrdCartPg.setupAutoPrintTickets(pay);
		vmOrdCartPg.clickProcessOrder();

		alert.setDismissible(true);
		alert.setBeforePageLoading(false);
		Object page = browser.waitExists(alert, printPg, popupPg, rulePopupPg, vmOrdSumPg, vmOrdCartPg);

		if (page == popupPg) { // pin popup or print ticket popup
			popupPg.enterPIN(pay.pin);
			popupPg.clickOK();
			page = browser.waitExists(alert, printPg, vmOrdSumPg, vmOrdCartPg);
		}
		if(page == rulePopupPg){//This is a override rule enter pin pop-up
			rulePopupPg.clickCancel();//always click cancel to get error message
			page = browser.waitExists(vmOrdCartPg);
		}
		if (page == vmOrdCartPg) {
			// get the rule validation message
			statusMsg = vmOrdCartPg.getErrorMessage();
			if (statusMsg == null || "".equalsIgnoreCase(statusMsg)) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to ticket (Tour# = "
						+ ticket.tour + ") in sales channel - Venue Manager.");
			}
			if (!statusMsg.contains(ruleCondID)
					|| !statusMsg.contains(shortNameOfTour)) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to ticket (Tour# = "
						+ ticket.tour + ") in sales channel - Venue Manager.");
			}
			logger
					.info("----The "
							+ ruleName
							+ " Rule (Rule Cond.ID = "
							+ ruleCondID
							+ ") take effect to sales channel - Venue Manager successfully.");
		} else {
			if (!isNegative) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to ticket (Tour# = "
						+ ticket.tour + ") in sales channel - Venue Manager.");
			}
			logger.info("----The " + ruleName + " Rule (Rule Cond.ID = "
					+ ruleCondID + ") applies negatively successfully.");
			statusMsg = vmOrdSumPg.getAllOrdNums();
			vmOrdSumPg.clickFinishOrder();
			vmHmPg.waitLoading();
		}
		return statusMsg;
		// if (page == printPg) { //print ticket popup
		// printPg.waitForDisappear();
		// vmOrdSumPg.waitExists();
		// }
		//		
		// if(isPrint){
		// vmOrdSumPg.clickPrintTickets();
		// //vmOrdSumPg.waitExists();
		// page=browser.waitExists(printPg,alert,vmOrdSumPg);
		// if(page==printPg){
		// //printPg.waitForDisappear();
		// printPg.close();
		// vmOrdSumPg.waitExists();
		// }
		// }
		//
		// vmOrdSumPg.clickFinishOrder();
		//
		// page=browser.waitExists(alert,printPg,vmHmPg); //the print page may
		// not be closed yet
		// if (page == printPg) { //print ticket popup
		// printPg.close();
		// vmHmPg.waitExists();
		// }
		// alert.resetDefault();
		// return statusMsg;
	}

	public void selectOneReport(String group, String reportName) {
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsRequestReportPage requestRptPg = OrmsRequestReportPage
				.getInstance();
		OrmsReportCriteriaPage rptPg = OrmsReportCriteriaPage.getInstance();

		logger.info("Select Report " + reportName);

		vmHmPg.clickReports();
		requestRptPg.waitLoading();
		requestRptPg.selectReportGroup(group);
		requestRptPg.waitLoading();
		requestRptPg.selectReport(reportName);
		requestRptPg.clickOK();
		rptPg.waitLoading();
	}

	/**
	 * This method used to run given report and download it
	 * 
	 * @param rd
	 * @param path
	 * @return-file store path
	 */
	public String runSpecialReport(ReportData rd, String path) {
		OrmsReportCriteriaPage rptCriteriaPg = OrmsReportCriteriaPage
				.getInstance();
		OrmsOnlineReportProcessingPage onlineRptPg = OrmsOnlineReportProcessingPage
				.getInstance();

		logger.info("Run Report " + rd.reportName);

		rptCriteriaPg.setReportCriteria(rd);
		rptCriteriaPg.clickOk();
		onlineRptPg.waitLoading();
		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new RuntimeException("Failed to create directory " + path);
			}
		}
		String fileName = file.getAbsolutePath() + "\\"
				+ rd.reportName.replaceAll(" ", "") + "_VM.pdf";
		downloadReport(fileName);
		//onlineRptPg.close();
		rptCriteriaPg.waitLoading();

		return fileName;
	}

	/**
	 * download a report
	 * 
	 * @param path
	 *            file path and name
	 */
	public void downloadReport(String path) {
		FileDownloadDialogPage filePage = FileDownloadDialogPage.getInstance();
		File pdfFile = new File(path);

		if (pdfFile.exists()) {
			boolean delFile = pdfFile.delete();
			if (!delFile) {
				throw new ErrorOnDataException("Fail to Delete File.");
			}
		}
		filePage.downloadSaveFile(path);

		int i = 1;
		while (!pdfFile.exists()) {
			Browser.sleep(1);
			i++;
			if (i > 120)
				break;
		}
		if (pdfFile.exists()) {
			logger.info("Download Report cost " + i + " seconds!");
		} else {
			logger.error("Can not download Report in " + i + " seconds!");
			throw new ItemNotFoundException("Can not download PDF in " + i
					+ " seconds!");
		}

		if (filePage.exists()) {
			filePage.clickClose();
		}
	}

	/**
	 * Verify One Report by compare the report with given template file
	 * 
	 * @param templatesPath
	 * @param comparedPath
	 * @param errorResultPath
	 */
	public boolean verifyPdfReport(String templatesPath, String comparedPath) {
		logger.info("Verify PDF format Report.");
		boolean isCorrect = true;
		try {
			if (!PDFParser.verifyRptRunDate(comparedPath)) {
				isCorrect = false;
			}
			File file = new File(comparedPath);
			List<String> list = PDFParser.comparePDFFile(templatesPath + "/"
					+ file.getName(), comparedPath);
			for (Object l : list) {
				if (l instanceof String) {
					isCorrect = false;
				} else if (l instanceof List<?>) {
					if (((List<?>) l).size() > 0) {
						isCorrect = false;
					}
				} else {
					throw new RuntimeException("Unknown Object Type.");
				}
			}
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}

	/**
	 * In Ticket order Search/List page,verify the print ticket function The
	 * work flow starts from Venue manager home page and ends at ticket order
	 * Search/List page.
	 * 
	 * @param ordNum
	 *            - order number
	 * @param isPrintTicket
	 *            - if the ticket should be print.
	 */
	public void verifyPrintTicketFunction(String ordNum, boolean isPrintTicket) {
		OrmsTicketOrderSearchPage vmTicketSchPg = OrmsTicketOrderSearchPage
				.getInstance();
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();

		List<String> beforePrintNumList = new ArrayList<String>();
		List<String> afterPrintNumList = new ArrayList<String>();
		String beforePrintNums = "";
		String afterPrintNums = "";
		int size = 0;

		logger.info("Verify Print ticket function.");

		vmHmPg.searchTicketOrder();
		vmTicketSchPg.waitLoading();

		// get print number before print
		vmTicketSchPg.searchTicketByOrderNumber(ordNum);
		vmTicketSchPg.waitLoading();
		beforePrintNums = vmTicketSchPg.getPrintNumMessage();
		size = beforePrintNums.split(" ").length;
		for (int i = 0; i < size; i++) {
			beforePrintNumList.add(beforePrintNums.split(" ")[i]);
		}

		if (isPrintTicket) {
			// verify the Print button is available
			if (vmTicketSchPg.checkPrintAvailable()) {
				vmTicketSchPg.clickPrint();
				// printPg.waitExists();
				// Wait alert page
				browser.waitExists(printPg, alert, vmTicketSchPg);
				// verify the print number before and after printing
				afterPrintNums = vmTicketSchPg.getPrintNumMessage();

				for (int j = 0; j < size; j++) {
					if (!beforePrintNumList.get(j).equals("0")) {
						throw new ErrorOnDataException(
								"The Print number is not correct before printing.");
					}
					afterPrintNumList.add(afterPrintNums.split(" ")[j]);
					// verify print ticket number
					if (Integer.parseInt(afterPrintNumList.get(j)) != Integer
							.parseInt(beforePrintNumList.get(j)) + 1) {
						throw new ErrorOnDataException(
								"Print number is not correct after printing.");
					}
				}
			} else {
				for (int i = 0; i < size; i++) {
					if (!beforePrintNumList.get(i).equals("1")) {
						throw new ErrorOnDataException(
								"Print number is not correct when having print feature.");
					}
				}
			} 
		} else {
			if (vmTicketSchPg.checkPrintAvailable()) {
				throw new ErrorOnDataException(
						"The Print button should not be available.");
			}
		}
	}

	/**
	 * verify waring message in order cart page
	 * 
	 * @param waringMessage
	 */
	public String verifyWaringMessageInOrderCartPg(String waringMessage) {
		OrmsOrderCartPage cmOrderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("verify waring message in process order cart page ");

		cmOrderCartPg.clickProcessOrder();
		cmOrderCartPg.waitLoading();
		String waringMessage_1 = cmOrderCartPg.getWaringMessage();

		if (!waringMessage_1.matches(waringMessage)) {
			throw new ErrorOnDataException("The warning message doesn't match.");
		}
		this.cancelCart();

		return waringMessage_1;
	}

	public void verifyManageTicket(boolean beforeAction, boolean afterAction,
			String cancelTicket) {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		VnuMgrManageTicketsPage vmManageTicketsPg = VnuMgrManageTicketsPage
				.getInstance();

		logger
				.info("verify the information after clicking the 'Manage Ticket' in ticket order detail page");

		vmTicketDetailPg.clickManageTickets();
		vmManageTicketsPg.waitLoading();

		vmManageTicketsPg.verifyManageTickets(beforeAction, afterAction,
				cancelTicket);
		vmManageTicketsPg.clickCancel();
		vmTicketDetailPg.waitLoading();
	}

	/**
	 * TODO purchase ticket from ticketAvailability page to ticket selection
	 * page
	 * 
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public TicketInfo purchaseTicket(TicketInfo ticket) {
		OrmsTicketAvailabilityPage availabilityPage = OrmsTicketAvailabilityPage
				.getInstance();
		TicketSearch ts = TicketSearch.getInstance();
		OrmsTourAlertsPage alertPage = OrmsTourAlertsPage.getInstance();
		OrmsTicketSelectionPage ticketselectionpage = OrmsTicketSelectionPage
				.getInstance();
		
		ts.searchTicket(ticket);

		availabilityPage.selectTicket(ticket.timeSlot, ticket.quantity);
		ticket.startDate = availabilityPage.getSearchTourDate();
		ticket.inventory = availabilityPage.getSelectTimeTourInventory(ticket.timeSlot);

		availabilityPage.clickPurchaseTickets();
		ajax.waitLoading();
		Object page = browser.waitExists(alertPage, ticketselectionpage);
		if (page == alertPage) {
			alertPage.clickOkButton();
			browser.waitExists(ticketselectionpage);
		}
		return ticket;
	}

	/**
	 * 
	 * TODO verify ticket availability link in ticket selection page,the flow
	 * end in ticket availability page
	 * 
	 * @Return void
	 * @Throws
	 */
	public void verifyTicketAvailabilityLink(TicketInfo ticket) {
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage
				.getInstance();
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		String inventory = null;

		logger.info("Verify ticket availability link........");
		selectionpage.clickTicketAvailabilityLink();
		Object page = browser.waitExists(availpage, selectionpage);
		if (page != availpage) {
			throw new PageNotFoundException("The link is invalid~!");
		}

		inventory = availpage.getSelectTimeTourInventory(ticket.timeSlot);
		if (!inventory.equals(ticket.inventory)) {
			throw new ActionFailedException(
					"the held inventory is not released~!");
		}
		logger.info("Verify successfully~!");

	}

	/**
	 * 
	 * TODO verify if the inventory is be released after cancel cart. the work
	 * flow start from page which has cancel cart to ticket availability page.
	 * 
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void verifyCacelCartLink(TicketInfo ticket) {
		OrmsTicketAvailabilityPage availPage = OrmsTicketAvailabilityPage
				.getInstance();
		logger.info("Verify cancel cart...");
		this.cancelCart();
		this.gotoTicketAvailabilityPage();
		availPage.searchAvailibility(ticket);
		String inventory = availPage
				.getSelectTimeTourInventory(ticket.timeSlot);
		if (!inventory.equals(ticket.inventory)) {
			throw new ActionFailedException(
					"The held ticket inventory is not released~!");
		}
		logger.info("verify successfully");

	}

	/**
	 * 
	 * TODO verify cancel cart,From any purchase process which has a cancel cart
	 * top link, End:ticket availbility page.
	 * 
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void verifyCancelCart(TicketInfo ticket) {
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		String inventory = null;
		logger.info("Verify cancel cart....");
		this.cancelCart();
		this.gotoTicketAvailabilityPage();
		inventory = availpage.getSelectTimeTourInventory(ticket.timeSlot);
		if (!inventory.equals(ticket.inventory)) {
			throw new ActionFailedException(
					"The ticket inventory is not released after cancel cart~!");
		}
		logger.info("verify successfully");
	}

	/**
	 * Verify Ticket RA Fee Schedule is used
	 * 
	 * @param feeType
	 *            - fee type
	 * @param isActive
	 *            - schedule status
	 * @param scheduleId
	 *            - schedule Id
	 */
	public void verifyTicketRAFeeScheduleIsUsed(String feeType,
			String scheduleId, boolean isActive) {
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage
				.getInstance();

		detailsPage.clickFees();
		feeDetailPg.waitLoading();

		boolean flag = feeDetailPg.checkFeeScheduleIDExist(feeType, scheduleId);
		if (isActive) {
			if (!flag) {
				throw new ItemNotFoundException("Can not find " + feeType
						+ " that schedule id is " + scheduleId);
			}
		} else {
			if (flag) {
				throw new ErrorOnPageException("Fee Schedule ID " + scheduleId
						+ " should not displayed.");
			}
		}
		logger.info("Verify fee schedule which schedule is is " + scheduleId
				+ " successfully !");

		feeDetailPg.clickOK();
		detailsPage.waitLoading();
	}

	public void downloadFile(String filePath) {
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage
				.getInstance();
		File file = new File(filePath);

		if (file.exists()) {
			boolean deleted = file.delete();
			if (!deleted) {
				throw new RuntimeException("Failed to delete existing file - "
						+ filePath);
			}
		}

		downloadPage.downloadSaveFile(filePath);
		int i = 0;
		while (!file.exists()) {
			Browser.sleep(1);
			i++;
			if (i > 120) {
				break;
			}
		}

		if (file.exists()) {
			logger.info("Successfully download file in " + i + " seconds.");
		} else {
			throw new ItemNotFoundException("Can't download file - " + filePath
					+ " in " + i + " seconds.");
		}

		if (downloadPage.exists()) {
			downloadPage.clickClose();
		}
	}

	/**
	 * Get the PDF file content by given full file name
	 * @param fileName
	 * @return
	 */
	public String getPdfFileContent(String fileName) {
		File file = new File(fileName);
		
		if(!file.exists()) {
			throw new ItemNotFoundException("Can't find ticket PDF file - " + fileName);
		}
		String content = PDFParser.retrievePDFContent(file);
		return content;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public String printTicketOrderAtHome(String path) {
		OrmsTicketOrderDetailsPage orderDetailPage = OrmsTicketOrderDetailsPage
				.getInstance();
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage
				.getInstance();

		String orderNum = orderDetailPage.getOrderNum();
		logger.info("Print @ Home for ticket order - " + orderNum);
		orderDetailPage.clickRequestPrintAtHome();
		downloadPage.setDismissible(false);
		downloadPage.setBeforePageLoading(false);
		downloadPage.waitLoading();
		File file = new File(path);
		if (!file.exists()) {
			boolean exists = file.mkdir();
			if (!exists) {
				throw new RuntimeException("Failed to create directory - "
						+ path);
			}
		}

		// download file
		String fullFileName = file.getAbsolutePath() + "\\" + "TicketOrder_"
				+ orderNum + ".pdf";
		this.downloadFile(fullFileName);
		orderDetailPage.waitLoading();

		return fullFileName;
	}

	/**
	 * request confirmation letter from ticket order detail page
	 * @param byMethod - can be 'Email',  'Add to Batch' and 'File/Print'
	 * @param path - if Delivery Method is 'File/Print', path must have value to store PDF file
	 * @return
	 */
	public String requestConfirmationLetter(String byMethod, String path) {
		OrmsTicketOrderDetailsPage orderDetailPage = OrmsTicketOrderDetailsPage.getInstance();
		VnuMgrRequestConfirmationLetterPage requestPage = VnuMgrRequestConfirmationLetterPage.getInstance();
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage.getInstance();
		
		String orderNum = orderDetailPage.getOrderNum();
		logger.info("Request Ticket Order - " + orderNum + " confirmation letter by " + byMethod + " distribution method.");
		orderDetailPage.clickRequestConfLetter();
		ajax.waitLoading();
		requestPage.waitLoading();
		requestPage.selectConfLetterDistributionMethod(byMethod);
		requestPage.clickOK();
		if(null != path && byMethod.equals("File/Print")) {//for Delivery Method: File/Print
			File file = new File(path);
			if (!file.exists()) {
				boolean exists = file.mkdir();
				if (!exists) {
					throw new RuntimeException("Failed to create directory - "
							+ path);
				}
			}
	
			//download confirmation letter file
			String fullFileName = file.getAbsolutePath() + "\\" + "ConfirmLetter_" + orderNum + ".pdf";
			downloadPage.setDismissible(false);
			downloadPage.setBeforePageLoading(false);
			downloadPage.waitLoading();
			this.downloadFile(fullFileName);
			
			return fullFileName;
		}
		orderDetailPage.waitLoading();
		
		return "";
	}
	
	/**
	 * request confirmation letter in ticket order detail page
	 * @param byMethod - only for 'Email' and 'Add to Batch'
	 */
	public void requestConfirmationLetter(String byMethod) {
		this.requestConfirmationLetter(byMethod, null);
	}
	
	/**
	 * update 'Per Ticket' tour participant attributes values in ticket order detail page
	 * @param map
	 */
	public void updateTourParticipantAttributesForPerTicket(Map<String, List<Map<String, String>>> map) {
		OrmsTicketOrderDetailsPage orderDetailPage = OrmsTicketOrderDetailsPage.getInstance();
		
		logger.info("Update 'Per Ticket' tour participant attributes values.");
		Set<Map.Entry<String, List<Map<String, String>>>> set = map.entrySet();
		for(Iterator<Map.Entry<String, List<Map<String, String>>>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, List<Map<String, String>>> entry = it.next();
			orderDetailPage.setTourParticipantAttributesForPerTicket(entry.getKey(), entry.getValue());
		}
		
		orderDetailPage.clickApply();
		orderDetailPage.waitLoading();
	}
	
	/**
	 * 
	 * @param map
	 */
	public void updateTourParticipantAttributesForPerInventory(String tourName, Map<String, String> map) {
		OrmsTicketOrderDetailsPage orderDetailPage = OrmsTicketOrderDetailsPage.getInstance();
		
		logger.info("Update 'Per Inventory' tour participant attributes values.");
		orderDetailPage.updateTourParticipantAttributesForPerInventory(tourName, map);
		orderDetailPage.clickApply();
		orderDetailPage.waitLoading();
	}
	
	/**
	 * Void ticket orders, do not care about payment
	 * @param ordNum
	 */
	public void voidTicketOrders(String... ordNum){
		Payment pay = new Payment();
		for(int i=0; i<ordNum.length; i++){
			gotoTicketOrderDetailsPage(ordNum[i]);
			voidTicketToCart("Other", "Auto test");
			processOrderCart(pay);
		}
	}
	
	/**
	 * Cancel ticket orders, do not care about payment
	 * @param ordNum
	 */
	public void cancelTicketOrders(String... ordNum){
		Payment pay = new Payment();
		for(int i=0; i<ordNum.length; i++){
			gotoTicketOrderDetailsPage(ordNum[i]);
			cancelTicketToCart(true, ordNum[i]);
			processOrderCart(pay);
		}
	}
	/**
	 * go to receipts details page.
	 * @param orderNum
	 */
	public void gotoReceiptsDetailsPageByOrderNum(String orderNum){
		VnuMgrReceiptsSearchPage vmReceSearPg = VnuMgrReceiptsSearchPage
				.getInstance();
		VnuMgrReceiptsDetailsPage receiptDetails = VnuMgrReceiptsDetailsPage.getInstance();
		this.searchTicketReceipt(orderNum);
		vmReceSearPg.clickReceiptNum(vmReceSearPg.getReceiptNum());
		ajax.waitLoading();
		receiptDetails.waitLoading();
	}
	/**
	 * This method is used to add no-combo ticket from order cart page
	 * @param ticket
	 */
	public void addTicketOrder(TicketInfo ticket){
		this.addTicketOrder(ticket, false);
	}
	/**
	 * This method is used to add no-combo or combo ticket from order cart page
	 * @param ticket
	 * @param isCombo
	 */
	public void addTicketOrder(TicketInfo ticket, boolean isCombo){
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage
				.getInstance();
		vmOrderCartPg.clickAddTicketOrder();
		vmTicketAvailSchPg.waitLoading();
		this.makeTicketOrderToCart(ticket, isCombo);
	}
    
	/**
	 * This method goes to charges label page under ticket order detail page
	 */
	public void gotoTicketPosChargesListPage() {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsPOSChargeListInOrderDetailPage chargeLabel = OrmsPOSChargeListInOrderDetailPage.getInstance();
		logger.info("Go to charges label page under ticket order detail page.");
		vmTicketDetailPg.clickCharges();
		chargeLabel.waitLoading();		
	}
    /**
     * This method charge a POS in ticket order detail page, it ends at order cart page
     * @param pos
     */
	public void chargePOSForTicketReservationToOrderCart(POSInfo pos) {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsOrderCartPage ormsOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsPOSAddItemPage ormsPosItemsPg = OrmsPOSAddItemPage.getInstance();

		logger.info("Charge a POS for ticket reservation.");
        
		vmTicketDetailPg.clickChargePOS();
		ormsPosItemsPg.waitLoading();
		logger.info("Select POS " + pos.product + " to cart .");
		ormsPosItemsPg.searchItem(pos.product);
		ajax.waitLoading();
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.selectItem(pos);
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.clickAddToCart();
		ajax.waitLoading();
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.clickGoToCart();
		ormsOrdCartPg.waitLoading();
	}
	
	/**
	 * Go to POS Product Setup page from home page.
	 */
	public void gotoPOSProductSetupPage(){
		VnuMgrHomePage homePage = VnuMgrHomePage.getInstance();
		OrmsPOSProductSetupPage posSetupPage = OrmsPOSProductSetupPage.getInstance();
		
		logger.info("Go to POS Product Setup page from home page.");
		homePage.clickPOS();
		posSetupPage.waitLoading();
	}

	/**
	 * Go to pos puchase page from Venue home page according to the 'Quick POS Sale' span
	 */
	public void gotoPOSAddItemPageFromHomePage(){
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsPOSAddItemPage vmPosItemsPg = OrmsPOSAddItemPage.getInstance();

		logger.info("Go to POS Add Item page from Home Page.");

		vmHmPg.clickPurchasePOS();
		vmPosItemsPg.waitLoading();
	}
	
	/**
	 * The method executes the work flow of making a new POS order to order
	 * cart. The work flow starts from Veneu Manager home page and ends at order
	 * cart.
	 *
	 * @param pos
	 *            - the POS information.
	 */
	public void makePOSOrderToCart(POSInfo pos) {
		OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Making a POS order to cart.");

		gotoPOSAddItemPageFromHomePage();
		selectPOSToCart(pos);

		vmOrdCartPg.waitLoading();
	}
	
	/**
	 * This method executes the work flow of going into POS details page. The
	 * work flow starts from Venue Manager home page and ends at POS details
	 * page.
	 *
	 * @param posID
	 */
	public void gotoPOSDetails(String posID) {
		VnuMgrTopMenuPage topMenuPage = VnuMgrTopMenuPage.getInstance();
		OrmsPOSSaleSearchPage posSchPg = OrmsPOSSaleSearchPage.getInstance();
		OrmsPOSDetailsPage posDetailPg = OrmsPOSDetailsPage.getInstance();

		logger.info("Going to POS#" + posID + " details page.");

		topMenuPage.selectSearchDropdownList("POS Sale");
		posSchPg.waitLoading();
		posSchPg.searchByPOSNum(posID);
		posSchPg.waitLoading();
		posSchPg.selectPOSOrder(posID);

		posDetailPg.waitLoading();
	}
	
	public void gotoVnuHomePage(){
		VnuMgrTopMenuPage topMenuPage = VnuMgrTopMenuPage.getInstance();
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		logger.info("Go to Venue manager home page");
		
		topMenuPage.clickHome();
		ajax.waitLoading();
		vmHmPg.waitLoading();
	}
	
	/**
	 * This method used to get report date error message
	 *
	 * @param rd
	 * @return error message
	 */
	public String getReportDateErrorMesg(String startDate, String endDate) {
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage
		.getInstance();
		ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();

		logger.info("Veriy Report Date Correct.");

		String alertMsg = "";
		
		Object alertPage = browser.waitExists(alertPg, rmCriteriaPg);
		if (alertPage == alertPg) {
			alertPg.dismiss();
		}
		
		if (null != startDate && startDate.length() > 0) {
			rmCriteriaPg.setStartDate(startDate);
		}
		if (null != endDate && endDate.length() > 0) {
			alertMsg = rmCriteriaPg.setEndDateWithInvalidDate(endDate);
			if(!StringUtil.isEmpty(alertMsg) && !alertMsg.contains("YYYYMMDD")){
				return alertMsg;
			}
		}
		//if endDate less than startDate, then after set endDate, AlertDialog will pop up, 
		//we will not click 'ok' on this condition. 
		alertPg.setDismissible(false);
		alertPage = browser.waitExists(alertPg,
				rmCriteriaPg);
		if(alertPage == alertPg)
		{
			alertMsg = alertPg.text();
			alertPg.dismiss();
		}else{
			rmCriteriaPg.clickOk();
			Object page = browser.waitExists(alertPg, rmSendRptPg, rmOnlineReport,
					rmCriteriaPg);
			if (page == alertPg) {
				alertMsg = alertPg.text();
				alertPg.dismiss();
			} else if (page == rmCriteriaPg) {
				alertMsg = rmCriteriaPg.getErrorMsg();
			}else{
				throw new ErrorOnPageException("Report Date Validation Not Work!");
			}
		}
	
		return alertMsg;
	}

	/**
	 * This method used to verify report date
	 *
	 * @param startDate
	 *            , endDate, errorMessage
	 */
	public void verifyReportDate(String startDate, String endDate,
			String errorMessage) {

		logger.info("Veriy Report Date Correct.");
		String msg = getReportDateErrorMesg(startDate, endDate);
		if (!msg.replaceAll("\\s*", "").matches(errorMessage.replaceAll("\\s*", ""))) {//added by pzhu
			throw new ErrorOnDataException("Error message '"+msg+"' doesn't correct.");
		}
	}
}
