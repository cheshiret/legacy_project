package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.shoppingcart.timer;

import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify Shopping cart timer after adding non-inventory product.
 * 1. Add camping reservation to shopping cart ---- Shopping cart timer is started
 * 2. Add camping reservation with donation to shopping cart -------- Shopping cart timer is re-started
 * 3. Remove camping reservation -------- Shopping cart timer not displayed
 * 4. Add one camping reservation -------- Shopping cart timer is started
 * 5. Add one camping lottery reservation -------- Shopping cart timer is restarted
 * 6. Remove camping reservation -------- Shopping cart timer not displayed
 * 7. Add one more camping lottery reservation -------- Shopping cart timer is not started
 * 8. Add one  camping reservation with donation to shopping cart ---- Shopping cart timer is started
 * 9. Remove camping reservation and lottery items from shopping cart ------- Shopping cart timer not displayed
 * 10. Add one ticketing reservation to shopping cart ----- Shopping cart timer is started
 * 11. Add one lottery product to shopping cart ----- Shopping cart timer is restarted
 * @Preconditions: 
 * 1. Make sure the site 006 in AIKEN park available in booking date.
 * 2. Make sure the donation is setup for AIKEN. Refer to  http://wiki.reserveamerica.com/display/qa/How+to+setup+and+verify+POS+on+the+Web
 * 3. Make sure lottery schedule is setup for loop Sites 001-034 in STONY BROOK STATE PARK. d_inv_new_lottery_program, id=30
 * 4. Make sure the tour "The Upstairs Suites" in  HEARST CASTLE are available for web. d_inv_tour_inventory, id=1230
 * 
 * @SPEC:
 *  Shopping Cart Timer - Non-Inventory product POS [TC:068521]
 *  Shopping Cart Timer - Adding Non-Inventory product [TC:052351]
 *  Shopping Cart Timer - Add Non-Inventory Product Lottery [TC:068522] 
 * @Task#: Auto-1777, Auto-1868
 * 
 * @author Lesley Wang, Sara Wang
 * @Date  Jul 15, 2013, Aug 19, 2013
 */
public class VerifyAfterAddNonIventoryPrd extends WebTestCase {
//	private BookingData bdLottory;
	private UwpShoppingCartPage shopCartPg = UwpShoppingCartPage.getInstance();
	private int totalTime;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);

		web.invokeURL(url);
		web.signIn(email, pw);

		// make a camping reservation, then make another camping reservation with a donation, verify shopping cart timer restart
		web.makeReservationToCart(bd);
		shopCartPg.verifyTimerStart();

		web.makeReservationWithPOSToCart(newBd, od);
		shopCartPg.verifyTimeRestart(totalTime);
		shopCartPg.verifyTimerStart();

		// Remove camping reservations and verify no shopping cart timer
		web.removeItemFromCart("1:2");
		shopCartPg.verifyTimerExistingOrNot(false);
		web.abandonCart();

		// make a camping reservation, then lottery reservation, verify shopping cart restart
		// comment below codes about lottery due to Defect-43676, no camping lottery supported
//		web.makeReservationToCart(bd);
//		web.makeReservationToCart(bdLottory); 
//		shopCartPg.verifyTimeRestart(totalTime);
//		shopCartPg.verifyTimerStart();
//
//		// remove camping reservation and verify no shopping cart timer
//		web.removeItemFromCart("1");
//		shopCartPg.verifyTimerExistingOrNot(false);
//
//		// make one more lottery and verify shopping cart timer not started
//		web.makeReservationToCart(bdLottory);
//		shopCartPg.verifyTimerExistingOrNot(false);
//
//		// make camping reservation with donation and verify shopping cart timer started
//		web.makeReservationWithPOSToCart(newBd, od);
//		shopCartPg.verifyTimerStart();
//
//		// remove camping and lottery reservations and verify no shopping cart timer
//		web.removeItemFromCart("1:2:3");
//		shopCartPg.verifyTimerExistingOrNot(false);
//
//		// make ticket reservation and verify shopping cart timer started
//		web.bookTourIntoCart(ticket);
//		shopCartPg.verifyTimerStart();
//
//		// make lottery reservation and verify shopping cart timer restarted
//		web.makeReservationToCart(bdLottory);
//		shopCartPg.verifyTimeRestart(totalTime);
//		shopCartPg.verifyTimerStart();
//
//		web.abandonCart();
		web.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SC", env);

		// camping reservation info
		bd.state = "South Carolina";
		bd.parkId = "10201";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"AIKEN";
		bd.contractCode = "SC";
		bd.arrivalDate = DateFunctions.getDateAfterToday(8);
		bd.lengthOfStay = "2";
		bd.loop = "Campground";
		bd.siteNo = "006";
		bd.siteID = "1948";

		// camping reservation with donation info
		newBd.state = "South Carolina";
		newBd.parkId = bd.parkId;
		newBd.park = bd.park;
		newBd.contractCode = "SC";
		newBd.arrivalDate = DateFunctions.getDateAfterToday(11);
		newBd.lengthOfStay = "2";
		newBd.loop = "Campground";
		newBd.siteNo = "006";
		newBd.siteID = "1948";
		od.isDonationPOS = true;
		od.isMinFixedAmount = true;

		// lottery info
//		bdLottory = new BookingData();
//		bdLottory.state = "New York";
//		bdLottory.park = "STONY BROOK STATE PARK";
//		bdLottory.conType = "State";
//		bdLottory.contractCode = "NY";
//		bdLottory.loop = "Sites 001-034";
//		bdLottory.siteNo = "004";
//		bdLottory.siteID = "248467";
//		bdLottory.arrivalDate = web.queryLotteryInventoryStartDate(DataBaseFunctions.getSchemaName("NY", env), bdLottory.park, bdLottory.loop);
//		bdLottory.lengthOfStay = "2";

		// Ticket info
		ticket.contractCode = "CA";
		ticket.parkId = "120102"; // HEARST CASTLE
		ticket.park = web.getFacilityName(ticket.parkId, DataBaseFunctions.getSchemaName("CA", env));
		ticket.tourName = "The Grand Rooms";
		ticket.tourDate = DateFunctions.getDateAfterToday(5);
		ticket.ticketTypes.add("Adult");
		ticket.ticketTypeNums.add("1");

		totalTime = 15*60; // 15 minutes
	}
}
