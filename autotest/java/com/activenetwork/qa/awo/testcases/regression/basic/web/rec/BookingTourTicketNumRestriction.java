package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourTicketsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHomePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourParkListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class BookingTourTicketNumRestriction extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_BookingTourTicketNumRestriction</b>
	 * Generated     : <b>Nov 3, 2009 4:14:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/03
	 * @author vzhao
	 */
	private InventoryManager inv = InventoryManager.getInstance();
	private LoginInfo login;
	private TicketInfo bd;
	private InventoryInfo ticket;
	private String email, pw, url;

	public void execute() {
		// update the max and min reservation ticket number in Inventory manager
		inv.loginInventoryManager(login);
		inv.gotoFacilityDetailsPg(ticket.facilityName);
		inv.gotoTourDetailsPg(ticket);

		this.updateMaxandMinTicketNum(); // update max & min ticket number
		inv.logoutInvManager();

		// verify whether or not the changes has been made successful
		// this case will failed by system cache sync
		web.invokeURL(url,false,true);
		web.signIn(email, pw);
		this.verifyTicketNumberViolation();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Vanderbilt Mansion Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(4);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.parkId = "77814";

		//inventory manager login paras
		login = new LoginInfo();
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		//System manager parameters
//		sysMgrUrl=AwoUtil.getOrmsURL(env) + "/SystemMgrLogin.do";
		
		// tour ticket paras
		ticket = new InventoryInfo();
		ticket.searchType = "Facility Name";
		ticket.searchValue = bd.park;
		ticket.facilityName = bd.park;
		ticket.tourStatus = "Active";
		ticket.tourType = "Tour Name";
		ticket.tourValue = bd.tourName;
		ticket.tourName = bd.tourName;
		ticket.minIndNum = "2";
		ticket.maxIndNum = "8";

		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
	}

	//update the max & min reservation ticket # in inventory manager
	public void updateMaxandMinTicketNum() {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();
		InvMgrTourTicketsPage tourTicket = InvMgrTourTicketsPage.getInstance();

		logger.info("Update tour's max & min ticket number.");
		tourDetail.clickTourTickets();
		tourTicket.waitLoading();

		int newMin, newMax; //make sure the min and max have been changed to a new number
		// update min ticket to a different num
		if (!StringUtil.isEmpty(ticket.minIndNum)) {
			if(!tourTicket.isMinimumIndTicketExsting()){
				tourTicket.clickAddTicketType("Minimum Individual Tickets");
				tourTicket.waitLoading();
			}
			if(tourTicket.getMinIndTicket().equalsIgnoreCase(ticket.minIndNum)){
				newMin = Integer.parseInt(ticket.minIndNum) + 1;
				ticket.minIndNum = newMin + "";
			}
		}
		// update max ticket to a different num
		if (!StringUtil.isEmpty(ticket.maxIndNum)) {
			if(!tourTicket.isMaxmumIndTicketExsting()){
				tourTicket.clickAddTicketType("Maximum Individual Tickets");
				tourTicket.waitLoading();
			}
			if(tourTicket.getMaxIndTicket().equalsIgnoreCase(ticket.maxIndNum)){
				newMax = Integer.parseInt(ticket.maxIndNum) - 1;
				ticket.maxIndNum = newMax + "";
			}
		}

		tourTicket.updateTicketNum(ticket);
		tourTicket.clickOK();
		facTour.waitLoading();
	}

	public void verifyTicketNumberViolation() {
		UwpHomePage homePg = UwpHomePage.getInstance();
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpTourParkListPage tourParkList = UwpTourParkListPage.getInstance();
		RecgovParkListPage parkList = RecgovParkListPage.getInstance();
		UwpTourSearchPanel tourSearch = UwpTourSearchPanel.getInstance();
		UwpTourDetailsPage tourDetails = UwpTourDetailsPage.getInstance();
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage.getInstance();
		UwpUnifiedSearchPanel unifiedSearchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		logger.info("Verify the Min & Max Ticket Restriction number.");
		
		// if it is unified search, set up search criteria 
		if(bd.isUnifiedSearch){
			//unified search 
			headerBar.clickHomeLink();
			homePg.waitLoading();
			unifiedSearchPanel.setupTourSearchCriteria(bd);
			unifiedSearchPanel.clickSearch();
			suggestPg.waitLoading();
			suggestPg.clickFacilitySuggestion(bd.parkId,bd.contractCode);
			parkList.waitLoading();
			parkList.clickFacility(bd.park);
			
		}else{
			headerBar.gotoTourParkList();
			tourParkList.waitLoading();
			tourParkList.gotoTourParkDetails(bd.park);
		}

		tourSearch.waitLoading();
		tourSearch.setSearchCriteria(bd);
		int period = 60;//days
		tourSearch.searchTour(period);
		tourDetails.waitLoading();

		String ticketWindow[] = new String[2];
		ticketWindow = tourDetails.getRestrictTicketNum(); // get the max and min tickets from web

		if (!ticketWindow[0].equals(ticket.minIndNum)
				|| !ticketWindow[1].equals(ticket.maxIndNum))
			throw new ErrorOnPageException(
					"Max or Min ticket number displays error.");

		// break Minimum Ticket Restriction rule
		int minTickets = Integer.parseInt(ticket.minIndNum);
		if (minTickets > 1) { // only verify the minimum number over 1
			tourDetails.bookTourByGivenTicketType(minTickets - 1 +"", bd.ticketTimeSeq, bd.ticketType); // 1 less ticket and change Integer to String
			browser.waitExists(tourDetails, tourShoppingCart);

			if (!tourDetails.exists())
				throw new ErrorOnPageException(
						"Minimum Ticket Restriction rule has been broken.");
			if (!tourDetails.getErrorMsg().matches(
					".*less than the Minimum Ticket Restriction of "
							+ ticketWindow[0] + " .*"))
				throw new ErrorOnPageException(
						"Minimum Ticket Restriction rule has been broken.");
		}

		//break Maximum Ticket Restriction rule
		int maxTickets = Integer.parseInt(ticket.maxIndNum);
		tourDetails.bookTourByGivenTicketType(maxTickets + 1 +"", bd.ticketTimeSeq, bd.ticketType); // 1 more ticket and change Integer to String
		browser.waitExists(tourDetails, tourShoppingCart);

		if (!tourDetails.exists())
			throw new ErrorOnPageException(
					"Maximum Ticket Restriction rule has been broken.");
		if (!tourDetails.getErrorMsg().matches(
				".*greater than the Maximum Ticket Restriction of "
						+ ticketWindow[1] + " .*"))
			throw new ErrorOnPageException(
					"Maximum Ticket Restriction rule has been broken.");
	}
}
