package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrReceiptsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class PurchaseTicket extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VM_PurchaseTicket</b>
	 * Generated     : <b>Oct 19, 2009 2:23:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/19
	 * @author QA
	 */

	String prices = "";

	public void execute() {

		vm.loginVenueManager(login);

		vm.makeTicketOrderToCart(ticket);
		vm.selectCustomerFromOrderCart(cust);
		String ordNum = vm.processOrderCart(pay);

		//Verify ticket order make successful and receipt is correct
		vm.gotoTiketOrderDetails(ordNum);
		this.getTicketInfo();
		vm.verifyStatus("Active");
		vm.searchTicketReceipt(ordNum);
		this.verifyReceiptCorrect();

		vm.gotoTiketOrderDetails(ordNum);
		vm.voidTicketToCart(ticket.voidReason, ticket.note);
		vm.processOrderCart(pay);

		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/YOUNG LAKE (SOUTH) CABIN";

		ticket.tour = "QA-REGRE-TEST";
		ticket.startDate = DateFunctions.getDateAfterToday(1);
		ticket.types = new String[3];
		ticket.typeNums = new String[3];
		ticket.types[0] = "Adult";
		ticket.types[1] = "Child";
		ticket.types[2] = "Youth";
		ticket.typeNums[0] = "2";
		ticket.typeNums[1] = "2";
		ticket.typeNums[2] = "1";
		ticket.quantity = "1";

		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
	}

	/*
	 * Get the price from details page
	 * the price is for verify in receipt page
	 * **/
	public void getTicketInfo() {
	  OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage.getInstance();
		prices = vmTicketDetailPg.getTicketPrice();
	}

	/*
	 * Verify the result in the receipt page
	 * 
	 * **/
	public void verifyReceiptCorrect() {
	  VnuMgrReceiptsSearchPage vmReceSearPg = VnuMgrReceiptsSearchPage.getInstance();
		if (!prices.equalsIgnoreCase(vmReceSearPg.getReceipt())) {
			throw new ItemNotFoundException("Receipts are not correct.");
		}
	}
}
