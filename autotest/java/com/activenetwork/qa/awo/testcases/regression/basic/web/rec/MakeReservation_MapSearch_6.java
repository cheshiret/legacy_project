package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 	See more detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_UT
 */
public class MakeReservation_MapSearch_6 extends RecgovTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.conType = "Federal";
		bd.parkId = "71464";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "3";
		bd.contractCode = "NRSO";
		bd.siteNo = "021";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"TWIN LAKES";
		
		bd.siteID = "78706";
		bookFromMap = true;
		clickNameLink = true;//click park name link in Map Browse section
		clickStateName = true;// click a state name link in Map Home page
		bd.clickBookNow = true;//click Book Now in park details page
		bd.clickEnterDate = true;//click Enter Date in site list page
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
