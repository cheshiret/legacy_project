package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_WI
 */
public class MakeReservation_WI extends WebTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.wi.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "WI";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Wisconsin";
		bd.park = "NEW GLARUS WOODS STATE PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "WI";
		bd.siteNo = "052";
		bd.isRange = true;
		
		bookFromMap = true;
		bd.siteID = "2963";
		bd.clickSiteNum = true;
		isCancelAvail = !MiscFunctions.isNoCancel("WI"); 
	}
}
