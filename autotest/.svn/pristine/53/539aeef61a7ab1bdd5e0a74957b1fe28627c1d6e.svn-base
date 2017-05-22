package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class VoidTicket extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VM_VoidTicket</b>
	 * Generated     : <b>Oct 27, 2009 4:19:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/27
	 * @author QA
	 */
	public void execute() {
		vm.loginVenueManager(login);
		vm.makeTicketOrderToCart(ticket);
		vm.selectCustomerFromOrderCart(cust);
		String ordNum = vm.processOrderCart(pay);

		vm.gotoTiketOrderDetails(ordNum);

		vm.voidTicketToCart(ticket.voidReason, ticket.note);
		vm.processOrderCart(pay);

		vm.gotoTiketOrderDetails(ordNum);
		vm.verifyStatus("Void");
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
		ticket.typeNums[1] = "1";
		ticket.typeNums[2] = "1";
		ticket.quantity = "1";

		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
	}
}
