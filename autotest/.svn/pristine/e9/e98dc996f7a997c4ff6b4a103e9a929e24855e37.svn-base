package com.activenetwork.qa.awo.testcases.abstractcases.web;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.uwp.UwpChangeReservationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpReservationDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: contracts="SC/CA/EB/IL/NRSO/MD/NY"
 * @Preconditions: 
 * @SPEC: "Site Transfer" section in
 * http://wiki.reserveamerica.com/display/qa/WEB+REGRESSION+LATEST+DETAILS+DRAFT%2C+please+change+this+title+date+when+you+UPDATE+this+document+Feb+06%2C+2012
 * @Task#:AUTO-805
 * 
 * @author SWang
 * @Date  April 6, 2012
 */
public abstract class SiteTransferTestCase extends WebTestCase {
	protected UwpUpdateDetailsPage updateResDetailPg = UwpUpdateDetailsPage.getInstance();
	protected UwpChangeReservationPage changeResPg = UwpChangeReservationPage.getInstance();
	protected String orderNum, siteType;
	protected BookingData newBd = new BookingData();

	public void execute() {
		if(bd.contractCode.equals("NRSO")){
			isSiteTransfer = MiscFunctions.isRecSiteTransfer(bd.contractCode);
		}else isSiteTransfer = MiscFunctions.isSiteTransfer(bd.contractCode);
		
		if(!isSiteTransfer){
			throw new ErrorOnDataException(bd.contractCode+" contract should have 'Change Date/Transfer to Another Site' functional.");
		}
		
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(newBd.lengthOfStay), false, bd.siteIDs);
		web.invokeURL(url);
		web.signIn(email, pw);

		//Make reservation order
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd); 
		web.fillInOrderDetails(od, bd.contractCode);
		orderNum = web.checkOutShoppingCart(pay);

		bd.siteType = siteType;
		newBd.siteType = bd.siteType;

		//Go to "Reservation Details" to check reservation site no, loop, arrival and departure info
		web.gotoCurrentReservationsPage();
		web.gotoReservationDetailPage(orderNum, bd.contractCode);
		this.verifySiteNoLoopArrivalAndDepartureDate(bd);

		//Go to 'Change Reservation' page from 'Reservation Details' page
		web.gotoChangeResPgFromResDetailsPg();

		//Make change date and transfer site to "Change Reservation Details" to check reservation summary
		web.changeDateOrTransferSiteToChangeResDetailsPg(newBd);
		updateResDetailPg.verifyOriginalAndNewResSummary(bd, newBd);

		//Update reservation details 
		web.updateReservationDetails(bd.equip, bd.equipLength);

		//Check reservation summary
		this.verifySiteNoLoopArrivalAndDepartureDate(newBd);

		//cancel the reservation
		web.cancelReservation(pay); 
		web.signOut();
	}


	/**
	 * Verify site number, loop, arrival and departure date info in "Reservation details" page
	 * @param testBd
	 */
	public void verifySiteNoLoopArrivalAndDepartureDate(BookingData testBd){
		UwpReservationDetailsPage resDetailsPage = UwpReservationDetailsPage.getInstance();

		String siteNo = resDetailsPage.getSiteNo();
		if(!siteNo.equalsIgnoreCase(testBd.siteNo)){
			throw new ErrorOnDataException("Site No is wrong", testBd.siteNo, siteNo);
		}

		String siteLoop = resDetailsPage.getSiteLoop();
		if(!siteLoop.startsWith(testBd.loop)){
			throw new ErrorOnDataException("Site loop is wrong", testBd.loop, siteLoop);
		}

		String arrivingDate = resDetailsPage.getArrivalDate();
		if(DateFunctions.compareDates(arrivingDate, testBd.arrivalDate)!=0){
			throw new ErrorOnDataException("Reservation arrival date is wrong", testBd.arrivalDate, arrivingDate);
		}

		String departureDateUI = resDetailsPage.getDepartureDate();
		String departureDate = DateFunctions.getDateAfterGivenDay(testBd.arrivalDate, Integer.parseInt(testBd.lengthOfStay));
		if(DateFunctions.compareDates(departureDateUI, departureDate)!=0){
			throw new ErrorOnDataException("Site Departure date is wrong", departureDate, departureDateUI);
		}
		logger.info("Successfully verify Site number, loop, arrival date, Departure date.");
	}
}
