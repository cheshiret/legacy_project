package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class CancelRAResFromPL_KY extends WebTestCase
{
	/**
	 * Script Name   : <b>CancelRAResFromPL_KY</b>
	 * Generated     : <b>Apr 23, 2010 1:28:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/04/23
	 * @author vzhao
	 */
	private String plUrl, resID;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 3, false, bd.siteID);
	  	// book a reservation from RA for KY state
		web.invokeURL(url);
		web.signIn(email, pw);

		web.makeReservationToCart(bd);
		resID=web.checkOutCart(pay);
		web.signOut();
		
		// verify this order and cancel it from KY private site
		web.invokeURL(plUrl,false,false);
		web.signIn(email, pw);
		
		String status = "Confirmed";
		web.gotoResDetailFromAccount(resID,bd.contractCode,status);
		web.cancelReservation(pay);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		url = TestProperty.getProperty(env + ".web.ra.url");
		plUrl = TestProperty.getProperty(env + ".web.ky.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Kentucky";
		bd.parkId = "91913";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "3";
		bd.contractCode = "KY";

//		bd.siteNo = "024";  bd.siteId = "1420";
		bd.siteID = "1417";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode;
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema); //021
		
	}
}
