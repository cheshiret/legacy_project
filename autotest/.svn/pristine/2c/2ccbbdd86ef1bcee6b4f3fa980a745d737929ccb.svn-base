package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NY
 */
public class MakeReservation_NY extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ny.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "Mongaup Pond";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "NY";
		bd.siteNo = "002";
		
		bookFromMap = false; //true;
		bd.siteID = "245133";
		clickNameLink = true;//Click on park name link in Map Search section
		bd.clickBookNow = true;//click Book Now in park details page
	}
}
