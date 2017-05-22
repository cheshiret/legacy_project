package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddTicketsNum extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VM_AddTickets</b>
	 * Generated     : <b>Oct 20, 2009 5:58:17 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/20
	 * @author QA
	 */

	int count = 0;

	public void execute() {
		vm.loginVenueManager(login);
		vm.makeTicketOrderToCart(ticket);
		//pay.payType = "cash";
		vm.selectCustomerFromOrderCart(cust);
		String ordNum = vm.processOrderCart(pay);

		vm.gotoTiketOrderDetails(ordNum);
		ticket.increaseTypeNumber(ticket.typeNums, "2");
		vm.addMoreTicketToCart(ticket.types, ticket.typeNums);
		vm.processOrderCart(pay);
		vm.gotoTiketOrderDetails(ordNum);
		for (int i = 0; i < count; i++) {
			this.verifyAddTypeNumSuccessful(ticket.types[i],ticket.typeNums[i], ticket.typeNums[i]);
		}

		vm.voidTicketToCart(ticket.voidReason, ticket.note);
		vm.processOrderCart(pay);

		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/YOUNG LAKE (SOUTH) CABIN";

		ticket.tour = "QA-REGRE-TEST";
		ticket.startDate = DateFunctions.getDateAfterToday(8);
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
		count = ticket.types.length;
	}

	public String getAddedNum(String preNum, String addNum) {
		int preNumer = Integer.parseInt(preNum);
		int addNumer = Integer.parseInt(addNum);
		int addedNumber = preNumer + addNumer;
		String addedRes = String.valueOf(addedNumber);
		return addedRes;
	}

	public void verifyAddTypeNumSuccessful(String type, String typeNum,String addTypeNum) {
		OrmsTicketOrderDetailsPage vmTicketDetailPg = OrmsTicketOrderDetailsPage.getInstance();
		String typeCount = vmTicketDetailPg.getTypeNum(type);
		String addedCount = getAddedNum(typeNum, addTypeNum);
		if (!typeCount.equalsIgnoreCase(addedCount)) {
			throw new ItemNotFoundException(type + " added number is incoorect");
		}
	}
}
