package com.activenetwork.qa.awo.datacollection.legacy.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.util.MiscFunctions;


public class TicketInfo extends UwpUnifiedSearch{
	
	public final static String TPA_LABEL_ADDRESS="Address";
	public final static String TPA_LABEL_ALERT="Alerts";
	public final static String TPA_LABEL_ANNIVERSARY="Anniversary";
	public final static String TPA_LABEL_ARRIVALTIME="ArrivalTime";
	public final static String TPA_LABEL_ARRIVALTIME_HH="ArrivalTime_Hh";
	public final static String TPA_LABEL_ARRIVALTIME_MM="ArrivalTime_Mm";
	public final static String TPA_LABEL_ARRIVALTIME_AP="ArrivalTime_Ap";
	public final static String TPA_LABEL_CURRENCY="Currency";
	public final static String TPA_LABEL_TO="To";
	public final static String TPA_LABEL_WISHPRICETO="WishPriceTo";
	public final static String TPA_LABEL_WISHPRICEFROM="Wish Price From";
	public final static String TPA_LABEL_WISHPRICE = "WishPrice";
	
	public final static String TPA_LABEL_GENDER="Gender";
	public final static String TPA_LABEL_LASTNAME="Last Name";
	public final static String TPA_LABEL_FIRSTNAME="First Name";
	public final static String TPA_LABEL_DATEOFBIRTH="Date of Birth";
	public final static String TPA_LABEL_VISITEDTIMES="Visited Times";
	public final static String TPA_LABEL_EVERVISIT="Ever Visit?";
	public final static String TPA_LABEL_FIRSTVISIT="First Visit?";
	public final static String TPA_LABEL_NAMES = "Names";
	public final static String TPA_LABEL_POWER = "Power";
//	public final static String TPA_LABEL_POWER="First Visit?";
	
	public final static String DELIVERY_METHOD_MAILLOUT_ID="1";
	public final static String DELIVERY_METHOD_PRINTATHOME_ID="2";
	public final static String DELIVERY_METHOD_WILLCALL_ID="3";
	
	public final static String DELIVERY_METHOD_MAILLOUT_NAME="Mail Out";
	public final static String DELIVERY_METHOD_PRINTATHOME_NAME="Print at Home";
	public final static String DELIVERY_METHOD_WILLCALL_NAME="Will Call";
	
	public final static String TICKET_CAT_All_ID="0";
	public final static String TICKET_CAT_Indv_ID="1";
	public final static String TICKET_CAT_ORG_ID="2";

	
	
	public String resId = "";

	public String park = "";

	public String tourName = "";

	public String category = "Individual";//Ticket Category:Individual and Organization

	public String tourCode = "";
	
	public String tourID = "";

	public String tourDate = "";

	public String tourTime = "";

	public String timeSlot = "";//it was used for choose tour time

	public String ticketType = "";

	public int validDays; //The "Valid Days" in Tour Details page in Inventory Manager
	
	public int getValidDays() {
		return validDays;
	}


	public void setValidDays(int validDays) {
		this.validDays = validDays;
	}

	public List<String> ticketTypeNums = new ArrayList<String>();
	public void setTicketNums(String... ticketNums){
		this.ticketTypeNums.clear();

		for(String ticketTypeNum: ticketNums){
			this.ticketTypeNums.add(ticketTypeNum);
		}
	}
	
	public List<String> ticketTypes = new ArrayList<String>();
	public void setTicketTypes(String... ticketTypes){
		this.ticketTypes.clear();

		for(String ticketType: ticketTypes){
			this.ticketTypes.add(ticketType);
		}
	}
	
	public String ticketNums = "";

	public String[] participantInfo = new String[]{"1", "QA", "TESTER"};

	public String participantAddress = "Automation Testing";

	public String status = "";

	public String adultNum = "";

	public String childNum = "";

	public String seniorNum = "";

	public String studentNum = "";

	public int ticketTimeSeq=1;

	public String deliveryMethod = "";

	public boolean isUnifiedSearch=false;

	public boolean isComboTour=false;

	public List<TicketInfo> comboChildTours = new ArrayList<TicketInfo>();

	//TPAs
	public Map<String, String> perInventoryTPAs = new HashMap<String, String>();
	public List<List<Map<String, String>>> perTicketTPAsList= new ArrayList<List<Map<String, String>>>();
	
	
	public TicketInfo copy(){
		TicketInfo ticket=new TicketInfo();
		ticket.contractCode = this.contractCode;
		ticket.parkId = this.parkId;
		ticket.park = this.park;
		ticket.tourName = this.tourName;
		ticket.tourDate = this.tourDate;
		ticket.ticketNums = this.ticketNums;
		ticket.ticketTypes.addAll( this.ticketTypes);
		ticket.ticketTypeNums.addAll( this.ticketTypeNums);
		ticket.deliveryMethod = this.deliveryMethod;
		
		for(int i=0;i<this.perTicketTPAsList.size();i++){
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			Map<String,String> map=null;
			for(int j=0;j<this.perTicketTPAsList.get(i).size();j++){
			    map=new HashMap<String,String>();
				map.putAll(this.perTicketTPAsList.get(i).get(j));
				list.add(map);
			}
			ticket.perTicketTPAsList.add(list);
		}
		
		
		ticket.perInventoryTPAs.putAll( this.perInventoryTPAs);
		
		return ticket;
	}
	
	public List<LotteryChoice> lotteryChoices = new ArrayList<LotteryChoice>();
	
	public static class LotteryChoice
	{
		public String[] typeNums = null;
		public String[] types = null;
		public String tourDate;
		public String tourTime;
		public String tourName;	
		
		public LotteryChoice() {};
		
		public LotteryChoice(String tourName, String tourDate, String tourTime, String[] types, String[] typeNums) {
			this.tourName = tourName;
			this.tourDate = tourDate;
			this.tourTime = tourTime;
			this.types = types;
			this.typeNums = typeNums;
		}
	}
	
	public boolean compareLotteryChoices(List<LotteryChoice> choices) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Num of Lottery Choices", this.lotteryChoices.size(), choices.size());
		if (result) {
			for (int i = 0; i < this.lotteryChoices.size(); i++) {
				LotteryChoice exp = this.lotteryChoices.get(i);
				LotteryChoice act = choices.get(i);
				boolean subRes = true;
				subRes &= MiscFunctions.compareString("Choice tour name", exp.tourName, act.tourName);
				subRes &= MiscFunctions.compareResult("Choice tour date", exp.tourDate, act.tourDate);
				subRes &= MiscFunctions.compareString("Choice tour time", exp.tourTime, act.tourTime);
				subRes &= MiscFunctions.compareArrayString("Choice tour types", exp.types, act.types);
				subRes &= MiscFunctions.compareArrayString("Choice tour type qtys", exp.typeNums, act.typeNums);
				result &= subRes;
			}
		}
		return result;
	}
}
