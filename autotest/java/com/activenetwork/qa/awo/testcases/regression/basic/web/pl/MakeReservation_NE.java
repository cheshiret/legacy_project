package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NE_1
 */
public class MakeReservation_NE extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ne.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NE";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Nebraska";
		bd.park = "Platte River SP";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "NE";
		bd.siteNo = "001OWEN";
		
		bookFromMap = true;
		bd.siteID = "1606";
		signInMiddle = true;//middle sign in
		clickStateName = true;// click a state name link in Map Home page
		bd.clickBookNow = true;//click Book Now in park details page
		bd.clickEnterDate = true;//click Enter Date in site list page
	}
}
