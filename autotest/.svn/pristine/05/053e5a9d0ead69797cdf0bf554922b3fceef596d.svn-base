package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class MoveSiteToAnotherLoop extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>MoveSiteToAnotherLoop</b>
	 * Generated     : <b>Mar 2, 2010 4:47:12 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/02
	 * @author QA
	 */
     String siteID = "";
     String newLoop = "";
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       
       im.goToSiteListPage();
       siteID=im.createSite(siteAttr);
       im.gotoSiteDetailPage(siteID, false, false);
       this.modifyParentLoop(newLoop);
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
         login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "SC Contract";
		 login.location="Administrator/South Carolina State Park Service";
		 
		 inventory.facilityName = "SANTEE";
		 
		 siteAttr.siteCode = "13"+StringUtil.getRandomString(4, true);
		 siteAttr.siteName = "Qa auto test 1234";
		 siteAttr.description = "qa auto test 1234";
		 siteAttr.lookingForCategory = "RV Site";
		 siteAttr.parentLoop = "Cabin Area";
		 newLoop = "Tent Loop";
     }
     
     public void modifyParentLoop(String loop) {
        InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
        InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
        
        invSiteDetailPg.selectParentLoop(loop);
        invSiteDetailPg.clickApply();
        invSiteDetailPg.waitLoading();
        invSiteDetailPg.clickOK();
        invSiteDetailPg.waitLoading();
        
        if(!invSiteDetailPg.getParentLoop().equalsIgnoreCase(loop)) {
          throw new ItemNotFoundException("Move site to another loop failed!");
        }
        
        invSiteDetailPg.clickDeleteSite();
        invSiteDetailPg.waitLoading();
        invSiteDetailPg.clickOK();
        invSitePg.waitLoading();
     }
}

