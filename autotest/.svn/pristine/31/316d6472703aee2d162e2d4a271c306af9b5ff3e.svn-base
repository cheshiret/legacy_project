package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, middle sign in, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_DE
 */
public class RA_MakeReservation_DE extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "DE";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Delaware";
		bd.parkId = "360109";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"DELAWARE SEASHORE STATE PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "DE";
		
		bd.siteID = "3226";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"206";
		
		newBd.siteID = "3227";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//207
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		bd.clickParkName = true;//forced to click on park name link
		bd.clickSiteNum = true;//forced to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("DE");
		isCancelAvail = !MiscFunctions.isNoCancel("DE"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("DE"); 
	}
}
