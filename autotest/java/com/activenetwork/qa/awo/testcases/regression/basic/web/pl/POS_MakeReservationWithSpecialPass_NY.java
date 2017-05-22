package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 1: make sure you run SetupPos support script before run this test case.
 * 2: make sure you add the POS Fee for this POS in Finance Manager.(Note POS fee must set to the same level as you added in SetupPos support script, 
 * right now support script set to Agency Level, so POS Fee schedule must set to Agency Level as well)
 * 3: in order to check whether SetupPos script running successfully or not, when you get to Agency POS fee schedule create details page, you should see "Summer Pass"
 * in the Product drop down list.
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Aug 15, 2011
 */
public class POS_MakeReservationWithSpecialPass_NY extends WebTestCase {
	private String posFee;
	private String[] items = new String[2];

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site

		web.checkPOSInOrderDetailsPg(od);// fill in Pass POS info
		web.fillInOrderDetails(od, bd.contractCode);// go to cart
		web.getAndVerifyPOSAmount(items[1], posFee);// verify pos amount in shopping cart
		web.verifyItemsInShopCartOrConfirmPg(2, items);// verify in shopping cart

		web.checkOutShoppingCart(pay);
		web.getAndVerifyPOSAmount(items[1], posFee);// verify pos amount in confirmation
		web.verifyItemsInShopCartOrConfirmPg(2, items);// verify in confirmation page

		web.gotoResDetailsFromConfirm(1); // clean up
		web.cancelReservation(pay);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") +"NY";
		url = TestProperty.getProperty(env + ".web.ny.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "MONGAUP POND";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(4);
		bd.lengthOfStay = "2";
		bd.contractCode = "NY";

		bd.siteNo = "007";
		bd.siteID = "245138";
		od.isPassPOS = true;
		od.isDonationPOS = true;//check None if Donation also setup for this facility

		items[0] = bd.park;
		items[1] = "Summer Pass DEC";// pass name may change in times
		posFee = "14.00";
	}
}
