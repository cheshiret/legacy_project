package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 	See more detailed work steps from:
 * http://wiki.reserveamerica.com/display/qa/Campsite+Sanity+Test+Cases+Package
 * Test case name @RA_MakeReservation_ID
 */
public class MakeReservation_NormalSearch_8 extends RecgovTestCase {

	@Override
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.conType = "Federal";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "3";
		bd.contractCode = "NRSO";
		bd.siteNo = "031";
		bd.isRange = true;
		bd.parkId="71464";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"TWIN LAKES";
		
		bd.siteID = "78716";
		signInMiddle = true;//middle sign in
		bd.clickParkName = true;//force to click on park name link
		bd.clickSiteNum = true;//force to click on site number link
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
