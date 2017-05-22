package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteBlockWeekBlockReleaseUnit_BlockReleaseDayOfWeek extends RATestCase {
	private String ruleName = "Maximum Window";
	
	private String theLastestArrivalDate;
	
	/**
	 * The Maximum Window Type=block, Length=10, Unit=month, Block Release Length=1, Block Release Unit=week, Block Release Day Of Week=Wednesday 
	 */
	public void execute() {
		web.checkAndReleaseInventory(schema, DateFunctions.getDateAfterToday(300), DateFunctions.getDateAfterToday(314), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		
		String today = DateFunctions.getToday();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateFunctions.parseDateString(today));
		
		int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
		
		if(weekIndex < 4) {
			//get the last week's Wednesday's date
			calendar.add(Calendar.DAY_OF_WEEK, -(weekIndex + 3));
		} else {
			//get this week's Wednesday's date
			calendar.add(Calendar.DAY_OF_WEEK, 4-weekIndex);
		}
		
		theLastestArrivalDate = DateFunctions.getDateAfterGivenDay(DateFunctions.getDateAfterGivenMonth(DateFunctions.formatDate(calendar.getTime(), "M/d/yyyy"), 10), 7);
		
		bd.arrivalDate = DateFunctions.getDateAfterGivenDay(theLastestArrivalDate, -2);
		bd.lengthOfStay = "2";
		
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(theLastestArrivalDate, 1), 3, "unavailable");
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 2, "available");
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
		
		bd.siteNo = "018";
		bd.siteID="1559";
	}
}
