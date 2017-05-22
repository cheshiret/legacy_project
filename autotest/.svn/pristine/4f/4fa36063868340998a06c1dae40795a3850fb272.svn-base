package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, middle sign in, park name link, site see details button.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_CT
 */
public class RA_MakeReservation_CT extends RATestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "CT";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Connecticut";
		bd.parkId = "100128";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"SALT ROCK CAMPGROUND";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(20,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "CT";

		bd.siteID = "1513";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);//"Salt Rock Sites 1-45";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"001";
		
		newBd.siteID = "1514";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//002
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		bd.clickParkName = true;//forced to click on park name link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("CT");
		isCancelAvail = !MiscFunctions.isNoCancel("CT"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("CT"); 
	}
}
