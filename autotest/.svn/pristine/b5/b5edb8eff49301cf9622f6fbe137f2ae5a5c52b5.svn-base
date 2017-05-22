package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketAvailabilityPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Prerequisite:  1: setup a combTest is a unavailable for sale tour
 * 				  2: setup a comboTour is a available for sale tour
 * 				  3: there is at least one tour for the selected Contract&Location 
 * Description   : Functional Test Script
 * @author QA
 */
public class AvailableForSaleTour extends VenueManagerTestCase
{
	/**
	 * Script Name   : <b>AvailableForSaleTour</b>
	 * Generated     : <b>Jan 11, 2010 2:18:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/01/11
	 * @author mchen
	 * @updated by bzhang 2011/05/24
	 */
  	public void execute() {
	  	vm.loginVenueManager(login);
	  	this.verifyAvailableForSaleTour();
	  	this.verifyTourSelectionBoxDefaultValue();
		vm.logoutVenueManager();
  	}
  	
  	public void wrapParameters(Object[] param) {
	  	login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/YOUNG LAKE (SOUTH) CABIN";
  	}
  	
  	/**
  	 * Verify whether the available tour dispalyed
  	 *
  	 */
  	public void verifyAvailableForSaleTour(){
  	  	VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
  	  	OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage.getInstance();
  	  	
  	  	vmHmPg.waitLoading();
  	  	if(vmHmPg.checkHtmlLinkExist("combTest")){     //"combTest is a unavailable for sale tour"
  	  	   throw new ItemNotFoundException("combTest should not show in home page");
  	  	}
  	  	if(!vmHmPg.checkHtmlLinkExist("comboTour")){    //comboTour is a available for sale tour
  	  	   throw new ItemNotFoundException("comboTour is not available");
  	  	}
  	  	vmHmPg.clickTomorrow();
  	  	vmTicketAvailSchPg.waitLoading();
  	  	if(!vmTicketAvailSchPg.checkItemExistInTourList("comboTour")){
  	  	    throw new ItemNotFoundException("comboTour should exist in dropdown");
  	  	}
  	  	if(vmTicketAvailSchPg.checkItemExistInTourList("combTest")){
  	  	    throw new ItemNotFoundException("combTest should not exist in dropdown");
  	  	}
  	}
  	
  	/**
  	 * Verify Tour Selection Box all Tours should be selected by default 
  	 */
  	public void verifyTourSelectionBoxDefaultValue(){
  		OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage.getInstance();
  		
  		if(!vmTicketAvailSchPg.isAllItemsSelectedInTourList()){
  			throw new ErrorOnDataException("Ticket Availability Page should load with all Tours in the Tour Selection Box selected by default");
  		}
  	}
}

