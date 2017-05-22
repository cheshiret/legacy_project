package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.payOutStandingBalance;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsInvoiceSearchPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * 1: make a ticket oder from venue manager.
 * 2: void the payment from venue manager.
 * 3: pay outstanding balance from the invoice search/list page.
 * 4: clean the environment from venue manager.
 * @Preconditions:
 * 1: the selected contract allow to make ticket order(NRRS).
 * 2: make sure assinged "ViewInvoice" feature to role name "NRRS - Venue Supervisor"(which is the role we assigned to VM login account)
 * @SPEC:
 * @Task#: AUTO-577
 * 
 * @author bzhang
 * @Date  June 3, 2011
 */
public class TicketOrder extends VenueManagerTestCase {
	
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		vm.makeTicketOrderToCart(ticket);
		vm.selectCustomerFromOrderCart(cust);
		inv.orderNum = vm.processOrderCart(pay);
		
		//void payment in Venue Manager
		vm.voidPaymentByGivenOrderNum(inv.orderNum, pay.paymentVoidReason);

		//pay outstanding balance from invoice search/list page
		vm.searchInvoiceBasedOnInvoiceInfo(inv);
		this.makePaymentOnInvoiceSchListPg();
		vm.processOrderToOrderSummary(pay);		
		vm.verifyNewAmountOwiningOnOrderSummaryPg("0.00");
		vm.finishOrder();	
		
		//clean environment
		this.cancelTickets(inv.orderNum); 	
		vm.logoutVenueManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		
		ticket.facility = "MAMMOTH CAVE NATIONAL PARK TOURS";
		ticket.tour = "New Entrance Tour";
		ticket.startDate = DateFunctions.getDateAfterToday(3);
		ticket.types = new String[2];
		ticket.typeNums = new String[2];
		ticket.types[0] = "Adult";
		ticket.types[1] = "Youth";
		ticket.typeNums[0] = "1";
		ticket.typeNums[1] = "1";
		ticket.quantity = "1";

		ticket.voidReason = "Other";
		ticket.note = "QA Automation";
				
		pay.payType = "cash";
		pay.ticketInfo.autoPrintTicketTurnOn = true;
		pay.ticketInfo.unSelectAutoPrintTicket = true;
		pay.paymentVoidReason = "QA automationfor payment void";
	}
	
	public void cancelTickets(String ordNum){
		VnuMgrHomePage VnuHome = VnuMgrHomePage.getInstance();
		vm.cancelTicketOrder(VnuHome, ordNum);
		vm.processOrderCart(pay);
	}
	
	/**
	 * make payment for all outstanding balance order on invoice search/list page.
	 * the work flow start from invoice search/list page, ends at order cart page.
	 */
	public void makePaymentOnInvoiceSchListPg(){
		OrmsInvoiceSearchPage oInvSchPg = OrmsInvoiceSearchPage.getInstance();
		OrmsOrderCartPage omCartPg = OrmsOrderCartPage.getInstance();
		oInvSchPg.selectFirstCheckBox();
		oInvSchPg.clickMakePayment();
		omCartPg.waitLoading();
	}

}
