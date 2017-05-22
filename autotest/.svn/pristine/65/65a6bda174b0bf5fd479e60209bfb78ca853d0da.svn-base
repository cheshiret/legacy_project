package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, sign in before, park name link, site see details button.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_MA
 */
public class RA_MakeReservation_MA extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MA";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Massachusetts";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "MA";
		bd.parkId = "32605";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"DAR State Forest";
		
		bd.siteID = "102040";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"002";
		
		newBd.siteID = "102039";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//001
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//forced to click on park name link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("MA");
		isCancelAvail = !MiscFunctions.isNoCancel("MA"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("MA"); 
	}
}
