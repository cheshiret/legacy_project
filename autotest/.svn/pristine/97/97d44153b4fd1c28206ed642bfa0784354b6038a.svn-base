package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.staybeyondmaximumwindow;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteWeekUnit extends RATestCase {
	private String ruleName = "Stay Beyond Maximum Window";
//	private String errorMsg = "Your arrival day has to be within 30 day(s) from today. Please choose an arrival date on or before";
	private String errorMsg = "Your arrival day has to be within 30 Days(s) from today. Please choose an arrival date on or before";
	
	public void execute() {
		web.checkAndReleaseInventory(schema, DateFunctions.getDateAfterToday(30), 45, false, bd.siteID);

		web.invokeURL(url);
		web.signIn(email, pw);
		
		/*
		 * Positively, days includes 1 days maximum window and 10 days of length set in Stay Beyond Maximum Window will be available
		 */
		bd.arrivalDate = DateFunctions.getDateAfterToday(29);
		bd.lengthOfStay = "8";
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), "available");
		
		/*
		 * Negatively, verify the days greater than the stay beyond maximum window length are unavailable
		 */
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterToday(38), "3");
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterToday(38), 3, "unavailable");
		
		/*
		 * Negatively again, making a reservation which arrives at one day of stay beyond maximum window and stay length is less than stay
		 * beyond length will be blocked at Site Detail page by Maximum Window rule
		 */
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterToday(31), "7");
		web.verifyBusinessRuleEffectiveAtSiteDetailPage("Maximum Window", errorMsg, bd.siteNo);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema=TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		
		bd.state = "Kentucky";
		bd.park = "CARR CREEK STATE PARK";
		bd.conType = "State";
		bd.contractCode = "KY";
		bd.siteNo = "011";
		bd.siteID="1332";
	}
}
