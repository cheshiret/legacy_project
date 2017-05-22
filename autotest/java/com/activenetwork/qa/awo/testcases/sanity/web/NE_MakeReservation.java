package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.PLTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 					- - - Map search (with specific date search) - - -
 * -> Sign in 
 * -> Go to "Campgrounds by Map" page 
 * -> Zoom out the map so that the expected park displayed 
 * -> Click the park icon in the map and click 'Enter Date' in pop up window
 * -> Find Campsites search panel + Campground Map page 
 * -> Search and verify a specific site for a specific date in Campground Map page
 * -> Click Campsite List tab and verify the only searched site displayed 
 * -> Click "See Details" button for the specific site
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * 
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_RI
 */
public class NE_MakeReservation extends PLTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ne.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NE";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Nebraska";
		bd.arrivalDate = DateFunctions.getDateAfterToday(20,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "NE";
		bd.park = "Platte River SP";
		bd.siteID = "1612";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"007OWEN";
		
		bookFromMap = true;
		clickMapFlag = true;// click the park icon in state map
	}
}
