package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.ordersummary.uicheck.venue;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderSummaryInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VoidTicketOrder extends VenueManagerTestCase {

	private OrmsOrderSummaryPage summaryPg = OrmsOrderSummaryPage.getInstance();
	private VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
	private OrderSummaryInfo ui = new OrderSummaryInfo();
	
	public void execute() {
		vm.loginVenueManager(login);
		
		vm.makeTicketOrderToCart(ticket);
		ticket.ordNum = vm.processOrderCart(pay);
		vm.gotoTiketOrderDetails(ticket.ordNum);
		
		vm.voidTicketToCart(ticket.voidReason, ticket.note);
		vm.processOrderCartToSummaryPg(pay);
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
		
		ticket.voidReason = "Other";
		ticket.note = "QA Automation Testing";
	}
	
	public void verifyOrderSummaryDetails() {
		ui = summaryPg.getTicketOrderSummaryData();
		
		String formatToday1 = DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM d yyyy");  
		
		if(ui.receiptDateTime.indexOf(formatToday1)==-1) throw new ErrorOnPageException("Receipt date '"+ui.receiptDateTime+"' is wrong!");
		
		if(!ui.salesLocation.equalsIgnoreCase(ticket.facility)) {
			throw new ErrorOnPageException("Sales location '"+ui.salesLocation+"' is wrong!");
		} else if(!ui.createdBy.equalsIgnoreCase(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName))) {
			throw new ErrorOnPageException("Create by '" +ui.createdBy+ "' is wrong!");
		} else if(!ui.custName.equalsIgnoreCase("PUBLIC,GENERAL")){
			throw new ErrorOnPageException("Customer name '" + ui.custName + " is wrong!");
		} else if(!ui.custEmail.equalsIgnoreCase("")) {//email should be blank in venue manager
			throw new ErrorOnPageException("Customer email '"+ui.custEmail+"' is wrong!");			
		} else if(!ui.facilityName.equalsIgnoreCase(ticket.facility)) {
			throw new ErrorOnPageException("Facility name '"+ui.facilityName+"' is wrong!");
		} else if(!ui.prdCategory.equalsIgnoreCase(ticket.category)) {
			throw new ErrorOnPageException("Product category '"+ui.prdCategory+"' is wrong!");
		} else if(!ui.tourNames.get(0).equalsIgnoreCase(ticket.tour)) {
			throw new ErrorOnPageException("Change to tour name '"+ui.tourNames.get(0)+"' is wrong!");
		} else {
			logger.info("Order summary page displays correct.");
		}
		
		/**
		 * The following check point is block by DEFECT-28229, If this defect closed,
		 * please update script, means that make blockByDefect= false
		 */	
		if(!MiscFunctions.blockByDefect()){
			if(!ui.transactions.get(0).equalsIgnoreCase("Void Ticket Order")) {
				throw new ErrorOnPageException("Transaction type 1 '"+ui.transactions.get(0)+"' is wrong!");
			} 
		   if(!ui.transactions.get(1).equalsIgnoreCase("Issue Refund")) {
				throw new ErrorOnPageException("Transaction type 2 '"+ui.transactions.get(1)+"' is wrong!");
			}
		}
		
		if(ui.arrivalDates.length<1) {
			throw new ErrorOnPageException("Page displays with error for arrival date.");
		} else {
			for(int i=0; i<ui.arrivalDates.length; i++) {
				if(!ui.arrivalDates[i].equalsIgnoreCase(ticket.startDate)) {
					throw new ErrorOnPageException("Ticket arrival date '"+ui.arrivalDates[i]+"' is wrong!");
				}
			}
		}
		
		summaryPg.clickFinishCall();
		vmHmPg.waitLoading();
	}
}
