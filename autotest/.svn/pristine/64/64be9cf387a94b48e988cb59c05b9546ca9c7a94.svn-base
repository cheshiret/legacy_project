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
 * Test case name @RA_MakeReservation_MRV
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-567
 * 
 * @author bzhang
 * @Date  May 20, 2011
 */
public class RA_MakeReservation_MRV extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MRV";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "New Jersey";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "3";
		bd.contractCode = "MRV";  //Morgan RV Contract
		bd.parkId = "481003";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Atlantic Blueberry Hill";
		
		bd.siteID = "1212";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"F/H";
		
		newBd.siteID = "1211";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//N/H
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "4";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//forced to click on park name link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("MRV");
		isCancelAvail = !MiscFunctions.isNoCancel("MRV"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("MRV"); 

	}
}
