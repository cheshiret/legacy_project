package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range, middle sign in, park see detail button, site specific date A link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_FL
 */
public class RA_MakeReservation_FL extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "FL";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Florida";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "FL";
		bd.parkId = "281060";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"OLENO SP";
		bd.isRange = true;
		
		bd.siteID = "3329";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);//"Dog1";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"032";
		
		newBd.siteID = "1914";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//061
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("FL");
		isCancelAvail = !MiscFunctions.isNoCancel("FL"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("FL"); 
	}
}
