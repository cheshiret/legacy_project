package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class AddSameTourIntoCart extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_AddSameTourIntoCart</b>
	 * Generated     : <b>Nov 1, 2009 9:29:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/01
	 * @author vzhao
	 */
	private String email, pw, url;
	private String[] items = new String[1];
	private TicketInfo bd;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

//		web.bookTourIntoCart(bd);
		web.bookTourIntoCart(bd); // book another tour with the same info
		web.verifyItemsInShopCartOrConfirmPg(1, items);// two items should combine to one

		web.checkOutTourCart(pay);
		web.verifyItemsInShopCartOrConfirmPg(1, items);// two items should combine to one
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.parkId = "77814";
		
		items[0] = "Tour: Eleanor(Val Kill) Indiv";
	}
}
