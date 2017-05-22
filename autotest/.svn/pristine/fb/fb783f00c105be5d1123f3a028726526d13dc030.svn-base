package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 					- - - Map search (with range date search) - - -
 * -> Sign in 
 * -> Go to "Campgrounds by Map" page 
 * -> Search a specific park in Map Home page
 * -> Map page for selected park with park name displayed in Map Search section
 * -> Click "See Details" button for the given park in Map search section
 * -> Find Campsites search panel + Campground Map page
 * -> Search and verify a specific site for a range date in Campground Map page
 * -> Click Date Range Availability tab and verify the only searched site displayed 
 * -> Click Site# link for the specific site
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * 
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_WI
 */
public class RA_MakeReservation_WI extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "WI";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Wisconsin";
		bd.arrivalDate = DateFunctions.getDateAfterToday(7,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "WI";
		bd.parkId = "60028";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"NEW GLARUS WOODS STATE PARK";
		bd.isRange = true;
		
		bookFromMap = true;
		bd.siteID = "2965";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"054";
		bd.clickSiteNum = true;
		
		newBd.siteID = "2966";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//055
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("WI");
		isCancelAvail = !MiscFunctions.isNoCancel("WI"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/		
		bd.isUpdateAble = !MiscFunctions.isNoChange("WI"); 
	}
}
