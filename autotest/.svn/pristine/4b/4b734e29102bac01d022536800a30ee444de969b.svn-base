package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:  add season to datapool
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Aug 9, 2011
 */
public class MakeReservation_ELS2 extends WebTestCase
{
	/**
	 * @since  2010/09/16
	 * @author vzhao
	 */
	private String plUrl;

	public void execute() {
	  	// Make a reservation from ELS2 for CA, NSS site
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(plUrl);
		web.signIn(email, pw);

		web.bookParkToSiteListPg(bd); // search park
		web.bookSiteToOrderDetailPg(bd); // search site
		web.fillInOrderDetails(od,bd.contractCode); // fill order detail and go to cart
		
		web.checkOutShoppingCart(pay);
		web.gotoResDetailsFromConfirm(1);
		web.cancelReservation(pay); // cancel the reservation
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		plUrl = TestProperty.getProperty(env + ".web.els2.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "California";
		bd.park = "San Francisco RV Resort";
		bd.parkId = "260403";
		bd.conType = "State";	
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.contractCode = "ELS2";

		bd.siteNo = "Premium Inland";
		bd.siteID = "1003";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode;
	}
}
