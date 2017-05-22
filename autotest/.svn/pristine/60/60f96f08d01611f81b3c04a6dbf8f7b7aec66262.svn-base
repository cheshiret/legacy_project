package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 					- - - Map search (with specific date search) - - -
 * -> Sign in 
 * -> Go to "Campgrounds by Map" page 
 * -> Search a specific park in Map Home page
 * -> Map page for selected park with park name displayed in Map Search section
 * -> Click park name link in Map Search section
 * -> Find Campsites search panel + Campground Details page
 * -> Search and verify a specific site for a specific date in Campground Map page
 * -> Click 'Book Now' from Campground Details page
 * -> Click 'See Details' button in Campsite List page
 * -> Set arrival date and length of stay and click Check Availability button in Check Availability page
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NY
 */
public class RA_MakeReservation_NY extends RATestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "New York";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(8,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "NY";
		bd.parkId = "100";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"MONGAUP POND";
		
		bookFromMap = true;
		bd.siteID = "245140";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);// "009"
		
		newBd.siteID = "245141";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//010
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		clickNameLink = true;//Click on park name link in Map Search section
		bd.clickBookNow = true;//click Book Now in park details page
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("NY");
		isCancelAvail = !MiscFunctions.isNoCancel("NY"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("NY"); 
	}
}
