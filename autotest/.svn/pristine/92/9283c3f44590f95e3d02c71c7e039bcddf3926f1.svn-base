package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date range, middle sign in, park see detail button, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_EB
 */
public class RA_MakeReservation_EB extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "EBAY";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "California";
		bd.conType = "Other";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "EB";
		bd.parkId = "110028";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"SUNOL";
		bd.isRange = true;
		
		bd.siteID = "395";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);//"Family Sites 1-4";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//FAM 1
		
		newBd.siteID = "397";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//FAM 2
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		web.updateProductActiveStatus(schema, ACTIVE_STATUS,bd.siteIDs);
		
		signInMiddle = true;//middle sign in
		bd.clickSiteNum = true;//forced to click on site number link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("EB");
		isCancelAvail = !MiscFunctions.isNoCancel("EB"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("EB"); 
	}
}
