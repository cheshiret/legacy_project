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
public class VM_ChangeDateVoid extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VMChangeDateVoid</b>
	 * Generated     : <b>Dec 20, 2010 9:40:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/12/20
	 * @author QA
	 */

	public void execute() {
		//		ticket.ordNum="4-7123";
		//login
		vm.loginVenueManager(login);

		//make an advanced ticket order
		vm.makeTicketOrderToCart(ticket);
		String ordNum = vm.processOrderCart(pay);

		//invalidate tickets
		vm.gotoTiketOrderDetails(ordNum);
		vm.invalidTickets();

		//change ticket time
		vm.changeTimeToCart(ticket.newDate, ticket.newTimeSlot,	ticket.quantity);
		vm.processOrderCart(pay);

		//void ticket order
		vm.gotoTiketOrderDetails(ordNum);
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

		ticket.tour = "River Styx";
		ticket.types = new String[1];
		ticket.typeNums = new String[1];
		ticket.types[0] = "Adult";
		ticket.typeNums[0] = "1";
		ticket.quantity = "1";
		ticket.startDate = DataBaseFunctions.getLatestAvailableTourDate(schema, "MAMMOTH CAVE NATIONAL PARK TOURS", ticket.tour, DateFunctions.getToday(),Integer.parseInt(ticket.quantity));
		
		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
		
	}
}
