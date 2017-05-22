package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteBlockMonthBlockReleaseUnit_BlockReleaseDayOfWeek extends RATestCase {
	private String ruleName = "Maximum Window";
	
	private String theLastestArrivalDate;
	
	/**
	 * The Maximum Window Type=block, Length=10, Unit=month, Block Release Length=1, Block Release Unit=month, Block Release Day Of Week=Monday 
	 */
	public void execute() {
		web.checkAndReleaseInventory(schema, DateFunctions.getDateAfterToday(300), DateFunctions.getDateAfterToday(330), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		
		/*
		int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(weekIndex == 1) {//Sunday
			//get the last week's Monday's date
			calendar.add(Calendar.DAY_OF_WEEK, -(weekIndex + 5));
		} else {
			//get this week's Monday's date
			calendar.add(Calendar.DAY_OF_WEEK, 2-weekIndex);
		}*/
		
		//the lastest arrival date must be Monday
//		theLastestArrivalDate = DateFunctions.getDateNearestDayOfWeek(DateFunctions.getDateAfterGivenMonth(DateFunctions.formatDate(calendar.getTime(), "M/d/yyyy"), 11), "MONDAY");
		theLastestArrivalDate = DateFunctions.getDateAfterGivenDay(DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), 11), -1);
		
		bd.arrivalDate = DateFunctions.getDateAfterGivenDay(theLastestArrivalDate, -2);
		bd.lengthOfStay = "3";
		
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(theLastestArrivalDate, 1), 3, "unavailable");
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 3, "available");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Kentucky";
		bd.park = "CARTER CAVES STATE RESORT PARK";
		bd.conType = "State";
		bd.contractCode = "KY";
		
		bd.siteNo = "019";
		bd.siteID="1560";
	}
}
