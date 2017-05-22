package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, sign in before, park see details button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_LARC
 */
public class MakeReservation_LC extends WebTestCase {

	public void wrapParameters(Object[] param) {
	  	url = TestProperty.getProperty(env + ".web.larc.url");
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "LARC";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Larimer County";
		bd.park = "Carter Lake";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "LARC";

		bd.siteNo = "E03";
		bd.siteID = "426";
		bd.clickSiteNum = true;//forced to click on site number link
	}
}
