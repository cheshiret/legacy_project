package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmitCampingLotteryInRec extends RecgovTestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		//make a camping lottery reservation in REC.GOV
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
		schema =  TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.park = "CLARKS HILL";
		bd.parkId = "71090";
		bd.conType = "Federal";
		
		bd.lengthOfStay = "3";
		bd.contractCode = "NRSO";
		bd.loop = "ELIJ";
		bd.siteNo = "1";
		bd.siteID = "67661";
		
		bd.isUnifiedSearch=isUnifiedSearch();

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		bd.arrivalDate =this.getArrivalDateForLottery();
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
		// this case may be blocked by SpecifiedStayStart rule
		// could not submit lottery on Sunday
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.TUESDAY, "EEE MMM d yyyy");
		return lotDate;		
	}
}
