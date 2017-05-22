package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: This test case passed on QA2 environment, b/c of the access time rule in QA2, 
 * this test case can only run after 11:00AM and before 3:00PM on QA2 for now.
 * @Description: 
 * Site specific, date specific, sign in before, park name link, site see details button.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_ORNG
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-567
 * 
 * @author bzhang
 * @Date  May 20, 2011
 */
public class RA_MakeReservation_ORNG extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "ORNG";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "California";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "ORNG";
		bd.parkId = "590437";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"ONEILL REGIONAL PARK";
		
		bd.siteID = "291";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);
		
		newBd.siteID = "292";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//001
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//forced to click on park name link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("ORNG");
		isCancelAvail = !MiscFunctions.isNoCancel("ORNG"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/		
		bd.isUpdateAble = !MiscFunctions.isNoChange("ORNG"); 
	}
}
