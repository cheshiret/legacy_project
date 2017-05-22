package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RA_ChangeDate_CA extends RATestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private String contract = "CA"; //for creating schema name

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate , Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		
		web.invokeURL(url);
		web.signIn(email, pw);
		
		web.makeReservationToCart(bd);
		web.changeDateFromCartToCart(bd.arrivalDate, bd.maxLoop);
		String resID = web.checkOutCart(pay);
		web.checkReservationExists(schema, resID);
		
		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.gotoResDetailFromAccount(resID, bd.contractCode, status);
		web.updateReservationDetails();
		web.cancelReservation(pay);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd.state = "California";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(7,Calendar.MONDAY);;
		bd.lengthOfStay = "2";
		bd.contractCode = "CA";
		bd.park = "HUMBOLDT REDWOODS SP";
//		bd.siteNo = "019BU";
		bd.siteID="5170";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);
	}
}
