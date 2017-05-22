package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.pl;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: business rules only work when we set lottery attribute to "Site Name/Code For" level. it will not work if we select "Loop/Area for" ;
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Sep 21, 2011
 */
public class BusinessRules_MinimumStay extends WebTestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		web.verifyBusinessRuleInSiteDetailsPg("Minimum stay", "Minimum length of stay.*");
		
		web.updateDateInSiteDetailPg(bd.arrivalDate, "4");
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
		bd.parkId="221";
		bd.park =DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.conType = "State";

//		bd.loop = "Loop One";
//		bd.siteNo = "E004";
//		bd.siteID = "247046";
		
		bd.loop = "Loop Four";
		bd.siteNo = "045";
		bd.siteID = "247089";
		
		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate = this.getArrivalDateForLottery();// set the arrival date for lottery inventory date
		bd.lengthOfStay = "3";//Minimum stay is 4
		bd.contractCode = "NY";
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.SATURDAY, "EEE MMM d yyyy");
		return lotDate;		
	}
}
