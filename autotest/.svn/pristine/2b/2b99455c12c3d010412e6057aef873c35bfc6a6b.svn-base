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
public class AddNewNssGroup extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddNewNssGroup</b>
	 * Generated     : <b>Mar 2, 2010 5:37:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/02
	 * @author QA
	 */
     public String siteID="";
     
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.gotoNonSiteSpecificGroupsPage();
       
       siteID = im.createNonSiteSpecGroup(siteAttr);
       im.gotoSiteDetailPage(siteID, true, false);
       this.veirfyNSSGroup();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
	   siteAttr.siteCode = "1236";
	   siteAttr.siteName = "qa auto test 1236";
	   siteAttr.description = "qa auto test 1236";
	   siteAttr.lookingForCategory = "RV Site,Trailer Site";
     }
     
     public void veirfyNSSGroup() {
       InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
       InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
       
       if(! invSiteDetailPg.getSiteCode().equals(siteAttr.siteCode) || ! invSiteDetailPg.getSiteName().equals(siteAttr.siteName) || ! invSiteDetailPg.getDescription().equals(siteAttr.description)) {
           throw new ItemNotFoundException("NSS group created failed!");
       }
       
       invSiteDetailPg.clickDeleteSite();
       invSiteDetailPg.waitLoading();
       invSiteDetailPg.clickOK();
       invSitePg.waitLoading();
     }
}

