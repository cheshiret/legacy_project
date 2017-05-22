/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.ticket.tourparticipant.orderdetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking the display of non-attribute related text in Tour Order Details page for single tour in group sale.
 * @Preconditions: 
 *	1. Tour "TPA_SingleTour2" exist with TPA settings in VOYAGEURS NATIONAL PARK TOURS in NRRS contract
 * 2. The tour has inventory and fee schedule
 * 3. The tour is visible in Web 
 * @SPEC: 
 * Tour Order Details page header (Group Sales) [TC:043565]
 * Tour Order Details page: Tour Ticket Instance sub-header (Group Sales) [TC:043568]
 * Tour Order Details page: Important Information tab (single tour) [TC:043569]
 * Tour Order Details page: 'Continue to Shopping Cart' button [TC:043571]
 * Change Tour Order Details page header [TC:043573]
 * @Task#: Auto-1406
 * 
 * @author Lesley Wang
 * @Date  Feb 17, 2013
 */
public class VerifyNonTPARelatedText_GroupSale extends WebTestCase {
	private UwpTourOrderDetailsPage ordDetailsPage=UwpTourOrderDetailsPage.getInstance();
	private String importantInfo, errMsg;
	
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookTourIntoTourOrderDetailsPg(ticket, ticket.isComboTour, true);
		
		// Checkpoint 1: Check page header, tour info, important info
		this.verifyNonTPATextOnTourOrderDetailsPg();
		
		// Checkpoint 2: Check error message when click Continue to shopping cart button without checking acknowledge 
		ordDetailsPage.GoToShoppingCartWithoutCheckingAcknowledge();
		ordDetailsPage.verifyErrorMsg(errMsg);
		
		// Input the required info and book the tour into cart
		web.bookTourIntoCartFromTourOrderDetailsPg(ticket);
		
		// Checkpoint 3: Check the page header when click the link "Change Details" in shopping cart page
		web.gotoTourOrderDetailsPgFromShoppingCartPg();
		ordDetailsPage.verifyPageTitle(ordDetailsPage.CHANGE_PAGE_TITLE);
		
		web.bookTourIntoCartFromTourOrderDetailsPg(ticket);
		web.abandonCart();
		web.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for UWP
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		url = TestProperty.getProperty(env + WEB_URL_RECGOV);
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS"; 
		
		// ticket info
		ticket.parkId = "72777"; // VOYAGEURS NATIONAL PARK TOURS
		ticket.contractCode = "NRSO";
		ticket.park = DataBaseFunctions.getFacilityName(ticket.parkId, schema);
		ticket.isUnifiedSearch = true;
		ticket.tourCode = "InterTest2";
		ticket.tourName = "TPA_SingleTour2";
		ticket.tourDate = DateFunctions.getDateAfterToday(5);
		ticket.timeSlot = "10:00 AM - 5:00 PM ";
		ticket.ticketTypes.add("Adult");
		ticket.ticketTypeNums.add("1");
		importantInfo = web.getProductAttributeValue(schema, ticket.tourCode, ticket.parkId, IMPORTANT_INFO);
		
		// Ticket TPA info
		Map<String, String> perTicketTPAsForAdult = new HashMap<String, String>();
		perTicketTPAsForAdult.put(TicketInfo.TPA_LABEL_DATEOFBIRTH, "1/1/1986");
		List<Map<String, String>> perTicketTPAsForAdultList = new ArrayList<Map<String, String>>();
		perTicketTPAsForAdultList.add(perTicketTPAsForAdult);
		ticket.perTicketTPAsList.add(perTicketTPAsForAdultList);		
		ticket.perInventoryTPAs.put(TicketInfo.TPA_LABEL_ADDRESS, "Address for TPA Test");		
		
		errMsg = "Please acknowledge that the information you have entered is accurate and that you have read any important information before continuing.";
	}

	/**
	 * Verify the page title, tour name, tour date and time, and tour important info on the page
	 */
	private void verifyNonTPATextOnTourOrderDetailsPg() {
		boolean result = ordDetailsPage.comparePageTitle(ordDetailsPage.PAGE_TITLE);
		result &= ordDetailsPage.compareTourInfo("tour name", ticket.tourName);
		result &= this.compareTourTimeAndNum(ticket.tourDate, ticket.timeSlot, ticket.ticketTypes, ticket.ticketTypeNums);
		result &= ordDetailsPage.compareTourImportantInfo(ticket.tourName, importantInfo, 0);
		
		if (!result) {
			throw new ErrorOnPageException("Order Details Page is wrong! Check logger error!");
		}
		logger.info("Checkpoint 1 - Successfully verify page title, tour info and important info!");
	}
	
	/**
	 * Compare tour time and ticket numbers for single tour
	 */
	public boolean compareTourTimeAndNum(String date, String time, List<String> ticketTypes, List<String> nums) {
		String expectedInfo = DateFunctions.formatDate(date, "EEE MMM dd yyyy") + " " + time + " - ";
		for (int i = 0; i < ticketTypes.size(); i++) {
			expectedInfo += ticketTypes.get(i) + " " + nums.get(i) + " ";
		}
		return ordDetailsPage.compareTourInfo("Tour Time and ticket nums", expectedInfo.trim());		
	}
}
