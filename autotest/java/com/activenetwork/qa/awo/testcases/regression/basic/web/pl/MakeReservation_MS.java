package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range search, sign in before, park see details button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_MS
 */
public class MakeReservation_MS extends WebTestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ms.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Mississippi";
		bd.park = "Trace";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "MS";
		bd.loop = "Deer Run";
		bd.siteNo = "002";
		bd.isRange = true;
		
		bd.siteID = "2384";
		bd.clickSiteNum = true;//forced to click on site number link
		isCancelAvail = false;//cancel is not available via web
	}
}
