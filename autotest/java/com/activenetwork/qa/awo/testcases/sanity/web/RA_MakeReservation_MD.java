package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, sign in before, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_MD
 */
public class RA_MakeReservation_MD extends RATestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MD";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Maryland";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(14,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "MD";
		bd.parkId = "380511";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"ELK NECK STATE PARK";
		
		bd.siteID = "1309";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"123";	
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		
		newBd.siteID = "1293";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//136
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//forced to click on park name link
		bd.clickSiteNum = true;//forced to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("MD");
		isCancelAvail = !MiscFunctions.isNoCancel("MD"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("MD"); 
	}
}
