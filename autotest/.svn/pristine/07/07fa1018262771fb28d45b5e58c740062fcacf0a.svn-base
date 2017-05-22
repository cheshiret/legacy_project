package com.activenetwork.qa.awo.keywords.orms;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData.DiscountSchdInfo;
import com.activenetwork.qa.awo.datacollection.legacy.FinSession;
import com.activenetwork.qa.awo.datacollection.legacy.FinSession.OpeningFloat;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.ReservationFeeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoiceTransmissionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTInvoicingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTRemittanceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InvoiceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EFTRemittanceTransInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.NSSSlipInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipReservationInfo;
import com.activenetwork.qa.awo.keywords.Awo;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeCalculationFunctions;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.orms.search.CustomerSearch;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrContractPolicyPage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrTicketSearchPage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsAddCampingUnitsWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsAdjustFeeToPastPaidWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsAlertPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsCreditCardInfoValidationWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsDiscountPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeeDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsGeneralSignedInPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsLoginPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsNoteAlertDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsNoteAlertListCommonPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOtherVouchersPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOverrideRulePinWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsParkDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsRequestConfirmationLetterPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationCustAlertPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationHistoryPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsRuleValidationPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSigninPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSystemErrorWidget;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsCancelReservationPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsInvoiceSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsInvoiceSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationAlertPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationBillingPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationFeesPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationFeesPage.FeeRecord;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsSiteDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsSiteListPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsSiteSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsVoidReservationPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerLoyaltyProgramRedeemPointsWidget;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerLoyaltyProgramReplaceCardWidget;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerLoyaltyProgramSelectWidget;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerPassAddRemovePoints;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerPassPointsListPage;
import com.activenetwork.qa.awo.pages.orms.common.customer.OrmsCustomerSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.dialog.ConfirmationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.common.event.OrmsEventDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsDepositDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsDepositPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsFinSessionDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsFinSessionSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsPaymentDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsPaymentSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsTransmittalAddDepositsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsTransmittalMainPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsTransmittalSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsTransmittalsPage;
import com.activenetwork.qa.awo.pages.orms.common.financial.OrmsVoidPaymentPage;
import com.activenetwork.qa.awo.pages.orms.common.giftcard.OrmsActivateGiftCardWidget;
import com.activenetwork.qa.awo.pages.orms.common.giftcard.OrmsAddGiftCardPge;
import com.activenetwork.qa.awo.pages.orms.common.giftcard.OrmsGiftCardDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.giftcard.OrmsVoidGiftCardSalePage;
import com.activenetwork.qa.awo.pages.orms.common.license.OrmsRequestHFConfirmLetterPage;
import com.activenetwork.qa.awo.pages.orms.common.lottery.OrmsLotteryApplicationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.lottery.OrmsLotteryApplicationSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.lottery.OrmsVoidTicketLotteryOrderPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsAddAndSearchListEntryPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsEnterBoatLengthWidget;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsListEntryContactStatusWidget;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsListEntryReservationDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsMarinaDetailsPgae;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsMarinaResultsPgae;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipAlertsPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipAutoSelectDiscountPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipListPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipReservationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipReservationNoteAlertDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipReservationNoteAlertListPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipReservationSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsSlipSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsUndockSlipReservationWidget;
import com.activenetwork.qa.awo.pages.orms.common.marina.OrmsVoidListEntryPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsEnterPinNumPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsOverrideRulePinPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsPrintPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsProcessOrderCartPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsProcessReportPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsSelectDiscountPopupPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSAddItemPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSChargeListInOrderDetailPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSSaleSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPosSelectRevenueLocationWidget;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPurchasePOSAdditionalInfoPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsVoidPOSSalePage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsAddPOSProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSImportInventoryFilePage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryFileLogPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationLogPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSProductSetupDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSProductSetupPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsAddTicketPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsCancelTicketOrderPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketAvailabilityPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketFeeDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketSearchPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketSelectionPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketTransferSelectionPage;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTourParticipantsWidget;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsVoidTicketOrderPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.operationManager.OpMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.operationManager.OpMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrManageTicketsPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrTopMenuPage;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotImplementedException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Email;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public abstract class Orms extends Awo {
	protected Ajax ajax=AwoAjax.getInstance();
	
	/**
	 * The method execute the process that open the url
	 * 
	 * @param url
	 */
	public void invokeURL(String url, boolean newBrowser) {
		invokeURL(url, ORMS, true, newBrowser);
	}
	
	public void invokeURL(String url) {
		invokeURL(url, ORMS, true, true);
	}

	public void login(LoginInfo login, String channel) {
		login(login,channel,true);
	}
	
	public String getSalesChannel() {
		return OrmsGeneralSignedInPage.getInstance().getManagerName();
	}
	
	public String getLoginUser(String saleschannel){
		String loginUser = "";
		if(saleschannel.equalsIgnoreCase("Field Manager"))
			loginUser = TestProperty.getProperty("orms.fm.user");
		else if(saleschannel.equalsIgnoreCase("Call Manager"))
			loginUser = TestProperty.getProperty("orms.cm.user");
		else if(saleschannel.equalsIgnoreCase("Operations Manager"))
			loginUser = TestProperty.getProperty("orms.om.user");
		else if(saleschannel.equalsIgnoreCase("Permit Manager"))
			loginUser = TestProperty.getProperty("orms.fm.user");
		else if(saleschannel.equalsIgnoreCase("Marina Manager"))
			loginUser = TestProperty.getProperty("orms.fm.user");
		else if(saleschannel.equalsIgnoreCase("Venue Manager"))
			loginUser = TestProperty.getProperty("orms.fm.user");
		else if(saleschannel.equalsIgnoreCase("License Manager"))
			//loginUser = TestProperty.getProperty("orms.fm.user");
			loginUser = TestProperty.getProperty("orms.migr.user");
		else
			throw new ItemNotFoundException(saleschannel+" is not defined.");
		
		return loginUser;
	}
	
	/**
	 * This method executes the work flow of logging into Orms
	 * The work flow will open a new browser to orms home page and ends at
	 * mrg home page.
	 */
	public void login(LoginInfo login, String channel, boolean newBrowser) {
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
		OrmsLoginPage omLoginPg = OrmsLoginPage.getInstance();

		OrmsPage page = signIn(login, channel,newBrowser);
		if (page == omSigninPg)
			throw new ItemNotFoundException("Failed to login to " + channel);
		else if (page == omLoginPg) {
			omLoginPg.logIn(login);
		}
		
	}

	public void transferTicketToCart(TicketInfo ticket) {
		transferTicketToCart(ticket, null, false);
	}

	public void transferTicketToCart(TicketInfo ticket, String reason) {
		transferTicketToCart(ticket, reason, false);
	}
	
	public void cancelFromTicketSelectionDuringTransferTicket(){
		OrmsTicketTransferSelectionPage vmTransferSelectionPg = OrmsTicketTransferSelectionPage
				.getInstance();
		OrmsTicketOrderDetailsPage ticketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		logger.info("Go to order detail page from ticket selection page during transfer.");
		vmTransferSelectionPg.clickCancel();
		ticketDetailPg.waitLoading();
	}
	
	public void transferTicketToTicketAvailiabilityPg(){
		OrmsTicketOrderDetailsPage ticketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketAvailabilityPage transferTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		logger.info("Go to transfer availibility ticket page from order detail page");
		ticketDetailPg.clickTransferTickets();
		transferTicketsAvailPg.waitLoading();
	}
	
	public void transferTicketFromAvailibilityPgToSelectionPg(TicketInfo ticket,
			boolean isComboTour){
		OrmsTicketTransferSelectionPage vmTransferSelectionPg = OrmsTicketTransferSelectionPage
				.getInstance();
		OrmsTicketAvailabilityPage vmTransferTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsAlertPage vmSiteAlertPg = OrmsAlertPage.getInstance();
		vmTransferTicketsAvailPg.transferOrChangeTicket(ticket, isComboTour);
		vmTransferTicketsAvailPg.clickTransferTicket();

		Object page = browser.waitExists(vmTransferSelectionPg, vmSiteAlertPg);
		if (page == vmSiteAlertPg) {
			vmSiteAlertPg.clickOK();
		}
		vmTransferSelectionPg.waitLoading();
	}

	/**
	 * transfer ticket to cartPage from OrmsOrderDetails
	 * 
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void transferTicketToCart(TicketInfo ticket, String reason,
			boolean isComboTour) {
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketTransferSelectionPage vmTransferSelectionPg = OrmsTicketTransferSelectionPage
				.getInstance();
		OrmsAlertPage vmSiteAlertPg = OrmsAlertPage.getInstance();
		OrmsTourParticipantsWidget ormsTourParticipantPg = OrmsTourParticipantsWidget
				.getInstance();

		logger.info("transfer ticket to Tour Name:"
				+ ticket.tour
				+ " start="
				+ (ticket.startDate.length() > 0 ? ticket.startDate : "unknown")
				+ " time="
				+ (ticket.timeSlot.length() > 0 ? ticket.timeSlot : "unknown")
				+ " number=" + ticket.ticketNum + " into order cart.");

		transferTicketToTicketAvailiabilityPg();
		transferTicketFromAvailibilityPgToSelectionPg(ticket, isComboTour);

		vmTransferSelectionPg.doTicketSelection(null, ticket.types,
				ticket.typeNums);

		if (reason != null) {
			vmTransferSelectionPg.selectTransferReason(reason);
		}

		vmTransferSelectionPg.clickOK();

		Object page = browser.waitExists(vmSiteAlertPg, ormsTourParticipantPg,vmOrderCartPg);
		if (page == vmSiteAlertPg) {
			vmSiteAlertPg.clickOK();
			page = browser.waitExists(ormsTourParticipantPg, vmOrderCartPg);
		}

		if (ormsTourParticipantPg == page) {
			if (ticket.removedTourParticipantAttributesForPerTicket.size() > 0) {
				ormsTourParticipantPg
						.removeTPAInfoOfPerTicket(ticket.removedTourParticipantAttributesForPerTicket);
			}

			if (ticket.updatedTourParticipantAttributesForPerTicket.size() > 0) {
				ormsTourParticipantPg.setTourParticipantAttributesForPerTicket(
						ticket.transferFromTourName,
						ticket.updatedTourParticipantAttributesForPerTicket);
			}

			if (ticket.updateTourParticipantAttributesForPerInventory.size() > 0) {
				ormsTourParticipantPg
						.setTourParticipantAttributesForPerInventory(
								ticket.transferFromTourName,
								ticket.updateTourParticipantAttributesForPerInventory);
			}

			if (ticket.tourParticipantAttributesForPerInventory.size() > 0) {
				ormsTourParticipantPg
						.setTourParticipantAttributesForPerInventory(
								ticket.tour,
								ticket.tourParticipantAttributesForPerInventory);
			}

			if (ticket.tourParticipantAttributesForPerTicket.size() > 0) {
				ormsTourParticipantPg.setTourParticipantAttributesForPerTicket(
						ticket.tour,
						ticket.tourParticipantAttributesForPerTicket);
			}

			ormsTourParticipantPg.clickOK();
			vmOrderCartPg.waitLoading();
		}
	}

//	public void changePermitExitDate(String exitDate) {
//		OrmsPermitOrderDetailsPage orderDetailPage = OrmsPermitOrderDetailsPage
//				.getInstance();
//
//		orderDetailPage.setExitDate(exitDate);
//
//	}

	/**
	 * Replace permit order Group Leader, this work flow starts with Permit
	 * Order Details and ends with Order Details page too
	 * 
	 * @param cust
	 */
	public void replaceGroupLeader(Customer cust) {
//		OrmsPermitOrderDetailsPage orderDetailPage = OrmsPermitOrderDetailsPage
//				.getInstance();
//		OrmsSetGroupLeaderPage setGroupLeaderPage = OrmsSetGroupLeaderPage
//				.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage
				.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Replace Group Leader to: " + cust.email);
//		orderDetailPage.clickReplaceGroupLeader();
//		setGroupLeaderPage.waitLoading();
//		setGroupLeaderPage.setEmailAddress(cust.email);
//		setGroupLeaderPage.moveFocusOutOfDateComponent();
		confirmDialog.setDismissible(false);
		confirmDialog.setBeforePageLoading(false);
//		Object page = browser.waitExists(confirmDialog, setGroupLeaderPage);
//		if (page == confirmDialog) {
//			confirmDialog.clickOK();
//			custSearchPage.waitLoading();
//			custSearchPage.searchCust(cust);
//			custSearchPage.selectCust(cust.lName);
//		}
//
//		if (page == setGroupLeaderPage) {
//			setGroupLeaderPage.setLeaderInfo(cust);
//			setGroupLeaderPage.clickApply();
//			setGroupLeaderPage.clickOK();
//		}
//		orderDetailPage.waitLoading();
	}

	/**
	 * Replace Group Leader from Permit Order Details page,the work flow end in
	 * Permit Order Details Page
	 * 
	 * @param cust
	 * @param sameAsCustomer
	 * @throws InterruptedException
	 * @Return void
	 * @Throws
	 */
	public void replaceGroupLeader(Customer cust, boolean sameAsCustomer,
			String schema, boolean chooseExistCust) {
//		OrmsPermitOrderDetailsPage orderdetailPage = OrmsPermitOrderDetailsPage
//				.getInstance();
		OrmsCustomerDetailsPage setleaderPage = OrmsCustomerDetailsPage
				.getInstance();
		ConfirmDialogPage confirmdailog = ConfirmDialogPage.getInstance();
		OrmsCustomerSearchPage searchpage = OrmsCustomerSearchPage
				.getInstance();
		Object page = null;

		// the warnning messge that should show on the confirm dailog.
		// String message =
		// "There are one or more existing customers which have the same Home "
		// +
		// "Phone number or Email address. Would you like to view these existing customers?";

		logger.info("Change permit group leader...");

//		if (sameAsCustomer) {
//			orderdetailPage.clickReplaceGroupLeader();
//		} else {
//			orderdetailPage.unselectSameAsCustomer();
//		}

		browser.waitExists(setleaderPage);

		int count = DataBaseFunctions.countCustomerPhoneNumber(schema,
				cust.hPhone);

		setleaderPage.setPhone(cust.hPhone.trim());

		if (count > 0) {
			confirmdailog.setDismissible(false);
			confirmdailog.waitLoading();
			if (chooseExistCust) {
				confirmdailog.clickOK();
			} else {
				confirmdailog.clickCancel();
			}
			confirmdailog.setDismissible(true);
		}

		page = browser.waitExists(searchpage, setleaderPage);

		// if (count != null && count.trim().length() > 0) {
		// // remove focus from Home Phone Text Input
		// setleaderPage.setEmailAddress("");
		//
		// BaseBrowser.sleep(5);
		//
		// if (confirmdailog.exists()) {
		// if (!message.equalsIgnoreCase(confirmdailog.text().trim())) {
		// throw new ErrorOnPageException(
		// "The error message is wrong in confirm dialog.");
		// }
		// confirmdailog.setDismissMethod(chooseExistCust);
		// page = browser.waitExists(confirmdailog, searchpage,
		// setleaderPage);
		//
		// if (chooseExistCust && page != searchpage) {
		// throw new ActionFailedException(
		// "Customer Search page should be displayed.");
		// }
		//
		// if (!chooseExistCust && page != setleaderPage) {
		// throw new ActionFailedException(
		// "Set Goup Leader page should be displayed !");
		// }
		// } else {
		// throw new ActionFailedException("The dailog is not displayed !");
		// }
		// }

		if (page == setleaderPage) {
			setleaderPage.setCustInfo(cust);
		} else {
			searchpage.setEmail(cust.email);
			searchpage.setPhone(cust.hPhone);
			searchpage.clickFindExistingCust();
			searchpage.waitLoading();
			searchpage.selectCust(cust.lName);
		}
//		browser.waitExists(orderdetailPage);
	}

	/**
	 * Goto Permit cart page from Order Details page
	 * 
	 * @param permit
	 * @param isWalkIn
	 */
//	public void gotoPermitCartPageFromOrderDetailsPage(PermitInfo permit,
//			boolean isWalkIn) {
//		this.gotoPermitCartPageFromOrderDetailsPage(permit, null, isWalkIn,
//				false);
//	}

	/**
	 * setup permit data in permit order detail page
	 * 
	 * @param permit
	 * @param pay
	 * @param isWalkIn
	 * @param isLottery
	 */
//	public void setupPermitDataInOrderDetailPage(PermitInfo permit,
//			Payment pay, boolean isWalkIn, boolean isLottery) {
////		OrmsPermitOrderDetailsPage pmOrdDetailsPg = OrmsPermitOrderDetailsPage
//				.getInstance();
//
//		logger.info("Setup permit data in Permit Order Detail page.");
//		pmOrdDetailsPg.removeAllGroupMember();// clean up
//		if (isLottery) {
//			pmOrdDetailsPg.setupPermitData(permit, pay, isWalkIn);
//		} else {
//			pmOrdDetailsPg.setupPermitData(permit, isWalkIn);
//		}
//	}

	/**
	 * 
	 * @param permit
	 * @param isWalkIn
	 */
//	public void setupPermitDataInOrderDetailPage(PermitInfo permit,
//			boolean isWalkIn) {
//		setupPermitDataInOrderDetailPage(permit, null, isWalkIn, false);
//	}

	/**
	 * Goto Permit cart page from Order Details page
	 * 
	 * @param permit
	 * @param isWalkIn
	 * @Return void
	 * @Throws
	 */
//	public void gotoPermitCartPageFromOrderDetailsPage(PermitInfo permit,
//			Payment pay, boolean isWalkIn, boolean isLottery) {
//		OrmsPermitOrderDetailsPage pmOrdDetailsPg = OrmsPermitOrderDetailsPage
//				.getInstance();
//		OrmsOrderCartPage pmOrdCartPg = OrmsOrderCartPage.getInstance();
//		OrmsEnterPinNumPopupPage rulePopupPg = OrmsEnterPinNumPopupPage
//				.getInstance();
//
//		logger.info("goto Order cart page....");
//
//		setupPermitDataInOrderDetailPage(permit, pay, isWalkIn, isLottery);
//
//		pmOrdDetailsPg.clickOK();
//		ajax.waitLoading();
//		Object page = browser.waitExists(rulePopupPg, pmOrdDetailsPg, pmOrdCartPg);
//		if (page == rulePopupPg) {
//			rulePopupPg.enterPIN(permit.pin);
//			rulePopupPg.clickOK();
//			browser.waitExists(pmOrdDetailsPg, pmOrdCartPg);
//		}
//	}
	
	/**
	 * 
	 * @param ordNum
	 * @param childSlip
	 * @param regularCheckin
	 */
//	protected void checkinSlipResFromQuickListToCart(String ordNum, String childSlip, boolean regularCheckin, boolean isMarinaSaleChnl) {
//		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
//		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
//		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
//		OrmsPage homePg;
//		OrmsSystemErrorWidget errorWidget = OrmsSystemErrorWidget.getInstance();
//		
//		logger.info("Check-in Slip Res(#=" + ordNum + ") to cart from quick list.");
//		if(isMarinaSaleChnl){
//			homePg = FldMgrMarinaHomePage.getInstance();
//			FldMgrMarinaHomePage.getInstance().selectQuickListOption("Check-ins");
//			ajax.waitLoading();
//			homePg.waitLoading();
//			FldMgrMarinaHomePage.getInstance().selectItemFromList(ordNum);
//		}else{
//			homePg = MrnMgrHomePage.getInstance();
//			MrnMgrHomePage.getInstance().selectQuickListOption("Check-ins");
//			ajax.waitLoading();
//			homePg.waitLoading();
//			MrnMgrHomePage.getInstance().selectItemFromList(ordNum);
//		}
//		
//		resDetailsPage.waitLoading();
//		boolean fastCheckinAvailable = resDetailsPage.isFastCheckInAvailable();
//		if(fastCheckinAvailable && !regularCheckin) {
//			resDetailsPage.selectFastCheckIn();
//		} else if(fastCheckinAvailable && regularCheckin) {
//			resDetailsPage.selectRegularCheckIn();
//		} else {
//			resDetailsPage.selectCheckIn();
//			ajax.waitLoading();
//		}
//		
//		Object page = browser.waitExists(errorWidget, resDetailsPage);
//		if(page == resDetailsPage){
//			resDetailsPage.clickOK();
//			ajax.waitLoading();
//			page = browser.waitExists(errorWidget, listPage, cartPage, homePg);
//			if(page == listPage) {
//				listPage.selectSlipRadioButtonByCode(childSlip);
//				ajax.waitLoading();
//				listPage.clickOK();
//				ajax.waitLoading();;
//				// if stay on list page, there is error message.
//				browser.waitExists(listPage, homePg, cartPage);
//			}
//		}
//	}

	/**
	 * early check out a slip reservation which the departure date MUST BE a future date to order cart page,
	 * and the Transaction Name in order cart is: Shorten Stay Leave Earlier
	 * @param isReturn
	 */
//	public void earlyCheckOutSlipToCart(boolean isReturn) {
//		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
//		.getInstance();
//		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
//		MrnMgrCheckOutSlipReservationWidget popUp = MrnMgrCheckOutSlipReservationWidget.getInstance();
//		
//		logger.info("Early Check-out slip to cart.");
//		resDetailsPage.clickCheckOut();
//		ajax.waitLoading();
//		Object page = browser.waitExists(popUp, cartPage);
//		if(page == popUp) {
//			popUp.selectType(isReturn);
//			popUp.clickOK();
//			ajax.waitLoading();
//		}
//		cartPage.waitLoading();//Transaction Name in order cart must be: Shorten Stay Leave Earlier
//	}
	
	/**
	 * Goto process cart page from Reservation Fees page
	 */
	public void gotoProcessCartFromOrderFeeDetails() {
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsFeeDetailsPage feeDetailsPg = OrmsFeeDetailsPage.getInstance();

		feeDetailsPg.clickCancel();
		fmOrdCartPg.waitLoading();
	}

	public String processOrderCart(Payment pay) {
		return processOrderCart(pay,getSalesChannel());
	}
	
	/**
	 * Process order cart. The work flow starts from order cart page and ends at
	 * permit manager/operation manager/call manager/license manager/field
	 * manager home page.
	 * 
	 * @param pay
	 *            - the payment information datacollection
	 * @return - the order numbers in the order cart.
	 */
	public String processOrderCart(Payment pay, String saleschannel) {
		OrmsOrderSummaryPage omOrdSumPg = OrmsOrderSummaryPage.getInstance();
		ConfirmDialogPage dialogPg = ConfirmDialogPage.getInstance();// added by
		// pzhu

		logger.info("Process order cart.");

		OrmsPage hmpg;
//		if(saleschannel.equalsIgnoreCase("Field Manager"))
//			hmpg=FldMgrHomePage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Call Manager"))
//			hmpg=CallMgrHomePage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Operations Manager"))
//			hmpg=OpMgrHomePage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Permit Manager"))
//			hmpg=PermitMgrHomePage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Marina Manager"))
//			hmpg=MrnMgrHomePage.getInstance();
		if(saleschannel.equalsIgnoreCase("Venue Manager"))
			hmpg=VnuMgrHomePage.getInstance();
		else if(saleschannel.equalsIgnoreCase("License Manager"))
			hmpg=LicenseMgrHomePage.getInstance();
		else
			throw new ItemNotFoundException(saleschannel+" is not defined.");
		
		this.processOrderToOrderSummary(pay);
		
		String ordID = omOrdSumPg.getAllOrdNums();
		logger.info("Processed order: " + ordID);

		omOrdSumPg.clickFinishButton();
		dialogPg.setDismissible(false);
//		FldMgrMarinaHomePage fmMarinaHomePage = FldMgrMarinaHomePage.getInstance();
//		Object page = browser.waitExists(dialogPg, hmpg, fmMarinaHomePage);// updated by pzhu
//		if (page == dialogPg) {
//			dialogPg.dismiss();
//			browser.waitExists(hmpg, fmMarinaHomePage);
//		}

		return ordID;
	}

	/**
	 * Process order cart. The work flow starts from order cart page and ends at
	 * order summary page if processing order successfully or at order cart page
	 * if processing order failed.
	 * 
	 * @param pay
	 *            - the payment information data collection
	 * @return
	 */
	public void processOrderToOrderSummary(Payment pay) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsOtherVouchersPage fmOtherVouchersPg = OrmsOtherVouchersPage
				.getInstance();
		OrmsOrderSummaryPage pmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsProcessOrderCartPopupPage pinPopupPg = OrmsProcessOrderCartPopupPage
				.getInstance();
		FileDownloadDialogPage downloadDialog = FileDownloadDialogPage
				.getInstance();
		OrmsProcessReportPopupPage processRptPopup = OrmsProcessReportPopupPage
				.getInstance();
		OrmsEnterPinNumPopupPage rulePopupPg = OrmsEnterPinNumPopupPage
				.getInstance();
		OrmsPrintPopupPage printPage = OrmsPrintPopupPage.getInstance();
		
		logger.info("Process order cart.");

		// Action for the "Auto-print Tickets" checkbox
		if (pay.ticketInfo.autoPrintTicketTurnOn) {
			if (!ordCartPg.checkAutoPrintTicketExist()) {
				throw new ObjectNotFoundException(
						"'Auto-Print Tickets' should exit.");
			} else if (!ordCartPg.checkAutoPrintTicketSelect()) {
				throw new ObjectNotFoundException(
						"'Auto-Print Tickets' should select.");
			}
			if (pay.ticketInfo.unSelectAutoPrintTicket) { // Unselect
				// "Auto-print Tickets"
				// checkbox
				ordCartPg.unSelectCheckboxForAutoPrintTicket();
			}
		} else {
			if (ordCartPg.checkAutoPrintTicketExist()) {
				throw new ObjectNotFoundException(
						"'Auto-Print Tickets' should not exit.");
			}
		}
		if (pay.useVoucherPayment) {
			if (pay.voucherID != null && pay.voucherID.length() > 0) {
				ordCartPg.selectVoucherAsPayment(pay.voucherID);
				ordCartPg.waitLoading();
			}
			if (pay.otherVoucherID != null && pay.otherVoucherID.length() > 0) {
				ordCartPg.clickSelectOtherVoucher();
				fmOtherVouchersPg.waitLoading();
				fmOtherVouchersPg.searchVouchersByVoucherID(pay.otherVoucherID);

				pinPopupPg.waitLoading();
				pinPopupPg.enterNote(pay.reason);
				pinPopupPg.enterPIN(pay.pin);
				pinPopupPg.clickOK();
				ordCartPg.waitLoading();
			}
		}
		ordCartPg.setupPayment(pay);

		if (pay != null && !pay.payType.equals("")
				&& !pay.payType.equalsIgnoreCase("None")) {
			if (null != pay.additionalPayType
					&& pay.additionalPayType.length() > 0) {
				ordCartPg.clickAdditionalPayment();
				ordCartPg.waitLoading();
				ordCartPg.setupAdditionalPayment(pay);
			}

			if (pay.issueToVoucher) {
				ordCartPg.selectRadioVoucher();
			} else {
				ordCartPg.selectRadioRefund();
			}
		}

		ordCartPg.clickProcessOrder();
//		ajax.waitLoading();
		processRptPopup.setBeforePageLoading(false);
		downloadDialog.setBeforePageLoading(false);
		Object page = browser.waitExists(processRptPopup, pinPopupPg, rulePopupPg,
				downloadDialog, printPage, pmOrdSumPg, ordCartPg);
		downloadDialog.setDismissible(false);
		// Issue refund
		if (page == pinPopupPg) {
			int issueToObjectCount = pinPopupPg.getIssueToObjectCount();
			if(issueToObjectCount>0) {
				for (int i = 0; i < issueToObjectCount; i++) {
					pinPopupPg.setupInformation(pay.pin, pay.issueCash,
							pay.issueGiftCard, pay.giftCardNum, i);
				}
			} else {
				pinPopupPg.setupInformation(pay.pin, pay.issueCash,
							pay.issueGiftCard, pay.giftCardNum);
			}
			pinPopupPg.clickOK();
			page = browser.waitExists(pinPopupPg, processRptPopup,
					downloadDialog, printPage, pmOrdSumPg, ordCartPg);
		}

		if (page == pmOrdSumPg) {
			page = browser.waitExists(processRptPopup, downloadDialog, printPage,
					pmOrdSumPg);
		}

		if (page == processRptPopup) {
			logger.info("Process Report popup displayed. Close it.");
			processRptPopup.close();
			page = browser.waitExists(downloadDialog, printPage, pmOrdSumPg);
		}

		if (page == downloadDialog) {
			logger.info("Download dialog displayed. Close it.");
			downloadDialog.clickCancel();
			page = browser.waitExists(processRptPopup, printPage, pmOrdSumPg);
		}

		if (page == processRptPopup) {
			logger.info("Process Report popup displayed. Close it.");
			processRptPopup.close();
			//pmOrdSumPg.waitLoading();
			page = browser.waitExists(printPage, pmOrdSumPg);
		}
		
		if(page == printPage) {
			logger.info("Print popup displayed. Close it.");
			printPage.close();
			pmOrdSumPg.waitLoading();
		}

		downloadDialog.setDismissible(true);
	}

//	public String finishPermitOrderFromOrderdetailsPage(PermitInfo permit,
//			Payment pay, boolean isWalkIn, boolean isLottery) {
//		String ordID;
//		logger.info("finish permit order from order details page to call/permit/operation manager home page");
//
//		gotoPermitCartPageFromOrderDetailsPage(permit, pay, isWalkIn, isLottery);
//		ordID = this.processOrderCart(pay,getSalesChannel());
//		return ordID;
//	}

	/**
	 * finish permit order from order details page to call/permit/operation
	 * manager home page.
	 * 
	 * @param permit
	 * @param isWalkIn
	 * @param pay
	 * @return
	 * @Return OrderSummaryInfo
	 * @Throws
	 */
	public String finishPermitOrderFromOrderdetailsPage(PermitInfo permit,
			boolean isWalkIn, Payment pay) {
		String ordID;

		logger.info("finish permit order from order details page to call/permit/operation manager home page");

//		gotoPermitCartPageFromOrderDetailsPage(permit, isWalkIn);
		ordID = this.processOrderCart(pay,getSalesChannel());
		return ordID;
	}

	/**
	 * Click the finish button on order summary page.
	 */
	public void finishOrder() {
		OrmsOrderSummaryPage omOrderSumPg = OrmsOrderSummaryPage.getInstance();
//		PermitMgrHomePage pmHomePg = PermitMgrHomePage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		OpMgrHomePage opHomePg = OpMgrHomePage.getInstance();
		CallMgrHomePage callHomePg = CallMgrHomePage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
//		FldMgrHomePage fmHomePg = FldMgrHomePage.getInstance();
		VnuMgrHomePage vmHomePg = VnuMgrHomePage.getInstance();
//		MrnMgrHomePage mmHomePg = MrnMgrHomePage.getInstance();

		String salesChannel=omOrderSumPg.getManagerName();
		omOrderSumPg.clickFinishButton();
		confirmDialogPg.setDismissible(false);
		confirmDialogPg.setBeforePageLoading(false);
		
		OrmsPage hmPg=null;
		if(salesChannel.equalsIgnoreCase("Operations Manager")) {
			hmPg=opHomePg;
		} else if(salesChannel.equalsIgnoreCase("Call Manager")) {
			hmPg=callHomePg;
//		} else if(salesChannel.equalsIgnoreCase("Permit Manager")) {
//			hmPg=pmHomePg;
		} else if(salesChannel.equalsIgnoreCase("License Manager")) {
			hmPg=lmHomePg;
//		} else if(salesChannel.equalsIgnoreCase("Field Manager")) {
//			hmPg=fmHomePg;
		} else if(salesChannel.equalsIgnoreCase("Venue Manager")) {
			hmPg=vmHomePg;
//		} else if(salesChannel.equalsIgnoreCase("Marina Manager")) {
//			hmPg=mmHomePg;
		} else {
			throw new ItemNotFoundException("Sales Channel \""+salesChannel+"\" is unknown.");
		}
		browser.waitExists(confirmDialogPg, hmPg);
		if (confirmDialogPg.exists()) {
			confirmDialogPg.clickOK();
			hmPg.waitLoading();
		}
	}

	/**
	 * verify error message in permit order search page
	 * 
	 * @param errorMessages
	 * @param type
	 * @Return void
	 * @Throws
	 */
//	public void verifyErrorMessageInOrderSearchPage(String[] errorMessages,
//			String type) {
//		OrmsPermitOrderSearchPage schOrdPg = null;
//		List<String> list = null;
//
//		if (type == null || type.trim().length() == 0) {
//			throw new ItemNotFoundException(
//					"Please specify the order type,such as ticket,permit...");
//		} else if (type.equalsIgnoreCase("Permit")) {
//			schOrdPg = OrmsPermitOrderSearchPage.getInstance();
//			list = schOrdPg.getErrorMessage();
//		} else {
//			throw new ItemNotFoundException("Unknown Type");
//		}
//
//		if (list == null || list.size() < 1) {
//			throw new ItemNotFoundException("No error message is found .");
//		}
//
//		for (String message : errorMessages) {
//			if (!list.contains(message)) {
//				throw new ErrorOnPageException("Can't found the error:"
//						+ message);
//			}
//		}
//
//		logger.info("Verify error mesage in order search page successfully.");
//
//	}

	/**
	 * goto site search page from Field/Call/Operation Manager
	 * HomePage,FldMgrTopMenuPage,OpMgrTopMenuPage
	 * 
	 * @param startPage
	 *            Field/Call/Operation Manager HomePage,
	 *            FldMgrTopMenuPage,OpMgrTopMenuPage
	 * @Return void
	 * @Throws
	 */
//	public void gotoSiteSearchPage(OrmsPage startPage) {
//		if (startPage instanceof FldMgrHomePage
//				|| startPage instanceof FldMgrTopMenuPage) {
//			FldMgrTopMenuPage topmenu = FldMgrTopMenuPage.getInstance();
//			topmenu.selectSearchDropdownList("Sites");
//			topmenu.clickSearch();
//		} else if (startPage instanceof CallMgrHomePage
//				|| startPage instanceof CallMgrTopMenuPage) {
//			if (startPage instanceof CallMgrHomePage) {
//				CallMgrHomePage callHome = (CallMgrHomePage) startPage;
//				callHome.clickCampingCall();
//			} else if (startPage instanceof CallMgrTopMenuPage) {
//				CallMgrTopMenuPage topmenu = CallMgrTopMenuPage.getInstance();
//				topmenu.clickSites();
//			} 
//		} else if (startPage instanceof OpMgrHomePage
//				|| startPage instanceof OpMgrTopMenuPage) {
//			OpMgrTopMenuPage topmenu = OpMgrTopMenuPage.getInstance();
//			topmenu.clickSites();
//		} else {
//			throw new PageNotFoundException("Unknown start page.");
//		}
//		browser.waitExists(OrmsSiteSearchPage.getInstance());
//	}

	/**
	 * 
	 * Search Site in site search page, the work flow ends in site list page or
	 * site details page(Feil Manager)
	 * 
	 * @param site
	 * @return
	 * @Return Object OrmsSiteListPage/OrmsParkDetailsPage/OrmsSiteDetailsPage
	 * @Throws
	 */
	public Object searchSite(SiteInfoData site) {
		OrmsAlertPage alertpage = OrmsAlertPage.getInstance();
		OrmsSiteListPage siteListpage = OrmsSiteListPage.getInstance();
		OrmsParkDetailsPage parkpage = OrmsParkDetailsPage.getInstance();
		OrmsSiteDetailsPage siteDetailPage = OrmsSiteDetailsPage.getInstance();
		OrmsSiteSearchPage searchpage = OrmsSiteSearchPage.getInstance();

		logger.info("Search site according to the conditions....");
		searchpage.searchSite(site);

		Object page = browser.waitExists(parkpage, alertpage, siteListpage,
				siteDetailPage);
		if (page == alertpage) {
			alertpage.clickOK();
			page = browser.waitExists(siteListpage, siteDetailPage);
		}
		return page;
	}

	/**
	 * Make reservation to Reservation details page from Field/Call/Operation
	 * Manager HomePage,FldMgrTopMenuPage,OpMgrTopMenuPage
	 * 
	 * @param res
	 * @param isWalkin
	 * @param startPage
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String makeResToResDetailPg(ReservationInfo res, boolean isWalkin,
			OrmsPage startPage) {

		String siteID = "";

		OrmsSiteDetailsPage siteDetailPg = OrmsSiteDetailsPage.getInstance();
		OrmsAlertPage parkAlertPg = OrmsAlertPage.getInstance();
		OrmsParkDetailsPage parkDtailsPg = OrmsParkDetailsPage.getInstance();
		OrmsSiteListPage siteListPg = OrmsSiteListPage.getInstance();
		OrmsAlertPage siteAlertPg = OrmsAlertPage.getInstance();
		OrmsCustomerSearchPage custSchPg = OrmsCustomerSearchPage.getInstance();
		OrmsOverrideRulePinPopupPage pinPopupPg = OrmsOverrideRulePinPopupPage
				.getInstance();
		OrmsAlertPage custAlertPg = OrmsAlertPage.getInstance();
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		ConfirmDialogPage confirmPg = ConfirmDialogPage.getInstance();

		logger.info("Make a " + (isWalkin ? "Walkin" : "Advanced")
				+ " reservation to cart.");
//		this.gotoSiteSearchPage(startPage);

		if (res.arriveDate != "")
			res.site.arrivalDate = res.arriveDate;
		if (res.nightNum != "")
			res.site.dayNightNum = res.nightNum;

		Object page = this.searchSite(res.site);

		// This "if" is only used in Call Manager
		if (page == parkDtailsPg) {
			if (!res.site.fixedDate) {
				throw new ItemNotFoundException(
						"There no site available for the given search critera.");
			} else {
				searchAnyAvailabeSiteInParkDetails(res.site);
			}
			page = browser.waitExists(parkAlertPg, siteListPg, siteDetailPg);
		}

		if (page == parkAlertPg) {
			parkAlertPg.clickOK();
			page = browser.waitExists(siteListPg, siteDetailPg);
		}

		if (page == siteListPg) {
			if (Integer.parseInt(res.siteQty) > 1) {
				siteListPg.selectQty(0, res.siteQty);
				siteID = siteListPg.selectSitesToOrder(res.site.siteNumber,
						res.siteQty);
			} else {
				siteID = siteListPg.goSiteDetail(res.site.siteNumber);
			}

		}

		page = browser.waitExists(siteDetailPg, custSchPg, resvDetailPg,
				confirmPg, siteAlertPg);

		if (siteDetailPg.exists()) {
			siteID = siteDetailPg.getSiteID();
			res.site.advanced = isWalkin ? false : true;
			if (res.site.advanced
					&& (startPage instanceof CallMgrHomePage || startPage instanceof CallMgrTopMenuPage)) {
				res.nightNum = res.site.dayNightNum;
				if (!siteDetailPg.checkSelectedAvailableInventory(res.nightNum)) {
					res.arriveDate = siteDetailPg
							.selectFirstAvailableInventory(res.nightNum);
					res.departDate = DateFunctions.getDateAfterGivenDay(
							res.arriveDate, Integer.parseInt(res.nightNum));
				}

				siteDetailPg.clickAddReservToOrder();
			} else {
				siteDetailPg.selectSite(res.nightNum);
			}

			// siteDetailPg.selectSite(res.arriveDate, res.nightNum);
			res.site.dayNightNum = res.nightNum;

			page = browser.waitExists(siteAlertPg, custSchPg, resvDetailPg,
					pinPopupPg, confirmPg, custAlertPg);
		}

		if (pinPopupPg == page) {
			pinPopupPg.enterPIN(res.pay.pin);
			pinPopupPg.clickOK();
			page = browser.waitExists(custSchPg, custAlertPg, resvDetailPg,
					siteAlertPg);
		}

		if (siteAlertPg == page) {
			siteAlertPg.clickOK();
			page = browser.waitExists(custSchPg, custAlertPg, resvDetailPg);
		}

		if (custSchPg == page) {
			custSchPg.searchCust(res.firstName, res.lastName, res.email);
			custSchPg.searchWaitExists();
			custSchPg.selectCust(res.lastName);
			page = browser.waitExists(custAlertPg, resvDetailPg);
		}

		if (custAlertPg == page) {
			custAlertPg.clickOK();
			resvDetailPg.waitLoading();
		}

		return siteID;
	}

	/**
	 * logic to search any available site in a loop in Park Details page. If a
	 * site is found, the end page will be either site list page or site details
	 * page
	 * 
	 * @param site
	 */
	private void searchAnyAvailabeSiteInParkDetails(SiteInfoData site) {
		OrmsSiteDetailsPage siteDetailPg = OrmsSiteDetailsPage.getInstance();
		OrmsSiteListPage siteListPg = OrmsSiteListPage.getInstance();
		OrmsParkDetailsPage parkDtailsPg = OrmsParkDetailsPage.getInstance();
		logger.info("Search an available site in park details page.");

		// logic to search any available site in a loop.
		HtmlMainPage[] pages = { siteListPg, siteDetailPg, parkDtailsPg };
		Object page = null;
		boolean found = false;
		int count = 0;
		while (!found) {
			count++;
			if (site.countReachMax(count)) {
				throw new ItemNotFoundException(
						"Failed to find an available site in " + count
								+ (site.advanced ? " months" : " days"));
			}
			site.setNextDates();
			parkDtailsPg.searchSites(site);
			page = browser.waitExists(pages);
			if (page != parkDtailsPg) {
				found = true;
			}
		}
	}

	public String makeResToCartFromResDeitailsPage(ReservationInfo res, boolean isWalkin, boolean isGroup) {
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsSiteListPage siteListPg = OrmsSiteListPage.getInstance();
		OrmsDiscountPage omDiscountPg = OrmsDiscountPage.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		OrmsAddCampingUnitsWidget campingUnitWidget = OrmsAddCampingUnitsWidget.getInstance();

		String siteID = "";
		int counter = 0;
		do {
			if (isGroup) {
				resvDetailPg.selectGroup();
				resvDetailPg.setNumOfOccupants(res.numofOccupant);
				resvDetailPg.setNumOfVehicles(res.numofVehicle);
			}
			if (isWalkin) {
				resvDetailPg.selectCheckIn();
				browser.waitExists(confirmDialog, resvDetailPg);
			} else {
				resvDetailPg.unselectCheckIn();
			}
			if (res.accessPass) {
				if ((!resvDetailPg.checkRemoveCustomerPassExist())
						&& resvDetailPg.checkAddCustomerPassExist()) {// &&&
					resvDetailPg.clickAddCustomerPass();
				} else {
					if (resvDetailPg.getRemoveCustomerTypeButton() < 2) {
						resvDetailPg.clickAddCustomerType();
					}
				}

				if (null != res.customer.type
						&& !"".equalsIgnoreCase(res.customer.type)) {
					resvDetailPg.clickAddCustomerType();
					resvDetailPg.selectCustomerType(res.customer.type, 1, 1);

				}

				if (null != res.customer.pass
						&& !"".equalsIgnoreCase(res.customer.pass)) {
					resvDetailPg.selectCustomerPass(res.customer.pass, 1);// Q
					resvDetailPg.waitLoading();
					resvDetailPg.setPassNum(res.customer.passNumber);
					resvDetailPg
							.setAccessPassHolderName(res.customer.passHolderName);
					resvDetailPg
							.setAccessPassExpireDate(res.customer.passExpiryDate);
				}
				if (resvDetailPg.getNumOfProofShownCheckBox() > 1) {
					resvDetailPg.selectProofShown(1);
				}
			}

			// retrieve the actual arrival/departure dates and nights
			res.arriveDate = resvDetailPg.getArriveDate();
			if (res.site.typeOfUse.equals("Day")) {
				res.departDate = resvDetailPg.getDaysDepartDate();
				res.nightNum = resvDetailPg.getDaysNightNum() + "";
			} else {
				res.departDate = resvDetailPg.getDepartDate();
				res.nightNum = resvDetailPg.getNightNum() + "";
			}

			resvDetailPg.addAnyUnit(res.site.validCampingUnitQty,
					res.site.campingUnitPlate, res.site.unitState);
			
			if(resvDetailPg.isOtherOccupantQtyExists()) {//Quentin[20131118], other occupant qty is mandatory in some scenario
				int minOccupt = resvDetailPg.getMinOccupantsNum(); //james[20131126] use min# of occupants
				String otherOccupantQty = resvDetailPg.getOtherOccupantQty();//Sara[12/4/2013] Other occupant maybe sets values at previous code: fmResvDetailPg.setNumOfOccupants(res.numofOccupant) in this keyword.
				if(StringUtil.isEmpty(otherOccupantQty)|| NumberUtil.isEqualToZero(Integer.parseInt(otherOccupantQty))){
					if(StringUtil.notEmpty(res.numofOccupant)){//Shane[20140425],use number of occupant-1 as other occupants,else use minimum number-1 as other occupants number
						resvDetailPg.setOtherOccupantQty(String.valueOf(Integer.parseInt(res.numofOccupant)-1));
					}else{
						resvDetailPg.setOtherOccupantQty(String.valueOf(minOccupt-1));
					}
				}
			}
			if(resvDetailPg.isAddCampingUnitsExists()) {
				resvDetailPg.clickAddCampingUnits();
				ajax.waitLoading();
				campingUnitWidget.waitLoading();
				if(res.customer.campingUnitList!=null&&res.customer.campingUnitList.size()>0){
					campingUnitWidget.addCampingUnit(res.customer.campingUnitList);
				}else{
					campingUnitWidget.addCampingUnit(res.site.numOfCampingUnits, res.site.validCampingUnit, res.site.validCampingUnitQty, res.site.campingUnitLength, res.site.campingUnitPlate);					
				}
				resvDetailPg.waitLoading();
			}
			resvDetailPg.clickOK();

			// comment out discount page
			ajax.waitLoading();
			Object page = browser.waitExists(confirmDialog, resvDetailPg,
					ordCartPg, siteListPg, omDiscountPg);
			if (page == siteListPg) {
				// work flows for checking in to NSS child site
				siteID = siteListPg.selectSiteToOrder(res.site.nssChild);
				ordCartPg.waitLoading();
			}

			counter++;
		} while (res.site.isNSSSite
				&& Integer.valueOf(res.siteQty.trim()) > counter);

		Object page = browser.waitExists(ordCartPg, resvDetailPg, siteListPg,
				omDiscountPg);
		if (page == resvDetailPg) {
			resvDetailPg.addAnyUnit(res.site.validCampingUnitQty);
			resvDetailPg.clickOK();

			ajax.waitLoading();
			page = browser.waitExists(omDiscountPg, ordCartPg);
		}

		if (page == omDiscountPg) {
			if (res.discountName.trim().length() > 0) {
				omDiscountPg.applyBaseDiscount(res.discountName);
			}
			omDiscountPg.clickOK();
			// ajax.waitLoading();
			browser.waitExists(ordCartPg);
		}
		
		return siteID;
	}
	
	/**
	 * 
	 * purchase site to order cart page from Field/Call/Operation Manager
	 * HomePage,FldMgrTopMenuPage,OpMgrTopMenuPage
	 * 
	 * @param homepage
	 *            instance of Field/Call/Operation Manager Home Page
	 * @param res
	 * @param isWalkin
	 * @param isGroup
	 * @Return void
	 * @Throws
	 */
	public String makeReservationToOrderCartPage(OrmsPage startPage,
			ReservationInfo res, boolean isWalkin, boolean isGroup) {

		String siteID = "";

		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsSiteListPage siteListPg = OrmsSiteListPage.getInstance();
		OrmsDiscountPage omDiscountPg = OrmsDiscountPage.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		OrmsAddCampingUnitsWidget campingUnitWidget = OrmsAddCampingUnitsWidget.getInstance();
		
		logger.info("purchase site to order cart page from "
				+ "Feild/Call/Operation Manager Home Page");
		siteID = this.makeResToResDetailPg(res, isWalkin, startPage);

		int counter = 0;

		do {
			if (isGroup) {
				resvDetailPg.selectGroup();
				resvDetailPg.setNumOfOccupants(res.numofOccupant);
				resvDetailPg.setNumOfVehicles(res.numofVehicle);
			}
			if (isWalkin) {
				resvDetailPg.selectCheckIn();
				browser.waitExists(confirmDialog, resvDetailPg);
			} else {
				resvDetailPg.unselectCheckIn();
			}
			if (res.accessPass) {
				if ((!resvDetailPg.checkRemoveCustomerPassExist())
						&& resvDetailPg.checkAddCustomerPassExist()) {// &&&
					resvDetailPg.clickAddCustomerPass();
				} else {
					if (resvDetailPg.getRemoveCustomerTypeButton() < 2) {
						resvDetailPg.clickAddCustomerType();
					}
				}

				if (null != res.customer.type
						&& !"".equalsIgnoreCase(res.customer.type)) {
					resvDetailPg.clickAddCustomerType();
					resvDetailPg.selectCustomerType(res.customer.type, 1, 1);

				}

				if (null != res.customer.pass
						&& !"".equalsIgnoreCase(res.customer.pass)) {
					resvDetailPg.selectCustomerPass(res.customer.pass, 1);// Q
					resvDetailPg.waitLoading();
					resvDetailPg.setPassNum(res.customer.passNumber);
					resvDetailPg
							.setAccessPassHolderName(res.customer.passHolderName);
					resvDetailPg
							.setAccessPassExpireDate(res.customer.passExpiryDate);
				}
				if (resvDetailPg.getNumOfProofShownCheckBox() > 1) {
					resvDetailPg.selectProofShown(1);
				}
			}

			// retrieve the actual arrival/departure dates and nights
			res.arriveDate = resvDetailPg.getArriveDate();
			if (res.site.typeOfUse.equals("Day")) {
				res.departDate = resvDetailPg.getDaysDepartDate();
				res.nightNum = resvDetailPg.getDaysNightNum() + "";
			} else {
				res.departDate = resvDetailPg.getDepartDate();
				res.nightNum = resvDetailPg.getNightNum() + "";
			}

//			resvDetailPg.addAnyUnit(res.site.validCampingUnitQty,
//					res.site.campingUnitPlate, res.site.unitState);
			//Quentin[20131115] 3.05 Camping Unit and Occupant setup work flow has been changed
			resvDetailPg.selectOccupantType(StringUtil.EMPTY);
			if(resvDetailPg.isAddCampingUnitsExists()) {
				resvDetailPg.clickAddCampingUnits();
				ajax.waitLoading();
				resvDetailPg.waitLoading();
				campingUnitWidget.addCampingUnit(res.site.numOfCampingUnits, res.site.validCampingUnit, res.site.validCampingUnitQty, res.site.campingUnitLength, res.site.campingUnitPlate);
				resvDetailPg.waitLoading();
			}
			
			resvDetailPg.clickOK();

			// comment out discount page
			ajax.waitLoading();
			Object page = browser.waitExists(confirmDialog, resvDetailPg,
					ordCartPg, siteListPg, omDiscountPg);
			if (page == siteListPg) {
				// work flows for checking in to NSS child site
				siteID = siteListPg.selectSiteToOrder(res.site.nssChild);
				ordCartPg.waitLoading();
			}

			counter++;
		} while (res.site.isNSSSite
				&& Integer.valueOf(res.siteQty.trim()) > counter);

		Object page = browser.waitExists(ordCartPg, resvDetailPg, siteListPg,
				omDiscountPg);
		if (page == resvDetailPg) {
			resvDetailPg.addAnyUnit(res.site.validCampingUnitQty);
			resvDetailPg.clickOK();

			ajax.waitLoading();
			page = browser.waitExists(omDiscountPg, ordCartPg);
		}

		if (page == omDiscountPg) {
			if (res.discountName.trim().length() > 0) {
				omDiscountPg.applyBaseDiscount(res.discountName);
			}
			omDiscountPg.clickOK();
			// ajax.waitLoading();
			browser.waitExists(ordCartPg);
		}

		return siteID;
	}

	/**
	 * Verify Fee Schedule is used when make a reservation
	 * 
	 * @param resId
	 * @param feeType
	 *            - fee type
	 * @param isActive
	 *            - schedule status
	 * @param scheduleId
	 *            - schedule Id
	 */
	public void verifyFeeScheduleIsUsed(String resId, String feeType,
			boolean isActive, String scheduleId) {
		this.verifyFeeScheduleIsUsed(resId, feeType, isActive, scheduleId,
				false);
	}
	
	public void gotoFeeDetailPageFromOrderCartPage(String resId, boolean isPermit){
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage
		.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Go to fee detail page from order cart page.");

		ordCartPg.goFeeDetail(resId, isPermit);
		ajax.waitLoading();
		feeDetailPg.waitLoading();
	}
	
	public void gotoOrderCartPgFromResDetailPage(){
		OrmsReservationDetailsPage cmResvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Go to order cart page from reservation detail page after click add to cart...");
		cmResvDetailPg.clickAddToCart();
		ordCartPg.waitLoading();
	}
	
	public void gotoOrderCartPageFromFeeDetailPage(boolean clickOK){
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Go to order cart page from fee detail page after click "+(clickOK?"OK":"Cancel")+".");

		if(clickOK){
			feeDetailPg.clickOK();
		}else feeDetailPg.clickCancel();
		ajax.waitLoading();
		ordCartPg.waitLoading();
	}
	
	public void gotoOrderCartPageFromFeeDetailPage(){
		gotoOrderCartPageFromFeeDetailPage(false);
	}
	
	public void verifyUseFeeScheduleInfo(String resId, String feeType, String feeScheduleId, double feeAmount, String rateUnit, boolean notSite) {
		verifyUseFeeScheduleInfo(resId, feeType, feeScheduleId, feeAmount, rateUnit, 0, notSite);
	}
	
	public void verifyUseFeeScheduleInfo(String resId, String feeType, String feeScheduleId, double feeAmount, String rateUnit, int index, boolean notSite) {
		verifyFeeScheduleInfo(resId, feeType, feeScheduleId, feeAmount, rateUnit, StringUtil.EMPTY, index, notSite);
	}
	
	public void verifyFeeScheduleInfo(String resId, String feeType, String feeScheduleId, double feeAmount, String rateUnit, String appliedTo, boolean notSite) {
		verifyFeeScheduleInfo(resId, feeType, feeScheduleId, feeAmount, rateUnit, appliedTo, 0, notSite);
	}
	
	public void verifyFeeScheduleInfo(String resId, String feeType, String feeScheduleId, double feeAmount, String rateUnit, String appliedTo, int index, boolean notSite) {
		logger.info("Verify " + feeType + "(ID = " + feeScheduleId + ") info in Order Fees details page.");
		
		this.gotoFeeDetailPageFromOrderCartPage(resId, notSite);
		this.verifyFeeScheduleInfo(feeType, feeScheduleId, feeAmount, rateUnit, appliedTo, index);
	}
	
	public void verifyFeeScheduleInfo(String feeType, String feeScheduleId, double feeAmount, String rateUnit, String appliedTo) {
		verifyFeeScheduleInfo(feeType, feeScheduleId, feeAmount, rateUnit, appliedTo, 0);
	}
	
	public void verifyFeeScheduleInfo(String feeType, String feeScheduleId, double feeAmount, String rateUnit, String appliedTo, int index) {
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage.getInstance();

		List<ReservationFeeInfo> actualFees = feeDetailPg.getFeeInfo(feeType, index);
		System.out.println(actualFees.size());
		
		boolean result = true;
		double totalAmount = 0;
		ReservationFeeInfo actualFee = new ReservationFeeInfo();
		for(int i = 0; i < actualFees.size(); i ++) {
			//combine multiple records of fee amount to 1 record with same id
			if(actualFees.get(i).feeSchID.trim().equals(feeScheduleId.trim())) {
				if(StringUtil.isEmpty(actualFee.feeType)) {
					actualFee.feeType = actualFees.get(i).feeType;
				}
				if(StringUtil.isEmpty(actualFee.feeSchID)) {
					actualFee.feeSchID = actualFees.get(i).feeSchID;
				}
				if(StringUtil.isEmpty(actualFee.rateUnit)) {
					actualFee.rateUnit = actualFees.get(i).rateUnit;
				}
				if(StringUtil.isEmpty(actualFee.tranType)) {
					actualFee.tranType = actualFees.get(i).tranType;
				}
				
				totalAmount += Double.parseDouble(actualFees.get(i).feeAmount);
			}
		}
		
		result &= MiscFunctions.compareResult("Fee Type", feeType, actualFee.feeType);
		result &= MiscFunctions.compareResult("Fee ID", feeScheduleId.trim(), actualFee.feeSchID.trim());
		result &= MiscFunctions.compareResult("Rate Unit", rateUnit, actualFee.rateUnit);
		if(!StringUtil.isEmpty(appliedTo)) {
			result &= MiscFunctions.compareResult("Applies To", appliedTo, actualFee.tranType);
		}
		
		BigDecimal expectedTotalAmount = new BigDecimal(feeAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal actualTotalAmount = new BigDecimal(totalAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		result &= MiscFunctions.compareResult("Amount", expectedTotalAmount, actualTotalAmount);
		
		if(!result) {
			throw new ErrorOnPageException(feeType + "(ID=" + feeScheduleId + ") info is NOT correct.");
		}
	}
	
	private void verifyFeeScheduleInfo(String feeType, String feeScheduleId, String feeAmount, String rateUnit, String appliedTo) {
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage.getInstance();
		
		logger.info("Verify " + feeType + "(ID = " + feeScheduleId + ") info in Order Fees details page.");
		List<String> actualFees = feeDetailPg.getFeeBySystemCalculate(feeType);
		feeAmount = new BigDecimal(feeAmount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		String expectedFee = feeAmount + feeScheduleId + rateUnit + appliedTo;
		if(!actualFees.contains(expectedFee)) {
			throw new ErrorOnPageException(feeType + "(ID=" + feeScheduleId + ") info is NOT correct. Expected is: " + expectedFee);
		} else logger.info(feeType + "(ID=" + feeScheduleId + ") info is correct as: " + expectedFee);
	}
	
	public void verifyFeeScheduleInfo(String resId, String feeType, String feeScheduleId, String feeAmounts[], String rateUnit, String appliedTo, boolean notSite) {
		this.gotoFeeDetailPageFromOrderCartPage(resId, notSite);
		
		for(int i = 0; i < feeAmounts.length; i ++) {
			this.verifyFeeScheduleInfo(feeType, feeScheduleId, feeAmounts[i], rateUnit, appliedTo);
		}
	}
	
	public void verifyRAFeeScheduleInfo(String feeType, String feeSchdlID, String origRates[], String amounts[]) {
		verifyRAFeeScheduleInfo(feeType, feeSchdlID, origRates, amounts, null);
	}
	
	public void verifyRAFeeScheduleInfo(String feeType, String feeSchdlID, String origRates[], String amounts[], String units[]) {
		OrmsReservationFeesPage feeDetailsPage = OrmsReservationFeesPage.getInstance();
		
		List<FeeRecord> actualRAFees = feeDetailsPage.getRAFeeData();
		List<FeeRecord> toRemoveList = new ArrayList<FeeRecord>();
		for(FeeRecord fr : actualRAFees) {
			if(!fr.feeSchID.equalsIgnoreCase(feeSchdlID)) {
				toRemoveList.add(fr);
			}
		}
		actualRAFees.removeAll(toRemoveList);
		
		if(actualRAFees.size() != origRates.length || actualRAFees.size() != amounts.length) {
			throw new ErrorOnPageException("RA Transaction Fee records size is not correct.");
		}
		
		Collections.reverse(actualRAFees);
		boolean result = true;
		for(int i = 0; i < actualRAFees.size(); i ++) {
			FeeRecord fee = actualRAFees.get(i);
			
			result &= MiscFunctions.compareResult("Fee Type", feeType, fee.feeType);
			result &= MiscFunctions.compareResult("RA Fee Schedule ID", feeSchdlID, fee.feeSchID);
			String doubleRegex = "\\d+\\.\\d{1,2}";
			result &= MiscFunctions.compareResult("Orig Rate", origRates[i].matches(doubleRegex) ? Double.parseDouble(origRates[i]) : origRates[i], fee.origRate.matches(doubleRegex) ? Double.parseDouble(fee.origRate) : fee.origRate);
			result &= MiscFunctions.compareResult("RA Fee Amount", Double.parseDouble(amounts[i]), Double.parseDouble(fee.feeAmount));
			if(units != null && units.length > 0) {
				result &= MiscFunctions.compareResult("Unit", StringUtil.isEmpty(units[i]) ? StringUtil.EMPTY : units[i], fee.unit);
			}
		}
		
		if(!result) {
			throw new ErrorOnPageException("RA Fee Schedule - " + feeSchdlID + " info is NOT correct.");
		} else logger.info("RA Fee Schedule - " + feeSchdlID + " info is correct.");
	}
	
	public void verifyRAFeeTotalAmount(double expected) {
		String actualFeeAmount = OrmsReservationFeesPage.getInstance().getRAFeeTotalAmount();
		if(!MiscFunctions.compareResult("RA Fee Total amount", expected, Double.parseDouble(actualFeeAmount))) {
			throw new ErrorOnPageException("RA Fee Total amount is NOT correct.");
		}
	}
	
	/**
	 * Verify Fee Schedule is used
	 * 
	 * @param resId
	 * @param feeType
	 *            - fee type
	 * @param isActive
	 *            - schedule status
	 * @param scheduleId
	 *            - schedule Id
	 */
	public void verifyFeeScheduleIsUsed(String resId, String feeType,
			boolean isActive, String scheduleId, boolean notSite) {
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage
				.getInstance();

		logger.info("Start to verify Fee Schedule " + scheduleId);

		if(!feeDetailPg.exists()){
			this.gotoFeeDetailPageFromOrderCartPage(resId, notSite);
		}
		
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
		logger.info("Verify fee schedule which id is " + scheduleId
				+ " successfully !");

		// List<ReservationFeeInfo> fees = feeDetailPg.getCusFeesDetail();
		// ReservationFeeInfo fee = null;
		// for (ReservationFeeInfo f : fees) {
		// if (f.feeType.equals(feeType)) {
		// fee = f;
		// break;
		// }
		// }
		//
		// if (fee != null) {
		// if (isActive) {
		// if (!fee.feeSchID.equals(scheduleId)) {
		// throw new ItemNotFoundException("Fee Schedule ID "
		// + fee.feeSchID + " not correct.");
		// }
		// } else {
		// if (fee.feeSchID.equals(scheduleId)) {
		// throw new ItemNotFoundException("Fee Schedule ID "
		// + fee.feeSchID + " not correct.");
		// }
		// }
		// } else {
		// if (isActive) {
		// throw new ItemNotFoundException("Can not find " + feeType);
		// }
		// }
	}

	/**
	 * Verify RA Fee Schedule is used
	 * 
	 * @param feeType
	 *            - fee type
	 * @param isActive
	 *            - schedule status
	 * @param scheduleId
	 *            - schedule Id
	 */
	public void verifyRAFeeScheduleIsUsed(String feeType, String scheduleId,
			boolean isActive) {
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsReservationFeesPage feeDetailPg = OrmsReservationFeesPage
				.getInstance();
		OrmsOrderCartPage omOrdCartPg = OrmsOrderCartPage.getInstance();

		resvDetailPg.clickFees();
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
		omOrdCartPg.waitLoading();
	}

	/**
	 * 
	 * cancel purchase ticket process in ticketselection page or other page,it
	 * depends on the param you input.
	 * 
	 * @param page
	 * @Return void
	 * @Throws
	 */
	public void cancelPurchaseTicket(OrmsPage page) {
		OrmsTicketSelectionPage ticketselectionpage = OrmsTicketSelectionPage
				.getInstance();
		if (page == ticketselectionpage) {
			logger.info("Cancel purchase ticket from ticket selection page");
			ticketselectionpage.clickCancel();
			browser.waitExists(OrmsTicketAvailabilityPage.getInstance());
		} else {
			throw new PageNotFoundException(
					page.getName()
							+ "is not expected as a starting page for cancelPurchaseTicket");
		}

	}

	/**
	 * 
	 * verify the cancel operation in purchasing ticket process Those work flows
	 * must occur between page classes in common pakage.
	 * (1)From:OrmsTicketSelectionPage TO:OrmsTicketAvailabilityPage
	 * (2)From:OrmsTicketAvailabilityPage To:OrmsTicketAvailabilityPage
	 * 
	 * @param endPage
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void verifyCancelPurchaseTicket(OrmsPage startPage, TicketInfo ticket) {
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage
				.getInstance();
		OrmsTicketSearchPage ticketsearchpage = OrmsTicketSearchPage
				.getInstance();
		boolean verifySuccess = true;
		try {
			logger.info("Verify cancel purchase ticket in "
					+ startPage.getName());

			if (startPage == selectionpage) {
				selectionpage.clickCancel();
				availpage.waitLoading();
				// verify previous page
				if (!availpage.exists())
					return;

				availpage.searchAvailibility(ticket);
				browser.waitExists(availpage);
				String inventory = availpage
						.getSelectTimeTourInventory(ticket.timeSlot);
				// verify if tour inventory has been released
				if (!inventory.equals(ticket.inventory)) {
					verifySuccess = false;
				}

			} else if (startPage == availpage) {
				availpage.searchAvailibility(ticket);
				availpage.waitLoading();
				ticket.inventory = availpage
						.getSelectTimeTourInventory(ticket.timeSlot);

				availpage.selectTicket(ticket.timeSlot, ticket.quantity);
				availpage.clickCancel();

				Object page = browser.waitExists(availpage, selectionpage,
						ticketsearchpage);

				if (page == ticketsearchpage) {
					ticketsearchpage.searchTicket(ticket);
					ticketsearchpage.clickCheckAvailability();
					availpage.waitLoading();
				} else if (page != availpage) {
					verifySuccess = false;
					logger.error(availpage.getName() + " is not found.");
					return;
				}
				String inventory = availpage
						.getSelectTimeTourInventory(ticket.timeSlot);
				if (!inventory.equals(ticket.inventory)) {
					logger.error("The inventory of time:" + ticket.timeSlot
							+ " should be " + ticket.inventory);
					verifySuccess = false;
					throw new ErrorOnPageException("The inventory of time:"
							+ ticket.timeSlot + " should be "
							+ ticket.inventory);
				}
			} else {
				throw new PageNotFoundException(
						startPage.getName()
								+ "is not include in this KeyWord,Please add your code to it.");
			}
			logger.info("verify successfully .");
		} finally {

			if (!verifySuccess) {
				throw new ActionFailedException(
						"the cancel operation is failed .");
			}
		}
	}

	/**
	 * 
	 * search tickets in ticket availability page
	 * 
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void searchTicketsInAvailabilityPage(TicketInfo ticket) {
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		logger.info("Search tickets in ticket availability page....");
		availpage.searchAvailibility(ticket);
		ajax.waitLoading();
		availpage.waitLoading();
	}

	/**
	 * 
	 * search multi-tour in ticket availability page
	 * 
	 * @param tours
	 *            tours' name
	 * @param date
	 *            start date
	 * @param ticketNum
	 *            the number of ticket you want to purchase
	 * @param showAvail
	 *            only show available or not.
	 * @Return void
	 * @Throws
	 */
	public void searchTicketsInAvailabilityPage(String tours[], String date,
			String ticketNum, boolean showAvail) {
		OrmsTicketAvailabilityPage availPage = OrmsTicketAvailabilityPage
				.getInstance();
		availPage.selectTours(tours);
		availPage.setDate(date);
		availPage.setNumOfTikets(ticketNum);
		if (showAvail) {
			availPage.selectShowAvailableOnlyCheckBox();
		} else {
			availPage.deSelectShowAvailableOnlyCheckBox();
		}

		availPage.clickGo();
		availPage.waitLoading();

	}

	/**
	 * 
	 * select ticket in ticket availability page.
	 * 
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void selectTicket(TicketInfo ticket) {
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		logger.info("Select ticket inventory in ticket availability page...");
		availpage.selectTicket(ticket.timeSlot, ticket.quantity);
		availpage.waitLoading();

	}

	/**
	 * 
	 * select all ticket which have the same timeslot
	 * 
	 * @param timeSlot
	 * @Return void
	 * @Throws
	 */
	public void selectTicket(String timeSlot) {
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		logger.info("Select ticket inventory in ticket availability page...");
		availpage.selectTicketSameTimeSlot(timeSlot);
		// this method doesn't need a wait method.\ufffd\ufffdAlex Sun
	}

	/**
	 * 
	 * verify error message in ticket selection page.
	 * 
	 * @param msg
	 * @Return void
	 * @Throws
	 */
	public void verifyErrorMessageInTicketSelectionPage(String msg) {
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage
				.getInstance();
		logger.info("Verify Error message in ticket selection page...");

		if (!selectionpage.exists()) {
			throw new PageNotFoundException(
					"Ticket Selection page is not found .");
		}

		List<String> list = selectionpage.getErrorMessages();
		if (list.size() < 1) {
			throw new ItemNotFoundException(
					"No any error message is displayed.");
		}

		if (!list.contains(msg)) {
			throw new ItemNotFoundException(
					"The excepted message is not displayed.");
		}

		logger.info("verify successfully.");

	}

	/**
	 * set quantity in ticket selection page,Add Tickets page .etc.
	 * 
	 * @param types
	 * @param typeNums
	 * @param page
	 *            the page where you fill the quantity
	 * @return
	 */
	public String setTicketQuantity(String[] types, String[] typeNums,
			OrmsPage page) {
		String inventory = null;
		logger.info("Fill in ticket types and corresponding quantities.....");
		if (page instanceof OrmsTicketSelectionPage) {
			OrmsTicketSelectionPage selectionPage = (OrmsTicketSelectionPage) page;
			selectionPage.setTicketTypeNumber(types, typeNums);
		} else if (page instanceof OrmsAddTicketPage) {
			OrmsAddTicketPage addpage = (OrmsAddTicketPage) page;
			addpage.addMoreTickets(types, typeNums);
			inventory = addpage.getTicketInventory(0);
		} else {
			throw new PageNotFoundException("The page you passed is not"
					+ " found in this keyword,please "
					+ "have a check,If you are sure pass "
					+ "your page to this keyword,you can add "
					+ "you own code to this keyword.thanks.");
		}

		return inventory;
	}

	/**
	 * 
	 * set ID/Pass# when purchse ticket, because we can set ID/Pass# in ticket
	 * selection page and add ticket page,so I reseve a "else if" for
	 * OrmsTicketSelectionPage, If you need to set pass for other page, you can
	 * add your own \ufffdelse if\ufffd to this keyword.
	 * 
	 * @param passes
	 * @param startPage
	 * @Return void
	 * @Throws
	 */
	public void setID_PassForTicket(HashMap<String, String[]> passes,
			OrmsPage currentPage) {
		if (currentPage instanceof OrmsAddTicketPage) {
			OrmsAddTicketPage addpage = (OrmsAddTicketPage) currentPage;
			addpage.addMorePass(passes);
		} else if (currentPage instanceof OrmsTicketSelectionPage) {
			OrmsTicketSelectionPage selectionpage = (OrmsTicketSelectionPage) currentPage;
			selectionpage.addMorePass(passes);
		} else {
			throw new PageNotFoundException("The page you passed is not"
					+ " found in this keyword,please "
					+ "have a check,If you are sure pass "
					+ "your page to this keyword,you can add "
					+ "you own code to this keyword.thanks.");
		}
	}

	/**
	 * 
	 * search customer in ticket selection page
	 * 
	 * @param customer
	 * @Return void
	 * @Throws
	 */
	public void searchCustomer(Customer customer) {
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		logger.info("Search a existing customer.");
		omCustSchPg.searchCust(customer);
		omCustSchPg.waitLoading();
	}
	

	/**
	 * 
	 * verify if the expected message is displayed in Add Tickets Page
	 * 
	 * @param expectedMsg
	 * @Return void
	 * @Throws
	 */
	public void verifyErrorMessageInAddTicketsPage(String... expectedMsg) {
		OrmsAddTicketPage addTicketpage = OrmsAddTicketPage.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		
		addTicketpage.clickAddTickets();

		ajax.waitLoading();

		Object page = browser.waitExists(cartPg,addTicketpage);
		String msgs = "";
		if (page == cartPg) {
		    cartPg.setupPayment(new Payment("Visa"));
		    cartPg.clickProcessOrder();
		    ajax.waitLoading();
		    page = browser.waitExists(cartPg, OrmsOrderSummaryPage.getInstance());
		    if(page !=cartPg){
		    	throw new PageNotFoundException(
						"This process should fail,and should stay 'OrmsOrderCartPage' !");
		    }
			msgs = cartPg.getErrorMessage();
		}else{
			msgs = addTicketpage.getErrorMessages();
		}

		logger.info("Verify error message in Add Ticket Page...");
		if (msgs.length() < 1) {
			logger.error("No any error message is displayed in this page.");
			throw new ItemNotFoundException(
					"No any error message is displayed in this page.");
		}
		for (String msg : expectedMsg) {
			if (!msgs.contains(msg)) {
				logger.error("The expected message is not displayed.");
				throw new ItemNotFoundException(
						"The expected message is not displayed.");
			}
		}
		logger.info("Verify successfully");
	}

	/**
	 * goto ticket order page from call/operation/venue manager Home
	 * Page,OrmsAddTicketPage,Ticket Order Details page
	 * 
	 * @param page
	 * @Return void
	 * @Throws
	 */
	public void gotoTicketOrderSearchPage(OrmsPage page) {
		OrmsTicketOrderSearchPage ordersearchpage = OrmsTicketOrderSearchPage
				.getInstance();
		if (page instanceof VnuMgrHomePage) {
			logger.info("Go to Ticket Order Search page from Venue Manager Home Page");
			VnuMgrHomePage vnuHomepage = (VnuMgrHomePage) page;

			vnuHomepage.selectSearchDropDown("Ticket Orders");
			ordersearchpage.waitLoading();
		} else if (page instanceof CallMgrHomePage) {
			logger.info("goto Ticket order search page from Call Manager Home page");

			CallMgrHomePage callHomePage = (CallMgrHomePage) page;
			CallMgrTopMenuPage toppage = CallMgrTopMenuPage.getInstance();

			callHomePage.clickTicketingCall();
			toppage.waitLoading();
			toppage.selectSearchDropDown("Ticket Orders");
			ordersearchpage.waitLoading();
		} else if (page instanceof OpMgrHomePage) {
			logger.info("Goto Order Search page from Operation manager home page");

			OpMgrTopMenuPage menupage = OpMgrTopMenuPage.getInstance();
			menupage.selectSearchDropDown("Ticket Orders");
			ordersearchpage.waitLoading();
		} else if (page instanceof OrmsTicketOrderDetailsPage) {
			OrmsTicketOrderDetailsPage detailspage = (OrmsTicketOrderDetailsPage) page;
			logger.info("go to Ticket Order Search Page from Ticket Order Details page");

			detailspage.clickCancel();
			browser.waitExists(OrmsTicketOrderSearchPage.getInstance());
		} else if (page instanceof OrmsAddTicketPage) {
			OrmsAddTicketPage addpage = (OrmsAddTicketPage) page;
			OrmsTicketOrderDetailsPage detailpage = OrmsTicketOrderDetailsPage
					.getInstance();

			logger.info("go to Ticket Order Search Page from Add Tickets page");

			addpage.clickDontAdd();
			detailpage.waitLoading();
			detailpage.clickCancel();
			browser.waitExists(OrmsTicketOrderSearchPage.getInstance());
		} else {
			throw new PageNotFoundException(
					"this Home Page is not existent in keyword, If you need it, Please add your code to this key word.");
		}
	}

	/**
	 * 
	 * search ticket order in ticket order search page
	 * 
	 * @param order
	 * @Return void
	 * @Throws
	 */
	public void searchTicketOrder(String orderNum) {
		OrmsTicketOrderSearchPage ordersearch = OrmsTicketOrderSearchPage
				.getInstance();
		logger.info("Search ticket order in Ticket Order Search page.");
		ordersearch.searchTicketOrder(orderNum);
		ordersearch.waitLoading();
	}

	/**
	 * 
	 * This method executes the work flow of going to an existing ticket's order
	 * details page. The work flow starts from home page and ends at the ticket
	 * order details page.
	 * 
	 * @param order
	 * @Return void
	 * @Throws
	 */
	public void gotoTicketOrderDetailsPage(String orderNum) {
		OpMgrHomePage omHmPg = OpMgrHomePage.getInstance();
		CallMgrHomePage callHomePage = CallMgrHomePage.getInstance();
		VnuMgrHomePage venueHomePage = VnuMgrHomePage.getInstance();
		OrmsTicketOrderSearchPage ordersearch = OrmsTicketOrderSearchPage
				.getInstance();
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsAlertPage omAlertPg = OrmsAlertPage.getInstance();
		String location = this.getClass().getSimpleName();

		logger.info("go to ticket order details(Order#=" + orderNum
				+ ") page from order search page.");

		if (location.equals("VenueManager")) {
			this.gotoTicketOrderSearchPage(venueHomePage);
		} else if (location.equals("OperationManager")) {
			this.gotoTicketOrderSearchPage(omHmPg);
		} else if (location.equals("CallManager")) {
			this.gotoTicketOrderSearchPage(callHomePage);
		} else {
			throw new ErrorOnDataException("Unknow Kewords");
		}

		ordersearch.waitLoading();
		this.searchTicketOrder(orderNum);
		ordersearch.clickOrderNumberLink(orderNum);
		Object page = browser.waitExists(omAlertPg, detailsPage);
		if (page == omAlertPg) {
			omAlertPg.clickOK();
			detailsPage.waitLoading();
		}
	}

	/**
	 * 
	 * goto Add tickets Page from Home Page Page.
	 * 
	 * @param order
	 * @Return void
	 * @Throws
	 */
	public void gotoAddTicketsPage(String orderNum) {
		OrmsAddTicketPage addpage = OrmsAddTicketPage.getInstance();
		OrmsTicketOrderDetailsPage detailspage = OrmsTicketOrderDetailsPage
				.getInstance();
		logger.info("go to Add Tickets Page.....");
		this.gotoTicketOrderDetailsPage(orderNum);
		detailspage.clickAddTickets();
		addpage.waitLoading();
	}
	
	public void cancelFromAddTickets(){
		OrmsAddTicketPage addpage = OrmsAddTicketPage.getInstance();
		OrmsTicketOrderDetailsPage detailspage = OrmsTicketOrderDetailsPage
				.getInstance();

		logger.info("Go to order cart page from add ticket page.");

		addpage.clickDontAdd();
		ajax.waitLoading();
		detailspage.waitLoading();
	}

	public String addTicketToOrder(String orderNum, TicketInfo ticket) {
		OrmsAddTicketPage addpage = OrmsAddTicketPage.getInstance();
		OrmsOrderCartPage ordercart = OrmsOrderCartPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget
				.getInstance();

		logger.info("Add tickets to a ticket order from Ticket Order Search Page.");
		this.gotoAddTicketsPage(orderNum);
		String inventory = this.setTicketQuantity(ticket.types,
				ticket.typeNums, addpage);
		if (addpage.isIDPassRequired()) {
			this.setID_PassForTicket(ticket.passes, addpage);
		}
		addpage.clickAddTickets();
		ajax.waitLoading();
		Object page = browser.waitExists(tpaWidget, ordercart);
		if (page == tpaWidget) {
			tpaWidget.addTourParticipantAttributesForPerTicket(ticket.tour,
					ticket.addedTourParticipantAttributesForPerTicket);
			tpaWidget.clickOK();
			ajax.waitLoading();
		}
		ordercart.waitLoading();

		return inventory;
	}

	/**
	 * 
	 * verify if the order actions button is disabled as expected in ticket
	 * order details page
	 * 
	 * @param buttonName
	 * @param disabled
	 *            (expect it disabled)?true:false
	 * @Return void
	 * @Throws
	 */
	public void verifyOrderActionsInTicketOrderDetailsPage(String buttonName,
			boolean disabled) {
		OrmsTicketOrderDetailsPage detailspage = OrmsTicketOrderDetailsPage
				.getInstance();
		logger.info("verify if the Order Action Buttons is as expected");
		if (disabled != detailspage.isThisButtonDisabled(buttonName)) {
			String flag = disabled ? "disable" : "enable";
			throw new ErrorOnPageException("the action button should be "
					+ flag);
		}
		logger.info("Verify successfully..");
	}

	/**
	 * 
	 * go to Ticket Order Cart page from Ticket Selection Page
	 * 
	 * @param ticket
	 * @param cust
	 * @Return void
	 * @Throws
	 */
	public void gotoTicketOrderCartPage(TicketInfo ticket, Customer cust) {
		OrmsTicketSelectionPage ticketSelectionPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		this.setTicketQuantity(ticket.types, ticket.typeNums, ticketSelectionPg);
		if (ticketSelectionPg.checkDeliveryMethodSectionExists()) {
			if (StringUtil.isEmpty(ticket.deliveryMethod))
				ticket.deliveryMethod = "Will Call";// Set as default delivery
													// method
				// throw new
				// ErrorOnDataException("Please specify ticket delivery method in test case.");
				// else
			ticketSelectionPg.selectDeliveryMethod(ticket.deliveryMethod);
		} else {
			if (!StringUtil.isEmpty(ticket.deliveryMethod))
				throw new ErrorOnDataException(
						"There is not 'Delivery Method' section on this page, please configure it.");
		}
		Object page = this.clickPurchaseTickets(OrmsTicketSelectionPage
				.getInstance());
		if (page == omCustSchPg) {
			omCustSchPg.waitLoading();
			this.searchCustomer(cust);
			omCustSchPg.selectCust(cust.lName);
		}
		cartPage.waitLoading();
	}

	/**
	 * change customer in order cart page. After search costomer, the work flow
	 * return order cart page
	 * 
	 * @param cust
	 * @Return void
	 * @Throws
	 */
	public void changeCustomer(Customer cust) {
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		logger.info("Change customer in order cart page...");
		cartPage.clickChangeCustomer();
		omCustSchPg.waitLoading();
		this.searchCustomer(cust);
		omCustSchPg.selectCust(cust.lName);
		cartPage.waitLoading();
		logger.info("Change customer successfully !");
	}

	/**
	 * go to Order Summary Page from ticket selection page.
	 * 
	 * @param ticket
	 * @param customer
	 * @Return void
	 * @Throws
	 */
	public String purchaseTicketFromTicketSelectionPage(TicketInfo ticket,
			Customer customer, Payment pay) {
		String orderNum = null;
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
		OrmsTicketSelectionPage ticketSelectionPg = OrmsTicketSelectionPage
				.getInstance();

		this.setTicketQuantity(ticket.types, ticket.typeNums,
				OrmsTicketSelectionPage.getInstance());
		if (ticketSelectionPg.checkDeliveryMethodSectionExists()) {
			if (StringUtil.isEmpty(ticket.deliveryMethod))
				ticket.deliveryMethod = "Will Call";// Set as default delivery
													// method
				// else
			ticketSelectionPg.selectDeliveryMethod(ticket.deliveryMethod);
		} else {
			if (!StringUtil.isEmpty(ticket.deliveryMethod))
				throw new ErrorOnDataException(
						"There is not 'Delivery Method' section on this page, please configure it.");
		}

		Object page = this.clickPurchaseTickets(OrmsTicketSelectionPage
				.getInstance());
		if (page == omCustSchPg) {
			this.searchCustomer(customer);
			omCustSchPg.selectCust(customer.lName);
			browser.waitExists(cartPage);
		}
		cartPage.setupPayment(pay);
		cartPage.clickProcessOrder();

		summaryPage.waitLoading();

		orderNum = summaryPage.getAllOrdNums();
		summaryPage.clickFinishButton();

		browser.waitExists(CallMgrHomePage.getInstance(),
				OpMgrHomePage.getInstance(), VnuMgrHomePage.getInstance());

		return orderNum;
	}

	/**
	 * 
	 * click the purchase tickets button in the startPage if any error message
	 * displayed,the end page will be the start page,or the end page will be the
	 * next page you eapect.
	 * 
	 * @param startPage
	 * @Return Object
	 * @Throws
	 */
	public Object clickPurchaseTickets(OrmsPage startPage) {
		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage
				.getInstance();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Purchase tickets process goes on.....");
		if (startPage == availpage) {
			availpage.clickPurchaseTickets();
		} else if (startPage == selectionpage) {
			selectionpage.clickAddToCart();
		} else {
			throw new PageNotFoundException(startPage.getName()
					+ " is not found.");
		}

		Object page = browser.waitExists(availpage, selectionpage,
				ConfirmDialogPage.getInstance(), omCustSchPg,
				OrmsCustomerSearchPage.getInstance(),orderCartPg);

		if (page == ConfirmDialogPage.getInstance()) {

			ConfirmDialogPage.getInstance().waitLoading();
			ConfirmDialogPage.getInstance().setDismissible(false);
		}

		return page;
	}

	/**
	 * 
	 * verify error message in ticket availability page
	 * 
	 * @Return void
	 * @Throws
	 */

	public void verifyErrorMessageInTicketAvailabilityPage(String expect_msg) {

		OrmsTicketAvailabilityPage availpage = OrmsTicketAvailabilityPage
				.getInstance();
		List<String> errMesages = availpage.getErrorMessages();

		logger.info("Verify Error Message on ticket availability page......");

		if (null == errMesages || errMesages.size() < 1) {
			throw new ItemNotFoundException("The error message is not found.");
		} else if (!errMesages.get(0).matches(
				"(Error:)?" + expect_msg + "(\n)?")) {

			throw new ErrorOnPageException("The exception message '"
					+ expect_msg + "' not found .The displayed message is:"
					+ errMesages);
		}

		logger.info("Verify Successfully.");
	}

	/**
	 * 
	 * Verify Minimum Tickets Restriction in ticketselection page
	 * 
	 * @param OKorCancel
	 * @Return void
	 * @Throws
	 */
	public void verifyMinimunTicketsRestriction(boolean isOk, String msg) {
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage
				.getInstance();
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		OrmsCustomerSearchPage customerPage = OrmsCustomerSearchPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		logger.info("Verify Minimum Tickets Restriction.....");
		selectionpage.clickAddToCart();

		// Verify message on confirm dailog
		confirmPage.setDismissible(false);
		confirmPage.waitLoading();
		String msg_dailog = confirmPage.text().split(":")[1].trim();
		if (!msg_dailog.equals(msg)) {
			throw new ErrorOnPageException("Message on page is wrong !");
		}
		confirmPage.setDismissMethod(isOk);
		confirmPage.setDismissible(true);
		confirmPage.waitLoading();
		Object page = browser.waitExists(confirmPage, selectionpage,
				customerPage, cartPage);

		if (page == selectionpage && !isOk) {
			this.verifyErrorMessageInTicketSelectionPage(msg);
		}

		if (isOk) {
			page = browser.waitExists(customerPage, cartPage);
		}

		if (!isOk && page != selectionpage) {
			throw new PageNotFoundException(
					"the Ticket Selection Page should be displayed.");
		}
		logger.info("Success for Verifying Verify Minimum Tickets Restriction ");

	}

	/**
	 * 
	 * Verify Maximum Tickets Restriction in ticket selection page
	 * 
	 * @param msg
	 * @Return void
	 * @Throws
	 */
	public void verifyMaximumTicketsRestriction(String msg) {
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage
				.getInstance();

		OrmsCustomerSearchPage customerPage = OrmsCustomerSearchPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		logger.info("Verify Maximum Tickets Restriction.....");
		selectionpage.clickAddToCart();

		Object page = browser.waitExists(selectionpage, customerPage, cartPage);

		if (page != selectionpage) {
			throw new PageNotFoundException(
					"the Ticket Selection Page should be displayed.");
		}
		this.verifyErrorMessageInTicketSelectionPage(msg);

		logger.info("Success for Verifying Verify Minimum Tickets Restriction ");

	}

	/**
	 * cancel a ticket order, the work flow start:Call,Venue or Operation Home
	 * page
	 * 
	 * @param startPage
	 *            Call,Venue or Operation Home page
	 * @param orderNum
	 * @Return void
	 * @Throws
	 */
	public void cancelTicketOrder(OrmsPage startPage, String orderNum) {
		cancelTicketOrder(startPage, orderNum, "", "");
	}

	/**
	 * Cancel a ticket order, the work flow start:Call, Venue or Operation Home
	 * page
	 * 
	 * @param startPage
	 * @param orderNum
	 * @param cancelReason
	 * @param reduceNum
	 */
	public void cancelTicketOrder(OrmsPage startPage, String orderNum,
			String cancelReason, String reduceNum) {

		OrmsTicketOrderSearchPage searchPage = OrmsTicketOrderSearchPage
				.getInstance();
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsCancelTicketOrderPage cancelPage = OrmsCancelTicketOrderPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		VnuMgrManageTicketsPage vmManageTicketPg = VnuMgrManageTicketsPage
				.getInstance();

		logger.info("Cancel Ticket Order-" + orderNum + ".");

		if (startPage instanceof CallMgrHomePage) {
			CallMgrTopMenuPage topMenu = CallMgrTopMenuPage.getInstance();
			CallMgrHomePage callHome = CallMgrHomePage.getInstance();

			callHome.clickTicketingCall();
			topMenu.waitLoading();
			topMenu.searchTicket();

		} else if (startPage instanceof OpMgrHomePage) {
			OpMgrTopMenuPage topMenu = OpMgrTopMenuPage.getInstance();
			topMenu.searchTicketOrder();

		} else if (startPage instanceof VnuMgrHomePage) {
			VnuMgrTopMenuPage topmenu = VnuMgrTopMenuPage.getInstance();
			topmenu.selectSearchDropDown("Ticket Orders");
			topmenu.clickSearch();
			ajax.waitLoading();

		} else {
			throw new PageNotFoundException(
					"Your Start page should be Venue,Call or operation manager Home page.");
		}
		searchPage.waitLoading();
		searchPage.searchTicketByOrderNumber(orderNum);
		searchPage.waitLoading();
		searchPage.clickOrderNumberLink(orderNum);

		logger.info("Invalid tickets in ticket order details page.");

		if (startPage instanceof VnuMgrHomePage) {
			
			// invalidate All Tickets
			if (null != cancelReason && !"".equals(cancelReason)) {
				detailsPage.waitLoading();
				detailsPage.clickManageTickets();
				ajax.waitLoading();
				vmManageTicketPg.waitLoading();
				vmManageTicketPg.invalidateAllTickets();
				vmManageTicketPg.waitLoading();
				vmManageTicketPg.clickCancel();

			}
		}

		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.clickCancelTickets();
		ajax.waitLoading();
		cancelPage.waitLoading();

		// select cancel reason
		if (null != cancelReason && !"".equals(cancelReason)) {
			cancelPage.selectAllCancelReason(cancelReason);
		}

		if (reduceNum != null && !"".equals(reduceNum)) {
			cancelPage.setTicketNumberToCancel(reduceNum);
		} else {
			cancelPage.cancelAllTickets();
		}
		cancelPage.clickCancelTickets();
		ajax.waitLoading();
		cartPage.waitLoading();
	}

	public String cancelTicketOrder(OrmsPage startPage, String cancelReason,
			String reduceNum) {
		return cancelTicketOrder(startPage, cancelReason, reduceNum, null, null);
	}

	public String cancelTicketOrder(OrmsPage startPage, String cancelReason,
			Map<String, Integer> cancelTypeAndNums,
			Map<String, List<Map<String, String>>> removeTourAndTPAs) {
		return cancelTicketOrder(startPage, cancelReason, null,
				cancelTypeAndNums, removeTourAndTPAs);
	}

	public String cancelTicketOrder(OrmsPage startPage, String cancelReason,
			String reduceNum,
			Map<String, List<Map<String, String>>> removeTourAndTPAs) {
		return cancelTicketOrder(startPage, cancelReason, reduceNum, null,
				removeTourAndTPAs);
	}

	/**
	 * Cancel ticket order from order detail page or cancel ticket page
	 * 
	 * @param startPage
	 * @param cancelReason
	 * @param reduceNum
	 * @return error message on cancel ticket page
	 */
	private String cancelTicketOrder(OrmsPage startPage, String cancelReason,
			String reduceNum, Map<String, Integer> cancelTypeAndNums,
			Map<String, List<Map<String, String>>> removeTourAndTPAs) {
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsCancelTicketOrderPage cancelPage = OrmsCancelTicketOrderPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget
				.getInstance();

		if (startPage instanceof OrmsTicketOrderDetailsPage) {
			logger.info("Go to cancel ticket page from order detail page");
			detailsPage.clickCancelTickets();
			cancelPage.waitLoading();
		} else if (startPage instanceof OrmsCancelTicketOrderPage) {
			logger.info("Cancel Tickets");
		} else {
			throw new PageNotFoundException(
					"Your Start page should be OrmsTicketOrderDetailsPage or CancelTicketOrderPage!");
		}

		// select cancel reason
		if (null != cancelReason && !"".equals(cancelReason)) {
			cancelPage.selectAllCancelReason(cancelReason);
		}
		if (cancelTypeAndNums != null && cancelTypeAndNums.size() > 0) {
			Set<Map.Entry<String, Integer>> set = cancelTypeAndNums.entrySet();
			for (Iterator<Map.Entry<String, Integer>> it = set.iterator(); it
					.hasNext();) {
				Map.Entry<String, Integer> entry = it.next();
				cancelPage.setTicketNumberToCancel(entry.getKey(),
						entry.getValue());
			}
		} else if (reduceNum != null && !"".equals(reduceNum)) {
			cancelPage.setTicketNumberToCancel(reduceNum);
		} else {
			logger.info("Select all tickets");
			cancelPage.cancelAllTickets();
		}

		String msg = null;
		cancelPage.clickCancelTickets();
		ajax.waitLoading();
		Object page = browser.waitExists(tpaWidget, cartPage, cancelPage);
		if (page == tpaWidget) {
			if (removeTourAndTPAs != null && removeTourAndTPAs.size() > 0) {
				tpaWidget.removeTPAInfoOfPerTicket(removeTourAndTPAs);
//				tpaWidget.clickOK();
//				ajax.waitLoading();
			}
			tpaWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(cartPage, cancelPage);
		}
		if (page == cancelPage) {
			msg = cancelPage.getErrorMessage();
		}

		return msg;
	}

	/**
	 * This method voids tickets. The work flow starts from Operation Manager
	 * Homepage page and ends at order cart
	 */
	public void voidTicketToCart(String reservNum) {
		gotoTicketOrderDetailsPage(reservNum);
		voidTicketToCart();
	}

	/**
	 * this method starts from order detail page and ends with order cart page
	 */
	public void voidTicketToCart() {
		voidTicketToCart("Other", "Auto test");
	}
	
	/**
	 * The method executes the work flow of pay outstanding balance reseravtion
	 * The work flow starts from and ends at order cart page.
	 * 
	 */
	public void payOutStandingBalanceFromOrderCartPg(String ordNum) {
		OrmsOrderCartPage omOderCartPg = OrmsOrderCartPage.getInstance();
		OrmsInvoiceSearchPage omInvSchPg = OrmsInvoiceSearchPage.getInstance();

		omOderCartPg.clickPayOutstanding();
		omInvSchPg.waitLoading();

		omInvSchPg.cleanSearchCriteria();

		omInvSchPg.setOrderNum(ordNum);
		omInvSchPg.clickGo();
		omInvSchPg.waitLoading();
		omInvSchPg.selectFirstCheckBox();
		omInvSchPg.clickMakePayment();
		omOderCartPg.waitLoading();
	}

	/**
	 * this method will void the first payment by given order Number, the work
	 * flow start from FieldManager/VenueManager/FinanceManager top menu, and
	 * ends at payment search page.
	 * 
	 * @param ordNum
	 *            :
	 * @param voidReason
	 */
	public void voidPaymentByGivenOrderNum(String ordNum, String voidReason) {
		// field manager pages start
//		FldMgrTopMenuPage fldMgrTopMenuPage = FldMgrTopMenuPage.getInstance();
		OrmsFinSessionSearchPage ormsFinSessionPage = OrmsFinSessionSearchPage
				.getInstance();
		// field manager pages end

		// Venue Manager pages start
		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
		// Venue Manager page end

		// Call manager pages start
		// CallMgrTopMenuPage cmMgrTopMnPg = CallMgrTopMenuPage.getInstance();
		// Call manager pages end

		// Finance Manager pages start
		FinMgrTopMenuPage finMgrTopMnPg = FinMgrTopMenuPage.getInstance();
		OrmsPaymentSearchPage omPaySearchPg = OrmsPaymentSearchPage
				.getInstance();
		OrmsPaymentDetailsPage omPaydetailsPg = OrmsPaymentDetailsPage
				.getInstance();
		OrmsVoidPaymentPage omvoidPayPg = OrmsVoidPaymentPage.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();
		
		// Finance Manager pages end

//		if (this instanceof FieldManager) {
//			logger.info("void payment from Field manager, starting from top menu");
//			fldMgrTopMenuPage.clickFinancials();
//			Object page = browser.waitExists(ormsFinSessionPage, omPaySearchPg);
//			if(page == ormsFinSessionPage) {
//				ormsFinSessionPage.clickPaymentsTab();
//			}
		//} else 
			if (this instanceof VenueManager) {
			logger.info("void payment from Vennue manager, starting from top menu");
			vmTopMenuPg.clickFinancials();
			ormsFinSessionPage.waitLoading();
			ormsFinSessionPage.clickPaymentsTab();
		} else if (this instanceof FinanceManager) {
			logger.info("void payment from finance manager, starting from top menu");
			finMgrTopMnPg.selectFromList("Payments");
//		} else if(this instanceof MarinaManager) {
//			logger.info("void payment from finance manager, starting from top menu");
//			MrnMgrTopMenuPage.getInstance().clickFinancials();
//			ormsFinSessionPage.waitLoading();
//			ormsFinSessionPage.clickPaymentsTab();
		} else {
			throw new ErrorOnDataException(
					"sorry this function only support FieldManager, VenueManager and FinanceManager for now!");
		}
		// } else if (this instanceof CallManager){
		// //To do
		// } else if (this instanceof OperationManager ){
		// //To do
		// }
		omPaySearchPg.waitLoading();

		logger.info("Search To Payment Detail Page.");
		omPaySearchPg.searchPaymentsByOrderNum(ordNum);
		omPaySearchPg.waitLoading();
		omPaySearchPg.clickFirstPayment();
		omPaydetailsPg.waitLoading();

		logger.info("click void payment button on Payment Detail Page for order#: "
				+ ordNum);
		omPaydetailsPg.clickVoidPayment();
		
		Object obj = browser.waitExists(confirmDialog,omvoidPayPg); 
		if(obj instanceof ConfirmDialogPage)
		{
			confirmDialog.clickOK();
		}
		omvoidPayPg.waitLoading();
		omvoidPayPg.setVoidNote(voidReason);
		omvoidPayPg.clickOK();
		omPaydetailsPg.waitLoading();
		omPaydetailsPg.clickOK();

		omPaySearchPg.waitLoading();
	}

	/**
	 * This method voids tickets. The work flow starts from ticket order details
	 * page and ends at order cart
	 * 
	 * @param reason
	 * @param note
	 */
	public void voidTicketToCart(String reason, String note) {
		OrmsOrderCartPage omOrderCartPg = OrmsOrderCartPage.getInstance();
		OrmsTicketOrderDetailsPage omTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsVoidTicketOrderPage omVoidTicketPg = OrmsVoidTicketOrderPage
				.getInstance();
		logger.info("Void ticket order into order cart.");

		omTicketDetailPg.clickVoidTickets();

		omVoidTicketPg.waitLoading();
		omVoidTicketPg.voidTicketOrder(reason, note);

		omOrderCartPg.waitLoading();
	}
	
	/**
	 * This method executes the work flow of voiding a lottery application. The
	 * work flow starts from reservation details page and ends at order cart.
	 */
	public void voidLotteryToCart() {
		OrmsLotteryApplicationDetailsPage lotteryAppDetailsPg = OrmsLotteryApplicationDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsVoidTicketLotteryOrderPage omVoidTicketPg = OrmsVoidTicketLotteryOrderPage
				.getInstance();

		logger.info("Void lottery application");
		lotteryAppDetailsPg.clickVoid();

		omVoidTicketPg.waitLoading();
		omVoidTicketPg.voidTicketOrder("Other", "Auto test");
		ajax.waitLoading();

		ordCartPg.waitLoading();
	}


	/**
	 * The method execute the SignIn process
	 * 
	 * @param login
	 *            -- login information
	 * @param mgr
	 *            -- the manager will be used
	 * @return
	 */
	public OrmsPage signIn(LoginInfo login, String mgr) {
		return signIn(login,mgr,true);
	}
	
	public OrmsPage signIn(LoginInfo login, String mgr, boolean newBrowser) {
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
		OrmsLoginPage omLoginPg = OrmsLoginPage.getInstance();
		OrmsGeneralSignedInPage omSignedInPg = OrmsGeneralSignedInPage
				.getInstance();

		if(mgr.equalsIgnoreCase("cm"))
		
		logger.info("Login in " + mgr + " Manager with url=" + login.url);

		gotoSignInPg(login.url, mgr,newBrowser);
		omSigninPg.login(login);

		Object page = browser.waitExists(omSigninPg, omLoginPg, omSignedInPg);
		
		if(page!=omSigninPg) {
			String currentID=((OrmsPage)page).getSessionID();
			String previousIDs=TestProperty.getProperty("session.id","");
			if(StringUtil.isEmpty(previousIDs) || !previousIDs.contains(currentID)) {
				String sessionIDs=StringUtil.isEmpty(previousIDs)?currentID:previousIDs+";"+currentID;
				TestProperty.putProperty("session.id", sessionIDs);
				DataBaseFunctions.recordSessionID(Integer.parseInt(TestProperty.getProperty("caseID", "0")), TestProperty.getProperty("runningId", ""), sessionIDs,currentID);
			} 
		}

		return (OrmsPage) page;
	}

	public boolean verifySignIn(LoginInfo login, String mgr) {
		Object page = signIn(login, mgr);
		return page != OrmsSigninPage.getInstance();
	}

	/**
	 * This work flow start from orms home page and end at sign in page
	 * 
	 * @param login
	 * @param mgr
	 */
	public void gotoSignInPg(String url, String mgr, boolean newBrowser) {
		OrmsHomePage omHmPg = OrmsHomePage.getInstance();
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();

		logger.info("Go to sign in page from orms home page");
		invokeURL(url, newBrowser);
		omHmPg.clickLink(mgr);

		omSigninPg.waitLoading();
	}
	
	public void gotoSignInPg(String url, String mgr) {
		gotoSignInPg(url,mgr,true);
	}

	public void setSignInInfo(LoginInfo login) {
		OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();

		logger.info("Set sign in info.");
		omSigninPg.setUserName(login.userName);
		omSigninPg.setPassword(login.password);
	}
	
	public String verifyBusinessRuleEffectiveAtSlipDetailPage(String ruleName,
			String ruleCondID, String slipCode, boolean isNegative,
			boolean isMaximumOrdersPerCallRule) {
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsSlipAlertsPage ormsSlipAlertPage = OrmsSlipAlertsPage.getInstance();
		OrmsCustomerSearchPage ormsCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsReservationDetailsPage ormsResvDetailPg = OrmsReservationDetailsPage
				.getInstance();
//		OrmsEnterPinNumPopupPage pinPopup = OrmsEnterPinNumPopupPage
//				.getInstance();
		OrmsOverrideRulePinWidget rulePopupPg = OrmsOverrideRulePinWidget
				.getInstance();
		String statusMsg = "";

		String fullAppName = this.getClass().getName();
		String applicationName = fullAppName.substring(fullAppName
				.lastIndexOf(".") + 1);

		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + applicationName + ".");

		if (ruleName.length() <= 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}
		if (ruleCondID.length() <= 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}
		detailsPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(detailsPage, ormsSlipAlertPage,
				ormsCustSchPg, ormsResvDetailPg, rulePopupPg);
		if (isMaximumOrdersPerCallRule) {
			if (page == ormsSlipAlertPage) {
				ormsSlipAlertPage.clickOK();
				page = browser.waitExists(rulePopupPg, detailsPage,
						ormsCustSchPg, ormsResvDetailPg);
			}
		}
		if (page == rulePopupPg) {
			rulePopupPg.clickCancel();
			page = browser.waitExists(detailsPage, ormsSlipAlertPage,
					ormsCustSchPg, ormsResvDetailPg);
		}
		if (page == detailsPage) {
			page = browser.waitExists(rulePopupPg, detailsPage,
					ormsCustSchPg, ormsResvDetailPg);
			if (page == rulePopupPg) {
				rulePopupPg.clickCancel();
			}
			// if the rule validation message displayed at the site detail page,
			// get this message and then verify the rule
			statusMsg = detailsPage.getErrorMessage();
			if (statusMsg.length() == 0 || !statusMsg.contains(ruleCondID)
					|| !statusMsg.contains(slipCode)) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to site ( product.cd=" + slipCode
						+ ") in sales channel - " + applicationName + ".");
			}
			logger.info("----The " + ruleName + " Rule (Rule Cond.ID = "
					+ ruleCondID + ") take effect to sales channel - "
					+ applicationName + " successfully.");
		} else {
			if (!isNegative) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to site (site#=" + slipCode
						+ ") in sales channel - " + applicationName + ".");
			}
			logger.info("----As negatively, the " + ruleName
					+ " Rule (Rule Cond.ID = " + ruleCondID
					+ ") applies correctly to sales channel - "
					+ applicationName + ".");
		}

		return statusMsg;
	}

	/**
	 * Verify the available status of site(s) from the arrival and lasted for
	 * the numOfStay days
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param arrivalDate
	 * @param numOfStay
	 * @param expectedStatus
	 */
	public void verifyBusinessRuleEffectiveAtSiteDetailPage(String ruleName,
			String ruleCondID, String arrivalDate, int numOfStay,
			String expectedStatus) {
		OrmsSiteDetailsPage siteDetailsPg = OrmsSiteDetailsPage.getInstance();

		String fullClassName = this.getClass().getName();
		String className = fullClassName.substring(fullClassName
				.lastIndexOf(".") + 1);
		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + className + ".");

		String siteNumber = siteDetailsPg.getSiteName().split("-")[1].trim();
		if (ruleName.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}
		if (ruleCondID.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}

		boolean siteStatus = true;
		if (expectedStatus.toLowerCase().startsWith("avail")) {
			siteStatus = siteDetailsPg.checkAvail(arrivalDate, numOfStay);
		} else if (expectedStatus.toLowerCase().startsWith("unavail")) {
			siteStatus = siteDetailsPg.checkUnavail(arrivalDate, numOfStay);
		}

		if (!siteStatus) {
			throw new ActionFailedException("The " + ruleName
					+ " Rule (Rule Cond.ID = " + ruleCondID
					+ ") doesn't take effect to Site (site# = " + siteNumber
					+ ") in " + className + ".");
		}

		logger.info("----The " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + className
				+ " successfully.");
	}

	/**
	 * Method to verify whether the rule validation message displayed at the
	 * Site Detail page
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param siteNumber
	 * @param isNegative
	 * @param isMaximumOrdersPerCallRule
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtSiteDetailPage(String ruleName,
			String ruleCondID, String siteNumber, boolean isNegative,
			boolean isMaximumOrdersPerCallRule) {
		OrmsSiteDetailsPage ormsSiteDetailPg = OrmsSiteDetailsPage
				.getInstance();
		OrmsAlertPage ormsSiteAlertPg = OrmsAlertPage.getInstance();
		OrmsCustomerSearchPage ormsCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsReservationDetailsPage ormsResvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsEnterPinNumPopupPage pinPopup = OrmsEnterPinNumPopupPage
				.getInstance();
		String statusMsg = "";

		String fullAppName = this.getClass().getName();
		String applicationName = fullAppName.substring(fullAppName
				.lastIndexOf(".") + 1);

		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + applicationName + ".");

		if (ruleName.length() <= 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}
		if (ruleCondID.length() <= 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}
		ormsSiteDetailPg.clickAddReservToOrder();
		Object page = browser.waitExists(ormsSiteDetailPg, ormsSiteAlertPg,
				ormsCustSchPg, ormsResvDetailPg, pinPopup);
		if (isMaximumOrdersPerCallRule) {
			if (page == ormsSiteAlertPg) {
				ormsSiteAlertPg.clickOK();
				page = browser.waitExists(pinPopup, ormsSiteDetailPg,
						ormsCustSchPg, ormsResvDetailPg);
			}
		}
		if (page == pinPopup) {
			pinPopup.clickCancel();
			page = browser.waitExists(ormsSiteDetailPg, ormsSiteAlertPg,
					ormsCustSchPg, ormsResvDetailPg);
		}
		if (page == ormsSiteDetailPg) {
			page = browser.waitExists(pinPopup, ormsSiteDetailPg,
					ormsCustSchPg, ormsResvDetailPg);
			if (page == pinPopup) {
				pinPopup.clickCancel();
			}
			// if the rule validation message displayed at the site detail page,
			// get this message and then verify the rule
			statusMsg = ormsSiteDetailPg.getErrorMessage();
			if (statusMsg.length() == 0 || !statusMsg.contains(ruleCondID)
					|| !statusMsg.contains(siteNumber)) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to site (site#=" + siteNumber
						+ ") in sales channel - " + applicationName + ".");
			}
			logger.info("----The " + ruleName + " Rule (Rule Cond.ID = "
					+ ruleCondID + ") take effect to sales channel - "
					+ applicationName + " successfully.");
		} else {
			if (!isNegative) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to site (site#=" + siteNumber
						+ ") in sales channel - " + applicationName + ".");
			}
			logger.info("----As negatively, the " + ruleName
					+ " Rule (Rule Cond.ID = " + ruleCondID
					+ ") applies correctly to sales channel - "
					+ applicationName + ".");
		}

		return statusMsg;
	}

	/**
	 * Overload method
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param siteNumber
	 * @param isNegative
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtSiteDetailPage(String ruleName,
			String ruleCondID, String siteNumber, boolean isNegative) {
		return this.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName,
				ruleCondID, siteNumber, isNegative, false);
	}

//	public String verifyBusinessRuleEffectiveAtPermitOrderDetailPage(
//			String ruleName, String ruleCondID, String prodName,
//			boolean isNegative) {
//		OrmsPermitOrderDetailsPage ordDetailPage = OrmsPermitOrderDetailsPage
//				.getInstance();
//		OrmsOrderCartPage orderCartPage = OrmsOrderCartPage.getInstance();
//		OrmsEnterPinNumPopupPage rulePinPopup = OrmsEnterPinNumPopupPage.getInstance();
//		
//		String fullAppName = this.getClass().getName();
//		String applicationName = fullAppName.substring(fullAppName
//				.lastIndexOf(".") + 1);
//		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
//				+ ruleCondID + ") take effect for Permit(Entrance=" + prodName
//				+ ") in " + applicationName + ".");
//		String errorMsg = "";
//		boolean pass = true;
//		ordDetailPage.clickOK();
//		ajax.waitLoading();
//		Object page = browser
//				.waitExists(rulePinPopup, orderCartPage, ordDetailPage);
//		// order cart page should be front of order detail page, because when
//		// order cart page exists, the order details page also exists
//		if (page == rulePinPopup) {
//			rulePinPopup.clickCancel();
//			page = browser.waitExists(orderCartPage, ordDetailPage);
//		}
//		if (page == ordDetailPage) {
//			if (!isNegative) {
//				errorMsg = ordDetailPage.getErrorMessage();
//				System.out.println(errorMsg);
//				if (errorMsg == null || errorMsg.length() == 0
//						|| !errorMsg.contains(ruleCondID)
//						|| !errorMsg.contains(prodName)) {
//					pass &= false;
//				}
//			} else {
//				pass &= false;
//			}
//		}
//		if (page == orderCartPage) {
//			if (!isNegative) {
//				pass &= false;
//			}
//		}
//
//		if (!pass) {
//			throw new ActionFailedException("The " + ruleName
//					+ " Rule (Rule Cond.ID = " + ruleCondID
//					+ ") doesn't take effect to procut (Product# = " + prodName
//					+ ") in sales channel - " + applicationName + ".");
//		}
//		String log = "----" + (isNegative ? "As negatively, " : "") + "The "
//				+ ruleName + " Rule (Rule Cond.ID = " + ruleCondID
//				+ ") take effect to sales channel - " + applicationName
//				+ " successfully.";
//		logger.info(log);
//
//		return errorMsg;
//	}

	public void verifyBusinessRuleEffectiveAtSlipListPage(String ruleName, String ruleCondID, String slipCode, List<String> expectedDates, String expectedStatus) {
		OrmsSlipListPage slipListPage = OrmsSlipListPage.getInstance();

		String fullClassName = this.getClass().getName();
		String appName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") take effect in " + appName + ".");
		if (ruleName.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}
		if (ruleCondID.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}

		boolean result = true;
		if(slipListPage.isTransientNightsTextFieldExists()) {
			for(int i = 0; i < expectedDates.size(); i ++) {
				result &= MiscFunctions.compareResult(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT + " Slip grid status of date " + expectedDates.get(i), expectedStatus, slipListPage.getSlipGridStatus(slipCode, expectedDates.get(i)));
			}
		} else if(slipListPage.isLeaseMonthsDropdownListExists()) {
			String actualAvailability = "";
			for(int i = 0; i < expectedDates.size(); i ++) {
				slipListPage.setArrivalDate(expectedDates.get(i));
				ajax.waitLoading();
				slipListPage.clickGo();
				ajax.waitLoading();
				slipListPage.waitLoading();
				
				actualAvailability = slipListPage.getSlipStatus(slipCode);
				result &= MiscFunctions.compareResult(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE + " " + expectedDates.get(i) + " Availability status", expectedStatus, actualAvailability);
			}
		}
		
		if (!result) {
			throw new ActionFailedException(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") doesn't take effect to Slip (CD# = " + slipCode + ") in " + appName + ".");
		} else logger.info(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") take effect to Slip (CD# = " + slipCode + ") in " + appName + ".");
	}
	
	public void verifyBusinessRuleEffectiveAtSlipDetailsPage(String ruleName, String ruleCondID, SlipInfo slip, boolean isNegative) {
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsOverrideRulePinWidget ruleWidget = OrmsOverrideRulePinWidget.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage.getInstance();
		
		String fullClassName = this.getClass().getName();
		String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		logger.info("Verify " + ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") takes effect to slip on slip details page in " + className);
		detailsPage.setFromDate(slip.getArrivalDate());
		ajax.waitLoading();
		if(detailsPage.isUpdateDisplayExists()) {
			detailsPage.clickUpdateDisplay();
			ajax.waitLoading();
		}
		detailsPage.waitLoading();
		if(detailsPage.isArrivalDateTextFieldExists()) {
			detailsPage.setArrivalDate(slip.getArrivalDate());
			ajax.waitLoading();
		}
		if(detailsPage.isDepartureDateTextFieldExists()) {
			detailsPage.setDepartureDate(slip.getDepartureDate());
			ajax.waitLoading();
		}
		if(detailsPage.isNightsExists()) {
			detailsPage.setNights(slip.getNights());
		}
		if(detailsPage.isMonthsExists()) {
			detailsPage.selectMonths(slip.getMonths());
			ajax.waitLoading();
		}
		detailsPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(ruleWidget, detailsPage, custSearchPage);
		String actualMsg = "";
		if(page == ruleWidget) {
			ruleWidget.clickCancel();
			ajax.waitLoading();
			page = browser.waitExists(detailsPage, custSearchPage);
		}
		
		if(page == detailsPage) {
			actualMsg = detailsPage.getErrorMessage();
			if(StringUtil.isEmpty(actualMsg) || !actualMsg.contains(ruleName.replaceAll("\\s", StringUtil.EMPTY)) || !actualMsg.contains(ruleCondID) || !actualMsg.contains(slip.getCode())) {
				throw new ActionFailedException(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") doesn't take effect to Slip (CD# = " + slip.getCode() + ") in " + className + "."); 
			} else logger.info(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") take effect to Slip (CD# = " + slip.getCode() + ") in " + className + ".");
		} else if(page == custSearchPage) {
			if (!isNegative) {
				throw new ActionFailedException(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") doesn't take effect to Slip (CD#=" + slip.getCode() + ") in sales channel - " + className + ".");
			}
			logger.info("----As negatively, the " + ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") applies correctly to sales channel - " + className + ".");
		}
	}
	
	public void verifyBusinessRuleEffectiveAtSlipDetailsPage(String ruleName, String ruleCondID, String slipCode, List<String> expectedDates, String expectedStatus) {
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		
		String fullClassName = this.getClass().getName();
		String appName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") take effect in " + appName + ".");
		if (ruleName.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}
		if (ruleCondID.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}

		boolean result = true;
		if(slipDetailsPage.isNightsExists()) {
			for(int i = 0; i < expectedDates.size(); i ++) {
				result &= MiscFunctions.compareResult("Slip grid status of date: " + expectedDates.get(i), expectedStatus, slipDetailsPage.getSlipGridStatus(expectedDates.get(i)));
			}
		} else if(slipDetailsPage.isMonthsExists()) {
			String actualAvailability = "";
			for(int i = 0; i < expectedDates.size(); i ++) {
				slipDetailsPage.setArrivalDate(expectedDates.get(i));
				ajax.waitLoading();
				slipDetailsPage.waitLoading();
				
				actualAvailability = slipDetailsPage.getAvailability();
				result &= MiscFunctions.compareResult(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE + " Slip Availability status of Arrival Date " + expectedDates.get(i), expectedStatus, actualAvailability);
			}
		}
		
		if (!result) {
			throw new ActionFailedException(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") doesn't take effect to Slip (CD# = " + slipCode + ") in " + appName + ".");
		} else logger.info(ruleName + " Rule (Rule Cond.ID = " + ruleCondID + ") take effect to Slip (CD# = " + slipCode + ") in " + appName + ".");
	}
	
	/**
	 * Method verifyBusinessRuleEffectiveAtOrderCartPage - verify whether the
	 * business rule validation message displayed at the Order Cart page
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param productNumber
	 * @param pay
	 * @param isNegative
	 * @return
	 */
	public String verifyBusinessRuleEffectiveAtOrderCartPage(String ruleName,
			String ruleCondID, String productNumber, Payment pay,
			boolean isNegative) {
		OrmsOrderCartPage ormsOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsOrderSummaryPage ormsOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsProcessOrderCartPopupPage ormsOrderCartPopup = OrmsProcessOrderCartPopupPage
				.getInstance();
		OrmsEnterPinNumPopupPage ormsRulePopup = OrmsEnterPinNumPopupPage
				.getInstance();
		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
		OpMgrHomePage omHmPg = OpMgrHomePage.getInstance();
		VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
		OrmsPrintPopupPage ormsPrintPopupPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
//		FldMgrHomePage fmHmPg = FldMgrHomePage.getInstance();
//		PermitMgrHomePage pmHmPg = PermitMgrHomePage.getInstance();
//		MrnMgrHomePage mmHmPg = MrnMgrHomePage.getInstance();
		
		Object page = null;
		String fullAppName = this.getClass().getName();
		String applicationName = fullAppName.substring(fullAppName
				.lastIndexOf(".") + 1);

		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + applicationName + ".");

		if (ruleName.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}
		if (ruleCondID.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}

		// if the rule validation message displayed at the order cart page, get
		// this message and then verify the rule
		// String statusMsg = ormsOrdCartPg.getErrorMessage();
		String statusMsg = "";

		// if (null == statusMsg || statusMsg.length() == 0) {
		ormsOrdCartPg.waitLoading();
		logger.info("----Process order cart.");
		if (pay != null) {
			if (applicationName.equalsIgnoreCase("VenueManager")) {
				ormsOrdCartPg.setupAutoPrintTickets(pay);
			}
			ormsOrdCartPg.setupPayment(pay);
		}
		ormsOrdCartPg.clickProcessOrder();

		if (applicationName.equalsIgnoreCase("FieldManager")) {
			alert.setDismissible(true);
			alert.setBeforePageLoading(false);
			ormsPrintPopupPg.setBeforePageLoading(false);
		}

		page = browser.waitExists(Browser.LONG_SLEEP, ormsOrderCartPopup,
				ormsRulePopup, ormsOrdCartPg, ormsOrdSumPg, ormsPrintPopupPg);
		if (page == ormsOrderCartPopup) {// for FieldManager
			ormsOrderCartPopup.setupInformation(pay.pin, pay.issueCash,
					pay.issueGiftCard, pay.giftCardNum);
			ormsOrderCartPopup.clickOK();
			page = browser.waitExists(ormsOrdCartPg, ormsOrdSumPg,
					ormsRulePopup, ormsPrintPopupPg);
			alert.resetDefault();
		}
		if (page == ormsRulePopup) {// for OperationManager/FieldManager?
			System.out
					.println("enter pin page pop up at order cart page before get error message");
			ormsRulePopup.clickCancel();
			ajax.waitLoading();
			page = browser.waitExists(ormsOrdCartPg, ormsOrdSumPg,
					ormsPrintPopupPg);
		}
		if (page == ormsOrdCartPg) {
			if (isNegative) {
				throw new ErrorOnPageException(
						"It should be Order Summary Page.");
			}
			// if the rule validation message displayed at the order cart page,
			// get this message and then verify the rule
			statusMsg = ormsOrdCartPg.getErrorMessage();
			if (statusMsg == null || statusMsg.length() == 0
					|| !statusMsg.contains(ruleCondID)
					|| !statusMsg.contains(productNumber)) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to procut (Product# = "
						+ productNumber + ") in sales channel - "
						+ applicationName + ".");
			}

			if (ormsRulePopup.exists()) {
				System.out
						.println("enter pin page pop up at order cart page after get error message");
				ormsRulePopup.clickCancel();
				ormsOrdCartPg.waitLoading();
			}
		} else {
			if (page == ormsPrintPopupPg) {
				ormsPrintPopupPg.close();
				ormsOrdSumPg.waitLoading();
			}
			
			if (!isNegative) {
				throw new ActionFailedException("The " + ruleName
						+ " Rule (Rule Cond.ID = " + ruleCondID
						+ ") doesn't take effect to prodcut (Product# = "
						+ productNumber + ") in sales channel - "
						+ applicationName + ".");
			}
			logger.info("----As negatively, the " + ruleName
					+ " Rule (Rule Cond.ID = " + ruleCondID
					+ ") applies correctly to sales channel - "
					+ applicationName + ".");
			// get the resID
			statusMsg = ormsOrdSumPg.getAllOrdNums();
			ormsOrdSumPg.clickFinishCall();
			
//			browser.waitExists(cmHmPg, omHmPg, fmHmPg, pmHmPg, vmHmPg, mmHmPg);
			browser.waitExists(cmHmPg, omHmPg, vmHmPg);
		}

		return statusMsg;
	}

	/**
	 * Verify the available status of permit at the arrival date
	 * 
	 * @param ruleName
	 * @param ruleCondID
	 * @param arrivalDate
	 * @param expectedStatus
	 */
//	public void verifyBusinessRuleEffectiveAtPermitAvailabilityPage(
//			String ruleName, String ruleCondID, String arrivalDate,
//			String expectedStatus) {
//		OrmsPermitAvailabilityPage permitAvailPage = OrmsPermitAvailabilityPage
//				.getInstance();
//
//		String fullClassName = this.getClass().getName();
//		String className = fullClassName.substring(fullClassName
//				.lastIndexOf(".") + 1);
//		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
//				+ ruleCondID + ") take effect in " + className + ".");
//
//		String entranceNumber = permitAvailPage.getEntranceName();
//		if (ruleCondID.length() == 0) {
//			throw new ActionFailedException(
//					"Please set value to the parameter - ruleCondID");
//		}
//		if (ruleName.length() == 0) {
//			throw new ActionFailedException(
//					"Please set value to the parameter - ruleName");
//		}
//
//		boolean permitStatus = true;
//		if (expectedStatus.toLowerCase().startsWith("avail")) {
//			permitStatus = permitAvailPage.checkAvail(arrivalDate);
//		} else if (expectedStatus.toLowerCase().startsWith("unavail")) {
//			permitStatus = permitAvailPage.checkUnavail(arrivalDate);
//		}
//
//		if (!permitStatus) {
//			throw new ActionFailedException("The " + ruleName
//					+ " Rule (Rule Cond.ID = " + ruleCondID
//					+ ") doesn't take effect to Permit (Entrance Name = "
//					+ entranceNumber + ") in " + className + ".");
//		}
//
//		logger.info("----The " + ruleName + " Rule (Rule Cond.ID = "
//				+ ruleCondID + ") take effect in " + className
//				+ " successfully.");
//	}

	/**
	 * Go to permit availability page and search permit
	 * 
	 * @param permit
	 */
//	public void gotoPermitAvailabilityPage(PermitInfo permit) {
//		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
//		OrmsPermitAvailabilityPage ormsAvailablePg = OrmsPermitAvailabilityPage
//				.getInstance();
//		OpMgrTopMenuPage omTopMenuPg = OpMgrTopMenuPage.getInstance();
//		PermitMgrHomePage pmHmPg = PermitMgrHomePage.getInstance();
//
//		logger.info("Go to permit availability page.");
//
//		if (this instanceof CallManager) {
//			cmHmPg.clickPermitCall();
//		} else if (this instanceof OperationManager) {
//			omTopMenuPg.clickPermit();
//		} else if (this instanceof PermitManager) {
//			pmHmPg.clickNewPermitReservation();
//		}
//
//		ormsAvailablePg.waitLoading();
//		ormsAvailablePg.searchInventory(permit);
//		ormsAvailablePg.waitLoading();
//	}

	/**
	 * Method execute the flow to ticket available page from home page.
	 * 
	 * @param ticket
	 */
//	public void gotoTicketAvailabilityPage(TicketInfo ticket) {
//		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
//		OrmsTicketAvailabilityPage ormsTicketAvailSchPg = OrmsTicketAvailabilityPage
//				.getInstance();
//		CallMgrTicketSearchPage cmTicketSearchPg = CallMgrTicketSearchPage
//				.getInstance();
//		OpMgrTopMenuPage omTopMenuPg = OpMgrTopMenuPage.getInstance();
//		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
//
//		logger.info("Go to ticket availability page.");
//
//		if (this instanceof CallManager) {
//			// ((CallManager)this).searchTicket(ticket);
//			cmHmPg.clickTicketingCall();
//			cmTicketSearchPg.waitLoading();
//			cmTicketSearchPg.searchTicket(ticket);
//			cmTicketSearchPg.clickCheckAvailability();
//		} else if (this instanceof OperationManager) {
//			omTopMenuPg.clickTickets();
//			cmTicketSearchPg.waitLoading();
//			cmTicketSearchPg.searchTicket(ticket);
//			cmTicketSearchPg.clickCheckAvailability();
//		} else if (this instanceof VenueManager) {
//			vmTopMenuPg.clickSales();
//			ormsTicketAvailSchPg.waitLoading();
//			ormsTicketAvailSchPg.searchAvailibility(ticket);
//		}
//
//		ormsTicketAvailSchPg.waitLoading();
//	}

	/**
	 * Verify the available status of ticket at the arrival date
	 * 
	 * @param ruleCondID
	 * @param ruleName
	 * @param arrivalDate
	 * @param expectedStatus
	 */
	public void verifyBusinessRuleEffectiveAtTicketAvailabilityPage(
			String ruleCondID, String ruleName, String tourName,
			String arrivalDate, String expectedStatus) {
		OrmsTicketAvailabilityPage ormsTicketAvailSchPg = OrmsTicketAvailabilityPage
				.getInstance();

		String fullClassName = this.getClass().getName();
		String className = fullClassName.substring(fullClassName
				.lastIndexOf(".") + 1);

		logger.info("Verify whether the " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + className + ".");
		if (ruleCondID.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleCondID");
		}
		if (ruleName.length() == 0) {
			throw new ActionFailedException(
					"Please set value to the parameter - ruleName");
		}

		boolean ticketStatus = true;
		if (expectedStatus.toLowerCase().startsWith("avail")) {
			ticketStatus = ormsTicketAvailSchPg.checkAvail(tourName,
					arrivalDate);
		} else if (expectedStatus.toLowerCase().startsWith("unavail")) {
			ticketStatus = ormsTicketAvailSchPg.checkUnavail(tourName,
					arrivalDate);
		}

		if (!ticketStatus) {
			throw new ActionFailedException("The " + ruleName
					+ " Rule (Rule Cond.ID = " + ruleCondID
					+ ") doesn't take effect to Ticket (Tour Name = "
					+ tourName + ") in " + className + ".");
		}

		logger.info("----The " + ruleName + " Rule (Rule Cond.ID = "
				+ ruleCondID + ") take effect in " + className
				+ " successfully.");
	}

	/**
	 * Update new date in site detail page
	 * 
	 * @param newArrivalDate
	 * @param dayNightNum
	 */
	public void updateDateInSiteDetailpg(String newArrivalDate,
			String dayNightNum) {
		OrmsSiteDetailsPage ormsSiteDetailPg = OrmsSiteDetailsPage
				.getInstance();
		logger.info("Update date in site detail page.");

		ormsSiteDetailPg.setUpdateDate(newArrivalDate);
		ormsSiteDetailPg.clickUpdateDisplay();

		ormsSiteDetailPg.waitLoading();

		ormsSiteDetailPg.setArrivalDate(newArrivalDate);
		ormsSiteDetailPg.setNightNum(dayNightNum);

		ormsSiteDetailPg.clickNightsLable();
	}

//	public void gotoUpdateCustomerPg(Customer customer){
//		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
//		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
////		CustomerSearch csearch = CustomerSearch.getInstance();
//		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
//				.getInstance();
//		OpMgrTopMenuPage omTopMenuPg = OpMgrTopMenuPage.getInstance();
//		FldMgrTopMenuPage fmTopMenuPg = FldMgrTopMenuPage.getInstance();
//		PermitMgrTopMenuPage pmTopMenuPg = PermitMgrTopMenuPage.getInstance();
//		VnuMgrTopMenuPage vmTopMenuPg = VnuMgrTopMenuPage.getInstance();
//		MrnMgrTopMenuPage mmTopMenuPg = MrnMgrTopMenuPage.getInstance();
//
//		
//		if (this instanceof CallManager) {
//			if (cmHmPg.exists()) {
//				cmHmPg.clickCampingCall();
//			}
//			cmTopMenuPg.waitLoading();
//			cmTopMenuPg.searchCustomers();
//		} else if (this instanceof OperationManager) {
//			omTopMenuPg.waitLoading();
//			omTopMenuPg.searchCustomers();
//		} else if (this instanceof FieldManager) {
//			fmTopMenuPg.waitLoading();
//			fmTopMenuPg.gotoCustomer();
//		} else if (this instanceof PermitManager) {
//			pmTopMenuPg.waitLoading();
//			pmTopMenuPg.selectSearchDropDown("Customers");
//		} else if (this instanceof VenueManager) {
//			vmTopMenuPg.waitLoading();
//			vmTopMenuPg.selectSearchDropDown("Customers");
//		} else if(this instanceof MarinaManager) {
//			mmTopMenuPg.waitLoading();
//			mmTopMenuPg.selectSearchDropdownList("Customers");
//		}
//
//		omCustSchPg.waitLoading();
//
//		omCustSchPg.searchCust(customer);
//		omCustSchPg.waitLoading();
////		csearch.verifyCustomerInCustomerSearchPg(customer);// verify customer
//
//		customer.lName = omCustSchPg.getCustLNameByEmail(customer.email);
//	}
	/**
	 * Method executed work flow of updating a existing customer detail.
	 * 
	 * @param customer
	 */
	public void updateCustomer(Customer customer) {
		CustomerSearch csearch = CustomerSearch.getInstance();
		logger.info("Update customer(Email=" + customer.email + ").");
		
//		this.gotoUpdateCustomerPg(customer);
		csearch.updateCustomerInfo(customer);
	}

	/**
	 * This method used to apply given discount to POS from order cart page
	 * 
	 * @param discountName
	 */
	public void applyDiscountInCart(String discountName) {
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		OrmsSelectDiscountPopupPage popupPg = OrmsSelectDiscountPopupPage
				.getInstance();

		logger.info("Apply Discount To Pos.");
		
		cartPg.clickApplyDiscount();
		popupPg.waitLoading();
		popupPg.applyDiscount(discountName);
		cartPg.waitLoading();
	}
	
	/**
	 * This method execute the process of apply discount manually the work flow
	 * start from order cart page and ends in order cart page
	 *
	 * @param index
	 *
	 * @param - accountFee the fee of the discount
	 * @param -- order the name of the Order (eg:"New-1")
	 */
	public void applyManualDiscount(DiscountSchdInfo discount, String order,
			int index) {
		this.applyManualDiscount(discount, order, index,false);
	}
	
	public void applyManualDiscount(DiscountSchdInfo discount, String order,
			int index,boolean isPermitOrSlip){
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsDiscountPage fmDiscountPg = OrmsDiscountPage.getInstance();

		fmOrdCartPg.applyDiscount(order,isPermitOrSlip);

		fmDiscountPg.waitLoading();
		fmDiscountPg.setupBasicDiscount(discount, index);
		fmDiscountPg.clickOK();
		ajax.waitLoading();
		fmOrdCartPg.waitLoading();
	}

	public void cancelFromDiscountPage() {
		OrmsDiscountPage discountPage = OrmsDiscountPage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Cancel from Select Discount page to order cart.");
		discountPage.clickCancel();
		ajax.waitLoading();
		cartPage.waitLoading();
	}
	
	/**
	 * This method executes the work flow of going to fee detail page
	 */
	public void gotoFeeDetailPageInPosDetail() {
		OrmsPOSDetailsPage fmResvDetailPg = OrmsPOSDetailsPage.getInstance();
		OrmsFeeDetailsPage fmFeeDetailPg = OrmsFeeDetailsPage.getInstance();

		fmResvDetailPg.clickFees();
		fmFeeDetailPg.waitLoading();
	}

	/**
	 * This method executes the work flow of going to fee detail page
	 */
	public void gotoFeeDetailPageInRes() {
		OrmsReservationDetailsPage fmResvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsFeeDetailsPage fmFeeDetailPg = OrmsFeeDetailsPage.getInstance();
		
		fmResvDetailPg.clickFees();
		ajax.waitLoading();
		fmFeeDetailPg.waitLoading();
	}
	
	public void gotoResFeeDetailPageInRes() {
		OrmsReservationDetailsPage fmResvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsReservationFeesPage resFeeDetailsPg = OrmsReservationFeesPage.getInstance();
		
		fmResvDetailPg.clickFees();
		ajax.waitLoading();
		resFeeDetailsPg.waitLoading();
	}
	
	/**
	 * This work flow star from fee detail page, end to ticket order cart page.
	 */
	public void gotoTicketOrderCartPgFromFeeDetailPg() {
		OrmsTicketFeeDetailsPage feeDetailPage = OrmsTicketFeeDetailsPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Got to ticket order cart page from fee detail page");
		feeDetailPage.clickOK();
		ordCartPg.waitLoading();
	}

	/**
	 * This work flow star from fee detail page, end to ticket order detail
	 * page.
	 */
	public void gotoTicketOrderDetailPgFromFeeDetailPg() {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsFeeDetailsPage vmFeeDetailPg = OrmsFeeDetailsPage.getInstance();

		logger.info("Got to ticket order detail page from fee detail page");
		vmFeeDetailPg.clickOK();
		vmTicketDetailPg.waitLoading();
	}
	
	/**
	 * The work flow start from fee detail page, end to lottery application detail page
	 */
	public void gotoLotteryApplicationDetailPageFromFeeDetailPg(){
		OrmsLotteryApplicationDetailsPage lotteryDetailPg = OrmsLotteryApplicationDetailsPage
				.getInstance();
		OrmsFeeDetailsPage vmFeeDetailPg = OrmsFeeDetailsPage.getInstance();

		logger.info("Got to ticket order detail page from fee detail page");
		vmFeeDetailPg.clickOK();
		lotteryDetailPg.waitLoading();
	}

	/**
	 * The method used to switch to another contract in same sales channel
	 * 
	 * @param targetContract
	 * @param location
	 */
	public void switchToContract(String targetContract, String location) {
		OrmsLoginPage loginPg = OrmsLoginPage.getInstance();
		OrmsPage homePage = null;

		logger.info("Switch To " + targetContract);

		if (this instanceof AdminManager) {
			AdmTopMenuPage.getInstance().clickSwitch();
			homePage = AdmMgrHomePage.getInstance();
			loginPg.waitLoading();
			loginPg.logIn(targetContract, location);
			homePage.waitLoading();
		} else if (this instanceof InventoryManager) {
			InvMgrTopMenuPage.getInstance().clickSwitch();
			homePage = InvMgrHomePage.getInstance();
			InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage
					.getInstance();
			InvMgrWarehouseDetailsPage warhouseDetailsPg = InvMgrWarehouseDetailsPage
					.getInstance();
			loginPg.waitLoading();
			loginPg.logIn(targetContract, location);
			browser.waitExists(homePage,inFacilityDetailPg,warhouseDetailsPg);
		} else if (this instanceof FinanceManager) {
			FinMgrTopMenuPage.getInstance().clickSwitch();
			homePage = FinMgrHomePage.getInstance();
			loginPg.waitLoading();
			loginPg.logIn(targetContract, location);
			homePage.waitLoading();
		} else {
			throw new NotImplementedException(
					"Unknown application,need to be Implemented.");
		}

	}

	public void changeTimeToTicketSelectionPage(TicketInfo ticket,
			boolean isComboTour) {
		OrmsTicketOrderDetailsPage cmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketAvailabilityPage cmChangeTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsTicketSelectionPage cmTransferSelectionPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();

		logger.info("Change ticket time to ticket selection page.");
		cmTicketDetailPg.clickChangeTicketTime();
		cmChangeTicketsAvailPg.waitLoading();
		cmChangeTicketsAvailPg.searchAvailibilitybyDate(ticket.startDate);
		cmChangeTicketsAvailPg.waitLoading();
		if (isComboTour) {
			cmChangeTicketsAvailPg.selectComboTourTicket(ticket.comboTourName,
					ticket.comboChildTours);
		} else {
			cmChangeTicketsAvailPg.selectTicket(ticket.timeSlot,
					ticket.quantity);
		}
		cmChangeTicketsAvailPg.clickChangeTicketTime();

		Object page = browser.waitExists(cmTransferSelectionPg, ormsAlertPg);
		if (page == ormsAlertPg) {
			ormsAlertPg.clickOK();
		}
		cmTransferSelectionPg.waitLoading();
	}
	
	public void cancelFromTicketSelectionDuringCHnageTime(){
		OrmsTicketOrderDetailsPage cmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketSelectionPage cmTransferSelectionPg = OrmsTicketSelectionPage
				.getInstance();
		logger.info("Change ticket time to ticket availability page.");
		cmTransferSelectionPg.clickCancel();
		cmTicketDetailPg.waitLoading();
	}
	
	public void changeTimeToTicketAvailabilityPg(){
		OrmsTicketOrderDetailsPage cmTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketAvailabilityPage cmChangeTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		logger.info("Change ticket time to ticket availability page.");
		cmTicketDetailPg.clickChangeTicketTime();
		cmChangeTicketsAvailPg.waitLoading();
	}
	
	public void changeTimeForAvaiToSelectionPg(String startDate,
			String timeSlot, String ticketNum, boolean isUpdateTime){
		OrmsTicketAvailabilityPage cmChangeTicketsAvailPg = OrmsTicketAvailabilityPage
				.getInstance();
		OrmsTicketSelectionPage cmTransferSelectionPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		cmChangeTicketsAvailPg.changeTicketTime(startDate, timeSlot, ticketNum,
				isUpdateTime);
		cmChangeTicketsAvailPg.clickChangeTicketTime();

		Object page = browser.waitExists(cmTransferSelectionPg, ormsAlertPg);
		if (page == ormsAlertPg) {
			ormsAlertPg.clickOK();
		}
		cmTransferSelectionPg.waitLoading();
	}

	public void changeTimeToTicketSelectionPage(String startDate,
			String timeSlot, String ticketNum, boolean isUpdateTime) {
		logger.info("Change ticket time to ticket selection page.");
		changeTimeToTicketAvailabilityPg();
		changeTimeForAvaiToSelectionPg(startDate, timeSlot, ticketNum, isUpdateTime);
	}

	public void changeTimeInTicketSelectionPage(String fromTicketTypes[],
			String toTicketTypes[], String changeQuanties[]) {
		OrmsTicketSelectionPage cmTransferSelectionPg = OrmsTicketSelectionPage
				.getInstance();
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget
				.getInstance();
		OrmsOrderCartPage cmOrderCartPg = OrmsOrderCartPage.getInstance();

		if ((null != toTicketTypes && toTicketTypes.length > 0)
				&& (changeQuanties != null && changeQuanties.length > 0)) {
			cmTransferSelectionPg.setTicketInfo(fromTicketTypes, toTicketTypes,
					changeQuanties);
		}
		cmTransferSelectionPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(tpaWidget, ormsAlertPg, cmOrderCartPg);
	}

	public void changeTimeInTPAWidget(String tourName, String toDate,
			String toTime,
			Map<String, List<Map<String, String>>> wantToRemoveTPA,
			Map<String, List<Map<String, String>>> perTicketTPA,
			Map<String, String> perInventoryTPA, boolean isCompletelyChange) {
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget
				.getInstance();
		OrmsOrderCartPage cmOrderCartPg = OrmsOrderCartPage.getInstance();

		if (wantToRemoveTPA != null && wantToRemoveTPA.size() > 0) {// remove old
			// tickets
			// TPA
			tpaWidget.removeTPAInfoOfPerTicket(wantToRemoveTPA, true);
		}
		// if is completely change, system will update existing TPA; if not,
		// system will only set the blank TPA fields
		tpaWidget.addTourParticipantAttributesForPerTicketWhenChangingTime(
				tourName, toDate, toTime, perTicketTPA, !isCompletelyChange);// update
		// existing
		// TPA
		// set 'Per Inventory' TPA
		if (perInventoryTPA != null && perInventoryTPA.size() > 0) {
			tpaWidget.setTourParticipantAttributesForPerInventory(tourName,
					perInventoryTPA);
		}
		tpaWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(ormsAlertPg, cmOrderCartPg);
		if (page == ormsAlertPg) {
			ormsAlertPg.clickOK();
		}
		cmOrderCartPg.waitLoading();
	}

	public void changeTimeToCart(TicketInfo ticket, String fromTypes[],
			String toTypes[], String changeQuanties[]) {
		changeTimeToCart(ticket, fromTypes, toTypes, changeQuanties, false);
	}

	public void changeTimeToCart(TicketInfo ticket, String fromTypes[],
			String toTypes[], String changeQuanties[], boolean isComboTour) {
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget
				.getInstance();
		OrmsOrderCartPage cmOrderCartPg = OrmsOrderCartPage.getInstance();

		changeTimeToTicketSelectionPage(ticket, isComboTour);
		changeTimeInTicketSelectionPage(fromTypes, toTypes, changeQuanties);

		Object page = browser.waitExists(tpaWidget, ormsAlertPg, cmOrderCartPg);
		if (page == tpaWidget) {
			int originalQuantity = 0;
			for (int i = 0; i < ticket.typeNums.length; i++) {
				originalQuantity += Integer.parseInt(ticket.typeNums[i]);
			}
			int wantToChangeQuantity = 0;
			for (int i = 0; i < changeQuanties.length; i++) {
				wantToChangeQuantity += Integer.parseInt(changeQuanties[i]);
			}
			boolean isCompletelyChange = (originalQuantity == wantToChangeQuantity) ? true
					: false;
			changeTimeInTPAWidget(ticket.tour, ticket.startDate,
					ticket.timeSlot,
					ticket.removedTourParticipantAttributesForPerTicket,
					ticket.addedTourParticipantAttributesForPerTicket,
					ticket.tourParticipantAttributesForPerInventory,
					isCompletelyChange);
		}
		cmOrderCartPg.waitLoading();
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
	 */
	public void changeTimeToCart(String startDate, String timeSlot,
			String ticketNum, boolean isTimeNotSpecified,
			String fromTicketTypes[], String toTicketTypes[],
			String changeQuanties[], String tourName,
			Map<String, List<Map<String, String>>> wantToRemoveTPA,
			Map<String, List<Map<String, String>>> addedTPA,
			boolean isCompletelyChange) {
		OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		OrmsTourParticipantsWidget tpaWidget = OrmsTourParticipantsWidget
				.getInstance();
		OrmsOrderCartPage cmOrderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Change ticket time to Date="
				+ (startDate.length() > 0 ? startDate : "unknown") + ", Time="
				+ (timeSlot.length() > 0 ? timeSlot : "unknown") + ", number="
				+ ticketNum + " into order cart.");

		changeTimeToTicketSelectionPage(startDate, timeSlot, ticketNum,
				isTimeNotSpecified);
		changeTimeInTicketSelectionPage(fromTicketTypes, toTicketTypes,
				changeQuanties);

		Object page = browser.waitExists(tpaWidget, ormsAlertPg, cmOrderCartPg);
		if (page == tpaWidget) {
			changeTimeInTPAWidget(tourName, startDate, timeSlot,
					wantToRemoveTPA, addedTPA, null, isCompletelyChange);
		}
		cmOrderCartPg.waitLoading();
		// OrmsOrderCartPage cmOrderCartPg = OrmsOrderCartPage.getInstance();
		// OrmsTicketOrderDetailsPage cmTicketDetailPg =
		// OrmsTicketOrderDetailsPage
		// .getInstance();
		// OrmsTicketAvailabilityPage cmChangeTicketsAvailPg =
		// OrmsTicketAvailabilityPage
		// .getInstance();
		// OrmsTicketSelectionPage cmTransferSelectionPg =
		// OrmsTicketSelectionPage
		// .getInstance();
		// OrmsAlertPage ormsAlertPg = OrmsAlertPage.getInstance();
		// OrmsTourParticipantsWidget tpaWidget =
		// OrmsTourParticipantsWidget.getInstance();
		//
		// logger.info("Change ticket time to start="
		// + (startDate.length() > 0 ? startDate : "unknown") + " time="
		// + (timeSlot.length() > 0 ? timeSlot : "unknown") + " number="
		// + ticketNum + " into order cart.");
		//
		// cmTicketDetailPg.clickChangeTicketTime();
		//
		// cmChangeTicketsAvailPg.waitExists();
		// cmChangeTicketsAvailPg.changeTicketTime(startDate, timeSlot,
		// ticketNum,
		// isTimeNotSpecified);
		// cmChangeTicketsAvailPg.clickChangeTicketTime();
		//
		// Object page = browser.waitExists(cmTransferSelectionPg,ormsAlertPg);
		// if(page == ormsAlertPg){
		// ormsAlertPg.clickOK();
		// cmTransferSelectionPg.waitExists();
		// }
		//
		// if((null != toTicketTypes && toTicketTypes.length > 0) ||
		// (changeQuanties != null && changeQuanties.length > 0)) {
		// // cmTransferSelectionPg.selectToTicketType(feeType);
		// // cmTransferSelectionPg.setTicketQuantity("1");
		// cmTransferSelectionPg.setTicketInfo(fromTicketTypes, toTicketTypes,
		// changeQuanties);
		// }
		// cmTransferSelectionPg.clickOK();
		// ajax.waitLoading();
		// page = browser.waitExists(tpaWidget, ormsAlertPg, cmOrderCartPg);
		// if(page == tpaWidget) {
		// if(wantToRemoveTPA != null & wantToRemoveTPA.size() > 0) {
		// tpaWidget.removeTPAInfoOfPerTicket(wantToRemoveTPA);
		// }
		// tpaWidget.addTourParticipantAttributesForPerTicket(tourName,
		// addedTPA);
		// page = browser.waitExists(ormsAlertPg, cmOrderCartPg);
		// }
		// if (ormsAlertPg.exists()) {
		// ormsAlertPg.clickOK();
		// cmOrderCartPg.waitExists();
		// }
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
		changeTimeToCart(startDate, timeSlot, ticketNum, isTimeNotSpecified,
				null, null, null, null, null, null, false);
	}

	// time slot is specified when update the ticket order
	public void changeTimeToCart(String startDate, String timeSlot,
			String ticketNum) {
		changeTimeToCart(startDate, timeSlot, ticketNum, false);
	}

	public void changeTimeToCart(String startDate, String timeSlot,
			String fromType, String toType, String changeQuantity) {
		changeTimeToCart(startDate, timeSlot, null, false,
				new String[] { fromType }, new String[] { toType },
				new String[] { changeQuantity }, null, null, null, false);
	}

	/**
	 * Check and cancel the permit order identified by facility name and
	 * organization name
	 * 
	 * @param facilityName
	 * @param organizationName
	 */
//	public void checkAndCancelPermitOrders(String facilityName,
//			String organizationName) {
//		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
//		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
//		OpMgrTopMenuPage omTopMenuPg = OpMgrTopMenuPage.getInstance();
//		FldMgrTopMenuPage fmTopMenuPg = FldMgrTopMenuPage.getInstance();
//		OrmsPermitOrderSearchPage permitOrdSearchPg = OrmsPermitOrderSearchPage
//				.getInstance();
//
//		logger.info("Clean up permit orders.");
//
//		if (this instanceof CallManager) {
//			if (!permitOrdSearchPg.exists()) {
//				if (cmHmPg.exists()) {
//					cmHmPg.clickPermitCall();
//				}
//				cmTopMenuPg.waitLoading();
//				cmTopMenuPg.selectSearchDropDown("Permit Orders");
//			}
//		} else if (this instanceof OperationManager) {
//			if (!permitOrdSearchPg.exists()) {
//				omTopMenuPg.waitLoading();
//				omTopMenuPg.selectSearchDropDown("Permit Orders");
//			}
//		} else if (this instanceof PermitManager) {
//			if (!permitOrdSearchPg.exists()) {
//				fmTopMenuPg.waitLoading();
//				fmTopMenuPg.selectSearchDropdownList("Permit Orders");
//			}
//		}
//
//		permitOrdSearchPg.waitLoading();
//		permitOrdSearchPg.setLName(TestProperty.getProperty("app.cust.lname"));
//		permitOrdSearchPg.setFName(TestProperty.getProperty("app.cust.fname"));
//		if (this instanceof CallManager) {
//			permitOrdSearchPg.selectFacilityName(facilityName);
//		}
//		permitOrdSearchPg.setOrganizaion(organizationName);
//		permitOrdSearchPg
//				.setFromEntryDate(DateFunctions.getDateAfterToday(-20));
//		permitOrdSearchPg.setToEntryDate(DateFunctions.getDateAfterToday(10));
//		permitOrdSearchPg.clickGo();
//		ajax.waitLoading();
//		permitOrdSearchPg.waitLoading();
//
//		if (permitOrdSearchPg.isResultEmpty()) {
//			logger.info("There is not eligible permit order found need to be cancelled.");
//			if (this instanceof CallManager) {
//				((CallManager) this).cancelCall();
//			}
//		} else {
//			List<String> permitOrdNums = new ArrayList<String>();
//			do {
//				permitOrdNums.addAll(permitOrdSearchPg
//						.getAllPermitOrerNumbers());
//			} while (permitOrdSearchPg.gotoNext());
//
//			StringBuffer sb = new StringBuffer();
//			for (int i = 0; i < permitOrdNums.size(); i++) {
//				sb.append(permitOrdNums.get(i) + " ");
//			}
//
//			logger.info("Need to cancel these below permit orders: "
//					+ sb.toString());
//
//			String cancelReason = "Customer Cancellation";
//			String cancelNote = "QA Regression Automation Testing";
//			if (this instanceof CallManager) {
//				((CallManager) this).cancelPermitOrders(cancelReason,
//						cancelNote, permitOrdNums.toArray(new String[0]));
//			} else if (this instanceof OperationManager) {
//				((OperationManager) this).cancelPermitOrders(cancelReason,
//						cancelNote, permitOrdNums.toArray(new String[0]));
//			} else if (this instanceof PermitManager) {
//				((PermitManager) this).cancelPermitOrders(cancelReason,
//						cancelNote, permitOrdNums.toArray(new String[0]));
//			}
//		}
//	}

	/**
	 * 
	 * @param permit
	 */
//	public void checkAndCancelPermitOrders(PermitInfo permit) {
//		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
//		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
//		OpMgrTopMenuPage omTopMenuPg = OpMgrTopMenuPage.getInstance();
////		FldMgrTopMenuPage fmTopMenuPg = FldMgrTopMenuPage.getInstance();
////		OrmsPermitOrderSearchPage permitOrdSearchPg = OrmsPermitOrderSearchPage
//				.getInstance();
//
//		logger.info("Clean up permit orders.");
//
//		if (this instanceof CallManager) {
//			if (!permitOrdSearchPg.exists()) {
//				if (cmHmPg.exists()) {
//					cmHmPg.clickPermitCall();
//				}
//				cmTopMenuPg.waitLoading();
//				cmTopMenuPg.selectSearchDropDown("Permit Orders");
//			}
//		} else if (this instanceof OperationManager) {
//			if (!permitOrdSearchPg.exists()) {
//				omTopMenuPg.waitLoading();
//				omTopMenuPg.selectSearchDropDown("Permit Orders");
//			}
//		} else if (this instanceof PermitManager) {
//			if (!permitOrdSearchPg.exists()) {
//				fmTopMenuPg.waitLoading();
//				fmTopMenuPg.selectSearchDropdownList("Permit Orders");
//			}
//		}
//
//		permitOrdSearchPg.waitLoading();
//		permitOrdSearchPg.setupSearchCriteria(permit);
//		permitOrdSearchPg.clickGo();
//		ajax.waitLoading();
//		permitOrdSearchPg.waitLoading();
//
//		if (permitOrdSearchPg.isResultEmpty()) {
//			logger.info("There is not eligible permit order found need to be cancelled.");
//			if (this instanceof CallManager) {
//				((CallManager) this).cancelCall();
//			}
//		} else {
//			List<String> permitOrdNums = new ArrayList<String>();
//			do {
//				permitOrdNums.addAll(permitOrdSearchPg
//						.getAllPermitOrerNumbers());
//			} while (permitOrdSearchPg.gotoNext());
//
//			StringBuffer sb = new StringBuffer();
//			for (int i = 0; i < permitOrdNums.size(); i++) {
//				sb.append(permitOrdNums.get(i) + " ");
//			}
//
//			logger.info("Need to cancel these below permit orders: "
//					+ sb.toString());
//
//			String cancelReason = "Customer Cancellation";
//			String cancelNote = "QA Regression Automation Testing";
//
//			if (this instanceof CallManager) {
//				for (int i = 0; i < permitOrdNums.size(); i++) {
//					((CallManager) this).gotoPermitOrderDetailsPage(permitOrdNums.get(i));
//					((CallManager) this).voidPermitToCart();
//					((CallManager) this).processOrderCart();
//				}
//				
//			} else if (this instanceof OperationManager) {
//				((OperationManager) this).cancelPermitOrders(cancelReason,
//						cancelNote, permitOrdNums.toArray(new String[0]));
//			} else if (this instanceof PermitManager) {
//				((PermitManager) this).cancelPermitOrders(cancelReason,
//						cancelNote, permitOrdNums.toArray(new String[0]));
//			}
//		}
//	}

	/**
	 * 
	 * Goto POS Invoice Summary Page based on given order# and Invoice number,
	 * start from FM/OM/CM home page, end at Invoice Summary Page.
	 * 
	 * @param posID
	 * @param invoiceID
	 */
//	public void gotoPOSInvoiceSummaryPage(String posID) {
//		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
//		OpMgrTopMenuPage omTopMenuPg = OpMgrTopMenuPage.getInstance();
//		FldMgrTopMenuPage fmTopMenuPg = FldMgrTopMenuPage.getInstance();
//		OrmsInvoiceSummaryPage invoiceSumPg = OrmsInvoiceSummaryPage
//				.getInstance();
//		CallMgrHomePage cmHomePg = CallMgrHomePage.getInstance();
//		OrmsSiteSearchPage omSiteSchPg = OrmsSiteSearchPage.getInstance();
//
//		OrmsPOSSaleSearchPage ormsPosSchPg = OrmsPOSSaleSearchPage
//				.getInstance();
//
//		logger.info("Going to POS#" + posID + " Invoice page.");
//
//		if (this instanceof FieldManager) {
//			if (!ormsPosSchPg.exists()) {
//				fmTopMenuPg.waitLoading();
//				fmTopMenuPg.selectSearchDropdownList("POS Sale");
//			}
//		} else if (this instanceof OperationManager) {
//			if (!ormsPosSchPg.exists()) {
//				omTopMenuPg.waitLoading();
//				omTopMenuPg.selectSearchDropDown("POS Sale");
//			}
//		} else if (this instanceof CallManager) {
//			Object page = browser.waitExists(ormsPosSchPg, cmTopMenuPg,
//					cmHomePg);
//			if (page == cmHomePg) {
//				cmHomePg.clickCampingCall();
//				omSiteSchPg.waitLoading();
//			}
//			if (!ormsPosSchPg.exists()) {
//				cmTopMenuPg.waitLoading();
//				cmTopMenuPg.selectSearchDropDown("POS Sale");
//			}
//		}
//
//		ormsPosSchPg.waitLoading();
//		ormsPosSchPg.setOrdNum(posID);
//		ormsPosSchPg.clickGo();
//
//		ormsPosSchPg.waitLoading();
//		String InvoiceNum = ormsPosSchPg.selectInvoiceNumBasedOnPOSNum(posID);
//		logger.info("select Invoice#" + InvoiceNum
//				+ "goto corresponding invoice summary page");
//		invoiceSumPg.waitLoading();
//	}

	public void addPOSToCart(POSInfo pos) {
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsPOSAddItemPage fmPosItemsPg = OrmsPOSAddItemPage.getInstance();
		
		logger.info("Add a POS to cart.");
		fmOrdCartPg.clickAddPOS();
		fmPosItemsPg.waitLoading();
		selectPOSToCart(pos);
		fmOrdCartPg.waitLoading();
	}
	
	/**
	 * The method executes the work flow of charge POS to new reservation. The
	 * work flow starts from and ends at order cart page
	 * 
	 * 
	 * @param pos
	 *            - the POS information.
	 */
	public void chargePOSToNewReservation(POSInfo pos) {
		OrmsOrderCartPage ormsOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsPOSAddItemPage ormsPosItemsPg = OrmsPOSAddItemPage.getInstance();

		logger.info("Charge a POS to new reservation.");

		ormsOrdCartPg.chargeResPOS();
		ormsPosItemsPg.waitLoading();

		logger.info("Select POS " + pos.product + " to cart.");

		ormsPosItemsPg.searchItem(pos.product);
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.selectItem(pos);
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.clickAddToCart();
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.clickGoToCart();

		ormsOrdCartPg.waitLoading();
	}
	
	/**
	 * Charge POS to camping reservation
	 * @param pos
	 * @param resNums
	 */
	public void chargePOSToMultiReservation(POSInfo pos,String...resNums){
		this.chargePOSToMultiReservation(pos, false, resNums);
	}
	
	/**
	 * Charge POS to other non site reservation
	 * @param pos
	 * @param isNotSite
	 * @param resNums
	 */
	public void chargePOSToMultiReservation(POSInfo pos,boolean isNotSite,String...resNums){
		OrmsOrderCartPage ormsOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsPOSAddItemPage ormsPosItemsPg = OrmsPOSAddItemPage.getInstance();
		
		logger.info("Charge POS "+pos.product+" to Reservation#"+StringUtil.arrayToString(resNums));
		
		ormsOrdCartPg.chargePOSToRes(isNotSite,resNums);
		ajax.waitLoading();
		ormsPosItemsPg.waitLoading();
		ormsPosItemsPg.addPosToCart(pos);
		ormsOrdCartPg.waitLoading();
	}
	
	public void chargePOSToPOSAddItemPgFromSlipResDetail() {
		OrmsPOSAddItemPage posItemPg = OrmsPOSAddItemPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
		.getInstance();
		logger.info("Charge POS to POS Add Item page from reservation detail page");
		resDetailsPage.clickChargePOS();
		posItemPg.waitLoading();
	}
	
	/**
	 * Charge POS to reservation from reservation detail page
	 * 
	 * @param pos
	 */
	public void chargePosToCartFromSlipResDetail(POSInfo pos) {
		logger.info("Charge POS To Reservation.");

		chargePOSToPOSAddItemPgFromSlipResDetail();
		selectPOSToCart(pos);
	}

	/**
	 * The method executes the work flow of pay outstanding balance reseravtion
	 * The work flow starts from reservation detail page and ends at order cart
	 * page.
	 * 
	 */
	public void payOutStandingBalanceFromResDetailPg() {
		OrmsReservationDetailsPage resDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsReservationBillingPage resBillingPg = OrmsReservationBillingPage
				.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		resDetailPg.clickInvoiceNum();

		resBillingPg.waitLoading();
		if (!resBillingPg.isMakePaymentAvailable()) {
			throw new ErrorOnDataException(
					"there is no outstanding balance reservation");
		}

		resBillingPg.selectAllCheckBox();
		resBillingPg.clickMakePayment();

		ordCartPg.waitLoading();
	}

	/**
	 * check the new amount owning on Order Summary Page, if the amount is not
	 * equal with the parameter an exception will be thrown.
	 * 
	 * @param amount
	 */
	public void verifyNewAmountOwiningOnOrderSummaryPg(String amount) {
		OrmsOrderSummaryPage omOrdSumPg = OrmsOrderSummaryPage.getInstance();
		String newAmountOwn = omOrdSumPg.getNewAmountOwning();
		if (!newAmountOwn.equals(amount)) {
			throw new ErrorOnDataException(
					"The new outstanding balance amount is incorrect, the expect value should be: "
							+ amount);
		}
	}

	/**
	 * 
	 * @param ticketfeeData
	 * @param feeInfo
	 * @param feeCal
	 * @param schema
	 * @param types
	 * @return
	 */
	public BigDecimal calculateTotalTicketFee(FeeValidationData ticketfeeData,
			FeeScheduleInformation feeInfo, FeeCalculationFunctions feeCal,
			String schema, List<String> types) {

		BigDecimal compareTotalPrice = BigDecimal.ZERO.setScale(2);
		List<BigDecimal> ticketFee = new ArrayList<BigDecimal>();

		// Get Ticket Fee and Transaction Fee from DB
		ticketfeeData = feeInfo.getTicketScheduleInfo(ticketfeeData, schema);
		if (ticketfeeData.units.size() != ticketfeeData.schedules.size()) {
			throw new ErrorOnDataException(
					"ticket fee quality is not correct.please check data set up...");
		}

		// calculate ticket fee
		ticketFee = feeCal.calculateTicketFee(ticketfeeData, types).get(0);
		for (int i = 0; i < ticketFee.size(); i++) {
			compareTotalPrice = compareTotalPrice.add(ticketFee.get(i));
		}

		return compareTotalPrice;
	}
	
	public void gotoReservationSearchPage(Object startPage) {
		OpMgrTopMenuPage omTopMenu = OpMgrTopMenuPage.getInstance();
//		FldMgrTopMenuPage fldMgrTopMenuPage = FldMgrTopMenuPage.getInstance();
		CallMgrCommonTopMenuPage cmTopMenu = CallMgrCommonTopMenuPage
				.getInstance();
		OrmsReservationSearchPage resSchPg = OrmsReservationSearchPage
				.getInstance();
		CallMgrHomePage cmHomePg = CallMgrHomePage.getInstance();
		if (startPage == omTopMenu) {
			omTopMenu.selectSearchDropDown("Reservations");
//		} else if (startPage == fldMgrTopMenuPage) {
//			fldMgrTopMenuPage.selectSearchDropdownList("Site Reservations");
		} else if (startPage == cmTopMenu) {
			cmTopMenu.searchReservations();
		} else if(startPage == cmHomePg){
			cmHomePg.clickCampingCall();
			cmTopMenu.waitLoading();
			cmTopMenu.searchReservations();
		}else {
			throw new ItemNotFoundException("Unknown start page"
					+ ((OrmsPage) startPage).getName());
		}

		resSchPg.waitLoading();
	}

	public void gotoReservationDetailsPage(String res) {
		OrmsReservationSearchPage resSchPg = OrmsReservationSearchPage
				.getInstance();
		OrmsReservationDetailsPage resDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsReservationAlertPage alertsPg = OrmsReservationAlertPage
				.getInstance();

		resSchPg.searchReservation(res);
		resSchPg.searchWaitExists();
		resSchPg.selectReservation(res);

		Object page = browser.waitExists(resDetailPg, alertsPg);

		if (page == alertsPg) {
			alertsPg.clickOK();
			resDetailPg.waitLoading();
		}
	}

	public void cleanResToCart(String[] res_nums,String reason, String note, String saleschannel) {
		OrmsPage topmenu;
		if(saleschannel.equalsIgnoreCase("Operations Manager"))
			topmenu=OpMgrTopMenuPage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Field Manager"))
//			topmenu=FldMgrTopMenuPage.getInstance();
		else
			throw new ItemNotFoundException(saleschannel+" is not expected Operations Manager/Field Manager");
		
		
		gotoReservationSearchPage(topmenu);
		gotoReservationDetailsPage(res_nums[0]);
		
		cleanResFromOrdDetailsToCart(reason, note);

		StringBuffer resNumsBuffer = new StringBuffer();
		resNumsBuffer.append(res_nums[0]);

		for (int i = 1; i < res_nums.length; i++) {
			gotoReservationDetailsFromCart(res_nums[i]);
			cleanResFromOrdDetailsToCart(reason, note);
			resNumsBuffer.append(" " + res_nums[i]);
		}
		
		logger.info("Cancel reservation(s)" + resNumsBuffer.toString() + " to order cart.");
	}
	
	/**
	 * This method executes the work flows of cancel/void a reservation. The
	 * method starts from reservation details page and ends at order cart page.
	 * 
	 * @param reason
	 *            - cancellation reason
	 * @param note
	 *            - note
	 */
	public void cleanResFromOrdDetailsToCart(String reason, String note) {
//		OrmsOrderCartPage fmOrderCardPg = OrmsOrderCartPage.getInstance();
		OrmsReservationDetailsPage fmResvDetailPg = OrmsReservationDetailsPage
				.getInstance();

		logger.info("Clean reservation to Order Cart.");

		if (fmResvDetailPg.cancelable()) {
			cancelResToCart(reason, note);
		} else if (fmResvDetailPg.voidable()) {
			voidResToCartInOrms(note);
		} else {
			String ord_num = fmResvDetailPg.getReservNum();
//			String contract = fmResvDetailPg.getContract();
//			String schema = TestProperty.getProperty(TestProperty
//					.getProperty("target_env") + ".db.schema.prefix")
//					+ contract;
			throw new ItemNotFoundException("Cannot either void or cancel reservation#"
					+ ord_num
					+ ". There is no permission to do it.");

			//James: clean reservation in DB will not reset OCAM automatically so the site is still not available. We should stop to do it.
//			cleanupSlipReservationsInDB(schema, ord_num);

//			fmResvDetailPg.clickCart();
//			fmOrderCardPg.waitExists();
		}
	}
	
	public void cleanSlipResToCart(String[] resNums, String reason, String note, String saleschannel) {
		OrmsPage topmenu;
		if(saleschannel.equalsIgnoreCase("Operations Manager"))
			topmenu=OpMgrTopMenuPage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Field Manager"))
//			topmenu=FldMgrTopMenuPage.getInstance();
//		else if(saleschannel.equalsIgnoreCase("Marina Manager"))
//			topmenu=MrnMgrTopMenuPage.getInstance();
		else
			throw new ItemNotFoundException(saleschannel+" is not expected Operations Manager/Field Manager/Marinas Manager");
		
//		gotoSlipReservationSearchPage(topmenu);
		
		gotoSlipReservationDetailsPageFromSearchPage(resNums[0]);
		
		cleanSlipResFromOrdDetailsToCart(reason, note);
		
		StringBuffer resNumsBuffer = new StringBuffer();
		resNumsBuffer.append(resNums[0]);

		for (int i = 1; i < resNums.length; i++) {
			gotoSlipReservationDetailsFromCart(resNums[i]);
			cleanSlipResFromOrdDetailsToCart(reason, note);
			resNumsBuffer.append(" " + resNums[i]);
		}
		
		logger.info("Cancel reservation(s)" + resNumsBuffer.toString() + " to order cart.");
		
	}

	/**
	 * This method executes the work flows of cancel/void a SLIP reservation.
	 * The method starts from slip reservation details page and ends at order
	 * cart page.
	 * 
	 * @param reason
	 *            - cancellation reason
	 * @param note
	 *            - note
	 */
	public void cleanSlipResFromOrdDetailsToCart(String reason, String note) {
//		OrmsOrderCartPage fmOrderCardPg = OrmsOrderCartPage.getInstance();
		OrmsSlipReservationDetailsPage slipResDetailPg = OrmsSlipReservationDetailsPage
				.getInstance();

		logger.info("Clean reservation to Order Cart.");

		if (slipResDetailPg.cancelable()) {
			cancelResToCart(reason, note);
		} else if (slipResDetailPg.voidable()) {
			voidResToCartInOrms(note);
		} else {
			String ord_num = slipResDetailPg.getResNum();
			//james: we will not cleanup order from DB anymore.
//			System.out.println(ord_num);
//			System.out.println(ord_num);
//			String contract = slipResDetailPg.getContract();
//			String schema = TestProperty.getProperty(TestProperty
//					.getProperty("target_env") + ".db.schema.prefix")
//					+ contract;
//			logger.warn("Cannot either void or cancel reservation#"
//					+ ord_num
//					+ ". The order will be cleaned directly from database, which may cause data inconsistent");
//
//			cleanupSlipReservationsInDB(schema, ord_num);
//
//			slipResDetailPg.clickCart();
//			fmOrderCardPg.waitExists();
			throw new ItemNotFoundException("Cannot either void or cancel reservation#"+ ord_num);
		}
	}

	public void voidPayments(String[] pmt_ids, String notes,
			String saleschannel) {
		OrmsFinSessionSearchPage ormsFinSessionPage = OrmsFinSessionSearchPage
				.getInstance();
		OrmsPaymentSearchPage ormsPaymentSearchPage = OrmsPaymentSearchPage
				.getInstance();

		logger.info("Void payments: " + MiscFunctions.arrayToString(pmt_ids));


		if(saleschannel.equalsIgnoreCase("Operations Manager"))
			OpMgrTopMenuPage.getInstance().clickFinancials();
//		else if(saleschannel.equalsIgnoreCase("Field Manager"))
//			FldMgrTopMenuPage.getInstance().clickFinancials();
//		else if(saleschannel.equalsIgnoreCase("Marina Manager"))
//			MrnMgrTopMenuPage.getInstance().clickFinancials();
		else
			throw new ItemNotFoundException(saleschannel+" is not expected Operations Manager/Field Manager/Marinas Manager");
		
		ormsFinSessionPage.waitLoading();
		ormsFinSessionPage.clickPaymentsTab();
		ormsPaymentSearchPage.waitLoading();
		for (String id : pmt_ids) {
			voidPayment(id, notes);
		}
	}

	/**
	 * This method is start from OrmsOrderCartPage and end at
	 * OrmsOrderSummaryPage it is a shorten work flow of processOrderCart()
	 * method Because it stop in OrmsOrderSummaryPage so we can get other
	 * information(such as refund details,Voucher details)in
	 * OrmsOrderSummaryPage
	 * 
	 */
	public String processOrderCartToSummary(Payment pay) {
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsOrderSummaryPage fmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		OrmsProcessOrderCartPopupPage orderCartPopupPg = OrmsProcessOrderCartPopupPage
				.getInstance();
		OrmsOverrideRulePinPopupPage rulePopupPg = OrmsOverrideRulePinPopupPage
				.getInstance();
		OrmsPrintPopupPage printPopupPg = OrmsPrintPopupPage.getInstance();
		OrmsOtherVouchersPage fmOtherVouchersPg = OrmsOtherVouchersPage
				.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
		CallMgrContractPolicyPage omContractPolicyPg = CallMgrContractPolicyPage
				.getInstance();
		OrmsEnterPinNumPopupPage pinPopupPage = OrmsEnterPinNumPopupPage.getInstance();
		
		String resIDs = "";
		String eventID = "";
		Object page = null;
		logger.info("Processing order cart.");

		if (pay != null && !pay.payType.equals("")
				&& !pay.payType.equalsIgnoreCase("None")) {
			if (pay.useVoucherPayment) {
				if (pay.voucherID != null && pay.voucherID.length() > 0) {
					fmOrdCartPg.selectVoucherAsPayment(pay.voucherID);
					fmOrdCartPg.waitLoading();
				}
				if (pay.otherVoucherID != null
						&& pay.otherVoucherID.length() > 0) {
					fmOrdCartPg.clickSelectOtherVoucher();
					fmOtherVouchersPg.waitLoading();
					fmOtherVouchersPg
							.searchVouchersByVoucherID(pay.otherVoucherID);

					page = browser.waitExists(rulePopupPg, fmOtherVouchersPg);
					if (page == rulePopupPg) {// Enter note popup displayed
						rulePopupPg.enterNote(pay.reason);
						rulePopupPg.enterPIN(pay.pin);
						rulePopupPg.clickOK();
						fmOrdCartPg.waitLoading();
					}
				}
			}
		}

		fmOrdCartPg.setupPayment(pay);

		if (pay != null && !pay.payType.equals("")
				&& !pay.payType.equalsIgnoreCase("None")) {
			if (null != pay.additionalPayType
					&& pay.additionalPayType.length() > 0) {
				fmOrdCartPg.clickAdditionalPayment();
				fmOrdCartPg.waitLoading();
				fmOrdCartPg.setupAdditionalPayment(pay);
			}

			if (pay.issueToVoucher) {
				fmOrdCartPg.selectRadioVoucher();
			} else {
				fmOrdCartPg.selectRadioRefund();
			}
		}

		fmOrdCartPg.clickProcessOrder();

		alert.setDismissible(true);
		alert.setBeforePageLoading(false);
		printPopupPg.setBeforePageLoading(false);

		// possible popups: payment pin popup or override rule popup or print
		// popup
		page = browser.waitExists(orderCartPopupPg, rulePopupPg, pinPopupPage, printPopupPg,
				fmOrdSumPg, alert, omContractPolicyPg);
		if (page == orderCartPopupPg) {
			int issueToObjectCount = orderCartPopupPg.getIssueToObjectCount();

			for (int i = 0; i < issueToObjectCount; i++) {
				orderCartPopupPg.setupInformation(pay.pin, pay.issueCash,
						pay.issueGiftCard, pay.giftCardNum, i);
			}

			orderCartPopupPg.clickOK();

			page = browser.waitExists(printPopupPg, rulePopupPg, pinPopupPage, fmOrdSumPg,
					omContractPolicyPg);
			alert.resetDefault();
		}
		int count = 0;
		while (page == pinPopupPage) {
			// need to override rules
			// in scenario of multiple order carts, the rulePopup may appear
			// more than 1 times
			String pin = getPinNum(pay.currentUser);
			pinPopupPage.enterReason();
			pinPopupPage.enterPIN(pin);
			pinPopupPage.clickOK();
			page = browser.waitExists(rulePopupPg, printPopupPg,
					orderCartPopupPg, fmOrdSumPg, alert, omContractPolicyPg);
			count++;
			if (count > 50) {
				throw new TestCaseFailedException(
						"Failed to get through rule popup page for " + count
								+ " times");
			}
		}

		if (page == orderCartPopupPg) {
			System.out.println("2 : into method...");
			int issueToObjectCount = orderCartPopupPg.getIssueToObjectCount();
			System.out.println(issueToObjectCount);
			for (int i = 0; i < issueToObjectCount; i++) {
				orderCartPopupPg.setupInformation(pay.pin, pay.issueCash,
						pay.issueGiftCard, pay.giftCardNum, i);
			}
			orderCartPopupPg.clickOK();

			page = browser.waitExists(printPopupPg, fmOrdSumPg,
					omContractPolicyPg);
			alert.resetDefault();
		}

		if (page == printPopupPg) {
			printPopupPg.close();
			fmOrdSumPg.waitLoading();
			page = browser.waitExists(fmOrdSumPg, omContractPolicyPg);
		}

		if (page == omContractPolicyPg) {
			omContractPolicyPg.clickAcceptPolicy();
			fmOrdSumPg.waitLoading();
		}

		resIDs = fmOrdSumPg.getAllOrdNums();
		eventID = fmOrdSumPg.getEventID();

		String log = "Processed orders " + resIDs;
		if (eventID.length() > 0)
			log = log + " for event#" + eventID;
		logger.info(log);

		// Workaround for click btn
		// browser.inputKey(KeyInput.get(KeyInput.PAGE_DOWN));
//Note this method end at order summary page, why the following steps needed?
//		fmOrdSumPg.clickFinishCall();
//		browser.waitExists();
//		browser.waitExists(omTopMenu, cmHomePage);

		return (resIDs + " " + eventID).trim();
	}

	/**
	 * Search and void a payment with the given payment id This keyword start
	 * from Payment search page and end at payment search page. this function is generally available in
	 * Operations Manager, Field Manager, Venue Manager, Permit Manager and Marina Manager
	 * 
	 * @param id
	 * @param note
	 */
	public void voidPayment(String id, String note) {
		OrmsPaymentSearchPage ormsPaymentSearchPage = OrmsPaymentSearchPage
				.getInstance();
		OrmsPaymentDetailsPage ormsPaymentDetailsPage = OrmsPaymentDetailsPage
				.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Void payment#" + id);

		ormsPaymentSearchPage.searchPaymentByID(id);
		ormsPaymentSearchPage.waitLoading();
		if (ormsPaymentSearchPage.checkPaymentIdLinkExist(id)) {
			ormsPaymentSearchPage.clickPaymentByPaymentID(id);
			ormsPaymentDetailsPage.waitLoading();
			ormsPaymentDetailsPage.clickVoidOrReturnPayment();
			browser.waitExists(ormsPaymentDetailsPage, confirmDialog);

			ormsPaymentDetailsPage.setReason(note);
			ormsPaymentDetailsPage.clickOK();

			ormsPaymentDetailsPage.waitLoading();
			ormsPaymentDetailsPage.clickOK();

			ormsPaymentSearchPage.waitLoading();
		}
	}

	/**
	 * This method executes the work flows of canceling a reservation The work
	 * flow starts from the reservation details page and ends at order cart
	 * 
	 * @param reason
	 *            - the reason code for cancellation
	 * @param note
	 *            - the note to be entered
	 */
	public void cancelResToCart(String reason, String note) {
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		OrmsCancelReservationPage cancelResvPg = OrmsCancelReservationPage
				.getInstance();
		OrmsConfirmDialogWidget confirmPg = OrmsConfirmDialogWidget.getInstance();

		logger.info("Cancelling reservation.");

		resvDetailPg.clickCancelRes();

		cancelResvPg.waitLoading();
		cancelResvPg.selectReason(reason);
		cancelResvPg.setNotes(note);

		cancelResvPg.clickCompleteCancel();
		Object o = browser.waitExists(confirmPg,cartPg);
		if(o instanceof OrmsConfirmDialogWidget)
		{
			confirmPg.clickOK();
			ajax.waitLoading();
			cartPg.waitLoading();
		}
		
	}

	/**
	 * This method executes the work flows of voiding a reservation. The work
	 * flow starts from the reservation details page and ends at the order cart
	 * page
	 * 
	 * @param note
	 *            - the note to be entered
	 */
	public void voidResToCartInOrms(String note) {
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		OrmsVoidReservationPage voidResvPg = OrmsVoidReservationPage
				.getInstance();
		OrmsConfirmDialogWidget confirmPg = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Voiding reservation.");

		resvDetailPg.clickVoid();
		voidResvPg.waitLoading();
		voidResvPg.setNotes(note);
		voidResvPg.clickCompleteVoid();
		Object o = browser.waitExists(confirmPg,cartPg);
		if(o instanceof OrmsConfirmDialogWidget){
			confirmPg.clickOK();
			ajax.waitLoading();
			cartPg.waitLoading();
		}
	}

	public void gotoReservationDetailsFromCart(String resNum) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsReservationSearchPage oResSchPg = OrmsReservationSearchPage
				.getInstance();
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsReservationAlertPage alertPg = OrmsReservationAlertPage
				.getInstance();

		logger.info("Goto reservation#" + resNum
				+ " details page from order cart.");

		ordCartPg.clickChangeExistingRes();
		oResSchPg.waitLoading();
		oResSchPg.searchReservation(resNum);
		oResSchPg.searchWaitExists();
		oResSchPg.selectReservation(resNum);

		Object page = browser.waitExists(alertPg, resvDetailPg);
		if (page == alertPg) {
			alertPg.clickOK();
			resvDetailPg.waitLoading();
		}
	}

	public void gotoSlipResDetailsFromCart() {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		logger.info("Go back to slip reservation page from cart by clicking transaction name link.");
		cartPage.clickTransactionName(new RegularExpression("(Advanced Slip Reservation|Float-in Registration)\\(New(\\s)?-(\\s)?\\d+\\)", false));
		resDetailsPage.waitLoading();
	}
	
	public void gotoSlipReservationDetailsFromCart(String resNum) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsSlipReservationSearchPage oResSchPg = OrmsSlipReservationSearchPage
				.getInstance();
		OrmsSlipReservationDetailsPage resvDetailPg = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsReservationAlertPage alertPg = OrmsReservationAlertPage
				.getInstance();

		logger.info("Goto slip reservation#" + resNum
				+ " details page from order cart.");

		ordCartPg.clickChangeSlipRes();
		oResSchPg.waitLoading();
		oResSchPg.searchReservation(resNum);
		ajax.waitLoading();
		oResSchPg.searchWaitExists();
		oResSchPg.selectReservation(resNum);

		Object page = browser.waitExists(alertPg, resvDetailPg);
		if (page == alertPg) {
			alertPg.clickOK();
			resvDetailPg.waitLoading();
		}
	}

	/**
	 * This method used to get notification/confirmation email from mail box
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param emailSubject
	 * @param timeDiff
	 *            if it is 0, will search email by subject only,else it should
	 *            be the time difference between mail server and script running
	 *            machine,will search email subject and send date
	 * @return
	 */
	public Properties[] getEmailFromMailBox(String host, String username,
			String password, String emailSubject, int timeDiff) {
		logger.info("Get email from mail box");

		Email e = new Email();
		e.connect(host, username, password, "pop3");
		RegularExpression subjectPattern = new RegularExpression(emailSubject,
				false);
		int time = CHECK_NOTIFICATION_IN_MAILBOX_THRESHOLD;
		Properties[] pros = null;
		String mailFolder = TestProperty
				.getProperty("mail.notification.folder");
		if (timeDiff != 0) {
			pros = e.searchEmail(mailFolder, subjectPattern,
					DateFunctions.getCurrentDate(), timeDiff, time, true);
		} else {
			pros = e.searchEmail(mailFolder, subjectPattern, time, true);
		}
		e.disconnect();

		return pros;
	}

	/**
	 * Process Order and get Voucher info on order summary page. Note: The
	 * voucher amount only contains the numbers, not $.
	 * 
	 * @return
	 * @author Lesley Wang
	 * @date June 4, 2012
	 */
	public Voucher processOrderAndGetVoucherInfo(){
		OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
//		FldMgrHomePage fmHomePg = FldMgrHomePage.getInstance();
		CallMgrHomePage cmHomePg = CallMgrHomePage.getInstance();
		OpMgrHomePage omHomePg = OpMgrHomePage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
//		MrnMgrHomePage mmHomePg = MrnMgrHomePage.getInstance();

		Payment pay = new Payment();
		pay.issueToVoucher = true;//IMPORTANT
		this.processOrderToOrderSummary(pay);
		
		Voucher voucher = new Voucher();
		voucher = ordSumPg.getVoucherInfo();
		String amt = voucher.amount;
		voucher.amount = amt.substring(amt.indexOf("$") + 1).trim();
		ordSumPg.clickFinishButton();
//		browser.waitExists(cmHomePg, fmHomePg, lmHomePg, omHomePg, mmHomePg);
		browser.waitExists(cmHomePg,  lmHomePg, omHomePg);
		return voucher;
	}

	/**
	 * Get total refund amount on order cart page. Select refund radio button
	 * firstly if it exists.
	 * 
	 * @return
	 * @author Lesley Wang
	 * @date June 4, 2012
	 */
	public String getRefundAmountOnOrderCartPage() {
		OrmsOrderCartPage lmOrdCartPg = OrmsOrderCartPage.getInstance();
		lmOrdCartPg.selectRadioRefund();
		String amount = lmOrdCartPg.getTotalRefundAmount().toString();
		return amount;
	}

	/**
	 * Verify EFT Invoice detail info correct or not
	 * 
	 * @param expected
	 * @param actual
	 */
	public void verifyEFTInvoiceInfo(EFTInvoicingInfo expected,
			EFTInvoicingInfo actual) {
		logger.info("Compare the EFT Invoice info(Num#="
				+ expected.getInvoiceNum() + ") with actual value in DB.");

		boolean result = true;
		result &= MiscFunctions.compareResult("EFT Invoice Num",
				expected.getInvoiceNum(), actual.getInvoiceNum());
		result &= MiscFunctions.compareResult("EFT Invoice Jobs Id",
				expected.getInvoiceJobId(), actual.getInvoiceJobId());
		result &= MiscFunctions.compareResult("EFT Invoice Type",
				MiscFunctions.convertEFTType(expected.getInvoiceType()),
				actual.getInvoiceType());
		result &= MiscFunctions.compareResult(
				"EFT Invoice associate Vendor Num", expected.getVendorNum(),
				actual.getVendorNum());
		result &= MiscFunctions.compareResult(
				"EFT Invoice associate Vendor Name", expected.getVendorName(),
				actual.getVendorName());
		result &= MiscFunctions.compareResult(
				"EFT Invoice associate Agent Num", expected.getAgentNum(),
				actual.getAgentNum());
		result &= MiscFunctions.compareResult(
				"EFT Invoice associate Agent Name", expected.getAgentName(),
				actual.getAgentName());
		result &= MiscFunctions.compareResult("EFT Invoice Group Name",
				expected.getInvoiceGrouping(), actual.getInvoiceGrouping());
		result &= MiscFunctions.compareResult("EFT Invoice Status",
				MiscFunctions.convertEFTInvoiceStatus(expected.getStatus()),
				actual.getStatus());
		result &= MiscFunctions.compareResult("EFT Invoice Date",
				expected.getInvoiceDate(), actual.getInvoiceDate());
		result &= MiscFunctions.compareResult("EFT Invoice Period End Date",
				expected.getPeriodDate(), actual.getPeriodDate());
		result &= MiscFunctions.compareResult("EFT Invoice Amount",
				Double.valueOf(expected.getAmount().replaceAll("\\$", "")),
				Double.valueOf(actual.getAmount()));

		if (!result) {
			throw new ErrorOnDataException(
					"EFT Invoice Info is incorrect, please refer log for details.");
		}
		logger.info("EFT Invoice Info is correct.");
	}

	/**
	 * Verify EFT Invoice Transaction detail info correct or not
	 * 
	 * @param expected
	 * @param actual
	 */
	public void verifyEFTInvoiceTransactionInfo(
			EFTInvoiceTransactionInfo expected, EFTInvoiceTransactionInfo actual) {
		logger.info("Compare the EFT Invoice Transaction info(ID#="
				+ actual.getId() + ") with actual value in DB.");

		boolean result = true;
		// result &= MiscFunctions.compareResult("EFT Invoice Transaction ID",
		// expected.getId(), actual.getId());
		result &= MiscFunctions.compareResult("EFT Invoice Number",
				expected.getInvoiceId(), actual.getInvoiceId());
		result &= MiscFunctions.compareResult("EFT Invoice Transaction Type",
				MiscFunctions.convertEFTInvoiceTransactionType(expected
						.getType()), actual.getType());
		result &= MiscFunctions.compareResult(
				"EFT Invoice Transaction Create Date",
				expected.getTransactionDate(), actual.getTransactionDate());
		result &= MiscFunctions.compareResult("EFT Invoice Transaction User",
				expected.getTransactionUser(), actual.getTransactionUser());
		result &= MiscFunctions.compareResult(
				"EFT Invoice Transaction Location",
				expected.getTransactionLocation(),
				actual.getTransactionLocation());

		if (!result) {
			throw new ErrorOnDataException(
					"EFT Invoice Transaction info is incorrect, please refer log for detail.");
		}
		logger.info("EFT Invoice Transaction info is correct.");
	}

	public EFTInvoiceTransmissionInfo verifyEFTInvoiceTransmission(
			String schema, String invoiceNum, EFTInvoiceTransmissionInfo expect) {
		EFTInvoiceTransmissionInfo actual = getEFTInvoiceTransmissionInfo(
				schema, invoiceNum).get(0);
		this.compareEFTInvoiceTransmissionInfo(expect, actual);

		return actual;
	}

	public void compareEFTInvoiceTransmissionInfo(
			EFTInvoiceTransmissionInfo expect, EFTInvoiceTransmissionInfo actual) {
		boolean result = true;

		result = MiscFunctions.compareResult("Invoice status",
				expect.getStatus(), actual.getStatus());
		result &= MiscFunctions.compareResult("Invoice transmission due date",
				expect.getTransDueDate(), actual.getTransDueDate());
		result &= MiscFunctions.compareResult("Invoice create date",
				expect.getCreateDate(), actual.getCreateDate());

		if (!result) {
			throw new ErrorOnDataException(
					"invoice transmission genereate data is not correct,please check log....");
		}
	}

	/**
	 * verify eft remittance transmission record info
	 * 
	 * @param schema
	 * @param invoiceNum
	 * @param expect
	 * @return
	 */
	public List<EFTRemittanceTransInfo> verifyEFTRemittanceTrans(String schema,
			String invoiceNum, List<EFTRemittanceTransInfo> expect) {
		logger.info("Verify EFT Remittance Transmission info identified by invoice#="
				+ invoiceNum);

		List<EFTRemittanceTransInfo> actual = getRemittanceTransInfoUsingInvoiceID(
				schema, invoiceNum);
		this.compareEFTRemittanceTransInfo(expect, actual);

		return actual;
	}

	/**
	 * compare remittance transmission data
	 * 
	 * @param expect
	 * @param actual
	 */
	public void compareEFTRemittanceTransInfo(
			List<EFTRemittanceTransInfo> expect,
			List<EFTRemittanceTransInfo> actual) {
		boolean result = true;
		if (expect.size() != actual.size()) {
			throw new ErrorOnDataException(
					"remittance transmission genereate number is not correct,please check eft remittance...");
		}
		for (int i = 0; i < expect.size(); i++) {
			EFTRemittanceTransInfo exptInfo = expect.get(i);
			EFTRemittanceTransInfo actualInfo = actual.get(i);
			result = MiscFunctions.compareResult("Remittance number",
					exptInfo.getRemittanceNumber(),
					actualInfo.getRemittanceNumber());
			result &= MiscFunctions.compareResult(
					"Remittance transmission date", exptInfo.getDate(),
					actualInfo.getDate());
			result &= MiscFunctions.compareResult(
					"Remittance transmission status", exptInfo.getStatus(),
					actualInfo.getStatus());
		}

		if (!result) {
			throw new ErrorOnDataException(
					"remittance transmission genereate data is not correct,please check log....");
		}
	}

	/**
	 * verify eft remittance record info
	 * 
	 * @param schema
	 * @param invoiceNum
	 * @param expect
	 */
	public List<EFTRemittanceInfo> verifyEFTRemittance(String schema,
			String invoiceNum, List<EFTRemittanceInfo> expect) {

		logger.info("Verify EFT Remittance info identified by invoice#="
				+ invoiceNum);

		List<EFTRemittanceInfo> actual = getRemittanceInfoUsingInvoiceID(
				schema, invoiceNum);
		this.compareEFTRemittanceInfo(expect, actual);
		return actual;
	}

	/**
	 * compare remittance data
	 * 
	 * @param expect
	 * @param actual
	 */
	public void compareEFTRemittanceInfo(List<EFTRemittanceInfo> expect,
			List<EFTRemittanceInfo> actual) {
		boolean result = true;

		if (expect.size() != actual.size()) {
			throw new ErrorOnDataException(
					"remittance genereate number is not correct,please check eft invoice");
		}
		for (int i = 0; i < expect.size(); i++) {
			EFTRemittanceInfo exptInfo = expect.get(i);
			EFTRemittanceInfo actualInfo = actual.get(i);
			result = MiscFunctions.compareResult("Invoice job id",
					exptInfo.getInvoiceJobID(), actualInfo.getInvoiceJobID());
			result &= MiscFunctions.compareResult("Vendor name",
					exptInfo.getVendorName(), actualInfo.getVendorName());
			result &= MiscFunctions.compareResult("Store name",
					exptInfo.getStoreName(), actualInfo.getStoreName());
			result &= MiscFunctions.compareResult("Account code",
					exptInfo.getAccountCode(), actualInfo.getAccountCode());
			result &= MiscFunctions.compareResult("Remittance date", exptInfo
					.getRemittanceDate(), DateFunctions.formatDate(
					actualInfo.getRemittanceDate(), "EEE MMM dd yyyy"));
			result &= MiscFunctions.compareResult("Period date", exptInfo
					.getPeriodDate(), DateFunctions.formatDate(
					actualInfo.getPeriodDate(), "EEE MMM dd yyyy"));
			result &= MiscFunctions.compareResult("Remittance status",
					exptInfo.getStatus(), actualInfo.getStatus());
			result &= MiscFunctions.compareResult("Amount", new BigDecimal(
					exptInfo.getAmount()),
					new BigDecimal(actualInfo.getAmount()));
		}

		if (!result) {
			throw new ErrorOnDataException(
					"remittance genereate data is not correct,please check log....");
		}
	}

	/**
	 * Verify Pos Quantity on fee details page This method starts from fee
	 * detail page and ends at order cart page
	 * 
	 * @param pos
	 */
	public void verifyPosQuantityOnOrderFeeDetailsPage(String posPrd,
			String posQty) {
		OrmsFeeDetailsPage feeDetailPg = OrmsFeeDetailsPage.getInstance();
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		String posQuantity = feeDetailPg.getPosQuantityByPosName(posPrd);

		if (!posQuantity.equals(posQty)) {
			throw new ErrorOnPageException(
					"Pos Quantity display un-correctly.", posQty, posQuantity);
		}

		logger.info("POS unit price and quantity display correctly on fee detail page.");
		feeDetailPg.clickCancel();
		ordCartPg.waitLoading();
	}

	/**
	 * Verify Pos quantity on order summary page
	 * 
	 * @param pos
	 */
	public void verifyPosQuantityOnOrderSummaryPage(POSInfo pos) {
		OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();

		String content = ordSumPg.getPOSOrderInfo(pos.ordID);
		content = content.substring(
				content.indexOf(pos.product) + pos.product.length(),
				content.length() - 1);
		String posQuantity = RegularExpression.getMatches(content,
				"\\d+(\\.\\d+)?")[0];

		if (!posQuantity.equals(pos.quantity)) {
			throw new ErrorOnPageException(
					"Pos Quantity display un-correctly.", pos.quantity,
					posQuantity);
		}

		logger.info("POS quantity display correctly on order summary page.");
	}

	/*
	 * Add primary occupant.
	 */
	public void addPrimaryOccupant(Customer cust) {
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsCustomerDetailsPage cusDetailPg = OrmsCustomerDetailsPage
				.getInstance();

		logger.info("Add primary occupant");
		resvDetailPg.unselectSameAsCustomer();
		ajax.waitLoading();
		cusDetailPg.waitLoading();
		cusDetailPg.setCustInfo(cust);
		cusDetailPg.clickOK();
		cusDetailPg.waitLoading();
		cusDetailPg.clickOK();
		resvDetailPg.waitLoading();
	}

	/**
	 * This method was used to verify error message when input invalid quantity
	 * on OrmsPOSAddItemPage This workflow was only on OrmsPOSAddItemPage
	 * 
	 * @param pos
	 * @param errMsg
	 */
	public void verifyErrorMsgForInvlidQuantity(POSInfo pos, String errMsg) {
		OrmsPOSAddItemPage posItemPg = OrmsPOSAddItemPage.getInstance();
		ConfirmDialogPage alert = ConfirmDialogPage.getInstance();

		logger.info("Add POS to order cart");

		// posItemPg.addPosToCart(pos);
		posItemPg.searchItem(pos.product);
		posItemPg.waitLoading();
		String msg = posItemPg.selectItem(pos);
		if(StringUtil.notEmpty(msg)){
			if (!msg.equalsIgnoreCase(errMsg)) {
				throw new ActionFailedException(
						"Could not get error message from alert dialog.");
			}
		}else{
			posItemPg.clickAddToCart();

			alert.setBeforePageLoading(false);
			alert.setDismissible(false);
			Object page = browser.waitExists(alert, posItemPg);
			if (page == alert) {
				msg = alert.text();
				if (!msg.equalsIgnoreCase(errMsg)) {
					throw new ActionFailedException(
							"Could not get error message from alert dialog.");
				}
				logger.info("Alert dialog message was verified successfully.");
				alert.dismiss();
				posItemPg.waitLoading();
			} else if (page == posItemPg) {
				throw new ActionFailedException(
						"Could not get alert dialog for invlid input.");
			}
		}
		
	}

	/**
	 * This method is used to add an order to cart by click "Add To Cart" in
	 * order detail page
	 * */
	public void addOrderToCart() {
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsOrderCartPage vmOrderCartPg = OrmsOrderCartPage.getInstance();
		logger.info("Add exist order to cart...");
		detailsPage.clickAddToCart();
		vmOrderCartPg.waitLoading();
	}

	/**
	 * Change Credit Card info in lottery application detail page.
	 * 
	 * @param pay
	 */
	public void changeCCInfoInLotteryAppDetailPg(Payment pay) {
		OrmsLotteryApplicationDetailsPage cmLotteryAppPg = OrmsLotteryApplicationDetailsPage
				.getInstance();
		OrmsLotteryApplicationSearchPage searchAppPg = OrmsLotteryApplicationSearchPage
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Change Credit Card info in lottery application detail page.");

		// change payment and reset
		cmLotteryAppPg.changeCCInfoInLotteryAppDetailPg(pay);
		cmLotteryAppPg.clickOK();
		Object page = browser.waitExists(searchAppPg, orderCartPg);
		if (page == orderCartPg) {
			this.processOrderCart(pay, orderCartPg.getManagerName());
		}
	}

	/**
	 * download a report
	 * 
	 * @param path
	 *            file path and name
	 */
	public void downloadReport(String path) {
		FileDownloadDialogPage filePage = FileDownloadDialogPage.getInstance();
		File file = new File(path);
		if (file.exists()) {
			boolean delFile = file.delete();
			if (!delFile) {
				logger.error("Fail to Delete File.");
			}
		}
		filePage.downloadSaveFile(path);
		int time = 1;
		while (!file.exists()) {
			Browser.sleep(1);
			time++;
			if (time > 120)
				break;
		}
		if (file.exists()) {
			logger.info("Download Report cost " + time + " seconds!");
		} else {
			logger.error("Can not download Report in " + time + " seconds!");
		}

		if (filePage.exists()) {
			filePage.clickClose();
		}
	}
	
	protected String downloadFile(String path,String name,String format){
		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new RuntimeException("Failed to create directory " + path);
			}
		}
		String fileName = file.getAbsolutePath()+File.separator+name+"."+format;
		downloadReport(fileName);
		return fileName;
	}

	/**
	 * void gift card order to cart.
	 */
	public void voidGiftCardOrdToCart() {
		OrmsGiftCardDetailsPage omGiftCardOrdDetailPg = OrmsGiftCardDetailsPage
				.getInstance();
		OrmsVoidGiftCardSalePage voidGcSalesPg = OrmsVoidGiftCardSalePage
				.getInstance();

		logger.info("void gift card order");
		omGiftCardOrdDetailPg.clickVoidSale();
		ajax.waitLoading();
		voidGcSalesPg.waitLoading();
		voidGcSalesPg.clickOK();
		ajax.waitLoading();
		omGiftCardOrdDetailPg.waitLoading();
	}

	public String getCurrentLoginChannel() {
		OrmsPage ormsPg = OrmsPage.getInstance();

		return ormsPg.getManagerName();
	}

	/**
	 * Go to pos charge list label in order detail page
	 * 
	 * @param posSaleNum
	 */
	public void gotoPOSChargeDetailPage(String posSaleNum) {
		OrmsPOSChargeListInOrderDetailPage chargeLabel = OrmsPOSChargeListInOrderDetailPage
				.getInstance();
		OrmsPOSDetailsPage posDetail = OrmsPOSDetailsPage.getInstance();
		logger.info("Go to ticket pos charge detail page.");
		chargeLabel.clickPosSaleNum(posSaleNum);
		ajax.waitLoading();
		posDetail.waitLoading();
	}
	
	public boolean checkPOSChargedToRes(String posNum){
		OrmsReservationDetailsPage detailPg = OrmsReservationDetailsPage.getInstance();
		OrmsPOSChargeListInOrderDetailPage chargePg = OrmsPOSChargeListInOrderDetailPage.getInstance();
		
		logger.info("Check POS charged to Reservation.");
		
		detailPg.clickChargesTab();
		ajax.waitLoading();
		chargePg.waitLoading();
		
		return chargePg.checkPOSCharged(posNum);
	}

	/**
	 * This method executes a work flow of voiding the POS sales. The work flow
	 * is from POS detail page and end with Order Cart Page.
	 * 
	 * @param reason
	 * @param note
	 */
	public void voidPosSales(String reason, String note) {
		OrmsPOSDetailsPage PosDetailPg = OrmsPOSDetailsPage.getInstance();
		OrmsVoidPOSSalePage PosVoidPg = OrmsVoidPOSSalePage.getInstance();
		OrmsOrderCartPage OrdCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Void the POS sales.");
		PosDetailPg.clickVoidSale();
		PosVoidPg.waitLoading();

		PosVoidPg.selectVoidReason(reason);
		PosVoidPg.setVoidReason(note);
		PosVoidPg.clickOK();

		OrdCartPg.waitLoading();
	}

	/**
	 * This method used to return a POS to order cart
	 * 
	 * @param pos
	 */
	public void returnPOSToCart(POSInfo pos) {
		OrmsOrderCartPage OrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsPOSDetailsPage PosDetailPg = OrmsPOSDetailsPage.getInstance();
		logger.info("Return POS to Order Cart.");
		PosDetailPg.setReturnQuantity(pos.returnQty);
		PosDetailPg.selectReasonCode(pos.reason);
		PosDetailPg.setNote(pos.note);
		PosDetailPg.clickOK();
		OrdCartPg.waitLoading();
	}

	/**
	 * This method used to adjust pos fee, it starts from order cart page and
	 * then go to fee detail page to adjust fee, ends at order cart page.
	 * 
	 * @param newFee
	 * @param notes
	 */
	public void adjustPOSFeeFromOrderCartPage(Double newFee, String notes) {
		OrmsFeeDetailsPage feePg = OrmsFeeDetailsPage.getInstance();
		OrmsOrderCartPage OrdCartPg = OrmsOrderCartPage.getInstance();
		logger.info("Go the fee detail to adjust fee.");
		OrdCartPg.selectFirstReservation();
		OrdCartPg.clickFeesButton();
		feePg.waitLoading();
		feePg.changePosFee(newFee);
		feePg.refreshPage();
		feePg.waitLoading();
		feePg.setAdjustmentNotes(notes);
		logger.info("Adjust pos fee to $" + newFee);
		feePg.clickOK();
		OrdCartPg.waitLoading();
	}
	
	/**
	 * This method used to adjust item trans fee, it starts from order cart page and
	 * then go to fee detail page to adjust fee, ends at order cart page.
	 * 
	 * @param newFee
	 * @param notes
	 */
	public void adjustTransFeeFromOrderCartPage(Double newFee, String notes) {
		OrmsFeeDetailsPage feePg = OrmsFeeDetailsPage.getInstance();
		OrmsOrderCartPage OrdCartPg = OrmsOrderCartPage.getInstance();
		logger.info("Go the fee detail to adjust fee.");
		OrdCartPg.selectFirstReservation();
		OrdCartPg.clickFeesButton();
		feePg.waitLoading();
		feePg.changeItemTransactionFee(newFee);
		feePg.refreshPage();
		feePg.waitLoading();
		feePg.setAdjustmentNotes(notes);
		logger.info("Adjust items trans fee to $" + newFee);
		feePg.clickOK();
		OrdCartPg.waitLoading();
	}

	/**
	 * This method used to adjust pos fee, it starts from pos order detail page
	 * and then go to fee detail page to adjust fee, ends at pos order detail
	 * page.
	 * 
	 * @param newFee
	 * @param notes
	 */
	public void adjustPOSFeeFromPOSOrderDetailPage(Double newFee, String notes) {
		OrmsFeeDetailsPage feePg = OrmsFeeDetailsPage.getInstance();
		OrmsOrderCartPage OrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsPOSDetailsPage posDetail = OrmsPOSDetailsPage.getInstance();
		logger.info("Go the fee detail to adjust fee.");
		posDetail.clickFees();
		feePg.waitLoading();
		feePg.changePosFee(newFee);
		feePg.refreshPage();
		feePg.waitLoading();
		feePg.setAdjustmentNotes(notes);
		logger.info("Adjust pos fee to $" + newFee);
		feePg.clickOK();
		OrdCartPg.waitLoading();
	}

	/**
	 * set the pos info.
	 * 
	 * @param posInfo
	 *            - the info of pos.
	 */
	public void setupPosInfo(POSInfo posInfo) {
		OrmsAddPOSProductDetailsPage posDetailsPg = OrmsAddPOSProductDetailsPage
				.getInstance();
		OrmsPosSelectRevenueLocationWidget selectRevenueLocation = OrmsPosSelectRevenueLocationWidget
				.getInstance();

		posDetailsPg.setPosProductCode(posInfo.productCode);
		posDetailsPg.setPosProdectName(posInfo.product);
		posDetailsPg.selectProductClass(posInfo.productClass);
		posDetailsPg.selectProductSubClass(posInfo.productSubClass);
		posDetailsPg.selectProductGroup(posInfo.productGroup);
		ajax.waitLoading();

		if (!posDetailsPg.getSelectedInventoryType().equalsIgnoreCase(
				posInfo.inventoryType)) {
			posDetailsPg.selectInventoryType(posInfo.inventoryType);
			ajax.waitLoading();
			posDetailsPg.waitLoading();
		}
		posDetailsPg.setPosProductDescription(posInfo.productDescription);
		posDetailsPg.setBarcode(posInfo.barcodeList);
		if(StringUtil.notEmpty(posInfo.serilizeNumberType)){
			posDetailsPg.selectSerializationNumType(posInfo.serilizeNumberType);
			ajax.waitLoading();
		}
		// Only for field manager.
		posDetailsPg.setPosProductUnitPrice(posInfo.unitPrice);
		if (!StringUtil.isEmpty(posInfo.effectiveStartDate)) {
			posDetailsPg.setEffectiveStartDate(posInfo.effectiveStartDate);
		}
		if (!StringUtil.isEmpty(posInfo.effectiveEndDate)) {
			posDetailsPg.setEffectiveEndDate(posInfo.effectiveEndDate);
		}
		posDetailsPg
				.selectVariablePriceIndicator(posInfo.variablePriceIndicator);
		posDetailsPg
				.selectPartialQuantityIndicator(posInfo.partialQuantityIndicator);
		posDetailsPg.setDisplayOrder(posInfo.displayOrder);
		posDetailsPg.selectAvaLocation(posInfo.availableLocation);
		if (posInfo.specificLocation) {
			posDetailsPg.selectSpecificRevLoc();
			ajax.waitLoading();
			selectRevenueLocation.waitLoading();
			selectRevenueLocation.selectLocation(posInfo.revLocation);
			selectRevenueLocation.clickOK();
			ajax.waitLoading();
			posDetailsPg.waitLoading();
		}
		posDetailsPg.selectAcquireZipCode(posInfo.acquierZipCodeInSale);
		posDetailsPg.selectApplicationCustomer(posInfo.applicationCustomer);

		if (posDetailsPg.isExtraDecimalIndicatorExisted()) {
			posDetailsPg
					.selectExtraDecimalIndicator(posInfo.extraDecimalIndicator);
		}
		if(!StringUtil.isEmpty(posInfo.account)) {
			posDetailsPg.selectAccount(posInfo.account);
		} else if(posDetailsPg.isAccountEnabled()) {
			posDetailsPg.selectAccount(1);
		}
		posDetailsPg.setPosSalesAttributes(posInfo.attributesArray);
	}

	/**
	 * add pos product.
	 * 
	 * @param posInfo
	 *            - pos product info.
	 */
	public String addPOSProduct(POSInfo posInfo) {
		OrmsPOSProductSetupPage posSearchPage = OrmsPOSProductSetupPage
				.getInstance();
		OrmsPOSProductSetupDetailsPage posProductSetupDetailsPg = OrmsPOSProductSetupDetailsPage
				.getInstance();
		OrmsAddPOSProductDetailsPage addPosDetailsPg = OrmsAddPOSProductDetailsPage
				.getInstance();
		OrmsConfirmDialogWidget confirmWidget =  OrmsConfirmDialogWidget.getInstance();

		logger.info("add pos product");
		posSearchPage.clickAddPosProduct();
		ajax.waitLoading();
		addPosDetailsPg.waitLoading();
		setupPosInfo(posInfo);
		addPosDetailsPg.clickApplyButton();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmWidget,posProductSetupDetailsPg);
		if(page == confirmWidget){
			confirmWidget.clickOK();
			ajax.waitLoading();
			posProductSetupDetailsPg.waitLoading();
		}
		posInfo.productID = posProductSetupDetailsPg.getProductId();
		posProductSetupDetailsPg.setPosProductInfo(posInfo);
		posProductSetupDetailsPg.clickOK();
		ajax.waitLoading();
		posSearchPage.waitLoading();
		return posInfo.productID;
	}

	public void gotoPOSInventoryReconciliationPage() {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();

		logger.info("Go to POS Inventory Reconciliation page.");
		reconciliationPage.clickPOSInventoryReconciliationTab();
		reconciliationPage.waitLoading();
	}

	public void gotoPOSInventoryReconciliationLogPage() {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();
		OrmsPOSInventoryReconciliationLogPage logPage = OrmsPOSInventoryReconciliationLogPage
				.getInstance();

		logger.info("Goto POS Inventory Reconciliation Log page.");

		reconciliationPage.clickViewInventoryReconciliationLog();

		logPage.waitLoading();
	}

	// TODO
	public void gotoPOSInventoryReconciliationFromImportFilePage() {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();
		OrmsPOSImportInventoryFilePage importFilePage = OrmsPOSImportInventoryFilePage
				.getInstance();

		logger.info("Go back to POS Inventory Reconciliation page from Import Inventory File page.");
		importFilePage.clickCancel();
		reconciliationPage.waitLoading();
	}

	public void gotoPOSInventoryFileLogPage() {
		OrmsPOSInventoryFileLogPage fileLogPage = OrmsPOSInventoryFileLogPage
				.getInstance();
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();

		logger.info("Goto POS Inventory File Log page.");

		reconciliationPage.clickViewInventoryFileLog();

		fileLogPage.waitLoading();
	}

	/**
	 * this method is used to import POS Inventory File(.csv, .text) into ORMS
	 * system.
	 * 
	 * @param path
	 * @return
	 */
	public String importPOSInventoryFile(String path) {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();
		OrmsPOSImportInventoryFilePage importFilePage = OrmsPOSImportInventoryFilePage
				.getInstance();

		logger.info("Import POS Inventory File into ORMS system.");
		reconciliationPage.clickImportInventoryFile();
		importFilePage.waitLoading();
		importFilePage.setInventoryFilePath(path);
		importFilePage.waitLoading();
//		Browser.sleep(30);
		importFilePage.clickOK();
		importFilePage.waitLoading();

		String id = "";
		if (importFilePage.isSuccess()) {
			id = importFilePage.getFileImportID();
		}

		return id;
	}

	public void reconcilePOSPhysicalInventory(String barcodes[],
			String names[], String physicalQtys[]) {
		if (names.length != physicalQtys.length) {
			throw new ErrorOnPageException(
					"The POS product names' length should be same with qty's.");
		}
		for (int i = 0; i < names.length; i++) {
			reconcilePOSPhysicalInventory(barcodes[i], names[i],
					physicalQtys[i]);
		}
	}

	public String reconcilePOSPhysicalInventory(String barcode, String name) {
		return reconcilePOSPhysicalInventory(barcode, name, null);
	}

	/**
	 * reconcile POS Physical Inventory in POS Inventory Reconciliation page
	 * 
	 * @param barcode
	 * @param name
	 * @param physicalQty
	 */
	public String reconcilePOSPhysicalInventory(String barcode, String name,
			String physicalQty) {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget
				.getInstance();

		logger.info("Reconcile POS(Name=" + name + ") Physical Inventory Qty: "
				+ physicalQty);
		if (!StringUtil.isEmpty(barcode) || !StringUtil.isEmpty(name)) {
			reconciliationPage.searchPOSProduct(barcode, name);
			if (!StringUtil.isEmpty(physicalQty)) {
				reconciliationPage.setPhysicalQtyOnHand(name, physicalQty);
			}
		}

		reconciliationPage.clickApply();

		ajax.waitLoading();
		reconciliationPage.clickReconcilePhysicalInventory();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		reconciliationPage.waitLoading();
		String id = reconciliationPage.getReconciliationID();

		return id;
	}

	public String reconcilePOSPhysicalInventory() {
		return reconcilePOSPhysicalInventory("", "", "");
	}

	/**
	 * print inventory list in reconciliation page, actually it is running 'POS
	 * Inventory List Report'
	 * 
	 * @param path
	 *            - report file store path
	 * @return
	 */
	public String printInventoryList(String path) {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage
				.getInstance();
		OrmsOnlineReportProcessingPage reportProcessPage = OrmsOnlineReportProcessingPage
				.getInstance();

		logger.info("Print Inventory List - 'POS Inventory List Report'.");
		reconciliationPage.clickPrintInventoryList();
		reportProcessPage.waitLoading();

		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new ItemNotFoundException("Failed To Make Directory.");
			}
		}
		String fullPathAndFileName = file.getAbsolutePath()
				+ "\\POSInventoryListReport_" + DateFunctions.getTimeStamp()
				+ ".pdf";
		downloadReport(fullPathAndFileName);

		if (reportProcessPage.exists()) {
			reportProcessPage.close();
		}
		reconciliationPage.waitLoading();

		return fullPathAndFileName;
	}

	
	
	/**
	 * ====================================Marina
	 * Manager==============================================
	 */
//	public void gotoSlipReservationSearchPage(OrmsPage topmenu) {
//		OpMgrTopMenuPage omTopMenu = OpMgrTopMenuPage.getInstance();
//		FldMgrTopMenuPage fmTopMenu = FldMgrTopMenuPage.getInstance();
//		MrnMgrTopMenuPage mmTopMenu = MrnMgrTopMenuPage.getInstance();
//		CallMgrTopMenuPage cmTopMenu = CallMgrTopMenuPage.getInstance();
//		OrmsSlipReservationSearchPage resSearchPage = OrmsSlipReservationSearchPage.getInstance();
//		
//		logger.info("Go to slip reservation search page from top menu.");
//		if(topmenu==omTopMenu)
//			omTopMenu.selectSearchDropDown("Slip Reservations");
//		else if(topmenu==fmTopMenu) {
//			if(!fmTopMenu.isMarinaMode()) {
//				fmTopMenu.selectSlipHome();
//				FldMgrMarinaHomePage.getInstance().waitLoading();
//			}
//			fmTopMenu.selectSearchDropdownList("Slip Reservations");
//		}else if(topmenu==mmTopMenu) {
//			mmTopMenu.selectTopDropdownList("Slip Reservations");
//		} else if(topmenu == cmTopMenu) {
//			CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
//
//			if (cmHmPg.exists()) {
//				cmHmPg.clickBoatingCall();
//				ajax.waitLoading();
//				cmTopMenu.waitLoading();
//			}
//			
//			cmTopMenu.selectSearchDropDown("Slip Reservations");
//		} else {
//			throw new PageNotFoundException(topmenu.getName()+" is not expected topmenu page for FM/OM/MM");
//		}
//			
//		resSearchPage.waitLoading();
//	}
	
	
	
	
	public void gotoSlipReservationDetailsPageFromSearchPage(String resNum) {
		OrmsSlipReservationSearchPage resSearchPage = OrmsSlipReservationSearchPage
				.getInstance();
		OrmsAlertPage alertPage = OrmsAlertPage.getInstance();
		OrmsReservationCustAlertPage custResAlertPg = OrmsReservationCustAlertPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();

		logger.info("Goto slip reservation(Res#-" + resNum
				+ ") details from search page.");
		resSearchPage.searchReservation(resNum);
		ajax.waitLoading();
		resSearchPage.searchWaitExists();
		resSearchPage.selectReservation(resNum);
		ajax.waitLoading();
		Object page = browser.waitExists(resDetailsPage, alertPage,custResAlertPg);
		if (page == alertPage) {
			alertPage.clickOK();
			resDetailsPage.waitLoading();
		}
		if (page == custResAlertPg) {
			custResAlertPg.clickOK();
			resDetailsPage.waitLoading();
		}
	}
	
	public void editNoteAlertInfoFromSlipResDetailPage(NoteAndAlertInfo noteAlertInfo){
		OrmsSlipReservationNoteAlertDetailPage slipResNADetailPg = OrmsSlipReservationNoteAlertDetailPage.getInstance();
		OrmsSlipReservationNoteAlertListPage resNAListPg = OrmsSlipReservationNoteAlertListPage.getInstance();
		logger.info("Edit Note Alert Info from slip reservation detail page for Note Alert id = " + noteAlertInfo.id);
		
		this.gotoNoteAlertDetailPageFromSlipReservationNoteAlertListPage(noteAlertInfo.id);
		slipResNADetailPg.setupNoteAlertInfo(noteAlertInfo);
		slipResNADetailPg.clickOK();
		ajax.waitLoading();
		resNAListPg.waitLoading();
	}

	public void transferToSlipListPage(SlipInfo toSlip) {
		transferToSlipListPage(toSlip, null, null);
	}
	
	public void transferToSlipListPage(SlipInfo toSlip, String resNum, SlipInfo fromSlip) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsSlipSearchPage searchPage = OrmsSlipSearchPage.getInstance();
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsAlertPage alertPage = OrmsAlertPage.getInstance();
		
		logger.info("Transfer Slip Res(#-" + (StringUtil.isEmpty(resNum) ? resDetailsPage.getResNum() : resNum)
				+ ") to slip search page.");
		resDetailsPage.clickTransfer();
		ajax.waitLoading();
		searchPage.waitLoading();
		//verify 'Transfer From Slip' section info
		if(!StringUtil.isEmpty(resNum) && fromSlip != null) {
			searchPage.verifyTransferFromSlipInfo(resNum, fromSlip);
		}
		searchPage.searchSlip(toSlip);
		Object page = browser.waitExists(searchPage, listPage, alertPage);
		if(page == alertPage) {
			alertPage.clickOK();
			page = browser.waitExists(searchPage, listPage);
		}
	}
	
	/**
	 * Go to slip details page from slip list page, 1. directly go to details
	 * page by clicking slip code in list page; 2. search slip, and then go to
	 * details page.
	 * 
	 * @param slip
	 */
	public void gotoSlipDetailsPageFromListPage(SlipInfo slip) {
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();

		logger.info("Goto slip(#=" + slip.getCode()
				+ ") details page from list page.");
		if (StringUtil.notEmpty(slip.getCode())&&listPage.isSlipExists(slip.getCode())) {
			listPage.clickSlipCodeLink(slip.getCode());
//			ajax.waitLoading();
		} else {
			listPage.clickFirstSlipCode();
		}
		detailsPage.waitLoading();
	}

	/**
	 * Goto slip details page from cart page by clicking product link
	 * @param code
	 * @param name
	 */
	public void gotoSlipDetailsPageFromCart(String code, String name) {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();
		
		logger.info("Goto Slip(CD=" + code + ") details page from Order Cart.");
		logger.info("Go to slip details page from cart.");
		cartPage.clickSlipLink(code, name);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoSlipDetailsPageFromResDetailsPage(String code, String name) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		
		logger.info("Goto Slip(CD=" + code + ") details page from slip reservation details page.");
		resDetailsPage.clickSlipLink(code, name);
		ajax.waitLoading();
		slipDetailsPage.waitLoading();
	}
 	
	public void gotoSlipResDetailsPageFromSlipDetailsPage() {
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		logger.info("Go back to slip reservation details page from slip details page.");
		slipDetailsPage.clickCancel();
		ajax.waitLoading();
		resDetailsPage.waitLoading();
	}
	
	private void gotoSlipResDetailsPageFromListPage(boolean isSkipFulfill, Customer cust) {
		OrmsSlipAlertsPage alertPage = OrmsSlipAlertsPage.getInstance();
		OrmsAlertPage custAlertPg = OrmsAlertPage.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage
				.getInstance();
		OrmsSlipReservationDetailsPage reservationDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsAddAndSearchListEntryPage listEntrySearchPg = OrmsAddAndSearchListEntryPage.getInstance();
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsEnterBoatLengthWidget boatLengthWidget = OrmsEnterBoatLengthWidget.getInstance();
		OrmsConfirmDialogWidget confirmPg = OrmsConfirmDialogWidget.getInstance();
		
		listPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(boatLengthWidget, custSearchPage,
				reservationDetailsPage, listEntrySearchPg, alertPage,confirmPg);
		if(page == confirmPg){//Shane: used for seasonal slip transfer, slip contract length change confirm page
			confirmPg.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(boatLengthWidget,custSearchPage, alertPage, reservationDetailsPage, listEntrySearchPg);
		}
		
		if(page == boatLengthWidget) {
			if(!StringUtil.isEmpty(cust.boat.getLength())) {
				boatLengthWidget.setBoatLength(cust.boat.getLength());
				boatLengthWidget.clickOK();
				ajax.waitLoading();
				page = browser.waitExists(custSearchPage, alertPage, reservationDetailsPage, listEntrySearchPg);
			} else throw new ErrorOnPageException("Please specify the Boat Length.");
		}
		
		if (page == alertPage) {
			alertPage.clickOK();
			page = browser.waitExists(custSearchPage, reservationDetailsPage, listEntrySearchPg);
		}
		if (page == listEntrySearchPg){
			if(isSkipFulfill){
				listEntrySearchPg.clickSkipFulfill();
				ajax.waitLoading();
				page = browser.waitExists(custSearchPage, reservationDetailsPage);
			}else{
				//TODO
			}
		}
		if (page == custSearchPage) {
			custSearchPage.searchCust(cust.fName, cust.lName, cust.email);
			custSearchPage.selectCust(cust.lName);
			page = browser.waitExists(custAlertPg, reservationDetailsPage);
			if(page == custAlertPg){
				custAlertPg.clickOK();
			}
			reservationDetailsPage.waitLoading();
		}
	}
	
	public Object makeSlipToResDetailsPageFromListPage(SlipInfo slip) {
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsSlipAlertsPage alertPage = OrmsSlipAlertsPage.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage
				.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsAddAndSearchListEntryPage listEntrySearchPg = OrmsAddAndSearchListEntryPage.getInstance();
		OrmsEnterBoatLengthWidget boatLengthWidget = OrmsEnterBoatLengthWidget.getInstance();
		OrmsConfirmDialogWidget confirmPg = OrmsConfirmDialogWidget.getInstance();
		OrmsOverrideRulePinWidget ruleWidget = OrmsOverrideRulePinWidget.getInstance();
		
		logger.info("Make Slip(#=" + slip.getCode()
				+ ") to Reservation details page from list page.");
//		if(!StringUtil.isEmpty(slip.getReservationType()) && slip.getReservationType().equalsIgnoreCase(SLIP_RESERVATION_TYPE_SEASONAL))
//		{
//			//for seasonal slip, we do not enter into slip detail page...
//			if(StringUtil.notEmpty(slip.getArrivalDate()))
//			{
//				listPage.setArrivalOfSlip(slip.getArrivalDate());
//				ajax.waitLoading();
//			}			
//			listPage.clickOK();
//			ajax.waitLoading();
//		}else{
			gotoSlipDetailsPageFromListPage(slip);
			if(!StringUtil.isEmpty(slip.getArrivalDate())) {//Quentine[12/3/2013] seasonal slip need to slip details page to update arrival date
				detailsPage.setArrivalDate(slip.getArrivalDate());
				ajax.waitLoading();
			}
			if (!StringUtil.isEmpty(slip.getDepartureDate()) && detailsPage.isDepartureDateTextFieldExists()) {
				detailsPage.setDepartureDate(slip.getDepartureDate());
				ajax.waitLoading();
			} else {
				if(StringUtil.isEmpty(slip.getDepartureDate())){ //For seasonal reservation, there is not departure date text field exist but do not need to reset departure date
					slip.setDepartureDate(detailsPage.getDepartureDate());
				}
			}
			if(NumberUtil.isGreaterThanZero(slip.getNights()) && detailsPage.isNightsExists()) {
				detailsPage.setNights(slip.getNights());
				ajax.waitLoading();
			}
			
			detailsPage.clickOK();
			ajax.waitLoading();
//		}
		return browser.waitExists(boatLengthWidget, custSearchPage,
				resDetailsPage, listEntrySearchPg, alertPage, confirmPg, ruleWidget,detailsPage);
	}
	
	public void makeSlipToResDetailsPageFromListPage(SlipInfo slip,
			Customer cust) {
		OrmsSlipAlertsPage alertPage = OrmsSlipAlertsPage.getInstance();
		OrmsAlertPage custAlertPg = OrmsAlertPage.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage
				.getInstance();
		OrmsSlipReservationDetailsPage reservationDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsAddAndSearchListEntryPage listEntrySearchPg = OrmsAddAndSearchListEntryPage.getInstance();
		OrmsEnterBoatLengthWidget boatLengthWidget = OrmsEnterBoatLengthWidget.getInstance();
		OrmsConfirmDialogWidget confirmPg = OrmsConfirmDialogWidget.getInstance();
		OrmsOverrideRulePinWidget ruleWidget = OrmsOverrideRulePinWidget.getInstance();
		Object page = makeSlipToResDetailsPageFromListPage(slip);
		
		if(page == ruleWidget){
			ruleWidget.enterPIN(TestProperty.getProperty("app.payPin"));
			ruleWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(confirmPg,boatLengthWidget,custSearchPage, alertPage, reservationDetailsPage, listEntrySearchPg);
		}
		if(page == confirmPg){//Shane: used for seasonal slip transfer, slip contract length change confirm page
			confirmPg.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(boatLengthWidget,custSearchPage, alertPage, reservationDetailsPage, listEntrySearchPg);
		}
		
		if(page == boatLengthWidget) {
			if(!StringUtil.isEmpty(cust.boat.getLength())) {
				boatLengthWidget.setBoatLength(cust.boat.getLength());
				boatLengthWidget.clickOK();
				ajax.waitLoading();
				page = browser.waitExists(custSearchPage, alertPage, reservationDetailsPage, listEntrySearchPg);
			} else throw new ErrorOnPageException("Please specify the Boat Length.");
		}
		
		if (page == alertPage) {
			alertPage.clickOK();
			page = browser.waitExists(custSearchPage, reservationDetailsPage, listEntrySearchPg);
		}
		if (page == listEntrySearchPg){
			if(slip.isSkipFulfill()){
				listEntrySearchPg.clickSkipFulfill();
				ajax.waitLoading();
				page = browser.waitExists(custSearchPage, reservationDetailsPage);
			}else{
				//TODO
			}
		}
		if (page == custSearchPage) {
			custSearchPage.searchCust(cust.fName, cust.lName, cust.email);
			custSearchPage.selectCust(cust.lName);
			page = browser.waitExists(custAlertPg, reservationDetailsPage);
			if(page == custAlertPg){
				custAlertPg.clickOK();
			}
			reservationDetailsPage.waitLoading();
		}
	}
	
	public void setupSlipReservationInfoInResDetailsPage(String actionReason, Customer cust){
		this.setupSlipReservationInfoInResDetailsPage(actionReason, cust, false);
	}
	
	public void setupSlipReservationInfoInResDetailsPage(String actionReason, Customer cust, boolean isCheckin) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		SlipReservationInfo res = new SlipReservationInfo();
		res.customer = cust;
		logger.info("Setup slip reservation details info in details page..");
		resDetailsPage.setupSlipResInfo(actionReason, res, isCheckin);
	}
	
	public void verifySlipReservationInfoInResDetailsPage(Customer cust) {
		SlipReservationInfo res = new SlipReservationInfo();
		res.customer = cust;
		this.verifySlipResDetailInDetailPg(res);
	}
	
	public void verifySlipResDetailInDetailPg(SlipReservationInfo res){
		OrmsSlipReservationDetailsPage detailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		boolean result = detailsPage.compareSlipReservationDetailsInfo(res);
		if(!result) {
			throw new ErrorOnPageException("Slip reservation details info is not correct.");
		} else logger.info("Slip reservation details info is correct.");
	}
	
	public void makeSlipToCartFromResDetailsPage(String actionReason, Customer cust){
		makeSlipToCartFromResDetailsPage(actionReason, StringUtil.EMPTY, cust, false);
	}
	
	public void makeSlipToCartFromResDetailsPage(String actionReason,SlipReservationInfo res){
		makeSlipToCartFromResDetailsPage(actionReason, StringUtil.EMPTY, res, false);
	}
	
	/**
	 * this keyword is mainly setting details info in slip reservation details
	 * page
	 * 
	 * @param actionReason
	 * @param cust
	 * @param custBoat
	 */
	public void makeSlipToCartFromResDetailsPage(String actionReason,String childslip,
			Customer cust, boolean isCheckin) {
		
		logger.info("Make slip reservation from res details to cart.");
		this.setupSlipReservationInfoInResDetailsPage(actionReason, cust, isCheckin);
		fromSlipResDetailToOrderCart(childslip,"");//if need to apply discount, refer to makeSlipToCartFromResDetailsPage(String actionReason,String childslip,SlipReservationInfo res,boolean isCheckIn) method
	}
	
	public void makeSlipToCartFromResDetailsPage(String actionReason,String childslip,SlipReservationInfo res,boolean isCheckIn){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		
		logger.info("Make slip reservaton from Res Detail to order cart.");
		
		resDetailsPage.setupSlipResInfo(actionReason, res, isCheckIn);
		fromSlipResDetailToOrderCart(childslip,res.discountName);
	}
	
	public void fromSlipResDetailToOrderCart(String childslip,String discount){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsSlipAutoSelectDiscountPage selectDiscountPage = OrmsSlipAutoSelectDiscountPage.getInstance();
		OrmsSlipListPage slipListPg=OrmsSlipListPage.getInstance();
		OrmsOverrideRulePinWidget ruleWidget = OrmsOverrideRulePinWidget.getInstance();
		OrmsCreditCardInfoValidationWidget ccValidationWidget = OrmsCreditCardInfoValidationWidget.getInstance();
		
		resDetailsPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(ccValidationWidget, cartPage,selectDiscountPage,ruleWidget,slipListPg, resDetailsPage);
		if(page == ccValidationWidget){
			// do nothing, just another ajax.
			ajax.waitLoading();
			cartPage.waitLoading();
		}
		if(page ==slipListPg ){
			if(StringUtil.notEmpty(childslip)){
				slipListPg.selectSlipByCode(childslip);
			}else{
				throw new ErrorOnPageException("Please set a specific slip to check in... ");
			}
			slipListPg.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(selectDiscountPage,cartPage);
		}
		if(page == ruleWidget){
			ruleWidget.enterPIN(TestProperty.getProperty("app.payPin"));
			ruleWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(selectDiscountPage,cartPage);
		}
		if(page == selectDiscountPage) {
			if(StringUtil.notEmpty(discount)){
				selectDiscountPage.selectDiscountSchedule(discount);
			}
			selectDiscountPage.clickOK();
			ajax.waitLoading();
			cartPage.waitLoading();
		}
	}

	public void searchSlipToListPage(SlipInfo slip) {
		OrmsSlipSearchPage searchPage = OrmsSlipSearchPage.getInstance();
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsAlertPage alertPg = OrmsAlertPage.getInstance();

		logger.info("Search Slip(#=" + slip.getCode() + ") info.");
		searchPage.searchSlip(slip);
		Object pages = browser.waitExists(searchPage,listPage,alertPg);
		if(alertPg == pages){
			alertPg.clickOK();
			browser.waitExists(searchPage,listPage);
		}
	}
	
	public void gotoFacilityDetailPgFromSlipListPg(String parkName){
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsMarinaDetailsPgae marinaDetailPg = OrmsMarinaDetailsPgae.getInstance();
		listPage.clickFacilityNameLink(parkName);
		marinaDetailPg.waitLoading();
	}
	
	
	/**
	 * go to Facility Detail Page From slip reservation detail Page
	 * @param parkName
	 */
	public void gotoFacilityDetailPgFromSlipResDetailPg(String parkName){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsMarinaDetailsPgae marinaDetailPg = OrmsMarinaDetailsPgae.getInstance();
		resDetailsPage.clickMarinaName(parkName);
		marinaDetailPg.waitLoading();
	}
	
	/**
	 * This method is just suitable in CM,OM
	 */
	public void searchSlipToMarinaResultPage(SlipInfo slip){
		OrmsMarinaResultsPgae marinaResultPg = OrmsMarinaResultsPgae.getInstance();
		OrmsSlipSearchPage searchPage = OrmsSlipSearchPage.getInstance();
		logger.info("Search slip with out specific marina or no result found for the slip in the specific slip, it will goto marina result page");
		searchPage.searchSlip(slip);
		marinaResultPg.waitLoading();
	}
	
	/**
	 * Usually can be used in day use,float in flows for field or marina manager
	 * @param slip
	 * @param cust
	 */
	public void searchSlipFromListToSlipDetail(SlipInfo slip,Customer cust){
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsEnterBoatLengthWidget boatLenWidgt = OrmsEnterBoatLengthWidget.getInstance();
		OrmsSlipAlertsPage slipAlertsPage = OrmsSlipAlertsPage.getInstance();
		
		listPage.searchSlip(slip, false);
		selectSlipOnSlipListPage(slip.getCode());
		
		if(boatLenWidgt.exists()) {
			if(!StringUtil.isEmpty(cust.boat.getLength())) {
				boatLenWidgt.setBoatLength(cust.boat.getLength());
				boatLenWidgt.clickOK();
				ajax.waitLoading();
				browser.waitExists(custSearchPage, slipAlertsPage, resDetailPage);
			} else throw new ErrorOnPageException("Please specify the Boat Length.");
		}
		if (slipAlertsPage.exists()) {
			slipAlertsPage.clickOK();
			browser.waitExists(custSearchPage, resDetailPage);
		}
		if (custSearchPage.exists()) {
			custSearchPage.searchCust(cust.fName, cust.lName, cust.email);
			custSearchPage.selectCust(cust.lName);
			resDetailPage.waitLoading();
		}
	}
	
	public void advancedSearchSlipToListPage(SlipInfo slip) {
		OrmsSlipSearchPage searchPage = OrmsSlipSearchPage.getInstance();
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsMarinaDetailsPgae marinaDetailPg = OrmsMarinaDetailsPgae.getInstance();
		logger.info("Search Slip(#=" + slip.getCode() + ") info.");
		searchPage.advancedSearchSlip(slip);
		browser.waitExists(searchPage,listPage, 
				marinaDetailPg /*this is for call manager when no result found*/);
	}

	/**
	 * this work flow starts from Top Menu page, ends with Order Cart page
	 * 
	 * @param slip
	 * @param cust
	 */
	public void makeSlipReservationToCart(SlipInfo slip, Customer cust) {
		searchSlipToListPage(slip);
		makeSlipToResDetailsPageFromListPage(slip, cust);
		makeSlipToCartFromResDetailsPage(StringUtil.EMPTY, cust);
	}
	
	public void gotoSlipSearchPgFromHomePg(){
//		MrnMgrHomePage homePg = MrnMgrHomePage.getInstance();
		OrmsSlipSearchPage searchPg = OrmsSlipSearchPage.getInstance();
		
		logger.info("Goto Slip Search Page From Home Page.");
		
//		homePg.clickSlipReservation();
		searchPg.waitLoading();
	}
	
	public void makeSlipReservationToCart(SlipReservationInfo... ress) {
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		
		this.gotoSlipSearchPgFromHomePg();
		//TODO
		SlipInfo slip = new SlipInfo();
		slip = ress[0].getSlip();
		String code = slip.getCode();
		slip.setCode(null);
		
		searchSlipToListPage(slip);
		
		ress[0].getSlip().setCode(code);
		for(SlipReservationInfo res : ress) {
			listPage.selectSlipByCode(res.getSlip().getCode());
		}
		this.gotoSlipResDetailsPageFromListPage(false, ress[0].customer);
		
		for(SlipReservationInfo res : ress) {
			this.makeSlipToCartFromResDetailsPage(null, res);
		}
	}
	
	public void makeSlipReservationToCart(SlipReservationInfo res){
		this.makeSlipReservationToCart(res,false);
	}
	
	
	public void makeSlipResToListPgFromHomePg(SlipReservationInfo res){
		gotoSlipSearchPgFromHomePg();
		searchSlipToListPage(res.getSlip());
	}
	
	public void makeSlipResToDetailFromHomePg(SlipReservationInfo res){
		makeSlipResToListPgFromHomePg(res);
		makeSlipToResDetailsPageFromListPage(res.getSlip(), res.customer);
	}
	
	public void makeSlipReservationToCart(SlipReservationInfo res,boolean isCheckIn){
		makeSlipResToDetailFromHomePg(res);
		makeSlipToCartFromResDetailsPage(StringUtil.EMPTY,StringUtil.EMPTY, res,isCheckIn);
	}
	
	public String addNoteAlertFromResDetailPage(NoteAndAlertInfo alertInfo) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsNoteAlertDetailPage noteAlertDetailPg = OrmsNoteAlertDetailPage.getInstance();
		
		logger.info("Start to add a new" + alertInfo.type + ".");
		
		resDetailsPage.clickAddNoteAndAlert();
		noteAlertDetailPg.waitLoading();
		
		noteAlertDetailPg.setupNoteAlertInfo(alertInfo);
		noteAlertDetailPg.clickApply();
		ajax.waitLoading();
		noteAlertDetailPg.waitLoading();
		String id = noteAlertDetailPg.getNoteOrAlertID();
		
		noteAlertDetailPg.clickOK();
		ajax.waitLoading();
		resDetailsPage.waitLoading();
		
		return id;
	}
	
	/**
	 * This method goes to reservation noteAlert list from reservation detail page.
	 * It clicked "note&alert" at reservation detail page.
	 * */
	public void gotoSlipResNoteAlertListPageFromSlipResDetailPage() {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsSlipReservationNoteAlertListPage resNAListPg = OrmsSlipReservationNoteAlertListPage.getInstance();
		logger.info("Go to Slip reservation note and alert list page.");
		resDetailsPage.clickNotesAndAlert();
		resNAListPg.waitLoading();	
	}
	
	public void gotoNoteAlertDetailPageFromSlipReservationNoteAlertListPage(String id){
		OrmsSlipReservationNoteAlertListPage resNAListPg = OrmsSlipReservationNoteAlertListPage.getInstance();
		OrmsSlipReservationNoteAlertDetailPage noteAlertDetailPg = OrmsSlipReservationNoteAlertDetailPage.getInstance();
		
		logger.info("Go to note alert detail page from slip reservation note alert list page. ID = " + id);
		resNAListPg.clickNoteAlertID(id);
		ajax.waitLoading();
		noteAlertDetailPg.waitLoading();
	}
	
	public void gotoSlipResNoteAlertListPageFromSlipResNoteAlertDetailPageByClickCancel(){
		OrmsSlipReservationNoteAlertListPage resNAListPg = OrmsSlipReservationNoteAlertListPage.getInstance();
		OrmsSlipReservationNoteAlertDetailPage noteAlertDetailPg = OrmsSlipReservationNoteAlertDetailPage.getInstance();
		logger.info("Go to Note Alert list page from slip res note alert detail page by click cancel button.");
		
		noteAlertDetailPg.clickCancel();
		ajax.waitLoading();
		resNAListPg.waitLoading();
	}
	
	/**
	 * make a check in slip reservation.
	 * @param slip
	 * @param cust
	 */
	public void makeFloatinSlipResToCart(SlipInfo slip, Customer cust) {
		searchSlipToListPage(slip);
		makeSlipToResDetailsPageFromListPage(slip, cust);
		makeSlipToCartFromResDetailsPage(StringUtil.EMPTY,StringUtil.EMPTY, cust, true);
	}
	/**
	 * float in NSS slip reservation
	 * @param nssslip
	 * @param cust
	 */
	public void makeFloatinNSSSlipResToCart(NSSSlipInfo nssslip, Customer cust) {
		searchSlipToListPage(nssslip);
		makeSlipToResDetailsPageFromListPage(nssslip, cust);
		makeSlipToCartFromResDetailsPage(StringUtil.EMPTY,nssslip.getcheckinChildCode(), cust, true);
	}
	
	public void transferSlipToCart(SlipInfo toSlip, Customer cust) {
		transferSlipToCart(toSlip, cust, false);
	}
	
	public void transferSlipToCart(SlipInfo toSlip, Customer cust, String resNum, SlipInfo fromSlip) {
		transferSlipToCart(toSlip, cust, false, resNum, fromSlip);
	}
	
	public void transferSlipToCart(SlipInfo toSlip, Customer cust, boolean isCheckin) {
		transferSlipToCart(toSlip, cust, isCheckin, null, null);
	}
	
	public void transferSlipToCart(SlipInfo toSlip, Customer cust, boolean isCheckin, String resNum, SlipInfo fromSlip) {
		transferSlipToResDetailsPage(toSlip, cust, resNum, fromSlip);
		makeSlipToCartFromResDetailsPage(toSlip.getActionReason(), StringUtil.EMPTY, cust, isCheckin);
	}
	
	public void transferSlipToResDetailsPage(SlipInfo toSlip, Customer cust, String resNum, SlipInfo fromSlip) {
		transferToSlipListPage(toSlip, resNum, fromSlip);
		makeSlipToResDetailsPageFromListPage(toSlip, cust);
	}
	
	public void transferSlipToResDetailsPage(SlipInfo toSlip, Customer cust) {
		transferSlipToResDetailsPage(toSlip, cust, null, null);
	}
	
	/**
	 * For rafting slip reservation, when doing transfer, it'll go to slip list page directly which is different
	 * to the other kind of slip reservation
	 * @param toSlip
	 * @param cust
	 * @param resNum
	 */
	public void transferRaftingSlipToCart(SlipInfo toSlip, Customer cust, String resNum){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();

		logger.info("Transfer Slip Res(#-" + (StringUtil.isEmpty(resNum) ? resDetailsPage.getResNum() : resNum)
				+ ") to slip list page.");
		resDetailsPage.clickTransfer();
		ajax.waitLoading();
		listPage.waitLoading();
		listPage.searchSlip(toSlip, true);
		makeSlipToResDetailsPageFromListPage(toSlip, cust);
		makeSlipToCartFromResDetailsPage(toSlip.getActionReason(), StringUtil.EMPTY, cust, true);
	}
	
	/**
	 * transfer to NSS slip
	 * @param toNSSSlip
	 * @param cust
	 */
	public void transferSlipToCart(NSSSlipInfo toNSSSlip, Customer cust) {
		transferSlipToCart(toNSSSlip, cust, false);
	}
	
	/**
	 * transfer to an NSS Slip, 
	 * 	1. if in FM/MM, need to specify an specific NSS Child slip to transfer to;
	 * 2. if in CM/OM, just transfer to NSS parent group
	 * @param toNSSSlip
	 * @param cust
	 * @param isCheckin
	 */
//	public void transferSlipToCart(NSSSlipInfo toNSSSlip, Customer cust, boolean isCheckin) {
//		transferToSlipListPage(toNSSSlip);
//		makeSlipToResDetailsPageFromListPage(toNSSSlip, null);
//		
//		String nssChild = StringUtil.EMPTY;
//		if(this instanceof FieldManager || this instanceof MarinaManager) {
//			nssChild = toNSSSlip.getcheckinChildCode();//if transfer SS to NSS in FM/MM, you need to specify a specific NSS Child code to transfer to
//		}
//		makeSlipToCartFromResDetailsPage(toNSSSlip.getActionReason(), nssChild, cust, isCheckin);
//	}
	
	public String makeSlipToResDetailsPageFromSlipDetailsPage(String arrival,
			int months) {
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsAlertPage alertPage = OrmsAlertPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();

		slipDetailsPage.updateDates(arrival, months);
		String newDepartureDate = slipDetailsPage.getDepartureDate();
		slipDetailsPage.clickOK();
		Object page = browser.waitExists(resDetailsPage,alertPage);
		if (page == alertPage) {
			alertPage.clickOK();
			resDetailsPage.waitLoading();
		}

		return newDepartureDate;
	}

	/**
	 * This for transient slip
	 * @param arrival
	 * @param departure
	 * @param nights
	 */
	public void makeSlipToReservationDetailsPageFromSlipDetailsPage(
			String arrival, String departure, int nights) {
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsAlertPage alertPage = OrmsAlertPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();

		slipDetailsPage.updateDates(arrival, departure, nights);
		slipDetailsPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(alertPage, resDetailsPage,slipDetailsPage);
		if (page == alertPage) {
			alertPage.clickOK();
			resDetailsPage.waitLoading();
		}
	}
	
	/**
	 * This for lease slip
	 * @param arrival
	 * @param months
	 */
	public void makeSlipToReservationDetailsPageFromSlipDetailsPage(
			String arrival, int months) {
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsAlertPage alertPage = OrmsAlertPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();

		slipDetailsPage.updateDates(arrival, months);
		slipDetailsPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(alertPage, resDetailsPage,slipDetailsPage);
		if (page == alertPage) {
			alertPage.clickOK();
			resDetailsPage.waitLoading();
		}
	}
	
	public void gotoRevDetailPageFromSlipDetailPg(){
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		slipDetailsPage.clickCancel();
		ajax.waitLoading();
		resDetailsPage.waitLoading();
	}

	public void changeSlipDateToSlipDetailsPage() {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsSlipDetailsPage slipDetailsPage = OrmsSlipDetailsPage.getInstance();

		logger.info("Change Date to slip details page.");
		resDetailsPage.clickDateChange();
		ajax.waitLoading();
		slipDetailsPage.waitLoading();
	}

	public void verifyChangeReservationDatesInSlipDetailsPage(String resNum, SlipInfo changeFromSlipInfo) {
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();
		
		detailsPage.verifyChangeReservationDatesSlipInfo(resNum, changeFromSlipInfo);
	}
	
	public String changeSlipDateToCart(String newArrival, int newMonths,
			BoatInfo boat) {
		changeSlipDateToSlipDetailsPage();
		String newDeparture = makeSlipToResDetailsPageFromSlipDetailsPage(
				newArrival, newMonths);
		Customer cust = new Customer();
		cust.boat = boat;
		makeSlipToCartFromResDetailsPage(null, cust);

		return newDeparture;
	}

	/**
	 * This is for lease change date
	 * @param newArrival
	 * @param newMonths
	 * @param actionReason
	 * @param boat
	 * @return
	 */
	public String changeSlipDateToCart(String newArrival, int newMonths,
			String actionReason, BoatInfo boat) {
		changeSlipDateToSlipDetailsPage();
		String newDeparture = makeSlipToResDetailsPageFromSlipDetailsPage(
				newArrival, newMonths);
		Customer cust = new Customer();
		cust.boat = boat;
		makeSlipToCartFromResDetailsPage(actionReason, cust);

		return newDeparture;
	}
	
	public void changeSlipDateToResDetailsPage(String newArrival, String newDeparture, int newNights) {
		changeSlipDateToSlipDetailsPage();
		makeSlipToReservationDetailsPageFromSlipDetailsPage(newArrival, newDeparture, newNights);
	}
	
	/**
	 * This is for transient change date
	 * @param newArrival
	 * @param newDeparture
	 * @param nights
	 * @param boat
	 */
	public void changeSlipDateToCart(String newArrival, String newDeparture,
			int nights, String actionReason, BoatInfo boat) {
		changeSlipDateToSlipDetailsPage();
		makeSlipToReservationDetailsPageFromSlipDetailsPage(newArrival,
				newDeparture, nights);
		Customer cust = new Customer();
		cust.boat = boat;
		makeSlipToCartFromResDetailsPage(actionReason, cust);
	}
	
	public void changeSlipDateToCart(String newArrival, int nights) {
		changeSlipDateToCart(newArrival, null, nights, null, null);
	}

	public void changeSlipDateToCart(String newArrival, String newDeparture) {
		changeSlipDateToCart(newArrival, newDeparture, -1, null, null);
	}

	public void changeSlipResDetailsToCart(Customer cust) {
		changeSlipResDetailsToCart(cust, false);
	}
	
	/**
	 * Change slip reservation details info in Res Details page to order cart
	 * @param cust
	 * @param isCheckIn
	 */
	public void changeSlipResDetailsToCart(Customer cust, boolean isCheckIn) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		//OrmsSlipReservationSearchPage resSearchPage = OrmsSlipReservationSearchPage.getInstance();
		OrmsOrderCartPage orderCart = OrmsOrderCartPage.getInstance();
		
		this.setupSlipReservationInfoInResDetailsPage(null, cust);
		if(resDetailsPage.isCheckInObjectExist()) {
			if(isCheckIn) {
				resDetailsPage.selectCheckIn();
				ajax.waitLoading();
				if(resDetailsPage.isFastCheckInAvailable()) {
					resDetailsPage.selectRegularCheckIn();
				}
			} else {
				resDetailsPage.unselectCheckIn();
				ajax.waitLoading();
			}
		}
		resDetailsPage.clickOK();
		ajax.waitLoading();
		browser.waitExists(resDetailsPage, orderCart);
	}
	
	public String changeSlipResDetails(Customer cust) {
		this.setupSlipReservationInfoInResDetailsPage(null, cust);
		return validateResDetails();
	}
	
	public String changeSlipResDetails(SlipReservationInfo res){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		resDetailsPage.setupSlipResInfo(StringUtil.EMPTY,res,false);
		return validateResDetails(res.discountName);
	}
	public String validateResDetails(){
		return validateResDetails(StringUtil.EMPTY);//if you want to operate discount, call changeSlipResDetails(SlipReservationInfo res) method
	}
	public String validateResDetails(String discount){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsSlipReservationSearchPage resSearchPage = OrmsSlipReservationSearchPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		OrmsSlipAutoSelectDiscountPage selectDiscountPage = OrmsSlipAutoSelectDiscountPage.getInstance();
		
		resDetailsPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmWidget, resDetailsPage, resSearchPage,selectDiscountPage,cartPg);
		String toReturn = "";
		if(page == confirmWidget) {
			toReturn = confirmWidget.getErrorMsg();
			confirmWidget.clickOK();
			ajax.waitLoading();
			resDetailsPage.waitLoading();
		}
		
		if(page == resDetailsPage) {
			toReturn = resDetailsPage.getErrorMessage();
		}
		
		if(page == selectDiscountPage){
			if(StringUtil.notEmpty(discount)){
				selectDiscountPage.selectDiscountSchedule(discount);
				ajax.waitLoading();
			}
			selectDiscountPage.clickOK();
			ajax.waitLoading();
			cartPg.waitLoading();
		}
		
		return toReturn;
	}
	
	public void cancelSlipRes(OrmsPage startPage,String cancelReason, String cancelNote,
			String... resIDs){
		for (int i = 0; i < resIDs.length; i++) {
//			this.gotoSlipReservationSearchPage(startPage);
			this.gotoSlipReservationDetailsPageFromSearchPage(resIDs[i]);
			this.cancelSlipResToCart(cancelReason, cancelNote);
		}

		this.processOrderCart(new Payment());
	}
	
	public void cancelSlipResToCart(String reason, String note) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsCancelReservationPage cancelResPage = OrmsCancelReservationPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		OrmsConfirmDialogWidget confirmDialog = OrmsConfirmDialogWidget
				.getInstance();

		logger.info("Cancel Slip Res#-" + resDetailsPage.getResNum()
				+ " to Cart.");
		this.gotoCancelSlipResPageFromSlipResDetailPage();
		cancelResPage.selectReason(reason);
		cancelResPage.setNotes(note);
		cancelResPage.clickCompleteCancel();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialog, cartPage);
		if (page == confirmDialog) {
			confirmDialog.clickOK();
			ajax.waitLoading();
			cartPage.waitLoading();
		}
	}
	
	public void gotoCancelSlipResPageFromSlipResDetailPage(){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsCancelReservationPage cancelResPage = OrmsCancelReservationPage.getInstance();
		
		logger.info("Go to Cancel reservation page from reservation detail page.");
		resDetailsPage.clickCancelRes();
		ajax.waitLoading();
		cancelResPage.waitLoading();
	}
	
	public void gotoVoidSlipResPageFromSlipResDetailPage(){
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsVoidReservationPage voidResPage = OrmsVoidReservationPage.getInstance();
		
		logger.info("Go to void slip reservation page from slip reservaton detail page.");
		resDetailsPage.clickVoid();
		ajax.waitLoading();
		voidResPage.waitLoading();
	}

	public void voidSlipResToCart(String note) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsVoidReservationPage voidResPage = OrmsVoidReservationPage
				.getInstance();
		OrmsConfirmDialogWidget confirmDialog = OrmsConfirmDialogWidget
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();

		logger.info("Void Slip Res#-" + resDetailsPage.getResNum()
				+ " to Cart.");
		this.gotoVoidSlipResPageFromSlipResDetailPage();
		voidResPage.setNotes(note);
		voidResPage.clickCompleteVoid();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmDialog, cartPage);
		if (page == confirmDialog) {
			confirmDialog.clickOK();
			ajax.waitLoading();
			cartPage.waitLoading();
		}
	}

	/**
	 * This method executes the work flow for adding a new deposit for a specify
	 * finance session ID and it support either adjustment or non adjustment
	 * just controlled by the parameters
	 * 
	 * @param finID
	 *            one closed finance session ID
	 * @param pinNum
	 * @param cashTotalOnHand
	 * @param nonCashTotalOnHand
	 * @param note
	 * @return new created deposit ID
	 */
	public String addNewDeposit(String finID, String pinNum,
			String cashTotalOnHand, String[] nonCashTotalOnHand, String note) {
		OrmsFinSessionSearchPage ormsFinSessPg = OrmsFinSessionSearchPage.getInstance();
		OrmsEnterPinNumPopupPage pinPopupPg = OrmsEnterPinNumPopupPage
				.getInstance();
		OrmsDepositPage depositPg = OrmsDepositPage.getInstance();
		OrmsDepositDetailPage depDetailPg = OrmsDepositDetailPage.getInstance();
		logger.info("Start to create a new deposit.");

		ormsFinSessPg.clickDepositsTab();
		depositPg.waitLoading();
		depositPg.clickNewDeposit();

		Object page = browser.waitExists(pinPopupPg, depDetailPg);
		if (page == pinPopupPg) {
			pinPopupPg.enterPIN(pinNum);
			pinPopupPg.clickOK();
		}

		depDetailPg.waitLoading();
		if (depDetailPg.checkLastExists()) {
			depDetailPg.clickGoLast();
			depDetailPg.waitLoading();
		}
		depDetailPg.createNewDeposit(finID, cashTotalOnHand,
				nonCashTotalOnHand, note);
		page = browser.waitExists(pinPopupPg, depDetailPg);
		if (page == pinPopupPg) {
			pinPopupPg.enterPIN(pinNum);
			pinPopupPg.clickOK();
		}
		depDetailPg.waitLoading();
		String depositID = depDetailPg.getDepositID();
		logger.info("New Create DepositID is " + depositID + ".");
		return depositID;
	}

	/**
	 * Unassign POS Product by ID. Start after search the product in POS Product
	 * Setup page, and end at POS Product Setup page.
	 */
	public void unassignPOSProductByID(String posID) {
		OrmsPOSProductSetupPage posSearchPage = OrmsPOSProductSetupPage
				.getInstance();
		OrmsConfirmDialogWidget dialogPg = OrmsConfirmDialogWidget
				.getInstance();

		logger.info("Unassign pos product " + posID + " from field manager...");

		posSearchPage.unassignPOSProduct(posID);
		Object page = browser.waitExists(dialogPg, posSearchPage);
		if (page == dialogPg) {
			dialogPg.clickOK();
			ajax.waitLoading();
			posSearchPage.waitLoading();
		}
	}

	/**
	 * Unassign POS product. Starts before search the product in POS Product
	 * Setup page, and end at POS Product Setup page.
	 * 
	 * @param pos
	 */
	public void unassignPOSProduct(POSInfo pos) {
		OrmsPOSProductSetupPage posSearchPage = OrmsPOSProductSetupPage
				.getInstance();

		posSearchPage.searchPOSByName(pos.product);
		if (StringUtil.isEmpty(pos.productID)) {
			pos.productID = posSearchPage.getPOSProductID(pos.product);
		}
		this.unassignPOSProductByID(pos.productID);
	}

	/**
	 * Assign pos products;
	 * 
	 * @param pos
	 */
	public void assignPosProduct(POSInfo pos) {
		OrmsPOSProductSetupPage posSearchPage = OrmsPOSProductSetupPage
				.getInstance();

		logger.info("Assign pos products.");
		posSearchPage.searchPOSByName(pos.product);
		if(StringUtil.isEmpty(pos.productID)) {
			pos.productID = posSearchPage.getPOSProductID(pos.product);
		}
		posSearchPage.setupPOSDetails(pos);
		posSearchPage.selectProductID(pos.productID);
		posSearchPage.clickAssignSelectedPOSProducts();
		ajax.waitLoading();
		posSearchPage.waitLoading();
	}

	/**
	 * Go to POS Setup Details page by clicking the POS ID link on POS Product
	 * Setup page.
	 * 
	 * @param posID
	 * @author Lesley Wang
	 * @date Aug 1, 2012
	 */
//	public void gotoPOSSetupDetailsPgFromPOSProdSetupPg(String posID) {
//		OrmsPOSProductSetupPage posSetupPg = OrmsPOSProductSetupPage
//				.getInstance();
//		OrmsPOSProductSetupDetails detailsPg = OrmsPOSProductSetupDetails
//				.getInstance();
//
//		logger.info("Go to POS Setup Details page from POS Assignment Page...");
//		posSetupPg.clickProductID(posID);
//		detailsPg.waitLoading();
//	}

	/**
	 * Save or cancel the pos setup details. Start from POS Setup Details Page
	 * and end at POS Assignment page or pos setup details page.
	 * 
	 * @param isSaved
	 * @author Lesley Wang
	 * @date Aug 3, 2012
	 */
//	private void saveOrCancelPOSSetupDetails(boolean isSaved) {
//		OrmsPOSProductSetupPage posSetupPg = OrmsPOSProductSetupPage
//				.getInstance();
////		OrmsPOSProductSetupDetails detailsPg = OrmsPOSProductSetupDetails
////				.getInstance();
//
//		logger.info((isSaved ? "Save" : "Cancel") + " the pos setup details...");
//		if (isSaved) {
//			detailsPg.clickOK();
//		} else {
//			detailsPg.clickCancel();
//		}
//		browser.waitExists(posSetupPg, detailsPg);
//	}

	/**
	 * Edit POS Setup Details. Start from POS Setup Details Page and end at POS
	 * Assignment page or pos setup details page.
	 * 
	 * @param pos
	 * @param isSaved
	 * @author Lesley Wang
	 * @date Aug 1, 2012
	 */
//	public void editPOSSetupDetails(POSInfo pos, boolean isSaved) {
//		OrmsPOSProductSetupDetails detailsPg = OrmsPOSProductSetupDetails
//				.getInstance();
//
//		logger.info("Edit POS info in POS setup details page...");
//		if (!detailsPg.exists()) {
//			this.gotoPOSSetupDetailsPgFromPOSProdSetupPg(pos.productID);
//		}
//		detailsPg.setupPOSDetails(pos);
//		saveOrCancelPOSSetupDetails(isSaved);
//	}

//	public void editPOSSetupDetails(POSInfo pos) {
//		editPOSSetupDetails(pos, true);
//	}
//
//	public void cancelFromPOSSetupDetailsPage() {
//		this.saveOrCancelPOSSetupDetails(false);
//	}
	
	public void selectPOSToCart(POSInfo... poss) {
		OrmsPOSAddItemPage POSItemPg = OrmsPOSAddItemPage.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		OrmsPurchasePOSAdditionalInfoPage additionInfoPg = OrmsPurchasePOSAdditionalInfoPage.getInstance();
		
		for(POSInfo pos : poss) {
			logger.info("Select POS " + pos.product + " to cart.");
			POSItemPg.searchItem(pos.product);
			POSItemPg.waitLoading();
			POSItemPg.selectItem(pos);
			POSItemPg.waitLoading();
			POSItemPg.clickAddToCart();
			POSItemPg.waitLoading();
			POSItemPg.setPOSVariablePrice(pos.product, pos.price);
		}
		POSItemPg.clickGoToCart();
		Object page = browser.waitExists(additionInfoPg,orderCartPg);
		if(page == additionInfoPg){//TO DO,for multiple pos and mutilple pass numbers
			if(additionInfoPg.isPassNumExists()) {
				additionInfoPg.setPassNum(poss[0].passNums.get(0));
			} else if(additionInfoPg.isPermitNumExists()) {
				additionInfoPg.setPermitNum(poss[0].permitNum);
			}//TODO
			
			additionInfoPg.clickOk();
			ajax.waitLoading();
		}
		orderCartPg.waitLoading();
	}
	
//	public void makeListEntryResToListEntryResDetailPgFromAddSearchEntryPg(ListInfo listInfo,Customer cust,String slipResNum,boolean forDiffBoat){
//		OrmsAddAndSearchListEntryPage listEntrySearchPg = OrmsAddAndSearchListEntryPage.getInstance();
//		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage.getInstance();
//		OrmsListEntryReservationDetailPage listEntryResDetailPg = OrmsListEntryReservationDetailPage.getInstance();
////		MrnMgrSelectCurrentSlipReservationWidget selectResPg = MrnMgrSelectCurrentSlipReservationWidget.getInstance();
//		
//		logger.info("Make list entry reservation to list entry reservation detail page from from add search entry page.");
//		listEntrySearchPg.setAddListEntryInfo(listInfo);
//		listEntrySearchPg.clickAddToList();
//		ajax.waitLoading();
//		Object pages = browser.waitExists(listEntryResDetailPg, omCustSchPg);
//		if(pages == omCustSchPg){
//			omCustSchPg.waitLoading();
//			omCustSchPg.searchCust(cust.fName, cust.lName, cust.email);
//			omCustSchPg.selectCust(cust.lName);
//			pages = browser.waitExists(listEntryResDetailPg,selectResPg);
//		}
//		if(selectResPg == pages){
//			if(StringUtil.notEmpty(slipResNum)){
//				selectResPg.selectOrderNum(slipResNum);
//			}else{
//				selectResPg.selectOrderNum(0);
//			}
//			if(forDiffBoat){
//				selectResPg.clickForDifferentBoat();
//			}else{
//				selectResPg.clickOK();
//			}
//			
//			ajax.waitLoading();
//			listEntryResDetailPg.waitLoading();
//		}
//	}
	
//	public void makeListEntryResToListEntryResDetailPgFromAddSearchEntryPg(ListInfo listInfo,Customer cust,String slipResNum){
//		makeListEntryResToListEntryResDetailPgFromAddSearchEntryPg(listInfo,cust,slipResNum,false);
//	}
//	
	public void makeListEntryResToOrderCartPageFromListEntryResDetailPg(ListInfo listInfo){
		OrmsListEntryReservationDetailPage listEntryResDetailPg = OrmsListEntryReservationDetailPage.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Make list entry reservation to order cart page from list entry reservation detail page.");
		listEntryResDetailPg.setListInfo(listInfo);
		listEntryResDetailPg.clickOK();
		ajax.waitLoading();
		orderCartPg.waitLoading();	
	}
	
	public void makeListEntryResToOrderCartFromAddSearchEntryPage(ListInfo listInfo,Customer cust,String slipResNum,boolean forDiffBoat){
//		this.makeListEntryResToListEntryResDetailPgFromAddSearchEntryPg(listInfo, cust,slipResNum,forDiffBoat);
		this.makeListEntryResToOrderCartPageFromListEntryResDetailPg(listInfo);
	}
	
	public void makeListEntryResToOrderCartFromAddSearchEntryPage(ListInfo listInfo,Customer cust,String slipResNum){
		makeListEntryResToOrderCartFromAddSearchEntryPage(listInfo,cust,slipResNum,false);
	}
	
	public void addSlipResInOrderCartPg(){
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsSlipSearchPage searchSlipPg = OrmsSlipSearchPage.getInstance();
		
//		ordCartPg.clickAddSlipRes();
		ordCartPg.clickAddSlipReservation();
		ajax.waitLoading();
		searchSlipPg.waitLoading();
	}
	
	public void addSlipReservationToCart(SlipInfo slip, Customer cust) {
		logger.info("Add slip reservation to cart.");
		this.addSlipResInOrderCartPg();
		this.makeSlipReservationToCart(slip, cust);
	}
	
	public void removeOrder(String... resIDs) {
		removeOrder(true, resIDs);
	}
	
	public void removeOrder(boolean isNotSiteRes,String... resIDs) {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Remove order in order cart.");
		for(String resID : resIDs) {
			cartPage.selectOrderCheckBox(resID, isNotSiteRes);
		}
		cartPage.clickRemoveOrder();
		ajax.waitLoading();
		cartPage.waitLoading();
	}
	
	
	
	public void splitOrder(String resID) {
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Split Order(#=" + resID + ")");
		cartPage.selectOrderCheckBox(resID, true);
		cartPage.clickSplitOrder();
		ajax.waitLoading();
		cartPage.waitLoading();
	}
	
	/**
	 * verify transaction name in order cart page
	 * @param expectedTransaction
	 */
	public void verifyTransactionNameInOrderCart(String expectedTransaction) {
		OrmsOrderCartPage orderCart = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify if transaction name in order cart is correct as: " + expectedTransaction);
		if(!orderCart.verifyTransactionName(expectedTransaction)) {
			throw new ErrorOnPageException("Transaction name is NOT expected "+expectedTransaction);
		} else logger.info("Transaction name is correct as: " + expectedTransaction);
	}

	public void verifyOrderNumInOrderCart(String ordNum) {
		OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify if order num in order cart is correct as: " + ordNum);
		if(!MiscFunctions.compareResult("Order Num", ordNum, cart.getResNum())) {
			throw new ErrorOnPageException("Order num in order cart is NOT expected " + ordNum);
		}
	}
	
	/**
	 * Close a financial Session with or without adjustment
	 * @param finID
	 * @param cashTotalOnHand
	 * @param nonCashTotalOnHand
	 * @param note
	 */
	public void closeFinSession(String finID, String cashTotalOnHand, String[] nonCashTotalOnHand, String note) {
		OrmsFinSessionSearchPage searchPage = OrmsFinSessionSearchPage.getInstance();
		OrmsFinSessionDetailsPage detailsPage = OrmsFinSessionDetailsPage.getInstance();
		
		if (!StringUtil.isEmpty(cashTotalOnHand) && !StringUtil.isEmpty(note)) {
			searchPage.clickFinSession(finID);
			detailsPage.waitLoading();
			detailsPage.setCashTotalOnHand(cashTotalOnHand);
			if (null != nonCashTotalOnHand && nonCashTotalOnHand.length > 1) {
				detailsPage.setNonCashTotalOnHand(nonCashTotalOnHand);
			}
			detailsPage.setNote(note);
			detailsPage.clickCloseSession();
//			pinPopup.waitExists();
//			pinPopup.enterPIN(pin);
//			pinPopup.clickOK();
//			detailsPage.waitExists();
		} else {
			searchPage.clickOneCheckBox(finID);
			searchPage.clickCloseWithoutAdjus();
		}
	}
	
	public String closeFinSessionInSummaryPage(String pin, String cashTotalOnHand, String nonCashTotalOnHands[], String note) {
		OrmsFinSessionDetailsPage detailsPage = OrmsFinSessionDetailsPage.getInstance();
		OrmsEnterPinNumPopupPage pinPopup = OrmsEnterPinNumPopupPage.getInstance();
		
		logger.info("Close Fin Session(ID=" + detailsPage.getFinSessionID() + ") in fin session summary page.");
		String toReturn = "";
		if(!StringUtil.isEmpty(cashTotalOnHand)) {
			detailsPage.setCashTotalOnHand(cashTotalOnHand);
			if(detailsPage.isErrorMessageExists()) {
				toReturn = detailsPage.getErrorMessage();
				return toReturn;
			}
		}
		if(nonCashTotalOnHands != null && nonCashTotalOnHands.length > 0) {
			detailsPage.setNonCashTotalOnHand(nonCashTotalOnHands);
		}
		detailsPage.setNote(note);
		detailsPage.clickCloseSession();
		pinPopup.waitLoading();
		pinPopup.enterPIN(pin);
		pinPopup.clickOK();
		detailsPage.waitLoading();
		
		if(detailsPage.isErrorMessageExists()) {
			toReturn = detailsPage.getErrorMessage();
		}
		return toReturn;
	}
	
	public void gotoFinSessionDetailsPage(String id) {
		OrmsFinSessionSearchPage searchPage = OrmsFinSessionSearchPage.getInstance();
		OrmsFinSessionDetailsPage detailsPage = OrmsFinSessionDetailsPage.getInstance();
		
		searchPage.clickFinSession(id);
		detailsPage.waitLoading();
	}
	
	public void gotoFinSessionDetailsPageByLocation(String location) {
		OrmsFinSessionSearchPage searchPage = OrmsFinSessionSearchPage.getInstance();
		OrmsFinSessionDetailsPage detailsPage = OrmsFinSessionDetailsPage.getInstance();
	
		logger.info("Goto fin session summary page of location: " + location);
		String id = searchPage.getFinSessionIDByLocation(location);
		searchPage.clickFinSession(id);
		detailsPage.waitLoading();
	}
	
	public void updateFinSessionOpeningFloatAmount(String pin, String amount) {
		OrmsFinSessionSearchPage searchPage = OrmsFinSessionSearchPage.getInstance();
		OrmsFinSessionDetailsPage detailsPage = OrmsFinSessionDetailsPage.getInstance();
		OrmsEnterPinNumPopupPage pinNumPopup = OrmsEnterPinNumPopupPage.getInstance();
		
		logger.info("Update Fin Session opening float amount as: " + amount);
//		gotoFinSessionDetailsPage(searchPage.getOpenFinSessionID());
		detailsPage.setOpeningFloat(amount);
		detailsPage.clickSave();
		Object page = browser.waitExists(pinNumPopup, detailsPage);
		if(page == pinNumPopup) {
			pinNumPopup.enterPIN(pin);
			pinNumPopup.clickOK();
			detailsPage.waitLoading();
		}
		detailsPage.clickCancel();
		searchPage.waitLoading();
	}
	
	public void verifyFinSessionOpeningFloatInDB(String schema, String finSessID, String reversedOpeningFloatID, String amount, String statusID) {
		OpeningFloat of = getFinSessionOpeningFloat(schema, finSessID, reversedOpeningFloatID, amount);
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Fin Session ID", finSessID, of.getFinSessionID());
		result &= MiscFunctions.compareResult("Opening Float Amount", new BigDecimal(amount).setScale(2), new BigDecimal(of.getAmount()).setScale(2));
		result &= MiscFunctions.compareResult("Opening Float Status", statusID, of.getStatus());
		if(!StringUtil.isEmpty(reversedOpeningFloatID)) {
			result &= MiscFunctions.compareResult("Reversed Opening Float ID", reversedOpeningFloatID, of.getReversedFloatID());
		}
		
		if(!result) {
			throw new ErrorOnDataException("Fin Session Opening Float record is incorrect.");
		} else logger.info("Fin Session Opening Float record is correct.");
	}
	
	/**
	 * Goto and Verify Specific FinSession in FinSessionSummary Page
	 * @param finSessID
	 */
	public void gotoAndViewFinSessionSummary(String finSessID) {
		OrmsFinSessionSearchPage searchPage = OrmsFinSessionSearchPage.getInstance();
		OrmsFinSessionDetailsPage detailsPage = OrmsFinSessionDetailsPage.getInstance();
		
		logger.info("Goto and View Financial Session Summary For " + finSessID);
		List<String> finSessInfo = searchPage.getFinSessInfoByID(finSessID,false);
		FinSession fs = searchPage.turnVectorToFinSession(finSessInfo);
		searchPage.clickFinSession(finSessID);
		detailsPage.waitLoading();
		if (!detailsPage.checkFinSessionSummaryExistsAndOpen()) {
			logger.error("Open FinSession Summary Page Fail!");
			throw new ItemNotFoundException(
					"Open FinSession Summary Page Fail!");
		}
		FinSession fs2 = detailsPage.getFinSessionDetail();
		detailsPage.compareTwoFinSession(fs, fs2);
		detailsPage.verifyFinSessSummaryInfo(fs);
	}
	
	public void cancelSlipResToCart(){
		this.cancelSlipResToCart("Cancellation", "Automation Testing");
	}
	
	/**
	 * This method execute a work flow from reservation detail page to customer
	 * profile by click customer name
	 * 
	 * @param custName
	 */
	public void gotoCustomerProfile(String custName) {
		OrmsReservationDetailsPage resDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsCustomerDetailsPage custDetailPg = OrmsCustomerDetailsPage
				.getInstance();

		logger.info("Goto Customer Profile.");

		resDetailPg.clickCustomerName(custName);
		custDetailPg.waitLoading();
	}
	
	public void gotoCustDetailPage(Customer cust){
		CustomerSearch csearch = new CustomerSearch();
		OrmsCustomerSearchPage omCustSchPg = OrmsCustomerSearchPage
				.getInstance();
		OrmsCustomerDetailsPage omCustDetailPg = OrmsCustomerDetailsPage
				.getInstance();
		logger.info("Go to customer detail page");
		csearch.customerSearch(cust);
		omCustSchPg.gotoCustDetail(cust.lName);
		omCustDetailPg.waitLoading();
	}
	
	/**
	 * This method select a slip and click ok on the slip search result page, the end of the page may be the slip list page or customer 
	 * search page
	 * @param slipCode
	 */
	public void selectSlipOnSlipListPage(String slipCode){
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage
				.getInstance();
		OrmsSlipReservationDetailsPage resDetailPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsEnterBoatLengthWidget boatLenWidgt = OrmsEnterBoatLengthWidget.getInstance();
		OrmsSlipAlertsPage slipAlertsPage = OrmsSlipAlertsPage.getInstance();
//		MrnMgrVoidVoucherBeforeTransferPage voidVoucherReasonPage = MrnMgrVoidVoucherBeforeTransferPage.getInstance();
		OrmsAddAndSearchListEntryPage listEntryPage = OrmsAddAndSearchListEntryPage.getInstance();
		
		listPage.selectSlipByCode(slipCode);
		listPage.clickOK();
		ajax.waitLoading();
		// will go to void Voucher Reason Page or reservation detail page when transfer
		browser.waitExists( boatLenWidgt, slipAlertsPage, listPage, custSearchPage, resDetailPage, listEntryPage);
		// if boat length widget exist, need to set boat length
	}
	
	public void gotoSlipResSearchPgFromSlipDetailsPg() {
		OrmsSlipDetailsPage detailsPage = OrmsSlipDetailsPage.getInstance();
		OrmsSlipReservationSearchPage resSearchPage = OrmsSlipReservationSearchPage.getInstance();
		
		detailsPage.clickViewReservations();
		ajax.waitLoading();
		resSearchPage.waitLoading();
	}
	
	public void cancelFeeDetailsPageToCart() {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsFeeDetailsPage feeDetailsPg = OrmsFeeDetailsPage.getInstance();

		feeDetailsPg.clickCancel();
		ordCartPg.waitLoading();
	}
	
	/**
	 * This method goto custom noteAlert subpage from customer detail default page.
	 * */
	public void gotoCustomerNoteAlertPageFromDetailPage() {
		OrmsCustomerDetailsPage omDetailCustPg = OrmsCustomerDetailsPage
				.getInstance();
		OrmsNoteAlertListCommonPage custNAListPg = OrmsNoteAlertListCommonPage.getInstance();
		logger.info("Go to customer note and alert list page.");
		omDetailCustPg.clickCustomerNoteAlert();
		custNAListPg.waitLoading();		
	}
	
	/**This method used to add noteAlert for reservation/pos/customer/giftcard
	 * It starts from reservation noteAlert list page and end at noteAlert list page
	 * @param type
	 * @param note
	 * @param alertStart
	 * @param alertEnd
	 * @param active
	 */
	public String addNoteAlertFromListPage(String type, String content,
			String startdate, String enddate, boolean active) {
		OrmsNoteAlertListCommonPage noteAlertListPg = OrmsNoteAlertListCommonPage.getInstance();
		OrmsNoteAlertDetailPage noteAlertDetailPg = OrmsNoteAlertDetailPage.getInstance();	
		logger.info("Start to add a new" + type + ".");
		noteAlertListPg.clickAddNew();
		noteAlertDetailPg.waitLoading();
		noteAlertDetailPg.setUpNoteAlertInfo(type, content, startdate, enddate, active);
		noteAlertDetailPg.clickApply();
		ajax.waitLoading();
		noteAlertDetailPg.waitLoading();
		String id = noteAlertDetailPg.getNoteOrAlertID();
		noteAlertDetailPg.clickOK();
		ajax.waitLoading();
		noteAlertListPg.waitLoading();
		return id;
	}	
	
	/**
	 * Verify Time To Clear rule take effect or not through checking the specific non cash depositable payment types
	 * (includes 'Personal Check', 'Travellers Check', 'Certified Check', 'Money Order', etc)
	 * exists in the payment method drop down list
	 * @param ruleCondID
	 * @param verifiedPaymentType
	 * @param expectedResult
	 */
	public void verifyTimeToClearRuleAtOrderCartPage(String ruleCondID, String verifyPaymentType, boolean expectedResult) {
		OrmsOrderCartPage ormsOrdCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Verify Time To Clear Rule(rule# = " + ruleCondID + ") take effect or not.");

		boolean paymentTypeExistent = ormsOrdCartPg.checkPaymentTypeExists(verifyPaymentType);
		if(expectedResult != paymentTypeExistent){
			if(ormsOrdCartPg.checkPaymentTypeExists(verifyPaymentType)) {
				throw new ActionFailedException("Time To Clear Rule(rule# = " + ruleCondID + ") doesn't take effect.");
			}
		}

		logger.info("Time To Clear Rule(rule# = " + ruleCondID + ") take effect successfully.");
	}
	
	public void verifyExpectPaymentFunctionInCartPage( boolean isDisplayed,int index,int product) {
		OrmsOrderCartPage ormsOrdCartPg = OrmsOrderCartPage.getInstance();

		logger.info("Verify Expected Payment Function on the order cart page");
		if(isDisplayed != ormsOrdCartPg.isExpectPaymentCheckBoxExist(index,product) || isDisplayed !=ormsOrdCartPg.isExpectPaymentDateFieldExist(index,product)){
			throw new ErrorOnPageException("Expected Payment Function " + ( isDisplayed==true? "Should ":"Should not " )+"Displayed on the order cart page...");
		}
	}
	
	public void fulfillListEntryToListeEntrySearchPage(SlipInfo slip) {
		OrmsSlipListPage listPage = OrmsSlipListPage.getInstance();
		OrmsAddAndSearchListEntryPage listEntryPg = OrmsAddAndSearchListEntryPage.getInstance();

		logger.info("Fulfill list for slip order to list entry search page.");
		
		this.searchSlipToListPage(slip);
		listPage.setArrivalOfSlip(slip.getArrivalDate());
		ajax.waitLoading();
		listPage.clickWaitingListBySlipCode(slip.getCode());
		ajax.waitLoading();
		listEntryPg.waitLoading();
	}
	
	public void fulfillListEntryToResDetailsPageFromListEntrySearchPage(String ordNum) {
		OrmsAddAndSearchListEntryPage listEntryPg = OrmsAddAndSearchListEntryPage.getInstance();
		OrmsSlipReservationDetailsPage reservationDetailsPage = OrmsSlipReservationDetailsPage.getInstance();

		logger.info("Fulfill list for slip order to reservation details page ---- "+ordNum);
		
		listEntryPg.selectEntry(ordNum);
		ajax.waitLoading();
		listEntryPg.clickOK();
		ajax.waitLoading();
		// if there is error message, it will stay on listEntryPg
		browser.waitExists(listEntryPg, reservationDetailsPage);
	}
	
	public void fulfillListEntryToReservationDetailsPage(SlipInfo slip, String ordNum) {
		logger.info("Fulfill list for slip order to reservation details page ---- "+ordNum);
		this.fulfillListEntryToListeEntrySearchPage(slip);
		this.fulfillListEntryToResDetailsPageFromListEntrySearchPage(ordNum);
	}
	
	public void fulfillListEntryToOrderCartPg(String ordNum, SlipReservationInfo res) {
		logger.info("Fulfill list entry for list order: " + ordNum);
		this.fulfillListEntryToReservationDetailsPage(res.getSlip(), ordNum);
		this.makeSlipToCartFromResDetailsPage(null, res);
	}
	
	public void fulfillListEntryToOrderCartPg(SlipInfo slip, String ordNum, Customer cust)
	{
		fulfillListEntryToOrderCartPg(slip, ordNum,cust,false);
	}
	
	public void fulfillListEntryToOrderCartPg(SlipInfo slip, String ordNum, Customer cust,boolean walkin){
		logger.info("Fulfill list for slip order--"+ordNum);
		this.fulfillListEntryToReservationDetailsPage(slip, ordNum);
		makeSlipToCartFromResDetailsPage(StringUtil.EMPTY, StringUtil.EMPTY, cust, walkin);
	}
	
	public void undoFulfillmentAndVoidSlipReservationToCart(String reason)
	{
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
		.getInstance();
//		MrnMgrUndoFulfillmentAndVoidSlipPage undoPg = MrnMgrUndoFulfillmentAndVoidSlipPage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Undo fulfillment of slip order...");
		resDetailsPage.clickUndoFulfillment();
		ajax.waitLoading();
//		undoPg.waitLoading();
//		undoPg.setReason(reason);
//		undoPg.clickCompleteVoid();
		
		ajax.waitLoading();
		cartPage.exists();
	}
	
	public void voidListEntryToCart(String voidReason){
		OrmsListEntryReservationDetailPage listEntryDetailPg = OrmsListEntryReservationDetailPage.getInstance();
		OrmsVoidListEntryPage voidPg = OrmsVoidListEntryPage.getInstance();
		OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
		
		logger.info("Void List Entry to Cart");
		
		listEntryDetailPg.clickVoid();
		ajax.waitLoading();
		voidPg.waitLoading();
		voidPg.setVoidReason(voidReason);
		voidPg.clickCompleteVoid();
		ajax.waitLoading();
		cart.waitLoading();
	}
	
//	public void cancelListEntryToCart() {
//		cancelListEntryToCartPg("Cancellation", "Automation");
//	}
//	
//	public void cancelListEntryToCartPg(String reason, String note){
//		
//		OrmsListEntryReservationDetailPage listEntryDetailPg = OrmsListEntryReservationDetailPage.getInstance();
////		MrnMgrCancelListEntryPage cancelPg = MrnMgrCancelListEntryPage.getInstance();
//		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
//		
//		logger.info("Cancel list entry to cart page...");
//		listEntryDetailPg.clickCancel();
//		ajax.waitLoading();
//		cancelPg.waitLoading();
//		
//		cancelPg.selectCancelReason(reason);
//		cancelPg.setNotes(note);
//		cancelPg.clickCompleteCancel();
//		ajax.waitLoading();
//		
//		ordCartPg.waitLoading();		
//		
//		
//	}
	
	public void gotoSlipResDetailPgFromFeeDetailsPg() {
		OrmsSlipReservationDetailsPage slipResDetailPg = OrmsSlipReservationDetailsPage
				.getInstance();
		OrmsReservationFeesPage resFeeDetailsPg = OrmsReservationFeesPage.getInstance();
		
		resFeeDetailsPg.clickCancel();
		ajax.waitLoading();
		slipResDetailPg.waitLoading();
	}

	public void gotoTransmittalPage(){
//		FldMgrTopMenuPage fmTopMenuPg = FldMgrTopMenuPage.getInstance();
		OrmsFinSessionSearchPage ormsFinSessionPg = OrmsFinSessionSearchPage.getInstance();
		OrmsTransmittalsPage transmittalPage = OrmsTransmittalsPage.getInstance();
		
		logger.info("Go to transmittal page.");
		
//		fmTopMenuPg.clickFinancials();
		ormsFinSessionPg.waitLoading();
		ormsFinSessionPg.clickTransmittalsTab();
		transmittalPage.waitLoading();
	}
	
	public String addNewTransmittal(String totalAdjust, String traceNum, String notes, String pinNum, String... depositIDs){
		OrmsTransmittalMainPage transmittalMainPage = OrmsTransmittalMainPage.getInstance();
		OrmsTransmittalAddDepositsPage addDepositPage = OrmsTransmittalAddDepositsPage.getInstance();
		OrmsTransmittalSummaryPage summaryPage = OrmsTransmittalSummaryPage.getInstance();
		OrmsEnterPinNumPopupPage pinPage = OrmsEnterPinNumPopupPage.getInstance();
		OrmsTransmittalsPage transmittalPage = OrmsTransmittalsPage.getInstance();
		
		logger.info("Start to add new transmittal.");
		this.gotoTransmittalPage();
		
		transmittalPage.clickNewTransmittal();
		transmittalMainPage.waitLoading();
		// should go to add deposits page directly
		logger.info("Select deposits.");
		if(!addDepositPage.exists()){
			transmittalMainPage.clickAddDeposit();
			addDepositPage.waitLoading();
		}
		
		addDepositPage.selectDeposits(depositIDs);
		addDepositPage.clickIncludeTransmittal();
		ajax.waitLoading();
		summaryPage.waitLoading();
		

		logger.info("Setup some field in transmittal summary page.");
		if(StringUtil.notEmpty(totalAdjust)){
			summaryPage.setTransmittalCost(totalAdjust);
		}
		
		if(StringUtil.notEmpty(traceNum)){
			summaryPage.setTraceNum(traceNum);
		}
		
		if(StringUtil.notEmpty(notes)){
			summaryPage.setNotes(notes);
		}
		summaryPage.clickCreateTransmittal();
//		ajax.waitLoading();
		Browser.sleep(10);
		browser.waitExists(pinPage, summaryPage, transmittalMainPage);
		if (pinPage.exists()) {
			pinPage.enterPIN(pinNum);
			pinPage.clickOK();
			transmittalMainPage.waitLoading();
		}
		String transmittalID = transmittalMainPage.getTransmittalID();
		logger.info("Transmittal ID is "+transmittalID);
		return transmittalID;
	}
	
	public String addNewTransmittalWithoutAdjust(String pinNum, String...depositIDs){
		return this.addNewTransmittal(null, null, "Automation test", pinNum, depositIDs);
	}

	public String addNewTransmittalWithAdjust(String pinNum, String adjust, String...depositIDs){
		return this.addNewTransmittal(adjust, null, "Automation test", pinNum, depositIDs);
	}
	
	/**
	 * 
	 * @param tourname
	 * @Return void
	 * @Throws
	 */
	public void verifyAlertFormatInTicketSelectionPage(String tourname) {
		OrmsTicketSelectionPage selectionpage = OrmsTicketSelectionPage.getInstance();
		String msg = null;

		String title_0 = "Tour Alerts - " + tourname;
		String title_1 = selectionpage.getAlertTitle();

		logger.info("Verify Alert format....");
		if (!title_0.equals(title_1)) {
			msg = "Alert title format is wrong. \n";
		}

		if (selectionpage.isAlertTextAreaEditable()) {
			msg += "Alert Text Area should be disabled. \n";
		}

		if (!selectionpage.isAddNoteAlertButtonEnable()) {
			msg += "'Add Note/Alert button should be enable . ";
		}

		if (msg != null) {
			throw new ActionFailedException(msg);
		}
		logger.info("Verify alert format in ticket selection page successfully!");
	}
	
	public void undockSlip() {
		undockSlip(null);
	}
	
	/**
	 * undock slip in slip reservation details page
	 * @param slip
	 */
	public void undockSlip(String expectedDockingDate) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsUndockSlipReservationWidget undockWidget = OrmsUndockSlipReservationWidget.getInstance();
		
		resDetailsPage.clickUnDock();
		ajax.waitLoading();
		undockWidget.waitLoading();
		if(!StringUtil.isEmpty(expectedDockingDate)) {
			undockWidget.setExpectedDockingDate(expectedDockingDate);
		}
		undockWidget.clickOK();
		ajax.waitLoading();
		resDetailsPage.waitLoading();
	}
	
	public void dockSlip() {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		resDetailsPage.clickDock();
		ajax.waitLoading();
		resDetailsPage.waitLoading();
	}
	
	public void verifySlipOrderItemStatus(SlipInfo slip, String expectedStatus) {
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		
		logger.info("Verify the slip reservation order item status.");
		String actualStatus = resDetailsPage.getOrderItemStatus(slip);
		if(!MiscFunctions.compareResult("Order Item Status", expectedStatus, actualStatus)) {
			throw new ErrorOnPageException("Slip reservation order item status is NOT correct. Expected is: " + expectedStatus + ", but actual is: " + actualStatus);
		}
	}
	
	
	private void batchActionByResNums(String actionName, String schema, String... resNumber){
		OrmsSlipReservationSearchPage resSearchPage = OrmsSlipReservationSearchPage.getInstance();

		for(int i=0; i<resNumber.length; i++){
			String orderID = this.getOrderID(resNumber[i], schema);
			browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false), ".value", orderID);
			resSearchPage.waitLoading();
		}
		
		if("check in".equals(actionName)){
			resSearchPage.clickCheckIn();
		} else if("check out".equals(actionName)){
			resSearchPage.clickCheckOut();
		} else if("dock".equals(actionName)){
			resSearchPage.clickDock();
		}
		ajax.waitLoading();
		browser.waitExists(resSearchPage, OrmsOrderCartPage.getInstance());
	}
	
	
	public void batchCheckInByResNums(String schema, String... resNumber){
		this.batchActionByResNums("check in", schema, resNumber);
	}
	
	public void batchCheckOutByResNums(String schema, String... resNumber){
		this.batchActionByResNums("check out", schema, resNumber);
	}

	public void batchDockByResNums(String schema, String... resNumber){
		this.batchActionByResNums("dock", schema, resNumber);
	}
	
	public void applyBaseDiscountInCart(String order, String disctName, boolean isPermitOrSlip){
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsDiscountPage fmDiscountPg = OrmsDiscountPage.getInstance();

		fmOrdCartPg.applyDiscount(order, isPermitOrSlip);
		ajax.waitLoading();//Do not remove it, it was used for buy x get y discount
		fmDiscountPg.waitLoading();
		fmDiscountPg.applyBaseDiscount(disctName);
		fmDiscountPg.clickOK();
		ajax.waitLoading();
		fmOrdCartPg.waitLoading();
	}
	
	public void applyAdditionalDiscountInCart(String order, String disctName, boolean isPermitOrSlip){
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsDiscountPage fmDiscountPg = OrmsDiscountPage.getInstance();

		fmOrdCartPg.applyDiscount(order, isPermitOrSlip);
		ajax.waitLoading();//Do not remove it, it was used for buy x get y discount
		fmDiscountPg.waitLoading();
		fmDiscountPg.applyAdditionalDiscout(disctName);
		fmDiscountPg.clickOK();
		ajax.waitLoading();
		fmOrdCartPg.waitLoading();
	}
	
	public void applyDiscountInCart(String order, String disctName1, String disctName2, boolean isPermitOrSlip){
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		OrmsDiscountPage fmDiscountPg = OrmsDiscountPage.getInstance();

		fmOrdCartPg.applyDiscount(order, isPermitOrSlip);
		ajax.waitLoading();//Do not remove it, it was used for buy x get y discount
		fmDiscountPg.waitLoading();
		fmDiscountPg.applyBaseDiscount(disctName1);
		fmDiscountPg.applyAdditionalDiscout(disctName2);
		fmDiscountPg.clickOK();
		ajax.waitLoading();
		fmOrdCartPg.waitLoading();
	}
	
	public void gotoRuleValidationPageFromSiteDetailsPage() {
		OrmsSiteDetailsPage siteDetailsPage = OrmsSiteDetailsPage.getInstance();
		InvMgrSiteDetailPage invSiteDetailsPage = InvMgrSiteDetailPage.getInstance();
		OrmsRuleValidationPage validationPage = OrmsRuleValidationPage.getInstance();
	
		logger.info("Goto rule validation page from site details page.");
		if(this instanceof InventoryManager) {
			invSiteDetailsPage.clickValidateRules();
		} else {
			siteDetailsPage.clickValidateRules();
		}
		
		validationPage.waitLoading();
	}
	
	public String adjustFeeToPastPaid(String resID, boolean isNotSite, String note) {
		OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
		OrmsAdjustFeeToPastPaidWidget adjustWidget = OrmsAdjustFeeToPastPaidWidget.getInstance();
		
		logger.info("Adjust Fees to Past Paid in order cart page.");
		cart.selectOrderCheckBox(resID, isNotSite);
		cart.clickAdjustFee();
		ajax.waitLoading();
		adjustWidget.waitLoading();
		adjustWidget.setAdjustmentNote(note);
		adjustWidget.clickOK();
		ajax.waitLoading();
		cart.waitLoading();
		
		String toReturn = cart.getErrorMessage();
		return toReturn;
	}
	
	/**
	 * This method go to history page from order detail page
	 */
	public void gotoReservationHistoryPg(){
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
		.getInstance();
		OrmsReservationHistoryPage resHistoryPg = OrmsReservationHistoryPage.getInstance();
		
		logger.info("Go to reservation history page from reservation detail page...");
		resvDetailPg.clickHistory();
		resHistoryPg.waitLoading();
	}
	
	public void cancelFromHistoryPgToOrderDetailPg(){
		OrmsReservationDetailsPage resvDetailPg = OrmsReservationDetailsPage
				.getInstance();
		OrmsReservationHistoryPage resHistoryPg = OrmsReservationHistoryPage.getInstance();
		logger.info("Go to reservation detail page from reservation history page...");
		resHistoryPg.clickCancel();
		resvDetailPg.waitLoading();
	}
	
	public void gotoTicketOrderHistoryPg(){
		OrmsTicketOrderDetailsPage ticketOrderDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketOrderHistoryPage ticketOrderHistoryPg = OrmsTicketOrderHistoryPage.getInstance();
		
		logger.info("Go to ticket order history page from reservation detail page...");
		ticketOrderDetailPg.clickHistory();
		ticketOrderHistoryPg.waitLoading();
	}
	
	public void cancelFromTickeOrderHistoryPgToDetailPg(){
		OrmsTicketOrderDetailsPage ticketOrderDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsTicketOrderHistoryPage ticketOrderHistoryPg = OrmsTicketOrderHistoryPage.getInstance();
		logger.info("Go to ticket order detail page from reservation history page...");
		ticketOrderHistoryPg.clcikCancel();
		ticketOrderDetailPg.waitLoading();
	}
	
	public void gotoResDetailPageFromResHistoryPage(){
		OrmsReservationHistoryPage resHistoryPg = OrmsReservationHistoryPage.getInstance();
		OrmsSlipReservationDetailsPage resDetailsPage = OrmsSlipReservationDetailsPage
				.getInstance();
		logger.info("Go to reservation detail page from history page.");

		resHistoryPg.clickReturnToResDetailsBtn();
		ajax.waitLoading();
		resDetailsPage.waitLoading();
	}
	
	/**
	 * Go to cancel ticket page from order detail page
	 */
	public void gotoCancelTicketPg(){
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsCancelTicketOrderPage cancelPage = OrmsCancelTicketOrderPage
				.getInstance();
		detailsPage.clickCancelTickets();
		ajax.waitLoading();
		cancelPage.waitLoading();
	}
	
	public void cancelFromCancelOrder() {
		OrmsTicketOrderDetailsPage detailsPage = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsCancelTicketOrderPage cancelPage = OrmsCancelTicketOrderPage
				.getInstance();
		cancelPage.clickDontCancel();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoVoidOrderPg() {
		OrmsTicketOrderDetailsPage omTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsVoidTicketOrderPage omVoidTicketPg = OrmsVoidTicketOrderPage
				.getInstance();
		logger.info("Void ticket order to void order page.");
		omTicketDetailPg.clickVoidTickets();
		omVoidTicketPg.waitLoading();
	}
	
	public void cancelFromVoidOrder(){
		OrmsTicketOrderDetailsPage omTicketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsVoidTicketOrderPage omVoidTicketPg = OrmsVoidTicketOrderPage
				.getInstance();
		logger.info("Void ticket order to void order page.");
		omVoidTicketPg.clickDontVoid();
		omTicketDetailPg.waitLoading();
	}
	
	public void cancelFromFeeDetailsPage() {
		OrmsReservationFeesPage feeDetailsPage = OrmsReservationFeesPage.getInstance();
		OrmsReservationDetailsPage siteResDetailsPage = OrmsReservationDetailsPage.getInstance();
		OrmsSlipReservationDetailsPage slipResDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Cancel from reservation fee details page.");
		feeDetailsPage.clickCancel();
		browser.waitExists(siteResDetailsPage, slipResDetailsPage, cartPage);
	}
	
//	public void adjustPermitOrderFee(List<String> feeTypes, String feeLevel, List<String[]> feeAmounts, String notes) {
////		OrmsPermitOrderFeesPage permitOrderFeePg = OrmsPermitOrderFeesPage.getInstance();
//		OrmsOrderCartPage OrdCartPg = OrmsOrderCartPage.getInstance();
//		logger.info("Go the fee detail to adjust fee.");
//		
//		if(!permitOrderFeePg.exists()){
//			OrdCartPg.selectFirstReservation();
//			OrdCartPg.clickFeesButton();
//			permitOrderFeePg.waitLoading();
//		}
//
//		for(int i=0; i<feeTypes.size(); i++){
//			permitOrderFeePg.adjustFeeInfoInFeeDetails(feeTypes.get(i), feeLevel, feeAmounts.get(i));
//		}
//		permitOrderFeePg.refreshPage();
//		permitOrderFeePg.waitLoading();
//		permitOrderFeePg.setAdjustmentNotes(notes);
//		permitOrderFeePg.clickOK();
//		OrdCartPg.waitLoading();
//	}
	
	public void gotoCustomerDetailPgFromEntryListDetailPage(String custName){
		OrmsListEntryReservationDetailPage detailsPage = OrmsListEntryReservationDetailPage.getInstance();
		OrmsCustomerDetailsPage omCustDetailPg = OrmsCustomerDetailsPage.getInstance();
		detailsPage.clickCustomerName(custName);
		ajax.waitLoading();
		omCustDetailPg.waitLoading();
	}
	
	public void addContactLogForListEntry(String contactStatus, String contactComment){
		OrmsListEntryReservationDetailPage detailsPage = OrmsListEntryReservationDetailPage.getInstance();
		OrmsListEntryContactStatusWidget contactWidget = OrmsListEntryContactStatusWidget.getInstance();
		detailsPage.clickAddContactLog();
		ajax.waitLoading();
		contactWidget.waitLoading();
		logger.info("Add contact log for list entry.");
		contactWidget.setContactInfoAndClickOk(contactStatus, contactComment);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void addToCartFromTicketDetailsPage() {
		OrmsTicketOrderDetailsPage ticketDetailPg = OrmsTicketOrderDetailsPage
				.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		ticketDetailPg.clickAddToCart();
		ajax.waitLoading();
		cartPage.waitLoading();
	}
	
	public void addToCartFromResDetailsPage() {
		OrmsReservationDetailsPage siteResDetailsPage = OrmsReservationDetailsPage.getInstance();
		OrmsSlipReservationDetailsPage slipResDetailsPage = OrmsSlipReservationDetailsPage.getInstance();
		OrmsOrderCartPage cartPage = OrmsOrderCartPage.getInstance();
		
		logger.info("Add to Cart from reservation(#=" + (siteResDetailsPage.exists() ? siteResDetailsPage.getReservNum() : slipResDetailsPage.getSlipReservationNum()) + ") details page.");
		slipResDetailsPage.clickAddToCart();
		ajax.waitLoading();
		cartPage.waitLoading();
	}
	
	public void gotoReceiptSearchPage() {
		LicMgrTopMenuPage lmTop = LicMgrTopMenuPage.getInstance();
		LicMgrPrivilegeOrderSearchPage privOrdSearchPage = LicMgrPrivilegeOrderSearchPage.getInstance();
//		PermitMgrTopMenuPage pmTop = PermitMgrTopMenuPage.getInstance();
		VnuMgrTopMenuPage vmTop = VnuMgrTopMenuPage.getInstance();
		OpMgrTopMenuPage opTop = OpMgrTopMenuPage.getInstance();
		OrmsReceiptSearchPage receiptSearchPage = OrmsReceiptSearchPage.getInstance();
//		FldMgrTopMenuPage fmTop = FldMgrTopMenuPage.getInstance();
		
		
		String manager = lmTop.getManagerName();
		String topMenu = "Receipts";
		logger.info("Goto receipt search/list page from " + manager + " top menu.");
		if(manager.equalsIgnoreCase("License Manager")) {
			lmTop.clickOrders();
			privOrdSearchPage.waitLoading();
			privOrdSearchPage.clickReceiptsTab();
			ajax.waitLoading();
		} else if(manager.equalsIgnoreCase("Call Manager")) {
			CallMgrHomePage cmHomePage = CallMgrHomePage.getInstance();
			CallMgrTopMenuPage cmTop = CallMgrTopMenuPage.getInstance();
			if(cmHomePage.exists()){
				cmHomePage.clickCampingCall();
				cmTop.waitLoading();
			}
			
			cmTop.selectReceipt();
//		}else if(manager.equalsIgnoreCase("Permit Manager")){
//			pmTop.selectSearchDropDown(topMenu);
		}else if(manager.equalsIgnoreCase("Venue Manager")){
			vmTop.selectSearchDropDown(topMenu);
		}else if(manager.equalsIgnoreCase("Operations Manager")){
			opTop.selectSearchDropDown(topMenu);
//		}else if(manager.equalsIgnoreCase("Field Manager")){
//			fmTop.selectSearchDropdownList(topMenu);
//		} else if(manager.equalsIgnoreCase("Marina Manager")) {
//			fmTop.selectSearchDropdownList(topMenu);
		}
		
		receiptSearchPage.waitLoading();
	}
	
	public void gotoReceiptDetailsPageFromSearchPage(String receiptNum) {
		OrmsReceiptSearchPage searchPage = OrmsReceiptSearchPage.getInstance();
		OrmsReceiptDetailsPage detailsPage = OrmsReceiptDetailsPage.getInstance();
		
		logger.info("Goto receipt(#=" + receiptNum + ") details page.");
		searchPage.searchByReceiptNum(receiptNum);
		searchPage.clickReceiptNumLink(receiptNum);
		detailsPage.waitLoading();
	}
	
	public void gotoReceiptDetailsPage(String receiptNum) {
		this.gotoReceiptSearchPage();
		this.gotoReceiptDetailsPageFromSearchPage(receiptNum);
	}
	
	public void gotoOrderDetailsPageFromReceiptDetailsPage(String num) {
		OrmsReceiptDetailsPage rcptDetailsPage = OrmsReceiptDetailsPage.getInstance();
		OrmsPOSDetailsPage posDetailsPage = OrmsPOSDetailsPage.getInstance();
//		OrmsPermitOrderDetailsPage permitDetailsPage = OrmsPermitOrderDetailsPage.getInstance();
		OrmsReservationDetailsPage resDetailsPage = OrmsReservationDetailsPage.getInstance();
		LicMgrConsumableOrderDetailsPage consumableDetailsPage = LicMgrConsumableOrderDetailsPage.getInstance();
		OrmsGiftCardDetailsPage giftCardDetailsPage = OrmsGiftCardDetailsPage.getInstance();
		LicMgrPrivilegeOrderDetailsPage privDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		OrmsLotteryApplicationDetailsPage lotteryDetailsPage = OrmsLotteryApplicationDetailsPage.getInstance();
		LicMgrSupplyOrderDetailsPage supplyDetailsPage = LicMgrSupplyOrderDetailsPage.getInstance();
		LicMgrVehicleOrderDetailsPage vehicleDetailsPage = LicMgrVehicleOrderDetailsPage.getInstance();
		OrmsEventDetailPage eventDetailsPage = OrmsEventDetailPage.getInstance();
		
		if(!rcptDetailsPage.isOrderNumDisplayedAsLink(num)) throw new ErrorOnPageException("Order num shall be displayed as link.");
		rcptDetailsPage.clickOrderNum(num);
//		browser.waitExists(posDetailsPage, permitDetailsPage, resDetailsPage, consumableDetailsPage, giftCardDetailsPage, privDetailsPage, lotteryDetailsPage, supplyDetailsPage, vehicleDetailsPage, eventDetailsPage);
	}
	
	/**
	 * This method just suitable for call and operation manager
	 * @param parkName
	 */
	public void gotoFacilityDetailPgFromMarinaResultPg(String parkName){
		OrmsMarinaResultsPgae marinaResultPg = OrmsMarinaResultsPgae.getInstance();
		OrmsMarinaDetailsPgae marinaDetailPg = OrmsMarinaDetailsPgae.getInstance();
		marinaResultPg.clickMarinaName(parkName);
		ajax.waitLoading();
		marinaDetailPg.waitLoading();
	}
	
	public void requestHFConfirmLetter(String method){
		OrmsRequestHFConfirmLetterPage confirmLetterPg = OrmsRequestHFConfirmLetterPage
				.getInstance();
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage
				.getInstance();
		
		logger.info("Request H&F confrmation letter by "+method);
		
		receiptPg.clickRequestHFConfLetter();
		confirmLetterPg.waitLoading();
		confirmLetterPg.selectDistributionMethod(method);
		confirmLetterPg.clickOK();
		ajax.waitLoading();
	}
	
	public void enrollCustomerToSelectWidget() {
		OrmsCustomerDetailsPage custDetailsPage = OrmsCustomerDetailsPage.getInstance();
		OrmsCustomerLoyaltyProgramSelectWidget selectWidget = OrmsCustomerLoyaltyProgramSelectWidget.getInstance();
		
		logger.info("Enroll customer to select widget.");
		custDetailsPage.clickEnrollCustomer();
		ajax.waitLoading();
		selectWidget.waitLoading();
	}
	
	public void enrollCustomer(String programName) {
		enrollCustomer(programName, null);
	}
	
	public void enrollCustomer(String programName, String cardNum) {
		OrmsCustomerDetailsPage custDetailsPage = OrmsCustomerDetailsPage.getInstance();
		OrmsCustomerLoyaltyProgramSelectWidget selectWidget = OrmsCustomerLoyaltyProgramSelectWidget.getInstance();
		OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
		
		logger.info("Enroll customer to Loyalty Program(Name=" + programName + ") to cart.");
		this.enrollCustomerToSelectWidget();
		if(selectWidget.isCheckboxExists()) {
			selectWidget.selectLoyaltyProgram(programName);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(cardNum)) {
			selectWidget.setCardNumber(programName, cardNum);
		}
		selectWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists(custDetailsPage, cart);
	}
	
	public void replaceCardToReplaceWidget(String name) {
		OrmsCustomerDetailsPage custDetailsPage = OrmsCustomerDetailsPage.getInstance();
		OrmsCustomerLoyaltyProgramReplaceCardWidget replaceCardWidget = OrmsCustomerLoyaltyProgramReplaceCardWidget.getInstance();
		
		logger.info("Replace Card for loyalty program - " + name + " to replace widget.");
		custDetailsPage.clickReplaceCard(name);
		ajax.waitLoading();
		replaceCardWidget.waitLoading();
	}
	
	public void replaceCard(String oldName, String newName, String notes) {
		replaceCard(oldName, newName, null, notes);
	}
	
	public void replaceCard(String oldName, String newName, String newCardNum, String notes) {
		OrmsCustomerDetailsPage custDetailsPage = OrmsCustomerDetailsPage.getInstance();
		OrmsCustomerLoyaltyProgramReplaceCardWidget replaceCardWidget = OrmsCustomerLoyaltyProgramReplaceCardWidget.getInstance();
		OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage additionalInfoPage = OrmsCustomerLoyaltyProgramReplaceCardAdditionalInformationPage.getInstance();
		OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
		
		logger.info("Replace Card for loyalty program - " + oldName);
		this.replaceCardToReplaceWidget(oldName);
		if(!StringUtil.isEmpty(newCardNum)) {
			replaceCardWidget.setNewCardNumber(newCardNum);
		}
		replaceCardWidget.setNotes(notes);
		replaceCardWidget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(custDetailsPage, additionalInfoPage, cart);
		if(page == additionalInfoPage) {
			additionalInfoPage.selectProgram(newName);
			additionalInfoPage.clickOK();
			ajax.waitLoading();
			cart.waitLoading();
		}
	}
	
	public String gotoRedeemPointsWidgetFromCart(String ordNum) {
		return gotoRedeemPointsWidgetFromCart(ordNum, false);
	}
	
	public String gotoRedeemPointsWidgetFromCart(String ordNum, boolean isNotSiteRes) {
		OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
		OrmsCustomerLoyaltyProgramRedeemPointsWidget redeemWidget = OrmsCustomerLoyaltyProgramRedeemPointsWidget.getInstance();
		
		if(!StringUtil.isEmpty(ordNum)) {
			cart.selectOrderCheckBox(ordNum, isNotSiteRes);
		}
		cart.clickRedeemPoint();
		ajax.waitLoading();
		Object page = browser.waitExists(redeemWidget, cart);
		String msg = "";
		if(page == cart) {
			msg = cart.getErrorMessage();
		}
		if(page == redeemWidget) {
			if(redeemWidget.isErrorMsgExists()) {
				msg = redeemWidget.getErrorMsg();
			}
		}
		
		return msg;
	}
	
	public String redeemPoints(String orderNum, String programName, String cardNum) {
		return redeemPoints(orderNum, false, programName, cardNum);
	}
	
	public String redeemPoints(String orderNum, boolean isNotSiteRes, String programName, String cardNum) {
		return redeemOrUnRedeemPoints(orderNum, isNotSiteRes, programName, cardNum, true);
	}
	
	public String unRedeemPoints(String orderNum, String programName, String cardNum) {
		return unRedeemPoints(orderNum, false, programName, cardNum);
	}
	
	public String unRedeemPoints(String orderNum, boolean isNotSiteRes, String programName, String cardNum) {
		return redeemOrUnRedeemPoints(orderNum, isNotSiteRes, programName, cardNum, false);
	}
	
	private String redeemOrUnRedeemPoints(String orderNum, boolean isNotSiteRes, String programName, String cardNum, boolean redeem) {
		OrmsCustomerLoyaltyProgramRedeemPointsWidget redeemWidget = OrmsCustomerLoyaltyProgramRedeemPointsWidget.getInstance();
		
		this.gotoRedeemPointsWidgetFromCart(orderNum, isNotSiteRes);
		redeemWidget.waitLoading();
		redeemWidget.selectLoyaltyCard(programName, cardNum);
		ajax.waitLoading();
		if(redeem) {
			redeemWidget.selectProduct(null);
		} else {
			redeemWidget.unselectProduct(null);
		}
		redeemWidget.clickRedeem();
		ajax.waitLoading();
		Object page = browser.waitExists(redeemWidget, OrmsOrderCartPage.getInstance());
		String msg = "";
		if(page == redeemWidget) {
			if(redeemWidget.isErrorMsgExists()) {
				msg = redeemWidget.getErrorMsg();
			}
		}
		
		return msg;
	}
	
	public String requestConfirmationLetterByMethod(String byMethod, String path){
		OrmsReservationDetailsPage resDetailPg = OrmsReservationDetailsPage.getInstance();
		OrmsRequestConfirmationLetterPage requestCofirmationLetterPage = OrmsRequestConfirmationLetterPage.getInstance();
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage.getInstance();
		
		String resNum = resDetailPg.getReservNum();
		logger.info("Request reservation number - " + resNum + " confirmation letter by " + byMethod + " distribution method.");
		
		resDetailPg.clickRequestConfLetter();
		requestCofirmationLetterPage.waitLoading();
		requestCofirmationLetterPage.selectConfLetterDistributionMethod(byMethod);
		requestCofirmationLetterPage.clickOK();
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
			String fullFileName = file.getAbsolutePath() + "\\" + "ConfirmLetter_" + resNum + ".pdf";
			downloadPage.setDismissible(false);
			downloadPage.setBeforePageLoading(false);
			downloadPage.waitLoading();
			this.downloadFile(fullFileName);
			
			return fullFileName;
		}
		resDetailPg.waitLoading();
		return "";
	}
	
	public void gotoInvoiceSearchPage() {
		CallMgrTopMenuPage cmTopMenu = CallMgrTopMenuPage.getInstance();
		LicMgrTopMenuPage lmTopMenu = LicMgrTopMenuPage.getInstance();
		OrmsInvoiceSearchPage searchPage = OrmsInvoiceSearchPage.getInstance();
		
		String manager = cmTopMenu.getManagerName();
		logger.info("Goto Invoice search page in " + manager);
		if(manager.equalsIgnoreCase("Call Manager")) {
			cmTopMenu.selectInvoice();
		} else if(manager.equalsIgnoreCase("License Manager")) {
			lmTopMenu.selectAdminOptions("Invoices");
		}//TODO to add more managers
		
		searchPage.waitLoading();
	}
	
	public void gotoInvoiceDetailsPage(String invoiceNum) {
		OrmsInvoiceSearchPage searchPage = OrmsInvoiceSearchPage.getInstance();
		OrmsInvoiceSummaryPage summaryPage = OrmsInvoiceSummaryPage.getInstance();
		
		logger.info("Goto Invoice(#=" + invoiceNum + ") summary page.");
		InvoiceInfo invoice = new InvoiceInfo();
		invoice.invoiceNum = invoiceNum;
		searchPage.searchInvoice(invoice);
		searchPage.clickInvoiceNum(invoiceNum);
		summaryPage.waitLoading();
	}
	
	public void gotoOrderDetailsPageFromInvoiceDetailsPage(String ordNum) {
		OrmsInvoiceSummaryPage summaryPage = OrmsInvoiceSummaryPage.getInstance();
		LicMgrPrivilegeOrderDetailsPage privilegeDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		logger.info("Goto order details page from invoice details page.");
		summaryPage.clickOrderNum(ordNum);
		browser.waitExists(privilegeDetailsPage);
	}
	
	public void gotoPassPointsSearchListPageFromCustDetailsPage(String program) {
		OrmsCustomerDetailsPage custDetailsPage = OrmsCustomerDetailsPage.getInstance();
		OrmsCustomerPassPointsListPage pointsListPage = OrmsCustomerPassPointsListPage.getInstance();
		
		logger.info("Goto customer pass points search/list page.");
		custDetailsPage.clickNotRedemablePoints(program);
		pointsListPage.waitLoading();
	}
	
	private void addRemovePoints(String action, String points, String reason, String comments) {
		OrmsCustomerPassPointsListPage pointsListPage = OrmsCustomerPassPointsListPage.getInstance();
		OrmsCustomerPassAddRemovePoints pointsWidget = OrmsCustomerPassAddRemovePoints.getInstance();
		
		pointsListPage.clickAddRemovePoints();
		ajax.waitLoading();
		pointsWidget.waitLoading();
		pointsWidget.setupPointsInfo(action, points, reason, comments);
		pointsWidget.clickOK();
		ajax.waitLoading();
		pointsListPage.waitLoading();
	}
	
	public void addPoints(String points, String reason, String comments) {
		addRemovePoints("Add", points, reason, comments);
	}
	
	public void removePoints(String points, String reason, String comments) {
		addRemovePoints("Remove", points, reason, comments);
	}
	
	/**
	 * go to confirm letter page.
	 */
	public void gotoConfimLetterPg() {
		OrmsReceiptDetailsPage receiptPg = OrmsReceiptDetailsPage.getInstance();
		OrmsRequestHFConfirmLetterPage confirmLetterPg = OrmsRequestHFConfirmLetterPage.getInstance();

		logger.info("Request Confirm Letter");
		receiptPg.clickRequestHFConfLetter();
		confirmLetterPg.waitLoading();
	}
	
	/**
	 * This method go to activate gift card widget from 
	 */
	public void goToActivateGiftCardWidgetFromDetailPg(){
		OrmsActivateGiftCardWidget activateWidget = OrmsActivateGiftCardWidget.getInstance();
		OrmsGiftCardDetailsPage omGiftCardOrdDetailPg = OrmsGiftCardDetailsPage.getInstance();
		omGiftCardOrdDetailPg.clickActivateCard();
		ajax.waitLoading();
		activateWidget.waitLoading();
	}
	
	public String activateGiftCard(String cardNum){
		String messge = "";
		OrmsGiftCardDetailsPage omGiftCardOrdDetailPg = OrmsGiftCardDetailsPage.getInstance();
		OrmsActivateGiftCardWidget activateWidget = OrmsActivateGiftCardWidget.getInstance();
		this.goToActivateGiftCardWidgetFromDetailPg();
		activateWidget.setGiftCardNum(cardNum);
		activateWidget.clickOK();
		ajax.waitLoading();
		activateWidget.waitLoading();
		messge = activateWidget.getSuccessMsg();
		if(StringUtil.notEmpty(messge)){
			activateWidget.clickOK();
			Browser.sleep(10); //Wait for the down load page to disappear
		}else{
			messge = activateWidget.getErrorMsg();
			activateWidget.clickCancel();
		}
		ajax.waitLoading();
		omGiftCardOrdDetailPg.waitLoading();
		return messge;
	}
	
	/**
	 * This method make a gift card order from home page to order cart page
	 * @param amount
	 * @param giftCardNum
	 * @param sendEmail This parameter indicate whether send an email to customer
	 * @param cust
	 */
	public void makeGiftCardOrderToCartFromAddGiftCardPgForFM(String amount, String giftCardNum, boolean sendEmail, Customer cust){
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsAddGiftCardPge addPg = OrmsAddGiftCardPge.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage.getInstance();
		logger.info("Making a Gift Card order to cart.");

		addPg.setAmount(amount);
		addPg.setCardNumber(giftCardNum);
		if(addPg.isPromptInformationExist()){
			addPg.selectGiftCardBeingMailed(sendEmail);
		}
		addPg.clickAddToCart();
		ajax.waitLoading();
		if(sendEmail){
			custSearchPage.waitLoading();
			custSearchPage.searchCust(cust.fName, cust.lName, cust.email);
			custSearchPage.selectCust(cust.lName);
			ajax.waitLoading();
		}else{
			Object page = browser.waitExists(confirm, ordCartPg);
			if(page==confirm){
				confirm.clickOK();
				ajax.waitLoading();
			}
		}
		ordCartPg.waitLoading();
	}


	/**
	 * The method used to purchase a gift cart to order cart
	 *
	 * @param amount
	 * @param giftCardNum
	 */
	public void makeGiftCardOrderToCart(String amount, String giftCardNum) {
//		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
//		OrmsAddGiftCardPge addPg = OrmsAddGiftCardPge.getInstance();
//
//		logger.info("Making a Gift Card order to cart.");
//
//		this.gotoAddGiftCardPgFromHome();
//		addPg.setAmount(amount);
//		addPg.setCardNumber(giftCardNum);
//		addPg.clickAddToCart();
//		fmOrdCartPg.waitLoading();
		makeGiftCardOrderToCart(amount, giftCardNum, false, null);
	}
	
	/**
	 * This method make a gift card order from home page to order cart page
	 * @param amount
	 * @param giftCardNum
	 * @param sendEmail This parameter indicate whether send an email to customer
	 * @param cust
	 */
	public void makeGiftCardOrderToCart(String amount, String giftCardNum, boolean sendEmail, Customer cust){
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		OrmsAddGiftCardPge addPg = OrmsAddGiftCardPge.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		OrmsCustomerSearchPage custSearchPage = OrmsCustomerSearchPage.getInstance();
		logger.info("Making a Gift Card order to cart.");

//		this.gotoAddGiftCardPgFromHome();
		addPg.setAmount(amount);
		addPg.setCardNumber(giftCardNum);
		if(addPg.isPromptInformationExist()){
			addPg.selectGiftCardBeingMailed(sendEmail);
		}
		addPg.clickAddToCart();
		ajax.waitLoading();
		if(sendEmail){
			custSearchPage.waitLoading();
			custSearchPage.searchCust(cust.fName, cust.lName, cust.email);
			custSearchPage.selectCust(cust.lName);
			ajax.waitLoading();
		}else{
			Object page = browser.waitExists(confirm, ordCartPg);
			if(page==confirm){
				confirm.clickOK();
				ajax.waitLoading();
			}
		}
		ordCartPg.waitLoading();
	}
	
	public String runSpecialRpt(ReportData rd, String path) {
		OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage.getInstance();
		OrmsOnlineReportProcessingPage rmOnlineReport = OrmsOnlineReportProcessingPage.getInstance();
		FileDownloadDialogPage filePg = FileDownloadDialogPage.getInstance();

		logger.info("Run " + rd.reportName);

		String fileName = "";
		rmCriteriaPg.setReportCriteria(rd);

		rmCriteriaPg.waitLoading();
		rmCriteriaPg.clickOk();
		File file = new File(path);
		if (rd.reportFormat.equalsIgnoreCase("pdf")
				|| rd.reportFormat.equalsIgnoreCase("xls")
				|| rd.reportFormat.equalsIgnoreCase("csv")|| rd.reportFormat.equalsIgnoreCase("text")) {
			filePg.setDismissible(false);
			browser.waitExists(rmOnlineReport,filePg);
			if (!file.exists()) {
				boolean success = file.mkdir();
				if (!success) {
					throw new ItemNotFoundException(
					"Failed To Make Directory.");
				}
			}
			fileName = file.getAbsolutePath()+ "\\";
			rd.fileName = rd.fileName.replaceAll(" ", "");
			if (rd.reportFormat.equalsIgnoreCase("pdf")) {
				fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ rd.reportName.replaceAll(" ", "")
						+ rd.reportType.replaceAll(" ", ""):fileName+rd.fileName;
				fileName = fileName + ".pdf";
			} else if (rd.reportFormat.equalsIgnoreCase("xls")) {
				fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ (rd.reportName + rd.dateType).replaceAll(" ", "")
						+ rd.productType
						+ rd.reportType.replaceAll(" ", "")
						+ rd.reportBy.replaceAll(" ", ""):fileName+rd.fileName;
				fileName = fileName + ".xls";
			} else if (rd.reportFormat.equalsIgnoreCase("csv")){
				fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ rd.reportName.replaceAll(" ", ""):fileName+rd.fileName;
				fileName = fileName + ".csv";
			}else{
				fileName = (StringUtil.isEmpty(rd.fileName))?fileName+ rd.reportName.replaceAll(" ", ""):fileName+rd.fileName;
				fileName = fileName + ".txt";
			}
			
		}
//			downloadReport(fileName);  Shane[20140310],due to IE9 download issue,skip download report logic
		rmCriteriaPg.waitLoading();
		return fileName;
	}

	
	public boolean skipVerifyOnlineReport(String templatesPath, String comparedPath){
		logger.info("Skip Online Report Verify due to IE9 download issue.");
		return true;
	}
	
	public void verifyReportCorrect(boolean result) {
		logger.info("Report content comparsion");

		if (result) {
			logger.info("Report Content Correct.");
		} else {
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
	}

	public String printSitePermit(String path) {
		OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
		OrmsReservationDetailsPage resDetailsPage = OrmsReservationDetailsPage.getInstance();
		OrmsOnlineReportProcessingPage reportProcessPage = OrmsOnlineReportProcessingPage.getInstance();
		
		logger.info("Print Slip Permit in " + (summaryPage.exists() ? "Order Summary page" : "Slip Reservation Details page"));
		if(summaryPage.exists()) {
			summaryPage.clickPrintPermit();
		} else if(resDetailsPage.exists()) {
			resDetailsPage.clickReprintPermit();
		}
		reportProcessPage.waitLoading();

		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new ItemNotFoundException("Failed To Make Directory.");
			}
		}
		String fullPathAndFileName = file.getAbsolutePath()
				+ "\\SlipPermit_" + DateFunctions.getTimeStamp()
				+ ".pdf";
		downloadReport(fullPathAndFileName);

		if (reportProcessPage.exists()) {
			reportProcessPage.close();
		}
		browser.waitExists(summaryPage, resDetailsPage);

		return fullPathAndFileName;
	}
}
