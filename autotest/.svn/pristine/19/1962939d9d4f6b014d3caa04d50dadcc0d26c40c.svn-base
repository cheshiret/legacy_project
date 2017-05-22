package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range search, sign in before, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NH
 */
public class MakeReservation_NH extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.nh.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NH";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New Hampshire";
		bd.park = "Umbagog Lake State Park";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "3";
		bd.contractCode = "NH";
		bd.siteNo = "002";
		bd.isRange = true;
		
		bd.siteID = "1297";
		bd.clickParkName = true;//force to click on park name link
		bd.clickSiteNum = true;//force to click on site number link
	}
}
