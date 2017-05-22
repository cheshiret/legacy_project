package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

public class TicketInfo extends TestData {
	public String ordNum = "";

	public String inventory = "";

	public String tour = "";
	
	public String tourCode = "";
	
	public boolean isInComboTour=false;

	public String park = "";

	public String category = "Individual";//Ticket Category:Individual and Organization

	public String startDate = "";

	public String quantity = "";
	
	public HashMap<String, String[]> passes=new HashMap<String, String[]>();

	// Want to purchase tickets from 'Add Ticket Order'function in process order
	// cart page. If ticketNum = 1,needn't 'Add Ticket Order'.
	public String ticketNum = "1";

	public String timeSlot = "";
	
	public String timeAmPm = "";

	public String type = "";

	public String typeNum = "";

	public String facilityID;
	
	public String facility = "";

	public String[] typeNums = null;

	public String[] types = null;

	// order information
	public String orderStatus = "";

	public String salesChannel = "";

	// payment information
	public String price = "";

	public String paid = "";

	public String unIssuedRefund = "";

	public String balance = "";

	public String confirmStatus = "";

	// for change ticket date
	public String newDate = "";

	public String newTimeSlot = "";

	// void reason and note
	public String voidReason = "";

	public String note = "";

	// in Ticket Availability page
	public boolean showAvailableOnly = false;

	public boolean autoPrintTicketTurnOn = false; // The feature
													// "Override Auto Print Tickets"
													// is turn on.

	public boolean autoPrintTicketTurnOff = false; // The feature
													// "Override Auto Print Tickets"
													// is turn off.

	public boolean unSelectAutoPrintTicket = false; // selects or unSelect "Auto-print Tickets"checkbox.
													// default selected.

	public String deliveryMethod = null;//include: 'Print at Home', 'Will Call' and 'Mail Out'
	
	public String comboTourName;
	
	public String orderItemStatus;
	
	public String ticketStatus;
	
	public List<TicketInfo> comboChildTours = new ArrayList<TicketInfo>();
	
	//Key - 'Per Inventory' TPA's name like 'Special Consideration', value - 'Per Inventory' TPA's value
	public Map<String, String> tourParticipantAttributesForPerInventory = new HashMap<String, String>();
	
	//Key - ticket type, value - 'Per Ticket' TPA's value
	public Map<String, List<Map<String, String>>> tourParticipantAttributesForPerTicket = new HashMap<String, List<Map<String, String>>>();
	
	//Key - Tour name, value - need to remove TPAs info
	public Map<String, List<Map<String, String>>> removedTourParticipantAttributesForPerTicket = new HashMap<String, List<Map<String, String>>>();
	
	public Map<String, List<Map<String, String>>> updatedTourParticipantAttributesForPerTicket = new HashMap<String, List<Map<String, String>>>();
	
	public Map<String, String> updateTourParticipantAttributesForPerInventory = new HashMap<String, String>();
	
	public String transferFromTourName;
	
	public List<lotteryChoice> lotteryChoices = new ArrayList<lotteryChoice>();
	
	public static class lotteryChoice
	{
		public String[] typeNums = null;
		public String[] types = null;
		public String tourDate;
		public String tourTime;
		public String deliveryMethod;
		
	}
	
	

	//Key - ticket type, value - 'Per Ticket' TPA
	public Map<String, List<Map<String, String>>> addedTourParticipantAttributesForPerTicket = new HashMap<String, List<Map<String,String>>>();
	
	//Key - need to cancel ticket type like 'Adult', 'Child', etc, value - the corresponding cancel ticket number
	public Map<String, Integer> cancelTypeAndNums = new HashMap<String, Integer>();
	
	public String increaseTypeNumber(String num, String increaseNum) {
		return Integer.toString(Integer.parseInt(num)
				+ Integer.parseInt(increaseNum));
	}
	
	public void increaseTypeNumber(String[] nums, String increaseNum) {
		for (int i = 0; i < nums.length; i++)
			increaseTypeNumber(nums[i], increaseNum);
	}
	
	public class PrintedTicketInfo {
		private String type;
		private String ordNum;
		private String startDateTime;
		private String tourName;
		private String comboTourName;
		private List<PrintedTicketInfo> comboChildTours;
		private String facility;
		private String custName;
		private String ticketNum;
		private String timeSlot;
		private boolean isReprint;
		
		public PrintedTicketInfo() {
			comboChildTours = new ArrayList<PrintedTicketInfo>();
		}
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getOrdNum() {
			return ordNum;
		}
		public void setOrdNum(String ordNum) {
			this.ordNum = ordNum;
		}
		public String getStartDateTime() {
			return startDateTime;
		}
		public void setStartDateTime(String startDateTime) {
			this.startDateTime = startDateTime;
		}
		public String getTourName() {
			return tourName;
		}
		public void setTourName(String tourName) {
			this.tourName = tourName;
		}
		public String getFacility() {
			return facility;
		}
		public void setFacility(String facility) {
			this.facility = facility;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getTicketNum() {
			return ticketNum;
		}
		public void setTicketNum(String ticketNum) {
			this.ticketNum = ticketNum;
		}
		public List<PrintedTicketInfo> getComboChildTours() {
			return comboChildTours;
		}
		public void setComboChildTours(List<PrintedTicketInfo> comboChildTours) {
			this.comboChildTours = comboChildTours;
		}
		public String getTimeSlot() {
			return timeSlot;
		}
		public void setTimeSlot(String timeSlot) {
			this.timeSlot = timeSlot;
		}
		public String getComboTourName() {
			return comboTourName;
		}
		public void setComboTourName(String comboTourName) {
			this.comboTourName = comboTourName;
		}

		public boolean isReprint() {
			return isReprint;
		}

		public void setIsReprint(boolean isReprint) {
			this.isReprint = isReprint;
		}
		
	}
	
	/**
	 * parse the Non-Combo printed ticket PDF file content to object, the content should be only 1 page of entire PDF
	 * @param content
	 * @return
	 */
	public static PrintedTicketInfo convertPDFContentToObjectForNonCombo(String content) {
		if(content.startsWith("\r\n")) {
			content = content.replaceFirst("\r\n", "");
		}
		PrintedTicketInfo pti = new TicketInfo().new PrintedTicketInfo();
		if(content.contains("REPRINT\r\n")) {
			content = content.replace("REPRINT\r\n", "");
			pti.setIsReprint(true);
		} else {
			pti.setIsReprint(false);
		}
		
		String info[] = content.split("\r\n");
		pti.setType(info[0].split("\\$")[0].trim());
		pti.setCustName(info[1]);
		pti.setOrdNum(info[2]);
		pti.setStartDateTime(info[3]);
		pti.setTourName(info[4]);
		pti.setTicketNum(info[5]);
		pti.setFacility(info[6]);
		
		return pti;
	}
	
	/**
	 * parse the Combo printed ticket PDF file content to object, the content should be only 1 page of entire PDF
	 * @param content
	 * @return
	 */
	public static PrintedTicketInfo convertPDFContentToObjectForCombo(String content) {
		if(content.startsWith("\r\n")) {
			content = content.replaceFirst("\r\n", "");
		}
		
		PrintedTicketInfo parentTi = new TicketInfo().new PrintedTicketInfo();
		if(content.contains("REPRINT\r\n")) {
			content = content.replace("REPRINT\r\n", "");
			parentTi.setIsReprint(true);
		} else {
			parentTi.setIsReprint(false);
		}
		
		String contents [] = content.split("Ticket\r\n");
		String childTourContent[];
		for(int i = 0; i < contents.length - 1; i ++) {
			PrintedTicketInfo cPti = new TicketInfo().new PrintedTicketInfo();
			childTourContent = contents[i].split("\r\n");
			cPti.setTimeSlot(childTourContent[0]);System.out.println("combo child tour time slot: " + cPti.getTimeSlot());
			cPti.setTicketNum(childTourContent[1]);
			cPti.setTourName(childTourContent[3]);System.out.println("combo child tour name: " + cPti.getTourName());
			parentTi.comboChildTours.add(cPti);
		}
		
		String info[] = contents[contents.length  - 1].split("\r\n");//the remaining content is tour info
		parentTi.setOrdNum(info[0]);System.out.println("order num: " + parentTi.getOrdNum());
		parentTi.setCustName(info[1]);System.out.println("customer name: " + parentTi.getCustName());
		parentTi.setStartDateTime(info[2]);System.out.println("start date time: " + parentTi.getStartDateTime());
		parentTi.setType(info[3].split("\\$")[0].trim());System.out.println("type: " + parentTi.getType());
		parentTi.setTourName(info[4]);System.out.println("tour: " + parentTi.getTourName());
		parentTi.setComboTourName(parentTi.tourName);
		parentTi.setFacility(info[5].split("Order")[0].trim());System.out.println("facility: " + parentTi.getFacility());
		
		return parentTi;
	}
}
