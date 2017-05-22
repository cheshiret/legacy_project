package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.VenueManager;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpTourListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class TourDetails_PartialAvailable extends RecgovTestCase
{
	/**
	 * Script Name   : <b>TourDetails_PartialAvailable</b>
	 * Generated     : <b>Mar 7, 2010 9:30:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/07
	 * @author vzhao
	 */
  	private String email, pw, url;
	private InventoryInfo tourInv;
	private TicketInfo ti;
	private LoginInfo login = new LoginInfo();
	private VenueManager vm = VenueManager.getInstance();

	public void execute() {
		//add new tour inventories
	  	vm.loginVenueManager(login);
	  	vm.addTourInventory(tourInv,true,true);
		vm.logoutVenueManager();

		web.invokeURL(url);
		web.signIn(email, pw);
		
		//verify Partial Available status
		web.gotoTourListPg(ti);
		web.verifyTourStatusInTourList("See Tour Times","Some tickets available");
		this.verifyPartialAvailInTourDetails();
		web.signOut();
	}
	
	/**
	 * Verify tour is partial available in tour details page.
	 */
	public void verifyPartialAvailInTourDetails(){
	  	UwpTourListPage tourList = UwpTourListPage.getInstance();
	  	UwpTourDetailsPage tourDetail = UwpTourDetailsPage.getInstance();
	  	
	  	logger.info("Verify tour status in tour details page.");
	  	tourList.gotoTourDetails(ti.tourName);
	  	tourDetail.waitLoading();
	  	
	  	//verify Partial Available in tour details page
	  	tourDetail.selectTourTime();
	  	tourDetail.setTicketNum(ti.ticketNums);
	  	tourDetail.clickOnBookTour();
	  	tourDetail.waitLoading();
	  	
	  	if(!tourDetail.getErrorMsg().equalsIgnoreCase("Requested Ticket Quantity for " +
	  	    					ti.tourName + " exceeds the Available Quantity.")){
	  	  	throw new ErrorOnPageException("Tour availability is wrong.");
	  	}else {
	  	  	logger.info("---Tour is partial available.");
	  	}
	}

	public void wrapParameters(Object[] param) {
		//venue manager login paras
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "NRRS Contract";
		login.location="NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		
		//Tour inv parameters
		tourInv = new InventoryInfo();
		tourInv.tourName = "River Styx - No Allocation";
		tourInv.startDate = DateFunctions.getDateAfterToday(3);
		tourInv.endDate = tourInv.startDate;
		tourInv.status = "Active";
		tourInv.firstTime = "8:00";
		tourInv.firstTimeStamp = "AM";
		tourInv.capacity = "2";
		
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		ti = new TicketInfo();
		ti.contractCode = "NRSO";
		ti.park = "Mammoth Cave National Park Tours";
		ti.parkId = "77817";
		ti.tourName = "River Styx";
		ti.tourDate = tourInv.startDate;
		ti.ticketNums = Integer.parseInt(tourInv.capacity) + 1 + "";// 1 more than capacity
		
		ti.isUnifiedSearch=isUnifiedSearch();
	}
}
