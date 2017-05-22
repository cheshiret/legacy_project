package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * prerequisiste : please make sure the two tours that you are going to book will not break time conflict Management rule, 
 * for example if the time conflict rule is "60" minutes, try to book two different tour that the time gap larger than 60 minutes.
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakeDiffTourResInSameCamp extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_MakeDiffTourResInSameCamp</b>
	 * Generated     : <b>Nov 1, 2009 9:26:02 PM</b>
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
		this.secondTourData(); // prepare second tour's booking data
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
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.parkId = "77814";
		
		items[0] = "Tour: Eleanor(Val Kill) Indiv";
		items[1] = "Tour: Vanderbilt Mansion Indiv";
	}

	public void secondTourData() {
		bd.tourName = "Vanderbilt Mansion Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketTimeSeq=6;
	}
}
