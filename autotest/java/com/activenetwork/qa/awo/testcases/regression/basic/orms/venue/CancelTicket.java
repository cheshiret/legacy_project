package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartEndPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class CancelTicket extends VenueManagerTestCase {
	/**
	 * Script Name   : <b>VM_CancelTicket</b>
	 * Generated     : <b>Oct 26, 2009 9:43:19 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/10/26
	 * @author QA
	 */
	public void execute() {
		vm.loginVenueManager(login);
		vm.makeTicketOrderToCart(ticket);
		//pay.payType = "cash";
		vm.selectCustomerFromOrderCart(cust);
		String ordNum = vm.processOrderCart(pay,true,true);

		vm.gotoTiketOrderDetails(ordNum);

		//vm.invalidTickets();
		vm.cancelTicketToCart(true," ");
		this.processOrderCart(pay);
		//vm.processOrderCart(pay);
		
		vm.gotoTiketOrderDetails(ordNum);
		vm.invalidTickets();
		vm.cancelTicketToCart(true," ");
		vm.processOrderCart(pay);
		
		vm.gotoTiketOrderDetails(ordNum);
		vm.verifyStatus("Inactive");
		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/YOUNG LAKE (SOUTH) CABIN";

		ticket.tour = "QA-REGRE-TEST";
		ticket.startDate = DateFunctions.getDateAfterToday(5);
		ticket.types = new String[3];
		ticket.typeNums = new String[3];
		ticket.types[0] = "Adult";
		ticket.types[1] = "Child";
		ticket.types[2] = "Youth";
		ticket.typeNums[0] = "2";
		ticket.typeNums[1] = "1";
		ticket.typeNums[2] = "1";
		ticket.quantity = "1";
	}
	
	public void processOrderCart(Payment pay){
	    OrmsOrderCartPage vmOrdCartPg = OrmsOrderCartPage.getInstance();
	    OrmsOrderCartEndPage vmOrdCartCancelPg=OrmsOrderCartEndPage.getInstance();
	    VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
	    
	    vmOrdCartPg.setupPayment(pay);
		vmOrdCartPg.clickProcessOrder();
		
		vmOrdCartPg.waitLoading();
		if(!vmOrdCartPg.getWarningMessage().matches("Please invalidate the following printed tickets.*")){
		   throw new ItemNotFoundException("Vaild waring message display incorrect");
		}
		vmOrdCartPg.clickCancelCart();
		
		vmOrdCartCancelPg.waitLoading();
		vmOrdCartCancelPg.clickCancelCart();
		vmHmPg.waitLoading();
		
	}
}
