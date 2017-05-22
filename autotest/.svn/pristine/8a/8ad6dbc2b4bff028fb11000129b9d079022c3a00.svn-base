package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class CancelPLResFromRA_CO extends WebTestCase
{
	/**
	 * Script Name   : <b>CancelPLResFromRA_CO</b>
	 * Generated     : <b>Apr 23, 2010 1:22:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/04/23
	 * @author vzhao
	 */
	private String contract = "CO"; //for creating schema name
  	private String plUrl, resID;

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 2, false, bd.siteID);
		
		// book a reservation from CO plw
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
		plUrl = TestProperty.getProperty(env + ".web.co.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd.state = "Colorado";
		bd.parkId = "50043";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"Sylvan Lake";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "CO";
		bd.loop = "CABN CABINS";
		bd.siteNo = "005";
		bd.siteID= "4458";
	}
}
