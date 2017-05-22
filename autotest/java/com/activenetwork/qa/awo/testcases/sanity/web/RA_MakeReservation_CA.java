package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * Site specific, date specific, middle sign in, park see details button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_CA
 */
public class RA_MakeReservation_CA extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "CA";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "California";
		bd.parkId = "120039";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"HALF MOON BAY SB";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "CA";

		bd.siteID = "3631";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//001

		newBd.siteID = "3632";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//002
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";

		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};

		signInMiddle = true;//middle sign in
		bd.clickSiteNum = true;//forced to click on site number link

		isSiteTransfer = MiscFunctions.isSiteTransfer("CA");
		isCancelAvail = !MiscFunctions.isNoCancel("CA"); 
		bd.isUpdateAble = !MiscFunctions.isNoChange("CA"); 
	}
}
