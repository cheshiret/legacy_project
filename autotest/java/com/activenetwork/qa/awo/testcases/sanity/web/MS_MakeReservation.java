package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.testcases.abstractcases.PLTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Site specific, date specific, middle sign in, park name link, site# link.
 * See detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_DE
 */
public class MS_MakeReservation extends PLTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ms.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Mississippi";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(30);
		bd.lengthOfStay = "3";
		bd.contractCode = "MS";
		bd.park = "TOMBIGBEE";
		bd.loop = "Standard";
		bd.siteID = "480";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"002";
		bd.isRange = true;
		
		
		signInMiddle = true;//middle sign in
		bd.clickParkName = true;//forced to click on park name link
		bd.clickSiteNum = true;//forced to click on site number link
		isCancelAvail = !MiscFunctions.isNoCancel("MS");;//cancel is not available via web
	}
}
