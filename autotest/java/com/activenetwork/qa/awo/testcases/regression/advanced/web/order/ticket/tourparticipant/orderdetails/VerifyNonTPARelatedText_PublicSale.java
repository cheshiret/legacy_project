/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.ticket.tourparticipant.orderdetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is for checking the display of non-attribute related text in Tour Order Details page.
 * @Preconditions: 
 *	1. Tour "TPA_ComboTour1" exists with TPA settings in VOYAGEURS NATIONAL PARK TOURS in NRRS contract:
 * 2. The tour has tow sub tours "TPA_SingleTour1" and "TPA_SubTour1"
 * 3. The tour has inventory and fee schedule
 * 4. The tour is visible in Web 
 * @SPEC: 
 * 	Tour Order Details page header [TC:043562]
 * Tour Order Details page: Tour Ticket Instance sub-header (Public Sales) [TC:043566]
 * Tour Order Details page: Important Information tab (combo tour) [TC:043570]
 * @Task#: Auto-1406
 * 
 * @author Lesley Wang
 * @Date  Feb 17, 2013
 */
public class VerifyNonTPARelatedText_PublicSale extends WebTestCase {

	private UwpTourOrderDetailsPage ordDetailsPage=UwpTourOrderDetailsPage.getInstance();
	private String importantInfo1, importantInfo2;
	private TicketInfo subTour1, subTour2;
	
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookTourIntoTourOrderDetailsPg(ticket, ticket.isComboTour, false);
		
		// Check page header, tour info, important info
		this.verifyNonTPATextOnTourOrderDetailsPg();
		
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
		ticket.isComboTour = true;
		ticket.tourName = "TPA_ComboTour1";
		ticket.tourDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(5), "EEE MMM dd yyyy");
		ticket.ticketTypes.add("Adult");
		ticket.ticketTypeNums.add("1");
		
		// sub tour info
		subTour1 = new TicketInfo();
		subTour1.tourName = "TPA_SingleTour1";
		subTour1.tourCode = "InterTest1";
		subTour1.tourDate = ticket.tourDate;
		subTour1.timeSlot = "9:00 AM";
		subTour2 = new TicketInfo();
		subTour2.tourName = "TPA_SubTour1";
		subTour2.tourCode = "InterTest3";
		subTour2.tourDate = ticket.tourDate + " - " + ticket.tourDate;
		subTour2.timeSlot = "10:00 AM - 5:00 PM ";
		importantInfo1 = web.getProductAttributeValue(schema, subTour1.tourCode, ticket.parkId, IMPORTANT_INFO);
		importantInfo2 = web.getProductAttributeValue(schema, subTour2.tourCode, ticket.parkId, IMPORTANT_INFO);
	
		// Ticket TPA info
		Map<String, String> perTicketTPAsForAdult = new HashMap<String, String>();
		perTicketTPAsForAdult.put(TicketInfo.TPA_LABEL_FIRSTNAME, "FN_Adult1");
		List<Map<String, String>> perTicketTPAsForAdultList = new ArrayList<Map<String, String>>();
		perTicketTPAsForAdultList.add(perTicketTPAsForAdult);
		ticket.perTicketTPAsList.add(perTicketTPAsForAdultList);		
		ticket.perInventoryTPAs.put(OrmsConstants.TPA_LABEL_ARRIVAL_HH, "10");
		ticket.perInventoryTPAs.put(OrmsConstants.TPA_LABEL_ARRIVAL_MM, "51");
		ticket.perInventoryTPAs.put(OrmsConstants.TPA_LABEL_ARRIVAL_AP, "AM");	
	}

	/**
	 * Verify the page title, tour name, tour date and time, and tour important info on the page
	 */
	private void verifyNonTPATextOnTourOrderDetailsPg() {
		boolean result = ordDetailsPage.comparePageTitle(ordDetailsPage.PAGE_TITLE);
		result &= ordDetailsPage.compareTourInfo("tour name", ticket.tourName);
		result &= this.compareTourTimeAndNum(ticket.ticketTypes, ticket.ticketTypeNums, subTour1, subTour2);
		result &= ordDetailsPage.compareTourImportantInfo(subTour1.tourName, importantInfo1, 0);
		result &= ordDetailsPage.compareTourImportantInfo(subTour2.tourName, importantInfo2, 1);
		
		if (!result) {
			throw new ErrorOnPageException("Order Details Page is wrong! Check logger error!");
		}
		logger.info("Checkpoint 1 - Successfully verify page title, tour info and important info!");
	}

	/**
	 * Compare tour time and ticket numbers for combo tour
	 */
	public boolean compareTourTimeAndNum(List<String> ticketTypes, List<String> nums, TicketInfo... subTours) {
		String expectedInfo = "";
		for (TicketInfo subTour : subTours) {
			expectedInfo += subTour.tourDate + " " + subTour.timeSlot + " - " + subTour.tourName + " ";
		}
		expectedInfo = expectedInfo.trim();
		for (int i = 0; i < ticketTypes.size(); i++) {
			expectedInfo += ticketTypes.get(i) + " " + nums.get(i) + " ";
		}
		return ordDetailsPage.compareTourInfo("Tour Time and ticket nums", expectedInfo.trim());		
	}
}
