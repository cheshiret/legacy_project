package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Prerequisite  : Make sure there is tour inventory setup(Venue Manager) for following two NNRS tour(if there is no inventory, you can run the AddTourInventory.java support script)
 * 				   -bd.tourPark = "Mammoth Cave National Park Tours";
				   -bd.tourName = "Broadway Tour";	
		           -bd.tourPark = "Roosevelt-Vanderbilt National Historic Sites";	
		           -bd.tourName = "Eleanor(Val Kill) Indiv";		
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakeTwoTourReservation extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_MakeTwoTourReservation</b>
	 * Generated     : <b>Nov 1, 2009 9:23:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/01
	 * @author vzhao
	 */
	private String email, pw, url;
	private String[] items = new String[2];
	private TicketInfo bd;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		web.bookTourIntoCart(bd);
		this.secondTourData(); // prepare second tour booking data
		web.bookTourIntoCart(bd);
		web.verifyItemsInShopCartOrConfirmPg(2, items);

		web.checkOutTourCart(pay);
		web.verifyItemsInShopCartOrConfirmPg(2, items);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Mammoth Cave National Park Tours";
		bd.parkId = "77817";
		bd.contractCode = "NRSO";
		bd.tourName = "Broadway Tour";
		bd.tourDate = DateFunctions.getDateAfterToday(5);
		bd.ticketType = "Adult";
		bd.ticketNums = "3";
		
		bd.isUnifiedSearch=isUnifiedSearch();

		items[0] = "Tour: Eleanor(Val Kill) Indiv";
		items[1] = "Tour: Broadway Tour";
	}

	public void secondTourData() {
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.parkId = "77814";
		bd.contractCode = "NRSO";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketNums = "3";
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
