package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 	See more detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @MakeReservation_MapSearch_2 
 */
public class MakeReservation_MapSearch_8 extends RecgovTestCase {

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
		bd.stateCode = "SC";
//		bd.siteNo = "023";
		bd.siteNo = "022";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"TWIN LAKES";
		
//		bd.siteID = "78708";
		bd.siteID = "78707";
		bookFromMap = true;
		isMapSearch = true;//search the park in Map Search section
		clickMapArea = true;// click a state from map area in Map Home page
		clickNameLink = true;//click on park name link from Map Search section
		bd.clickBookNow = true;//click Book Now from Campground Details page
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
