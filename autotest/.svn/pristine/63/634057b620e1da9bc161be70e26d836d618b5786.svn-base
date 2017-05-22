package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrFacilityInventoryPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrTicketListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ViewTicketInventory extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>ViewTicketInventory</b>
	 * Generated     : <b>Mar 5, 2010 3:35:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/05
	 * @author QA
	 */
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       
       im.gotoInventoryPg();
       this.viewTickerByStatus(ticket.orderStatus);
       
       im.logoutInvManager();
       
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "NRRS Contract";
	   login.location="Administrator/NRRS";
		 
	   inventory.facilityName = "YOUNG LAKE (SOUTH) CABIN";
	   ticket.orderStatus = "Booked";
     }
     
     public void viewTickerByStatus(String status) {
       InvMgrFacilityInventoryPage invInvenPg = InvMgrFacilityInventoryPage.getInstance();
       InvMgrTicketListPage invTicketPg = InvMgrTicketListPage.getInstance();
       
       invInvenPg.clickTicketList();
       invTicketPg.waitLoading();
       invTicketPg.setStartDate("");
       invTicketPg.setEndDate("");
       invTicketPg.selectStatus(status);
       invTicketPg.clickSearch();
       invTicketPg.waitLoading();
       
       for(int i = 0; i < invTicketPg.getStatus().size(); i++){
         if(!invTicketPg.getStatus().get(i).toString().equals(status)){
           throw new ItemNotFoundException("Search result is incorrect");
         }
       }
     }
}

