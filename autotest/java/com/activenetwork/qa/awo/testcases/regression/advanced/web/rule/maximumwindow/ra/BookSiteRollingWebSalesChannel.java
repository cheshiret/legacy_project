package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteRollingWebSalesChannel extends RATestCase {
	private String ruleName = "Maximum Window";
	
	/**
	 * The Maximum Window Length=150 Unit=day
	 */
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		//verify the sites' status are available
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 2, "available");
		
		//verify the sites' status are unavailable
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterToday(151), 2, "unavailable");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		
		bd.state = "Kentucky";
		bd.park = "CARTER CAVES STATE RESORT PARK";
		bd.arrivalDate = DateFunctions.getDateAfterToday(149);
		bd.lengthOfStay = "2";
		bd.conType = "State";
		bd.contractCode = "KY";
		
		bd.siteNo = "003";
		bd.siteID="1544";
	}
}
