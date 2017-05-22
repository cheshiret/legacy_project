package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Site specific, date specific, sign in before, park name link, site see details button.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_IN
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-567
 * 
 * @author bzhang
 * @Date  May 20, 2011
 */
public class RA_MakeReservation_IN extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "IN";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Indiana";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "IN";
		bd.parkId = "570053";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"Shakamak";
		
		bd.siteID = "5845";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema); //"SHAKAMAK ELECTRIC";
		
		newBd.siteID = "5846";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//008
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//forced to click on park name link
		bd.isUpdateAble = false; //the selected part don't allow update on reservation detail page
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("IN");
		isCancelAvail = !MiscFunctions.isNoCancel("IN"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("IN"); 
		
	}
}
