/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.tpa;

/**
 * 
 * @Description: 
 * @Preconditions: http://wiki.reserveamerica.com/display/dev/How+to+Add+new+Facility-Level+Tour+Participant+Attributes
 * 					1. A tour: Peter TPA tour; In table 'D_INV_CREATE_TOUR', ID=680;
 * 					2. A TPA config for Tour, In table 'D_INV_TPA', ID=173;
 * @SPEC: Ticket Order Details -- Participant attributes
 * @Task#: AUTO-1150(TC:036958)
 * 
 * @author pzhu
 * @Date  Aug 1, 2012
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;


public class ActiveTicketOrderDetails extends VenueManagerTestCase {
	private OrmsTicketOrderDetailsPage orderDetail = OrmsTicketOrderDetailsPage.getInstance();
	private String groupNameOfPerTicket;
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		//make an advanced ticket order for Non-Combo tour
		vm.makeTicketOrderToCart(ticket);
		ticket.ordNum = vm.processOrderCart(pay);
		
		//goto ticket order detail page to verify the tour participant attributes' value
		vm.gotoTicketOrderDetailsPage(ticket.ordNum);

		//check point 1: verify data in 'per ticket' or 'per inventory'.
		orderDetail.verifyTPAInfoOfPerTicket(ticket.tour, ticket.tourParticipantAttributesForPerTicket);
		orderDetail.verifyTPAInfoOfPerInventory(ticket.tour, ticket.tourParticipantAttributesForPerInventory);
		
		//check point 2: verify delivery type:
		this.verifyDeliveryType();

		//check point 3: verify sort of ticket type:
		this.verifySortOfTicketType();
		
		//check point 4: verify attribute group name of per Ticket
		this.verifyGroupNameOfPerTicket();
		
		
		//check point 5: verify attribute group name of per Inventory
		this.verifyGroupNameOfPerInventory();
		
		//clean up
		vm.voidTicketToCart();
		vm.processOrderCart(pay);
		vm.logoutVenueManager();
	}

	/**
	 * 
	 */
	private void verifyGroupNameOfPerInventory() {
		List<String> groups = orderDetail.getAttrGroupNameOfPerInventory();
		String[] rawString = (String[])groups.toArray(new String[groups.size()]);
		
		String[] sortString = (String[])groups.toArray(new String[groups.size()]);
		Arrays.sort(sortString );
		
		if(!Arrays.toString(rawString).equals(Arrays.toString(sortString)))
		{
			throw new ErrorOnPageException("Sort of Group name of Per Inventory is wrong ",Arrays.toString(sortString),Arrays.toString(rawString));
		}
		
		logger.info("Verify sort of Group Name of Per inventory successfully!!!");
		
	}

	/**
	 * 
	 */
	private void verifyGroupNameOfPerTicket() {
		List<String> groups = orderDetail.getAttrGroupNameOfPerTicket();
		if(!groups.get(0).equalsIgnoreCase(this.groupNameOfPerTicket))
		{
			throw new ErrorOnPageException("Group name of Per Ticket is wrong ",this.groupNameOfPerTicket,groups.get(0));
		}
		logger.info("Verify group name of Per Ticket successfully.");
	}

	/**
	 * 
	 */
	private void verifySortOfTicketType() {
		List<String> types = orderDetail.getTicketTypes();
		if(!(types.indexOf(ticket.types[0])<types.indexOf(ticket.types[1])))
		{
			throw new ErrorOnPageException("Sort of Ticket Type error ",ticket.types[0]+":"+ticket.types[1], types.get(0)+":"+types.get(1));
		}
		logger.info("Verify sort of ticket type successfully.");
		
	}
	
	

	/**
	 * 
	 */
	private void verifyDeliveryType() {
		
		List<String> methods = orderDetail.getDeliveryMethodInPerTicket();
		for(String method: methods)
		{
			if(!method.equalsIgnoreCase(ticket.deliveryMethod))
			{
				throw new ErrorOnPageException("Check Dellivery Type error ",ticket.deliveryMethod, method); 
			}
		}
		logger.info("Verify deliverty type successfully");
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/VOYAGEURS NATIONAL PARK TOURS";
		login.station = "Ash River VC";
		
		ticket.facility = "VOYAGEURS NATIONAL PARK TOURS";
		ticket.tour = "Peter TPA tour";
		ticket.startDate = DateFunctions.getDateAfterToday(5);
		ticket.category = "Individual";
		ticket.quantity = "2";
		ticket.types = new String[]{"Adult", "Child"};
		ticket.typeNums = new String[]{"1", "1"};
		ticket.deliveryMethod = "Will Call";
		
		//per ticket TPAs info
			//-----map
		Map<String, String> perTicketTPAsForAdult = new HashMap<String, String>();
		perTicketTPAsForAdult.put("First Name", cust.fName + "_Adult");
		perTicketTPAsForAdult.put("Last Name", cust.lName + "_Adult");
		perTicketTPAsForAdult.put("Date of Birth", "1/1/1980");
		
				
		Map<String, String> perTicketTPAsForChild = new HashMap<String, String>();
		perTicketTPAsForChild.put("First Name", cust.fName + "_Child");
		perTicketTPAsForChild.put("Last Name", cust.lName + "_Child");
		perTicketTPAsForChild.put("Date of Birth", "1/1/2005");
		
			//----list contains maps
		List<Map<String, String>> tpasListForAdult = new ArrayList<Map<String, String>>();
		tpasListForAdult.add(perTicketTPAsForAdult);
		
		List<Map<String, String>> tpasListForChild = new ArrayList<Map<String, String>>();
		tpasListForChild.add(perTicketTPAsForChild);
			//-----map contains 'Ticket Type' and list
		ticket.tourParticipantAttributesForPerTicket.put(ticket.types[0], tpasListForAdult);
		ticket.tourParticipantAttributesForPerTicket.put(ticket.types[1], tpasListForChild);
		
		
		//per inventory TPA
		ticket.tourParticipantAttributesForPerInventory.put("EverVisit", "Yes");
		ticket.tourParticipantAttributesForPerInventory.put("Tour_Power", "Yes");
		ticket.tourParticipantAttributesForPerInventory.put("Tour_SplConsid", "AUTO-1150");
		ticket.tourParticipantAttributesForPerInventory.put("WishPriceFrom", "9");
		ticket.tourParticipantAttributesForPerInventory.put("WishPriceTo", "99");
		
		this.groupNameOfPerTicket = "Customer.info";
		pay = new Payment("Cash");
		
	}
}
