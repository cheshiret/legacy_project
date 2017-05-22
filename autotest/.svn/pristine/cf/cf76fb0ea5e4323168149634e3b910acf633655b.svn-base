package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ModifySiteAttribute extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>ModifySiteAttribute</b>
	 * Generated     : <b>Mar 2, 2010 5:16:22 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/02
	 * @author QA
	 */
	 private String siteID = "";
     private String description = "";
     private InvMgrSitesPage siteListPg = InvMgrSitesPage.getInstance();
     
  	 public void execute() {
	  	 im.loginInventoryManager(login);
	     im.gotoFacilityDetailsPg(inventory.facilityName);
	     im.goToSiteListPage();
	     
	     if(!siteListPg.isSiteCodeExisting(siteAttr.siteCode)){
		     siteID=im.createSite(siteAttr); 
	     }else{
	    	 siteID=im.getSiteIDs(new String[]{siteAttr.siteCode}, siteListPg)[0];
	     }
	     
	     im.gotoSiteDetailPage(siteID, false, false);
	     this.modifyDescription(description);
	     
	     im.logoutInvManager();
  	 }
  	 
  	 public void wrapParameters(Object[] args) {
	  	 login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "SC Contract";
		 login.location="Administrator/South Carolina State Park Service";
		 
		 inventory.facilityName = "SANTEE";
		 
		 siteAttr.siteCode = "1235";
		 siteAttr.siteName = "Qa auto test 1234";
		 siteAttr.description = "qa auto test 1234";
		 siteAttr.lookingForCategory = "RV Site";
		 siteAttr.parentLoop = "Cabin Area";
		 description = "qa auto test"+DateFunctions.getCurrentTime();
  	 }
  	 
  	 public void modifyDescription(String des) {
  	     InvMgrSiteDetailPage invSiteDetailPg=InvMgrSiteDetailPage.getInstance();
  	     InvMgrSitesPage invSitePg=InvMgrSitesPage.getInstance();
  	     
  	     invSiteDetailPg.setDescription(des);
  	     invSiteDetailPg.clickOK();
  	     invSitePg.waitLoading();
  	     
  	     invSitePg.searchSite("Site ID", siteID); 
		 invSitePg.waitLoading();
		  
		 invSitePg.clickSiteID(siteID);
  	     invSiteDetailPg.waitLoading();
  	     
  	     if(!invSiteDetailPg.getDescription().equals(des)){
  	        throw new ItemNotFoundException("Modified site info failed!");
  	     }
  	     
	  	 invSiteDetailPg.clickDeleteSite();
	     invSiteDetailPg.waitLoading();
	     invSiteDetailPg.clickOK();
	     invSitePg.waitLoading();
  	 }
}

