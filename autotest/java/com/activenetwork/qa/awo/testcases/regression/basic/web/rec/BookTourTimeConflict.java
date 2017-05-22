package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class BookTourTimeConflict extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_BookTourTimeConflict</b>
	 * Generated     : <b>Nov 5, 2009 10:09:04 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/05
	 * @author vzhao
	 */
	private String email, pw, url;
	private InventoryManager inv = InventoryManager.getInstance();
	private LoginInfo login;
	private TicketInfo fTour;
	private TicketInfo sTour = new TicketInfo();
	private InventoryInfo ticket;

	public void execute() {
		//Turn on Time Conflict Management
		inv.loginInventoryManager(login);
		inv.gotoFacilityDetailsPg(ticket.facilityName);
		inv.gotoTourDetailsPg(ticket);
		this.turnOnTimeConflictRule();
		inv.logoutInvManager();

		// Verify time conflict rule on web
		web.invokeURL(url);
		web.signIn(email, pw);

		sTour = web.bookTourIntoCart(fTour);
		this.verifyTimeConflictWarnning();
		this.verifyTimeConflictError();

		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		//inventory manager login paras
		login = new LoginInfo();
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		fTour = new TicketInfo();
		fTour.contractCode = "NRSO";
		fTour.park = "Roosevelt-Vanderbilt National Historic Sites";
		fTour.tourName = "Vanderbilt Mansion Indiv";
		fTour.tourDate = DateFunctions.getDateAfterToday(4);
		fTour.timeSlot = "10:00";
		fTour.ticketNums = "3";
		fTour.isUnifiedSearch=isUnifiedSearch();
		fTour.parkId = "77814";

		//tour ticket paras
		ticket = new InventoryInfo();
		ticket.searchType = "Facility Name";
		ticket.searchValue = fTour.park;
		ticket.facilityName = fTour.park;
		ticket.tourStatus = "Active";
		ticket.tourType = "Tour Name";
		ticket.tourValue = fTour.tourName;
		ticket.tourName = fTour.tourName;
		
		// web login info
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
	}

	//Turn on Time Conflict Management
	public void turnOnTimeConflictRule() {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();

		logger.info("Turn on Time Conflict Management.");
		tourDetail.turnOnTimeConflict();
		tourDetail.clickOK();
		facTour.waitLoading();
	}

	// book a same time for different tour to verify time conflict warnning
	public void verifyTimeConflictWarnning() {
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage
				.getInstance();

		logger.info("Book same time tour to verify time conflict warnning.");
		tourShoppingCart.gotoBookMoreReservation();
		tourSearch.waitLoading();

		sTour.tourName = "Eleanor(Val Kill) Indiv";
		sTour.ticketNums = fTour.ticketNums;
		sTour.timeSlot = fTour.timeSlot;
		tourSearch.setSearchCriteria(sTour);
		tourSearch.clickSearchTours();
		tourSearch.waitLoading();

		if (!tourSearch.isDateAvailable())
			throw new ItemNotFoundException("Inv not available for "
					+ sTour.tourName + " on " + sTour.tourDate);
		tourSearch.clickOnFirstAvailableTour();
		tourDetails.waitLoading();
		
//		tourDetails.bookTourByGivenTicketType(sTour.ticketNums, fTour.ticketTimeSeq, sTour.ticketType);
		tourDetails.bookTour(sTour.ticketNums, 0, fTour.timeSlot);
		browser.waitExists(tourDetails, tourShoppingCart);

		if (!tourDetails.exists())
			throw new ErrorOnPageException(
					"Time conflict warnning rule has been broken.");
		if (!tourDetails.getErrorMsg().matches(
						"^Alert:This request does not allow enough time between tours.*"))
			throw new ErrorOnPageException(
					"Time conflict warnning rule has been broken.");
	}

	// book combo tour in same time to verify time conflict error
	public void verifyTimeConflictError() {
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage
				.getInstance();

		logger.info("Book Combo tour to verify time conflict Error.");
		tourDetails.clickFindOtherTour();
		tourSearch.waitLoading();

		sTour.tourName = "Combo Tour Individual";
		sTour.ticketNums = fTour.ticketNums;

		tourSearch.setSearchCriteria(sTour);
		int period = 60;//days
		tourSearch.searchTour(period);
		tourDetails.waitLoading();

		tourDetails.setTicketNum(sTour.ticketNums);
		tourDetails.selectEachFirstTimeForComboTour();
		tourDetails.clickOnBookTour();
		browser.waitExists(tourDetails, tourShoppingCart);

		if (!tourDetails.exists())
			throw new ErrorOnPageException(
					"Time conflict Error rule has been broken.");
		if (!tourDetails.getErrorMsg().matches(
				"^This request does not allow enough time between tours.*"))
			throw new ErrorOnPageException(
					"Time conflict Error rule has been broken.");

		tourDetails.gotoShoppingCart();
		tourShoppingCart.waitLoading();
	}
}
