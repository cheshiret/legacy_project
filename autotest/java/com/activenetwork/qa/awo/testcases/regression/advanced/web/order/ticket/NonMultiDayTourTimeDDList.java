package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.ticket;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * On Tour details list, 
 * Drop down list shows 'Valid from Date' and 'Valid to Date' values in the list and sorted by open time ascending and by close time ascending.
 * @Preconditions:
 * 1.tour: Code:NTSLCWOMD, Non Time Limited Capacity No Multi Day,Timed Entry:No(means no time), Limited Capavity:Yes, Multi-Day:No
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
public class NonMultiDayTourTimeDDList extends RecgovTestCase{
	private List<String> tourDateRegx = new ArrayList<String>();
	private TicketInfo tour = new TicketInfo();

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.boolTourIntoTourDetailsPg(tour, false);
		this.verifyToutTimeDDList();
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

		tour.tourName = "Non Time Limited Capacity No Multi Day";
		tour.tourDate = DateFunctions.getDateAfterToday(3);
		tour.ticketNums = "2";

		tourDateRegx.add("8:30 AM - 8:50 PM -- \\d+ tickets available");
		tourDateRegx.add("8:30 AM - 9:00 PM -- \\d+ tickets available");
		tourDateRegx.add("9:00 AM - 8:00 PM -- \\d+ tickets available");
	}

	/**
	 * Verify Tour time drop down list
	 */
	private void verifyToutTimeDDList(){
		UwpTourDetailsPage tourDetailsPg = UwpTourDetailsPage.getInstance();
		List<String> tourTimes = tourDetailsPg.getToutTimesFromDDList();
		boolean result = true;

		result &= MiscFunctions.compareResult("The number of tour time", tourDateRegx.size(), tourTimes.size()-1);
		for(int i=1; i<tourTimes.size(); i++){
			if(!tourTimes.get(i).matches(tourDateRegx.get(i-1))){
				logger.info(tourTimes.get(i) +" doesn't match "+tourDateRegx.get(i-1));
				result = false;
			}else logger.info(tourTimes.get(i) +" matchs "+tourDateRegx.get(i-1));
		}

		if(!result){
			throw new ErrorOnPageException("Not all the check point are passed. Please check detail info from previous logs.");
		}
	}
}
