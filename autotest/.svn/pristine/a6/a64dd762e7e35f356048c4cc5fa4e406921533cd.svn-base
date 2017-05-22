package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteRollingRollupEndOfMonthExtraDays_True extends RATestCase {
	private String ruleName = "Maximum Window";
	
	/**
	 * The Maximum Window Length=11, Unit=month
	 */
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		
		//get the date after 11 month from today
		String theLastDayOfMaximumWindow = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), 11);
		
		bd.arrivalDate = DateFunctions.getDateAfterGivenDay(theLastDayOfMaximumWindow, -2);
		bd.lengthOfStay = "2";
		
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		
		/*
		 * Positively, verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(theLastDayOfMaximumWindow, 1), 2, "unavailable");
		
		/*
		 * Negatively, verifY the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 2, "available");
		/**
		 * TODO need to implement at the end of this month
		 */
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Kentucky";
		bd.park = "CARTER CAVES STATE RESORT PARK";
		bd.conType = "State";
		bd.contractCode = "KY";
		
		bd.siteNo = "011";
		bd.siteID="1552";
	}
}
