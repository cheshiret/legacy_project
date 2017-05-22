package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrFacilityInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ViewSiteByStatus extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>ViewSiteByStatus</b>
	 * Generated     : <b>Mar 4, 2010 9:28:59 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
	 * @author QA
	 */
  	 public void execute() {
	  	 im.loginInventoryManager(login);
	     im.gotoFacilityDetailsPg(inventory.facilityName);
	     im.gotoInventoryPg();
	     
	     this.searchByStatus(inventory.searchStatus);
	     this.verifySearchResult();
	     
	     im.logoutInvManager();
  	 }
  	 
  	 public void wrapParameters(Object[] args) {
  	     login.url = AwoUtil.getOrmsURL(env);
	     login.contract = "SC Contract";
	     login.location="Administrator/South Carolina State Park Service";
		 
	     inventory.facilityName = "SANTEE";
	     inventory.searchStatus = "Closed";
  	 }
  	 
  	 public void searchByStatus(String status) {
  	    InvMgrFacilityInventoryPage invInventPg = InvMgrFacilityInventoryPage.getInstance();
  	    
  	    invInventPg.selectInventoryStatus(status);
        invInventPg.selectSiteSpecific("All");
        invInventPg.selectSiteType("All");
        invInventPg.clickGo();
        invInventPg.waitLoading();
  	 }
  	 
  	 public void verifySearchResult() {
  	    InvMgrFacilityInventoryPage invInventPg = InvMgrFacilityInventoryPage.getInstance();
  	    List<String> status = invInventPg.getStatus();
  	    
  	    for(int i = 0; i < status.size(); i++){
  	       if(!status.get(i).toString().equals(inventory.searchStatus)){
  	          throw new ItemNotFoundException("Search result is incorrect");
  	       }
  	    }
  	 }
}

