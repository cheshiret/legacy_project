package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range, middle sign in, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_ID
 */
public class RA_MakeReservation_ID extends RATestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "ID";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Idaho";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "ID";
		bd.parkId = "311011";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"Priest Lake State Park";
		bd.isRange = true;
		
		bd.siteID = "3117";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"Mackinaw";
		
		newBd.siteID = "3116";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//Grizzly
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		bd.clickParkName = true;//force to click on park name link
		bd.clickSiteNum = true;//force to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("ID");
		isCancelAvail = !MiscFunctions.isNoCancel("ID"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("ID"); 
	}
}
