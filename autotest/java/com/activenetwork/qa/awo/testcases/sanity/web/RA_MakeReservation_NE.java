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
 * -> Click any state link from state list
 * -> Select another state from State dropdown list
 * -> All parks near the given city displayed in Map Browse section
 * -> Click the park link from the map browse section
 * -> Find Campsites search panel + Campground Details page
 * -> Click 'Book Now' from Campground Details page
 * -> Click 'Enter Date' in Campsite List page
 * -> Set arrival date and length of stay and click Check Availability button in Check Availability page
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NE_1
 */
public class RA_MakeReservation_NE extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NE";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Alabama";
		bd.switchToState = "Nebraska";//switch to Nebraska in sate map page
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "NE";
		bd.parkId = "230224";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Platte River SP";
		
		bookFromMap = true;
		bd.siteID = "1611";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"006OWEN";
//		
		newBd.siteID = "1612";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//007OWEN
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);//DEVI_RVSiteLoop31-59_RV039
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		clickStateName = true;// click a state name link in Map Home page
		bd.clickBookNow = true;//click Book Now in park details page
		bd.clickEnterDate = true;//click Enter Date in site list page
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("NE");
		isCancelAvail = !MiscFunctions.isNoCancel("NE"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("NE"); 
	}
}
