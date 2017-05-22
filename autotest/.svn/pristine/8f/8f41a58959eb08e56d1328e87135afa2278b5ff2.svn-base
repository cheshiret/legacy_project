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
 * Prerequisite  : make sure there is no tour booked for the day that you are going to use.
 * Description   : Functional Test Script
 * @author vzhao
 */
public class TourDetails_SoldOut extends RecgovTestCase
{
	/**
	 * Script Name   : <b>TourDetails_SoldOut</b>
	 * Generated     : <b>Mar 7, 2010 9:29:41 PM</b>
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
	  	tourInv.firstTime = "12:00";
	  	vm.deleteTourInventory(tourInv);
		tourInv.firstTime = "8:00";
	  	vm.addTourInventory(tourInv,true,false);
		vm.logoutVenueManager();

		web.invokeURL(url);
		web.signIn(email, pw);
		
		//make a tour reservation to use up the inventory
		web.bookTourIntoCart(ti);
		web.checkOutTourCart(pay); 
		
		//verify sold out status
		web.gotoTourListPg(ti);
		web.verifyTourStatusInTourList("Find Next Avail. Date","Sold out");
		this.verifySoldOutInTourDetails();
		
		web.signOut();
	}
	
	/**
	 * Verify Sold out message displays on tour details page.
	 * Also verify Find Next Available and Find Previous Available link.
	 */
	public void verifySoldOutInTourDetails(){
	  	UwpTourListPage tourList = UwpTourListPage.getInstance();
	  	UwpTourDetailsPage tourDetail = UwpTourDetailsPage.getInstance();
	  	
	  	logger.info("Verify tour status in tour details page.");
	  	tourList.gotoTourDetails(ti.tourName);
	  	tourDetail.waitLoading();
	  	
	  	//update tour date to previous to verify Sold Out msg displays
	  	tourDetail.setTourDate(ti.tourDate);
	  	tourDetail.clickUpdateTourTimes();
	  	tourDetail.waitLoading();
	  	if(!tourDetail.getErrorMsg().equalsIgnoreCase("Sold Out")){
	  	  	throw new ErrorOnPageException("Tour availability is wrong.");
	  	}
	  	
	  	//verify Find Next available in tour details page, will one day after 
	  	//the tour date as we added 3 days inventories.
	  	tourDetail.clickNextAvailable();
	  	tourDetail.waitLoading();
	  	String expectDate = DateFunctions.getDateAfterGivenDay(ti.tourDate,1);
	  	String actualDate = tourDetail.getTourDate();
	  	if(DateFunctions.compareDates(actualDate,expectDate)!=0){
	  	  	throw new ErrorOnPageException("Available date is wrong.");
	  	}else {
	  	  	logger.info("---Next available date is right.");
	  	}
	  	
	  	//verify Find Previous available in tour details page, will one day before 
	  	//the tour date as we added 3 days inventories.
	  	tourDetail.clickPreviousAvailable();
	  	tourDetail.waitLoading();
	  	expectDate = DateFunctions.getDateAfterGivenDay(ti.tourDate,-1);
	  	actualDate = tourDetail.getTourDate();
	  	if(DateFunctions.compareDates(actualDate,expectDate)!=0){
	  	  	throw new ErrorOnPageException("Available date is wrong.");
	  	}else {
	  	  	logger.info("---Previous available date is right.");
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
		tourInv.tourName = "Historic - No Allocation";
		tourInv.startDate = DateFunctions.getDateAfterToday(220);//change date to avoid case conflict
		tourInv.endDate = DateFunctions.getDateAfterGivenDay(tourInv.startDate,2);
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
		ti.tourName = "Historic";
		ti.tourDate = DateFunctions.getDateAfterGivenDay(tourInv.startDate,1);
		ti.ticketNums = tourInv.capacity;
		ti.ticketType = "Adult";
		
		ti.isUnifiedSearch=isUnifiedSearch();
	}
}
