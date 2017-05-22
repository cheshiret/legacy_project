package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 					- - - Map search (with specific date search) - - -
 * -> Go to "Campgrounds by Map" page 
 * -> Search a specific park in Map Home page
 * -> Map page for selected park with park name displayed in Map Search section
 * -> Click 'A' icon from map and click 'See Details' in pop up widget
 * -> Find Campsites search panel + Campground Map page
 * -> Search and verify a specific site for a specific date in Campground Map page
 * -> Click Campsite List tab and verify the only searched site displayed 
 * -> Click "See Details" button for the specific site
 * -> Click "Book these Dates" button, 
 * -> Sign in 
 * -> process order, and order cancellation.
 * 
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_VA
 */
public class RA_MakeReservation_VA extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "VA";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Virginia";
		bd.arrivalDate = DateFunctions.getDateAfterToday(15,Calendar.SATURDAY);
		bd.lengthOfStay = "7";
		bd.contractCode = "VA";
		bd.parkId = "140171";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"TWIN LAKES STATE PARK";
		
		bookFromMap = true;
		signInMiddle = true;//middle sign in
		bd.siteID = "4978";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"01-SAT-S";
		
		newBd.siteID = "4979";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//02-SUN-SW
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		clickMapFlag = true;// click the park icon in state map
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("VA");
		isCancelAvail = !MiscFunctions.isNoCancel("VA"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/		
		bd.isUpdateAble = !MiscFunctions.isNoChange("VA"); 
	}
}
