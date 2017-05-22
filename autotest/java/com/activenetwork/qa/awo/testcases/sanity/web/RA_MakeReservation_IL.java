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
 * Test case name @RA_MakeReservation_IL
 */
public class RA_MakeReservation_IL extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "IL";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Illinois";
		bd.parkId = "452421";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"Fox Ridge State Park";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(15,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "IL";

		bd.siteID = "1116";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//002

		newBd.siteID = "1117";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//003
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};

		signInMiddle = true;//middle sign in
		bd.clickSiteNum = true;//forced to click on site number link

		isSiteTransfer = MiscFunctions.isSiteTransfer("IL");
		isCancelAvail = !MiscFunctions.isNoCancel("IL"); 
		bd.isUpdateAble = !MiscFunctions.isNoChange("IL"); 
	}
}
