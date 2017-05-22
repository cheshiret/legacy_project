package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range search, sign in before, park name link, site specific date A link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NM
 */
public class MakeReservation_NM extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.nm.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NM";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New Mexico";
		bd.park = "Bluewater Lake";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.endDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "2";
		bd.contractCode = "NM";
		bd.siteNo = "28";
		bd.isRange = true;
		
		bd.siteID = "1029";
		bd.clickParkName = true;//force to click on park name link
	}
}
