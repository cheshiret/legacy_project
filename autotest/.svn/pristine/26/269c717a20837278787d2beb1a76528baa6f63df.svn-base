package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteRollingWeekUnit extends RATestCase {
	private String ruleName = "Maximum Window";
	
	/**
	 * The Maximum Window Length=10, Unit=week
	 */
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		
		/*
		 * Positively, verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterToday(71), 2, "unavailable");
		
		/*
		 * Negatively, verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 2, "available");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Kentucky";
		bd.park = "CARTER CAVES STATE RESORT PARK";
		bd.arrivalDate = DateFunctions.getDateAfterToday(69);
		bd.lengthOfStay = "2";
		bd.conType = "State";
		bd.contractCode = "KY";
		
		bd.siteNo = "009";
		bd.siteID="1550";
	}
}
