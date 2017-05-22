package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_RI
 */
public class MakeReservation_RI extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ri.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "RI";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Rhode Island";
		bd.park = "George Washington Campground";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "RI";
		bd.siteNo = "A002";
		
		bookFromMap = true;
		bd.siteID = "2351";
		signInMiddle = true;//middle sign in
		clickMapArea = true;// click a state area on map from Map Home page
		clickMapFlag = true;// click the park icon in state map
	}
}
