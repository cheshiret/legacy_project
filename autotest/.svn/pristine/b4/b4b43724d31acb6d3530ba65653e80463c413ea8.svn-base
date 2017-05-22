package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.staybeyondmaximumwindow;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteMonthUnit extends RATestCase {
	private String ruleName = "Stay Beyond Maximum Window";
//	private String errorMsg = "Your arrival day has to be within 30 day(s) from today. Please choose an arrival date on or before";
	private String errorMsg = "Your arrival day has to be within 30 Days(s) from today. Please choose an arrival date on or before";
	
	public void execute() {
		web.checkAndReleaseInventory(schema, DateFunctions.getToday(), 60, false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		
		/*
		 * Positively, days includes 1 days maximum window and 10 days of length set in Stay Beyond Maximum Window will be available
		 */
		bd.arrivalDate = DateFunctions.getDateAfterToday(29);
		bd.lengthOfStay = "" + DateFunctions.diffBetween(DateFunctions.parseDateString(DateFunctions.getDateAfterToday(31)), DateFunctions.parseDateString(bd.arrivalDate));
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		//because the maximum number of the site availability calendar is 14, so only can verify the 14 days availabilities
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 13, "available");
		
		/*
		 * Negatively, verify the days greater than the stay beyond maximum window length are unavailable
		 */
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.parseInt(bd.lengthOfStay)), "3");
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.parseInt(bd.lengthOfStay)), 3, "unavailable");
		
		/*
		 * Negatively again, making a reservation which arrives at one day of stay beyond maximum window and stay length is less than stay
		 * beyond length will be blocked at Site Detail page by Maximum Window rule
		 */
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterGivenDay(DateFunctions.getDateAfterGivenDay(bd.arrivalDate, Integer.parseInt(bd.lengthOfStay)), 1), "10");
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
		
		bd.siteNo = "013";
		bd.siteID="1334";
	}
}
