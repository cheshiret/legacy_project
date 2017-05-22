package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteBlockMonthBlockReleaseUnit_BlockReleaseDayOfMonth extends RATestCase {
	private String ruleName = "Maximum Window";
	
	private String theLastestArrivalDate;
	
	/**
	 * The Maximum Window Type=block, Length=10, Unit=month, Block Release Length=1, Block Release Unit=month, Block Release Day Of Month=20th
	 */
	public void execute() {
		web.checkAndReleaseInventory(schema, DateFunctions.getDateAfterToday(330), DateFunctions.getDateAfterToday(330), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		
		String dayOfToday = DateFunctions.getToday().split("/")[1];
		String monthOfToday = DateFunctions.getToday().split("/")[0];
		String yearOfToday = DateFunctions.getToday().split("/")[2];
		
		if(Integer.parseInt(dayOfToday) < 20) {
			theLastestArrivalDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.combineStringToDate(yearOfToday, String.valueOf(Integer.parseInt(monthOfToday) - 1), "20"), 11);
		} else {
			theLastestArrivalDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.combineStringToDate(yearOfToday, monthOfToday, "20"), 11);
		}
		
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
		
		bd.siteNo = "020";
		bd.siteID = "1561";
		bd.lengthOfStay = "2";
	}
}
