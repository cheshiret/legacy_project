package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakeOneTourReservation extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_MakeOneTourReservation</b>
	 * Generated     : <b>Oct 29, 2009 3:14:05 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/29
	 * @author vzhao
	 */
	private String email, pw, url;
	private TicketInfo bd;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		web.bookTourIntoCart(bd);
		web.checkOutTourCart(pay); // will verify the success in this keyword

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(6);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.parkId = "77814";
	}
}
