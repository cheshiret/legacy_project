package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MakeResWithOldCustomer_KY extends WebTestCase  {
	private Customer cust;
	private String contract = "KY"; //for creating schema name

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		//create a new account
		web.createAccount(cust);
		web.signOut();
		web.updateWebSeq(cust.email);
		
		//create an old login name which not using email addr
		String oldLoginName = cust.email.split("@")[0];
		oldLoginName = web.updateToOldLoginName(cust.email, oldLoginName);
		
		//make a reservation
		web.signIn(oldLoginName, pw);
		
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		web.fillInOrderDetails(od, bd.contractCode);// go to cart
		String resID = web.checkOutCart(pay);
		
		// change schema to verify order exists in db
		web.checkReservationExists(schema, resID);
		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.gotoResDetailFromAccount(resID, bd.contractCode, status);
		web.updateReservationDetails();
		web.cancelReservation(pay);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ky.url");
		email = web.generateNewWebEmail("old", env);
		pw = web.readQADB("OLD_PWD");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);

		bd.state = "Kentucky";
		bd.park = "PENNYRILE FOREST STATE RESORT PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		bd.lengthOfStay = "3";
		bd.contractCode = "KY";

		bd.siteNo = "026";
		bd.siteID = "1422";
	}
}
