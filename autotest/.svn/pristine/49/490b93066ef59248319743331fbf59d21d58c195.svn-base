package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.ticket;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: p
 * On Tour details list, 
 * Drop down list shows 'Valid from Date' and 'Valid to Date' values in the list and sorted by open time ascending and by close time ascending.
 * @Preconditions:
 * 1.tour: Code:NTSLCWMD, Name:Non Time Limited Capacity Yes Multi Day,Timed Entry:No(means no time), Limited Capavity:Yes, Multi-Day:Yes, validate days(5), Entry dates(3)
   2.Tour have 3 kinds of inventory with different start date and close date
   2.1 start date:8:30 AM, close date:8:50 PM
   2.2 start date:8:30 AM, close date:9:00 PM
   2.3 start date:9:00 AM, close date:8:30 PM

 * @SPEC: Tour Time Drop-Down list verification on Tour details list [TC:036335] 
 * @Task#:AUTO-1293
 * 
 * @author SWang
 * @Date  Oct 25, 2012
 */
public class MultiDayTourTimeDDList extends RecgovTestCase{
	private TicketInfo tour = new TicketInfo();
	private UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();
	private String tourDate;
	private List<String> tourDateRegxs = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookTourIntoTourOrderDetailsPg(tour, false);
		tourDate = tourOrderDetailsPg.getTourTimeAndToutTypeInfo().get(0);
		this.verifyToutTimeDDListOption(tourDate, tourDateRegxs.get(0));

		tour.ticketTimeSeq = 2;
		web.makeMoreReservationsToTourDetailsPg(tourOrderDetailsPg, tour, false);
		web.bookTourIntoTourOrderDetailsPgFromTourDetailsPg(tour, false);
		tourDate = tourOrderDetailsPg.getTourTimeAndToutTypeInfo().get(0);
		this.verifyToutTimeDDListOption(tourDate, tourDateRegxs.get(1));

		tour.ticketTimeSeq = 3;
		web.makeMoreReservationsToTourDetailsPg(tourOrderDetailsPg, tour, false);
		web.bookTourIntoTourOrderDetailsPgFromTourDetailsPg(tour, false);
		tourDate = tourOrderDetailsPg.getTourTimeAndToutTypeInfo().get(0);
		this.verifyToutTimeDDListOption(tourDate, tourDateRegxs.get(2));
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		url = TestProperty.getProperty(env + WEB_URL_RECGOV);
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		tour.isUnifiedSearch = true;

		tour.contractCode = "NRSO";
		tour.parkId = "72777";
		tour.park =DataBaseFunctions.getFacilityName(tour.parkId, schema);//"VOYAGEURS NATIONAL PARK TOURS";

		tour.tourName = "Non Time Limited Capacity Yes Multi Day";
		tour.tourDate = DateFunctions.getDateAfterToday(3);
		tour.ticketNums = "1";
		tour.validDays = 5;

		tour.ticketTimeSeq = 1;

		tour.ticketTypes.add("Adult");
		tour.ticketTypeNums.add(tour.ticketNums);
		tour.deliveryMethod = "Will Call";

		//Mon Oct 29 2012 - Fri Nov 02 2012 8:30 AM - 9:00 PM - Adult 1
		tourDateRegxs.add("[A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+ - [A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+ 8:30 AM - 8:50 PM - "+tour.ticketTypes.get(0)+" \\d+");
		tourDateRegxs.add("[A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+ - [A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+ 8:30 AM - 9:00 PM - "+tour.ticketTypes.get(0)+" \\d+");
		tourDateRegxs.add("[A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+ - [A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+ 9:00 AM - 8:00 PM - "+tour.ticketTypes.get(0)+" \\d+");
	}

	/**
	 * Verify Tour time drop down list option. 
	 * Because it is so hard to verify the details start and close date in "tour order details" page based on the same option format (MMM dd - MMM dd),
	 * So I select the top three, then verify the tour time info in "Tour order details" page
	 */
	private void verifyToutTimeDDListOption(String tourDate, String tourDateRegx){
		if(!tourDate.replaceAll("\\s+", "").matches(tourDateRegx.replaceAll("\\s+", ""))){
			throw new ErrorOnPageException(tourDate +" doesn't match "+tourDateRegx);
		}else logger.info(tourDate +" matches "+tourDateRegx);
	}
}
