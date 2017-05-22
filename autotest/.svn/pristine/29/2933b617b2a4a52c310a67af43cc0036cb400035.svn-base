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
 * -> Set Near=another city name and click Go
 * -> All parks near the given city displayed in Map Browse section
 * -> Click the park link from the map browse section
 * -> Find Campsites search panel + Campground Details page
 * -> Search and verify a specific site for a specific date in Campground Details page
 * -> Verify the only searched site displayed in Campsite List page
 * -> Click "See Details" button for the specific site
 * -> Click "Book these Dates" button, process order, and order cancellation.
 * See more detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_IA
 */
public class RA_MakeReservation_IA extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "IA";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Alabama";
		bd.near = "Iowa";//search parks near Iowa in state map page
		bd.arrivalDate = DateFunctions.getDateAfterToday(15,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "IA";
		bd.parkId = "610105";
        bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);
		
//		bd.siteNo = "001";
		bd.siteID = "1378";//in qa2, it 1378
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		bd.isUpdateAble=false;
		
		newBd.siteID = "1379";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//002
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bookFromMap = true;
		signInMiddle = true;//middle sign in
		clickMapArea = true;//click on state area in Map Home page
		clickMapFlag = true;//click on park map flag in State map page
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("IA");
		isCancelAvail = !MiscFunctions.isNoCancel("IA"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("IA"); 
	}
}
