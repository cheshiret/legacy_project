package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, middle sign in, park/site see details button.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_SC
 */
public class MakeReservation_SC extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.sc.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.park = "Devils Fork";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "SC";

		bd.loop = "Double Springs";
		bd.siteNo = "B013";
		bd.siteID = "1049";
		signInMiddle = true;//middle sign in
	}
}
