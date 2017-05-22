package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class POS_MakeReservationWithDonationFixedValue_NY extends WebTestCase
{
	/**
	 * @since  2010/09/19
	 * @author vzhao
	 */
  	private String minAmnt;
  	private String[] items = new String[2];
	
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		minAmnt = web.checkPOSInOrderDetailsPg(od);//fill in POS info
		BigDecimal dMinAmnt = new BigDecimal(Integer.parseInt(minAmnt)).setScale(2);
		minAmnt = dMinAmnt.toString();//update the retrieved amount
		web.fillInOrderDetails(od,bd.contractCode);// go to cart
		web.getAndVerifyPOSAmount(items[1], minAmnt);//verify pos amount in shopping cart
		web.verifyItemsInShopCartOrConfirmPg(2, items);//verify in shopping cart
		
		web.checkOutShoppingCart(pay);
		web.getAndVerifyPOSAmount(items[1], minAmnt);//verify pos amount in confirmation
		web.verifyItemsInShopCartOrConfirmPg(2, items);//verify in confirmation page
		
		web.gotoResDetailsFromConfirm(1); // clean up
		web.cancelReservation(pay);
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
		bd.siteNo = "008";
		bd.siteID = "243853";

		od.isDonationPOS = true;
		od.isMinFixedAmount = true;
		
		items[0] = bd.park;
		items[1] = "Natural Heritage Trust Donation";//donation name may change in times
	}
}
