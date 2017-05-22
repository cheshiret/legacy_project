package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range, middle sign in, park name link, site specific date
 * A link. See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_KY
 */
public class RA_MakeReservation_KY extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Kentucky";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "KY";
		bd.parkId = "91913";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"PENNYRILE FOREST STATE RESORT PARK";
		bd.isRange = true;

		bd.siteID = "1386"; //"1424";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"028";016
		
		newBd.siteID = "1369";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//015
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;// middle sign in
		bd.clickParkName = true;// force to click on park name link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("KY");
		isCancelAvail = !MiscFunctions.isNoCancel("KY"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("KY"); 
	}
}
