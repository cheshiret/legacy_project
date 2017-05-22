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
 * -> Click a state link from state list 
 * -> Zoom out the map so that the expected park displayed 
 * -> Click the park link from the map browse section
 * -> Find Campsites search panel + Campground Details page
 * -> Click 'Book Now' from Campground Details page
 * -> Click 'Enter Date' in Campsite List page
 * -> Set arrival date and length of stay and click Check Availability button in Check Availability page
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * 
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_UT
 */
public class RA_MakeReservation_UT extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "UT";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Utah";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "UT";
		bd.parkId = "343871";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Utah Lake State Park";
		
		bookFromMap = true;
		bd.siteID = "6430";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"A002";
		
		newBd.siteID = "6431";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//A003
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		clickNameLink = true;//click park name link in Map Browse section
		clickStateName = true;// click a state name link in Map Home page
		bd.clickBookNow = true;//click Book Now in park details page
		bd.clickEnterDate = true;//click Enter Date in site list page
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("UT");
		isCancelAvail = !MiscFunctions.isNoCancel("UT"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("UT"); 
	}
}
