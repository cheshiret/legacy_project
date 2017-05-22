package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CancelPLResFromRA_ELS2 extends WebTestCase
{
	/**
	 * @since  2010/09/16
	 * @author vzhao
	 */
  	private String plUrl, resID;
	
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, 2, false, bd.siteID);
	  	// book a reservation from els2 plw
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
		plUrl = TestProperty.getProperty(env + ".web.els2.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "California";
		bd.park = "San Francisco RV Resort";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "ELS2";
//		bd.siteNo = "Large Ocean";
//		bd.siteID= "1006";
		bd.siteNo = "Pull Thru";
		bd.siteID= "1004";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode;
	}
}
