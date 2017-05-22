package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.pagestrigger.pl;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CurrentReservationsPageTrigger extends WebTestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate,Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		
		//selects the \ufffdCurrent Reservation\ufffd link from the \ufffdMy Reservation & Account\ufffd page
		web.signIn(email, pw); 
		web.gotoCurrentReservationsPage();
		
		//selects \ufffdCurrent Reservations\ufffd link from the \ufffdReservations\ufffd section in Account Overview Page
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection("Current Reservations");
		
		//selects \ufffdView My Current Reservations\ufffd link from the Order Confirmation page
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd); 
		web.fillInOrderDetails(od, bd.contractCode);// go to cart
		String resID = web.checkOutShoppingCart(pay, true);//minimum paid
		web.gotoCurrentReservationsPage();//from order confirmation page
		web.accountPanelVerification(resID);
		
		web.gotoMyReservationsAccount();
		//selects \ufffdContinue to Current Reservations\ufffd link from the pay balance confirm page
		web.gotoResDetailFromAccount(resID, bd.contractCode, "Confirmed");
		web.payBalance(pay); // pay balance
		web.gotoCurrentReservationsPage();// from pay balance confirmation page
		web.accountPanelVerification(resID);
		
		web.gotoMyReservationsAccount();
		//selects \ufffdContinue to Current Reservations\ufffd link from the cancellation confirm page
		web.gotoResDetailFromAccount(resID, bd.contractCode, "Confirmed");
		web.cancelReservation(pay);
		web.gotoCurrentReservationsPage();// from cancellation confirmation page
		web.accountPanelVerification(resID);
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.sc.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema=TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		bd.state = "South Carolina";
		bd.park = "HICKORY KNOB";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3, Calendar.SATURDAY);
		bd.lengthOfStay = "7";
		bd.contractCode = "SC";

		bd.loop = "Cabins loop 2";
		bd.siteNo = "288";
		bd.siteID="1091";
	}
}
