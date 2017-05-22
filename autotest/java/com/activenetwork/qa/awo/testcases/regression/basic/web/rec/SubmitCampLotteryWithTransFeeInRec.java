package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmitCampLotteryWithTransFeeInRec  extends RecgovTestCase {

	public void execute() {
		//make a camping lottery reservation in REC.GOV
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		// fill in lottery application details page 1 and 2
		web.fillInLotteryDetails(bd, od);
		String resId=web.checkOutShoppingCart(pay); // check out shopping cart
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
		bd.loop = "COVE";
		bd.siteNo = "03";
		bd.siteID = "149295";
		
		bd.isUnifiedSearch=isUnifiedSearch();

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		bd.arrivalDate = this.getArrivalDateForLottery();
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
//		System.out.println("start date:"+startDate);
		// start date could not be Sunday, as rule has been set up
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.MONDAY, "EEE MMM d yyyy");
//		System.out.println("lot date:"+lotDate);
		return lotDate;		
	}
}
