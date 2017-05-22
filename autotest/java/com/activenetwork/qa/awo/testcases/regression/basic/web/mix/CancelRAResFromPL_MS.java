package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class CancelRAResFromPL_MS extends WebTestCase
{
	/**
	 * Script Name   : <b>CancelRAResFromPL_MS</b>
	 * Generated     : <b>Apr 23, 2010 1:29:22 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/04/23
	 * @author vzhao
	 */
	private String plUrl, resID;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 2, false, bd.siteID);
	  	// book a reservation from RA for MS state
		web.invokeURL(url);
		web.signIn(email, pw);

		web.makeReservationToCart(bd);
		resID=web.checkOutCart(pay);
		web.signOut();
		
		// verify this order and cancel it from MS private site
		web.invokeURL(plUrl,false,false);
		web.signIn(email, pw);
		
		String status = "Confirmed";
		web.gotoResDetailFromAccount(resID,bd.contractCode,status);
//		web.cancelReservation(pay);
		web.verifyCancelLinkNotAvailable();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		plUrl = TestProperty.getProperty(env + ".web.ms.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "Mississippi";
		bd.park = "Trace";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "MS";

		bd.loop = "Deer Run";
		bd.siteNo = "006";
		bd.siteID="2388";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode;
	}
}
