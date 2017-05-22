package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range, middle sign in, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_ID
 */
public class MakeReservation_ID extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.id.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "ID";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Idaho";
		bd.park = "Priest Lake State Park";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "ID";

		bd.siteNo = "Lynx";
		
		bd.siteID = "3115";
		signInMiddle = true;//middle sign in
		bd.clickParkName = true;//force to click on park name link
		bd.clickSiteNum = true;//force to click on site number link
	}
}
