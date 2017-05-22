/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.tpa;

/**
 * 
 * @Description: 
 * @Preconditions: http://wiki.reserveamerica.com/display/dev/How+to+Add+new+Facility-Level+Tour+Participant+Attributes
 * 					1. A tour: Peter TPA tour; In table 'D_INV_CREATE_TOUR', ID=680;
 * 					2. A TPA configuration for Tour, In table 'D_INV_TPA', ID=173;
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
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;


public class VoidedTicketOrderDetails extends VenueManagerTestCase {
	private OrmsTicketOrderDetailsPage orderDetail = OrmsTicketOrderDetailsPage.getInstance();
	Map<String, String> perTicketTPAsForAdult = new HashMap<String, String>();
	Map<String, String> perTicketTPAsForChild = new HashMap<String, String>();
	List<Map<String, String>> tpasListForAdult = new ArrayList<Map<String, String>>();
	List<Map<String, String>> tpasListForChild = new ArrayList<Map<String, String>>();
	private String groupNameOfPerTicket;
	private String isEverVisited;
	private String isTourPower;
	private String tourSplConsid;
	private String wishPriceFrom;
	private String wishPriceTo;
	
	private String groupNameOfisEverVisited;
	private String groupNameOfisTourPower;
	private String groupNameOftourSplConsid;
	private String groupNameOfwishPriceFrom;
	private String groupNameOfwishPriceTo;
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		//make an advanced ticket order for Non-Combo tour
		vm.makeTicketOrderToCart(ticket);
		ticket.ordNum = vm.processOrderCart(pay);
		
		vm.gotoTicketOrderDetailsPage(ticket.ordNum);
		//void ticket
		vm.voidTicketToCart();
		vm.processOrderCart(pay);
		
		//check TPA info for voided order.
		vm.gotoTicketOrderDetailsPage(ticket.ordNum);		
		
		//check point 1: verify attribute cannot be edit.
		this.checkAttributePerTicketUneditable();
		this.checkAttributePerInventoryUneditable();
		
		//check point 2: verify delivery type:
		this.verifyDeliveryType();

		//check point 3: verify sort of ticket type:
		this.verifySortOfTicketType();
		
		//check point 4: verify attribute group name of per Ticket
		this.verifyGroupNameOfPerTicket();
		
		
		//check point 5: verify attribute group name of per Inventory
		this.verifyGroupNameOfPerInventory();

		vm.logoutVenueManager();
	}

	/**
	 * 
	 */
	private void checkAttributePerInventoryUneditable() {
		boolean result = true;
		
		result &= orderDetail.isEditableOfAttrPerInventory(this.groupNameOfisEverVisited, "NoYes"); //dropdown list
		result &= orderDetail.isEditableOfAttrPerInventory(this.groupNameOfisTourPower, "NoYes");//dropdown list
		result &= orderDetail.isEditableOfAttrPerInventory(this.groupNameOftourSplConsid, this.tourSplConsid);//span
		result &= orderDetail.isEditableOfAttrPerInventory(this.groupNameOfwishPriceFrom, this.wishPriceFrom);//span
		result &= orderDetail.isEditableOfAttrPerInventory(this.groupNameOfwishPriceTo, this.wishPriceTo);//span
		
		if(!result)
		{
			throw new ErrorOnPageException("Attribute part of Per Inventory should all be un-editable, but check failed.");
		}
		logger.info("Checking un-ediable of Per Inventory successfully.");
		
	}

	/**
	 * 
	 */
	private void checkAttributePerTicketUneditable() {

		boolean result = true;
		result &= orderDetail.isEditableOfAttrPerTicket(this.groupNameOfPerTicket,perTicketTPAsForAdult.get("First Name"));//span
		result &= orderDetail.isEditableOfAttrPerTicket(this.groupNameOfPerTicket,perTicketTPAsForAdult.get("Last Name"));//span
		result &= orderDetail.isEditableOfAttrPerTicket(this.groupNameOfPerTicket,perTicketTPAsForAdult.get("Date of Birth"));//span
		result &= orderDetail.isEditableOfAttrPerTicket(this.groupNameOfPerTicket,perTicketTPAsForChild.get("First Name"));//span
		result &= orderDetail.isEditableOfAttrPerTicket(this.groupNameOfPerTicket,perTicketTPAsForChild.get("Last Name"));//span
		result &= orderDetail.isEditableOfAttrPerTicket(this.groupNameOfPerTicket,perTicketTPAsForChild.get("Date of Birth"));//span
		
		if(!result)
		{
			throw new ErrorOnPageException("Attribute part of Per ticket should all be un-editable, but check failed.");
		}
		logger.info("Checking un-ediable of Per ticket successfully.");
		

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
			throw new ErrorOnDataException("Sort of Group name of Per Inventory is wrong ",Arrays.toString(sortString),Arrays.toString(rawString));
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
			throw new ErrorOnDataException("Group name of Per Ticket is wrong ",this.groupNameOfPerTicket,groups.get(0));
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
			throw new ErrorOnDataException("Sort of Ticket Type error ",ticket.types[0]+":"+ticket.types[1], types.get(0)+":"+types.get(1));
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
				throw new ErrorOnDataException("Check Dellivery Type error ",ticket.deliveryMethod, method); 
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

		//Attribute info of Per inventory:
		//            Group Name									Attibute Value
		this.groupNameOfisEverVisited = "EverVisit"; 		this.isEverVisited = "Yes";
		this.groupNameOfisTourPower = "Tour_Power"; 		this.isTourPower = "Yes";
		this.groupNameOftourSplConsid = "Tour_SplConsid"; 	this.tourSplConsid = "AUTO-1150";
		this.groupNameOfwishPriceFrom = "WishPriceFrom"; 	this.wishPriceFrom = "9";
		this.groupNameOfwishPriceTo = "WishPriceTo"; 		this.wishPriceTo = "99";
		
		//per ticket TPAs info
			//-----map
		
		perTicketTPAsForAdult.put("First Name", cust.fName + "_Adult");
		perTicketTPAsForAdult.put("Last Name", cust.lName + "_Adult");
		perTicketTPAsForAdult.put("Date of Birth", "1/1/1980");
				
				
		
		perTicketTPAsForChild.put("First Name", cust.fName + "_Child");
		perTicketTPAsForChild.put("Last Name", cust.lName + "_Child");
		perTicketTPAsForChild.put("Date of Birth", "1/1/2005");
		
			//----list contains maps
		
		tpasListForAdult.add(perTicketTPAsForAdult);
			
		tpasListForChild.add(perTicketTPAsForChild);
			//-----map contains 'Ticket Type' and list
		ticket.tourParticipantAttributesForPerTicket.put(ticket.types[0], tpasListForAdult);
		ticket.tourParticipantAttributesForPerTicket.put(ticket.types[1], tpasListForChild);
		
		
		//per inventory TPA
		ticket.tourParticipantAttributesForPerInventory.put("EverVisit", this.isEverVisited);
		ticket.tourParticipantAttributesForPerInventory.put("Tour_Power", this.isTourPower);
		ticket.tourParticipantAttributesForPerInventory.put("Tour_SplConsid", this.tourSplConsid);
		ticket.tourParticipantAttributesForPerInventory.put("WishPriceFrom", this.wishPriceFrom);
		ticket.tourParticipantAttributesForPerInventory.put("WishPriceTo", this.wishPriceTo );
		
		this.groupNameOfPerTicket = "Customer.info";
		

		
		pay = new Payment("Cash");
		
	}
}
