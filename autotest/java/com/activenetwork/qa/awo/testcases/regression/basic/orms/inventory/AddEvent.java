package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrEventsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddEvent extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddEvent</b>
	 * Generated     : <b>Mar 4, 2010 10:02:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
	 * @author QA
	 */
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       
       im.gotoServicePage();
       event.eventID = im.addEvent(event);
       this.checkAddEvent();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
	   event.eventName = "qa auto test";
	   event.eventDescription = "qa auto test";
	   event.publishDate = DateFunctions.getDateAfterToday(-5);
	   event.eventStart = DateFunctions.getToday();
	   event.eventEnd = DateFunctions.getDateAfterToday(30);
     }
     
     public void checkAddEvent() {
       InvMgrEventsPage invEventPg=InvMgrEventsPage.getInstance();
       ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
       
       if(! invEventPg.checkEventExist(event.eventID)) {
          throw new ItemNotFoundException("Event did not exist!");
       }
       invEventPg.selectEventCheckbox(event.eventID);
       invEventPg.clickRemove();
       ajax.waitLoading();
       if(confirmDialogPg.exists()){
    	   confirmDialogPg.clickOK();
    	   ajax.waitLoading();
       }
       invEventPg.waitLoading();
     }
}

