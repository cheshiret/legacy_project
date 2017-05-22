package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourConfirmationPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakeSameTourResInDiffDates extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_MakeSameTourResInDiffDates</b>
	 * Generated     : <b>Nov 1, 2009 9:25:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/01
	 * @author vzhao
	 */
	private String email, pw, url;
	private TicketInfo bd;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		bd = web.bookTourIntoCart(bd);
		this.dateDataProvider(); // prepare second item's booking data
		web.bookTourIntoCart(bd);
		this.verifyShoppingCart();

		web.checkOutTourCart(pay);
		this.verifyConfirmationPg();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.parkId = "77814";
		bd.contractCode = "NRSO";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(2);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
	}

	public void dateDataProvider() {
		bd.park = "Roosevelt-Vanderbilt National Historic Sites";
		bd.tourName = "Eleanor(Val Kill) Indiv";
		bd.parkId = "77814";
		bd.contractCode = "NRSO";
		bd.tourDate = DateFunctions.getDateAfterGivenDay(bd.tourDate, 1);
		bd.ticketNums = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
	}

	public void verifyShoppingCart() {
		UwpTourShoppingCartPage shopCart = UwpTourShoppingCartPage.getInstance();

		logger.info("Verify tour info in shopping cart page.");
		
		if (shopCart.getNumOfItems() != 2)
			throw new ErrorOnPageException(
					"Items have not been separated for diff tour park.");
		if (shopCart.getTextInShoppingCart().indexOf("Tour: " + bd.tourName)==
	    	shopCart.getTextInShoppingCart().lastIndexOf("Tour: " + bd.tourName))// should display twice
			throw new ErrorOnPageException(
					"Tour name displays error in shopping cart.");
	}

	public void verifyConfirmationPg() {
		UwpTourConfirmationPage confirm = UwpTourConfirmationPage.getInstance();

		logger.info("Verify tour info in confirmation page.");
		if (confirm.getNumOfReservations() != 2)
			throw new ErrorOnPageException("Res number have not been separated for diff tour park.");
		if (confirm.getTextInConfirmPage().indexOf("Tour: " + bd.tourName)==
		  	confirm.getTextInConfirmPage().lastIndexOf("Tour: " + bd.tourName))// should display twice
			throw new ErrorOnPageException("Tour name displays error in confirm page.");
	}
}
