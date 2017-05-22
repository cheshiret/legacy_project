package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignSiteToClosure extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AssignSiteToClosure</b>
	 * Generated     : <b>Mar 4, 2010 3:32:28 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
	 * @author mchen
	 */
     public String siteID="";
     public void execute() {
       im.loginInventoryManager(login);
  	   
  	   im.gotoFacilityDetailsPg(inventory.facilityName);
     
       im.goToSiteListPage();
       siteID = im.createSite(siteAttr);
       im.activeSite(siteID);
       im.gotoSeasonsPg();
       
       im.assignSiteToClosure(closure, siteAttr.siteCode);
       this.verifyAssignToClosure(siteAttr.siteCode);
       
       im.gotoSiteDetailPage(siteID, false, false);
       im.deleteSite(true);
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
       closure.closureID = "37976369";
       siteAttr.siteCode = Integer.toString(DataBaseFunctions.getEmailSequence()); //use email sequence as a unique site code
	   siteAttr.siteName = "Qa auto test";
	   siteAttr.description = "qa auto test";
	   siteAttr.lookingForCategory = "RV Site";
     }
     
     public void verifyAssignToClosure(String siteNum) {
 	    InvMgrClosuresSitesPage closureSitesPg = InvMgrClosuresSitesPage.getInstance();
 	    
 	    closureSitesPg.searchSiteByCode(siteNum);
 	    closureSitesPg.waitLoading();
 	    
 	    if(!closureSitesPg.getAssignStatus().equals("Yes")) {
 	      throw new ItemNotFoundException("Assigned site to closure failed");
 	    }
 	    
 	    closureSitesPg.selectAll();
 	    closureSitesPg.clickRemove();
 	    closureSitesPg.waitLoading();
 	 }
}

