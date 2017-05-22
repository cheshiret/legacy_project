package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmitCampingLottery_NY extends WebTestCase {

	public void execute() {
		//make a camping lottery reservation
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		// fill in lottery application details page 1 and 2
		web.fillInLotteryDetails(bd, od);
		String resId=web.checkOutFreeResCart(); // check out free shopping cart
		web.gotoMyReservationsAccount();// go to account panel
		web.verifyLotteryOrderStatusFromAccount(resId, "Entered - Payment Confirmed");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ny.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.parkId="221";
		bd.park =DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.conType = "State";
		
		bd.lengthOfStay = "4";
		bd.contractCode = "NY";

		bd.loop = "Loop One";
		bd.siteNo = "E002";
		bd.siteID = "247069";

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		//the arrival date is within lottery inventory date
		bd.arrivalDate = this.getArrivalDateForLottery();
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
		//set arrival date to Saturday, special start is Sunday
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.TUESDAY, "EEE MMM d yyyy");
		return lotDate;		
	}
}
