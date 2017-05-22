package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.ordersummary.uicheck.venue;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderSummaryInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class CancelTicketOrder extends VenueManagerTestCase {

	private OrmsOrderSummaryPage summaryPg = OrmsOrderSummaryPage.getInstance();
	private VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
	private OrderSummaryInfo ui = new OrderSummaryInfo();
	
	public void execute() {
		vm.loginVenueManager(login);
		
		vm.makeTicketOrderToCart(ticket);
		ticket.ordNum = vm.processOrderCart(pay);
		vm.gotoTiketOrderDetails(ticket.ordNum);
		vm.cancelTicketToCart(true, ticket.ordNum);
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
		pay.ticketInfo.autoPrintTicketTurnOn = true;
		pay.ticketInfo.unSelectAutoPrintTicket = true;
		
	}
	
	public void verifyOrderSummaryDetails() {
		ui = summaryPg.getTicketOrderSummaryData();
		String formatToday1 = DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM d yyyy");  
		String errorLog = null;
		try{
			if(ui.receiptDateTime.indexOf(formatToday1)==-1) throw new ErrorOnPageException("Receipt date '"+ui.receiptDateTime+"' is wrong!");
			
			if(!ui.salesLocation.equalsIgnoreCase(ticket.facility)) {
				errorLog += "Sales location '"+ui.salesLocation+"' is wrong! \n";
			} 
			
			if(!ui.createdBy.equalsIgnoreCase(com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName))) {
				errorLog += "Create by '"+ui.createdBy+"' is wrong! \n";
			} 
			
			if(!ui.custName.equalsIgnoreCase("PUBLIC,GENERAL")) {
				errorLog += "Customer name '"+ui.custName+"' is wrong! \n";
			} 
			if(!ui.custEmail.equalsIgnoreCase("")) {//email should be blank in venue manager
				errorLog += "Customer email '"+ui.custEmail+"' is not blank! \n";
			} 
			if(!ui.transactions.get(0).equalsIgnoreCase("Cancel Tickets - Customer Cancellation")) {
				errorLog += "Transaction type 1 '"+ui.transactions.get(0)+"' is wrong! \n";
			} 
			if(!ui.facilityName.equalsIgnoreCase(ticket.facility)) {
				errorLog += "Facility name '"+ui.facilityName+"' is wrong! \n";
			} 
			if(!ui.prdCategory.equalsIgnoreCase(ticket.category)) {
				errorLog += "Product category '"+ui.prdCategory+"' is wrong! \n";
			} 
			if(!ui.tourNames.get(0).equalsIgnoreCase(ticket.tour)) { 
				errorLog += "Change to tour name '"+ui.tourNames.get(0)+"' is wrong! \n";
			} 
			if(!ui.tourNames.get(1).equalsIgnoreCase(ticket.tour)) {
				errorLog += "Change from tour name '"+ui.tourNames.get(1)+"' is wrong! \n";
			} 
			if(!ui.arrivalDate.equalsIgnoreCase(ticket.startDate)) {
				errorLog += "Ticket arrival date '"+ui.arrivalDate+"' is wrong! \n";
			} 
			
			if(ui.arrivalDates.length != 1) {
				errorLog += "Page displays with error for arrival date. \n";
			}
			
		} catch (Throwable e){
			
		} finally{
			if (null != errorLog && errorLog.length() >0){
				throw new ErrorOnDataException(errorLog);
			}
		}
		
		
		summaryPg.clickFinishCall();
		vmHmPg.waitLoading();
	}
}
