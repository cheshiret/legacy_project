package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.ra;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmissionRules_MinimumStay extends RATestCase {

	public void execute() {
		//make a camping lottery reservation
		web.invokeURL(url);		
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		
		//verify the minimum stay rule
		web.verifyBusinessRuleInSiteDetailsPg("Minimum stay","Minimum length of stay.*");

		web.updateDateInSiteDetailPg(bd.arrivalDate, "4");//update stay length over 3
		web.fillInLotteryDetails(bd, od);
		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "STONY BROOK STATE PARK";
		bd.conType = "State";

		bd.loop = "Sites 035-053";
		bd.siteNo = "036";

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		bd.arrivalDate =this.getArrivalDateForLottery();//set arrival date to not Monday
		bd.lengthOfStay = "3";
		bd.contractCode = "NY";
		}
	
	private String getArrivalDateForLottery(){		
			String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
			String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.SATURDAY, "EEE MMM d yyyy");
			return lotDate;		
		}
}
