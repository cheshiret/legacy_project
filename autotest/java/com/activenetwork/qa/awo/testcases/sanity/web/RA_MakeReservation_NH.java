package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range search, sign in before, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_NH
 */
public class RA_MakeReservation_NH extends RATestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NH";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "New Hampshire";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(8,Calendar.MONDAY);
		bd.lengthOfStay = "3";
		bd.contractCode = "NH";
		bd.parkId = "270082";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"UMBAGOG LAKE STATE PARK";
		bd.isRange = true;
		
		bd.siteID = "1300";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"007";
		
		newBd.siteID = "1297";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//02
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "4";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//force to click on park name link
		bd.clickSiteNum = true;//force to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("NH");
		isCancelAvail = !MiscFunctions.isNoCancel("NH"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("NH"); 
		
		if(isSiteTransfer) {
			bd.siteIDs=new String[]{bd.siteID,newBd.siteID};
		}
	}
}
