package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import java.io.IOException;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.itextpdf.text.pdf.PdfReader;

/**
 * 
 * @Description: Verify the function 'Request Print at Home' works correctly when making Combo tour order in VM
 * @Preconditions: 1. have a combo tour - 'Combo Tour - PrintAtHome' with 2 child assigned tours('Kettle Falls Cruise (Kabetogama Lake)' & 'Kettle Falls Cruise (Rainy Lake)'), ticket allocation and fee schedule.
 * 							2. there are enough inventories
 * @SPEC: <<Print-Print at Home Tickets>>
 * @Task#: AUTO-1043
 * 
 * @author qchen
 * @Date  May 7, 2012
 */
public class PrintAtHome_Combo extends VenueManagerTestCase {
	private String path, fullFileName;
	
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		//make an Combo ticket order
		vm.makeTicketOrderToCart(ticket, true);
		vm.changeCustomer(cust);
		pay.amount = String.valueOf(OrmsOrderCartPage.getInstance().getAmountOwing());//full pay
		ticket.ordNum = vm.processOrderCart(pay);
		
		//goto ticket order detail to request print at home
		vm.gotoTicketOrderDetailsPage(ticket.ordNum);
		fullFileName = vm.printTicketOrderAtHome(path);
		int printedFileCount = this.getPrintedTicketQuantity(fullFileName);
		this.verifyPrintedFileCount(Integer.parseInt(ticket.typeNums[0]), printedFileCount);
		
		//clean up
		vm.voidTicketToCart(ticket.ordNum);
		vm.processOrderCart(pay);
		vm.logoutVenueManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/VOYAGEURS NATIONAL PARK TOURS";
		login.station = "Ash River VC";
		
		//combo tour
		ticket.facility = "VOYAGEURS NATIONAL PARK TOURS";
		//ticket.tour = "Combo Tour - PrintAtHome";
		ticket.tour = "PrintAtHome";
		ticket.comboTourName = ticket.tour;
		ticket.category = "Individual";
		ticket.startDate = DateFunctions.getDateAfterToday(3);
		ticket.types = new String[]{"Adult"};
		ticket.typeNums = new String[]{"3"};
		ticket.deliveryMethod = "Print at Home";
		
		//combo child tours info
		TicketInfo cTicket1 = new TicketInfo();
		cTicket1.tour = "Kettle Falls Cruise (Kabetogama Lake)";
		cTicket1.timeSlot = "8:00 AM";
		TicketInfo cTicket2 = new TicketInfo();
		cTicket2.tour = "Kettle Falls Cruise (Rainy Lake)";
		cTicket2.timeSlot = "6:00 PM";
		
		ticket.comboChildTours.add(cTicket1);
		ticket.comboChildTours.add(cTicket2);
		
		path = comparedPath;//path to store PDF file
	}
	
	private int getPrintedTicketQuantity(String fileName) {
		PdfReader reader = null;
		try {
			reader = new PdfReader(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int num =  reader.getNumberOfPages();
		
		return num;
	}
	
	private void verifyPrintedFileCount(int expected, int actual) {
		if(expected != actual) {
			throw new ErrorOnPageException("The printed ticket file count doesn't match the purchased.");
		} else logger.info("The print ticket file really exists and the count is correct.");
	}
}
