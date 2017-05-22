package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class CreateNewSite extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>CreateNewSite</b>
	 * Generated     : <b>Mar 2, 2010 3:03:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/02
	 * @author mchen
	 */
     String siteID="";
     public void execute(){
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       
       im.goToSiteListPage();
       siteID=im.createSite(siteAttr);
       im.gotoSiteDetailPage(siteID, false, false);
       this.verifySiteDetails();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
         login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "SC Contract";
		 login.location="Administrator/South Carolina State Park Service";
		 
		 inventory.facilityName="SANTEE";
		 
		 siteAttr.siteCode="1234";
		 siteAttr.siteName="Qa auto test";
		 siteAttr.description="qa auto test";
		 siteAttr.lookingForCategory="RV Site";
     }
     
     public void verifySiteDetails(){
         InvMgrSiteDetailPage invSiteDetailPg=InvMgrSiteDetailPage.getInstance();
         InvMgrSitesPage invSitePg=InvMgrSitesPage.getInstance();
         
         if(!invSiteDetailPg.getSiteCode().equals(siteAttr.siteCode)||!invSiteDetailPg.getSiteName().equals(siteAttr.siteName)||!invSiteDetailPg.getDescription().equals(siteAttr.description)){
            throw new ItemNotFoundException("The new site info is incorrect");
         }
         
         invSiteDetailPg.clickDeleteSite();
         invSiteDetailPg.waitLoading();
         invSiteDetailPg.clickOK();
         invSitePg.waitLoading();
     }
}

