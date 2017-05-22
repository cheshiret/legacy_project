package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, sign in before, park see details button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_LARC
 */
public class RA_MakeReservation_LARC extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "LARC";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Colorado";
		bd.conType = "Other";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "LARC";  //represent Larimer County Contract in ORMS
		bd.parkId = "710102";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Carter Lake";
		
		bd.siteID = "430";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"E07";
		
		newBd.siteID = "438";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//E08
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickSiteNum = true;//forced to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("LARC");
		isCancelAvail = !MiscFunctions.isNoCancel("LARC"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("LARC"); 
	}
}
