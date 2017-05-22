package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookTourWithOldCustomer extends RecgovTestCase {
	private String email, pw, url, schema;
	private Customer cust;
	private TicketInfo bd, tour;
	private String contract = "NRRS"; //for creating schema name

	public void execute() {
		web.invokeURL(url);
		
		//create a new account
		web.createAccount(cust);
		web.signOut();
		web.updateWebSeq(cust.email);
		
		//create an old login name which not using email addr
		String oldLoginName = cust.email.split("@")[0];
		oldLoginName = web.updateToOldLoginName(cust.email, oldLoginName);
		
		//book a tour
		web.signIn(oldLoginName, pw);
		tour = web.bookTourIntoCart(bd);
		tour.resId = web.checkOutTourCart(pay);// will verify the success in this keyword
		tour.contractCode = bd.contractCode;
		 
		// change schema to verify order exists in db
		web.checkReservationExists(schema, tour.resId);
		
		tour.status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.gotoTourDetailsFromAccount(tour);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.generateNewWebEmail("old", env);
		pw = web.readQADB("OLD_PWD");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.parkId = "77814";
	}
}
