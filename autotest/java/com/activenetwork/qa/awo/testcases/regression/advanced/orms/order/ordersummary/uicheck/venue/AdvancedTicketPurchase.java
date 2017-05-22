package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.ordersummary.uicheck.venue;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderSummaryInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AdvancedTicketPurchase extends VenueManagerTestCase {

	private OrmsOrderSummaryPage summaryPg = OrmsOrderSummaryPage.getInstance();
	private VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
	private OrderSummaryInfo ui = new OrderSummaryInfo();
	
	public void execute() {
		vm.loginVenueManager(login);
		
		vm.makeTicketOrderToCart(ticket);
		ticket.ordNum = vm.processOrderCartToSummaryPg(pay);
		this.verifyOrderSummaryDetails();

		vm.logoutVenueManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		
		pay.payType = "Cash";
		
		ticket.facility = "MAMMOTH CAVE NATIONAL PARK TOURS";
		ticket.tour = "Broadway Tour";
		ticket.category = "Individual";
		ticket.startDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(10),"EEE MMM d yyyy");
		ticket.quantity = "1";
		ticket.types = new String[] {"Adult"};
		ticket.typeNums = new String[] {"1"};
	}
	
	public void verifyOrderSummaryDetails() {
		ui = summaryPg.getTicketOrderSummaryData();
		
		String formatToday1 = DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM d yyyy");  
		
		if(ui.receiptDateTime.indexOf(formatToday1)==-1) throw new ErrorOnPageException("Receipt date '"+ui.receiptDateTime+"' is wrong!");
		
		if(!ui.salesLocation.equalsIgnoreCase(ticket.facility)) {
			throw new ErrorOnPageException("Sales location '"+ui.salesLocation+"' is wrong!");
		} else if(!ui.createdBy.equalsIgnoreCase(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName).replaceAll(" ", ""))) {
			throw new ErrorOnPageException("Create by '"+ui.createdBy+"' is wrong!");
		} else if(!ui.custName.equalsIgnoreCase("PUBLIC,GENERAL")) {
			throw new ErrorOnPageException("Customer name '"+ui.custName+"' is wrong!");
		} else if(!ui.custEmail.equalsIgnoreCase("")) {//email should be blank in venue manager
			throw new ErrorOnPageException("Customer email '"+ui.custEmail+"' is not blank!");
		} else if(!ui.transactions.get(0).equalsIgnoreCase("Advanced Ticket Purchase")) {
			throw new ErrorOnPageException("Transaction type 1 '"+ui.transactions.get(0)+"' is wrong!");
		} else if(!ui.transactions.get(1).equalsIgnoreCase("Make Payment")) {
			throw new ErrorOnPageException("Transaction type 2 '"+ui.transactions.get(1)+"' is wrong!");
		} else if(!ui.facilityName.equalsIgnoreCase(ticket.facility)) {
			throw new ErrorOnPageException("Facility name '"+ui.facilityName+"' is wrong!");
		} else if(!ui.prdCategory.equalsIgnoreCase(ticket.category)) {
			throw new ErrorOnPageException("Product category '"+ui.prdCategory+"' is wrong!");
		} else if(!ui.tourName.equalsIgnoreCase(ticket.tour)) {
			throw new ErrorOnPageException("Ticket name '"+ui.tourName+"' is wrong!");
		} else if(!ui.arrivalDate.equalsIgnoreCase(ticket.startDate)) {
			throw new ErrorOnPageException("Ticket arrival date '"+ui.arrivalDate+"' is wrong!");
		} else if(!ui.numOfTickets.equalsIgnoreCase(ticket.quantity)) {
			throw new ErrorOnPageException("Ticket quantity '"+ui.numOfTickets+"' is wrong!");
		}  else {
			logger.info("Order summary page displays correct.");
		}
		
		summaryPg.clickFinishOrder();
		vmHmPg.waitLoading();
	}
}
