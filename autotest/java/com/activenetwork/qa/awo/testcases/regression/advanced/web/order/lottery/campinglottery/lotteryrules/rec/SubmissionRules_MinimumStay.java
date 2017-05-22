package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.rec;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmissionRules_MinimumStay extends RecgovTestCase {

	public void execute() {
		//make a camping lottery reservation in REC.GOV
		web.invokeURL(url);		
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		
		//verify the minimum stay rule
		web.verifyBusinessRuleInSiteDetailsPg("Minimum stay","Minimum length of stay.*");

		web.updateDateInSiteDetailPg(bd.arrivalDate, "3");//update stay length over 2
		web.fillInLotteryDetails(bd, od);
		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.park = "CLARKS HILL";
		bd.parkId = "71090";
		bd.conType = "Federal";

		bd.contractCode = "NRSO";
		bd.loop = "ELIJ";
		bd.siteNo = "2";

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate =this.getArrivalDateForLottery();// set arrival date to not sunday
		bd.lengthOfStay = "2";//minimum stay is 3
		
		bd.isUnifiedSearch=isUnifiedSearch();
		}
	
	private String getArrivalDateForLottery(){		
			String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
			String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.MONDAY, "EEE MMM d yyyy");
			return lotDate;		
		}
}
