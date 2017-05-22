package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.minimumwindow;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteWebSalesChannel extends RATestCase {
	private String ruleName = "Minimum Window";
	private String errorMsg = "Earliest arrival date for on-line reservations at this facility is";
		
	public void execute() {
		web.checkAndReleaseInventory(schema,DateFunctions.getDateAfterToday(2), 5, false, bd.siteID);
		
		web.invokeURL(url);
		web.signIn(email, pw);
		/*
		 * Positively, making a reservation which the arrival date is within minimum window will be blocked at Site Detail page
		 */
		bd.arrivalDate = DateFunctions.getDateAfterToday(2);
		bd.lengthOfStay = "3";
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, errorMsg, bd.siteNo);
		
		/*
		 * Negatively, making a reservation which the arrival date is later than the minimum window will succeed
		 */
		bd.startDate = "";
		bd.arrivalDate = DateFunctions.getDateAfterToday(6);
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		web.fillInOrderDetails(bd.contractCode);
		String resID = web.checkOutCart(pay);
		
		//clean up reservation
		web.cleanUpReservation(resID, bd.contractCode, "Confirmed");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		
		//reservation info
		bd.state = "Kentucky";
		bd.park = "LAKE MALONE STATE PARK";
		bd.conType = "State";
		bd.contractCode = "KY";
		
		bd.siteNo = "003";
		bd.siteID="3933";
		
	}
}
