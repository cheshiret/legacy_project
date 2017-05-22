package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.pl;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmissionRules_SpecifiedStayStart extends WebTestCase {

	public void execute() {
		//make a camping lottery reservation
		web.invokeURL(url);		
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		
		//verify the Specify stay start rule
		web.verifyBusinessRuleInSiteDetailsPg("Specify stay start","Arrival date must be Tuesday.*");
		
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterGivenDay(bd.arrivalDate, 1), bd.lengthOfStay);
		web.fillInLotteryDetails(bd, od);
		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ny.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "STONY BROOK STATE PARK";
		bd.conType = "State";

		bd.loop = "Sites 035-053";
		bd.siteNo = "037";
		
		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate =this.getArrivalDateForLottery();// set date to Monday which can not start by rule setup
		bd.lengthOfStay = "4";
		bd.contractCode = "NY";
		}
	
	private String getArrivalDateForLottery(){		
			String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
			String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.MONDAY, "EEE MMM d yyyy");
			return lotDate;		
		}
}
