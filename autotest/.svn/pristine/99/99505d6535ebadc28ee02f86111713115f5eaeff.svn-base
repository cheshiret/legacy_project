package com.activenetwork.qa.awo.testcases.sanity.orms;

import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class VM_AddTicketTransferCancel extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VMAddTransferCancel</b>
	 * Generated     : <b>Dec 20, 2010 9:20:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/12/20
	 * @author QA
	 */

	public void execute() {
		//ticket.ordNum="4-7064";
		//login
		vm.loginVenueManager(login);

		//make an advanced ticket order
		vm.makeTicketOrderToCart(ticket);
		ticket.ordNum = vm.processOrderCart(pay);

		//add 2 more tickets
		vm.gotoTiketOrderDetails(ticket.ordNum);
		ticket.increaseTypeNumber(ticket.typeNums, "2");
		vm.addMoreTicketToCart(ticket.types, ticket.typeNums);
		vm.processOrderCart(pay);

		//invalidate tickets
		vm.gotoTiketOrderDetails(ticket.ordNum);
		vm.invalidTickets();

		//transfer tickets
		ticket.quantity = "2";
		ticket.tour = "River Styx";
		ticket.startDate = DataBaseFunctions.getLatestAvailableTourDate(schema, ticket.park, ticket.tour, DateFunctions.getDateAfterGivenDay(ticket.startDate, 2),Integer.parseInt(ticket.quantity));
		vm.transferTicketToCart(ticket);
		vm.processOrderCart(pay);

		//void ticket order
		vm.gotoTiketOrderDetails(ticket.ordNum);
		vm.voidTicketToCart(ticket.voidReason, ticket.note);
		vm.processOrderCart(pay);

		//logout
		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"nrrs";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		pay.payType = "Cash";

		ticket.tour = "Broadway Tour";
		ticket.park="MAMMOTH CAVE NATIONAL PARK TOURS";
		ticket.types = new String[1];
		ticket.typeNums = new String[1];
		ticket.types[0] = "Adult";
		ticket.typeNums[0] = "2";
		ticket.quantity = "2";
		ticket.startDate = DataBaseFunctions.getLatestAvailableTourDate(schema, ticket.park, ticket.tour, DateFunctions.getToday(),Integer.parseInt(ticket.quantity));
//		ticket.newDate = DateFunctions.getDateAfterToday(28);

		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
	}
}
