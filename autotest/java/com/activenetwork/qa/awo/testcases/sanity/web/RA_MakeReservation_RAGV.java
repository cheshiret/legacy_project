package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range search, sign in before, park see details button, site specific date A link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NE
 */
public class RA_MakeReservation_RAGV extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "RAGV";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Kentucky";
		bd.conType = "Private";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "RAGV";
		bd.parkId = "710101";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"Pikeville RV Park";
		bd.isRange = true;
		
		bd.siteID = "200";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);// "1";
		
		newBd.siteID = "201";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//2
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("RAGV");
		isCancelAvail = !MiscFunctions.isNoCancel("RAGV"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("RAGV"); 
	}
}
