package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_IA
 */
public class REC_MakeReservation_NRRS_3 extends RecgovTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.conType = "Federal";
		bd.park = "BELOW DAM SOUTH CAROLINA";
		bd.parkId = "75452";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "3";
		bd.contractCode = "NRSO";
//		bd.siteNo = "01";		
		bd.siteID = "149412";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);
		bd.isUnifiedSearch=isUnifiedSearch();
		
		bookFromMap = true;
		signInMiddle = true;//middle sign in
		clickMapArea = true;//click on state area in Map Home page
		clickMapFlag = true;//click on park map flag in State map page
	}
}
