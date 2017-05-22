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
 * -> Search and verify a specific site for a specific date in Campground Details page
 * -> Verify the only searched site displayed in Campsite List page
 * -> Click "See Details" button for the specific site
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * 
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_OR
 */
public class RA_MakeReservation_OR extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "OR";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Oregon";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "OR";
		bd.parkId = "404074";
		bd.park =  DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Indian Mary";
		bd.isRange = true;
		
		bookFromMap = true;
		bd.siteID = "16567";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"086";
		
		newBd.siteID = "16568";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//087
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		clickNameLink = true;//click on park name link in Map Search section
		clickStateName = true;// click a state name link in Map Home page
		bd.clickSiteNum = true;//force to click on site number link in site list page
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("OR");
		isCancelAvail = !MiscFunctions.isNoCancel("OR"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("OR"); 
	}
}
