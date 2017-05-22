package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class REC_BookTour extends RecgovTestCase {
	/**
	 * @since  2010/12/20
	 * @author VZHAO
	 */
	private String email, pw, url, schema;
	private TicketInfo bd, tour;
	private String contract = "NRRS";

	public void execute() {
		web.checkTourInventory(bd.park, bd.tourName, bd.tourDate, schema);
		web.invokeURL(url);

		web.signIn(email, pw);
		
		web.gotoHomePage();
		tour = web.bookTourIntoCart(bd);
		tour.resId = web.checkOutTourCart(pay);
		tour.contractCode = bd.contractCode;

		web.checkReservationExists(schema, tour.resId);

		tour.status = "Confirmed";

		web.gotoMyReservationsAccount();
		web.gotoTourDetailsFromAccount(tour);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.parkId="77814";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		url = TestProperty.getProperty(env + ".web.recgov.url");
	}
}
