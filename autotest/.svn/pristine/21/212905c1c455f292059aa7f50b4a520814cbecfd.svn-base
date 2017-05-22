package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class CancelPLResFromRA_NH extends WebTestCase
{
	/**
	 * Script Name   : <b>CancelPLResFromRA_NH</b>
	 * Generated     : <b>Apr 23, 2010 1:29:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/04/23
	 * @author vzhao
	 */
	private String plUrl, resID;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 3, false, bd.siteID);
	  	// book a reservation from NH
		web.invokeURL(plUrl);
		web.signIn(email, pw);

		web.makeReservationToCart(bd);
		resID=web.checkOutCart(pay);
		web.signOut();
		
		// verify this order and cancel it from RA
		web.invokeURL(url,false,false);
		web.signIn(email, pw);
		
		String status = "Confirmed";
		web.gotoResDetailFromAccount(resID,bd.contractCode,status);
		web.cancelReservation(pay);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		plUrl = TestProperty.getProperty(env + ".web.nh.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New Hampshire";
		bd.park = "Umbagog Lake State Park";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "3";
		bd.contractCode = "NH";
		bd.siteNo = "004";
		bd.siteID="1299";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode;
	}
}
