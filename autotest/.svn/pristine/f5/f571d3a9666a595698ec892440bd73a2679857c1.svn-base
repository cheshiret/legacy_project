package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, middle sign in, park see details button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_CO
 */
public class MakeReservation_CO extends WebTestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.co.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "CO";

		bd.state = "Colorado";
		bd.park = "Sylvan Lake";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "CO";

		bd.loop = "CABN CABINS";
		bd.siteNo = "003";
		
		bd.siteID = "4456";
		signInMiddle = true;//middle sign in
		bd.clickSiteNum = true;//forced to click on site number link
	}
}
