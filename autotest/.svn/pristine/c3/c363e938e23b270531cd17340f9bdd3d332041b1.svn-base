package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_UT
 */
public class MakeReservation_UT extends WebTestCase {

	public void wrapParameters(Object[] param) {
	  	url = TestProperty.getProperty(env + ".web.ut.url");
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "UT";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Utah";
//		bd.park = "Quail Creek State Park";
		bd.park = "Huntington State Park";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "UT";
		bd.siteNo = "HU02";
		
		bookFromMap = true;
		bd.siteID = "4176";
		clickNameLink = true;//click park name link in Map Browse section
		clickStateName = true;// click a state name link in Map Home page
		bd.clickBookNow = true;//click Book Now in park details page
		bd.clickEnterDate = true;//click Enter Date in site list page
	}
}
