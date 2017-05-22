package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range search, sign in before, park see details button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_MS
 */
public class RA_MakeReservation_MS extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Mississippi";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(20,Calendar.MONDAY);
		bd.lengthOfStay = "3";
		bd.contractCode = "MS";
		bd.parkId = "151817";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"TOMBIGBEE";
		bd.isRange = true;
		
		bd.siteID = "473";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);//"Standard";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"001";
		
		newBd.siteID = "480";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//002
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "4";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickSiteNum = true;//forced to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("MS");
		isCancelAvail = !MiscFunctions.isNoCancel("MS"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("MS"); 
	}
}
