package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.ra;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
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
public class BusinessRules_SpecifiedStayStart extends RATestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		web.verifyBusinessRuleInSiteDetailsPg("Special stay start", ".*Arrival date (at this facility )?must be.*");
		
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterGivenDay(bd.arrivalDate, 1), bd.lengthOfStay);
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
		bd.parkId="221";
		bd.park =DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.conType = "State";

		bd.loop = "Loop Four";
		bd.siteNo = "046";
		bd.siteID = "247090";
		
		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate = this.getArrivalDateForLottery();//set arrival date to Saturday, special start is Sunday
		bd.lengthOfStay = "3";
		bd.contractCode = "NY";
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
		//set arrival date to Saturday, special start is Sunday
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.SATURDAY, "EEE MMM d yyyy");
		return lotDate;		
	}
}
