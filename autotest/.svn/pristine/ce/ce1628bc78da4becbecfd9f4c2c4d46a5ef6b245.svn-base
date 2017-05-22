package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.pagestrigger.rec;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CurrentReservationsPageTrigger  extends RecgovTestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		
		//selects the \ufffdCurrent Reservation\ufffd link from the \ufffdMy Reservation & Account\ufffd page
		web.signIn(email, pw); 
		web.gotoCurrentReservationsPage();
		
		//selects \ufffdCurrent Reservations\ufffd link from the \ufffdReservations\ufffd section in Account Overview Page
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection("Current Reservations");
		
		web.gotoMyReservationsAccount();
		//selects \ufffdView My Current Reservations\ufffd link from the Order Confirmation page
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd); 
		web.fillInOrderDetails(od, bd.contractCode);// go to cart
		String resID = web.checkOutShoppingCart(pay);
		web.gotoCurrentReservationsPage();//from order confirmation page
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
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "South Carolina";
		bd.park = "TWELVE MILE";
		bd.parkId = "71460";
		bd.conType = "Federal";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		bd.lengthOfStay = "1";
		bd.contractCode = "NRSO";
		
		bd.siteNo = "2";
		bd.siteID = "78635";
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
