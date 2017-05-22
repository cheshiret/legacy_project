package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MakeResWithOldCustomerInRec extends RecgovTestCase  {
	private Customer cust;

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
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.generateNewWebEmail("old", env); 
		pw = web.readQADB("OLD_PWD");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);

		bd.state = "South Carolina";
		bd.park = "TWIN LAKES (SC)";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
	  	bd.contractCode = "NRSO";
	  	bd.siteNo = "012";
	  	bd.lengthOfStay = "2";
	  	bd.siteID="78697";
	  	bd.parkId = "71464";
		
		bd.isUnifiedSearch=isUnifiedSearch();

	}
}
