package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: blocked by DEFECT-30858 for now
 * Description   : Functional Test Script
 * @author vzhao
 */
public class CrossoverFromRaToRecGov extends RATestCase {
	/**
	 * Script Name   : <b>CrossoverFromRaToRecGov</b>
	 * Generated     : <b>Oct 13, 2009 2:54:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/13
	 * @author vzhao
	 */
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 3, false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);

		web.bookParkToSiteListPg(bd); // search park
		web.bookSiteToSiteDetails(bd);
		web.crossOverVerification(email, pw,false);//what ever isStay is, will not show confirm dialog
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.contractCode = "NRSO";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		bd.parkId = "71464";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"TWIN LAKES (SC)";
		bd.conType = "Federal";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "3";
		bd.siteNo = "002";
		bd.siteID="78687";
	}
}
