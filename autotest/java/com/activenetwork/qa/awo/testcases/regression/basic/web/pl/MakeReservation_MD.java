package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, sign in before, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_MD
 */
public class MakeReservation_MD extends WebTestCase {
	
	public void wrapParameters(Object[] param) {
	  	url = TestProperty.getProperty(env + ".web.md.url");
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MD";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Maryland";
		bd.park = "ELK NECK STATE PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(8);
		bd.lengthOfStay = "2";
		bd.contractCode = "MD";

		bd.siteID="1142";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema); //"3 CARDINAL";
		bd.clickParkName = true;//forced to click on park name link
		bd.clickSiteNum = true;//forced to click on site number link
	}
}
