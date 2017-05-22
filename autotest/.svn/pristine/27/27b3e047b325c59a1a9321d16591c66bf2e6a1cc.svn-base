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
 * Test case name @RA_MakeReservation_MT
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-567
 * 
 * @author bzhang
 * @Date  May 20, 2011
 */
public class RA_MakeReservation_MT extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MT";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Montana";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "MT";
		bd.parkId = "630511";
		bd.park =  DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Cooney";
		
		bd.siteID = "767";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"C005";
		
		newBd.siteID = "768";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//C006
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);//DEVI_RVSiteLoop31-59_RV039
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
		
		bd.clickParkName = true;//forced to click on park name link
		
		isSiteTransfer = MiscFunctions.isSiteTransfer("MT");
		isCancelAvail = !MiscFunctions.isNoCancel("MT"); //web.nocancel.contracts:/VA/LA/WI/EB/OR/SNOH/MS/MRV/
		bd.isUpdateAble = !MiscFunctions.isNoChange("MT"); 

	}
}
