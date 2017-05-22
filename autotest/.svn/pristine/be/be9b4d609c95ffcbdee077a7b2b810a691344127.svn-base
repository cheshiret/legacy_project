package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.pl;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmissionRules_MaxNumberPerPrimaryOccupant extends WebTestCase {

	public void execute() {
		//make a camping lottery reservation
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		web.fillInLotteryDetails(bd, od);// fill in lottery application details page 1 and 2
		web.checkOutShoppingCart(pay); // check out shopping cart
		
		//make another reservation on same site
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		
		//verify the Maximum number per primary occupant rule
		web.verifyBusinessRuleInSiteDetailsPg("Maximum number per primary occupant",
				"You have exceeded the maximum number of applications allowed for a season.*");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ny.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "STONY BROOK STATE PARK";

		bd.loop = "Sites 035-053";
		bd.siteNo = "035";

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate =this.getArrivalDateForLottery();//set arrival date to not Monday
		bd.lengthOfStay = "4";
		bd.contractCode = "NY";
		}
	
	private String getArrivalDateForLottery(){		
			String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
			String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.SATURDAY, "EEE MMM d yyyy");
			return lotDate;		
		}
}
