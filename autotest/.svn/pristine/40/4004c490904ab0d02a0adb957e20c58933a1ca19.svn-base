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
 * -> Click a state from map 
 * -> Zoom out the map so that the expected park displayed 
 * -> Click the park icon in the map and click 'Enter Date' in pop up window
 * -> Find Campsites search panel + Campground Map page 
 * -> Search and verify a specific site for a specific date in Campground Map page
 * -> Click Campsite List tab and verify the only searched site displayed 
 * -> Click "See Details" button for the specific site
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_RI
 */
public class RA_MakeReservation_RI extends RATestCase {
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "RI";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Rhode Island";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "RI";
		bd.parkId = "253123";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"George Washington Campground";
		
		bookFromMap = true;
		bd.siteID = "1245";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);//"GEORGE WASHINGTON";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"01";
		
		newBd.siteID = "1246";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//02
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);//DEVI_RVSiteLoop31-59_RV039
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		clickMapArea = true;// click a state area on map from Map Home page
		clickMapFlag = true;// click the park icon in state map
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("RI");
		isCancelAvail = !MiscFunctions.isNoCancel("RI"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("RI"); 
	}
}
