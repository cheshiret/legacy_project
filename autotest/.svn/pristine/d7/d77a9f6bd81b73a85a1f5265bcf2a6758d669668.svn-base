package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.testcases.abstractcases.PLTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MS_ChangeDate extends PLTestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private String contract = "MS"; //for creating schema name

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate,Integer.parseInt(bd.lengthOfStay), false,bd.siteID);
		
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
		//web.cancelReservation(pay);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ms.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd.state = "Mississippi";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(25);
		bd.lengthOfStay = "3";
		bd.contractCode = "MS";
		bd.park = "TRACE";
		bd.loop = "Deer Run";
		bd.siteID="2413";
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//"009";
		
	}
}
