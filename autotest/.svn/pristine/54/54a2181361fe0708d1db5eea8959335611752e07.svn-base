package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, middle sign in, park/site see details button.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_SC
 */
public class RA_MakeReservation_SC extends RATestCase {
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "South Carolina";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.FRIDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "SC";
		bd.parkId = "10371";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"DEVILS FORK";

		bd.siteID = "2308";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//038
		
		newBd.siteID = "2306";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//036
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);//DEVI_RVSiteLoop31-59_RV039
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		signInMiddle = true;//middle sign in
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("SC");
		isCancelAvail = !MiscFunctions.isNoCancel("SC"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("SC"); 
	}
}
