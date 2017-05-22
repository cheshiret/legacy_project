package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class POS_MakeReservationWithNoneDonation_NY extends WebTestCase
{
  	private String[] items = new String[1];
	
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		web.checkPOSInOrderDetailsPg(od);//fill in POS info
		web.fillInOrderDetails(od,bd.contractCode);// go to cart
		web.verifyItemsInShopCartOrConfirmPg(1, items);//No POS item in cart
	
		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema=TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ny.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "ALLEGANY STATE PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(4);
		bd.lengthOfStay = "2";
		bd.contractCode = "NY";

		bd.loop = "Anderson";
		bd.siteNo = "006";
		bd.siteID = "243851";

		od.isDonationPOS = true;
		items[0] = bd.park;
	}
}
