package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrFacilityInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ViewSiteBySiteNum extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>ViewSiteBySiteNum</b>
	 * Generated     : <b>Mar 4, 2010 7:37:30 AM</b>
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
       
       this.searchInvenByID(inventory.siteNum, inventory.arrivalDate);
       this.verifySearchResult();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
	   inventory.siteNum = "C-006";
	   inventory.arrivalDate = "01-22-2010";
     }
     
     public void searchInvenByID(String sitenum, String arrivalDate) {
       InvMgrFacilityInventoryPage invInventPg = InvMgrFacilityInventoryPage.getInstance();
       
       invInventPg.setSiteNumber(sitenum);
       invInventPg.setArrivalDate(arrivalDate);
       invInventPg.selectInventoryStatus("All");
       invInventPg.selectSiteSpecific("All");
       invInventPg.selectSiteType("All");
       invInventPg.clickGo();
       invInventPg.waitLoading();
    }
     
     public void verifySearchResult() {
       InvMgrFacilityInventoryPage invInventPg=InvMgrFacilityInventoryPage.getInstance();
       
       if(!invInventPg.getInventoryInfo().matches(".*" + inventory.siteNum + ".+" + inventory.arrivalDate + ".*")){
         throw new ItemNotFoundException("Search result is incorrect!");
       }
     }
}
