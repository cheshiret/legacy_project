package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**@Prerequisite: 
 * 1: make sure you run SetupPos support script before run this test case.
 * 2: make sure you add the POS Fee for this POS in Finance Manager.(Note POS fee must set to the same level as you added in SetupPos support script, 
 * right now support script set to Agency Level, so POS Fee schedule must set to Agency Level as well)
 * 3: in order to check whether SetupPos script running successfully or not, when you get to Agency POS fee schedule create details page, you should see "Summer Pass"
 * in the Product drop down list.
 * 4. Agency id: 2, name: DEC
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author vzhao
 * @updatedBy bzhang
 * @Date  Aug 15, 2011
 */
public class POS_MakeReservationWithDonationCustValue_NY extends WebTestCase
{
  	private String[] items = new String[2];
	
	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.bookSiteToOrderDetailPg(bd); // search site
		
		web.checkPOSInOrderDetailsPg(od);//fill in POS info
		web.fillInOrderDetails(od,bd.contractCode);// go to cart
		web.getAndVerifyPOSAmount(items[1], od.otherAmount);//verify pos amount in shopping cart
		web.verifyItemsInShopCartOrConfirmPg(2, items);//verify in shopping cart
		
		web.checkOutShoppingCart(pay);
		web.getAndVerifyPOSAmount(items[1], od.otherAmount);//verify pos amount in confirmation
		web.verifyItemsInShopCartOrConfirmPg(2, items);//verify in confirmation page
		
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
		bd.park = "ALLEGANY STATE PARK";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(4, Calendar.SUNDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "NY";

		bd.loop = "Anderson";
		bd.siteNo = "007";
		bd.siteID = "243852";

		od.isDonationPOS = true;
		od.otherAmount = "40.00";
		
		items[0] = bd.park;
		items[1] = "Natural Heritage Trust Donation";//donation name may change in times
	}
}
