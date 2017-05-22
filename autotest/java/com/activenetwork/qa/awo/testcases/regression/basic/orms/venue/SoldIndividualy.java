package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue;

import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketAvailabilityPage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class SoldIndividualy extends VenueManagerTestCase
{
	/**
	 * Script Name   : <b>SoldIndividualy</b>
	 * Generated     : <b>Jan 11, 2010 5:35:10 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/01/11
	 * @author mchen
	 */
  	 String tour="qa auto test1";
     public void execute() {
       	 vm.loginVenueManager(login);
       	 this.verifySoldIndividual();
       	 vm.logoutVenueManager();
     }
     
     public void wrapParameters(Object[] param) {
        login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/YOUNG LAKE (SOUTH) CABIN";
     }
     
     public void verifySoldIndividual(){
        VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();
        OrmsTicketAvailabilityPage vmTicketAvailSchPg = OrmsTicketAvailabilityPage.getInstance();
        
        vmHmPg.waitLoading();
        if(vmHmPg.checkHtmlLinkExist(tour)){
           throw new ItemNotFoundException("the "+tour+" should not display in home page");
        }
        
        vmHmPg.clickToday();
        vmTicketAvailSchPg.waitLoading();
        
        if(vmTicketAvailSchPg.checkItemExistInTourList(tour)){
	  	    throw new ItemNotFoundException(tour+" should not exist in dropdown");
	  	}
        
        if(vmTicketAvailSchPg.checkItemExistInResult(tour)){
            throw new ItemNotFoundException(tour+" should not exist in result table");
        }
        
        vmHmPg.waitLoading();
        vmHmPg.clickHome();
        vmHmPg.waitLoading();
        
        vmHmPg.clickTomorrow();
        vmTicketAvailSchPg.waitLoading();
        
        if(vmTicketAvailSchPg.checkItemExistInTourList(tour)){
	  	    throw new ItemNotFoundException(tour+" should not exist in dropdown");
	  	}
        
        if(vmTicketAvailSchPg.checkItemExistInResult(tour)){
            throw new ItemNotFoundException(tour+" should not exist in result table");
        }
     }
}

