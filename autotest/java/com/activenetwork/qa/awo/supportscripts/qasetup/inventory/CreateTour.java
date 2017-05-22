package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketType;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.CreateTourFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * This script goes to create a new tour facility, add fee tour schedule and add tour
 * inventories.
 * @author QA
 */
public class CreateTour extends SetupCase
{
	/**
	 * Script Name   : <b>CreateTour</b>
	 * Generated     : <b>Mar 11, 2010 1:09:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/11
	 * @author vzhao
	 */
	private LoginInfo login = new LoginInfo();
	private TourInfo tour;
	private String facilityName;
	private CreateTourFunction createTourFunc = new CreateTourFunction();
    
	public void wrapParameters( Object[] param ) { 
		dataTableName = "d_inv_create_tour";
	  	
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
	}
	
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = facilityName;
		args[2] = tour;
		
		createTourFunc.execute(args);
	}
	
	public void readDataFromDatabase() {
	    login.contract = datasFromDB.get("Contract");
	    login.location = datasFromDB.get("Location");
	    facilityName = datasFromDB.get("Facility");
	    
	    tour = new TourInfo();
	    
	    //this variable shows whether the tour is combo tout or not
	    tour.isComboTour = Boolean.parseBoolean(datasFromDB.get("IsComboTour"));
	    
	    tour.tourCode = datasFromDB.get("TourCode");
	    tour.tourName = datasFromDB.get("TourName");
	    tour.ticketTourName = datasFromDB.get("TicketTourName");
	    tour.tourType = datasFromDB.get("TourType");
	    tour.tourFeeClass = datasFromDB.get("TourFeeClass");
	    tour.description = datasFromDB.get("Description");
	    tour.ticketCategory = datasFromDB.get("TicketCategory");
	    tour.rateType = datasFromDB.get("RateType");
	    tour.count = datasFromDB.get("Count");
	    tour.isSoldIndividual = Boolean.parseBoolean(datasFromDB.get("SoldIndividual"));
	    tour.timeEntry = Boolean.parseBoolean(datasFromDB.get("TimedEntry")) ? "Yes" : "No";
	    tour.duration = datasFromDB.get("Duration");
	    tour.timeConflict = datasFromDB.get("TimeConflictManagement");
	    if(StringUtil.notEmpty(tour.timeConflict)){
	    	tour.timeConflict =  Boolean.parseBoolean(tour.timeConflict) ? "Yes" : "No";
	    }
	    tour.limitedCapacity = datasFromDB.get("LimitedCapacity");
	    if(StringUtil.notEmpty(tour.limitedCapacity)){
	    	tour.limitedCapacity =  Boolean.parseBoolean(tour.limitedCapacity) ? "Yes" : "No";
	    }
	    tour.multiDay = datasFromDB.get("MultiDay");
	    if(StringUtil.notEmpty(tour.multiDay)){
	    	tour.multiDay =  Boolean.parseBoolean(tour.multiDay) ? "Yes" : "No";
	    }

	    if(tour.multiDay.equalsIgnoreCase("Yes")){
	    	tour.validDays = datasFromDB.get("ValidDays");
	    	tour.entryDays = datasFromDB.get("EntryDays");
	    }
	    tour.capacity = datasFromDB.get("Capacity");
	    tour.longDescription = datasFromDB.get("LongDescription");
	    tour.information = datasFromDB.get("ImportantInfo");
	    
	    String tempTourDaysOfWeeks[] = datasFromDB.get("TourDaysOfWeek").split(",");
	    for(int i = 0; i < tempTourDaysOfWeeks.length; i ++){
	    	if(tempTourDaysOfWeeks[i].trim().length() > 0) {
	    		tour.daysOfWeek.put(tempTourDaysOfWeeks[i].trim(), true);
	    	}
	    }
	    String individualTicketTypes = datasFromDB.get("IndividualTicketTypes").trim();
	    String organizationTicketTypes = datasFromDB.get("OrganizationTicketTypes").trim();
	    
	    tour.individualTicketTypes = individualTicketTypes.length()>0?individualTicketTypes.split(","):new String[]{};
	    tour.organizationTicketTypes = organizationTicketTypes.length()>0?organizationTicketTypes.split(","):new String[]{};
	    
	    //minimum individual tickets info
	    String ticketsInfo = datasFromDB.get("MinIndividualTickets");
	    tour.minIndividualTickets = this.getTicketsInfo(ticketsInfo);
	    
	    //maximum individual tickets info
	    ticketsInfo = datasFromDB.get("MaxIndividualTickets");
	    tour.maxIndividualTickets = this.getTicketsInfo(ticketsInfo);
	    
	    //minimum organization tickets info
	    ticketsInfo = datasFromDB.get("MinOrgTickets");
	    tour.minOrgTickets = this.getTicketsInfo(ticketsInfo);
	    
	 	//maximum organization tickets info
	    ticketsInfo = datasFromDB.get("MaxOrgTickets");
	    tour.maxOrgTickets = this.getTicketsInfo(ticketsInfo);
	    
	    //combo tour detail
	    tour.availableSale = Boolean.parseBoolean(datasFromDB.get("AvailableSaleForComboTour"));
	    String subTours = datasFromDB.get("AssignTourCodesForCombo");
	    if(!StringUtil.isEmpty(subTours)) {
	    	tour.subTourCodes = subTours.split(",");
	    }
	 }
	
	/** Get the min or max tour tickets info. The format of the data from DB is TicketType1, TicketNum1, IsApplyTo1;TicketType2, TicketNum2, IsApplyTo2*/
	private List<TicketType> getTicketsInfo(String info) {
		if (StringUtil.isEmpty(info)) {
			return null;
		}
		
		List<TicketType> tickets = new ArrayList<TicketType> ();
		String[] ticketsInfo = info.split(";");
		for (int i = 0; i < ticketsInfo.length; i++) {
			String[] ticketInfo = ticketsInfo[i].split(",");
			TicketType temp = new TicketType();
			temp.type = ticketInfo[0];
			temp.minTicketNum = ticketInfo[1];
			temp.isApplyToAdvSaleOnly = Boolean.valueOf(ticketInfo[2]);
			tickets.add(temp);
		}
		
		return tickets;
	}
}
